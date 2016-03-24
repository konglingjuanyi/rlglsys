<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    <title><s:property value="getText('rlglsys.browserhead.IE')" /></title>
    <%
    response.addHeader("Cache-Control", "no-cache");
    %>
        <script language="JavaScript">
        // 申请退款
        function refund(TransactionID) {
            // 缴费订单
            $("#TransactionID").val(TransactionID);
            $("#form").attr("action", "rlgl100100Refund.action");
            $("#form").submit();
        }
        </script>
    </head>
    <body>
    <s:form id="form" name="form" method="post" action="rlgl100100Init" nameSpace="/rlgl">
        <s:hidden name="navigationId" id="navigationId"/>
        <s:hidden name="screenId" id="screenId"/>
        <s:hidden name="only_search" id="only_search"/>
        <s:hidden name="TransactionID" id="TransactionID"/>
        <my:navigation></my:navigation>
        <my:message></my:message>
        <!-- 检索条件 -->
        <table style="width:98%" class="am-table am-table-bordered am-table-radius am-table-striped am-margin am-table-hover">
        <thead>
        <tr>
            <th>序号</th>
            <th>消费项目编号</th>
            <th>消费项目</th>
             <th>消费金额</th>
              <th>消费日期</th>
               <th>当前状态</th> 
        </tr>
    </thead>
     <s:iterator value="tb02PrepayMsgList" status="L">
                  <my:trStripe index="${L.index}">
                    <td>${L.index +1}</td>
                    <td ><s:property value='TransactionID'/></td>
                    <td ><s:property value='MerchantNM'/></td>
                    <td>
                    
                    <s:if test="%{Amount == 'ERROR'}">
					 <font color="red">数据异常</font>
					 </s:if>
	                 <s:else>
	                 <s:property value='Amount'/>
	                 </s:else>

                    </td>
                    <td height="20" class="tdc"><s:property value='pay_date'/></td>
                    <td height="20" class="tdl">
                      <s:if test="%{refund_flag == 0}">
                                                          已缴费成功
                      </s:if>
                      <s:if test="%{refund_flag == 3}">
                                                          已消费
                      </s:if>
                      <s:if test="%{refund_flag == 1}">
                          <a href="#">已申请退费</a>
                      </s:if>
                      <s:if test="%{refund_flag == 2}">
                          <a href="#">退费成功</a>
                      </s:if>
                    </td> 
                  </my:trStripe>
              </s:iterator>
        </table>
        <div class="am-margin">
         <my:dividepage actionId="rlgl100100Init.action"></my:dividepage>
         </div>
  <!--       <div class="content">
           
            <div id="result" style="position: relative;top:15px">
           
              <table width="100%" style="position: relative; top: 10px;" border="1" cellpadding="2" cellspacing="2">
              
              <s:iterator value="tb02PrepayMsgList" status="L">
                  <my:trStripe index="${L.index}">
                    <td height="20" class="tdc">${L.index +1}</td>
                    <td height="20" class="tdl"><s:property value='TransactionID'/></td>
                    <td height="20" class="tdl"><s:property value='MerchantNM'/></td>
                    <td height="20" class="tdc">
                    
                    <s:if test="%{Amount == 'ERROR'}">
					 <font color="red">数据异常</font>
					 </s:if>
	                 <s:else>
	                 <s:property value='Amount'/>
	                 </s:else>

                    </td>
                    <td height="20" class="tdc"><s:property value='pay_date'/></td>
                    <td height="20" class="tdl">
                      <s:if test="%{refund_flag == 0}">
                                                          已缴费成功
                      </s:if>
                      <s:if test="%{refund_flag == 3}">
                                                          已消费
                      </s:if>
                      <s:if test="%{refund_flag == 1}">
                          <a href="#">已申请退费</a>
                      </s:if>
                      <s:if test="%{refund_flag == 2}">
                          <a href="#">退费成功</a>
                      </s:if>
                    </td> 
                  </my:trStripe>
              </s:iterator>
              </table>--> 
              
            </div>
        </div>
    </s:form>
    </body>
</html>

