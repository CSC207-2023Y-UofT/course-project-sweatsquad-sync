package abr.requestAndResponse;

import abr.requestAndResponse.authenticationFields.IssueList;
import abr.requestAndResponse.authenticationFields.RegistrationField;

public class RegistrationResponse extends AuthenticationResponseModel<RegistrationField> {
    public RegistrationResponse(boolean success, IssueList<RegistrationField> fieldIssues) {
        super(success, fieldIssues);
    }
}
