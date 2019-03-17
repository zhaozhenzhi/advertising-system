package cn.dazhiyy.ad.common.container;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dazhi
 * @projectName dazhi-root
 * @packageName cn.dazhi.xy.fruits.system.container
 * @className ContainerConfig
 * @description 设置web容器 jetty容易
 * @date 2018/10/28 06:58
 */
@Configuration
@ConditionalOnProperty(name = "xy.web.container", havingValue = "jetty")
public class JettyContainerConfig {

    @Bean
    public JettyServletWebServerFactory jettyReactiveWebServerFactory(){
        return new JettyServletWebServerFactory();
    }

}
