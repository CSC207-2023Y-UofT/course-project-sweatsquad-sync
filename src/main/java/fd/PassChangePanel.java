package fd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PassChangePanel extends JPanel implements ActionListener {

    //private JButton infoChange, passChange;
    private JLabel underLine;

    private JPasswordField oldPasscode,newPasscode, confirmNewPasscode;
    private JButton savePasscodeBtn;
    private JLabel err1, err2, err3;

    public PassChangePanel() {

        this.setLayout(null);
        this.setOpaque(false);

        underLine = new JLabel(""); // underline
        underLine.setBounds(189, 65, 422, 9);
        add(underLine);

        JLabel oldPassLabel = new JLabel("Old Password");
        oldPassLabel.setFont(UI.MB15);
        oldPassLabel.setBounds(189, 98, 422, 25);
        this.add(oldPassLabel);

        oldPasscode = UI.genRoundPasswordField("", 20, "#FFFFFF", false);
        oldPasscode.setBounds(189, 123, 422, 45);
        oldPasscode.setFont(UI.CB18);
        this.add(oldPasscode);

        JLabel newPassLabel = new JLabel("Email");
        newPassLabel.setFont(UI.MB15);
        newPassLabel.setBounds(189, 185, 422, 25);
        this.add(newPassLabel);

        newPasscode = UI.genRoundPasswordField("", 20, "#FFFFFF", false);
        newPasscode.setBounds(189, 210, 422, 45);
        newPasscode.setFont(UI.CB18);
        this.add(newPasscode);

        JLabel confirmNewPassLabel = new JLabel("Username");
        confirmNewPassLabel.setFont(UI.MB15);
        confirmNewPassLabel.setBounds(189, 272, 422, 25);
        this.add(confirmNewPassLabel);

        confirmNewPasscode = UI.genRoundPasswordField("", 20, "#FFFFFF", false);
        confirmNewPasscode.setBounds(189, 297, 422, 45);
        confirmNewPasscode.setFont(UI.CB18);
        this.add(confirmNewPasscode);

        savePasscodeBtn = UI.genRoundBtn("Update Password", 50, "#001561", false);
        savePasscodeBtn.setFont(UI.MB13);
        savePasscodeBtn.setForeground(Color.decode("#FFFFFF"));
        savePasscodeBtn.setBounds(189, 450, 422, 50);
        savePasscodeBtn.addActionListener(this);
        this.add(savePasscodeBtn);

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == savePasscodeBtn) {
            //todo come back here
        }
    }
}
