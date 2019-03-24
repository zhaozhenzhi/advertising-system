package cn.dazhiyy.ad.listener;

import cn.dazhiyy.ad.config.DataBaseTemplateConfig;
import cn.dazhiyy.ad.config.MysqlTemplateConfig;
import cn.dazhiyy.ad.config.TableTempalteConfig;
import cn.dazhiyy.ad.index.OpType;
import cn.dazhiyy.ad.mysql.UserTemplateInfo;
import cn.dazhiyy.ad.sender.ISender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.listener
 * @className BinlogIListener
 * @description TODO
 * @date 2019/3/24 11:40
 */
@Component
public class BinlogIListener implements IListener {

    @Autowired
    private BinlogListener binlogListener;

    @Autowired
    private UserTemplateInfo userTemplateInfo;

    @Autowired
    private List<ISender<BinlogData>> senders;

    @Override
    @PostConstruct
    public void registered() {
        MysqlTemplateConfig tempalte = userTemplateInfo.getTempalte();
        Map<String, DataBaseTemplateConfig> databaseMap = tempalte.getDatabaseMap();
        for (Map.Entry<String, DataBaseTemplateConfig> entry : databaseMap.entrySet()) {
            DataBaseTemplateConfig value = entry.getValue();
            Map<String, TableTempalteConfig> tableMap = value.getTableMap();
            for (String key : tableMap.keySet()) {
                binlogListener.registered(entry.getKey(),key,this);
            }
        }
    }

    @Override
    public void onEvent(BinlogData binlogData, OpType op) {
        for (ISender<BinlogData> sender : senders) {
            sender.onSender(binlogData,op);
        }
    }
}
