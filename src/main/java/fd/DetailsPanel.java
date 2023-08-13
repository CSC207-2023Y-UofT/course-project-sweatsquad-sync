package fd;

import ia.UserInfoPresenter;
import ia.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DetailsPanel extends JPanel implements ActionListener, View<UserInfoPresenter> {

    // presenter declaration
    private UserInfoPresenter presenter;

    // component declaration
    public final JTextField firstNameInfoField, lastNameInfoField, emailInfoField, userInfoField;
    private final JPasswordField passField;
    private final JButton saveChangesBtn;
    private final JLabel err1;
    private final JLabel err2;
    private final JLabel err3;
    private final JLabel err4;

    // constructor
    public DetailsPanel() {
        // displayed 'on top' of the EntryPointFrame
        this.setLayout(null);
        this.setOpaque(false);

        // component initialization
        JLabel firstNameLabel = new JLabel("First Name");
        firstNameLabel.setFont(ComponentFactory.MB15);
        firstNameLabel.setBounds(189, 98, 422, 25);
        this.add(firstNameLabel);

        firstNameInfoField = ComponentFactory.genRoundTextField("", 20, "#FFFFFF");
        firstNameInfoField.setBounds(189, 123, 198, 45);
        firstNameInfoField.setFont(ComponentFactory.CB18);
        this.add(firstNameInfoField);

        JLabel lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setFont(ComponentFactory.MB15);
        lastNameLabel.setBounds(413, 98, 422, 25);
        this.add(lastNameLabel);

        lastNameInfoField = ComponentFactory.genRoundTextField("", 20, "#FFFFFF");
        lastNameInfoField.setBounds(413, 123, 198, 45);
        lastNameInfoField.setFont(ComponentFactory.CB18);
        this.add(lastNameInfoField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(ComponentFactory.MB15);
        emailLabel.setBounds(189, 185, 422, 25);
        this.add(emailLabel);

        emailInfoField = ComponentFactory.genRoundTextField("", 20, "#FFFFFF");
        emailInfoField.setBounds(189, 210, 422, 45);
        emailInfoField.setFont(ComponentFactory.CB18);
        this.add(emailInfoField);

        JLabel signupUsernameLabel = new JLabel("Username");
        signupUsernameLabel.setFont(ComponentFactory.MB15);
        signupUsernameLabel.setBounds(189, 272, 422, 25);
        this.add(signupUsernameLabel);

        userInfoField = ComponentFactory.genRoundTextField("", 20, "#FFFFFF");
        userInfoField.setBounds(189, 297, 422, 45);
        userInfoField.setFont(ComponentFactory.CB18);
        this.add(userInfoField);

        JLabel signupPasswordLabel = new JLabel("Enter password to save changes");
        signupPasswordLabel.setFont(ComponentFactory.MB15);
        signupPasswordLabel.setBounds(189, 359, 422, 25);
        this.add(signupPasswordLabel);

        passField = ComponentFactory.genRoundPasswordField("", 20, "#FFFFFF");
        passField.setBounds(189, 384, 422, 45);
        passField.setFont(ComponentFactory.CB18);
        this.add(passField);

        saveChangesBtn = ComponentFactory.genRoundBtn("Save Changes", 50, "#172A87", false);
        saveChangesBtn.setFont(ComponentFactory.MB13);
        saveChangesBtn.setForeground(Color.decode("#FFFFFF"));
        saveChangesBtn.setBounds(189, 450, 422, 50);
        saveChangesBtn.addActionListener(this);
        this.add(saveChangesBtn);

        err1 = new JLabel("");
        err1.setFont(ComponentFactory.MB12);
        err1.setBounds(620, 124, 130, 40);
        this.add(err1);
        err2 = new JLabel("");
        err2.setFont(ComponentFactory.MB12);
        err2.setBounds(620, 211, 150, 40);
        this.add(err2);
        err3 = new JLabel("");
        err3.setFont(ComponentFactory.MB12);
        err3.setBounds(620, 298, 150, 40);
        this.add(err3);
        err4 = new JLabel("");
        err4.setFont(ComponentFactory.MB12);
        err4.setBounds(620, 385, 150, 40);
        this.add(err4);

        JLabel coverBG = ComponentFactory.genRoundLabel("", 20, "#FAFAF2");
        coverBG.setBounds(0, 88, 800, 522);
        this.add(coverBG);
    }

    public void clearPass () {
        passField.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveChangesBtn) {
            String firstName = ComponentFactory.capitalizeRemoveTrailingSpaces(firstNameInfoField.getText());
            firstNameInfoField.setText(firstName);
            String lastName = ComponentFactory.capitalizeRemoveTrailingSpaces(lastNameInfoField.getText());
            lastNameInfoField.setText(lastName);
            String username = userInfoField.getText().trim();
            String email = emailInfoField.getText().trim();
            emailInfoField.setText(email);
            err1.setText("");
            err2.setText("");
            err3.setText("");
            err4.setText("");

            if (firstName.equals(presenter.getActiveUserFirstName()) &&
                    lastName.equals(presenter.getActiveUserLastName()) &&
                    email.equals(presenter.getActiveUserEmail()) &&
                    username.equals(presenter.getActiveUserUsername())) {
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
                userInfoField.setText(presenter.getActiveUserUsername());
                err3.setText("Username must be at least 2 characters");
                JOptionPane.showMessageDialog(this, "<html>*Usernames must be<BR>at least 2 characters</html>");
                checksFailed = true;
            }

            if (presenter.takenUsername(username) && !username.equals(presenter.getActiveUserUsername())) {
                userInfoField.setText(presenter.getActiveUserUsername());
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
            if (!presenter.verifyUserDetails(presenter.getActiveUserUsername(), new String(passField.getPassword()))) {
                JOptionPane.showMessageDialog(this, "Invalid credentials, try again.");
                return;
            } else {
                passField.setText("");
            }

            JOptionPane.showMessageDialog(this, "Changes saved!");
            presenter.requestCloseFrame();

            // login is successful -updates user details
            if (!firstName.equals(presenter.getActiveUserFirstName())) {
                presenter.updateActiveUserFirstName(firstName);
                System.out.println("First name changed");
            }

            if (!lastName.equals(presenter.getActiveUserLastName())) {
                presenter.updateActiveUserLastName(lastName);
                System.out.println("Last name changed");
            }

            if (!email.equals(presenter.getActiveUserEmail())) {
                presenter.updateActiveUserEmail(email);
                System.out.println("Email changed");
            }

            if (!username.equals(presenter.getActiveUserUsername())) {
                presenter.updateActiveUserUsername(username);
                System.out.println("Username has been changed to: " + username);
            }
        }
    }

    @Override
    public void displayInfoMessage(String message) {

    }

    @Override
    public void displayErrorMessage(String message) {

    }

    @Override
    public void setPresenter(UserInfoPresenter presenter) {
        this.presenter = presenter;
    }
}
