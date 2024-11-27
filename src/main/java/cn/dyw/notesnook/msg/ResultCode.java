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

    LOGIN_FAILURE(2000, "登陆失败"),

    NOT_LOGIN(2001, "未登陆, 请先登陆"),

    LOGIN_ERROR(2003, "登陆处理异常"),

    ACCOUNT_STATUS_ABNORMAL(2004, "账号异常"),

    BAD_CREDENTIALS(2005, "用户名或密码错误"),

    ACCOUNT_NOT_FOUND(2006, "用户不存在"),

    AUTH_FAILURE(3000, "权限不足, 无法完成操作"),

    PARAM_ERROR(5002, "参数异常, 请检查参数"),

    REQUEST_METHOD_ERROR(5003, "请求方法错误"),

    REQUEST_BODY_MISS_ERROR(5004, "缺少Request Body"),

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
