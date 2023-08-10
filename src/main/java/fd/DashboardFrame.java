package fd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DashboardFrame extends JFrame implements ActionListener {
    private final JButton viewEntireSchedule, enrolBtn, logout, settings,
            manageRooms, manageUsers;
    private final JLabel welcomeUser; // basically title label (Welcome back, [User Name]!)
    private final JLabel c1box, c2box, c3box; // used to contain info for upcoming 3 classes
    private final JLabel c1name, c1time, c1room, c2name, c2time, c2room, c3name, c3time, c3room;
    private final JLabel upcomingC, no_classes;
    private final JLabel coverBG, userEnrolBtnCover, viewFullScheduleCover;

    private final CourseFrame courseBrowser = new CourseFrame();
    private final ScheduleFrame scheduleView = new ScheduleFrame();
    private final UserInfoFrame userInfoEdit = new UserInfoFrame();
    private final ManageUserFrame userManager = new ManageUserFrame();
    private final ManageRoomFrame roomManager = new ManageRoomFrame();

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

        // class 1 info
        c1name = new JLabel("Class 1 Name");
        c1name.setFont(UI.MB18);
        c1name.setForeground(Color.decode("#FFFFFF"));
        c1name.setBounds(100, 331, 171, 22);
        c1time = new JLabel("Class 1 Time");
        c1time.setFont(UI.MB12);
        c1time.setForeground(Color.decode("#000000"));
        c1time.setBounds(100, 358, 171, 22);
        c1room = new JLabel("Class 1 Room");
        c1room.setFont(UI.MB12);
        c1room.setForeground(Color.decode("#000000"));
        c1room.setBounds(100, 384, 171, 22);
        c1box = UI.genRoundLabel("", 25, "#FFC278");
        c1box.setBackground(Color.decode("#40508a"));
        c1box.setOpaque(true);
        c1box.setBounds(83, 316, 197, 104);

        // class 1 info
        c2name = new JLabel("Class 2 Name");
        c2name.setFont(UI.MB18);
        c2name.setForeground(Color.decode("#FFFFFF"));
        c2name.setBounds(318, 331, 171, 22);
        c2time = new JLabel("Class 2 Time");
        c2time.setFont(UI.MB12);
        c2time.setForeground(Color.decode("#000000"));
        c2time.setBounds(318, 358, 171, 22);
        c2room = new JLabel("Class 2 Room");
        c2room.setFont(UI.MB12);
        c2room.setForeground(Color.decode("#000000"));
        c2room.setBounds(318, 384, 171, 22);
        c2box = UI.genRoundLabel("", 25, "#FFC278");
        c2box.setBackground(Color.decode("#40508a"));
        c2box.setOpaque(true);
        c2box.setBounds(301, 316, 197, 104);

        // class 1 info
        c3name = new JLabel("Class 2 Name");
        c3name.setFont(UI.MB18);
        c3name.setForeground(Color.decode("#FFFFFF"));
        c3name.setBounds(536, 331, 171, 22);
        c3time = new JLabel("Class 2 Time");
        c3time.setFont(UI.MB12);
        c3time.setForeground(Color.decode("#000000"));
        c3time.setBounds(536, 358, 171, 22);
        c3room = new JLabel("Class 2 Room");
        c3room.setFont(UI.MB12);
        c3room.setForeground(Color.decode("#000000"));
        c3room.setBounds(536, 384, 171, 22);
        c3box = UI.genRoundLabel("", 25, "#FFC278");
        c3box.setBackground(Color.decode("#40508a"));
        c3box.setOpaque(true);
        c3box.setBounds(519, 316, 197, 104);

        no_classes = new JLabel("");
        no_classes.setIcon(new ImageIcon("images/no_classes.png"));
        no_classes.setBounds(64, 316, 672, 122);

        upcomingC = new JLabel("Upcoming Classes");
        upcomingC.setIcon(new ImageIcon("images/upcoming_classes.png"));
        upcomingC.setBounds(64, 263, 672, 175);

        viewFullScheduleCover = new JLabel("");
        viewFullScheduleCover.setBounds(64, 463, 569, 79);
        viewFullScheduleCover.setIcon(new ImageIcon("images/viewFullScheduleCover.png"));
        viewFullScheduleCover.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        viewEntireSchedule = UI.genImageButton("images/viewEntireSchedule.png", 672, 79);
        viewEntireSchedule.setBounds(64, 463, 672, 79);
        viewEntireSchedule.addActionListener(this);

        enrolBtn = UI.genImageButton("images/admin_manage_class.png", 672, 100);
        enrolBtn.setBounds(64, 138, 672, 100);
        enrolBtn.addActionListener(this);

        userEnrolBtnCover = new JLabel("");
        userEnrolBtnCover.setIcon(new ImageIcon("images/user_manage_class.png"));
        userEnrolBtnCover.setBounds(64, 138, 672, 100);
        userEnrolBtnCover.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

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
        this.add(userEnrolBtnCover);
        this.add(enrolBtn);

        List<String[]> next = App.db.getNextThreeOfferings();
        if (!next.isEmpty()) {
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
            this.add(viewFullScheduleCover);
            this.add(viewEntireSchedule);
            this.remove(no_classes);
            c1name.setText(next.get(0)[0]);
            c1room.setText(next.get(0)[1]);
            c1time.setText(timeConversion(next.get(0)[2]));
            c2name.setText(next.get(1)[0]);
            c2room.setText(next.get(1)[1]);
            c2time.setText(timeConversion(next.get(1)[2]));
            c3name.setText(next.get(2)[0]);
            c3room.setText(next.get(2)[1]);
            c3time.setText(timeConversion(next.get(2)[2]));
            this.add(upcomingC);
        } else {
            this.remove(c1name);
            this.remove(c1time);
            this.remove(c1room);
            this.remove(c1box);
            this.remove(c2name);
            this.remove(c2time);
            this.remove(c2room);
            this.remove(c2box);
            this.remove(c3name);
            this.remove(c3time);
            this.remove(c3room);
            this.remove(c3box);
            this.remove(viewFullScheduleCover);
            this.remove(viewEntireSchedule);
            this.add(no_classes);
            this.add(upcomingC);
        }
        this.add(enrolBtn);
        this.remove(manageRooms);
        this.remove(manageUsers);
        courseBrowser.userView();
        this.add(coverBG);
    }

    public void instructorRefresh() {
        userRefresh();
        this.remove(userEnrolBtnCover);
        courseBrowser.instructorView();
        this.add(coverBG);
    }

    public void adminRefresh() {
        this.remove(upcomingC);
        this.remove(c1name);
        this.remove(c1time);
        this.remove(c1room);
        this.remove(c1box);
        this.remove(c2name);
        this.remove(c2time);
        this.remove(c2room);
        this.remove(c2box);
        this.remove(c3name);
        this.remove(c3time);
        this.remove(c3room);
        this.remove(c3box);
        this.remove(viewEntireSchedule);
        this.remove(no_classes);
        this.remove(userEnrolBtnCover);
        this.remove(viewFullScheduleCover);
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

    public static String timeConversion(String input) {
        return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                .format(DateTimeFormatter.ofPattern("MM/dd/yy @ ha"))
                .replace("a.m.", "AM")
                .replace("p.m.", "PM");
    }
}
