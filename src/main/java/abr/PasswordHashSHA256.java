package abr;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashSHA256 implements PasswordHashStrategy {
    @Override
    public String hashPassword(String visualPassword)  {
        try {
            // inst of the SHA-256 messageDigest
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // hashes password
            byte[] hashedPassword = md.digest(visualPassword.getBytes());

            StringBuilder sb = new StringBuilder(); // to create hash pw str
            for (byte b : hashedPassword) { // loops through each bye
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("password failed to hash");
        }

    }
}
