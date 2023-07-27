package fd;

// import statements

import javax.swing.*;
import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

// RoundBtn class
public class RoundBtn extends JFrame { // implements ActionListener; REMOVED
    // declare components / layout
    private CardLayout cardLayout = new CardLayout();
    private JButton roundedButton;
    private JPanel thePanel;

    // constructor
    public RoundBtn() {
        /* deprecated; REMOVED
        setTitle("TestGUI - RoundBtn");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200, 75);

        thePanel = new JPanel();
        thePanel.setLayout(null); // using null layout
        thePanel.setPreferredSize(new Dimension(250, 70)); // choosing x by y

        // calling the method to see if it can be called by other files
        roundedButton = genRoundBtn("Rounded Button", 50, "#3278DC", false);
        roundedButton.setFont(new Font("Arial", Font.PLAIN, 16));
        roundedButton.setForeground(Color.WHITE); // font colour
        roundedButton.setFocusPainted(false); // gets rid pressed blue outline

        // set the bounds manually for the rounded button
        roundedButton.setBounds(10, 10, 230, 50);

        thePanel.add(roundedButton); // adding the button to the panel
        add(thePanel); // adding the panel to the JFrame's content pane

        pack(); // adjusting the JFrame size to fit its contents
        setVisible(true); // making the JFrame visible
        */
    }

    // method to actually create a round button (generate round button)
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

        // adds a mouse listener
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

        // adds the ActionListener to the button itself; REMOVED
        // roundedButton.addActionListener(this);

        // returns new round button as desired
        return roundedButton;
    }

    /* only needed for the TestGUI; REMOVED
    public void actionPerformed(ActionEvent e) {
        // Handle button click action here
        if (e.getSource() == roundedButton) {
            System.out.println("Button Pressed");
        }
    } */

    /* this is unnecessary; REMOVED
    public static void main(String[] args) {
        new RoundBtn();
    }*/
}