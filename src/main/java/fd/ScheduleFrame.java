package fd;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScheduleFrame extends JDialog implements ActionListener {
    public ScheduleFrame() {
        setTitle("Schedule"); // window title
        setSize(800, 600); // window dimensions
        setResizable(false); // disables resizing
        setLocationRelativeTo(null); // centers ui if left 'null'
        setModal(true);

        Object[][] scheduleTableData = new Object[10][8];
        for (int i = 0; i < 10; i++) {
            scheduleTableData[i][0] = (8 + i) + ":00";
        }
        String[] scheduleTableCols = {"", "M", "T", "W", "Tu", "F", "S", "Su"};
        JTable scheduleTable = new JTable(scheduleTableData, scheduleTableCols);
        scheduleTable.setRowHeight(50);
        JScrollPane p = new JScrollPane(scheduleTable);
        this.add(p);
    }
    public void refreshShow() {
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
