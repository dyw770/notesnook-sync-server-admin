package cn.dyw.notesnook.service;

import cn.dyw.notesnook.msg.rs.DashboardRs;
import cn.dyw.notesnook.repository.identity.UserRepository;
import cn.dyw.notesnook.repository.notesnook.AttachmentRepository;
import cn.dyw.notesnook.repository.notesnook.MonographRepository;
import cn.dyw.notesnook.repository.notesnook.NotesRepository;
import org.springframework.stereotype.Service;

/**
 * @author dyw770
 * @date 2024-12-02
 */
@Service
public class NotesService {

    private final NotesRepository notesRepository;

    private final UserRepository userRepository;

    private final MonographRepository monographRepository;

    private final AttachmentRepository attachmentRepository;

    public NotesService(NotesRepository notesRepository,
                        UserRepository userRepository,
                        MonographRepository monographRepository,
                        AttachmentRepository attachmentRepository) {
        this.notesRepository = notesRepository;
        this.userRepository = userRepository;
        this.monographRepository = monographRepository;
        this.attachmentRepository = attachmentRepository;
    }

    public DashboardRs dashboard() {
        DashboardRs rs = new DashboardRs();
        rs.setNoteCount(notesRepository.count());
        rs.setUserCount(userRepository.count());
        rs.setMonographCount(monographRepository.count());
        rs.setAttachmentCount(attachmentRepository.count());
        return rs;
    }
}
