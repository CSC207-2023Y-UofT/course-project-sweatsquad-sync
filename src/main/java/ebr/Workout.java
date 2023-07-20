package ebr;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Workout {
    private static int count = 0;
    private int id;
    public String name;
    public Set<String> requiredCerts;

    public Workout(String name) {
        this.id = count++;
        this.name = name;
        this.requiredCerts = new HashSet<>();
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
        return id;
    }
}
