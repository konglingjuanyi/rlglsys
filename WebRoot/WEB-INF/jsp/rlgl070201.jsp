<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <TITLE>
              日照医学教育
     </TITLE>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/WdatePicker.js"></script>
<script language="JavaScript">
$(document).ready(function(){
  
     //发表申请按钮按下
     $("#btnSubmit").click(function(){
    	 
         if (Trim($("#rlgl070202Bean_apply_unit_nm").val()) == "")
         {
            alertMessage("AM117");
            $("#user_id").val(Trim($("#user_id").val()));
            $("#rlgl070202Bean_apply_unit_nm").focus();
            return false;         
         }
         
         if (Trim($("#rlgl070202Bean_apply_person_nm").val()) == "")
         {
            alertMessage("AM118");
            $("#rlgl070202Bean_apply_person_nm").val(Trim($("#rlgl070202Bean_apply_person_nm").val()));
            $("#rlgl070202Bean_apply_person_nm").focus();
            return false;         
         }
         
         if(Trim($("#rlgl070202Bean_apply_person_tel").val()) == ""){
        	 alertMessage("AM119");
        	 return false; 
        	 
         } else if(!checkInput($("#rlgl070202Bean_apply_person_tel").val())){
			    if(!checkPhoneNum($("#rlgl070202Bean_apply_person_tel").val())){
			    	alertMessage("AM017");
			    	$("#rlgl070202Bean_apply_person_tel").focus();
			    	return false;
			    }
		    }
	    	
		    // 提交
		   var m = 0;
            $("input[id='select_kbn_check']").each(function(idx, obj) {
	            if (this.checked == true)
	            {
	                 m++;
	            }
        	});
	        if (m == 0)
	        {
	            alertMessage("AM120");
	            return false;
	        }
	        confirmMessage("CM138", updateSection);
        
     });
     
     // 全选
     $("#btnSelectAll").click(function(){
         $("input[id='select_kbn_check']").each(function(idx, obj) {
           this.checked = true;
          });
     });
     // 反选
     $("#btnSelectNo").click(function(){
         $("input[id='select_kbn_check']").each(function(idx, obj) {
           this.checked = false;
          });
     });
});

function updateSection() {
    var selectKbn = "";
    var i = 0;
    // 遍历checkBOx
    $("input[id='select_kbn_check']").each(function(idx, obj) {
        if (i == 0)
        {
            if (this.checked == true)
            {
                selectKbn = selectKbn + "1";
            }
            else
            {
                selectKbn = selectKbn + "0";
            }
        }
        else
        {
            if (this.checked == true)
            {
                selectKbn = selectKbn + ",1";
            }
            else
            {
                selectKbn = selectKbn + ",0";
            }
        }
        i++;
    });

        // 给隐藏变量赋值
    $("#rlgl070202Bean_user_to_check").val(selectKbn);
    $("#rlgl070202Bean_unit_no").val(Trim($("#rlgl070202Bean_unit_no").val()));

    $("#form1").attr("action", "rlgl070201Submit.action");
    $("#form1").submit();
}

</script>    
</head>
  <body><div>
  <s:form id="form1" name="form1" method="post" action="rlgl070201Init" nameSpace="/rlgl">
      <s:hidden name="rlgl070202Bean.user_to_check" id="rlgl070202Bean_user_to_check"/>
      <s:hidden name="rlgl070202Bean.unit_no" id="rlgl070202Bean_unit_no"/>
      <s:hidden name="rlgl070202Bean.apply_year" id="rlgl070202Bean_apply_year"/>
    <div id="searchInfo">
    <my:navigation></my:navigation>
    <my:message></my:message>
    <div class="content">
       <div style="position: relative">
        <table width="100%" style="position: relative; top: 10px;" border="1" cellpadding="2" cellspacing="2">
          <tr>
            <td width="15%" class="lc1">申请单位名称<font color="red">*</font></td>
            <td colspan='3'>
              <s:textfield id="rlgl070202Bean_apply_unit_nm" name="rlgl070202Bean.apply_unit_nm" size="50" cssClass="put" maxLength="50"></s:textfield>
            </td>
          </tr>
          <tr>
            <td width="15%" class="lc1">申请人姓名<font color="red">*</font></td>
            <td colspan='3'>
              <s:textfield id="rlgl070202Bean_apply_person_nm" name="rlgl070202Bean.apply_person_nm" size="50" cssClass="put" maxLength="50"></s:textfield>
            </td>
          </tr>
          <tr>
            <td width="15%" class="lc1">申请人电话<font color="red">*</font></td>
            <td colspan='3'>
              <s:textfield id="rlgl070202Bean_apply_person_tel" name="rlgl070202Bean.apply_person_tel" size="50" cssClass="put" maxLength="50"></s:textfield>
            </td>
          </tr>
          <tr>
            <td align="center" colspan="4">
            <input type="button"  class="inp_L3" value="全选" name="btnSelectAll" id="btnSelectAll">
            <input type="button"  class="inp_L3" value="反选" name="btnSelectNo" id="btnSelectNo">
            <input type="button" class="inp_L3" value="提交申请" name="btnSubmit" id="btnSubmit"><span>(*提交申请前请根据交费人员选择开具发票人的数)</span>
            </td>
          </tr>
        </table>
      </div>
    
     <div id="result" style="position: relative;top:20px">
      <!--<my:dividepage actionId="rlgl070201Init.action"></my:dividepage>-->
        <table width="100%" style="position: relative;" border="1" cellpadding="2" cellspacing="2">
         <caption>交费人员名单 <s:property value="rlgl070202Bean.personCount"/>人   </caption>
       <tr>
          <th height="28" width="5%" class="thTitleItrn">选择</th>
          <th height="28" width="5%" class="thTitleItrn">序号</th>
          <th height="28" width="15%" class="thTitleItrn">用户ID</th>
          <th height="28" class="thTitleItrn" width="15%">用户姓名</th>
          <th height="28" class="thTitleItrn" width="15%">交费年度</th>
          <th height="28" width="45%" class="thTitleItrn">单位名称</th>
        </tr>
          <s:iterator value="rlgl070202BeanList" status="L">
            <my:trStripe index="${L.index}">
               <td class="tdc">
                <s:if test="%{check_flg == 1}">
                    <s:checkbox id="select_kbn_check" name="select_kbn_check" value="true"></s:checkbox>
                </s:if>
                <s:else>
                    <s:checkbox id="select_kbn_check" name="select_kbn_check" value="false"></s:checkbox>
                </s:else>
                </td>
                <td class="tdc">${L.index +1}</td>
                <td class="tdl">
                <s:property value="personnal_id"/>
                </td>
                <td class="tdl">
                <s:property value="personnal_nm"/>
                </td>
                <td class="tdl">
                <s:property value="apply_year"/>
                </td>
                <td class="tdl">
                    <s:property value="unit_nm"/>
                </td>   
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
