package fd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserInfoFrame extends JFrame implements ActionListener {

    private JButton infoChange, passChange;
    private JLabel underLine;

    private JTextField firstNameField, lastNameField, emailField, userField;
    private JPasswordField passField;
    private JButton updateButton;
    private JLabel err1, err2, err3, err4;

    private CardLayout cardLayout = new CardLayout();
    private JPanel cards = new JPanel(cardLayout);
    private JPanel detailsPanel = new JPanel();
    private JPanel passChangePanel = new JPanel();

    public UserInfoFrame() {
        setTitle("Edit User Information");
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        infoChange = new JButton("Account Details"); // left tab
        infoChange.setBounds(180, 27, 225, 50);
        infoChange.addActionListener(this);
        infoChange.setOpaque(false);
        infoChange.setContentAreaFilled(false);
        infoChange.setBorderPainted(false);
        infoChange.setFocusPainted(false);
        add(infoChange);

        passChange = new JButton("Change Password"); // right tab
        passChange.setBounds(395, 27, 225, 50);
        passChange.addActionListener(this);
        passChange.setOpaque(false);
        passChange.setContentAreaFilled(false);
        passChange.setBorderPainted(false);
        passChange.setFocusPainted(false);
        add(passChange);

        underLine = new JLabel(""); // underline
        underLine.setBounds(189, 65, 422, 9);
        add(underLine);

        // transferred content
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
        passField.setBounds(189, 384, 422, 45);
        passField.setFont(UI.CB18);
        this.add(passField);

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


        // adding panels to cards
        cards.add(detailsPanel, "Details");
        cards.add(passChangePanel, "PassChange");
        cards.setBounds(180, 100, 600, 450);
        add(cards);

        infoChangeCard();  // default card (left tab)
    }

    public void infoChangeCard() {
        cardLayout.show(cards, "Details");

        infoChange.setFocusable(false);
        infoChange.setFont(UI.MB16);  // Placeholder font
        infoChange.setForeground(Color.decode("#001561"));

        passChange.setFocusable(true);
        passChange.setFont(UI.MB14);  // Placeholder font
        passChange.setForeground(Color.decode("#000000"));

        underLine.setIcon(new ImageIcon("images/00101-sign-indicator.png"));
    }

    public void passChangeCard() {
        cardLayout.show(cards, "PassChange");

        infoChange.setFocusable(true);
        infoChange.setFont(UI.MB14);
        infoChange.setForeground(Color.decode("#000000"));

        passChange.setFocusable(false);
        passChange.setFont(UI.MB16);
        passChange.setForeground(Color.decode("#001561"));

        underLine.setIcon(new ImageIcon("images/00201-reg-indicator.png"));
    }

    public void refreshShow() {
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == infoChange)
            infoChangeCard();
        else if (e.getSource() == passChange)
            passChangeCard();
    }

    public static void main(String[] args) {  // Just for testing
        new UserInfoFrame();
    }
}
