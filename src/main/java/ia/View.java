package ia;

public interface View<C extends Controller> {

    public void displayInfoMessage(String message);
    public void displayErrorMessage(String message);
    public void setController(C c);
}
