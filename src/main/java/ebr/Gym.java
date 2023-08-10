package ebr;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Gym implements Serializable  {
    public String name;
    private final List<Room> rooms;
    private final List<User> members;
    private final List<Workout> workouts;

    public Gym(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.members = new ArrayList<>();
        this.workouts = new ArrayList<>();
    }

    public void addUser(User u) {
        members.add(u);
    }

    public void removeUser(User u) {
        members.remove(u);
        for (Workout w : workouts)
            u.removeWorkout(w);
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(this.members);
    }

    public void addWorkout(Workout u) {
        workouts.add(u);
    }

    public void removeWorkout(Workout w) {
        workouts.remove(w);
        for (User u : members)
            u.removeWorkout(w);
    }

    public List<Workout> getWorkouts() {
        return Collections.unmodifiableList(this.workouts);
    }

    public void addRoom(Room r) {
        rooms.add(r);
    }

    public void removeRooms(Room r) {
        rooms.remove(r);
        for (Workout w : workouts)
            w.offerings.removeIf(o -> o.room.equals(r));
    }

    public List<Room> getRooms() {
        return Collections.unmodifiableList(this.rooms);
    }

    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
        input.defaultReadObject();
        for (Workout w : workouts)
            eachUser:
            for (User u: w.getUsers())
                for (User uu : members)
                    if (uu.equals(u)) {
                        w.removeUser(u);
                        w.addUser(uu);
                        uu.addWorkout(w);
                        break eachUser;
                    }
    }
}
