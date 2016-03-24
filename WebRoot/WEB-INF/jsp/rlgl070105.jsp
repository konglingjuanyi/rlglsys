<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <TITLE>
              日照医学教育
     </TITLE>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.printArea.js"></script>
<script language="JavaScript">
$(document).ready(function(){
      //打印
     $("#btnPrint").click(function(){
          $('#myPrintArea').printArea();
     });
});
</script>    
</head>
    <body>
     <my:navigation></my:navigation>
		<div class="content">
			<div id= "myPrintArea">
		        <table width="100%" style="position: relative; top: 10px;" border="1" cellpadding="2" cellspacing="2">
		       	 <tr>
		            <td class="lc1">订单编号：</td>
		            <td >&nbsp;
		              <s:property value='rlgl070104Bean.clum005'/>
		            </td>
		            <td  class="lc1">用户账号：</td>
		            <td >&nbsp;
		              <s:property value='rlgl070104Bean.clum006'/>
		            </td>
	          	</tr>
		        </table>
	        </div>
	        <div>
		 		<table  width="100%" style="position: relative;top:20px">
			          <tr>
			            <td colspan="4" align="center">
			              <input type="button" class="inp_L3 btnClass_${only_search}" value="打印明细" name="btnPrint" id="btnPrint"></input>
			            </td>
			          </tr>
		      </table> 
      	  </div>
	    </div>
  </body>
</html>
