import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

     public static Connection connect() {
        String url = "jdbc:sqlite:reservation.db";

         try {
            Connection connection =
                    DriverManager.getConnection(url);

                     System.out.println("Database connected successfully!");
            return connection;

            } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
            return null;
        }
    }

     public static void main(String[] args) {
        connect();
    }
}



