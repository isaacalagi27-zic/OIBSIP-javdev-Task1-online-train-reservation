import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class MainMenu extends JFrame {


    private final Color navy = new Color(18, 35, 64);
    private final Color gold = new Color(232, 176, 75);
    private final Color background = new Color(245, 247, 251);
    private final Color textColor = new Color(45, 55, 72);


    public MainMenu() {
        setTitle("RailReserve | Dashboard");
        setSize(760, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);


        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(background);


        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(navy);
        header.setBorder(new EmptyBorder(20, 30, 20, 30));

      

      JLabel logo = new JLabel("RAILRESERVE");
        logo.setFont(new Font("SansSerif", Font.BOLD, 23));
        logo.setForeground(gold);


        JLabel headerText = new JLabel("ONLINE RESERVATION SYSTEM");
        headerText.setFont(new Font("SansSerif", Font.PLAIN, 12));
        headerText.setForeground(Color.WHITE);



        header.add(logo, BorderLayout.WEST);
        header.add(headerText, BorderLayout.EAST);


          // Main content
        JPanel content = new JPanel();
        content.setBackground(background);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(new EmptyBorder(35, 45, 30, 45));



        JLabel welcome = new JLabel("Welcome to RailReserve");
        welcome.setFont(new Font("SansSerif", Font.BOLD, 28));
        welcome.setForeground(navy);
        welcome.setAlignmentX(Component.LEFT_ALIGNMENT);


        JLabel subtitle = new JLabel(
            "Where would you like to go today?"
        );


        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 15));
        subtitle.setForeground(new Color(100, 110, 125));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);


        content.add(welcome);
        content.add(Box.createVerticalStrut(8));
        content.add(subtitle);
        content.add(Box.createVerticalStrut(30));


        // Action buttons
        JButton bookButton = createButton(
            "Book a Ticket", navy, Color.WHITE
        );


        JButton cancelButton = createButton(
            "Cancel a Reservation", Color.WHITE, navy
        );


        cancelButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                new Color(215, 222, 232)
            ),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));


        JButton logoutButton = createButton(
            "Logout", new Color(245, 247, 251), textColor
        );
        logoutButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                new Color(215, 222, 232)
            ),
            BorderFactory.createEmptyBorder(12, 15, 12, 15)
        ));

        bookButton.addActionListener(e -> {
            new Reservation().setVisible(true);
        });


        cancelButton.addActionListener(e -> {
            new Cancellation().setVisible(true);
        });


         logoutButton.addActionListener(e -> {
            dispose();
            new Main().setVisible(true);
        });


        content.add(bookButton);
        content.add(Box.createVerticalStrut(15));
        content.add(cancelButton);
        content.add(Box.createVerticalStrut(15));
        content.add(logoutButton);

        // Footer
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(Color.WHITE);
        footer.setBorder(new EmptyBorder(14, 30, 14, 30));

        JLabel footerText = new JLabel(
            "Your journey starts here."
        );
        footerText.setFont(new Font("SansSerif", Font.PLAIN, 12));
        footerText.setForeground(new Color(100, 110, 125));


        JLabel status = new JLabel("RESERVATION PORTAL");
        status.setFont(new Font("SansSerif", Font.BOLD, 10));
        status.setForeground(navy);


        footer.add(footerText, BorderLayout.WEST);
        footer.add(status, BorderLayout.EAST);


        root.add(header, BorderLayout.NORTH);
        root.add(content, BorderLayout.CENTER);
        root.add(footer, BorderLayout.SOUTH);

        setContentPane(root);
    }


    private JButton createButton(
        String text, Color backgroundColor, Color foregroundColor
    ) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 15));
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setFocusPainted(false);
        button.setCursor(
            Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
        button.setPreferredSize(new Dimension(400, 48));
        button.setBorder(BorderFactory.createEmptyBorder(
            12, 15, 12, 15
        ));
        button.setOpaque(true);
        button.setBorderPainted(
            backgroundColor.equals(navy)
        );
        return button;
    }

     public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new MainMenu().setVisible(true)
        );
    }
}







