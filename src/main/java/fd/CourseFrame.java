package fd;

import ebr.Workout;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseFrame extends JDialog implements ActionListener {
    private JButton addCourse;
    public CourseFrame() {
        setTitle("Courses"); // window title
        setSize(800, 600); // window dimensions
        setResizable(false); // disables resizing
        setLocationRelativeTo(null); // centers ui if left 'null'
        setLayout(null);
        setModal(true);

        addCourse = new JButton("add");
        addCourse.setBounds(0, 0, 100, 40);
        addCourse.addActionListener(this);
        this.add(addCourse);

        Object[][] enrolTableData = new Object[App.db.gym.getWorkouts().size()][];
        int _etd_idx = 0;
        // todo rid this dependency
        for (Workout c : App.db.gym.getWorkouts()) {
            enrolTableData[_etd_idx] = new Object[]{c.name, "", ""};
            _etd_idx++;
        }
        String[] enrolTableCols = {"Workout", "Room", "Time"};
        // todo use TableModel instead of this ctor
        JTable enrolTable = new JTable(enrolTableData, enrolTableCols);
        JScrollPane p = new JScrollPane(enrolTable);
        p.setBounds(50, 50, 500, 500);
        this.add(p);

    }
    public void refreshShow() {
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCourse) {
            String name = JOptionPane.showInputDialog(this, "Course name?", null);
            if ((name != null) && (!name.isEmpty())) {
                App.db.gym.addWorkout(new Workout(name));
            }
        }
    }
}
