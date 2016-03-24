<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="global.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<TITLE>山东省卫生人力资源管理系统</TITLE>
<%
response.addHeader("P3P","CP=CAO PSA OUR");
%>
<script language="JavaScript">
Date.prototype.Format = function (fmt) { 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}


$(document).ready(function(){
    $("#btn_cancel").click(function(){
    	var str="";
    	$("[name='xz_ckb'][checked]").each(function(){     
         	str ="1";   
        });
        if(str == ''){
         alert("请选择一条数据！");
         return  false;
        }     
    	 $("#rlgl100104form").attr("action", "rlgl100104Cancel.action");
	     $("#rlgl100104form").attr("namespace", "/rlgl");
	     $("#rlgl100104form").submit();
    });
	$("#select_kbn_check").click(function(){
		if(this.checked){
			$("#total").html("120.00");
		}
		else
		{
		 	$("#total").html($("#totalHid").val());
		}
    });
    $("#select_kbn_check").click(function(){
        openChildWindow.open("rlgl300101Init.action", param, callBackFun_unitSelect, "1",location=no);
    });
    $("#profile_img").hide();
    $("#profile_txt").hide();
   
});

//textarea自适应高度
var agt = navigator.userAgent.toLowerCase();
var is_op = (agt.indexOf("opera") != -1);
var is_ie = (agt.indexOf("msie") != -1) && document.all && !is_op;
function ResizeTextarea(row){
    if(!row)
        row=5;
    var b=document.getElementById("divShow").value.split("\n");
    var c=is_ie?1:0;
    c+=b.length;
    var d=document.getElementById("divShow").cols;
    if(d<=20){d=40}
    for(var e=0;e<b.length;e++){
        if(b[e].length>=d){
            c+=Math.ceil(b[e].length/d)
        }
    }
    c=Math.max(c,row);
    if(c!=document.getElementById("divShow").rows){
        document.getElementById("divShow").rows=c;
    }
}

//控制显示简介
function showDiv(id,explain,url)
{
	if(url != "" && url != null)
	{
		document.getElementById("imgShow").src = "/rlglsys/images/learnonline/" + url;
	}
	else
	{
		document.getElementById("imgShow").src = "/rlglsys/images/image2.jpg";
	}
	document.getElementById("divShow").value = explain;
	ResizeTextarea(4);
    $("#profile_img").show();
    $("#profile_txt").show();
}

//返回页面
function goBack() {
	$("#rlgl100104form").attr("action", "rlgl022005Init.action");
	$("#rlgl100104form").submit();
}
function learnExam(end_date,course_id, course_support)
{
//     if($("#course_count").val() < 3){
//      alert("您未通过三门课程学习，不可进行公共课考试！");
//      return  false;
//     }
    
//     if($("#public_course_count").val() > 1){
//      alert("您已通过两门公共课考试，不可继续进行公共课考试！");
//      return  false;
//     }
    var time1 = new Date().Format("yyyy-MM-dd");
   	if(time1 > end_date){
   		alert("考试时间已过！");
   		return false;
   	}
    
    
    
    var a = Math.floor(Math.random() * 10 + 1);
    //if(a%2 ==1){
     //course_support = "002";
   //}else{
     course_support = "001";
    //}
	learnOnline.url = "LearnOnlineLink.action";
    learnOnline.pageObjectId = "learnOnlineUrl";
    // 课程ID、课程提供方、画面标记、用户ID、系统网址
    learnOnline.urlParams = course_id +"," + course_support + "," + "rlgl100107" + ","+ $("#user_id").val()+"," + $("#sysUrl").val()+"," +"0";
    learnOnlineLink();
}

</script>
</head>
<body>
	<my:navigation></my:navigation>
	<table cellpadding='0' cellspacing='0' width='100%'>
		<tr>
			<td>
				<div style="height: 150px;" class="content">
					<form id="rlgl100104form" nameSpace="/rlgl" action="doLogin"
						method="post">
						<s:hidden name="navigationId" id="navigationId" />
						<s:hidden name="screenId" id="screenId" />
						<s:hidden name="learnOnlineUrl" id="learnOnlineUrl" />
						<s:hidden name="total" id="totalHid" />
						<s:hidden name="temp_str" id="temp_str" />
						<s:hidden name="end_date" id="end_date" />
						<s:hidden name="sysUrl" id="sysUrl" />
						<s:hidden name="user_id" id="user_id" />

						<div class="am-panel am-panel-secondary am-margin">
							<div class="am-panel-hd">
								<h3 class="am-panel-title">公共课程列表</h3>
							</div>
						</div>
						<table style="width: 98%"
							class="am-table am-margin am-table-striped am-table-hover am-table-bordered">
							<thead>
								<tr>

									<th>序号</th>
									<th>试题名称</th>
									<th>学习对象</th>
									<th>学习要求</th>
									<th>考试截止日期</th>
									<th>学分</th>
									<th>考试</th>
								</tr>
							</thead>
							<s:iterator value="courseSelectedList" status="L" var="course">
								<my:trStripe index="${L.index}"> 
									<td>${L.index +1}</td>
									<td ><s:if test="course_name==''">
											<s:property value='无' />
										</s:if> <s:else>
											<!-- <a onclick="showDiv('<s:property value='course_id' />','<s:property value='course_explain' />','<s:property value='course_image_url' />');"> -->
											<s:property value='course_name' />
											<!-- </a> -->
										</s:else></td>

									<td ><s:property value='course_expert_unit' /></td>
									<td><s:property value='bixiu_flag' /></td>
									<s:hidden value='course_support' />
									<td ><s:property value='end_date' /></td>
									<td ><span style="color: #00AA00"><s:property
												value='xuefen' />(分)</span></td>
									<td class="am-danger"><s:if test="is_prep==0">
											<input class="inp_L3 btnClass_${only_search}" type="button"
												id="butLearn" value="考&nbsp;&nbsp;试"
												onclick="learnExam('<s:property value='end_date'/>','<s:property value='course_id'/>','<s:property value='course_support'/>');" /></input>
										</s:if> <s:if test="is_prep==1">
											<input class="inp_L3 btnClass_${only_search}" type="button"
												id="butLearn" value="已通过" disabled="disabled"
												onclick="learnExam('<s:property value='end_date'/>','<s:property value='course_id'/>','<s:property value='course_support'/>');" /></input>
										</s:if> <s:if test="is_prep==2">
											<input class="inp_L3 btnClass_${only_search}" type="button"
												id="butLearn" value="补考"
												onclick="learnExam('<s:property value='end_date'/>','<s:property value='course_id'/>','<s:property value='course_support'/>');" /></input>
										</s:if></td>
								</my:trStripe>
							</s:iterator>
						</table>

					</form>
				</div>
			</td>
		</tr>
		<!-- <tr>
			<td>
				<Strong>请参照以下简介(请点击课件名称)：</Strong>
			</td>
		</tr>
		<tr>
			<td>
				<img width="100%" src="/images/login/libg.png"></img>
			</td>
		</tr>
		<tr>
			<td>
				<table border="0" style="width:99%">
					<tr>
						<td width="20%" valign="top">
							<div id="profile_img">
								<img src="" id="imgShow" width="160" height="160" border="0"></img>
							</div>
						</td>
						<td valign="top">
							<div id="profile_txt" style="margin-left:10px;">
								<textarea type="text" id="divShow" value="" rows="4" style="overflow-y:hidden;line-height:normal;width:100%;background:transparent;border-style:none; " disabled="disabled"/></textarea>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr> -->
	</table>
</body>
</html>
