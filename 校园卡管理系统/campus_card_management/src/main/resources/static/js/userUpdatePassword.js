$(function(){
    $("#sub").on("click",function () {  //点击提交按钮
        if($("#originalPassword1").val()!=$("#originalPassword2").val()){
            alert("原密码不正确")
        } else if( $("#confirmPassword").val() != $("#newPassword").val() ){
            alert("两次密码输入不正确");
        }
        else{
            $.ajax({
                type: "POST",   //提交的方法
                url:"/user/updatePassword", //提交的地址
                data:$('#change_password').serialize(),// 序列化表单值
                async: false,
                error: function(request) {  //失败的话
                    alert("Connection error");
                },
                success: function(data) {  //成功
                    if(data.flag){
                        alert("密码修改成功,请重新登录");
                        location.href="/login";
                    }else{
                        alert(data.errorMsg);
                    }
                }
            });
        }

    });
});