<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" language="javascript"> 
$(document).ready(function(){
     //搜索按钮
     $("#btnSearch").click(function(){
         $("#form").attr("action", "rlgl021110Search.action");
         $("#form").submit();
     });
     
});
function auditBut(project_no,project_nm)
{
   $("#project_no").val(project_no);
    $("#project_nm").val(project_nm);
   $("#form").attr("action", "rlgl021110Audit.action");
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
              <s:hidden name="project_no" id="project_no"/>
              <s:hidden name="project_nm" id="project_nm"/>
              <s:hidden name="only_search" id="only_search"/>
	          <table width="100%" valign="top" border="1px">
	          <caption>查询条件</caption>
	            <tr> 
	              <td class='lc1' sytle="">项目编号</td>
	              <td class='lc2'><s:textfield  name="rlgl021110Bean.project_no"  style="width:150px"/></td>
	              <td class='lc1'>项目名称</td>
	              <td class='lc2'><s:textfield  name="rlgl021110Bean.project_nm"  style="width:150px"/></td>
	              <td style="text-align:right" > <input class="inp_L3" type="button" name="btn_Interview" value="查询" style="width:80px" id="btnSearch">
	              </td>
	               </tr>
	          </table>
           <div id="result" style="position: relative;top:20px">
            <my:dividepage actionId="rlgl021104Search.action"></my:dividepage>
	        <table width="100%" valign="top" border="1px">
              <tr>
                <th class='thTitleItrn'>序号</th>
                <th class='thTitleItrn'>项目编号</th>
                <th class='thTitleItrn'>项目名称</th>
                <th class='thTitleItrn'>发起单位</th>
                <th class='thTitleItrn'>讲授地点</th>
                <th class='thTitleItrn'>讲授开始日期</th>
                <th class='thTitleItrn'>总学时</th>
                <th class='thTitleItrn'>签到人数</th>
                <th width="100" class='thTitleItrn'>用户操作</th>
              </tr>
              <s:iterator value="ProjectList" status='L'>
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
	                 <td  class="tbl">
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
	                  <td class="tbl" >
	                     <s:property value='sumnum'/>
	                  </td>
	                 <td class="tdc" width="10%">
	                   <input type="button" class="inp_L3 btnClass_${only_search}" value="审核"  onclick="auditBut('<s:property value='project_no'/>','<s:property value='project_nm'/>')"/>
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