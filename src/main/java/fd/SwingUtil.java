package fd;

import javax.swing.*;
import java.awt.*;

public class SwingUtil {

    public static void clearPanel(JPanel panel) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JTextField)
            {
                JTextField c = (JTextField) component;
                c.setText("");
            }
        }
    }
}
