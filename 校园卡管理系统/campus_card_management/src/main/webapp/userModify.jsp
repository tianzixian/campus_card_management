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
    <link rel="stylesheet" type="text/css" href="/css/userInfo.css">
    <!-- Bootstrap -->
    <%@include file="mian.jsp"%>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="rg_layout">
    <div class="rg_form clearfix" style="height: 410px">
        <div class="rg_form_center">
            <!--注册表单-->
            <form id="registerForm" action="/user/modify" method="post">
                <!--提交处理请求的标识符-->
                <%--                <input type="hidden" name="name" value="${user.name}">--%>
                <input type="hidden" name="id" value="${user.id}">
                <input type="hidden" name="age" value="${user.age}">
                <table style="margin-top: 25px;">
                    <tr>
                        <td class="td_left">
                            <label for="name">姓名</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="name" name="name" value="${user.name}" >
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="email">Email</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="email" name="email" value="${user.email}">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="telephone">手机号</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="telephone" name="telephone" value="${user.telephone}">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="major">专业</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="major" name="major" value="${user.major}">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="sex">性别</label>
                        </td>
                        <td class="td_right gender">
                            <c:if test="${'男'.equals(user.sex)}">
                                <input type="radio" id="sex" name="sex" value="男" checked> 男
                                <input type="radio" name="sex" value="女"> 女
                            </c:if>
                            <c:if test="${'女'.equals(user.sex)}">
                                <input type="radio" id="sex" name="sex" value="男"> 男
                                <input type="radio" name="sex" value="女" checked> 女
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="birthday">出生日期</label>
                        </td>
                        <td class="td_right">
                            <%
                                User user = (User)request.getAttribute("user");
                                String bir = new SimpleDateFormat("yyyy-MM-dd").format(user.getBirthday());
                            %>
                            <input type="date" id="birthday" value="<%=bir%>">
                            <input id="sent_birthday" name="birthday" hidden="hidden">
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                        </td>
                        <td class="td_right check">
                            <input onclick="javascript:send();" type="submit" class="submit" value="保存并提交">
                            <span class="query_account"><a href="/account/info/${user.id}">账户查询</a></span>
                            <script>
                                function send() {
                                    var b = document.getElementById("birthday").value;
                                    var date = new Date(Date.parse(b.replace(/-/g, "/")));
                                    document.getElementById("sent_birthday").value = date.toDateString();
                                }
                            </script>
                            <span id="msg" style="color: red;"></span>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</body>
</body>
</html>

