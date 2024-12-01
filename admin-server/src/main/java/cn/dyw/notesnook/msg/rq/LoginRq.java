package cn.dyw.notesnook.msg.rq;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @author dyw770
 * @date 2024-11-27
 */
@Data
public class LoginRq {

    /**
     * 用户名
     */
    @NotEmpty
    @Length(min = 3, max = 20)
    private String username;

    /**
     * 密码
     */
    @NotEmpty
    @Length(min = 3, max = 20)
    private String password;
}
