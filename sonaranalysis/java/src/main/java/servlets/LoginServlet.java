package servlets;

import dao.DatabaseConnection;
import dao.UserDAO;
import models.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        response.setContentType("text/html");

        try (Connection con = DatabaseConnection.initializeDatabase()) {
            UserDAO userDAO = new UserDAO(con);
            User user = userDAO.loginUser(email, password);

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getId());
                session.setAttribute("userName", user.getName());
                response.sendRedirect("blogs.html");
            } else {
                response.getWriter().println("<h3 style='color:red;'>Invalid email or password!</h3>");
                response.getWriter().println("<a href='login.html'>Back to Login</a>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3 style='color:red;'>Server Error: " + e.getMessage() + "</h3>");
            response.getWriter().println("<a href='login.html'>Back to Login</a>");
        }
    }
}
