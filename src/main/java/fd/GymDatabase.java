package fd;

import ebr.Gym;

import java.io.IOException;

public interface GymDatabase {

    public void save(Gym g) throws IOException;

    public Gym load() throws IOException, ClassNotFoundException;

}


