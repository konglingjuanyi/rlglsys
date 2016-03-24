/****************************************************************
 * 方法名：$(document).ready
 * 概 要：页面各事件的定义，页面初期显示处理调用的Javascript定义
 * 参 数：无
 * 返回值：无
 ***************************************************************/
var sessionTimeOutFLg="";
$(document).ready(function(a){
	// 【医疗卫生机构入口】事件的定义
    $("#medical1,#medical2").click(function(){
    	
        $("#selectPage").val("loginpage1");
        $("#selectTitle").val("tit_1.png");
        sessionTimeOutFLg = "1";
        sessionCheck();
       
    });

	// 【卫生行政部门入口】事件的定义
    $("#department1,#department2").click(function(){
        $("#selectPage").val("loginpage2");
        $("#selectTitle").val("tit_2.png");
        sessionTimeOutFLg = "2";
        sessionCheck();
    });

	// 【卫生技术人员入口】事件的定义
    $("#technology1,#technology2").click(function(){
        $("#selectPage").val("loginpage3");
        $("#selectTitle").val("tit_3.png");
        sessionTimeOutFLg = "3";
        sessionCheck();
    });

	// 【乡医培训入口】事件的定义
    $("#dotortrain1,#dotortrain2").click(function(){
        $("#selectPage").val("loginpage4");
        $("#selectTitle").val("tit_4.png");
        sessionTimeOutFLg = "4";
        sessionCheck();
    });

    // 画面初始化
    initialize(a);
    $("#content").height($(document).height());
    $(".add").typewriter(100); 
});
function sessionCheck()
{
	learnOnline.url = "sessioncheck.action";
    learnOnline.pageObjectId = "sessionTimeOutFLg";
    learnOnline.urlParams = "";
    getTimeOUtCheckFlg();
}
function getTimeOUtCheckFlg(){
    $.post(learnOnline.url, null, callbackSessionCheck);
}

function callbackSessionCheck(result, textStatus){
	// 需要变更的隐藏变量的ID
	var sysUrl = location.protocol + "//" + location.host + "/rlglsysnew";
	if (result != "")
    { 
		if (sessionTimeOutFLg == "1")
		{
			openTopMenuSession("${ctx}/doRedirect.action?selectPage=loginpage1&selectTitle=tit_1.png&sysUrl="+sysUrl);
		}
		if (sessionTimeOutFLg == "2")
		{
			openTopMenuSession("${ctx}/doRedirect.action?selectPage=loginpage2&selectTitle=tit_2.png&sysUrl="+sysUrl);
		}
		if (sessionTimeOutFLg == "3")
		{
			openTopMenuSession("${ctx}/doRedirect.action?selectPage=loginpage3&selectTitle=tit_3.png&sysUrl="+sysUrl);
		}
		if (sessionTimeOutFLg == "4")
		{
			openTopMenuSession("${ctx}/doRedirect.action?selectPage=loginpage4&selectTitle=tit_4.png&sysUrl="+sysUrl);
		}
    	
    } else {
    	if (sessionTimeOutFLg == "1")
		{
    		openTopMenu("${ctx}/doRedirect.action?selectPage=loginpage1&selectTitle=tit_1.png&sysUrl="+sysUrl);
		}
		if (sessionTimeOutFLg == "2")
		{
			openTopMenu("${ctx}/doRedirect.action?selectPage=loginpage2&selectTitle=tit_2.png&sysUrl="+sysUrl);
		}
		if (sessionTimeOutFLg == "3")
		{
			openTopMenu("${ctx}/doRedirect.action?selectPage=loginpage3&selectTitle=tit_3.png&sysUrl="+sysUrl);
		}
		if (sessionTimeOutFLg == "4")
		{
			openTopMenu("${ctx}/doRedirect.action?selectPage=loginpage4&selectTitle=tit_4.png&sysUrl="+sysUrl);
		}
    	
    }
}
function initialize(a){
	var apk_file = "/rlglsys/rlgl.apk";
    var apk_addr = location.protocol + "//" + location.host + apk_file;
    
	a.fn.typewriter = function(speed) {
		this.each(function() {
		    var d = a(this),
		    c = d.html() + apk_addr,
		    b = 0;
		    d.html("");
		    var e = setInterval(function() {
		        var f = c.substr(b, 1);
		        if (f == "<") {
		            b = c.indexOf(">", b) + 1;
		        } else {
		            b++;
		        }
		        d.html(c.substring(0, b));
		        if (b >= c.length) {
		            clearInterval(e);
		        }
		   }, speed); });
		return this;
		}
}

var topMenu_top=0;
var topMenu_left=0;
var topMenu_x=980;
var topMenu_y=680;
var WINDOW_NAME = "rlglsys";
function openTopMenu(action) {
	 
	window.location.href=action;
	/* window.open(action); 
	  var strParameters = "status=yes, toolbar=no, location=no, menubar=no, scrollbars=yes, resizable=yes,";
	  strParameters = strParameters +  " top=" + topMenu_top +", left=" + topMenu_left +",  height=" + topMenu_y + ", width=" + topMenu_x;
	  var StartGamen = window.open("_blank", WINDOW_NAME, strParameters);
	  StartGamen.location= action;
	  StartGamen.focus();
	  top.opener = '';
	  top.open("","_self");
	  if ($("#returnFlg").val() != "1"){
		  window.close();
	  }  */
}

function openTopMenuSession(action) {
	 
	window.location.href=action;
	/*  var strParameters = "status=yes, toolbar=no, location=no, menubar=no, scrollbars=yes, resizable=yes,";
	  strParameters = strParameters +  " top=" + topMenu_top +", left=" + topMenu_left +",  height=" + topMenu_y + ", width=" + topMenu_x;
	  var StartGamen = window.open("_blank", WINDOW_NAME, strParameters);
	  StartGamen.location= action;
	  StartGamen.focus();
	  top.opener = '';
	  top.open("","_self");  */
}