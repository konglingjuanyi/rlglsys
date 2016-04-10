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
       <script type="text/javascript" language="javascript"> 
       		function updateAction() {
			    $('input').removeAttr("disabled");
			    $("#btnUpdate").css('display','none'); 
				$("#btnCommit").css('display','block'); 
		    }
		    // 【提交】按钮的事件定义
			function commitAction() {
			    if(commitCheck()==false){
			    	return false;
			    }
			    $("form").attr("action", "rlgl010319Update.action");
			    $("form").submit();
		    }
		    function commitCheck(){
		    	// 手机号码验证
			    if(!checkInput($("#rlgl010319Update_personnel_personnel_tel").val())){
				    if(!checkPhoneNum($("#rlgl010319Update_personnel_personnel_tel").val())){
				    	$("#modal-alert").text("请输入正确的电话号码！");
						$('#my-alert').modal();
				    	$("#rlgl010319Update_personnel_personnel_tel").focus();
				    	return false;
				    }
			    }
			    return true;
		    }
		</script> 

    </HEAD>
    <body>
    <s:form action="rlgl010319Update" method="post" nameSpace="/rlgl" enctype="multipart/form-data">
    <s:hidden name="navigationId" id="navigationId"/>
    <s:hidden name="screenId" id="screenId"/>
    <s:hidden name="personnel_id" id="personnel_id"/>
   	<s:hidden name="only_search" id="only_search"/>
    <s:hidden name="personnel.personnel_id" id="personnel.personnel_id"/>
    <my:navigation></my:navigation>
    <div class="content">
    <my:message></my:message>
    <table width="100%"  border="0" cellspacing="0" cellpadding="0" >
      <tr>
        <td align="center" valign="top">
        <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
          <tr>
            <td>
    <!-- 基本信息 -->
    <table width="100%" border="0" cellspacing="0" cellpadding="0" >
        <tr>
            <td height="28" style="padding-top:5px;" align="left"><font color="#1F6087" style="font-size:14px;"><strong>联系方式</strong></font></td>
        </tr>
        <tr>
            <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="userBaseCss" style="border-color:#2484C0;">
                <tr valign="top">
                <td width="90%">
                 <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">姓名</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <s:label name="personnel.personnel_nm" ></s:label>
                      <s:hidden name="personnel.personnel_nm"  />
                      </td>
                      <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">身份证号</td>
                      <td width="35%" style="padding-left:6px;font-size:12px;">
                      <s:label name="personnel.personnel_card_id" ></s:label>
                      <s:hidden name="personnel.personnel_card_id"  />
                      </td>
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                    <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">手机</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <s:textfield cssClass="textphonenum" name="personnel.personnel_tel"  maxLength="11" ></s:textfield>
                      </td>
                      <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">邮箱</td>
                      <td width="35%" style="padding-left:6px;font-size:12px;">
                      <s:textfield cssClass="textEmail"  name="personnel.personnel_email"  maxLength="50" ></s:textfield>
                      </td>
			</tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                    <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">QQ</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                       <s:textfield  name="personnel.personnel_qq"  maxLength="50" ></s:textfield>
                      </td>
                      <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">微博</td>
                      <td width="35%" style="padding-left:6px;font-size:12px;">
                      <s:textfield  name="personnel.personnel_miblog"  maxLength="50" ></s:textfield>
                      </td>
                      </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                    <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">单位电话</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                       <s:textfield cssClass="textphonenum" name="personnel.personnel_officetel"  maxLength="50" ></s:textfield>
                      </td>
                      </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                  </table>
                </td>
                </tr>
            </table>
          </td>
        </tr>
      </table>
   		</td>
        </tr>
</table>


<!-- 操作按扭 -->
<table width="70%" align="center" border="0" cellspacing="1" cellpadding="0">
<tr>
    <td height="50" align="center">
 <input type="button" class="am-btn am-btn-danger " onClick="commitAction()" width="100px" value="提交" name="btnCommit" id="btnCommit"/>
 <input type="button" class="am-btn am-btn-danger " onClick="updateAction()" width="100px" value="修改" name="btnUpdate" id="btnUpdate"/>

</td>
</tr>
</table>

          </td>
          </tr>
        </table>
</div>
    </s:form>
    </body>
    <script type="text/javascript" language="javascript"> 
       		$('input').attr("disabled","disabled");
       		$("#btnUpdate").removeAttr("disabled");
			$("#btnCommit").removeAttr("disabled");
       		$("#btnUpdate").css('display','block'); 
			$("#btnCommit").css('display','none'); 
	</script>
</html>
