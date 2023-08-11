package ia;

import abr.GymManager;
import ebr.Instructor;
import ebr.User;

import java.util.List;
import java.util.stream.Collectors;

public class WorkoutUsersPresenter implements Presenter {



    private GymManager gymManager;

    public WorkoutUsersPresenter(GymManager gymManager) {
        this.gymManager = gymManager;
    }

    public List<String[]> getCurrentWorkoutUsers(int i) {
        return gymManager.getGym().getWorkouts().stream().collect(Collectors.toList()).get(i).getUsers().stream().map(
                u -> new String[]{
                        u.firstName == null ? "N/A" : u.firstName + " " + u.lastName,
                        u.getUsername(),
                        u.getClass().getSimpleName(),
                        u instanceof Instructor ?
                                ((Instructor) u).certs.isEmpty() ? "None" :
                                        String.join(", ", ((Instructor) u).certs) :
                                "N/A"
                }).collect(Collectors.toList());
    }

    public void removeUserFromWorkout(int i, String name) {
        for (User u : gymManager.getGym().getUsers())
            if (u.getUsername().equals(name))
                u.removeWorkout(gymManager.getGym().getWorkouts().stream().collect(Collectors.toList()).get(i));
    }

}
