package cn.dazhiyy.ad.common.exception;

import cn.dazhiyy.ad.common.enums.SystemErrorCodeEnum;
import lombok.Data;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.common.exception
 * @className BizException
 * @description 统一的业务异常
 * @date 2019/3/16 17:15
 */
@Data
public class BizException extends RuntimeException{

    /**
     * 错误异常Code SystemErrorCodeEnum code 一一对应
     */
    private Integer errorCode;

    /**
     * 是否转换成当地文字
     */
    private Boolean isLocal = Boolean.FALSE;

    /**
     * 系统信息
     */
    private String message;

    /**
     * 异常参数
     */
    private Object[] args;

    public BizException(Object... args){
        this.errorCode =SystemErrorCodeEnum.FAILURE.getCode();
        this.message = SystemErrorCodeEnum.FAILURE.getMsg();
        this.args= args;
    }

    public BizException(Boolean isLocal,Object... args){
        this.errorCode =SystemErrorCodeEnum.FAILURE.getCode();
        this.message = SystemErrorCodeEnum.FAILURE.getMsg();
        this.isLocal = isLocal;
        this.args= args;
    }

    public BizException(Integer errorCode,Object... args){
        this.errorCode = errorCode;
        this.message = SystemErrorCodeEnum.getMsg(errorCode);
        this.args= args;
    }

    public BizException(Integer errorCode,Boolean isLocal,Object... args){
        this.errorCode = errorCode;
        this.message = SystemErrorCodeEnum.getMsg(errorCode);
        this.isLocal = isLocal;
        this.args= args;
    }

    public BizException(Integer errorCode,String message,Object... args){
        this.errorCode = errorCode;
        this.message = message;
        this.args= args;
    }

    public BizException(Integer errorCode,String message,Boolean isLocal,Object... args){
        this.errorCode = errorCode;
        this.message = message;
        this.isLocal = isLocal;
        this.args= args;
    }

    public BizException(SystemErrorCodeEnum systemErrorCodeEnum,Object... args){
        this.errorCode = systemErrorCodeEnum.getCode();
        this.message = systemErrorCodeEnum.getMsg();
        this.args= args;
    }

    public BizException(SystemErrorCodeEnum systemErrorCodeEnum,Boolean isLocal,Object... args){
        this.errorCode = systemErrorCodeEnum.getCode();
        this.message = systemErrorCodeEnum.getMsg();
        this.isLocal = isLocal;
        this.args= args;
    }

}
