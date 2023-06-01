<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="org.example.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="static org.example.Utils.GetConnection" %>

<%--
  Created by IntelliJ IDEA.
  User: 18084
  Date: 2023/5/29
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
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
          请<a href="login.jsp">登录&nbsp;</a>或者<a href="register.jsp" class="red_color">注册</a>

          <br>
        </li>
      </ul>
    </div>
    <div class="fr">
      <ul>
        <%
          //1.获取Session对象
          HttpSession session1 = request.getSession();
          //2.获取数据
          Object name1 = session.getAttribute("name");
        %>
        <li>用户:&nbsp;<a href="ShopCar.jsp"><%= name1 %></a></li>
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


  //3:定义sql语句
  String sql = "select * from user";

  PreparedStatement preparedStatement = GetConnection().prepareStatement(sql);

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
  GetConnection().close();

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


<!-- 商品列表 -->
<div class="guess_like w">
  <h3>商品列表</h3>
  <div class="guess_thing">
    <ul>
      <li>
        <img src="img/1.jpeg" alt="">
        <div class="guess_font">
          <div>花1</div>
          <div class="price">￥10.00</div>
          <form method="get" action="/FlowerShop_war_exploded/AdminDelete">
            <input type="submit" value="加入购物车">
          </form>

        </div>
      </li>
      <li>
        <img src="img/2.jpeg" alt="">
        <div class="guess_font">
          <div>花2</div>
          <div class="price">￥20.00</div>
          <form method="get" action="/FlowerShop_war_exploded/AdminDelete">
            <input type="submit" value="加入购物车">
          </form>
        </div>
      </li>
      <li>
        <img src="img/3.jpeg" alt="">
        <div class="guess_font">
          <div>花3</div>
          <div class="price">￥30.00</div>
          <form method="get" action="/FlowerShop_war_exploded/AdminDelete">
            <input type="submit" value="加入购物车">
          </form>
        </div>
      </li>
      <li>
        <img src="img/4.jpeg" alt="">
        <div class="guess_font">
          <div>花4</div>
          <div class="price">￥40.00</div>
          <form method="get" action="/FlowerShop_war_exploded/AdminDelete">
            <input type="submit" value="加入购物车">
          </form>
        </div>
      </li>
      <li>
        <img src="img/5.jpeg" alt="">
        <div class="guess_font">
          <div>花5</div>
          <div class="price">￥50.00</div>
          <form method="get" action="/FlowerShop_war_exploded/AdminDelete">
            <input type="submit" value="加入购物车">
          </form>
        </div>
      </li>
      <li>
        <img src="img/6.jpg" alt="">
        <div class="guess_font">
          <div>花6</div>
          <div class="price">￥60.00</div>
          <form method="get" action="/FlowerShop_war_exploded/AdminDelete">
            <input type="submit" value="加入购物车">
          </form>
        </div>
      </li>
    </ul>
  </div>

</div>
</body>

</html>
