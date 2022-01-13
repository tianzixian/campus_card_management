//删除用户处理
function changeurl(t){
    if(confirm("确认删除?")){
        location.href="/user/delete/"+t.id;
    }
}
function pageChange(pageNum,condition,info){
    // uri="/user/findByCondition/"+pageNum+"?condition="+condition+"&info="+info;
    if(condition.length==0) condition="searchall";
    if(info.length==0) info="null";
    uri="/user/findByCondition/"+pageNum+"/"+condition+"/"+info;
    location.href = uri;
}
function searchPageBtn(condition,info){
    var pageNum = $("#pn_input").val();
    if(pageNum.length == 0) {
        pageNum = 1;
    }
    if(condition.length==0) condition="searchall";
    if(info.length==0) info="null";
    // uri="/user/findByCondition/"+pageNum+"?condition="+condition+"&info="+info;
    uri="/user/findByCondition/"+pageNum+"/"+condition+"/"+info;
    location.href = uri;
}

$(function () {
    //选择查询条件
    $("#sel li").click(function () {
        var choice = this.innerText;
        var st = choice + ' <span class="caret"></span>';
        $("#dropdownMenu1").html(st);
        //保存查询条件
        $("#condition").attr("value", choice);
    })




    // $("#sub").on("click", function () {  //点击提交按钮
    //     $.ajax({
    //         type: "POST",   //提交的方法
    //         url: "/user/findByCondition/1", //提交的地址
    //         data: $('#fm').serialize(),// 序列化表单值
    //         async: false,
    //         error: function (request) {  //失败的话
    //             alert("Connection error");
    //         },
    //         success: function (data) {  //成功
    //             if (data.flag) {
    //                 var list = data.data;
    //                 //填充表格
    //                 var search_table = $("#search_table").removeAttr("hidden");
    //                 var tbody_html;
    //                 for (let i = 0; i < list.length; i++) {
    //                     var id = list[i].id;
    //                     tbody_html+=`<tr>
    //                 <td>`+id+`</td>
    //                 <td>`+list[i].name+`</td>
    //                 <td>`+list[i].sex+`</td>
    //                 <td>`+list[i].major+`</td>
    //                 <td>`+list[i].age+`</td>
    //                 <td>`+list[i].telephone+`</td>
    //                 <td>`+list[i].email+`</td>
    //                 <td>
    //                   <a href='/user/info/`+id+`'>修改</a>&nbsp;|&nbsp;
    //                   <a href='/account/info/`+id+`'>账户信息</a>&nbsp;|&nbsp;
    //                   <a href="javascript:void();" id='`+id+`' onclick="changeurl(this);">删除</a>
    //                 </td>
    //               </tr>`;
    //                 }
    //                 $("#table_list").html(tbody_html);
    //             } else {
    //                 alert(data.errorMsg);
    //             }
    //
    //         }
    //     });
    // });
})


