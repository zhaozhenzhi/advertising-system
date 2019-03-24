package cn.dazhiyy.ad.config;

import lombok.Data;

import java.util.Map;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.config
 * @className DataBaseTemplateConfig
 * @description TODO
 * @date 2019/3/23 00:38
 */
@Data
public class DataBaseTemplateConfig {

    private String databaseName;

    /**
     * string -> 表名
     * tableTempalteConfig -> 表信息
     */
    private Map<String,TableTempalteConfig> tableMap;



}
