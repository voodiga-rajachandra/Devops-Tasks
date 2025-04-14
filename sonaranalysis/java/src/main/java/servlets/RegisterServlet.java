package servlets;

import dao.DatabaseConnection;
import dao.UserDAO;
import models.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password"); // Directly store password

        try (Connection con = DatabaseConnection.initializeDatabase()) {
            UserDAO userDAO = new UserDAO(con);

            // Check if the email already exists
            if (userDAO.emailExists(email)) {
                response.getWriter().println("Error: Email already exists. Try another email.");
                return;
            }

            // Register user (Plain password, no hashing)
            boolean success = userDAO.registerUser(new User(0, name, email, password));

            if (success) {
                response.sendRedirect("login.html");
            } else {
                response.getWriter().println("Error in registration!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
