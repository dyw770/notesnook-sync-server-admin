package cn.dyw.notesnook.controller;

import cn.dyw.notesnook.entity.User;
import cn.dyw.notesnook.msg.ApiResult;
import cn.dyw.notesnook.repository.identity.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dyw770
 * @date 2024-11-27
 */
@RestController
@RequestMapping("/admin/users")
public class UserAdminController {

    private final UserRepository userRepository;

    public UserAdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 返回所有的用户
     *
     * @return 用户列表
     */
    @GetMapping()
    public ApiResult<List<User>> list() {
        List<User> list = userRepository.findAll();
        return ApiResult.success(list);
    }
}

