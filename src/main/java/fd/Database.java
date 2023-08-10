package fd;

// import statements
import ebr.*;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.stream.Collectors;

public class Database {
    private final String filename = "gym.bin";
    private Gym gym;
    private User activeUser;

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
                    activeUser = u;
                    return true;
                } else {
                    System.out.println("Incorrect password");
                    return false;
                }
        System.out.println("User does not exist");
        return false;
    }

    // method to check if username is taken
    public boolean takenUsername(String username) {
        for (User u : gym.getUsers())
            if (u.getName().equals(username))
                    return true;
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

    public void updateActiveUserFirstName(String newFirstName) {
        activeUser.firstName = newFirstName;
    }

    public void updateActiveUserLastName(String newLastName) {
        activeUser.firstName = newLastName;
    }

    public void updateActiveUserEmail(String newEmail) {
        activeUser.firstName = newEmail;
    }

    public boolean updateActiveUserUsername(String newUsername) {
        for (User u : gym.getUsers())
            if (u.getName().equals(newUsername))
                return false;

        activeUser.setName(newUsername);
        return true;
    }

    public void updateActiveUserPasscode(String user, String newPasscode) {
        for (User u : gym.getUsers())
            if (u.getName().equals(user))
                u.passHash = hashPassword(newPasscode);
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

    public String adminReqInstructorAuthCode(int i) {
        return ((Instructor)gym.getUsers().stream().collect(Collectors.toList()).get(i)).getAuthCode();
    }

    public void removeWorkout(int i) {
        gym.removeWorkout(gym.getWorkouts().stream().collect(Collectors.toList()).get(i));
    }

    public void removeOffering(int i, int j) {
        gym.getWorkouts().stream().collect(Collectors.toList()).get(i).offerings.remove(j);
    }

    public String toggleEnrol(int i) {
        Workout w = gym.getWorkouts().stream().collect(Collectors.toList()).get(i);
        if (activeUser instanceof Instructor)
            if (w.getUsers().contains(activeUser))
                return "You already teach this class!";
            else {
                if (activeUser.addWorkout(w))
                    return "You now teach this class!";
                else
                    return "You do not have the required certs to teach this class!";
            }
        else {
            if (w.getUsers().contains(activeUser))
                activeUser.removeWorkout(w);
            else
                if (!activeUser.addWorkout(w))
                    return "This class is full!";
            return null;
        }
    }

    public List<String[]> getNextThreeOfferings() {
        class R {
            LocalDateTime t;
            String name, room;
            R(LocalDateTime t, String n, String room) { this.t = t; this.name = n; this.room = room; }
        }
        List<R> rs = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            LocalDate wk = LocalDate.now().with(DayOfWeek.SUNDAY).plusWeeks(i);
            for (Workout w : activeUser.getWorkouts())
                for (Workout.Offering o : w.offerings)
                    rs.add(new R(wk.with(o.day).atTime(o.start), w.name, o.room.name));
        }
        rs.sort(new Comparator<R>() {
            @Override
            public int compare(R o1, R o2) {
                return o1.t.compareTo(o2.t);
            }
        });
        return rs.stream().filter(r -> r.t.isAfter(LocalDateTime.now())).map(r -> new String[]{
                r.name,
                r.room,
                r.t.format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + r.t.format(DateTimeFormatter.ISO_LOCAL_TIME)
        }).limit(3).collect(Collectors.toList());
    }

    public List<String[]> getCurrentOfferings(int idx) {
        return gym.getWorkouts().stream().collect(Collectors.toList()).get(idx).offerings.stream().map(o -> new String[]{
                o.day.toString() + " " + o.start.format(DateTimeFormatter.ofPattern("HH:mm")),
                Long.toString(o.duration.toHours()),
                o.room.name
        }).collect(Collectors.toList());
    }

    public String requireCert(int i, String cert) {
        Workout w = gym.getWorkouts().stream().collect(Collectors.toList()).get(i);
        for (User u : w.getUsers())
            if (u instanceof Instructor)
                if (!((Instructor) u).certs.contains(cert))
                    return "Instructor " + u.firstName + " " + u.lastName + " does not have cert " + cert + "!";

        if (w.getRequiredCerts().contains(cert))
            return "This cert is already required for this workout!";

        w.requireCert(cert);
        return null;
    }

    public boolean editOffering(int course, int offering, DayOfWeek day, String start, int duration, String room) {
        LocalTime s = LocalTime.parse(start, DateTimeFormatter.ofPattern("HH:mm"));
        Duration d = Duration.ofHours(duration);

        Workout w = gym.getWorkouts().stream().collect(Collectors.toList()).get(course);
        for (Workout.Offering o : w.offerings) {
            if (o.day == day && ((!o.start.isAfter(s) && s.isBefore(o.start.plus(o.duration)))
                    || (o.start.isAfter(s) && o.start.isBefore(s.plus(d)))))
                return false;
        }

        Room r = null;
        for (Room rr : gym.getRooms())
            if (rr.name.equals(room)) {
                r = rr;
                break;
            }

        if (offering == -1)
            w.offerings.add(new Workout.Offering(day, s, d, r));
        else {
            Workout.Offering o = w.offerings.get(offering);
            o.day = day;
            o.start = s;
            o.duration = d;
            o.room = r;
        }
        return true;
    }

    public boolean canEditCourse(int course) {
        return course != -1 && (!(activeUser instanceof Instructor) || gym.getWorkouts().stream().collect(Collectors.toList()).get(course).getUsers().contains(activeUser));
    }

    public String[] getCurrentWorkoutCerts(int index) {
        return gym.getWorkouts().stream().collect(Collectors.toList()).get(index).getRequiredCerts().toArray(new String[]{});
    }

    public void removeCurrentCert(int i, String cert) {
        gym.getWorkouts().stream().collect(Collectors.toList()).get(i).deleteCert(cert);
    }

    public void removeUserFromWorkout(int i, String name) {
        for (User u : gym.getUsers())
            if (u.getName().equals(name))
                u.removeWorkout(gym.getWorkouts().stream().collect(Collectors.toList()).get(i));
    }

    public List<String[]> getCurrentWorkoutUsers(int i) {
        return gym.getWorkouts().stream().collect(Collectors.toList()).get(i).getUsers().stream().map(
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


    public String getScheduleBlockAt(int hr, int day) {
        DayOfWeek[] day_jump = new DayOfWeek[]{ DayOfWeek.SUNDAY,  DayOfWeek.MONDAY,  DayOfWeek.TUESDAY,  DayOfWeek.WEDNESDAY,  DayOfWeek.THURSDAY,  DayOfWeek.FRIDAY,  DayOfWeek.SATURDAY};
        class R {
            LocalTime t;
            DayOfWeek day;
            Duration d;
            String name, room;
            R(LocalTime t, DayOfWeek day, Duration d, String n, String room) { this.t = t; this.day = day; this.d = d; this.name = n; this.room = room; }
        }
        return "<HTML>" + activeUser.getWorkouts().stream().flatMap(w -> w.offerings.stream()
                .map(o -> new R(o.start, o.day, o.duration, w.name, o.room.name)))
                .filter(r -> r.day == day_jump[day] && r.t.getHour() <= hr && hr < r.t.plus(r.d).getHour())
                .map(r -> "<B>" + r.name + "</B>" + "<BR>" + r.room).collect(Collectors.joining("<BR>")) + "</HTML>";
    }

    public boolean changeWorkoutName(int workout, String name) {
        for (Workout w : gym.getWorkouts())
            if (w.name.equals(name))
                return false;

        gym.getWorkouts().stream().collect(Collectors.toList()).get(workout).name = name;
        return true;
    }
}
