<%--
  Created by IntelliJ IDEA.
  User: wangyulin
  Date: 2020/7/29
  Time: 23:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>8、拦截器</title>
</head>
<body>
    <h3>拦截器</h3>
    <a href="interceptor/testInterceptor" >拦截器</a>

    <h3>登录页面</h3>
    <form action="interceptor/loginSubmit" method="post">
        用户姓名：<input type="text" name="username"/><br/>
        用户密码：<input type="text" name="pwd"/><br/>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
