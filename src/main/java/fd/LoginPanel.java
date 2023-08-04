package fd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ActionListener {
    private JButton loginButton;
    private JTextField usernameField;
    private JPasswordField passcodeField;
    LoginPanel() {
        this.setLayout(null); // layout form, switches between cards
        this.setOpaque(false);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(UI.MB15);
        usernameLabel.setBounds(189, 98, 422, 25);
        this.add(usernameLabel);

        usernameField = UI.genRoundTextField("", 20, "#FFFFFF", false);
        usernameField.setBounds(189, 123, 422, 45);
        usernameField.setFont(UI.CB18);
        this.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(UI.MB15);
        passwordLabel.setBounds(189, 185, 422, 25);
        this.add(passwordLabel);

        passcodeField = UI.genRoundPasswordField("", 20, "#FFFFFF",
                false);
        passcodeField.setFont(UI.A16);
        passcodeField.setBounds(189, 210, 422, 45);
        this.add(passcodeField);

        loginButton = UI.genRoundBtn("Login", 50, "#001561", false);
        loginButton.setFont(UI.MB13);
        loginButton.setForeground(Color.decode("#FFFFFF"));
        loginButton.setBounds(189, 292, 422, 50);
        loginButton.addActionListener(this);
        this.add(loginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText().trim();
            usernameField.setText(username);
            String password = new String(passcodeField.getPassword());

            if (!App.login(username, password))
                JOptionPane.showMessageDialog(this, "Invalid credentials");
            else {
                usernameField.setText("");
                passcodeField.setText("");
            }
        }
    }
}
