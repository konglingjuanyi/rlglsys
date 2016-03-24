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
  $("#select_kbn_check").click(function(){
          if(this.checked){
            $("#total").html("150.00");
            $("#Amount").val("150.00");
           }
           else
           {
             $("#total").html($("#totalHid").val());
             $("#Amount").val($("#totalHid").val());
           }
		   // 通过ajax后台编辑数据
           learnOnline.url = "paudataEdit.action";
		   learnOnline.pageObjectId = "Signatures";
		   // 课程ID、课程提供方、画面标记、用户ID、系统网址
		   learnOnline.urlParams = $("#MerchantID").val() +"," + $("#TransactionID").val() + "," + $("#Amount").val() + ","+ $("#MerchantURL").val();
		   paydataEdit();
    });
     $("#butNowCourse").click(function(){
     	 // 余额支付
     	 $("#payFlg").val("1");
     	 $("#rlgl022006form").attr("action", "rlgl022007Save.action");
		 $("#rlgl022006form").submit();
     });
     $("#butAddCourse").click(function(){
     	 // 网银支付
         var merchantId = document.getElementById("MerchantID").value;
         var transactionId = document.getElementById("TransactionID").value;
         var amount = document.getElementById("Amount").value;
         var signature = document.getElementById("Signatures").value;
         var merchantUrl = document.getElementById("MerchantURL").value;
         $("#pay").show();

	     $("#bg1").show();
	     
	     // 通过ajax后台编辑数据
         learnOnline.url = "paydataInsert.action";
		 learnOnline.pageObjectId = "ajxFlg";
		 // 课程ID、课程提供方、画面标记、用户ID、系统网址
		 learnOnline.urlParams = merchantId +"," + transactionId + "," + amount + ","+ signature + ","+ merchantUrl;
		 paydataInsert();
         //window.open("http://pay.sdecp.com.cn/PaymentGateway?MerchantID="+merchantId+"&TransactionID="+transactionId+"&Amount="+amount+"&MerchantURL="+merchantUrl+"&Signature="+signature);
     });
     
     $("#overDiv").click(function()
     {
         $("#rlgl022006form").attr("action", "rlgl022007Select.action");
		 $("#rlgl022006form").submit();
         $("#pay").hide();
         $("#bg1").hide();
     });
     
     $("#closeDiv").click(function()
     {
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
         $("#butAddCourse").attr("disabled",true);
     });
});
//控制按钮是否变灰
function addCourse()
{
    //$("#rlgl022006form").attr("action", "rlgl022007Save.action");
    //$("#rlgl022006form").submit();
   
}

