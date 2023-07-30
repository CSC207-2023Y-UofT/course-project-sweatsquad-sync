package fd;

import abr.LoginDetails;
import abr.RegisterDetails;
import ebr.Gym;

import java.io.IOException;

public interface GymDatabase {

    public void save() throws IOException;

    public Gym load() throws IOException, ClassNotFoundException;

    public void register(RegisterDetails rd, int level);

    public boolean userExists(LoginDetails ld);

    public boolean validateAuthCode(String code);
}
