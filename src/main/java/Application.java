import abr.AuthenticatorFactory;
import fd.FileDatabase;
import fd.LoginView;
import ia.LoginController;

import javax.swing.*;


// Code sourced from https://riptutorial.com/swing/example/14137/simple-mvp-example
public class Application {

    public Application() {
        AuthenticatorFactory f = new AuthenticatorFactory(new FileDatabase());
        LoginView v = new LoginView();
        LoginController l = new LoginController(v, f);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Application();
            }
        });
    }
}
