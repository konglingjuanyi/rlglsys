<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
   response.addHeader("Cache-Control", "no-cache");
%>
<title>日照医学教育</title>
<link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon" />
<script type="text/javascript">
    var topFlg="1";
    var isSubmitted = false;
</script>
</head>
<s:hidden name="screenId" id="screenId"/>
<frameset rows="52,*" frameborder="no" framespacing="0">
    <frame name="identity" src="headInit.action" frameborder="0" scrolling="no" noresize>
    <frameset name="pageframe" id="pageframe" cols="250,*" frameborder="no">
      <frame name="menu" src="menuInit.action" frameborder="0" scrolling="auto" noresize>
      <frame name="contents" id="contents" src="contents.action" frameborder="0" scrolling="auto" noresize>
    </frameset>
  </frameset>
</html>
