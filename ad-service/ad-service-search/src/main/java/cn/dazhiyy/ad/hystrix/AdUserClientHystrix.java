package cn.dazhiyy.ad.hystrix;

import cn.dazhiyy.ad.client.AdUserClient;
import cn.dazhiyy.ad.common.enums.SystemErrorCodeEnum;
import cn.dazhiyy.ad.common.vo.CommonResponseVO;
import cn.dazhiyy.ad.vo.AdUserResponseVO;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.hystrix
 * @className AdUserClientHystrix
 * @description TODO
 * @date 2019/3/17 23:41
 */
public class AdUserClientHystrix implements AdUserClient {

    @Override
    public CommonResponseVO<AdUserResponseVO> findAdUserInfoByUsername(String userName) {
        return new CommonResponseVO<AdUserResponseVO>(CommonResponseVO.Type.error,SystemErrorCodeEnum.NO_KNOW_ERROR.getCode(), SystemErrorCodeEnum.NO_KNOW_ERROR.getMsg());
    }
}
