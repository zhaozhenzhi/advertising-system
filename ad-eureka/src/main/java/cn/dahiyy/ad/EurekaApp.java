package cn.dahiyy.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dahiyy.ad
 * @className EurekaApp
 * @description TODO
 * @date 2019/3/16 09:23
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApp {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApp.class,args);
    }
}
