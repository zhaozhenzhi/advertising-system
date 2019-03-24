package cn.dazhiyy.ad.mysql.template;

import lombok.Data;

import java.util.List;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.mysql
 * @className TemplateJson
 * @description TODO
 * @date 2019/3/23 00:09
 */
@Data
public class TemplateJson {

    private List<Database> databaseList;
}
