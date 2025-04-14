package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import dao.DatabaseConnection;
import dao.BlogDAO;
import models.Blog;

@WebServlet("/UpdateBlogServlet")
public class UpdateBlogServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.html");
            return;
        }

        String blogIdStr = request.getParameter("blogId");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String content = request.getParameter("content");

        if (blogIdStr == null || name == null || description == null || content == null) {
            response.getWriter().println("Error: Missing parameters.");
            return;
        }

        try (Connection conn = DatabaseConnection.initializeDatabase()) {
            int blogId = Integer.parseInt(blogIdStr);
            BlogDAO blogDAO = new BlogDAO(conn);

            Blog blog = blogDAO.getBlogById(blogId);
            if (blog == null || blog.getUserId() != userId) {
                response.getWriter().println("Error: Unauthorized.");
                return;
            }

            // ✅ Fix: Pass `blog.getCreatorName()` instead of empty string
            Blog updatedBlog = new Blog(blogId, userId, name, description, content, blog.getCreatorName());
            boolean success = blogDAO.updateBlog(updatedBlog);

            if (success) {
                response.sendRedirect("blogs.html");
            } else {
                response.getWriter().println("Error: Failed to update blog.");
            }
        } catch (NumberFormatException e) {
            response.getWriter().println("Error: Invalid blog ID.");
        } catch (SQLException e) {
            response.getWriter().println("Error: Database issue.");
        } catch (Exception e) {
            response.getWriter().println("Error: Something went wrong.");
        }
    }
}
