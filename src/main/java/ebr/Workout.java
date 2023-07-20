package ebr;

import java.util.HashSet;
import java.util.Set;

public class Workout {
    public String name;
    public Set<String> requiredCerts;

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

    public Workout(String name) {
        this.name = name;
        this.requiredCerts = new HashSet<>();
    }
}
