package ia;

import static org.mockito.Mockito.*;

import abr.GymManager;
import ebr.Gym;
import ebr.Instructor;
import ebr.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

// tests the behavior of the some methods from interface adapters -specifically WorkoutCertsPresenter
public class IATest {

    private WorkoutCertsPresenter presenter;
    private Workout workoutMock;

    // initialize + mocks GymManager, Gym, and Workout
    @BeforeEach
    public void setup() {
        GymManager gymManagerMock = mock(GymManager.class);
        Gym gymMock = mock(Gym.class);
        workoutMock = mock(Workout.class);

        when(gymManagerMock.getGym()).thenReturn(gymMock);
        when(gymMock.getWorkouts()).thenReturn(Collections.singletonList(workoutMock));

        presenter = new WorkoutCertsPresenter(gymManagerMock);
    }

    // tests getCurrentWorkoutCerts method.
    @Test
    public void testGetCurrentWorkoutCerts() {
        when(workoutMock.getRequiredCerts()).thenReturn(Arrays.asList("cert1", "cert2"));

        String[] certs = presenter.getCurrentWorkoutCerts(0);

        assertThat(certs).containsExactly("cert1", "cert2");
    }

    // tests the requireCert method when an instructor does not have a required certification
    @Test
    public void testRequireCert_WithInstructorNotHavingCert() {
        Instructor instructor = new Instructor("");
        instructor.certs = Collections.emptyList(); // Assuming `certs` is a public field
        instructor.firstName = "John";
        instructor.lastName = "Doe";

        when(workoutMock.getUsers()).thenReturn(Collections.singletonList(instructor));

        String result = presenter.requireCert(0, "cert1");

        assertThat(result).isEqualTo("Instructor John Doe does not have cert cert1!");
    }

    // tests testRequireCert_WithCertAlreadyRequired method
    @Test
    public void testRequireCert_WithCertAlreadyRequired() {
        when(workoutMock.getRequiredCerts()).thenReturn(List.of("cert1"));

        String result = presenter.requireCert(0, "cert1");

        assertThat(result).isEqualTo("This cert is already required for this workout!");
    }

    // tests the successful case of the requireCert method
    @Test
    public void testRequireCert_Success() {
        when(workoutMock.getRequiredCerts()).thenReturn(Collections.emptyList());
        when(workoutMock.getUsers()).thenReturn(Collections.emptyList());

        String result = presenter.requireCert(0, "cert1");

        assertThat(result).isNull();
        verify(workoutMock).requireCert("cert1");
    }
}
