<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <TITLE>
              日照医学教育
     </TITLE>
<!--  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>-->
<script language="JavaScript">
// 判断是否缴过费
 
$(document).ready(function(){ 
     //提交
     $("#btnPrepay").click(function(){
    	 var idcard =document.getElementsByName("Payment");
         for(var i=0;i<idcard.length;i++)
         {
             if(idcard[i].checked==true)
             {
            	 var amount=document.getElementById("clum003").innerHTML.trim();
            	 
            	 window.open('http://118.192.147.9:8080/alipay/index.jsp?a='+idcard[i].value+'&b='+amount);
            	 
             }
         }
     

    	
    	
    	
    	
    	 
     });
});

</script>    
</head>
   <body><div>
    <s:form id="rlgl100101"  method="post" action="rlgl100101Init" nameSpace="/rlgl">
    <s:hidden name="navigationId" id="navigationId"/>
    <s:hidden name="screenId" id="screenId"/>
    <s:hidden name="only_search" id="only_search"/>
    <s:hidden name="flg" id="flg"/>
    <s:hidden name="type" value="1"/>
    <div id="searchInfo">
    <my:navigation></my:navigation>
    <my:message></my:message>
    <div class="content">
       <div style="position: relative">
        <table width="99%" class="am-table am-table-bordered am-table-striped am-table-hover am-margin" cellpadding="2" cellspacing="2">
          
          <tr>
            <td width="15%" class="lc1">用户名称</td>
            <td>
              <s:property value="rlgl100101Bean.clum002"/><s:hidden name="clum001" value="rlgl100101Bean.clum001"/>
            </td>
          </tr>
          <tr>
            <td width="15%" class="lc1">身份证号</td>
            <td>
            <div id="clum003" name="clum003">
              <s:property value="rlgl100101Bean.clum003" />
             </div>
           </td>
          </tr>
           
          <tr>
            <td width="15%" class="lc1">交费金额</td>
            <td>            
            <input type="radio" name="Payment" value="20" /> 20元
            
				 <input type="radio" name="Payment" value="120"  checked="true" /> 120元

            <br></td>
            
          </tr>
         
          <tr>
            <td align="center" colspan="2">
            <input type="button" class="am-btn am-btn-danger " width="100px" value="交费" name="btnPrepay" id="btnPrepay"></input>
            </td>
          </tr>
        </table>
      </div>
      
      
      
      
      
      
       <div class="am-panel am-panel-danger am-margin">
         <header class="am-panel-hd">
    <h3 class="am-panel-title">Tips</h3>
  </header>
  <p class =" am-padding am-danger">
备注：
   <font color="red">以下是特别注意事项</font></P>
    <P>1、当您扫描支付宝充值二维码结束，千万不要手动关闭支付页面，请务必等待付款成功与失败页面的跳转，页面提示付款成功（付款失败）时，方可关闭支付界面。  </p>
    <p>2、充值金额可自行修改。</p>
    <p>3、在缴费过程中，注意缴费成功后，及时进行余额查询，请勿重复缴费。</p>
    <p>4、如果您打开余额界面之后缴费的，请关闭余额界面，再重新打开查询余额。</p>
    
		
		</div>
      </div>
  </div>
</s:form>
</div>
  </body>
</html>


