<%--
  Created by IntelliJ IDEA.
  User: wangyulin
  Date: 2020/7/30
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>

    <a href="account/findAll">查询所有</a><br/>

    <form action="account/saveAccount" method="post">
        用户名：<input type="text" name="name"/><br/>
        存款数：<input type="text" name="money"/><br/>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>
