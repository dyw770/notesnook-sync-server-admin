package cn.dyw.notesnook.configuration;


import cn.dyw.notesnook.exception.AppRuntimeException;
import cn.dyw.notesnook.msg.ApiResult;
import cn.dyw.notesnook.msg.ResultCode;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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

    /**
     * 授权异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(AuthenticationException.class)
    public ApiResult<Void> authenticationExceptionHandler(AuthenticationException e) {
        logException(e);
        return ApiResult.fail(ResultCode.NOT_LOGIN);
    }

    /**
     * 账号异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(AccountStatusException.class)
    public ApiResult<Void> accountStatusExceptionHandler(AuthenticationException e) {
        logException(e);
        return ApiResult.fail(ResultCode.ACCOUNT_STATUS_ABNORMAL);
    }

    /**
     * 用户不存在
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ApiResult<Void> usernameNotFoundExceptionHandler(UsernameNotFoundException e) {
        logException(e);
        return ApiResult.fail(ResultCode.ACCOUNT_NOT_FOUND);
    }

    /**
     * 凭据错误
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ApiResult<Void> badCredentialsExceptionHandler(BadCredentialsException e) {
        logException(e);
        return ApiResult.fail(ResultCode.BAD_CREDENTIALS);
    }


    /**
     * 访问拒绝
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(AccessDeniedException.class)
    private ApiResult<Void> accessDeniedExceptionHandler(AccessDeniedException e) {
        logException(e);
        return ApiResult.fail(ResultCode.AUTH_FAILURE);
    }

    /**
     * 请求方法错误
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    private ApiResult<Void> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        logException(e);
        return ApiResult.fail(ResultCode.REQUEST_METHOD_ERROR);
    }


    /**
     * 参数相关异常
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler({MethodArgumentNotValidException.class,
            ConstraintViolationException.class,
            ServletRequestBindingException.class,
            MethodArgumentTypeMismatchException.class})
    private ApiResult<Void> methodArgumentNotValidExceptionHandler(Exception e) {
        logException(e);
        return ApiResult.fail(ResultCode.PARAM_ERROR);
    }

    /**
     * 缺少 Request body
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    private ApiResult<Void> httpMessageNotReadableExceptionHandler(Exception e) {
        logException(e);
        return ApiResult.fail(ResultCode.REQUEST_BODY_MISS_ERROR);
    }
    

    private void logException(Exception e) {
        if (log.isDebugEnabled()) {
            log.debug("捕获到请求异常", e);
        } else {
            log.info("捕获到请求异常, {}", e.getMessage());
        }
    }
}
