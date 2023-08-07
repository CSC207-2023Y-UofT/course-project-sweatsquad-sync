package fd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfoFrame extends JDialog implements ActionListener {
    private JButton detailsTab, passChangeTab;
    private JLabel signIndicator;
    private CardLayout cardLayout = new CardLayout();
    private JPanel cards = new JPanel(cardLayout);

    public DetailsPanel detailsPanel = new DetailsPanel();
    public PassChangePanel passChangePanel = new PassChangePanel();

    public UserInfoFrame() {
        setTitle("Details");
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setBackground(Color.decode("#DADADA"));
        setModal(true);

        detailsTab = new JButton("Account Details");
        detailsTab.setBounds(180, 27, 225, 50);
        detailsTab.addActionListener(this);
        detailsTab.setOpaque(false);
        detailsTab.setContentAreaFilled(false);
        detailsTab.setBorderPainted(false);
        detailsTab.setFocusPainted(false);
        add(detailsTab);

        passChangeTab = new JButton("Change Password");
        passChangeTab.setBounds(395, 27, 225, 50);
        passChangeTab.addActionListener(this);
        passChangeTab.setOpaque(false);
        passChangeTab.setContentAreaFilled(false);
        passChangeTab.setBorderPainted(false);
        passChangeTab.setFocusPainted(false);
        add(passChangeTab);

        signIndicator = new JLabel("");
        signIndicator.setBounds(189, 65, 422, 9);
        add(signIndicator);

        cards.add(detailsPanel, "Change Details");
        cards.add(passChangePanel, "Change Passcode");
        cards.setBounds(0, 0, 800, 700);
        cards.setOpaque(false);
        cardLayout.show(cards, "Login");
        add(cards);

        detailsCard();
    }

    public void detailsCard() {
        cardLayout.show(cards, "Change Details");

        detailsTab.setFocusable(false);
        detailsTab.setFont(UI.MB16);
        detailsTab.setForeground(Color.decode("#001561"));
        this.add(detailsTab);

        passChangeTab.setFocusable(true);
        passChangeTab.setFont(UI.MB14);
        passChangeTab.setForeground(Color.decode("#000000"));
        this.add(passChangeTab);

        // Assuming some indicator for the DetailsPanel
        signIndicator.setIcon(new ImageIcon("images/00101-sign-indicator.png"));
    }

    public void passChangeCard() {
        //this.setVisible(true);

        cardLayout.show(cards, "Change Passcode");

        detailsTab.setFocusable(true);
        detailsTab.setFont(UI.MB14);
        detailsTab.setForeground(Color.decode("#000000"));
        this.add(detailsTab);

        passChangeTab.setFocusable(false);
        passChangeTab.setFont(UI.MB16);
        passChangeTab.setForeground(Color.decode("#001561"));
        this.add(passChangeTab);

        signIndicator.setIcon(new ImageIcon("images/00201-reg-indicator.png"));
    }

    public void refreshShow() {
        this.setVisible(true);
        detailsPanel.firstNameInfoField.setText(App.db.getActiveUserFirstName());
        detailsPanel.lastNameInfoField.setText(App.db.getActiveUserLastName());
        detailsPanel.emailInfoField.setText(App.db.getActiveUserEmail());
        detailsPanel.userInfoField.setText(App.db.getActiveUserUsername());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == detailsTab) {
            detailsCard();
        } else if (e.getSource() == passChangeTab){
            passChangeCard();
        }
    }
}
