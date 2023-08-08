package ia;

import abr.*;
import abr.IODataModels.*;
import abr.IODataModels.authenticationFields.Field;
import abr.IODataModels.authenticationFields.FieldIssue;
import abr.IODataModels.authenticationFields.LoginField;
import abr.IODataModels.authenticationFields.RegisterField;

public class EntryFramePresenter implements Presenter {

    private final InputBoundary<AuthenticationRequestModel> authenticationModel;

    private final EntryFrameView entryFrameView;

    public EntryFramePresenter(InputBoundary<AuthenticationRequestModel> authenticationModel, EntryFrameView entryFrameView) {
        this.authenticationModel = authenticationModel;
        this.entryFrameView = entryFrameView;
    }

    public void loginAttempted(String username, String password) {
        authenticationModel.receiveRequest(new LoginDetails(username.trim(), password));
    }

    public void instructorRegistrationAttempted(RegisterRequestViewModel rrvm) {
        authenticationModel.receiveRequest(convertToAuthenticationRequest(formatDetails(rrvm), AccountType.INSTRUCTOR));
    }

    public void regularRegistrationAttempted(RegisterRequestViewModel rrvm) {
        authenticationModel.receiveRequest(convertToAuthenticationRequest(formatDetails(rrvm), AccountType.REGULAR));
    }

    public void adminRegistrationAttempted(RegisterRequestViewModel rrvm) {

        authenticationModel.receiveRequest(convertToAuthenticationRequest(formatDetails(rrvm), AccountType.ADMIN));
    }

    public OutputBoundary<AuthenticationResponseModel<? extends Field>> getAuthenticationHandler() {
        return arm -> {
            if (arm instanceof LoginResponse lr) {

                reactLoginResponse(lr);
            } else if (arm instanceof  RegisterResponse rr) {
                reactRegisterResponse(rr);
            } else if (arm instanceof  ActivationCodeResponse acr) {
                reactActivationResponse(acr);

            }
        };
    }


    public OutputBoundary<LogoutEvent> getLogoutHandler() {
        return rm -> {
            entryFrameView.showView();
            entryFrameView.showLogin();
        };
    }

    private RegisterRequestViewModel formatDetails(RegisterRequestViewModel rrvm) {
        return new RegisterRequestViewModel(
                // TODO: implement a document filter to prevent leading and trailing spaces from being typed instead. Or simply reject such inputs in EBR
                capitalizeRemoveTrailingSpaces(rrvm.firstName()),
                capitalizeRemoveTrailingSpaces(rrvm.lastName()),
                rrvm.username().trim(),
                rrvm.email().trim(),
                rrvm.password(),
                rrvm.confirmPassword()
        );

    }

    private RegisterDetails convertToAuthenticationRequest(RegisterRequestViewModel rrvm, AccountType at) {
        return new RegisterDetails(
                rrvm.firstName(), rrvm.lastName(), rrvm.username(), rrvm.email(), rrvm.password(), rrvm.confirmPassword(), at
        );
    }

    public void codeActivationAttempted(String code) {
        authenticationModel.receiveRequest(new ActivationCodeDetails(code));
    }

    public void switchToLoginAttempted() {
        entryFrameView.showLogin();
    }

    public void switchToSignUpAttempted() {
        entryFrameView.showSignUp();
    }

    public void switchToActivationAttempted() {
        entryFrameView.showActivationPortal();
    }


    private void reactLoginResponse(LoginResponse lr) {
        if (!lr.isSuccessful()) {
            if (lr.getIssues().size() == 1) {
                entryFrameView.displayInfoMessage(lr.getIssues().get(0).issue());
            } else {
                StringBuilder builder = new StringBuilder();
                builder.append("<ul>");
                for (FieldIssue<LoginField> issue : lr.getIssues()) {
                    builder.append("<li>" + issue.issue() + "</li>");
                }
                builder.append("</ul>");
                entryFrameView.displayInfoMessage(builder.toString());
            }

        } else {

            entryFrameView.clearInputs();

            entryFrameView.hideView();
        }

    }

    private void reactRegisterResponse(RegisterResponse rr) {

        if (rr.isSuccessful()) {
            entryFrameView.displayInfoMessage("Account successfully created");
            entryFrameView.showLogin();
        } else {
            String namesError = "";
            String usernameError = "";
            String emailError = "";
            String passwordsError = "";
            for (FieldIssue<RegisterField> fi : rr.getIssues()) {
                switch (fi.field()) {

                    case USERNAME -> {
                        usernameError = formatFieldError(fi.issue());
                    }
                    case FIRST_NAME, LAST_NAME -> {
                        namesError = formatFieldError(fi.issue());
                    }
                    case EMAIL -> {
                        emailError = formatFieldError(fi.issue());
                    } case PASSWORD -> {
                        passwordsError = formatFieldError(fi.issue());
                    }
                }
            }

            entryFrameView.displayRegistrationErrors(new RegisterErrorViewModel(namesError, usernameError, emailError, passwordsError));
        }
    }
    private String capitalizeRemoveTrailingSpaces(String input) {
        String trimmedInput = input.trim();

        if (!trimmedInput.isEmpty())
            return Character.toUpperCase(trimmedInput.charAt(0)) + trimmedInput.substring(1);
        else
            return "";
    }

    private String formatFieldError(String s) {
        return ("<html>*" + s + "<html>");
    }
    private void reactActivationResponse(ActivationCodeResponse acr) {

        if (acr.isSuccessful()) {
            entryFrameView.displayInfoMessage("Activation code found. Register to create an Instructor account");

            entryFrameView.showSignUp();
        } else {

            entryFrameView.displayInfoMessage("Activation code not found");
        }

    }

}
