#  Online Train Reservation System

A Java-based Train Reservation System that allows users to book train tickets, retrieve reservation details, and cancel bookings through a graphical user interface. The application uses SQLite to store user accounts, train information, and reservation records.

##  Features

- **User Login:** Secure login interface with validation and rejection of invalid credentials.
- **Train Booking:** Enter passenger details, train number, travel class, journey date, source, and destination.
- **Automatic Train Details:** Automatically populate train names based on the selected train number.
- **PNR Generation:** Generate a unique Passenger Name Record (PNR) for each successful reservation.
- **Booking Confirmation:** Display confirmation after a successful ticket booking.
- **PNR Enquiry:** Retrieve and display existing reservation details using a PNR number.
- **Ticket Cancellation:** Cancel existing reservations with a confirmation prompt.
- **Input Validation:** Validate required fields and numeric train numbers to reduce invalid entries.
- **Database Integration:** Store and manage reservation data using SQLite.

##  Technologies Used

- **Programming Language:** Java
- **GUI Framework:** Java Swing
- **Database:** SQLite
- **Database Connectivity:** JDBC
- **Java Version:** Java 21

##  Sample Train Information

| Train Number | Train Name | Route |
|---|---|---|
| 101 | Lagos Express | Lagos – Ibadan |
| 102 | Benin Express | Benin City – Lagos |

*These are sample train records for demonstration purposes.*

## Database Structure

The application uses three main database tables:

- `users` — stores user login information.
- `trains` — stores train numbers, names, and route information.
- `reservations` — stores passenger details, journey information, and PNR numbers.

##  Getting Started

### Prerequisites

- Java Development Kit (JDK) 21 or compatible version.
- SQLite JDBC driver.
- A Java IDE such as IntelliJ IDEA, Eclipse, or VS Code with Java support.

### Installation

1. Clone or download this repository.
2. Open the project in your preferred Java IDE.
3. Ensure the SQLite JDBC driver is included in the project's classpath.
4. Configure the database connection and ensure the required tables are initialized.
5. Run the application's main Java class.
6. Log in and explore the booking, enquiry, and cancellation features.

##  How to Use

1. Launch the application.
2. Log in with valid credentials.
3. Enter the passenger and journey details.
4. Select the train, travel class, journey date, source, and destination.
5. Submit the booking and receive the generated PNR.
6. Use the PNR enquiry feature to retrieve booking details.
7. To cancel a ticket, enter the relevant PNR and confirm the cancellation.

##  Project Objectives

- Demonstrate Java GUI application development.
- Apply JDBC for database connectivity.
- Implement CRUD operations for reservation management.
- Practise input validation and exception handling.
- Understand how booking references can be generated and used to retrieve reservations.

## Future Improvements

- Online payment integration.
- Email or SMS booking notifications.
- Live train schedules and seat availability.
- Password hashing and improved authentication.
- Deployment as a web-based reservation platform.

## Author

**Isaac Alagi**

Computer Science Student | Aspiring Software Developer

##  License

This project was developed for educational and learning purposes.
