<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><s:property value="getText('rlglsys.browserhead.IE')" /></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/WdatePicker.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadPhotos.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
    <%
response.addHeader("Cache-Control", "no-cache");
	%>
       <script type="text/javascript" language="javascript"> 
			// 被修改项目数组
			var objectArray = new Array();
			// 上次被修改项目数组（未审核通过的项目）
			var beforeObjectArray = new Array();
			function change() {
				$("#objectArray").val(objectArray);
				$("#changedObject").val(objectArray);
				$("form").attr("action", "rlgl010306Change.action");
				$("form").submit();
			}
			function upload() {
				$("#objectArray").val(objectArray);
				$("#changedObject").val(objectArray);
				$("form").attr("action", "upload.action");
				$("form").submit();
			}
			function showFileInput(file){
				$("#"+file).css('display','block');
		    }
		    function fileInput(fileObject,filePath){
		        if(objectArray==""){
		        	objectArray=$("#changedObject").val();
		        }
				$("#objectArray").val(objectArray);
				$("#changedObject").val(objectArray);
		    	$("#inputObject_id").val(fileObject);
		    	$("#filePath").val(filePath);
				$("form").attr("action", "rlgl010306Change.action");
				$("form").submit();
		    }
			
			//党派信息
			function addNewDpxx(table_id) 
			{   if(objectArray==""){
		        	objectArray=$("#changedObject").val();
		        }
				$("#objectArray").val(objectArray);
				$("#changedObject").val(objectArray);
				$("#addFlg").val("addFlg6");
				$("form").attr("action", "rlgl010306DangpaiChange.action");
				$("form").submit();
			} 
			
			function back(){
				$("#modal-confirm").text(" 是否确认退出该页面？");
				$('#my-confirm').modal({
			        relatedTarget: this,
			        onConfirm: function(options) {
			        	history.go(-1);
			        },
			        onCancel: function() {
			          return;
			        }
			      });
		    }
		    
		    function delet(object,tableid){
			    var delFlg=false;
			    var iRow=0;
			    var checked = $("input[type='checkbox'][name='" + object + "']"); 
				$(checked).each(function(){ 
					iRow++;
					if($(this).is(':checked')==true)
					{ 
						delFlg=true; 
					} 
				});
				
				if(iRow==1){
					$("#modal-alert").text("请至少保留一行！");
					$('#my-alert').modal();
					return;
				}
				if (delFlg==false) {
				$("#modal-alert").text("请选择对象！");
				$('#my-alert').modal();
				return;
				}
				var ids="";
				var idsArray=new Array();
				
				$("#modal-confirm").text(" 是否确认删除这条信息？");
				$('#my-confirm').modal({
			        relatedTarget: this,
			        onConfirm: function(options) {
			        	checked = $("input[type='checkbox'][name='" + object + "']"); 
						$(checked).each(function(){ 
						if($(this).is(':checked')==true)
						{ 
							ids = $(this).parent().parent().find("input").map(function(){
		                    return this.id;
		                	}).get().join(",");

		                	idsArray=ids.split(",");
		                	for(var i=0;i<idsArray.length;i++){
					      		if(objectArray.contains(idsArray[i])){
					      			objectArray.remove(idsArray[i]);
					      		}
							}
		                	ids = $(this).parent().parent().find("select").map(function(){
		                    	return this.id;
		                	}).get().join(",");
		                	
							idsArray=ids.split(",");
		                	for(var i=0;i<idsArray.length;i++){
					      		if(objectArray.contains(idsArray[i])){
					      			objectArray.remove(idsArray[i]);
					      		}
							}
							$("#objectArray").val(objectArray);
							$("#changedObject").val(objectArray);
							$(this).parent().parent().remove(); 
						}
						});
			        },
			        onCancel: function() {
			          return;
			        }
			      });
		    }
		    
		    function getRowCount(object){
		    var count=0;
		    var checked = $("input[type='checkbox'][name='" + object + "']"); 
				$(checked).each(function(){ 
				count++;
				}); 
				return count;
		    }
		    // 【提交】按钮的事件定义
		    function doSaveAction() {
	      		if(checkItems() == true){
  				    	$("#objectArray").val(objectArray);
					$("#changedObject").val(objectArray);
				    $("#saveAction").val("1");
				    $("form").attr("action", "rlgl010306DangpaiAdd.action");
				    $("form").submit();
	      		}		    			
		    }
		    
		    // 页面上的验证组合
		    function checkItems(){
		    	// 党派信息 验证
		    	if(dpxx_check() == true){
			    	   return true;
			    	}
		    }
		    //check输入框的值是否为空的js
		 function checkValue(kongjianId){
            var kjValue = $("#"+kongjianId).val();
            if(kjValue == null || kjValue==''){
               return false;
            }else{
               return true;
            }
            
         }
        
       // 党派信息_check()
       function dpxx_check(){
       		for(var i = 0;i<$('.dp_jrsj').size();i++){
       			if($('.dp_jrsj').eq(i).val()==''){
       				if($('.dp_dpmc').eq(i).val()!=''){
       				     alert("请填写加入时间信息！");
       				     $('.dp_jrsj').eq(i).focus();
       				     return false;
       				}
       				if($('.dp_ybzs').eq(i).val()!=''){
       				     alert("请填写加入时间信息！");
       				     $('.dp_jrsj').eq(i).focus();
       				     return false;
       				}
       			}
      		 }
      		 return true;
      		}
      
      // 提交按钮的事件
		function commitAction() {
		 if(checkItems() == true){
				var checkFlg = 0;
				$("input[id='rlgl010306PractitionersInfoList_certificate_no']").each(function(idx, obj) {
			        $(this).val(Trim($(this).val()));
			        if($(this).val() == "")
			        {
			            checkFlg++;
			        }
			    });
			    if (checkFlg > 0)
			    { 
			    	$("#modal-alert").text("请填写必须输入项目！");
					$('#my-alert').modal();
		            return false;
			    }
				$("#objectArray").val(objectArray);
				$("#changedObject").val(objectArray);
			    $("#saveAction").val("0");
			    $("form").attr("action", "rlgl010306DangpaiAdd.action");
			    $("form").submit();
		 	}
	    }
		    
    function commitCheck(){
    // 电话号码验证
	    if(!checkInput($("#rlgl010306Add_personnel_personnel_tel").val())){
		    if(!checkPhoneNum($("#rlgl010306Add_personnel_personnel_tel").value)){
		    	$("#modal-alert").text("请输入正确的电话号码！");
				$('#my-alert').modal();
		    	$("#rlgl010306Add_personnel_personnel_tel").focus();
		    	return false;
		    }
	    }
	    if(!checkInput($("#rlgl010306Add_personnel_personnel_officetel").value)){
		    if(!checkPhoneNum($("#rlgl010306Add_personnel_personnel_officetel").value)){
		    	$("#modal-alert").text("请输入正确的电话号码！");
				$('#my-alert').modal();
		    	$("#rlgl010306Add_personnel_personnel_officetel").focus();
		    	return false;
		    }
	    }
	    return true;
    }
		    
	function onelevelChange(onelevel,twolevel,threelevel){
		$("#"+onelevel).change(function(){
          var value=$(this).val();
          comboxLinkageStructure.url = "ComboxContextFromAdmForGrade.action";
          comboxLinkageStructure.nextComboxId = twolevel + "," + threelevel;
          comboxLinkageStructure.nextComboxName = "等级,等次";
          comboxLinkageStructure.urlParams ="087,"+value;
          comboxChanged();
      	});
	}
	function twolevelChange(onelevel,twolevel,threelevel){
		$("#"+twolevel).change(function(){
          var value=$(this).val();
          comboxLinkageStructure.url = "ComboxContextFromAdmForGrade.action";
          comboxLinkageStructure.nextComboxId = threelevel;
          comboxLinkageStructure.nextComboxName = "等次";
          comboxLinkageStructure.urlParams ="027,,"+value;
          comboxChanged();
      	});
	}

	function pratypeChange(onelevel,twolevel){
		$("#"+onelevel).change(function(){
          var value=$(this).val();
          if(value == "183"){
          	$("#"+twolevel).css('visibility','visible');
          }else
          {
          $("#"+twolevel).css('visibility','hidden');
	          objectArray.remove(twolevel);
	          removeUpdateMark(twolevel);
          }
          comboxLinkageStructure.url = "ComboxContextFromAdmForGrade.action";
          comboxLinkageStructure.nextComboxId = twolevel;
          comboxLinkageStructure.nextComboxName = "级别";
          comboxLinkageStructure.urlParams ="009,"+value;
          comboxChanged();
      	});
	}  
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
	// 变更标记
	if($("#changedObject").length>0){
	 objectArray=$("#changedObject").val().split(",");
	}
	 createReviewMark($("#changedObject").val(),"update");
	 doMark();
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
	    // 被修改项目ID保存到数组
         $(':text,:file,textarea').change(function(flgl, obj) {
            if ($(this).val() != this.defaultValue) {
                if (!objectArray.contains(this.id)) {
                    objectArray.push(this.id);
                    createUpdateMark(this.id);
                }
            } else {
                if (objectArray.contains(this.id)) {
                	// 上次保存的修改字段标识不会被删除(20130918)
                    var upArray=new Array();
                    upArray=$("#saveChangeValue").val().split(",");
                    if (!upArray.contains(this.id)) {
	                    objectArray.remove(this.id);
	                    removeUpdateMark(this.id);
                    }
                }
            }
        });

       // 被修改项目ID保存到数组
        $('select').change(function() {
            var updateFlg = false;
            for(var i = 0; i < this.options.length; i++) {
                if (this.options[i].defaultSelected != this.options[i].selected) {
                    updateFlg = true;
                    break;
                }
            }
            if (updateFlg) {
                if (!objectArray.contains(this.id)) {
                    objectArray.push(this.id);
                    createUpdateMark(this.id);
                }
            } else {
                if (objectArray.contains(this.id)) {
               		// 上次保存的修改字段标识不会被删除(20130918)
                    var upArray=new Array();
                    upArray=$("#saveChangeValue").val().split(",");
                    if (!upArray.contains(this.id)) {
	                    objectArray.remove(this.id);
	                    removeUpdateMark(this.id);
                    }
                }
            }
        });
	 
	 //区域划分获得市级
      $("#province").change(function(){
          var value=$(this).val();
          comboxLinkageStructure.url = "ComboxCommonContextFromArea.action";
          comboxLinkageStructure.nextComboxId = "city,zone";
          comboxLinkageStructure.nextComboxName = "市,区县";
          comboxLinkageStructure.urlParams =value;
          comboxChanged();
      });
      //区域划分获得县级
      $("#city").change(function(){
          var city=$(this).val();
          var province=$("#province").val();
          comboxLinkageStructure.url = "ComboxCommonContextFromZone.action";
          comboxLinkageStructure.nextComboxId = "zone";
          comboxLinkageStructure.nextComboxName = "区县";
          comboxLinkageStructure.urlParams =province+","+city;
          comboxChanged();
      });
		
     	var i=0;
      	for(i=0;i<100;i++){
      	onelevelChange("onelevel_" + i,"twolevel_" + i,"threelevel_" + i);
      	twolevelChange("onelevel_" + i,"twolevel_" + i,"threelevel_" + i);
		}
		
 });
 function backAction() {
       	$("#backFlag").val("1");
      	$("form").attr("action", $("#backAction").val());
	    $("form").submit();
}
function AddMark(object){
		if ($(object).val() != object.defaultValue) {
             if (!objectArray.contains(object.id)) {
                 objectArray.push(object.id);
                 createUpdateMark(object.id);
             }
         } else {
             if (objectArray.contains(object.id)) {
                 objectArray.remove(object.id);
                 removeUpdateMark(object.id);
             }
         }
}
function doMark(){
		beforeObjectArray=$("#changedObject").val().split(",");
		if($("#addOrInputFlg").val().length < 4){
		 return;
		}
	    // 新加行或批量导入时添加修改标记
      	for(var i=0;i<100;i++){
	      	if($("#addFlag1_"+i).length>0 && $("#addOrInputFlg").val() == "Flg1"){
				$("#changedObject").val($("#changedObject").val() + "," + $("#addFlag1_"+i).val());
				// 变更标记
	 			createReviewMark($("#addFlag1_"+i).val(),"update");
	 			objectArray=$("#changedObject").val().split(",");
		    }
		    if($("#addFlag2_"+i).length>0 && $("#addOrInputFlg").val() == "Flg2"){
				$("#changedObject").val($("#changedObject").val() + "," + $("#addFlag2_"+i).val());
				// 变更标记
	 			createReviewMark($("#addFlag2_"+i).val(),"update");
	 			objectArray=$("#changedObject").val().split(",");
		    }
		    if($("#addFlag3_"+i).length>0 && $("#addOrInputFlg").val() == "Flg3"){
				$("#changedObject").val($("#changedObject").val() + "," + $("#addFlag3_"+i).val());
				// 变更标记
	 			createReviewMark($("#addFlag3_"+i).val(),"update");
	 			objectArray=$("#changedObject").val().split(",");
		    }
		    if($("#addFlag4_"+i).length>0 && $("#addOrInputFlg").val() == "Flg4"){
				$("#changedObject").val($("#changedObject").val() + "," + $("#addFlag4_"+i).val());
				// 变更标记
	 			createReviewMark($("#addFlag4_"+i).val(),"update");
	 			objectArray=$("#changedObject").val().split(",");
		    }
		    if($("#addFlag5_"+i).length>0 && $("#addOrInputFlg").val() == "Flg5"){
				$("#changedObject").val($("#changedObject").val() + "," + $("#addFlag5_"+i).val());
				// 变更标记
	 			createReviewMark($("#addFlag5_"+i).val(),"update");
	 			objectArray=$("#changedObject").val().split(",");
		    }
		    if($("#addFlag6_"+i).length>0 && $("#addOrInputFlg").val() == "Flg6"){
				$("#changedObject").val($("#changedObject").val() + "," + $("#addFlag6_"+i).val());
				// 变更标记
	 			createReviewMark($("#addFlag6_"+i).val(),"update");
	 			objectArray=$("#changedObject").val().split(",");
		    }
		    if($("#addFlag7_"+i).length>0 && $("#addOrInputFlg").val() == "Flg7"){
				$("#changedObject").val($("#changedObject").val() + "," + $("#addFlag7_"+i).val());
				// 变更标记
	 			createReviewMark($("#addFlag7_"+i).val(),"update");
	 			objectArray=$("#changedObject").val().split(",");
		    }
		    if($("#addFlag8_"+i).length>0 && $("#addOrInputFlg").val() == "Flg8"){
				$("#changedObject").val($("#changedObject").val() + "," + $("#addFlag8_"+i).val());
				// 变更标记
	 			createReviewMark($("#addFlag8_"+i).val(),"update");
	 			objectArray=$("#changedObject").val().split(",");
		    }
		    beforeObjectArray=$("#changedObject").val().split(",");
		}
	}
	
