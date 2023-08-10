package fd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class DashboardFrame extends JFrame implements ActionListener {
    private JButton viewEntireSchedule, enrolBtn, logout, logout_icon, settings,
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

        logout_icon = new JButton();
        logout_icon.setBounds(640, 15, 40, 30);
        logout_icon.setBorderPainted(false); // Hide the button border
        logout_icon.setFocusPainted(false); // Remove the focus indication
        logout_icon.setContentAreaFilled(false);
        logout_icon.setIcon(new ImageIcon("images/logout_icon.png"));
        logout_icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        });
        logout_icon.addActionListener(this);
        this.add(logout_icon);

        logout = UI.genRoundBtn("", 23, "#172A87", false);
        logout.setFont(UI.MB19);
        logout.setForeground(Color.decode("#FFFFFF"));
        logout.setBounds(640, 15, 40, 30);
        logout.setOpaque(false);
        this.add(logout);

        settings = UI.genRoundBtn("Account Settings ⚙", 30, "#172A87", false);
        settings.setFont(UI.MB20);
        settings.setForeground(Color.decode("#FFFFFF"));
        settings.setBounds(413, 300, 280, 140);
        settings.addActionListener(this);
        this.add(settings);

        upcomingC = new JLabel("Upcoming Classes");
        upcomingC.setFont(UI.MB20);
        upcomingC.setForeground(Color.decode("#000000"));
        upcomingC.setBounds(66, 130, 422, 47);

        upcomingC1 = new JLabel("Class 1 Info");
        upcomingC1.setFont(UI.MP16);
        upcomingC1.setForeground(Color.decode("#FFFFFF"));
        upcomingC1.setBackground(Color.decode("#40508a"));
        upcomingC1.setOpaque(true);
        upcomingC1.setBounds(66, 180, 320, 60);

        upcomingC2 = new JLabel("Class 2 Info");
        upcomingC2.setFont(UI.MP16);
        upcomingC2.setForeground(Color.decode("#FFFFFF"));
        upcomingC2.setBackground(Color.decode("#40508a"));
        upcomingC2.setOpaque(true);
        upcomingC2.setBounds(66, 275, 320, 60);
        upcomingC2.setOpaque(true);

        upcomingC3 = new JLabel("Class 3 Info");
        upcomingC3.setFont(UI.MP16);
        upcomingC3.setForeground(Color.decode("#FFFFFF"));
        upcomingC3.setBackground(Color.decode("#40508a"));
        upcomingC3.setOpaque(true);
        upcomingC3.setBounds(66, 370, 320, 60);

        viewEntireSchedule = UI.genRoundBtn("View Entire Schedule", 30,
                "#172A87", false);
        viewEntireSchedule.setFont(UI.MB20);
        viewEntireSchedule.setForeground(Color.decode("#FFFFFF"));
        viewEntireSchedule.setBounds(66, 445, 315, 45);
        viewEntireSchedule.addActionListener(this);

        enrolBtn = UI.genRoundBtn("Manage Classes", 30, "#172A87", false);
        enrolBtn.setFont(UI.MB23);
        enrolBtn.setForeground(Color.decode("#FFFFFF"));
        enrolBtn.setBounds(413, 123, 280, 140);
        enrolBtn.addActionListener(this);

        manageRooms = UI.genRoundBtn("Manage Rooms", 30, "#172A87", false);
        manageRooms.setFont(UI.MB23);
        manageRooms.setForeground(Color.decode("#FFFFFF"));
        manageRooms.setBounds(107, 123, 280, 140);
        manageRooms.addActionListener(this);

        manageUsers = UI.genRoundBtn("Manage Users", 30, "#172A87", false);
        manageUsers.setFont(UI.MB23);
        manageUsers.setForeground(Color.decode("#FFFFFF"));
        manageUsers.setBounds(107, 300, 280, 140);
        manageUsers.addActionListener(this);

        coverBG = UI.genRoundLabel("", 20, "#FAFAF2");
        coverBG.setBounds(0, 88, 800, 522);
        this.add(coverBG);

    }

    public void userRefresh() {
        enrolBtn.setBounds(490, 180, 218, 75);
        settings.setBounds(490, 380, 218, 75);

        List<String[]> next = App.db.getNextThreeOfferings();
        if (!next.isEmpty()) {
            this.add(upcomingC);
            this.add(upcomingC1);
            this.add(upcomingC2);
            this.add(upcomingC3);
            this.add(viewEntireSchedule);
            this.add(coverBG);
            upcomingC1.setText("<html><b>" + next.get(0)[0] + "</b><BR>" + next.get(0)[1] +"<BR>" + next.get(0)[2] + "</html>");
            upcomingC2.setText("<html><b>" + next.get(1)[0] + "</b><BR>" + next.get(1)[1] +"<BR>" + next.get(1)[2] + "</html>");
            upcomingC3.setText("<html><b>" + next.get(2)[0] + "</b><BR>" + next.get(2)[1] +"<BR>" + next.get(2)[2] + "</html>");
        }
        else {
            this.add(coverBG);
            this.remove(upcomingC);
            this.remove(upcomingC1);
            this.remove(upcomingC2);
            this.remove(upcomingC3);
            this.remove(viewEntireSchedule);
        }
        this.add(enrolBtn);
        this.add(coverBG);
        this.remove(manageRooms);
        this.remove(manageUsers);
        courseBrowser.userView();
    }

    public void instructorRefresh() {
        userRefresh();
        enrolBtn.setBounds(490, 180, 218, 75);
        settings.setBounds(490, 380, 218, 75);
        courseBrowser.instructorView();
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
        enrolBtn.setBounds(413, 123, 280, 140);
        settings.setBounds(413, 300, 280, 140);
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
        else if (e.getSource() == logout_icon)
            App.logout();
        else if (e.getSource() == manageRooms)
            roomManager.refreshShow();
        else if (e.getSource() == manageUsers)
            userManager.refreshShow();
    }
}
