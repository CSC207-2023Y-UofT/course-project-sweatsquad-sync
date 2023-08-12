package fd;

import ia.CourseEnrolmentPresenter;
import ia.RefreshRequestListener;
import ia.RefreshRequester;
import ia.View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class CourseEnrollmentFrame extends JDialog implements ActionListener, View<CourseEnrolmentPresenter>, RefreshRequester {

    private CourseEnrolmentPresenter presenter;

    private RefreshRequestListener dashboard;

    private final JButton addCourse, editCourse, removeCourse, enrolCourse, editCerts, editUsers, editName;
    private final JTextField search;
    private final AbstractTableModel courseTable = new AbstractTableModel() {
        private final String[] enrolTableCols = {"Workout", "Room", "Time", "Instructor", "Status", "ButtonText"};
        public int getColumnCount() { return enrolTableCols.length - 1; }
        public int getRowCount() { return presenter.getCurrentWorkouts().size(); }
        public String getColumnName(int col) {
            return enrolTableCols[col];
        }
        public Object getValueAt(int row, int col) {
            List<String[]> workouts = presenter.getCurrentWorkouts();
            return workouts.get(row)[col].isEmpty() ? "TBD" : workouts.get(row)[col];
        }
    };
    private final JTable enrolTable;
    private final WorkoutOfferingFrame workoutOfferingFrame;
    private final WorkoutCertsFrame workoutCertsFrame;
    private final WorkoutUsersFrame workoutUsersFrame;
    public CourseEnrollmentFrame(WorkoutOfferingFrame workoutOfferingFrame, WorkoutCertsFrame workoutCertsFrame, WorkoutUsersFrame workoutUsersFrame) {
        this.workoutOfferingFrame = workoutOfferingFrame;
        this.workoutCertsFrame = workoutCertsFrame;
        this.workoutUsersFrame = workoutUsersFrame;

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

        editCerts = new JButton("Edit Certificates");
        editCerts.setBounds(300, 0, 125, 40);
        editCerts.addActionListener(this);
        editCerts.setEnabled(false);

        editUsers = new JButton("Edit Users");
        editUsers.setBounds(425, 0, 100, 40);
        editUsers.addActionListener(this);
        editUsers.setEnabled(false);

        editName = new JButton("Edit Name");
        editName.setBounds(525, 0, 100, 40);
        editName.addActionListener(this);
        editName.setEnabled(false);

        enrolCourse = new JButton("Enrol");
        enrolCourse.setBounds(690, 0, 100, 40);
        enrolCourse.addActionListener(this);
        enrolCourse.setVisible(false);

        JLabel searchL = new JLabel("Search:");
        searchL.setBounds(0, 40, 100, 25);

        search = new JTextField();
        search.setBounds(50, 40, 200, 25);
        search.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { applyFilter(); }

            @Override
            public void removeUpdate(DocumentEvent e) { applyFilter(); }

            @Override
            public void changedUpdate(DocumentEvent e) { applyFilter(); }

            private void applyFilter() {
                TableRowSorter<AbstractTableModel> sorter = new TableRowSorter<>(courseTable);
                sorter.setRowFilter(RowFilter.regexFilter(search.getText()));
                enrolTable.setRowSorter(sorter);
            }
        });

        this.add(searchL);
        this.add(search);

        enrolTable = new JTable(courseTable);
        enrolTable.getTableHeader().setResizingAllowed(false);
        enrolTable.getTableHeader().setReorderingAllowed(false);
        enrolTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        enrolTable.setRowSelectionAllowed(true);
        enrolTable.setColumnSelectionAllowed(false);
        enrolTable.getSelectionModel().addListSelectionListener(e -> {
            removeCourse.setEnabled(enrolTable.getSelectedRow() != -1);
            editCourse.setEnabled(enrolTable.getSelectedRow() != -1);
            editCerts.setEnabled(enrolTable.getSelectedRow() != -1);
            editUsers.setEnabled(enrolTable.getSelectedRow() != -1);
            editName.setEnabled(enrolTable.getSelectedRow() != -1);
            enrolCourse.setVisible(enrolTable.getSelectedRow() != -1);
            if (enrolTable.getSelectedRow() != -1)
                enrolCourse.setText(courseTable.getValueAt(enrolTable.getSelectedRow(), 5).toString());
        });
        JScrollPane p = new JScrollPane(enrolTable);
        p.setBounds(0, 65, 800, 500);
        this.add(p);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dashboard.refresh();
            }
        });
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
        this.remove(editCerts);
        this.remove(editUsers);
        this.remove(editName);
    }

    public void instructorView() {
        this.add(enrolCourse);
        enrolCourse.setText("Teach");
        this.add(addCourse);
        this.add(editCourse);
        this.remove(removeCourse);
        this.remove(editCerts);
        this.remove(editUsers);
        this.remove(editName);
    }

    public void adminView() {
        this.remove(enrolCourse);
        this.add(addCourse);
        this.add(editCourse);
        this.add(removeCourse);
        this.add(editCerts);
        this.add(editUsers);
        this.add(editName);
    }

    private void addCoursePrompt() {
        JTextField name = new JTextField();
        JTextField cap = new JTextField();
        cap.setText("15");
        Object[] message = {
                "Name:", name,
                "Capacity:", cap
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Add Course", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION)
            if ((name.getText() != null) && (!name.getText().isEmpty())) {
                if ((cap.getText() != null) && (!cap.getText().isEmpty()))
                    try {
                        if (!presenter.addWorkout(name.getText(), Integer.parseInt(cap.getText())))
                            JOptionPane.showMessageDialog(this, "Workout already exists!");
                    }
                    catch (NumberFormatException exp) {
                        JOptionPane.showMessageDialog(this, "Invalid capacity!");
                    }
            }
            else
                JOptionPane.showMessageDialog(this, "Invalid name!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCourse)
            addCoursePrompt();
        else if (e.getSource() == removeCourse)
            presenter.removeWorkout(enrolTable.getSelectedRow());
        else if (e.getSource() == editCourse) {
            if (presenter.canEditCourse(enrolTable.getSelectedRow()))
                workoutOfferingFrame.refreshShow(enrolTable.getSelectedRow());
            else
                JOptionPane.showMessageDialog(this, "You don't teach this class!");
        }
        else if (e.getSource() == enrolCourse) {
            String msg = presenter.toggleEnrol(enrolTable.getSelectedRow());
            if (msg != null)
                JOptionPane.showMessageDialog(this, msg);
        }
        else if (e.getSource() == editCerts)
            workoutCertsFrame.refreshShow(enrolTable.getSelectedRow());
        else if (e.getSource() == editUsers)
            workoutUsersFrame.refreshShow(enrolTable.getSelectedRow());
        else if (e.getSource() == editName) {
            if (presenter.canEditCourse(enrolTable.getSelectedRow())) {
                String name = JOptionPane.showInputDialog(this, "New name?", null);
                if (name != null) {
                    if (!name.isEmpty()) {
                        if (!presenter.changeWorkoutName(enrolTable.getSelectedRow(), name))
                            JOptionPane.showMessageDialog(this, "There is already a workout with that name!");
                    }
                    else
                        JOptionPane.showMessageDialog(this, "Invalid cert name!");
                }
            }
            else
                JOptionPane.showMessageDialog(this, "You don't teach this class!");
        }

        courseTable.fireTableDataChanged();
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

    @Override
    public void addRefreshRequestListener(RefreshRequestListener rrl) {
        this.dashboard = rrl;
    }
}
