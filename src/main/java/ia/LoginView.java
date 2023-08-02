package ia;

import abr.Field;

public interface LoginView extends View<LoginController> {

    public void provideInstrSignup();

    public void provideSignup();

    public void provideLogin();
    
    public void provideInstrAuthentication();

    public void displayFieldError(Field inputField, String message);

}
