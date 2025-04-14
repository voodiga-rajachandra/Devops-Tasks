package servlets;

import dao.BlogDAO;
import dao.DatabaseConnection;
import models.Blog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet("/GetBlogByIdServlet")
public class GetBlogByIdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.getWriter().write("{}");
            return;
        }

        int blogId = Integer.parseInt(idParam);

        try (Connection con = DatabaseConnection.initializeDatabase();
             PrintWriter out = response.getWriter()) {

            BlogDAO blogDAO = new BlogDAO(con);
            Blog blog = blogDAO.getBlogById(blogId);

            if (blog != null) {
                String json = String.format(
                    "{\"id\":%d,\"user_id\":%d,\"name\":\"%s\",\"description\":\"%s\",\"content\":\"%s\",\"creator\":\"%s\"}",
                    blog.getId(), blog.getUserId(), blog.getName(), blog.getDescription(), blog.getContent(), blog.getCreatorName()
                );
                out.print(json);
            } else {
                out.print("{}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("{}");
        }
    }
}
