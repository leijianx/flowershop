package org.example;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import static org.example.Utils.DeleteUser;

@WebServlet("/AdminDelete")
public class Servlet_adminDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("yes");
        int userId = Integer.parseInt(request.getParameter("userId"));

        //删除影响的行数
        int n;
        try {
             n = DeleteUser(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(n);
        response.setHeader("refresh", "1;url=AdminPage.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
