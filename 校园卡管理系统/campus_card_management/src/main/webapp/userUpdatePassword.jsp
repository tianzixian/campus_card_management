<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.tolfin.web.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: 86159
  Date: 2021/12/2
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息</title>
    <link rel="stylesheet" type="text/css" href="/css/userUpdatePassword.css">
    <%@include file="mian.jsp"%>
    <script src="/js/userUpdatePassword.js"></script>
</head>

<body>
<jsp:include page="header.jsp"/>

<div class="rg_layout">
    <div class="rg_form clearfix">
        <div class="rg_form_center">

            <form id="change_password" method="post">
                <input type="text" name="id" value="${user.id}" hidden="hidden">
                <!--提交处理请求的标识符-->
                <table style="margin-top: 25px; margin-left: 100px;">
                    <tr>
                        <td class="td_left">
                            <label>学工号</label>
                        </td>
                        <td class="td_right pssw">
                            <input type="text" value="${user.id}" disabled="disabled">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label>原密码</label>
                        </td>
                        <td class="td_right pssw">
                            <input type="password" id="originalPassword2">
                            <input type="password" id="originalPassword1" value="${user.password}" hidden="hidden">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label>新密码</label>
                        </td>
                        <td class="td_right pssw">
                            <input type="password" name="newPassword" id="newPassword">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left ">
                            <label>确认密码</label>
                        </td>
                        <td class="td_right pssw">
                            <input type="password" name="confirmPassword" id="confirmPassword">
                        </td>
                    </tr>
                </table>
                <br>
                <div style="vertical-align:middle; padding-left: 200px">
                    <input id="sub" type="button" class="submit" value="修改密码">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>

