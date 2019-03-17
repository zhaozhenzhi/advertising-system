package cn.dazhiyy.ad.service.impl;

import cn.dazhiyy.ad.common.enums.SystemErrorCodeEnum;
import cn.dazhiyy.ad.common.exception.BizException;
import cn.dazhiyy.ad.dao.AdUserRespository;
import cn.dazhiyy.ad.entity.AdUserEntity;
import cn.dazhiyy.ad.enums.CommonStatus;
import cn.dazhiyy.ad.service.AdUserService;
import cn.dazhiyy.ad.vo.AdUserResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.service.impl
 * @className AdUserServiceImpl
 * @description 用户业务层
 * @date 2019/3/17 22:14
 */
@Slf4j
@Service
public class AdUserServiceImpl implements AdUserService {

    @Autowired
    private AdUserRespository adUserRespository;


    @Override
    public AdUserResponseVO findByUsername(String userName) {
        if (StringUtils.isBlank(userName)) {
            throw new BizException(SystemErrorCodeEnum.USER_NAME_IS_NOT_NULL);
        }
        AdUserEntity entity = adUserRespository.findByUsername(userName);
        if (entity == null) {
            throw new BizException(SystemErrorCodeEnum.USER_IS_EXIST);
        }
        return new AdUserResponseVO(entity.getId(),entity.getUsername(),
                entity.getToken(),entity.getUserStatus(),
                CommonStatus.getMsg(entity.getUserStatus()));
    }
}
