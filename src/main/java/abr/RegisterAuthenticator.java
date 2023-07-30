package abr;

import ebr.User;
import fd.GymDatabase;

public class RegisterAuthenticator extends Authenticator<RegisterDetails> {

    private final GymDatabase db;

    public RegisterAuthenticator(GymDatabase db) {
        this.db = db;
    }


    @Override
    public boolean authenticate(RegisterDetails details) {

        if (details.firstName().isEmpty() || details.lastName().isEmpty()) {
            setStatus("First and last name fields " +
                    "must not be empty");
            return false;
        }

        if (!details.email().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            setStatus("Invalid email format");
            return false;
        }

        if (details.username().length() < 3) {
            setStatus("Username must be at least " +
                    "3 characters long");
            return false;
        }

        if (!correctCharacterTypes(details.username()) || !correctCharacterTypes(details.password())) {
            setStatus("Usernames and passwords can only consist of letters, digits and underscores.");
            return false;
        }

        if (db.usernameExists(details.username())) {
            setStatus("Username already exists");
            return false;
        }

    }
    private boolean correctCharacterTypes(String input) {
        // returns T iff letters (both UC + LC), digits, and underscores, else F
        return input.matches("\\w+");
    }
}
