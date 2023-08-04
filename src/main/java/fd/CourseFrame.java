package fd;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CourseFrame extends JDialog implements ActionListener {
    private JButton addCourse, editCourse;
    private AbstractTableModel courseTable = new AbstractTableModel() {
        private final String[] enrolTableCols = {"Workout", "Room", "Time", "Instructor", "Status"};
        public int getColumnCount() { return 5; }
        public int getRowCount() { return (int)App.db.getCurrentWorkouts().size(); }
        public String getColumnName(int col) {
            return enrolTableCols[col];
        }
        public Object getValueAt(int row, int col) {
            List<String[]> workouts = App.db.getCurrentWorkouts();
            return workouts.get(row)[col].isEmpty() ? "TBD" : workouts.get(row)[col];
        }
    };
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

        editCourse = new JButton("edit");
        editCourse.setBounds(0, 0, 100, 40);
        editCourse.addActionListener(this);
        this.add(editCourse);

        JTable enrolTable = new JTable(courseTable);
        enrolTable.getTableHeader().setResizingAllowed(false);
        enrolTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane p = new JScrollPane(enrolTable);
        p.setBounds(0, 40, 800, 500);
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
                String capacity = JOptionPane.showInputDialog(this, "Capacity?", "50");
                if ((capacity != null) && (!capacity.isEmpty())) {
                    try {
                        App.db.addWorkout(name, Integer.parseInt(capacity));
                        courseTable.fireTableDataChanged();
                    }
                    catch (NumberFormatException exp) {
                        JOptionPane.showMessageDialog(this, "Invalid capacity!");
                    }
                }
            }
        }
    }
}
