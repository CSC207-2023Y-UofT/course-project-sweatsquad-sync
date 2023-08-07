package fd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class SignupPanel extends JPanel implements ActionListener {
    private JTextField firstNameField, lastNameField, emailField, userField;
    private JPasswordField passField, confirmPassField;
    private JButton registerButton;
    private JLabel err1, err2, err3, err4, haveCodeLabel;

    @FunctionalInterface
    public interface RegisterMethod {
        public boolean apply(String firstName, String lastName, String username, String email, String password);
    }
    private RegisterMethod registerDispatch;

    SignupPanel() {
        registerDispatch = App.db::registerBasicUser;

        this.setLayout(null);
        this.setOpaque(false);

        JLabel firstNameLabel = new JLabel("First Name");
        firstNameLabel.setFont(UI.MB15);
        firstNameLabel.setBounds(189, 98, 422, 25);
        this.add(firstNameLabel);

        firstNameField = UI.genRoundTextField("", 20, "#FFFFFF", false);
        firstNameField.setBounds(189, 123, 198, 45);
        firstNameField.setFont(UI.CB18);
        this.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setFont(UI.MB15);
        lastNameLabel.setBounds(413, 98, 422, 25);
        this.add(lastNameLabel);

        lastNameField = UI.genRoundTextField("", 20, "#FFFFFF", false);
        lastNameField.setBounds(413, 123, 198, 45);
        lastNameField.setFont(UI.CB18);
        this.add(lastNameField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(UI.MB15);
        emailLabel.setBounds(189, 185, 422, 25);
        this.add(emailLabel);

        emailField = UI.genRoundTextField("", 20, "#FFFFFF", false);
        emailField.setBounds(189, 210, 422, 45);
        emailField.setFont(UI.CB18);
        this.add(emailField);

        JLabel signupUsernameLabel = new JLabel("Username");
        signupUsernameLabel.setFont(UI.MB15);
        signupUsernameLabel.setBounds(189, 272, 422, 25);
        this.add(signupUsernameLabel);

        userField = UI.genRoundTextField("", 20, "#FFFFFF", false);
        userField.setBounds(189, 297, 422, 45);
        userField.setFont(UI.CB18);
        this.add(userField);

        JLabel signupPasswordLabel = new JLabel("Password");
        signupPasswordLabel.setFont(UI.MB15);
        signupPasswordLabel.setBounds(189, 359, 422, 25);
        this.add(signupPasswordLabel);

        passField = UI.genRoundPasswordField("", 20, "#FFFFFF", false);
        passField.setBounds(189, 384, 198, 45);
        passField.setFont(UI.CB18);
        this.add(passField);

        JLabel confirmPasswordLabel = new JLabel("Password");
        confirmPasswordLabel.setFont(UI.MB15);
        confirmPasswordLabel.setBounds(189, 359, 422, 25);
        this.add(confirmPasswordLabel);

        confirmPassField = UI.genRoundPasswordField("", 20, "#FFFFFF", false);
        confirmPassField.setBounds(413, 384, 198, 45);
        confirmPassField.setFont(UI.CB18);
        this.add(confirmPassField);

        registerButton = UI.genRoundBtn("Register", 50, "#001561", false);
        registerButton.setFont(UI.MB13);
        registerButton.setForeground(Color.decode("#FFFFFF"));
        registerButton.setBounds(189, 450, 422, 50);
        registerButton.addActionListener(this);
        this.add(registerButton);

        haveCodeLabel = new JLabel("<HTML><U>I have a code</U></HTML>");
        haveCodeLabel.setForeground(Color.BLUE);
        haveCodeLabel.setFont(UI.CB13);
        haveCodeLabel.setBounds(350, 514, 200, 30);
        haveCodeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        haveCodeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                App.entry.authCodeCard();
            }
        });
        this.add(haveCodeLabel);

        err1 = new JLabel("");
        err1.setFont(UI.MB12);
        err1.setBounds(620, 124, 130, 40);
        this.add(err1);
        err2 = new JLabel("");
        err2.setFont(UI.MB12);
        err2.setBounds(620, 211, 150, 40);
        this.add(err2);
        err3 = new JLabel("");
        err3.setFont(UI.MB12);
        err3.setBounds(620, 298, 150, 40);
        this.add(err3);
        err4 = new JLabel("");
        err4.setFont(UI.MB12);
        err4.setBounds(620, 385, 150, 40);
        this.add(err4);
    }

    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        userField.setText("");
        passField.setText("");
        confirmPassField.setText("");
        for (JLabel l : new JLabel[]{err1,err2,err3,err4})
            l.setText("");
    }

    public void reset() {
        clearFields();
        registerDispatch = App.db::registerBasicUser;
        this.add(haveCodeLabel);
    }

    public void setInstructorView(String auth) {
        setRegisterDispatch((fn, ln, un, em, pw) -> App.db.claimInstructor(auth, fn, ln, un, em, pw));
        this.remove(haveCodeLabel);
    }

    public void setAdminView() {
        setRegisterDispatch(App.db::registerAdmin);
        this.remove(haveCodeLabel);
    }

    public void setRegisterDispatch(RegisterMethod f) {
        registerDispatch = f;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) { // register logic
            String firstName = UI.capitalizeRemoveTrailingSpaces(firstNameField.getText()),
                    lastName = UI.capitalizeRemoveTrailingSpaces(lastNameField.getText()),
                    username = userField.getText().trim(),
                    email = emailField.getText().trim(),
                    password = new String(passField.getPassword()),
                    confirmPassword = new String(confirmPassField.getPassword());

            userField.setText(username);
            emailField.setText(email);

            for (JLabel l : new JLabel[]{err1,err2,err3,err4})
                l.setText("");

            boolean err = false;

            if (firstName.isEmpty() || lastName.isEmpty()) {
                System.out.println("First and last name fields " +
                        "must not be empty");
                err1.setText("<HTML>*Names cannot be<BR> left blank</HTML>");
                err = true;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                System.out.println("Invalid email format");
                err2.setText("*Invalid email format");
                err = true;
            }

            if (username.length() < 2) {
                System.out.println("Username must be at least 2 characters long");
                err3.setText("<html>*Usernames must be<BR>at least 2 characters</html>");
                err = true;
            }

            if (password.isEmpty()) {
                System.out.println("*Passwords cannot be left blank");
                err4.setText("<HTML>*Passwords cannot be<BR>left blank</HTML>");
                err = true;
            } else if (!password.equals(confirmPassword)) {
                System.out.println("Passwords do not match, try again");
                err4.setText("*Passwords must match");
                err = true;
            }

            if (err)
                return;

            if (registerDispatch.apply(firstName, lastName, username, email, password)) {
                this.reset();
                App.entry.loginCard();
            }
            else
                err3.setText("<HTML>*Username already<BR>in use<HTML>");
        }
    }
}
