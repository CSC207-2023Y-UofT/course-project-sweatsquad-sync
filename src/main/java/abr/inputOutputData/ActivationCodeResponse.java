package abr.inputOutputData;

import abr.inputOutputData.authenticationFields.ActivationCodeField;
import abr.inputOutputData.authenticationFields.IssueList;

public class ActivationCodeResponse extends AuthenticationResponseModel<ActivationCodeField> {
    public ActivationCodeResponse(boolean success, IssueList<ActivationCodeField> fieldIssues) {
        super(success, fieldIssues);
    }
}
