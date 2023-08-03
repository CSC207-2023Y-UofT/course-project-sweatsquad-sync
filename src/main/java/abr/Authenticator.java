package abr;

import abr.requestAndResponse.*;
import abr.requestAndResponse.authenticationFields.*;


public class Authenticator implements InputBoundary<AuthenticationRequestModel> {

    private final UserAccountManager uam;
    private final ActiveUserManager aum;
    private final OutputBoundary<AuthenticationResponseModel<? extends Field>> outputBoundary;


    public Authenticator(UserAccountManager uam, ActiveUserManager aum, OutputBoundary<AuthenticationResponseModel<? extends Field>> outputBoundary) {
        this.aum = aum;
        this.uam = uam;
        this.outputBoundary = outputBoundary;
    }

    private static final String leftBlank = "Cannot be left blank";

    private LoginResponse authenticateLogin(LoginDetails ld) {
        IssueList<LoginField> issues = new IssueList<>();

        if (ld.username().isEmpty()) {
            issues.add(new FieldIssue<>(LoginField.USERNAME, leftBlank));
        }

        if (ld.password().isEmpty()) {

            issues.add(new FieldIssue<>(LoginField.PASSWORD, leftBlank));
        }

        if (!uam.verifyLogin(ld)) {
            issues.add(new FieldIssue<>(LoginField.USERNAME, "Either your username or password is incorrect"));

        }

        if (!issues.isEmpty()) {
            return new LoginResponse(false, issues);
        }

        aum.loginUser(uam.retrieveUser(ld));
        return new LoginResponse(true, new IssueList<>());


    }

    private RegistrationResponse authenticateRegistration(RegisterDetails rd) {
        IssueList<RegistrationField> issues = new IssueList<>();
        String[] fieldValues = {rd.username(), rd.password(), rd.confirmPassword(), rd.firstName(), rd.lastName(), rd.email()};
        RegistrationField[] fields = RegistrationField.values();

        boolean anyBlank = false;
        for (int i = 0; i < fields.length; i++) {

            if (fieldValues[i].isEmpty()) {
                anyBlank = true;
                issues.add(new FieldIssue<>(fields[i], leftBlank));
            }
        }


        if (!rd.correctEmailFormat()) {
            issues.add(new FieldIssue<>(RegistrationField.EMAIL, "Invalid email format"));
        }

        if (!correctCharacterTypes(rd.username())) {
            issues.add(new FieldIssue<>(RegistrationField.USERNAME, "Can only consist of letters, digits and underscores."));
        }

        if (rd.username().length() < 3) {
            issues.add(new FieldIssue<>(RegistrationField.USERNAME, "Username must be at least 3 characters long"));
        }

        if (!correctCharacterTypes(rd.password())) {
            issues.add(new FieldIssue<>(RegistrationField.PASSWORD, "Can only consist of letters, digits and underscores."));
        }

        if (!rd.password().equals(rd.confirmPassword())) {
            issues.add(new FieldIssue<>(RegistrationField.PASSWORD, "Passwords must match"));
        }

        if (uam.usernameExists(rd.username())) {

            issues.add(new FieldIssue<>(RegistrationField.USERNAME, "Username already exists"));
        }

        if (!issues.isEmpty()) {
            return new RegistrationResponse(false, issues);
        }
        return new RegistrationResponse(true, new IssueList<>());

    }

    private ActivationCodeResponse authenticateActivationCode(ActivationCodeDetails acd) {
        IssueList<ActivationCodeField> issues = new IssueList<>();

        if (acd.code().isEmpty()) {
            issues.add(new FieldIssue<>(ActivationCodeField.ACTIVATION_CODE_FIELD, leftBlank));
        }
        if (!uam.validateAuthCode(acd)) {

            issues.add(new FieldIssue<>(ActivationCodeField.ACTIVATION_CODE_FIELD, "Activation code was not found"));
        }
        if (!issues.isEmpty()) {
            return new ActivationCodeResponse(false, issues);
        }
        return new ActivationCodeResponse(true, new IssueList<>());
    }



    private boolean correctCharacterTypes(String input) {
        // returns T iff letters (both UC + LC), digits, and underscores, else F
        return input.matches("\\w+");
    }


    @Override
    public void receiveRequest(AuthenticationRequestModel rm) {

        if (rm instanceof RegisterDetails rd) {

            RegistrationResponse response = authenticateRegistration(rd);
            outputBoundary.receiveResponse(authenticateRegistration(rd));

        } else if (rm instanceof LoginDetails ld) {
            LoginResponse response = authenticateLogin(ld);
            if (response.isSuccessful()) {
                aum.loginUser(uam.retrieveUser(ld));
            }
            outputBoundary.receiveResponse(response);
        } else if (rm instanceof ActivationCodeDetails acd) {

            outputBoundary.receiveResponse(authenticateActivationCode(acd));
        }
        throw new RuntimeException("Request model not recognized by this use case");
    }
}
