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
        Assertions.assertEquals(g.getRooms().toString(), "[ebr.Room@64]");
        g.removeRooms(r2);
        Assertions.assertEquals(g.getRooms().toString(), "[ebr.Room@64]");
        g.addRoom(r2);
        Assertions.assertEquals(g.getRooms().toString(), "[ebr.Room@64, ebr.Room@65]");
        g.removeRooms(r1);
        Assertions.assertEquals(g.getRooms().toString(), "[ebr.Room@65]");
    }
    /* comment
    @Test
    public void WorkoutOfferingTest() {
        Workout w = new Workout("asdf", 50);
        Room r = new Room("r");
        w.offerings.add(new Workout.Offering(Workout.Weekday.Monday, LocalTime.of(10, 0), Duration.ofMinutes(30), r));
        System.out.println(w.offerings);
    }
    */
}
