package fd;

// import statements
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class ComponentFactory {
    public static Font
            A16  = new Font("Arial", Font.PLAIN, 16),
            MP12 = new Font("Monsterrat", Font.PLAIN, 12),
            MP16 = new Font("Monsterrat", Font.PLAIN, 16),
            MB12 = new Font("Monsterrat", Font.BOLD, 12),
            MB13 = new Font("Monsterrat", Font.BOLD, 13),
            MB14 = new Font("Monsterrat", Font.BOLD, 14),
            MB15 = new Font("Monsterrat", Font.BOLD, 15),
            MB16 = new Font("Monsterrat", Font.BOLD, 16),
            MB19 = new Font("Monsterrat", Font.BOLD, 19),
            MB20 = new Font("Monsterrat", Font.BOLD, 20),
            MB23 = new Font("Monsterrat", Font.BOLD, 23),
            CB13 = new Font("Comfortaa", Font.BOLD, 13),
            CB18 = new Font("Comfortaa", Font.BOLD, 18);

    public static JButton genRoundBtn(String text, int roundness,
                                      String colorHex, boolean light) {

        // decodes colour to be used later
        Color defaultColor = Color.decode(colorHex);

        // determines whether to make the hovered button darker w/ given input
        Color hoverColor;
        if (light) {
            hoverColor = defaultColor.brighter(); // true = brighter
        } else {
            hoverColor = defaultColor.darker(); // false = darker
        }

        // an actual button is created
        JButton roundedButton = new JButton(text) {
            @Override
            // custom button painting is done in this method (needs overriding)
            protected void paintComponent(Graphics g) {
                // a Graphics2D object is created to paint on
                Graphics2D g2 = (Graphics2D) g.create();
                // antialiasing for smoother graphics (blends colours)
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                //checks if the mouse is hovering the button
                boolean isHovering = Boolean.TRUE.equals(getClientProperty("isHovering"));

                // choosing button background color based on the hover state
                Color bgColor;
                if (isHovering) {
                    bgColor = hoverColor; // hovering -> choose set hover colour
                } else {
                    bgColor = defaultColor;  // not hovering -> default colour
                }

                // actually setting the colour for the button
                g2.setColor(bgColor);

                // filling a round rectangle w/ RoundRectangle2D from geom lib.
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), roundness, roundness));

                // draw button text centered both vert. and horiz. in the btn
                g2.setColor(getForeground());
                // creates variable that stores font data
                FontMetrics metrics = g2.getFontMetrics();
                // x = the dif between button's width + text's width over 2
                int x = (getWidth() - metrics.stringWidth(getText())) / 2;
                // y = the dif between button's height + text's height over 2
                int y = (getHeight() - metrics.getHeight()) / 2
                        + metrics.getAscent();

                // draws button text at the correct position
                g2.drawString(getText(), x, y);
                // equiv. of closing scanner, but for Graphic2D obj
                g2.dispose();
            }
        };

        roundedButton.addMouseListener(new MouseAdapter() {

            // repaints to match colour when mouse is on the button
            @Override
            public void mouseEntered(MouseEvent e) {
                // changes cursor to clickable state
                roundedButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                roundedButton.putClientProperty("isHovering", true);
                roundedButton.repaint();
            }

            // repaints to match colour when mouse has left the button
            @Override
            public void mouseExited(MouseEvent e) {
                // reverts cursor to normal state
                roundedButton.setCursor(Cursor.getDefaultCursor());
                roundedButton.putClientProperty("isHovering", false);
                roundedButton.repaint();
            }
        });

        return roundedButton;
    }

    public static JTextField genRoundTextField(String text, int roundness, String colorHex, boolean light) {
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

        return roundedField;
    }

    // creates custom rounded JPasswordField
    public static JPasswordField genRoundPasswordField(String text, int roundness, String colorHex, boolean light) {
        JPasswordField roundedField = new JPasswordField(text) {
            @Override
            // custom painting for the rounded background
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color defaultColor = Color.decode(colorHex);

                g2.setColor(defaultColor);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), roundness, roundness));

                super.paintComponent(g);

                g2.dispose();
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(330, 30);
            }

            @Override
            protected void paintBorder(Graphics g) {}
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

        return roundedField;
    }
}
