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
     $("#form1").attr("action", "rlgl021120Init.action");
     $("#form1").attr("namespace", "/rlgl");
     $("#form1").submit();
     });
   }); 
   
/*   function jubanwangcheng(id){
       $("#userIndex").val(id);
	   $("#form1").attr("action", "rlgl021120SearchPeople.action");
	   $("#form1").attr("namespace", "/rlgl");
	   $("#form1").submit(); 
 } */

</script>
  </head>
  <body><div>
  <s:form name="form1" id="form1" method="post" action="rlgl090601Search" nameSpace="/rlgl">
    <s:hidden name="navigationId" id="navigationId"/>
    <s:hidden name="screenId" id="screenId"/>
    <s:hidden name="userIndex" id="userIndex"/>
    <div id="content">
     <my:navigation></my:navigation>
      <my:message></my:message>
      <div style="position: relative">
        <table width="70%" style="position: relative; top:10px;" border="1" cellpadding="2" cellspacing="2">
          <tr>
            <td class="lc1">项目名称：<s:textfield name="project_nm" maxLength="10" width="8"/></td>
            <td class="lc1">单位名称：&nbsp;<s:property value='%{mtb91Info.unit_nm}'/></td>
          </tr>
             <tr>
             <td  class="lc1" align="right" colspan="2">
              <input type="button" class="inp_L3" value="查询" name="btnSearch" id="btnSearch">
            </td>
             </tr>
        </table>
      </div>
      <br>
      <table border="1" width="100%" cellpadding="2" style="position: relative; top:8px;">
        <tr><td colspan=6>
             <my:dividepage actionId="rlgl021120Init.action"></my:dividepage>
            </td>
         </tr>
        <tr> 
          <th height="28" width="3%" class="thTitleItrn">序号</th>
          <th height="28" width="12%" class="thTitleItrn">项目编号</th>
          <th height="28" width="20%" class="thTitleItrn">项目名称</th>
          <th height="28" width="10%" class="thTitleItrn">授课人</th>
          <th height="28" width="10%" class="thTitleItrn">结束时间</th>
          <th height="28" width="10%" class="thTitleItrn">操作</th>
        </tr>
       <s:iterator value="projectList" status="L" id="course">
        <my:trStripe index="${L.index}">
              <td height="20" class="tdc">
              ${L.index +1}
              <s:hidden  name="projectList[%{#L.index}].arrly_no" value='%{arrly_no}'></s:hidden>
              </td>
              <td height="20" class="tdc">
              <s:property value='project_no'/>
              <s:hidden  name="projectList[%{#L.index}].project_no" value='%{project_no}'></s:hidden>
              </td>
              <td height="20" class="tdc">
              <s:property value='project_nm'/>
              <s:hidden  name="projectList[%{#L.index}].project_nm" value='%{project_nm}'></s:hidden>
              <s:hidden  name="projectList[%{#L.index}].student_credit" value='%{student_credit}'></s:hidden>
              </td>
              <td height="20" class="tdc">
               <s:property value='teach_people'/>
               <s:hidden  name="projectList[%{#L.index}].teach_people" value='%{teach_people}'></s:hidden>
               <s:hidden name="projectList[%{#L.index}].teacher_credit" value='%{teacher_credit}'></s:hidden>
              </td>
              <td height="20" class="tdc">
              <s:property value='teach_end_date'/>
              <s:hidden  name="projectList[%{#L.index}].teach_end_date" value='%{teach_end_date}'></s:hidden>
              </td>
               <td height="20" class="tdc">
               <%-- <input type="button" class="inp_L3" value="举办完成" id="btnJuBan" name="btnJuBan" onclick="jubanwangcheng(${L.index})"> --%>
               <a href="${pageContext.request.contextPath}/rlgl/rlgl021120SearchPeople.action?AppNo=<s:property value='arrly_no'/>">举办完成</a>
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
