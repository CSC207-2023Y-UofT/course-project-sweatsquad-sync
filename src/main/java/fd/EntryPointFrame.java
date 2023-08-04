package fd;

import ebr.GymAdmin;
import ebr.Instructor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EntryPointFrame extends JFrame implements ActionListener {
    private class LoginPanel extends JPanel implements ActionListener {
        private JButton loginButton;
        private JTextField usernameField;
        private JPasswordField passcodeField;
        LoginPanel() {
            this.setLayout(null); // layout form, switches between cards
            this.setBackground(Color.decode("#DADADA"));

            JLabel usernameLabel = new JLabel("Username:");
            usernameLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
            usernameLabel.setBounds(189, 98, 422, 25);
            this.add(usernameLabel);

            usernameField = UI.genRoundTextField("", 20, "#FFFFFF", false);
            usernameField.setBounds(189, 123, 422, 45);
            usernameField.setFont(new Font("Comfortaa", Font.BOLD, 18));
            this.add(usernameField);

            JLabel passwordLabel = new JLabel("Password:");
            passwordLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
            passwordLabel.setBounds(189, 185, 422, 25);
            this.add(passwordLabel);

            passcodeField = UI.genRoundPasswordField("", 20, "#FFFFFF",
                    false);
            passcodeField.setFont(new Font("Arial", Font.PLAIN, 16));
            passcodeField.setBounds(189, 210, 422, 45);
            this.add(passcodeField);

            loginButton = UI.genRoundBtn("Login", 50, "#001561", false);
            loginButton.setFont(new Font("Monsterrat", Font.BOLD, 13));
            loginButton.setForeground(Color.decode("#FFFFFF"));
            loginButton.setBounds(189, 292, 422, 50);
            loginButton.addActionListener(this);
            this.add(loginButton);
        }

        public void onLogin() {
            String username = usernameField.getText().trim();
            usernameField.setText(username);
            String password = new String(passcodeField.getPassword());

            if (App.db.validateLogin(username, password)) {
//            welcomeUser.setText("Welcome back, " + App.db.activeUser.firstName + "!");
                if (App.db.activeUser instanceof Instructor) {
//                viewType.setText("Instructor View");
//                instructorDash.add(viewType);
//                viewType.setBounds(615, 16, 160, 26);
//                instructorDash.add(welcomeUser);
//                instructorDash.add(dashboardTitle);
//                instructorDash.add(logout_icon);
//                logout_icon.setBounds(565, 15, 40, 30);
//                instructorDash.add(logout);
//                logout.setBounds(565, 15, 40, 30);
//                instructorDash.add(dailyWorkoutTips);
//                dailyWorkoutTips.setBounds(490, 295, 218, 75);
//                instructorDash.add(settings);
//                settings.setBounds(490, 410, 218, 75);
//                activeDash = "Instructor Dashboard";
                } else if (App.db.activeUser instanceof GymAdmin) {
//                viewType.setText("Admin View");
//                activeDash = "Admin Dashboard";
                } else {
//                viewType.setText("User View");
//                userDash.add(viewType);
//                userDash.add(welcomeUser);
//                userDash.add(dashboardTitle);
//                userDash.add(logout);
//                userDash.add(dailyWorkoutTips);
//                dailyWorkoutTips.setBounds(174, 230, 452, 60);
//                userDash.add(settings);
//                settings.setBounds(174, 330, 452, 60);
//                activeDash = "User Dashboard";
                }
//            cardLayout.show(getContentPane(), activeDash);
            }
            else
                JOptionPane.showMessageDialog(this, "Invalid credentials");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginButton)
                onLogin();
        }
    }

    private class SignupPanel extends JPanel implements ActionListener {
        private JTextField usernameField, firstNameField, lastNameField, emailField, userField;
        private JPasswordField passField, confirmPassField;
        private JButton registerButton;
        SignupPanel() {
            this.setLayout(null);
            this.setBackground(Color.decode("#DADADA"));

            JLabel firstNameLabel = new JLabel("First Name");
            firstNameLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
            firstNameLabel.setBounds(189, 98, 422, 25);
            this.add(firstNameLabel);

            firstNameField = UI.genRoundTextField("", 20, "#FFFFFF", false);
            firstNameField.setBounds(189, 123, 198, 45);
            firstNameField.setFont(new Font("Comfortaa", Font.BOLD, 18));
            this.add(firstNameField);

            JLabel lastNameLabel = new JLabel("Last Name");
            lastNameLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
            lastNameLabel.setBounds(413, 98, 422, 25);
            this.add(lastNameLabel);

            lastNameField = UI.genRoundTextField("", 20, "#FFFFFF", false);
            lastNameField.setBounds(413, 123, 198, 45);
            lastNameField.setFont(new Font("Comfortaa", Font.BOLD, 18));
            this.add(lastNameField);

            JLabel emailLabel = new JLabel("Email");
            emailLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
            emailLabel.setBounds(189, 185, 422, 25);
            this.add(emailLabel);

            emailField = UI.genRoundTextField("", 20, "#FFFFFF", false);
            emailField.setBounds(189, 210, 422, 45);
            emailField.setFont(new Font("Comfortaa", Font.BOLD, 18));
            this.add(emailField);

            JLabel signupUsernameLabel = new JLabel("Username");
            signupUsernameLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
            signupUsernameLabel.setBounds(189, 272, 422, 25);
            this.add(signupUsernameLabel);

            userField = UI.genRoundTextField("", 20, "#FFFFFF", false);
            userField.setBounds(189, 297, 422, 45);
            userField.setFont(new Font("Comfortaa", Font.BOLD, 18));
            this.add(userField);

            JLabel signupPasswordLabel = new JLabel("Password");
            signupPasswordLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
            signupPasswordLabel.setBounds(189, 359, 422, 25);
            this.add(signupPasswordLabel);

            passField = UI.genRoundPasswordField("", 20, "#FFFFFF", false);
            passField.setBounds(189, 384, 198, 45);
            passField.setFont(new Font("Comfortaa", Font.BOLD, 18));
            this.add(passField);

            JLabel confirmPasswordLabel = new JLabel("Password");
            confirmPasswordLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
            confirmPasswordLabel.setBounds(189, 359, 422, 25);
            this.add(confirmPasswordLabel);

            confirmPassField = UI.genRoundPasswordField("", 20, "#FFFFFF", false);
            confirmPassField.setBounds(413, 384, 198, 45);
            confirmPassField.setFont(new Font("Comfortaa", Font.BOLD, 18));
            this.add(confirmPassField);

            registerButton = UI.genRoundBtn("Register", 50, "#001561", false);
            registerButton.setFont(new Font("Monsterrat", Font.BOLD, 13));
            registerButton.setForeground(Color.decode("#FFFFFF"));
            registerButton.setBounds(413, 450, 196, 50);
            registerButton.addActionListener(this);
            this.add(registerButton);

            JLabel haveCodeLabel = new JLabel("<HTML><U>I have a code</U></HTML>");
            haveCodeLabel.setForeground(Color.BLUE);
            haveCodeLabel.setFont(new Font("Comfortaa", Font.BOLD, 13));
            haveCodeLabel.setBounds(350, 514, 200, 30);
            haveCodeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            haveCodeLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // clear all the fields
//                    usernameField.setText("");
//                    passcodeField.setText("");
//                    userField.setText("");
//                    emailField.setText("");
//                    passField.setText("");
//                    confirmPassField.setText("");
//
//                    // switch the card layout back to the login panel
//                    cardLayout.show(getContentPane(), "AuthCode");
                }
            });
            this.add(haveCodeLabel);
        }

        @Override
        public void actionPerformed(ActionEvent e) {

//        if (e.getSource() == registerButton) { // register logic
//            // gets text from respective JTextField components
//            String firstName = capitalizeRemoveTrailingSpaces(firstNameField.getText());
//            String lastName = capitalizeRemoveTrailingSpaces(lastNameField.getText());
//            String username = userField.getText().trim();
//            userField.setText(username);
//            String email = emailField.getText().trim();
//            emailField.setText(email);
//            String password = new String(passField.getPassword());
//            String confirmPassword = new String(confirmPassField.getPassword());
//            err1.setText("");
//            err2.setText("");
//            err3.setText("");
//            err4.setText("");
//
//            boolean check = true;
//
//            if (firstName.isEmpty() || lastName.isEmpty()) {
//                System.out.println("First and last name fields " +
//                        "must not be empty");
//                err1.setText("<HTML>*Names cannot be<BR> left blank</HTML>");
//                check = false;
//            }
//
//            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
//                System.out.println("Invalid email format");
//                err2.setText("*Invalid email format");
//                check = false;
//            }
//
//            if (username.length() < 3) {
//                System.out.println("Username must be at least " +
//                        "3 characters long");
//                err3.setText("<html>*Usernames must be<BR>at least 3 characters"
//                        + "</html>");
//                check = false;
//            }
//
//            if (password.isEmpty()) {
//                System.out.println("*Passwords cannot be left blank");
//                err4.setText("<HTML>*Passwords cannot be<BR>left blank</HTML>");
//                check = false;
//            } else if (!password.equals(confirmPassword)) {
//                System.out.println("Passwords do not match, try again");
//                err4.setText("*Passwords must match");
//                check = false;
//            }
//
//            if (!check) {
//                return;
//            }
//
//            if(database.registerBasicUser(firstName, lastName, username,
//                    email, password)) { // uniqueness
//                System.out.println("Registered successfully"); // console msg
//
//                // console messages for testing
//                System.out.println("Name: " + firstName + " " + lastName);
//                System.out.println("Username: " + username);
//                System.out.println("Email: " + email);
//                System.out.println("Password: " + password);
//                System.out.println("Confirm Password: " + confirmPassword);
//
//                // Switch the card layout back to the login panel + reset JTFs
//                usernameField.setText("");
//                passcodeField.setText("");
//                cardLayout.show(getContentPane(), "Login");
//            } else {
//                err3.setText("<HTML>*Username already<BR>exists<HTML>");
//                System.out.println("Invalid input"); // F console msg
//            }
//        }
        }
    }

