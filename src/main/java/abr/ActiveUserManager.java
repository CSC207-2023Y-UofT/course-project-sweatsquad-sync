package abr;

import abr.requestAndResponse.LoginLogout;
import ebr.GymAdmin;
import ebr.Instructor;
import ebr.User;

public class ActiveUserManager {

    private User activeUser;
    private UserAccountManager uam;
    private OutputBoundary<LoginLogout> loginListener;

    public ActiveUserManager(UserAccountManager uam, OutputBoundary<LoginLogout> loginListener) {
        this.uam = uam;
        this.loginListener = loginListener;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void loginUser(User u) {

        activeUser = u;
        if (u instanceof Instructor) {
            loginListener.receiveResponse(LoginLogout.LOGIN_INSTRUCTOR);

        } else if (u instanceof GymAdmin) {
            loginListener.receiveResponse(LoginLogout.LOGIN_ADMIN);

        } else{
            loginListener.receiveResponse(LoginLogout.LOGIN_REGULAR_USER);
        }

    };

    public void logoutUser() {

        activeUser = null;
    }


}
