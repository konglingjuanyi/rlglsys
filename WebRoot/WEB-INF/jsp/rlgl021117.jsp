<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" language="javascript"> 
$(document).ready(function(){
     //搜索按钮
     $("#btnSearch").click(function(){
  
         $("#form").attr("action", "rlgl021117Search.action");
         $("#form").submit();
     });
     //保存按钮
     $("#saveApply").click(function(){
         $("#form").attr("action", "rlgl021101SaveApply.action");
         $("#form").submit();
     });
     //判断是否选中（修改报名项）
     $(".comcheckbox").click(function(){
          if($(this).attr("checked")==true)
          {
	          var value=$(this).parent().prev().children().val();
	          if(value=="")
	          {
	            alertMessage("AM073");
	            $(this).attr("checked",false);
	          }
         }
             var sum=0;
             $("input[id='select_kbn_check']").each(function(idx, obj) {
             if(this.checked){
               sum+=parseInt(1);
              }
            });
             butdis(sum);
         
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
function register(projectNo,projectNm,unitNo,unitNm)
{
  $("#project_no").val(projectNo);
  $("#project_nm").val(projectNm);
  $("#project_unit_no").val(unitNo);
  $("#project_unit_nm").val(unitNm);
  $("#form").attr("action", "rlgl021117Register.action");
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
              <s:hidden name="unit_no" id="unit_no"/>
              <s:hidden name="only_search" id="only_search"/>
              <s:hidden name="rlgl021113Bean.project_no" id="project_no"/>
              <s:hidden name="rlgl021113Bean.project_nm" id="project_nm"/>
              <s:hidden name="rlgl021113Bean.project_unit_no" id="project_unit_no"/>
              <s:hidden name="rlgl021113Bean.project_unit_nm" id="project_unit_nm"/>
	          <table width="100%" valign="top" border="1px">
	          <caption>查询条件</caption>
	            <tr> 
	              <td class='lc1' sytle="">项目编号</td>
	              <td class='lc2'><s:textfield  name="rlgl021112Bean.project_no"  style="width:150px"/></td>
	              <td class='lc1'>项目名称</td>
	              <td class='lc2'><s:textfield  name="rlgl021112Bean.project_nm"  style="width:150px"/></td>
	              <td><input class="inp_L3" type="button" name="btn_Interview" value="查询" style="width:80px" id="btnSearch"></td>
	               </tr>
	            <tr>
	            
	            </tr>
	          </table>
           <div id="result" style="position: relative;top:20px">
            <my:dividepage actionId="rlgl021103Search.action"></my:dividepage>
	        <table width="100%" valign="top" border="1px">
              <tr>
                <th class='thTitleItrn'>序号</th>
                <th class='thTitleItrn'>项目编号</th>
                <th class='thTitleItrn'>项目名称</th>
                 <th class='thTitleItrn'>发起单位</th>
                <th class='thTitleItrn'>讲授地点</th>
                <th class='thTitleItrn'>讲授开始日期</th>
                <th class='thTitleItrn'>总学时</th>
                <th width="100" class='thTitleItrn'>选择</th>
              </tr>
              <s:iterator value="hospitalCreditProjectList" status='L'>
                   <s:hidden name="hospitalCreditProjectList[%{#L.index}].project_no" id="project_no"/>
                   <s:hidden name="hospitalCreditProjectList[%{#L.index}].project_nm" id="project_nm"/>
                   <s:hidden name="hospitalCreditProjectList[%{#L.index}].arrly_no" id="arrly_no"/>
                   <s:hidden name="hospitalCreditProjectList[%{#L.index}].unit_no" id="unit_no"/>
	              <my:trStripe index="${L.index}">
	                 <td  class="tdc">${L.index +1}</td>
	                 <td class="tbl">
	                    <s:property value='project_no'/>
	                 </td>
	                 <td  class="tbl" >
	                     <s:property value='project_nm'/></td>
	                <td  class="tbl" >
	                     <s:property value='unit_nm'/></td>
	                 <td class="tbl" class='tdc' >
	                   <s:if test="teach_place==''"> 
	                                                        无
	                   </s:if>
	                   <s:else>
	                       <s:property value='teach_place'/>
	                    </s:else>
	                 </td>
	                 <td  class="tbl" width="20%">
	                     <s:property value='teach_start_date'/>
	                 </td>
	                 <td class="tbl" >
	                   <s:if test="total_hours==''"> 
	                                                        无
	                   </s:if>
	                   <s:else>
	                       <s:property value='total_hours'/>
	                    </s:else>
	                 </td>
	                  <td class="tdc" >
	                    <s:if test="arrly_kbn==0">
                                <input class="inp_L3" type="button" name="btn_Interview" value="签到"  id="saveApply" onclick="register('<s:property value='project_no'/>','<s:property value='project_nm'/>','<s:property value='unit_no'/>','<s:property value='unit_nm'/>')" >
                        </s:if>
                           <s:else>
                                                                                        已签到
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