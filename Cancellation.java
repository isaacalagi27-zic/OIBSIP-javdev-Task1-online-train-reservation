import java.awt.*;
import java.sql.*;
import javax.swing.*;


public class Cancellation extends JFrame {


    private JTextField pnrField;
    private JTextArea detailsArea;
    private JButton fetchButton;
    private JButton cancelButton;

    public Cancellation() {
        setTitle("Online Reservation System - Cancel Ticket");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );


        JPanel searchPanel = new JPanel(new GridLayout(2, 2, 10, 10));



        pnrField = new JTextField();
        fetchButton = new JButton("Fetch Booking");


        searchPanel.add(new JLabel("Enter PNR Number:"));
        searchPanel.add(pnrField);
        searchPanel.add(new JLabel(""));
        searchPanel.add(fetchButton);


        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        cancelButton = new JButton("Confirm Cancellation");
        cancelButton.setEnabled(false);


        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(detailsArea), BorderLayout.CENTER);
        mainPanel.add(cancelButton, BorderLayout.SOUTH);


        add(mainPanel);

        fetchButton.addActionListener(e -> fetchBooking());
        cancelButton.addActionListener(e -> cancelBooking());
    }


     private void fetchBooking() {
        String pnr = pnrField.getText().trim();


        if (pnr.isEmpty()) {
            JOptionPane.showMessageDialog(
                this, "Please enter a PNR number."
            );
            return;
        }

        String sql = "SELECT * FROM reservations WHERE pnr = ?";

        try (Connection conn = Database.connect()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(
                    this, "Database connection failed."
                );
                return;
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, pnr);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        detailsArea.setText(
                            "PNR: " + rs.getString("pnr") + "\n"
                            + "Passenger: " + rs.getString("passenger_name") + "\n"
                            + "Train Number: " + rs.getInt("train_number") + "\n"
                            + "Train Name: " + rs.getString("train_name") + "\n"
                            + "Class: " + rs.getString("class_type") + "\n"
                            + "Journey Date: " + rs.getString("journey_date") + "\n"
                            + "Source: " + rs.getString("source") + "\n"
                            + "Destination: " + rs.getString("destination")
                        );


                        cancelButton.setEnabled(true);
                    } else {
                        detailsArea.setText("");
                        cancelButton.setEnabled(false);

                        JOptionPane.showMessageDialog(
                            this, "No booking found for that PNR."
                        );
                    }
                }
            }

             } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this, "Error fetching booking: " + ex.getMessage()
            );
        }

    }

     private void cancelBooking() {
        String pnr = pnrField.getText().trim();

        int choice = showCancellationDialog();

if (choice != JOptionPane.YES_OPTION) {
    return;
}

        String sql = "DELETE FROM reservations WHERE pnr = ?";


        try (Connection conn = Database.connect()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(
                    this, "Database connection failed."
                );
                return;
            }


            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, pnr);

                int rowsDeleted = stmt.executeUpdate();

                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(
                        this, "Booking cancelled successfully."
                    );


                    detailsArea.setText("");
                    pnrField.setText("");
                    cancelButton.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(
                        this, "Booking was not found."
                    );
                }
            }

            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                this, "Cancellation failed: " + ex.getMessage()
            );
        }
    }




private int showCancellationDialog() {
    final int[] choice = {JOptionPane.NO_OPTION};

    JDialog dialog = new JDialog(this, "Confirm Cancellation", true);
    dialog.setSize(400, 230);
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
    body.setBorder(BorderFactory.createEmptyBorder(20, 15, 15, 15));

    JLabel message = new JLabel("Cancel this reservation?");
    message.setFont(new Font("SansSerif", Font.BOLD, 18));
    message.setForeground(navy);
    message.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel detail = new JLabel("This action cannot be undone.");
    detail.setFont(new Font("SansSerif", Font.PLAIN, 13));
    detail.setForeground(new Color(100, 110, 125));
    detail.setAlignmentX(Component.CENTER_ALIGNMENT);

    JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
    buttons.setBackground(Color.WHITE);

    JButton keepButton = new JButton("Keep Booking");
    keepButton.setForeground(navy);
    keepButton.setBackground(Color.WHITE);
    keepButton.setFocusPainted(false);
    keepButton.addActionListener(e -> dialog.dispose());

    JButton cancelButton = new JButton("Cancel Ticket");
    cancelButton.setForeground(Color.WHITE);
    cancelButton.setBackground(navy);
    cancelButton.setFocusPainted(false);
    cancelButton.setOpaque(true);
    cancelButton.setBorderPainted(false);
    cancelButton.addActionListener(e -> {
        choice[0] = JOptionPane.YES_OPTION;
        dialog.dispose();
    });

    buttons.add(keepButton);
    buttons.add(cancelButton);

    body.add(message);
    body.add(Box.createVerticalStrut(8));
    body.add(detail);
    body.add(Box.createVerticalStrut(20));
    body.add(buttons);

    root.add(header, BorderLayout.NORTH);
    root.add(body, BorderLayout.CENTER);

    dialog.setContentPane(root);
    dialog.setVisible(true);

    return choice[0];
}





    public static void main(String[] args) {
        SwingUtilities.invokeLater(
            () -> new Cancellation().setVisible(true)
        );
    }
}











