<%--
  Created by IntelliJ IDEA.
  User: wangyulin
  Date: 2020/7/30
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>list</title>
</head>
<body>
    <h3>账户List</h3>

    <ul>
        <c:forEach items="${list}" var="account">
            <li>姓名：${account.name};&nbsp;&nbsp; 现金：${account.money}</li>
        </c:forEach>
    </ul>
</body>
</html>
