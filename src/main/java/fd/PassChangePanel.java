package fd;

import ia.UserInfoPresenter;
import ia.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PassChangePanel extends JPanel implements ActionListener, View<UserInfoPresenter> {
    private JPasswordField oldPasscode,newPasscode, confirmNewPasscode;
    private JButton savePasscodeBtn;
    private JLabel err1, err2, err3;

    private UserInfoPresenter presenter;

    public PassChangePanel() {
        this.setLayout(null);
        this.setOpaque(false);

        JLabel oldPassLabel = new JLabel("Old Password");
        oldPassLabel.setFont(ComponentFactory.MB15);
        oldPassLabel.setBounds(189, 98, 422, 25);
        this.add(oldPassLabel);

        oldPasscode = ComponentFactory.genRoundPasswordField("", 20, "#FFFFFF");
        oldPasscode.setBounds(189, 123, 422, 45);
        oldPasscode.setFont(ComponentFactory.CB18);
        this.add(oldPasscode);

        JLabel newPassLabel = new JLabel("New Password");
        newPassLabel.setFont(ComponentFactory.MB15);
        newPassLabel.setBounds(189, 185, 422, 25);
        this.add(newPassLabel);

        newPasscode = ComponentFactory.genRoundPasswordField("", 20, "#FFFFFF");
        newPasscode.setBounds(189, 210, 422, 45);
        newPasscode.setFont(ComponentFactory.CB18);
        this.add(newPasscode);

        JLabel confirmNewPassLabel = new JLabel("Confirm New Password");
        confirmNewPassLabel.setFont(ComponentFactory.MB15);
        confirmNewPassLabel.setBounds(189, 272, 422, 25);
        this.add(confirmNewPassLabel);

        confirmNewPasscode = ComponentFactory.genRoundPasswordField("", 20, "#FFFFFF");
        confirmNewPasscode.setBounds(189, 297, 422, 45);
        confirmNewPasscode.setFont(ComponentFactory.CB18);
        this.add(confirmNewPasscode);

        savePasscodeBtn = ComponentFactory.genRoundBtn("Update Password", 50, "#172A87", false);
        savePasscodeBtn.setFont(ComponentFactory.MB13);
        savePasscodeBtn.setForeground(Color.decode("#FFFFFF"));
        savePasscodeBtn.setBounds(189, 450, 422, 50);
        savePasscodeBtn.addActionListener(this);
        this.add(savePasscodeBtn);

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == savePasscodeBtn) {
            String oldPass = new String(oldPasscode.getPassword());
            String newPass = new String(newPasscode.getPassword());
            String confirmNewPass = new String(confirmNewPasscode.getPassword());
            err1.setText("");
            err2.setText("");
            err3.setText("");

            boolean checksFailed = false;

            // checks if old password is correct
            if (!presenter.verifyUserDetails(presenter.getActiveUserUsername(), oldPass)) {
                err1.setText("Incorrect password");
                JOptionPane.showMessageDialog(this, "*Incorrect password, try again");
                checksFailed = true;
            }

            // check empty - new password field
            if (newPass.isEmpty()) {
                err2.setText("New password field cannot be left blank");
                JOptionPane.showMessageDialog(this, "<HTML>*New password field<BR>cannot be left blank</HTML>");
                checksFailed = true;
            }

            // check new password + confirm password fields match
            if (!newPass.equals(confirmNewPass)) {
                err3.setText("Passwords do not match");
                JOptionPane.showMessageDialog(this, "*Passwords do not match");
                return;
            }

            // check if new password is the same as old password
            if (newPass.equals(oldPass)) {
                err2.setText("<HTML>*New password cannot<BR>be the same as the old password</HTML>");
                JOptionPane.showMessageDialog(this, "<HTML>*New password cannot be<BR>the same as the old password</HTML>");
                checksFailed = true;
            }

            if (checksFailed) {
                oldPasscode.setText("");
                newPasscode.setText("");
                confirmNewPasscode.setText("");
                return;
            }

            // all checks have passed - update user password
            presenter.updateActiveUserPasscode(presenter.getActiveUserUsername(), newPass);
            JOptionPane.showMessageDialog(this, "Password successfully updated!");
            oldPasscode.setText("");
            newPasscode.setText("");
            confirmNewPasscode.setText("");
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
