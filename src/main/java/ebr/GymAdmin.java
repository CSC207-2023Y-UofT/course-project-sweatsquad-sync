package ebr;

import java.io.Serializable;

public class GymAdmin extends RegisteredUser implements Serializable {
    protected GymAdmin() {};
    public GymAdmin(String name, String password) {
        super(name, password);

    }
}
