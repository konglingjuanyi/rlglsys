<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <TITLE>
              日照医学教育
     </TITLE>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/WdatePicker.js"></script>
<script language="JavaScript">
// 【出力按钮】事件的定义
//function printBtn(){
  //var print = function(){
     // document.form1.action = "rlgl070202Search.action";
      //document.form1.nameSpace = "/rlgl";
      //document.form1.submit();
  //};
//}
    
   $(document).ready(function(){
	  //返回按钮事件
	  $("#btnBack").click(function(){
	  	$("#form1").attr("action", "rlgl070202Search.action");
	  	$("#form1").submit();
	  }); 
	 }); 
</script>    
</head>
  <body><div>
  <s:form id="form1" name="form1" method="post" action="rlgl070211Init" nameSpace="/rlgl">
      <s:hidden name="rlgl070202Bean.user_to_check" id="rlgl070202Bean_user_to_check"/>
      <s:hidden name="rlgl070202Bean.unit_no" id="rlgl070202Bean_unit_no"/>
      <s:hidden name="rlgl070202Bean.unit_no_bk" id="unit_no_bk"/>
      <s:hidden name="rlgl070202Bean.apply_year" id="rlgl070202Bean_apply_year"/>
    <div id="searchInfo">
    <my:navigation></my:navigation>
    <my:message></my:message>
    <div class="content">
     <div id="result" style="position: relative;top:20px">
      <my:dividepage actionId="rlgl070211Init.action"></my:dividepage>
        <table width="100%" style="position: relative;" border="1" cellpadding="2" cellspacing="2">
         <caption>已申请发票人员名单  </caption>
       <tr>
          <th height="28" width="5%" class="thTitleItrn">序号</th>
          <th height="28" width="15%" class="thTitleItrn">用户ID</th>
          <th height="28" class="thTitleItrn" width="15%">用户姓名</th>
          <th height="28" class="thTitleItrn" width="15%">交费年度</th>
          <th height="28" width="45%" class="thTitleItrn">单位名称</th>
        </tr>
          <s:iterator value="rlgl070202BeanList" status="L">
            <my:trStripe index="${L.index}">
                <td class="tdc">${L.index +1}</td>
                <td class="tdl">
                <s:property value="personnal_id"/>
                </td>
                <td class="tdl">
                <s:property value="personnal_nm"/>
                </td>
                <td class="tdl">
                <s:property value="apply_year"/>
                </td>
                <td class="tdl">
                    <s:property value="unit_nm"/>
                </td>   
             </my:trStripe>
         </s:iterator>
        </table>
      </div>
      <div class="btn">
         <!--操作按扭-->
          <table width="100%">
          <tr>
          <td height="15px"></td>
          </tr>
            <tr>
               <td align="center"><input type="button" class="inp_L3" value="返回" name="btnBack" id="btnBack"></td>
             </tr>
           </table>
         </div>
      </div>
  </div>
</s:form>
</div>
</body>
</html>
