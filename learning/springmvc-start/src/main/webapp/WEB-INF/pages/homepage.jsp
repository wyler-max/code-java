<%--
  Created by IntelliJ IDEA.
  User: wangyulin
  Date: 2020/7/30
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>9、主页面</title>
</head>
<body>
    当前用户：${STUDENT_SESSION.username} <br/>
    <a href="${pageContext.request.contextPath}/interceptor/logout">退出</a>
</body>
</html>
