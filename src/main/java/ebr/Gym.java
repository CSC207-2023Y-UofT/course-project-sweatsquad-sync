package ebr;

import java.util.HashSet;
import java.util.Set;

// TODO serialization??
public class Gym {
    public String name;
    private Set<Room> rooms;
    private Set<User> members;
    private Set<Instructor> instructors;
    private Set<GymAdmin> admins;
    private Set<Workout> workouts;

    public Gym() {
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

    public void scheduleClass() {
        // TODO
    }
}
