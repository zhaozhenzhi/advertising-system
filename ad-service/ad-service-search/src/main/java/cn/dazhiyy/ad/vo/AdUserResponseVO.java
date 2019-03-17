package cn.dazhiyy.ad.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad.vo
 * @className AdUserResponseVO
 * @description 返回页面用户信息
 * @date 2019/3/17 22:25
 */
@Data
@NoArgsConstructor
public class AdUserResponseVO {

    private Long id;

    private String username;

    private String token;

    private Integer userStatus;

    private String userStatusMsg;

    public AdUserResponseVO(Long id, String username, String token, Integer userStatus, String userStatusMsg) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.userStatus = userStatus;
        this.userStatusMsg = userStatusMsg;
    }
}
