package dao;

import models.User;
import java.sql.*;

public class UserDAO {
    private Connection con;

    public UserDAO(Connection con) {
        this.con = con;
    }

    // Register User (Store Plain Password)
    public boolean registerUser(User user) throws SQLException {
        String query = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPassword()); // No Hashing, Store Directly
        return ps.executeUpdate() > 0;
    }

    // Login User (Direct Comparison)
    public User loginUser(String email, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?"; // Direct match
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, email);
        ps.setString(2, password); // No Hashing, Compare Directly
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("password"));
        }
        return null;
    }

    // Check if Email Already Exists
    public boolean emailExists(String email) throws SQLException {
        String query = "SELECT id FROM users WHERE email = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, email);
        ResultSet rs = ps.executeQuery();
        return rs.next(); // If there is a result, email already exists
    }
}
