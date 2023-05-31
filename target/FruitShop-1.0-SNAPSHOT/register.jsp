<%--
  Created by IntelliJ IDEA.
  User: 18084
  Date: 2023/5/30
  Time: 16:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">C
  <title>欢迎注册</title>
  <link href="CSS/register.css" rel="stylesheet">
</head>
<body>

<div class="form-div">
  <div class="reg-content">
    <h1>欢迎注册</h1>
    <span>已有帐号？</span> <a href="login.jsp">登录</a>
  </div>
  <form id="reg-form" action="/FruitShop_war_exploded/register" method="get">

    <table>

      <tr>
        <td>用户名</td>
        <td class="inputs">
          <input name="username" type="text" id="username">
        </td>

      </tr>

      <tr>
        <td>密码</td>
        <td class="inputs">
          <input name="password" type="password" id="password">
        </td>
      </tr>

    </table>

    <div class="buttons">
      <input value="注 册" type="submit" id="reg_btn">
    </div>
    <br class="clear">
  </form>

</div>
</body>
</html>
