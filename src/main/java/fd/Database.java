package fd;

// import statements
import ebr.Gym;
import ebr.Instructor;
import ebr.User;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Database {

    // declares database vars - hashmap, txt file, and hash toggle
    private final String filename = "user_credentials.txt";
    private Gym gym;

    // database constructor
    public Database() {
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

    public void save(Gym g) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
        oos.writeObject(g);
        oos.close();
    }

    public Gym load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
        Gym g = (Gym)ois.readObject();
        ois.close();
        return g;
    }

    // method to register new user, returns T if successful, F if already exists
    public boolean register(String firstName, String lastName, String username,
                            String email, String passcode, int level) {
        if (!validateInput(username) || !validateInput(passcode)) {
            System.out.println("Invalid input");
            return false;
        }

        for (User u : gym.getUsers()) {
            if (u.name.equals(username)) {
                 System.out.println("Username already exists");
                return false;
            }
        }

        gym.addUser(new User(username, hashPassword(passcode)));
        return true;
    }

    // method to check if login credentials are valid
    public boolean validateLogin(String username, String password) {
        for (User u : gym.getUsers())
            if (u.name.equals(username))
                if (u.passHash.equals(hashPassword(password))) {
                    System.out.println("Logged in successfully");
                    return true;
                } else {
                    System.out.println("Incorrect password");
                    return false;
                }
        System.out.println("User does not exist");
        return false;
    }

    // checks if input's valid
    private boolean validateInput(String input) {
        return input.matches("\\w+");
    }

    // method to check if auth code is valid
    public boolean validateAuthCode(String code) {
        for (User u : gym.getUsers())
            if (u instanceof Instructor && ((Instructor) u).tempAuth != null)
                if (((Instructor) u).tempAuth.equals(hashPassword(code))) {
                    System.out.println("Authentication code is valid");
                    return true;
                }


        System.out.println("Authentication code is not valid");
        return false;
    }

    private String hashPassword(String password) {
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
