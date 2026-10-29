import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.UUID;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Reservation extends JFrame {


    private JTextField passengerField;
    private JTextField trainNumberField;
    private JTextField trainNameField;
    private JComboBox<String> classBox;
    private JTextField dateField;
    private JTextField sourceField;
    private JTextField destinationField;


    private final Color navy = new Color(18, 35, 64);
    private final Color gold = new Color(232, 176, 75);
    private final Color background = new Color(245, 247, 251);
    private final Color textColor = new Color(45, 55, 72);


    public Reservation() {
        setTitle("RailReserve | Book a Ticket");
        setSize(760, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(background);


        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(navy);
        header.setBorder(new EmptyBorder(18, 28, 18, 28));

        JLabel logo = new JLabel("RAILRESERVE");
        logo.setFont(new Font("SansSerif", Font.BOLD, 22));
        logo.setForeground(gold);

        JLabel headerLabel = new JLabel("TICKET BOOKING");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        headerLabel.setForeground(Color.WHITE);

        header.add(logo, BorderLayout.WEST);
        header.add(headerLabel, BorderLayout.EAST);

        // Main form area
        JPanel content = new JPanel(new BorderLayout(0, 20));
        content.setBackground(background);
        content.setBorder(new EmptyBorder(25, 35, 25, 35));

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(background);
        titlePanel.setLayout(new BoxLayout(
            titlePanel, BoxLayout.Y_AXIS
        ));


        JLabel title = new JLabel("Book your journey");
        title.setFont(new Font("SansSerif", Font.BOLD, 27));
        title.setForeground(navy);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subtitle = new JLabel(
            "Enter your travel details below to reserve a ticket."
        );

        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitle.setForeground(new Color(100, 110, 125));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);


        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(7));
        titlePanel.add(subtitle);


        // Form fields
        JPanel form = new JPanel(new GridLayout(0, 2, 18, 15));
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                new Color(225, 230, 238)
            ),
            new EmptyBorder(22, 22, 22, 22)
        ));


        passengerField = new JTextField();
        trainNumberField = new JTextField();
        trainNameField = new JTextField();
        trainNameField.setEditable(false);

        classBox = new JComboBox<>(new String[]{
            "Select Class", "Economy", "Business", "First Class"
        });


        dateField = new JTextField();
        dateField.setToolTipText("Enter date as YYYY-MM-DD");


        sourceField = new JTextField();
        destinationField = new JTextField();


        styleField(passengerField);
        styleField(trainNumberField);
        styleField(trainNameField);
        styleField(dateField);
        styleField(sourceField);
        styleField(destinationField);


        classBox.setFont(new Font("SansSerif", Font.PLAIN, 14));
        classBox.setBackground(Color.WHITE);
        classBox.setPreferredSize(new Dimension(200, 40));


        addField(form, "Passenger Name", passengerField);
        addField(form, "Train Number", trainNumberField);
        addField(form, "Train Name (Automatic)", trainNameField);
        addField(form, "Class Type", classBox);
        addField(form, "Journey Date (YYYY-MM-DD)", dateField);
        addField(form, "Source Station", sourceField);
        addField(form, "Destination Station", destinationField);


        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(
            FlowLayout.RIGHT, 10, 0
        ));
        buttonPanel.setBackground(background);




JButton backButton = new JButton("Cancel");
backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
backButton.setForeground(navy);
backButton.setBackground(Color.WHITE);
backButton.setFocusPainted(false);
backButton.setOpaque(true);
backButton.setContentAreaFilled(true);

backButton.setBorder(
    BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(
            new Color(200, 210, 224), 1
        ),
        BorderFactory.createEmptyBorder(10, 20, 10, 20)
    )
);

backButton.setCursor(
    Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
);

