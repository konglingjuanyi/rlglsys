/****************************************************************
 * 方法名：$(function(){
 * 概 要：页面各事件的定义，页面初期显示处理调用的Javascript定义
 * 参 数：无
 * 返回值：无
 ***************************************************************/
$(function(){
	// 退出按钮押下
    $("#loginOutBtn").click(function(){
    	var callBackFun = function() {
    		$("#headForm").attr("action", "exitPage.action");
    	    $("#headForm").submit();
    		window.parent.close();
    		};
    	confirmMessage("CM001", callBackFun);
    });

	// 重新登录按钮押下
    $("#reLoginBtn").click(function(){
    	confirmMessage("CM002", logout);
    });

	// 隐藏菜单按钮押下
    $("#hideMenu").click(function(){
        if (parent.pageframe.cols=="220,*"){
            parent.pageframe.cols="0,*";
            $(this).val("显示左侧菜单");
        } else {
            parent.pageframe.cols="220,*";
            $(this).val("隐藏左侧菜单");
        }
    });

	// 修改密码按钮押下
    $("#passChangeBtn").click(function(){
    	var action = "rlgl000103Init.action" + "?naviId=" + "navi060";
	    parent.contents.addTab(action, "修改密码");
    });
    setInterval(function() {
    	             $("#nowTime").html(current());
    	             }
                ,1000);
});

function current(){ 
	var d=new Date(),str=''; 
	str +=d.getFullYear()+'年'; //获取当前年份 
	str +=d.getMonth()+1+'月'; //获取当前月份（0——11） 
	str +=d.getDate()+'日'; 
	str +=d.getHours()+'时'; 
	str +=d.getMinutes()+'分'; 
	str +=d.getSeconds()+'秒'; 
	return str;
} 

function removeDisabled(){
	$("#loginOutBtn").removeAttr("disabled");
	$("#reLoginBtn").removeAttr("disabled");
	$("#passChangeBtn").removeAttr("disabled");
}
/****************************************************************
 * 方法名：logout
 * 处理概要：重新登录
 * 参数： 无
 * 返回值： 无
 ***************************************************************/
function logout(){
	var topMenu_top=0;
	var topMenu_left=0;
	var topMenu_x=980;
	var topMenu_y=680;
	var WINDOW_NAME = "rlglsys";
	var strParameters = "status=yes, toolbar=no, location=no, menubar=no, scrollbars=yes, resizable=yes,";
	strParameters = strParameters +  " top=" + topMenu_top +", left=" + topMenu_left +",  height=" + topMenu_y + ", width=" + topMenu_x;
	var StartGamen = window.open("./doLogout.action", WINDOW_NAME, strParameters);
	StartGamen.focus();
	top.opener = "";
	top.open("","_self");
}