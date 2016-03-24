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
response.addHeader("P3P","CP=CAO PSA OUR");
%>
</head>
 <body>
  <s:form id="form1" name="form1" method="post" action="rlgl100104Init" nameSpace="/rlgl">
    <s:hidden name="navigationId" id="navigationId"/>
    <s:hidden name="screenId" id="screenId"/>
    <s:hidden name="only_search" id="only_search"/>
    <s:hidden name="page_flg" id="page_flg"/>
    <my:navigation></my:navigation>
    <my:message></my:message>
    <div class="content" style="position: relative">
        <table width="100%" style="position: relative; top: 10px;" border="1" cellpadding="2" cellspacing="2">
          <tr>
            <td align="center" colspan="1">
            <s:if test="%{page_flg == 'rlgl100104'}">
                <s:if test="%{pass != '0'}">
	                <font size="4" color="green"><b>考试顺利通过！</b></font>
	            </s:if>
	            <s:else>
	                <font size="4" color="red"><b>本次考试没有通过，请补考！</b></font>
	            </s:else>
            </s:if>
                <s:if test="%{page_flg == 'rlgl100113'}">
                <s:if test="%{pass != '0'}">
	                <font size="4" color="green"><b>学习顺利通过！</b></font>
	            </s:if>
	            <s:else>
	                <font size="4" color="red"><b>本次学习没有通过请再次学习！</b></font>
	            </s:else>
            </s:if>
            <s:elseif test="%{page_flg == 'rlgl100107'}">
	            <s:if test="%{pass != '0'}">
	                <font size="4" color="green"><b>考试顺利通过！</b></font>
	            </s:if>
	            <s:else>
	                <font size="4" color="red"><b>本次考试没有通过，请补考！</b></font>
	            </s:else>
            </s:elseif>
            <s:elseif test="%{page_flg == 'rlgl022008'}">
	            <s:if test="%{pass != '0'}">
	                <font size="4" color="green"><b>考试顺利通过！</b></font>
	            </s:if>
	            <s:else>
	                <font size="4" color="red"><b>本次考试没有通过，请补考！</b></font>
	            </s:else>
            </s:elseif>
            <s:else>
	            <font size="4" color="red"><b>本画面课程您只有学习权限，请在已选课程画面学习并提交成绩！</b></font>
            </s:else>
            </td>
          </tr>
          <tr>
            <td align="center" colspan="1">
            <s:if test="%{page_flg == 'rlgl100107'}">
                <h4 style="color:red;">提示：</h4>如果考试通过，公共课考试相应的课程会显示已通过
            </s:if>
            <s:else>
	            <h4 style="color:red;">提示：</h4>如果考试通过，请到学习并通过考试课程中查看通过考试的课程，如果没有通过，请重新学习
            </s:else>              
            </td>
          </tr>
        </table>
      </div>
  </s:form>
</body>
</html>