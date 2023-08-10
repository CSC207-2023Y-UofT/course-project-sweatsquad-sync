package ebr;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

public class EBRTest {
    @Test
    public void hashingTest() {
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

    @Test
    public void workoutOfferingTest() {
        Workout w = new Workout("asdf", 50);
        Room r = new Room("r");
        w.offerings.add(new Workout.Offering(DayOfWeek.MONDAY, LocalTime.of(10, 0), Duration.ofMinutes(30), r));
        Assertions.assertEquals(w.offerings.size(), 1);
    }

    @Test
    public void gymTest() {
        Gym g = new Gym("a");
        Room r1 = new Room("d");
        User u1 = new User("d", "", "", "", "");
        Workout w = new Workout("d",  1);
        g.removeRooms(r1);
        Assertions.assertEquals(g.getRooms().size(), 0);
        g.addRoom(r1);
        Assertions.assertEquals(g.getRooms().size(), 1);
        g.addRoom(r1);
        Assertions.assertEquals(g.getRooms().size(), 1);
        g.removeRooms(r1);
        Assertions.assertEquals(g.getRooms().size(), 0);
        g.removeUser(u1);
        Assertions.assertEquals(g.getUsers().size(), 0);
        g.addUser(u1);
        Assertions.assertEquals(g.getUsers().size(), 1);
        g.addUser(u1);
        Assertions.assertEquals(g.getUsers().size(), 1);
        g.removeUser(u1);
        Assertions.assertEquals(g.getUsers().size(), 0);
        g.removeWorkout(w);
        Assertions.assertEquals(g.getWorkouts().size(), 0);
        g.addWorkout(w);
        Assertions.assertEquals(g.getWorkouts().size(), 1);
        g.addWorkout(w);
        Assertions.assertEquals(g.getWorkouts().size(), 1);
        g.removeWorkout(w);
        Assertions.assertEquals(g.getWorkouts().size(), 0);
    }

    @Test
    public void serializeTest() throws IOException, ClassNotFoundException {
        Gym g = new Gym("a");
        User u1 = new User("1","","","","");
        User u2 = new User("2","","","","");
        Workout w = new Workout("d", 50);
        g.addUser(u1);
        g.addUser(u2);
        g.addWorkout(w);
        u1.addWorkout(w);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("serializeTest"));
        oos.writeObject(g);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("serializeTest"));
        Gym gg = (Gym)ois.readObject();
        ois.close();

        Assertions.assertTrue(gg.getWorkouts().contains(w));
        Assertions.assertEquals(2, gg.getUsers().size());
        Assertions.assertTrue(gg.getWorkouts().stream().allMatch(ww -> ww.getUsers().stream().allMatch(u -> u.name.equals(u1.name))));
    }

    @Test
    public void certTest() {
        Workout w = new Workout("w",  20);
        w.requireCert("d");
        Instructor i = new Instructor();
        i.claim("i", "", "", "", "");
        i.addWorkout(w);
        Assertions.assertEquals(w.getUsers().size(), 0);
        Assertions.assertEquals(i.getWorkouts().size(), 0);
        i.certs.add("d");
        i.addWorkout(w);
        Assertions.assertEquals(w.getUsers().size(), 1);
        Assertions.assertEquals(i.getWorkouts().size(), 1);
    }

    @Test
    public void nonStaffCountTest() {
        Workout w = new Workout("w", 20);
        User u1 = new User("u1", "", "", "", "");
        User u2 = new User("u2", "", "", "", "");
        Instructor i = new Instructor();
        i.claim("i", "", "", "", "");
        u1.addWorkout(w);
        u2.addWorkout(w);
        i.addWorkout(w);
        Assertions.assertEquals(w.getNonStaffUserCount(), 2);
    }

    @Test
    public void adminAddTest() {
        Workout w = new Workout("w", 20);
        GymAdmin a = new GymAdmin("a", "", "", "", "");
        UnsupportedOperationException thrown = Assertions.assertThrows(
                UnsupportedOperationException.class,
                () -> a.addWorkout(w),
                "Expected addWorkout() to throw, but it didn't"
        );
    }

    @Test
    public void fullWorkoutTest() {
        Workout w = new Workout("w", 1);
        User u1 = new User("u1", "", "", "", "");
        User u2 = new User("u2", "", "", "", "");

        u1.addWorkout(w);
        Assertions.assertEquals(w.getUsers().size(), 1);
        u2.addWorkout(w);
        Assertions.assertEquals(w.getUsers().size(), 1);
    }

    @Test
    public void unenrollTest() {
        Workout w = new Workout("w", 1);
        User u1 = new User("u1", "", "", "", "");
        u1.removeWorkout(w);
        Assertions.assertEquals(u1.getWorkouts().size(), 0);
        Assertions.assertEquals(w.getUsers().size(), 0);

        u1.addWorkout(w);
        u1.removeWorkout(w);
        Assertions.assertEquals(u1.getWorkouts().size(), 0);
        Assertions.assertEquals(w.getUsers().size(), 0);
    }
}
