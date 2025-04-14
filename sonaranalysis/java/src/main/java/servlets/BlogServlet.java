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
import java.util.List;

@WebServlet("/BlogServlet")
public class BlogServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.html");
            return;
        }

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String content = request.getParameter("content");

        try (Connection con = DatabaseConnection.initializeDatabase()) {
            BlogDAO blogDAO = new BlogDAO(con);
            Blog blog = new Blog(0, userId, name, description, content, "");
            boolean success = blogDAO.createBlog(blog);

            if (success) {
                response.sendRedirect("blogs.html");
            } else {
                response.getWriter().println("Error creating blog!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection con = DatabaseConnection.initializeDatabase()) {
            BlogDAO blogDAO = new BlogDAO(con);
            List<Blog> blogs = blogDAO.getAllBlogs();
            request.setAttribute("blogs", blogs);
            request.getRequestDispatcher("blogs.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
