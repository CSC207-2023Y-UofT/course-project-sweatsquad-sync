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
    public class DefaultUI extends JFrame implements ActionListener {
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

            // Username field
            usernameField = new JTextField(20);
            usernameField.setBounds(50, 30, 200, 30);

            // Password field
            passwordField = new JPasswordField(20);
            passwordField.setBounds(50, 70, 200, 30);

            // Login button
            loginButton = new JButton("Login");
            loginButton.setBounds(50, 110, 80, 30);
            loginButton.addActionListener(this);

            // Signup button
            signupButton = new JButton("Signup");
            signupButton.setBounds(170, 110, 80, 30);
            signupButton.addActionListener(this);

            add(usernameField);
            add(passwordField);
            add(loginButton);
            add(signupButton);

            setVisible(true);
        }

        // implement actionPerformed to handle user actions
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginButton) {
                // Logic for login button goes here
            } else if (e.getSource() == signupButton) {
                new SignupUI();
            }
        }
    } // DefaultUI end bracket

    // Signup UI
    public class SignupUI extends JFrame implements ActionListener {
        private JTextField usernameField;
        private JPasswordField passwordField;
        private JPasswordField confirmPasswordField;
        private JTextField emailField;
        private JButton registerButton;

        public SignupUI() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 600);
            setLayout(null);
            setTitle("Signup");

            // Username field
            usernameField = new JTextField(20);
            usernameField.setBounds(50, 30, 200, 30);

            // Email field
            emailField = new JTextField(20);
            emailField.setBounds(50, 70, 200, 30);

            // Password field
            passwordField = new JPasswordField(20);
            passwordField.setBounds(50, 110, 200, 30);

            // Confirm password field
            confirmPasswordField = new JPasswordField(20);
            confirmPasswordField.setBounds(50, 150, 200, 30);

            // Register button
            registerButton = new JButton("Register");
            registerButton.setBounds(50, 190, 100, 30);
            registerButton.addActionListener(this);

            add(usernameField);
            add(emailField);
            add(passwordField);
            add(confirmPasswordField);
            add(registerButton);

            setVisible(true);
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == registerButton) {
                String username = usernameField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                System.out.println("Username: " + username);
                System.out.println("Email: " + email);
                System.out.println("Password: " + password);
                System.out.println("Confirm Password: " + confirmPassword);
                // You can add the logic to save the user details and create the account
            }
        }
    }

    public static void main(String[] args) {
        // Entry point of the program; creates instance of the UI class
        new UI();
    }
}
