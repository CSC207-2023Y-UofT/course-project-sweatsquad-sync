package ia;

import abr.GymManager;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.stream.Collectors;

public class SchedulePresenter implements Presenter {

    public SchedulePresenter(GymManager gymManager) {
        this.gymManager = gymManager;
    }

    GymManager gymManager;


    public String getScheduleBlockAt(int hr, int day) {
        DayOfWeek[] day_jump = new DayOfWeek[]{ DayOfWeek.SUNDAY,  DayOfWeek.MONDAY,  DayOfWeek.TUESDAY,  DayOfWeek.WEDNESDAY,  DayOfWeek.THURSDAY,  DayOfWeek.FRIDAY,  DayOfWeek.SATURDAY};
        class R {
            LocalTime t;
            DayOfWeek day;
            Duration d;
            String name, room;
            R(LocalTime t, DayOfWeek day, Duration d, String n, String room) { this.t = t; this.day = day; this.d = d; this.name = n; this.room = room; }
        }
        return "<HTML>" + gymManager.getGym().getWorkouts().stream().flatMap(w -> w.offerings.stream()
                        .map(o -> new R(o.start, o.day, o.duration, w.name, o.room.name)))
                .filter(r -> r.day == day_jump[day] && r.t.getHour() <= hr && hr < r.t.plus(r.d).getHour())
                .map(r -> "<B>" + r.name + "</B>" + "<BR>" + r.room).collect(Collectors.joining("<BR>")) + "</HTML>";
    }
}
