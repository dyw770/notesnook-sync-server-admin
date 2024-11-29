package cn.dyw.notesnook.service;

import cn.dyw.notesnook.configuration.properties.NotesnookProperties;
import cn.dyw.notesnook.entity.Role;
import cn.dyw.notesnook.entity.User;
import cn.dyw.notesnook.entity.UserSetting;
import cn.dyw.notesnook.msg.rq.CreateUserRq;
import cn.dyw.notesnook.msg.rq.LockUserRq;
import cn.dyw.notesnook.msg.rs.UserRs;
import cn.dyw.notesnook.repository.identity.RoleRepository;
import cn.dyw.notesnook.repository.identity.UserRepository;
import cn.dyw.notesnook.repository.notesnook.UserSettingRepository;
import cn.dyw.notesnook.utils.NotesnookUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

    public final String SETTINGS_KEY = "settingsv2";
    public final String ATTACHMENTS_KEY = "attachments";
    public final String CONTENT_KEY = "content";
    public final String NOTES_KEY = "notes";
    public final String NOTEBOOKS_KEY = "notebooks";
    public final String RELATIONS_KEY = "relations";
    public final String REMINDERS_KEY = "reminders";
    public final String LEGACY_SETTINGS_KEY = "settings";
    public final String SHORTCUTS_KEY = "shortcuts";
    public final String TAGS_KEY = "tags";
    public final String COLORS_KEY = "colors";
    public final String VAULTS_KEY = "vaults";
    public final String USER_SETTINGS_KEY = "user_settings";
    public final String MONOGRAPHS_KEY = "monographs";

    private final UserRepository userRepository;

    private final NotesnookProperties notesnookProperties;

    private final RoleRepository roleRepository;

    private final UserSettingRepository userSettingRepository;

    private final MongoTemplate notesnookMongoTemplate;

    public NoteUserService(UserRepository userRepository,
                           NotesnookProperties notesnookProperties,
                           RoleRepository roleRepository,
                           UserSettingRepository userSettingRepository,
                           MongoTemplate notesnookMongoTemplate) {
        this.userRepository = userRepository;
        this.notesnookProperties = notesnookProperties;
        this.roleRepository = roleRepository;
        this.userSettingRepository = userSettingRepository;
        this.notesnookMongoTemplate = notesnookMongoTemplate;
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

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    public void deleteUser(String id) {
        userRepository.deleteById(id);

        Query userIdQuery = Query.query(Criteria.where("UserId").is(id));

        notesnookMongoTemplate.remove(userIdQuery, NOTES_KEY);
        notesnookMongoTemplate.remove(userIdQuery, NOTEBOOKS_KEY);
        notesnookMongoTemplate.remove(userIdQuery, CONTENT_KEY);
        notesnookMongoTemplate.remove(userIdQuery, SETTINGS_KEY);
        notesnookMongoTemplate.remove(userIdQuery, ATTACHMENTS_KEY);
        notesnookMongoTemplate.remove(userIdQuery, RELATIONS_KEY);
        notesnookMongoTemplate.remove(userIdQuery, TAGS_KEY);
        notesnookMongoTemplate.remove(userIdQuery, USER_SETTINGS_KEY);
        notesnookMongoTemplate.remove(userIdQuery, MONOGRAPHS_KEY);

    }

    /**
     * 锁定用户
     *
     * @param rq 参数
     */
    public void lockUser(LockUserRq rq) {
        userRepository.updateUserLock(rq.getId(), rq.getLockoutEnd());
    }

    /**
     * 解锁
     *
     * @param id 用户id
     */
    public void unlockUser(String id) {
        userRepository.updateUserLock(id, Instant.now());
    }
}

