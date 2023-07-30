package ia;

import abr.AuthCode;
import abr.LoginDetails;
import abr.RegisterDetails;

public interface LoginViewListener {

    public void loginAttempted(LoginDetails d);
    public void instructorRegistrationAttempted(RegisterDetails r);
    public void registrationAttempted(RegisterDetails r);
    public void codeActivationAttempted(AuthCode c);
}

