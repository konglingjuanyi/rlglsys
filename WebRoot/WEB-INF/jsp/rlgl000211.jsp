<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <TITLE>
              日照医学教育
     </TITLE>
<script language="JavaScript">
$(document).ready(function(){
	// 查询事件的定义
   // $("#lowerUnit").click(function(){
   //     $("form").attr("action", "rlgl010501Search.action");
    //    $("form").submit();
    //});
     //查询
        $("#search").click(function(){
        $("#rlgl000211form").attr("action", "rlgl000211Search.action");
        $("#rlgl000211form").submit();
    });   
});

function addUserRole(user_id,user_name,user_enter)
{
        $("#user_id").val(user_id);
        $("#userName").val(user_name);
        $("#user_enter").val(user_enter);
        $("#rlgl000211form").attr("action", "rlgl000211AddUserRole.action");
        $("#rlgl000211form").submit();
}
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
	          <!-- 数据检索条件 -->  
	          <form name="form"  id="rlgl000211form" nameSpace="/rlgl" action="doLogin" method="post" >
	          		<s:hidden name="navigationId" id="navigationId"/>
          		 	<s:hidden name="screenId" id="screenId"/>
          		 	<s:hidden name="user_id" id="user_id"/>
          		 	<s:hidden name="userName" id="userName"/>
          		 	<s:hidden name="user_enter" id="user_enter"/>
          		 	<s:hidden name="roletype" id="roletype" value="0"/>
          		 	<s:hidden name="only_search" id="only_search"/>
			          <table width="100%" valign="top" border="1px">
			          <caption>查询条件</caption>
			            <tr> 
			              <td class='lc1'>用户名称</td>
			              <td class='lc2'><s:textfield  name="user_name"  style="width:150px"/></td>
			              <td class='tdc' style="text-align:left"><input class="inp_L3" type="button" name="btn_Interview" value="查询" style="width:80px" id="search"></td>
			            </tr>
			          </table>
		           <div id="result" style="position: relative;top:20px">
		          <my:dividepage actionId="rlgl000211Search.action"></my:dividepage>
		          <table width="100%" valign="top" border="1px">
		              <tr>
		                <th class='thTitleItrn'>序号</th>
		                <th class='thTitleItrn'>用户名称</th>
		                <th class='thTitleItrn'>电话</th>
		                <th class='thTitleItrn'>邮箱</th>
		                <th class='thTitleItrn'>用户类别</th>
		                <th width="150" class='thTitleItrn'>用户操作</th>
		              </tr>
		              <s:iterator value="userList" status="L">
			              <my:trStripe index="${L.index}">
			                <td class="tdc">${L.index +1}</td>
			                <td class='tbl' >
			                <s:if test="user_name==''"> 
			                                                            无
			                </s:if>
			                <s:else>
			                 <s:property value='user_name'/>
			                </s:else>
			                  </td>
			                 <td class='tbl' >
			                   <s:if test="personnel_tel==''"> 
			                                                            无
			                   </s:if>
			                   <s:else>
			                     <s:property value='personnel_tel'/>
			                    </s:else>
			                 </td>
			                 <td class='tbl'  >
			                   <s:if test="personnel_email==''"> 
			                                                         无
			                   </s:if>
			                   <s:else>
			                       <s:property value='personnel_email'/>
			                    </s:else>
			                </td>
			                  <td class='tbl'  >
			                   <s:if test="user_type==1"> 
			                                                                  单位用户
			                   </s:if>
			                   <s:else>
			                                                                个人用户
			                    </s:else>
			                </td>
			                <td class='tdc'>
			                  <input class="inp_L3 btnClass_${only_search}" type="button" value="配置角色" onclick="addUserRole('<s:property value='user_id'/>','<s:property value='user_name'/>','<s:property value='user_enter'/>')" /></input>&nbsp;
			              <!--    <input class="inp_L3" type="button" value="删除"  onclick="deleteUnit('<s:property value='unit_no'/>')"/> --> 
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
