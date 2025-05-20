package com.test.servlet;

import com.mysql.cj.Session;
import com.test.dao.UserMessageDao;
import com.test.entity.UserMessage;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 注意: 此代码的WebServlet与login.jsp连接 但他的名字是login_jsp
 */
//@WebServlet(urlPatterns = "/login_jsp")
@WebServlet(urlPatterns = "/login_jsp")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置字符集


        //判断用户编号是否为空 if 空  跳转login.jsp 页面
        //判断用户密码是否为空 if 空  跳转login.jsp 页面
        //验证用户登录是否成功
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");


        //获取浏览器输入账密
        String userId = req.getParameter("userId");
        String userPassword = req.getParameter("userPassword");


        //判断是否相等
        if (userId == null || "".equals(userId.trim())) {
           /* 使用四个范围对象(领域对象) && 生命周期 服务器所有的web项目共用一个ServletContext对象
            ServletContext(application) 服务器启动就出现 服务器关闭销毁
            HttpSession(Session)        它是解决HTTP无状态的一种方法(url重写也是 cookie也中)
                                        关闭浏览器不会直接销毁 但地址会丢 or Session超时 or调用.invalidate()直接销毁;

            HttpServletRequest
            PageContext
            */


           /* 获得ServletContext对象
            ServletContext application=this.getServletContext();
            将错误信息放进去 ->string + object
            application.setAttribute("error","<script>alert('用户编号不得为空')</script>");
            */

            //获得浏览器Session对象 当浏览器没有用Session对象 返回null 有危险性(不建议)
            HttpSession session1 = req.getSession();
            //获得浏览器Session对象 如果没有Session对象 就创建一个Session对象并返回(很好用)
            HttpSession session2 = req.getSession(true);

            /*

            //获取Session id
            String id = session2.getId();
            System.out.println("Session id = " + id);
            //获取Session创建时间 最后一次的访问时间 (时间戳 1970-1-1 00:00) 到最后访问Session对象的毫秒数
            long creationTime = session2.getCreationTime();
            System.out.println("Session creationTime = " + creationTime);
            //获取Session有效时间 (1800s->30min -> Tomcat)
            int maxInactiveInterval = session2.getMaxInactiveInterval();
            System.out.println("Session maxInactiveInterval = " + maxInactiveInterval);

*/

            session2.setAttribute("error", "<script>alert('用户编号不得为空')</script>");

//            设置Session存活时间: 秒:
//            设置某一Session的存活时间    ->60s*10=6(min)
//            session2.setMaxInactiveInterval(60*10);

            System.out.println(session2.getMaxInactiveInterval());

//        无条件销毁kill    session2.invalidate();

            //从当前跳转过去 login.jsp
            resp.sendRedirect("login.jsp");
            return;
        }

//        判读密码是否为空 如果空跳回去 并传递错误信息
        if (userPassword == null || "".equals(userPassword.trim())) {

            req.setAttribute("error", "<script>alert('用户密码不得为空')</script>");

//            将用户的编号以userId名字存入范围对象中
            req.setAttribute("userId", userId);

            //转发 可以通过HttpServletRequest传递数据
            req.getRequestDispatcher("login.jsp").forward(req, resp);
            return;
        }
//        验证用户是否登陆成功
        UserMessageDao dao = new UserMessageDao();
        try {
            UserMessage login = dao.login(Integer.parseInt(userId), userPassword);

            if (login != null) {
//                System.out.println("登录成功");
                //登录成功 从当前Servlet跳转到main.jsp 并将用户信息传递到main.jsp页面
//                将登录用户信息以user名字存入范围对象中  这次用的是请求转发

//                req.setAttribute("user",login);
//                req.getRequestDispatcher("main.jsp").forward(req,resp);
                HttpSession session=  req.getSession(true);
                session.setAttribute("user",login);
                resp.sendRedirect("main.jsp");
                return;
            }


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        System.out.println("登录失败!");
//        登录失败 或发生异常 从当前Servlet跳回login.jsp 并将错误信息传递到               login.jsp
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
