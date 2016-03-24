<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <TITLE>
              日照医学教育
     </TITLE>
<script language="JavaScript">
$(document).ready(function(){
  
     //搜索按钮
     $("#btnSearch").click(function(){
         $("#rlgl022009form").attr("action", "rlgl022009Search.action");
         $("#rlgl022009form").submit();
     });
});
function refundBut(user_id)
{
   $("#user_id").val(user_id);
   $("#rlgl022009form").attr("action", "rlgl022010Init.action");
   $("#rlgl022009form").submit();
   
}
</script>    
</head>
    <body>
     <my:navigation></my:navigation>
    <table cellpadding='0' cellspacing='0' width='100%'>
      <tr>
        <td> 
        
		      <div class="content">
		      <form id="rlgl022009form" nameSpace="/rlgl" action="doLogin" method="post" >
		        <s:hidden name="navigationId" id="navigationId"/>
		        <s:hidden name="total" id="totalHid"/>
		        <s:hidden name="temp_str" id="temp_str"/>
		        <s:hidden name="rlgl022009Bean.user_id" id="user_id"/>
		         <table width="100%" valign="top" border="1px">
	          <caption>查询条件</caption>
	            <tr> 
	              <td class='lc1'>用户名称</td>
	              <td class='lc2'><s:textfield  name="rlgl022009Bean.user_name"  style="width:150px"/></td>
	              <td class='lc1' sytle="">身份证号</td>
	              <td class='lc2'><s:textfield  name="rlgl022009Bean.personnel_id"  style="width:150px"/></td>
	              <td><input class="inp_L3" type="button" name="btn_Interview" value="查询" style="width:80px" id="btnSearch"></td>
	               </tr>
	            <tr>
	            
	            </tr>
	          </table>
	          <my:dividepage actionId="rlgl022009Search.action"></my:dividepage>
	           <table width="100%"  border="1px">
	           <caption>退费用户列表</caption>
		              <tr>
		                <th class='thTitleItrn'>序号</th>
		                <th class='thTitleItrn'>用户编号</th>
		                <th class='thTitleItrn'>用户名称</th>
		                <th class='thTitleItrn'>身份证号</th>
		                <th class='thTitleItrn'>办公电话</th>
		                <th class='thTitleItrn'>所在单位</th>
		                <th class='thTitleItrn'>所在科室</th>
		                <th class='thTitleItrn'>操作</th>
		              </tr>
		              
		              <s:iterator value="userList" status="L">
			              <my:trStripe index="${L.index}">
			                <td class="tdc">${L.index +1}</td>
			                <td class='tbl' >
			                 <s:property value="user_id"/>
			                  </td>
			                <td class='tbl' ><s:property value='user_name'/></td>
			                 <td class='tbl'  >
			                       <s:property value="personnel_id"/>
			                </td>
			                <td class='tbl' ><s:property value='personnel_officetel'/></td>
			                <td class='tbl' ><s:property value='unit_no'/></td>
			                 
			                 <td class='tdc'  >
			                     <s:property value='section_id'/>
			                </td>
			                <td class='tdc'  >
		 						<input class="inp_L3 btnClass_${only_search}" type="button" id="butLearn" value="退费" onclick="refundBut('<s:property value='user_id'/>');"/>
			                </td>
			              </my:trStripe>
		              </s:iterator>
		          </table>
	             </form>
	             </div>
        </td>
      </tr>
    </table>
  </body>
</html>
