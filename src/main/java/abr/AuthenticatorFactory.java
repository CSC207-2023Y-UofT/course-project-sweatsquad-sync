package abr;

import fd.GymDatabase;

public class AuthenticatorFactory {
    GymDatabase db;

    public AuthenticatorFactory(GymDatabase db) {
        this.db = db;
    }

    public Authenticator getAuthenticator(UserDetails data) {
        if (data instanceof RegisterDetails) {
            return new RegisterAuthenticator(db);
        } else if (data instanceof LoginDetails) {
            return new LoginAuthenticator(db);
        } else {
            return new InstructorAuthenticator(db);
        }
    }

}
