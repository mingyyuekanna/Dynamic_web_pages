package com.test.servlet;

import com.test.dao.UserMessageDao;
import com.test.entity.UserMessage;
import com.test.until.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        设置字符集
        获取用户在浏览器输入的注册信息
        注册新用户

         */
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password1");
        String phone = req.getParameter("userphone");
        String email = req.getParameter("userEmail");
        String sex = req.getParameter("userSex");
        PrintWriter writer = resp.getWriter();

        UserMessage userMessage = new UserMessage(username, password, phone, email, sex);
   /*
        fuck construction
        userMessage.setUserName(username);
        userMessage.setUserPassword(password);
        userMessage.setUserPhone(phone);
        userMessage.setUserEmail(email);
        userMessage.setUserSex(sex);
    */

        UserMessageDao userMessageDao = new UserMessageDao();
        try {
            int adduser = userMessageDao.adduser(userMessage);
            Connection connection = DBUtil.getConnection();
            if (adduser > 0) {
                writer.println("注册成功！<a href='login.html'>去登录</a>");
                connection.commit();
            } else {
                writer.println("注册失败! ");
            }
        } catch (ClassNotFoundException |SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
