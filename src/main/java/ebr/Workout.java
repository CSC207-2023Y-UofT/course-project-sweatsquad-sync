package ebr;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class Workout implements Serializable {
    public String name;
    public Set<String> requiredCerts;
    private Set<User> users;

    enum Weekday {
        Monday, Tuesday, Wednesday, Thursday, Friday
    }

    static public class Offering {
        Weekday day;
        LocalTime start;
        Duration duration;
        Room room;
        public Offering(Weekday day, LocalTime start, Duration duration, Room room) {
            this.day = day;
            this.start = start;
            this.duration = duration;
            this.room = room;
        }
    }

    public List<Offering> offerings;

    public Workout(String name) {
        this.name = name;
        this.requiredCerts = new HashSet<>();
        this.offerings = new ArrayList<>();
        this.users = new HashSet<>();
    }

    public Set<String> getRequiredCerts() {
        return requiredCerts;
    }

    public boolean validateCerts(Set<String> certs) {
        return certs.containsAll(requiredCerts);
    }

    public void requireCert(String cert) {
        requiredCerts.add(cert);
    }

    public void deleteCert(String cert) {
        requiredCerts.remove(cert);
    }

    protected boolean addUser(User u) {
        if (u instanceof Instructor) {
            if (validateCerts(((Instructor)u).certs)) {
                this.users.add(u);
                return true;
            }
            else return false;
        }
        else {
            this.users.add(u);
            return true;
        }
    }

    protected void removeUser(User u) {
        this.users.remove(u);
    }

    public Set<User> getUsers() {
        return Collections.unmodifiableSet(this.users);
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