//    private JPanel authCodePanel() {
//        JPanel authCodePanel = new JPanel();
//        authCodePanel.setLayout(null);
//        authCodePanel.setBackground(Color.decode("#DADADA"));
//
//        JLabel authCodeLabel = new JLabel("Enter Authentication Code                   (16 Characters)");
//        authCodeLabel.setBounds(189, 140, 422, 25);
//        authCodeLabel.setFont(new Font("Monsterrat", Font.BOLD, 15));
//        authCodePanel.add(authCodeLabel);
//
//        authField = UI.genRoundTextField("", 20, "#FFFFFF", false);
//        authField.setBounds(189, 165, 422, 45);
//        authField.setFont(new Font("Comfortaa", Font.BOLD, 18));
//        authField.setHorizontalAlignment(SwingConstants.CENTER); // c-align
//        authCodePanel.add(authField);
//
//        returnLoginBtn = UI.genRoundBtn("Return", 50, "#001561", false);
//        returnLoginBtn.setFont(new Font("Monsterrat", Font.BOLD, 13));
//        returnLoginBtn.setForeground(Color.decode("#FFFFFF"));
//        returnLoginBtn.setBounds(189, 292, 196, 50);
//        returnLoginBtn.addActionListener(this);
//        authCodePanel.add(returnLoginBtn);
//
//        authenticateBtn = UI.genRoundBtn("Authenticate", 50, "#001561", false);
//        authenticateBtn.setFont(new Font("Monsterrat", Font.BOLD, 13));
//        authenticateBtn.setForeground(Color.decode("#FFFFFF"));
//        authenticateBtn.setBounds(413, 292, 196, 50);
//        authenticateBtn.addActionListener(this);
//        authCodePanel.add(authenticateBtn);
//
//        JLabel indicatorBar = new JLabel("");
//        indicatorBar.setIcon(new ImageIcon("images/00301-auth-indicator.png"));
//        indicatorBar.setBounds(189, 65, 422, 9);
//        authCodePanel.add(indicatorBar);
//
//        JLabel authLabelBig = new JLabel("Instructor Authentication", SwingConstants.CENTER);
//        authLabelBig.setFont(new Font("Monsterrat", Font.BOLD, 16));
//        authLabelBig.setForeground(Color.decode("#001561"));
//        authLabelBig.setBounds(189, 25, 422, 47);
//        authCodePanel.add(authLabelBig);
//        return authCodePanel;
//    }

    private MouseAdapter cursorHover = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setCursor(Cursor.getDefaultCursor());
        }
    };

    private JButton loginTab, signupTab;
    private JLabel signIndicator, signInTextLI, registerTextLI;
    private CardLayout cardLayout = new CardLayout();
    private JPanel cards = new JPanel(cardLayout);
    public EntryPointFrame() {
        setTitle("Login"); // window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ends program if x
        setSize(800, 600); // window dimensions
        setResizable(false); // disables resizing
        setLocationRelativeTo(null); // centers frame
        setLayout(null);

        loginTab = new JButton("");
        loginTab.setBounds(180, 27, 225, 50);
        loginTab.addActionListener(this);
        loginTab.setOpaque(false);
        loginTab.setContentAreaFilled(false);
        loginTab.setBorderPainted(false);
        loginTab.setFocusPainted(false);

        loginTab.setFocusable(false);

        this.add(loginTab);

        signupTab = new JButton();
        signupTab.setBounds(395, 27, 225, 50);
        signupTab.addActionListener(this);
        signupTab.setOpaque(false);
        signupTab.setContentAreaFilled(false);
        signupTab.setBorderPainted(false);
        signupTab.setFocusPainted(false);

        signupTab.addMouseListener(cursorHover);

        this.add(signupTab);

        signIndicator = new JLabel("");
        signIndicator.setIcon(new ImageIcon("images/00101-sign-indicator.png"));
        signIndicator.setBounds(189, 65, 422, 9);
        this.add(signIndicator);

        signInTextLI = new JLabel("Sign In", SwingConstants.CENTER);
        signInTextLI.setFont(new Font("Monsterrat", Font.BOLD, 16));
        signInTextLI.setForeground(Color.decode("#001561"));
        signInTextLI.setBounds(189, 25, 211, 47);
        this.add(signInTextLI);

        registerTextLI = new JLabel("Register", SwingConstants.CENTER);
        registerTextLI.setFont(new Font("Monsterrat", Font.BOLD, 14));
        registerTextLI.setForeground(Color.decode("#000000"));
        registerTextLI.setBounds(400, 27, 211, 47);
        this.add(registerTextLI);

        cards.add(new LoginPanel(), "Login");
        cards.add(new SignupPanel(), "Signup");
        cards.setBounds(0, 0, 800, 700);
        //add(authCodePanel(), "AuthCode"); // adds panel to the card "deck"
        cardLayout.show(cards, "Login");
        this.add(cards);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginTab) {
            cardLayout.show(cards, "Login");
            this.add(signupTab);
            this.add(loginTab);

            loginTab.setFocusable(false);
            loginTab.removeMouseListener(cursorHover);
            signupTab.setFocusable(true);
            signupTab.addMouseListener(cursorHover);

            registerTextLI.setFont(new Font("Monsterrat", Font.BOLD, 14));
            registerTextLI.setForeground(Color.decode("#000000"));
            signIndicator.setIcon(new ImageIcon("images/00101-sign-indicator.png"));
            signInTextLI.setFont(new Font("Monsterrat", Font.BOLD, 16));
            signInTextLI.setForeground(Color.decode("#001561"));

        }
        else if (e.getSource() == signupTab) {
            cardLayout.show(cards, "Signup");
            this.add(signupTab);
            this.add(loginTab);

            signupTab.setFocusable(false);
            signupTab.removeMouseListener(cursorHover);
            loginTab.setFocusable(true);
            loginTab.addMouseListener(cursorHover);

            signIndicator.setIcon(new ImageIcon("images/00201-reg-indicator.png"));
            registerTextLI.setFont(new Font("Monsterrat", Font.BOLD, 16));
            registerTextLI.setForeground(Color.decode("#001561"));
            signInTextLI.setFont(new Font("Monsterrat", Font.BOLD, 14));
            signInTextLI.setForeground(Color.decode("#000000"));
        }
