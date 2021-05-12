<%--
  Created by IntelliJ IDEA.
  User: wangyulin
  Date: 2020/7/28
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>2、请求参数绑定</title>
</head>
<body>

    <%--请求参数绑定--%>
    <a href="param/testParam?username=hehe&password=123">请求参数绑定</a>

    <%--把数据封装Account类中--%>
    <h3>把数据封装Account类中</h3>
    <form action="param/saveAccount" method="post">
        姓名：<input type="text" name="username"/><br/>
        密码：<input type="text" name="password"/><br/>
        金额：<input type="text" name="money"/><br/>

        用户姓名：<input type="text" name="userReq.uname" /><br/>
        用户年龄：<input type="text" name="userReq.age" /><br/>

        用户姓名：<input type="text" name="list[0].uname" /><br/>
        用户年龄：<input type="text" name="list[0].age" /><br/>
        用户姓名：<input type="text" name="list[1].uname" /><br/>
        用户年龄：<input type="text" name="list[1].age" /><br/>

        用户姓名：<input type="text" name="map['one'].uname" /><br/>
        用户年龄：<input type="text" name="map['one'].age" /><br/>
        <input type="submit" value="提交"/>
    </form>

    <%--自定义类型转换器--%>
    <h3>自定义类型转换器</h3>
    <form action="param/saveUser" method="post">
        用户姓名：<input type="text" name="uname" /><br/>
        用户年龄：<input type="text" name="age" /><br/>
        用户生日：<input type="text" name="date" /><br/>
        <input type="submit" value="提交" />
    </form>

    <a href="param/testServlet">Servlet原生的API</a>

</body>
</html>
