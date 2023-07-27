package ebr;

import java.io.Serializable;

public class User implements Serializable {
    public String name;
    public String passHash;

    protected User() {}

    public User(String name, String passHash) {
        this.name = name;
        this.passHash = passHash;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
