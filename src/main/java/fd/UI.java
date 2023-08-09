package fd;

// import statements
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.text.JTextComponent;

public class UI {
    public static Font
            A16  = new Font("Arial", Font.PLAIN, 16),
    // MP12 = new Font("Monsterrat", Font.PLAIN, 12), unused
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

    static public String capitalizeRemoveTrailingSpaces(String input) {
        String trimmedInput = input.trim();

        if (!trimmedInput.isEmpty()) {
            return Character.toUpperCase(trimmedInput.charAt(0))
                    + trimmedInput.substring(1);
        }
        else
            return "";
    }

    // refactored genRoundBtn method - removing code smells
    public static JButton genRoundBtn(String text, int roundness, String colorHex, boolean light) {
        Color defaultColor = Color.decode(colorHex);

        JButton roundedButton = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = setupGraphics(g);

                boolean isHovering = Boolean.TRUE.equals(getClientProperty("isHovering"));
                Color bgColor = getHoverColor(defaultColor, isHovering, light);

                g2.setColor(bgColor);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), roundness, roundness));

                g2.setColor(getForeground());
                FontMetrics metrics = g2.getFontMetrics();
                int x = (getWidth() - metrics.stringWidth(getText())) / 2;
                int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();

                g2.drawString(getText(), x, y);
                g2.dispose();
            }
        };

        roundedButton.setBorderPainted(false);
        roundedButton.setContentAreaFilled(false);
        roundedButton.setOpaque(false);

        roundedButton.addMouseListener(createHoverEffectMouseListener(roundedButton, getHoverColor(defaultColor, true, light)));

        return roundedButton;
    }

    // refactored genRoundTextField method -removed code smells
    public static JTextField genRoundTextField(String text, int roundness, String colorHex, boolean light) {
        JTextField roundedField = new JTextField(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = setupGraphics(g);

                Color defaultColor = Color.decode(colorHex);
                g2.setColor(defaultColor);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), roundness, roundness));

                g2.setColor(Color.BLACK); // set the color to black for the outline
                g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, roundness, roundness));

                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {}
        };

        configureRoundField(roundedField, colorHex, Cursor.TEXT_CURSOR);

        return roundedField;
    }

    // refactored genRoundPasswordField method -removed code smells
    public static JPasswordField genRoundPasswordField(String text, int roundness, String colorHex) {
        JPasswordField roundedField = new JPasswordField(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = setupGraphics(g);

                Color defaultColor = Color.decode(colorHex);
                g2.setColor(defaultColor);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), roundness, roundness));

                g2.setColor(Color.BLACK); // set the color to black for the outline
                g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, roundness, roundness));

                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {}
        };

        configureRoundField(roundedField, colorHex, Cursor.TEXT_CURSOR);

        return roundedField;
    }

    public static JLabel genRoundLabel(String text, int roundness, String colorHex) {
        Color defaultColor = Color.decode(colorHex);

        JLabel roundedLabel = new JLabel(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = setupGraphics(g);

                g2.setColor(defaultColor);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), roundness, roundness));

                g2.setColor(getForeground());
                FontMetrics metrics = g2.getFontMetrics();
                int x = (getWidth() - metrics.stringWidth(getText())) / 2;
                int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();

                g2.drawString(getText(), x, y);
                g2.dispose();
            }
        };

        roundedLabel.setOpaque(false);

        return roundedLabel;
    }

    // helper method for hover effects (creates MouseListener) for buttons
    private static MouseAdapter createHoverEffectMouseListener(JComponent component, Color hoverColor) {
        return createHoverEffectMouseListener(component, hoverColor, Cursor.HAND_CURSOR);
    }

    // helper method for hover effects, MouseListener for text/pass fields
    private static MouseAdapter createHoverEffectMouseListener(JComponent component, Color hoverColor, int cursorType) {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                component.setCursor(Cursor.getPredefinedCursor(cursorType));
                component.putClientProperty("isHovering", true);
                component.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                component.setCursor(Cursor.getDefaultCursor());
                component.putClientProperty("isHovering", false);
                component.repaint();
            }
        };
    }


    // helper method to set up button's / field's common graphics settings
    private static Graphics2D setupGraphics(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return g2;
    }

    // helper method for hover color logic
    private static Color getHoverColor(Color defaultColor, boolean isHovering, boolean light) {
        if (isHovering) {
            return light ? defaultColor.brighter() : defaultColor.darker();
        }
        return defaultColor;
    }

    // common functionality; genRoundTextField + genRoundPasswordField
    private static void configureRoundField(JTextComponent field, String colorHex, int cursorType) {
        Color defaultColor = Color.decode(colorHex);

        field.setOpaque(false);
        field.addMouseListener(createHoverEffectMouseListener(field, defaultColor, cursorType));
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                field.repaint();
            }
        });

        field.setOpaque(false);

        field.addMouseListener(createHoverEffectMouseListener(field, defaultColor, cursorType));
    }

}
