package cn.dazhiyy.ad.listener.handler;

import cn.dazhiyy.ad.handler.BinlogEventHandler;
import cn.dazhiyy.ad.listener.BinlogListenerContext;
import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.event.EventType;
import com.github.shyiko.mysql.binlog.event.TableMapEventData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.listener
 * @className BinlogEventHanderImpl
 * @description TODO
 * @date 2019/3/24 09:37
 */
@Slf4j
@Component
public class BinlogTableEventHander implements BinlogEventHandler {

    @Override
    public void onHandler(Event event, BinlogListenerContext context) {
        TableMapEventData data = event.getData();
        //获得context内的容器
        Map<String, Long> tableIdMap = context.getTableIdMap();
        Map<Long, BinlogListenerContext.TableEeventData> tableMap = context.getTableMap();
        Map<String, BinlogListenerContext.TableEeventData> tableInfoMap = context.getTableInfoMap();

        Long tableId = tableIdMap.get(data.getTable());
        BinlogListenerContext.TableEeventData tableEeventData = tableMap.get(data.getTableId());

        if (tableEeventData == null && tableId == null) {
            //没有存储关系
            BinlogListenerContext.TableEeventData tableEevent = context.getInstantTableEeventData(data.getTableId(),data.getDatabase(),data.getTable());
            tableMap.put(data.getTableId(),tableEevent);
            tableIdMap.put(data.getTable(),data.getTableId());
            tableInfoMap.put(data.getTable(),tableEevent);
        } else {
            // 说明关系已经变化
            BinlogListenerContext.TableEeventData table = tableInfoMap.get(data.getTable());
            Long oldId = table.getTableId();
            table.setTableId(data.getTableId());
            tableMap.remove(oldId);
            tableMap.put(data.getTableId(),table);
            tableIdMap.put(data.getTable(),data.getTableId());
        }
        log.info("结束处理{}类型",event.getHeader().getEventType());
    }

    @Override
    public Boolean isSupport(Event event) {
        if (EventType.TABLE_MAP.equals(event.getHeader().getEventType())) {
            log.info("开始处理{}类型",event.getHeader().getEventType());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
