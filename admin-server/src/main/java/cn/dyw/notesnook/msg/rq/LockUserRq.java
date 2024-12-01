package cn.dyw.notesnook.msg.rq;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;

/**
 * @author dyw770
 * @date 2024-11-29
 */
@Data
public class LockUserRq {

    /**
     * 用户id
     */
    @Length(min = 24, max = 24)
    private String id;

    /**
     * 锁定结束时间
     */
    private Instant lockoutEnd;
}
