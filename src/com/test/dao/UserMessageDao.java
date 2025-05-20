package com.test.dao;

import com.test.entity.UserMessage;
import com.test.until.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 用户模块持久化层 实现对USER_MESSAGE表进行增删改查的方法
 *
 * @author qings
 */
public class UserMessageDao {

    /**
     * @param user 新用户信息(姓名 密码 手机号 邮箱 性别)
     * @return 注册成功返回大于0整数 否则返回0
     * @throws SQLException           数据库驱动加载失败
     * @throws ClassNotFoundException 用户注册失败
     * @author qings
     */
    public int adduser(UserMessage user) throws SQLException, ClassNotFoundException {

//        Scanner scanner = new Scanner(System.in);
        //加载数据库驱动&&获取数据库连接对象
        //创建inset语句
        //获得准备语句对象 设置准备语句将要执行的insert语句
        //替换准备语句的 ?
        //使用准备语句执行insert 获取执行添加的行数
        //成功?提交返回行数:失败回退事务

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBUtil.getConnection();
            String sql = " insert into user_message (user_name,user_password,user_phone,user_email,user_sex) values (?,?,?,?,?) ";

            preparedStatement=connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getUserPassword());
            preparedStatement.setString(3, user.getUserPhone());
            preparedStatement.setString(4, user.getUserEmail());
            preparedStatement.setString(5, user.getUserSex());

            int rows = preparedStatement.executeUpdate();

            if(rows>0){
                connection.commit();
                return rows;
            }
            connection.rollback();
        } finally {
            DBUtil.close(preparedStatement,connection);
        }

        return 0;
    }

    /**
     * 用户登录
     * @param userId 用户编号
     * @param userPassword  用户密码
     * @return  登陆成功 返回com.entity.UserMessage实例(封装了登录用户的完整信息)否则返回null
     * @throws ClassNotFoundException   ClassNotFoundException数据库驱动加载失败
     * @throws SQLException 用户登录失败
     * @author 东雪莲
     */
    public UserMessage login(int userId,String userPassword) throws ClassNotFoundException,SQLException{

        Connection connection = null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;

        try{
            //加载数据库驱动并获取数据库连接对象
            //创建select语句 获得准备语句对象 并设置语句对象将要执行的select语句
            //替换准备语句对象中的? 使用准备语句对象执行select语句 将查询结果放入结果集中
            //如果登陆成功 将登录用户信息封装到userMessage类对象中 并返回

            connection=DBUtil.getConnection();
            String sql = "select * from user_Message where user_Id=? and user_Password=?";


            preparedStatement=connection.prepareStatement(sql);

            preparedStatement.setInt(1,userId);
            preparedStatement.setString(2,userPassword);
            resultSet= preparedStatement.executeQuery();


            if(resultSet.next()){
                UserMessage user=new UserMessage();
                user.setUserId(resultSet.getInt("user_Id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setUserPassword(resultSet.getString("user_password"));
                user.setUserPhone(resultSet.getString("user_phone"));
                user.setUserEmail(resultSet.getString("user_email"));
                user.setUserSex(resultSet.getString("user_sex"));
                return user;
            }
        }finally {
            // 关闭与数据库相关的对象
            DBUtil.close(resultSet,preparedStatement,connection);
        }

        return null;
    }


    public void logout(){

    }
}
