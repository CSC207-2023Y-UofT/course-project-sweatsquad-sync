package ebr;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class Workout implements Serializable {
    public String name;
    private final List<String> requiredCerts;
    private final List<User> users;
    public final int capacity;

    static public class Offering implements Serializable {
        public DayOfWeek day;
        public LocalTime start;
        public Duration duration;
        public Room room;
        public Offering(DayOfWeek day, LocalTime start, Duration duration, Room room) {
            this.day = day;
            this.start = start;
            this.duration = duration;
            this.room = room;
        }
    }

    public List<Offering> offerings;

    public Workout(String name, int capacity) {
        this.name = name;
        this.requiredCerts = new ArrayList<>();
        this.offerings = new ArrayList<>();
        this.users = new ArrayList<>();
        this.capacity = capacity;
    }

    public List<String> getRequiredCerts() {
        return requiredCerts;
    }

    public boolean validateCerts(List<String> certs) {
        return new HashSet<>(certs).containsAll(requiredCerts);
    }

    public void requireCert(String cert) {
        if (!requiredCerts.contains(cert))
            requiredCerts.add(cert);
    }

    public void deleteCert(String cert) {
        requiredCerts.remove(cert);
    }

    public int getNonStaffUserCount() {
        return (int)this.users.stream().filter(a -> a.getClass().equals(User.class)).count();
    }

    protected boolean addUser(User u) {
        if (u instanceof Instructor) {
            if (validateCerts(((Instructor)u).certs) && !this.users.contains(u)) {
                this.users.add(u);
                return true;
            }
            else return false;
        }
        else {
            if (this.getNonStaffUserCount() < capacity && !this.users.contains(u)) {
                this.users.add(u);
                return true;
            }
            else
                return false;
        }
    }

    protected void removeUser(User u) {
        this.users.remove(u);
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(this.users);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workout workout = (Workout) o;
        return Objects.equals(name, workout.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
