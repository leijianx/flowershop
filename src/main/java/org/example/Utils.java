package org.example;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Utils {

    //1:将获取数据库连接对象的方法封装起来
    public static Connection GetConnection() throws Exception {
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取链接
        String URL = "jdbc:mysql://127.0.0.1:3306/shop";
        String Username = "root";
        String Password = "Sakura";
        //返回得到是一个connection对象
        return DriverManager.getConnection(URL,Username, Password);
    }

    public static boolean Register(String name , int password) throws Exception {
        //定义sql语句
        String sql = "INSERT INTO user(name, password) VALUES (?,?)";


        PreparedStatement preparedStatement = GetConnection().prepareStatement(sql);

        //设置参数
        preparedStatement.setString(1,name);
        preparedStatement.setInt(2,password);

        int i = preparedStatement.executeUpdate();

        //释放资源
        preparedStatement.close();
        GetConnection().close();

        if (i >0){
            return true;
        }else {
            return false;
        }
    }

    public  static boolean login(String name , int password) throws Exception {

        String sql = "select * from user where name=? and password=? ";

        PreparedStatement preparedStatement = GetConnection().prepareStatement(sql);
        preparedStatement.setString(1,name);
        preparedStatement.setInt(2,password);

        ResultSet resultSet = preparedStatement.executeQuery();

        User user =null;
        ArrayList<User> users = new ArrayList<>();
        while (resultSet.next()){
            int User_id = resultSet.getInt("id");
            String User_name = resultSet.getString("name");
            int User_password = resultSet.getInt("password");

            user =new User(User_id,User_name,User_password);

            users.add(user);
        }

        if (user !=null){
            return true;
        }else {
            return false;
        }
    }

    public  static boolean adminlogin(String name , int password) throws Exception {

        String sql = "select * from admin where name=? and password=? ";

        PreparedStatement preparedStatement = GetConnection().prepareStatement(sql);
        preparedStatement.setString(1,name);
        preparedStatement.setInt(2,password);

        ResultSet resultSet = preparedStatement.executeQuery();

        Admin admin =null;
        ArrayList<Admin> admins = new ArrayList<>();
        while (resultSet.next()){
            int Admin_id = resultSet.getInt("id");
            String Admin_name = resultSet.getString("name");
            int Admin_password = resultSet.getInt("password");

            admin =new Admin(Admin_id, Admin_name ,Admin_password);

            admins.add(admin);
        }

        if (admin !=null){
            return true;
        }else {
            return false;
        }
    }

    //查询所有用户
    public  static boolean SelectAll(String name , int password) throws Exception {

        String sql = "select * from user ";

        PreparedStatement preparedStatement = GetConnection().prepareStatement(sql);


        ResultSet resultSet = preparedStatement.executeQuery();

        User user =null;
        ArrayList<User> users = new ArrayList<>();
        while (resultSet.next()){
            int User_id = resultSet.getInt("id");
            String User_name = resultSet.getString("name");
            int User_password = resultSet.getInt("password");

            user =new User(User_id,User_name,User_password);

            users.add(user);
        }

        return false;

    }


    //DeleteUser:删除指定id的用户
    public  static int DeleteUser(int id) throws Exception {

        String sql = " delete from user where id = ?";

        PreparedStatement preparedStatement = GetConnection().prepareStatement(sql);
        preparedStatement.setInt(1,id);

        int i = preparedStatement.executeUpdate();

        if (i>0){
            return 1;
        }else
            return 0;
    }
    //添加商品到购物车
    public  static int InsertShopCar(String name,int price) throws Exception {

        String sql = " insert into 孙智洁_shopcar(name, price) values ( ? , ? );";

        PreparedStatement preparedStatement = GetConnection().prepareStatement(sql);
        preparedStatement.setString(1,name);
        preparedStatement.setInt(2,price);


        int i = preparedStatement.executeUpdate();

        if (i>0){
            return 1;
        }else
            return 0;
    }
}
