package ebr;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

// TODO serialization??
public class Gym implements Serializable  {
    public String name;
    private Set<Room> rooms;
    private Set<User> members;
    private Set<Instructor> instructors;
    private Set<GymAdmin> admins;
    private Set<Workout> workouts;

    public Gym(String name) {
        this.name = name;
        this.rooms = new HashSet<>();
        this.members = new HashSet<>();
        this.instructors = new HashSet<>();
        this.admins = new HashSet<>();
        this.workouts = new HashSet<>();
    }

    public void addUser(User u) {
        members.add(u);
    }

    public void removeUser(User u) {
        members.remove(u);
        // TODO remove all refs to that user here
    }

    public void addInstructor(Instructor u) {
        instructors.add(u);
    }

    public void removeInstructor(Instructor u) {
        instructors.remove(u);
        // TODO remove all refs to that instructor here
    }

    public void addAdmin(GymAdmin u) {
        admins.add(u);
    }

    public void removeAdmin(GymAdmin u) {
        admins.remove(u);
        // TODO remove all refs to that admin here
    }

    public void addWorkout(Workout u) {
        workouts.add(u);
    }

    public void removeWorkout(Workout u) {
        workouts.remove(u);
        // TODO remove all refs to that workout here
    }

    public void addRoom(Room r) {
        rooms.add(r);
    }

    public void removeRooms(Room r) {
        rooms.remove(r);
        // TODO remove all refs to that room here
    }

    public String getRooms() {
        return this.rooms.toString();
    }

    public void save() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("file.txt"));
        oos.writeObject(this);
        oos.close();
    }

    public Gym load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("file.txt"));
        Gym g = (Gym)ois.readObject();
        ois.close();
        return g;
    }
}
