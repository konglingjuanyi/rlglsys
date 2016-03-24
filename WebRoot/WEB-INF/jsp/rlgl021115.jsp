<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" language="javascript"> 
$(document).ready(function(){
     //搜索按钮
     $("#btnSearch").click(function(){
  		 $("audit").val("1");
         $("#form").attr("action", "rlgl021115Search.action");
         $("#form").submit();
     });
      //单位性质级联
      $("#personal_unit_no").change(function(){
          var value=$(this).val();
          var project_no=$("#project_no").val();
          comboxLinkageStructure.url = "SectionAuditCommonAction.action";
          comboxLinkageStructure.nextComboxId = "personal_section_no";
          comboxLinkageStructure.nextComboxName = "";
          comboxLinkageStructure.urlParams =value+","+project_no;
          comboxChanged();
      });
     
     
});
function auditBtn(personal_id,personal_nm,personal_unit_nm,personal_section_name)
{
     $("#project_no1").val($("#project_no").val());
     $("#project_nm1").val($("#project_nm").val());
     $("#personal_section_nm").val(personal_section_name);
     $("#personal_nm").val(personal_nm);
     $("#personal_unit_nm").val(personal_unit_nm);
     $("#personal_id").val(personal_id);
     $("#form").attr("action", "rlgl021116Init.action");
     $("#form").submit();
}
</script>
     <TITLE>
              日照医学教育
     </TITLE>
