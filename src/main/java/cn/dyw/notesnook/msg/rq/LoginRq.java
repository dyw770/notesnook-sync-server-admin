package cn.dyw.notesnook.msg.rq;

import lombok.Data;

/**
 * @author dyw770
 * @date 2024-11-27
 */
@Data
public class LoginRq {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
