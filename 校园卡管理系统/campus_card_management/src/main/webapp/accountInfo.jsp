<%@ page import="com.tolfin.web.pojo.Account" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: 86159
  Date: 2021/12/3
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>账户信息</title>
    <link rel="stylesheet" type="text/css" href="/css/accountInfo.css">
    <%@include file="mian.jsp"%>
</head>
<body>
<jsp:include page="header.jsp"/>
<c:if test="${null==account}">
    <div class="to_activate">
        <span>账户还未激活！</span>
        &nbsp;&nbsp;
        <a href="#">点我去激活</a>
    </div>
    <script>
        //账户未激活，不显示下面的表单框
        $(function () {
            $(".panel").attr("hidden","hidden");
            $(".online_recharge").attr("hidden","hidden");
        })
    </script>
</c:if>
<div class="panel panel-default" style="border-bottom: 0px;margin-top:30px">
    <div class="panel-heading">校园卡帐户信息</div>
    <table class="table">
        <tr>
            <th>卡号</th>
            <th>账户余额</th>
            <th>今日消费</th>
            <th>末次充值金额</th>
            <th>末次充值时间</th>
        </tr>
        <tr>
            <c:if test="${null!=account}">
                <td>${account.id}</td>
                <td>${account.accountBalance}</td>
                <td>${account.consumptionToday}</td>
                <td>${account.lastRechargeAmount}</td>
                <%
                    Account account = (Account)request.getAttribute("account");
                    Date lastRechargeTime = account.getLastRechargeTime();
                    String lrt;
                    if(null == lastRechargeTime){
                        lrt = "账户还未充值";
                    }else{
                        lrt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(lastRechargeTime);
                    }
                %>
                <td><%=lrt%></td>
            </c:if>
        </tr>
    </table>
</div>
<div class="online_recharge"><a href="/sbc/info/${account.id}">网上充值</a></div>

<%--<c:if test="${null==account}">--%>
<%--    <script>--%>
<%--        $(".panel").attr("hidden","hidden");--%>
<%--        $(".online_recharge").attr("hidden","hidden");--%>
<%--    </script>--%>
<%--</c:if>--%>
</body>
</html>
