package fd;

import abr.DefaultActivationCodeGenerationStrategy;
import abr.GymManager;
import abr.PasswordHashSHA256;
import ia.*;

import java.io.IOException;

public class App {

    public static void main(String[] args) {

        GymDatabase db = new FileDatabase();
        GymManager gymManager = new GymManager(db, new PasswordHashSHA256());

        //Setup entry frame
        EntryFrameView entryFrameView = new EntryPointFrame();
        EntryFramePresenter entryFramePresenter = new EntryFramePresenter(gymManager.getAuthenticationRequestHandler(), entryFrameView);
        entryFrameView.setPresenter(entryFramePresenter);
        gymManager.addLoginListener(entryFramePresenter.getLoginHandler());

        //Setup dashboard

        WorkoutCertsFrame workoutCertsFrame = new WorkoutCertsFrame();
        WorkoutCertsPresenter workoutCertsPresenter = new WorkoutCertsPresenter(gymManager);
        workoutCertsFrame.setPresenter(workoutCertsPresenter);
        WorkoutOfferingFrame workoutOfferingFrame = new WorkoutOfferingFrame();
        WorkoutOfferingPresenter workoutOfferingPresenter = new WorkoutOfferingPresenter(gymManager);
        workoutOfferingFrame.setPresenter(workoutOfferingPresenter);
        WorkoutUsersFrame workoutUsersFrame = new WorkoutUsersFrame();
        WorkoutUsersPresenter workoutUsersPresenter = new WorkoutUsersPresenter(gymManager);
        workoutUsersFrame.setPresenter(workoutUsersPresenter);
        CourseEnrollmentFrame courseEnrollmentFrame = new CourseEnrollmentFrame(workoutOfferingFrame, workoutCertsFrame, workoutUsersFrame);
        CourseEnrolmentPresenter courseEnrolmentPresenter = new CourseEnrolmentPresenter(gymManager);
        courseEnrollmentFrame.setPresenter(courseEnrolmentPresenter);




        ManageRoomFrame manageRoomFrame = new ManageRoomFrame();
        ManageRoomPresenter manageRoomPresenter = new ManageRoomPresenter(gymManager);
        manageRoomFrame.setPresenter(manageRoomPresenter);

        ManagerUserPresenter managerUserPresenter = new ManagerUserPresenter(gymManager);
        managerUserPresenter.setStrategy(new DefaultActivationCodeGenerationStrategy());
        ManageUserFrame manageUserFrame = new ManageUserFrame();
        manageUserFrame.setPresenter(managerUserPresenter);

        SchedulePresenter schedulePresenter = new SchedulePresenter(gymManager);
        ScheduleFrame scheduleFrame = new ScheduleFrame();
        scheduleFrame.setPresenter(schedulePresenter);

        UserInfoPresenter userInfoPresenter = new UserInfoPresenter(gymManager, new PasswordHashSHA256());

        UserInfoFrame userInfoFrame = new UserInfoFrame(userInfoPresenter);
        userInfoPresenter.setView(userInfoFrame);

        DashboardFrame dashboardFrame = new DashboardFrame(scheduleFrame, courseEnrollmentFrame, userInfoFrame, manageUserFrame, manageRoomFrame);
        DashboardPresenter dashboardPresenter = new DashboardPresenter(dashboardFrame, gymManager.getLogoutRequestHandler(), gymManager);
        dashboardFrame.setPresenter(dashboardPresenter);

        courseEnrollmentFrame.addRefreshRequestListener(dashboardPresenter);
        workoutOfferingFrame.addRefreshRequestListener(dashboardPresenter);
        workoutUsersFrame.addRefreshRequestListener(dashboardPresenter);


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
