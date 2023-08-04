package fd;

// import statements
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import javax.swing.*;
import ebr.*;

public class UI {
//
//    // declaring UI components / layout
//    private CardLayout cardLayout = new CardLayout();
//
//    private JPanel schedulePanel, enrolPanel, smthPanel;
//    private JTable enrolTable, scheduleTable;
//    private RoundBtn roundBtn = new RoundBtn();
//
//    public String activeDash;
//
//    // UI constructor - has a database, and covers login/registration
//    public UI() {
//
//        this.addWindowListener(new java.awt.event.WindowAdapter() {
//            public void windowClosing(WindowEvent winEvt) {
//                try {
//                    database.save();
//                }
//                catch (IOException e) {
//                    e.printStackTrace();
//                    System.err.println("Failed to write gym!");
//                }
//            }
//        });
//
//        // ui 'defaults'
//        setTitle("SweatSquad Sync System"); // window title
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ends program if x
//        setSize(800, 600); // window dimensions
//        setResizable(false); // disables resizing
//        setLayout(cardLayout); // layout form, switches between cards
//        setLocationRelativeTo(null); // centers ui if left 'null'
//
//        setVisible(true);
//    }
//
//    // button action listener response function
//    public void actionPerformed(ActionEvent e) {
//
//        if (e.getSource() == back2dash) {
//            back2dash.setBounds(592, 478, 138, 68);
//            cardLayout.show(getContentPane(), activeDash);
//        }
//        else if (e.getSource() == saveAccountChanges) {
//            String firstName = capitalizeRemoveTrailingSpaces(firstNameField.getText());
//            firstNameField.setText(firstName);
//            String lastName = capitalizeRemoveTrailingSpaces(lastNameField.getText());
//            lastNameField.setText(lastName);
//            String username = userField.getText().trim();
//            String email = emailField.getText().trim();
//            emailField.setText(email);
//            err1.setText("");
//            err2.setText("");
//            err3.setText("");
//            err4.setText("");
//
//            if (!firstName.equals(database.activeUser.firstName)) {
//                if (!firstName.isEmpty()) {
//                    database.activeUser.firstName = firstName;
//                } else {
//                    System.out.println("Name fields cannot be left blank");
//                }
//            }
//
//            if (!lastName.equals(database.activeUser.lastName)) {
//                if (!lastName.isEmpty()) {
//                    database.activeUser.lastName = lastName;
//                } else {
//                    System.out.println("Name fields cannot be left blank");
//                }
//            }
//
//            if (!email.equals(database.activeUser.email)) {
//                if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
//                    database.activeUser.email = email;
//                } else {
//                    System.out.println("Invalid email format.");
//                }
//            }
//
//            if (!username.equals(database.activeUser.firstName)) {
//                // TODO
//            }
//        }
//    }
//
//    // removes trailing spaces from the front -used for names
//    public static String capitalizeRemoveTrailingSpaces(String input) {
//        String trimmedInput = input.trim(); // removes whitespace on both ends
//
//        // capitalizes the first letter
//        if (!trimmedInput.isEmpty()) { // checks that there is a first letter
//            return Character.toUpperCase(trimmedInput.charAt(0)) + trimmedInput.substring(1);
//        } else {
//            return ""; // no letters = empty string
//        }
//    }
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
}