//返回页面
function goBack() {
   $("#rlgl022006form").attr("action", "rlgl022005Init.action");
   $("#rlgl022006form").submit();
}
</script>    
</head>
    <body>
     <my:navigation></my:navigation>
     <my:message></my:message>
    <table cellpadding='0' cellspacing='0' width='100%'>
      <tr>
        <td> 
             <div class="content">
               <table width='100%'>
                <tr>
                <td align="left">
                 <s:if test="courseNum==1">
                   <span style="color: red" >  重要提示：您选够5个课程可享受150元打包优惠价。</span> 
               </s:if>
               <s:else>
                    <span style="color: red" >  重要提示：您没有选够5个课程不可享受150元打包优惠价。</span> 
               </s:else>
                </td>
                <td align="right" >
                     <input class="inp_L3" type="button" value="返回" onclick="goBack()"/>&nbsp;&nbsp;
                </td>
                </tr>
              
              </table>
               
		     </div>
		      <div class="content" style="height: 180px;overflow: auto;">
		      <form id="rlgl022006form" nameSpace="/rlgl" action="doLogin" method="post" >
		       <s:hidden name="navigationId" id="navigationId"/>
		       <s:hidden name="total" id="totalHid"/>
		       <s:hidden name="MerchantID" id="MerchantID"/>
			   <s:hidden name="TransactionID" id="TransactionID"/>
			   <s:hidden name="Amount" id="Amount"/>
			   <s:hidden name="Succeed" id="Succeed"/>
			   <s:hidden name="Signatures" id="Signatures"/>
			   <s:hidden name="MerchantURL" id="MerchantURL"/>
			   <s:hidden name="ajxFlg" id="ajxFlg"/>
			   <s:hidden name="payFlg" id="payFlg"/>
			   <s:hidden name="nowMoney" id="nowMoney"/>
	           <table width="100%"  border="1px">
	            <caption>支付列表</caption>
		              <tr>
		                <th class='thTitleItrn'>序号</th>
		                <th class='thTitleItrn'>课件名称</th>
		                <th class='thTitleItrn'>出版单位</th>
		                <th class='thTitleItrn'>出品人</th>
		                <th class='thTitleItrn'>单价</th>
		                <th class='thTitleItrn'>学分</th>
		              </tr>
		              <s:iterator value="courseTemporarViewList" status="L">
		                  <s:hidden name="courseTemporarViewList[%{#L.index}].course_id" id="course_id"/>
		                  <s:hidden name="courseTemporarViewList[%{#L.index}].course_name" id="course_name"/>
		                  <s:hidden name="courseTemporarViewList[%{#L.index}].course_expert" id="course_expert"/>
		                  <s:hidden name="courseTemporarViewList[%{#L.index}].course_price" id="course_price"/>
		                  <s:hidden name="courseTemporarViewList[%{#L.index}].xuefen" id="xuefen"/>
			              <my:trStripe index="${L.index}">
			                <td class="tdc">${L.index +1}</td>
			                <td class='tbl' >
			                <s:if test="course_name==''"> 
			                   <s:property value='无'/>
			                </s:if>
			                <s:else>
			                 <s:property value='course_name'/>
			                </s:else>
			                  </td>
			                <td class='tbl' ><s:property value='course_expert_unit'/></td>
			                 <td class='tbl'  >
			                   <s:if test="course_expert==''"> 
			                                                         无
			                   </s:if>
			                   <s:else>
			                       <s:property value='course_expert'/>
			                    </s:else>
			                </td>
			                 <td class='tdc'  >
			                     <span style="color: red">￥<s:property value='course_price'/></span>
			                </td>
			                <td class='tdc'  >
		 						 <span style="color: #00AA00 "><s:property value='xuefen'/>(分)</span>
			                </td>
			              </my:trStripe>
		              </s:iterator>
		          </table>
	             </form>
		       </div>
		       <div class="content">
			       <table  width="100%" valign="top">
			         <tr><s:if test="courseNum==1"><td align="right"><s:checkbox name="ishere" id="select_kbn_check"  fieldValue="selected" />&nbsp;组合优惠（不允许退费）</td></s:if></tr>
	                 <tr>
	                     <td align="right">
	                       <span>通过考试后可获得分数：</span>  &nbsp;<span style="color: red"><s:property value="xuefen"/>分</span> &nbsp;&nbsp;&nbsp; <span>需要支付：</span>&nbsp;<span style="color: red; width: 250px ">￥</span>
	                       <span id="total" style="color: red">
	                       <s:property value="total"/></span> &nbsp; &nbsp; &nbsp; 
	                    <s:if test="%{total <![CDATA[  <= ]]> nowMoney}">
	                        <input class="inp_L3 btnClass_${only_search}" type="button" id="butNowCourse" value="余额支付 " ></input>
	                    </s:if>
	                    <s:else>
		                <input class="inp_L3 btnClass_${only_search}" type="button" disabled="disabled" id="butNowCourse" value="余额支付 " /></input>
		                </s:else>(余额：￥<s:property value="nowMoney"/>)&nbsp;&nbsp;
	                        <input class="inp_L3 btnClass_${only_search}" type="button" id="butAddCourse" value="网银支付" /></input>&nbsp;&nbsp;
	                     </td>
	                 </tr>
	              </table>
		     </div>
        </td>
      </tr>
    </table>
     <!-- 显示支付层 引用-->
  <jsp:include page="./paydiv.jsp"></jsp:include>
  <!-- 显示支付失败层 引用-->
  <jsp:include page="./payFailurediv.jsp"></jsp:include>
  <!-- 显示支付成功层 引用-->
  <jsp:include page="./paySuccessdiv.jsp"></jsp:include>
  </body>
</html>
