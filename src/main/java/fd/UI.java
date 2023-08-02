package fd;

// import statements
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import static fd.Database.UserType;

public class UI extends JFrame implements ActionListener {

    // declaring UI components / layout
    private CardLayout cardLayout = new CardLayout();
    private JButton loginButton, signupButton, registerButton, back2loginBtn;
    private JButton registerTransparentBtn, signupTransparentBtn;
    private JButton returnLoginBtn, authenticateBtn, registerButtonIR;
    private JButton viewEntireSchedule, enrolBtn, smthBtn, logout, settings;
    private JTextField usernameField, userField, emailField, authField;
    private JTextField firstNameField, lastNameField, firstNameFieldIR;
    private JTextField lastNameFieldIR, emailFieldIR, userFieldIR;
    private JPasswordField passcodeField, passField, confirmPassField;
    private JPasswordField passFieldIR, confirmPassFieldIR;
    private JPanel loginPanel, signupPanel, authCodePanel, instrRegPanel, mainP;
    private JPanel schedulePanel, enrolPanel, smthPanel, settingsPanel;
    private JLabel haveCodeLabel, welcomeUser, err1, err2, err3, err4;
    private RoundBtn roundBtn = new RoundBtn();
    private RoundField roundField = new RoundField();

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

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                try {
                    database.save();
                }
                catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Failed to write gym!");
                }
            }
        });

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
        usernameField = roundField.genRoundTextField("", 20, "#FFFFFF", false);
        usernameField.setBounds(189, 123, 422, 45);
        usernameField.setFont(new Font("Comfortaa", Font.BOLD, 18));
        loginPanel.add(usernameField);

        // passcode label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
        passwordLabel.setBounds(189, 185, 422, 25);
        loginPanel.add(passwordLabel);

        // passcode field
        passcodeField = roundField.genRoundPasswordField("", 20, "#FFFFFF",
                                                         false);
        passcodeField.setFont(new Font("Arial", Font.PLAIN, 16));
        passcodeField.setBounds(189, 210, 422, 45);
        loginPanel.add(passcodeField);

        // login button UPDATED (round button)
        loginButton = roundBtn.genRoundBtn("Login", 50, "#001561", false);
        loginButton.setFont(new Font("Monsterrat", Font.BOLD, 13));
        loginButton.setForeground(Color.decode("#FFFFFF"));
        loginButton.setBounds(189, 292, 196, 50);
        loginButton.addActionListener(this);
        loginPanel.add(loginButton);

        // signup button UPDATED (round button)
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

        // Register Transparent Button
        registerTransparentBtn = new JButton("");
        registerTransparentBtn.setBounds(400, 27, 211, 47);
        registerTransparentBtn.setOpaque(false);
        registerTransparentBtn.setContentAreaFilled(false);
        registerTransparentBtn.setBorderPainted(false);
        registerTransparentBtn.addActionListener(this);
        // adds a mouse listener
        registerTransparentBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        });
        loginPanel.add(registerTransparentBtn);

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
        firstNameField = roundField.genRoundTextField("", 20, "#FFFFFF", false);
        firstNameField.setBounds(189, 123, 198, 45);
        firstNameField.setFont(new Font("Comfortaa", Font.BOLD, 18));
        signupPanel.add(firstNameField);

        // last name label
        JLabel lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
        lastNameLabel.setBounds(413, 98, 422, 25);
        signupPanel.add(lastNameLabel);

        // last name field
        lastNameField = roundField.genRoundTextField("", 20, "#FFFFFF", false);
        lastNameField.setBounds(413, 123, 198, 45);
        lastNameField.setFont(new Font("Comfortaa", Font.BOLD, 18));
        signupPanel.add(lastNameField);

        // email label
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
        emailLabel.setBounds(189, 185, 422, 25);
        signupPanel.add(emailLabel);

        // email field
        emailField = roundField.genRoundTextField("", 20, "#FFFFFF", false);
        emailField.setBounds(189, 210, 422, 45);
        emailField.setFont(new Font("Comfortaa", Font.BOLD, 18));
        signupPanel.add(emailField);

        // username label
        JLabel signupUsernameLabel = new JLabel("Username");
        signupUsernameLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
        signupUsernameLabel.setBounds(189, 272, 422, 25);
        signupPanel.add(signupUsernameLabel);

        // username field
        userField = roundField.genRoundTextField("", 20, "#FFFFFF", false);
        userField.setBounds(189, 297, 422, 45);
        userField.setFont(new Font("Comfortaa", Font.BOLD, 18));
        signupPanel.add(userField);

        // password label
        JLabel signupPasswordLabel = new JLabel("Password");
        signupPasswordLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
        signupPasswordLabel.setBounds(189, 359, 422, 25);
        signupPanel.add(signupPasswordLabel);

        // password field
        passField = roundField.genRoundPasswordField("", 20, "#FFFFFF", false);
        passField.setBounds(189, 384, 198, 45);
        passField.setFont(new Font("Comfortaa", Font.BOLD, 18));
        signupPanel.add(passField);

        // confirm password label
        JLabel confirmPasswordLabel = new JLabel("Password");
        confirmPasswordLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
        confirmPasswordLabel.setBounds(189, 359, 422, 25);
        signupPanel.add(confirmPasswordLabel);

        // confirm password field
        confirmPassField = roundField.genRoundPasswordField("", 20, "#FFFFFF",
                                                            false);
        confirmPassField.setBounds(413, 384, 198, 45);
        confirmPassField.setFont(new Font("Comfortaa", Font.BOLD, 18));
        signupPanel.add(confirmPassField);

        // back button UPDATED (round button)
        back2loginBtn = roundBtn.genRoundBtn("Back", 50, "#001561", false);
        back2loginBtn.setFont(new Font("Monsterrat", Font.BOLD, 13));
        back2loginBtn.setForeground(Color.decode("#FFFFFF"));
        back2loginBtn.setBounds(189, 450, 196, 50);
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
        JLabel signInTextSU = new JLabel("Sign In", SwingConstants.CENTER);
        signInTextSU.setFont(new Font("Monsterrat", Font.BOLD, 14));
        signInTextSU.setForeground(Color.decode("#000000"));
        signInTextSU.setBounds(189, 27, 211, 47);
        signupPanel.add(signInTextSU);

        // Register Transparent Button
        signupTransparentBtn = new JButton("");
        signupTransparentBtn.setBounds(189, 27, 211, 47);
        signupTransparentBtn.setOpaque(false);
        signupTransparentBtn.setContentAreaFilled(false);
        signupTransparentBtn.setBorderPainted(false);
        signupTransparentBtn.addActionListener(this);
        // adds a mouse listener
        signupTransparentBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        });
        signupPanel.add(signupTransparentBtn);

        // Register JLabel
        JLabel registerTextSU = new JLabel("Register", SwingConstants.CENTER);
        registerTextSU.setFont(new Font("Monsterrat", Font.BOLD, 16));
        registerTextSU.setForeground(Color.decode("#001561"));
        registerTextSU.setBounds(400, 25, 211, 47);
        signupPanel.add(registerTextSU);

        // error messages
        err1 = new JLabel("");
        err1.setFont(new Font("Monsterrat", Font.BOLD, 12));
        err1.setBounds(620, 124, 130, 40);
        signupPanel.add(err1);

        // error messages
        err2 = new JLabel("");
        err2.setFont(new Font("Monsterrat", Font.BOLD, 12));
        err2.setBounds(620, 211, 150, 40);
        signupPanel.add(err2);

        // error messages
        err3 = new JLabel("");
        err3.setFont(new Font("Monsterrat", Font.BOLD, 12));
        err3.setBounds(620, 298, 150, 40);
        signupPanel.add(err3);

        // error messages
        err4 = new JLabel("");
        err4.setFont(new Font("Monsterrat", Font.BOLD, 12));
        err4.setBounds(620, 385, 150, 40);
        signupPanel.add(err4);

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
        authField = roundField.genRoundTextField("", 20, "#FFFFFF", false);
        authField.setBounds(189, 165, 422, 45);
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
        firstNameFieldIR = roundField.genRoundTextField("", 20, "#FFFFFF",
                                                        false);
        firstNameFieldIR.setBounds(189, 123, 198, 45);
        firstNameFieldIR.setFont(new Font("Comfortaa", Font.BOLD, 18));
        instrRegPanel.add(firstNameFieldIR);

        // last name label
        JLabel lastNameLabelIR = new JLabel("Last Name");
        lastNameLabelIR.setFont(new Font("Monsterrat", Font.BOLD, 15));
        lastNameLabelIR.setBounds(413, 98, 422, 25);
        instrRegPanel.add(lastNameLabelIR);

        // last name field
        lastNameFieldIR = roundField.genRoundTextField("", 20, "#FFFFFF",
                                                       false);
        lastNameFieldIR.setBounds(413, 123, 198, 45);
        lastNameFieldIR.setFont(new Font("Comfortaa", Font.BOLD, 18));
        instrRegPanel.add(lastNameFieldIR);

        // email label
        JLabel emailLabelIR = new JLabel("Email");
        emailLabelIR.setFont(new Font("Monsterrat", Font.BOLD, 15));
        emailLabelIR.setBounds(189, 185, 422, 25);
        instrRegPanel.add(emailLabelIR);

        // email field
        emailFieldIR = roundField.genRoundTextField("", 20, "#FFFFFF", false);
        emailFieldIR.setBounds(189, 210, 422, 45);
        emailFieldIR.setFont(new Font("Comfortaa", Font.BOLD, 18));
        instrRegPanel.add(emailFieldIR);

        // username label
        JLabel signupUsernameLabelIR = new JLabel("Username");
        signupUsernameLabelIR.setFont(new Font("Monsterrat", Font.BOLD, 15));
        signupUsernameLabelIR.setBounds(189, 272, 422, 25);
        instrRegPanel.add(signupUsernameLabelIR);

        // username field
        userFieldIR = roundField.genRoundTextField("", 20, "#FFFFFF", false);
        userFieldIR.setBounds(189, 297, 422, 45);
        userFieldIR.setFont(new Font("Comfortaa", Font.BOLD, 18));
        instrRegPanel.add(userFieldIR);

        // password label
        JLabel signupPasswordLabelIR = new JLabel("Password");
        signupPasswordLabelIR.setFont(new Font("Monsterrat", Font.BOLD, 15));
        signupPasswordLabelIR.setBounds(189, 359, 422, 25);
        instrRegPanel.add(signupPasswordLabelIR);

        // password field
        passFieldIR = roundField.genRoundPasswordField("", 20, "#FFFFFF",
                                                       false);
        passFieldIR.setBounds(189, 384, 198, 45);
        passFieldIR.setFont(new Font("Comfortaa", Font.BOLD, 18));
        instrRegPanel.add(passFieldIR);

        // confirm password label
        JLabel confirmPasswordLabelIR = new JLabel("Password");
        confirmPasswordLabelIR.setFont(new Font("Monsterrat", Font.BOLD, 15));
        confirmPasswordLabelIR.setBounds(189, 359, 422, 25);
        instrRegPanel.add(confirmPasswordLabelIR);

        // confirm password field
        confirmPassFieldIR = roundField.genRoundPasswordField("", 20, "#FFFFFF",
                                                              false);
        confirmPassFieldIR.setBounds(413, 384, 198, 45);
        confirmPassFieldIR.setFont(new Font("Comfortaa", Font.BOLD, 18));
        instrRegPanel.add(confirmPassFieldIR);

        // register button UPDATED (round button)
        registerButtonIR = roundBtn.genRoundBtn("Register", 50, "#001561",
                                                false);
        registerButtonIR.setFont(new Font("Monsterrat", Font.BOLD, 13));
        registerButtonIR.setForeground(Color.decode("#FFFFFF"));
        registerButtonIR.setBounds(189, 450, 422, 50);
        registerButtonIR.addActionListener(this);
        instrRegPanel.add(registerButtonIR);

        // login indicator bar JLabel
        JLabel regIndicatorIR = new JLabel("");
        String tempImg1 = "images/00301-auth-indicator.png";
        regIndicatorIR.setIcon(new ImageIcon(tempImg1));
        regIndicatorIR.setBounds(189, 65, 422, 9);
        instrRegPanel.add(regIndicatorIR);

        // Sign In JLabel
        JLabel instrRegText = new JLabel("Instructor Registration",
                                         SwingConstants.CENTER);
        instrRegText.setFont(new Font("Monsterrat", Font.BOLD, 16));
        instrRegText.setForeground(Color.decode("#001561"));
        instrRegText.setBounds(189, 25, 422, 47);
        instrRegPanel.add(instrRegText);

        // main logged-in view panel
        mainP = new JPanel();
        mainP.setLayout(null);
        mainP.setBackground(Color.decode("#DADADA"));

        JLabel dashboardTitle = new JLabel("Dashboard");
        dashboardTitle.setFont(new Font("Monsterrat", Font.BOLD, 28));
        dashboardTitle.setForeground(Color.decode("#001561"));
        dashboardTitle.setBounds(64, 25, 422, 47);
        mainP.add(dashboardTitle);

        welcomeUser = new JLabel("");
        welcomeUser.setFont(new Font("Monsterrat", Font.BOLD, 20));
        welcomeUser.setForeground(Color.decode("#001561"));
        welcomeUser.setBounds(66, 64, 422, 47);
        mainP.add(welcomeUser);

        JLabel upcomingC = new JLabel("Upcoming Classes");
        upcomingC.setFont(new Font("Monsterrat", Font.BOLD, 20));
        upcomingC.setForeground(Color.decode("#000000"));
        upcomingC.setBounds(66, 130, 422, 47);
        mainP.add(upcomingC);

        JLabel upcomingC1 = new JLabel("Class 1 Info");
        upcomingC1.setFont(new Font("Monsterrat", Font.BOLD, 20));
        upcomingC1.setForeground(Color.decode("#FFFFFF"));
        upcomingC1.setBackground(Color.decode("#40508a"));
        upcomingC1.setOpaque(true);
        upcomingC1.setBounds(66, 180, 320, 60);
        mainP.add(upcomingC1);

        JLabel upcomingC2 = new JLabel("Class 2 Info");
        upcomingC2.setFont(new Font("Monsterrat", Font.BOLD, 20));
        upcomingC2.setForeground(Color.decode("#FFFFFF"));
        upcomingC2.setBackground(Color.decode("#40508a"));
        upcomingC2.setOpaque(true);
        upcomingC2.setBounds(66, 275, 320, 60);
        upcomingC2.setOpaque(true);
        mainP.add(upcomingC2);

        JLabel upcomingC3 = new JLabel("Class 3 Info");
        upcomingC3.setFont(new Font("Monsterrat", Font.BOLD, 20));
        upcomingC3.setForeground(Color.decode("#FFFFFF"));
        upcomingC3.setBackground(Color.decode("#40508a"));
        upcomingC3.setOpaque(true);
        upcomingC3.setBounds(66, 370, 320, 60);
        mainP.add(upcomingC3);

        viewEntireSchedule = roundBtn.genRoundBtn("View Entire Schedule", 30,
                                                  "#001561", false);
        viewEntireSchedule.setFont(new Font("Monsterrat", Font.BOLD, 20));
        viewEntireSchedule.setForeground(Color.decode("#FFFFFF"));
        viewEntireSchedule.setBounds(66, 445, 320, 40);
        viewEntireSchedule.addActionListener(this);
        mainP.add(viewEntireSchedule);

        settings = roundBtn.genRoundBtn("⚙", 30, "#001561", false);
        settings.setFont(new Font("Monsterrat", Font.BOLD, 24));
        settings.setForeground(Color.decode("#FFFFFF"));
        settings.setBounds(608, 12, 48, 32);
        settings.addActionListener(this);
        mainP.add(settings);

        JLabel viewType = new JLabel("User View");
        viewType.setFont(new Font("Monsterrat", Font.BOLD, 20));
        viewType.setForeground(Color.decode("#001561"));
        viewType.setBounds(670, 16, 150, 26);
        mainP.add(viewType);

        enrolBtn = roundBtn.genRoundBtn("Enroll in Classes", 30, "#001561",
                                        false);
        enrolBtn.setFont(new Font("Monsterrat", Font.BOLD, 23));
        enrolBtn.setForeground(Color.decode("#FFFFFF"));
        enrolBtn.setBounds(490, 180, 218, 100);
        enrolBtn.addActionListener(this);
        mainP.add(enrolBtn);

        smthBtn = roundBtn.genRoundBtn("Smth", 30, "#001561", false);
        smthBtn.setFont(new Font("Monsterrat", Font.BOLD, 23));
        smthBtn.setForeground(Color.decode("#FFFFFF"));
        smthBtn.setBounds(490, 300, 218, 100);
        smthBtn.addActionListener(this);
        mainP.add(smthBtn);

        logout = roundBtn.genRoundBtn("Logout", 30, "#001561", false);
        logout.setFont(new Font("Monsterrat", Font.BOLD, 23));
        logout.setForeground(Color.decode("#FFFFFF"));
        logout.setBounds(490, 420, 218, 64);
        logout.addActionListener(this);
        mainP.add(logout);

        // settings panel
        settingsPanel = new JPanel();
        settingsPanel.setLayout(null);
        settingsPanel.setBackground(Color.decode("#DADADA"));

        // Login panel
        schedulePanel = new JPanel();
        schedulePanel.setLayout(null);
        schedulePanel.setBackground(Color.decode("#DADADA"));

        // Login panel
        enrolPanel = new JPanel();
        enrolPanel.setLayout(null);
        enrolPanel.setBackground(Color.decode("#DADADA"));

        // Login panel
        smthPanel = new JPanel();
        smthPanel.setLayout(null);
        smthPanel.setBackground(Color.decode("#DADADA"));

        add(loginPanel, "Login"); // adds panel to the card "deck"
        add(signupPanel, "Signup"); // adds panel to the card "deck"
        add(authCodePanel, "AuthCode"); // adds panel to the card "deck"
        add(instrRegPanel, "InstrReg"); // adds panel to the card "deck"
        add(mainP, "main"); // adds panel to the card "deck"
        add(settingsPanel, "settings"); // adds panel to the card "deck"
        add(schedulePanel, "schedule"); // adds panel to the card "deck"
        add(enrolPanel, "enrolment"); // adds panel to the card "deck"
        add(smthPanel, "something"); // adds panel to the card "deck"

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
                usernameField.setText("");
                passcodeField.setText("");
                welcomeUser.setText("Welcome back, " +
                                    database.activeUser.firstName + "!");
                cardLayout.show(getContentPane(), "main");
            } else {
                System.out.println("Invalid credentials"); // failed console msg
            }

        } else if ((e.getSource() == signupButton) ||
                   (e.getSource() == registerTransparentBtn)) { // signup logic
            cardLayout.show(getContentPane(), "Signup"); // switches to SU panel
            // resets fields (signup screen)
            firstNameField.setText("");
            lastNameField.setText("");
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
            err1.setText("");
            err2.setText("");
            err3.setText("");
            err4.setText("");

            boolean check = true;

            if (firstName.isEmpty() || lastName.isEmpty()) {
                System.out.println("First and last name fields " +
                        "must not be empty");
                err1.setText("<HTML>*Names cannot be<BR> left blank</HTML>");
                check = false;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                System.out.println("Invalid email format");
                err2.setText("*Invalid email format");
                check = false;
            }

            if (username.length() < 3) {
                System.out.println("Username must be at least " +
                        "3 characters long");
                err3.setText("<html>*Usernames must be<BR>at least 3 characters"
                             + "</html>");
                check = false;
            }

            if (password.isEmpty()) {
                System.out.println("*Passwords cannot be left blank");
                err4.setText("<HTML>*Passwords cannot be<BR>left blank</HTML>");
                check = false;
            } else if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match, try again");
                err4.setText("*Passwords must match");
                check = false;
            }

            if (!check) {
                return;
            }

            if(database.register(firstName, lastName, username,
                    email, password, UserType.unregistered, null)) { // uniqueness
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
                err3.setText("<HTML>*Username already<BR>exists<HTML>");
                System.out.println("Invalid input"); // F console msg
            }
        } else if ((e.getSource() == back2loginBtn) ||
                   (e.getSource() == signupTransparentBtn)) { // if back button
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
            authField.setText("");

            // switches the card layout back to log-in panel
            cardLayout.show(getContentPane(), "Login");
        } else if (e.getSource() == authenticateBtn) {
            String inputCode = authField.getText(); // get the input from user
            if (database.validateAuthCode(inputCode) != null) {
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
            String confirmPass = new String(confirmPassFieldIR.getPassword());
            err1.setText("");
            err2.setText("");
            err3.setText("");
            err4.setText("");

            boolean check = true;

            if (firstName.isEmpty() || lastName.isEmpty()) {
                System.out.println("First and last name fields " +
                        "must not be empty");
                err1.setText("<HTML>*Names cannot be<BR> left blank</HTML>");
                check = false;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                System.out.println("Invalid email format");
                err2.setText("*Invalid email format");
                check = false;
            }

            if (username.length() < 3) {
                System.out.println("Username must be at least " +
                        "3 characters long");
                err3.setText("<html>*Usernames must be<BR>at least 3 " +
                             "characters</html>");
                check = false;
            }

            if (password.isEmpty()) {
                System.out.println("*Passwords cannot be left blank");
                err4.setText("<HTML>*Passwords cannot be<BR>left blank</HTML>");
            } else if (!password.equals(confirmPass)) {
                System.out.println("Passwords do not match, try again");
                err4.setText("*Passwords must match");
                check = false;
            }

            if (!check) {
                return;
            }

            if (database.register(firstName, lastName, username,
                    email, password, UserType.instructor, authField.getText())) { // uniqueness
                System.out.println("Instructor registered successfully");
                // console messages for testing
                System.out.println("Name: " + firstName + " " + lastName);
                System.out.println("Username: " + username);
                System.out.println("Email: " + email);
                System.out.println("Password: " + password);
                System.out.println("Confirm Password: " + confirmPass);

                // Switch the card layout back to the login panel + reset JTFs
                usernameField.setText("");
                passcodeField.setText("");
                cardLayout.show(getContentPane(), "Login");
            } else {
                err3.setText("<HTML>*Username already<BR>exists<HTML>");
                System.out.println("Username already exists"); // F console msg
            }
        } else if (e.getSource() == viewEntireSchedule) {
            cardLayout.show(getContentPane(), "schedule");
        } else if (e.getSource() == enrolBtn) {
            cardLayout.show(getContentPane(), "enrolment");
        } else if (e.getSource() == smthBtn) {
            cardLayout.show(getContentPane(), "something");
        } else if (e.getSource() == settings) {
            cardLayout.show(getContentPane(), "settings");
        } else if (e.getSource() == logout) {
            System.out.println("Logged out successfully");
            cardLayout.show(getContentPane(), "Login");
        }
    }
}
