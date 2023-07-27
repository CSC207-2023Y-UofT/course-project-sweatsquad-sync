package fd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class RoundField extends JFrame {
    /* deprecated
    private JTextField roundedTextField;
    private JPasswordField roundedPasswordField;
    private JPanel thePanel;*/

    public RoundField() {
        // sets up JFrame properties
        /* deprecated
        setTitle("TestGUI - RoundField");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // main panel w/ null layout
        thePanel = new JPanel();
        thePanel.setLayout(null);
        thePanel.setPreferredSize(new Dimension(800, 600));

        // test rounded JTextField and JPasswordField
        roundedTextField = genRoundTextField("", 30, "#FFFFFF", false);
        roundedTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        roundedTextField.setBounds(189, 123, 422, 45);
        roundedTextField.setFont(new Font("Comfortaa", Font.BOLD, 18));
        thePanel.add(roundedTextField);

        roundedPasswordField = genRoundPasswordField("", 20, "#FFFFFF", false);
        roundedPasswordField.setFont(new Font("Arial", Font.PLAIN, 16));
        roundedPasswordField.setBounds(189, 210, 422, 45);
        thePanel.add(roundedPasswordField);

        add(thePanel);
        pack();
        setVisible(true);*/
    }

    // creates custom rounded JTextField
    public static JTextField genRoundTextField(String text, int roundness, String colorHex, boolean light) {
        // create a custom JTextField with the specified text and overridden painting
        JTextField roundedField = new JTextField("") {
            @Override
            // custom painting for the rounded background
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // check if the mouse is hovering over the field
                boolean isHovering = Boolean.TRUE.equals(getClientProperty("isHovering"));
                // decode the colorHex to a Color object
                Color defaultColor = Color.decode(colorHex);

                // fill a round rectangle to create the rounded effect
                g2.setColor(defaultColor);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), roundness, roundness));

                // call paintComponent (from super) to render the text
                super.paintComponent(g);

                g2.dispose();
            }

            @Override
            public Dimension getPreferredSize() {
                // set the preferred size for the custom field
                return new Dimension(330, 30);
            }

            @Override
            protected void paintBorder(Graphics g) {
                // Do nothing to remove the border
            }
        };

        // add mouse listener to handle hover effects
        roundedField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Change cursor to text cursor on hover
                roundedField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                roundedField.putClientProperty("isHovering", true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Revert cursor to default on exit
                roundedField.setCursor(Cursor.getDefaultCursor());
                roundedField.putClientProperty("isHovering", false);
            }
        });

        // return the custom rounded JTextField
        return roundedField;
    }

    // creates custom rounded JPasswordField
    public static JPasswordField genRoundPasswordField(String text, int roundness, String colorHex, boolean light) {
        // create a custom JPasswordField with the specified text and overridden painting
        JPasswordField roundedField = new JPasswordField(text) {
            @Override
            // custom painting for the rounded background
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // decode the colorHex to a Color object
                Color defaultColor = Color.decode(colorHex);

                // round rectangle fill to create the rounded effect w/input val
                g2.setColor(defaultColor);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), roundness, roundness));

                // calling paintComponent from superclass for text rendering
                super.paintComponent(g);

                g2.dispose();
            }

            @Override
            public Dimension getPreferredSize() {
                // set the preferred size for custom field
                return new Dimension(330, 30);
            }

            @Override
            protected void paintBorder(Graphics g) {
                // do nothing
            }
        };

        // add mouse listener to handle hover effects -just in case
        roundedField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // changes cursor to text cursor on hover
                roundedField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                roundedField.putClientProperty("isHovering", true);
                //roundedField.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // revert cursor to default on exit
                roundedField.setCursor(Cursor.getDefaultCursor());
                roundedField.putClientProperty("isHovering", false);
                roundedField.repaint();
            }
        });

        // return custom rounded JPasswordField
        return roundedField;
    }

    /* not needed
    public static void main(String[] args) {
        new RoundField();
    }*/
}
