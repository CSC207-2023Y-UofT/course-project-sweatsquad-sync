package abr;

import fd.GymDatabase;

public class InstructorAuthenticator extends Authenticator<AuthCode> {


    private final GymDatabase db;

    public InstructorAuthenticator(GymDatabase db) {
        this.db = db;
    }

    @Override
    public boolean authenticate(AuthCode inputCode) {
        if(db.validateAuthCode(inputCode)) {
            return true;
        } else {
            setStatus("Invalid authentication code.");
            return false;
        }
    }
}
