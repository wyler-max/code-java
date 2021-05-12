<%--
  Created by IntelliJ IDEA.
  User: wangyulin
  Date: 2020/7/29
  Time: 17:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>5、响应数据和结果视图</title>

    <script src="js/jquery.min.js"></script>

    <script>
        // 页面加载，绑定单击事件
        $(function(){
            $("#btn").click(function(){
                // alert("hello btn");
                // 发送ajax请求
                $.ajax({
                    // 编写json格式，设置属性和值
                    url:"response/testAjax",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"uname":"nobody","age":"123"}',
                    dataType:"json",
                    type:"post",
                    success:function(data){
                        // data服务器端响应的json的数据，进行解析
                        alert(data);
                        alert(data.uname);
                        alert(data.age);
                        alert(data.date);
                    }
                });

            });
        });

        // 发送ajax的请求, 响应json
        $(function(){
            $("#btn-json").click(function(){
                // 发送ajax请求
                $.ajax({
                    // 编写json格式，设置属性和值
                    url:"response/testResponseJson",
                    contentType:"application/json;charset=UTF-8",
                    data:'{"username":"迪丽热巴","password":"111111","money":200}',
                    dataType:"json",
                    type:"post",
                    success:function(data){
                        // data服务器端响应的json的数据，进行解析
                        alert(data);
                        alert(data.username);
                        alert(data.password);
                        alert(data.money);
                    }
                });
            });
        });
    </script>
</head>
<body>
    <a href="response/testReturnString">testReturnString</a><br/>
    <a href="response/testReturnVoid">testReturnVoid</a><br/>

    <a href="response/testModelAndView">testModelAndView</a><br/>

    <button id="btn">发送ajax的请求</button><br/>

    <button id="btn-json">发送ajax的请求, 响应json</button><br/>
</body>
</html>
