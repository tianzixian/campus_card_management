// 判断是否为电话号
function telFun(tels){
    var myreg = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
    if (!myreg.test(tels)) {
        return false;
    } else {
        return true;
    }
}
$(function(){
    $("#send").on("click",function () {  //点击提交按钮

        //对生日框的判断
        var b = document.getElementById("birthday").value;
        if(b.length==0 || b == '' || b == undefined || b == null){
            var date = new Date();
            console.log(date.toDateString());
            document.getElementById("sent_birthday").value = date.toDateString();
        }else{
            var date = new Date(Date.parse(b.replace(/-/g, "/")));
            document.getElementById("sent_birthday").value = date.toDateString();
            console.log(date.toDateString());
        }
        // 对学工号 密码 电话号码的判断
        var uid = $("#id").val();
        var pwd = $("#password").val();
        var telephone = $("#telephone").val();
        if (uid.length==0 || uid == '' || uid == undefined || uid == null){
            alert("学工号不能为空！");
        }else if(pwd.length==0 || pwd == '' || pwd == undefined || pwd == null){
            alert("密码不能为空！");
        }else if(telFun(telephone)){
            alert("手机号格式不正确！");
        } else{
            $.ajax({
                type: "POST",   //提交的方法
                url:"/user/register", //提交的地址
                data:$('#registerForm').serialize(),// 序列化表单值
                async: false,
                error: function(request) {  //失败的话
                    alert("Connection error");
                },
                success: function(data) {  //成功
                    if(data.flag){
                        console.log(data.data)
                        if("adm"==data.data){
                            alert("用户添加成功！");
                            location.href="/adminOpt";
                        }else{
                            alert("注册成功！");
                            location.href="/login";
                        }
                    }else{
                        alert(data.errorMsg);
                    }
                }
            });
        }
    });
});