package fd;

import ia.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.DayOfWeek;
import java.time.Duration;
import java.util.List;

public class WorkoutOfferingFrame extends JDialog implements ActionListener, View<WorkoutOfferingPresenter>, RefreshRequester {

    private WorkoutOfferingPresenter presenter;

    private RefreshRequestListener refreshRequestListener;


    private final JButton add, edit, remove;
    private final AbstractTableModel offeringTable = new AbstractTableModel() {
        private final String[] cols = {"Time", "Duration (Hour)", "Room"};
        public int getColumnCount() { return cols.length; }
        public int getRowCount() { return presenter.getCurrentOfferings(courseIndex).size(); }
        public String getColumnName(int col) {
            return cols[col];
        }
        public Object getValueAt(int row, int col) {
            List<String[]> offerings = presenter.getCurrentOfferings(courseIndex);
            return offerings.get(row)[col].isEmpty() ? "TBD" : offerings.get(row)[col];
        }
    };
    private final JTable table = new JTable(offeringTable);
    private int courseIndex = -1;
    public WorkoutOfferingFrame() {
        setTitle("Offerings"); // window title
        setSize(600, 400); // window dimensions
        setResizable(false); // disables resizing
        setLocationRelativeTo(null); // centers ui if left 'null'
        setLayout(null);
        setModal(true);

        add = new JButton("Add");
        add.setBounds(0, 0, 100, 40);
        add.addActionListener(this);
        this.add(add);

        edit = new JButton("Edit");
        edit.setBounds(100, 0, 100, 40);
        edit.addActionListener(this);
        edit.setEnabled(false);
        this.add(edit);

        remove = new JButton("Remove");
        remove.setBounds(200, 0, 100, 40);
        remove.addActionListener(this);
        remove.setEnabled(false);
        this.add(remove);


        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.getSelectionModel().addListSelectionListener(e -> {
            edit.setEnabled(table.getSelectedRow() != -1);
            remove.setEnabled(table.getSelectedRow() != -1);
        });
        JScrollPane p = new JScrollPane(table);
        p.setBounds(0, 40, 600, 500);
        this.add(p);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                refreshRequestListener.refresh();
            }
        });
    }
    public void refreshShow(int idx) {
        courseIndex = idx;
        this.setVisible(true);
        if (courseIndex != -1)
            offeringTable.fireTableDataChanged();
    }

    private void prompt(int offering) {
        List<String> rooms = presenter.getCurrentRooms();
        if (rooms.isEmpty()) {
            JOptionPane.showMessageDialog(this, "There are no rooms in the gym!");
            return;
        }
        DayOfWeek[] dayData = new DayOfWeek[]{
                DayOfWeek.SUNDAY,
                DayOfWeek.MONDAY,
                DayOfWeek.TUESDAY,
                DayOfWeek.WEDNESDAY,
                DayOfWeek.THURSDAY,
                DayOfWeek.FRIDAY,
                DayOfWeek.SATURDAY
        };
        String[] startData = new String[] {
                "08:00",
                "09:00",
                "10:00",
                "11:00",
                "12:00",
                "13:00",
                "14:00",
                "15:00",
        };
        Integer[] durationData = new Integer[]{ 1,2,3,4,5 };
        JComboBox<DayOfWeek> day = new JComboBox<>(dayData);
        JComboBox<String> start = new JComboBox<>(startData);
        JComboBox<Integer> duration = new JComboBox<>(durationData);
        JComboBox<String> room = new JComboBox<>(rooms.toArray(new String[]{}));
        Object[] message = new Object[] {
                "Day:", day,
                "Start Time:", start,
                "Duration (Hours):", duration,
                "Room:", room
        };

        if (offering != -1) {
            String[] s = presenter.getCurrentOfferings(courseIndex).get(offering);
            day.setSelectedItem(DayOfWeek.valueOf(s[0].split(" ")[0].toUpperCase()));
            start.setSelectedItem(s[0].split(" ")[1]);
            duration.setSelectedItem(Duration.ofHours(Integer.parseInt(s[1])));
            room.setSelectedItem(s[2]);
        }
        int option = JOptionPane.showConfirmDialog(null, message, "Add Offering", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION)
            if (!presenter.editOffering(courseIndex, offering, dayData[day.getSelectedIndex()], startData[start.getSelectedIndex()],
                    durationData[duration.getSelectedIndex()], rooms.get(room.getSelectedIndex())))
                JOptionPane.showMessageDialog(this, "This collides with another offering of this course!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            prompt(-1);
            offeringTable.fireTableDataChanged();
        }
        else if (e.getSource() == edit) {
            prompt(table.getSelectedRow());
            offeringTable.fireTableDataChanged();
        }
        else if (e.getSource() == remove) {
            presenter.removeOffering(courseIndex, table.getSelectedRow());
            offeringTable.fireTableDataChanged();
        }

    }

    @Override
    public void displayInfoMessage(String message) {

    }

    @Override
    public void displayErrorMessage(String message) {

    }

    @Override
    public void setPresenter(WorkoutOfferingPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addRefreshRequestListener(RefreshRequestListener rrl) {
        this.refreshRequestListener = rrl;
    }
}
