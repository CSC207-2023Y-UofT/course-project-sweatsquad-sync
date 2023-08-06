package fd;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageRoomFrame extends JDialog implements ActionListener {
    private AbstractTableModel roomTable = new AbstractTableModel() {
        private final String[] cols = {"Room"};
        public int getColumnCount() { return cols.length; }
        public int getRowCount() { return (int)App.db.getCurrentRooms().size(); }
        public String getColumnName(int col) {
            return cols[col];
        }
        public Object getValueAt(int row, int col) {
            List<String> rooms = App.db.getCurrentRooms();
            return rooms.get(row);
        }
    };

    private JButton addRoom, removeRoom;
    private JTable table;
    public ManageRoomFrame() {
        setTitle("Manage Rooms"); // window title
        setSize(800, 600); // window dimensions
        setResizable(false); // disables resizing
        setLocationRelativeTo(null); // centers ui if left 'null'
        setLayout(null);
        setModal(true);

        addRoom = new JButton("Add");
        addRoom.setBounds(0, 0, 100, 40);
        addRoom.addActionListener(this);
        this.add(addRoom);

        removeRoom = new JButton("Remove");
        removeRoom.setBounds(100, 0, 100, 40);
        removeRoom.addActionListener(this);
        removeRoom.setEnabled(false);
        this.add(removeRoom);

        table = new JTable(roomTable);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                removeRoom.setEnabled(table.getSelectedRow() != -1);
            }
        });

        JScrollPane p = new JScrollPane(table);
        p.setBounds(0, 40, 800, 500);
        this.add(p);
    }

    public void refreshShow() {
        this.setVisible(true);
        roomTable.fireTableDataChanged();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addRoom) {
            String name = JOptionPane.showInputDialog(this, "Room name?", null);
            if (name != null) {
                if (!name.isEmpty()) {
                    if (!App.db.addRoom(name))
                        JOptionPane.showMessageDialog(this, "Room already exists!");
                }
                else
                    JOptionPane.showMessageDialog(this, "Invalid name!");
            }
        }
        else if (e.getSource() == removeRoom)
            App.db.removeRoom(roomTable.getValueAt(table.getSelectedRow(), table.getSelectedColumn()).toString());

        roomTable.fireTableDataChanged();
    }
}
