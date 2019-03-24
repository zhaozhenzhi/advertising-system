package cn.dazhiyy.ad.mysql;

import cn.dazhiyy.ad.config.BinlogProperties;
import cn.dazhiyy.ad.config.MysqlTemplateConfig;
import cn.dazhiyy.ad.mysql.template.TemplateJson;
import cn.dazhiyy.ad.template.TemplateInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.mysql
 * @className UserTemplateInfo
 * @description TODO
 * @date 2019/3/24 11:52
 */
@Slf4j
@Component
public class UserTemplateInfo implements TemplateInfo<MysqlTemplateConfig> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BinlogProperties binlogProperties;

    private MysqlTemplateLoader mysqlTemplateLoader;

    private ConfigTemplateParse configTemplateParse;

    private MysqlTemplateConfig mysqlTemplateConfig;

    @PostConstruct
    public void init(){
        configTemplateParse = new ConfigTemplateParse(jdbcTemplate);
        mysqlTemplateLoader = new MysqlTemplateLoader();
    }

    @Override
    public MysqlTemplateConfig getTempalte() {
        if (mysqlTemplateConfig == null) {
            String templateName = binlogProperties.getTemplateName();
            String name =  StringUtils.isBlank(templateName)?"template.json": templateName;
            TemplateJson loader = mysqlTemplateLoader.loader(name);
            mysqlTemplateConfig = configTemplateParse.parse(loader);
        }
        return mysqlTemplateConfig;
    }
}
