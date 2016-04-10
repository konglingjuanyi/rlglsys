<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="global.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><s:property value="getText('rlglsys.browserhead.IE')" />
</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/WdatePicker.js"></script>

<%
	response.addHeader("Cache-Control", "no-cache");
%>

<script language="JavaScript">
$(document).ready(function(){
	if($("#payMentFlag").val() == "true")
	{
		$("#btnApply").attr("disabled","disabled");
	}
	
	$('#btnDelete').on('click', function() {
      	$('#my-confirm').modal({
        relatedTarget: this,
        onConfirm: function(options) {
        	$("form").attr("action", "rlgl020601Delete.action");
            $("form").submit();
        },
        onCancel: function() {
          return;
        }
      });
    });
});
function deleteNo(applyNo) {
    $("#apply_No").val(applyNo);
}
function applyenter(naviId) {
	$("#navi_Id").val(naviId);
    // 录入申请
    $("form").attr("action", "rlgl020602Init.action");
    $("form").submit();
}
</script>

</head>

<body>
	<div>
		<s:form name="form1" method="post" action="rlgl020601Init"
			nameSpace="/rlgl">
			<s:hidden name="navigationId" id="navigationId" />
			<s:hidden name="apply_No" id="apply_No" />
			<s:hidden name="navi_Id" id="navi_Id" />
			<s:hidden name="only_search" id="only_search"/>
			<s:hidden name="payMentFlag" id="payMentFlag"/>
			<my:navigation></my:navigation>
					<my:message></my:message>
			<div class="content">
				<div id="searchInfo">
					<div style="position:relative;hight:100px">
					<my:dividepage actionId="rlgl020601Init.action"></my:dividepage>
						<table width="98%"
							style="position: relative; top: 10px; left: 10px" border="1"
							cellpadding="2" cellspacing="2">
							<tr>
								<td>
								<input type="button" class="am-btn am-btn-primary" onclick="applyenter('<s:property value='naviId'/>')" value="录入申请" name="btnApply" id="btnApply"/>
								</td>
							</tr>
							<tr>
								<th height="28" width="5%" class="thTitleItrn">序号</th>
								<s:iterator value="formList" id="outFormList" status="status">
									<th height="28" class="thTitleItrn"><s:property
											value='formList[#status.index].item_name' />
									</th>
								</s:iterator>
								<th height="28" width="10%" class="thTitleItrn">操作</th>
							</tr>
							<s:iterator value="rlgl020601List" id="outApply" status="status">
								<my:trStripe index="${status.index}">
									<td class="tdc">
									  ${status.index +1}
									</td>
									<td height="20" class="lc2"><s:property
											value='rlgl020601List[#status.index].credit' /></td>
									<td height="20" class="lc2"><a href="javascript:void(0)"
										onclick="return showApplyInfo('<s:property value='rlgl020601List[#status.index].flow'/>','<s:property value='rlgl020601List[#status.index].stutas'/>')">
											<s:property
												value='rlgl020601List[#status.index].check_result' /> </a>
									</td>
									<s:iterator value="rlgl020601List[#status.index].expandInfo"
										id="outExpandInfo" status="expandStatus">
										<s:if
											test="rlgl020601List[#status.index].expandInfo[#expandStatus.index] != ''">
											<td height="20" class="lc2"><s:property
													value='rlgl020601List[#status.index].expandInfo[#expandStatus.index]' />
											</td>
										</s:if>
									</s:iterator>
									<td height="20" class="lc2"><s:if
											test="rlgl020601List[#status.index].end_mark == 0">
												<button  type="button"  class="am-btn am-btn-warning"  id="btnDelete" onclick="deleteNo('<s:property value='rlgl020601List[#status.index].apply_no'/>')"
												name="btnDelete">删除</button>
										</s:if> <s:else>
											<button  type="button"  class="am-btn am-btn-warning"  id="btnDelete" onclick="deleteNo('<s:property value='rlgl020601List[#status.index].apply_no'/>')"
											name="btnDelete" disabled="true">删除</button>
										</s:else>
									</td>
									<div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
									  <div class="am-modal-dialog">
									    <div class="am-modal-hd">提示信息</div>
									    <div class="am-modal-bd">
									      	是否确认删除这条信息？
									    </div>
									    <div class="am-modal-footer">
									      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
									      <span class="am-modal-btn" data-am-modal-confirm>确定</span>
									    </div>
									  </div>
									</div>
								</my:trStripe>
							</s:iterator>
						</table>
					</div>
				</div>
			</div>
		</s:form>
	</div>
</body>
</html>
