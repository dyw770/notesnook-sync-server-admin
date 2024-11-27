package cn.dyw.notesnook.controller;

import cn.dyw.notesnook.msg.ApiResult;
import cn.dyw.notesnook.msg.rq.LoginRq;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

/**
 * 系统接口
 *
 * @author dyw770
 * @date 2024-11-27
 */
@RestController
@RequestMapping
public class AdminController {

    private final AuthenticationManager authenticationManager;

    private final SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    public AdminController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 登陆
     *
     * @param rq 用户信息
     * @return 登陆信息
     */
    @PostMapping("/login")
    public ApiResult<String> login(@RequestBody LoginRq rq) {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(rq.getUsername(), rq.getPassword());
        Authentication authenticationResponse =
                this.authenticationManager.authenticate(authenticationRequest);

        SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authenticationResponse);
        this.securityContextHolderStrategy.setContext(context);

        return ApiResult.success("登陆成功", rq.getUsername());
    }

    /**
     * 登出
     *
     * @param authentication 授权信息
     * @param request        请求
     * @param response       响应
     * @return 登出结果
     */
    @GetMapping("/logout")
    public ApiResult<Void> logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {

        this.logoutHandler.logout(request, response, authentication);
        return ApiResult.success("登出成功");
    }
}
