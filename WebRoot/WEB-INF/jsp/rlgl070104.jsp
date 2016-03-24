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
         $("#rlgl070104form").attr("action", "rlgl070104Search.action");
         $("#rlgl070104form").submit();
     });
      //返回
         $("#btnBack").click(function(){
         $("#rlgl070104form").attr("action", "rlgl070103Search.action");
	     $("#rlgl070104form").submit();
     });
      
});
// 打印发票
function btnInterview(user_id,order_no)
{
   $("#userId").val(user_id);
   $("#orderNo").val(order_no);
   $("#rlgl070104form").attr("action", "rlgl070104Detail.action");
   $("#rlgl070104form").submit();
   
}
</script>    
</head>
    <body>
     <my:navigation></my:navigation>
		<div class="content">
		  <form id="rlgl070104form"  action="" method="post"   nameSpace="/rlgl">
		        <s:hidden name="navigationId" id="navigationId"/>
		        <s:hidden name="total" id="totalHid"/>
		        <s:hidden name="clum001" id="userId"/>
		        <s:hidden name="clum005" id="orderNo" />
		        <s:hidden name="rlgl070103Bean.user_id" />
		        <s:hidden name="rlgl070103Bean.user_name" />
		      <table width="100%" valign="top" border="1px">
	          <caption>查询条件</caption>
	            <tr> 
	              <td class='lc1' >订单编号</td>
	              <td class='lc2'><s:textfield  name="rlgl070104Bean.clum005"   cssClass="put" maxLength="20" /></td>
	              <td class='lc1' >用户编号</td>
	              <td class='lc2'><s:textfield  name="rlgl070104Bean.clum001"  cssClass="put" maxLength="50" value="%{rlgl070104Bean.clum001}" readonly="true"/></td>
	            </tr>
	            <tr>
	             <td class='lc1' >订单类型</td>
	             <td class='lc2'>
	                  <s:select name="rlgl070104Bean.clum008" list="OrderKbnAdmlist" listKey="adm_num" listValue="adm_name"  headerValue="--请选择--" headerKey="" />	              
	             </td>
	             <td class='lc1' >打印状态</td>
	             <td class='lc2'>
	                  <s:select name="rlgl070104Bean.clum009" list="PrintStatusAdmlist" listKey="adm_num" listValue="adm_name"  headerValue="--请选择--" headerKey="" />	              
	             </td>
	            </tr>
	            <tr> 
	              <td class='lc1' >订单提交区间</td>
	              <td class='lc2' colspan="4" ><s:textfield  name="rlgl070104Bean.startDate"   cssClass="put" maxLength="20" onClick="WdatePicker()"/> 至  
	                  <s:textfield  name="rlgl070104Bean.endDate"   cssClass="put" maxLength="20" onClick="WdatePicker()"/></td>
	            </tr>
	            <tr>
		             <td colspan="4" align="right"><input class="inp_L3" type="button" name="btn_Interview" value="查询" style="width:80px" id="btnSearch"></input>
		             <input class="inp_L3" type="button"  value="返回"  id="btnBack" ></input>
		             </td>
	            </tr>
	          </table>
	          <my:dividepage actionId="rlgl022009Search.action"></my:dividepage>
	           <table style="width:100% " border="1px">
		              <caption>发票打印列表</caption>
		              <tr>
		                <th class='thTitleItrn'>序号</th>
		                <th class='thTitleItrn'>订单编号</th>
		                <th class='thTitleItrn'>用户账号</th>
		                <th class='thTitleItrn'>提交时间</th>
		                <th class='thTitleItrn'>订单类型</th>
		                <th class='thTitleItrn'>打印状态</th>
		                <th class='thTitleItrn'>操作</th>
		              </tr>
		              
		              <s:iterator value="applyInvoiceList" status="L">
			              <my:trStripe index="${L.index}">
			                <td class="tdc">${L.index +1}</td>
			                <td class='tbl' ><s:property value='clum005'/></td>
			                <td class='tbl' ><s:property value='clum006'/></td>
			                <td class='tbl' ><s:property value='clum007'/></td>
			                <td class='tbl' ><s:property value='clum008'/></td>
			                <td class='tbl' ><s:property value='clum009'/></td>
			                <td class='tdc' > 
			                     <input class="inp_L3" type="button" name="btn_Interview" value="打印发票"  onclick="btnInterview('<s:property value='clum001'/>','<s:property value='clum005'/>')"></input>
			                </td>
			              </my:trStripe>
		              </s:iterator>
		          </table>
	             </form>
	             </div>
  </body>
</html>
