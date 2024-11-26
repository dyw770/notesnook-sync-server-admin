package cn.dyw.notesnook.configuration;


import cn.dyw.notesnook.exception.AppRuntimeException;
import cn.dyw.notesnook.msg.ApiResult;
import cn.dyw.notesnook.msg.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/***
 * 全局异常错误
 *
 * @author dyw770
 * @date 2024-11-26
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(Exception.class)
    public ApiResult<Void> defaultExceptionHandler(Exception e) {
        logException(e);
        return ApiResult.fail(ResultCode.UNKNOWN);
    }

    /**
     * 系统运行异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(AppRuntimeException.class)
    private ApiResult<Void> appRuntimeExceptionHandler(AppRuntimeException e) {
        logException(e);
        return ApiResult.fail(e.getCode());
    }


    private void logException(Exception e) {
        if (log.isDebugEnabled()) {
            log.debug("捕获到请求异常", e);
        } else {
            log.info("捕获到请求异常, {}", e.getMessage());
        }
    }
}
