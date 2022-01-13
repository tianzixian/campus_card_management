<%--
  Created by IntelliJ IDEA.
  User: 86159
  Date: 2021/12/1
  Time: 20:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="/css/login.css">
    <%@include file="mian.jsp"%>
    <script src="/js/login.js"></script>
</head>

<body>
<div class="container" style="width: 400px;">
    <form class="form-signin" id="loginForm">
        <h2 class="form-signin-heading">用户登录</h2>
        <div class="user_identity">
            <span>登录身份</span>
            <select name="identity">
                <option value="ord">普通用户</option>
                <option value="adm">管理员</option>
            </select>
        </div>
        <input name="id" class="form-control" placeholder="请输入学工号" required autofocus>
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="请输入密码" required>
        <div class="verify">
            <input name="check" type="text" placeholder="请输入验证码" autocomplete="off">
            <span><img src="/user/checkCode" alt="" onclick="changeCheckCode(this)"></span>
            <script type="text/javascript">
                //图片点击事件
                function changeCheckCode(img) {
                    img.src="/user/checkCode?"+new Date().getTime();
                }
            </script>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> 自动登录
            </label>
            &nbsp;&nbsp;<span id="err_info" style="color: red; font-weight: bold"></span>
        </div>
        <div class="reg">没有账户？<a href="javascript:location.href='register';">立即注册</a></div>
        <button id="btn_sub" class="btn btn-lg btn-primary btn-block" type="button">登录</button>
    </form>
</div>
</body>
</html>
