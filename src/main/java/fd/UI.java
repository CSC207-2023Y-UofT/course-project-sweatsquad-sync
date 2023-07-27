package fd;

// import statements
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UI extends JFrame implements ActionListener {

    // declaring UI components / layout
    private CardLayout cardLayout = new CardLayout();
    private JButton loginButton, signupButton, registerButton, back2loginBtn;
    private JButton returnLoginBtn, authenticateBtn;
    private JTextField usernameField, userField, emailField, authField;
    private JTextField firstNameField, lastNameField;
    private JPasswordField passcodeField, passField, confirmPassField;
    private JPanel loginPanel, signupPanel, authCodePanel;
    private JLabel haveCodeLabel;
    private RoundBtn roundBtn = new RoundBtn();

    // declaring the database instance (database.java)
    private Database database;

    // entry point of the program; creates instance of the UI class
    public static void main(String[] args) {
        new UI();
    }

    // UI constructor - has a database, and covers login/registration
    public UI() {

        //init Database instance
        database = new Database();

        // ui 'defaults'
        setTitle("SweatSquad Sync System"); // window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ends program if x
        setSize(800, 600); // window dimensions
        setResizable(false); // disables resizing
        setLayout(cardLayout); // layout form, switches between cards
        setLocationRelativeTo(null); // centers ui if left 'null'

        // define component width and height
        int compW = 200; // dictates text field length
        int compH = 30; // dictates text field height

        // horizontal and vertical offset variable assignment for centering
        int xOffset = (getWidth() - compW) / 2;
        int spacing = 50; // define the space between the two columns
        int xOffsetLeft = (getWidth() - 2 * compW - spacing) / 2;
        int xOffsetRight = xOffsetLeft + compW + spacing;
        int yOffset = (getHeight() - compH) / 2;

        // Login panel
        loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(Color.decode("#F5F4F2")); // pantone cool 25%

        // username label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
        usernameLabel.setBounds(189, 98, 422, 25);
        loginPanel.add(usernameLabel);

        // username field
        usernameField = new JTextField(20); // 20 is default -negligible
        usernameField.setBounds(189, 123, 422, 45);
        usernameField.setBorder(new EmptyBorder(0, 20, 0, 20)); // removes bord.
        usernameField.setFont(new Font("Comfortaa", Font.BOLD, 18));
        loginPanel.add(usernameField);

        // passcode label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
        passwordLabel.setBounds(189, 185, 422, 25);
        loginPanel.add(passwordLabel);

        // passcode field
        passcodeField = new JPasswordField(20); // 20 is default -negligible
        passcodeField.setBounds(189, 210, 422, 45);
        passcodeField.setBorder(new EmptyBorder(0, 20, 0, 20)); // removes bord.
        loginPanel.add(passcodeField);

        // login button (round button)
        loginButton = roundBtn.genRoundBtn("Login", 50, "#001561", false);
        loginButton.setFont(new Font("Monsterrat", Font.BOLD, 13));
        loginButton.setForeground(Color.decode("#FFFFFF"));
        loginButton.setBounds(189, 292, 196, 50);
        loginButton.addActionListener(this);
        loginPanel.add(loginButton);

        // signup button
        signupButton = roundBtn.genRoundBtn("Create Account", 50, "#001561",
                                            false);
        signupButton.setFont(new Font("Monsterrat", Font.BOLD, 13));
        signupButton.setForeground(Color.decode("#FFFFFF"));
        signupButton.setBounds(413, 292, 196, 50);
        signupButton.addActionListener(this);
        loginPanel.add(signupButton);

        // login indicator bar JLabel
        JLabel signIndicator = new JLabel("");
        signIndicator.setIcon(new ImageIcon("images/00101-sign-indicator.png"));
        signIndicator.setBounds(189, 65, 422, 9);
        loginPanel.add(signIndicator);

        // Sign In JLabel
        JLabel signInTextLI = new JLabel("Sign In", SwingConstants.CENTER);
        signInTextLI.setFont(new Font("Monsterrat", Font.BOLD, 16));
        signInTextLI.setForeground(Color.decode("#001561"));
        signInTextLI.setBounds(189, 25, 211, 47);
        loginPanel.add(signInTextLI);

        // Register JLabel
        JLabel registerTextLI = new JLabel("Register", SwingConstants.CENTER);
        registerTextLI.setFont(new Font("Monsterrat", Font.BOLD, 14));
        registerTextLI.setForeground(Color.decode("#000000"));
        registerTextLI.setBounds(400, 27, 211, 47);
        loginPanel.add(registerTextLI);

        // signup panel
        signupPanel = new JPanel();
        signupPanel.setLayout(null);

        /* first name label + field
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(200, 145, 200, 30);
        signupPanel.add(firstNameLabel);

        // first name field
        firstNameField = new JTextField(20);
        firstNameField.setBounds(200, 165, 200, 30);
        signupPanel.add(firstNameField);*/

        // first name label
        JLabel firstNameLabel = new JLabel("First Name");
        firstNameLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
        firstNameLabel.setBounds(189, 98, 422, 25);
        signupPanel.add(firstNameLabel);

        // first name field
        firstNameField = new JTextField(20); // 20 is default -negligible
        firstNameField.setBounds(189, 123, 198, 45);
        firstNameField.setBorder(new EmptyBorder(0, 20, 0, 20)); // removes bord.
        firstNameField.setFont(new Font("Comfortaa", Font.BOLD, 18));
        signupPanel.add(firstNameField);

        // last name label
        JLabel lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
        lastNameLabel.setBounds(413, 98, 422, 25);
        signupPanel.add(lastNameLabel);

        // last name field
        lastNameField = new JTextField(20); // 20 is default -negligible
        lastNameField.setBounds(413, 123, 198, 45);
        lastNameField.setBorder(new EmptyBorder(0, 20, 0, 20)); // removes bord.
        lastNameField.setFont(new Font("Comfortaa", Font.BOLD, 18));
        signupPanel.add(lastNameField);

        // email label
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
        emailLabel.setBounds(189, 185, 422, 25);
        signupPanel.add(emailLabel);

        // email field
        emailField = new JTextField(20); // 20 is default -negligible
        emailField.setBounds(189, 210, 422, 45);
        emailField.setBorder(new EmptyBorder(0, 20, 0, 20)); // removes bord.
        emailField.setFont(new Font("Comfortaa", Font.BOLD, 18));
        signupPanel.add(emailField);

        /* username label + field
        JLabel signupUsernameLabel = new JLabel("Username");
        signupUsernameLabel.setBounds(500, 145, 200, 30);
        signupPanel.add(signupUsernameLabel);
        userField = new JTextField(20);
        userField.setBounds(500, 165, 200, 30);
        signupPanel.add(userField);*/

        // username label
        JLabel signupUsernameLabel = new JLabel("Username");
        signupUsernameLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
        signupUsernameLabel.setBounds(189, 272, 422, 25);
        signupPanel.add(signupUsernameLabel);

        // username field
        userField = new JTextField(20); // 20 is default -negligible
        userField.setBounds(189, 297, 422, 45);
        userField.setBorder(new EmptyBorder(0, 20, 0, 20)); // removes bord.
        userField.setFont(new Font("Comfortaa", Font.BOLD, 18));
        signupPanel.add(userField);

        /* passcode label + field
        JLabel signupPasswordLabel = new JLabel("Password");
        signupPasswordLabel.setBounds(500, 205, 200, 30);
        signupPanel.add(signupPasswordLabel);
        passField = new JPasswordField(20);
        passField.setBounds(500, 225, 200, 30);
        signupPanel.add(passField);*/

        // password label
        JLabel signupPasswordLabel = new JLabel("Password");
        signupPasswordLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
        signupPasswordLabel.setBounds(189, 359, 422, 25);
        signupPanel.add(signupPasswordLabel);

        // password field
        passField = new JPasswordField(20); // 20 is default -negligible
        passField.setBounds(189, 384, 198, 45);
        passField.setBorder(new EmptyBorder(0, 20, 0, 20)); // removes bord.
        passField.setFont(new Font("Comfortaa", Font.BOLD, 18));
        signupPanel.add(passField);

        // confirm password label
        JLabel confirmPasswordLabel = new JLabel("Password");
        confirmPasswordLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
        confirmPasswordLabel.setBounds(189, 359, 422, 25);
        signupPanel.add(confirmPasswordLabel);

        // confirm password field
        confirmPassField = new JPasswordField(20); // 20 is default -negligible
        confirmPassField.setBounds(413, 384, 198, 45);
        confirmPassField.setBorder(new EmptyBorder(0, 20, 0, 20)); // removes bord.
        confirmPassField.setFont(new Font("Comfortaa", Font.BOLD, 18));
        signupPanel.add(confirmPassField);

        // register button
        registerButton = new JButton("Register");
        registerButton.setBounds(420, 345, 100, 30);
        registerButton.addActionListener(this);
        signupPanel.add(registerButton);

        // back button
        back2loginBtn = new JButton("Back");
        back2loginBtn.setBounds(280, 345, 100, 30);
        back2loginBtn.addActionListener(this);
        signupPanel.add(back2loginBtn);

        haveCodeLabel = new JLabel("<HTML><U>I have a code</U></HTML>");
        haveCodeLabel.setForeground(Color.BLUE);
        haveCodeLabel.setBounds(350, 385, 200, 30);
        haveCodeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        haveCodeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // clear all the fields
                usernameField.setText("");
                passcodeField.setText("");
                userField.setText("");
                emailField.setText("");
                passField.setText("");
                confirmPassField.setText("");

                // switch the card layout back to the login panel
                cardLayout.show(getContentPane(), "AuthCode");
            }
        });
        signupPanel.add(haveCodeLabel);

        /* login panel background
        JLabel signupBG = new JLabel("");
        signupBG.setIcon(new ImageIcon("images/signupBG.png"));
        signupBG.setBounds(0, -10, 800, 600);
        signupPanel.add(signupBG);*/

        // login indicator bar JLabel
        JLabel regIndicator = new JLabel("");
        regIndicator.setIcon(new ImageIcon("images/00201-reg-indicator.png"));
        regIndicator.setBounds(189, 65, 422, 9);
        signupPanel.add(regIndicator);

        // Sign In JLabel
        JLabel signInTextSU = new JLabel("Sign In", SwingConstants.CENTER);
        signInTextSU.setFont(new Font("Monsterrat", Font.BOLD, 14));
        signInTextSU.setForeground(Color.decode("#000000"));
        signInTextSU.setBounds(189, 27, 211, 47);
        signupPanel.add(signInTextSU);

        // Register JLabel
        JLabel registerTextSU = new JLabel("Register", SwingConstants.CENTER);
        registerTextSU.setFont(new Font("Monsterrat", Font.BOLD, 16));
        registerTextSU.setForeground(Color.decode("#001561"));
        registerTextSU.setBounds(400, 25, 211, 47);
        signupPanel.add(registerTextSU);

        // instructor authenticate code panel
        authCodePanel = new JPanel();
        authCodePanel.setLayout(null);

        // auth code label + field
        JLabel authCodeLabel = new JLabel("Authorization Code:");
        authCodeLabel.setBounds(300, 225, 200, 30);
        authCodePanel.add(authCodeLabel);
        authField = new JTextField(20);
        authField.setBounds(300, 245, 200, 30);
        authCodePanel.add(authField);

        // return to log-in btn
        returnLoginBtn = new JButton("Return");
        returnLoginBtn.setBounds(295, 285, 95, 30);
        returnLoginBtn.addActionListener(this);
        authCodePanel.add(returnLoginBtn);

        // authenticate btn
        authenticateBtn = new JButton("Authenticate");
        authenticateBtn.setBounds(395, 285, 110, 30);
        authenticateBtn.addActionListener(this);
        authCodePanel.add(authenticateBtn);

        add(loginPanel, "Login"); // adds panel to the card "deck"
        add(signupPanel, "Signup");  // adds panel to the card "deck"
        add(authCodePanel, "AuthCode");  // adds panel to the card "deck"

        setVisible(true);
    }

    // button action listener response function
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) { // checks 4 loginButton; login logic

            // fetches username + passcode from JTextField components
            String username = usernameField.getText();
            String password = new String(passcodeField.getPassword());

            // checks if credentials are correct; uses Database.validateLogin
            if(database.validateLogin(username, password)) {
                System.out.println("Logged in successfully"); // console msg
            } else {
                System.out.println("Invalid credentials"); // failed console msg
            }

        } else if (e.getSource() == signupButton) { // signup logic if pressed
            cardLayout.show(getContentPane(), "Signup"); // switches to SU panel
            // resets fields (signup screen)
            userField.setText("");
            emailField.setText("");
            passField.setText("");
            confirmPassField.setText("");

        } else if (e.getSource() == registerButton) { // register logic
            // gets text from respective JTextField components
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String username = userField.getText();
            String email = emailField.getText();
            String password = new String(passField.getPassword());
            String confirmPassword = new String(confirmPassField.getPassword());

            if (username.length() < 3) {
                System.out.println("Username must be at least " +
                        "3 characters long");
                return;
            }

            if (firstName.isEmpty() || lastName.isEmpty()) {
                System.out.println("First and last name fields " +
                        "must not be empty");
                return;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                System.out.println("Invalid email format");
                return;
            }

            if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match, try again");
                return;
            }

            if(database.register(firstName, lastName, username,
                    email, password)) { // checks for uniqueness
                System.out.println("Registered successfully"); // console msg

                // console messages for testing
                System.out.println("Username: " + username);
                System.out.println("Email: " + email);
                System.out.println("Password: " + password);
                System.out.println("Confirm Password: " + confirmPassword);

                // Switch the card layout back to the login panel + reset JTFs
                usernameField.setText("");
                passcodeField.setText("");
                cardLayout.show(getContentPane(), "Login");
            } else {
                System.out.println("Username already exists"); // F console msg
            }
        } else if (e.getSource() == back2loginBtn) { // if back button is clicked
            // clears all the fields
            usernameField.setText("");
            passcodeField.setText("");
            userField.setText("");
            emailField.setText("");
            passField.setText("");
            confirmPassField.setText("");

            // switches the card layout back to log-in panel
            cardLayout.show(getContentPane(), "Login");
        } else if (e.getSource() == returnLoginBtn) {
            // clears all the fields
            usernameField.setText("");
            passcodeField.setText("");
            userField.setText("");
            emailField.setText("");
            passField.setText("");
            confirmPassField.setText("");

            // switches the card layout back to log-in panel
            cardLayout.show(getContentPane(), "Login");
        }
    }
}