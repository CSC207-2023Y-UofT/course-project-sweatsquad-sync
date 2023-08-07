package ebr;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Instructor extends User implements Serializable {
    // N.B. Set code below to null when instructor account is claimed
    private String tempAuth;
    public Set<String> certs;
    private static final String ALPHA_NUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";

    public Instructor() {
        super(null, null, null, null, null);

        StringBuilder codeBuilder = new StringBuilder();
        Random rnd = new Random();
        while (codeBuilder.length() < 16)
            codeBuilder.append(ALPHA_NUM.charAt((int)(rnd.nextFloat() * ALPHA_NUM.length())));

        tempAuth = codeBuilder.toString();

        certs = new HashSet<>();
    }
    public String getAuthCode() {
        return tempAuth;
    }
    public void claim(String name, String passHash, String firstName, String lastName, String email) {
        if (tempAuth != null) {
            tempAuth = null;
            this.name = name;
            this.passHash = passHash;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }
    }

    @Override
    public String getName() {
        return this.name == null ? "UNCLAIMED INSTRUCTOR" : this.name;
    }

    @Override
    public void setName(String name) {
        if (this.name != null)
            this.name = name;
    }

    @Override
    public int hashCode() {
        return tempAuth != null ? tempAuth.hashCode() : name.hashCode();
    }
}
