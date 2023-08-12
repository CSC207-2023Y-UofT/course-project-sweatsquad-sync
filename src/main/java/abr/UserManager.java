package abr;

import abr.IODataModels.RegisterDetails;
import abr.IODataModels.ActivationCodeDetails;
import abr.IODataModels.LoginDetails;
import ebr.*;

public class UserManager {

    private final Gym gym;
    private final PasswordHashStrategy hashStrategy;

    private Instructor instructorToActivate;


    public UserManager(Gym gym, PasswordHashStrategy hashStrategy) {
        this.gym = gym;
        this.hashStrategy = hashStrategy;
    }

    public void register(RegisterDetails d) {
        switch (d.accountType()) {

            case INSTRUCTOR -> instructorToActivate.claim(d.username(), hashPassword(d.password()), d.firstName(), d.lastName(), d.email());
            case REGULAR -> gym.addUser(new User(d.username(), hashPassword(d.password()), d.firstName(), d.lastName(), d.email()));
            case ADMIN -> gym.addUser(new GymAdmin(d.username(), hashPassword(d.password()), d.firstName(), d.lastName(), d.email()));
        }

    }

    // method to check if user login exists in database
    public boolean verifyLogin(LoginDetails ld) {
        for (User u : gym.getUsers())
            if (u.getUsername().equals(ld.username()))
                if (u.passHash.equals(hashPassword(ld.password()))) {
                    System.out.println("Log in found");
                    return true;
                } else {
                    System.out.println("Incorrect password");
                    return false;
                }
        System.out.println("User does not exist");
        return false;
    }


    public User retrieveUser(LoginDetails ld) {
        for (User u : gym.getUsers())
            if (u.getUsername().equals(ld.username()))
                if (u.passHash.equals(hashPassword(ld.password()))) {
                    return u;
                }
        throw new RuntimeException("User not found. Use verifyLogin first");
    }

    // checks if input's valid

    public boolean usernameExists(String username) {
        for (User u : gym.getUsers()) {
            if (u.getUsername().equals(username)) {
                System.out.println("Username already exists");
                return true;
            }
        }

        return false;

    }

    // method to check if auth code is valid
    public boolean validateAuthCode(ActivationCodeDetails inputCode) {
        for (User u : gym.getUsers())
            if (u instanceof Instructor i && ((Instructor) u).getAuthCode() != null)
                if (i.getAuthCode().equals(inputCode.code())) {
                    instructorToActivate = i;
                    System.out.println("Authentication code is valid");
                    return true;
                }

        System.out.println("Authentication code is not valid");
        return false;
    }


    private String hashPassword(String password) {
        return hashStrategy.hashPassword(password);
    }

}
