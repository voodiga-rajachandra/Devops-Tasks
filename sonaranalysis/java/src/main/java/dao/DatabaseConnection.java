package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/BlogApp?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // Change if needed
    private static final String PASSWORD = "78badsad70"; // Change if needed

    public static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
        // Load MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish and return connection
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
