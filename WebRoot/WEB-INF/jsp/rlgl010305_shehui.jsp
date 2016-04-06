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
			    $("form").attr("action", "rlgl010306Init.action");
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
<!--               <strong>  -->
<%-- 				<s:if test="%{personnel.personnel_isapproval == '000'}"> --%>
<!-- 				          您的信息还未提交申请，请提交申请！ -->
<%--                 </s:if> --%>
<%--                 <s:if test="%{personnel.personnel_isapproval == '001'}"> --%>
<!--     				您的信息正在审核中！ -->
<%--                 </s:if> --%>
<%--                  <s:if test="%{personnel.personnel_isapproval == '002'}"> --%>
<!--     				您的信息已通过审核！ -->
<%--                 </s:if> --%>
<%--                  <s:if test="%{personnel.personnel_isapproval == '003'}"> --%>
<!--    					您的信息申请已被驳回！ -->
<%--                 </s:if> --%>
<!--     		</strong> -->
            
<!--             </font></td> -->
        </tr>
      <tr>
        <td align="center" valign="top">
        <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
          <tr>
            <td>
   
     <div>
        <TABLE cellSpacing="0" cellPadding="0" width="100%" border="0" ID="">
            <tr><td style="padding-top:5px;" height="28" align="left"><FONT color="#1F6087" style="font-size:14px;"><STRONG>社会关系</STRONG></FONT></td>
            </tr>
            <tr>
                <td style="PADDING-TOP: 2px">
            <TABLE cellSpacing="0" cellPadding="1" width="100%" border="1" ID="tab_shgl" class="tabCss">
            <tr>
              <th height="28" width="14%" class="thTitleItrn">与本人关系</th>
              <th width="14%" class="thTitleItrn">姓名</th>
              <th width="14%" class="thTitleItrn">出生日期</th>
              <th width="13%" class="thTitleItrn">政治面貌</th>
              <th width="15%" class="thTitleItrn">工作单位</th>
              <th width="10%" class="thTitleItrn">职务</th>
              <th width="10%" class="thTitleItrn">电话</th>
            </tr>
<s:iterator value="rlgl010302SocialInfoList" status='st'>
            <tr>
              <td height="25" width="14%"><s:label id="init_rlgl010306SocialInfoList_%{#st.index }__relationship"><s:property value="relationship"/></s:label></td>
              <td width="14%"><s:label id="init_rlgl010306SocialInfoList_%{#st.index }__name"><s:property value="name"/></s:label></td>
              <td width="14%"><s:label id="init_rlgl010306SocialInfoList_%{#st.index }__birthday"><s:property value="birthday"/></s:label></td>
              <td width="13%"><s:label id="init_rlgl010306SocialInfoList_%{#st.index }__political_landscape"><s:property value="political_landscape"/></s:label></td>
              <td width="15%"><s:label id="init_rlgl010306SocialInfoList_%{#st.index }__workunit"><s:property value="workunit"/></s:label></td>
              <td width="10%"><s:label id="init_rlgl010306SocialInfoList_%{#st.index }__position"><s:property value="position"/></s:label></td>
              <td width="10%"><s:label id="init_rlgl010306SocialInfoList_%{#st.index }__tel"><s:property value="tel"/></s:label></td>
            </tr>
 </s:iterator>
        </table>
                </td>
            </tr>
        </TABLE>
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
    <script type="text/javascript" language="javascript"> 
    $("#showImgFile").attr("src", $("#showImgFile")[0].src+"?rand="+new Date().getTime());
    </script>
    </body>
</html>
