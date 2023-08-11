package abr.IODataModels;

import abr.ResponseModel;
import abr.IODataModels.authenticationFields.Field;
import abr.IODataModels.authenticationFields.FieldIssue;
import abr.IODataModels.authenticationFields.IssueList;

import java.util.List;

public abstract class AuthenticationResponseModel<F extends Field> implements ResponseModel {

    final boolean success;
    final IssueList<F> issues;

    public AuthenticationResponseModel(boolean success, IssueList<F> issues) {
        this.success = success;
        this.issues = issues;
    }

    public boolean isSuccessful() {
        return success;
    }
    public List<FieldIssue<F>> getIssues() {
        return issues.getIssues();
    }

}