package abr;

import java.util.List;

public class ActivationCodeResponse extends AuthenticationResponseModel<ActivationCodeField> {
    public ActivationCodeResponse(boolean success, IssueList<ActivationCodeField> fieldIssues) {
        super(success, fieldIssues);
    }
}
