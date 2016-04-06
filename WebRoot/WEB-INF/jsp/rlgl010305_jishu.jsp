<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <title><s:property value="getText('rlglsys.browserhead.IE')" /></title>
    <%
response.addHeader("Cache-Control", "no-cache");
	%>
       <script type="text/javascript" language="javascript"> 
			$(document).ready(function(){
			// 图片
				$("[id^=showImgFile]").each(function(e){
			        var w = $(this).width();
			        var h = $(this).height();
			        if( w >=141){
			            $(this).width(141);
			        }
			        if( h >=200){
			            $(this).height(200);
			        }
			      });
			      $("[id^=showImgFile]").each(function(e){
			        var w = $(this).width();
			        var h = $(this).height();
			        if( w >=141){
			            $(this).width(141);
			        }
			        if( h >=200){
			            $(this).height(200);
			        }
			      });
			    // 添加待审核状态
			    createReviewMark($("#reviewElement").val());
			    // 返回事件的定义
		        $("#btnBack").click(function(){
		            confirmMessage("CM005", backAction);
		        });
			});
			function close(){
				confirmMessage("CM015", doClose);
		    }
		    function doClose(){
		    	window.close();
		    }
		    function updatePersonnel() {
			    $("form").attr("action", "rlgl010306Jishu.action");
				$("form").submit();
			}
			
	        function backAction() {
	        	$("#backFlag").val("1");
	       		$("form").attr("action", $("#backAction").val());
			    $("form").submit();
            }
		</script> 

    </HEAD>
    <body>
    <s:form name="init" id="init" action="rlgl010306Init"  method="post" nameSpace="/rlgl">
    <s:hidden name="navigationId" id="navigationId"/>
    <s:hidden name="screenId" id="screenId"/>
    <s:hidden name="reviewElement" id="reviewElement"/>
    <s:hidden name="backAction" id="backAction"/>
    <s:hidden name="backFlag" id="backFlag"/>
    <!-- test begin -->
    <s:hidden name="personnel_id" id="personnel_id"/>
    <!-- test end -->
 <div class="content">
    <my:navigation></my:navigation>
    <table width="100%"  border="0" cellspacing="0" cellpadding="0">
    <tr>
            <td align="center"><font color="#0066CC" style="font-size:14px;">
        </tr>
      <tr>
        <td align="center" valign="top">
        <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
          <tr>
            <td>
   
     <div>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" ID="Table11">
        <tr>
            <td height="28" style="padding-top:5px;" align="left"><font color="#1F6087" style="font-size:14px;"><strong>专业技术职务信息</strong></font></td>
        </tr>
        <tr>
            <td style="padding-top:2px;">
            <table cellSpacing="0" cellPadding="1" width="100%" border="1" ID="tab_zyjszwxx" name="tab_zyjszwxx" class="tabCss">
            <tr>
              <th height="28" width="25%" class="thTitleItrn">级别</th>
              <th width="15%" class="thTitleItrn">名称</th>
              <th width="15%" class="thTitleItrn">审批机关</th>
              <th width="15%" class="thTitleItrn">取得时间</th>
            </tr>
            <tr>
   <s:iterator value="rlgl010302ProfessionalInfoList" status='st'>
            <tr>
            <td width="15%">
            <s:label id="onelevel_%{#st.index }"><s:property value="onelevel"/></s:label>
            <s:label id="twolevel_%{#st.index }"><s:property value="twolevel"/></s:label>
            <s:label id="threelevel_%{#st.index }"><s:property value="threelevel"/></s:label>
            </td>
            <td width="15%"><s:label id="init_rlgl010306ProfessionalInfoList_%{#st.index }__name"><s:property value="name"/></s:label></td>
            <td width="15%"><s:label id="init_rlgl010306ProfessionalInfoList_%{#st.index }__original"><s:property value="original"/></s:label></td>
            <td width="15%"><s:label id="init_rlgl010306ProfessionalInfoList_%{#st.index }__get_time"><s:property value="get_time"/></s:label></td>
            </tr>
        </s:iterator>
            </tr>
        </table>
        </td>
      </tr>
    </table></div>


<!-- 操作按扭 -->
<table width="70%" align="center" border="0" cellspacing="1" cellpadding="0">
<tr>
    <td height="50" align="center">
<s:if test="%{updateAction == null}">
<s:if test="%{personnel.personnel_isapproval.trim() != '001'}"> 
<input type="button" name="btnUpdate" id="btnUpdate" class="inp_L3" value="修改" onClick="updatePersonnel();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</s:if>
</s:if>
<s:if test="%{backAction.trim() != ''}">
 <input type="button" class="inp_L3" value="返回" name="btnBack" id="btnBack">
</s:if>
</td>
</tr>
</table>

          </td>
          </tr>
        </table>
        </td>
      </tr>
    </table>
    </div>
    </s:form>
    </body>
</html>
