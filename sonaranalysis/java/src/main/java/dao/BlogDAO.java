 package dao;

import models.Blog;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogDAO {
    private Connection con;

    public BlogDAO(Connection con) {
        this.con = con;
    }

    // Create a new blog
    public boolean createBlog(Blog blog) throws SQLException {
        String query = "INSERT INTO blogs (user_id, name, description, content) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, blog.getUserId());
            ps.setString(2, blog.getName());
            ps.setString(3, blog.getDescription());
            ps.setString(4, blog.getContent());
            return ps.executeUpdate() > 0;
        }
    }

    // Fetch all blogs with creator name
    public List<Blog> getAllBlogs() throws SQLException {
        List<Blog> blogs = new ArrayList<>();
        String query = "SELECT blogs.id, blogs.user_id, blogs.name, blogs.description, blogs.content, users.name AS creator_name FROM blogs JOIN users ON blogs.user_id = users.id";

        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                blogs.add(new Blog(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("content"),
                        rs.getString("creator_name")
                ));
            }
        }
        return blogs;
    }

    // Fetch a single blog by ID
    public Blog getBlogById(int id) throws SQLException {
        String query = "SELECT blogs.id, blogs.user_id, blogs.name, blogs.description, blogs.content, users.name AS creator_name FROM blogs JOIN users ON blogs.user_id = users.id WHERE blogs.id = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Blog(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("content"),
                        rs.getString("creator_name")
                );
            }
        }
        return null;
    }

    // Update an existing blog
    public boolean updateBlog(Blog blog) throws SQLException {
        String query = "UPDATE blogs SET name = ?, description = ?, content = ? WHERE id = ? AND user_id = ?";
        
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, blog.getName());
            ps.setString(2, blog.getDescription());
            ps.setString(3, blog.getContent());
            ps.setInt(4, blog.getId());
            ps.setInt(5, blog.getUserId());
            return ps.executeUpdate() > 0;
        }
    }

    // Delete a blog (only if the user is the owner)
    public boolean deleteBlog(int id, int userId) throws SQLException {
        String query = "DELETE FROM blogs WHERE id = ? AND user_id = ?";
        
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.setInt(2, userId);
            return ps.executeUpdate() > 0;
        }
    }

    // Check if the user is the owner of the blog
    public boolean isOwner(int blogId, int userId) throws SQLException {
        String query = "SELECT id FROM blogs WHERE id = ? AND user_id = ?";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, blogId);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // If result exists, user is the owner
        }
    }
}
