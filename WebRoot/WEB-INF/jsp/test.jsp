<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <TITLE>
              日照医学教育
     </TITLE>
<script language="JavaScript">

</script>    
</head>
   <body><div>
    <s:form method="post" action="https://payment.ChinaPay.com/pay/TransGet">
    <s:hidden name="MerId" value="rlgl100101_1Bean.MerId"/>
    <s:hidden name="OrdId" value="rlgl100101_1Bean.OrdId" />
    <s:hidden name="TransAmt" value="rlgl100101_1Bean.TransAmt"/>
    <s:hidden name="CuryId" value="rlgl100101_1Bean.CuryId"/>
    <s:hidden name="TransDate" value="rlgl100101_1Bean.TransDate"/>
    <s:hidden name="TransType" value="rlgl100101_1Bean.TransType"/>
    <s:hidden name="Version" value="rlgl100101_1Bean.Version"/>
    <s:hidden name="BgRetUrl" value="com.rlglsys.action.learnonline。Rlgl100101ReceiveAction"/>
    <s:hidden name="PageRetUrl" value="http://www.chinaypay.com/pay/Pgreturn.jsp"/>
    <s:hidden name="GateId" value="rlgl100101_1Bean.GateId"/>
    <s:hidden name="Privl" value="rlgl100101_1Bean.Privl"/>
    <s:hidden name="ChkValue" value="rlgl100101_1Bean.ChkValue"/>
    <div id="searchInfo">
    <my:navigation></my:navigation>
    <my:message></my:message>
    <div class="content">
       <div style="position: relative">
        <table width="100%" style="position: relative; top: 10px;" border="1" cellpadding="2" cellspacing="2">
         <tr>
            <td width="15%" class="lc1">商户号</td>
            <td>
              <s:property value="rlgl100101_1Bean.MerId"/>
            </td>
          </tr>
          <tr>
            <td width="15%" class="lc1">交易订单号</td>
            <td>
              <s:property value="rlgl100101_1Bean.OrdId"/>
            </td>
          </tr>
          <tr>
            <td width="15%" class="lc1">订单交易金额</td>
            <td>
              <s:property value="rlgl100101_1Bean.TransAmt"/>
            </td>
          </tr>
          <tr>
            <td width="15%" class="lc1">订单交易币种</td>
            <td width="15%" class="lc1">人民币</td>
          </tr>
          <tr>
            <td width="15%" class="lc1">订单交易日期</td>
            <td>
              <s:property value="rlgl100101_1Bean.TransDate"/>
            </td>
          </tr>
          <tr>
            <td width="15%" class="lc1">订单交易日期</td>
            <td>
              <s:property value="rlgl100101_1Bean.TransDate"/>
            </td>
          </tr>
 
          <tr>
            <td align="center" colspan="2">
             <s:submit class="inp_L3 btnClass_${only_search}" value="确定" name="btnPrepay" id="btnPrepay"></s:submit>
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