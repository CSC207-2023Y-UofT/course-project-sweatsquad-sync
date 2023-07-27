package ebr;

import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

// TODO serialization??
public class Gym implements Serializable  {
    public String name;
    private Set<Room> rooms;
    private Set<User> members;
    private Set<Workout> workouts;

    public Gym(String name) {
        this.name = name;
        this.rooms = new HashSet<>();
        this.members = new HashSet<>();
        this.workouts = new HashSet<>();
    }

    public void addUser(User u) {
        members.add(u);
    }

    public void removeUser(User u) {
        members.remove(u);
        // TODO remove all refs to that user here
        if (u instanceof RegisteredUser) {}
        if (u instanceof Instructor) {}
    }

    public Set<User> getUsers() {
        return Collections.unmodifiableSet(this.members);
    }

    public void addWorkout(Workout u) {
        workouts.add(u);
    }

    public void removeWorkout(Workout u) {
        workouts.remove(u);
        // TODO remove all refs to that workout here
    }

    public Set<Workout> getWorkouts() {
        return Collections.unmodifiableSet(this.workouts);
    }

    public void addRoom(Room r) {
        rooms.add(r);
    }

    public void removeRooms(Room r) {
        rooms.remove(r);
        // TODO remove all refs to that room here
    }

    public Set<Room> getRooms() {
        return Collections.unmodifiableSet(this.rooms);
    }
}
