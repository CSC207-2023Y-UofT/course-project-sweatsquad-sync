package fd;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageUserFrame extends JDialog implements ActionListener {
    private final AbstractTableModel userTable = new AbstractTableModel() {
        private final String[] cols = {"Name", "Username", "Type", "Certs"};
        public int getColumnCount() { return cols.length; }
        public int getRowCount() { return App.db.getCurrentUsers().size(); }
        public String getColumnName(int col) {
            return cols[col];
        }
        public Object getValueAt(int row, int col) {
            List<String[]> users = App.db.getCurrentUsers();
            return users.get(row)[col];
        }
    };

    private final JButton delete, addInstructor, addCerts, copyCode;
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
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean sel = table.getSelectedRow() != -1;
                delete.setEnabled(sel);
                if (sel) {
                    addCerts.setVisible(table.getValueAt(table.getSelectedRow(), 2).toString().equals("Instructor"));
                    copyCode.setVisible(table.getValueAt(table.getSelectedRow(), 2).toString().equals("Instructor"));
                    copyCode.setEnabled(table.getValueAt(table.getSelectedRow(), 0).toString().equals("N/A"));
                }
                else  {
                    addCerts.setVisible(false);
                    copyCode.setVisible(false);
                }
            }
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

        copyCode = new JButton("Copy AuthCode");
        copyCode.setBounds(350, 0, 150, 40);
        copyCode.addActionListener(this);
        copyCode.setVisible(false);
        this.add(copyCode);
    }

    public void refreshShow() {
        this.setVisible(true);
        userTable.fireTableDataChanged();
        table.clearSelection();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addInstructor) {
            Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .setContents(new StringSelection(App.db.newInstructor()),null);
            JOptionPane.showMessageDialog(this, "Registration code has been copied to clipboard!");
        }
        else if (e.getSource() == delete) {
            if (table.getValueAt(table.getSelectedRow(), 2).toString().equals("GymAdmin"))
                JOptionPane.showMessageDialog(this, "Cannot delete admin!");
            else
                App.db.removeUser(table.getSelectedRow());
        }
        else if (e.getSource() == addCerts) {
            String cert = JOptionPane.showInputDialog(this, "Cert name?", null);
            if ((cert != null) && (!cert.isEmpty()) && !cert.equals("None"))
                App.db.instructorAddCert(table.getSelectedRow(), cert);
            else
                JOptionPane.showMessageDialog(this, "Invalid name!");
        }
        else if (e.getSource() == copyCode) {
            Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .setContents(new StringSelection(App.db.adminReqInstructorAuthCode(table.getSelectedRow())),null);
            JOptionPane.showMessageDialog(this, "Registration code has been copied to clipboard!");
        }

        userTable.fireTableDataChanged();
    }
}
