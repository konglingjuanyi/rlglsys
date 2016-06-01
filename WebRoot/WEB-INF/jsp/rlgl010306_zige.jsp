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
		  
			//资格信息
			function addNewZyzgxx(table_id) 
			{   if(objectArray==""){
		        	objectArray=$("#changedObject").val();
		        }
				$("#objectArray").val(objectArray);
				$("#changedObject").val(objectArray);
				$("#addFlg").val("addFlg7");
				$("form").attr("action", "rlgl010306Change.action");
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
				    $("form").attr("action", "rlgl010306ZigeAdd.action");
				    $("form").submit();
	      		}		    			
		    }
		    
		    // 页面上的验证组合
		    function checkItems(){
		    	
		    	// 资格证编号的值
		        var zgzbh =  checkValue("rlgl010306PractitionersInfoList_certificate_no");
		        //资格证编号的值为空的是后，其他的项目如果有值，则提示信息
		    	if(zgzbh == false){
		    	   // 发证机关
		    	   var fzjg = checkValue("rlgl010306PractitionersInfoList_issuing_authority"); 
		    	   if(fzjg == true){
			    	   alert("请填写资格证编号信息！");
			    	   $("#rlgl010306PractitionersInfoList_certificate_no").focus();
			    	   return false;
		    	   }
		    		// 发证日期
		    	   var fzrq = checkValue("rlgl010306PractitionersInfoList_issue_time"); 
			    	   if(fzrq == true){
			    	   alert("请填写资格证编号信息！");
			    	   $("#rlgl010306PractitionersInfoList_certificate_no").focus();
			    	   return false;
		    	   }
		    	    // 专业类别
		    	   var zylb= $('.zg_zylb').val(); 
		    	   if(zylb !=''){
			    	   alert("请填写资格证编号信息！");
			    	   $("#rlgl010306PractitionersInfoList_certificate_no").focus();
		    	  	   return false;
		    	   }
		    	}
		    	
		    	// 行政职务信息 验证
		    	if(xzzw_check() == true){
		    	    //社会关系 验证
	    			if(shgx_check() == true){
			    		// 教育经历——check
				    	if(jyjl_check() == true){
					    	if(gzjl_check() == true){
						    	if(dpxx_check() == true){
							    	// 导师信息——check
							    	if(dsxx_check()==true){
							    	   return true;
							    	}
						    	}
					    	}
				    	}
			    	}
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
       
        // 行政职务信息验证
        function xzzw_check(){
            for(var i = 0;i<$('.rzfs').size();i++){
              if($('.zwmc').eq(i).val()==''){
                 if($('.rzfs').eq(i).val() !=''){
                    alert("请选择职务名称信息！");
                    $('.zwmc').eq(i).focus();
                    return false;
                 }
                 
                 if($('.zwlb').eq(i).val() !=''){
                 	alert("请选择职务名称信息！");
                    $('.zwmc').eq(i).focus();
                    return false;
                 }
                 if($('.zwjb').eq(i).val() !=''){
                 	alert("请选择职务名称信息！");
                    $('.zwmc').eq(i).focus();
                    return false;
                 }
                 // 任职日期与任职文号
                 if($('.rzrq').eq(i).val() !=''){
                 	alert("请选择职务名称信息！");
                    $('.zwmc').eq(i).focus();
                    return false;
                 }
                 if($('.rzwh').eq(i).val() !=''){
                 	alert("请选择职务名称信息！");
                    $('.zwmc').eq(i).focus();
                    return false;
                 }
              }
            }
            return true;
        }
        //社会关系——验证
        function shgx_check(){
            for(var i = 0;i<$('.ybrgx').size();i++){
                if($('.ybrgx').eq(i).val() ==''){
                   if($('.xm').eq(i).val() !=''){
                   		alert("请填写与本人关系信息！");
                   		$('.ybrgx').eq(i).focus();
                   		return false;
                   }
                   if($('.csrq').eq(i).val() !=''){
                   		alert("请填写与本人关系信息！");
                   		$('.ybrgx').eq(i).focus();
                   		return false;
                   }

                   if($('.gzdw').eq(i).val() !=''){
                   		alert("请填写与本人关系信息！");
                   		$('.ybrgx').eq(i).focus();
                   		return false;
                   }
                   if($('.zw').eq(i).val() !=''){
                   		alert("请填写与本人关系信息！");
                   		$('.ybrgx').eq(i).focus();
                   		return false;
                   }
                }
                if($('.ybrgx').eq(i).val() !='' && $('.xm').eq(i).val() ==''){
                	 if($('.csrq').eq(i).val() !=''){
                   		alert("请填写姓名信息！");
                   		$('.xm').eq(i).focus();
                   		return false;
                   }

                   if($('.gzdw').eq(i).val() !=''){
                   		alert("请填写姓名信息！");
                   		$('.xm').eq(i).focus();
                   		return false;
                   }
                   if($('.zw').eq(i).val() !=''){
                   		alert("请填写姓名信息！");
                   		$('.xm').eq(i).focus();
                   		return false;
                   }
                
                }
            }
            return true;
        }
        // 教育经历——check
        function jyjl_check(){
        	for(var i = 0;i<$('.xxxs').size();i++){
        		if($('.xxxs').eq(i).val() ==''){
        			 if($('.rxsj').eq(i).val() !=''){
                   		alert("请选择学习形式信息！");
                   		$('.xxxs').eq(i).focus();
                   		return false;
                   }
        			 if($('.bysj').eq(i).val() !=''){
                   		alert("请选择学习形式信息！");
                   		$('.xxxs').eq(i).focus();
                   		return false;
                   }
                   if($('.byyx').eq(i).val() !=''){
                   		alert("请选择学习形式信息！");
                   		$('.xxxs').eq(i).focus();
                   		return false;
                   }
                   if($('.yxlx').eq(i).val() !=''){
                   		alert("请选择学习形式信息！");
                   		$('.xxxs').eq(i).focus();
                   		return false;
                   }
                   if($('.sxzy').eq(i).val() !=''){
                   		alert("请选择学习形式信息！");
                   		$('.xxxs').eq(i).focus();
                   		return false;
                   }
                   if($('.zmr').eq(i).val() !=''){
                   		alert("请选择学习形式信息！");
                   		$('.xxxs').eq(i).focus();
                   		return false;
                   }
                   if($('.xl').eq(i).val() !=''){
                   		alert("请选择学习形式信息！");
                   		$('.xxxs').eq(i).focus();
                   		return false;
                   }
                   if($('.xw').eq(i).val() !=''){
                   		alert("请选择学习形式信息！");
                   		$('.xxxs').eq(i).focus();
                   		return false;
                   }
        		}
        	}
        	return true;
        }
        // 工作经历
        function gzjl_check(){
        	for(var i = 0;i<$('.gz_kssj').size();i++){
        		if($('.gz_kssj').eq(i).val() ==''){
        			 if($('.gz_jssj').eq(i).val() !=''){
                   		alert("请填写开始时间信息！");
                   		$('.gz_kssj').eq(i).focus();
                   		return false;
                   }
                    if($('.gz_gzdw').eq(i).val() !=''){
                   		alert("请填写开始时间信息！");
                   		$('.gz_kssj').eq(i).focus();
                   		return false;
                   }
                    if($('.gz_zmr').eq(i).val() !=''){
                   		alert("请填写开始时间信息！");
                   		$('.gz_kssj').eq(i).focus();
                   		return false;
                   }
                   if($('.gz_zw').eq(i).val() !=''){
                   		alert("请填写开始时间信息！");
                   		$('.gz_kssj').eq(i).focus();
                   		return false;
                   }
        	}
        	if($('.gz_kssj').eq(i).val() !='' && $('.gz_jssj').eq(i).val() !=''){
        	    if($('.gz_gzdw').eq(i).val()==''){
        	      if($('.gz_zmr').eq(i).val()==''){
        	      	alert("请填写工作单位信息！");
        	        $('.gz_gzdw').eq(i).focus();
        	        return false;
        	      }
        	      if($('.gz_zw').eq(i).val()==''){
        	      	alert("请填写工作单位信息！");
        	        $('.gz_gzdw').eq(i).focus();
        	        return false;
        	      }
        	    }
        	}
         }
         return true;
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
      // 导师信息-check
      function dsxx_check(){
      		if($("#dslb").val() ==''){
      			if($("#dsxm").val() !=''){
      			 	alert("请选择导师类别信息！");
      			 	$("#dslb").focus();
      			 	return false;
      			}
      			 if($("#szdx").val() !=''){
      			 	alert("请选择导师类别信息！");
      			 	$("#dslb").focus();
      			 	return false;
      			}
      			 if($("#yjfx").val() !=''){
      			 	alert("请选择导师类别信息！");
      			 	$("#dslb").focus();
      			 	return false;
      			}
      		}
      		if($("#dslb").val() !='' && $("#dsxm").val() ==''){
      		      	if($("#szdx").val() !=''){
      			 	alert("请填写导师姓名信息！");
      			 	$("#dsxm").focus();
      			 	return false;
      			}
      			 if($("#yjfx").val() !=''){
      			 	alert("请填写导师姓名信息！");
      			 	$("#dsxm").focus();
      			 	return false;
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
				    $("form").attr("action", "rlgl010306ZigeAdd.action");
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
            <td height="28" style="padding-top:5px;" align="left"><font color="#1F6087" style="font-size:14px;"><strong>资格信息</strong></font></td>
          </tr>
            <tr>
                <td style="PADDING-TOP: 2px">
            <table cellSpacing="0" cellPadding="1" width="100%" border="1" ID="tab_zyzgxx" class="tabCss">
            <tr>
              <th height="28" width="11%" class="thTitleItrn">资格证编号</th>
              <th width="11%" class="thTitleItrn">发证机关</th>
              <th width="11%" class="thTitleItrn">发证日期</th>
              <th width="14%"class="thTitleItrn">专业类别</th>
              <th width="15%"class="thTitleItrn">专业级别</th>
              <th width="11%"class="thTitleItrn" style="display:none">执业范围</th>
              <th width="8%"class="thTitleItrn" style="display:none">变更记录</th>
              <th width="8%"class="thTitleItrn" style="display:none">执业考核记录</th>
              <th width="5%"class="thTitleItrn" style="display:none" >对象</th>
            </tr>
            	<s:if test="%{rlgl010306PractitionersInfoList!=null && rlgl010306PractitionersInfoList.size>0}">
            	<s:iterator  value="rlgl010306PractitionersInfoList" status='st'>
            <tr>
              <td height="25">
			<s:if test="%{addFlag == 1}">
			<s:hidden  name="rlgl010306PractitionersInfoList[%{#st.index }].addFlag" />
			<s:hidden name="addFlag7_%{#st.index }" id="addFlag7_%{#st.index }" value="init_rlgl010306PractitionersInfoList_%{#st.index }__certificate_no,init_rlgl010306PractitionersInfoList_%{#st.index }__issuing_authority,init_rlgl010306PractitionersInfoList_%{#st.index }__issue_time,pratypelist_%{#st.index },pralevellist_%{#st.index },init_rlgl010306PractitionersInfoList_%{#st.index }__area,init_rlgl010306PractitionersInfoList_%{#st.index }__upd_record,init_rlgl010306PractitionersInfoList_%{#st.index }__assess_record" />
			</s:if>
			  <!-- 资格证编号 -->
              <s:textfield  name="rlgl010306PractitionersInfoList[%{#st.index }].certificate_no" id="rlgl010306PractitionersInfoList_certificate_no" maxLength="20" size="9"></s:textfield></td>
              <!-- 发证机关 -->
              <td><s:textfield  name="rlgl010306PractitionersInfoList[%{#st.index }].issuing_authority" id="rlgl010306PractitionersInfoList_issuing_authority"  maxLength="20" size="9"></s:textfield></td>
              <!-- 发证日期 -->
              <td><s:textfield  name="rlgl010306PractitionersInfoList[%{#st.index }].issue_time" id="rlgl010306PractitionersInfoList_issue_time"  onClick="WdatePicker();" onBlur="AddMark(this);"  maxLength="8" size="9" readonly="true"></s:textfield>
              </td>
              <td>
              <!-- 专业类别 -->
              <s:select name="rlgl010306PractitionersInfoList[%{#st.index }].type"  cssClass="zg_zylb" id="pratypelist_%{#st.index }" onChange="pratypeChange('pratypelist_%{#st.index }','pralevellist_%{#st.index }')"  list="protypelist" listKey="adm_num" listValue="adm_name" headerValue="-请选择类别-" headerKey=""/>
              </td>
              <td>
              <s:if test="%{type == '183'}">
              		<s:select name="rlgl010306PractitionersInfoList[%{#st.index }].level" id="pralevellist_%{#st.index }"  list="pralevellist" listKey="adm_num" listValue="adm_name" headerValue="-请选择级别-" headerKey=""/>
              </s:if>
              <s:else>
              		<s:select name="rlgl010306PractitionersInfoList[%{#st.index }].level" id="pralevellist_%{#st.index }"  list="pralevellist" listKey="adm_num" listValue="adm_name" headerValue="-请选择级别-" headerKey=""  style="visibility: hidden;" />
              </s:else>
              </td>
              <td style="display:none">
               		<s:select name="rlgl010306PractitionersInfoList[%{#st.index }].area" list="jobArealist" listKey="adm_num" listValue="adm_name" headerValue="-请选择-" headerKey="" />
              </td>
              <td style="display:none"><s:textfield  name="rlgl010306PractitionersInfoList[%{#st.index }].upd_record"  maxLength="50" size="4"></s:textfield></td>
              <td style="display:none"><s:textfield  name="rlgl010306PractitionersInfoList[%{#st.index }].assess_record"  maxLength="50" size="4"></s:textfield></td>
              <td style="display:none"><input type="checkbox" name="object7" value="" id="object7"/></td>
            </tr> 
            	</s:iterator>
            </s:if>
            <s:else>
            	<tr>
              <td height="25"><s:textfield  name="rlgl010306PractitionersInfoList[0].certificate_no" value="%{#request.rlgl010306PractitionersInfoList[0].certificate_no}" id="rlgl010306PractitionersInfoList_certificate_no" maxLength="20" size="9"></s:textfield></td>
              <td><s:textfield  name="rlgl010306PractitionersInfoList[0].issuing_authority" value="%{#request.rlgl010306PractitionersInfoList[0].issuing_authority}"  id="rlgl010306PractitionersInfoList_issuing_authority" maxLength="20" size="9"></s:textfield></td>
              <td><s:textfield  name="rlgl010306PractitionersInfoList[0].issue_time" value="%{#request.rlgl010306PractitionersInfoList[0].issue_time}"   id="rlgl010306PractitionersInfoList_issue_time" onClick="WdatePicker();" onBlur="AddMark(this);" maxLength="8" size="9"></s:textfield></td>
              
              <td>
              		<s:select name="rlgl010306PractitionersInfoList[0].type"  cssClass="zg_zylb" id="pratypelist_0"  list="protypelist" listKey="adm_num" listValue="adm_name" headerValue="-请选择类别-" headerKey=""  onChange="pratypeChange('pratypelist_0','pralevellist_0')" />
              </td>
              <td>
              		<s:select name="rlgl010306PractitionersInfoList[0].level" id="pralevellist_0"  list="pralevellist" listKey="adm_num" listValue="adm_name" headerValue="-请选择级别-" headerKey="" style="visibility: hidden;"/>
              </td>
              <td style="display:none">
               		<s:select name="rlgl010306PractitionersInfoList[0].area" list="jobArealist" listKey="adm_num" listValue="adm_name" headerValue="-请选择-" headerKey="" />
              </td>
              <td style="display:none"><s:textfield  name="rlgl010306PractitionersInfoList[0].upd_record" value="%{#request.rlgl010306PractitionersInfoList[0].upd_record}" maxLength="50" size="4"></s:textfield></td>
              <td style="display:none"><s:textfield  name="rlgl010306PractitionersInfoList[0].assess_record" value="%{#request.rlgl010306PractitionersInfoList[0].assess_record}" maxLength="50" size="4"></s:textfield></td>
              <td  style="display:none" ><input type="checkbox" name="object7" value="" id="object7"/></td>
            </tr> 
            </s:else>
            
          </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:none" >
            <tr>
            <td height="40" align="right">
              <input type="button" name="btn_Add" class="inp_L3" value="行追加" onClick="addNewZyzgxx('tab_zyzgxx')"/>&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="button" name="btn_Delete" class="inp_L3" value="行删除" onClick="delet('object7','tab_zyzgxx');"/>&nbsp;&nbsp;&nbsp;&nbsp;
              <s:file style="display:none" name="fileProfessionalInfo" id="file7" label="选择文件" onChange="fileInput('rlgl010306PractitionersInfoList',this.value)"></s:file>
              <input type="button" name="btn_AllAdd" class="inp_L3" value="批量增加" onClick="showFileInput('file7')"/>
            </td>
            </tr>
        </table>
                </TD>
            </TR>
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
		<s:if test="%{backAction.trim() != ''}">
 			<input type="button" class="am-btn am-btn-primary"  value="返回" name="btnBack" id="btnBack"/>
		</s:if>
	</td>

 
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