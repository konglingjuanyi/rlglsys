<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="global.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	response.addHeader("Cache-Control", "no-cache");
%>
<title>IFREAMTEST</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/head.js">
	
</script> 
</head>

<body>
	<!--[if lte IE 9]>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
  以获得更好的体验！</p>
<![endif]-->
	<header class="am-topbar admin-header">
	<div class="am-topbar-brand">
		<strong>日照医学教育</strong><small>---<s:property value="unitName" /></small>
	</div> 
	<div class="am-collapse am-topbar-collapse" id="topbar-collapse">

		<ul
			class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
			<li><a href="http://www.rzyxjy.cn/webquestions.html" target="_blank"><span class="am-icon-envelope-o"></span>
					常见问题解答</a>
					</li>
			<li class="am-dropdown" data-am-dropdown><a
				class="am-dropdown-toggle" data-am-dropdown-toggle
				href="javascript:;"> <span class="am-icon-users"></span><s:property value="user_name" />（<s:property value="user_id" />） <span
					class="am-icon-caret-down"></span>
			</a>
				<ul class="am-dropdown-content">
				<li><a   href="http://www.rzyxjy.cn/webhelp.html" target="_blank"><span class="am-icon-user"></span> 使用帮助</a></li>
				<li><a    href="http://www.rzyxjy.cn/support.html" target="_blank"><span class="am-icon-user"></span> 技术支持</a></li>
					<li><a  id="passChangeBtn" href="#" ><span class="am-icon-user"></span> 修改密码</a></li>
					<li><a id="reLoginBtn" href="#"><span class="am-icon-cog"></span> 重新登录</a></li>
					<li><a id="loginOutBtn" href="#"><span class="am-icon-power-off"></span> 退出</a></li>
				</ul></li> 
				<li><a id="hideMenu" href="#"><span class="am-icon-envelope-o"></span>
					隐藏左侧菜单</a>
					</li> 
			<li class="am-hide-sm-only"><a href="javascript:;"
				id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span
					class="admin-fullText">开启全屏</span></a></li>
		</ul>
	</div>
	</header> 
</body>
</html>
