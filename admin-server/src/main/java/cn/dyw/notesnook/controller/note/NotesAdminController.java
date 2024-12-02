package cn.dyw.notesnook.controller.note;

import cn.dyw.notesnook.msg.ApiResult;
import cn.dyw.notesnook.msg.rs.DashboardRs;
import cn.dyw.notesnook.service.NotesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dyw770
 * @date 2024-11-27
 */
@RestController
@RequestMapping("/admin/notes")
public class NotesAdminController {


    private final NotesService notesService;

    public NotesAdminController(NotesService notesService) {
        this.notesService = notesService;
    }

    /**
     * 返回首页统计数据
     *
     * @return 统计数据
     */
    @GetMapping("/dashboard")
    public ApiResult<DashboardRs> dashboard() {
        return ApiResult.success(notesService.dashboard());
    }
}

