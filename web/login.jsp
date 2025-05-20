<%--
  Created by IntelliJ IDEA.
  User: qings
  Date: 2025/4/16
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user Login page</title>
</head>
<body>
    <form method="post" action="login_jsp">
        <table>
            <tr>
                <td>用户编号</td>
                <td>
                 <input type="text" name="userId" placeholder="请输入用户编号" value="<%=request.getAttribute("userId")%>" autofocus> <br>
                </td>
            </tr>

           <tr>
               <td>
                   用户密码
               </td>

               <td>
                    <input type="password" name="userPassword" placeholder="输入密码"> <br>
               </td>
           </tr>

            <tr>
                <td colspan="2">
                     <button type="submit"> 登录 </button>
                    <button type="reset"> 重置 </button>
                </td>
            </tr>
        </table>
    </form>
    <%-- 从范围对象中获取错误信息 并将错误信息显示到页面 --%>
    <%--    <%=application.getAttribute("error")==null?"":application.getAttribute("error")%>--%>
    <%=session.getAttribute("error")==null?"":session.getAttribute("error")%>
    <%-- 从范围对象中移除指定名字的数据 减轻服务器鸭力  --%>
    <%session.removeAttribute("error");%>

    <%--  对密码的补全  --%>
    <%=request.getAttribute("error")==null?"":request.getAttribute("error")%>
<%--    <%=request.getAttribute("error")%>--%>
</body>
</html>
