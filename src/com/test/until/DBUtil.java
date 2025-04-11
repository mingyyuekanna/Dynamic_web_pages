package com.test.until;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * Mysql 数据库工具类
 *
 * @author qings
 */

public class DBUtil {

    /**
     * 加载数据库驱动 并返回数据库连接对象
     *
     * @return 返回java.sql.Connection类型的实例
     * @throws ClassNotFoundException 数据库加载失败
     * @throws SQLException           数据库连接失败
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        //装载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //获得连接对象
        Connection connection= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test_db1516?characterEncoding=utf8","root","123456");

        //关闭自动提交
        connection.setAutoCommit(false);

        //加载数据库驱动并返回数据库连接对象 connection ..
        return connection;



    }
    /**
     * 关闭数据库相关的对象
     * @param resultSet         结果集
     * @param statement         语句对象/准备对象
     * @param connection        数据库连接地哦写
     * @throws SQLException     数据库对象关闭失败
     * @author qings
     */
    public static void close(ResultSet resultSet, Statement statement,Connection connection) throws SQLException {

        if(resultSet!=null){
            connection.close();
        }
        if(statement!=null){
            statement.close();
        }
        if (connection!=null){
            connection.close();
        }

    }
/*
unused...
    public static void close( Statement statement,Connection connection) throws SQLException {

        if(statement!=null){
            statement.close();
        }
        if (connection!=null){
            connection.close();
        }

    }

 */

    public static void close( PreparedStatement preparedStatement,Connection connection) throws SQLException {

        if(preparedStatement!=null){
            preparedStatement.close();
        }
        if (connection!=null){
            connection.close();
        }

    }
}
