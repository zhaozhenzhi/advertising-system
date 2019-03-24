package cn.dazhiyy.ad.config;

import lombok.Data;

import java.util.Map;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.config
 * @className MysqlTemplateConfig
 * @description TODO
 * @date 2019/3/23 00:36
 */
@Data
public class MysqlTemplateConfig {
    /**
     * String -> 数据库名称
     * DataBaseTemplateConfig -> 数据库信息
     */
    private Map<String,DataBaseTemplateConfig> databaseMap;

}
