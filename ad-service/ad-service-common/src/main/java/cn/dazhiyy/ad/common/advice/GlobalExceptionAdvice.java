package cn.dazhiyy.ad.common.advice;

import cn.dazhiyy.ad.common.exception.BizException;
import cn.dazhiyy.ad.common.vo.CommonResponseVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.common.advice
 * @className GlobalExceptionAdvice
 * @description 统一的业务异常处理
 * @date 2019/3/16 17:28
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = BizException.class)
    public CommonResponseVO handerBizException(BizException exc){
        if (exc.getErrorCode() == null) {
            return CommonResponseVO.error(exc.getArgs());
        } else if (exc.getErrorCode() !=null && StringUtils.isBlank(exc.getMessage())) {
            return CommonResponseVO.error(exc.getErrorCode(),exc.getArgs());
        } else {
            return CommonResponseVO.error(exc.getErrorCode(),exc.getMessage(),exc.getArgs());
        }
    }
}
