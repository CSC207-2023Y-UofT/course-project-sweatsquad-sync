package ebr;

import java.util.Objects;

public class User {
    private static int count = 0;
    public String name;
    private String password;
    private int id;

    protected User() {};

    public User(String name, String password) {
        this.id = count++;
        this.name = name;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
