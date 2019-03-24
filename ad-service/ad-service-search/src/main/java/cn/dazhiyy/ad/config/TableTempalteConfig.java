package cn.dazhiyy.ad.config;

import cn.dazhiyy.ad.index.OpType;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.config
 * @className TableTempalteConfig
 * @description TODO
 * @date 2019/3/23 00:41
 */
@Data
public class TableTempalteConfig {

    private String tableName;

    private Integer level;

    /**
     * OpType -> 操作符
     * list -> 操作对应的字段
     */
    private Map<OpType, List<String>> opTypeFieldValueMap;

    /**
     * Integer 数据的字段存储id
     * String 字段名
     *
     */
    private Map<Integer,String> filedNameIdMap;


}
