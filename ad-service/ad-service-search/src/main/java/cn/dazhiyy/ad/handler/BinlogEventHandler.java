package cn.dazhiyy.ad.handler;

import cn.dazhiyy.ad.listener.BinlogListenerContext;
import com.github.shyiko.mysql.binlog.event.Event;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.handler
 * @className BinlogEventHandler
 * @description TODO
 * @date 2019/3/24 09:37
 */
public interface BinlogEventHandler {

    /**
     * 处理事件的
     *
     * @param event
     * @param context
     */
    void onHandler(Event event, BinlogListenerContext context);

    /**
     * 是否支持
     *
     * @param event
     * @return
     */
    Boolean isSupport(Event event);
}
