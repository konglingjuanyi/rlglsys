<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <TITLE>
              日照医学教育
     </TITLE>
<script type="text/javascript" language="javascript"> 
$(document).ready(function(){
     // 查询按钮
     $("#btnSearch").click(function(){
         $("#form").attr("action", "rlgl060301Search.action");
         $("#form").submit();
     });
     // 报名按钮
     $("#btnSignUp").click(function(){
         $("#form").attr("action", "rlgl060301SignUp.action");
         $("#form").submit();
     });
     // 选中条数取得
     $(".comcheckbox").click(function(){
          var sum=0;
          $("input[id='select_kbn_check']").each(function(idx, obj) {
          if(this.checked){
            sum+=parseInt(1);
           }});
          butdis(sum);
     });
     
});
// 报名按钮的控制
function butdis(sum)
{
   if(sum>0){
      $("#btnSignUp").attr("disabled",false);}
   else{
      $("#btnSignUp").attr("disabled",true);}
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
          <form  id="form" nameSpace="/rlgl" action="doLogin" method="post" >
              <s:hidden name="navigationId" id="navigationId"/>
          	  <s:hidden name="screenId" id="screenId"/>
	          <table width="100%" valign="top" border="1px">
	          <caption>查询条件</caption>
	            <tr>
	              <td class='lc1'>考试名称</td>
	              <td class='lc2'><s:select name="rlgl060301Bean.exam_no" list="examNameList" listKey="exam_no" listValue="examination_name" headerValue="--请选择--" headerKey="" style="width:300px"/></td>
	            </tr>
	            <tr>
		            <td align="right" colspan="2">
		            <input type="button" class="inp_L3" value="查询" name="btnSearch" id="btnSearch">
		            <input type="button" class="inp_L3" value="报名" name="btnSignUp" id="btnSignUp" disabled>
		            </td>
		        </tr>
	          </table>
            <div id="result" style="position: relative;top:20px">
            <my:dividepage actionId="rlgl060301Search.action"></my:dividepage>
	        <table width="100%" valign="top" border="1px">
              <tr>
                <th class='thTitleItrn'>序号</th>
                <th class='thTitleItrn'>科目种类</th>
                <th class='thTitleItrn'>试题名称</th>
                <th class='thTitleItrn'>发起单位</th>
                <th class='thTitleItrn'>考试地点</th>
                <th class='thTitleItrn'>报名开始日期</th>
                <th class='thTitleItrn'>报名结束日期</th>
                <th class='thTitleItrn'>考试开始日期</th>
                <th class='thTitleItrn'>考试结束日期</th>
                <th class='thTitleItrn'>考试时间</th>
                <th class='thTitleItrn'>选择</th>
              </tr>
              <s:iterator value="examSettingList" status='L'>
                  <s:hidden name="examSettingList[%{#L.index}].exam_no" id="exam_no"/>
                  <s:hidden name="examSettingList[%{#L.index}].subject_kb" id="subject_kb"/>
                  <s:hidden name="examSettingList[%{#L.index}].examination_name" id="examination_name"/>
                  <s:hidden name="examSettingList[%{#L.index}].start_unit" id="start_unit"/>
                  <s:hidden name="examSettingList[%{#L.index}].exam_place" id="exam_place"/>
	              <my:trStripe index="${L.index}">
	                <td class="tdc">${L.index +1}</td>
	                <td class="tbl">
	                    <s:property value='subject_name'/></td>
	                <td class="tbl">
	                    <s:property value='examination_name'/></td>
                    <td class="tbl">
                    	<s:property value='start_unit_name'/></td>
	                <td class="tbl">
	                    <s:property value='exam_place'/></td>
	                <td class="tbl">
	                    <s:property value='apply_start_date'/></td>
	                <td class="tbl">
	                    <s:property value='apply_end_date'/></td>
	                <td class="tbl">
	                    <s:property value='exam_start_date'/></td>
	                <td class="tbl">
	                    <s:property value='exam_end_date'/></td>
                    <td class="tbl">
                    	<s:property value='exam_time'/>分钟</td>
	                <td class="tdc">
	                    <s:checkbox cssClass="comcheckbox" name="ishere" id="select_kbn_check"  fieldValue="%{#L.index }"  />      
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