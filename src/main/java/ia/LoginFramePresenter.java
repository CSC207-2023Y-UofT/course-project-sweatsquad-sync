package ia;


import abr.OutputBoundary;
import abr.requestAndResponse.authenticationFields.Field;
import abr.requestAndResponse.authenticationFields.FieldIssue;
import abr.requestAndResponse.AuthenticationResponseModel;

public class LoginFramePresenter implements OutputBoundary<AuthenticationResponseModel<? extends Field>> {

    private final LoginView loginView;

    public LoginFramePresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void receiveResponse(AuthenticationResponseModel<? extends Field> rm) {

        if (!rm.isSuccessful()) {
            for (FieldIssue<? extends Field> i : rm.getIssues()) {
                loginView.displayFieldError(i.field(), i.issue());
            }
        }
    }
}
