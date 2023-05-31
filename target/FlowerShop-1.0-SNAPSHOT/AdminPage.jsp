<%@ page import="org.example.User" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 18084
  Date: 2023/5/30
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>后台管理员页面</title>
  <link href="CSS/styles.css" rel="stylesheet">
</head>
<body>
<h1>后台管理员页面</h1>

<h2>删除用户</h2>
<form id="deleteUserForm" method="get" action="/FlowerShop_war_exploded/AdminDelete">
  <label for="userId">用户ID:</label>
  <input id="userId" name="userId" type="text"><br>
  <input type="submit" value="删除">
</form>

<h2>获取所有用户`</h2>
<form id="one" method="get" action="">
  <input type="submit" value="获取">
</form>
<%--连接数据库,获取所有user表数据,并把数据存到集合中--%>
<%
  //1:注册驱动
  Class.forName("com.mysql.cj.jdbc.Driver");

  //2:获取链接
  String URL = "jdbc:mysql://127.0.0.1:3306/shop";
  String Username = "root";
  String Password = "Sakura";
  //返回得到是一个connection对象
  Connection conn = DriverManager.getConnection(URL,Username, Password);

  //3:定义sql语句
  String sql = "select * from user";

  PreparedStatement preparedStatement = conn.prepareStatement(sql);

  ResultSet resultSet = preparedStatement.executeQuery();


  User user = null;
  ArrayList<User> users = new ArrayList<>();
  while (resultSet.next()){
    int id = resultSet.getInt("id");
    String name = resultSet.getString("name");
    int password = resultSet.getInt("password");

    user = new User(id,name,password);

    users.add(user);
  }

  System.out.println(users);
  String StrPassWord = "*********";
  //释放资源
  resultSet.close();
  preparedStatement.close();
  conn.close();
%>

<h2>所有用户列表</h2>
<table id="userListTable">
  <tr>
    <th>用户ID</th>
    <th>用户名</th>
    <th>密码</th>
  </tr>
  <% for (User u : users) { %>
  <tr>
    <td><%= u.getId() %></td>
    <td><%= u.getName() %></td>
    <td><%= StrPassWord%></td>
  </tr>
  <% } %>
</table>

<script>
  var p1 = document.getElementById('one')
  var p2 = document.getElementById('userListTable')
  console.log(p1);
  console.log(p2)
  p1.onmouseover = function(){
    p2.style.visibility = "visible"
  }
  p1.onmouseout = function(){
    p2.style.visibility = "hidden"
  }

</script>
</body>
</html>
