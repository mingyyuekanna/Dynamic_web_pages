<%@ page import="java.io.Writer" %>
<%@ page import="com.test.entity.UserMessage" %><%--
  Created by IntelliJ IDEA.
  User: qings
  Date: 2025/5/16
  Time: 8:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div style="color:darkblue">
<%--        从范围对象中获得登录用户信息 \|/这个强制类型转换 因为getAttrubit是object的--%>
        <%UserMessage login  =(UserMessage) session.getAttribute("user");%>
<%--        将用户名打印出来 显示到页面--%>
<%--        <%=login.getUserName()%>--%>
        ${user.userName}
    ,欢迎回来
    </div>
</body>
</html>
