<!--rlglsyslearnonline-->
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="my" uri="/WEB-INF/navigation.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<link href="${sysUrl}/style/style.css" rel="stylesheet" type="text/css" />
<link href="${sysUrl}/style/common.css" rel="stylesheet" type="text/css" />
<link href="${sysUrl}/style/boxy.css" rel="stylesheet" type="text/css" />
<%
	response.addHeader("P3P", "CP=CAO PSA OUR");
%>
</head>
<body>
	<s:form id="form1" name="form1" method="post" action="rlgl100104Init"
		nameSpace="/rlgl">
		<my:message></my:message>
		<div class="content" style="position: relative">
			<table width="100%" style="position: relative; top: 10px;" border="1"
				cellpadding="2" cellspacing="2">
				<tr>
					<td align="center" colspan="1"><font size="4" color="green"><b>缴费成功！</b></font>
			</table>
		</div>
	</s:form>
</body>
</html>