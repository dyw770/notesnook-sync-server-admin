package cn.dyw.notesnook.msg.rs;

import lombok.Data;

/**
 * 首页统计数据
 *
 * @author dyw770
 * @date 2024-12-02
 */
@Data
public class DashboardRs {

    /**
     * 笔记数量
     */
    private Long noteCount;

    /**
     * 用户数量
     */
    private Long userCount;

    /**
     * 专注数量
     */
    private Long monographCount;

    /**
     * 附件数量
     */
    private Long attachmentCount;
}
