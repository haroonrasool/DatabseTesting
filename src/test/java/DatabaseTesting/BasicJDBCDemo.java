package DatabaseTesting;

import java.sql.*;

public class BasicJDBCDemo {
    Connection connection = null;

    public static void main(String[] args) {
        new BasicJDBCDemo();
    }

    public BasicJDBCDemo() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost/coffeebreak";
            connection = DriverManager.getConnection(url, "root", "Password");
            doTests();
            connection.close();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.err.println(ex.getMessage());
        } catch (InstantiationException ex) {
            System.err.println(ex.getMessage());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void doTests() {
        doSelectTest();
        doInsertTest();
        doSelectTest();
        doUpdateTest();
        doSelectTest();
        doDeleteTest();
        doSelectTest();
    }

    private void doSelectTest() {
        System.out.println("[OUTPUT FROM SELECT]");
        String query = "SELECT COF_NAME, PRICE FROM COFFEES";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String s = rs.getString("COF_NAME");
                float n = rs.getFloat("PRICE");
                System.out.println(s + "\t\t" + n);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void doInsertTest() {
        System.out.print("\n[Performing INSERT] ... ");
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("INSERT INTO COFFEES " +
                    "VALUES ('BREAKFAST BLEND', 200, 7.99, 0, 0)");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void doUpdateTest() {
        System.out.print("\n[Performing UPDATE] ... ");
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("UPDATE COFFEES SET PRICE=4.99 WHERE COF_NAME = 'BREAKFAST BLEND'");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void doDeleteTest() {
        System.out.print("\n[Performing DELETE] ... ");
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("DELETE FROM COFFEES WHERE COF_NAME = 'BREAKFAST BLEND'");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
