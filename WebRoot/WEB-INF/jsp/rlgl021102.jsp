<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <sx:head/>
  <s:head/>
    <title><s:property value="getText('rlglsys.browserhead.IE')" /></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/WdatePicker.js"></script>
<%
response.addHeader("Cache-Control", "no-cache");
%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/TimeCSS/jquery-ui.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/JqueryTime/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/JqueryTime/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/JqueryTime/jquery-ui-slide.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/JqueryTime/jquery-ui-timepicker-addon.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/TimeCSS/picker-ui.css" type="text/css"></link>
<script type="text/javascript">
$(function(){
	$('#example_2').timepicker({});
	$('#example_3').timepicker({});
});

	$(document).ready(function(){
	   // 登录流程
	    $("#btnConfirm").click(function(){
	        var checkvalue = checkValue(); 
	        if(checkvalue){
	         $("#form").attr("action", "rlgl021102Submit.action");
	         $("#form").attr("namespace", "/rlgl");
             $("#form").submit();
             }
        });
    $("#btnback").click(function(){
      $("#form").attr("action", "rlgl021102Back.action");
	  $("#form").attr("namespace", "/rlgl");
      $("#form").submit();
    });
});
// 只能输入数字
function clearNoNum(obj) {
		obj.value = obj.value.replace(/[^\d.]/g, ""); //清除“数字”和“.”以外的字符  
		obj.value = obj.value.replace(/^\./g, ""); //验证第一个字符是数字而不是.  
		obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个. 清除多余的  
		obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace(
				"$#$", ".");
		obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3');//只能输入两个小数  
	}
	function  checkValue(){
	    var project_nm = $("#project_nm").val();
	    if(project_nm ==null || project_nm==""){
	     alert("请输入项目名称信息！");
	     return false;
	    }
	    var teach_people = $("#teach_people").val();
	    if(teach_people ==null || teach_people==""){
	      alert("请输入讲授人信息！");
	      return false;
	    }
	    var teach_place = $("#teach_place").val();
	    if(teach_place ==null || teach_place==""){
	      alert("请输入讲授地点信息！");
	      return false;
	    }
	    var total_hours = $("#total_hours").val();
	    if(total_hours ==null || total_hours==""){
	      alert("请输入总学时信息！");
	      return false;
	    }
	    
	    var teacher_credit = $("#teacher_credit").val();
	    if(teacher_credit ==null || teacher_credit==""){
	      alert("请输入授予讲师学分信息！");
	      return false;
	    }
	    var student_credit = $("#student_credit").val();
	    if(student_credit ==null || student_credit==""){
	      alert("请输入授予学员学分信息！");
	      return false;
	    }
	    
	    var teach_start_date =dojo.widget.byId("teach_start_date").getValue();
	    if(teach_start_date ==null || teach_start_date==""){
	      alert("请输入开始日期信息！");
	      return false;
	    }
	    var teach_start_time = $("#example_2").val();
	    if(teach_start_time ==null || teach_start_time==""){
	      alert("请输入开始时间信息！");
	      return false;
	    }
	    var teach_end_date =dojo.widget.byId("teach_end_date").getValue();
	    if(teach_end_date ==null || teach_end_date==""){
	      alert("请输入结束日期信息！");
	      return false;
	    }
	    var teach_end_time = $("#example_3").val();
	    if(teach_end_time ==null || teach_end_time==""){
	      alert("请输入结束时间信息！");
	      return false;
	    }
	    if(teach_start_date > teach_end_date ){
	    alert("开始日期大于结束日期，请重新输入！");
	    return false;
	    }
	    if(teach_start_date == teach_end_date && teach_start_time > teach_end_time){
	     alert("开始时间大于结束时间，请重新输入！");
	     return false;
	    }
	    return true;

	}
