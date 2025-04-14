package servlets;

import dao.BlogDAO;
import dao.DatabaseConnection;
import models.Blog;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/CreateBlogServlet")
public class CreateBlogServlet extends HttpServlet {

    // Handle GET requests (Optional)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().println("Create Blog Page. Use a POST request to create a blog.");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        // Redirect to login if the user is not authenticated
        if (userId == null) {
            response.sendRedirect("login.html");
            return;
        }

        // Get blog details from the form (trim to remove spaces)
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String content = request.getParameter("content");

        // Basic input validation
        if (name == null || description == null || content == null || 
            name.trim().isEmpty() || description.trim().isEmpty() || content.trim().isEmpty()) {
            response.getWriter().println("Error: All fields are required!");
            return;
        }

        String creatorName = (String) session.getAttribute("userName");
        if (creatorName == null) {
            creatorName = "Unknown"; // Default name if session data is missing
        }

        try (Connection con = DatabaseConnection.initializeDatabase()) {
            BlogDAO blogDAO = new BlogDAO(con);

            // Create a new Blog object
            Blog blog = new Blog(0, userId, name.trim(), description.trim(), content.trim(), creatorName);
            boolean success = blogDAO.createBlog(blog);

            if (success) {
                response.sendRedirect("blogs.html"); // Redirect to blog listing page
            } else {
                response.getWriter().println("Error creating blog!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred: " + e.getMessage());
        }
    }
}
