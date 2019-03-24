package cn.dazhiyy.ad.mysql.template;

import lombok.Data;

import java.util.List;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.mysql
 * @className Database
 * @description TODO
 * @date 2019/3/23 00:12
 */
@Data
public class Database {

    private String databaseName;

    private List<Table> tableList;
}
