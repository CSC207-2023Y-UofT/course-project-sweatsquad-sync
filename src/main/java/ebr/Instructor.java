package ebr;

import java.io.Serializable;

public class Instructor extends RegisteredUser implements Serializable {
    protected Instructor() {};
    public Instructor(String name, String password) {
        super(name, password);

    }
}
