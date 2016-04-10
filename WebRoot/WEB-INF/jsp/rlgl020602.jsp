<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="global.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title><s:property value="getText('rlglsys.browserhead.IE')" />
</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/WdatePicker.js"></script>
<script language="JavaScript">
$(document).ready(function(){
    $(":text,:radio,textarea,select").each(function() {
        $(this).attr("id",$(this).parent().children().val());
        $(this).attr("name", "rlgl020602." + $(this).parent().children().val());
    });
    // 提交按钮
    $("#Btnsubmit").click(function(){
    	var i = 0;
		$(":text,:radio,textarea,select").each(function() {
		  if ($(this).val() == "") {
		  	i ++;
		  }
		});
	    if (i > 0)
	    {
	    	$("#modal-alert").text("请填写必须输入项目！");
			$('#my-alert').modal();
		    return false;
	    }
	    $("#creditForm").attr("action", "rlgl020602Submit.action");
	    $("#creditForm").submit();

    });
    // 返回按钮
    $("#Btnback").click(function(){
	    $("#creditForm").attr("action", "rlgl020602Back.action");
	    $("#creditForm").submit();
    });
});

	// 刊物选择按钮
    function selectChange(publicationChange) {
     
    	var params=
    	{
          "params":publicationChange.value
         };
        $.post("publication.action",params, callbackOfPublication);
      
    }
    function callbackOfPublication(result) {
    	if (result != "") {
    		var resultSplit = result.split(",");
	    	$("#expand5").html(resultSplit[0]);
	    	$("#expand6").html(resultSplit[1]);
	    	$("#expand7").html(resultSplit[2]);
	    	$("#expand8").html(resultSplit[3]);
	    	$("#expand9").html(resultSplit[4]);
    	} else {
    		$("#expand5").html("");
	    	$("#expand6").html("");
	    	$("#expand7").html("");
	    	$("#expand8").html("");
	    	$("#expand9").html("");
    	}
    }
    // 检查日期大小(开始时间，结束时间，错误信息)
    function checkStartDateToEndDate(date1,date2,alertMsg) {
        // 开始日期
        var s_date_arys = $("#" + date1).val().split('-');
        var s_date = new Date(s_date_arys[0], s_date_arys[1], s_date_arys[2]);
        // 结束日期
        var e_date_arys = $("#" + date2).val().split('-');
        var e_date = new Date(e_date_arys[0], e_date_arys[1], e_date_arys[2]);
        
        // 如果开始日期大于结束日期
        if(s_date > e_date) {
            // 提示错误信息
            alertMessage(alertMsg);
        }
    }
</script>
</head>

