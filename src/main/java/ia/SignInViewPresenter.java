package ia;


import abr.OutputBoundary;
import abr.inputOutputData.authenticationFields.Field;
import abr.inputOutputData.authenticationFields.FieldIssue;
import abr.inputOutputData.AuthenticationResponseModel;

public class SignInViewPresenter implements OutputBoundary<AuthenticationResponseModel<? extends Field>> {

    private final SignInView signInView;

    public SignInViewPresenter(SignInView signInView) {
        this.signInView = signInView;
    }

    @Override
    public void receiveResponse(AuthenticationResponseModel<? extends Field> rm) {

        if (!rm.isSuccessful()) {
            for (FieldIssue<? extends Field> i : rm.getIssues()) {
                signInView.displayFieldError(i.field(), i.issue());
            }
        }
    }


}
