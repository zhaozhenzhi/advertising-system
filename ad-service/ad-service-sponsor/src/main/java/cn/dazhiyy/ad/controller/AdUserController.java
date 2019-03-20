package cn.dazhiyy.ad.controller;

import cn.dazhiyy.ad.service.impl.AdUserServiceImpl;
import cn.dazhiyy.ad.vo.AdUserResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.controller
 * @className AdUserController
 * @description TODO
 * @date 2019/3/17 22:35
 */
@RestController
@RequestMapping("/adUser")
public class AdUserController {

    @Autowired
    private AdUserServiceImpl adUserService;

    @ResponseBody
    @GetMapping("/findAdUserInfoByUsername")
    public AdUserResponseVO findAdUserInfoByUsername(String userName){
        return adUserService.findByUsername(userName);
    }


}
