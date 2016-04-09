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
			    // 添加待审核状态
			    createReviewMark($("#reviewElement").val());
			    // 返回事件的定义
			    $("#btnBack").click(function(){
			          $("#modal-confirm").text("是否确认返回前画面？");
						$('#my-confirm').modal({
					        relatedTarget: this,
					        onConfirm: function(options) {
					        	backAction();
					        },
					        onCancel: function() {
					          return;
					        }
					      });
			      });
			});
			
		    function updatePersonnel() {
			    $("form").attr("action", "rlgl010306Dangpai.action");
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
            <td height="28" style="padding-top:5px;" align="left"><font color="#1F6087" style="font-size:14px;"><strong>党派信息</strong></font></td>
          </tr>
            <tr>
                <td style="PADDING-TOP: 2px">
            <TABLE cellSpacing="0" cellPadding="1" width="100%" border="1" ID="tab_dpxx" class="tabCss">
            <tr>
              <th height="28" width="30%" class="thTitleItrn">加入时间</th>
              <th width="30%" class="thTitleItrn">党派名称</th>
              <th width="30%" class="thTitleItrn">预备/正式</th>
            </tr>
		<s:iterator value="rlgl010302PartisanInfoList" status='st'>
            <tr>
              <td><s:label id="init_rlgl010306PartisanInfoList_%{#st.index }__jointime"><s:property value="jointime"/></s:label></td>
              <td><s:label id="init_rlgl010306PartisanInfoList_%{#st.index }__partisan_nm"><s:property value="partisan_nm"/></s:label></td>
              <td><s:label id="init_rlgl010306PartisanInfoList_%{#st.index }__prep_or_officially"><s:property value="prep_or_officially"/></s:label></td>
            </tr> 
 		</s:iterator>
          </table>
                </td>
            </tr>
        </table>
      </div>
	<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd">提示信息</div>
	    <div class="am-modal-bd" id="modal-confirm">
	    </div>
	    <div class="am-modal-footer">
	      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
	      <span class="am-modal-btn" data-am-modal-confirm>确定</span>
	    </div>
	  </div>
	</div>
<!-- 操作按扭 -->
<table width="70%" align="center" border="0" cellspacing="1" cellpadding="0">
<tr>
    <td height="50" align="center">
<s:if test="%{updateAction == null}">
<s:if test="%{personnel.personnel_isapproval.trim() != '001'}"> 
<input type="button" class="am-btn am-btn-danger" onClick="updatePersonnel();"value="修改"  id="btnUpdate" name="btnUpdate"/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</s:if>
</s:if>
<s:if test="%{backAction.trim() != ''}">
 <input type="button" class="am-btn am-btn-danger"  id="btnBack" value="返回" name="btnBack"/>
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
