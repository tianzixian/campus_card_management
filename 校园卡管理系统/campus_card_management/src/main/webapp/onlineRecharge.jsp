<%--
  Created by IntelliJ IDEA.
  User: 86159
  Date: 2021/12/3
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>网上充值</title>
    <link rel="stylesheet" type="text/css" href="/css/onlineRecharge.css">
    <%@include file="mian.jsp"%>
    <script src="/js/onlineRecharge.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/>
<form id="fm">
    <input hidden="hidden" value="${sid}" name="sid">
    <!-- 展示银行卡信息 -->
    <table class="table">
        <tr>
            <th>选择充值</th>
            <th>银行</th>
            <th>卡号</th>
            <th>余额</th>
        </tr>
        <c:forEach items="${bankCardList}" var="card" varStatus="vs">
            <tr>
                <td><input name="cardId" type="radio" value="${card.cid}"></td>
                <td>${card.description}</td>
                <td>${card.cid}</td>
                <td name="balance">${card.balance}</td>
            </tr>
        </c:forEach>

    </table>
    <button type="button" class="btn btn-default" onclick="recharge();">选择银行卡</button>

    <br> <br> <br>
    <div id="hid_div" hidden="hidden">
        <span>请输入银行卡密码：</span>
        <input type="password" class="psd" name="password">
        <br> <br>
        <span>请输入充值金额：</span>
        <input type="text" class="psd" id="aom" name="aom">
        <br><br>
        <button type="button" id="sub" class="btn btn-lg btn-primary">充值</button>
    </div>
</form>

<%
    if(null!=request.getAttribute("nofind")){
%>
<script>
    document.getElementById("fm").setAttribute("hidden","hidden");
</script>
<h2 style="color: red"><a>${notfind}</a></h2>
<%
    }
%>
</body>
</html>
