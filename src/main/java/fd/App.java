package fd;

public class App {
    static public Database db;
    public static void main(String[] args) {
        db = new Database();
        new EntryPointFrame();
    }
}
