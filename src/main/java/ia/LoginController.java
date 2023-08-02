package ia;

import abr.*;

import java.util.List;

public class LoginController implements Controller {

    private final LoginView loginView;
    private final AuthenticationUseCase authenticationModel;

    public LoginController(LoginView loginView, AuthenticationUseCase authenticationModel) {
        this.loginView = loginView;
        loginView.setController(this);
        this.authenticationModel = authenticationModel;
    }

    private boolean attemptAuthenticate(AuthenticationRequestModel d) {

        AuthenticationResponseModel<? extends Field> m = authenticationModel.requestAuthentication(d);
        if (!m.isSuccessful()) {
            for (FieldIssue<? extends Field> i : m.getIssues()) {
                loginView.displayFieldError(i.field(), i.issue());
            }
        }
        return m.isSuccessful();
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
