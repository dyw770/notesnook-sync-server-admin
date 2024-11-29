package cn.dyw.notesnook.utils;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.apache.tuweni.crypto.sodium.GenericHash;
import org.apache.tuweni.crypto.sodium.PasswordHash;
import org.apache.tuweni.crypto.sodium.PasswordHashSupport;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * Notesnook 相关工具
 *
 * @author dyw770
 * @date 2024-11-27
 */
public final class NotesnookUtils {

    private final static SecureRandom secureRandom = new SecureRandom();

    private static final Base32 codec = Base32.builder().get();

    /**
     * 生成密码
     *
     * @param password 密码
     * @param email    邮箱
     * @param appSalt  salt
     * @return 密码
     */
    public static String generatePassword(String password, String email, String appSalt) {

        // 1. 将用户的邮箱加上appSalt 生成一段hash字节
        // 2. 将hash字节作为salt 将密码hash一次 再base64url编码，得到新的密码
        GenericHash.Input input = GenericHash.Input.fromBytes((appSalt + email).getBytes(StandardCharsets.UTF_8));
        GenericHash.Hash passwordSalt = GenericHash.hash(16, input);

        PasswordHash.Salt salt = PasswordHash.Salt.fromBytes(passwordSalt.bytes());
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);

        byte[] passwordHashBytes = PasswordHash.hash(
                passwordBytes,
                32,
                salt,
                3,
                1024 * 1024 * 64,
                PasswordHash.Algorithm.argon2id13()
        );
        String passwordHash = Base64.encodeBase64URLSafeString(passwordHashBytes);

        // 3. 最后将得到的字符串通过crypto_pwhash_str_alg算法得到最后存储的字符
        return PasswordHashSupport
                .hash(passwordHash, 3, 65536);
    }

    /**
     * 生成UUID
     *
     * @return UUID
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 生成base32
     *
     * @return 结果
     */
    public static String securityStamp() {
        byte[] rng = new byte[20];
        secureRandom.nextBytes(rng);
        return codec.encodeAsString(rng);
    }


    /**
     * 生成salt
     *
     * @return 结果
     */
    public static String generateSalt() {
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return Base64.encodeBase64URLSafeString(salt);
    }
}
