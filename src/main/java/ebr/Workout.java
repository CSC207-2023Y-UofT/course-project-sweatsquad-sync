package ebr;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class Workout implements Serializable {
    public String name;
    public Set<String> requiredCerts;

    enum Weekday {
        Monday, Tuesday, Wednesday, Thursday, Friday
    }

    static public class Offering {
        Weekday day;
        LocalTime start;
        Duration duration;
        Instructor instructor;
        Room room;
        public Offering(Weekday day, LocalTime start, Duration duration, Room room) {
            this.day = day;
            this.start = start;
            this.duration = duration;
            this.room = room;
            this.instructor = null;
        }
        public Offering(Weekday day, LocalTime start, Duration duration, Room room, Instructor instructor) {
            this.day = day;
            this.start = start;
            this.duration = duration;
            this.room = room;
            this.instructor = instructor;
        }
    }

    public List<Offering> offerings;

    public Workout(String name) {
        this.name = name;
        this.requiredCerts = new HashSet<>();
        this.offerings = new ArrayList<>();
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
