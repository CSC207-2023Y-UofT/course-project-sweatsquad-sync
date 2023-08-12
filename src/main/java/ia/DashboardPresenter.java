package ia;

import abr.GymManager;
import abr.IODataModels.LogoutRequest;
import abr.InputBoundary;
import abr.OutputBoundary;
import abr.IODataModels.LoginEvent;
import ebr.Workout;
import fd.DashboardFrame;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

interface DashboardDispatch {
    void apply();
}

public class DashboardPresenter implements Presenter, OutputBoundary<LoginEvent>, RefreshRequestListener  {

    private final DashboardFrame dashboardFrame;

    private DashboardDispatch dashboardDispatch;

    private final InputBoundary<LogoutRequest> logoutRequestInputBoundary;

    private final GymManager gymManager;


    public DashboardPresenter(DashboardFrame dashboardFrame, InputBoundary<LogoutRequest> logoutRequestHandler, GymManager gymManager) {
        this.dashboardFrame = dashboardFrame;
        this.logoutRequestInputBoundary = logoutRequestHandler;
        this.gymManager = gymManager;
    }


    @Override
    public void receiveResponse(LoginEvent rm) {

        dashboardFrame.setGreetingMessage("Welcome back, " + rm.firstName() + "!");
        switch (rm.accountType()) {

            case INSTRUCTOR -> dashboardDispatch = dashboardFrame::instructorRefresh;
            case REGULAR -> dashboardDispatch = dashboardFrame::userRefresh;
            case ADMIN -> dashboardDispatch = dashboardFrame::adminRefresh;

        }
        dashboardFrame.showDashboard();

        dashBoardRequested();
    }

    public void dashBoardRequested() {
        dashboardDispatch.apply();
    }

    public void logoutRequested() {
        logoutRequestInputBoundary.receiveRequest(new LogoutRequest());
    }

    @Override
    public void refresh() {
        dashboardFrame.repaint();
        dashboardFrame.showDashboard();
        dashboardDispatch.apply();
    }

    public List<String[]> getNextThreeOfferings() {
        class R {
            final LocalDateTime t;
            final String name, room;
            R(LocalDateTime t, String n, String room) { this.t = t; this.name = n; this.room = room; }
        }
        List<R> rs = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            LocalDate wk = LocalDate.now().with(DayOfWeek.SUNDAY).plusWeeks(i);
            for (Workout w : gymManager.getActiveUser().getWorkouts())
                for (Workout.Offering o : w.offerings)
                    rs.add(new R(wk.with(o.day).atTime(o.start), w.name, o.room.name));
        }
        rs.sort(Comparator.comparing(o -> o.t));
        return rs.stream().filter(r -> r.t.isAfter(LocalDateTime.now())).map(r -> new String[]{
                r.name,
                r.room,
                r.t.format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + r.t.format(DateTimeFormatter.ISO_LOCAL_TIME)
        }).limit(3).collect(Collectors.toList());
    }

}
