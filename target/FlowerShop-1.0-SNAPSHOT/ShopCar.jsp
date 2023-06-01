<%@ page import="org.example.User" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="static org.example.Utils.GetConnection" %>
<%@ page import="org.example.ShopCar" %>
<%--
  Created by IntelliJ IDEA.
  User: 18084
  Date: 2023/6/1
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="CSS/styles.css" rel="stylesheet">
</head>
<body>
<h2>购物车表</h2>
<%
    //定义sql语句
    String sql = "select * from 孙智洁_shopcar";

    PreparedStatement preparedStatement = GetConnection().prepareStatement(sql);

    ResultSet resultSet = preparedStatement.executeQuery();


    ShopCar shopCar = null;
    ArrayList<ShopCar> ShopCars = new ArrayList<>();
    while (resultSet.next()){
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int price = resultSet.getInt("price");

        shopCar = new ShopCar(id,name,price);

        ShopCars.add(shopCar);
    }

    System.out.println(ShopCars);
    String StrPassWord = "*********";
    //释放资源
    resultSet.close();
    preparedStatement.close();
    GetConnection().close();
%>
<table id="shangping">
    <tr>
        <th>购物车ID</th>
        <th>商品名</th>
        <th>价格</th>
    </tr>
    <% for (ShopCar s : ShopCars) { %>
    <tr>
        <td><%= s.getId() %></td>
        <td><%= s.getName() %></td>
        <td><%= s.getPrice()%></td>
    </tr>
    <% } %>
</table>
</body>
</html>
