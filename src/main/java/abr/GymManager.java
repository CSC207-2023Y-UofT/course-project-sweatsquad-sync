package abr;


import abr.IODataModels.*;
import abr.IODataModels.authenticationFields.*;
import ebr.Gym;
import ebr.GymAdmin;
import ebr.Instructor;
import ebr.User;
import fd.GymDatabase;
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
    private List<OutputBoundary<LoginEvent>> loginEventListeners;

    private OutputBoundary<LogoutEvent> logoutListener;


    public GymManager(GymDatabase database, PasswordHashStrategy passwordHashStrategy) {
        Gym gym1;
        this.database = database;
        try {
            gym1 = database.load();
        } catch (Exception e) {
            gym1 = new Gym("Sweatsquad Gym");
        }

        this.gym = gym1;
        this.userAccountManager = new UserManager(gym, passwordHashStrategy);
        this.activeUserManager = new ActiveUserManager(userAccountManager, passwordHashStrategy);
        this.authenticator = new Authenticator(userAccountManager);
        this.loginEventListeners = new ArrayList<>();
    }

    public void setAuthenticationListener(OutputBoundary<AuthenticationResponseModel<? extends Field>> al) {
        this.authenticationListener = al;
    }

    public void addLoginListener(OutputBoundary<LoginEvent> le) {

        this.loginEventListeners.add(le);
    }

    public void setLogoutListener(OutputBoundary<LogoutEvent> le) {
        this.logoutListener = le;
    }

    public boolean verifyLoginDetails(LoginDetails ld) {

        LoginResponse response = authenticator.authenticateLogin(ld);
        if (response.isSuccessful()) {
            return true;
        } else {
            return false;
        }
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
                        for (OutputBoundary<LoginEvent> loginListener: loginEventListeners) {
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

    public User getActiveUser() {

        return activeUserManager.getActiveUser();
    }

    public Gym getGym() {
        return gym;
    }

}
