package ebr;

import java.io.Serializable;

public class User implements Serializable {
    public String name, passHash, firstName, lastName, email;

    protected User() {}

    public User(String name, String passHash, String firstName, String lastName, String email) {
        this.name = name;
        this.passHash = passHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
