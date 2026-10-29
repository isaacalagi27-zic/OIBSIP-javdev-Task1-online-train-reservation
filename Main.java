import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

     private final Color navy = new Color(18, 35, 64);
    private final Color gold = new Color(232, 176, 75);
    private final Color background = new Color(245, 247, 251);
    private final Color textColor = new Color(45, 55, 72);


    public Main() {
        setTitle("RailReserve | Online Reservation System");
        setSize(760, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);


        JPanel root = new JPanel(new GridLayout(1, 2));
        root.setBackground(background);

        // Left welcome panel
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBackground(navy);
        welcomePanel.setLayout(new BoxLayout(
            welcomePanel, BoxLayout.Y_AXIS
        ));


         welcomePanel.setBorder(
            new EmptyBorder(45, 30, 35, 30)
        );

        JLabel logo = new JLabel("RAILRESERVE");
        logo.setFont(new Font("SansSerif", Font.BOLD, 25));
        logo.setForeground(gold);
        logo.setAlignmentX(Component.LEFT_ALIGNMENT);


        JLabel trainIcon = new JLabel("\uD83D\uDE86");
        trainIcon.setFont(new Font("SansSerif", Font.PLAIN, 55));
        trainIcon.setAlignmentX(Component.LEFT_ALIGNMENT);


        JLabel welcome = new JLabel(
            "<html>Every journey<br>starts here.</html>"
        );
        welcome.setFont(new Font("SansSerif", Font.BOLD, 29));
        welcome.setForeground(Color.WHITE);
        welcome.setAlignmentX(Component.LEFT_ALIGNMENT);


        JLabel description = new JLabel(
            "<html><div style='width:230px'>"
            + "Book your train journey with ease. "
            + "Manage reservations and travel "
            + "with confidence.</div></html>"
        );

        description.setFont(new Font("SansSerif", Font.PLAIN, 14));
        description.setForeground(new Color(210, 220, 235));
        description.setAlignmentX(Component.LEFT_ALIGNMENT);

        welcomePanel.add(logo);
        welcomePanel.add(Box.createVerticalStrut(55));
        welcomePanel.add(trainIcon);
        welcomePanel.add(Box.createVerticalStrut(18));
        welcomePanel.add(welcome);
        welcomePanel.add(Box.createVerticalStrut(15));
        welcomePanel.add(description);


        // Right login panel
        JPanel loginPanel = new JPanel();
        loginPanel.setBackground(Color.WHITE);
        loginPanel.setLayout(new BoxLayout(
            loginPanel, BoxLayout.Y_AXIS
        ));
        loginPanel.setBorder(
            new EmptyBorder(55, 38, 40, 38)
        );

       JLabel heading = new JLabel(" journey starts here.");
        heading.setFont(new Font("SansSerif", Font.BOLD, 27));
        heading.setForeground(navy);
        heading.setAlignmentX(Component.LEFT_ALIGNMENT);



       
        JLabel subtitle = new JLabel("Sign in to continue");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitle.setForeground(Color.GRAY);
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);


        JLabel usernameLabel = new JLabel("Username");
        styleLabel(usernameLabel);

        usernameField = new JTextField();
        styleField(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        styleLabel(passwordLabel);

        passwordField = new JPasswordField();
        styleField(passwordField);

        JButton loginButton = new JButton("Sign In");
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 15));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(navy);
        loginButton.setFocusPainted(false);
        loginButton.setCursor(
            Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );

        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginButton.setMaximumSize(
            new Dimension(Integer.MAX_VALUE, 44)
            );
        loginButton.setBorder(
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        );
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);


        JLabel footer = new JLabel(
            "Secure access to your reservations"
        );
        footer.setFont(new Font("SansSerif", Font.PLAIN, 11));
        footer.setForeground(Color.GRAY);
        footer.setAlignmentX(Component.LEFT_ALIGNMENT);


        loginPanel.add(heading);
        loginPanel.add(Box.createVerticalStrut(8));
        loginPanel.add(subtitle);
        loginPanel.add(Box.createVerticalStrut(30));
        loginPanel.add(usernameLabel);
        loginPanel.add(Box.createVerticalStrut(7));
        loginPanel.add(usernameField);
        loginPanel.add(Box.createVerticalStrut(18));
        loginPanel.add(passwordLabel);
        loginPanel.add(Box.createVerticalStrut(7));
        loginPanel.add(passwordField);
        loginPanel.add(Box.createVerticalStrut(25));
        loginPanel.add(loginButton);
        loginPanel.add(Box.createVerticalStrut(22));
        loginPanel.add(footer);


        root.add(welcomePanel);
        root.add(loginPanel);
        setContentPane(root);

        loginButton.addActionListener(e -> login());
        getRootPane().setDefaultButton(loginButton);
    }

    private void styleLabel(JLabel label) {
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(textColor);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
    }

    private void styleField(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        field.setPreferredSize(new Dimension(250, 42));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                new Color(215, 222, 232)
            ),

            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        field.setBackground(new Color(250, 251, 253));
    }


   
