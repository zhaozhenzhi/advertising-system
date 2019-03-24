package cn.dazhiyy.ad.mysql.template;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.mysql
 * @className JsonTable
 * @description TODO
 * @date 2019/3/22 15:20
 */
@Slf4j
@Data
public class Table {
    private String tableName;

    private Integer level;

    private List<Column> insert;
    private List<Column> update;
    private List<Column> delete;

}
