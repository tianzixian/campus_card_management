<%--
  Created by IntelliJ IDEA.
  User: 86159
  Date: 2021/12/1
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户注册</title>
    <link rel="stylesheet" type="text/css" href="/css/register.css">
    <!-- Bootstrap -->
    <%@include file="mian.jsp"%>
    <script src="/js/register.js"></script>
</head>
<body>
<div class="rg_layout">
    <div class="rg_form clearfix">
        <div class="rg_form_left">
            <p>新用户注册</p>
            <p>USER REGISTER</p>
        </div>
        <div class="rg_form_center">
            <!--注册表单-->
            <form id="registerForm" >
                <!--提交处理请求的标识符-->
                <input type="hidden" name="action" value="register">
                <table style="margin-top: 25px;">
                    <tr>
                        <td class="td_left">
                            <label for="id">学工号</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="id" name="id" placeholder="请输入账号">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="password">密码</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="password" name="password" placeholder="请输入密码">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="email">Email</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="email" name="email" placeholder="请输入Email">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="name">姓名</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="name" name="name" placeholder="请输入真实姓名">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="telephone">手机号</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="telephone" name="telephone" placeholder="请输入您的手机号">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="major">专业</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="major" name="major" placeholder="专业信息">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="sex">性别</label>
                        </td>
                        <td class="td_right gender">
                            <input type="radio" id="sex" name="sex" value="男" checked> 男
                            <input type="radio" name="sex" value="女"> 女
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="birthday">出生日期</label>
                        </td>
                        <td class="td_right">
                            <input type="date" id="birthday" >
                            <input id="sent_birthday" name="birthday" hidden="hidden">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                        </td>
                        <td class="td_right check">
<%--                            <input id="send" onclick="javascript:send();" type="button" class="submit" value="注册">--%>
    <input id="send" type="button" class="submit" value="注册">
                            <span id="msg" style="color: red;"></span>
<%--                            <script>--%>
<%--                                function send() {--%>
<%--                                    var b = document.getElementById("birthday").value;--%>
<%--                                    var date = new Date(Date.parse(b.replace(/-/g, "/")));--%>
<%--                                    document.getElementById("sent_birthday").value = date.toDateString();--%>
<%--                                }--%>
<%--                            </script>--%>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="rg_form_right">
            <p>
                已有账号？
                <a href="javascript:location.href='login'">立即登录</a>
            </p>
        </div>
    </div>
</div>

</body>
</html>
