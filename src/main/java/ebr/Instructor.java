package ebr;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Instructor extends RegisteredUser implements Serializable {
    protected Instructor() {};
    // N.B. Set code below to null when instructor account is claimed
    public String tempAuth;
    public Set<String> certs;
    public Instructor(String name, String password) {
        super(name, password);
        tempAuth = "1234567890";
        certs = new HashSet<>();
    }
}
