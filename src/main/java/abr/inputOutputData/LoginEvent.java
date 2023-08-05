package abr.inputOutputData;

import abr.ResponseModel;

public class LoginEvent implements ResponseModel {

    private AccountType at;
    public LoginEvent(AccountType at) {
        this.at = at;
    }

    public AccountType getAccountType() {
        return at;
    }
}
