package abr;

import abr.inputOutputData.RegisterDetails;
import abr.inputOutputData.ActivationCodeDetails;
import abr.inputOutputData.LoginDetails;
import ebr.*;
import fd.GymDatabase;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserAccountManager {

    private final GymDatabase db;
    private final Gym gym;


    public UserAccountManager(GymDatabase db) {
        this.db = db;
        try {
            this.gym = db.load();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void register(@NotNull RegisterDetails d) {

        switch (d.accountType()) {

            case INSTRUCTOR -> {
                gym.addUser(new Instructor(d.username(), hashPassword(d.password()), d.firstName(), d.lastName(), d.email()));
            }
            case REGULAR -> {

                gym.addUser(new User(d.username(), hashPassword(d.password()), d.firstName(), d.lastName(), d.email()));
            }
            case ADMIN -> {

                gym.addUser(new GymAdmin(d.username(), hashPassword(d.password()), d.firstName(), d.lastName(), d.email()));
            }
        }

    }

    // method to check if user login exists in database

    public boolean verifyLogin(LoginDetails ld) {
        for (User u : gym.getUsers())
            if (u.name.equals(ld.username()))
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
            if (u.name.equals(ld.username()))
                if (u.passHash.equals(hashPassword(ld.password()))) {
                    return u;
                }
        throw new RuntimeException("User not found. Use verifyLogin first");
    }

    // checks if input's valid

    public boolean usernameExists(String username) {
        for (User u : gym.getUsers()) {
            if (u.name.equals(username)) {
                System.out.println("Username already exists");
                return false;
            }
        }

        return false;

    }
    // method to check if auth code is valid
    public boolean validateAuthCode(ActivationCodeDetails inputCode) {
        for (User u : gym.getUsers())
            if (u instanceof Instructor && ((Instructor) u).tempAuth != null)
                if (((Instructor) u).tempAuth.equals(hashPassword(inputCode.code()))) {
                    System.out.println("Authentication code is valid");
                    return true;
                }


        System.out.println("Authentication code is not valid");
        return false;
    }

    private @NotNull String hashPassword(@NotNull String password) {
        try {
            // inst of the SHA-256 messageDigest
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // hashes password
            byte[] hashedPassword = md.digest(password.getBytes());

            StringBuilder sb = new StringBuilder(); // to create hash pw str
            for (byte b : hashedPassword) { // loops through each bye
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("Cannot use SHA-256"); // Don't fail silently
        }
    }

}
