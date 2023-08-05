package abr;

import abr.inputOutputData.AccountType;
import abr.inputOutputData.LoginEvent;
import ebr.GymAdmin;
import ebr.Instructor;
import ebr.RegisteredUser;
import ebr.User;

public class ActiveUserManager {

    private User activeUser;
    private UserAccountManager uam;
    private OutputBoundary<LoginEvent> loginListener;

    public ActiveUserManager(UserAccountManager uam, OutputBoundary<LoginEvent> loginListener) {
        this.uam = uam;
        this.loginListener = loginListener;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void loginUser(User u) {

        activeUser = u;
        if (u instanceof Instructor) {
            loginListener.receiveResponse(new LoginEvent(AccountType.INSTRUCTOR));

        } else if (u instanceof GymAdmin) {
            loginListener.receiveResponse(new LoginEvent(AccountType.ADMIN));

        } else if (u instanceof RegisteredUser){
            loginListener.receiveResponse(new LoginEvent(AccountType.REGISTERED));
        } else {

            loginListener.receiveResponse(new LoginEvent(AccountType.REGULAR));
        }

    };

    public void logoutUser() {

        activeUser = null;
    }


}