</script> 

    </HEAD>
    <body>
    <s:form  name="init" id="init" action="rlgl010306Init"  method="post" nameSpace="/rlgl" enctype="multipart/form-data">
    <s:hidden name="navigationId" id="navigationId"/>
    <s:hidden name="screenId" id="screenId"/>
    <s:hidden name="personnel.personnel_imgname" id="imgName"/>
     <s:hidden name="personnel_id" id="personnel_id"/>
     <s:hidden name="personnel.personnel_id" id="personnel.personnel_id"/>
     <s:hidden name="personnel.personnel_nm"  />
     <s:hidden name="personnel.personnel_card_id"  />
     <s:hidden name="personnel.personnel_check"  />
      <s:hidden name="personnel.personnel_unit"  />
      <s:hidden name="personnel.personnel_office"  />
      <s:hidden name="personnel.personnel_admintype"  />
     <s:hidden name="inputObject_id" id="inputObject_id"/>
     <s:hidden name="filePath" id="filePath"/>
     <s:hidden name="objectArray" id="objectArray"/> 
     <s:hidden name="saveAction" id="saveAction"/>
     <s:hidden name="change_value" id="changedObject"/>
     <s:hidden name="saveChangeValue" id="saveChangeValue"/>
     <s:hidden name="addFlg" id="addFlg"/>
     <s:hidden name="backAction" id="backAction"/>
     <s:hidden name="backFlag" id="backFlag"/>
     <s:hidden name="addOrInputFlg" id="addOrInputFlg"/>
     <s:hidden name="imgFileHidden" id="imgFileHidden"/>
   	 <s:hidden name="only_search" id="only_search"/>
    <my:navigation></my:navigation>
    <div class="content">
    <table width="100%"  border="0" cellspacing="0" cellpadding="0">
     <tr>
    	<td align="center">
    	<font color="#0066CC" style="font-size:14px;">
        <strong> 
				<s:if test="%{personnel.personnel_isapproval == '000'}">
				          您的信息还未提交申请，请提交申请！
                </s:if>
                <s:if test="%{personnel.personnel_isapproval == '001'}">
    				您的信息正在审核中！
                </s:if>
                 <s:if test="%{personnel.personnel_isapproval == '002'}">
    				您的信息已通过审核！
                </s:if>
                 <s:if test="%{personnel.personnel_isapproval == '003'}">
   					您的信息申请已被驳回！
                </s:if>
   		</strong>
        </font>
        </td>
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
              <th width="5%"class="thTitleItrn"><label>对象</label></th>
            </tr>
            <s:if test="%{rlgl010306PartisanInfoList!=null&&rlgl010306PartisanInfoList.size>0}">
            	<s:iterator  value="rlgl010306PartisanInfoList" status='st'>
            		<tr>
              <td><!--加入时间  -->
					<s:if test="%{addFlag == 1}">
					<s:hidden  name="rlgl010306PartisanInfoList[%{#st.index }].addFlag" />
					<s:hidden name="addFlag6_%{#st.index }" id="addFlag6_%{#st.index }" value="init_rlgl010306PartisanInfoList_%{#st.index }__jointime,init_rlgl010306PartisanInfoList_%{#st.index }__partisan_nm,init_rlgl010306PartisanInfoList_%{#st.index }__prep_or_officially" />
					</s:if>
              		<s:textfield  name="rlgl010306PartisanInfoList[%{#st.index }].jointime"  cssClass="dp_jrsj" onClick="WdatePicker();" onBlur="AddMark(this);" maxLength="8" size="42"></s:textfield></td>
              <td><!--党派名称 -->
				<s:select name="rlgl010306PartisanInfoList[%{#st.index }].partisan_nm"  cssClass="dp_dpmc" list="partisanlist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey=""/>
              </td>
              <td><!--预备/正式 -->
					<s:select name="rlgl010306PartisanInfoList[%{#st.index }].prep_or_officially" cssClass="dp_ybzs"  list="preplist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey=""/>
              </td>
              
              <td><input type="checkbox" name="object6" value="" id="object6"/></td>
            </tr> 
            	</s:iterator>
            </s:if>
            <s:else>
            	<tr><!--加入时间  -->
              <td><s:textfield  name="rlgl010306PartisanInfoList[0].jointime" cssClass="dp_jrsj" value="%{#request.rlgl010306PartisanInfoList[0].jointime}"  onClick="WdatePicker();" onBlur="AddMark(this);" maxLength="8" size="42"></s:textfield></td>
              <td><!--党派名称  -->
              	<s:select name="rlgl010306PartisanInfoList[0].partisan_nm"   cssClass="dp_dpmc" list="partisanlist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey=""/>
				</td>
              <td><!--预备/正式 -->
              	<s:select name="rlgl010306PartisanInfoList[0].prep_or_officially" cssClass="dp_ybzs"   list="preplist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey=""/>
				</td>
              <td><input type="checkbox" name="object6" value="" id="object6"/></td>
            </tr> 
            </s:else>
            
          </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
            <td height="30" align="right">
              <input type="button" class="am-btn am-btn-primary" onClick="addNewDpxx('tab_dpxx')" width="100px" value="行追加" name="btn_Add"/>
              &nbsp;&nbsp;&nbsp;&nbsp;
              <input type="button" class="am-btn am-btn-primary" onClick="delet('object6','tab_dpxx');" width="100px" value="行删除" name="btn_Delete"/>
              &nbsp;&nbsp;&nbsp;&nbsp;
              <s:file style="display:none" name="fileProfessionalInfo" id="file6" label="选择文件" onChange="fileInput('rlgl010306PartisanInfoList',this.value)"/>
              <input type="button" class="am-btn am-btn-primary" onClick="showFileInput('file6')" width="100px" value="批量增加" name="btn_AllAdd"/>
            </td>
            </tr>
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

<!-- 操作按扭 -->
<table width="70%" align="center" border="0" cellspacing="1" cellpadding="0">
<tr>
<td height="50" align="left" >
<label style="color:green;"><b>提交人事口信息审核</b></label>
<br>
1.您必须首先维护好人事口的所有信息才可以使用此功能。
<br>
2.如果您不提交申请，单位是无法看到你的审核信息的，如果您的信息不能被单位审核通过，将会严重影响到您日后的使用。
<br>
3.打印任期合格证书时需要用到您的某部分人事口信息，当然这部分信息是审核通过了的。
<br>
</td>
</tr>
<tr>
    <td height="50" align="center">
		<input type="button" class="am-btn am-btn-danger " onClick="doSaveAction()" width="100px" value="保存" name="btnSave" id="btnSave"/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 		<input type="button" class="am-btn am-btn-danger " onClick="commitAction()" width="100px" value="提交申请" name="btnCommit" id="btnCommit"/>
 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<s:if test="%{backAction.trim() != ''}">
 			<input type="button" class="am-btn am-btn-primary"  value="返回" name="btnBack" id="btnBack"/>
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