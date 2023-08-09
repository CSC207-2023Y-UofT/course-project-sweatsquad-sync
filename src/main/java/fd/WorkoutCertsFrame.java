package fd;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkoutCertsFrame extends JDialog implements ActionListener {
    private JButton add, remove;
    private AbstractTableModel certsTable = new AbstractTableModel() {
        private final String[] cols = {"Name"};
        public int getColumnCount() { return cols.length; }
        public int getRowCount() { return (int)App.db.getCurrentWorkoutCerts(courseIndex).length; }
        public String getColumnName(int col) {
            return cols[col];
        }
        public Object getValueAt(int row, int col) {
            String[] certs = App.db.getCurrentWorkoutCerts(courseIndex);
            return certs[row];
        }
    };
    private JTable table = new JTable(certsTable);
    private int courseIndex = -1;
    public WorkoutCertsFrame() {
        setTitle("Certifications"); // window title
        setSize(600, 400); // window dimensions
        setResizable(false); // disables resizing
        setLocationRelativeTo(null); // centers ui if left 'null'
        setLayout(null);
        setModal(true);

        add = new JButton("Add");
        add.setBounds(0, 0, 100, 40);
        add.addActionListener(this);
        this.add(add);

        remove = new JButton("Remove");
        remove.setBounds(100, 0, 100, 40);
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
                remove.setEnabled(table.getSelectedRow() != -1);
            }
        });
        JScrollPane p = new JScrollPane(table);
        p.setBounds(0, 40, 600, 500);
        this.add(p);
    }
    public void refreshShow(int idx) {
        courseIndex = idx;
        this.setVisible(true);
        if (courseIndex != -1)
            certsTable.fireTableDataChanged();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String cert = JOptionPane.showInputDialog(this, "Cert name?", null);
            if (cert != null) {
                if (!cert.isEmpty()) {
                    String msg = App.db.requireCert(courseIndex, cert);
                    if (!(msg == null))
                        JOptionPane.showMessageDialog(this, msg);
                }
                else
                    JOptionPane.showMessageDialog(this, "Invalid cert name!");
            }
            certsTable.fireTableDataChanged();
        }
        else if (e.getSource() == remove) {
            App.db.removeCurrentCert(courseIndex, (String)table.getValueAt(table.getSelectedRow(),0));
            certsTable.fireTableDataChanged();
        }

    }
}

