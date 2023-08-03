package fd;

import ebr.Gym;
import java.io.*;

public class FileDatabase implements GymDatabase {

    // declares database vars - hashmap, txt file, and hash toggle
    private final String filename = "gym.bin";

    // database constructor
    public FileDatabase() {}


    @Override
    public void save(Gym g) throws IOException {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
        oos.writeObject(g);
        oos.close();
    }

    @Override
    public Gym load() throws IOException, ClassNotFoundException {

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
            Gym g = (Gym) ois.readObject();
            ois.close();
            return g;
        } catch (IOException e1) {
            System.err.println("File not found");
            throw e1;
        } catch (ClassNotFoundException e2) {
            System.err.println("File is corrupt");
            throw e2;
        }

    }

}
