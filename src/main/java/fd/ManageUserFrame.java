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
    private AbstractTableModel userTable = new AbstractTableModel() {
        private final String[] cols = {"Name", "Username", "Type"};
        public int getColumnCount() { return cols.length; }
        public int getRowCount() { return (int)App.db.getCurrentUsers().size(); }
        public String getColumnName(int col) {
            return cols[col];
        }
        public Object getValueAt(int row, int col) {
            List<String[]> users = App.db.getCurrentUsers();
            return users.get(row)[col];
        }
    };

    private JButton delete, addInstructor;
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
                delete.setEnabled(table.getSelectedRow() != -1);
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
                    .setContents(new StringSelection(App.db.newInstructor()),null);
            JOptionPane.showMessageDialog(this, "Registration code has been copied to clipboard!");
        }
        else if (e.getSource() == delete)
            App.db.removeUser(table.getSelectedRow());

        userTable.fireTableDataChanged();
    }
}
