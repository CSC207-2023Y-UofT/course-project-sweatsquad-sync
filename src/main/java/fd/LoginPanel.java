package fd;

import ia.EntryFramePresenter;
import ia.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel implements ActionListener, View<EntryFramePresenter> {
    private JButton loginButton;
    private JTextField usernameField;
    private JPasswordField passcodeField;
    private EntryFramePresenter presenter;
    LoginPanel() {
        this.setLayout(null); // layout form, switches between cards
        this.setOpaque(false);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(ComponentFactory.MB15);
        usernameLabel.setBounds(189, 98, 422, 25);
        this.add(usernameLabel);

        usernameField = ComponentFactory.genRoundTextField("", 20, "#FFFFFF", false);
        usernameField.setBounds(189, 123, 422, 45);
        usernameField.setFont(ComponentFactory.CB18);
        this.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(ComponentFactory.MB15);
        passwordLabel.setBounds(189, 185, 422, 25);
        this.add(passwordLabel);

        passcodeField = ComponentFactory.genRoundPasswordField("", 20, "#FFFFFF",
                false);
        passcodeField.setFont(ComponentFactory.A16);
        passcodeField.setBounds(189, 210, 422, 45);
        this.add(passcodeField);

        loginButton = ComponentFactory.genRoundBtn("Login", 50, "#001561", false);
        loginButton.setFont(ComponentFactory.MB13);
        loginButton.setForeground(Color.decode("#FFFFFF"));
        loginButton.setBounds(189, 292, 422, 50);
        loginButton.addActionListener(this);
        this.add(loginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            usernameField.setText(username);
            String password = new String(passcodeField.getPassword());
            presenter.loginAttempted(username, password);
        }
    }

    @Override
    public void displayInfoMessage(String message) {

    }

    @Override
    public void displayErrorMessage(String message) {

    }

    @Override
    public void setPresenter(EntryFramePresenter entryFramePresenter) {
        this.presenter = entryFramePresenter;
    }
}
