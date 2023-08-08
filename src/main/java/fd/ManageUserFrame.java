package fd;

import ia.DashboardPresenter;
import ia.ManagerUserPresenter;
import ia.View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageUserFrame extends JDialog implements ActionListener, View<ManagerUserPresenter> {

    private ManagerUserPresenter presenter;
    private AbstractTableModel userTable = new AbstractTableModel() {
        private final String[] cols = {"Name", "Username", "Type", "Certs"};
        @Override
        public int getColumnCount() { return cols.length; }
        @Override
        public int getRowCount() { return (int)presenter.getCurrentUsers().size(); }
        @Override
        public String getColumnName(int col) {
            return cols[col];
        }
        @Override
        public Object getValueAt(int row, int col) {
            List<String[]> users = presenter.getCurrentUsers();
            return users.get(row)[col];
        }
    };

    private JButton delete, addInstructor, addCerts;
    JTable table;
    public ManageUserFrame() {
        setTitle("Manage Users"); // window title
        setSize(800, 600); // window dimensions
        setResizable(false); // disables resizing
        setLocationRelativeTo(null); // centers ui if left 'null'
        setLayout(null);
        setModal(true);

        table = new JTable(userTable);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(false);
        table.getSelectionModel().addListSelectionListener(e -> {
            boolean sel = table.getSelectedRow() != -1;
            delete.setEnabled(sel);
            if (sel)
                addCerts.setVisible(table.getValueAt(table.getSelectedColumn(), 2).toString().equals("Instructor"));
        });
        JScrollPane p = new JScrollPane(table);
        p.setBounds(0, 40, 800, 500);
        this.add(p);

        addInstructor = new JButton("Add Instructor");
        addInstructor.setBounds(0, 0, 150, 40);
        addInstructor.addActionListener(this);
        this.add(addInstructor);

        delete = new JButton("Remove");
        delete.setBounds(150, 0, 100, 40);
        delete.addActionListener(this);
        delete.setEnabled(false);
        this.add(delete);

        addCerts = new JButton("Add Cert");
        addCerts.setBounds(250, 0, 100, 40);
        addCerts.addActionListener(this);
        addCerts.setVisible(false);
        this.add(addCerts);
    }

    public void refreshShow() {
        this.setVisible(true);
        userTable.fireTableDataChanged();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addInstructor) {
            Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .setContents(new StringSelection(presenter.newInstructor()),null);
            JOptionPane.showMessageDialog(this, "Registration code has been copied to clipboard!");
        }
        else if (e.getSource() == delete)
            presenter.removeUser(table.getSelectedRow());
        else if (e.getSource() == addCerts) {
            String cert = JOptionPane.showInputDialog(this, "Cert name?", null);
            if ((cert != null) && (!cert.isEmpty()) && !cert.equals("None"))
                presenter.instructorAddCert(table.getSelectedRow(), cert);
            else
                JOptionPane.showMessageDialog(this, "Invalid name!");
        }

        userTable.fireTableDataChanged();
    }

    @Override
    public void displayInfoMessage(String message) {

    }

    @Override
    public void displayErrorMessage(String message) {

    }

    @Override
    public void setPresenter(ManagerUserPresenter presenter) {
        this.presenter = presenter;
    }
}
