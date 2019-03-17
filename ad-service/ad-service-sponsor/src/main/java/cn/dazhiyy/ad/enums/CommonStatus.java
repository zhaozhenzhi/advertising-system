package cn.dazhiyy.ad.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Qinyi.
 */
@Getter
@AllArgsConstructor
public enum CommonStatus {

    VALID(1, "有效状态"),
    INVALID(0, "无效状态");

    private Integer code;
    private String msg;

   public static String getMsg(Integer code){
       for (CommonStatus c : CommonStatus.values()) {
           if (c.getCode().equals(code)) {
               return c.getMsg();
           }
       }
       return null;
   }
}
