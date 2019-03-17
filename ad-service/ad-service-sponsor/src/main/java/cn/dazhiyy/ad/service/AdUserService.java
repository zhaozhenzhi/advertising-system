package cn.dazhiyy.ad.service;

import cn.dazhiyy.ad.vo.AdUserResponseVO;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.service
 * @className AdUserService
 * @description 用户服务
 * @date 2019/3/17 11:04
 */
public interface AdUserService {

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    AdUserResponseVO findByUsername(String username);

}
