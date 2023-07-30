package ia;

import abr.*;
import fd.LoginView;

public class LoginController {

    private LoginView loginView;
    private AuthenticatorFactory loginModel;

    public LoginController(LoginView loginView, AuthenticatorFactory loginModel) {
        this.loginView = loginView;
        this.loginModel = loginModel;
        loginView.addListener(new LoginHandler());
    }

    private class LoginHandler implements  LoginViewListener {

        private boolean attemptAuthenticate(UserDetails d) {

            Authenticator<UserDetails> a = loginModel.getAuthenticator(d);
            if (!a.authenticate(d)) {
                loginView.displayInfoMessage(a.getStatus());
                return false;
            }
            return true;
        }

        @Override
        public void loginAttempted(LoginDetails d) {
            if (attemptAuthenticate(d)) {
                // TODO: i'm not even sure tbh but something has to be done here
                loginView.displayInfoMessage("Login success!");
            }
        }

        @Override
        public void instructorRegistrationAttempted(RegisterDetails r) {
            if (attemptAuthenticate(r)) {
                // TODO: register the instructor
                loginView.displayInfoMessage("Registration success!");
                loginView.showLoginPanel();
            }
        }

        @Override
        public void registrationAttempted(RegisterDetails r) {
            if (attemptAuthenticate(r)) {
                // TODO: register the user
                loginView.displayInfoMessage("Registration success!");
                loginView.showLoginPanel();
            }
        }

        @Override
        public void codeActivationAttempted(AuthCode c) {
            if (attemptAuthenticate(c)) {
                // TODO: register the instructor
                loginView.displayInfoMessage("Account activation success!!");
                loginView.showInstrRegistrationPanel();
            }
        }
    }


}
