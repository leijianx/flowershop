package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static org.example.Utils.login;

@WebServlet("/login")
public class Servlet_login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            //1.获取Session对象
            HttpSession session = request.getSession();
            //2.存储数据
            session.setAttribute("name", name);
            System.out.println("logs:用户"+name+"登录成功");
            response.getWriter().write("登录成功！2秒后跳到主界面…");

            response.setHeader("refresh", "1;url=index.jsp");

        }else {
            System.out.println("logs:用户"+name+"登录失败,数据库没有该用户");

            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("<script language='javascript'>" +
                    "alert('您输入的账号或密码错误，请重新输入！');" +
                    "window.location.href='login.jsp';</script>')");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
