package ia;

import abr.GymManager;
import ebr.Gym;
import ebr.Instructor;
import ebr.Workout;

import java.util.List;
import java.util.stream.Collectors;

public class CourseEnrolmentPresenter implements Presenter {

    private final GymManager gymManager;

    public CourseEnrolmentPresenter(GymManager gymManager) {
        this.gymManager = gymManager;
    }

    private Gym getGym() {

        return gymManager.getGym();
    }

    public List<String[]> getCurrentWorkouts() {
        return getGym().getWorkouts().stream().map(w -> new String[]{
                w.name,
                w.offerings.stream().map(o -> o.room.name).collect(Collectors.joining(", ")),
                w.offerings.stream().map(o -> o.start.toString()).collect(Collectors.joining(", ")),
                w.getUsers().stream().filter(u -> u instanceof Instructor).map(Object::toString).collect(Collectors.joining(", ")),
                w.getUsers().size() + "/" + w.capacity,
                gymManager.getActiveUser() instanceof Instructor ? "Teach" : (w.getUsers().contains(gymManager.getActiveUser()) ? "Drop" : "Enrol")
        }).collect(Collectors.toList());
    }

    public boolean addWorkout(String name, int capacity) {
        for (Workout w : getGym().getWorkouts())
            if (w.name.equals(name))
                return false;

        getGym().addWorkout(new Workout(name, capacity));
        return true;
    }

    public void removeWorkout(int i) {
        getGym().removeWorkout(getGym().getWorkouts().get(i));
    }

    public boolean canEditCourse(int course) {
        return course != -1 && (!(gymManager.getActiveUser() instanceof Instructor) || getGym().getWorkouts().get(course).getUsers().contains(gymManager.getActiveUser()));
    }

    public String toggleEnrol(int i) {

        Workout w = getGym().getWorkouts().get(i);
        if (gymManager.getActiveUser() instanceof Instructor)
            if (w.getUsers().contains(gymManager.getActiveUser()))
                return "You already teach this class!";
            else {
                if (gymManager.getActiveUser().addWorkout(w))
                    return "You now teach this class!";
                else
                    return "You do not have the required certs to teach this class!";
            }
        else {
            if (w.getUsers().contains(gymManager.getActiveUser()))
                gymManager.getActiveUser().removeWorkout(w);
            else
                gymManager.getActiveUser().addWorkout(w);
            return null;
        }
    }

    public boolean changeWorkoutName(int workout, String name) {
        for (Workout w : getGym().getWorkouts())
            if (w.name.equals(name))
                return false;

        getGym().getWorkouts().get(workout).name = name;
        return true;
    }


}
