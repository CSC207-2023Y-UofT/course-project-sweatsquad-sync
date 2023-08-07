package fd;

import ebr.Workout;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.util.List;

public class ScheduleFrame extends JDialog implements ActionListener {
    private AbstractTableModel scheduleTable = new AbstractTableModel() {
        private final String[] cols = {
                "",
                DayOfWeek.SUNDAY.toString(),
                DayOfWeek.MONDAY.toString(),
                DayOfWeek.TUESDAY.toString(),
                DayOfWeek.WEDNESDAY.toString(),
                DayOfWeek.THURSDAY.toString(),
                DayOfWeek.FRIDAY.toString(),
                DayOfWeek.SATURDAY.toString()
        };
        public int getColumnCount() { return cols.length; }
        public int getRowCount() { return 10; }
        public String getColumnName(int col) {
            return cols[col];
        }
        public Object getValueAt(int row, int col) {
            if (col == 0)
                return (8 + row) + ":00";

            return App.db.getScheduleBlockAt(row - 1 + 8, col - 1);
        }
    };

    public ScheduleFrame() {
        setTitle("Schedule"); // window title
        setSize(800, 600); // window dimensions
        setResizable(false); // disables resizing
        setLocationRelativeTo(null); // centers ui if left 'null'
        setModal(true);

        JTable t = new JTable(scheduleTable);
        t.setRowHeight(50);
        t.getTableHeader().setResizingAllowed(false);
        t.getTableHeader().setReorderingAllowed(false);
        t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane p = new JScrollPane(t);
        this.add(p);
    }
    public void refreshShow() {
        scheduleTable.fireTableDataChanged();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
