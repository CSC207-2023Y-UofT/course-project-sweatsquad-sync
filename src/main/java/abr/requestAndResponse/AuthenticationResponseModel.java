package abr.requestAndResponse;

import abr.ResponseModel;
import abr.requestAndResponse.authenticationFields.Field;
import abr.requestAndResponse.authenticationFields.FieldIssue;
import abr.requestAndResponse.authenticationFields.IssueList;

import java.util.List;

public abstract class AuthenticationResponseModel<T extends Field> implements ResponseModel {

    final boolean success;
    final IssueList<T> issues;

    public AuthenticationResponseModel(boolean success, IssueList<T> issues) {
        this.success = success;
        this.issues = issues;
    }

    public boolean isSuccessful() {
        return success;
    }
    public List<FieldIssue<T>> getIssues() {
        return issues.getIssues();
    }

}