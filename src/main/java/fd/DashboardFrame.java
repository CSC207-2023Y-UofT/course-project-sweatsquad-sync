package fd;

import ebr.Workout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashboardFrame extends JFrame implements ActionListener {
    private CardLayout cardLayout = new CardLayout();
    private JButton viewEntireSchedule, enrolBtn, logout, settings;
    private JPanel userDash, regUserDash, instructorDash, adminDash;
    private JLabel haveCodeLabel, welcomeUser, err1, err2, err3, err4, viewType;
    private JLabel dashboardTitle, firstNameLabel, lastNameLabel, emailLabel, signupUsernameLabel, signupPasswordLabel, signUpConfirmLabel, confirmPasswordLabel, regIndicator, signInTextSU, registerTextSU;
    private JButton viewClassOfferings, dailyWorkoutTips;
    private JPanel viewOfferingsPanel, workoutTipsPanel, settingsPanel;
    private JButton back2dash, logout_icon, saveAccountChanges;
    private JButton addWorkoutBtn;

    public DashboardFrame() {
//        setTitle("Dashboard"); // window title
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ends program if x
//        setSize(800, 600); // window dimensions
//        setResizable(false); // disables resizing
//        setLayout(cardLayout); // layout form, switches between cards
//        setLocationRelativeTo(null); // centers ui if left 'null'
//
//        setVisible(true);
//
//
//        userDash = new JPanel();
//        userDash.setLayout(null);
//        userDash.setBackground(Color.decode("#DADADA"));
//
//        dashboardTitle = new JLabel("Dashboard");
//        dashboardTitle.setFont(new Font("Monsterrat", Font.BOLD, 28));
//        dashboardTitle.setForeground(Color.decode("#001561"));
//        dashboardTitle.setBounds(64, 25, 422, 47);
//        userDash.add(dashboardTitle);
//
//        welcomeUser = new JLabel("");
//        welcomeUser.setFont(new Font("Monsterrat", Font.BOLD, 20));
//        welcomeUser.setForeground(Color.decode("#001561"));
//        welcomeUser.setBounds(66, 64, 422, 47);
//        userDash.add(welcomeUser);
//
//        logout_icon = new JButton();
//        logout_icon.setBounds(620, 15, 40, 30);
//        logout_icon.setBorderPainted(false); // Hide the button border
//        logout_icon.setFocusPainted(false); // Remove the focus indication
//        logout_icon.setContentAreaFilled(false);
//        logout_icon.setIcon(new ImageIcon("images/logout_icon.png"));
//        logout_icon.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                setCursor(Cursor.getDefaultCursor());
//            }
//        });
//        logout_icon.addActionListener(this);
//        userDash.add(logout_icon);
//
//        logout = roundBtn.genRoundBtn("", 23, "#001561", false);
//        logout.setFont(new Font("Monsterrat", Font.BOLD, 19));
//        logout.setForeground(Color.decode("#FFFFFF"));
//        logout.setBounds(620, 15, 40, 30);
//        userDash.add(logout); // just the background -for the overridden paint
//
//        viewType = new JLabel("");
//        viewType.setFont(new Font("Monsterrat", Font.BOLD, 20));
//        viewType.setForeground(Color.decode("#001561"));
//        viewType.setBounds(670, 16, 150, 26);
//        userDash.add(viewType);
//
//        viewClassOfferings = roundBtn.genRoundBtn("View Class Offerings", 30, "#001561", false);
//        viewClassOfferings.setFont(new Font("Monsterrat", Font.BOLD, 20));
//        viewClassOfferings.setForeground(Color.decode("#FFFFFF"));
//        viewClassOfferings.setBounds(174, 130, 452, 60);
//        viewClassOfferings.addActionListener(this);
//        userDash.add(viewClassOfferings);
//
//        viewOfferingsPanel = new JPanel();
//        viewOfferingsPanel.setLayout(null);
//        viewOfferingsPanel.setBackground(Color.decode("#DADADA"));
//
//        back2dash = roundBtn.genRoundBtn("Back", 30, "#001561", false);
//        back2dash.setFont(new Font("Monsterrat", Font.BOLD, 20));
//        back2dash.setForeground(Color.decode("#FFFFFF"));
//        back2dash.setBounds(592, 478, 138, 68);
//        back2dash.addActionListener(this);
//        viewOfferingsPanel.add(back2dash);
//
//        dailyWorkoutTips = roundBtn.genRoundBtn("Daily Workout Tips", 30, "#001561", false);
//        dailyWorkoutTips.setFont(new Font("Monsterrat", Font.BOLD, 20));
//        dailyWorkoutTips.setForeground(Color.decode("#FFFFFF"));
//        dailyWorkoutTips.setBounds(174, 230, 452, 60);
//        dailyWorkoutTips.addActionListener(this);
//        userDash.add(dailyWorkoutTips);
//
//        workoutTipsPanel = new JPanel();
//        workoutTipsPanel.setLayout(null);
//        workoutTipsPanel.setBackground(Color.decode("#DADADA"));
//
//        settings = roundBtn.genRoundBtn("Account Settings ⚙", 30, "#001561", false);
//        settings.setFont(new Font("Monsterrat", Font.BOLD, 20));
//        settings.setForeground(Color.decode("#FFFFFF"));
//        settings.setBounds(174, 330, 452, 60);
//        settings.addActionListener(this);
//        userDash.add(settings);
//
//        settingsPanel = new JPanel();
//        settingsPanel.setLayout(null);
//        settingsPanel.setBackground(Color.decode("#DADADA"));
//
//        saveAccountChanges = roundBtn.genRoundBtn("Save Changes", 50, "#001561", false);
//        saveAccountChanges.setFont(new Font("Monsterrat", Font.BOLD, 13));
//        saveAccountChanges.setForeground(Color.decode("#FFFFFF"));
//        saveAccountChanges.setBounds(189, 450, 422, 50);
//        saveAccountChanges.addActionListener(this);
//        settingsPanel.add(saveAccountChanges);
//
//        // main logged-in view panel
//        instructorDash = new JPanel();
//        instructorDash.setLayout(null);
//        instructorDash.setBackground(Color.decode("#DADADA"));
//
//        JLabel upcomingC = new JLabel("Upcoming Classes");
//        upcomingC.setFont(new Font("Monsterrat", Font.BOLD, 20));
//        upcomingC.setForeground(Color.decode("#000000"));
//        upcomingC.setBounds(66, 130, 422, 47);
//        instructorDash.add(upcomingC);
//
//        JLabel upcomingC1 = new JLabel("<html><b>14:00</b><BR><BR>16:00</html>");
//        upcomingC1.setFont(new Font("Monsterrat", Font.PLAIN, 12));
//        upcomingC1.setForeground(Color.decode("#FFFFFF"));
//        upcomingC1.setBackground(Color.decode("#40508a"));
//        upcomingC1.setOpaque(true);
//        upcomingC1.setBounds(66, 180, 320, 60);
//        instructorDash.add(upcomingC1);
//
//        JLabel upcomingC2 = new JLabel("Class 2 Info");
//        upcomingC2.setFont(new Font("Monsterrat", Font.PLAIN, 16));
//        upcomingC2.setForeground(Color.decode("#FFFFFF"));
//        upcomingC2.setBackground(Color.decode("#40508a"));
//        upcomingC2.setOpaque(true);
//        upcomingC2.setBounds(66, 275, 320, 60);
//        upcomingC2.setOpaque(true);
//        instructorDash.add(upcomingC2);
//
//        JLabel upcomingC3 = new JLabel("Class 3 Info");
//        upcomingC3.setFont(new Font("Monsterrat", Font.PLAIN, 16));
//        upcomingC3.setForeground(Color.decode("#FFFFFF"));
//        upcomingC3.setBackground(Color.decode("#40508a"));
//        upcomingC3.setOpaque(true);
//        upcomingC3.setBounds(66, 370, 320, 60);
//        instructorDash.add(upcomingC3);
//
//        viewEntireSchedule = roundBtn.genRoundBtn("View Entire Schedule", 30,
//                "#001561", false);
//        viewEntireSchedule.setFont(new Font("Monsterrat", Font.BOLD, 20));
//        viewEntireSchedule.setForeground(Color.decode("#FFFFFF"));
//        viewEntireSchedule.setBounds(66, 445, 320, 40);
//        viewEntireSchedule.addActionListener(this);
//        instructorDash.add(viewEntireSchedule);
//
//        enrolBtn = roundBtn.genRoundBtn("Manage Classes", 30, "#001561", false);
//        enrolBtn.setFont(new Font("Monsterrat", Font.BOLD, 23));
//        enrolBtn.setForeground(Color.decode("#FFFFFF"));
//        enrolBtn.setBounds(490, 180, 218, 75);
//        enrolBtn.addActionListener(this);
//        instructorDash.add(enrolBtn);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == viewClassOfferings) {
//            viewOfferingsPanel.add(back2dash);
//            cardLayout.show(getContentPane(), "Offerings");
//        }
//        else if (e.getSource() == dailyWorkoutTips) {
//            workoutTipsPanel.add(back2dash);
//            cardLayout.show(getContentPane(), "Daily Workout Tips");
//        }
//        else if (e.getSource() == enrolBtn) {
//            enrolPanel.add(back2dash);
//            Object[][] enrolTableData = new Object[database.gym.getWorkouts().size()][];
//            int _etd_idx = 0;
//            for (Workout c : database.gym.getWorkouts()) {
//                enrolTableData[_etd_idx] = new Object[]{c.name, "", ""};
//                _etd_idx++;
//            }
//            String[] enrolTableCols = {"Workout", "Room", "Time"};
//            enrolTable = new JTable(enrolTableData, enrolTableCols);
//            JScrollPane p = new JScrollPane(enrolTable);
//            p.setBounds(50, 50, 500, 500);
//            enrolPanel.add(p);
//
//            cardLayout.show(getContentPane(), "Enrolment");
//        } else if (e.getSource() == addWorkoutBtn) {
//            String name = JOptionPane.showInputDialog(this,
//                    "Course name?", null);
//            if ((name != null) && (name.length() > 0)) {
//                database.gym.addWorkout(new Workout(name));
//            }
//        }
//        else if (e.getSource() == settings) {
//            settingsPanel.add(firstNameLabel);
//            settingsPanel.add(firstNameField);
//            firstNameField.setText(database.activeUser.firstName); // set FN
//            settingsPanel.add(lastNameLabel);
//            settingsPanel.add(lastNameField);
//            lastNameField.setText(database.activeUser.lastName); // set LN
//            settingsPanel.add(emailLabel);
//            settingsPanel.add(emailField);
//            emailField.setText(database.activeUser.email); // set email
//            settingsPanel.add(signupUsernameLabel);
//            settingsPanel.add(userField);
//            userField.setText(database.activeUser.getName());
//            settingsPanel.add(err1);
//            settingsPanel.add(err2);
//            settingsPanel.add(err3);
//            settingsPanel.add(err4);
//            settingsPanel.add(back2dash);
//            back2dash.setBounds(12, 12, 138, 68);
//            cardLayout.show(getContentPane(), "Account Settings");
//        }
//        else if (e.getSource() == viewEntireSchedule) {
//            schedulePanel.add(back2dash);
//
//            Object[][] scheduleTableData = new Object[10][8];
//            for (int i = 0; i < 10; i++) {
//                scheduleTableData[i][0] = (8 + i) + ":00";
//            }
//            String[] scheduleTableCols = {"", "M", "T", "W", "Tu", "F", "S", "Su"};
//            scheduleTable = new JTable(scheduleTableData, scheduleTableCols);
//            scheduleTable.setRowHeight(50);
//            JScrollPane p = new JScrollPane(scheduleTable);
//            p.setBounds(50, 50, 500, 500);
//            schedulePanel.add(p);
//
//            cardLayout.show(getContentPane(), "Schedule");
//        }
//        else if (e.getSource() == logout_icon) {
//            database.activeUser = null;
//            System.out.println("Logged out successfully");
//            cardLayout.show(getContentPane(), "Login");
//        }
    }
}
