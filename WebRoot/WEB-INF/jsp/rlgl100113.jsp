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

function learnExam(end_date,course_id, course_support)
{
    course_support = "001";
	learnOnline.url = "LearnOnlineLink.action";
    learnOnline.pageObjectId = "learnOnlineUrl";
    // 课程ID、课程提供方、画面标记、用户ID、系统网址
    learnOnline.urlParams = course_id +"," + course_support + "," + "rlgl100113" + ","+ $("#user_id").val()+"," + $("#sysUrl").val()+","+"0";
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
<%-- 						<s:hidden name="course_count" id="course_count" /> --%>
<%-- 						<s:hidden name="public_course_count" id="public_course_count" /> --%>
						<table width="100%" border="1px">
							<tr>
								<td colspan="8" align="left">公共课程学习列表&nbsp;&nbsp;&nbsp;&nbsp;
								</td>
							</tr>
							<tr>
								<th class='thTitleItrn'>序号</th>
								<th class='thTitleItrn'>课件名称</th>
								<th class='thTitleItrn'>学习对象</th>
								<th class='thTitleItrn'>课件来源</th>
								<th class='thTitleItrn'>考试截止日期</th>
								<th class='thTitleItrn'>学分</th>
								<th class='thTitleItrn'>学习</th>
							</tr>

							<s:iterator value="courseSelectedList" status="L" var="course">
								<my:trStripe index="${L.index}">
									<td class="tdc">${L.index +1}</td>
									<td class='tbl'><s:if test="course_name==''">
											<s:property value='无' />
										</s:if> <s:else>
										<s:property value='course_name' />
										</s:else></td>
									<td class='tbl'><s:property value='course_expert_unit' /></td>
									</td>	
									<td class='tbl'>
									<s:if test="course_support=='001'">好医生</s:if>
									<s:else>好医生</s:else>
									</td>
									<td class='tbl'><s:property value='end_date' /></td>
									<td class='tdc'><span style="color: #00AA00 "><s:property
												value='xuefen' />(分)</span></td>
									<td class='tdc'>
										<s:if test="is_prep==0">
											<input class="inp_L3 btnClass_${only_search}" type="button"
											id="butLearn" value="学&nbsp;&nbsp;习"
											onclick="learnExam('<s:property value='end_date'/>','<s:property value='course_id'/>','<s:property value='course_support'/>');" /></input>
										</s:if>
									</td>
								</my:trStripe>
							</s:iterator>
						</table>
					</form>
				</div>
			</td>
		</tr>
	</table>
		<div style="margin-top: 30px;">
		<font>1、本模块（<font color="red">公共课程学习</font>）中，提供的课件仅供参考。</br>
			2、学习完毕必须到<font color="red">公共课程考试</font>模块中通过考试，才能获得相应成绩。
			</br>
			3、可以不进行学习，直接到<font color="red">公共课程考试</font>模块中考试。
		</font>
	</div>
</body>
</html>
