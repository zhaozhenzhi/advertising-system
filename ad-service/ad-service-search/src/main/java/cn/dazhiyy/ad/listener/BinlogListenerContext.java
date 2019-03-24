package cn.dazhiyy.ad.listener;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.listener
 * @className BinlogListenerContext
 * @description binlog 事件上下文对象
 * @date 2019/3/24 09:49
 */
@Data
public class BinlogListenerContext {

    private Map<Long,TableEeventData> tableMap = Maps.newConcurrentMap();
    private Map<String,Long> tableIdMap = Maps.newConcurrentMap();
    private Map<String,TableEeventData> tableInfoMap = Maps.newConcurrentMap();

    private Map<String,IListener > listenerMap = Maps.newHashMap();
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class TableEeventData{
        private Long tableId;
        private String database;
        private String table;
    }

    public TableEeventData getInstantTableEeventData(){
        return new TableEeventData();
    }

    public TableEeventData getInstantTableEeventData(Long tableId, String database, String table){
        return new TableEeventData(tableId,database,table);
    }
}
