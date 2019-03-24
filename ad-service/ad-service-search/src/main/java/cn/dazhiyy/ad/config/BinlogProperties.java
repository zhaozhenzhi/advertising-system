package cn.dazhiyy.ad.config;


import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.config
 * @className BinlogConfig
 * @description TODO
 * @date 2019/3/23 17:49
 */
@Component
@ConfigurationProperties(prefix = "binlog.mysql")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BinlogProperties {

    private Map<String,DbInfoProperties> db = Maps.newHashMap();

    private String templateName;

    private String templatePath;

}
