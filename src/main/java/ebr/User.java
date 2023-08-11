package ebr;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User implements Serializable {
    protected String username;
    public String passHash, firstName, lastName, email;
    private transient List<Workout> workouts;

    public User(String username, String passHash, String firstName, String lastName, String email) {
        this.username = username;
        this.passHash = passHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.workouts = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    // Enrol (RegisteredUser) or Teach (Instructor) cert check is done in Workout.addUser
    public boolean addWorkout(Workout w) {
        if (w.addUser(this) && !this.workouts.contains(w))
            return this.workouts.add(w);
        else return false;
    }
    public void removeWorkout(Workout w) {
        if (workouts.remove(w)) {
            w.removeUser(this);
        }
    }

    public List<Workout> getWorkouts() {
        return Collections.unmodifiableList(this.workouts);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Serial
    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
        input.defaultReadObject();
        this.workouts = new ArrayList<>();
    }
}
