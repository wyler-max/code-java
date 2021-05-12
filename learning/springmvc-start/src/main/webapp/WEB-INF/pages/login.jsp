<%--
  Created by IntelliJ IDEA.
  User: wangyulin
  Date: 2020/7/28
  Time: 22:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>9、登录页面</title>
</head>
<body>
    ${msg}
    <h3>登录页面</h3>
    <form action="${pageContext.request.contextPath}/interceptor/loginSubmit" method="post">
        用户姓名：<input type="text" name="username"/><br/>
        用户密码：<input type="text" name="password"/><br/>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
