package ebr;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Instructor extends RegisteredUser implements Serializable {
    // N.B. Set code below to null when instructor account is claimed
    private String tempAuth;
    public Set<String> certs;
    public Instructor() {
        super(null, null, null, null, null);
        tempAuth = "1234567890";
        certs = new HashSet<>();
    }
    public String getAuthCode() {
        return tempAuth;
    }
    public void claim(String auth, String name, String passHash, String firstName, String lastName, String email) {
        if (tempAuth != null && tempAuth == auth) {
            tempAuth = null;
            this.name = name;
            this.passHash = passHash;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }
    }
}
