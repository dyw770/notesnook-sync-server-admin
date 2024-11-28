package cn.dyw.notesnook.service;

import cn.dyw.notesnook.configuration.properties.NotesnookProperties;
import cn.dyw.notesnook.entity.Role;
import cn.dyw.notesnook.entity.User;
import cn.dyw.notesnook.entity.UserSetting;
import cn.dyw.notesnook.msg.rq.CreateUserRq;
import cn.dyw.notesnook.msg.rq.PageRq;
import cn.dyw.notesnook.msg.rs.UserRs;
import cn.dyw.notesnook.repository.identity.RoleRepository;
import cn.dyw.notesnook.repository.identity.UserRepository;
import cn.dyw.notesnook.repository.notesnook.UserSettingRepository;
import cn.dyw.notesnook.utils.NotesnookUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 用户相关操作
 *
 * @author dyw770
 * @date 2024-11-27
 */
@Service
public class NoteUserService {

    private final UserRepository userRepository;

    private final NotesnookProperties notesnookProperties;

    private final RoleRepository roleRepository;

    private final UserSettingRepository userSettingRepository;

    public NoteUserService(UserRepository userRepository, NotesnookProperties notesnookProperties, RoleRepository roleRepository, UserSettingRepository userSettingRepository) {
        this.userRepository = userRepository;
        this.notesnookProperties = notesnookProperties;
        this.roleRepository = roleRepository;
        this.userSettingRepository = userSettingRepository;
    }

    /**
     * 创建用户
     *
     * @param rq 参数
     * @return 用户id
     */
    public String createNoteUser(CreateUserRq rq) {
        User user = new User();
        user.setUserName(rq.getUserName());
        user.setNormalizedUserName(rq.getUserName().toUpperCase());
        user.setEmail(rq.getEmail());
        user.setNormalizedEmail(rq.getEmail().toUpperCase());
        user.setAccessFailedCount(0);
        user.setConcurrencyStamp(NotesnookUtils.uuid());
        user.setEmailConfirmed(true);
        user.setLockoutEnabled(true);
        user.setLogins(Collections.emptyList());
        user.setTokens(Collections.emptyList());
        user.setClaims(Collections.emptyList());

        String passwordHash = NotesnookUtils.generatePassword(rq.getPassword(), rq.getEmail(), notesnookProperties.getAppSalt());
        user.setPasswordHash(passwordHash);

        user.setPhoneNumberConfirmed(false);
        user.setTwoFactorEnabled(true);
        user.setSecurityStamp(NotesnookUtils.securityStamp());

        List<Role> roles = roleRepository.getRoleByName("notesnook");

        List<String> roleIds = roles.stream()
                .map(Role::getId)
                .toList();
        user.setRoles(roleIds);

        User savedUser = userRepository.save(user);

        UserSetting userSetting = new UserSetting();
        userSetting.setUserId(savedUser.getId());
        userSetting.setSalt(NotesnookUtils.generateSalt());
        userSetting.setLastSynced(0L);

        userSettingRepository.save(userSetting);

        return user.getId();
    }

    /**
     * 查询用户列表
     *
     * @param pageable 参数
     * @return 用户列表
     */
    public Page<UserRs> listUser(Pageable pageable) {
        return userRepository.queryPage(pageable);
    }
}
