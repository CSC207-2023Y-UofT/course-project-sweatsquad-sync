package abr;

import java.util.List;

public class RegistrationResponse extends AuthenticationResponseModel<RegistrationField> {
    public RegistrationResponse(boolean success, IssueList<RegistrationField> fieldIssues) {
        super(success, fieldIssues);
    }
}
