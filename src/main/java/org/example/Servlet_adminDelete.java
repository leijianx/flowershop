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
        int userId = Integer.parseInt(request.getParameter("userId"));

        //删除影响的行数
        int n;
        try {
             n = DeleteUser(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (n >0){
            System.out.println("logs:删除成功,删除了id为"+userId+"的用户");
            response.setHeader("refresh", "1;url=AdminPage.jsp");
        }else {
            System.out.println("logs:删除失败,没有id为"+userId+"的用户");

            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("<script language='javascript'>" +
                    "alert('删除失败 , 没有这个id的用户');" +
                    "window.location.href='AdminPage.jsp';</script>')");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
