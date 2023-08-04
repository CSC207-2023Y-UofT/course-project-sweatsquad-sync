package fd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardViewSwing extends JFrame implements ActionListener {

    private JPanel mainP;
    private JPanel schedulePanel, enrolPanel, smthPanel, settingsPanel;
    private JLabel welcomeUser;
    private JButton viewEntireSchedule, enrolBtn, smthBtn, logout, settings;
    private RoundBtn roundBtn;
    private RoundField roundField;

    private CardLayout cardLayout;

    public DashboardViewSwing() throws HeadlessException {
        setTitle("Sweatsquad Dashboard");

        roundBtn = new RoundBtn();
        roundField = new RoundField();

        cardLayout = new CardLayout();

        mainP = new JPanel();
        mainP.setLayout(cardLayout);
        mainP.setBackground(Color.decode("#DADADA"));

        JLabel dashboardTitle = new JLabel("Dashboard");
        dashboardTitle.setFont(new Font("Monsterrat", Font.BOLD, 28));
        dashboardTitle.setForeground(Color.decode("#001561"));
        dashboardTitle.setBounds(64, 25, 422, 47);
        mainP.add(dashboardTitle);

        welcomeUser = new JLabel("");
        welcomeUser.setFont(new Font("Monsterrat", Font.BOLD, 20));
        welcomeUser.setForeground(Color.decode("#001561"));
        welcomeUser.setBounds(66, 64, 422, 47);
        mainP.add(welcomeUser);

        JLabel upcomingC = new JLabel("Upcoming Classes");
        upcomingC.setFont(new Font("Monsterrat", Font.BOLD, 20));
        upcomingC.setForeground(Color.decode("#000000"));
        upcomingC.setBounds(66, 130, 422, 47);
        mainP.add(upcomingC);

        JLabel upcomingC1 = new JLabel("Class 1 Info");
        upcomingC1.setFont(new Font("Monsterrat", Font.BOLD, 20));
        upcomingC1.setForeground(Color.decode("#FFFFFF"));
        upcomingC1.setBackground(Color.decode("#40508a"));
        upcomingC1.setOpaque(true);
        upcomingC1.setBounds(66, 180, 320, 60);
        mainP.add(upcomingC1);

        JLabel upcomingC2 = new JLabel("Class 2 Info");
        upcomingC2.setFont(new Font("Monsterrat", Font.BOLD, 20));
        upcomingC2.setForeground(Color.decode("#FFFFFF"));
        upcomingC2.setBackground(Color.decode("#40508a"));
        upcomingC2.setOpaque(true);
        upcomingC2.setBounds(66, 275, 320, 60);
        upcomingC2.setOpaque(true);
        mainP.add(upcomingC2);

        JLabel upcomingC3 = new JLabel("Class 3 Info");
        upcomingC3.setFont(new Font("Monsterrat", Font.BOLD, 20));
        upcomingC3.setForeground(Color.decode("#FFFFFF"));
        upcomingC3.setBackground(Color.decode("#40508a"));
        upcomingC3.setOpaque(true);
        upcomingC3.setBounds(66, 370, 320, 60);
        mainP.add(upcomingC3);

        viewEntireSchedule = roundBtn.genRoundBtn("View Entire Schedule", 30, "#001561", false);
        viewEntireSchedule.setFont(new Font("Monsterrat", Font.BOLD, 20));
        viewEntireSchedule.setForeground(Color.decode("#FFFFFF"));
        viewEntireSchedule.setBounds(66, 445, 320, 40);
        viewEntireSchedule.addActionListener(this);
        mainP.add(viewEntireSchedule);

        settings = roundBtn.genRoundBtn("⚙", 30, "#001561", false);
        settings.setFont(new Font("Monsterrat", Font.BOLD, 24));
        settings.setForeground(Color.decode("#FFFFFF"));
        settings.setBounds(608, 12, 48, 32);
        settings.addActionListener(this);
        mainP.add(settings);

        JLabel viewType = new JLabel("User View");
        viewType.setFont(new Font("Monsterrat", Font.BOLD, 20));
        viewType.setForeground(Color.decode("#001561"));
        viewType.setBounds(670, 16, 150, 26);
        mainP.add(viewType);

        enrolBtn = roundBtn.genRoundBtn("Enroll in Classes", 30, "#001561", false);
        enrolBtn.setFont(new Font("Monsterrat", Font.BOLD, 23));
        enrolBtn.setForeground(Color.decode("#FFFFFF"));
        enrolBtn.setBounds(490, 180, 218, 100);
        enrolBtn.addActionListener(this);
        mainP.add(enrolBtn);

        smthBtn = roundBtn.genRoundBtn("Smth", 30, "#001561", false);
        smthBtn.setFont(new Font("Monsterrat", Font.BOLD, 23));
        smthBtn.setForeground(Color.decode("#FFFFFF"));
        smthBtn.setBounds(490, 300, 218, 100);
        smthBtn.addActionListener(this);
        mainP.add(smthBtn);

        logout = roundBtn.genRoundBtn("Logout", 30, "#001561", false);
        logout.setFont(new Font("Monsterrat", Font.BOLD, 23));
        logout.setForeground(Color.decode("#FFFFFF"));
        logout.setBounds(490, 420, 218, 64);
        logout.addActionListener(this);
        mainP.add(logout);

        // settings panel
        settingsPanel = new JPanel();
        settingsPanel.setLayout(null);
        settingsPanel.setBackground(Color.decode("#DADADA"));

        // Login panel
        schedulePanel = new JPanel();
        schedulePanel.setLayout(null);
        schedulePanel.setBackground(Color.decode("#DADADA"));

        // Login panel
        enrolPanel = new JPanel();
        enrolPanel.setLayout(null);
        enrolPanel.setBackground(Color.decode("#DADADA"));

        // Login panel
        smthPanel = new JPanel();
        smthPanel.setLayout(null);
        smthPanel.setBackground(Color.decode("#DADADA"));

        add(mainP, "main"); // adds panel to the card "deck"
        add(settingsPanel, "settings"); // adds panel to the card "deck"
        add(schedulePanel, "schedule"); // adds panel to the card "deck"
        add(enrolPanel, "enrolment"); // adds panel to the card "deck"
        add(smthPanel, "something"); // adds panel to the card "deck"

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == viewEntireSchedule) {
            cardLayout.show(getContentPane(), "schedule");
        } else if (e.getSource() == enrolBtn) {
            cardLayout.show(getContentPane(), "enrolment");
        } else if (e.getSource() == smthBtn) {
            cardLayout.show(getContentPane(), "something");
        } else if (e.getSource() == settings) {
            cardLayout.show(getContentPane(), "settings");
        } else if (e.getSource() == logout) {
            cardLayout.show(getContentPane(), "Login");
        }
    }
}
