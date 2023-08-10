package ia;

import abr.GymManager;
import ebr.Room;
import ebr.Workout;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class WorkoutOfferingPresenter implements Presenter {


    private GymManager gymManager;

    public WorkoutOfferingPresenter(GymManager gymManager) {
        this.gymManager = gymManager;
    }



    public List<String[]> getCurrentOfferings(int idx) {
        return gymManager.getGym().getWorkouts().stream().collect(Collectors.toList()).get(idx).offerings.stream().map(o -> new String[]{
                o.day.toString() + " " + o.start.format(DateTimeFormatter.ofPattern("HH:mm")),
                Long.toString(o.duration.toHours()),
                o.room.name
        }).collect(Collectors.toList());
    }

    public List<String> getCurrentRooms() {
        return gymManager.getGym().getRooms().stream().map(r -> r.name).collect(Collectors.toList());
    }

    public boolean editOffering(int course, int offering, DayOfWeek day, String start, int duration, String room) {
        LocalTime s = LocalTime.parse(start, DateTimeFormatter.ofPattern("HH:mm"));
        Duration d = Duration.ofHours(duration);

        Workout w = gymManager.getGym().getWorkouts().stream().collect(Collectors.toList()).get(course);
        for (Workout.Offering o : w.offerings) {
            if (o.day == day && ((!o.start.isAfter(s) && s.isBefore(o.start.plus(o.duration)))
                    || (o.start.isAfter(s) && o.start.isBefore(s.plus(d)))))
                return false;
        }

        Room r = null;
        for (Room rr : gymManager.getGym().getRooms())
            if (rr.name.equals(room)) {
                r = rr;
                break;
            }

        if (offering == -1)
            w.offerings.add(new Workout.Offering(day, s, d, r));
        else {
            Workout.Offering o = w.offerings.get(offering);
            o.day = day;
            o.start = s;
            o.duration = d;
            o.room = r;
        }
        return true;
    }

    public void removeOffering(int i, int j) {
        gymManager.getGym().getWorkouts().stream().collect(Collectors.toList()).get(i).offerings.remove(j);
    }
}
