<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<!-- <link href="/rlglsys/style/style.css" rel="stylesheet" type="text/css" />
<link href="/rlglsys/style/common.css" rel="stylesheet" type="text/css" />
<link href="/rlglsys/style/boxy.css" rel="stylesheet" type="text/css" />  -->
 <link rel="alternate icon" type="image/png" href="/rlglsys/assets/i/favicon.png">
  <link rel="stylesheet" href="/rlglsys/assets/css/amazeui.min.css"/> 
  <link rel="stylesheet" href="assets/css/admin.css">
  
<script type="text/javascript" src="/rlglsys/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="/rlglsys/js/jquery-1.2.6.pack.js"></script>
<!--<script type="text/javascript" src="/rlglsys/js/jquery.boxy.js"></script>-->
<script type="text/javascript" src="/rlglsys/js/common.js"></script>



</head>
<!-- <body>
    <div id="loading" style="position:absolute;left:0;width:100%;height:100%;top:0;background:#FFFFFF;opacity:0.1;filter:alpha(opacity=10);Z-index:9999;">
    </div>
    <div id="loading2" style="position:absolute;cursor:wait;left:50%;top:50%;padding:0;color:#000;Z-index:9999;">
    <img src="/rlglsys/images/pagination_loading.gif"/></div>
</body>  -->

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="assets/js/jquery.min.js"></script>
<!--<![endif]-->
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/app.js"></script>
</body>

<head>
<title></title>
<meta charset="UTF-8">
<title>Login Page | Amaze UI Example</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />

<style>
.header {
	text-align: center;
}

.header h1 {
	font-size: 200%;
	color: #333;
	margin-top: 30px;
}

.header p {
	font-size: 14px;
}
</style>


<!--  <link href="/rlglsys/style/login.css"
	rel="stylesheet" type="text/css" />
<link rel="icon"
	href="/rlglsys/images/favicon.ico"
	type="image/x-icon" />
<link rel="shortcut icon"
	href="/rlglsys/images/favicon.ico"
	type="image/x-icon" /> -->
