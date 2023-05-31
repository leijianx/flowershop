<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="org.example.User" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: 18084
  Date: 2023/5/29
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- 网站标题 -->
  <title></title>

  <!-- 初始化样式文件 -->
  <link rel="stylesheet" href="CSS/base.css">
  <!-- 公共样式文件 -->
  <link rel="stylesheet" href="CSS/common.css">
  <link rel="stylesheet" href="CSS/form.css">



</head>

<body>
<!-- 快捷导航栏 -->
<!-- 快捷导航栏 -->
<section class="shortcut">
  <div class="w">
    <div class="fl">
      <ul>
        <li><a href="adminlogin.jsp">鲜花商城</a>&nbsp; 欢迎你！</li>
        <li>
          请
          <a href="login.jsp">登录&nbsp;</a>
          或者
          <a href="register.jsp" class="red_color">注册</a>
          <br>
        </li>
      </ul>
    </div>
  </div>
</section>

<div class="total">
  <ul id="banner">
    <li><img src="img/1.jpeg"></li>
    <li><img src="img/2.jpeg"></li>
    <li><img src="img/3.jpeg"></li>
    <li><img src="img/4.jpeg"></li>
  </ul>
</div>

<%
  //1:注册驱动
  Class.forName("com.mysql.cj.jdbc.Driver");

  //2:获取链接
  String URL = "jdbc:mysql://localhost:3306/flowershop";
  String Username = "root";
  String Password = "sc";
  //返回得到是一个connection对象
  Connection conn = DriverManager.getConnection(URL,Username, 159357+Password);

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
  //释放资源
  resultSet.close();
  preparedStatement.close();
  conn.close();

%>

<script>
  var curindex = 0
  var maxlen = document.getElementById("banner").getElementsByTagName("li").length - 1
  var timer = null
  timer = setInterval(change_auto, 3000)
  function change_auto() {
    if (curindex < maxlen) {
      curindex++;
      get_next();
    } else {
      curindex = 0;
      get_reset()
    }
  }
  var width = 655
  function get_next() {
    var totalbanner = document.getElementById("banner")
    totalbanner.style.marginLeft = "-" + width * curindex + "px"
    totalbanner.style.transition = 0.3 + "s"
  }
  function get_reset() {
    var totalbanner = document.getElementById("banner")
    totalbanner.style.marginLeft = "0px"
    totalbanner.style.transition = "0s"
  }
</script>

</body>

</html>
