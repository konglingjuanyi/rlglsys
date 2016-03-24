<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" language="javascript"> 
$(document).ready(function(){
     //搜索按钮
     $("#btnSearch").click(function(){
         $("#form").attr("action", "rlgl021103Search.action");
         $("#form").submit();
     });
     //保存按钮
     $("#saveRegister").click(function(){
           var apply_item = $("#apply_item option:selected").attr("value");
           var remark=$("#remark").val();
          if(apply_item !="" && remark !="")
          {
            $("#form").attr("action", "rlgl021117SaveRegister.action");
            $("#form").submit();
          }else
          {
             alertMessage("AM079");
          }
        
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
     <s:hidden name="rlgl021113Bean.project_no" id="project_no"/>
     <s:hidden name="rlgl021113Bean.project_nm" id="project_nm"/>
     <s:hidden name="rlgl021113Bean.project_unit_no" id="project_unit_no"/>
     <s:hidden name="rlgl021113Bean.project_unit_nm" id="project_unit_nm"/>
     <s:hidden name="rlgl021112Bean.project_no" />
     <s:hidden name="rlgl021112Bean.project_nm" />
    <div id="searchInfo">
    <my:message></my:message>
    <div class="content">
       <div style="position: relative">
        <table width="100%" style="position: relative; top: 10px;" border="1" cellpadding="2" cellspacing="2">
          <tr>
            <td width="15%" class="lc1">项目编号</td>
            <td>
              <s:property value="rlgl021113Bean.project_no"/>
            </td>
            <td width="15%" class="lc1">项目名称</td>
            <td>
            <s:property value="rlgl021113Bean.project_nm"/>
            </td>
          </tr>
          <tr>
           <td width="15%" class="lc1" >发起单位编号</td>
            <td>
             <s:property value="rlgl021113Bean.project_unit_no"/>
            </td>
            <td width="15%" class="lc1" >发起单位名称</td>
	          <td>
	          <s:property value="rlgl021113Bean.project_unit_nm"/>
	           </td>
          </tr>
          <tr>
           <td width="15%" class="lc1" >补充签到<font color="red">*</font></td>
            <td colspan="3">
              <s:select name="rlgl021113Bean.apply_item" id="apply_item"  list="applyAdmlist" listKey="adm_num" listValue="adm_name"  headerValue="--请选择--" headerKey="" />
            </td>
          </tr>
          <tr>
               <td width="15%" class="lc1" >补充说明<font color="red">*</font></td>
	           <td colspan="3">
	             <s:textarea rows="9" cols="50" name="rlgl021113Bean.remark"  id="remark"></s:textarea>
	           </td>
          </tr>
          <tr> 
            <td align="center" colspan="4">
            <input type="button" class="inp_L3 btnClass_${only_search}" value="提交" name="btnConfirm" id="saveRegister">
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