package abr.requestAndResponse;

import abr.requestAndResponse.authenticationFields.IssueList;
import abr.requestAndResponse.authenticationFields.LoginField;

public class LoginResponse extends AuthenticationResponseModel<LoginField> {
    public LoginResponse(boolean success, IssueList<LoginField> fieldIssues) {
        super(success, fieldIssues);
    }
}
