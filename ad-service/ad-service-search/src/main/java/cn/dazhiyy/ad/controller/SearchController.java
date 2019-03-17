package cn.dazhiyy.ad.controller;

import cn.dazhiyy.ad.client.AdUserClient;
import cn.dazhiyy.ad.common.vo.CommonResponseVO;
import cn.dazhiyy.ad.vo.AdUserResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.controller
 * @className SearchController
 * @description TODO
 * @date 2019/3/17 23:01
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AdUserClient adUserClient;

    @GetMapping("/userInfo")
    public CommonResponseVO<AdUserResponseVO> findUserInfo(String userName){
        return restTemplate.getForEntity("http://ad-service-sponsor/ad-service-sponsor/adUser/findAdUserInfoByUsername?userName={1}",
                CommonResponseVO.class,userName).getBody();
    }

    @GetMapping("/userInfoFeign")
    public CommonResponseVO<AdUserResponseVO> findUserInfoFeign(String userName){
        return adUserClient.findAdUserInfoByUsername(userName);
    }

}
