<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><s:property value="getText('rlglsys.browserhead.IE')" /></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/WdatePicker.js"></script>
<%
response.addHeader("Cache-Control", "no-cache");
%>

<script language="JavaScript">
// 查询按钮的提交
   $(document).ready(function(){
    $("#btnSearch").click(function(){ 
     $("#form1").attr("action", "rlgl021112Search.action");
     $("#form1").attr("namespace", "/rlgl");
     $("#form1").submit();
     });
   }); 
</script>
  </head>
  <body><div>
  <s:form name="form1" id="form1" method="post" action="rlgl021112Search" nameSpace="/rlgl">
    <s:hidden name="navigationId" id="navigationId"/>
    <s:hidden name="screenId" id="screenId"/>
    <div id="content">
     <my:navigation></my:navigation>
      <my:message></my:message>
      <div style="position: relative">
        <table width="70%" style="position: relative; top:10px;" border="1" cellpadding="2" cellspacing="2">
          <tr>
            <td class="lc1">项目名称：<s:textfield name="project_nm" maxLength="10" width="8"/></td>
            <td class="lc1">单位名称：&nbsp;<s:property value='%{unit_nm}'/></td>
          </tr>
             <tr>
             <td  align="right" colspan="2">
              <input type="button" class="inp_L3" value="查询" name="btnSearch" id="btnSearch">
            </td>
             </tr>
        </table>
        <br>
      </div>
      <table border="1" width="100%" cellpadding="2" style="position: relative; top:8px;">
        <tr><td colspan=6>
             <my:dividepage actionId="rlgl021112Search.action"></my:dividepage>
            </td>
         </tr>
        <tr> 
          <th height="28" width="3%" class="thTitleItrn">序号</th>
          <th height="28" width="20%" class="thTitleItrn">项目名称</th>
          <th height="28" width="12%" class="thTitleItrn">授课人</th>
          <th height="28" width="10%" class="thTitleItrn">申请单位</th>
          <th height="28" width="10%" class="thTitleItrn">审核状态</th>
          <th height="28" width="20%" class="thTitleItrn">操  作</th>
        </tr>
       <s:iterator value="shprojectList" status="L" id="course">
        <my:trStripe index="${L.index}">
              <td height="20" class="tdc">
              ${L.index +1}
              </td>
              <td height="20" class="tdc">
              <s:property value='project_nm'/>
              </td>
              <td height="20" class="tdc">
              <s:property value='teach_people'/>
              </td>
              <td height="20" class="tdc">
              <s:property value='unit_nm'/>
              </td>
             <td height="20" class="tdc">
              <s:property value='audit_status_name'/>
              </td>
              <td height="20" class="tdc"><a href="${pageContext.request.contextPath}/rlgl/rlgl02112Detail.action?unitNo=<s:property value='unit_no'/>&AppNo=<s:property value='arrly_no'/>">查  看</a>&nbsp;
                                          <a href="${pageContext.request.contextPath}/rlgl/rlgl02112GoRe.action?unitNo=<s:property value='unit_no'/>&AppNo=<s:property value='arrly_no'/>">进行审核</a>
                 </td>
         </my:trStripe>
       </s:iterator>
        </table>
      </div>
      </s:form>
      <br/>
  </div>
  </body>
</html>
