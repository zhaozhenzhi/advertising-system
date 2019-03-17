package cn.dazhiyy.ad.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.common.enums
 * @className SystemErrorCodeEnum
 * @description TODO
 * @date 2019/3/16 16:55
 */
@Getter
@AllArgsConstructor
public enum  SystemErrorCodeEnum {
    /**
     * 成功码 0-100
     */
    SUCCESS(SC_OK,"success"),
    LOGIN_SUCCESS(1,"login_success"),
    SAVE_SUCCESS(2,"save_success"),
    UPDATE_SUCCESS(3,"update_success"),
    INSERT_SUCCESS(4,"insert_success"),
    SELECT_SUCCESS(5,"select_success"),
    CONCERN_SUCCESS(6,"concern_success"),
    CANCEL_CONCERN_SUCCESS(7,"cancel_concern_success"),
    SUBSCRIPTION_SUCCESS(8,"subscription_success"),
    CANCEL_SUBSCRIPTION_SUCCESS(9,"cancel_subscription_success"),
    /**
     * 失败码 1000-1100
     */
    FAILURE(SC_INTERNAL_SERVER_ERROR,"error"),
    LOGIN_FAILURE(1001,"login_failure"),
    SAVE_FAILURE(1002,"save_failure"),
    UPDATE_FAILURE(1003,"update_failure"),
    INSERT_FAILURE(1004,"insert_failure"),
    SELECT_FAILURE(1005,"select_failure"),
    CONCERN_FAILURE(1006,"concern_failure"),
    CANCEL_CONCERN_FAILURE(1007,"cancel_concern_failure"),
    SUBSCRIPTION_FAILURE(1008,"subscription_failure"),
    CANCEL_SUBSCRIPTION_FAILURE(1009,"cancel_subscription_failure"),

    USER_NAME_IS_NOT_NULL(2000,"user_name_is_not_null"),
    USER_IS_EXIST(2001,"user_is_exist"),

    NO_KNOW_ERROR(2002,"no_know_error")
    ;
    private Integer code;
    private String msg;


    public static String getMsg(Integer code) {
        for (SystemErrorCodeEnum c : SystemErrorCodeEnum.values()) {
            if (c.getCode().equals(code)) {
                return c.getMsg();
            }
        }
        return null;
    }

}
