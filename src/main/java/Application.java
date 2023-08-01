import abr.AuthenticationUseCase;
import fd.FileDatabase;
import fd.LoginViewSwing;
import ia.LoginView;
import ia.LoginController;

import javax.swing.*;


// Code sourced from https://riptutorial.com/swing/example/14137/simple-mvp-example
public class Application {

    public Application() {
        AuthenticationUseCase f = new AuthenticationUseCase(new FileDatabase());
        LoginView v = new LoginViewSwing();
        LoginController l = new LoginController(v, f);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Application::new);
    }
}
