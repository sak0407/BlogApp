package sel.prac.springboot.BlogApp.Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("S"));
        System.out.println(encoder.encode("A"));
        System.out.println(encoder.encode("X"));
        System.out.println(encoder.encode("Z"));
    }
}
