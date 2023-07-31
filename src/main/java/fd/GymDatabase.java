package fd;

import abr.ActivationCodeDetails;
import abr.LoginDetails;
import abr.RegisterDetails;
import ebr.Gym;
import ebr.User;

import java.io.IOException;

public interface GymDatabase {

    public User getActiveUser();

    public void setActiveUser(User u);

    public void save() throws IOException;

    public Gym load() throws IOException, ClassNotFoundException;

    public void register(RegisterDetails rd, int level);

    public boolean verifyLogin(LoginDetails ld);

    public boolean usernameExists(String username);

    public boolean validateAuthCode(ActivationCodeDetails code);
}
