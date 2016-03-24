<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
   response.addHeader("Cache-Control", "no-cache");
%>
<title>山东卫生人力管理平台</title>
<script type="text/javascript">
var topFlg="1";
var isSubmitted = false;
</script>
</head>
<s:hidden name="screenId" id="screenId"/>
<%-- <s:textfield name="flg" id="flg"/> --%>
    <frameset rows="40,*" frameborder="no" framespacing="0">
    <frame name="navi" src="naviInit_jn.action" frameborder="0" scrolling="no" noresize>
      <frameset name="pageframe" id="pageframe" cols="180,*" frameborder="no">
         <frame name="coursware" src="coursware_jn_2.action" frameborder="0" scrolling="auto" noresize>
         <frame name="contents1" src="coursInit_jn_2.action" frameborder="0" scrolling="auto" noresize>
      </frameset>
  </frameset>
</html>
