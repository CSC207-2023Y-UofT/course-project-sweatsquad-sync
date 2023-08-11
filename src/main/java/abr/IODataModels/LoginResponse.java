package abr.IODataModels;

import abr.IODataModels.authenticationFields.IssueList;
import abr.IODataModels.authenticationFields.LoginField;

public class LoginResponse extends AuthenticationResponseModel<LoginField> {
    public LoginResponse(boolean success, IssueList<LoginField> issues) {
        super(success, issues);
    }


}
