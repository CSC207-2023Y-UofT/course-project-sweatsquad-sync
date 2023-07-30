import abr.AuthenticatorFactory;
import fd.FileDatabase;
import fd.LoginView;
import ia.LoginController;

public class Main {

    public static void main(String[] args) {
        AuthenticatorFactory f = new AuthenticatorFactory(new FileDatabase());
        LoginView v = new LoginView();
        LoginController l = new LoginController(v, f);
    }
}
