package fd;

import ia.DashboardPresenter;
import ia.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DashboardFrame extends JFrame implements ActionListener, View<DashboardPresenter> {
    private final JButton viewEntireSchedule, enrolBtn, logout, settings, manageRooms, manageUsers;
    private final JLabel welcomeUser, c1box, c2box, c3box, c1name, c1time, c1room, c2name, c2time, c2room, c3name, c3time, c3room, upcomingC, no_classes, userEnrolBtnCover, viewFullScheduleCover;

    private final CourseEnrollmentFrame courseBrowser;
    private final ScheduleFrame scheduleView;
    private final UserInfoFrame userInfoEdit;
    private final ManageUserFrame userManager;
    private final ManageRoomFrame roomManager;

    private DashboardPresenter dashboardPresenter;

    public DashboardFrame(ScheduleFrame scheduleView, CourseEnrollmentFrame courseBrowser, UserInfoFrame userInfoEdit, ManageUserFrame userManager, ManageRoomFrame roomManager) {
        this.scheduleView = scheduleView;
        this.courseBrowser = courseBrowser;
        this.userInfoEdit = userInfoEdit;
        this.userManager = userManager;
        this.roomManager = roomManager;

        setTitle("Dashboard"); // window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ends program if x
        setSize(800, 600); // window dimensions
        setResizable(false); // disables resizing
        setLocationRelativeTo(null); // centers ui if left 'null'
        setLayout(null); // absolute layout
        getContentPane().setBackground(Color.decode("#8F98FF")); // bg colour

        welcomeUser = new JLabel("");
        welcomeUser.setFont(ComponentFactory.MB23);
        welcomeUser.setForeground(Color.decode("#FFFFFF"));
        welcomeUser.setBounds(34, 41, 422, 47);
        this.add(welcomeUser);

        logout = ComponentFactory.genImageButton("images/logout.png", 25, 25);
        logout.setBounds(729, 44, 25, 25);
        logout.addActionListener(this);
        this.add(logout);

        settings = ComponentFactory.genImageButton("images/gear.png", 27, 26);
        settings.setBounds(681, 44, 27, 27);
        settings.addActionListener(this);
        this.add(settings);

        // class 1 info
        c1name = new JLabel("Class 1 Name");
        c1name.setFont(ComponentFactory.MB18);
        c1name.setForeground(Color.decode("#FFFFFF"));
        c1name.setBounds(100, 331, 171, 22);
        c1time = new JLabel("Class 1 Time");
        c1time.setFont(ComponentFactory.MB12);
        c1time.setForeground(Color.decode("#000000"));
        c1time.setBounds(100, 358, 171, 22);
        c1room = new JLabel("Class 1 Room");
        c1room.setFont(ComponentFactory.MB12);
        c1room.setForeground(Color.decode("#000000"));
        c1room.setBounds(100, 384, 171, 22);
        c1box = ComponentFactory.genRoundLabel("", 25, "#FFC278");
        c1box.setBackground(Color.decode("#40508a"));
        c1box.setOpaque(true);
        c1box.setBounds(83, 316, 197, 104);

        // class 1 info
        c2name = new JLabel("Class 2 Name");
        c2name.setFont(ComponentFactory.MB18);
        c2name.setForeground(Color.decode("#FFFFFF"));
        c2name.setBounds(318, 331, 171, 22);
        c2time = new JLabel("Class 2 Time");
        c2time.setFont(ComponentFactory.MB12);
        c2time.setForeground(Color.decode("#000000"));
        c2time.setBounds(318, 358, 171, 22);
        c2room = new JLabel("Class 2 Room");
        c2room.setFont(ComponentFactory.MB12);
        c2room.setForeground(Color.decode("#000000"));
        c2room.setBounds(318, 384, 171, 22);
        c2box = ComponentFactory.genRoundLabel("", 25, "#FFC278");
        c2box.setBackground(Color.decode("#40508a"));
        c2box.setOpaque(true);
        c2box.setBounds(301, 316, 197, 104);

        // class 1 info
        c3name = new JLabel("Class 2 Name");
        c3name.setFont(ComponentFactory.MB18);
        c3name.setForeground(Color.decode("#FFFFFF"));
        c3name.setBounds(536, 331, 171, 22);
        c3time = new JLabel("Class 2 Time");
        c3time.setFont(ComponentFactory.MB12);
        c3time.setForeground(Color.decode("#000000"));
        c3time.setBounds(536, 358, 171, 22);
        c3room = new JLabel("Class 2 Room");
        c3room.setFont(ComponentFactory.MB12);
        c3room.setForeground(Color.decode("#000000"));
        c3room.setBounds(536, 384, 171, 22);
        c3box = ComponentFactory.genRoundLabel("", 25, "#FFC278");
        c3box.setBackground(Color.decode("#40508a"));
        c3box.setOpaque(true);
        c3box.setBounds(519, 316, 197, 104);

        no_classes = new JLabel("");
        no_classes.setIcon(new ImageIcon("images/no_classes.png"));
        no_classes.setBounds(64, 316, 672, 122);

        this.add(c1name);
        this.add(c1time);
        this.add(c1room);
        this.add(c1box);
        this.add(c2name);
        this.add(c2time);
        this.add(c2room);
        this.add(c2box);
        this.add(c3name);
        this.add(c3time);
        this.add(c3room);
        this.add(c3box);
        this.add(no_classes);

        upcomingC = new JLabel("Upcoming Classes");
        upcomingC.setIcon(new ImageIcon("images/upcoming_classes.png"));
        upcomingC.setBounds(64, 263, 672, 175);
        this.add(upcomingC);

        viewFullScheduleCover = new JLabel("");
        viewFullScheduleCover.setBounds(64, 463, 569, 79);
        viewFullScheduleCover.setIcon(new ImageIcon("images/viewFullScheduleCover.png"));
        viewFullScheduleCover.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        viewEntireSchedule = ComponentFactory.genImageButton("images/viewEntireSchedule.png", 672, 79);
        viewEntireSchedule.setBounds(64, 463, 672, 79);
        viewEntireSchedule.addActionListener(this);

        this.add(viewFullScheduleCover);
        this.add(viewEntireSchedule);

        enrolBtn = ComponentFactory.genImageButton("images/admin_manage_class.png", 672, 100);
        enrolBtn.setBounds(64, 138, 672, 100);
        enrolBtn.addActionListener(this);
        this.add(enrolBtn);

        userEnrolBtnCover = new JLabel("");
        userEnrolBtnCover.setIcon(new ImageIcon("images/user_manage_class.png"));
        userEnrolBtnCover.setBounds(64, 138, 672, 100);
        userEnrolBtnCover.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.add(userEnrolBtnCover);

        manageRooms = ComponentFactory.genImageButton("images/manage_rooms.png", 672, 100);
        manageRooms.setBounds(64, 278, 672, 100);
        manageRooms.addActionListener(this);
        this.add(manageRooms);

        manageUsers = ComponentFactory.genImageButton("images/manage_users.png", 672, 100);
        manageUsers.setBounds(64, 418, 672, 100);
        manageUsers.addActionListener(this);
        this.add(manageUsers);

        JLabel coverBG = ComponentFactory.genRoundLabel("", 20, "#FAFAF2");
        coverBG.setBounds(0, 88, 800, 522);
        this.add(coverBG);
    }

    public void userRefresh() {
        userEnrolBtnCover.setVisible(true);
        enrolBtn.setVisible(true);

        List<String[]> next = dashboardPresenter.getNextThreeOfferings();
        if (!next.isEmpty()) {
            c1name.setVisible(true);
            c1time.setVisible(true);
            c1room.setVisible(true);
            c1box.setVisible(true);
            c2name.setVisible(true);
            c2time.setVisible(true);
            c2room.setVisible(true);
            c2box.setVisible(true);
            c3name.setVisible(true);
            c3time.setVisible(true);
            c3room.setVisible(true);
            c3box.setVisible(true);
            viewFullScheduleCover.setVisible(true);
            viewEntireSchedule.setVisible(true);
            no_classes.setVisible(false);
            c1name.setText(next.get(0)[0]);
            c1room.setText(next.get(0)[1]);
            c1time.setText(timeConversion(next.get(0)[2]));
            c2name.setText(next.get(1)[0]);
            c2room.setText(next.get(1)[1]);
            c2time.setText(timeConversion(next.get(1)[2]));
            c3name.setText(next.get(2)[0]);
            c3room.setText(next.get(2)[1]);
            c3time.setText(timeConversion(next.get(2)[2]));
            upcomingC.setVisible(true);
        } else {
            c1name.setVisible(false);
            c1time.setVisible(false);
            c1room.setVisible(false);
            c1box.setVisible(false);
            c2name.setVisible(false);
            c2time.setVisible(false);
            c2room.setVisible(false);
            c2box.setVisible(false);
            c3name.setVisible(false);
            c3time.setVisible(false);
            c3room.setVisible(false);
            c3box.setVisible(false);
            viewFullScheduleCover.setVisible(false);
            viewEntireSchedule.setVisible(false);
            no_classes.setVisible(true);
            upcomingC.setVisible(true);
        }
        enrolBtn.setVisible(true);
        manageRooms.setVisible(false);
        manageUsers.setVisible(false);
        courseBrowser.userView();
    }

    public void instructorRefresh() {
        userRefresh();
        userEnrolBtnCover.setVisible(false);
        courseBrowser.instructorView();
    }

    public void adminRefresh() {
        upcomingC.setVisible(false);
        c1name.setVisible(false);
        c1time.setVisible(false);
        c1room.setVisible(false);
        c1box.setVisible(false);
        c2name.setVisible(false);
        c2time.setVisible(false);
        c2room.setVisible(false);
        c2box.setVisible(false);
        c3name.setVisible(false);
        c3time.setVisible(false);
        c3room.setVisible(false);
        c3box.setVisible(false);
        viewEntireSchedule.setVisible(false);
        no_classes.setVisible(false);
        userEnrolBtnCover.setVisible(false);
        viewFullScheduleCover.setVisible(false);
        enrolBtn.setVisible(true);
        manageRooms.setVisible(true);
        manageUsers.setVisible(true);
        enrolBtn.setBounds(64, 138, 672, 100);
        courseBrowser.adminView();
    }

    public void showDashboard() {
        this.setVisible(true);
    }

    public void setGreetingMessage(String message) {
        welcomeUser.setText(message);
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
            dashboardPresenter.logoutRequested();
        else if (e.getSource() == manageRooms)
            roomManager.refreshShow();
        else if (e.getSource() == manageUsers)
            userManager.refreshShow();
    }

    public static String timeConversion(String input) {
        return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                .format(DateTimeFormatter.ofPattern("MM/dd/yy @ ha"))
                .replace("a.m.", "AM")
                .replace("p.m.", "PM");
    }
    @Override
    public void displayInfoMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void setPresenter(DashboardPresenter presenter) {
        this.dashboardPresenter = presenter;
    }
}
