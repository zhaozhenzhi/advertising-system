package cn.dazhiyy.ad.common.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.common.config
 * @className RestTemplateConfig
 * @description TODO
 * @date 2019/3/17 22:56
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
