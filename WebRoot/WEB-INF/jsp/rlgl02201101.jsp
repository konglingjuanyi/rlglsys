<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <TITLE>
              日照医学教育
     </TITLE>
<script language="JavaScript">

   
$(document).ready(function(){
     //checkFlg();
     //提交
     $("#btnPrepay").click(function(){
    	 //alert("我敢保证，你现在用的是演示一");
//          var merchantId = document.getElementById("MerchantID").value;
//          var transactionId = document.getElementById("TransactionID").value;
//          var amount = document.getElementById("Amount").value;
//          var signature = document.getElementById("Signatures").value;
//          var merchantUrl = document.getElementById("MerchantURL").value;
//           $("#pay").show();

// 	     // $("#bg1").height($(".content").height());
// 	      $("#bg1").show();
	      
// 	       // 通过ajax后台编辑数据
//            learnOnline.url = "paydataInsert.action";
// 		   learnOnline.pageObjectId = "ajxFlg";
// 		   // 课程ID、课程提供方、画面标记、用户ID、系统网址
// 		   learnOnline.urlParams = merchantId +"," + transactionId + "," + amount + ","+ signature + ","+ merchantUrl;
// 		   paydataInsert();
         $("#reg_form").attr("action", "Rlgl022110Init.action");
		 $("#reg_form").submit();
		   
         // var sFeatures = "height=700, width=900, scrollbars=no, resizable=no,location=no,status=no";  
	     // window.open("http://pay.sdecp.com.cn/PaymentGateway?MerchantID="+merchantId+"&TransactionID="+transactionId+"&Amount="+amount+"&MerchantURL="+merchantUrl+"&Signature="+signature,"3km",sFeatures);
         //window.open("http://pay.sdecp.com.cn/PaymentGateway?MerchantID="+merchantId+"&TransactionID="+transactionId+"&Amount="+amount+"&MerchantURL="+merchantUrl+"&Signature="+signature);
     });
     
//      $("#btnReturn").click(function(){
//     	 if($("#PageFlg").val()=='001')
//     	 { 
//     	 $("#reg_form").attr("action", "Rlgl022001101Init.action");
// 		 $("#reg_form").submit();
// 		 }
    	
//      });
     
//      $("#overDiv").click(function()
//      {
//          $("#reg_form").attr("action", "rlgl100101Select.action");
// 		 $("#reg_form").submit();
//          $("#pay").hide();
//          $("#bg1").hide();
//      });
     
//      $("#closeDiv").click(function()
//      {
//      	 $("#TransactionID").val("");
//          $("#pay").hide();
//          $("#bg1").hide();
//          $("#reg_form").attr("action", "rlgl100101Prepay.action");
// 		 $("#reg_form").submit();
//      });
//      $("#closeDiv2").click(function()
//      {
//          $("#payFailure").hide();
//          $("#bg2").hide();
         
//      });
//      $("#closeDiv3").click(function()
//      {
//          $("#paySuccess").hide();
//          $("#bg3").hide();
//          $("#btnPrepay").attr("disabled",true);
//      });
});

</script>    
</head>
   <body><div>
    <s:form name="reg_form" id="reg_form" method="post" action="http://user.sdecpay.com/decpay.com/paygate.html" namespace="/">
    <s:hidden name="PageFlg1" id="PageFlg1"/>
    <s:hidden name="course_name1" id="course_name1"/>
    <s:hidden name="course_id1" id="course_id1"/>
    <s:hidden name="Amount1" id="Amount1"/>
    <div id="searchInfo">
    <my:navigation></my:navigation>
    <my:message></my:message>
    <div class="content">
       <div style="position: relative">
        <table width="100%" style="position: relative; top: 10px;" border="1" cellpadding="2" cellspacing="2">
         <tr>
            <td width="15%" class="lc1">课件编号</td>
            <td>
              <s:property value="course_id1"/>
            </td>
          </tr>
          <tr>
            <td width="15%" class="lc1">课件名称</td>
            <td>
              <s:property value="course_name1"/>
            </td>
          </tr>
          <tr>
            <td width="15%" class="lc1">交费金额</td>
            <td>

              <s:if test="%{Amount == 'ERROR'}">
					 <font color="red">数据异常</font>
					 </s:if>
	                 <s:else>
	                 <s:property value='Amount1'/>
	                 </s:else>
            </td>
          </tr>
 
          <tr>
            <td align="center" colspan="2">
             <input type="button" class="inp_L3 btnClass_${only_search}" value="确定" name="btnPrepay" id="btnPrepay">
<%--              <input type="button" class="inp_L3 btnClass_${only_search}" value="返回" name="btnReturn" id="btnReturn"> --%>
            </td>
          </tr>
        </table>
      </div>
      </div>
  </div>
</s:form>
</div>
 <%-- <!-- 显示支付层 引用--> 
  <jsp:include page="./paydiv.jsp"></jsp:include>
  <!-- 显示支付失败层 引用--> 
  <jsp:include page="./payFailurediv.jsp"></jsp:include>
  <!-- 显示支付成功层 引用--> 
  <jsp:include page="./paySuccessdiv.jsp"></jsp:include> --%>
  </body>
</html>