package DatabaseTesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class DatabaseTestCases {
    // Constant for Database URL
    private static String databaseUrl = "jdbc:mysql://localhost:3306/selenium";
    // Constant for Database Username
    private static String databaseUsername = "root";
    // Constant for Database Password
    private static String databasePassword = "Password";

    // Connection object declaration and initialization.
    private Connection connection = null;
    // Statement object declaration.
    private Statement statement = null;

    @Before
    public void setUp() throws Exception {
        try {
            // Make the database connection and Load MySQL Java Database Connectivity Driver.
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");
            // Create a connection with the Database -> Databse url, Databse username, Database password.
            // Connection URL Syntax: "jdbc:<db_type>://ip_address:port_number/db_name"
            Connection connection = DriverManager.getConnection(databaseUrl, databaseUsername, databasePassword); //MySQL_Password
            System.out.println("Connected to MySQL Database");
            // Use Statement object to send the SQL statement to the Database.
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void read() throws SQLException {
        try {
            // Execute the SQL query and store the result in ResultSet variable.
            ResultSet resultSet = statement.executeQuery("SELECT * FROM selenium_users;"); // table name
            // while loop to iterate through all database records and print the results.
            while (resultSet.next()) {
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                System.out.println(first_name + "\t\t" + last_name + "\t\t" + email);
            }
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void create() {
        try {
            // the insert query
            String query = "INSERT INTO selenium_users (first_name, last_name, email) VALUES ('Ricky', 'Martin', 'ricky@gmail.com');";
            // execute the query
            statement.execute(query);
            System.out.println("Data inserted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        try {
            // the delete query
            String query = "DELETE FROM selenium_users WHERE first_name = 'Super'";
            // execute the query
            statement.execute(query);
            System.out.println("Record deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void update() {
        try {
            // the update query
            String query = "UPDATE selenium_users SET last_name = 'Tudor' WHERE first_name = 'William';";
            // execute the query
            statement.execute(query);
            System.out.println("Record updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws SQLException {
        // Close statement.
        statement.close();
        // Close connection.
        if (connection != null) {
            connection.close();
        }
    }
}
