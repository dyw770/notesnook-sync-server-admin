package cn.dyw.notesnook.controller.note;

import cn.dyw.notesnook.msg.ApiResult;
import cn.dyw.notesnook.msg.rq.CreateUserRq;
import cn.dyw.notesnook.msg.rq.LockUserRq;
import cn.dyw.notesnook.msg.rs.UserRs;
import cn.dyw.notesnook.service.NoteUserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author dyw770
 * @date 2024-11-27
 */
@Validated
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
    public ApiResult<String> create(@Valid @RequestBody CreateUserRq rq) {
        String userId = noteUserService.createNoteUser(rq);
        return ApiResult.success("Create successful", userId);
    }

    /**
     * 查询用户列表
     *
     * @param pageable 请求参数
     * @return 用户列表
     */
    @GetMapping
    public ApiResult<Page<UserRs>> list(Pageable pageable) {
        return ApiResult.success("Query successful", noteUserService.listUser(pageable));
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 结果
     */
    @DeleteMapping
    public ApiResult<Void> delete(@Length(min = 24, max = 24) @NotEmpty @RequestParam("id") String id) {
        noteUserService.deleteUser(id);
        return ApiResult.success("Delete successful");
    }

    /**
     * 锁定用户
     *
     * @param rq 锁定参数
     * @return 结果
     */
    @PutMapping("/lock")
    public ApiResult<Void> lock(@RequestBody LockUserRq rq) {
        noteUserService.lockUser(rq);
        return ApiResult.success("Lock successful");
    }

    @GetMapping("/unlock")
    public ApiResult<Void> unlock(@Length(min = 24, max = 24) @RequestParam("id") String id) {
        noteUserService.unlockUser(id);
        return ApiResult.success("Unlock successful");
    }
}

