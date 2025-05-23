<%@ page import="com.test.entity.UserMessage" %>

<%--
  Created by IntelliJ IDEA.
  User: qings
  Date: 2025/5/16
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    使用过滤器设置字符集时 建议表单的method的熟悉为post--%>
<form action="testFilter" method="post">
    <input type="text" name="msg">
    <button type="submit">Submit</button>
</form>
<hr>
<h2>java code && el expression</h2>
<%
    String username="Tom";
    session.setAttribute("name",username);
    String username1="东雪莲";
    request.setAttribute("name",username1);
    //当同名时未指定 el表达式从小到大顺序查找范围对象的值
%>
<ul>
    <%--java get value --%>
    <li>java 代码 <%=session.getAttribute("name")%> </li>


    <%--el get value --%>
    <li>el 表达式 ${name}</li>
    <li>查找特定 ${sessionScope.name} </li>
</ul>


    <%--el表达式与JavaBean--%>
<%
    UserMessage userMessage=new UserMessage();
    userMessage.setUserName("fuck");

//    随便赋个值
    userMessage.setUserId(1001);
    userMessage.setUserName("东雪莲");
//    将JavaBean对象以指定的的名字存放范围对象中
    pageContext.setAttribute("userMsg",userMessage);
%>

<h4>JavaCode</h4>
<p>getAttribute返回的是Object类型 需要拿userMessage来转型 </p>

<ul>
    <li>
        编号: <%= ((UserMessage)pageContext.getAttribute("userMsg")).getUserId()%>
    </li>
    <li>姓名: <%= ((UserMessage)pageContext.getAttribute("userMsg")).getUserName()%> </li>
</ul>

<h4>EL表达式</h4>
<ul>
    <li>姓名: ${userMsg.userName}</li>
    <li>编号:${userMsg.userId}</li>
</ul>

<hr>

<form action="main.jsp" method="post">
    <input type="text" name="msg">
    <button type="submit">submit</button>
</form>

</body>
</html>
