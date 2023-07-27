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
    private JButton returnLoginBtn, authenticateBtn, registerButtonIR;
    private JTextField usernameField, userField, emailField, authField;
    private JTextField firstNameField, lastNameField, firstNameFieldIR;
    private JTextField lastNameFieldIR, emailFieldIR, userFieldIR;
    private JPasswordField passcodeField, passField, confirmPassField;
    private JPasswordField passFieldIR, confirmPassFieldIR;
    private JPanel loginPanel, signupPanel, authCodePanel, instrRegPanel;
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

        // Login panel
        loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setBackground(Color.decode("#DADADA"));

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

        // login button UPDATED (round button)
        loginButton = roundBtn.genRoundBtn("Login", 50, "#001561", false);
        loginButton.setFont(new Font("Monsterrat", Font.BOLD, 13));
        loginButton.setForeground(Color.decode("#FFFFFF"));
//        loginButton.setBounds(189, 292, 196, 50);
        loginButton.setBounds(413, 292, 196, 50);
        loginButton.addActionListener(this);
        loginPanel.add(loginButton);

        // signup button UPDATED (round button)
        signupButton = roundBtn.genRoundBtn("Create Account", 50, "#001561",
                                            false);
        signupButton.setFont(new Font("Monsterrat", Font.BOLD, 13));
        signupButton.setForeground(Color.decode("#FFFFFF"));
//        signupButton.setBounds(413, 292, 196, 50);
        signupButton.setBounds(400, 27, 211, 47);
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
//        JLabel registerTextLI = new JLabel("Register", SwingConstants.CENTER);
//        registerTextLI.setFont(new Font("Monsterrat", Font.BOLD, 14));
//        registerTextLI.setForeground(Color.decode("#000000"));
//        registerTextLI.setBounds(400, 27, 211, 47);
//        loginPanel.add(registerTextLI);

        // signup panel
        signupPanel = new JPanel();
        signupPanel.setLayout(null);
        signupPanel.setBackground(Color.decode("#DADADA"));

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

        // back button UPDATED (round button)
        back2loginBtn = roundBtn.genRoundBtn("Sign In", 50, "#001561", false);
        back2loginBtn.setFont(new Font("Monsterrat", Font.BOLD, 13));
        back2loginBtn.setForeground(Color.decode("#FFFFFF"));
        back2loginBtn.setBounds(189, 27, 211, 47);
        back2loginBtn.addActionListener(this);
        signupPanel.add(back2loginBtn);

        // register button UPDATED (round button)
        registerButton = roundBtn.genRoundBtn("Register", 50, "#001561", false);
        registerButton.setFont(new Font("Monsterrat", Font.BOLD, 13));
        registerButton.setForeground(Color.decode("#FFFFFF"));
        registerButton.setBounds(413, 450, 196, 50);
        registerButton.addActionListener(this);
        signupPanel.add(registerButton);

        haveCodeLabel = new JLabel("<HTML><U>I have a code</U></HTML>");
        haveCodeLabel.setForeground(Color.BLUE);
        haveCodeLabel.setFont(new Font("Comfortaa", Font.BOLD, 13));
        haveCodeLabel.setBounds(350, 514, 200, 30);
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

        // login indicator bar JLabel
        JLabel regIndicator = new JLabel("");
        regIndicator.setIcon(new ImageIcon("images/00201-reg-indicator.png"));
        regIndicator.setBounds(189, 65, 422, 9);
        signupPanel.add(regIndicator);

        // Sign In JLabel
