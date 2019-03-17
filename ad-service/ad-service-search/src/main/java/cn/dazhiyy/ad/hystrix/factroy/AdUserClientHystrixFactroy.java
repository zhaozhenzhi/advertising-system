package cn.dazhiyy.ad.hystrix.factroy;

import cn.dazhiyy.ad.client.AdUserClient;
import cn.dazhiyy.ad.hystrix.AdUserClientHystrix;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.hystrix.factroy
 * @className AdUserClientHystrixFactroy
 * @description TODO
 * @date 2019/3/18 00:14
 */
@Component
public class AdUserClientHystrixFactroy implements FallbackFactory<AdUserClient> {


    @Override
    public AdUserClient create(Throwable throwable) {
        return new AdUserClientHystrix();
    }
}
