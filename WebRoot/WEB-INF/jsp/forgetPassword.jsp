<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="global.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<link href="${pageContext.request.contextPath}/style/login.css"
	rel="stylesheet" type="text/css" />
<script language="JavaScript">
	$(function() {
		// 登录按钮押下
		$("#btnLoginCs").click(function() {
			if (Trim($("#user_id1").val()) == "") {
				alertMessage("AM039");
				$("#user_id1").val(Trim($("#user_id1").val()));
			}

			if (Trim($("#email").val()) == "") {
				alertMessage("AM050");
				$("#email").val(Trim($("#email").val()));
			}
			
			$("#form1").attr("action", "Rlgl022110Init.action");
			$("#form1").submit();
		});
	});
</script>
</head>
<body style="text-align: center;">
	<div class="loginpage4">
		<h3>
			<img id="imgTitle" class="tit" />
		</h3>
		<img src="${pageContext.request.contextPath}/images/login/su.png"
			class="su" />
		<div class="login">
			<div class="loginL">
				<img src="${pageContext.request.contextPath}/images/login/logo.png" />
				<p>密码修改</p>
			</div>
			<div class="loginR">
				<s:form nameSpace="/rlgl" id="form1" method="post">
					<s:hidden name="PageFlg1" value = "forgetPassword" />
					<label style="font-size: 14px;"><span>身份证</span></label>
					<s:textfield name="user_id" cssClass="login_input" maxLength="20"
						id="user_id1" />
					<br />
					<br />
					<label style="font-size: 14px;"><span>邮&nbsp;&nbsp;&nbsp;箱</span></label>
					<s:textfield name="email" cssClass="login_input" maxLength="20"
						id="email" />
					<br />
					<br />
					<br />
					<a id="btnLoginCs" class="log login_1" href="#">确定</a>
				</s:form>
			</div>
		</div>
		<div class="footer">
			<div class="foot">
				<!--<div class="footL">
        		<div class="com">
                	<p>中国电信集团系统</p> 
					<p>集成有限责任公司山东分公司</p>
                </div>
                <img src="${pageContext.request.contextPath}/images/login/dianxin.png"/>
            </div>-->
				<div class="footR">
					<p>CopyRight 2013 All Rights reserved</p>
				</div>
			</div>
		</div>
	</div>
</body>