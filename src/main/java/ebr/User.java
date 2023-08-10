package ebr;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
    protected String username;
    public String passHash, firstName, lastName, email;
    private transient Set<Workout> workouts;

    protected User() {}

    public User(String username, String passHash, String firstName, String lastName, String email) {
        this.username = username;
        this.passHash = passHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.workouts = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Enrol (RegisteredUser) or Teach (Instructor) cert check is done in Workout.addUser
    public boolean addWorkout(Workout w) {
        if (w.addUser(this))
            return this.workouts.add(w);
        else return false;
    }
    public void removeWorkout(Workout w) {
        if (workouts.remove(w)) {
            w.removeUser(this);
        }
    }

    public Set<Workout> getWorkouts() {
        return Collections.unmodifiableSet(this.workouts);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
        input.defaultReadObject();
        this.workouts = new HashSet<>();
    }
}
