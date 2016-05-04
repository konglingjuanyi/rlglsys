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
			//专业技术
			function addNewZyjszwxx(table_id) 
			{   if(objectArray==""){
		        	objectArray=$("#changedObject").val();
		        }
				$("#objectArray").val(objectArray);
				$("#changedObject").val(objectArray);
				$("#addFlg").val("addFlg1");
				$("form").attr("action", "rlgl010306Change.action");
				$("form").submit();
			} 
			//行政职务信息
			function addNewXzzwxx(table_id) 
			{   if(objectArray==""){
		        	objectArray=$("#changedObject").val();
		        }
				$("#objectArray").val(objectArray);
				$("#changedObject").val(objectArray);
				$("#addFlg").val("addFlg2");
				$("form").attr("action", "rlgl010306Change.action");
				$("form").submit();
			} 
			//社会关系
			function addNewShgx(table_id) 
			{   if(objectArray==""){
		        	objectArray=$("#changedObject").val();
		        }
				$("#objectArray").val(objectArray);
				$("#changedObject").val(objectArray);
				$("#addFlg").val("addFlg3");
				$("form").attr("action", "rlgl010306Change.action");
				$("form").submit();
			}
			//教育经历
			function addNewJyjl(table_id) 
			{   if(objectArray==""){
		        	objectArray=$("#changedObject").val();
		        }
				$("#objectArray").val(objectArray);
				$("#changedObject").val(objectArray);
				$("#addFlg").val("addFlg4");
				$("form").attr("action", "rlgl010306Change.action");
				$("form").submit();
			} 
			//工作经历
			function addNewGzjl(table_id) 
			{   if(objectArray==""){
		        	objectArray=$("#changedObject").val();
		        }
				$("#objectArray").val(objectArray);
				$("#changedObject").val(objectArray);
				$("#addFlg").val("addFlg5");
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
			//执业信息
			function addPractice() 
			{   if(objectArray==""){
		        	objectArray=$("#changedObject").val();
		        }
				$("#objectArray").val(objectArray);
				$("#changedObject").val(objectArray);
				$("#addFlg").val("addFlg8");
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
					    $("form").attr("action", "rlgl010306Add.action");
					    $("form").submit();
		      		}		    			
		    }
		    
		    // 页面上的验证组合
		    function checkItems(){
		    	//出生日期
		    	var personnel_birthday = $("#personnel_birthday").val();
		    	//参加工作时间 
		    	var personnel_worktime = $("#personnel_worktime").val();
		    	//入党时间
		    	var personnel_joinpartytime = $("#personnel_joinpartytime").val();
		    	if((personnel_birthday.length!=0 || personnel_birthday!="") && (personnel_worktime.length!=0 || personnel_worktime!="") && personnel_birthday > personnel_worktime){
		    		$("#modal-alert").text("参加工作时间必须大于出生日期！");
					$('#my-alert').modal();
		    		return false;
		    	}else if((personnel_birthday.length!=0 || personnel_birthday!="") && (personnel_joinpartytime.length!=0 || personnel_joinpartytime!="") && personnel_birthday > personnel_joinpartytime){
		    		$("#modal-alert").text("入党(团)时间必须大于出生日期！");
					$('#my-alert').modal();
		    		return false;
		    	}
		    	
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
		    	// 执业信息
		    	// 执业证编号
		    	var zyzbh = checkValue("zyzbh"); 
		    	if(zyzbh == false){
			    	// 发证机关
			    	var zyfzjg = checkValue("zyzfzjg"); 
			    	if(zyfzjg==true){
			    		alert("请填写执业证编号信息！");
			    		$("#zyzbh").focus();
			    		return false;
			    	}
		    	// 发证日期
		    	var zyzfzrq = checkValue("zyzfzrq");
		    	if(zyzfzrq == true){
		    		alert("请填写执业证编号信息！");
		    		$("#zyzbh").focus();
		    		return false;
		    	}
		    	  // 专业类别
		    	   var zyzylb= $('.zy_zylb').val(); 
		    	   if(zyzylb !=''){
		    	   alert("请填写执业证编号信息！");
		    	   $("#zyzbh").focus();
		    	   return false;
		    	   }
		    	 // 执业类别
		    	 var zyzzylb = $('.zy_zylx').val();  
		    	 if(zyzzylb !=''){
		    	 	 alert("请填写执业证编号信息！");
		    	   	 $("#zyzbh").focus();
		    	   	 return false;
		    	 }
		    	 // 执业范围
		    	var fw1 = $("#zyfw1").val();
		    	var fw2 = $("#zyfw2").val();  
		    	if(fw1 !='' ||fw2 !=''){
		    		 alert("请填写执业证编号信息！");
		    	   	 $("#zyzbh").focus();
		    	   	 return false;
		    	}
		    	// 执业地点
		    	var dd1 = $("#zydd1").val();
		    	var dd2 = $("#zydd2").val();  
		    	if(dd1 !='' ||dd2 !=''){
		    		 alert("请填写执业证编号信息！");
		    	   	 $("#zyzbh").focus();
		    	   	 return false;
		    	 }
		    	}
		    	// 专业技术职务信息 验证
		    	if(tab_zyjuzz()==true){
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
         // 循环多行表格（专业技术职务信息），进行数据的验证
         function tab_zyjuzz(){
             for(var i = 0; i < $('.zyjszw_name').size(); i++){
             // 名字
             if($('.onelevel').eq(i).val()==''){
               	    if($('.zyjszw_name').eq(i).val() != ''){
                    alert("请选择级别信息！");
                    $('.onelevel').eq(i).focus();
                    return false;
                    }
              }
               if($('.onelevel').eq(i).val()!='' && $('.twolevel').eq(i).val()=='' &&  $('.zyjszw_name').eq(i).val() != ''){
                    	alert("请选择级别信息！");
                    	$('.twolevel').eq(i).focus();
                    	return false;
               }
                 if($('.onelevel').eq(i).val()!='' && $('.twolevel').eq(i).val() !='' 
                     && $('.threelevel').eq(i).val() =='' &&  $('.zyjszw_name').eq(i).val() != ''){
                    	alert("请选择级别信息！");
                    	$('.threelevel').eq(i).focus();
                    	return false;
                }
                
                
               // 审批机关
              if($('.onelevel').eq(i).val()==''){
               	    if($('.zyjszw_spjg').eq(i).val() != ''){
                    alert("请选择级别信息！");
                    $('.onelevel').eq(i).focus();
                    return false;
                    }
              }
               if($('.onelevel').eq(i).val()!='' && $('.twolevel').eq(i).val()=='' &&  $('.zyjszw_spjg').eq(i).val() != ''){
                    	alert("请选择级别信息！");
                    	$('.twolevel').eq(i).focus();
                    	return false;
               }
                 if($('.onelevel').eq(i).val()!='' && $('.twolevel').eq(i).val() !='' 
                     && $('.threelevel').eq(i).val() =='' &&  $('.zyjszw_spjg').eq(i).val() != ''){
                    	alert("请选择级别信息！");
                    	$('.threelevel').eq(i).focus();
                    	return false;
                }
                // 取得日期
              if($('.onelevel').eq(i).val()==''){
               	    if($('.zyjszw_qdsj').eq(i).val() != ''){
                    alert("请选择级别信息！");
                    $('.onelevel').eq(i).focus();
                    return false;
                    }
              }
               if($('.onelevel').eq(i).val()!='' && $('.twolevel').eq(i).val()=='' &&  $('.zyjszw_qdsj').eq(i).val() != ''){
                    	alert("请选择级别信息！");
                    	$('.twolevel').eq(i).focus();
                    	return false;
               }
                 if($('.onelevel').eq(i).val()!='' && $('.twolevel').eq(i).val() !='' 
                     && $('.threelevel').eq(i).val() =='' &&  $('.zyjszw_qdsj').eq(i).val() != ''){
                    	alert("请选择级别信息！");
                    	$('.threelevel').eq(i).focus();
                    	return false;
                }
            }
            return true;
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
			        alertMessage("AM021");
		            return false;
			    }
				$("#objectArray").val(objectArray);
				$("#changedObject").val(objectArray);
			    $("#saveAction").val("0");
			    $("form").attr("action", "rlgl010306Add.action");
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
    <!-- 基本信息 -->
    <table width="100%" border="0" cellspacing="0" cellpadding="0" >
        <tr>
            <td height="28" style="padding-top:5px;" align="left"><font color="#1F6087" style="font-size:14px;"><strong>基本信息</strong></font></td>
        </tr>
        <tr>
            <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="userBaseCss" style="border-bottom:0px">
                <tr valign="top">
                <td width="80%">
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
                    <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">曾用名</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <s:textfield  name="personnel.personnel_beforename"  maxLength="20" ></s:textfield>
                      </td>
                      <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">性别</td>
                      <td width="35%" style="padding-left:6px;font-size:12px;">
                      <s:select name="personnel.personnel_gender" list="genderAdmlist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey="" />
                      </td>
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                    <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">出生日期</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <s:textfield  name="personnel.personnel_birthday" onClick="WdatePicker();" onBlur="AddMark(this);" maxLength="8" id="personnel_birthday"></s:textfield>
                      </td>
                      <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">籍贯</td>
                      <td width="35%" style="padding-left:6px;font-size:12px;"><s:textfield  name="personnel.personnel_hometown"  maxLength="50" ></s:textfield></td>
                    
			</tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                    <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">民族</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                       <s:select name="personnel.personnel_ethnic"  list="ethnicAdmlist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey="" />
                      </td>
                      <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">参加工作时间</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;"><s:textfield  name="personnel.personnel_worktime" id="personnel_worktime"  onClick="WdatePicker();" onBlur="AddMark(this);" maxLength="8" ></s:textfield></td>
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                     <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">户口所在地</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <s:textfield  name="personnel.personnel_account_location" maxLength="100" ></s:textfield>
                      </td>
                     <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">健康状况</td>
                      <td style="padding-left:6px;font-size:12px;">
                      <s:select name="personnel.personnel_health_status"  list="healthAdmlist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey="" />
                       </td>
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                     <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">个人身份</td>
                      <td style="padding-left:6px;font-size:12px;">
                      <s:select name="personnel.personnel_personal_identification"  list="identificationAdmlist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey="" />
                         
                        </td>
                       <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">用工形式</td>
                      <td style="padding-left:6px;font-size:12px;">
                      <s:select name="personnel.personnel_employment_forms"  list="formsAdmlist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey="" />
                         
                       </td>
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                    <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">政治面貌</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                       <s:select name="personnel.personnel_political_landscape" list="landscapeAdmlist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey="" />
                       </td>
                      
                       <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">入党(团)时间</td>
                      <td style="padding-left:6px;font-size:12px;">
                      <s:textfield  name="personnel.personnel_joinpartytime" id="personnel_joinpartytime" maxLength="8"  onClick="WdatePicker();" onBlur="AddMark(this);" ></s:textfield>
                       </td> 
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                     <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">婚姻状况</td>
                      <td style="padding-left:6px;font-size:12px;">
                      <s:select name="personnel.personnel_marital_status"  list="maritalAdmlist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey="" />
                       
                       </td>
                     <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">爱好特长</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <s:textfield  name="personnel.personnel_hobbies"  maxLength="50" ></s:textfield>
                      </td>
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                     <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">外语水平</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <s:textfield  name="personnel.personnel_foreignlanguage_level"  maxLength="20" ></s:textfield>
                      </td>
                      <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">在编状态</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <s:select name="personnel.personnel_regular"  list="regularlist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey="" />
                       </td>
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                     <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">存档单位</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <s:textfield  name="personnel.personnel_archive_unit"  maxLength="50" ></s:textfield>
                      </td>
                      <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">档案位置</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <s:textfield  name="personnel.personnel_filelocation"  maxLength="50" ></s:textfield>
                      </td>
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                    <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">岗位状态</td>
                      <td style="padding-left:6px;font-size:12px;">
                      <s:select name="personnel.personnel_status"  list="statusAdmlist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey="" />
                       
                        </td>
                      <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">年度审核判定</td>
                      <td style="padding-left:6px;font-size:12px;">
                       <s:if test="%{personnel.personnel_check.trim() != ''}">
                      <s:label name="personnel.personnel_check" ></s:label>
                      </s:if>
               		  <s:else>未审</s:else>
                      <s:hidden name="personnel.personnel_check"  />
                       </td>
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                    <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">所在单位</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <s:label name="personnel.personnel_unit_nm" ></s:label>
                      <s:hidden name="personnel.personnel_unit"  />
                		</td>
                      <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">所在科室</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <s:if test="%{personnel.personnel_office_nm != ''}">
                      <s:label name="personnel.personnel_office_nm" ></s:label>
                      </s:if>
               		  <s:else>未分配</s:else>
                      <s:hidden name="personnel.personnel_office"  />
                     </td>
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                    <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">岗位类别</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <s:if test="%{personnel.personnel_admintype != ''}">
                      <s:label name="personnel.personnel_admintype" ></s:label>
                      </s:if>
               		  <s:else>未分配</s:else>
                      <s:hidden name="personnel.personnel_admintype"  />
                		</td>
                      <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">是否继续教育</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;"> 是</td>
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                  </table>
                </td>
                <td width="20%" align="center" style="padding-top:14px;">
                <!-- 个人照片 -->
                    <s:if test="%{personnel.personnel_imgname != ''}">
                <img src="${pageContext.request.contextPath}/upload/${personnel.personnel_imgname}" id="showImgFile" width="141" height="200" border="0">
                </s:if>
                <s:else>
                <img src="${pageContext.request.contextPath}/images/picture.jpg" id="showImgFile"  width="141" height="200" border="0">
                </s:else><br>
            	<s:file name="imgFile" id="imgFile" label="图片" onChange="previewChange('imgFile','${personnel.personnel_card_id}','showImgFile','imgName','temporary')"></s:file>
                  </td>
                </tr>
            </table>
            
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="userBaseCss" style="border-top:0px; text-align:left;">
               <tr > 
                <td width="12%" class="lc1" style="padding-left:5px;font-size:12px;">家庭住址</td>
                <td width="78%" style="padding-left:5px;font-size:12px;">
                <s:select name="personnel.personnel_province"  id="province"   list="provincelist" listKey="area_id" listValue="province" headerValue="- -" headerKey="" />
                <s:select name="personnel.personnel_city"   id="city" list="citylist" listKey="area_id" listValue="city" headerValue="- -" headerKey=""  />
                <s:select name="personnel.personnel_zone" id="zone" list="zonelist" listKey="area_id" listValue="zone" headerValue="- -" headerKey=""/>
                <s:textfield  name="personnel.personnel_address" maxLength="100" size="40"></s:textfield>
                </td>
                <td width="10%" ></td>
              </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:none;">
              <tr>
                  <td height="28" style="padding-top:5px;" align="left"><font color="#1F6087" style="font-size:14px;"><strong>联系信息</strong></font></td>
              </tr>
              <tr>
              <td>
              <table width="100%" border="0" cellspacing="2" cellpadding="0" class="userBaseCss">
              <tr>
                <td  width="10%" class="lc1" style="padding-left:5px;font-size:12px;">电子邮件</td>
                <td width="20%" style="padding-left:5px;font-size:12px;">
                <s:textfield  name="personnel.personnel_email"  maxLength="50" ></s:textfield>
                </td>
                <td width="10%" class="lc1" style="padding-left:6px;font-size:12px;">移动电话</td>
                <td width="20%" style="padding-left:6px;font-size:12px;">
                <s:textfield  name="personnel.personnel_tel"  maxLength="11" ></s:textfield>
                </td>
                <td width="10%" class="lc1" style="padding-left:6px;font-size:12px;">单位电话</td>
                <td width="20%" style="padding-left:6px;font-size:12px;">
                <s:textfield  name="personnel.personnel_officetel"  maxLength="50" ></s:textfield>
                </td>
              </tr>
             </table>
            </td>
            </tr>
          </table>
          </td>
        </tr>
      </table>


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
              <td height="25"><s:textfield  name="rlgl010306PractitionersInfoList[0].certificate_no" value="%{#request.rlgl010306PractitionersInfoList[0].certificate_no}" maxLength="20" size="9"></s:textfield></td>
              <td><s:textfield  name="rlgl010306PractitionersInfoList[0].issuing_authority" value="%{#request.rlgl010306PractitionersInfoList[0].issuing_authority}" maxLength="20" size="9"></s:textfield></td>
              <td><s:textfield  name="rlgl010306PractitionersInfoList[0].issue_time" value="%{#request.rlgl010306PractitionersInfoList[0].issue_time}"  onClick="WdatePicker();" onBlur="AddMark(this);" maxLength="8" size="9"></s:textfield></td>
              
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
      
<div> 
        <table width="100%" border="0" cellspacing="0" cellpadding="0" ID="Table20">
          <tr> 
            <td height="28" style="padding-top:5px;" align="left"><font color="#1F6087" style="font-size:14px;"><strong>执业信息</strong></font></td>
          </tr>
            <tr>
                <td style="PADDING-TOP: 2px">
            <table cellSpacing="0" cellPadding="1" width="100%" border="1" ID="tab_zyzgxx" class="tabCss">
            <tr>
              <th height="28" width="7%" class="thTitleItrn">执业证编号</th>
              <th width="7%" class="thTitleItrn">发证机关</th>
              <th width="7%" class="thTitleItrn">发证日期</th>
              <th width="11%"class="thTitleItrn">专业类别</th>
              <th width="7%"class="thTitleItrn">专业级别</th>
              <th width="11%"class="thTitleItrn">执业类别</th>
              <th width="18%"class="thTitleItrn">执业范围</th>
              <th width="11%"class="thTitleItrn">执业地点</th>
              <th width="5%"class="thTitleItrn" style="display:none">变更记录</th>
              <th width="8%"class="thTitleItrn" style="display:none">执业考核记录</th>
              <th width="0%"class="thTitleItrn" style="display:none" >对象</th>
            </tr>
            <s:if test="%{rlgl010306PracticeInfoList!=null&&rlgl010306PracticeInfoList.size>0}">
            	<s:iterator  value="rlgl010306PracticeInfoList" status='st'>
            <tr>
              <td height="25">
					<s:if test="%{addFlag == 1}">
					<s:hidden  name="rlgl010306PracticeInfoList[%{#st.index }].addFlag" />
					<s:hidden name="addFlag8_%{#st.index }" id="addFlag8_%{#st.index }" value="init_rlgl010306PracticeInfoList_%{#st.index }__certificate_no,init_rlgl010306PracticeInfoList_%{#st.index }__issuing_authority,init_rlgl010306PracticeInfoList_%{#st.index }__issue_time,practypelist_%{#st.index },praclevellist_%{#st.index },init_rlgl010306PracticeInfoList_%{#st.index }__area,init_rlgl010306PracticeInfoList_%{#st.index }__upd_record,init_rlgl010306PracticeInfoList_%{#st.index }__assess_record" />
					</s:if>       
              <!-- 执业证编号 -->
             	 <s:textfield  name="rlgl010306PracticeInfoList[%{#st.index }].certificate_no"  id="zyzbh" maxLength="20" size="4"></s:textfield></td>
              <td>
                <!-- 发证机关 -->
              	<s:textfield  name="rlgl010306PracticeInfoList[%{#st.index }].issuing_authority"  id="zyzfzjg" maxLength="20" size="4"></s:textfield>
              </td>
              <td>
                 <!-- 发证日期 -->
              	<s:textfield  name="rlgl010306PracticeInfoList[%{#st.index }].issue_time"  id="zyzfzrq" onClick="WdatePicker();" onBlur="AddMark(this);"  maxLength="8" size="4"></s:textfield>
              </td>
              <td>
              <!-- 专业类别 -->
              		<s:select name="rlgl010306PracticeInfoList[%{#st.index }].type" cssClass="zy_zylb" id="practypelist_%{#st.index }" onChange="pratypeChange('practypelist_%{#st.index }','praclevellist_%{#st.index }')"  list="protypelist" listKey="adm_num" listValue="adm_name" headerValue="-请选择类别-" headerKey=""/>
              </td>
              <td>
              <s:if test="%{type == '183'}">
              <s:select name="rlgl010306PracticeInfoList[%{#st.index }].level" id="praclevellist_%{#st.index }"  list="pralevellist" listKey="adm_num" listValue="adm_name" headerValue="-请选择级别-" headerKey=""/>
              </s:if>
              <s:else>
              <s:select name="rlgl010306PracticeInfoList[%{#st.index }].level" id="praclevellist_%{#st.index }"  list="pralevellist" listKey="adm_num" listValue="adm_name" headerValue="-请选择级别-" headerKey=""  style="visibility: hidden;" />
              </s:else>
              </td>
              <td><!-- 执业类别 -->
              		<s:select name="rlgl010306PracticeInfoList[%{#st.index }].pro_type" cssClass="zy_zylx" id="proctypelist_%{#st.index }"   list="pratypelist" listKey="adm_num" listValue="adm_name" headerValue="-请选择类别-" headerKey=""/>
              </td>
              <td><!-- 执业范围 -->
              		 范围1:<s:select name="rlgl010306PracticeInfoList[%{#st.index }].area1" id ="zyfw1" list="proArealist" listKey="adm_num" listValue="adm_name" headerValue="-请选择-" headerKey="" />
      			<br>范围2:<s:select name="rlgl010306PracticeInfoList[%{#st.index }].area2" d ="zyfw2"  list="proArealist" listKey="adm_num" listValue="adm_name" headerValue="-请选择-" headerKey="" />
                     </td>
              <td><!-- 执业地点-->
            	  地点1:<s:textfield  name="rlgl010306PracticeInfoList[%{#st.index }].place1"  id="zydd1" maxLength="20" size="4"></s:textfield>
      			<br>地点2:<s:textfield  name="rlgl010306PracticeInfoList[%{#st.index }].place2"  id="zydd2" maxLength="20" size="4"></s:textfield>
               </td>
	              <td style="display:none">
	             	 <s:textfield  name="rlgl010306PracticeInfoList[%{#st.index }].upd_record"  maxLength="50" size="2"></s:textfield>
	              </td>
	              <td style="display:none">
	                 <s:textfield  name="rlgl010306PracticeInfoList[%{#st.index }].assess_record"  maxLength="50" size="2"></s:textfield>
	              </td>
              <td style="display:none"><input type="checkbox" name="object8" value="" id="object8" /></td>
            </tr> 
            	</s:iterator>
            </s:if>
            <s:else>
            	<tr>
              <td height="25"><s:textfield  name="rlgl010306PracticeInfoList[0].certificate_no" value="%{#request.rlgl010306PracticeInfoList[0].certificate_no}" maxLength="20" size="4"></s:textfield></td>
              <td><s:textfield  name="rlgl010306PracticeInfoList[0].issuing_authority" value="%{#request.rlgl010306PracticeInfoList[0].issuing_authority}" maxLength="20" size="4"></s:textfield></td>
              <td><s:textfield  name="rlgl010306PracticeInfoList[0].issue_time" value="%{#request.rlgl010306PracticeInfoList[0].issue_time}"  onClick="WdatePicker();" onBlur="AddMark(this);" maxLength="8" size="4"></s:textfield></td>
              
              <td>
              <s:select name="rlgl010306PracticeInfoList[0].type"  cssClass="zy_zylb" id="practypelist_0"  list="protypelist" listKey="adm_num" listValue="adm_name" headerValue="-请选择类别-" headerKey=""  onChange="pratypeChange('practypelist_0','praclevellist_0')" />
              </td>
              <td>
              <s:select name="rlgl010306PracticeInfoList[0].level" id="praclevellist_0"  list="pralevellist" listKey="adm_num" listValue="adm_name" headerValue="-请选择级别-" headerKey="" style="visibility: hidden;"/>
              </td>
              <td>
              <s:select name="rlgl010306PracticeInfoList[0].pro_type" cssClass="zy_zylx"  id="proctypelist_%{#st.index }"   list="pratypelist" listKey="adm_num" listValue="adm_name" headerValue="-请选择类别-" headerKey=""/>
              </td>
              <td>
              		范围1:<s:select name="rlgl010306PracticeInfoList[0].area1" id ="zyfw1" list="proArealist" listKey="adm_num" listValue="adm_name" headerValue="-请选择-" headerKey="" />
             		<br>范围2:<s:select name="rlgl010306PracticeInfoList[0].area2" id ="zyfw2" list="proArealist" listKey="adm_num" listValue="adm_name" headerValue="-请选择-" headerKey="" />
              </td>

              <td>
            	 	 地点1:<s:textfield  name="rlgl010306PracticeInfoList[0].place1" id="zydd1"  maxLength="20" size="4"></s:textfield>
      				<br>地点2:<s:textfield  name="rlgl010306PracticeInfoList[0].place2" id="zydd2" maxLength="20" size="4"></s:textfield>
               </td>

              <td style="display:none"><s:textfield  name="rlgl010306PracticeInfoList[0].upd_record" value="%{#request.rlgl010306PracticeInfoList[0].upd_record}" maxLength="50" size="2"></s:textfield></td>
              <td style="display:none"><s:textfield  name="rlgl010306PracticeInfoList[0].assess_record" value="%{#request.rlgl010306PracticeInfoList[0].assess_record}" maxLength="50" size="2"></s:textfield></td>
              <td style="display:none"><input type="checkbox" name="object8" value="" id="object8"/></td>
            </tr> 
            </s:else>
            
          </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:none" >
            <tr>
            <td height="40" align="right">
              <input type="button" name="btn_Add" class="inp_L3" value="行追加" onClick="addPractice()"/>&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="button" name="btn_Delete" class="inp_L3" value="行删除" onClick="delet('object8','tab_practice');"/>&nbsp;&nbsp;&nbsp;&nbsp;
              <s:file style="display:none" name="fileProfessionalInfo" id="file8" label="选择文件" onChange="fileInput('rlgl010306PracticeInfoList',this.value)"></s:file>
              <input type="button" name="btn_AllAdd" class="inp_L3" value="批量增加" onClick="showFileInput('file8')"/>
            </td>
            </tr>
        </table>
                </TD>
            </TR>
        </table>
      </div>
      
    <div>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" ID="Table11">
        <tr>
            <td height="28" style="padding-top:5px;" align="left"><font color="#1F6087" style="font-size:14px;"><strong>专业技术职务信息</strong></font></td>
        </tr>
        <tr>
            <td style="padding-top:2px;">
            <table cellSpacing="0" cellPadding="1" width="100%" border="1" ID="tab_zyjszwxx" name="tab_zyjszwxx" class="tabCss">
            <tr>
              <th height="28" width="35%" class="thTitleItrn">级别</th>
              <th width="15%" class="thTitleItrn">名称</th>
              <th width="15%" class="thTitleItrn">审批机关</th>
              <th width="15%" class="thTitleItrn">取得时间</th>
              <th width="5%"class="thTitleItrn"><label>对象</label></th>
            </tr>
             <s:if test="%{rlgl010306ProfessionalInfoList!=null&&rlgl010306ProfessionalInfoList.size>0}">
             <s:iterator  value="rlgl010306ProfessionalInfoList" status='st'>
             	<tr>
	              	<td width="25%"><!-- 级别 -->
						<s:if test="%{addFlag == 1}">
						<s:hidden  name="rlgl010306ProfessionalInfoList[%{#st.index }].addFlag" />
						<s:hidden name="addFlag1_%{#st.index }" id="addFlag1_%{#st.index }" value="onelevel_%{#st.index },twolevel_%{#st.index },threelevel_%{#st.index },init_rlgl010306ProfessionalInfoList_%{#st.index }__name,init_rlgl010306ProfessionalInfoList_%{#st.index }__original,init_rlgl010306ProfessionalInfoList_%{#st.index }__get_time" />
						</s:if>
	              		<s:select name="rlgl010306ProfessionalInfoList[%{#st.index }].onelevel"  id="onelevel_%{#st.index }"  list="onelevellist" listKey="adm_num" listValue="adm_name" headerValue="-请选择等级-" headerKey="" cssClass="onelevel"/>
                		<s:select name="rlgl010306ProfessionalInfoList[%{#st.index }].twolevel"   id="twolevel_%{#st.index }"  list="twolevellist" listKey="adm_num" listValue="adm_name" headerValue="-请选择等级-" headerKey="" cssClass="twolevel" />
                		<s:select name="rlgl010306ProfessionalInfoList[%{#st.index }].threelevel" id="threelevel_%{#st.index }" list="threelevellist" listKey="adm_num" listValue="adm_name" headerValue="-请选择等次-" headerKey="" cssClass="threelevel"/>
	              	</td>
		            <td width="15%"><!-- 名称 -->
		               <s:textfield  name="rlgl010306ProfessionalInfoList[%{#st.index }].name" id="zyjszw_name" size="12" maxLength="20"  cssClass="zyjszw_name"></s:textfield>
		             </td>
		            <td width="15%"><!-- 审批机关 -->
		              	<s:textfield  name="rlgl010306ProfessionalInfoList[%{#st.index }].original" id="zyjszw_spjg" size="12" maxLength="20" cssClass="zyjszw_spjg"></s:textfield>
		             </td>
		            <td width="15%"><!-- 取得时间 -->
		             	<s:textfield  name="rlgl010306ProfessionalInfoList[%{#st.index }].get_time" id="zyjszw_qdsj" onClick="WdatePicker();" onBlur="AddMark(this);" size="12" maxLength="8" cssClass="zyjszw_qdsj"></s:textfield>
		             </td>
		            <td width="5%">
		             		<input type="checkbox" name="object1" value="" id="object1"/>
		            </td>
            	</tr>
             </s:iterator>
              </s:if>
              <s:else>
              <tr>
              	<td width="25%"> <!-- 级别 -->
	              	<s:select name="rlgl010306ProfessionalInfoList[0].onelevel"  id="onelevel_0"  list="onelevellist" listKey="adm_num" listValue="adm_name" headerValue="-请选择等级-" headerKey="" cssClass="onelevel"/>
	                <s:select name="rlgl010306ProfessionalInfoList[0].twolevel"   id="twolevel_0"  list="twolevellist" listKey="adm_num" listValue="adm_name" headerValue="-请选择等级-" headerKey=""  cssClass="twolevel"/>
	                <s:select name="rlgl010306ProfessionalInfoList[0].threelevel" id="threelevel_0" list="threelevellist" listKey="adm_num" listValue="adm_name" headerValue="-请选择等次-" headerKey="" cssClass="threeelevel"/>
              	</td>
	            <td width="15%"><!-- 名称 -->
	            	<s:textfield  name="rlgl010306ProfessionalInfoList[0].name" size="12"  id="zyjszw_name" maxLength="20" cssClass="zyjszw_name"></s:textfield>
	            </td>
	            <td width="15%"><!-- 审批机关 -->
	            	<s:textfield  name="rlgl010306ProfessionalInfoList[0].original" size="12"  id="zyjszw_spjg" maxLength="20" cssClass="zyjszw_spjg"></s:textfield>
	            </td>
	            <td width="15%"><!-- 取得时间 -->
	            	<s:textfield  name="rlgl010306ProfessionalInfoList[0].get_time" size="12"  id="zyjszw_qdsj" onClick="WdatePicker();" onBlur="AddMark(this);" maxLength="8" cssClass="zyjszw_qdsj">
	            	</s:textfield>
	            </td>
	            <td width="5%"><input type="checkbox" name="object1" value="" id="object1"/></td>
            </tr>
              </s:else>
            
            
        </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
            <td height="30" align="right">
              <input type="button" name="btn_Add" class="inp_L3" value="行追加" onClick="addNewZyjszwxx('tab_zyjszwxx')"/>&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="button" name="btn_Delete" class="inp_L3" value="行删除" onClick="delet('object1','tab_zyjszwxx');"/>&nbsp;&nbsp;&nbsp;&nbsp;
              <s:file style="display:none" name="fileProfessionalInfo" id="file1" label="选择文件" onChange="fileInput('rlgl010306ProfessionalInfoList',this.value)"></s:file>
              <input type="button" name="btn_AllAdd" class="inp_L3" value="批量增加" onClick="showFileInput('file1')"/>
            </td>
            </tr>
        </table>
        </td>
      </tr>
    </table></div>

    <div >
        <TABLE cellSpacing="0" cellPadding="0" width="100%" border="0" ID="tab_xzzw">
            <tr><td style="padding-top:5px;" height="28" align="left"><FONT color="#1F6087" style="font-size:14px;"><STRONG>行政职务信息</STRONG></FONT></td>
            </tr>
            <tr>
                <td style="PADDING-TOP: 2px">
                
            <TABLE cellSpacing="0" cellPadding="1" width="100%" border="1" ID="tab_xzzwxx" class="tabCss">
            <tr>
              <th height="28" width="12%" class="thTitleItrn">职务名称</th>
              <th width="12%" class="thTitleItrn">任职方式</th>
              <th width="12%" class="thTitleItrn">职务类别</th>
              <th width="20%" class="thTitleItrn">职务级别</th>
              <th width="14%" class="thTitleItrn">任职日期</th>
              <th width="14%" class="thTitleItrn">任职文号</th>
              <th width="5%"class="thTitleItrn"><label>对象</label></th>
            </tr>
            <s:if test="%{rlgl010306JobInfoList!=null&&rlgl010306JobInfoList.size>0}">
            	<s:iterator  value="rlgl010306JobInfoList" status='st'>
            		<tr>
              			<td height="25" width="15%"><!-- 职务名称 -->
							<s:if test="%{addFlag == 1}">
							<s:hidden  name="rlgl010306JobInfoList[%{#st.index }].addFlag" />
							<s:hidden name="addFlag2_%{#st.index }" id="addFlag2_%{#st.index }" value="init_rlgl010306JobInfoList_%{#st.index }__position_nm,init_rlgl010306JobInfoList_%{#st.index }__mode,init_rlgl010306JobInfoList_%{#st.index }__type,init_rlgl010306JobInfoList_%{#st.index }__level,init_rlgl010306JobInfoList_%{#st.index }__appoint_time,init_rlgl010306JobInfoList_%{#st.index }__appoint_no" />
							</s:if>
              				<s:select name="rlgl010306JobInfoList[%{#st.index }].position_nm"   list="positionlist" listKey="adm_num" listValue="adm_name" headerValue="-请选择职务-" headerKey="" cssClass="zwmc"/>
              			</td>
              <td width="15%"><!-- 任职方式 -->
              <s:textfield  name="rlgl010306JobInfoList[%{#st.index }].mode" maxLength="20" size="12" cssClass="rzfs"></s:textfield>
              </td>
              <td width="15%"><!-- 职务类别-->
              <s:select name="rlgl010306JobInfoList[%{#st.index }].type"   list="typelist" listKey="adm_num" listValue="adm_name" headerValue="-请选择类别-" headerKey="" cssClass="zwlb"/>
              </td>
              <td width="15%"><!-- 职务级别-->
              <s:select name="rlgl010306JobInfoList[%{#st.index }].level"   list="levellist" listKey="adm_num" listValue="adm_name" headerValue="-请选择级别-" headerKey="" cssClass="zwjb"/>
              </td>
              <td width="15%"><!-- 任职日期-->
              <s:textfield  name="rlgl010306JobInfoList[%{#st.index }].appoint_time"  onClick="WdatePicker();" onBlur="AddMark(this);" maxLength="8" size="12" cssClass="rzrq"></s:textfield>
              </td>
              <td width="15%"><!-- 任职文号-->
              <s:textfield  name="rlgl010306JobInfoList[%{#st.index }].appoint_no"  maxLength="20" size="12" cssClass="rzwh"/>
              </td>
              <td width="5%"><input type="checkbox" name="object2" value="" id="object2"/></td>
            </tr>
            	</s:iterator>
            </s:if>
            <s:else>
            <tr>
              <td height="25" width="15%"><!-- 职务名称 -->
              <s:select name="rlgl010306JobInfoList[0].position_nm"   list="positionlist" listKey="adm_num" listValue="adm_name" headerValue="-请选择职务-" headerKey="" cssClass="zwmc"/>
              </td>
              <td width="15%"><!-- 任职方式 -->
              <s:textfield  name="rlgl010306JobInfoList[0].mode" maxLength="20" size="12" cssClass="rzfs"></s:textfield>
              </td>
              <td width="15%"><!-- 职务类别-->
             <s:select name="rlgl010306JobInfoList[0].type"   list="typelist" listKey="adm_num" listValue="adm_name" headerValue="-请选择类别-" headerKey="" cssClass="zwlb"/>
               </td>
              <td width="15%"><!-- 职务级别-->
              <s:select name="rlgl010306JobInfoList[0].level" cssClass="zwjb"  list="levellist" listKey="adm_num" listValue="adm_name" headerValue="-请选择级别-" headerKey=""/>
              </td>
              <td width="15%"><!-- 任职日期-->
              <s:textfield  name="rlgl010306JobInfoList[0].appoint_time" cssClass="rzrq"  onClick="WdatePicker();" onBlur="AddMark(this);" maxLength="8" size="12"></s:textfield>
              </td>
              <td width="15%"><!-- 任职文号-->
              <s:textfield  name="rlgl010306JobInfoList[0].appoint_no" cssClass="rzwh" maxLength="20" size="12"></s:textfield>
              </td>
              <td width="5%"><input type="checkbox" name="object2" value="" id="object2"/></td>
            </tr>
            </s:else>
        </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
            <td height="30" align="right">
              <input type="button" name="btn_Add" class="inp_L3" value="行追加" onClick="addNewXzzwxx('tab_xzzwxx')"/>&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="button" name="btn_Delete" class="inp_L3" value="行删除" onClick="delet('object2','tab_xzzw');"/>&nbsp;&nbsp;&nbsp;&nbsp;
              <s:file style="display:none" name="fileProfessionalInfo" id="file2" label="选择文件" onChange="fileInput('rlgl010306JobInfoList',this.value)"></s:file>
              <input type="button" name="btn_AllAdd" class="inp_L3" value="批量增加" onClick="showFileInput('file2')"/>
            </td>
            </tr>
        </table>
                </td>
            </tr>
        </TABLE>
    </div>
    
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
              <th width="10%" class="thTitleItrn">出生日期</th>
              <th width="10%" class="thTitleItrn">政治面貌</th>
              <th width="15%" class="thTitleItrn">工作单位</th>
              <th width="13%" class="thTitleItrn">职务</th>
              <th width="13%" class="thTitleItrn">电话</th>
              <th width="5%"class="thTitleItrn"><label>对象</label></th>
            </tr>
            <s:if test="%{rlgl010306SocialInfoList!=null&&rlgl010306SocialInfoList.size>0}">
            	<s:iterator  value="rlgl010306SocialInfoList" status='st'>
           	<tr>
              <td height="25" width="14%"><!--与本人关系  -->
				<s:if test="%{addFlag == 1}">
				<s:hidden  name="rlgl010306SocialInfoList[%{#st.index }].addFlag" />
				<s:hidden name="addFlag3_%{#st.index }" id="addFlag3_%{#st.index }" value="init_rlgl010306SocialInfoList_%{#st.index }__relationship,init_rlgl010306SocialInfoList_%{#st.index }__name,init_rlgl010306SocialInfoList_%{#st.index }__birthday,init_rlgl010306SocialInfoList_%{#st.index }__political_landscape,init_rlgl010306SocialInfoList_%{#st.index }__workunit,init_rlgl010306SocialInfoList_%{#st.index }__position,init_rlgl010306SocialInfoList_%{#st.index }__tel" />
				</s:if>
              	<s:textfield  name="rlgl010306SocialInfoList[%{#st.index }].relationship" cssClass="ybrgx" maxLength="20" size="10"></s:textfield>
              </td>
              <td width="14%"><!-- 姓名 -->
					<s:textfield  name="rlgl010306SocialInfoList[%{#st.index }].name" cssClass="xm" maxLength="20" size="10"></s:textfield></td>
              <td width="14%"><!-- 出生日期 -->
              		<s:textfield  name="rlgl010306SocialInfoList[%{#st.index }].birthday" cssClass="csrq" onClick="WdatePicker();" onBlur="AddMark(this);" maxLength="8" size="10"></s:textfield>
              </td>
              <td width="13%"><!-- 政治面貌 -->
					<s:select name="rlgl010306SocialInfoList[%{#st.index }].political_landscape" cssClass="zzmm" list="landscapeAdmlist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey="" />
              </td>
              <!-- 工作单位 -->
              <td width="15%"><s:textfield  name="rlgl010306SocialInfoList[%{#st.index }].workunit"  cssClass="gzdw" maxLength="50" size="14"></s:textfield></td>
              <!-- 职务 -->
              <td width="10%"><s:textfield  name="rlgl010306SocialInfoList[%{#st.index }].position" cssClass="zw" maxLength="20" size="8"></s:textfield></td>
              <!-- 电话 -->
              <td width="10%"><s:textfield  name="rlgl010306SocialInfoList[%{#st.index }].tel"   cssClass="textphonenum"  maxLength="20" size="8"></s:textfield></td>
              <td width="5%"><input type="checkbox" name="object3" value="10" id="object3"/></td>
            </tr>
            	</s:iterator>
            </s:if>
            <s:else>
            <tr>   
	              <td height="25" width="14%"><!--与本人关系  -->
	              	<s:textfield  name="rlgl010306SocialInfoList[0].relationship" cssClass="ybrgx" value="%{#request.rlgl010306SocialInfoList[0].relationship}" maxLength="20" size="10" ></s:textfield>
	              </td>
	              <td width="14%"><!--姓名  -->
					<s:textfield  name="rlgl010306SocialInfoList[0].name" cssClass="xm" value="%{#request.rlgl010306SocialInfoList[0].name}" maxLength="20" size="10"></s:textfield></td>
	              <td width="14%"><!--出生日期  -->
	              	<s:textfield  name="rlgl010306SocialInfoList[0].birthday" cssClass="csrq" value="%{#request.rlgl010306SocialInfoList[0].birthday}"  onClick="WdatePicker();" onBlur="AddMark(this);" maxLength="8" size="10"></s:textfield>
	              </td>
	              <td width="13%"><!-- 政治面貌 -->
	              	<s:select name="rlgl010306SocialInfoList[0].political_landscape" cssClass="zzmm" list="landscapeAdmlist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey="" />
	              </td>
	              <!--工作单位  -->
	              <td width="15%"><s:textfield  name="rlgl010306SocialInfoList[0].workunit" cssClass="gzdw" value="%{#request.rlgl010306SocialInfoList[0].workunit}" maxLength="50" size="14"></s:textfield></td>
	              <!--职务  -->
	              <td width="10%"><s:textfield  name="rlgl010306SocialInfoList[0].position" cssClass="zw" value="%{#request.rlgl010306SocialInfoList[0].position}" maxLength="20" size="8"></s:textfield></td>
	              <!--电话-->
	              <td width="10%"><s:textfield  name="rlgl010306SocialInfoList[0].tel"  cssClass="textphonenum" value="%{#request.rlgl010306SocialInfoList[0].tel}" maxLength="20" size="8"></s:textfield></td>
	              <td width="5%"><input type="checkbox" name="object3" value="10" id="object3"/></td>
            </tr>
            </s:else>
            
        </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
            <td height="30" align="right">
              <input type="button" name="btn_Add" class="inp_L3" value="行追加" onClick="addNewShgx('tab_shgl')"/>&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="button" name="btn_Delete" class="inp_L3" value="行删除" onClick="delet('object3','tab_shgl');"/>&nbsp;&nbsp;&nbsp;&nbsp;
              <s:file style="display:none" name="fileProfessionalInfo" id="file3" label="选择文件" onChange="fileInput('rlgl010306SocialInfoList',this.value)"></s:file>
              <input type="button" name="btn_AllAdd" class="inp_L3" value="批量增加" onClick="showFileInput('file3')"/>
            </td>
            </tr>
        </table>
                </td>
            </tr>
        </TABLE>
    </div>
    
    
    <div> 
        <table width="100%" border="0" cellspacing="0" cellpadding="0" ID="Table15">
          <tr> 
            <td height="28" style="padding-top:5px;" align="left"><font color="#1F6087" style="font-size:14px;"><strong>教育经历</strong></font></td>
          </tr>
          
            <tr>
                <td style="PADDING-TOP: 2px">
            <TABLE cellSpacing="0" cellPadding="1" width="100%" border="1" ID="tab_jyjl" class="tabCss">
            <tr>
              <th height="28" width="10%" class="thTitleItrn">学习形式</th>
              <th width="11%" class="thTitleItrn">入学时间</th>
              <th width="11%" class="thTitleItrn">毕业时间</th>
              <th width="11%" class="thTitleItrn">毕业学校</th>
              <th width="11%" class="thTitleItrn">院校类型</th>
              <th width="11%" class="thTitleItrn">所学专业</th>
              <th width="10%" class="thTitleItrn">证明人</th>
              <th width="12%" class="thTitleItrn">学历</th>
              <th width="8%" class="thTitleItrn">学位</th>
              <th width="5%"class="thTitleItrn"><label>对象</label></th>
            </tr>
            <s:if test="%{rlgl010306EduInfoList!=null&&rlgl010306EduInfoList.size>0}">
            	<s:iterator  value="rlgl010306EduInfoList" status='st'>
            <tr>
              <td height="25"><!-- 学习形式 -->
				<s:if test="%{addFlag == 1}">
				<s:hidden  name="rlgl010306EduInfoList[%{#st.index }].addFlag" />
				<s:hidden name="addFlag4_%{#st.index }" id="addFlag4_%{#st.index }" value="init_rlgl010306EduInfoList_%{#st.index }__learning_format,init_rlgl010306EduInfoList_%{#st.index }__admission_time,init_rlgl010306EduInfoList_%{#st.index }__graduation_time,init_rlgl010306EduInfoList_%{#st.index }__school,init_rlgl010306EduInfoList_%{#st.index }__college_type,init_rlgl010306EduInfoList_%{#st.index }__profession,init_rlgl010306EduInfoList_%{#st.index }__proof_people,init_rlgl010306EduInfoList_%{#st.index }__educational_bg,init_rlgl010306EduInfoList_%{#st.index }__degree" />
				</s:if>
				<s:select name="rlgl010306EduInfoList[%{#st.index }].learning_format" cssClass="xxxs"   list="learninglist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey=""/>
			</td>
				<!-- 入学时间 -->
              <td>
               	<s:textfield  name="rlgl010306EduInfoList[%{#st.index }].admission_time"  cssClass="rxsj"  onClick="WdatePicker();" onBlur="AddMark(this);" maxLength="8" size="8"></s:textfield>
              </td>
              <td><!-- 毕业时间 -->
              	<s:textfield  name="rlgl010306EduInfoList[%{#st.index }].graduation_time"  cssClass="bysj"  onClick="WdatePicker();" onBlur="AddMark(this);" maxLength="8" size="8"></s:textfield>
            </td>
            	<!-- 毕业学校-->
              <td><s:textfield  name="rlgl010306EduInfoList[%{#st.index }].school" cssClass="byyx"  maxLength="20" size="8"></s:textfield></td>
              <td><!-- 院校类型-->
				<s:select name="rlgl010306EduInfoList[%{#st.index }].college_type" cssClass="yxlx"   list="collegetypelist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey=""/>
			  </td>
			  <!-- 所学专业-->
              <td><s:textfield  name="rlgl010306EduInfoList[%{#st.index }].profession" cssClass="sxzy"  maxLength="20" size="8"></s:textfield></td>
               <!-- 证明人-->
              <td><s:textfield  name="rlgl010306EduInfoList[%{#st.index }].proof_people" cssClass="zmr"  maxLength="20" size="6"></s:textfield></td>
              <td><!-- 学历 -->
              		<s:select name="rlgl010306EduInfoList[%{#st.index }].educational_bg"  cssClass="xl"  list="educationalbglist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey=""/>
              </td>
              <td><!-- 学位 -->
              		<s:select name="rlgl010306EduInfoList[%{#st.index }].degree"  cssClass="xw"  list="degreelist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey=""/>
              </td>
              <td><input type="checkbox" name="object4" value="10" id="object4"/></td>
            </tr>
            	</s:iterator>
            </s:if>
            <s:else>
          <tr>
              <td height="25"><!-- 学习形式 -->
					<s:select name="rlgl010306EduInfoList[0].learning_format"  cssClass="xxxs" list="learninglist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey=""/>  
              </td>
              <td><!-- 入学时间 -->
              		<s:textfield  name="rlgl010306EduInfoList[0].admission_time" cssClass="rxsj" value="%{#request.rlgl010306EduInfoList[0].admission_time}"  onClick="WdatePicker();" onBlur="AddMark(this);" maxLength="8" size="8"></s:textfield>
              </td>
              <!-- 毕业时间 -->
              <td><s:textfield  name="rlgl010306EduInfoList[0].graduation_time" cssClass="bysj" value="%{#request.rlgl010306EduInfoList[0].graduation_time}" onClick="WdatePicker();" onBlur="AddMark(this);"  maxLength="8" size="8"></s:textfield></td>
              <!-- 毕业院校-->
              <td><s:textfield  name="rlgl010306EduInfoList[0].school" cssClass="byyx" value="%{#request.rlgl010306EduInfoList[0].school}" maxLength="20" size="8"></s:textfield></td>
              
              <td><!-- 院校类型-->
					<s:select name="rlgl010306EduInfoList[0].college_type" cssClass="yxlx"  list="collegetypelist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey=""/>  
			 </td>
			 <!-- 所学专业 -->
              <td><s:textfield  name="rlgl010306EduInfoList[0].profession" cssClass="sxzy"  value="%{#request.rlgl010306EduInfoList[0].profession}" maxLength="20" size="8"></s:textfield></td>
               <!-- 证明人 -->
              <td><s:textfield  name="rlgl010306EduInfoList[0].proof_people" cssClass="zmr"  value="%{#request.rlgl010306EduInfoList[0].proof_people}" maxLength="20" size="6"></s:textfield></td>
              <td> <!-- 学历 -->
             	 <s:select name="rlgl010306EduInfoList[%{#st.index }].educational_bg" cssClass="xl"   list="educationalbglist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey=""/>
              </td>
              <td> <!-- 学位 -->
            	<s:select name="rlgl010306EduInfoList[%{#st.index }].degree"  cssClass="xw"  list="degreelist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey=""/>
			  </td>
              <td><input type="checkbox" name="object4" value="10" id="object4"/></td>
            </tr>
            </s:else>
            
        </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
            <td height="30" align="right">
              <input type="button" name="btn_Add" class="inp_L3" value="行追加" onClick="addNewJyjl('tab_jyjl')"/>&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="button" name="btn_Delete" class="inp_L3" value="行删除" onClick="delet('object4','tab_jyjl');"/>&nbsp;&nbsp;&nbsp;&nbsp;
              <s:file style="display:none" name="fileProfessionalInfo" id="file4" label="选择文件" onChange="fileInput('rlgl010306EduInfoList',this.value)"></s:file>
              <input type="button" name="btn_AllAdd" class="inp_L3" value="批量增加" onClick="showFileInput('file4')"/>
            </td>
            </tr>
        </table>
                </td>
            </tr>
        </table>
      </div>

      <div> 
        <table width="100%" border="0" cellspacing="0" cellpadding="0" ID="Table20">
          <tr> 
            <td height="28" style="padding-top:5px;" align="left"><font color="#1F6087" style="font-size:14px;"><strong>工作经历</strong></font></td>
          </tr>        
            <tr>
                <td style="PADDING-TOP: 2px">
            <TABLE cellSpacing="0" cellPadding="1" width="100%" border="1" ID="tab_gzjl" class="tabCss">
            <tr>
              <th height="28" width="16%" class="thTitleItrn">开始时间</th>
              <th width="16%" class="thTitleItrn">结束时间</th>
              <th width="16%" class="thTitleItrn">工作单位</th>
              <th width="16%" class="thTitleItrn">证明人</th>
              <th width="16%" class="thTitleItrn">职务</th>
              <th width="16%" class="thTitleItrn">电话</th>
              <th width="5%"class="thTitleItrn"><label>对象</label></th>
            </tr>
            <s:if test="%{rlgl010306WorkInfoList!=null&&rlgl010306WorkInfoList.size>0}">
            	<s:iterator  value="rlgl010306WorkInfoList" status='st'>
            		<tr>
              <td height="25"><!-- 开始时间 -->
					<s:if test="%{addFlag == 1}">
					<s:hidden  name="rlgl010306WorkInfoList[%{#st.index }].addFlag" />
					<s:hidden name="addFlag5_%{#st.index }" id="addFlag5_%{#st.index }" value="init_rlgl010306WorkInfoList_%{#st.index }__endtime,init_rlgl010306WorkInfoList_%{#st.index }__workunit,init_rlgl010306WorkInfoList_%{#st.index }__proofpeople,init_rlgl010306WorkInfoList_%{#st.index }__position,init_rlgl010306WorkInfoList_%{#st.index }__tel" />
					</s:if>
              		<s:textfield  name="rlgl010306WorkInfoList[%{#st.index }].starttime" cssClass="gz_kssj" onClick="WdatePicker();" onBlur="AddMark(this);" maxLength="8" size="10"></s:textfield>
              </td>
              <!-- 结束时间 -->
              <td><s:textfield  name="rlgl010306WorkInfoList[%{#st.index }].endtime"  cssClass="gz_jssj" onClick="WdatePicker();" onBlur="AddMark(this);" maxLength="20" size="10"></s:textfield></td>
              <!-- 工作单位 -->
              <td><s:textfield  name="rlgl010306WorkInfoList[%{#st.index }].workunit" cssClass="gz_gzdw" maxLength="50" size="10"></s:textfield></td>
            	 <!-- 证明人 -->
              <td><s:textfield  name="rlgl010306WorkInfoList[%{#st.index }].proofpeople" cssClass="gz_zmr" maxLength="20" size="10"></s:textfield></td>
              <!-- 职务 -->
              <td><s:textfield  name="rlgl010306WorkInfoList[%{#st.index }].position" cssClass="gz_zw" maxLength="20" size="10"></s:textfield></td>
              <!-- 电话 -->
              <td><s:textfield  name="rlgl010306WorkInfoList[%{#st.index }].tel"  cssClass="textphonenum"  maxLength="20" size="10"></s:textfield></td>
              <td><input type="checkbox" name="object5" value="" id="object5"/></td>
            </tr> 
            	</s:iterator>
            </s:if>
            <s:else>
            <tr>
              <td height="25"><s:textfield  name="rlgl010306WorkInfoList[0].starttime" cssClass="gz_kssj" value="%{#request.rlgl010306WorkInfoList[0].starttime}"  onClick="WdatePicker();" onBlur="AddMark(this);" maxLength="8" size="10"></s:textfield></td>
              <td><s:textfield  name="rlgl010306WorkInfoList[0].endtime" cssClass="gz_jssj" value="%{#request.rlgl010306WorkInfoList[0].endtime}" onClick="WdatePicker();" onBlur="AddMark(this);"  maxLength="20" size="10"></s:textfield></td>
              <td><s:textfield  name="rlgl010306WorkInfoList[0].workunit" cssClass="gz_gzdw" value="%{#request.rlgl010306WorkInfoList[0].workunit}" maxLength="50" size="10"></s:textfield></td>
              <td><s:textfield  name="rlgl010306WorkInfoList[0].proofpeople" cssClass="gz_zmr" value="%{#request.rlgl010306WorkInfoList[0].proofpeople}" maxLength="20" size="10"></s:textfield></td>
              <td><s:textfield  name="rlgl010306WorkInfoList[0].position" cssClass="gz_zw" value="%{#request.rlgl010306WorkInfoList[0].position}" maxLength="20" size="10"></s:textfield></td>
              <td><s:textfield  name="rlgl010306WorkInfoList[0].tel"  cssClass="textphonenum" value="%{#request.rlgl010306WorkInfoList[0].tel}" maxLength="20" size="10"></s:textfield></td>
              <td><input type="checkbox" name="object5" value="" id="object5"/></td>
            </tr>
            </s:else>
             
          </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
            <td height="30" align="right">
              <input type="button" name="btn_Add" class="inp_L3" value="行追加" onClick="addNewGzjl('tab_gzjl')"/>&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="button" name="btn_Delete" class="inp_L3" value="行删除" onClick="delet('object5','tab_gzjl');"/>&nbsp;&nbsp;&nbsp;&nbsp;
              <s:file style="display:none" name="fileProfessionalInfo" id="file5" label="选择文件" onChange="fileInput('rlgl010306WorkInfoList',this.value)"></s:file>
              <input type="button" name="btn_AllAdd" class="inp_L3" value="批量增加" onClick="showFileInput('file5')"/>
            </td>
            </tr>
        </table>
                </td>
            </tr>
        </table>
      </div>
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
              <input type="button" name="btn_Add" class="inp_L3" value="行追加" onClick="addNewDpxx('tab_dpxx')"/>&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="button" name="btn_Delete" class="inp_L3" value="行删除" onClick="delet('object6','tab_dpxx');"/>
              <s:file style="display:none" name="fileProfessionalInfo" id="file6" label="选择文件" onChange="fileInput('rlgl010306PartisanInfoList',this.value)"></s:file>
              <input type="button" name="btn_AllAdd" class="inp_L3" value="批量增加" onClick="showFileInput('file6')"/>
            </td>
            </tr>
        </table>
                </td>
            </tr>
        </table>
      </div>

<div> 
        <table width="100%" border="0" cellspacing="0" cellpadding="0" ID="Table20">
          <tr> 
            <td height="28" style="padding-top:5px;" align="left"><font color="#1F6087" style="font-size:14px;"><strong>导师信息</strong></font></td>
          </tr>
            <tr>
                <td style="PADDING-TOP: 2px">
            <TABLE cellSpacing="0" cellPadding="1" width="100%" border="1" ID="tab_dpxx" class="tabCss">
            <tr>
              <th height="28" width="20%" class="thTitleItrn">导师类别</th>
              <th height="28" width="20%" class="thTitleItrn">导师姓名</th>
              <th width="20%" class="thTitleItrn">所在大学</th>
              <th width="20%" class="thTitleItrn">研究方向</th>
             
            </tr>
            <s:if test="%{rlgl010306TutorInfoList!=null&&rlgl010306TutorInfoList.size>0}">
            	<s:iterator  value="rlgl010306TutorInfoList" status='st'>
            <tr>
            	<td>
            		<s:select name="rlgl010306TutorInfoList[%{#st.index }].teachertype"  id="dslb" list="teachertypelist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey=""/>
            	</td>
              <td><s:textfield  name="rlgl010306TutorInfoList[%{#st.index }].name" id="dsxm" maxLength="20" size="30"></s:textfield></td>
              <td>
              	<s:textfield  name="rlgl010306TutorInfoList[%{#st.index }].school" id="szdx" maxLength="20" size="30"></s:textfield>
              </td>
              <td>
                <s:textfield  name="rlgl010306TutorInfoList[%{#st.index }].researcharea" id="yjfx" maxLength="20" size="30"></s:textfield>
              </td>
           
            </tr> 
            	</s:iterator>
            </s:if>
            <s:else>
            	<tr>
            	 <td>
            <s:select name="rlgl010306TutorInfoList[0].teachertype" id="dslb" list="teachertypelist" listKey="adm_num" listValue="adm_name" headerValue="- -" headerKey=""/>
            </td>
              <td><s:textfield  name="rlgl010306TutorInfoList[0].name" id="dsxm" value="%{#request.rlgl010306TutorInfoList[0].name}"  maxLength="20" size="30"></s:textfield></td>
              <td><s:textfield  name="rlgl010306TutorInfoList[0].school"  id="szdx" value="%{#request.rlgl010306TutorInfoList[0].school}" maxLength="20" size="30"></s:textfield></td>
              <td><s:textfield  name="rlgl010306TutorInfoList[0].researcharea" id="yjfx" value="%{#request.rlgl010306TutorInfoList[0].researcharea}" maxLength="20" size="30"></s:textfield></td>
             
            </tr> 
            </s:else>
            
          </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:none">
            <tr>
            <td height="30" align="right">
              <input type="button" name="btn_Add" class="inp_L3" value="行追加" onClick="addNewDpxx('tab_dpxx')"/>&nbsp;&nbsp;&nbsp;&nbsp;
              <input type="button" name="btn_Delete" class="inp_L3" value="行删除" onClick="delet('object8','tab_dpxx');"/>&nbsp;&nbsp;&nbsp;&nbsp;
              <s:file style="display:none" name="fileProfessionalInfo" id="file8" label="选择文件" onChange="fileInput('rlgl010306PartisanInfoList',this.value)"></s:file>
              <input type="button" name="btn_AllAdd" class="inp_L3" value="批量增加" onClick="showFileInput('file8')"/>
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
    <script type="text/javascript" language="javascript"> 
    $("#showImgFile").attr("src", $("#showImgFile")[0].src+"?rand="+new Date().getTime());
    </script>
    </body>
</html>
