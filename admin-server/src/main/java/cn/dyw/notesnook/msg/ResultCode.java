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
    SUCCESS(0, "Success"),

    UNKNOWN(1000, "Unknown"),

    FAIL(1001, "Request failure"),

    LOGIN_FAILURE(2000, "Login failure"),

    NOT_LOGIN(2001, "Not login"),

    LOGIN_ERROR(2003, "Login error"),

    ACCOUNT_STATUS_ABNORMAL(2004, "Account status abnormal"),

    BAD_CREDENTIALS(2005, "Username or password is incorrect"),

    ACCOUNT_NOT_FOUND(2006, "User not found"),

    AUTH_FAILURE(3000, "Permission denied"),

    PARAM_ERROR(5002, "Param error"),

    REQUEST_METHOD_ERROR(5003, "Request method error"),

    REQUEST_BODY_MISS_ERROR(5004, "Miss Request Body"),

    NO_STATIC_RESOURCE_ERROR(5005, "Resource not found"),

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
