package cn.dazhiyy.ad.listener;

import cn.dazhiyy.ad.handler.BinlogEventHandler;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.listener
 * @className BinlogListener
 * @description TODO
 * @date 2019/3/23 18:20
 */
@Component
public class BinlogListener implements BinaryLogClient.EventListener  {

    @Autowired
    private List<BinlogEventHandler> binHandler;

    private BinlogListenerContext context = new BinlogListenerContext();

    public void registered(String dbName,String tableName,IListener listener){
        Map<String, IListener> listenerMap = context.getListenerMap();
        listenerMap.put(dbName+"-"+tableName,listener);
    }

    @Override
    public void onEvent(Event event) {
        for (BinlogEventHandler handler : binHandler) {
            if (handler.isSupport(event)) {
                handler.onHandler(event,context);
                break;
            }
        }
    }
}
