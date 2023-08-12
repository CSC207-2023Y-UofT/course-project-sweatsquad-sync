package fd;

import ia.UserInfoPresenter;
import ia.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserInfoFrame extends JDialog implements ActionListener, View<UserInfoPresenter> {
    private UserInfoPresenter presenter;
    private final JButton detailsTab, passChangeTab;
    private final JLabel signIndicator;
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cards = new JPanel(cardLayout);

    public DetailsPanel detailsPanel = new DetailsPanel();
    public PassChangePanel passChangePanel = new PassChangePanel();

    public UserInfoFrame(UserInfoPresenter presenter) {
        setPresenter(presenter);
        setTitle("Details");
        setSize(800, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(Color.decode("#8F98FF"));
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
        cardLayout.show(cards, "Change Details");
        add(cards);

        detailsCard();
    }

    public void detailsCard() {
        passChangePanel.clearFields();
        textSetup();
        cardLayout.show(cards, "Change Details");

        detailsTab.setFocusable(false);
        detailsTab.setFont(ComponentFactory.MB16);
        detailsTab.setForeground(Color.decode("#172A87"));
        detailsTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // no longer shows that the button area's clickable
                setCursor(Cursor.getDefaultCursor());
            }
        });
        this.add(detailsTab);

        passChangeTab.setFocusable(true);
        passChangeTab.setFont(ComponentFactory.MB14);
        passChangeTab.setForeground(Color.decode("#000000"));
        passChangeTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        });
        this.add(passChangeTab);

        // Assuming some indicator for the DetailsPanel
        signIndicator.setIcon(new ImageIcon("images/00101-sign-indicator.png"));
    }

    public void passChangeCard() {
        detailsPanel.clearPass();

        cardLayout.show(cards, "Change Passcode");

        detailsTab.setFocusable(true);
        detailsTab.setFont(ComponentFactory.MB14);
        detailsTab.setForeground(Color.decode("#000000"));
        detailsTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        });
        this.add(detailsTab);

        passChangeTab.setFocusable(false);
        passChangeTab.setFont(ComponentFactory.MB16);
        passChangeTab.setForeground(Color.decode("#172A87"));
        passChangeTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // no longer shows that the button area's clickable
                setCursor(Cursor.getDefaultCursor());
            }
        });
        this.add(passChangeTab);

        signIndicator.setIcon(new ImageIcon("images/00201-reg-indicator.png"));
    }

    public void textSetup() {
        detailsPanel.firstNameInfoField.setText(presenter.getActiveUserFirstName());
        detailsPanel.lastNameInfoField.setText(presenter.getActiveUserLastName());
        detailsPanel.emailInfoField.setText(presenter.getActiveUserEmail());
        detailsPanel.userInfoField.setText(presenter.getActiveUserUsername());
    }

    public void refreshShow() {
        textSetup();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == detailsTab) {
            detailsCard();
        } else if (e.getSource() == passChangeTab){
            passChangeCard();
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
            passChangePanel.setPresenter(presenter);
            detailsPanel.setPresenter(presenter);
    }
}
