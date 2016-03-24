<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" language="javascript"> 
$(document).ready(function(){
    // 全选
    $("#btnSelectAll").click(function(){
        var sum=0;
        $("input[id='select_kbn_check']").each(function(idx, obj) {
          this.checked = true;
           sum+=parseInt(1);
         });
        butdis(sum);
    });
    // 反选
    $("#btnSelectNo").click(function(){
        var sum=0;
        $("input[id='select_kbn_check']").each(function(idx, obj) {
          this.checked = false;
           sum+=parseInt(1);
         });
         butdis("0");
    });
     //单个选择
    $(".comcheckbox").click(function(){
        var sum=0;
        $("input[id='select_kbn_check']").each(function(idx, obj) {
          if(this.checked){
            sum+=parseInt(1);
           }
         });
	         butdis(sum);
    });
     //搜索按钮
     $("#btnSearch").click(function(){
  		 $("audit").val("1");
         $("#form").attr("action", "rlgl021105Search.action");
         $("#form").submit();
     });
     //不通过按钮
     $("#auditNBTN").click(function(){
         $("#audit").val("2");
         $("#form").attr("action", "rlgl021105Audit.action");
         $("#form").submit();
     }); 
     //通过按钮
     $("#auditYBTN").click(function(){
         $("#audit").val("1");
         $("#form").attr("action", "rlgl021105Audit.action");
         $("#form").submit();
     }); 
      //单位性质级联
      $("#personal_unit_no").change(function(){
          var value=$(this).val();
          var project_no=$("#project_no").val();
          alert(project_no);
          comboxLinkageStructure.url = "SectionArrlyCommonAction.action";
          comboxLinkageStructure.nextComboxId = "personal_section_no";
          comboxLinkageStructure.nextComboxName = "";
          comboxLinkageStructure.urlParams =value+","+project_no;
          comboxChanged();
      });
     
     
});
//控制按钮是否变灰
function butdis(sum)
{
   if(sum>0)
   {
      $("#auditYBTN").attr("disabled",false);
      $("#auditNBTN").attr("disabled",false);
      
   }
   else
   {
      $("#auditYBTN").attr("disabled",true);
      $("#auditNBTN").attr("disabled",true);
   }
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
              <s:hidden name="rlgl021105Bean.project_no" id="project_no"/>
              <s:hidden name="audit" id="audit"/>
	          <table width="100%" valign="top" border="1px">
	          <caption>查询条件</caption>
	            <tr> 
	              <td class='lc1'>所在单位</td>
	              <td class='lc2'>
	              <s:select name="rlgl021105Bean.personal_unit_no" id="personal_unit_no"  list="unitlist" listKey="personal_unit_no" listValue="personal_unit_nm"  headerValue="--请选择--" headerKey="" /></td>
	              <td class='lc1'>所在科室</td>
	              <td class='lc2'>
	              <s:select name="rlgl021105Bean.personal_section_no" id="personal_section_no"   list="sectionlist" listKey="personal_section_no" listValue="personal_section_name"  headerValue="--请选择--" headerKey="" /></td>
	           </tr>
	           <tr>
	              <td class='lc1'>审核状态</td>
	              <td class='lc2'> <s:select name="rlgl021105Bean.audit_status"   list="auditAdmlist" listKey="adm_num" listValue="adm_name"  headerValue="--请选择--" headerKey="" /></td>
	              <td class='lc1' >用户名称</td>
	              <td class='lc2'><s:textfield  name="rlgl021105Bean.personal_nm"  style="width:150px"/></td>
	            </tr>
	            <tr>
	            <td style="text-align:right" colspan="4"><input class="inp_L3" type="button" name="btn_Interview" value="查询" style="width:80px" id="btnSearch"> <input type="button" class="inp_L3" value="全选" name="btnSelectAll" id="btnSelectAll">
	                <input type="button" class="inp_L3" value="反选" name="btnSelectNo" id="btnSelectNo">
	                <input class="inp_L3" type="button" name="btn_Interview" value="通过" style="width:80px" id="auditYBTN" disabled>
	                <input class="inp_L3" type="button" name="btn_Interview" value="不通过" style="width:80px" id="auditNBTN" disabled>
	            </td>
	            </tr>
	              
	          </table>
                               记录数:&nbsp;<s:property value="recordCount"/>条
           
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
                 <s:hidden name="userList[%{#L.index}].personal_unit_no" id="personal_unit_no"/>
                 <s:hidden name="userList[%{#L.index}].personal_id" id="personal_id"/>
                 <s:hidden name="userList[%{#L.index}].project_no" id="project_no"/>
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
	                   <s:if test="audit_status==1"> 
	                                                        通过
	                   </s:if>
	                   <s:elseif test="audit_status==2">
	                                                       未通过
	                   
	                   </s:elseif>
	                   <s:else>
	                                                      未审核
	                    </s:else>
	                 </td>
	                  
	                 <td class="tdc" width="10%">
                          <s:checkbox cssClass="comcheckbox" name="ishere" id="select_kbn_check"  fieldValue="%{#L.index }"  />  
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