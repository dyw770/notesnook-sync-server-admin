package cn.dyw.notesnook.repository.identity;

import cn.dyw.notesnook.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author dyw770
 * @date 2024-11-27
 */
public interface RoleRepository extends MongoRepository<Role, String> {

    List<Role> getRoleByName(String name);
}
