package andrehsvictor.api.dailydo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncrypter {
    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encrypt(String password) {
        return passwordEncoder.encode(password);
    }
}
