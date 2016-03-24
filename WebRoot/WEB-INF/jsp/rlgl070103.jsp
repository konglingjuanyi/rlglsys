<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <TITLE>
              日照医学教育
     </TITLE>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/WdatePicker.js"></script>
<script language="JavaScript">
$(document).ready(function(){
  
     //搜索按钮
     $("#btnSearch").click(function(){
         $("#rlgl070103form").attr("action", "rlgl070103Search.action");
         $("#rlgl070103form").submit();
     });
     
});

/* 进入订单查看画面 */
function btnInterview(user_id)
{
   $("#user_id").val(user_id);
   $("#rlgl070103form").attr("action", "rlgl070104Init.action");
   $("#rlgl070103form").submit();
}
</script>    
</head>
    <body>
     <my:navigation></my:navigation>
    <table cellpadding='0' cellspacing='0' width='100%'>
      <tr>
        <td> 
        
		      <div class="content">
		      <form id="rlgl070103form" nameSpace="/rlgl" action="doLogin" method="post" >
		        <s:hidden name="navigationId" id="navigationId"/>
		        <s:hidden name="total" id="totalHid"/>
		        <s:hidden name="user_id" id="user_id"/>
		        <table width="100%" valign="top" border="1px">
	          <caption>查询条件</caption>
	            <tr> 
	              <td class='lc1' >用户编号</td>
	              <td class='lc2'><s:textfield  name="rlgl070103Bean.user_id"   cssClass="put" maxLength="20" /></td>
	              <td class='lc1' >用户名称</td>
	              <td class='lc2'><s:textfield  name="rlgl070103Bean.user_name"  cssClass="put" maxLength="50" /></td>
	              <td  align="right"><input class="inp_L3" type="button" name="btn_Interview" value="查询" style="width:80px" id="btnSearch">
	              </td>
	            </tr>
	            <tr>
	            
	            </tr>
	          </table>
	          <my:dividepage actionId="rlgl022009Search.action"></my:dividepage>
	           <table width="100%"  border="1px">
	            <caption>用户列表</caption>
		              <tr>
		                <th class='thTitleItrn'>序号</th>
		                <th class='thTitleItrn'>用户编号</th>
		                <th class='thTitleItrn'>用户名称</th>
		                <th class='thTitleItrn'>所在单位名称</th>
		                <th class='thTitleItrn'>所在科室</th>
		                 <th class='thTitleItrn'>操作</th>
		              </tr>
		              
		              <s:iterator value="applyInvoiceUserList" status="L">
			              <my:trStripe index="${L.index}">
			                <td class="tdc">${L.index +1}</td>
			                <td class='tbl' ><s:property value='user_id'/></td>
			                 <td class='tbl'  >
			                       <s:property value='user_name'/>
			                </td>
			                <td class='tbl' ><s:property value='unit_nm'/></td>
			                <td class='tbl' ><s:property value='section_name'/></td>
			                 <td class='tdc'  >
			                     <input class="inp_L3" type="button" name="btn_Interview" value="订单查看"  onclick="btnInterview('<s:property value='user_id'/>')"></input>
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
