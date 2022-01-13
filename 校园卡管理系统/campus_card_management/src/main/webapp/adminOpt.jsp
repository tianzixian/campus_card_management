<%--
  Created by IntelliJ IDEA.
  User: 86159
  Date: 2021/12/8
  Time: 11:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="mian.jsp"%>
<%--    <%@include file="device_page.jsp"%>--%>
    <title>管理员查询</title>
    <link rel="stylesheet" type="text/css" href="/css/adminOpt.css">
    <script src="/js/adminOpt.js"></script>

</head>
<body>
<jsp:include page="header.jsp"/>
<div class="wrapper">
    <p>选择查询条件查询学生</p>
    <form id="fm" action="/user/findByCondition/1" method="post">
        <input id="condition" hidden="hidden" name="condition">
        <div class="col-lg-4">
            <div class="input-group">
                <div class="input-group-btn">
                    <button style="width: 100px;" id="dropdownMenu1" type="button"
                            class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false">
                        选择
                        <span class="caret"></span>
                    </button>
                    <ul id="sel" class="dropdown-menu">
                        <li><a href="#">学工号</a></li>
                        <li><a href="#">姓名</a></li>
                        <li><a href="#">学院</a></li>
                        <li><a href="#">电话</a></li>
                        <li><a href="#">邮箱</a></li>
                    </ul>
                </div>
                <input id="info" type="text" class="form-control" name="info">
            </div>
        </div>
        <input id="sub" class="sub" type="submit" value="查询">
    </form>

</div>


<%--<div id="search_table" class="wrapper search_table" hidden="hidden">--%>
<div id="search_table" class="wrapper search_table">
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>学工号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>专业</th>
                <th>年龄</th>
                <th>电话</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="table_list">
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.sex}</td>
                    <td>${user.major}</td>
                    <td>${user.age}</td>
                    <td>${user.telephone}</td>
                    <td>${user.email}</td>
                    <td>
                        <a href="/user/info/${user.id}">修改</a>&nbsp;|&nbsp;
                        <a href="/account/info/${user.id}">账户信息</a>&nbsp;|&nbsp;
                        <a href="#" id="${user.id}" onclick="changeurl(this);">删除</a>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>


<%--分页条的开始--%>
<div id="page_nav">
    <%--大于首页，才显示--%>
    <c:if test="${pageInfo.pageNum > 1}">
        <button onclick="pageChange(1,'${condition}','${info}');"><a href="#">首页</a></button>
        <button onclick="pageChange(${pageInfo.pageNum-1},'${condition}','${info}');"><a href="#">上一页</a></button>
    </c:if>
    <%--页码输出的开始--%>
    <c:choose>
        <%--情况1：如果总页码小于等于5的情况，页码的范围是：1-总页码--%>
        <c:when test="${pageInfo.pages<= 5 }">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${pageInfo.pages}"/>
        </c:when>
        <%--情况2：总页码大于5的情况--%>
        <c:when test="${pageInfo.pages > 5}">
            <c:choose>
                <%--小情况1：当前页码为前面3个：1，2，3的情况，页码范围是：1-5.--%>
                <c:when test="${pageInfo.pageNum <= 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <%--小情况2：当前页码为最后3个，8，9，10，页码范围是：总页码减4 - 总页码--%>
                <c:when test="${pageInfo.pageNum > pageInfo.pages-3}">
                    <c:set var="begin" value="${pageInfo.pages-4}"/>
                    <c:set var="end" value="${pageInfo.pages}"/>
                </c:when>
                <%--小情况3：4，5，6，7，页码范围是：当前页码减2 - 当前页码加2--%>
                <c:otherwise>
                    <c:set var="begin" value="${pageInfo.pageNum-2}"/>
                    <c:set var="end" value="${pageInfo.pageNum+2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == pageInfo.pageNum}">
            <button style="background-color: rgb(214, 214, 214);" onclick="pageChange(${i},'${condition}','${info}');"><a href="#">${i}</a></button>
        </c:if>
        <c:if test="${i != pageInfo.pageNum}">
            <button onclick="pageChange(${i},'${condition}','${info}');"><a href="#">${i}</a></button>
<%--            <a href="${pageContext.request.contextPath}/user/findByCondition/${i}">${i}</a>--%>
        </c:if>
    </c:forEach>
    <%--页码输出的结束--%>


    <%-- 如果已经 是最后一页，则不显示下一页，末页 --%>
    <c:if test="${pageInfo.pageNum < pageInfo.pages}">
        <button onclick="pageChange(${pageInfo.pages},'${condition}','${info}');"><a href="#">末页</a></button>
        <button onclick="pageChange(${pageInfo.pageNum+1},'${condition}','${info}');"><a href="#">下一页</a></button>
    </c:if>
        &nbsp;
    共${ pageInfo.pages }页，${pageInfo.total}条记录&nbsp;&nbsp;
    到第&nbsp;
        <input class="form-control" style="width: 4%; display:inline;" value="${pageInfo.pageNum}" name="pageNum" id="pn_input"/>
        &nbsp;页
    <input class="btn" id="searchPageBtn" type="button" value="确定" onclick="searchPageBtn('${condition}','${info}');">
</div>
<%--分页条的结束--%>

</body>
</html>
