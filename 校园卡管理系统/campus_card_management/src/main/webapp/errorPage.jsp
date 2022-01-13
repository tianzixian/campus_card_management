<%--
  Created by IntelliJ IDEA.
  User: 86159
  Date: 2021/12/3
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ERROR</title>
    <style>
        .container{
            margin-left: 40px;
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>ERROR</h1>
    <h3>
        发生错误：${error_msg}
    </h3>
    <span>
    <a href="/login">点我去登录</a>
</span>
</div>
</body>
</html>
