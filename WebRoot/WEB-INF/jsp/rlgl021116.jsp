<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" language="javascript"> 
$(document).ready(function(){
     //返回
     $("#btnBack").click(function(){
         $("#form").attr("action", "rlgl021116AuditBack.action");
         $("#form").submit();
     });
     //不通过按钮
     $("#auditNBTN").click(function(){
         $("#project_unit_audit").val("2");
         //修改项目发起单位审核状态
         $("#form").attr("action", "rlgl021116AuditRegister.action");
         $("#form").submit();
     }); 
     //通过按钮
     $("#auditYBTN").click(function(){
         $("#project_unit_audit").val("1");
          //修改项目发起单位审核状态
         $("#form").attr("action", "rlgl021116AuditRegister.action");
         $("#form").submit();
     }); 
});
//控制按钮是否变灰
function butdis(sum)
{
   if(sum>0)
   {
      $("#saveApply").attr("disabled",false);
   }
   else
   {
      $("#saveApply").attr("disabled",true);
   }
}
</script>
     <TITLE>
              日照医学教育
     </TITLE>
<script language="JavaScript">
</script>    
</head>
   <body><div>
  <s:form id="form" method="post" action="rlgl090101Init" nameSpace="/rlgl">
    <s:hidden name="navigationId" id="navigationId"/>
    <s:hidden name="screenId" id="screenId"/>
    <s:hidden name="only_search" id="only_search"/>
     <s:hidden name="rlgl021116Bean.project_unit_audit" id="project_unit_audit"/>
    <s:hidden name="rlgl021116Bean.personal_id" />
    <s:hidden name="rlgl021116Bean.project_no" />
    <s:hidden name="rlgl021116Bean.project_nm" />
     <s:hidden name="rlgl021116Bean.audit_kbn"/>
     <s:hidden name="rlgl021116Bean.apply_item1"/>
     <s:hidden name="rlgl021116Bean.personal_card_id"/>
     <s:hidden name="rlgl021115Bean.project_nm" />
     <s:hidden name="rlgl021115Bean.personal_unit_no" />
      <s:hidden name="rlgl021115Bean.personal_section_no" />
      <s:hidden name="rlgl021115Bean.audit_status" />
      <s:hidden name="rlgl021115Bean.personal_nm" />
    <div id="searchInfo">
    <my:message></my:message>
    <div class="content">
       <div style="position: relative">
        <table width="100%" style="position: relative; top: 10px;" border="1" cellpadding="2" cellspacing="2">
         <tr>
            <td width="15%" class="lc1">用户编号</td>
            <td>
              <s:property value="rlgl021116Bean.personal_id"/>
            </td>
            <td width="15%" class="lc1">用户名称</td>
            <td>
            <s:property value="rlgl021116Bean.personal_nm"/>
            </td>
          </tr>
          <tr>
            <td width="15%" class="lc1">项目编号</td>
            <td>
              <s:property value="rlgl021116Bean.project_no"/>
            </td>
            <td width="15%" class="lc1">项目名称</td>
            <td>
            <s:property value="rlgl021116Bean.project_nm"/>
            </td>
          </tr>
          <tr>
           <td width="15%" class="lc1" >所在单位</td>
            <td>
             <s:property value="rlgl021116Bean.personal_unit_nm"/>
            </td>
           <td width="15%" class="lc1" >所在科室</td>
            <td>
             <s:property value="rlgl021116Bean.personal_section_nm"/>
            </td>
          </tr>
          <tr>
           <td width="15%" class="lc1" >补充签到</td>
            <td colspan="3">
            <s:property value="rlgl021116Bean.apply_item"/>
            </td>
          </tr>
          <tr>
               <td width="15%" class="lc1" >补充说明</td>
	           <td colspan="3">
	                <s:property value="rlgl021116Bean.remark"/>
	           </td>
          </tr>
          <tr> 
            <td align="center" colspan="4">
            <input class="inp_L3" type="button" name="btn_Interview" value="通过" style="width:80px" id="auditYBTN" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        <input class="inp_L3" type="button" name="btn_Interview" value="不通过" style="width:80px" id="auditNBTN" >
	        <input class="inp_L3" type="button" name="btn_Interview" value="返回" style="width:80px" id="btnBack" >
            </td>
          </tr>
        </table>
      </div>
      </div>
  </div>
</s:form>
<s:form name="form3" method="post" action="rlgl090101Init" nameSpace="/rlgl">
</s:form>
</div>
  </body>
</html>