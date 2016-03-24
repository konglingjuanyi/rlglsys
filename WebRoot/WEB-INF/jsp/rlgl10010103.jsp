<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="global.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<TITLE>山东省卫生人力资源管理系统</TITLE>
</head>
<body>
	<div>
		  <s:form name="reg_form" id="reg_form" method="post"
			 action="#">
			 <!--  action="http://user.sdecpay.com/decpay.com/paygate.html"> -->
			 
			<div id="searchInfo">
				<my:navigation></my:navigation>
				<my:message></my:message>
				<table style="width:400px"
					class="am-table am-margin am-table-striped am-table-hover am-table-bordered">

					<tbody>
						<tr>
							<td >用户名</td>
							<td ><s:property value="Name" /></td>
						</tr>
						<tr>
							<td>账户余额</td>
							<td><s:property value='Amount' /></td>
					</tbody>
					</table>
			</div>
		</s:form>
	</div>
	 <div class="am-panel am-panel-danger am-margin">
         <header class="am-panel-hd">
    <h3 class="am-panel-title">Tips</h3>
  </header>
  <p class =" am-padding am-danger">

   <font color="red">备注：</font></P>
   
    <p>1、如果您打开余额界面之后缴费的，请关闭余额界面，再重新打开查询余额。</p>
    
		
		</div>
</body>
</html>