backButton.setPreferredSize(new Dimension(125, 42));
backButton.addActionListener(e -> dispose());

        JButton bookButton = new JButton("Confirm Booking");
        bookButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        bookButton.setBackground(navy);
        bookButton.setForeground(Color.WHITE);
        bookButton.setFocusPainted(false);
        bookButton.setOpaque(true);
        bookButton.setBorderPainted(false);
        bookButton.setCursor(
            Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        );
        bookButton.setPreferredSize(new Dimension(170, 42));


        buttonPanel.add(backButton);
        buttonPanel.add(bookButton);

        content.add(titlePanel, BorderLayout.NORTH);
        content.add(form, BorderLayout.CENTER);
        content.add(buttonPanel, BorderLayout.SOUTH);

        root.add(header, BorderLayout.NORTH);
        root.add(content, BorderLayout.CENTER);

        setContentPane(root);


        trainNumberField.addActionListener(e -> fetchTrain());
        trainNumberField.addFocusListener(
            new java.awt.event.FocusAdapter() {
                @Override
                public void focusLost(java.awt.event.FocusEvent e) {
                    fetchTrain();
                }
            }
        );

         bookButton.addActionListener(e -> bookTicket());
    }

    private void addField(
        JPanel form, String labelText, JComponent field
    ) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(textColor);

        form.add(label);
        form.add(field);
    }


    private void styleField(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(200, 40));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(
                new Color(215, 222, 232)
            ),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        field.setBackground(new Color(250, 251, 253));
    }


    private void fetchTrain() {
        String number = trainNumberField.getText().trim();

        if (number.isEmpty()) {
            trainNameField.setText("");
            sourceField.setText("");
            destinationField.setText("");
            return;
        }


        try {
            int trainNumber = Integer.parseInt(number);

            String sql =
                "SELECT train_name, source, destination "
                + "FROM trains WHERE train_number = ?";


                try (Connection conn = Database.connect()) {
                if (conn == null) {
                    JOptionPane.showMessageDialog(
                        this, "Unable to connect to the database."
                    );
                    return;
                }


                try (PreparedStatement stmt =
                    conn.prepareStatement(sql)) {

                    stmt.setInt(1, trainNumber);

                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            trainNameField.setText(
                                rs.getString("train_name")
                            );


                            sourceField.setText(
                                rs.getString("source")
                            );
                            destinationField.setText(
                                rs.getString("destination")
                            );

                            } else {
                            trainNameField.setText("");
                            sourceField.setText("");
                            destinationField.setText("");

                            JOptionPane.showMessageDialog(
                                this, "Train number not found."
                            );
                        }
                    }
                }
                
             }

             } catch (NumberFormatException ex) {
            trainNameField.setText("");
            sourceField.setText("");
            destinationField.setText("");

            JOptionPane.showMessageDialog(
                this, "Train number must be numeric."
            );


            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this, "Error fetching train: " + ex.getMessage()
            );
        }
    }


    private void bookTicket() {
        String passenger = passengerField.getText().trim();
        String trainText = trainNumberField.getText().trim();
        String trainName = trainNameField.getText().trim();
        String classType = (String) classBox.getSelectedItem();
        String date = dateField.getText().trim();
        String source = sourceField.getText().trim();
        String destination = destinationField.getText().trim();


        if (passenger.isEmpty() || trainText.isEmpty()
                || trainName.isEmpty() || date.isEmpty()
                || source.isEmpty() || destination.isEmpty()
                || classBox.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(
                this, "Please fill in all required fields."
            );
            return;
        }


        int trainNumber;

        try {
            trainNumber = Integer.parseInt(trainText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this, "Train number must be numeric."
            );
            return;
        }



        try {
            LocalDate journeyDate = LocalDate.parse(date);

            if (journeyDate.isBefore(LocalDate.now())) {
                JOptionPane.showMessageDialog(
                    this, "Journey date cannot be in the past."
                );
                return;
            }


            } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                this, "Enter a valid date as YYYY-MM-DD."
            );
            return;
        }


        String pnr = UUID.randomUUID()
            .toString()
            .replace("-", "")
            .substring(0, 10)
            .toUpperCase();


            String sql = "INSERT INTO reservations "
            + "(pnr, passenger_name, train_number, train_name, "
            + "class_type, journey_date, source, destination) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


            try (Connection conn = Database.connect()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(
                    this, "Unable to connect to the database."
                );
                return;
            }


            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, pnr);
                stmt.setString(2, passenger);
                stmt.setInt(3, trainNumber);
                stmt.setString(4, trainName);
                stmt.setString(5, classType);
                stmt.setString(6, date);
                stmt.setString(7, source);
                stmt.setString(8, destination);


                stmt.executeUpdate();

                JOptionPane.showMessageDialog(
                    this,
                    "Booking successful!\n\n"
                    + "PNR: " + pnr + "\n"
                    + "Passenger: " + passenger + "\n"
                    + "Train: " + trainName + " (" + trainNumber + ")\n"
                    + "Class: " + classType + "\n"
                    + "Journey Date: " + date + "\n"
                    + "Route: " + source + " to " + destination,
                    "Booking Confirmation",
                    JOptionPane.INFORMATION_MESSAGE
                );

            }

            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this, "Booking failed: " + ex.getMessage()
            );
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(
            () -> new Reservation().setVisible(true)
        );
    }
}








