package fd;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfoFrame extends JDialog implements ActionListener  {


    public UserInfoFrame() {
        setTitle("Edit User Information"); // window title
        setSize(800, 600); // window dimensions
        setResizable(false); // disables resizing
        setLocationRelativeTo(null); // centers ui if left 'null'
        setLayout(null);
        setModal(true);
        // TODO actually i think you may be able to just spawn a SignupPanel and add it to this frame
        //  (may need to change some stuff from there though (but still better than redoing this))
//        firstNameField.setText(database.activeUser.firstName); // set FN
//        settingsPanel.add(lastNameLabel);
//        settingsPanel.add(lastNameField);
//        lastNameField.setText(database.activeUser.lastName); // set LN
//        settingsPanel.add(emailLabel);
//        settingsPanel.add(emailField);
//        emailField.setText(database.activeUser.email); // set email
//        settingsPanel.add(signupUsernameLabel);
//        settingsPanel.add(userField);
//        userField.setText(database.activeUser.getName());
//        settingsPanel.add(err1);
//        settingsPanel.add(err2);
//        settingsPanel.add(err3);
//        settingsPanel.add(err4);
//        settingsPanel.add(back2dash);
//        back2dash.setBounds(12, 12, 138, 68);
//        cardLayout.show(getContentPane(), "Account Settings");
        //        saveAccountChanges = UI.genRoundBtn("Save Changes", 50, "#001561", false);
//        saveAccountChanges.setFont(UI.MB13);
//        saveAccountChanges.setForeground(Color.decode("#FFFFFF"));
//        saveAccountChanges.setBounds(189, 450, 422, 50);
//        saveAccountChanges.addActionListener(this);
//        this.add(saveAccountChanges);
    }


//    // button action listener response function
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == saveAccountChanges) {
//            String firstName = capitalizeRemoveTrailingSpaces(firstNameField.getText());
//            firstNameField.setText(firstName);
//            String lastName = capitalizeRemoveTrailingSpaces(lastNameField.getText());
//            lastNameField.setText(lastName);
//            String username = userField.getText().trim();
//            String email = emailField.getText().trim();
//            emailField.setText(email);
//            err1.setText("");
//            err2.setText("");
//            err3.setText("");
//            err4.setText("");
//
//            if (!firstName.equals(database.activeUser.firstName)) {
//                if (!firstName.isEmpty()) {
//                    database.activeUser.firstName = firstName;
//                } else {
//                    System.out.println("Name fields cannot be left blank");
//                }
//            }
//
//            if (!lastName.equals(database.activeUser.lastName)) {
//                if (!lastName.isEmpty()) {
//                    database.activeUser.lastName = lastName;
//                } else {
//                    System.out.println("Name fields cannot be left blank");
//                }
//            }
//
//            if (!email.equals(database.activeUser.email)) {
//                if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
//                    database.activeUser.email = email;
//                } else {
//                    System.out.println("Invalid email format.");
//                }
//            }
//
//            if (!username.equals(database.activeUser.firstName)) {
//                // TODO
//            }
//        }
//    }
    public void refreshShow() {
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
