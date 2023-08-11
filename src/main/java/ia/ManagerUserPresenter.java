package ia;

import abr.ActivationCodeGenerationStrategy;
import abr.GymManager;
import ebr.Instructor;

import java.util.List;
import java.util.stream.Collectors;

public class ManagerUserPresenter implements Presenter {

    private GymManager gymManager;
    private ActivationCodeGenerationStrategy generationStrategy;

    public ManagerUserPresenter(GymManager gymManager) {
        this.gymManager = gymManager;
    }

    public void setStrategy(ActivationCodeGenerationStrategy strategy) {
        this.generationStrategy = strategy;
    }

    public List<String[]> getCurrentUsers() {
        return gymManager.getGym().getUsers().stream().map(
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

    public String newInstructor() {
        Instructor i = new Instructor(generationStrategy.generate());
        gymManager.getGym().addUser(i);
        return i.getAuthCode();
    }

    public void removeUser(int i) {
        gymManager.getGym().removeUser(gymManager.getGym().getUsers().get(i));
    }

    public void instructorAddCert(int i, String cert) {
        ((Instructor)gymManager.getGym().getUsers().get(i)).certs.add(cert);
    }

    public String adminReqInstructorAuthCode(int i) {
        return ((Instructor)gymManager.getGym().getUsers().get(i)).getAuthCode();
    }

}
