package fd;

// import statements
import abr.ActivationCodeDetails;
import abr.LoginDetails;
import abr.RegisterDetails;
import ebr.Gym;
import ebr.Instructor;
import ebr.User;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileDatabase implements GymDatabase {

    // declares database vars - hashmap, txt file, and hash toggle
    private final String filename = "gym.bin";
    private Gym gym;
    public User activeUser;

    // database constructor
    public FileDatabase() {
        try {
            gym = load();
        }
        catch (IOException e) {
            gym = new Gym("Gym");
        }
        catch (ClassNotFoundException e) {
            System.err.println("File is corrupt, creating new gym");
            gym = new Gym("Gym");
        }
    }

    @Override
    public User getActiveUser() {
        return activeUser;
    }

    @Override
    public void setActiveUser(User u) {

    }

    @Override
    public void save() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
        oos.writeObject(gym);
        oos.close();
    }

    @Override
    public Gym load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
        Gym g = (Gym)ois.readObject();
        ois.close();
        return g;
    }

    // method to register new user, returns T if successful, F if already exists
    @Override
    public void register(@NotNull RegisterDetails d, int level) {


        gym.addUser(new User(d.username(), hashPassword(d.password()), d.firstName(), d.lastName(), d.email()));
    }

    // method to check if user login exists in database
    @Override
    public boolean verifyLogin(LoginDetails ld) {
        for (User u : gym.getUsers())
            if (u.name.equals(ld.username()))
                if (u.passHash.equals(hashPassword(ld.password()))) {
                    System.out.println("Logged in found");
                    activeUser = u;
                    return true;
                } else {
                    System.out.println("Incorrect password");
                    return false;
                }
        System.out.println("User does not exist");
        return false;
    }

    // checks if input's valid
    @Override
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
    @Override
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
