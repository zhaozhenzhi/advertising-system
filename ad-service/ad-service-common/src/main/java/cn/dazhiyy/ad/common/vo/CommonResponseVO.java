package cn.dazhiyy.ad.common.vo;

import cn.dazhiyy.ad.common.enums.SystemErrorCodeEnum;
import lombok.Data;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.common.vo
 * @className CommonResponseVO
 * @description 统一返回模型
 * @date 2019/3/16 15:52
 */
@Data
public class CommonResponseVO<T> implements Serializable {

    private static final long serialVersionUID = -1196219025699590281L;
    /**
     * Type.
     */
    public enum Type {
        /**
         * success.
         */
        success,
        /**
         * warn.
         */
        warn,
        /**
         * error.
         */
        error
    }

    private Type type;

    private Integer code;

    private String msg;

    private T data;

    public CommonResponseVO() {

    }

    public CommonResponseVO(Type type, String msg, Object... args){
        this.type = type;
        this.msg = msg;
    }


    public CommonResponseVO(Type type, int code, String msg, Object... args) {
        this.code = code;
        this.type = type;
        this.msg = msg;
    }


    public CommonResponseVO(Type type, int code, String msg, T data, Object... args) {
        this.type = type;
        this.msg = msg;
        this.data = data;
        this.code = code;
    }

    public CommonResponseVO(Type type, int code, T data) {
        this.type = type;
        this.data = data;
        this.code = code;
    }

    public static <T> CommonResponseVO<T> success() {
        return new CommonResponseVO<>(Type.success, SystemErrorCodeEnum.SUCCESS.getCode(), SystemErrorCodeEnum.SUCCESS.getMsg(),null);
    }

    public static <T> CommonResponseVO<T> successData(T data) {
        return new CommonResponseVO<>(Type.success, SystemErrorCodeEnum.SUCCESS.getCode(), data);
    }

    public static <T> CommonResponseVO<T> successData(int code, String content, T data, Object... args) {
        return new CommonResponseVO<>(Type.success, code, content, data, args);
    }

    public static  <T> CommonResponseVO<T> success(String content, Object... args) {
        return new CommonResponseVO<>(Type.success, HttpServletResponse.SC_OK, content, args);
    }


    /**
     * 带返回数据的错误消息
     *
     * @param content
     * @param args
     *
     * @return
     */

    public static <T> CommonResponseVO<T> error(int code, String content, Object... args) {
        return new CommonResponseVO<>(Type.error, code, content, args);
    }

    public static <T> CommonResponseVO<T> error(Object... args) {
        return new CommonResponseVO<>(Type.error, SystemErrorCodeEnum.FAILURE.getCode(), SystemErrorCodeEnum.FAILURE.getMsg(),args);
    }

    public static <T> CommonResponseVO<T> error(int code) {
        return new CommonResponseVO<>(Type.error, code, SystemErrorCodeEnum.getMsg(code),null);
    }

    public static <T> CommonResponseVO<T> error(String content, Object... args) {
        return new CommonResponseVO<>(Type.error, SystemErrorCodeEnum.FAILURE.getCode(), content, args);
    }


}
