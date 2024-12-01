package cn.dyw.notesnook.msg.rq;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
    @Email
    @NotEmpty
    private String email;

    /**
     * 用户名
     */
    @Length(min = 3, max = 10)
    @NotEmpty
    private String userName;

    /**
     * 初始密码
     */
    @NotEmpty
    @Length(min = 12, max = 16)
    private String password;
    
}
