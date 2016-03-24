/****************************************************************
 * 方法名：$(document).ready
 * 概 要：页面各事件的定义，页面初期显示处理调用的Javascript定义
 * 参 数：无
 * 返回值：无
 ***************************************************************/
// 用户一览
function searchuser() {
    document.form1.action = "rlgl090102Init.action";
    document.form1.nameSpace = "/rlgl";
    document.form1.submit();
}
// 保存
function registerUser() {
    document.form1.action = "rlgl090101Register.action";
    document.form1.nameSpace = "/rlgl";
    document.form1.submit();
}
// 删除用户
function delUser(id) {
    document.getElementById("userIndex").value=id;
    document.form1.action = "rlgl090101Del.action";
    document.form1.nameSpace = "/rlgl";
    document.form1.submit();
}
$(function(){
    // 用户添加
    $("#btnAdduser").click(function(){
        $("form").attr("action", "rlgl090101Add.action");
        $("form").submit();
    });
    
    // 用户一览
    $("#btnSearchuser").click(function(){
        $("#form").attr("action", "rlgl090102Init.action");
        $("#form").submit();
    });
    
    // 注册
    $("#btnSave").click(function(){
        $("#form").attr("action", "rlgl090101Register.action");
        $("#form").submit();
    });
    
    // 删除
    $("#btnDel").click(function(){
        $("#form1").attr("action", "rlgl090101Del.action");
        $("#form1").submit();
    });
});