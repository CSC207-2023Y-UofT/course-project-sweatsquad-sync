package abr;

import ebr.User;
import fd.GymDatabase;

public class RegisterAuthenticator extends Authenticator<RegisterDetails> {

    private final GymDatabase db;

    public RegisterAuthenticator(GymDatabase db) {
        this.db = db;
    }

    public boolean authenticateInstructor(RegisterDetails details) {

    }
    @Override
    public boolean authenticate(RegisterDetails details) {

        if (!correctCharacterTypes(details.username()) || !correctCharacterTypes(details.password())) {
            setStatus("Usernames and passwords can only consist of letters, digits and underscores.");
            return false;
        }

        if (db.usernameExists(details.username())) {
            setStatus("Username already exists");
            return false;
        }

        if (db.validateAuthCode(details.authCode())) {
            setStatus("Invalid Code")
        }

    }
    private boolean correctCharacterTypes(String input) {
        // returns T iff letters (both UC + LC), digits, and underscores, else F
        return input.matches("\\w+");
    }
}