//        JLabel signInTextSU = new JLabel("Sign In", SwingConstants.CENTER);
//        signInTextSU.setFont(new Font("Monsterrat", Font.BOLD, 14));
//        signInTextSU.setForeground(Color.decode("#000000"));
//        signInTextSU.setBounds(189, 27, 211, 47);
//        signupPanel.add(signInTextSU);

        // Register JLabel
        JLabel registerTextSU = new JLabel("Register", SwingConstants.CENTER);
        registerTextSU.setFont(new Font("Monsterrat", Font.BOLD, 16));
        registerTextSU.setForeground(Color.decode("#001561"));
        registerTextSU.setBounds(400, 25, 211, 47);
        signupPanel.add(registerTextSU);

        // instructor authenticate code panel
        authCodePanel = new JPanel();
        authCodePanel.setLayout(null);
        authCodePanel.setBackground(Color.decode("#DADADA"));

        // auth code label
        JLabel authCodeLabel = new JLabel("Enter Authentication Code" +
                                          "                   (16 Characters)");
        // note: look at AbstractDocument and DocumentFilter to limit characters
        authCodeLabel.setBounds(189, 140, 422, 25);
        authCodeLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
        authCodePanel.add(authCodeLabel);

        // auth code field
        authField = new JTextField(20); // 20 is default -negligible
        authField.setBounds(189, 165, 422, 45);
        authField.setBorder(new EmptyBorder(0, 20, 0, 20)); // removes bord.
        authField.setFont(new Font("Comfortaa", Font.BOLD, 18));
        authField.setHorizontalAlignment(SwingConstants.CENTER); // c-align
        authCodePanel.add(authField);

        // return to log-in button UPDATED (round button)
        returnLoginBtn = roundBtn.genRoundBtn("Return", 50, "#001561", false);
        returnLoginBtn.setFont(new Font("Monsterrat", Font.BOLD, 13));
        returnLoginBtn.setForeground(Color.decode("#FFFFFF"));
        returnLoginBtn.setBounds(189, 292, 196, 50);
        returnLoginBtn.addActionListener(this);
        authCodePanel.add(returnLoginBtn);

        // authenticate button UPDATED (round button)
        authenticateBtn = roundBtn.genRoundBtn("Authenticate", 50, "#001561",
                false);
        authenticateBtn.setFont(new Font("Monsterrat", Font.BOLD, 13));
        authenticateBtn.setForeground(Color.decode("#FFFFFF"));
        authenticateBtn.setBounds(413, 292, 196, 50);
        authenticateBtn.addActionListener(this);
        authCodePanel.add(authenticateBtn);

        // screen indicator bar JLabel
        JLabel indicatorBar = new JLabel("");
        indicatorBar.setIcon(new ImageIcon("images/00301-auth-indicator.png"));
        indicatorBar.setBounds(189, 65, 422, 9);
        authCodePanel.add(indicatorBar);

        // screen title JLabel
        JLabel authLabelBig = new JLabel("Instructor Authentication",
                                         SwingConstants.CENTER);
        authLabelBig.setFont(new Font("Monsterrat", Font.BOLD, 16));
        authLabelBig.setForeground(Color.decode("#001561"));
        authLabelBig.setBounds(189, 25, 422, 47);
        authCodePanel.add(authLabelBig);

        // instructor registration code panel
        instrRegPanel = new JPanel();
        instrRegPanel.setLayout(null);
        instrRegPanel.setBackground(Color.decode("#DADADA"));

        // first name label
        JLabel firstNameLabelIR = new JLabel("First Name");
        firstNameLabelIR.setFont(new Font("Monsterrat", Font.BOLD, 15));
        firstNameLabelIR.setBounds(189, 98, 422, 25);
        instrRegPanel.add(firstNameLabelIR);

        // first name field
        firstNameFieldIR = new JTextField(20); // 20 is default -negligible
        firstNameFieldIR.setBounds(189, 123, 198, 45);
        firstNameFieldIR.setBorder(new EmptyBorder(0, 20, 0, 20)); // removes bord.
        firstNameFieldIR.setFont(new Font("Comfortaa", Font.BOLD, 18));
        instrRegPanel.add(firstNameFieldIR);

        // last name label
        JLabel lastNameLabelIR = new JLabel("Last Name");
        lastNameLabelIR.setFont(new Font("Monsterrat", Font.BOLD, 15));
        lastNameLabelIR.setBounds(413, 98, 422, 25);
        instrRegPanel.add(lastNameLabelIR);

        // last name field
        lastNameFieldIR = new JTextField(20); // 20 is default -negligible
        lastNameFieldIR.setBounds(413, 123, 198, 45);
        lastNameFieldIR.setBorder(new EmptyBorder(0, 20, 0, 20)); // removes bord.
        lastNameFieldIR.setFont(new Font("Comfortaa", Font.BOLD, 18));
        instrRegPanel.add(lastNameFieldIR);

        // email label
        JLabel emailLabelIR = new JLabel("Email");
        emailLabelIR.setFont(new Font("Monsterrat", Font.BOLD, 15));
        emailLabelIR.setBounds(189, 185, 422, 25);
        instrRegPanel.add(emailLabelIR);

        // email field
        emailFieldIR = new JTextField(20); // 20 is default -negligible
        emailFieldIR.setBounds(189, 210, 422, 45);
        emailFieldIR.setBorder(new EmptyBorder(0, 20, 0, 20)); // removes bord.
        emailFieldIR.setFont(new Font("Comfortaa", Font.BOLD, 18));
        instrRegPanel.add(emailFieldIR);

        // username label
        JLabel signupUsernameLabelIR = new JLabel("Username");
        signupUsernameLabelIR.setFont(new Font("Monsterrat", Font.BOLD, 15));
        signupUsernameLabelIR.setBounds(189, 272, 422, 25);
        instrRegPanel.add(signupUsernameLabelIR);

        // username field
        userFieldIR = new JTextField(20); // 20 is default -negligible
        userFieldIR.setBounds(189, 297, 422, 45);
        userFieldIR.setBorder(new EmptyBorder(0, 20, 0, 20)); // removes bord.
        userFieldIR.setFont(new Font("Comfortaa", Font.BOLD, 18));
        instrRegPanel.add(userFieldIR);

        // password label
        JLabel signupPasswordLabelIR = new JLabel("Password");
        signupPasswordLabelIR.setFont(new Font("Monsterrat", Font.BOLD, 15));
        signupPasswordLabelIR.setBounds(189, 359, 422, 25);
        instrRegPanel.add(signupPasswordLabelIR);

        // password field
        passFieldIR = new JPasswordField(20); // 20 is default -negligible
        passFieldIR.setBounds(189, 384, 198, 45);
        passFieldIR.setBorder(new EmptyBorder(0, 20, 0, 20)); // removes bord.
        passFieldIR.setFont(new Font("Comfortaa", Font.BOLD, 18));
        instrRegPanel.add(passFieldIR);

        // confirm password label
        JLabel confirmPasswordLabelIR = new JLabel("Password");
        confirmPasswordLabelIR.setFont(new Font("Monsterrat", Font.BOLD, 15));
        confirmPasswordLabelIR.setBounds(189, 359, 422, 25);
        instrRegPanel.add(confirmPasswordLabelIR);

        // confirm password field
        confirmPassFieldIR = new JPasswordField(20); // 20 is default -negligible
        confirmPassFieldIR.setBounds(413, 384, 198, 45);
        confirmPassFieldIR.setBorder(new EmptyBorder(0, 20, 0, 20)); // removes bord.
        confirmPassFieldIR.setFont(new Font("Comfortaa", Font.BOLD, 18));
        instrRegPanel.add(confirmPassFieldIR);

        // register button UPDATED (round button)
        registerButtonIR = roundBtn.genRoundBtn("Register", 50, "#001561", false);
        registerButtonIR.setFont(new Font("Monsterrat", Font.BOLD, 13));
        registerButtonIR.setForeground(Color.decode("#FFFFFF"));
        registerButtonIR.setBounds(189, 450, 422, 50);
        registerButtonIR.addActionListener(this);
        instrRegPanel.add(registerButtonIR);

        // login indicator bar JLabel
        JLabel regIndicatorIR = new JLabel("");
        regIndicatorIR.setIcon(new ImageIcon("images/00301-auth-indicator.png"));
        regIndicatorIR.setBounds(189, 65, 422, 9);
        instrRegPanel.add(regIndicatorIR);

        // Sign In JLabel
        JLabel instrRegText = new JLabel("Instructor Registration", SwingConstants.CENTER);
        instrRegText.setFont(new Font("Monsterrat", Font.BOLD, 16));
        instrRegText.setForeground(Color.decode("#001561"));
        instrRegText.setBounds(189, 25, 422, 47);
        instrRegPanel.add(instrRegText);

        add(loginPanel, "Login"); // adds panel to the card "deck"
        add(signupPanel, "Signup"); // adds panel to the card "deck"
        add(authCodePanel, "AuthCode"); // adds panel to the card "deck"
        add(instrRegPanel, "InstrReg"); // adds panel to the card "deck"

        setVisible(true);
    }

    // button action listener response function
    public void actionPerformed(ActionEvent e) {
        Runnable clearFields = () -> {
            firstNameField.setText("");
            lastNameField.setText("");
            userField.setText("");
            emailField.setText("");
            passField.setText("");
            confirmPassField.setText("");

            usernameField.setText("");
            passcodeField.setText("");
            authField.setText("");
        };
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
            clearFields.run();
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
                    email, password, 0)) { // checks for uniqueness
                System.out.println("Registered successfully"); // console msg

                // console messages for testing
                System.out.println("Name: " + firstName + " " + lastName);
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
        } else if (e.getSource() == back2loginBtn) { // if back button clicked
            // clears all the fields
            clearFields.run();


            // switches the card layout back to log-in panel
            cardLayout.show(getContentPane(), "Login");
        } else if (e.getSource() == returnLoginBtn) {
            // clears all the fields

            clearFields.run();
            // switches the card layout back to log-in panel
            cardLayout.show(getContentPane(), "Login");
        } else if (e.getSource() == authenticateBtn) {
            String inputCode = authField.getText(); // get the input from user
            if(database.validateAuthCode(inputCode)) {
                System.out.println("Success! Valid code.");
                authField.setText("");
                cardLayout.show(getContentPane(), "InstrReg");
            } else {
                System.out.println("Failure. Invalid code.");
            }
        } else if (e.getSource() == registerButtonIR) {
            // gets text from respective JTextField components
            String firstName = firstNameFieldIR.getText();
            String lastName = lastNameFieldIR.getText();
            String username = userFieldIR.getText();
            String email = emailFieldIR.getText();
            String password = new String(passFieldIR.getPassword());
            String confirmPassword = new String(confirmPassFieldIR.getPassword());

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
                    email, password, 2)) { // checks for uniqueness
                System.out.println("Instructor registered successfully"); // console msg

                // console messages for testing
                System.out.println("Name: " + firstName + " " + lastName);
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
        }
    }
}