<!-- 超链接的点击事件 -->
<script language="JavaScript">
	$(function() {
		$("#escrow_unit_nm").click(function() {
			openUnitSelectWindow("sort=4");
		});
		$("#btnReg").click(function() {
			if (Trim($("#user_id").val()) == "") {
				alertMessage("AM010");
				//alert('AM010');
				$("#user_id").val(Trim($("#user_id").val()));
				return false;
			}
			var reg = /(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
			if (reg.test(Trim($("#user_id").val())) === false) {
				alertMessage("AM006");
				$("#user_id").val(Trim($("#user_id").val()));
				return false;
			}
			if (Trim($("#user_name").val()) == "") {
				alertMessage("AM009");
				$("#user_name").val(Trim($("#user_name").val()));
				return false;
				
			}
			if (Trim($("#expend_01").val()) == "") {
				alertMessage("请填写正确的手机号");
				$("#expend_01").val(Trim($("#expend_01").val()));
				return false;
				
			}
			if (Trim($("#password").val()) == "") {
				alertMessage("AM031");
				$("#password").val(Trim($("#password").val()));
				return false;

			}
			if (Trim($("#password").val()) != Trim($("#password2").val())) {
				alertMessage("AM030");
				$("#password2").val("");
				return false;
			}
			if (Trim($("#escrow_unit_nm").val()) == "") {
				alertMessage("AM094");
				$("#escrow_unit_nm").val(Trim($("#escrow_unit_nm").val()));
				return false;

			}
			if (Trim($("#personnel_email").val()) == "") {
				alertMessage("AM121");
				$("#personnel_email").val(Trim($("#personnel_email").val()));
				return false;

			}

			$("#form").attr("action", "doregiter.action");
			$("#form").submit();
		});

		// 返回按钮
		$("#btnReturn").click(function() {
			$("#returnFlg").val("1");
			$("#form2").attr("action", "topMenu.action");
			$("#form2").get(0).submit();
			return false;
		});
	});

	// 【单位查询】按钮的返回事件
	function callBackFun_unitSelect(unitInfo) {
		$("#unit_no").val(unitInfo.unit_no);
		$("#escrow_unit_nm").val(unitInfo.unit_nm);
	};
	function setUnitValue(value){
		 
		var start=value.indexOf('(');
		var end=value.indexOf(')');
		var unitNo=value.substring(start+1,end);
		var unitName=value.substring(0,start);
		 
		alert(unitNo);
		$("#unit_no").val(unitNo);
		$("#escrow_unit_nm").val(unitName);
	}
</script>

</head>

<body>
	<div class="header">
		<div class="am-g">
			<h1>日照医学教育网-用户注册</h1> 
			<p>提高卫生计生专业技术人员素质，促进医学科学技术发展!</p>
		</div>
		<hr />
	</div>
	<div class="am-g">
		<div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
<form id="form2" name="form2" action="/rlglsys/doLogin.action" method="post" nameSpace="/rlgl">
						<input type="hidden" name="returnFlg" value="" id="returnFlg"/>
					</form>



  <form id="form" name="form" action="/rlglsys/rlglregiter.action" method="post" nameSpace="/rlgl">

	
			 	<div class="am-form">
			 	<label >单位编码:</label>
				<input type="" name="unit_no"   id="unit_no"  />
				<br>
				<br>
				<label for="user_id">身份证号:</label>
				<input type="" name="user_id" maxlength="20" value="" id="user_id" class="login_input"/>（<span style="color: red">必填</span>，请输入18位身份证号）
				<br>
				<br>
				 <label for="user_name">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label> 
				<input type="" name="user_name" maxlength="20" value="" id="user_name" class="login_input"/>（<span style="color: red">必填</span>，请输入与身份证一致的姓名【汉字】）
				<br> 
				<br>
				
				<label for="password">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</label>
				<input type="" name="password" maxlength="20" id="password" class="login_pass"/>（<span style="color: red">必填</span>）
				<br>
				<br>
				 <label for="expend_01">手机号码:</label>
				<input type="" name="expend_01" maxlength="20" id="expend_01" class="login_input"/>（<span style="color: red">必填</span>）
				<br>
				<br>
				 <label for="password2">确认密码:</label>
				<input type="" name="password2" maxlength="20" id="password2" class="login_input"/>（<span style="color: red">必填</span>）
				<br>
				<br>
				 <label for="personnel_email">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱:</label>
				<input type="" name="personnel_email" value="" id="personnel_email" class="login_input"/>（<span style="color: red">必填</span>，找回密码用）
				<br>
				<br>
				 <label for="escrow_unit_nm">所在单位:</label>
				<input type="" value="<%=request.getParameter("city") %>" name="escrow_unit_nm" maxlength="18" value="" readonly="readonly" id="escrow_unit_nm" class="login_input" va/>
				<br />
				<br>
				<br>
				 
				<div class="am-cf">
					<input id="btnReg" type="submit" name="" value="注册"
						class="am-btn am-btn-primary am-btn-sm am-fl"> &nbsp;
						<input id="btnReturn" type="submit" name="" value="退出"
						class="am-btn am-btn-danger am-btn-sm am-fl"> 
						<a class="chin" href="http://rzyxjy.cn/webquestions.html"><input
						 name="" value="在线帮助 "
						class="am-btn am-btn-default am-btn-sm am-fr"></a>
						<br>
						<br>
						<br>
				</div>
				</div>
		</form>




			<hr>
			<p>© 2016 AllMobilize, Inc. Licensed under MIT license.</p>
		</div>

		<div class="loginpage4">
			 
			 
			<div class="reigst">
				 
				<div class="loginR">
				 
					
				</div>
			</div>
		</div>
		<script type="text/javascript">
			setUnitValue('<%=request.getParameter("city") %>');
		</script>
</body>

</html>