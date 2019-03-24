package cn.dazhiyy.ad.config;

import lombok.Data;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.config
 * @className DbInfoProperties
 * @description TODO
 * @date 2019/3/23 17:57
 */
@Data
public class DbInfoProperties {

    private String host;
    private Integer port;
    private String  username;
    private String  password;

    private String binlogName;
    private Long position;
}
