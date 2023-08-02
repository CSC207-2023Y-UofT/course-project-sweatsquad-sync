package abr;

import java.util.ArrayList;
import java.util.List;

public class IssueList<T extends Field>{
    private final List<FieldIssue<T>> issues = new ArrayList<>();

    public void add(FieldIssue<T> fi) {
        for (FieldIssue<T> other: issues) {
            if (other.field().equals(fi.field())) {
                return;
            }
        }
        issues.add(fi);
    }
    public boolean isEmpty() {
        return issues.isEmpty();
    }
    public List<FieldIssue<T>> getIssues() {
        return issues;
    }

}
