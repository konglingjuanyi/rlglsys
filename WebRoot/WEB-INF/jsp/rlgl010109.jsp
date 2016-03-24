<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <TITLE>
              日照医学教育
     </TITLE>
<script language="JavaScript">
//用于区分显示位置（被代管和代管文本框）
var unitSelect = "";
$(document).ready(function(){
     // 【单位查询】按钮的事件定义
    $("#btnSearchUnitEscrow").click(function(){
        unitSelect = "1";
        //参数用于限制对话框中显示单位为本单位下级医疗单位
        openUnitSelectWindow("sort=1");
    });
    $("#escrow_unit_nm").click(function(){
        unitSelect = "1";
        openUnitSelectWindow("sort=1");
    });
    $("#btnSearchUnitManaged").click(function(){
        unitSelect = "2";
        openUnitSelectWindow("sort=1");
    });
     $("#managed_unit_nm").click(function(){
        unitSelect = "2";
        openUnitSelectWindow("sort=1");
    });
    // 提交代管单位
    $("#btnAddEscrow").click(function(){
          var escrow_unit_no=$("#escrow_unit_no").val();
          var managed_unit_no=$("#managed_unit_no").val(); 
          var escrowSave = function() {
             $("#form").attr("action", "rlgl010109EscrowUnit.action");
             $("#form").submit();
          };
        if(escrow_unit_no !='' && managed_unit_no !='')
        {
         
	        if(escrow_unit_no != managed_unit_no)
	        {
	            confirmMessage("CM050", escrowSave);
	        }
	        else
	        {
	            
	            alertMessage("AM046");
	        }
         }
         else
         {
               alertMessage("AM047");  
       
         }
    });
    //查询代管单位信息
     $("#btnSearch").click(function(){
            $("#form").attr("action", "rlgl010109Search.action");
            $("#form").submit();
     });
     
});
// 【单位查询】按钮的返回事件
function callBackFun_unitSelect(unitInfo) {
    if (unitSelect =="1") {
        $("#escrow_unit_no").val(unitInfo.unit_no);
        $("#escrow_unit_nm").val(unitInfo.unit_nm);
    }
    else
    {
        $("#managed_unit_no").val(unitInfo.unit_no);
        $("#managed_unit_nm").val(unitInfo.unit_nm);
    }
}
//解除代管
function freeUnit(unit_no,escrow_unit_no)
{
     $("#escrow_unit_no").val(escrow_unit_no);
     $("#managed_unit_no").val(unit_no);
     var freeUnit = function() {
              $("#form").attr("action", "rlgl010109DelEscrowUnit.action");
              $("#form").submit();
          };
     confirmMessage("CM051", freeUnit);
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
	         <s:form id="form" name="form" nameSpace="/rlgl" action="doLogin" method="post" >
	               <s:hidden name="navigationId" id="navigationId"/>
          		   <s:hidden name="screenId" id="screenId"/>
	               <s:hidden name="escrow_unit_no_snap" id="escrow_unit_no_snap" />
	               <s:hidden name="managed_unit_no_snap" id="managed_unit_no_snap" />
	               <s:hidden name="escrow_unit_no" id="escrow_unit_no" />
	               <s:hidden name="managed_unit_no" id="managed_unit_no" />
	                <s:hidden name="only_search" id="only_search"/>
			      <div id="searchInfo">
			       <div style="position: relative">
			        <table width="100%" valign="top" border="1px">
			           <tr>
			           <td width="15%" class="lc1" >代管单位</td>
			            <td width="35%" >
			              <s:textfield name="escrow_unit_nm" id="escrow_unit_nm" cssClass="put width:98%" maxLength="18" />
			              <input type="button" class="inp_L3" value="选择单位" name="btnSearchUnitEscrow" id="btnSearchUnitEscrow">
			            </td>
			            
			            <td width="15%" class="lc1">被代管单位</td>
			            <td width="35%">
			              <s:textfield name="managed_unit_nm" id="managed_unit_nm"  cssClass="put width:98%" maxLength="18" />
			              <input type="button" class="inp_L3" value="选择单位" name="btnSearchUnitManaged" id="btnSearchUnitManaged">
			            </td>
			          </tr>
			          <tr>
			            <td align="right" colspan="6">
			              <input type="button" class="inp_L3 btnClass_${only_search}" value="授权" name="btnAddEscrow" id="btnAddEscrow">
			              <input type="button" class="inp_L3" value="查询" name="btnSearch" id="btnSearch">
			            </td>
			          </tr>
			        </table>
			      </div>
			    
			       <div id="result" style="position: relative;top:20px">
			       <my:dividepage actionId="rlgl010109Search.action"></my:dividepage>
			        <table  width="100%" valign="top" border="1px">
			       <tr>
			          <th class='thTitleItrn' width="40px">序号</th>
			          <th height="28" width="19%" class="thTitleItrn">代管单位编号</th>
			          <th height="28" width="19%" class="thTitleItrn">代管单位名称</th>
			          <th height="28" width="19%" class="thTitleItrn">被代管单位编号</th>
			          <th height="28" width="19%"  class="thTitleItrn">被代管单位名称</th>
			          <th height="28" width="19%" class="thTitleItrn">操作</th>
			        </tr>
			          <s:iterator value="unitEscrowList" status="L">
			            <my:trStripe index="${L.index}">
			             <td  class="tdc">${L.index +1}</td>
			              <td class="tdl"><s:property value="escrow_unit_no"/></td>
			              <td class="tdl"><s:property value="escrow_unit_nm"/></td>
			              <td class="tdl"><s:property value="unit_no"/></td>
			              <td class="tdl"><s:property value="unit_nm"/></td>
			              <td class="tdc">
			                     <input type="button" class="inp_L3 btnClass_${only_search}" onclick="freeUnit('<s:property value="unit_no"/>','<s:property value="escrow_unit_no"/>')" value="解除"  >
			              </td>
			            </my:trStripe>
			         </s:iterator>
			        </table>
			      </div>
			  </div>
			</s:form>
			</div>
        </td>
      </tr>
    </table>
  </body>
</html>
