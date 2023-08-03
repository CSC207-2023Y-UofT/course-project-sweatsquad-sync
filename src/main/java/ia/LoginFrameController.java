package ia;

import abr.*;
import abr.requestAndResponse.*;

public class LoginFrameController implements LoginViewController {

    private final InputBoundary<AuthenticationRequestModel> authenticationModel;

    public LoginFrameController(Authenticator authenticationModel) {
        this.authenticationModel = authenticationModel;
    }

    @Override
    public void loginAttempted(String username, String password) {
        authenticationModel.receiveRequest(new LoginDetails(username, password));
    }

    @Override
    public void instructorRegistrationAttempted(String firstName, String lastName, String username, String email, String password, String confirmPassword) {
        authenticationModel.receiveRequest(new RegisterDetails(firstName, lastName, username, email, password, confirmPassword, AccountType.INSTRUCTOR));
    }

    @Override
    public void regularRegistrationAttempted(String firstName, String lastName, String username, String email, String password, String confirmPassword) {
        authenticationModel.receiveRequest(new RegisterDetails(firstName, lastName, username, email, password, confirmPassword, AccountType.REGULAR));
    }

    @Override
    public void codeActivationAttempted(String code) {
        authenticationModel.receiveRequest(new ActivationCodeDetails(code));
    }
}
