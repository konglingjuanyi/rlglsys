<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="global.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><s:property value="getText('rlglsys.browserhead.IE')" /></title>
<%
	response.addHeader("Cache-Control", "no-cache");
%>
<script language="JavaScript">
	// 查询按钮的提交
	$(document).ready(function() {
		$("#btnSearch").click(function() {

			$("#form").attr("action", "rlgl100100_5InitSerach.action");
			$("#form").attr("namespace", "/rlgl");
			$("#form").submit();
		});
		//添加余额
		$("#btnDaoChu").click(function() {
			$("#updateFlg").val("0");
			$("#form").attr("action", "rlgl100100_4TimeInit.action");
			$("#form").attr("namespace", "/rlgl");
			$("#form").submit();
		});
	});
	// 修改余额
	function updateNotice(userId, Amount) {
		var callBackFun = function() {
			$("#updateFlg").val("1");
			$("#yue").val(Amount);
			$("#usid").val(userId);
			$("#form").attr("action", "rlgl100100_4TimeInit.action");
			$("#form").submit();
		};
		confirmMessage("CM139", callBackFun);
	}
</script>
</head>

<body>
	<s:form id="form" name="form" method="post" action="rlgl100100Init"
		nameSpace="/rlgl">
		<s:hidden name="navigationId" id="navigationId" />
		<s:hidden name="screenId" id="screenId" />
		<s:hidden name="only_search" id="only_search" />
		<s:hidden name="TransactionID" id="TransactionID" />
		<s:hidden name="playFlag" id="playFlag" />
		<s:hidden name="updateFlg" id="updateFlg" />
		<s:hidden name="yue" id="yue" />
		<s:hidden name="usid" id="usid" />
		<my:navigation></my:navigation>
		<my:message></my:message>
		<!-- 检索条件 -->
		<div class="content">
			<div style="position: relative">
				<table width="70%" style="position: relative; top: 10px;" border="1"
					cellpadding="2" cellspacing="2">
					<%
						
					%>
					<tr>
						<td class="lc1">开始日期： <s:textfield id="startdate"
								name="startdate" /></td>
						<td class="lc1">结束日期：<s:textfield id="enddate" name="enddate" /><font
							color=#FF0000;>日期格式为yyyy-mm-dd例如（1999-01-01）</font></td>
					</tr>
					<tr>
						<td class="lc1">身份证号：<s:textfield id="id" name="id" /></td>
						<td class="lc1" align="right"><input type="button"
							class="inp_L3" value="查询" name="btnSearch" id="btnSearch">
							<input type="button" class="inp_L3" value="新增" name="btnDaoChu"
							id="btnDaoChu"> <s:hidden id="errormsg" name="errmsg"
								value=""></s:hidden></td>
					</tr>
				</table>
			</div>
			<s:if test="%{playFlag==2}">
				<table border="0" width="75%" cellpadding="2"
					style="position: relative;">
					<tr>
						<td align="center"><h4>
								<font color="red"><br />对不起，没有查询到消费记录。</font>
							</h4></td>
					</tr>
				</table>
			</s:if>
			<s:if test="%{playFlag==1}">
				<!-- 明细一览列表 -->
				<div id="result" style="position: relative; top: 15px">
					<my:dividepage actionId="rlgl100100_5InitSerach.action"></my:dividepage>
					<table width="100%" style="position: relative; top: 10px;"
						border="1" cellpadding="2" cellspacing="2">
						<tr>
							<th height="28" width="5%" class="thTitleItrn">序号</th>
							<th height="28" width="8%" class="thTitleItrn">姓名</th>
							<th height="28" width="10%" class="thTitleItrn">身份证号</th>
							<th height="28" width="10%" class="thTitleItrn">工作单位</th>
							<th height="28" width="8%" class="thTitleItrn">单位编号</th>
							<th height="28" width="14%" class="thTitleItrn">单位所属区县</th>
							<th height="28" width="5%" class="thTitleItrn">当前余额</th>
							<th height="28" width="5%" class="thTitleItrn">操作</th>
						</tr>
						<s:iterator value="tb02PrepayMsgList" status="L">
							<my:trStripe index="${L.index}">
								<td height="20" class="tdc">${L.index +1}</td>
								<td height="20" class="tdl"><s:property value='name' /></td>
								<td height="20" class="tdl"><s:property value='userId' /></td>
								<td height="20" class="tdc"><s:property
										value='danweibianNM' /></td>
								<td height="20" class="tdc"><s:property
										value='danweibianhao' /></td>
								<td height="20" class="tdc"><s:property
										value='dangweidizhi' /></td>
								<td height="20" class="tdc"><s:property value='Amount' /></td>
								<td class="tdc"><input type="button"
									class="inp_L3 btnClass_${only_search}"
									onclick="updateNotice('<s:property value="userId"/>','<s:property value="Amount"/>')"
									value="修改" name="btnUpdate" id="btnUpdate"></td>
							</my:trStripe>
						</s:iterator>
					</table>
				</div>
			</s:if>
		</div>
	</s:form>
</body>
</html>

