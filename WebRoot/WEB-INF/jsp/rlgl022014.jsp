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
         $("#rlgl022014form").attr("action", "rlgl022014Search.action");
         $("#rlgl022014form").submit();
     });
     
});
function refundBut(user_id)
{
   $("#user_id").val(user_id);
   $("#rlgl022014form").attr("action", "rlgl022014Init.action");
   $("#rlgl022014form").submit();
   
}
function btnBack(type)
{
   if(type=="1")
   {
	   $("#rlgl022014form").attr("action", "rlgl022012Init.action");
	   $("#rlgl022014form").submit();
   }else
   {
      $("#rlgl022014form").attr("action", "rlgl022013Init.action");
	  $("#rlgl022014form").submit();
   }
   
}
</script>    
</head>
    <body>
     <my:navigation></my:navigation>
    <table cellpadding='0' cellspacing='0' width='100%'>
      <tr>
        <td> 
        
		      <div class="content">
		      <form id="rlgl022014form" nameSpace="/rlgl" action="doLogin" method="post" >
		        <s:hidden name="navigationId" id="navigationId"/>
		        <s:hidden name="total" id="totalHid"/>
		        <s:hidden name="temp_str" id="temp_str"/>
		        <s:hidden name="rlgl022013Bean.user_id" id="user_id"/>
		         <table width="100%" valign="top" border="1px">
	          <caption>查询条件</caption>
	            <tr>
	              <td class='lc1' >开始日期</td>
	              <td class='lc2'><s:textfield  name="rlgl022013Bean.startDate"   cssClass="put" maxLength="20" onClick="WdatePicker()"/></td>
	              <td class='lc1' >截止日期</td>
	              <td class='lc2'><s:textfield  name="rlgl022013Bean.endDate"  cssClass="put" maxLength="20" onClick="WdatePicker()"/></td>
	              </tr>
	              <tr>
	              <td colspan="4" align="right"><input class="inp_L3" type="button" name="btn_Interview" value="查询" style="width:80px" id="btnSearch">
	              <input class="inp_L3" type="button" name="btn_Interview" value="返回"  onclick="btnBack('<s:property value='type'/>')">
	              
	              </td>
	              </tr>
	            </tr>
	            <tr>
	            
	            </tr>
	          </table>
	          <my:dividepage actionId="rlgl022014Search.action"></my:dividepage>
	           <table width="100%"  border="1px">
	           <caption>充值记录列表</caption>
		              <tr>
		                <th class='thTitleItrn'>序号</th>
		                <th class='thTitleItrn'>银行订单号</th>
		                <th class='thTitleItrn'>用户名称</th>
		                <th class='thTitleItrn'>交易金额</th>
		                <th class='thTitleItrn'>交易时间</th>
		                <th class='thTitleItrn'>订单类型</th>
		                <th class='thTitleItrn'>订单摘要</th>
		              </tr>
		              
		              <s:iterator value="prepayRecordList" status="L">
			              <my:trStripe index="${L.index}">
			                <td class="tdc">${L.index +1}</td>
			                <td class="tbl" >
			                 <s:property value='clum015'/>
			                  </td>
			                <td class="tbl" ><s:property value='clum007'/></td>
			                 <td class="tbc" style="text-align:center;" ><s:property value='clum008'/></td>
			                <td class="tbc" style="text-align:center;"><s:property value='clum009'/></td>
			                <td class="tbl" ><s:property value='clum010'/></td>
			                 <td class="tdl"  >
			                     <s:property value='clum011'/>
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
