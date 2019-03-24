package cn.dazhiyy.ad.listener;

import cn.dazhiyy.ad.config.TableTempalteConfig;
import com.github.shyiko.mysql.binlog.event.EventType;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.listener
 * @className BInlogData
 * @description TODO
 * @date 2019/3/24 11:38
 */
@Data
public class BinlogData {

    private TableTempalteConfig tempalteConfig;

    private EventType eventType;

    private List<Map<String, String>> after;

    private List<Map<String, String>> before;
}
