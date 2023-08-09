package ebr;

public class GymAdmin extends User {
    public GymAdmin(String name, String passHash, String firstName, String lastName, String email) {
        super(name, passHash, firstName, lastName, email);
    }
        @Override
    public boolean addWorkout(Workout w) {
        throw new UnsupportedOperationException();
    }
}
