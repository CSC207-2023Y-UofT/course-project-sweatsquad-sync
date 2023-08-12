package ia;

public interface View<P extends Presenter> {

    void displayInfoMessage(String message);
    void displayErrorMessage(String message);
    void setPresenter(P presenter);
}
