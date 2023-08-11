package fd;

import ia.View;
import ia.WorkoutCertsPresenter;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkoutCertsFrame extends JDialog implements ActionListener, View<WorkoutCertsPresenter> {
    private final JButton add, remove;

    private WorkoutCertsPresenter presenter;
    private final AbstractTableModel certsTable = new AbstractTableModel() {
        private final String[] cols = {"Name"};
        public int getColumnCount() { return cols.length; }
        public int getRowCount() { return presenter.getCurrentWorkoutCerts(courseIndex).length; }
        public String getColumnName(int col) {
            return cols[col];
        }
        public Object getValueAt(int row, int col) {
            String[] certs = presenter.getCurrentWorkoutCerts(courseIndex);
            return certs[row];
        }
    };
    private final JTable table = new JTable(certsTable);
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
        table.getSelectionModel().addListSelectionListener(e -> remove.setEnabled(table.getSelectedRow() != -1));
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
                    String msg = presenter.requireCert(courseIndex, cert);
                    if (!(msg == null))
                        JOptionPane.showMessageDialog(this, msg);
                }
                else
                    JOptionPane.showMessageDialog(this, "Invalid cert name!");
            }
            certsTable.fireTableDataChanged();
        }
        else if (e.getSource() == remove) {
            presenter.removeCurrentCert(courseIndex, (String)table.getValueAt(table.getSelectedRow(),0));
            certsTable.fireTableDataChanged();
        }

    }

    @Override
    public void displayInfoMessage(String message) {}

    @Override
    public void displayErrorMessage(String message) {}

    @Override
    public void setPresenter(WorkoutCertsPresenter presenter) {
        this.presenter = presenter;
    }
}

