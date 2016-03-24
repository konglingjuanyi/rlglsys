<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <TITLE>
              日照医学教育
     </TITLE>
<script language="JavaScript">

// 判断缴费是否成功
function checkFlg(){

   if ($("#flg").val() != null) {
       if ($("#flg").val() == "1") {
           $("#payFailure").show();
	       $("#bg2").show();
	   }
	   if ($("#flg").val() == "0") {
           $("#paySuccess").show();
	       $("#bg3").show();
	   }   
   }
}
   
$(document).ready(function(){
     checkFlg();
     //提交
     $("#btnPrepay").click(function(){
         var merchantId = document.getElementById("MerchantID").value;
         var transactionId = document.getElementById("TransactionID").value;
         var amount = document.getElementById("Amount").value;
         var signature = document.getElementById("Signatures").value;
         var merchantUrl = document.getElementById("MerchantURL").value;
         var merchantURLReturn = document.getElementById("MerchantURLReturn").value;
          $("#pay").show();

	     // $("#bg1").height($(".content").height());
	      $("#bg1").show();
	      
	       // 通过ajax后台编辑数据
           learnOnline.url = "paydataInsert.action";
		   learnOnline.pageObjectId = "ajxFlg";
		   // 课程ID、课程提供方、画面标记、用户ID、系统网址
		   learnOnline.urlParams = merchantId +"," + transactionId + "," + amount + ","+ signature + ","+ merchantUrl+","+ merchantURLReturn;
		   paydataInsert();
		   
         // var sFeatures = "height=700, width=900, scrollbars=no, resizable=no,location=no,status=no";  
	     // window.open("http://pay.sdecp.com.cn/PaymentGateway?MerchantID="+merchantId+"&TransactionID="+transactionId+"&Amount="+amount+"&MerchantURL="+merchantUrl+"&Signature="+signature,"3km",sFeatures);
         //window.open("http://pay.sdecp.com.cn/PaymentGateway?MerchantID="+merchantId+"&TransactionID="+transactionId+"&Amount="+amount+"&MerchantURL="+merchantUrl+"&Signature="+signature);
     });
     
     $("#btnReturn").click(function(){
    	 $("#reg_form").attr("action", "rlgl100101Init.action");
		 $("#reg_form").submit();
     });
     
     $("#overDiv").click(function()
     {
         $("#reg_form").attr("action", "rlgl100101Select.action");
		 $("#reg_form").submit();
         $("#pay").hide();
         $("#bg1").hide();
     });
     
     $("#closeDiv").click(function()
     {
     	 $("#TransactionID").val("");
         $("#pay").hide();
         $("#bg1").hide();
         $("#reg_form").attr("action", "rlgl100101Prepay.action");
		 $("#reg_form").submit();
     });
     $("#closeDiv2").click(function()
     {
         $("#payFailure").hide();
         $("#bg2").hide();
         
     });
     $("#closeDiv3").click(function()
     {
         $("#paySuccess").hide();
         $("#bg3").hide();
         $("#btnPrepay").attr("disabled",true);
     });
});

</script>    
</head>
   <body><div>
    <s:form name="reg_form" id="reg_form" method="post" action="http://user.sdecpay.com/decpay.com/paygate.html">
    <s:hidden name="MerchantID" id="MerchantID"/>
    <s:hidden name="TransactionID" id="TransactionID"/>
    <s:hidden name="Amount" id="Amount"/>
    <s:hidden name="Succeed" id="Succeed"/>
    <s:hidden name="Signatures" id="Signatures"/>
    <s:hidden name="ajxFlg" id="ajxFlg"/>
    <s:hidden name="flg" id="flg"/>
    <s:hidden name="MerchantURL" id="MerchantURL"/>
    <s:hidden name="MerchantURLReturn" id="MerchantURLReturn"/>
    <div id="searchInfo">
    <my:navigation></my:navigation>
    <my:message></my:message>
    <div class="content">
       <div style="position: relative">
        <table width="100%" style="position: relative; top: 10px;" border="1" cellpadding="2" cellspacing="2">
         <tr>
            <td width="15%" class="lc1">商户名</td>
            <td>
              <s:property value="MerchantNM"/>
            </td>
          </tr>
          <tr>
            <td width="15%" class="lc1">交易订单号</td>
            <td>
              <s:property value="TransactionID"/>
            </td>
          </tr>
          <tr>
            <td width="15%" class="lc1">订单交易金额</td>
            <td>

              <s:if test="%{Amount == 'ERROR'}">
					 <font color="red">数据异常</font>
					 </s:if>
	                 <s:else>
	                 <s:property value='Amount'/>
	                 </s:else>
            </td>
          </tr>
 
          <tr>
            <td align="center" colspan="2">
             <input type="button" class="inp_L3 btnClass_${only_search}" value="确定" name="btnPrepay" id="btnPrepay">
             <input type="button" class="inp_L3 btnClass_${only_search}" value="返回" name="btnReturn" id="btnReturn">
            </td>
          </tr>
        </table>
      </div>
      </div>
  </div>
</s:form>
</div>
 <!-- 显示支付层 引用-->
  <jsp:include page="./paydiv.jsp"></jsp:include>
  <!-- 显示支付失败层 引用-->
  <jsp:include page="./payFailurediv.jsp"></jsp:include>
  <!-- 显示支付成功层 引用-->
  <jsp:include page="./paySuccessdiv.jsp"></jsp:include>
  </body>
</html>