package cn.dyw.notesnook.msg.rq;

import lombok.Data;

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
    private String id;

    /**
     * 锁定结束时间
     */
    private Instant lockoutEnd;
}
