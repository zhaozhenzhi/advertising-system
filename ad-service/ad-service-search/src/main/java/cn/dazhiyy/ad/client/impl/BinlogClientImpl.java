package cn.dazhiyy.ad.client.impl;

import cn.dazhiyy.ad.client.BinlogClient;
import cn.dazhiyy.ad.config.BinlogProperties;
import cn.dazhiyy.ad.config.DbInfoProperties;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.client
 * @className BinlogClientImpl
 * @description binlog客户端
 * @date 2019/3/23 17:47
 */
@Slf4j
@Component
public class BinlogClientImpl implements BinlogClient {

    @Getter
    private Map<String,BinaryLogClient> clients = Maps.newConcurrentMap();

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    @Autowired
    private BinlogProperties config;

    @Autowired
    private BinaryLogClient.EventListener listener;

    @Override
    public void connect() {
        Map<String, DbInfoProperties> db = config.getDb();
        for (Map.Entry<String, DbInfoProperties> entry : db.entrySet()) {
            DbInfoProperties dbInfoProperties = entry.getValue();
            executor.execute(() -> run(entry.getKey(), dbInfoProperties));
        }
    }

    private void run(String key, DbInfoProperties dbInfoProperties) {
        BinaryLogClient client = new BinaryLogClient(dbInfoProperties.getHost(),
                dbInfoProperties.getPort(),dbInfoProperties.getUsername(),dbInfoProperties.getPassword());
        clients.put(key,client);

        if (!StringUtils.isEmpty(dbInfoProperties.getBinlogName()) &&
                !dbInfoProperties.getPosition().equals(-1L)) {
            client.setBinlogFilename(dbInfoProperties.getBinlogName());
            client.setBinlogPosition(dbInfoProperties.getPosition());
        }

        client.registerEventListener(listener);

        try {
            log.info("connecting to mysql start");
            client.connect();
            log.info("connecting to mysql done");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void connect(String name) {
        DbInfoProperties dbInfoProperties = config.getDb().get(name);
        executor.execute(() -> run(name, dbInfoProperties));
    }

    @Override
    public void close() {
        for (String name : clients.keySet()) {
            close(name);
        }
    }

    @Override
    public void close(String name) {
        BinaryLogClient client = clients.get(name);
        try {
            log.info("close to mysql start");
            client.disconnect();
            log.info("close to mysql done");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
