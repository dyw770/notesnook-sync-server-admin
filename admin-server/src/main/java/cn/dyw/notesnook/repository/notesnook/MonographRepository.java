package cn.dyw.notesnook.repository.notesnook;

import cn.dyw.notesnook.entity.Monograph;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author dyw770
 * @date 2024-11-27
 */
public interface MonographRepository extends MongoRepository<Monograph, String> {

}
