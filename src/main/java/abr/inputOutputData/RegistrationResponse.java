package abr.inputOutputData;

import abr.inputOutputData.authenticationFields.IssueList;
import abr.inputOutputData.authenticationFields.RegistrationField;

public class RegistrationResponse extends AuthenticationResponseModel<RegistrationField> {
    public RegistrationResponse(boolean success, IssueList<RegistrationField> fieldIssues) {
        super(success, fieldIssues);
    }
}
