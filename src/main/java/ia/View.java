package ia;

public interface View<P extends Presenter> {

    public void displayInfoMessage(String message);
    public void displayErrorMessage(String message);
    public void setPresenter(P presenter);
}
