package cn.dyw.notesnook.controller.note;

import cn.dyw.notesnook.msg.ApiResult;
import cn.dyw.notesnook.msg.rq.CreateUserRq;
import cn.dyw.notesnook.service.NoteUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dyw770
 * @date 2024-11-27
 */
@RestController
@RequestMapping("/admin/users")
public class UserAdminController {

    private final NoteUserService noteUserService;

    public UserAdminController(NoteUserService noteUserService) {
        this.noteUserService = noteUserService;
    }

    /**
     * 新建用户
     *
     * @param rq 请求参数
     * @return 用户ID
     */
    @PostMapping
    public ApiResult<String> create(CreateUserRq rq) {
        String userId = noteUserService.createNoteUser(rq);
        return ApiResult.success("创建用户成功", userId);
    }
}

