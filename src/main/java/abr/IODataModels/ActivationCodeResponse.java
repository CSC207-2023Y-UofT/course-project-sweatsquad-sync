package abr.IODataModels;

import abr.IODataModels.authenticationFields.ActivationCodeField;
import abr.IODataModels.authenticationFields.IssueList;

public class ActivationCodeResponse extends AuthenticationResponseModel<ActivationCodeField> {
    public ActivationCodeResponse(boolean success, IssueList<ActivationCodeField> issues, String comment) {
        super(success, issues);
    }


}
