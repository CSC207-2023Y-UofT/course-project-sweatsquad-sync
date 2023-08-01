package abr;


import fd.GymDatabase;

public class AuthenticationUseCase extends UseCase {

    private final GymDatabase db;
    public AuthenticationUseCase (GymDatabase db) {
        this.db = db;
    }

    public AuthenticationResponseModel requestAuthentication(AuthenticationRequestModel data) {
        if (data instanceof RegisterDetails d) {
            if (db.usernameExists(d.username())) {
                return new AuthenticationResponseModel(false, "Username already exists");
            } else {

                return verifyRegisterDetailsFormat(d);
            }

        } else if (data instanceof LoginDetails d) {
            if (!db.verifyLogin(d)) {
                return new AuthenticationResponseModel(false, "Either your username or password is incorrect");

            } else {

                return new AuthenticationResponseModel(true, "Login record found");
            }

        } else if (data instanceof ActivationCodeDetails c) {

            if (!db.validateAuthCode(c)) {

                return new AuthenticationResponseModel(false, "Authentication failed");
            } else {

                return new AuthenticationResponseModel(false, "Authentication success");
            }
        }

        return new AuthenticationResponseModel(false, "An error has occurred.");
    }


    private AuthenticationResponseModel verifyRegisterDetailsFormat(RegisterDetails details) {
        String status = "Registration details verified";
        boolean verified = true;
        if (details.anyBlank()) {
            status = "Fields were left blank";
            verified = false;
        }

        if (!details.correctEmailFormat()) {
            status = "Invalid email format";
            verified =  false;
        }

        if (details.username().length() < 3) {
            status = "Username must be at least 3 characters long";
            verified = false;
        }

        if (!correctCharacterTypes(details.username()) || !correctCharacterTypes(details.password())) {
            status = "Usernames and passwords can only consist of letters, digits and underscores.";
            verified =  false;
        }

        if (db.usernameExists(details.username())) {
            status = "Username already exists";
            verified = false;
        }

        return new AuthenticationResponseModel(verified, status);

    }

    private boolean correctCharacterTypes(String input) {
        // returns T iff letters (both UC + LC), digits, and underscores, else F
        return input.matches("\\w+");
    }


}
