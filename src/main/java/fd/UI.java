package fd;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UI extends JFrame implements ActionListener {

    private CardLayout cardLayout = new CardLayout();
    private JButton loginButton, signupButton, registerButton;
    private JTextField usernameField, emailField;
    private JPasswordField passwordField, confirmPasswordField;
    private JPanel loginPanel, signupPanel;

    // Entry point of the program; creates instance of the UI class
    public static void main(String[] args) {
        new UI();
    }

    public UI() {
        setTitle("User Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        setLayout(cardLayout);

        // Define component width and height
        int compWidth = 200;
        int compHeight = 30;

        // Calculate horizontal and vertical offset for centering
        int xOffset = (getWidth() - compWidth) / 2;
        int yOffset = (getHeight() - compHeight) / 2;

        // Login panel
        loginPanel = new JPanel();
        loginPanel.setLayout(null);

        // Username field
        usernameField = new JTextField(20);
        usernameField.setBounds(xOffset, yOffset - 80, compWidth, compHeight);
        loginPanel.add(usernameField);

        // Password field
        passwordField = new JPasswordField(20);
        passwordField.setBounds(xOffset, yOffset - 40, compWidth, compHeight);
        loginPanel.add(passwordField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setBounds(xOffset, yOffset, 80, compHeight);
        loginButton.addActionListener(this);
        loginPanel.add(loginButton);

        // Signup button
        signupButton = new JButton("Signup");
        signupButton.setBounds(xOffset + 120, yOffset, 80, compHeight);
        signupButton.addActionListener(this);
        loginPanel.add(signupButton);

        // Signup panel
        signupPanel = new JPanel();
        signupPanel.setLayout(null);

        // Username field
        usernameField = new JTextField(20);
        usernameField.setBounds(xOffset, yOffset - 140, compWidth, compHeight);
        signupPanel.add(usernameField);

        // Email field
        emailField = new JTextField(20);
        emailField.setBounds(xOffset, yOffset - 100, compWidth, compHeight);
        signupPanel.add(emailField);

        // Password field
        passwordField = new JPasswordField(20);
        passwordField.setBounds(xOffset, yOffset - 60, compWidth, compHeight);
        signupPanel.add(passwordField);

        // Confirm password field
        confirmPasswordField = new JPasswordField(20);
        confirmPasswordField.setBounds(xOffset, yOffset - 20, compWidth, compHeight);
        signupPanel.add(confirmPasswordField);

        // Register button
        registerButton = new JButton("Register");
        registerButton.setBounds(xOffset, yOffset + 20, 100, compHeight);
        registerButton.addActionListener(this);
        signupPanel.add(registerButton);

        add(loginPanel, "Login");
        add(signupPanel, "Signup");

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            // login logic is missing
            System.out.println("doesn't work yet lol");
        } else if (e.getSource() == signupButton) {
            cardLayout.show(getContentPane(), "Signup");
        } else if (e.getSource() == registerButton) {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            System.out.println("Username: " + username);
            System.out.println("Email: " + email);
            System.out.println("Password: " + password);
            System.out.println("Confirm Password: " + confirmPassword);

            cardLayout.show(getContentPane(), "Login");
        }
    }
}