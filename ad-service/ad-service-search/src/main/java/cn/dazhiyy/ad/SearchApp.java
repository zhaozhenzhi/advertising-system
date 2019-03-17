package cn.dazhiyy.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad
 * @className SearchApp
 * @description 所以启动类
 * @date 2019/3/17 20:06
 */
@EnableFeignClients
@EnableEurekaClient
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class SearchApp {

    public static void main(String[] args) {
        SpringApplication.run(SearchApp.class,args);
    }
}
