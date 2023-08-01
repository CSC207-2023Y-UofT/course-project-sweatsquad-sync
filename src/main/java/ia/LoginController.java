package ia;

import abr.*;

public class LoginController implements Controller {

    private final LoginView loginView;
    private final AuthenticationUseCase authenticationModel;

    public LoginController(LoginView loginView, AuthenticationUseCase authenticationModel) {
        this.loginView = loginView;
        loginView.setController(this);
        this.authenticationModel = authenticationModel;
    }

    private boolean attemptAuthenticate(AuthenticationRequestModel d) {

        AuthenticationResponseModel m = authenticationModel.requestAuthentication(d);
        loginView.displayInfoMessage(m.responseMessage()); //TODO move into if-statement after testing
        return m.success();
    }

    public void loginAttempted(LoginDetails d) {
        attemptAuthenticate(d);
    }

    public void instructorRegistrationAttempted(RegisterDetails r) {
        if (attemptAuthenticate(r)) {
            loginView.provideLogin();
        }
    }

    public void registrationAttempted(RegisterDetails r) {
        if (attemptAuthenticate(r)) {
            loginView.provideLogin();
        }
    }

    public void codeActivationAttempted(ActivationCodeDetails c) {
        if (attemptAuthenticate(c)) {
            loginView.provideInstrSignup();
        }
    }



}
