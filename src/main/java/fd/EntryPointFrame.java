package fd;

import ia.EntryFramePresenter;
import ia.EntryFrameView;
import ia.RegisterErrorViewModel;

import javax.swing.*;
import java.awt.*;

public class EntryPointFrame extends JFrame implements EntryFrameView {
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
        getContentPane().setBackground(Color.decode("#8F98FF"));

        loginTab = new JButton("Sign In");
        loginTab.setBounds(180, 27, 225, 50);
        loginTab.addActionListener((event) -> showLogin());
        loginTab.setOpaque(false);
        loginTab.setContentAreaFilled(false);
        loginTab.setBorderPainted(false);
        loginTab.setFocusPainted(false);
        add(loginTab);

        signupTab = new JButton("Register");
        signupTab.setBounds(395, 27, 225, 50);
        signupTab.addActionListener((event) -> showSignUp());
        signupTab.setOpaque(false);
        signupTab.setContentAreaFilled(false);
        signupTab.setBorderPainted(false);
        signupTab.setFocusPainted(false);
        add(signupTab);

        signIndicator = new JLabel("");
        signIndicator.setBounds(189, 65, 422, 9);
        add(signIndicator);

        authCodeLabel = new JLabel("Instructor Authentication", SwingConstants.CENTER);
        authCodeLabel.setFont(ComponentFactory.MB16);
        authCodeLabel.setForeground(Color.decode("#172A87"));
        authCodeLabel.setBounds(189, 25, 422, 47);
        authCodePanel.add(authCodeLabel);

        cards.add(loginPanel, "Login");
        cards.add(signupPanel, "Signup");
        cards.add(authCodePanel, "AuthCode");
        cards.setBounds(0, 0, 800, 700);
        cards.setOpaque(false);
        cardLayout.show(cards, "Login");
        add(cards);

        showLogin();
    }

    @Override
    public void displayRegistrationErrors(RegisterErrorViewModel model) {
        signupPanel.displayErrors(model);

    }

    public void showLogin() {
        cardLayout.show(cards, "Login");

        loginTab.setFocusable(false);
        loginTab.setFont(ComponentFactory.MB16);
        loginTab.setForeground(Color.decode("#172A87"));
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
        signupTab.setFont(ComponentFactory.MB14);
        signupTab.setForeground(Color.decode("#FFFFFF"));
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

    @Override
    public void showSignUp() {
        signupPanel.showBasicView();

        cardLayout.show(cards, "Signup");

        loginTab.setFocusable(true);
        loginTab.setFont(ComponentFactory.MB14);
        loginTab.setForeground(Color.decode("#FFFFFF"));
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
        signupTab.setFont(ComponentFactory.MB16);
        signupTab.setForeground(Color.decode("#172A87"));
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
    @Override
    public void showActivationPortal() {
        cardLayout.show(cards, "AuthCode");

        this.remove(loginTab);
        this.remove(signupTab);

        this.add(authCodeLabel);

        signIndicator.setIcon(new ImageIcon("images/00301-auth-indicator.png"));
    }

    @Override
    public void showView() {
        setVisible(true);
    }

    @Override
    public void hideView() {
        setVisible(false);
    }

    @Override
    public void clearInputs() {

    }

    @Override
    public void showInstructorSignUp() {
        signupPanel.showInstructorView();

        cardLayout.show(cards, "Signup");

        this.remove(loginTab);
        this.remove(signupTab);

        authCodeLabel.setText("Instructor Registration");
        this.add(authCodeLabel);

        signIndicator.setIcon(new ImageIcon("images/00301-auth-indicator.png"));
    }

    @Override
    public void showAdminSignUp() {
        signupPanel.showAdminView();

        cardLayout.show(cards, "Signup");

        this.remove(loginTab);
        this.remove(signupTab);

        authCodeLabel.setText("Administrator Registration");
        this.add(authCodeLabel);

        signIndicator.setIcon(new ImageIcon("images/00301-auth-indicator.png"));
    }

    @Override
    public void displayInfoMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void setPresenter(EntryFramePresenter presenter) {
        loginPanel.setPresenter(presenter);
        signupPanel.setPresenter(presenter);
        authCodePanel.setPresenter(presenter);
    }
}
