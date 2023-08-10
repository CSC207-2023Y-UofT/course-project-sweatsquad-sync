package abr;

import java.security.NoSuchAlgorithmException;

@FunctionalInterface
public interface PasswordHashStrategy {
    String hashPassword(String visualPassword);
}
