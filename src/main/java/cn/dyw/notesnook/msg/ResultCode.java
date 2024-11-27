package cn.dyw.notesnook.msg;

import lombok.Getter;

/**
 * @author dyw770
 * @date 2024-11-06
 */
@Getter
public enum ResultCode {

    /**
     * 请求成功
     */
    SUCCESS(0, "请求成功"),

    UNKNOWN(1000, "未知异常"),

    FAIL(1001, "操作失败"),

    ;


    /**
     * 响应码
     */
    private final int code;

    /**
     * 消息
     */
    private final String msg;

    ResultCode(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }
}