//        else if (e.getSource() == authenticateBtn) {
//            String inputCode = authField.getText(); // get the input from user
//            if (database.validateAuthCode(inputCode) != null) {
//                System.out.println("Success! Valid code.");
//                cardLayout.show(getContentPane(), "InstrReg");
//            } else {
//                System.out.println("Failure. Invalid code.");
//            }
//        }
//        else if (e.getSource() == registerButtonIR) {
//            // gets text from respective JTextField components
//            String firstName = capitalizeRemoveTrailingSpaces(firstNameFieldIR.getText());
//            String lastName = capitalizeRemoveTrailingSpaces(lastNameFieldIR.getText());
//            String username = userFieldIR.getText().trim();
//            userFieldIR.setText(username);
//            String email = emailFieldIR.getText().trim();
//            emailFieldIR.setText(email);
//            String password = new String(passFieldIR.getPassword());
//            String confirmPass = new String(confirmPassFieldIR.getPassword());
//            err1.setText("");
//            err2.setText("");
//            err3.setText("");
//            err4.setText("");
//
//            boolean check = true;
//
//            if (firstName.isEmpty() || lastName.isEmpty()) {
//                System.out.println("First and last name fields " +
//                        "must not be empty");
//                err1.setText("<HTML>*Names cannot be<BR> left blank</HTML>");
//                check = false;
//            }
//
//            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
//                System.out.println("Invalid email format");
//                err2.setText("*Invalid email format");
//                check = false;
//            }
//
//            if (username.length() < 3) {
//                System.out.println("Username must be at least " +
//                        "3 characters long");
//                err3.setText("<html>*Usernames must be<BR>at least 3 " +
//                        "characters</html>");
//                check = false;
//            }
//
//            if (password.isEmpty()) {
//                System.out.println("*Passwords cannot be left blank");
//                err4.setText("<HTML>*Passwords cannot be<BR>left blank</HTML>");
//            } else if (!password.equals(confirmPass)) {
//                System.out.println("Passwords do not match, try again");
//                err4.setText("*Passwords must match");
//                check = false;
//            }
//
//            if (!check) {
//                return;
//            }
//
//
//
//            if (database.claimInstructor(database.validateAuthCode(authField.getText()), firstName, lastName, username,
//                    email, password)) { // uniqueness
//                authField.setText("");
//                System.out.println("Instructor registered successfully");
//                // console messages for testing
//                System.out.println("Name: " + firstName + " " + lastName);
//                System.out.println("Username: " + username);
//                System.out.println("Email: " + email);
//                System.out.println("Password: " + password);
//                System.out.println("Confirm Password: " + confirmPass);
//
//                // Switch the card layout back to the login panel + reset JTFs
//                usernameField.setText("");
//                passcodeField.setText("");
//                cardLayout.show(getContentPane(), "Login");
//            } else {
//                err3.setText("<HTML>*Username already<BR>exists<HTML>");
//                System.out.println("Username already exists"); // F console msg
//            }
//        }
    }
}