<body>
	<s:form name='creditForm'  id='creditForm' action="rlgl020602Submit" nameSpace="/rlgl">
		<s:hidden name="navigationId" id="navigationId"/>
		<s:hidden name="navi_Id" id="navi_Id" />
		<s:hidden name="params" id="params" />
		<s:hidden name="only_search" id="only_search"/>
		<my:navigation></my:navigation>
		<my:message></my:message>
		<table width="50%" style="position: relative; top: 10px; left: 10px" border="1"
							cellpadding="2" cellspacing="2">

			<s:iterator value="formList" id="outFormList" status="status">
				<tr>
				<td class='lc1'><s:property
						value='formList[#status.index].item_name' /><font color="red">*</font></td>
				<s:if test="formList[#status.index].item_kind == 0">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:textfield id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}" 
						cssClass="%{formList[#status.index].check_id}"
						value="" size="5" maxLength="4"
						style="width:80px" />
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 1">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:radio id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}"
						list="#timeList" 
						listKey="adm_num" 
						listValue="adm_name"
						value="'001'"
						theme="simple" />
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 2">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:textfield  name="" style="width:80px" maxLength="20" onblur="%{formList[#status.index].check_id}" onClick="WdatePicker()" readonly="true"/>
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 3">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:textfield id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}" 
						cssClass="%{formList[#status.index].check_id}"
						value="" size="5" maxLength="50"
						style="width:220px" />
						
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 4">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:radio id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}" 
						list="#creditList" 
						listKey="adm_num" 
						listValue="adm_name"
						value="'001'"
						theme="simple" />
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 5">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:select id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}" 
						list="#country" 
						listKey="adm_num" 
						listValue="adm_name"
						headerValue="-----------请选择-----------"
						headerKey=""
						theme="simple"></s:select>
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 6">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:select id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}" 
						list="#city" 
						listKey="adm_num" 
						listValue="adm_name"
						headerValue="-----------请选择-----------"
						headerKey=""
						theme="simple"></s:select>
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 7">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:select id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}" 
						list="#areaLevel" 
						listKey="adm_num" 
						listValue="adm_name"
						headerValue="-----------请选择-----------"
						headerKey=""
						theme="simple"></s:select>
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 8">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:select id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}" 
						list="#winnerLevel" 
						listKey="adm_num" 
						listValue="adm_name"
						headerValue="-----------请选择-----------"
						headerKey=""
						 theme="simple" ></s:select>
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 9">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:select id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}" 
						list="#participateLevel" 
						listKey="adm_num" 
						listValue="adm_name"
						headerValue="-----------请选择-----------"
						headerKey=""
						theme="simple" ></s:select>
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 10">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:textarea id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}" 
						onkeyup="this.value = this.value.slice(0, 255)"></s:textarea>
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 11">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:select id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}" 
						list="#topicLevel" 
						listKey="adm_num" 
						listValue="adm_name"
						headerValue="-----------请选择-----------"
						headerKey=""
						 theme="simple" ></s:select>
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 12">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:select id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}" 
						list="#takersLevel" 
						listKey="adm_num" 
						listValue="adm_name"
						headerValue="-----------请选择-----------"
						headerKey=""
						 theme="simple" ></s:select>
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 13">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:select id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}" 
						list="#methods" 
						listKey="adm_num" 
						listValue="adm_name"
						headerValue="-----------请选择-----------"
						headerKey=""
						 theme="simple" ></s:select>
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 15">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:select id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}" 
						list="#topicKind" 
						listKey="adm_num" 
						listValue="adm_name"
						headerValue="-----------请选择-----------"
						headerKey=""
						 theme="simple" ></s:select>
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 16">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:select id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}" 
						list="#participation" 
						listKey="adm_num" 
						listValue="adm_name"
						headerValue="-----------请选择-----------"
						headerKey=""
						theme="simple" ></s:select>
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 17">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:select id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}" 
						list="#phases" 
						listKey="adm_num" 
						listValue="adm_name"
						headerValue="-----------请选择-----------"
						headerKey=""
						theme="simple" ></s:select>
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 18">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:select id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}" 
						list="#participate" 
						listKey="adm_num" 
						listValue="adm_name"
						headerValue="-----------请选择-----------"
						headerKey=""
						theme="simple" ></s:select>
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 19">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:select id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}" 
						onchange="selectChange(this)"
						list="#publication" 
						listKey="publication_cd" 
						listValue="publication_name"
						headerValue="-----------请选择-----------"
						headerKey=""
						theme="simple" ></s:select>
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 20">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:label id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}"></s:label>
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 21">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:select id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}" 
						list="course" 
						listKey="course_id" 
						listValue="course_name"
						headerValue="-----------请选择-----------"
						headerKey=""
						theme="simple" ></s:select>
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 22">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:select id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}" 
						list="#train" 
						listKey="adm_num" 
						listValue="adm_name"
						headerValue="-----------请选择-----------"
						headerKey=""
						theme="simple" ></s:select>
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 23">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:select id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}" 
						list="#patentCategory" 
						listKey="adm_num" 
						listValue="adm_name"
						headerValue="-----------请选择-----------"
						headerKey=""
						theme="simple" ></s:select>
					</td>
				</s:if>
				<s:if test="formList[#status.index].item_kind == 24">
					<td>&nbsp;
						<s:hidden name="item_id" id="item_name"/>
						<s:select id="%{formList[#status.index].item_id}"
						name="%{formList[#status.index].item_id}" 
						list="#patentPlaces" 
						listKey="adm_num" 
						listValue="adm_name"
						headerValue="-----------请选择-----------"
						headerKey=""
						theme="simple" ></s:select>
					</td>
				</s:if>
				</tr>
			</s:iterator>
			<tr>
				<td align="center" colspan="2">&nbsp;
					 <input type="button" class="am-btn am-btn-danger"   value="提交审核" name="Btnsubmit" id="Btnsubmit"/>
					 &nbsp;
					 <input type="button" class="am-btn am-btn-danger"  value="返回" name="Btnback" id="Btnback"/>
					 &nbsp;
				 </td>
			</tr>
		</table>
	</s:form>
	<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd">提示信息</div>
	    <div class="am-modal-bd"  id="modal-alert">
	    </div>
	    <div class="am-modal-footer">
	      <span class="am-modal-btn">确定</span>
	    </div>
	  </div>
	</div>
</body>
</html>
