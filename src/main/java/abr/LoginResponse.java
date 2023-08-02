package abr;

import java.util.List;

public class LoginResponse extends AuthenticationResponseModel<LoginField> {
    public LoginResponse(boolean success, IssueList<LoginField> fieldIssues) {
        super(success, fieldIssues);
    }
}
