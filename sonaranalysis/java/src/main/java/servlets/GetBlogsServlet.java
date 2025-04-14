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
import java.util.List;

@WebServlet("/GetBlogsServlet")
public class GetBlogsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (Connection con = DatabaseConnection.initializeDatabase();
             PrintWriter out = response.getWriter()) {

            BlogDAO blogDAO = new BlogDAO(con);
            List<Blog> blogs = blogDAO.getAllBlogs();

            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < blogs.size(); i++) {
                Blog blog = blogs.get(i);
                json.append(String.format(
                    "{\"id\":%d,\"user_id\":%d,\"name\":\"%s\",\"description\":\"%s\",\"creator\":\"%s\"}",
                    blog.getId(), blog.getUserId(), blog.getName(), blog.getDescription(), blog.getCreatorName()
                ));
                if (i < blogs.size() - 1) json.append(",");
            }
            json.append("]");
            
            out.print(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("[]"); // Return empty JSON array in case of error
        }
    }
}
