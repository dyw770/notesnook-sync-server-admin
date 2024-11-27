package cn.dyw.notesnook.msg.rq;

import lombok.Data;

/**
 * 新建用户
 *
 * @author dyw770
 * @date 2024-11-27
 */
@Data
public class CreateUserRq {

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 初始密码
     */
    private String password;
    
}
