package ebr;

public class User {
    public String name;
    private String password;

    protected User() {};

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
