package fd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EntryPointFrame extends JFrame implements ActionListener {
    private MouseAdapter cursorHover = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setCursor(Cursor.getDefaultCursor());
        }
    };

    private JButton loginTab, signupTab;
    private JLabel signIndicator;
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
        loginTab.setFont(UI.MB14);
        loginTab.setForeground(Color.decode("#001561"));
        add(loginTab);

        signupTab = new JButton("Register");
        signupTab.setBounds(395, 27, 225, 50);
        signupTab.addActionListener(this);
        signupTab.setOpaque(false);
        signupTab.setContentAreaFilled(false);
        signupTab.setBorderPainted(false);
        signupTab.setFocusPainted(false);
        signupTab.setFont(UI.MB14);
        signupTab.setForeground(Color.decode("#000000"));
        add(signupTab);

        signIndicator = new JLabel("");
        signIndicator.setIcon(new ImageIcon("images/00101-sign-indicator.png"));
        signIndicator.setBounds(189, 65, 422, 9);
        add(signIndicator);

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
        loginTab.removeMouseListener(cursorHover);
        signupTab.setFocusable(true);
        signupTab.addMouseListener(cursorHover);

        signupTab.setFont(UI.MB14);
        signupTab.setForeground(Color.decode("#000000"));
        loginTab.setFont(UI.MB16);
        loginTab.setForeground(Color.decode("#001561"));

        signIndicator.setIcon(new ImageIcon("images/00101-sign-indicator.png"));
    }

    public void signupCard() {
        cardLayout.show(cards, "Signup");

        signupTab.setFocusable(false);
        signupTab.removeMouseListener(cursorHover);
        loginTab.setFocusable(true);
        loginTab.addMouseListener(cursorHover);

        signupTab.setFont(UI.MB16);
        signupTab.setForeground(Color.decode("#001561"));
        loginTab.setFont(UI.MB14);
        loginTab.setForeground(Color.decode("#000000"));

        signIndicator.setIcon(new ImageIcon("images/00201-reg-indicator.png"));
    }

    public void authCodeCard() {
        cardLayout.show(cards, "AuthCode");

        signupTab.setFocusable(true);
        signupTab.addMouseListener(cursorHover);
        loginTab.setFocusable(true);
        loginTab.addMouseListener(cursorHover);

        signupTab.setFont(UI.MB14);
        signupTab.setForeground(Color.decode("#000000"));
        loginTab.setFont(UI.MB14);
        loginTab.setForeground(Color.decode("#000000"));

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