<script language="JavaScript">
</script>    
</head>
    <body>
    <table cellpadding='0' cellspacing='0' width='100%'>
      <tr>
        <td>
         <!-- 内容显示区域 -->
	      <my:navigation></my:navigation>
	      <my:message></my:message>
	      <div class="content">
          <!-- 数据检索条件 -->  
          <form  id="form" nameSpace="/rlgl" action="doLogin" method="post" >
              <s:hidden name="navigationId" id="navigationId"/>
          	  <s:hidden name="screenId" id="screenId"/>
              <s:hidden name="only_search" id="only_search"/>
              <s:hidden name="rlgl021115Bean.project_no" id="project_no"/>
              <s:hidden name="rlgl021115Bean.project_nm" id="project_nm"/>
              <s:hidden name="rlgl021115Bean.audit_kbn" />
              <s:hidden name="audit" id="audit"/>
              
              <s:hidden name="rlgl021116Bean.project_no" id="project_no1"/>
              <s:hidden name="rlgl021116Bean.project_nm" id="project_nm1"/>
              <s:hidden name="rlgl021116Bean.personal_section_nm" id="personal_section_nm"/>
              <s:hidden name="rlgl021116Bean.project_unit_nm" id="project_unit_nm"/>
              <s:hidden name="rlgl021116Bean.personal_unit_nm" id="personal_unit_nm"/>
               <s:hidden name="rlgl021116Bean.personal_id" id="personal_id"/>
	          <table width="100%" valign="top" border="1px">
	          <caption>查询条件</caption>
	            <tr> 
	              <td class='lc1'>所在单位</td>
	              <td class='lc2'>
	              <s:select name="rlgl021115Bean.personal_unit_no" id="personal_unit_no"  list="unitlist" listKey="personal_unit_no" listValue="personal_unit_nm"  headerValue="--请选择--" headerKey="" /></td>
	              <td class='lc1'>所在科室</td>
	              <td class='lc2'>
	              <s:select name="rlgl021115Bean.personal_section_no" id="personal_section_no"   list="sectionlist" listKey="personal_section_no" listValue="personal_section_name"  headerValue="--请选择--" headerKey="" /></td>
	           </tr>
	           <tr>
	              <td class='lc1'>审核状态</td>
	              <td class='lc2'> <s:select name="rlgl021115Bean.audit_status"   list="auditAdmlist" listKey="adm_num" listValue="adm_name"  headerValue="--请选择--" headerKey="" /></td>
	              <td class='lc1' >用户名称</td>
	              <td class='lc2'><s:textfield  name="rlgl021115Bean.personal_nm"  style="width:150px"/></td>
	            </tr>
	            <tr>
	            <td style="text-align:right" colspan="4"><input class="inp_L3" type="button" name="btn_Interview" value="查询" style="width:80px" id="btnSearch"> 
	             
	            </td>
	            </tr>
	              
	          </table>
                               记录数:&nbsp;<s:property value="recordCount"/>
           
            <div class="content" style="height: 100px;overflow: auto;"  >
            
	        <table width="100%" valign="top" border="1px">
              <tr>
                <th class='thTitleItrn'>序号</th>
                <th class='thTitleItrn'>用户编号</th>
                <th class='thTitleItrn'>用户名称</th>
                <th class='thTitleItrn'>所在单位</th>
                <th class='thTitleItrn'>所在科室</th>
                <th class='thTitleItrn'> 审核状态</th>
                <th width="250" class='thTitleItrn'>用户操作</th>
              </tr>
              <s:iterator value="userList" status='L'>
	              <my:trStripe index="${L.index}">
	                 <td  class="tdc">${L.index +1}</td>
	                 <td class="tbl">
	                    <s:property value='personal_id'/>
	                 </td>
	                 <td  class="tbl" >
	                     <s:property value='personal_nm'/></td>
	                <td  class="tbl" >
	                     <s:property value='personal_unit_nm'/></td>
	                 <td class="tbl" class='tdc' >
	                   <s:if test="personal_section_nm==''"> 
	                                                        无
	                   </s:if>
	                   <s:else>
	                       <s:property value='personal_section_name'/>
	                    </s:else>
	                 </td>
	                  <td class="tbl" class='tdc' >
	                  
	                  
	                   <s:if test="rlgl021115Bean.audit_kbn==1"> 
	                           <s:if test="end_unit_audit==1"> 
			                                                        通过
			                   </s:if>
			                   <s:elseif test="end_unit_audit==2">
			                                                       未通过
			                   
			                   </s:elseif>
			                   <s:else>
			                                                      未审核
			                    </s:else>                            
	                   </s:if>
	                   <s:else>
	                        <s:if test="project_unit_audit==1"> 
			                                                        通过
			                   </s:if>
			                   <s:elseif test="project_unit_audit==2">
			                                                       未通过
			                   </s:elseif>
			                   <s:else>
			                                                      未审核
			                    </s:else>     
	                   </s:else>
	                 </td>
	                  
	                 <td class="tdc" width="10%">
                          <s:if test="end_unit_audit==0 || project_unit_audit==0"> 
	                          </s:if>
	                       <s:if test="rlgl021115Bean.audit_kbn==1"> 
	                           <s:if test="end_unit_audit==0"> 
			                         <input class="inp_L3" type="button"  value="审核" style="width:80px" onclick="auditBtn('<s:property value='personal_id'/>','<s:property value='personal_nm'/>','<s:property value='personal_unit_nm'/>','<s:property value='personal_section_name'/>');" >
			                   </s:if>
			                   <s:else>
			                          <input class="inp_L3" type="button"  value="审核" style="width:80px" disabled="disabled" >
			                    </s:else>                            
	                   </s:if>
	                   <s:else>
	                        <s:if test="project_unit_audit==0"> 
			                         <input class="inp_L3" type="button"  value="审核" style="width:80px" onclick="auditBtn('<s:property value='personal_id'/>','<s:property value='personal_nm'/>','<s:property value='personal_unit_nm'/>','<s:property value='personal_section_name'/>');" >
			                   </s:if>
			                   <s:else>
			                        <input class="inp_L3" type="button"  value="审核" style="width:80px" disabled="disabled" >
			                    </s:else>     
	                   </s:else>
	                 </td>
	              </my:trStripe>
              </s:iterator>
          </table>
          </div>
           </form>
          </div>
        </td>
      </tr>
    </table>
  </body>
</html>