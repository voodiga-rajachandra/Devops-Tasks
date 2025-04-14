package servlets;

import dao.BlogDAO;
import dao.DatabaseConnection;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/DeleteBlogServlet")
public class DeleteBlogServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.html");
            return;
        }

        int blogId;
        try {
            blogId = Integer.parseInt(request.getParameter("blogId"));
        } catch (NumberFormatException e) {
            response.getWriter().println("Invalid blog ID.");
            return;
        }

        try (Connection con = DatabaseConnection.initializeDatabase()) {
            BlogDAO blogDAO = new BlogDAO(con);

            // Check if the user owns the blog
            if (!blogDAO.isOwner(blogId, userId)) {
                response.getWriter().println("Error: You are not the owner of this blog.");
                return;
            }

            // Delete the blog
            boolean success = blogDAO.deleteBlog(blogId, userId);

            if (success) {
                response.sendRedirect("blogs.html");
            } else {
                response.getWriter().println("Error deleting blog or unauthorized action!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Internal Server Error: " + e.getMessage());
        }
    }
}
