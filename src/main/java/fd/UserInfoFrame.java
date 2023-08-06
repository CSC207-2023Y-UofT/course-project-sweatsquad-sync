package fd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfoFrame extends JFrame implements ActionListener {

    private JButton infoChange, passChange;
    private JLabel underLine;
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

        // content
        // detailsPanel.add(new JLabel("Details content goes here"));
        // passChangePanel.add(new JLabel("Password change content goes here"));

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
