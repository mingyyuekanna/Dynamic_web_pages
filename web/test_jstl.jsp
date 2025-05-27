<%@ page import="java.util.Scanner" %>
<%@ page import="java.util.Random" %>
<%@ page import="java.util.concurrent.ThreadLocalRandom" %><%--
  Created by IntelliJ IDEA.
  User: qings
  Date: 2025/5/23
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>JSTL核心标签库常用的标签...</h1>

<%--  c:set 等同于这段代码   <%request.setAttribute("username","lian");%>--%>
<%--
    scope属性->只能为page,request,session,application
    属性可省略 默认则是 page
--%>
    <c:set value="<span style='color:#FF0000'>lian</span>" var="username" scope="request"> </c:set>
    ${username}
<ol>
    <li>el表达式: ${username}</li>
<%--    escapeXml="true" -> 是否不渲染值中的代码  默认是true  --%>
    <li>JSTL <c:out value="${username}" default="默认值" > </c:out> </li>

<%--    <%request.removeAttribute("username");%>  等价
        c:remove 从范围对象中移除指定名字的数据
 --%>
<%--    <c:remove var="username" scope="request"> </c:remove>--%>

    <c:set value="20" var="userAge"> </c:set>

<%--    c:if可读性强 但效率低 --%>
<%--    大于等价于gt 大于等于等价于ge--%>
    <c:if test="${userAge>=20}">
        年龄大于20
    </c:if>


    <%--    小于等价于lt 小于等于等价于le--%>
    <c:if test="${userAge<=20}">
        年龄小于20
    </c:if>


    <c:if test="${userAge==20}">
        年龄是20
    </c:if>


    <hr>
<%--    c:choose 分支较多 推荐使用c:choose 效率高 --%>
    <c:choose>
        <c:when test="${userAge ge 20}">
            age >20
        </c:when>

        <c:when test="${userAge le 20}">
            age <20
        </c:when>

        <c:otherwise>
            other...
        </c:otherwise>
    </c:choose>

<%--    JSTL 循环标签--%>
<%--    c:forEach 遍历集合--%>
    <%--        创建长度为10 的数组 想数组中添加1-100的随机数--%>
    <%
        Random random= new Random();
        int[] array=new int[10];
        for (int i = 0; i < array.length; i++) {
            array[i]=random.nextInt(100)+1;
        }
        request.setAttribute("numbers",array);
    %>
    <br>
<%--    将数组以numbers名字存入request中--%>
<%--    items属性: 将要遍历的集合
        var属性:将集合中的值 以指定的名字存到最小范围对象中
        varStatus属性:以将每次循环的信息 以指定名字 存到最小的对象范围中
            index属性: 保存每次循环的下标 从0开始
            count属性: 保存每次循环的次数 从1开始
            begin 从指定下标开始 ->end 指定下标结束 =>[begin,end]类型
            step属性:步进 一次递进多少

--%>
    <c:forEach var="num" step="2" varStatus="loop" items="${requestScope.numbers}" begin="0" end="5">
        ${pageScope.num}
        ${pageScope.loop}-->${pageScope.loop.index} -->${pageScope.loop.count}
        <br>
    </c:forEach>


</ol>
</body>
</html>
