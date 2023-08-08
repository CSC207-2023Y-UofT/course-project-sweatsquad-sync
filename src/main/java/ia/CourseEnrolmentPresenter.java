package ia;

import abr.GymManager;
import ebr.Instructor;
import ebr.Workout;

import java.util.List;
import java.util.stream.Collectors;

public class CourseEnrolmentPresenter implements Presenter {

    private GymManager gymManager;

    public CourseEnrolmentPresenter(GymManager gymManager) {
        this.gymManager = gymManager;
    }

    public List<String[]> getCurrentWorkouts() {
        return gymManager.getGym().getWorkouts().stream().map(w -> new String[]{
                w.name,
                w.offerings.stream().map(o -> o.room.name).collect(Collectors.joining(", ")),
                w.offerings.stream().map(o -> o.start.toString()).collect(Collectors.joining(", ")),
                w.getUsers().stream().filter(u -> u instanceof Instructor).map(Object::toString).collect(Collectors.joining(", ")),
                w.getUsers().size() + "/" + w.capacity
        }).collect(Collectors.toList());
    }

    public boolean addWorkout(String name, int capacity) {
        for (Workout w : gymManager.getGym().getWorkouts())
            if (w.name.equals(name))
                return false;

        gymManager.getGym().addWorkout(new Workout(name, capacity));
        return true;
    }
}
