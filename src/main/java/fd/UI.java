package fd;

import java.awt.event.*;
import javax.swing.*;

// UserInterface Class
public class UI {

    // Constructor for UI
    public UI() {
        new DefaultUI();
    }

    // UI for attendees - default user
    public static class DefaultUI extends JFrame implements ActionListener {
        // UI elements and features for default users - 2.1.1
        private JTextField usernameField;
        private JPasswordField passwordField;
        private JButton loginButton;
        private JButton signupButton;

        public DefaultUI() {
            // init UI elements for everyone

            // for the window itself
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 600);
            setLayout(null);
            setTitle("Login");

            // username field
            usernameField = new JTextField(20);
            usernameField.setBounds(50, 30, 200, 30);

            // passcode field
            passwordField = new JPasswordField(20);
            passwordField.setBounds(50, 70, 200, 30);

            // login button
            loginButton = new JButton("Login");
            loginButton.setBounds(50, 110, 80, 30);
            loginButton.addActionListener(this);

            // signup button
            signupButton = new JButton("Signup");
            signupButton.setBounds(170, 110, 80, 30);
            signupButton.addActionListener(this);

            // adds all components to the current JFrame
            add(usernameField);
            add(passwordField);
            add(loginButton);
            add(signupButton);

            // sets visibility so it can be seen
            setVisible(true);
        }

        // actionPerformed handles user actions
        public void actionPerformed(ActionEvent e) {
            // handle actions from input components
            if (e.getSource() == loginButton) {
                // logic for login button
            } else if (e.getSource() == signupButton) {
                // logic for signup button
            }
        }
    } // DefaultUI end bracket

    // other classes

    public static void main(String[] args) {
        // Entry point of the program; creates instance of the UI class
        new UI();
    }
}
