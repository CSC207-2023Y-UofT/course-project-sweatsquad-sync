package abr;


import fd.GymDatabase;


public class AuthenticationUseCase extends UseCase {

    private final GymDatabase db;
    public AuthenticationUseCase (GymDatabase db) {
        this.db = db;
    }

    private static final String leftBlank = "Cannot be left blank";

    public AuthenticationResponseModel<? extends Field> requestAuthentication(AuthenticationRequestModel data) {
        if (data instanceof RegisterDetails rd) {

            return authenticateRegistration(rd);
        } else if (data instanceof LoginDetails ld) {

            return authenticateLogin(ld);
        } else if (data instanceof ActivationCodeDetails acd) {

            return authenticateActivationCode(acd);
        }
        // TODO: optional create a separate type of fieldissue that doesn't mention a specific one
        throw new RuntimeException("An error has ocurred with authentication");
    }

    private LoginResponse authenticateLogin(LoginDetails ld) {
        IssueList<LoginField> issues = new IssueList<>();

        if (ld.username().isEmpty()) {
            issues.add(new FieldIssue<>(LoginField.USERNAME, leftBlank));
        }

        if (ld.password().isEmpty()) {

            issues.add(new FieldIssue<>(LoginField.PASSWORD, leftBlank));
        }

        if (!db.verifyLogin(ld)) {
            issues.add(new FieldIssue<>(LoginField.USERNAME, "Either your username or password is incorrect"));

        }

        if (!issues.isEmpty()) {
            return new LoginResponse(false, issues);
        }

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

        if (db.usernameExists(rd.username())) {

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
        if (!db.validateAuthCode(acd)) {

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


}
