package abr;

import abr.IODataModels.*;
import abr.IODataModels.authenticationFields.*;


public class Authenticator  {
    private final UserManager userManager;

    public Authenticator(UserManager userManager) {
        this.userManager = userManager;
    }

    private static final String leftBlank = " cannot be left blank";

    private String isLeftBlankMessage(Field field) {

        return field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1) + leftBlank;
    }

    public LoginResponse authenticateLogin(LoginDetails ld) {
        IssueList<LoginField> issues = new IssueList<>();

        if (ld.username().isEmpty()) {
            issues.add(new FieldIssue<>(LoginField.USERNAME, isLeftBlankMessage(LoginField.USERNAME)));
        }

        if (ld.password().isEmpty()) {

            issues.add(new FieldIssue<>(LoginField.PASSWORD, isLeftBlankMessage(LoginField.PASSWORD)));
        }

        if (!userManager.verifyLogin(ld)) {
            issues.add(new FieldIssue<>(LoginField.USERNAME, "Either your username or password is incorrect"));

        }

        if (!issues.isEmpty()) {
            return new LoginResponse(false, issues);
        } else {
            return new LoginResponse(true, new IssueList<>());
        }

    }

    public RegisterResponse authenticateRegistration(RegisterDetails rd) {
        IssueList<RegisterField> issues = new IssueList<>();
        String[] fieldValues = {rd.username(), rd.password(), rd.confirmPassword(), rd.firstName(), rd.lastName(), rd.email()};
        RegisterField[] fields = RegisterField.values();

        for (int i = 0; i < fields.length; i++)
            if (fieldValues[i].isEmpty())
                issues.add(new FieldIssue<>(fields[i], isLeftBlankMessage(fields[i])));


        if (!rd.correctEmailFormat())
            issues.add(new FieldIssue<>(RegisterField.EMAIL, "Invalid email format"));

        if (!correctCharacterTypes(rd.username()))
            issues.add(new FieldIssue<>(RegisterField.USERNAME, "Can only consist of letters, digits and underscores."));

        if (rd.username().length() < 3)
            issues.add(new FieldIssue<>(RegisterField.USERNAME, "Username must be at least 3 characters long"));

        if (!correctCharacterTypes(rd.password()))
            issues.add(new FieldIssue<>(RegisterField.PASSWORD, "Can only consist of letters, digits and underscores."));

        if (!rd.password().equals(rd.confirmPassword()))
            issues.add(new FieldIssue<>(RegisterField.PASSWORD, "Passwords must match"));

        if (userManager.usernameExists(rd.username()))
            issues.add(new FieldIssue<>(RegisterField.USERNAME, "Username already exists"));

        if (!issues.isEmpty())
            return new RegisterResponse(false, issues);

        return new RegisterResponse(true, new IssueList<>());

    }

    public ActivationCodeResponse authenticateActivationCode(ActivationCodeDetails acd) {
        IssueList<ActivationCodeField> issues = new IssueList<>();

        if (acd.code().isEmpty()) {
            issues.add(new FieldIssue<>(ActivationCodeField.ACTIVATION_CODE, isLeftBlankMessage(ActivationCodeField.ACTIVATION_CODE)));
        }
        if (!userManager.validateAuthCode(acd)) {

            issues.add(new FieldIssue<>(ActivationCodeField.ACTIVATION_CODE, "Activation code was not found"));
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
