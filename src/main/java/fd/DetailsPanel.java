package fd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DetailsPanel extends JPanel implements ActionListener {

    private JButton infoChange, passChange;
    private JLabel underLine;

    public JTextField firstNameInfoField, lastNameInfoField, emailInfoField, userInfoField;
    private JPasswordField passField;
    private JButton saveChangesBtn;
    private JLabel err1, err2, err3, err4;

    public DetailsPanel() {

        this.setLayout(null);
        this.setOpaque(false);

//        infoChange = new JButton("Account Details"); // left tab
//        infoChange.setBounds(180, 27, 225, 50);
//        infoChange.addActionListener(this);
//        infoChange.setOpaque(false);
//        infoChange.setContentAreaFilled(false);
//        infoChange.setBorderPainted(false);
//        infoChange.setFocusPainted(false);
//        add(infoChange);
//
//        passChange = new JButton("Change Password"); // right tab
//        passChange.setBounds(395, 27, 225, 50);
//        passChange.addActionListener(this);
//        passChange.setOpaque(false);
//        passChange.setContentAreaFilled(false);
//        passChange.setBorderPainted(false);
//        passChange.setFocusPainted(false);
//        add(passChange);

        underLine = new JLabel(""); // underline
        underLine.setBounds(189, 65, 422, 9);
        add(underLine);

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

        JLabel signupPasswordLabel = new JLabel("Password");
        signupPasswordLabel.setFont(UI.MB15);
        signupPasswordLabel.setBounds(189, 359, 422, 25);
        this.add(signupPasswordLabel);

        passField = UI.genRoundPasswordField("", 20, "#FFFFFF", false);
        passField.setBounds(189, 384, 422, 45);
        passField.setFont(UI.CB18);
        this.add(passField);

        saveChangesBtn = UI.genRoundBtn("Save Changes", 50, "#001561", false);
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
                System.out.println("No changes were made");
                return;
            }

            if (!firstName.equals(App.db.getActiveUserFirstName())) {
                if (!firstName.isEmpty()) {
                    App.db.updateUserFirstName(App.db.getActiveUserFirstName(), firstName);
                    System.out.println("First name changed");
                } else {
                    System.out.println("Name fields cannot be left blank");
                }
            }

            if (!lastName.equals(App.db.getActiveUserLastName())) {
                if (!lastName.isEmpty()) {
                    App.db.updateUserFirstName(App.db.getActiveUserLastName(), lastName);
                    System.out.println("Last name changed");
                } else {
                    System.out.println("Name fields cannot be left blank");
                }
            }

            if (!email.equals(App.db.getActiveUserEmail())) {
                if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                    App.db.updateUserFirstName(App.db.getActiveUserEmail(), email);
                    System.out.println("Email changed");
                } else {
                    System.out.println("Invalid email format.");
                }
            }

            if ((!username.equals(App.db.getActiveUserUsername())) & !(username.length() <3)) {
                App.db.updateUserFirstName(App.db.getActiveUserUsername(), username);
                System.out.println("Username changed");
            }
        }
    }
}
