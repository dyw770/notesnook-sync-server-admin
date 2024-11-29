package cn.dyw.notesnook;

import jnr.ffi.Platform;
import org.apache.commons.codec.binary.Base64;
import org.apache.tuweni.crypto.sodium.GenericHash;
import org.apache.tuweni.crypto.sodium.PasswordHash;
import org.apache.tuweni.crypto.sodium.PasswordHashSupport;
import org.apache.tuweni.crypto.sodium.Sodium;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Objects;

/**
 * @author dyw770
 * @date 2024-11-26
 */
public class TestLibsodium {

    // l6GOkhOjzDubxXiX0eukKj3ht4i9jkxeUQHudZ8s9YM
    public static void main(String[] args) {


        if (Objects.requireNonNull(Platform.getNativePlatform().getOS()) == Platform.OS.WINDOWS) {
            Sodium.loadLibrary(Path.of("libsodium/win/x64/libsodium.dll"));
        } else {
            Sodium.searchLibrary("sodium",
                    Path.of("/usr/local/lib"),
                    Path.of("/opt/local/lib"),
                    Path.of("/usr/lib"),
                    Path.of("/lib")
            );
        }

        byte[] password = "123456".getBytes(StandardCharsets.UTF_8);

        GenericHash.Input input = GenericHash.Input.fromBytes(("oVzKtazBo7d8sb7TBvY9jw" + "xxxx@qq.com")
                .getBytes(StandardCharsets.UTF_8));
        GenericHash.Hash hash = GenericHash.hash(16, input);
        PasswordHash.Salt salt = PasswordHash.Salt.fromBytes(hash.bytes());
        byte[] hash1 = PasswordHash
                .hash(password, 32, salt, 3,
                        1024 * 1024 * 64,
                        PasswordHash.Algorithm.argon2id13());

        String passwordHash = Base64.encodeBase64URLSafeString(hash1);
        System.out.println(passwordHash);

        String hash2 = PasswordHashSupport
                .hash(passwordHash, 3, 65536);
        System.out.println(hash2);


        System.out.println(PasswordHashSupport.verify(hash2, passwordHash));

    }
}
