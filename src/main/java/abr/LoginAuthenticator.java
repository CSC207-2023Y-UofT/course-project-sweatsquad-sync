package abr;

import fd.GymDatabase;

public class LoginAuthenticator extends Authenticator<LoginDetails> {

    private final GymDatabase db;

    public LoginAuthenticator(GymDatabase db) {
        this.db = db;
    }

    @Override
    public boolean authenticate(LoginDetails details) {
        if (!db.verifyLogin(details)) {
            setStatus("Either your username or password is incorrect");
            return false;
        }
        return true;
    }
}
