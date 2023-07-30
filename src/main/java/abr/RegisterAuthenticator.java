package abr;

import fd.GymDatabase;

public class RegisterAuthenticator extends Authenticator<RegisterDetails> {

    private final GymDatabase db;

    public RegisterAuthenticator(GymDatabase db) {
        this.db = db;
    }

    @Override
    public boolean authenticate(RegisterDetails details) {

        return false;
    }
}
