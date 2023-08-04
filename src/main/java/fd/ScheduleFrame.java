package fd;

import ebr.Workout;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ScheduleFrame extends JDialog implements ActionListener {
    private AbstractTableModel scheduleTable = new AbstractTableModel() {
        private final String[] enrolTableCols = {"", "M", "T", "W", "Tu", "F", "S", "Su"};
        public int getColumnCount() { return 8; }
        public int getRowCount() { return 10; }
        public String getColumnName(int col) {
            return enrolTableCols[col];
        }
        public Object getValueAt(int row, int col) {
            if (col == 0)
                return (8 + row) + ":00";

            return "TODO";
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
