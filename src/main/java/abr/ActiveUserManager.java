package abr;

import abr.IODataModels.LoginDetails;
import abr.IODataModels.RegisterDetails;
import ebr.User;

public class ActiveUserManager {

    private PasswordHashStrategy passwordHashStrategy;

    private User activeUser;
    private UserManager uam;

    public ActiveUserManager(UserManager uam, PasswordHashStrategy passwordHashStrategy) {
        this.uam = uam;
        this.passwordHashStrategy = passwordHashStrategy;
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

    public void changeUserInfo(RegisterDetails rd) {
        if (!rd.username().equals("")) {
            activeUser.setUsername(rd.username());
        }
        if (!rd.password().equals("")) {
            activeUser.passHash = passwordHashStrategy.hashPassword(rd.password());
        }
        if (!rd.email().equals("")) {
            activeUser.email = rd.email();
        }
        if (!rd.username().equals("")) {
            activeUser.setUsername(rd.username());
        }
    }
}
