package fd;

import java.io.IOException;
import javax.swing.*;
import javax.swing.UIManager.*;

public class App {
    static public Database db;
    static public EntryPointFrame entry;
    static public DashboardFrame dashboard;
    static boolean login(String user, String pass) {
        if (db.validateLogin(user, pass)) {
            entry.setVisible(false);
            dashboard.refreshShow();
            return true;
        }
        else
            return false;
    }
    static void logout() {
        db.logout();
        entry.setVisible(true);
        dashboard.setVisible(false);
    }

    public static void setNimbus() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            if ("Nimbus".equals(info.getName())) {
                UIManager.setLookAndFeel(info.getClassName());
                break;
            }
    }

    public static void main(String[] args) {
        try { setNimbus(); }
        catch (Exception ignored) {}

        db = new Database();
        entry = new EntryPointFrame();
        dashboard = new DashboardFrame();

        entry.setVisible(true);
        if (db.isEmpty())
            entry.adminSignupCard();

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
