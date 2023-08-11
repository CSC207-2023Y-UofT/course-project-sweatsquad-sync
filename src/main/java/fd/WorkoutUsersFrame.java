package fd;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class WorkoutUsersFrame extends JDialog implements ActionListener {
    private final JButton remove;
    private final AbstractTableModel userTable = new AbstractTableModel() {
        private final String[] cols = {"Name", "Username", "Type", "Certs"};

        public int getColumnCount() {
            return cols.length;
        }

        public int getRowCount() {
            return App.db.getCurrentWorkoutUsers(courseIndex).size();
        }

        public String getColumnName(int col) {
            return cols[col];
        }

        public Object getValueAt(int row, int col) {
            List<String[]> users = App.db.getCurrentWorkoutUsers(courseIndex);
            return users.get(row)[col];
        }
    };
    private final JTable table = new JTable(userTable);
    private int courseIndex = -1;

    public WorkoutUsersFrame() {
        setTitle("Edit Users in Workout"); // window title
        setSize(600, 400); // window dimensions
        setResizable(false); // disables resizing
        setLocationRelativeTo(null); // centers ui if left 'null'
        setLayout(null);
        setModal(true);

        remove = new JButton("Remove");
        remove.setBounds(0, 0, 100, 40);
        remove.addActionListener(this);
        remove.setEnabled(false);
        this.add(remove);

        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.getSelectionModel().addListSelectionListener(e -> remove.setEnabled(table.getSelectedRow() != -1));
        JScrollPane p = new JScrollPane(table);
        p.setBounds(0, 40, 600, 500);
        this.add(p);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                App.dashboard.refreshShow();
            }
        });
    }

    public void refreshShow(int idx) {
        courseIndex = idx;
        this.setVisible(true);
        if (courseIndex != -1)
            userTable.fireTableDataChanged();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == remove) {
            App.db.removeUserFromWorkout(courseIndex, (String) userTable.getValueAt(table.getSelectedRow(), 1));
            userTable.fireTableDataChanged();
        }

    }
}