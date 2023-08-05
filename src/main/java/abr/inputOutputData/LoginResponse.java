package abr.inputOutputData;

import abr.inputOutputData.authenticationFields.IssueList;
import abr.inputOutputData.authenticationFields.LoginField;

public class LoginResponse extends AuthenticationResponseModel<LoginField> {
    public LoginResponse(boolean success, IssueList<LoginField> fieldIssues) {
        super(success, fieldIssues);
    }
}
