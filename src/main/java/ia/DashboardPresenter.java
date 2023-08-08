package ia;

import abr.IODataModels.LogoutRequest;
import abr.InputBoundary;
import abr.OutputBoundary;
import abr.IODataModels.LoginEvent;
import fd.DashboardFrame;

interface DashboardDispatch {
    void apply();
}

public class DashboardPresenter implements Presenter, OutputBoundary<LoginEvent>  {

    private DashboardFrame dashboardFrame;

    private DashboardDispatch dashboardDispatch;

    private InputBoundary<LogoutRequest> logoutRequestInputBoundary;

    public DashboardPresenter(DashboardFrame dashboardFrame, InputBoundary<LogoutRequest> logoutRequestHandler) {
        this.dashboardFrame = dashboardFrame;
        this.logoutRequestInputBoundary = logoutRequestHandler;
    }


    @Override
    public void receiveResponse(LoginEvent rm) {

        dashboardFrame.setGreetingMessage(rm.firstName());
        switch (rm.accountType()) {

            case INSTRUCTOR -> {

                dashboardDispatch = dashboardFrame::instructorRefresh;
            }
            case REGULAR -> {
                dashboardDispatch = dashboardFrame::userRefresh;
            }
            case ADMIN -> {
                dashboardDispatch = dashboardFrame::adminRefresh;
            }

        }
        dashboardFrame.showDashboard();

        dashBoardRequested();
    }

    public void dashBoardRequested() {
        dashboardDispatch.apply();
    }

    public String[] nextClasses() {
//    todo    activeUser.getWorkouts().stream().flatMap(w -> w.offerings.stream()).map(o -> o.start);
        return new String[]{};
    }

    public void logoutRequested() {
        logoutRequestInputBoundary.receiveRequest(new LogoutRequest());
    }
}
