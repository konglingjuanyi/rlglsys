<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <TITLE>
              日照医学教育
     </TITLE>
<script language="JavaScript">
function saveUserRole(user_id)
{
    $("#user_id").val(user_id);
    $("#rlgl000212form").attr("action", "rlgl000212SaveUserRole.action");
    $("#rlgl000212form").submit();
}
 //返回页面
	function goBack() {
		var roletype=$("#roletype").val();
		if(roletype=="1"){
		    $("#rlgl000212form").attr("action", "rlgl000213Search.action");
		}
		else
		{
		   $("#rlgl000212form").attr("action", "rlgl000211Search.action");
		}
	      $("#rlgl000212form").submit();
	}
// checkbox只能选中一个
function SignCheck(cbox) {
    var obj = document.getElementsByTagName("input");
    for (var i = 0; i < obj.length; i++) {
        if (obj[i].type == "checkbox") {
            obj[i].checked = false;
        }
        var sid = cbox.id;
        document.getElementById(sid).checked = true;
    }
}
</script>    
</head>
    <body>
    <my:navigation></my:navigation>
    <table cellpadding='0' cellspacing='0' width='100%'>
      <tr>
        <td>
        <!-- 内容显示区域 -->
	     <div class="content">
          <!-- 数据检索条件 -->  
          <form name="form"  id="rlgl000212form" nameSpace="/rlgl" action="doLogin" method="post" >
          		<s:hidden name="navigationId" id="navigationId"/>
       		 	<s:hidden name="screenId" id="screenId"/>
       		 	<s:hidden name="user_id" id="user_id"/>
       		 	<s:hidden name="user_name"/>
       		 	<s:hidden name="userName"/>	
       		 	<s:hidden name="user_enter"/>	
       		 	<s:hidden name="unitNo"/>			
       		 	<s:hidden name="roletype" id="roletype"/>	
       		 	<s:hidden name="unit.unit_no"/>
       		 	<s:hidden name="unit.unit_nm"/>		
       		 	<s:hidden name="only_search" id="only_search"/>	
	           <div id="result" style="position: relative;top:20px">
	           <table  width="100%" valign="top">
	           <tr><td align="left"><font color="red" style="font-size:16;">用户名称:</font><s:property value='userName'/></td><td align="right"><input class="inp_L3 btnClass_${only_search}" type="button" value="保存角色" onclick="saveUserRole('<s:property value='user_id'/>')" />&nbsp;&nbsp;<input class="inp_L3" type="button" value="返回" onclick="goBack()" />&nbsp;&nbsp;</td></tr>
	           </table>
	          <table width="100%" valign="top" border="1px">
	              <tr>
	                <th class='thTitleItrn'>序号</th>
	                <th class='thTitleItrn'>角色名称</th>
	                <th width="150" class='thTitleItrn'>选择角色</th>
	              </tr>
	               <s:iterator value="roleList" status="L">
	                   <s:hidden name="roleList[%{#L.index}].role_name" id="role_name"/>
                       <s:hidden name="roleList[%{#L.index}].role_id" id="role_id"/>
                       <s:hidden name="roleList[%{#L.index}].unit_role" id="unit_role"/>
		              <my:trStripe index="${L.index}">
		                <td class="tdc">${L.index +1}</td>
		                <td class='tbl' >
		                <s:property value='role_name'/>
		                  </td>
		                <td class='tdc'>
		                  <s:if test="data_has==1">
			                   <s:if test="user_role==1">
			                      <s:checkbox name="ishere" id="%{#L.index }" fieldValue='%{#L.index }' checked="checked" onclick="SignCheck(this)" />
			                   </s:if>
			                   <s:else>
			                      <s:checkbox name="ishere" id="%{#L.index }"  fieldValue="%{#L.index }" onclick="SignCheck(this)"/>
			                   </s:else>
		                 </s:if>
		                 <s:else>
		                      <s:checkbox name="ishere" id="%{#L.index }"  fieldValue="%{#L.index }" disabled="true" />
		                 </s:else>
		                </td>
		              </my:trStripe>
	              </s:iterator>
	          </table>
	          </div>
	          </form>
          </div>
        </td>
      </tr>
    </table>
  </body>
</html>
