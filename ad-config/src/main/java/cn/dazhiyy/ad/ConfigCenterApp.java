package cn.dazhiyy.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad
 * @className ConfigCenterApp
 * @description TODO
 * @date 2019/3/17 01:10
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigCenterApp {

    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterApp.class,args);
    }
}
