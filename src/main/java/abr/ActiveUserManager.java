package abr;

import abr.IODataModels.LoginDetails;
import ebr.User;

public class ActiveUserManager {

    private User activeUser;
    private UserManager uam;

    public ActiveUserManager(UserManager uam) {
        this.uam = uam;
    }

    public User getActiveUser() {
        return activeUser;
    }


    public void logoutUser() {

        activeUser = null;
    }

    public void loginUser(LoginDetails rm) {

        activeUser = uam.retrieveUser(rm);
    }
}
