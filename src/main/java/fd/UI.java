package fd;

// import statements
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.text.JTextComponent;

// image icon imports for the genImageBtn
import java.awt.image.*;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import java.io.File;

public class UI {
    public static Font
            A16  = new Font("Arial", Font.PLAIN, 16),
            MB12 = new Font("Montserrat", Font.BOLD, 12),
            MB13 = new Font("Montserrat", Font.BOLD, 13),
            MB14 = new Font("Montserrat", Font.BOLD, 14),
            MB15 = new Font("Montserrat", Font.BOLD, 15),
            MB16 = new Font("Montserrat", Font.BOLD, 16),
            MB18 = new Font("Montserrat", Font.BOLD, 18),
            MB23 = new Font("Montserrat", Font.BOLD, 23),
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

        roundedButton.addMouseListener(createHoverEffectMouseListener(roundedButton));

        return roundedButton;
    }

    // refactored genRoundTextField method -removed code smells
    public static JTextField genRoundTextField(String text, int roundness, String colorHex) {
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

        configureRoundField(roundedField);

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

        configureRoundField(roundedField);

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

    // creates a hover-able icon button
    public static JButton genImageButton(String pngFileName, int x, int y) {
        JButton imageButton = new JButton();
        imageButton.setPreferredSize(new Dimension(x, y));
        imageButton.setContentAreaFilled(false);
        imageButton.setBorderPainted(false);

        try {
            // Load and resize image
            BufferedImage originalImage = ImageIO.read(new File(pngFileName));
            Image resizedImage = originalImage.getScaledInstance(x, y, Image.SCALE_SMOOTH);
            ImageIcon defaultIcon = new ImageIcon(resizedImage);

            // Create a darker version for hover effect
            BufferedImage darkenedImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = darkenedImage.createGraphics();
            g2d.drawImage(resizedImage, 0, 0, null);

            // Darken non-transparent parts
            for (int i = 0; i < darkenedImage.getWidth(); i++) {
                for (int j = 0; j < darkenedImage.getHeight(); j++) {
                    int rgba = darkenedImage.getRGB(i, j);
                    int alpha = (rgba >> 24) & 0xFF;
                    if (alpha != 0) {  // if pixel is not transparent
                        int darkColor = new Color(
                                (rgba >> 16) & 0xFF,
                                (rgba >> 8) & 0xFF,
                                rgba & 0xFF).darker().getRGB();
                        darkenedImage.setRGB(i, j, (alpha << 24) | (darkColor & 0x00FFFFFF));
                    }
                }
            }

            g2d.dispose();

            ImageIcon darkIcon = new ImageIcon(darkenedImage);

            imageButton.setIcon(defaultIcon);

            imageButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    imageButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    imageButton.setIcon(darkIcon);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    imageButton.setCursor(Cursor.getDefaultCursor());
                    imageButton.setIcon(defaultIcon);
                }
            });

        } catch (Exception e) {
            System.out.println("Image File \"" + pngFileName + "\" is missing.");
        }

        return imageButton;
    }

    // helper method for hover effects (creates MouseListener) for buttons
    private static MouseAdapter createHoverEffectMouseListener(JComponent component) {
        return createHoverEffectMouseListener(component, Cursor.HAND_CURSOR);
    }

    // helper method for hover effects, MouseListener for text/pass fields
    private static MouseAdapter createHoverEffectMouseListener(JComponent component, int cursorType) {
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
    private static void configureRoundField(JTextComponent field) {
        field.setOpaque(false);
        field.addMouseListener(createHoverEffectMouseListener(field, Cursor.TEXT_CURSOR));
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                field.repaint();
            }
        });

        field.setOpaque(false);

        field.addMouseListener(createHoverEffectMouseListener(field, Cursor.TEXT_CURSOR));
    }

}
