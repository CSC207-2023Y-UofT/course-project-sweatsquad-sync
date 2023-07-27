package ebr;

import java.io.Serializable;

public class Instructor extends RegisteredUser implements Serializable {
    protected Instructor() {};
    // N.B. Set code below to null when instructor account is claimed
    public String tempAuth;
    public Instructor(String name, String password) {
        super(name, password);
        tempAuth = "1234567890";
    }
}
