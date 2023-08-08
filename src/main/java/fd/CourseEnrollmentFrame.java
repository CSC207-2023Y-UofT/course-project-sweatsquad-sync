package fd;

import ia.CourseEnrolmentPresenter;
import ia.View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CourseEnrollmentFrame extends JDialog implements ActionListener, View<CourseEnrolmentPresenter> {

    private CourseEnrolmentPresenter presenter;

    private JButton addCourse, editCourse, removeCourse, enrolCourse;
    private AbstractTableModel courseTable = new AbstractTableModel() {
        private final String[] enrolTableCols = {"Workout", "Room", "Time", "Instructor", "Status"};
        public int getColumnCount() { return enrolTableCols.length; }
        public int getRowCount() { return (int)presenter.getCurrentWorkouts().size(); }
        public String getColumnName(int col) {
            return enrolTableCols[col];
        }
        public Object getValueAt(int row, int col) {
            List<String[]> workouts = presenter.getCurrentWorkouts();
            return workouts.get(row)[col].isEmpty() ? "TBD" : workouts.get(row)[col];
        }
    };
    public CourseEnrollmentFrame() {
        setTitle("Courses"); // window title
        setSize(800, 600); // window dimensions
        setResizable(false); // disables resizing
        setLocationRelativeTo(null); // centers ui if left 'null'
        setLayout(null);
        setModal(true);

        addCourse = new JButton("Add");
        addCourse.setBounds(0, 0, 100, 40);
        addCourse.addActionListener(this);

        editCourse = new JButton("Edit");
        editCourse.setBounds(100, 0, 100, 40);
        editCourse.addActionListener(this);
        editCourse.setEnabled(false);

        removeCourse = new JButton("Remove");
        removeCourse.setBounds(200, 0, 100, 40);
        removeCourse.addActionListener(this);
        removeCourse.setEnabled(false);

        enrolCourse = new JButton("Enrol");
        enrolCourse.setBounds(690, 0, 100, 40);
        enrolCourse.addActionListener(this);
        enrolCourse.setVisible(false);

        JTable enrolTable = new JTable(courseTable);
        enrolTable.getTableHeader().setResizingAllowed(false);
        enrolTable.getTableHeader().setReorderingAllowed(false);
        enrolTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        enrolTable.setRowSelectionAllowed(true);
        enrolTable.setColumnSelectionAllowed(false);
        enrolTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                removeCourse.setEnabled(enrolTable.getSelectedRow() != -1);
                editCourse.setEnabled(enrolTable.getSelectedRow() != -1);
                enrolCourse.setVisible(enrolTable.getSelectedRow() != -1);
            }
        });
        JScrollPane p = new JScrollPane(enrolTable);
        p.setBounds(0, 40, 800, 500);
        this.add(p);

    }
    public void refreshShow() {
        this.setVisible(true);
        courseTable.fireTableDataChanged();
    }

    public void userView() {
        this.add(enrolCourse);
        enrolCourse.setText("Enrol");
        this.remove(addCourse);
        this.remove(editCourse);
        this.remove(removeCourse);
    }

    public void instructorView() {
        this.add(enrolCourse);
        enrolCourse.setText("Teach");
        this.add(addCourse);
        this.add(editCourse);
        this.remove(removeCourse);
    }

    public void adminView() {
        this.remove(enrolCourse);
        this.add(addCourse);
        this.add(editCourse);
        this.add(removeCourse);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCourse) {
            String name = JOptionPane.showInputDialog(this, "Course name?", null);
            if ((name != null) && (!name.isEmpty())) {
                String capacity = JOptionPane.showInputDialog(this, "Capacity?", "50");
                if ((capacity != null) && (!capacity.isEmpty())) {
                    try {
                        presenter.addWorkout(name, Integer.parseInt(capacity));
                        courseTable.fireTableDataChanged();
                    }
                    catch (NumberFormatException exp) {
                        JOptionPane.showMessageDialog(this, "Workout already exists or invalid capacity!");
                    }
                }
            }
        }
    }

    @Override
    public void displayInfoMessage(String message) {

    }

    @Override
    public void displayErrorMessage(String message) {

    }

    @Override
    public void setPresenter(CourseEnrolmentPresenter presenter) {
        this.presenter = presenter;
    }
}
