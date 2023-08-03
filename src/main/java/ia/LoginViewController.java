package ia;

public interface LoginViewController extends Controller {

    public void loginAttempted(String username, String password);
    public void instructorRegistrationAttempted(String firstName, String lastName, String username, String email, String password, String confirmPassword);
    public void regularRegistrationAttempted(String firstName, String lastName, String username, String email, String password, String confirmPassword);
    public void codeActivationAttempted(String code);
}

