package com.test.dao;

import com.test.entity.UserMessage;
import com.test.until.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

}
