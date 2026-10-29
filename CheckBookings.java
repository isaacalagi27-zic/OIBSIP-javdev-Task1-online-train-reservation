
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CheckBookings {
    public static void main(String[] args) {
        try (
            Connection conn = Database.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                "SELECT * FROM reservations"
            )
        ) {
            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println("----------------------");
                System.out.println("PNR: " + rs.getString("pnr"));
                System.out.println("Passenger: " + rs.getString("passenger_name"));
                System.out.println("Train: " + rs.getString("train_name"));
                System.out.println("Route: " + rs.getString("source")
                    + " -> " + rs.getString("destination"));
                System.out.println("Date: " + rs.getString("journey_date"));
            }

            if (!found) {
                System.out.println("No bookings found.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
