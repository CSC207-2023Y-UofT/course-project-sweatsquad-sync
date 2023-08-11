package fd;

import ia.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@FunctionalInterface
interface RegistrationDispatch {
    void apply(RegisterRequestViewModel vm);
}

class SignupPanel extends JPanel implements ActionListener, View<EntryFramePresenter> {
    private JTextField firstNameField, lastNameField, emailField, userField;
    private JPasswordField passField, confirmPassField;
    private JButton registerButton;
    private JLabel err1, err2, err3, err4, haveCodeLabel, coverBG;

    private RegistrationDispatch registrationDispatch;

    SignupPanel() {

        this.setLayout(null);
        this.setOpaque(false);

        JLabel firstNameLabel = new JLabel("First Name");
        firstNameLabel.setFont(ComponentFactory.MB15);
        firstNameLabel.setBounds(189, 98, 422, 25);
        this.add(firstNameLabel);

        firstNameField = ComponentFactory.genRoundTextField("", 20, "#FFFFFF", false);
        firstNameField.setBounds(189, 123, 198, 45);
        firstNameField.setFont(ComponentFactory.CB18);
        this.add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setFont(ComponentFactory.MB15);
        lastNameLabel.setBounds(413, 98, 422, 25);
        this.add(lastNameLabel);

        lastNameField = ComponentFactory.genRoundTextField("", 20, "#FFFFFF", false);
        lastNameField.setBounds(413, 123, 198, 45);
        lastNameField.setFont(ComponentFactory.CB18);
        this.add(lastNameField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(ComponentFactory.MB15);
        emailLabel.setBounds(189, 185, 422, 25);
        this.add(emailLabel);

        emailField = ComponentFactory.genRoundTextField("", 20, "#FFFFFF", false);
        emailField.setBounds(189, 210, 422, 45);
        emailField.setFont(ComponentFactory.CB18);
        this.add(emailField);

        JLabel signupUsernameLabel = new JLabel("Username");
        signupUsernameLabel.setFont(ComponentFactory.MB15);
        signupUsernameLabel.setBounds(189, 272, 422, 25);
        this.add(signupUsernameLabel);

        userField = ComponentFactory.genRoundTextField("", 20, "#FFFFFF", false);
        userField.setBounds(189, 297, 422, 45);
        userField.setFont(ComponentFactory.CB18);
        this.add(userField);

        JLabel signupPasswordLabel = new JLabel("Password");
        signupPasswordLabel.setFont(ComponentFactory.MB15);
        signupPasswordLabel.setBounds(189, 359, 422, 25);
        this.add(signupPasswordLabel);

        passField = ComponentFactory.genRoundPasswordField("", 20, "#FFFFFF");
        passField.setBounds(189, 384, 198, 45);
        passField.setFont(ComponentFactory.CB18);
        this.add(passField);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setFont(ComponentFactory.MB15);
        confirmPasswordLabel.setBounds(413, 359, 422, 25);
        this.add(confirmPasswordLabel);

        JLabel confirmPassLabel = new JLabel("Password");
        confirmPassLabel.setFont(ComponentFactory.MB15);
        confirmPassLabel.setBounds(189, 359, 422, 25);
        this.add(signupPasswordLabel);

        confirmPassField = ComponentFactory.genRoundPasswordField("", 20, "#FFFFFF");
        confirmPassField.setBounds(413, 384, 198, 45);
        confirmPassField.setFont(ComponentFactory.CB18);
        this.add(confirmPassField);

        registerButton = ComponentFactory.genRoundBtn("Register", 50, "#172A87", false);
        registerButton.setFont(ComponentFactory.MB13);
        registerButton.setForeground(Color.decode("#FFFFFF"));
        registerButton.setBounds(189, 450, 422, 50);
        registerButton.addActionListener(this);
        this.add(registerButton);

        haveCodeLabel = new JLabel("<HTML><U>I have a code</U></HTML>");
        haveCodeLabel.setForeground(Color.BLUE);
        haveCodeLabel.setFont(ComponentFactory.CB13);
        haveCodeLabel.setBounds(350, 514, 200, 30);
        haveCodeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        haveCodeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                presenter.switchToActivation();
            }
        });
        this.add(haveCodeLabel);

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

        coverBG = ComponentFactory.genRoundLabel("", 20, "#FAFAF2");
        coverBG.setBounds(0, 88, 800, 522);
        this.add(coverBG);


    }

    private EntryFramePresenter presenter;

    @Override
    public void displayInfoMessage(String message) {

    }

    @Override
    public void displayErrorMessage(String message) {

    }

    @Override
    public void setPresenter(EntryFramePresenter presenter) {
        this.presenter = presenter;
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

    public void showBasicView() {
        clearFields();
        registrationDispatch = presenter::regularRegistrationAttempted;
        haveCodeLabel.setVisible(true);
    }

    public void showInstructorView() {
        registrationDispatch = presenter::instructorRegistrationAttempted;
        haveCodeLabel.setVisible(false);
    }

    public void showAdminView() {
        registrationDispatch = presenter::adminRegistrationAttempted;
        haveCodeLabel.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) { // register logic

            haveCodeLabel.setVisible(true);
            String firstName = firstNameField.getText(),
                    lastName = lastNameField.getText(),
                    username = userField.getText().trim(),
                    email = emailField.getText().trim(),
                    password = new String(passField.getPassword()),
                    confirmPassword = new String(confirmPassField.getPassword());

            userField.setText(username);
            emailField.setText(email);

            for (JLabel l : new JLabel[]{err1,err2,err3,err4})
                l.setText("");


            registrationDispatch.apply(new RegisterRequestViewModel(firstName, lastName, username, email, password, confirmPassword));
        }
    }
    public void displayErrors(RegisterErrorViewModel rvm) {

        err1.setText(rvm.namesError());
        err3.setText(rvm.usernameError());
        err2.setText(rvm.emailError());
        err4.setText(rvm.passwordsError());
    }

}
