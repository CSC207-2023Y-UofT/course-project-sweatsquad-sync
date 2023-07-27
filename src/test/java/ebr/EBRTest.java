package ebr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;

public class EBRTest {
    @Test
    public void HashingTest() {
        Gym g = new Gym("so true");
        Room r1 = new Room("d");
        Room r2 = new Room("e");
        g.addRoom(r1);
        Assertions.assertEquals(g.getRooms(), "[ebr.Room@0]");
        g.removeRooms(r2);
        Assertions.assertEquals(g.getRooms(), "[ebr.Room@0]");
        g.addRoom(r2);
        Assertions.assertEquals(g.getRooms(), "[ebr.Room@0, ebr.Room@1]");
        g.removeRooms(r1);
        Assertions.assertEquals(g.getRooms(), "[ebr.Room@1]");
    }
    @Test
    public void WorkoutOfferingTest() {
        Workout w = new Workout("asdf");
        w.addOffering(Workout.Weekday.Monday, LocalTime.of(10, 0), Duration.ofMinutes(30));
        System.out.println(w.offerings);
    }
}
