<%--
  Created by IntelliJ IDEA.
  User: wangyulin
  Date: 2020/7/29
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>3、restful风格</title>
</head>
<body>
    <!-- 保存 -->
    <form action="rest/testRestPOST" method="post">
        用户名称：<input type="text" name="uname"><br/>
        <!-- <input type="hidden" name="_method" value="POST"> -->
        <input type="submit" value="保存">
    </form><br/>

    <!-- 更新 -->
    <form action="rest/testRestPUT/1" method="post">
        用户名称：<input type="text" name="uname"><br/>
        <input type="hidden" name="_method" value="PUT">
        <input type="submit" value="更新">
    </form><br/>

    <!-- 删除 -->
    <form action="rest/testRestDELETE/1" method="post">
        <input type="hidden" name="_method" value="DELETE">
        <input type="submit" value="删除">
    </form> <br/>

    <!-- 查询一个 -->
    <form action="rest/testRestGET/1" method="post">
        <input type="hidden" name="_method" value="GET">
        <input type="submit" value="查询">
    </form>
</body>
</html>
