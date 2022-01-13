<%--
  Created by IntelliJ IDEA.
  User: 86159
  Date: 2021/12/2
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户选择跳转</title>
    <link rel="stylesheet" type="text/css" href="/css/userOpt.css">
    <!-- Bootstrap -->
    <%@include file="mian.jsp"%>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="out">
    <p><strong>操作选择</strong></p>
    <div class="alert alert-warning" role="alert">&nbsp;&nbsp;&nbsp;
        <strong><a href="/user/info/${sid}">查询个人信息</a></strong>
    </div>
    <div class="alert alert-info" role="alert">&nbsp;&nbsp;&nbsp;
        <strong><a href="/account/info/${sid}">账户信息</a></strong>
    </div>
</div>
</body>
</html>
