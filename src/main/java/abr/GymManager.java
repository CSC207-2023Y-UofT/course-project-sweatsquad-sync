package abr;


import abr.IODataModels.*;
import abr.IODataModels.authenticationFields.Field;
import ebr.Gym;
import ebr.GymAdmin;
import ebr.Instructor;
import ebr.User;
import fd.GymDatabase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Facade class
public class GymManager {

    private final Authenticator authenticator;
    private final ActiveUserManager activeUserManager;
    private final UserManager userAccountManager;
    private final GymDatabase database;

    private final Gym gym;

    private OutputBoundary<AuthenticationResponseModel<? extends Field>> authenticationListener;
    private List<OutputBoundary<LoginEvent>> loginListeners;
    private OutputBoundary<LogoutEvent> logoutListener;

    public GymManager(GymDatabase database) {
        Gym gym1;
        this.database = database;
        try {
            gym1 = database.load();
        } catch (Exception e) {
            gym1 = new Gym("Sweatsquad Gym");
        }

        this.gym = gym1;
        this.userAccountManager = new UserManager(gym, new PasswordHashSHA256());
        this.activeUserManager = new ActiveUserManager(userAccountManager);
        this.authenticator = new Authenticator(userAccountManager);
        this.loginListeners = new ArrayList<>();
    }

    public void setAuthenticationListener(OutputBoundary<AuthenticationResponseModel<? extends Field>> al) {
        this.authenticationListener = al;
    }

    public void addLoginListener(OutputBoundary<LoginEvent> le) {

        this.loginListeners.add(le);
    }

    public void setLogoutListener(OutputBoundary<LogoutEvent> le) {
        this.logoutListener = le;
    }

    public InputBoundary<AuthenticationRequestModel> getAuthenticationRequestHandler() {

        return new InputBoundary<>() {
            @Override
            public void receiveRequest(AuthenticationRequestModel rm) {

                if (rm instanceof RegisterDetails rd) {
                    RegisterResponse response = authenticator.authenticateRegistration(rd);
                    if (response.isSuccessful()) {
                        userAccountManager.register(rd);
                    }
                    authenticationListener.receiveResponse(response);


                } else if (rm instanceof LoginDetails ld) {

                    LoginResponse response = authenticator.authenticateLogin(ld);
                    if (response.isSuccessful()) {

                        activeUserManager.loginUser(ld);
                        User activeUser = activeUserManager.getActiveUser();
                        for (OutputBoundary<LoginEvent> loginListener: loginListeners) {
                            if (activeUser instanceof Instructor) {

                                loginListener.receiveResponse(createLoginEvent(AccountType.REGULAR, activeUser));
                            } else if (activeUser instanceof GymAdmin) {

                                loginListener.receiveResponse(createLoginEvent(AccountType.ADMIN, activeUser));
                            } else {

                                loginListener.receiveResponse(createLoginEvent(AccountType.INSTRUCTOR, activeUser));
                            }
                        }

                    }

                } else if (rm instanceof ActivationCodeDetails acd) {

                    authenticationListener.receiveResponse(authenticator.authenticateActivationCode(acd));
                }
            }

            private LoginEvent createLoginEvent(AccountType at, User user) {
                return new LoginEvent(at, user.firstName, user.lastName, user.getUsername(), user.email);

            }
        };
    }

    public InputBoundary<LogoutRequest> getLogoutRequestHandler() {
        return rm -> {
            activeUserManager.logoutUser();
            logoutListener.receiveResponse(new LogoutEvent());
        };
    }

    public Gym getGym() {
        return gym;
    }

}
