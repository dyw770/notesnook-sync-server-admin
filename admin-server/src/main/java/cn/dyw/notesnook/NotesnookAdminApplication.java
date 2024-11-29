package cn.dyw.notesnook;

import jnr.ffi.Platform;
import org.apache.tuweni.crypto.sodium.Sodium;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Path;
import java.util.Objects;

@SpringBootApplication
public class NotesnookAdminApplication {

    public static void main(String[] args) {
        loadLibsodium();
        SpringApplication.run(NotesnookAdminApplication.class, args);
    }

    /**
     * 加载 libsodium
     */
    private static void loadLibsodium() {
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
    }

}
