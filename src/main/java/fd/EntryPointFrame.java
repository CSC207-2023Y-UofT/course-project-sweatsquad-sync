package fd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EntryPointFrame extends JFrame implements ActionListener {
    private JButton loginTab, signupTab;
    private JLabel signIndicator, authCodeLabel;
    private CardLayout cardLayout = new CardLayout();
    private JPanel cards = new JPanel(cardLayout);

    public LoginPanel loginPanel = new LoginPanel();
    public SignupPanel signupPanel = new SignupPanel();
    public AuthCodePanel authCodePanel = new AuthCodePanel();
    public EntryPointFrame() {
        setTitle("Login"); // window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ends program if x
        setSize(800, 600); // window dimensions
        setResizable(false); // disables resizing
        setLocationRelativeTo(null); // centers frame
        setLayout(null);
        setBackground(Color.decode("#DADADA"));

        loginTab = new JButton("Sign In");
        loginTab.setBounds(180, 27, 225, 50);
        loginTab.addActionListener(this);
        loginTab.setOpaque(false);
        loginTab.setContentAreaFilled(false);
        loginTab.setBorderPainted(false);
        loginTab.setFocusPainted(false);
        add(loginTab);

        signupTab = new JButton("Register");
        signupTab.setBounds(395, 27, 225, 50);
        signupTab.addActionListener(this);
        signupTab.setOpaque(false);
        signupTab.setContentAreaFilled(false);
        signupTab.setBorderPainted(false);
        signupTab.setFocusPainted(false);
        add(signupTab);

        signIndicator = new JLabel("");
        signIndicator.setBounds(189, 65, 422, 9);
        add(signIndicator);

        authCodeLabel = new JLabel("Instructor Authentication", SwingConstants.CENTER);
        authCodeLabel.setFont(UI.MB16);
        authCodeLabel.setForeground(Color.decode("#001561"));
        authCodeLabel.setBounds(189, 25, 422, 47);
        authCodePanel.add(authCodeLabel);

        cards.add(loginPanel, "Login");
        cards.add(signupPanel, "Signup");
        cards.add(authCodePanel, "AuthCode");
        cards.setBounds(0, 0, 800, 700);
        cards.setOpaque(false);
        cardLayout.show(cards, "Login");
        add(cards);

        loginCard();

    }

    public void loginCard() {
        cardLayout.show(cards, "Login");

        loginTab.setFocusable(false);
        loginTab.setFont(UI.MB16);
        loginTab.setForeground(Color.decode("#001561"));
        // mouse event to revert mouse to default cursor
        dispatchEvent(new MouseEvent(signupTab, MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, 0, 0, 0, false));
        loginTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // no longer shows that the button area's clickable
                setCursor(Cursor.getDefaultCursor());
            }
        });
        this.add(loginTab);

        signupTab.setFocusable(true);
        signupTab.setFont(UI.MB14);
        signupTab.setForeground(Color.decode("#000000"));
        signupTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        });
        this.add(signupTab);

        this.remove(authCodeLabel);

        signIndicator.setIcon(new ImageIcon("images/00101-sign-indicator.png"));
    }

    public void signupCard() {
        signupPanel.reset();

        cardLayout.show(cards, "Signup");

        loginTab.setFocusable(true);
        loginTab.setFont(UI.MB14);
        loginTab.setForeground(Color.decode("#000000"));
        loginTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        });
        this.add(loginTab);

        signupTab.setFocusable(false);
        signupTab.setFont(UI.MB16);
        signupTab.setForeground(Color.decode("#001561"));
        // mouse event to revert mouse to default cursor
        dispatchEvent(new MouseEvent(loginTab, MouseEvent.MOUSE_MOVED, System.currentTimeMillis(), 0, 0, 0, 0, false));
        signupTab.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // no longer shows that the button area's clickable
                setCursor(Cursor.getDefaultCursor());
            }
        });
        this.add(signupTab);

        this.remove(authCodeLabel);

        signIndicator.setIcon(new ImageIcon("images/00201-reg-indicator.png"));
    }

    public void authCodeCard() {
        cardLayout.show(cards, "AuthCode");

        this.remove(loginTab);
        this.remove(signupTab);

        this.add(authCodeLabel);

        signIndicator.setIcon(new ImageIcon("images/00301-auth-indicator.png"));
    }

    public void instructorSignupCard(String auth) {
        signupPanel.setInstructorView(auth);

        cardLayout.show(cards, "Signup");

        this.remove(loginTab);
        this.remove(signupTab);

        authCodeLabel.setText("Instructor Registration");
        this.add(authCodeLabel);

        signIndicator.setIcon(new ImageIcon("images/00301-auth-indicator.png"));
    }

    public void adminSignupCard() {
        signupPanel.setAdminView();

        cardLayout.show(cards, "Signup");

        this.remove(loginTab);
        this.remove(signupTab);

        authCodeLabel.setText("Administrator Registration");
        this.add(authCodeLabel);

        signIndicator.setIcon(new ImageIcon("images/00301-auth-indicator.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginTab)
            loginCard();
        else if (e.getSource() == signupTab)
            signupCard();
    }
}
