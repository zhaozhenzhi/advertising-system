package cn.dazhiyy.ad.listener.handler;

import cn.dazhiyy.ad.config.DataBaseTemplateConfig;
import cn.dazhiyy.ad.config.MysqlTemplateConfig;
import cn.dazhiyy.ad.config.TableTempalteConfig;
import cn.dazhiyy.ad.handler.BinlogEventHandler;
import cn.dazhiyy.ad.index.OpType;
import cn.dazhiyy.ad.listener.BinlogData;
import cn.dazhiyy.ad.listener.BinlogListenerContext;
import cn.dazhiyy.ad.listener.IListener;
import cn.dazhiyy.ad.mysql.UserTemplateInfo;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.Event;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.EventType;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.listener.handler
 * @className BinLogUpdateEventHandler
 * @description TODO
 * @date 2019/3/24 10:44
 */
@Slf4j
@Component
public class BinLogCRUEventHandler implements BinlogEventHandler {

    @Autowired
    private UserTemplateInfo userTemplateInfo;

    @Override
    public void onHandler(Event event, BinlogListenerContext context) {
        BinlogListenerContext.TableEeventData tableEeventData = getTableEeventData(event, context);
        if (tableEeventData == null) {
            return;
        }
        MysqlTemplateConfig tempalte = userTemplateInfo.getTempalte();
        DataBaseTemplateConfig config = tempalte.getDatabaseMap().get(tableEeventData.getDatabase());
        if (config == null) {
            //不需要处理
            return;
        }

        TableTempalteConfig tempalteConfig = config.getTableMap().get(tableEeventData.getTable());
        if (tempalteConfig == null) {
            return;
        }
        OpType to = OpType.to(event.getHeader().getEventType());
        BinlogData binlogData = buildBinlogData(event, event.getData(), tempalteConfig,to );
        Map<String, IListener> listenerMap = context.getListenerMap();
        IListener iListener = listenerMap.get(tableEeventData.getDatabase() + "-" + tableEeventData.getTable());
        iListener.onEvent(binlogData,to);
    }

    private BinlogListenerContext.TableEeventData getTableEeventData(Event event, BinlogListenerContext context) {
        EventData eventData =  event.getData();
        if (event.getData() instanceof WriteRowsEventData) {
            WriteRowsEventData data = event.getData();
            return context.getTableMap().get(data.getTableId());
        }

        if (eventData instanceof UpdateRowsEventData) {
            UpdateRowsEventData data = event.getData();
            return context.getTableMap().get(data.getTableId());
        }

        if (eventData instanceof DeleteRowsEventData) {
            DeleteRowsEventData data = event.getData();
            return context.getTableMap().get(data.getTableId());
        }

        //获得表名
        return null;
    }

    private List<Serializable[]> getAfterValues(EventData eventData) {

        if (eventData instanceof WriteRowsEventData) {
            return ((WriteRowsEventData) eventData).getRows();
        }

        if (eventData instanceof UpdateRowsEventData) {
            return ((UpdateRowsEventData) eventData).getRows().stream()
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
        }

        if (eventData instanceof DeleteRowsEventData) {
            return ((DeleteRowsEventData) eventData).getRows();
        }

        return Collections.emptyList();
    }

    private BinlogData buildBinlogData(Event event, EventData data, TableTempalteConfig tempalteConfig,OpType op) {
        //是用户配置的表和库
        List<Serializable[]> collect = getAfterValues(data);

        List<Map<String, String>> afterMapList = Lists.newArrayList();
        for (Serializable[] after : collect) {
            Map<String, String> afterMap = Maps.newHashMap();

            int colLen = after.length;

            for (int ix = 0; ix < colLen; ++ix) {

                // 取出当前位置对应的列名
                Map<Integer, String> filedNameIdMap = tempalteConfig.getFiledNameIdMap();
                String colName = filedNameIdMap.get(ix+1);

                // 如果没有则说明不关心这个列
                if (null == colName) {
                    log.debug("ignore position: {}", ix+1);
                    continue;
                } else {
                    Map<OpType, List<String>> opTypeFieldValueMap = tempalteConfig.getOpTypeFieldValueMap();
                    List<String> fileds = opTypeFieldValueMap.get(op);
                    if (!fileds.contains(colName)) {
                        log.debug("ignore position: {}", ix+1);
                        continue;
                    }
                }

                String colValue = after[ix].toString();
                afterMap.put(colName, colValue);
            }

            afterMapList.add(afterMap);
        }

        BinlogData rowData = new BinlogData();
        rowData.setEventType(event.getHeader().getEventType());
        rowData.setAfter(afterMapList);
        rowData.setTempalteConfig(tempalteConfig);
        return rowData;
    }


    @Override
    public Boolean isSupport(Event event) {
        if (EventType.EXT_UPDATE_ROWS.equals(event.getHeader().getEventType())
            ||EventType.EXT_WRITE_ROWS.equals(event.getHeader().getEventType())
            ||EventType.EXT_DELETE_ROWS.equals(event.getHeader().getEventType())) {
            log.info("开始处理{}类型",event.getHeader().getEventType());
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
