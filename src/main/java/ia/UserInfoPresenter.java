package ia;

import abr.GymManager;
import abr.IODataModels.LoginDetails;
import abr.PasswordHashStrategy;
import ebr.User;
import fd.UserInfoFrame;

import java.awt.event.WindowEvent;


public class UserInfoPresenter implements Presenter {


    private final PasswordHashStrategy passwordHashStrategy;
    private final GymManager gymManager;

    private UserInfoFrame userInfoFrame;

    public UserInfoPresenter(GymManager gymManager, PasswordHashStrategy passwordHashStrategy) {
        this.passwordHashStrategy = passwordHashStrategy;
        this.gymManager = gymManager;
    }

    private User getActiveUser() {
        return gymManager.getActiveUser();
    }


    public String getActiveUserFirstName() {
        return this.getActiveUser() == null ? "Guest" : this.getActiveUser().firstName;
    }

    public String getActiveUserLastName() {
        return this.getActiveUser() == null ? "Guest" : this.getActiveUser().lastName;
    }

    public String getActiveUserEmail() {
        return this.getActiveUser() == null ? "Guest" : this.getActiveUser().email;
    }

    public boolean takenUsername(String username) {
        for (User u : gymManager.getGym().getUsers())
            if (u.getUsername().equals(username))
                return true;
        return false;
    }

    public String getActiveUserUsername() {
        return this.getActiveUser() == null ? "Guest" : getActiveUser().getUsername();
    }

    public void updateActiveUserFirstName(String newFirstName) {
        getActiveUser().firstName = newFirstName;
    }

    public void updateActiveUserLastName(String newLastName) {
        getActiveUser().firstName = newLastName;
    }

    public void updateActiveUserEmail(String newEmail) {
        getActiveUser().firstName = newEmail;
    }

    public void updateActiveUserUsername(String newUsername) {
        // Assume name collision has been taken care of
        getActiveUser().setUsername(newUsername);
    }

    public void updateActiveUserPasscode(String user, String newPasscode) {
        for (User u : gymManager.getGym().getUsers())
            if (u.getUsername().equals(user))
                u.passHash = passwordHashStrategy.hashPassword(newPasscode);
    }

    public boolean verifyUserDetails(String user, String password) {
        return gymManager.verifyLoginDetails(new LoginDetails(user, password));
    }

    public void requestCloseFrame() {
        userInfoFrame.dispatchEvent(new WindowEvent(
                userInfoFrame, WindowEvent.WINDOW_CLOSING));
    }

    public void setView(UserInfoFrame uif) {
        this.userInfoFrame = uif;
    }
}
