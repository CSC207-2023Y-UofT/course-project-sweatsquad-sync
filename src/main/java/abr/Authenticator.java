package abr;

public abstract class Authenticator<T> extends UseCase {
    private String status;

    public abstract boolean authenticate(T details);

    public String getStatus() {
        return status;
    }

    void setStatus(String s) {
        status = s;
    }

    public void resetStatus() {
        status = "";
    }
}