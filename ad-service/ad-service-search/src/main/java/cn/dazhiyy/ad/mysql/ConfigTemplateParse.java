package cn.dazhiyy.ad.mysql;

import cn.dazhiyy.ad.common.factory.BeanInstanceFactory;
import cn.dazhiyy.ad.config.DataBaseTemplateConfig;
import cn.dazhiyy.ad.config.MysqlTemplateConfig;
import cn.dazhiyy.ad.config.TableTempalteConfig;
import cn.dazhiyy.ad.index.OpType;
import cn.dazhiyy.ad.mysql.template.Column;
import cn.dazhiyy.ad.mysql.template.Database;
import cn.dazhiyy.ad.mysql.template.Table;
import cn.dazhiyy.ad.mysql.template.TemplateJson;
import cn.dazhiyy.ad.template.TemplateParse;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.mysql
 * @className ConfigTemplatePaser
 * @description TODO
 * @date 2019/3/23 03:12
 */
public class ConfigTemplateParse implements TemplateParse<TemplateJson, MysqlTemplateConfig> {

    private JdbcTemplate jdbcTemplate;

    public ConfigTemplateParse(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public MysqlTemplateConfig parse(TemplateJson resource) {
        MysqlTemplateConfig configTemplate = BeanInstanceFactory.newInstance(MysqlTemplateConfig::new);
        List<Database> databaseList = resource.getDatabaseList();
        Map<String,DataBaseTemplateConfig> databaseMap = Maps.newHashMap();
        for (Database database : databaseList) {
            buildBatabase(databaseMap, database);
        }
        configTemplate.setDatabaseMap(databaseMap);
        return configTemplate;
    }

    /**
     * 构建数据库配置
     *
     * @param databaseMap
     * @param database
     */
    private void buildBatabase(Map<String, DataBaseTemplateConfig> databaseMap, Database database) {
        DataBaseTemplateConfig config = BeanInstanceFactory.newInstance(DataBaseTemplateConfig::new);
        String databaseName = database.getDatabaseName();
        config.setDatabaseName(databaseName);
        List<Table> tableList = database.getTableList();
        //加载表配置
        Map<String, TableTempalteConfig> tableMap = Maps.newHashMap();
        for (Table table : tableList) {
            buildTable(tableMap, table,databaseName);
        }
        config.setTableMap(tableMap);
        databaseMap.put(database.getDatabaseName(),config);
    }

    /**
     * 构建表结构
     *
     * @param tableMap
     * @param table
     */
    private void buildTable(Map<String, TableTempalteConfig> tableMap, Table table,String databaseName) {
        TableTempalteConfig tableConfig = BeanInstanceFactory.newInstance(TableTempalteConfig::new);
        String tableName = table.getTableName();
        tableConfig.setTableName(tableName);
        tableConfig.setLevel(table.getLevel());
        tableConfig.setOpTypeFieldValueMap(buildFiled(table));
        tableConfig.setFiledNameIdMap(buildFiled(tableName,databaseName));
        tableMap.put(tableName,tableConfig);
    }

    /**
     * 查询并封装数据库对应的信息
     *
     * @param tableName
     * @param databaseName
     * @return
     */
    private Map<Integer,String> buildFiled(String tableName,String databaseName) {
        Map<Integer,String> filedNameIdMap = Maps.newHashMap();
        String sql = "SELECT TABLE_SCHEMA ,TABLE_NAME,COLUMN_NAME,ORDINAL_POSITION FROM information_schema.`COLUMNS` WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";

        jdbcTemplate.query(sql,new Object[]{
                databaseName,tableName,
        },(rs, i) -> {
            int pos = rs.getInt("ORDINAL_POSITION");
            String colName = rs.getString("COLUMN_NAME");
            filedNameIdMap.put(pos,colName);
            return null;
        });

        return filedNameIdMap;
    }

    /**
     * 构建字段名
     *
     * @param table
     */
    private Map<OpType, List<String>> buildFiled(Table table) {
        Map<OpType, List<String>> opTypeFieldValueMap = Maps.newHashMap();
        //插入配置
        List<Column> insert = table.getInsert();
        if (CollectionUtils.isNotEmpty(insert)){
            List<String> insertStr = insert.stream().map(Column::getColumn).collect(Collectors.toList());
            opTypeFieldValueMap.put(OpType.ADD,insertStr);
        }

        //更新配置
        List<Column> update = table.getUpdate();
        if (CollectionUtils.isNotEmpty(update)) {
            List<String> updateStr = update.stream().map(Column::getColumn).collect(Collectors.toList());
            opTypeFieldValueMap.put(OpType.UPDATE,updateStr);
        }

        //删除配置
        List<Column> delete = table.getDelete();
        if (CollectionUtils.isNotEmpty(delete)) {
            List<String> deleteStr = delete.stream().map(Column::getColumn).collect(Collectors.toList());
            opTypeFieldValueMap.put(OpType.DELETE,deleteStr);
        }
        return opTypeFieldValueMap;
    }
}
