<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="global.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<TITLE>山东省卫生人力资源管理系统</TITLE>
<%
response.addHeader("P3P","CP=CAO PSA OUR");
%>
<script language="JavaScript">
$(document).ready(function(){
    $("#btn_cancel").click(function(){
    	var str="";
    	$("input[name='xz_ckb']:checked").each(function(){     
			alert("aaaaa")
         	str ="1";   
        });
        if(str == ''){
         alert("请选择一条数据！");
         return  false;
        }     
    	 $("#rlgl100104form").attr("action", "rlgl100104_2Cancel.action");
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
function learnExam(end_date,course_id, course_support,is_prep)
{
	learnOnline.url = "LearnOnlineLink.action";
    learnOnline.pageObjectId = "learnOnlineUrl";
    // 课程ID、课程提供方、画面标记、用户ID、系统网址
    learnOnline.urlParams = course_id +"," + course_support + "," + "rlgl100104" + ","+ $("#user_id").val()+"," + $("#sysUrl").val()+","+is_prep;
    learnOnlineLink();
}

</script>
</head>
<body>
	<my:navigation></my:navigation>
	<table cellpadding='0' cellspacing='0' width='100%'>
		<tr>
			<td>
				<div  style="height:150px;" class="content">
					<form id="rlgl100104form" nameSpace="/rlgl" action="doLogin"
						method="post">
						<s:hidden name="navigationId" id="navigationId" />
						<s:hidden name="screenId" id="screenId"/>
						<s:hidden name="learnOnlineUrl" id="learnOnlineUrl"/>
						<s:hidden name="total" id="totalHid" />
						<s:hidden name="temp_str" id="temp_str" />
						<s:hidden name="end_date" id="end_date" />
						<s:hidden name="sysUrl" id="sysUrl" />
						<s:hidden name="user_id" id="user_id" />
							<div class="am-panel am-panel-secondary am-margin">
							<div class="am-panel-hd">
								<h3 class="am-panel-title">已选课件列表	2<input type="button" id="btn_cancel" value="取消课程" class="am-btn am-btn-primary" /></h3>
								
							</div>
						</div>
						<table style="width: 98%"
							class="am-table am-margin am-table-striped am-table-hover am-table-bordered">
							 <thead>
							<tr>
								<th >选择</th>
								<th >序号</th>
								<th >课件名称</th>
								<th >出版单位</th>
								<th >课件来源</th>
								<th >截止日期</th>
								<th >学分</th>
								<th >学习</th>
							</tr>
							</thead>

							<s:iterator value="courseSelectedList" status="L" var="course">
								<my:trStripe index="${L.index}" delflg="${is_prep}">
									<td >
									<s:if test="is_prep==1">
										<input type="checkbox" id="xz_ckb" disabled="disabled" value="${course.course_id}" name="xz_ckb">
									</s:if>
									<s:else>
									    <input type="checkbox" id="xz_ckb" value="${course.course_id}" name="xz_ckb">
									</s:else>									
									</input></td> 
									<td >${L.index +1}</td>
									<td ><s:if test="course_name==''">
											<s:property value='无' />
										</s:if> <s:else>
											<a onclick="showDiv('<s:property value='course_id' />','<s:property value='course_explain' />','<s:property value='course_image_url' />');"><s:property value='course_name' /></a>
										</s:else></td>
									<td ><s:property value='course_expert_unit' /></td>
									<td ><s:if test="course_support == 001">好医生</s:if> 
										<s:if test="course_support == 002">华医网</s:if> 
										<s:if test="course_support == 003">24小时在线</s:if>
										<s:if test="course_support == 004">自主制作</s:if>
										<s:if test="course_support == 005">其他</s:if>
									</td>		
									<td ><s:property value='end_date' /></td>
									<td ><span style="color: #00AA00 "><s:property
												value='xuefen' />(分)</span></td>
									<td >
										<s:if test="is_prep==0">
											<input class="inp_L3 btnClass_${only_search}" type="button"
											id="butLearn" value="学&nbsp;&nbsp;习"
											onclick="learnExam('<s:property value='end_date'/>','<s:property value='course_id'/>','<s:property value='course_support'/>','<s:property value='is_prep'/>');" /></input>
										</s:if>
										<s:if test="is_prep==1">
											<input class="inp_L3 btnClass_${only_search}" type="button"
											id="butLearn" value="已通过" disabled="disabled"
											onclick="learnExam('<s:property value='end_date'/>','<s:property value='course_id'/>','<s:property value='course_support'/>','<s:property value='is_prep'/>');" /></input>
										</s:if>
										<s:if test="is_prep==2">
											<input class="inp_L3 btnClass_${only_search}" type="button"
											id="butLearn" value="补考"
											onclick="learnExam('<s:property value='end_date'/>','<s:property value='course_id'/>','<s:property value='course_support'/>','<s:property value='is_prep'/>');" /></input>
										</s:if>
									</td>
								</my:trStripe>
							</s:iterator>
						</table>
					</form>
				</div>
			</td>
		</tr>
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<Strong>请参照以下简介(请点击课件名称)：</Strong> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<img width="100%" src="/images/login/libg.png"></img> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td> -->
<!-- 				<table border="0" style="width:99%"> -->
<!-- 					<tr> -->
<!-- 						<td width="20%" valign="top"> -->
<!-- 							<div id="profile_img"> -->
<!-- 								<img src="" id="imgShow" width="160" height="160" border="0"></img> -->
<!-- 							</div> -->
<!-- 						</td> -->
<!-- 						<td valign="top"> -->
<!-- 							<div id="profile_txt" style="margin-left:10px;"> -->
<!-- 								<textarea type="text" id="divShow" value="" rows="4" style="overflow-y:hidden;line-height:normal;width:100%;background:transparent;border-style:none; " disabled="disabled"/></textarea> -->
<!-- 							</div> -->
<!-- 						</td> -->
<!-- 					</tr> -->
<!-- 				</table> -->
<!-- 			</td> -->
<!-- 		</tr> -->
	</table>
</body>
</html>
