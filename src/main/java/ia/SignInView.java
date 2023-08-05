package ia;

import abr.inputOutputData.authenticationFields.Field;

public interface SignInView extends View<SignInFrameController> {

    public void displayErrorsInstrSignup(RegisterViewModel model);

    public void displayErrorsRegSignup(RegisterViewModel model);

    public void displayFieldError(Field inputField, String message);


    public void showView();
    public void hideView();
    public void clearInputs();

}
