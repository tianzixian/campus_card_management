$(function(){
    $("#btn_sub").on("click",function () {  //点击提交按钮
            $.ajax({
                type: "POST",   //提交的方法
                url:"/intermedia/login", //提交的地址
                data:$('#loginForm').serialize(),// 序列化表单值
                async: false,
                error: function(request) {  //失败的话
                    alert("Connection error");
                },
                success: function(data,request) {  //成功
                    if(data.flag){
                        var token = data.data.token;
                        var identity = data.data.identity;
                        window.localStorage.setItem("token",token);
                        var d = new Date();
                        d.setTime(d.getTime()+(1*24*60*60*1000)); //一天后到期
                        document.cookie = "token=" + token + ";expires="+d.toGMTString();
                        if("ord"==identity){
                            var sid = data.data.sid;
                            location.href="/user/opt/" + sid;
                        }
                        if("adm"==identity){
                            location.href="/adminOpt";
                        }
                    }else{
                        $("#err_info").html(data.errorMsg);
                        //修改验证码
                        var img = $("div.verify span img")[0];
                        changeCheckCode(img);
                        //alert(data.errorMsg);
                    }
                }
            });
    });
});