package cn.dyw.notesnook.repository.identity;

import cn.dyw.notesnook.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author dyw770
 * @date 2024-11-27
 */
public interface UserRepository extends MongoRepository<User, String> {

}
