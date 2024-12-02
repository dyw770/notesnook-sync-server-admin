package cn.dyw.notesnook.repository.notesnook;

import cn.dyw.notesnook.entity.Attachment;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author dyw770
 * @date 2024-11-27
 */
public interface AttachmentRepository extends MongoRepository<Attachment, String> {

}
