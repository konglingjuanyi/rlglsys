<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" language="javascript"> 
$(document).ready(function(){
     //搜索按钮
     $("#btnSearch").click(function(){
       // document.form.action = "rlgl010124Search.action";
       // document.form.nameSpace = "/rlgl";
       // document.form.submit();
        $("#rlgl010124form").attr("action", "rlgl010124Search.action");
        $("#rlgl010124form").submit();
     });
});
//修改统计数量
function updateStatistic(value) {
	$("#index").val(value);
	$("#rlgl010124form").attr("action", "rlgl010124updateStatistic.action");
    $("#rlgl010124form").submit();
    //document.form.action = "rlgl010124updateStatistic.action";
    //document.form.nameSpace = "/rlgl";
    //document.form.submit();
}
</script>
     <TITLE>
              日照医学教育
     </TITLE>
<script language="JavaScript">
</script>    
</head>
    <body class="body">
    <table cellpadding='0' cellspacing='0' width='100%'>
      <tr>
        <td>
         <!-- 内容显示区域 -->
	      <my:navigation></my:navigation>
	      <my:message></my:message>
	      <div class="content">
          <!-- 数据检索条件 -->  
          <form name="form" id="rlgl010124form" nameSpace="/rlgl" action="doLogin" method="post" >
              <s:hidden name="navigationId" id="navigationId"/>
          	  <s:hidden name="screenId" id="screenId"/>
              <s:hidden name="unitNo" id="unitNo"/>
              <s:hidden name="indexnum" id="index"/>
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
		       <my:dividepage actionId="rlgl010124Search.action"></my:dividepage>
	        	<table width="100%" valign="top" border="1px" >
	               <tr>
	               <th class='thTitleItrn'>序号</th>
	                <th class='thTitleItrn'>单位编号</th>
	                <th class='thTitleItrn'>单位名称</th>
	                <th class='thTitleItrn'>上级单位</th>
	                <th class='thTitleItrn'>主管单位</th>
	                <th class='thTitleItrn'>代管单位</th>
	                <th width="250" class='thTitleItrn'>用户操作</th>
	               </tr>
	              <s:iterator  value="unitList" status='L' >
	              <my:trStripe index="${L.index}">
	                <td  class="tdc">${L.index +1}</td>
	                 <td class="tdl">
	                    <s:property value='unit_no'/>
	               
	                 </td>
	                 <td class="tdl" >
	                     <s:property value='unit_nm'/></td>
	                 <td class="tdl" >
	                    <s:if test="unit_super==''"> 
	                                                         无
	                    </s:if>
	                    <s:else>
	                       <s:property value='unit_super'/>
	                    </s:else>
	                 </td>
	                 <td class="tdl" class='tdc' >
	                   <s:if test="unit_lower==''"> 
	                                                        无
	                   </s:if>
	                   <s:else>
	                       <s:property value='unit_lower'/>
	                    </s:else>
	                 </td>
	                 <td class="tdl" class='tdc' >
	                   <s:if test="escrow_unit_no==''"> 
	                                                        无
	                   </s:if>
	                   <s:else>
	                       <s:property value='escrow_unit_no'/>
	                    </s:else>
	                 </td>
	                 <td >
	                         <s:hidden name="unitList[%{#L.index}].unit_no" />
		                     <s:select name="unitList[%{#L.index}].statistic_kbn" value="statistic_kbn" list="statisticKbnList"  listKey="adm_num" listValue="adm_name"  headerKey="" headerValue="--选择状态----"/>
				             <input type="button" class="inp_L3 btnClass_${only_search}" onclick="updateStatistic(<s:property value='%{#L.index}'/>)" value="提 交   " name="submitBtn" />
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