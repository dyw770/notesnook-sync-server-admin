package org.apache.tuweni.crypto.sodium;

import jnr.ffi.Pointer;

import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author dyw770
 * @date 2024-11-22
 */
public class PasswordHashSupport {

    /**
     * Compute a hash from a password.
     *
     * @param password The password to hash.
     * @param opsLimit The operations limit, which must be in the range {@link PasswordHash#minOpsLimit()} to {@link PasswordHash#maxOpsLimit()}.
     * @param memLimit The memory limit, which must be in the range {@link PasswordHash#minMemLimit()} to {@link PasswordHash#maxMemLimit()}.
     * @return The hash string.
     */
    public static String hash(String password, long opsLimit, long memLimit) {

        byte[] out = new byte[hashStringLength()];

        byte[] pwBytes = password.getBytes(StandardCharsets.UTF_8);
        int rc = Sodium.crypto_pwhash_str_alg(out, pwBytes, pwBytes.length, opsLimit, memLimit, PasswordHash.Algorithm.argon2id13().id());
        if (rc != 0) {
            throw new SodiumException("crypto_pwhash_str: failed with result " + rc);
        }

        return new String(out,  StandardCharsets.UTF_8);
    }

    /**
     * Verify a password against a hash.
     *
     * @param hash The hash.
     * @param password The password to verify.
     * @return {@code true} if the password matches the hash.
     */
    public static boolean verify(String hash, String password) {
        byte[] hashBytes = hash.getBytes(UTF_8);

        int hashLength = hashStringLength();
        if (hashBytes.length > hashLength) {
            return false;
        }

        Pointer str = Sodium.malloc(hashLength);
        try {
            str.put(0, hashBytes, 0, hashBytes.length);
            if (hashBytes.length < hashLength) {
                str.putByte(hashBytes.length, (byte) 0);
            }

            byte[] pwBytes = password.getBytes(UTF_8);
            return Sodium.crypto_pwhash_str_verify(str, pwBytes, pwBytes.length) == 0;
        } finally {
            Sodium.sodium_free(str);
        }
    }

    private static int hashStringLength() {
        long hashLength = Sodium.crypto_pwhash_strbytes();
        if (hashLength > Integer.MAX_VALUE) {
            throw new IllegalStateException("crypto_pwhash_strbytes: " + hashLength + " is too large");
        }
        return (int) hashLength;
    }
}
