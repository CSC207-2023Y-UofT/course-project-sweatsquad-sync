package fd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AuthCodePanel extends JPanel implements ActionListener {
    private JTextField authField;
    private JButton authenticateBtn;
    AuthCodePanel() {
        this.setLayout(null);
        this.setOpaque(false);

        JLabel authCodeLabel = new JLabel("Enter Authentication Code                   (16 Characters)");
        authCodeLabel.setBounds(189, 140, 422, 25);
        authCodeLabel.setFont(UI.MB15);
        this.add(authCodeLabel);

        authField = UI.genRoundTextField("", 20, "#FFFFFF", false);
        authField.setBounds(189, 165, 422, 45);
        authField.setFont(UI.CB18);
        authField.setHorizontalAlignment(SwingConstants.CENTER); // c-align
        this.add(authField);

        authenticateBtn = UI.genRoundBtn("Authenticate", 50, "#001561", false);
        authenticateBtn.setFont(UI.MB13);
        authenticateBtn.setForeground(Color.decode("#FFFFFF"));
        authenticateBtn.setBounds(413, 292, 196, 50);
        authenticateBtn.addActionListener(this);
        this.add(authenticateBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == authenticateBtn) {
            String inputCode = authField.getText(); // get the input from user
            if (App.db.validateAuthCode(inputCode) != null) {
                App.entry.signupPanel.setInstructor(authField.getText());
                App.entry.signupCard();
            }
            else
                JOptionPane.showMessageDialog(this,"Failure. Invalid code.");
        }
    }
}
