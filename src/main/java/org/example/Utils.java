package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Utils {
    public static boolean Register(String name , int password) throws Exception {
        //1:注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2:获取链接
        String URL = "jdbc:mysql://localhost:3306/cookie";
        String Username = "root";
        String Password = "159357sc";
        //返回得到是一个connection对象
        Connection conn = DriverManager.getConnection(URL,Username, Password);

        //3:定义sql语句
        String sql = "INSERT INTO user(name, password) VALUES (?,?)";


        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        //4:设置参数
        preparedStatement.setString(1,name);
        preparedStatement.setInt(2,password);

        int i = preparedStatement.executeUpdate();

        //释放资源
        preparedStatement.close();
        conn.close();

        if (i >0){
            return true;
        }else {
            return false;
        }
    }

    public  static boolean login(String name , int password) throws Exception {
        //1:注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2:获取链接
        String URL = "jdbc:mysql://localhost:3306/cookie";
        String Username = "root";
        String Password = "159357sc";
        //返回得到是一个connection对象
        Connection conn = DriverManager.getConnection(URL,Username, Password);

        String sql = "select * from user where name=? and password=? ";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
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
            System.out.println("list不为空");
            return true;
        }else {
            System.out.println("list为空");
            return false;
        }
    }

    public  static boolean adminlogin(String name , int password) throws Exception {
        //1:注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2:获取链接
        String URL = "jdbc:mysql://localhost:3306/cookie";
        String Username = "root";
        String Password = "159357sc";
        //返回得到是一个connection对象
        Connection conn = DriverManager.getConnection(URL,Username, Password);

        String sql = "select * from admin where name=? and password=? ";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
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
            System.out.println("list不为空");
            return true;
        }else {
            System.out.println("list为空");
            return false;
        }
    }

    //查询所有用户
    public  static boolean SelectAll(String name , int password) throws Exception {
        //1:注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2:获取链接
        String URL = "jdbc:mysql://localhost:3306/cookie";
        String Username = "root";
        String Password = "159357sc";
        //返回得到是一个connection对象
        Connection conn = DriverManager.getConnection(URL,Username, Password);

        String sql = "select * from user ";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);


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

    //删除指定id的用户
    public  static int DeleteUser(int id) throws Exception {
        //1:注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2:获取链接
        String URL = "jdbc:mysql://localhost:3306/cookie";
        String Username = "root";
        String Password = "159357sc";
        //返回得到是一个connection对象
        Connection conn = DriverManager.getConnection(URL,Username, Password);

        String sql = " delete from user where id = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,id);

        int i = preparedStatement.executeUpdate();

        if (i>0){
            System.out.println("删除成功");
            return 1;
        }else
            return 0;
    }


}
