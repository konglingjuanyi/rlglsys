<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><s:property value="getText('rlglsys.browserhead.IE')" /></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/WdatePicker.js"></script>
<%
response.addHeader("Cache-Control", "no-cache");
%>

<script language="JavaScript">
// 查询按钮的提交
   $(document).ready(function(){
    $("#btnSearch").click(function(){ 
     $("#form1").attr("action", "rlgl021102Search.action");
     $("#form1").attr("namespace", "/rlgl");
     $("#form1").submit();
     });
   }); 
  //checkbox单选
	function checkchange(cb) {
		var obj = $("input[name='ckx_course_id']");
		if (cb.checked == false) {
			cb.checked = false;
		} else {
			for (i = 0; i < obj.length; i++) {
				if (obj[i] != cb)
					obj[i].checked = false;
				else
					obj[i].checked = true;
			}
		}
	}
//修改超链接的点击事件
function linkToModify(flag){

 var selectKbn = "";
    var i = 0;
    // 遍历checkBOx
    var ckx_course = $("input[id='ckx_course_id']");
    for(i=0;i<ckx_course.length;i++)
    {
      if(ckx_course[i].checked){
       selectKbn = ckx_course[i].value;
       break;
      }
    }
     
    if(selectKbn =="")
    {
      alert("请先选择一条数据！");
    } else{
      if(flag == "xiugai"){
       window.location.href ="${pageContext.request.contextPath}/rlgl/rlgl021102Modify.action?flag=xiugai&arrly_no="+selectKbn;
      }
      if(flag == "xiangxi"){
      window.location.href ="${pageContext.request.contextPath}/rlgl/rlgl021102Modify.action?flag=xiangxi&arrly_no="+selectKbn;
      }
    }
}
</script>
  </head>
  <body><div>
  <s:form name="form1" id="form1" method="post" action="rlgl090601Search" nameSpace="/rlgl">
    <s:hidden name="navigationId" id="navigationId"/>
    <s:hidden name="screenId" id="screenId"/>
    <div id="content">
     <my:navigation></my:navigation>
      <my:message></my:message>
      <div style="position: relative">
        <table width="70%" style="position: relative; top:10px;" border="1" cellpadding="2" cellspacing="2">
          <tr>
            <td class="lc1">项目名称：<s:textfield name="project_nm" maxLength="10" width="8"/></td>
            <td class="lc1">单位名称：&nbsp;<s:property value='%{mtb91Info.unit_nm}'/></td>
          </tr>
             <tr>
             <td align="right" colspan="2">
              <input type="button" class="inp_L3" value="查询" name="btnSearch" id="btnSearch">
            </td>
             </tr>
        </table>
      </div>
       <table border="0" width="90%" cellpadding="2" style="position: relative; top:10px;">
       <tr>
         <td align="right">
          <a href="${pageContext.request.contextPath}/rlgl/rlgl021102Init.action">新建</a>&nbsp;
          <a id="hrefModify" onclick="linkToModify('xiugai')">修改</a>&nbsp;
          <a id="hrefModify" onclick="linkToModify('xiangxi')">详细</a>&nbsp;
          <s:hidden name="only_search" id="only_search"/>
          </td>
       </tr>
        </table>
      <table border="1" width="100%" cellpadding="2" style="position: relative; top:8px;">
        <tr><td colspan=7>
             <my:dividepage actionId="rlgl021102Search.action"></my:dividepage>
            </td>
         </tr>
        <tr> 
          <th height="28" width="3%" class="thTitleItrn">选择</th> 
          <th height="28" width="3%" class="thTitleItrn">序号</th>
          <th height="28" width="20%" class="thTitleItrn">项目名称</th>
          <th height="28" width="12%" class="thTitleItrn">授课人</th>
          <th height="28" width="10%" class="thTitleItrn">开始时间</th>
          <th height="28" width="10%" class="thTitleItrn">结束时间</th>
          <th height="28" width="10%" class="thTitleItrn">审核状态</th>
        </tr>
       <s:iterator value="projectList" status="L" id="course">
        <my:trStripe index="${L.index}">
              <td height="20" class="tdc"><input type="checkbox" id="ckx_course_id" name="ckx_course_id" value="${course.arrly_no}" onclick="checkchange(this)"></input></td>
              <td height="20" class="tdc">
              ${L.index +1}
              </td>
              <td height="20" class="tdc">
              <s:property value='project_nm'/>
              </td>
              <td height="20" class="tdc">
              <s:property value='teach_people'/>
              </td>
              <td height="20" class="tdc">
              <s:property value='teach_start_date'/>
              </td>
              <td height="20" class="tdc">
              <s:property value='teach_end_date'/>
              </td>
             <td height="20" class="tdc">
              <s:property value='audit_status_name'/>
              </td>
         </my:trStripe>
       </s:iterator>
        </table>
      </div>
      </s:form>
      <br/>
  </div>
  </body>
</html>
