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
     $("#form1").attr("action", "rlgl021120SearchPeople.action");
     $("#form1").attr("namespace", "/rlgl");
     $("#form1").submit();
     }); 
 }); 
 function review(id){
       $("#userIndex").val(id);
	   $("#form1").attr("action", "rlgl021120XueFen.action");
	   $("#form1").attr("namespace", "/rlgl");
	   $("#form1").submit(); 
 }
    


</script>
  </head>
  <body><div>
  <s:form name="form1" id="form1" method="post" action="rlgl090601Search" nameSpace="/rlgl">
    <s:hidden name="navigationId" id="navigationId"/>
    <s:hidden name="screenId" id="screenId"/>
    <s:hidden name="userIndex" id="userIndex"/>
    <s:hidden name="AppNo" id="AppNo"/>
    
    <div id="content">
     <my:navigation></my:navigation>
      <my:message></my:message>
      <div style="position: relative">
        <table width="70%" style="position: relative; top:10px;" border="1" cellpadding="2" cellspacing="2">
          <tr>
            <td class="lc1" width="15%">姓&nbsp;&nbsp;名：</td>
            <td><s:textfield name="personnel_nm" maxLength="12" width="8"/></td>
            <td class="lc1" width="15%">单位名称：</td>
              <td class="lc1" ><s:property value='%{unit_nm}'/></td>
          </tr>
             <tr>
              <td class="lc1" width="15%">身份证号：</td>
                <td><s:textfield id ="personnel_id" name="personnel_id" cssClass="put textidentificationCard" maxLength="18" width="8"/>
             </td>
             <td  class="lc1" align="right" colspan="2">
              <input type="button" class="inp_L3" value="查询" name="btnSearch" id="btnSearch">
            </td>
             </tr>
        </table>
      </div>
      <br>
      <table border="1" width="100%" cellpadding="2" style="position: relative; top:8px;">
        <tr><td colspan=7>
             <my:dividepage actionId="rlgl021120SearchPeople.action"></my:dividepage>
            </td>
         </tr>
        <tr> 
          <th height="28" width="3%" class="thTitleItrn">序号</th>
          <th height="28" width="15%" class="thTitleItrn">身份证号</th>
          <th height="28" width="10%" class="thTitleItrn">姓  名</th>
          <th height="28" width="20%" class="thTitleItrn">项目名称</th>
           <th height="28" width="7%" class="thTitleItrn">角色</th>
          <th height="28" width="7%" class="thTitleItrn">学  分</th>
          <th height="28" width="10%" class="thTitleItrn">操作</th>
        </tr>
       <s:iterator value="peopleList" status="L" id="people">
        <my:trStripe index="${L.index}">
              <td height="20" class="tdc">
              ${L.index +1}
              </td>
              <td height="20" class="tdc">
              <s:property value='personal_card_id'/>
              <s:hidden name="peopleList[%{#L.index}].personal_card_id" value='%{personal_card_id}'></s:hidden>
              </td>
              <td height="20" class="tdc">
              <s:property value='personal_nm'/>
               <s:hidden name="peopleList[%{#L.index}].personal_nm" value='%{personal_nm}'></s:hidden>
               <s:hidden name="peopleList[%{#L.index}].arrly_no" value='%{arrly_no}'></s:hidden>
              </td>
              <td height="20" class="tdc">
              <s:property value='project_nm'/>
               <s:hidden name="peopleList[%{#L.index}].project_no" value='%{project_no}'></s:hidden>
              </td>
             <td height="20" class="tdc">
              <s:property value='juese_nm'/>
               <s:hidden name="peopleList[%{#L.index}].juese_nm" value='%{juese_nm}'></s:hidden>
              </td>
              <td height="20" class="tdc">
               <s:property value='personal_xuefen'/>
               <s:hidden name="peopleList[%{#L.index}].personal_xuefen" value='%{personal_xuefen}'></s:hidden>
              </td>
               <td height="20" class="tdc">
               <input type="button" class="inp_L3" value="授予学分" id="btnxuefen" name="btnxuefen" onclick="review(${L.index})">
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
