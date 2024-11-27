package cn.dyw.notesnook.controller;

import cn.dyw.notesnook.entity.Notes;
import cn.dyw.notesnook.msg.ApiResult;
import cn.dyw.notesnook.repository.notesnook.NotesRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dyw770
 * @date 2024-11-27
 */
@RestController
@RequestMapping("/admin/notes")
public class NotesAdminController {

    private final NotesRepository notesRepository;

    public NotesAdminController(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    /**
     * 返回所有的笔记
     *
     * @return 笔记列表
     */
    @GetMapping()
    public ApiResult<List<Notes>> list() {
        List<Notes> list = notesRepository.findAll();
        return ApiResult.success(list);
    }
}

