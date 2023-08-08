package fd;

import abr.DefaultActivationCodeGenerationStrategy;
import abr.GymManager;
import ia.*;

import java.io.IOException;

public class App {
    public static void main(String[] args) {

        GymDatabase db = new FileDatabase();
        GymManager gymManager = new GymManager(db);

        //Setup entry frame
        EntryFrameView entryFrameView = new EntryPointFrame();
        EntryFramePresenter entryFramePresenter = new EntryFramePresenter(gymManager.getAuthenticationRequestHandler(), entryFrameView);
        entryFrameView.setPresenter(entryFramePresenter);
        gymManager.addLoginListener(entryFramePresenter.getLoginHandler());

        //Setup dashboard
        CourseEnrollmentFrame courseEnrollmentFrame = new CourseEnrollmentFrame();
        CourseEnrolmentPresenter courseEnrolmentPresenter = new CourseEnrolmentPresenter(gymManager);
        courseEnrollmentFrame.setPresenter(courseEnrolmentPresenter);


        ManageRoomFrame manageRoomFrame = new ManageRoomFrame();
        ManageRoomPresenter manageRoomPresenter = new ManageRoomPresenter(gymManager);
        manageRoomFrame.setPresenter(manageRoomPresenter);

        ManagerUserPresenter managerUserPresenter = new ManagerUserPresenter(gymManager);
        managerUserPresenter.setStrategy(new DefaultActivationCodeGenerationStrategy());
        ManageUserFrame manageUserFrame = new ManageUserFrame();
        manageUserFrame.setPresenter(managerUserPresenter);

        SchedulePresenter schedulePresenter = new SchedulePresenter();
        ScheduleFrame scheduleFrame = new ScheduleFrame();
        scheduleFrame.setPresenter(schedulePresenter);

        DashboardFrame dashboardFrame = new DashboardFrame(scheduleFrame, courseEnrollmentFrame, new UserInfoFrame(), manageUserFrame, manageRoomFrame);
        DashboardPresenter dashboardPresenter = new DashboardPresenter(dashboardFrame, gymManager.getLogoutRequestHandler());
        dashboardFrame.setPresenter(dashboardPresenter);


        //Tying ABR and presenters together
        gymManager.addLoginListener(dashboardPresenter);
        gymManager.setAuthenticationListener(entryFramePresenter.getAuthenticationHandler());
        gymManager.setLogoutListener(entryFramePresenter.getLogoutHandler());
        entryFrameView.showView();
        if (gymManager.getGym().getUsers().isEmpty())
            entryFrameView.showAdminSignUp();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                db.save(gymManager.getGym());
            }
            catch (IOException e) {
                e.printStackTrace();
                System.err.println("Failed to write gym!");
            }
        }));

    }
}
