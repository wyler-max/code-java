<%--
  Created by IntelliJ IDEA.
  User: wangyulin
  Date: 2020/7/29
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>4、常用注解</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

    <h3>常用注解</h3>

    <a href="anno/testRequestMapping?username=杰克">RequestMapping</a><br/>

    <a href="anno/testRequestParam?name=杰克">RequestParam</a><br/>

    <form action="anno/testRequestBody" method="post">
        用户姓名：<input type="text" name="username" /><br/>
        用户年龄：<input type="text" name="age" /><br/>
        <input type="submit" value="提交" />
    </form>
    <br/>

    <a href="anno/testPathVariable/10">PathVariable</a><br>

    <a href="anno/testRequestHeader">RequestHeader</a><br>

    <a href="anno/testCookieValue">CookieValue</a><br>

    <h3>ModelAttribute</h3>
    <form action="anno/testModelAttribute2" method="post">
        用户姓名：<input type="text" name="uname" /><br/>
        用户年龄：<input type="text" name="age" /><br/>
        <input type="submit" value="提交" />
    </form><br>

    <h3>SessionAttributes</h3>
    <a href="anno/testSessionAttributes">存入SessionAttributes</a><br>
    <a href="anno/getSessionAttributes">取出SessionAttributes</a><br>
    <a href="anno/delSessionAttributes">清除SessionAttributes</a><br>

</body>
</html>
