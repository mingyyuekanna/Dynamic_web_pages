package com.forwawrd;

import com.test.until.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/logger")
public class logger extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String pwd = req.getParameter("pwd");

        try {
            PreparedStatement preparedStatement=null;
            Connection connection = DBUtil.getConnection();
            String sql =" select * from user_message where user_name=? and user_password=? ";

            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,pwd);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                req.setAttribute("name",name);
                req.getRequestDispatcher("/fucker").forward(req,resp);
            }else {
                resp.sendRedirect("failed.jsp");
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doPost(req,resp);
    }
}
