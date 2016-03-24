<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <TITLE>
              日照医学教育
     </TITLE>
<script language="JavaScript">
$(document).ready(function(){
  
     //切换按钮
     $("#butCutover").click(function(){
         $("#rlgl022010").attr("action", "rlgl022013Init.action");
         $("#rlgl022010").submit();
     });
     //提交
     $("#btnPrepay").click(function(){
        if($("#clum006").val() =="" )
          {
           alertMessage("AM079");
           return;
          }
          
         $("#Amount").val($("#clum006").val());
         $("#invoice").val($("#clum007").val());
         $("#pay").show();

	     $("#bg1").show();
	     
	     // 通过ajax后台编辑数据
       learnOnline.url =  "insertPrepaydata.action";
	   learnOnline.pageObjectId = "Signatures";
	   // 课程ID、课程提供方、画面标记、用户ID、系统网址
	   learnOnline.urlParams = $("#MerchantID").val() +"," + $("#TransactionID").val() + "," + $("#Amount").val() + ","+ $("#MerchantURL").val()+ ","+ $("#invoice").val();
	   paydataPrepay();
     });
     
     $("#overDiv").click(function()
     {
         $("#TransactionID").val("");
         $("#rlgl022010").attr("action", "rlgl022012Init.action");
		 $("#rlgl022010").submit();
         $("#pay").hide();
         $("#bg1").hide();
     });
     
     $("#closeDiv").click(function()
     {
         $("#TransactionID").val("");
         $("#rlgl022010").attr("action", "rlgl022012Init.action");
		 $("#rlgl022010").submit();
         $("#pay").hide();
         $("#bg1").hide();
         
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

//详细信息
function detail() {
   $("#backShowFlg").val("1");
   $("#rlgl022010").attr("action", "rlgl022014Init.action");
   $("#rlgl022010").submit();
}
function downLinedetail() {
   $("#backShowFlg").val("1");
   $("#rlgl022010").attr("action", "rlgl022016Init.action");
   $("#rlgl022010").submit();
}
</script>    
</head>
   <body><div>
    <s:form id="rlgl022010"  method="post" action="rlgl022010Init" nameSpace="/rlgl">
    <s:hidden name="MerchantID" id="MerchantID"/>
    <s:hidden name="TransactionID" id="TransactionID"/>
    <s:hidden name="Amount" id="Amount"/>
    <s:hidden name="invoice" id="invoice"/>
    <s:hidden name="Succeed" id="Succeed"/>
    <s:hidden name="Signatures" id="Signatures"/>
    <s:hidden name="ajxFlg" id="ajxFlg"/>
    <s:hidden name="flg" id="flg"/>
    <s:hidden name="backShowFlg" id="backShowFlg"/>
    <s:hidden name="MerchantURL" id="MerchantURL"/>
    
    <s:hidden name="navigationId" id="navigationId"/>
    <s:hidden name="screenId" id="screenId"/>
    <s:hidden name="only_search" id="only_search"/>
    <s:hidden name="type" value="1"/>
    <div id="searchInfo">
    <my:navigation></my:navigation>
    <my:message></my:message>
    <div class="content">
       <div style="position: relative">
        <table width="100%" style="position: relative; top: 10px;" border="1" cellpadding="2" cellspacing="2">
         <caption>网银支付  <input type="button" class="inp_L5 btnClass_${only_search}" value="切换到线下" name="butCutover" id="butCutover">  </caption>
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
              <td width="15%" class="lc1" >交费金额</td>
	          <td>
	             <s:textfield id="clum006" name="rlgl022012Bean.paymentMoney"></s:textfield> 元<font color="red"> *</font>
	           </td>
          </tr>
         <tr>
              <td width="15%" class="lc1" >申请发票</td>
	          <td>
	             <s:select id="clum007" name="rlgl022012Bean.clum007" list="applyInvoiceList" listKey="adm_num" listValue="adm_name"  />
	           </td>
          </tr>
          <tr>
            <td align="center" colspan="2">
            <input type="button" class="inp_L3 btnClass_${only_search}" value="交费" name="btnPrepay" id="btnPrepay">
            备注：系统网银支付暂不支持华夏银行、浦发银行和兴业银行！
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

