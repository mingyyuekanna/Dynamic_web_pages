package com.test.servlet;

import com.test.dao.UserMessageDao;
import com.test.entity.UserMessage;
import com.test.until.DBUtil;

import javax.servlet.RequestDispatcher;
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
//              先扔掉 为重定向服务
//              writer.println("注册成功！<a href='login.html'>去登录</a>");
//                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/success.html");

//            req.getRequestDispatcher("success.html").forward(req,resp);

                resp.sendRedirect("success.html");
                //判断目标资源是否存在..
//                if(requestDispatcher!=null){
//                    //请求转发 把此中的req/resp内容发到requestDispatcher代表的网页
//                    requestDispatcher.forward(req,resp);
//                }
                connection.commit();
            } else {
                writer.println("注册失败! ");
                //通过浏览器响应  重定向..
                resp.sendRedirect("error.html");
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
/*
    请求转发与重定向:
    都可以从一个资源跳转到另一个资源 ->转发和重定向 (地址栏变不变 / 带参与不带参...)

    请求转发 :
    只产生一个请求一个响应
    浏览器地址栏内容不变
    可以通过httpServletRequest传递数据
    不能传非本地外网 只能本站点

    重定向:
    两个请求 两个响应 浏览器
    浏览器地址栏内容发生改变
    重定向时无法通过httpServletRequest传递数据
    可以跳转到其他站点

    盲点:
    注册成功我们建议用重定向进行跳转 请求转发注册时f5会多次进行他妈的插入 数据库就fuck了
    web资源之间只有三种关系 ->请求转发 重定向 和 包含
 */
