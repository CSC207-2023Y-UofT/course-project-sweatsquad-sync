package ia;

import abr.GymManager;
import ebr.Instructor;
import ebr.User;
import ebr.Workout;
import fd.WorkoutCertsFrame;

import java.util.stream.Collectors;

public class WorkoutCertsPresenter implements Presenter {
    public WorkoutCertsPresenter(GymManager gymManager) {
        this.gymManager = gymManager;
    }

    private GymManager gymManager;

    public String[] getCurrentWorkoutCerts(int index) {
        return gymManager.getGym().getWorkouts().get(index).getRequiredCerts().toArray(new String[]{});
    }

    public String requireCert(int i, String cert) {
        Workout w = gymManager.getGym().getWorkouts().get(i);
        for (User u : w.getUsers())
            if (u instanceof Instructor)
                if (!((Instructor) u).certs.contains(cert))
                    return "Instructor " + u.firstName + " " + u.lastName + " does not have cert " + cert + "!";

        if (w.getRequiredCerts().contains(cert))
            return "This cert is already required for this workout!";

        w.requireCert(cert);
        return null;
    }

    public void removeCurrentCert(int i, String cert) {
        gymManager.getGym().getWorkouts().get(i).deleteCert(cert);
    }

}
