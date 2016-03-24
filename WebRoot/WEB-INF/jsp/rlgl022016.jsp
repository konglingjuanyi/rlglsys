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
     	 $("#money").val("");
   		 $("#bank_order_no").val("");
         $("#rlgl022016form").attr("action", "rlgl022016Search.action");
         $("#rlgl022016form").submit();
     });
});
//核实
function confirm(listId,bankOrderNo,pid,pcard,punit,pamount,ptime)
{
   $("#money").val($("#prepayRecordList_"+ listId +"__clum008").val());
   $("#bank_order_no").val(bankOrderNo);
   $("#clum015").val(bankOrderNo);
   
   $("#personnelId").val(pid);
   $("#personnelCard").val(pcard);
   $("#personnelUnit").val(punit);
   $("#personnelAmount").val(pamount);
   $("#personnelTime").val(ptime);
   
   $("#rlgl022016form").attr("action", "rlgl022016Search.action");
   $("#rlgl022016form").submit();
   
}
function refundBut(user_id)
{
   $("#user_id").val(user_id);
   $("#rlgl022016form").attr("action", "rlgl022016Init.action");
   $("#rlgl022016form").submit();
   
}
function btnBack(type)
{
   if(type=="1")
   {
	   $("#rlgl022016form").attr("action", "rlgl022012Init.action");
	   $("#rlgl022016form").submit();
   }else
   {
      $("#rlgl022016form").attr("action", "rlgl022013Init.action");
	  $("#rlgl022016form").submit();
   }
   
}
  
	// 只能输入数字
	function clearNoNum(obj) {
		obj.value = obj.value.replace(/[^\d.]/g, ""); //清除“数字”和“.”以外的字符  
		obj.value = obj.value.replace(/^\./g, ""); //验证第一个字符是数字而不是.  
		obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的  
		obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace(
				"$#$", ".");
		obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');//只能输入两个小数  
	}
</script>    
</head>
    <body>
     <my:navigation></my:navigation>
    <table cellpadding='0' cellspacing='0' width='100%'>
      <tr>
        <td> 
        
		      <div class="content">
		      <form id="rlgl022016form" nameSpace="/rlgl" action="doLogin" method="post" >
		        <s:hidden name="navigationId" id="navigationId"/>
		        <s:hidden name="total" id="totalHid"/>
		        <s:hidden name="temp_str" id="temp_str"/>
		        <s:hidden name="rlgl022013Bean.money" id="money"/>
		        <s:hidden name="rlgl022013Bean.bank_order_no" id="bank_order_no"/>
		        <s:hidden name="rlgl022013Bean.clum015" id="clum015"/>
		        <s:hidden name="rlgl022013Bean.user_id" id="user_id"/>
		        <s:hidden name="backShowFlg" id="backShowFlg"/>
		        <s:hidden name="personnelId" id="personnelId"/>
		        <s:hidden name="personnelTime" id="personnelTime"/>
		        <s:hidden name="personnelCard" id="personnelCard"/>
		        <s:hidden name="personnelUnit" id="personnelUnit"/>
		        <s:hidden name="personnelAmount" id="personnelAmount"/>
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
	              <s:if test="%{backShowFlg == 1}">
	              <input class="inp_L3" type="button" name="btn_Interview" value="返回"  onclick="btnBack('<s:property value='type'/>')">
	              </s:if>
	              </td>
	              </tr>
	            </tr>
	            <tr>
	            
	            </tr>
	          </table>
	          <my:dividepage actionId="rlgl022016Search.action"></my:dividepage>
	           <table width="100%"  border="1px">
	           <caption>充值记录列表</caption>
		              <tr>
		                <th class='thTitleItrn'>序号</th>
		                <th class='thTitleItrn'>银行订单号</th>
		                <th class='thTitleItrn'>用户名称</th>
		                <th class='thTitleItrn'>交易金额</th>
		                <th class='thTitleItrn'>交易时间</th>
		                <th class='thTitleItrn'>订单类型</th>
		                
		                <th class='thTitleItrn'>核实状态</th>
		              </tr>
		              
		              <s:iterator value="prepayRecordList" status="L">
			              <my:trStripe index="${L.index}">
			                <td class="tdc">${L.index +1}</td>
			                <td class="tbl" >
			                 <s:property value='clum015'/>
			                  </td>
			                <td class="tbl" ><s:property value='clum007'/></td>
			                 <td class="tbc" style="text-align:center;">
			                       <s:if test="%{rlgl022013Bean.adminFlag == 1}">
			                       <s:if test="%{clum014 == '002'}">
			                     <s:textfield  name="prepayRecordList[%{#L.index}].clum008"  cssClass="put" style="width:70px;text-align:right" maxLength="10" onkeyup="clearNoNum(this)"/>
			                     </s:if>
			                      <s:else>
			                     <s:property value='clum008'/>
			                     </s:else>
			                      </s:if>
			                     <s:else>
			                     <s:property value='clum008'/>
			                     </s:else>
			                       
			                </td>
			                <td class="tdc" style="text-align:center;"><s:property value='clum009'/></td>
			                <td class="tbl" ><s:property value='clum010'/></td>
			                 
			                 
			                <td class="tdl">
			                     <s:property value='paymentCheck'/>
			                     <s:if test="%{rlgl022013Bean.adminFlag == 1}">
			                     <s:if test="%{clum014 == '002'}">
			                     <input type="button" class="inp_L3" onclick="confirm('${L.index}','${clum015}','${clum001}','${clum002}','${clum003}','${clum008}','${clum009}')" value="核实" name="btnConfirm" id="btnConfirm" >
			                     </s:if>
			                    
			                     </s:if>
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
