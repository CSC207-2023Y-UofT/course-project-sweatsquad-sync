package fd;

// import statements
import java.util.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Database {

    // declares database vars - hashmap, txt file, and hash toggle
    private HashMap<String, String> users;
    private HashMap<String, String> authCodes;
    private final String filename = "user_credentials.txt";
    private final String authFilename = "authenticate_codes.txt";
    private boolean hashPass = true; // can be toggled between T/F to hash
    private boolean hashCodes = false; // can be toggled between T/F to hash codes

    // database constructor
    public Database() {
        users = new HashMap<>(); // initializes the hashmap
        authCodes = new HashMap<>(); // initializes the auth codes hashmap
        loadUsers(); // loads existing users into hashmap
        loadAuthCodes(); // loads existing auth codes into hashmap
    }

    // method to load credentials from the "user_credentials.txt" file
    private void loadUsers() {
        try { // try-catch block to handle any IO  exceptions
            File file = new File(filename); // creating a file object for u_c
            if (file.exists()) { // checks if the file exists
                Scanner reader = new Scanner(file); // creates scanner to read
                while (reader.hasNextLine()) { // iterates through lines in file
                    // splits line at the comma (between user and pass)
                    String[] credentials = reader.nextLine().split(",");
                    // adds credentials onto the hashmap
                    users.put(credentials[0], credentials[1] + "," +
                            credentials[2] + "," + credentials[3] + "," +
                            credentials[4]);
                }
                reader.close(); // closes scanner once done using
            } else {
                file.createNewFile(); // creates file if it doesn't exist
            }
        } catch (IOException e) { // catches any IO exception
            e.printStackTrace(); // throws/prints error to console
        }
    }

    // method to register new user, returns T if successful, F if already exists
    public boolean register(String firstName, String lastName, String username,
                            String email, String passcode) {
        if (!validateInput(username) || !validateInput(passcode)) {
            System.out.println("Invalid input");
            return false;
        } else if(users.containsKey(username)) {
            System.out.println("Username already exists");
            return false;
        } else {
            if (hashPass) {
                passcode = hashPassword(passcode);
            }
            users.put(username, firstName + "," + lastName + "," + email + "," +
                      passcode);
            saveUsers();
            return true;
        }
    }

    // method to load auth codes from the "authenticate_codes.txt" file
    private void loadAuthCodes() {
        try { // try-catch block to handle any IO exceptions
            File file = new File(authFilename); // creating a file object for authenticate_codes
            if (file.exists()) { // checks if the file exists
                Scanner reader = new Scanner(file); // creates scanner to read
                while (reader.hasNextLine()) { // iterates through lines in file
                    String code = reader.nextLine();
                    if (hashCodes) {
                        code = hashPassword(code); // use the same hashing function as for passwords
                    }
                    authCodes.put(code, ""); // adds code to the hashmap
                }
                reader.close(); // closes scanner once done using
            } else {
                file.createNewFile(); // creates file if it doesn't exist
            }
        } catch (IOException e) { // catches any IO exception
            e.printStackTrace(); // throws/prints error to console
        }
    }

    // method to check if login credentials are valid
    public boolean validateLogin(String username, String password) {
        if(users.containsKey(username)) {
            String storedPassword = users.get(username).split(",")[3];
            if (hashPass) { //checks for hash toggle
                password = hashPassword(password);
            }
            if (storedPassword.equals(password)) {
                System.out.println("Logged in successfully");
                return true;
            } else {
                System.out.println("Incorrect password");
                return false;
            }
        } else {
            System.out.println("User does not exist");
            return false;
        }
    }

    // checks if input's valid
    private boolean validateInput(String input) {
        // returns T iff letters (both UC + LC), digits, and underscores, else F
        return input.matches("\\w+");
    }

    // method to check if auth code is valid
    public boolean validateAuthCode(String code) {
        if (hashCodes) {
            code = hashPassword(code);
        }
        if (authCodes.containsKey(code)) {
            System.out.println("Authentication code is valid");
            return true;
        } else {
            System.out.println("Authentication code is not valid");
            return false;
        }
    }

    // saves hashmap to the file
    private void saveUsers() {
        try { // to catch IO exceptions
            FileWriter writer = new FileWriter(filename); // file-writer init

            // loops through each entry in the hashmap
            for (Map.Entry<String, String> entry : users.entrySet()) {
                // writes each entry to the file, separated w/ comma
                writer.write(entry.getKey() + "," + entry.getValue() + "\n");
            }
            writer.close(); // closes the FileWriter
        } catch (IOException e) { // catches IO exception
            e.printStackTrace(); // throws/prints stack trace
        }
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
            return password;
        }
    }
}
