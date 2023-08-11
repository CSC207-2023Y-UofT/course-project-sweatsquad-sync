package ia;


public interface EntryFrameView extends View<EntryFramePresenter> {

    void displayRegistrationErrors(RegisterErrorViewModel model);

    void showLogin();

    void showSignUp();

    void showActivationPortal();

    void showInstructorSignUp();

    void showAdminSignUp();

    void showView();

    void hideView();

    void clearInputs();

}
