package abr.IODataModels.authenticationFields;

public enum RegisterField implements Field {

    USERNAME("username"),
    PASSWORD("password"),

    CONFIRM_PASSWORD("confirm password"),

    FIRST_NAME("first name"),

    LAST_NAME("last name"),
    EMAIL("email");

    private String name;

    RegisterField(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
