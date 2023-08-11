package ebr;

import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
        for (Workout w : workouts)
            u.removeWorkout(w);
    }

    public Set<User> getUsers() {
        return Collections.unmodifiableSet(this.members);
    }

    public void addWorkout(Workout u) {
        workouts.add(u);
    }

    public void removeWorkout(Workout w) {
        workouts.remove(w);
        for (User u : members)
            u.removeWorkout(w);
    }

    public Set<Workout> getWorkouts() {
        return Collections.unmodifiableSet(this.workouts);
    }

    public void addRoom(Room r) {
        rooms.add(r);
    }

    public void removeRooms(Room r) {
        rooms.remove(r);
        for (Workout w : workouts)
            w.offerings.removeIf(o -> o.room.equals(r));
    }

    public Set<Room> getRooms() {
        return Collections.unmodifiableSet(this.rooms);
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
                        uu.workouts.add(w);
                        break eachUser;
                    }
    }
}