</script>
</head>
  <body><div>
  <s:form id="form" name="form" method="post" action="rlgl020401Init" nameSpace="/rlgl">
    <s:hidden name="navigationId" id="navigationId"/>
    <s:hidden name="screenId" id="screenId"/>
    <s:hidden name="only_search" id="only_search"/>
    <div id="searchInfo">
    <div class="content">
     <my:navigation></my:navigation>
      <my:message></my:message>
       <div style="position: relative">
        <table width="80%" style="position: relative; top: 10px;" border="1" cellpadding="2" cellspacing="2">
        <tr>
            <td width="15%" class="lc1">单位编号：<font color="red">*</font></td>
            <td>&nbsp;<s:property value='%{mtb91Info.unit_no}' />
            </td>
            <td width="15%" class="lc1">单位名称：<font color="red">*</font></td>
            <td>
              &nbsp;<s:property value='%{mtb91Info.unit_nm}' />
            </td>
          </tr>
          <tr>
            <td width="15%" class="lc1">项目名称：<font color="red">*</font></td>
            <td>
              <s:textfield id="project_nm" name="mtb91Info.project_nm" cssClass="put" maxLength="38" cssStyle="width:150px"></s:textfield>
            </td>
            <td width="15%" class="lc1">讲授人：<font color="red">*</font></td>
            <td>
              <s:textfield id="teach_people" name="mtb91Info.teach_people" cssClass="put" maxLength="24"></s:textfield>
            </td>
          </tr>
          <tr>
            <td width="15%" class="lc1">讲授地点：<font color="red">*</font></td>
            <td>
              <s:textfield id="teach_place" name="mtb91Info.teach_place" cssClass="put" maxLength="24" cssStyle="width:150px"></s:textfield>
            </td>
            <td width="15%" class="lc1">总学时：<font color="red">*</font></td>
            <td>
              <s:textfield id="total_hours" name="mtb91Info.total_hours"  cssClass="put" maxLength="3" onkeyup="clearNoNum(this)"></s:textfield>小时
            </td>
          </tr>
         <tr>
            <td width="15%" class="lc1">讲师学分：<font color="red">*</font></td>
            <td>
              <s:textfield id="teacher_credit" name="mtb91Info.teacher_credit" cssClass="put" maxLength="3" onkeyup="clearNoNum(this)"></s:textfield>分
            </td>
            <td width="15%" class="lc1">学员学分：<font color="red">*</font></td>
            <td>
              <s:textfield id="student_credit" name="mtb91Info.student_credit"  cssClass="put" maxLength="3" onkeyup="clearNoNum(this)"></s:textfield>分
            </td>
          </tr>
          <tr>
            <td width="15%" class="lc1">开始日期：<font color="red">*</font></td>
            <td>
            <sx:datetimepicker  type="date" name="mtb91Info.teach_start_date"  displayFormat="yyyy-MM-dd" id="teach_start_date"
            ></sx:datetimepicker>
            </td>
            <td width="15%" class="lc1">开始时间：<font color="red">*</font></td>
            <td>
            <input type="text" id="example_2" name="mtb91Info.teach_start_time"/>
            </td>
          </tr>
          <tr>
            <td width="15%" class="lc1">结束日期：<font color="red">*</font></td>
            <td>
              <sx:datetimepicker  type="date" name="mtb91Info.teach_end_date"  displayFormat="yyyy-MM-dd" id="teach_end_date"
            ></sx:datetimepicker>
            </td>
            <td width="15%" class="lc1">结束时间：<font color="red">*</font></td>
            <td>
             <input type="text" id="example_3" name="mtb91Info.teach_end_time"/></td>
          </tr>
          <tr>
          </tr>
          <tr>
            <td align="right" colspan="4">
            <input type="button" class="inp_L3 btnClass_${only_search}" value="提  交" name="btnConfirm" id="btnConfirm">&nbsp;
            <input type="button" class="inp_L3 btnClass_${only_search}" value="返  回" name="btnback" id="btnback">
            </td>
          </tr>
        </table>
      </div>
      </div>
  </div>
</s:form>
</div>
  </body>
</html>
