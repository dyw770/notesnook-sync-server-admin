package cn.dyw.notesnook.exception;

import cn.dyw.notesnook.msg.ResultCode;
import lombok.Getter;

/**
 * 自定义系统运行异常
 *
 * @author dyw770
 * @date 2024-11-06
 */
@Getter
public class AppRuntimeException extends RuntimeException {

    private final ResultCode code;

    public AppRuntimeException(ResultCode code) {
        this.code = code;
    }

}
