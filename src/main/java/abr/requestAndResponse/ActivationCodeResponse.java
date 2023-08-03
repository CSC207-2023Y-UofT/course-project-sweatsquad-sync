package abr.requestAndResponse;

import abr.requestAndResponse.authenticationFields.ActivationCodeField;
import abr.requestAndResponse.authenticationFields.IssueList;

public class ActivationCodeResponse extends AuthenticationResponseModel<ActivationCodeField> {
    public ActivationCodeResponse(boolean success, IssueList<ActivationCodeField> fieldIssues) {
        super(success, fieldIssues);
    }
}
