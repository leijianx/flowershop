package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.example.Utils.login;

@WebServlet("/login")
public class Servlet_login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("login");
        String name = request.getParameter("username");
        int password = Integer.parseInt(request.getParameter("password"));

        boolean isflag = false;

        try {
            isflag = login(name,password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (isflag){
            System.out.println("logs:用户"+name+"登录成功");
            response.getWriter().write("登录成功！2秒后跳到主界面…");
            //设置3秒钟跳转
            response.setHeader("refresh", "2;url=index.jsp");

        }else {
            System.out.println("logs:用户"+name+"登录成功");
            response.getWriter().write("登录失败！2秒后请重新登录…");
            //设置3秒钟跳转
            response.setHeader("refresh", "2;url=login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
