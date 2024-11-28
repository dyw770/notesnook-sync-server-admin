package cn.dyw.notesnook.repository.identity;

import cn.dyw.notesnook.entity.User;
import cn.dyw.notesnook.msg.rs.UserRs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * @author dyw770
 * @date 2024-11-27
 */
public interface UserRepository extends MongoRepository<User, String> {

    @Query(value = "{}", fields = "{ 'id': 1, 'userName': 1, 'email': 1, 'emailConfirmed': 1, 'phoneNumber': 1, 'phoneNumberConfirmed': 1, 'twoFactorEnabled': 1, 'lockoutEnabled': 1,'lockoutEnd': 1, 'accessFailedCount': 1}")
    Page<UserRs> queryPage(Pageable pageable);
}
