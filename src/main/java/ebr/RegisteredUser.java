package ebr;

import java.util.HashSet;
import java.util.Set;

public class RegisteredUser extends User {
    private Set<Workout> workouts;
    protected RegisteredUser() {}
    public RegisteredUser(String name, String password, String firstName, String lastName, String email) {
        super(name, password, firstName, lastName, email);
        this.workouts = new HashSet<>();
    }
    // Enrol (RegisteredUser) or Teach (Instructor) cert check is done in Workout.addUser
    public void addWorkout(Workout w) {
        if (w.addUser(this))
            this.workouts.add(w);
    }
    public void removeWorkout(Workout w) {
        if (workouts.remove(w)) {
            w.removeUser(this);
        }
    }
}
