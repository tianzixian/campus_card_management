<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<script>
    $(function () {
        // 查询用户信息
        $.post("/user/findOne",{},function (data) {
            if(data){
                $("#user_welcome").html(data.name+" ,欢迎回来");
                var identity = data.identity;
                if("ord"==identity){
                    //获取学生id
                    var sid = data.id.toString();
                    // 学生查询账户信息路径
                    var recharge_uri = "/account/info/"+ sid;
                    $("#account_recharge").attr("href",recharge_uri);
                    // 学生查询个人信息路径
                    var user_info_uri = "/user/info/" + sid;
                    $("#user_info").attr("href",user_info_uri);
                    //用户更新密码
                    var update_password_uri = "/user/updatePassword/"+sid;
                    $("#update_password").attr("href",update_password_uri);
                    //移除管理员操作
                    $("#adm_opt").remove();
                }
                if("adm"== identity){
                    //移除普通用户操作
                    $("#ord_opt").remove();
                }
            }
        })
    })
</script>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">
                <span id="user_welcome"></span>
            </a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
<%--            普通用户操作--%>
            <div id="ord_opt">
                <ul class="nav navbar-nav">
                    <li><a id="account_recharge" href="#">账户充值</a></li>
                </ul>
                <ul class="nav navbar-nav">
                    <li><a id="user_info" href="#">用户信息</a></li>
                </ul>
                <ul class="nav navbar-nav">
                    <li><a id="update_password" href="#">修改密码</a></li>
                </ul>
            </div>
<%--    管理员操作--%>
            <div id="adm_opt">
                <ul class="nav navbar-nav">
                    <li><a href="/adminOpt">查询用户</a></li>
                </ul>
                <ul class="nav navbar-nav">
                    <li><a href="/register">添加用户</a></li>
                </ul>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/user/exit">退出登录</a></li>
            </ul>
        </div>
    </div><!-- /.container-fluid -->
</nav>



