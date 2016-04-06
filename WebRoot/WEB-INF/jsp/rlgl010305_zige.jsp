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
			    $("form").attr("action", "rlgl010306Zige.action");
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
        <table width="100%" border="0" cellspacing="0" cellpadding="0" ID="Table20">
          <tr> 
            <td height="28" style="padding-top:5px;" align="left"><font color="#1F6087" style="font-size:14px;"><strong>资格信息</strong></font></td>
          </tr>
            <tr>
                <td style="PADDING-TOP: 2px">
            <table cellSpacing="0" cellPadding="1" width="100%" border="1" ID="tab_zyzgxx" class="tabCss">
            <tr>
              <th height="28" width="11%" class="thTitleItrn">资格证编号</th>
              <th width="11%" class="thTitleItrn">发证机关</th>
              <th width="11%" class="thTitleItrn">发证日期</th>
              <th width="11%"class="thTitleItrn">专业类别</th>
              <th width="11%"class="thTitleItrn">专业级别</th>
              <th width="11%"class="thTitleItrn" style="display:none">执业范围</th>
              <th width="11%"class="thTitleItrn" style="display:none">变更记录</th>
              <th width="11%"class="thTitleItrn" style="display:none">执业考核记录</th>
            </tr>
            <s:iterator value="rlgl010302PractitionersInfoList" status='st'>
            <tr>
              <td height="25"><s:label id="init_rlgl010306PractitionersInfoList_%{#st.index }__certificate_no"><s:property value="certificate_no"/></s:label></td>
              <td><s:label id="init_rlgl010306PractitionersInfoList_%{#st.index }__issuing_authority"><s:property value="issuing_authority"/></s:label></td>
              <td><s:label id="init_rlgl010306PractitionersInfoList_%{#st.index }__issue_time"><s:property value="issue_time"/></s:label></td>
              <td><s:label id="pratypelist_%{#st.index }"><s:property value="type"/></s:label></td>
              <td><s:label id="pralevellist_%{#st.index }"><s:property value="level"/></s:label></td>
              <td style="display:none"><s:label id="init_rlgl010306PractitionersInfoList_%{#st.index }__area"><s:property value="area"/></s:label></td>
              <td style="display:none"><s:label id="init_rlgl010306PractitionersInfoList_%{#st.index }__upd_record"><s:property value="upd_record"/></s:label></td>
              <td style="display:none"><s:label id="init_rlgl010306PractitionersInfoList_%{#st.index }__assess_record"><s:property value="assess_record"/></s:label></td>
            </tr> 
             </s:iterator>
          </table>
                </TD>
            </TR>
        </table>
      </div>

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
