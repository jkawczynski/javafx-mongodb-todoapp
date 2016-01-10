package todoapp.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

/**
 *
 * @author jkawczynski
 */
public class HashUtils {

    public static Optional<String> md5(String toHash) {
        MessageDigest cipher;
        try {
            cipher = MessageDigest.getInstance("MD5");
            byte[] digest = cipher.digest(toHash.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toHexString(0xFF & digest[i]));
            }
            return Optional.of(sb.toString());
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            return Optional.empty();
        }
    }

}
