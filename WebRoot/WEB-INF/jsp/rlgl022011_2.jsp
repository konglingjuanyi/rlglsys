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
   $(document).ready(function(){
     $("#btnSearch").click(function(){ 
    /*  var credit_year = $("#credit_year").val();
     var myDate = new Date();
     var nowyear = myDate.getFullYear();
     var minyear = nowyear -4;
     if(credit_year !=null && credit_year != "" && 
        (credit_year > nowyear ||credit_year< minyear)){
      alert("您输入的学分年度值不正确，正确的年度为最近五年的年度值。");
      return false;
     } */
     $("#form1").attr("action", "rlgl022004_2Search.action");
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
   
   //申请学分按钮
	function jiaoExam(course_name,course_id,course_price)
	{   
	   if(course_price!=null){
	    var course_price1 =parseFloat(course_price);}else {course_price1 =0;}
		if(course_price1>$("#balance").val())
		{
			alert("余额不足，请先从本网站左上角的“网上缴费”进行充值");
			return ;
		}
	   $("#course_name1").val(course_name);
	   $("#course_id1").val(course_id);
	   $("#Amount1").val(course_price);
	   $("#form1").attr("action", "Rlgl022001101Init.action");
       $("#form1").submit();
       
	}

</script>
</head>
<body>
	<div>
		<s:form name="form1" id="form1" method="post"
			action="rlgl022004Search" nameSpace="/rlgl">
			<s:hidden name="navigationId" id="navigationId" />
			<s:hidden name="screenId" id="screenId" />
			<s:hidden name="course_name1" id="course_name1" />
			<s:hidden name="course_id1" id="course_id1" />
			<s:hidden name="Amount1" id="Amount1" />
			<s:hidden name="xuefeileibie" id="xuefeileibie" />
			<s:hidden name="zongxuefen" id="zongxuefen" />
			<s:hidden name="PageFlg1" value="rlg022011" />
			<s:hidden name="balance" id="balance" />
			<div id="content">
				<my:navigation></my:navigation>
				<my:message></my:message>

				<table style="width: 68%"
					class="am-table am-margin am-table-striped am-table-hover am-table-bordered ">
					<tr>
						<td>学分年度：<s:textfield id="credit_year" name="credit_year"
								maxLength="4" width="8" onkeyup="clearNoNum(this)" /></td>
						<td>证件编号：<s:property value='%{userId}' /></td>
					</tr>
					<tr>
						<td>课程名称：<s:textfield id="course_name" name="course_name"
								maxLength="50" width="8" /></td>
						<td align="right"><input type="button"
							class="am-btn am-btn-success  am-btn-block" value="查询"
							name="btnSearch" id="btnSearch"> <s:hidden id="errormsg"
								name="errmsg" value=""></s:hidden></td>
					</tr>
				</table>

				<s:if test="%{playFlag==2}">
					<div class="am-alert am-alert-secondary am-margin " style="width: 68%"
						data-am-alert>对不起，没有查询到通过考试的课程信息.</div>
				</s:if>
				<s:if test="%{playFlag==1}"> 
							<table style="width: 98%"
								class="am-table am-margin am-table-striped am-table-hover am-table-bordered ">
								<thead>
									<tr>
										<th height="28" width="3%">序号</th>
										<th height="28" width="12%">课程编号</th>
										<th height="28" width="20%">课程名称</th>
										<th height="28" width="5%">学分</th>
										<th height="28" width="10%">价格</th>
										<th height="28" width="10%">通过时间</th>
										<th height="28" width="10%">所在年度</th>
										<th height="28" width="10%">操作</th>
									</tr>
								</thead>
								<s:iterator value="courseExamsList" status="L" id="course">
									<my:trStripe index="${L.index}">
										<td height="20">${L.index +1}</td>
										<td height="20"><s:property value='course_id' /></td>
										<td height="20"><s:property value='course_name' /></td>
										<td height="20"><s:property value='credit' /></td>
										<s:hidden name="credit" id="credit" />
										<td height="20"><s:property value='course_price' /></td>
										<td height="20"><s:property value='exams_time' /></td>
										<td height="20"><s:property value='credit_year' /></td>
										<td><s:if test="isapply==0">
												<input class="inp_L3 btnClass_${only_search}" type="button"
													id="butLearn" value="申&nbsp;请&nbsp;学分"
													onclick="jiaoExam('<s:property value='course_name'/>','<s:property value='course_id'/>','<s:property value='course_price'/>');" /></input>
											</s:if> <s:if test="isapply==1">
												<input class="inp_L3 btnClass_${only_search}" type="button"
													id="butLearn" value="已申请学分" disabled="disabled"
													onclick="jiaoExam('<s:property value='course_name'/>','<s:property value='course_id'/>','<s:property value='course_id'/>');" /></input>
											</s:if>
									</my:trStripe>
								</s:iterator>
							</table>
							<div class="am-margin">
							<my:dividepage
									actionId="rlgl022004Search.action"></my:dividepage>
							</div>
				</s:if>
			</div>
		</s:form>
		<br />
	</div>
</body>
</html>
