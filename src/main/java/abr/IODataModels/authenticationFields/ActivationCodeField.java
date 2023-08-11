package abr.IODataModels.authenticationFields;

public enum ActivationCodeField implements Field {
    ACTIVATION_CODE("activation code");

    private String name;

    ActivationCodeField(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