private void login() {
    String username = usernameField.getText().trim();
    String password = new String(passwordField.getPassword());

    if (username.isEmpty() || password.isEmpty()) {
        JOptionPane.showMessageDialog(
            this,
            "Please enter your username and password.",
            "Missing Information",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    String sql =
        "SELECT id FROM users WHERE username = ? AND password = ?";

    try (Connection conn = Database.connect()) {
        if (conn == null) {
            JOptionPane.showMessageDialog(
                this, "Unable to connect to the database."
            );
            return;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    showSuccessDialog();
                    dispose();
                    new MainMenu().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(
                        this,
                        "Invalid username or password!",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(
            this,
            "Login error: " + ex.getMessage()
        );
    }
}


private void showSuccessDialog() {
    JDialog dialog = new JDialog(this, "Login Successful", true);
    dialog.setSize(400, 240);
    dialog.setResizable(false);
    dialog.setLocationRelativeTo(this);

    Color navy = new Color(18, 35, 64);
    Color gold = new Color(232, 176, 75);

    JPanel root = new JPanel(new BorderLayout());
    root.setBackground(Color.WHITE);

    JPanel header = new JPanel(new BorderLayout());
    header.setBackground(navy);
    header.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

    JLabel logo = new JLabel("RAILRESERVE");
    logo.setFont(new Font("SansSerif", Font.BOLD, 17));
    logo.setForeground(gold);
    header.add(logo, BorderLayout.WEST);

    JPanel body = new JPanel();
    body.setBackground(Color.WHITE);
    body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
    body.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

    JLabel heading = new JLabel("Login successful!");
    heading.setFont(new Font("SansSerif", Font.BOLD, 20));
    heading.setForeground(navy);
    heading.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel message = new JLabel("Welcome to your reservation dashboard.");
    message.setFont(new Font("SansSerif", Font.PLAIN, 12));
    message.setForeground(new Color(100, 110, 125));
    message.setAlignmentX(Component.CENTER_ALIGNMENT);

    JButton continueButton = new JButton("Continue");
    continueButton.setFont(new Font("SansSerif", Font.BOLD, 13));
    continueButton.setForeground(Color.WHITE);
    continueButton.setBackground(navy);
    continueButton.setFocusPainted(false);
    continueButton.setOpaque(true);
    continueButton.setBorderPainted(false);
    continueButton.setCursor(
        Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
    );
    continueButton.setPreferredSize(new Dimension(130, 38));
    continueButton.setMaximumSize(new Dimension(130, 38));
    continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    continueButton.addActionListener(e -> dialog.dispose());

    body.add(heading);
    body.add(Box.createVerticalStrut(8));
    body.add(message);
    body.add(Box.createVerticalStrut(20));
    body.add(continueButton);

    root.add(header, BorderLayout.NORTH);
    root.add(body, BorderLayout.CENTER);

    dialog.setContentPane(root);
    dialog.setVisible(true);
}








public static void main(String[] args) {
        SwingUtilities.invokeLater(
            () -> new Main().setVisible(true)
        );
    }
}




