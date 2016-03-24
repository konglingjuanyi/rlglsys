<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" language="javascript"> 
$(document).ready(function(){
     //搜索按钮
     $("#btnSearch").click(function(){
  
         $("#form").attr("action", "rlgl000213Search.action");
         $("#form").submit();
        //document.form.action = "rlgl010108Search.action";
        //document.form.nameSpace = "/rlgl";
        //document.form.submit();
     });
      //注销按钮
     // $("#freeBtn").change(function(){
     //     var rlgl010108deleteUnit = function (){
	//	  var value=$(this).val();
      //    comboxLinkageStructure.url = "rlgl010109EscrowUnit.action";
     //     comboxLinkageStructure.urlParams =value;
     //     comboxChanged();
	 //    };
     //    confirmMessage("CM029",rlgl010108deleteUnit);
    //  });
});
function addUserRole(unit_no,unit_nm,unit_status,user_id)
{
        $("#user_id").val(user_id);
        $("#userName").val(unit_nm);
        $("#unit_status").val(unit_status);
        $("#unit_no").val(unit_no);
        //if(unit_no=="37")
        //{
          // $("#user_id").val("37");
        //}
        $("#form").attr("action", "rlgl000211AddUserRole.action");
        $("#form").submit();
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
          <!-- 数据检索条件 -->  
          <form  id="form" nameSpace="/rlgl" action="doLogin" method="post" >
              <s:hidden name="navigationId" id="navigationId"/>
          	  <s:hidden name="screenId" id="screenId"/>
              <s:hidden name="user_id" id="user_id"/>
              <s:hidden name="unitNo" id="unit_no"/>
              <s:hidden name="userName" id="userName"/>
              <s:hidden name="user_enter" id="unit_status"/>
              <s:hidden name="roletype" id="roletype" value="1"/>
              <s:hidden name="only_search" id="only_search"/>
	          <table width="100%" valign="top" border="1px">
	          <caption>查询条件</caption>
	            <tr> 
	              <td class='lc1' sytle="">单位编号</td>
	              <td class='lc2'><s:textfield  name="unit.unit_no"  style="width:150px"/></td>
	              <td class='lc1'>单位名称</td>
	              <td class='lc2'><s:textfield  name="unit.unit_nm"  style="width:150px"/></td>
	              <td class='tdc' style="text-align:left"><input class="inp_L3" type="button" name="btn_Interview" value="查询" style="width:80px" id="btnSearch"></td>
	            </tr>
	          </table>
           <div id="result" style="position: relative;top:20px">
            <my:dividepage actionId="rlgl000213Search.action"></my:dividepage>
	        <table width="100%" valign="top" border="1px">
              <tr>
                <th width="50"  class='thTitleItrn'>序号</th>
                <th width="15%" class='thTitleItrn'>单位编号</th>
                <th  class='thTitleItrn'>单位名称</th>
                <th class='thTitleItrn'>单位电话</th>
                <th class='thTitleItrn'>代管单位</th>
                <th width="100" class='thTitleItrn'>用户操作</th>
              </tr>
              <s:iterator value="unitList" status='L'>
	              <my:trStripe index="${L.index}">
	                 <td  class="tdc">${L.index +1}</td>
	                 <td class="tbl">
	                    <s:property value='unit_no'/>
	               
	                 </td>
	                 <td  class="tbl" width="20%">
	                     <s:property value='unit_nm'/></td>
	                 <td class="tbl" class='tdc' >
	                   <s:if test="contact==''"> 
	                                                        无
	                   </s:if>
	                   <s:else>
	                       <s:property value='contact'/>
	                    </s:else>
	                 </td>
	                 <td class="tbl" >
	                   <s:if test="escrow_unit_no==''"> 
	                                                        无
	                   </s:if>
	                   <s:else>
	                       <s:property value='escrow_unit_no'/>
	                    </s:else>
	                 </td>
	                 <td class="tdc">
		               <input class="inp_L3 btnClass_${only_search}" type="button" value="配置角色" onclick="addUserRole('<s:property value='unit_no'/>','<s:property value='unit_nm'/>','<s:property value='unit_status'/>','<s:property value='expend_01'/>')" />
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