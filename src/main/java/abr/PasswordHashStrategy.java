package abr;

@FunctionalInterface
public interface PasswordHashStrategy {
    String hashPassword(String visualPassword);
}
