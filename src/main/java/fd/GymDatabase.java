package fd;

import ebr.Gym;

import java.io.IOException;

public interface GymDatabase {

    void save(Gym g) throws IOException;

    Gym load() throws IOException, ClassNotFoundException;

}


