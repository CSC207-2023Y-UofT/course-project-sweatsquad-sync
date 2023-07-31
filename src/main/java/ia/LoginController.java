package ia;

import abr.*;
import fd.LoginView;

public class LoginController {

    private LoginView loginView;
    private AuthenticationUseCase authenticationModel;

    public LoginController(LoginView loginView, AuthenticationUseCase authenticationModel) {
        this.loginView = loginView;
        this.authenticationModel = authenticationModel;
        loginView.addListener(new LoginHandler());
    }

    private class LoginHandler implements  LoginViewListener {

        private boolean attemptAuthenticate(AuthenticationRequestModel d) {

            AuthenticationResponseModel m = authenticationModel.requestAuthentication(d);
            loginView.displayInfoMessage(m.responseMessage()); //TODO move into if-statement after testing
            if (!m.success()) {

                return false;
            }
            return true;
        }

        @Override
        public void loginAttempted(LoginDetails d) {
            attemptAuthenticate(d);
        }

        @Override
        public void instructorRegistrationAttempted(RegisterDetails r) {
            if (attemptAuthenticate(r)) {
                loginView.showLoginPanel();
            }
        }

        @Override
        public void registrationAttempted(RegisterDetails r) {
            if (attemptAuthenticate(r)) {
                loginView.showLoginPanel();
            }
        }

        @Override
        public void codeActivationAttempted(ActivationCodeDetails c) {
            if (attemptAuthenticate(c)) {
                loginView.showInstrRegistrationPanel();
            }
        }
    }


}
