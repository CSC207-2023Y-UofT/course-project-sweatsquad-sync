package abr.IODataModels;

import abr.IODataModels.authenticationFields.IssueList;
import abr.IODataModels.authenticationFields.RegisterField;

public class RegisterResponse extends AuthenticationResponseModel<RegisterField> {

    public RegisterResponse(boolean success, IssueList<RegisterField> issues) {
        super(success, issues);
    }

}
