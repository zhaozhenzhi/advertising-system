package cn.dazhiyy.ad.client;

import cn.dazhiyy.ad.common.vo.CommonResponseVO;
import cn.dazhiyy.ad.hystrix.factroy.AdUserClientHystrixFactroy;
import cn.dazhiyy.ad.vo.AdUserResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.client
 * @className AdUserClient
 * @description TODO
 * @date 2019/3/17 23:30
 */
@FeignClient(value = "ad-service-sponsor",fallbackFactory = AdUserClientHystrixFactroy.class)
@RequestMapping("ad-service-sponsor")
public interface AdUserClient {

    /**
     * 调用服务的findAdUserInfoByUsername方法
     *
     * @param userName 用户名
     * @return
     */
    @RequestMapping(value = "/adUser/findAdUserInfoByUsername" ,method = RequestMethod.GET)
    CommonResponseVO<AdUserResponseVO> findAdUserInfoByUsername(@RequestParam("userName") String userName);
}
