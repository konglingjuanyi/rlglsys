<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" > 
$(document).ready(function(){
     //搜索按钮
     $("#btnSearch").click(function(){
  
         $("#form").attr("action", "rlgl021101Init.action");
         $("#form").submit();
     });
});
//开放单位
function startHospitalCreditKbn(value)
{   
    $("#unit_no").val(value);
    $("#hospitalCreditKbn").val("1");
    var rlgl021101HospitalCreditKbn = function (){
         $("#form").attr("action", "rlgl021101HospitalCreditKbn.action");
         $("#form").submit();
	};
    confirmMessage("CM088",rlgl021101HospitalCreditKbn);
}
//关闭单位
function closeHospitalCreditKbn(value)
{   
	$("#unit_no").val(value);
	$("#hospitalCreditKbn").val("0");
	
    var rlgl021101HospitalCreditKbn = function (){
         $("#form").attr("action", "rlgl021101HospitalCreditKbn.action");
         $("#form").submit();
	};
    confirmMessage("CM089",rlgl021101HospitalCreditKbn);

}

</script>
     <TITLE>
              日照医学教育
     </TITLE>
</head>
    <body>
         <!-- 内容显示区域 -->
	      <my:navigation></my:navigation>
	      <my:message></my:message>
	      <div class="content">
          <!-- 数据检索条件 -->  
          <form  id="form" nameSpace="/rlgl" action="doLogin" method="post" >
              <s:hidden name="navigationId" id="navigationId"/>
          	  <s:hidden name="screenId" id="screenId"/>
              <s:hidden name="unit_no" id="unit_no"/>
              <s:hidden name="only_search" id="only_search"/>
              <s:hidden name="hospitalCreditKbn" id="hospitalCreditKbn"/>
	          <table width="100%" valign="top" border="1px">
	          <caption>查询条件</caption>
	            <tr> 
	              <td class='lc1' sytle="">单位编号</td>
	              <td class='lc2'><s:textfield  name="unit.unit_no"  style="width:150px"/></td>
	              <td class='lc1'>单位名称</td>
	              <td class='lc2'><s:textfield  name="unit.unit_nm"  style="width:150px"/></td>
	              <td class='tdc' style="text-align:left"><input class="inp_L3" type="button" name="btn_Interview" value="查询" style="width:80px" id="btnSearch"></input></td>
	            </tr>
	          </table>
           <div id="result" style="position: relative;top:20px">
            <my:dividepage actionId="rlgl021101Init.action"></my:dividepage>
	        <table width="100%" valign="top" border="1px">
              <tr>
                <th class='thTitleItrn'>序号</th>
                <th class='thTitleItrn'>单位编号</th>
                <th class='thTitleItrn'>单位名称</th>
                <th class='thTitleItrn'>上级单位</th>
                <th class='thTitleItrn'>主管单位</th>
                <th class='thTitleItrn'>代管单位</th>
                <th class='thTitleItrn'>状态</th>
                <th width="250" class='thTitleItrn'>举办权设置</th>
              </tr>
              <s:iterator value="unitList" status='L'>
	              <my:trStripe index="${L.index}">
	                 <td  class="tdc">${L.index +1}</td>
	                 <td class="tbl">
	                    <s:property value='unit_no'/>
	               
	                 </td>
	                 <td  class="tbl" width="20%">
	                     <s:property value='unit_nm'/></td>
	                 <td class="tbl" width="20%">
	                    <s:if test="unit_super==''"> 
	                                                         无
	                    </s:if>
	                    <s:else>
	                       <s:property value='unit_super'/>
	                    </s:else>
	                 </td>
	                 <td class="tbl" class='tdc' >
	                   <s:if test="unit_lower==''"> 
	                                                        无
	                   </s:if>
	                   <s:else>
	                       <s:property value='unit_lower'/>
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
	                 
	                 <td class="tbl" >
	                   <s:if test="del_kbn=='1'">
	                                                        无效
	                   </s:if>
	                   <s:else>
	                                                        有效
	                   </s:else>
	                 </td>
	                 <td width="10%">
		              
		                 <s:if test="%{hospital_credit_kbn==1}">
		                 
		                      &nbsp;&nbsp;&nbsp;<input type="button" class="inp_L3 btnClass_${only_search}" value="关闭"  onclick="closeHospitalCreditKbn(<s:property value='unit_no'/>)"/></input>
		                 </s:if>
		                 <s:else>
		                     &nbsp;&nbsp;&nbsp;<input type="button" class="inp_L3 btnClass_${only_search}"  value="开放"  onclick="startHospitalCreditKbn(<s:property value='unit_no'/>)"/></input>
		                 </s:else>
		                
	                 </td>
	              </my:trStripe>
              </s:iterator>
          </table>
          </div>
           </form>
          </div>
  </body>
</html>