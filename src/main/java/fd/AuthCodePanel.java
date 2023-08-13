package fd;

import ia.EntryFramePresenter;
import ia.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AuthCodePanel extends JPanel implements ActionListener, View<EntryFramePresenter> {

    // component declarations
    private final JTextField authField;
    private final JButton authenticateBtn, backButton;

    // presenter declaration
    private EntryFramePresenter presenter;
    AuthCodePanel() {
        this.setLayout(null);
        this.setOpaque(false);

    // components are set up as required for the screen
        JLabel authCodeLabel = new JLabel("Enter Authentication Code" +
                "                   (16 Characters)");
        authCodeLabel.setBounds(189, 140, 422, 25);
        authCodeLabel.setFont(ComponentFactory.MB15);
        this.add(authCodeLabel);

        authField = ComponentFactory.genRoundTextField("", 20, "#FFFFFF");
        authField.setBounds(189, 165, 422, 45);
        authField.setFont(ComponentFactory.CB18);
        authField.setHorizontalAlignment(SwingConstants.CENTER); // c-align
        this.add(authField);

        backButton = ComponentFactory.genRoundBtn("Back", 50, "#172A87", false);
        backButton.setFont(ComponentFactory.MB13);
        backButton.setForeground(Color.decode("#FFFFFF"));
        backButton.setBounds(189, 292, 196, 50);
        backButton.addActionListener(this);
        this.add(backButton);

        authenticateBtn = ComponentFactory.genRoundBtn("Authenticate", 50, "#172A87", false);
        authenticateBtn.setFont(ComponentFactory.MB13);
        authenticateBtn.setForeground(Color.decode("#FFFFFF"));
        authenticateBtn.setBounds(413, 292, 196, 50);
        authenticateBtn.addActionListener(this);
        this.add(authenticateBtn);

        JLabel coverBG = ComponentFactory.genRoundLabel("", 20, "#FAFAF2");
        coverBG.setBounds(0, 88, 800, 522);
        this.add(coverBG);

    }

    // button control has been set up for this screen
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == authenticateBtn) {
            String inputCode = authField.getText(); // get the input from user
            presenter.codeActivationAttempted(inputCode);
        } else if (e.getSource() == backButton) {
            presenter.switchToSignUp();
            authField.setText("");
        }
    }

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
}
