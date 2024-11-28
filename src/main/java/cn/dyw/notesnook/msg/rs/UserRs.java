package cn.dyw.notesnook.msg.rs;

import lombok.Data;

import java.time.Instant;

/**
 * @author dyw770
 * @date 2024-11-28
 */
@Data
public class UserRs {

    /**
     * ID
     */
    private String id;

    /**
     * 登陆失败次数
     */
    private Integer accessFailedCount;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否锁定用户
     */
    private Boolean lockoutEnabled;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 邮箱验证
     */
    private Boolean emailConfirmed;

    /**
     * 锁定结束时间
     */
    private Instant lockoutEnd;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 手机号验证
     */
    private Boolean phoneNumberConfirmed;
}
