package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.example.Utils.adminlogin;

@WebServlet("/adminlogin")
public class Servlet_adminlogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        int password = Integer.parseInt(request.getParameter("password"));

        boolean isflag = false;

        try {
            isflag = adminlogin(name,password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (isflag){
            System.out.println("logs:管理员"+name+"登录成功");
            response.getWriter().write("管理员登录成功！2秒后跳到管理员页面");
            //设置3秒钟跳转
            response.setHeader("refresh", "2;url=AdminPage.jsp");
        }else {
            System.out.println("logs:管理员"+name+"登录失败");
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("<script language='javascript'>" +
                    "alert('您输入的账号或密码错误，请重新输入！');" +
                    "window.location.href='adminlogin.jsp';</script>')");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
