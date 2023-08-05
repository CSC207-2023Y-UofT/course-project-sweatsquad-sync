import javax.swing.*;


// Code sourced from https://riptutorial.com/swing/example/14137/simple-mvp-example
public class Application {

    public Application() {
        /*
        Authenticator f = new Authenticator(new FileDatabase());
        LoginView v = new LoginViewSwing();
        LoginFrameController l = new LoginFrameController(v, f);*/
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Application::new);
    }
}
