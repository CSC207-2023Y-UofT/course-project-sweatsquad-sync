package ebr;

public class GymAdmin extends User {
    public GymAdmin(String username, String passHash, String firstName, String lastName, String email) {
        super(username, passHash, firstName, lastName, email);
    }

    public void addWorkout(Workout w) {
        throw new UnsupportedOperationException();
    }
}
