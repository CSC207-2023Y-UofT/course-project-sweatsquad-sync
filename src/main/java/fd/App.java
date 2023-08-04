package fd;

import java.io.IOException;

public class App {
    static public Database db = new Database();
    static public EntryPointFrame entry = new EntryPointFrame();
    static public DashboardFrame dashboard = new DashboardFrame();
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
    public static void main(String[] args) {
        entry.setVisible(true);
        if (db.isEmpty()) {
            entry.signupCard();
            entry.signupPanel.setAdminView();
        }

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
