package ebr;

public class RegisteredUser extends User {
    protected RegisteredUser() {};
    public RegisteredUser(String name, String password, String firstName, String lastName, String email) {
        super(name, password, firstName, lastName, email);

    }
}
