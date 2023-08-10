package fd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DashboardFrame extends JFrame implements ActionListener {
    private JButton viewEntireSchedule, enrolBtn, logout, settings,
            manageRooms, manageUsers;
    private JLabel welcomeUser, upcomingC, upcomingC1, upcomingC2, upcomingC3;
    private JLabel coverBG;

    private CourseFrame courseBrowser = new CourseFrame();
    private ScheduleFrame scheduleView = new ScheduleFrame();
    private UserInfoFrame userInfoEdit = new UserInfoFrame();
    private ManageUserFrame userManager = new ManageUserFrame();
    private ManageRoomFrame roomManager = new ManageRoomFrame();

    public DashboardFrame() {
        setTitle("Dashboard"); // window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ends program if x
        setSize(800, 600); // window dimensions
        setResizable(false); // disables resizing
        setLocationRelativeTo(null); // centers ui if left 'null'
        setLayout(null); // absolute layout
        getContentPane().setBackground(Color.decode("#8F98FF")); // bg colour

        welcomeUser = new JLabel("");
        welcomeUser.setFont(UI.MB23);
        welcomeUser.setForeground(Color.decode("#FFFFFF"));
        welcomeUser.setBounds(34, 41, 422, 47);
        this.add(welcomeUser);

        logout = UI.genImageButton("images/logout.png", 25, 25);
        logout.setBounds(729, 44, 25, 25);
        logout.addActionListener(this);
        this.add(logout);

        settings = UI.genImageButton("images/gear.png", 27, 26);
        settings.setBounds(681, 44, 27, 27);
        settings.addActionListener(this);
        this.add(settings);

        upcomingC1 = UI.genRoundLabel("Class 1 Info", 25, "#FFC278");
        upcomingC1.setFont(UI.MP16);
        upcomingC1.setForeground(Color.decode("#FFFFFF"));
        upcomingC1.setBackground(Color.decode("#40508a"));
        upcomingC1.setOpaque(true);
        upcomingC1.setBounds(83, 316, 197, 104);

        upcomingC2 = UI.genRoundLabel("Class 2 Info", 25, "#FFC278");
        upcomingC2.setFont(UI.MP16);
        upcomingC2.setForeground(Color.decode("#FFFFFF"));
        upcomingC2.setBackground(Color.decode("#40508a"));
        upcomingC2.setOpaque(true);
        upcomingC2.setBounds(301, 316, 197, 104);

        upcomingC3 = UI.genRoundLabel("Class 3 Info", 25, "#FFC278");
        upcomingC3.setFont(UI.MP16);
        upcomingC3.setForeground(Color.decode("#FFFFFF"));
        upcomingC3.setBackground(Color.decode("#40508a"));
        upcomingC3.setOpaque(true);
        upcomingC3.setBounds(519, 316, 197, 104);

        upcomingC = new JLabel("Upcoming Classes");
        upcomingC.setIcon(new ImageIcon("images/upcoming_classes.png"));
        upcomingC.setBounds(64, 263, 672, 175);

        viewEntireSchedule = UI.genRoundBtn("View Entire Schedule", 30,
                "#172A87", false);
        viewEntireSchedule.setFont(UI.MB20);
        viewEntireSchedule.setForeground(Color.decode("#FFFFFF"));
        viewEntireSchedule.setBounds(66, 445, 315, 45);
        viewEntireSchedule.addActionListener(this);

        enrolBtn = UI.genImageButton("images/manage_classes.png", 672, 100);
        enrolBtn.setBounds(64, 138, 672, 100);
        enrolBtn.addActionListener(this);

        manageRooms = UI.genImageButton("images/manage_rooms.png", 672, 100);
        manageRooms.setBounds(64, 278, 672, 100);
        manageRooms.addActionListener(this);

        manageUsers = UI.genImageButton("images/manage_users.png", 672, 100);
        manageUsers.setBounds(64, 418, 672, 100);
        manageUsers.addActionListener(this);

        coverBG = UI.genRoundLabel("", 20, "#FAFAF2");
        coverBG.setBounds(0, 88, 800, 522);
        this.add(coverBG);

    }

    public void userRefresh() {
        enrolBtn.setBounds(64, 138, 672, 100);

        List<String[]> next = App.db.getNextThreeOfferings();
        System.out.println(next);
        if (!next.isEmpty()) {
            this.add(upcomingC1);
            this.add(upcomingC2);
            this.add(upcomingC3);
            this.add(upcomingC);
            this.add(viewEntireSchedule);
            upcomingC1.setText("<html><b>" + next.get(0)[0] + "</b><BR>" + next.get(0)[1] +"<BR>" + next.get(0)[2] + "</html>");
            upcomingC2.setText("<html><b>" + next.get(1)[0] + "</b><BR>" + next.get(1)[1] +"<BR>" + next.get(1)[2] + "</html>");
            upcomingC3.setText("<html><b>" + next.get(2)[0] + "</b><BR>" + next.get(2)[1] +"<BR>" + next.get(2)[2] + "</html>");
        }
        else {
            this.remove(upcomingC1);
            this.remove(upcomingC2);
            this.remove(upcomingC3);
            this.remove(upcomingC);
            this.remove(viewEntireSchedule);
        }
        this.add(enrolBtn);
        this.remove(manageRooms);
        this.remove(manageUsers);
        courseBrowser.userView();
        this.add(coverBG);
    }

    public void instructorRefresh() {
        userRefresh();
        enrolBtn.setBounds(64, 138, 672, 100);
        courseBrowser.instructorView();
        this.add(coverBG);
    }

    public void adminRefresh() {
        this.remove(upcomingC);
        this.remove(upcomingC1);
        this.remove(upcomingC2);
        this.remove(upcomingC3);
        this.remove(viewEntireSchedule);
        this.add(enrolBtn);
        this.add(manageRooms);
        this.add(manageUsers);
        this.add(coverBG);
        enrolBtn.setBounds(64, 138, 672, 100);
        courseBrowser.adminView();
    }

    public void refreshShow() {
        welcomeUser.setText("Welcome back, " + App.db.getActiveUserFirstName() + "!");
        this.setVisible(true);
        App.db.dashRefresh(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enrolBtn)
            courseBrowser.refreshShow();
        else if (e.getSource() == settings)
            userInfoEdit.refreshShow();
        else if (e.getSource() == viewEntireSchedule)
            scheduleView.refreshShow();
        else if (e.getSource() == logout)
            App.logout();
        else if (e.getSource() == manageRooms)
            roomManager.refreshShow();
        else if (e.getSource() == manageUsers)
            userManager.refreshShow();
    }
}
