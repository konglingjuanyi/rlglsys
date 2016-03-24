<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" language="javascript"> 

$(document).ready(function(){
   //添加保存操作人
    $("#Btnsubmit").click(function(){
        $("#rlgl010107form").attr("action", "rlgl010109Save.action");
        $("#rlgl010107form").submit();
    });
});
//级联用户列表刷新页面
function change() {
    $("#rlgl010107form").attr("action", "rlgl010109Change.action");
    $("#rlgl010107form").submit();
   	//document.form.action = "rlgl010109Change.action";
   	//document.form.nameSpace = "/rlgl";
   	//document.form.submit();
}

//function save() {
 //   document.form.action = "rlgl010109Save.action";
 //   document.form.nameSpace = "/rlgl";
 //   document.form.submit();
//}
//删除操作人	
function deleteUser(user_id,section_id) {
    $("#user_id").val(user_id);
    $("#section_id").val(section_id);
    $("#rlgl010107form").attr("action", "rlgl010109Delete.action");
    $("#rlgl010107form").submit();
    //document.form.action = "rlgl010109Delete.action";
    //document.form.nameSpace = "/rlgl";
    //document.form.submit();
}
function saveUserName(user_name)
{
   $("#user_name").val(user_name.options[user_name.selectedIndex].text);
}
function goBack()
{
    $("form1").attr("action", "rlgl010125Init.action");
    $("form1").submit();
    //document.form1.action = "rlgl010125Init.action";
    //document.form1.nameSpace = "/rlgl";
    //document.form1.submit();
}
</script>
     <TITLE>
              日照医学教育
     </TITLE>
<script language="JavaScript">
</script>    
</head>
    <body>
    <table cellpadding='0' cellspacing='0' width='100%'>
      <tr>
        <td>
         <!-- 内容显示区域 -->
	      <my:navigation></my:navigation>
	      <my:message></my:message>
	      <div class="content">
	       <form name="form1" method="post"></form>
          <!-- 数据检索条件 -->  
          <form name="form" id="rlgl010107form" nameSpace="/rlgl" action="doLogin" method="post" >
            <s:hidden name="navigationId" id="navigationId"/>
          	<s:hidden name="screenId" id="screenId"/>
            <s:hidden name="operatorUnitNo" />
            <s:hidden name="operatorUnitNm" />
            <s:hidden name="rlgl010110Bean.user_name" id="user_name"/>
            <s:hidden name="rlgl010107Bean.user_id" id="user_id"/>
            <s:hidden name="rlgl010107Bean.section_id" id="section_id"/>
             <s:hidden name="only_search" id="only_search"/>
            <s:hidden id="BtnsubmitRequire" name="BtnsubmitRequire" value="section_id,user_id,cellphone,email"/>
	        <table width="100%" valign="top" border="1px">
	            <caption>添加操作人</caption>
	                 <tr> 
			             <td class='lc1'>科室<font color="red">*</font></td>
			             <td class='lc2'><s:select name="rlgl010110Bean.section_id" id="section_id" list="ectionList" listKey="section_no" listValue="section_name" headerValue="-----请选择科室-----" headerKey="" onchange="change()"/></td>
			             <td class='lc1'>人员<font color="red">*</font></td>
			             <td class='lc2'><s:select name="rlgl010110Bean.user_id" id="user_id" list="personnelList" listKey="personnel_id" listValue="personnel_nm" headerValue="-----请选择人员-----" headerKey="" onchange="saveUserName(this)"  /></td>
			         </tr>
		             <tr>
		            	 <td class="lc1">
		                                                    联系电话<font color="red">*</font>
		             	 </td>
			             <td class="lc2">
			               <s:textfield name="rlgl010110Bean.cellphone" id="cellphone" cssClass="put " >
			               </s:textfield>
			             </td>
			             <td class="lc1">
			                                        电子邮箱<font color="red">*</font>
			             </td>
		                 <td class="lc2">
		                    <s:textfield name="rlgl010110Bean.email" id="email">
		                    </s:textfield>
		                 </td>
	          		 </tr>
	          		 <tr>
			             <td class="lc1">
			                                         腾讯 QQ
			             </td>
			             <td class="lc2">
			               <s:textfield name="rlgl010110Bean.qqnumber">
			               </s:textfield>
			             </td>
			             <td class="lc1">
			                                       传  &nbsp;真
			             </td>
			             <td class="lc2">
			               <s:textfield name="rlgl010110Bean.fax">
			               </s:textfield>
			             </td>
		             </tr>
		             <tr>
			             <td class="lc1" colspan="4" align="center">
			               <input type="button" class="inp_L3 btnClass_${only_search}" value="  提 交   " onclick="save()" id="Btnsubmit" name="submitBtn" />
			               &nbsp;&nbsp;
			               <input type="button" class="inp_L3" onclick="goBack()" value="  返 回   " name="goBackBtn" />
			             </td>
          		     </tr>
	          </table>
          </form>
           <table width="100%" valign="top" border="1px">
           <caption>操作人列表</caption>
               <tr>
                    <th class='thTitleItrn'>操作人</th>
	                <th class='thTitleItrn'>所在科室</th>
	                <th class='thTitleItrn'>联系电话</th>
	                <th class='thTitleItrn'>电子邮箱</th>
	                <th class='thTitleItrn'>传  &nbsp;真</th>
	                <th class='thTitleItrn'>腾讯 QQ</th>
	                <th width="20%" class='thTitleItrn'>用户操作</th>
               
               </tr>
               <s:iterator value="operatorList"   status='L'> 
		              <my:trStripe index="${L.index}">
			              <td class="lc2">
			                 <s:property value="user_name"/>
			              </td>
			              <td class="lc2">
			                 <s:property value="section_no"/>
			              </td>
			              <td class="lc2">
			                 <s:property value="cellphone"/>
			              </td>
			              <td class="lc2">
			                 <s:property value="email"/>
			              </td>
			              <td class="lc2">
			                 <s:property value="qqnumber"/>
			              </td>
			               <td class="lc2">
			                 <s:property value="fax"/>
			              </td>
			              <td class="lc2">
			                <input class="inp_L3 btnClass_${only_search}" type="button" value="删除" name="infoChange" onclick="deleteUser( '<s:property value='user_id'/>','<s:property value='section_id'/>')"/>&nbsp;
			              </td>
            		 </my:trStripe>
	              </s:iterator>
           
           </table>
        </div>
        </td>
      </tr>
    </table>
  </body>
</html>
                

