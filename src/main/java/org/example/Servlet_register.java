package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.example.Utils.Register;

@WebServlet("/register")
public class Servlet_register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("username");
        int password = Integer.parseInt(request.getParameter("password"));

        boolean isflag = false;

        try {
             isflag=Register(name,password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        if (isflag){
            System.out.println("logs:用户"+name+"注册成功");
            response.getWriter().write("注册成功！2秒钟跳到登录页面…");
            //设置3秒钟跳转
            response.setHeader("refresh", "3;url=login.jsp");
        }else {
            System.out.println("logs:用户"+name+"注册失败");

            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("<script language='javascript'>" +
                    "alert('注册失败 , 请重新注册);" +
                    "window.location.href='register.jsp';</script>')");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
