package abr.IODataModels.authenticationFields;

public enum LoginField implements Field {
    USERNAME("username"),
    PASSWORD("password");
    private final String name;
    LoginField(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }
}
