<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="global.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><s:property value="getText('rlglsys.browserhead.IE')" /></title>
<%
	response.addHeader("Cache-Control", "no-cache");
%>

<script language="JavaScript">
	$(document).ready(
			function() {
				$('#user_id_sigle').val($("user_id").val());
				
				// 确认修改密码
				$("#btnReset").click(
						function() {
							if (($("#password_old").val() == ""
									|| $("#password_new").val() == "" || $(
									"#password_new_confirm").val() == "")
									&& $("#user_id").val() == "") {
								alertMessage("AM031");
								return false;
							}
							if ($("#password_new").val() != $(
									"#password_new_confirm").val()) {
								alertMessage("AM030");
								return false;
							}
							
						    $("#form1").attr("action","rlgl000103Update.action");
							$("#form1").submit(); 
						});
				/* // 重置
				$("#btnReset").click(function() {
					$("#password_old").val("");
					$("#password_new").val("");
					$("#password_new_confirm").val("");
				}); */

				initialize();
			});

	function initialize() {
		var updateFlg = $("#updateFlg").val();

		// 如果修改成功
		if (updateFlg == "success") {
			alertMessage("AM033", logout);
			if ($("#user_id").val() != "") {
// 				window.opener = null;
// 				window.open('', '_self');
// 				window.close();
			}
		}
	}
	/****************************************************************
	 * 方法名：logout
	 * 处理概要：重新登录
	 * 参数： 无
	 * 返回值： 无
	 ***************************************************************/
	function logout() {
		var topMenu_top = 0;
		var topMenu_left = 0;
		var topMenu_x = 980;
		var topMenu_y = 680;
		var WINDOW_NAME = "rlglsys";
		var strParameters = "status=yes, toolbar=no, location=no, menubar=no, scrollbars=yes, resizable=yes,";
		strParameters = strParameters + " top=" + topMenu_top + ", left="
				+ topMenu_left + ",  height=" + topMenu_y + ", width="
				+ topMenu_x;
		var StartGamen = window.open("./doLogout.action", WINDOW_NAME,
				strParameters);
		StartGamen.focus();
		top.opener = "";
		top.open("", "_self");
	}
</script>
</head>
<body>
	<div>
		<s:form id="form1" name="form1" method="post" action="rlgl000103Init"
			nameSpace="/rlgl">
			<s:hidden name="screenId" id="screenId" />
			<s:hidden name="rlgl000103Bean.password" id="password" />
			<s:hidden name="rlgl000103Bean.user_id" id="user_id" />
			<s:hidden name="updateFlg" id="updateFlg" />
			<div id="searchInfo">
				<s:if test="%{user_id.trim() != ''}">
					<s:else>
						<s:hidden name="navigationId" id="navigationId" />
						<my:navigation></my:navigation>
					</s:else>
				</s:if>
				<my:message></my:message>
				<table style="width: 400px"
					class="am-table am-margin am-table-striped am-table-hover am-table-bordered">
					<s:hidden name="user_id" id="user_id_sigle" /> 
					<tbody>
						<s:if test="%{user_id.trim() != ''}"></s:if>
						<s:else>
							<tr>
								<td>用户密码</td>
								<td><s:password id="password_old"
										name="rlgl000103Bean.password_old" cssClass="put"
										maxLength="20" /></td>
							</tr>
						</s:else> 
						<tr>
							<td>新密码</td>
							<td><s:password id="password_new"
									name="rlgl000103Bean.password_new" cssClass="put"
									maxLength="20" /></td>
						</tr>
						<tr>
							<td>新密码确认</td>
							<td><s:password id="password_new_confirm"
									name="rlgl000103Bean.password_new_confirm" cssClass="put"
									maxLength="20" /></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="button" class="am-btn am-btn-danger"
								value="重置密码" name="btnReset" id="btnReset"></td>
						</tr>
					</tbody>
				</table>


			<!--   	<div style="align: center;">
					<table width="100%">
						<tr>
							<td height="10px"></td>
						</tr>
						<tr>
							<td align="center">
								<table width="30%"
									style="position: relative; top: 10px; left: 10px" border="1"
									cellpadding="2" cellspacing="2">

									<s:else>
										<tr>
											<td width="10%" class="lc1" align="left"></td>
											<td width="20%" align="left"></td>
										</tr>
									</s:else>
									<tr>
										<td width="10%" class="lc1" align="left"></td>
										<td width="20%" align="left"></td>
									</tr>
									<tr>
										<td width="10%" class="lc1" align="left"></td>
										<td align="left"></td>
									</tr>
									<tr>
										<td align="center" colspan="2"><input type="button"
											class="inp_L3" value="确认修改" name="btnConfirm" id="btnConfirm">
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>

				</div> 
				-->
			</div>
		</s:form>
		<s:form name="form3" method="post" action="rlgl000103Init"
			nameSpace="/rlgl">
		</s:form>
	</div>
</body>
</html>
