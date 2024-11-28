package cn.dyw.notesnook.msg.rq;

import lombok.Data;

/**
 * 分页
 *
 * @author dyw770
 * @date 2024-11-28
 */
@Data
public class PageRq {

    /**
     * page
     */
    private int page;

    /**
     * size
     */
    private int size;
}
