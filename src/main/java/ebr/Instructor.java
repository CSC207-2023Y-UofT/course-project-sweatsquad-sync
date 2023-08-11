package ebr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Instructor extends User implements Serializable {
    // N.B. Set code below to null when instructor account is claimed
    private String tempAuth;
    public List<String> certs;

    public Instructor(String tempAuth) {
        super(null, null, null, null, null);

        this.tempAuth = tempAuth;

        certs = new ArrayList<>();
    }
    public String getAuthCode() {
        return tempAuth;
    }
    public void claim(String username, String passHash, String firstName, String lastName, String email) {
        if (tempAuth != null) {
            tempAuth = null;
            this.username = username;
            this.passHash = passHash;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }
    }

    @Override
    public String getUsername() {
        return this.username == null ? "UNCLAIMED INSTRUCTOR" : this.username;
    }

    @Override
    public void setUsername(String name) {
        if (this.username != null)
            this.username = name;
    }

    @Override
    public int hashCode() {
        return tempAuth != null ? tempAuth.hashCode() : username.hashCode();
    }
}
