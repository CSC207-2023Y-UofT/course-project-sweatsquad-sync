package fd;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class App {
    static public Database db = new Database();
    static public EntryPointFrame entry = new EntryPointFrame();
    static public DashboardFrame dashboard = new DashboardFrame();
    static boolean login(String user, String pass) {
        if (db.validateLogin(user, pass)) {
            entry.setVisible(false);
            dashboard.setVisible(true);
            return true;
        }
        else
            return false;
    }
    public static void main(String[] args) {
        entry.setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                db.save();
            }
            catch (IOException e) {
                e.printStackTrace();
                System.err.println("Failed to write gym!");
            }
        }));
    }
}
