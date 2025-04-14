package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/GetCurrentUserServlet")
public class GetCurrentUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        String userName = (String) session.getAttribute("userName");

        if (userId != null) {
            response.getWriter().write(String.format("{\"id\":%d, \"name\":\"%s\"}", userId, userName));
        } else {
            response.getWriter().write("{}"); // Empty object if not logged in
        }
    }
}
