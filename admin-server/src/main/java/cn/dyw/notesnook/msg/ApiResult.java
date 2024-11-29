package cn.dyw.notesnook.msg;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author dyw770
 * @date 2024-11-06
 */
@Data
public class ApiResult<T> {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 响应码
     */
    private int code;

    /**
     * 信息
     */
    private String msg;

    /**
     * 数据
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;


    public ApiResult(boolean success, int code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 快速返回一个成功操作
     *
     * @param msg  msg
     * @param data data
     * @param <T>  数据类型
     * @return 结果
     */
    public static <T> ApiResult<T> success(String msg, T data) {
        return new ApiResult<>(true, ResultCode.SUCCESS.getCode(), msg, data);
    }

    /**
     * 快速返回一个成功操作
     *
     * @param data data
     * @param <T>  数据类型
     * @return 结果
     */
    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(true, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data);
    }

    /**
     * 快速返回一个成功操作
     *
     * @return 结果
     */
    public static ApiResult<Void> success() {
        return new ApiResult<>(true, ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), null);
    }

    /**
     * 快速返回一个成功操作
     *
     * @param msg msg
     * @return 结果
     */
    public static ApiResult<Void> success(String msg) {
        return new ApiResult<>(true, ResultCode.SUCCESS.getCode(), msg, null);
    }

    public static <T> ApiResult<T> fail(int code, String msg, T data) {
        return new ApiResult<>(false, code, msg, data);
    }

    public static ApiResult<Void> fail(int code, String msg) {
        return new ApiResult<>(false, code, msg, null);
    }

    public static ApiResult<Void> fail(ResultCode code) {
        return new ApiResult<>(false, code.getCode(), code.getMsg(), null);
    }
}
