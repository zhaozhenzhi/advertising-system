package cn.dazhiyy.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad
 * @className ZuulGetawayApp
 * @description TODO
 * @date 2019/3/16 15:12
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulGetawayApp {

    public static void main(String[] args) {
        SpringApplication.run(ZuulGetawayApp.class,args);
    }
}
