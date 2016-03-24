<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="global.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><s:property value="getText('rlglsys.browserhead.IE')" /></title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/WdatePicker.js"></script>
<%
	response.addHeader("Cache-Control", "no-cache");
%>

<script language="JavaScript">
	/* 	//页面初始化时候，给学分年度赋值
	 $(document).ready(function(){
	 var myDate = new Date();
	 var nowyear = myDate.getFullYear();
	 $("#credit_year").val(nowyear);
	 });  */
	// 查询按钮的提交
	$(document).ready(function() {
		$("#btnSearch").click(function() {
			$("#form1").attr("action", "rlgl400103Search.action");
			$("#form1").attr("namespace", "/rlgl");
			$("#form1").submit();
		});
	});
	// 只能输入数字的js 
	function clearNoNum(obj) {
		obj.value = obj.value.replace(/[^\d.]/g, ""); //清除“数字”和“.”以外的字符  
		obj.value = obj.value.replace(/^\./g, ""); //验证第一个字符是数字而不是.  
		obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的  
		obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace(
				"$#$", ".");
		obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');//只能输入两个小数  
	}
</script>
</head>
<body>
	<div>
		<s:form name="form1" id="form1" method="post"
			action="rlgl400103Search" nameSpace="/rlgl">
			<s:hidden name="navigationId" id="navigationId" />
			<s:hidden name="screenId" id="screenId" />
			<s:hidden name="course_name1" id="course_name1" />
			<s:hidden name="course_id1" id="course_id1" />
			<s:hidden name="Amount1" id="Amount1" />
			<s:hidden name="xuefeileibie" id="xuefeileibie" />
			<s:hidden name="zongxuefen" id="zongxuefen" />
			<s:hidden name="PageFlg1" value="rlg022011" />
			<s:hidden name="playFlag" id="playFlag" />
			<div id="content">
				<my:navigation></my:navigation>
				<my:message></my:message>
				<div style="position: relative">
					<table style="width: 68%"
						class="am-table am-margin am-table-striped am-table-hover am-table-bordered ">
						<tr>

							<td>学分年度：<s:textfield id="credit_year" name="credit_year"
									maxLength="4" width="8" onkeyup="clearNoNum(this)" /></td>
							<td>证件编号：<s:property value='%{userId}' /></td>
						</tr>
						<tr>
							<td></td>
							<td align="right"><input type="button"
								class="am-btn am-btn-success  am-btn-block" value="查询"
								name="btnSearch" id="btnSearch"> <s:hidden id="errormsg"
									name="errmsg" value=""></s:hidden></td>
						</tr>
					</table>
				</div>
				<br>
				<s:if test="%{playFlag==2}">
					<div class="am-alert am-alert-secondary am-margin "
						style="width: 68%" data-am-alert>对不起，没有查询到学分信息。</div>
				</s:if>
				<s:if test="%{playFlag==1}">
					<table style="width: 98%"
						class="am-table am-margin am-table-striped am-table-hover am-table-bordered ">
						<thead>
							<tr>
								<th height="28" width="1%" >序号</th>
								<th height="28" width="3%" >课程编号</th>
								<th height="28" width="5%">课程名称</th>
								<th height="28" width="5%" >通过时间</th>
								<th height="28" width="5%" >所在年度</th>
								<th height="28" width="5%" >学分类别</th>
								<th height="28" width="5%" >学分分值</th>
							</tr>
						</thead>
						<s:iterator value="courseExamsList" status="L" id="course">
							<my:trStripe index="${L.index}">
								<td height="20" >${L.index +1}</td>
								<td height="20" ><s:property value='course_id' />
								</td>
								<td height="20" ><s:property value='course_name' />
								</td>
								<td height="20" ><s:property value='exams_time' />
								</td>
								<td height="20"><s:property value='credit_year' />
								</td>

								<td height="20" ><s:if
										test="%{credit_category==001}">
								I类学分
								</s:if> <s:else>
								II类学分
								</s:else></td>
								<td height="20" ><s:property value='credit' /></td>
							</my:trStripe>
						</s:iterator>
					</table>
					<div class="am-margin">
						<my:dividepage actionId="rlgl400103Search.action"></my:dividepage>
					</div>
				</s:if>
			</div>
		</s:form>
		<br />
	</div>
</body>
</html>
