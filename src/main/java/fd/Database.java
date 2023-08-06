package fd;

// import statements
import ebr.*;

import java.util.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;

public class Database {

    // declares database vars - hashmap, txt file, and hash toggle
    private final String filename = "gym.bin";
    private Gym gym;
    private User activeUser;

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

    public void save() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
        oos.writeObject(gym);
        oos.close();
    }

    public Gym load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
        Gym g = (Gym)ois.readObject();
        ois.close();
        return g;
    }

    // method to register new user, returns T if successful, F if already exists
    public boolean registerBasicUser(String firstName, String lastName, String username,
                            String email, String passcode) {
        if (!validateInput(username)) {
            System.out.println("Invalid input");
            return false;
        }

        for (User u : gym.getUsers()) {
            if (u.getName().equals(username)) {
                System.out.println("Username already exists");
                return false;
            }
        }

        gym.addUser(new User(username, hashPassword(passcode), firstName, lastName, email));
        return true;
    }

    public boolean claimInstructor(String auth, String firstName, String lastName, String username,
                                     String email, String passcode) {
        if (!validateInput(username)) {
            System.out.println("Invalid input");
            return false;
        }

        for (User u : gym.getUsers()) {
            if (u.getName().equals(username)) {
                System.out.println("Username already exists");
                return false;
            }
        }

        validateAuthCode(auth).claim(username, hashPassword(passcode), firstName, lastName, email);
        return true;
    }

    public boolean registerAdmin(String firstName, String lastName, String username,
                                   String email, String passcode) {
        if (!validateInput(username)) {
            System.out.println("Invalid input");
            return false;
        }

        for (User u : gym.getUsers()) {
            if (u.getName().equals(username)) {
                System.out.println("Username already exists");
                return false;
            }
        }

        gym.addUser(new GymAdmin(username, hashPassword(passcode), firstName, lastName, email));
        return true;
    }

    // method to check if login credentials are valid
    public boolean validateLogin(String username, String password) {
        for (User u : gym.getUsers())
            if (u.getName().equals(username))
                if (u.passHash.equals(hashPassword(password))) {
                    System.out.println("Logged in successfully");
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
    private boolean validateInput(String input) {
        // returns T iff letters (both UC + LC), digits, and underscores, else F
        return input.matches("\\w+");
    }

    // method to check if auth code is valid
    public Instructor validateAuthCode(String code) {
        for (User u : gym.getUsers())
            if (u instanceof Instructor && ((Instructor) u).getAuthCode() != null)
                if (((Instructor) u).getAuthCode().equals(code)) {
                    System.out.println("Authentication code is valid");
                    return (Instructor)u;
                }


        System.out.println("Authentication code is not valid");
        return null;
    }

    private String hashPassword(String password) {
        try {
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

    public void logout() {
        this.activeUser = null;
    }

    public String getActiveUserFirstName() {
        return this.activeUser == null ? "Guest" : this.activeUser.firstName;
    }

    public String getActiveUserLastName() {
        return this.activeUser == null ? "Guest" : this.activeUser.lastName;
    }

    public String getActiveUserEmail() {
        return this.activeUser == null ? "Guest" : this.activeUser.email;
    }

    public String getActiveUserUsername() {
        return this.activeUser == null ? "Guest" : this.activeUser.getName();
    }

    public List<String[]> getCurrentWorkouts() {
        return gym.getWorkouts().stream().map(w -> new String[]{
                w.name,
                w.offerings.stream().map(o -> o.room.name).collect(Collectors.joining(", ")),
                w.offerings.stream().map(o -> o.start.toString()).collect(Collectors.joining(", ")),
                w.getUsers().stream().filter(u -> u instanceof Instructor).map(i -> i.firstName + " " + i.lastName).collect(Collectors.joining(", ")),
                w.getNonStaffUserCount() + "/" + w.capacity,
                activeUser instanceof Instructor ? "Teach" : (w.getUsers().contains(activeUser) ? "Drop" : "Enrol")
        }).collect(Collectors.toList());
    }

    public List<String> getCurrentRooms() {
        return gym.getRooms().stream().map(r -> r.name).collect(Collectors.toList());
    }

    public List<String[]> getCurrentUsers() {
        return gym.getUsers().stream().map(
                u -> new String[]{
                        u.firstName == null ? "N/A" : u.firstName + " " + u.lastName,
                        u.getName(),
                        u.getClass().getSimpleName(),
                        u instanceof Instructor ?
                                ((Instructor) u).certs.isEmpty() ? "None" :
                                        String.join(", ", ((Instructor) u).certs) :
                                "N/A"
                }).collect(Collectors.toList());
    }

    public boolean addWorkout(String name, int capacity) {
        for (Workout w : gym.getWorkouts())
            if (w.name.equals(name))
                return false;

        gym.addWorkout(new Workout(name, capacity));
        return true;
    }

    public void dashRefresh(DashboardFrame dash) {
        if (activeUser instanceof GymAdmin)
            dash.adminRefresh();
        else if (activeUser instanceof Instructor)
            dash.instructorRefresh();
        else
            dash.userRefresh();
    }

    public boolean isEmpty() {
        return gym.getUsers().isEmpty();
    }

    public String newInstructor() {
        Instructor i = new Instructor();
        gym.addUser(i);
        return i.getAuthCode();
    }

    public void removeUser(int i) {
        gym.removeUser(gym.getUsers().stream().collect(Collectors.toList()).get(i));
    }

    public boolean addRoom(String name) {
        for (Room r : gym.getRooms())
            if (r.name.equals(name))
                return false;

        gym.addRoom(new Room(name));
        return true;
    }

    public void removeRoom(String name) {
        for (Room r: gym.getRooms())
            if (r.name.equals(name)) {
                gym.removeRooms(r);
                return;
            }
    }
    public void instructorAddCert(int i, String cert) {
        ((Instructor)gym.getUsers().stream().collect(Collectors.toList()).get(i)).certs.add(cert);
    }

    public String[] nextClasses() {
//    todo    activeUser.getWorkouts().stream().flatMap(w -> w.offerings.stream()).map(o -> o.start);
        return new String[]{};
    }

    public String adminReqInstructorAuthCode(int i) {
        return ((Instructor)gym.getUsers().stream().collect(Collectors.toList()).get(i)).getAuthCode();
    }

    public void removeWorkout(int i) {
        gym.removeWorkout(gym.getWorkouts().stream().collect(Collectors.toList()).get(i));
    }

    public String toggleEnrol(int i) {
        Workout w = gym.getWorkouts().stream().collect(Collectors.toList()).get(i);
        if (activeUser instanceof Instructor)
            if (w.getUsers().contains(activeUser))
                return "You already teach this class!";
            else {
                activeUser.addWorkout(w);
                return "You now teach this class!";
            }
        else {
            if (w.getUsers().contains(activeUser)) {
                activeUser.removeWorkout(w);
            }
            else {
                activeUser.addWorkout(w);
            }
            return null;
        }
    }

    public List<String[]> getCurrentOfferings(int i) {
        return gym.getWorkouts().stream().collect(Collectors.toList()).get(i).offerings.stream().map(o -> new String[]{
                o.day.toString() + o.start.toString(),
                o.duration.toString(),
                o.room.toString()
        }).collect(Collectors.toList());
    }
}
