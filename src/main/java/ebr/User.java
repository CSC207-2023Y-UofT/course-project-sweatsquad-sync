package ebr;

import java.io.Serializable;

public class User implements Serializable {
    protected String name;
    public String passHash, firstName, lastName, email;

    protected User() {}

    public User(String name, String passHash, String firstName, String lastName, String email) {
        this.name = name;
        this.passHash = passHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
