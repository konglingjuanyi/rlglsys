<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="global.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><s:property value="getText('rlglsys.browserhead.IE')" />
    </title>
<script language="JavaScript">
 var tabs;
$(document).ready(function(){
     $(".admsearch").click(function(){
        $("#course_catagory").val($(this).next().val());
        $("form").attr("action", "coursInit_jn.action");
        $("form").attr("target","contents1");
        $("form").submit();
     });
});

</script>

  </head>
  <body>
   <s:form  action="coursware_jn" nameSpace="/rlgl" target="contents1" method="POST">
        <s:hidden name="course_catagory" id="course_catagory"/>
        <s:hidden name="distinguish" id="distinguish" value="1"/>
		<table width="100%" style="position: relative;"   class=" am-table am-table-bordered am-table-radius am-table-striped am-table-hover "
							cellpadding="0" cellspacing="0">
				<thead><tr>
					<th  colspan="2">&nbsp;&nbsp;&nbsp;培训课程列表2：</th>
				</tr>
				</thead>
			<s:iterator value="courseList" status="L">
			   <tr >
			   
			    <td>&nbsp;&nbsp;<a href="#" class="admsearch" style="font-size:12px"><s:property value='adm_name'/></a>
			    		<!-- 课件来源 -->
			    		<s:hidden name="adm_num"/></td>
			   </tr>
			</s:iterator>
		</table>
	</s:form>
  </body>
</html>
