package fd;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class WorkoutOfferingFrame extends JDialog implements ActionListener {
    private JButton add, edit, remove;
    private AbstractTableModel offeringTable = new AbstractTableModel() {
        private final String[] cols = {"Time", "Duration", "Room"};
        public int getColumnCount() { return cols.length; }
        public int getRowCount() { return (int)App.db.getCurrentOfferings(courseIndex).size(); }
        public String getColumnName(int col) {
            return cols[col];
        }
        public Object getValueAt(int row, int col) {
            List<String[]> offerings = App.db.getCurrentOfferings(courseIndex);
            return offerings.get(row)[col].isEmpty() ? "TBD" : offerings.get(row)[col];
        }
    };
    private JTable table = new JTable(offeringTable);
    private int courseIndex = -1;
    public WorkoutOfferingFrame() {
        setTitle("Courses"); // window title
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
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                add.setEnabled(table.getSelectedRow() != -1);
                edit.setEnabled(table.getSelectedRow() != -1);
                remove.setEnabled(table.getSelectedRow() != -1);
            }
        });
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
        offeringTable.fireTableDataChanged();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add)
            ;
        else if (e.getSource() == edit)
            ;
        else if (e.getSource() == remove)
            ;

        offeringTable.fireTableDataChanged();
    }
}
