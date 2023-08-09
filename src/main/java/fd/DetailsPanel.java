package fd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DetailsPanel extends JPanel implements ActionListener {

    public JTextField firstNameInfoField, lastNameInfoField, emailInfoField, userInfoField;
    private JPasswordField passField;
    private JButton saveChangesBtn;
    private JLabel err1, err2, err3, err4;

    public DetailsPanel() {
        this.setLayout(null);
        this.setOpaque(false);

        JLabel firstNameLabel = new JLabel("First Name");
        firstNameLabel.setFont(UI.MB15);
        firstNameLabel.setBounds(189, 98, 422, 25);
        this.add(firstNameLabel);

        firstNameInfoField = UI.genRoundTextField("", 20, "#FFFFFF", false);
        firstNameInfoField.setBounds(189, 123, 198, 45);
        firstNameInfoField.setFont(UI.CB18);
        this.add(firstNameInfoField);

        JLabel lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setFont(UI.MB15);
        lastNameLabel.setBounds(413, 98, 422, 25);
        this.add(lastNameLabel);

        lastNameInfoField = UI.genRoundTextField("", 20, "#FFFFFF", false);
        lastNameInfoField.setBounds(413, 123, 198, 45);
        lastNameInfoField.setFont(UI.CB18);
        this.add(lastNameInfoField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(UI.MB15);
        emailLabel.setBounds(189, 185, 422, 25);
        this.add(emailLabel);

        emailInfoField = UI.genRoundTextField("", 20, "#FFFFFF", false);
        emailInfoField.setBounds(189, 210, 422, 45);
        emailInfoField.setFont(UI.CB18);
        this.add(emailInfoField);

        JLabel signupUsernameLabel = new JLabel("Username");
        signupUsernameLabel.setFont(UI.MB15);
        signupUsernameLabel.setBounds(189, 272, 422, 25);
        this.add(signupUsernameLabel);

        userInfoField = UI.genRoundTextField("", 20, "#FFFFFF", false);
        userInfoField.setBounds(189, 297, 422, 45);
        userInfoField.setFont(UI.CB18);
        this.add(userInfoField);

        JLabel signupPasswordLabel = new JLabel("Enter password to save changes");
        signupPasswordLabel.setFont(UI.MB15);
        signupPasswordLabel.setBounds(189, 359, 422, 25);
        this.add(signupPasswordLabel);

        passField = UI.genRoundPasswordField("", 20, "#FFFFFF");
        passField.setBounds(189, 384, 422, 45);
        passField.setFont(UI.CB18);
        this.add(passField);

        saveChangesBtn = UI.genRoundBtn("Save Changes", 50, "#172A87", false);
        saveChangesBtn.setFont(UI.MB13);
        saveChangesBtn.setForeground(Color.decode("#FFFFFF"));
        saveChangesBtn.setBounds(189, 450, 422, 50);
        saveChangesBtn.addActionListener(this);
        this.add(saveChangesBtn);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveChangesBtn) {
            String firstName = UI.capitalizeRemoveTrailingSpaces(firstNameInfoField.getText());
            firstNameInfoField.setText(firstName);
            String lastName = UI.capitalizeRemoveTrailingSpaces(lastNameInfoField.getText());
            lastNameInfoField.setText(lastName);
            String username = userInfoField.getText().trim();
            String email = emailInfoField.getText().trim();
            emailInfoField.setText(email);
            err1.setText("");
            err2.setText("");
            err3.setText("");
            err4.setText("");

            if (firstName.equals(App.db.getActiveUserFirstName()) &&
                    lastName.equals(App.db.getActiveUserLastName()) &&
                    email.equals(App.db.getActiveUserEmail()) &&
                    username.equals(App.db.getActiveUserUsername())) {
                JOptionPane.showMessageDialog(this, "No changes were made.");
                return;
            }

            boolean checksFailed = false;

            if (firstName.isEmpty() || lastName.isEmpty()) {
                err1.setText("Name fields cannot be left blank");
                JOptionPane.showMessageDialog(this, "<HTML>*Names cannot be<BR> left blank</HTML>");
                checksFailed = true;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                err2.setText("Invalid email format.");
                JOptionPane.showMessageDialog(this, "*Invalid email format");
                checksFailed = true;
            }

            if (username.length() < 2) {
                userInfoField.setText(App.db.getActiveUserUsername());
                err3.setText("Username must be at least 2 characters");
                JOptionPane.showMessageDialog(this, "<html>*Usernames must be<BR>at least 2 characters</html>");
                checksFailed = true;
            }

            if (App.db.takenUsername(username) && !username.equals(App.db.getActiveUserUsername())) {
                userInfoField.setText(App.db.getActiveUserUsername());
                err3.setText("This username is already in use");
                JOptionPane.showMessageDialog(this, "<HTML>*Username already<BR>in use<HTML>");
                checksFailed = true;
            }

            if (checksFailed) {
                passField.setText("");
                return;
            }

            if ("".equals(new String(passField.getPassword()))) {
                JOptionPane.showMessageDialog(this, "Enter your password to save changes");
                return;
            }

            // all checks have passed (valid inputs)
            if (!App.login(App.db.getActiveUserUsername(), new String(passField.getPassword()))) {
                JOptionPane.showMessageDialog(this, "Invalid credentials, try again.");
                return;
            } else {
                passField.setText("");
            }

            // login is successful -updates user details
            if (!firstName.equals(App.db.getActiveUserFirstName())) {
                App.db.updateActiveUserFirstName(firstName);
                System.out.println("First name changed");
            }

            if (!lastName.equals(App.db.getActiveUserLastName())) {
                App.db.updateActiveUserLastName(lastName);
                System.out.println("Last name changed");
            }

            if (!email.equals(App.db.getActiveUserEmail())) {
                App.db.updateActiveUserEmail(email);
                System.out.println("Email changed");
            }

            if (!username.equals(App.db.getActiveUserUsername())) {
                App.db.updateActiveUserUsername(username);
                System.out.println("Username has been changed to: " + username);
            }
        }
    }
}
