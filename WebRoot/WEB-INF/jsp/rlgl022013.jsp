<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <TITLE>
              日照医学教育
     </TITLE>
<script language="JavaScript">
var boxyContent = "boxy-content-detail";
$(document).ready(function(){
     //支付按钮
     $("#btnPrepay").click(function(){
     
       if($("#paymentNo").val() =="" || $("#paymentMoney").val() =="" )
          {
           alertMessage("AM079");
           return;
          }
         // 线下支付时默认为未核实状态
   		$("#paymentCheck").val("002");
   		$("#rlgl022013").attr("action", "rlgl022012Prepay.action");
   		$("#rlgl022013").submit();
     });
     //切换按钮
     $("#butCutover").click(function(){
         $("#rlgl022013").attr("action", "rlgl022012Init.action");
         $("#rlgl022013").submit();
     });
});
function btnPrepay(user_id)
{
   
}
//详细信息
function detail() {
   $("#backShowFlg").val("1");
   $("#rlgl022013").attr("action", "rlgl022014Init.action");
   $("#rlgl022013").submit();
}
function downLinedetail() {
   $("#backShowFlg").val("1");
   $("#rlgl022013").attr("action", "rlgl022016Init.action");
   $("#rlgl022013").submit();
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
   <body><div>
    <s:form id="rlgl022013" name="rlgl022010Init" method="post" action="rlgl022010Init" nameSpace="/rlgl">
    <s:hidden name="navigationId" id="navigationId"/>
    <s:hidden name="screenId" id="screenId"/>
    <s:hidden name="only_search" id="only_search"/>
    <s:hidden name="backShowFlg" id="backShowFlg"/>
    <s:hidden name="rlgl022012Bean.paymentCheck" id="paymentCheck"/>
    <s:hidden name="type" value="2"/>
    <div id="searchInfo">
    <my:navigation></my:navigation>
    <my:message></my:message>
    <div class="content">
       <div style="position: relative">
        <table width="100%" style="position: relative; top: 10px;" border="1" cellpadding="2" cellspacing="2">
         <caption>线下支付 <input type="button" class="inp_L5 btnClass_${only_search}" value="切换到网银 " name="butCutover" id="butCutover"> </caption>
          <tr>
           <td width="15%" class="lc1" >用户账号</td>
            <td>
            <s:property value="rlgl022012Bean.clum001"/>
            </td>
          </tr>
          <tr>
            <td width="15%" class="lc1">用户名称</td>
            <td>
              <s:property value="rlgl022012Bean.clum002"/>
            </td>
          </tr>
          <tr>
            <td width="15%" class="lc1">身份证号</td>
            <td>
              <s:property value="rlgl022012Bean.clum003"/>
            <br></td>
          </tr>
          <tr>
            <td width="15%" class="lc1">余额</td>
            <td>
               <s:if test="rlgl022012Bean.clum006==''">
                    0.00元
               </s:if>
               <s:else>
                   <s:property value="rlgl022012Bean.clum006"/>
               </s:else>
                <a href="#" onclick="detail()">网银缴费记录</a>
                &nbsp;<a href="#" onclick="downLinedetail()">线下缴费记录</a>
            </td>
          </tr>
          <tr>
              <td width="15%" class="lc1" >交费账单号</td>
	          <td>
	             <s:textfield name="rlgl022012Bean.paymentNo" id="paymentNo"></s:textfield><font color="red"> *</font>
	           </td>
          </tr>
          <tr>
              <td width="15%" class="lc1" >交费金额</td>
	          <td>
	             <s:textfield name="rlgl022012Bean.paymentMoney" id="paymentMoney" onkeyup="clearNoNum(this)"></s:textfield> 元<font color="red"> *</font>
	           </td>
          </tr>
          <tr>
              <td width="15%" class="lc1" >申请发票</td>
	          <td>
	             <s:select name="rlgl022012Bean.clum007" list="applyInvoiceList" listKey="adm_num" listValue="adm_name"  />
	           </td>
          </tr>
          <tr>
            <td align="center" colspan="2">
            <input type="button" class="inp_L3 btnClass_${only_search}" value="提交" name="btnPrepay" id="btnPrepay">
            </td>
          </tr>
        </table>
      </div>
      </div>
  </div>
</s:form>
</div>
  </body>
</html>

