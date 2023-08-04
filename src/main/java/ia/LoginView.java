package ia;

import abr.requestAndResponse.authenticationFields.Field;

public interface LoginView extends View<LoginFrameController> {

    public void provideInstrSignup();

    public void provideSignup();

    public void provideLogin();
    
    public void provideInstrAuthentication();

    public void displayFieldError(Field inputField, String message);

    public void showView();
    public void hideView();
    public void clearInputs();

}
