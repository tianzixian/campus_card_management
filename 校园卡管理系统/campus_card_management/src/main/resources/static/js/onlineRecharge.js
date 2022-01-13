function recharge(){
    var resualt = false;
    var cards = document.getElementsByName("cardId");
    for(var i=0 ; i<cards.length ; i++){
        if(cards[i].checked){
            resualt=true;
        }
    }
    if(!resualt){
        alert("请选择需要充值的银行卡！");
    }else{
        document.getElementById("hid_div").removeAttribute("hidden");
    }
}
function subm(){
    var aom = document.getElementById("aom").value;
    var cardval = 0;
    var cards = document.getElementsByName("cardId");
    for(var i=0 ; i < cards.length ; i++){
        if(cards[i].checked){
            cardval = cards[i].parentNode.parentNode.children[3].innerHTML;
            break;
        }
    }
    if(parseInt(cardval) >= parseInt(aom)){
        return true;
    }
    alert("余额不足");
    return false;
}
$(function(){
    $("#sub").on("click",function () {  //点击提交按钮
        if(subm()){    //余额检测
            $.ajax({
                type: "POST",   //提交的方法
                url:"/bankcard/recharge", //提交的地址
                data:$('#fm').serialize(),// 序列化表单值
                async: false,
                error: function(request) {  //失败的话
                    alert("Connection error");
                },
                success: function(data) {  //成功
                    if(data.flag){
                        alert("充值成功");
                        location.href="/sbc/info/"+data.data;
                    }else{
                        alert(data.errorMsg);
                    }
                }
            });
        }
    });
});
