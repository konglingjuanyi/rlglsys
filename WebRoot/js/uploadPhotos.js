//图片id   
var previewId="";
//图片隐藏变量
var previewHidden="";
//上传图片预览
	function previewChange1(imgURL,imgNm,imgId,imgHidden,imgFile)
	{     
		
		  previewId=imgId;
		  previewHidden=imgHidden;
	      var Validate=ValidateImgUrl(imgURL);
	      var imgSize=imgSizeCorrection(imgURL);
	      //验证文件格式
	      if(Validate)
	      {   
	          //验证文件大小
	          if(imgSize)
	          { 
		       // preview.url = "previewChange.action";
		         var params = {
	                'params':getFullPath(imgURL)+","+imgNm+","+imgFile
	              }; 
		          $.post("previewChange.action",params, returnPreviewURL);
		       }
	      }
	}
	 function previewChange(imgfileId,imgNm,imgId,imgHidden,imgFile)
		{
		     var Validate=ValidateImgUrl(imgfileId);
		     //验证文件格式
		      if(Validate)
		      {  
				 $.ajaxFileUpload({  
		             url:"previewChange.action",            //需要链接到服务器地址
		             secureuri:false,
		             fileElementId:imgfileId,                        //文件选择框的id属性
		             dataType: "multipart/form-data", 
		             data:{ "fileName":$("#"+imgfileId).val(),"imgNm":imgNm,"imgFileNm":imgFile},                                   //服务器返回的格式，可以是json
		             success: function (data, status)            //相当于java中try语句块的用法
		             {
		            	 if(data=="2")
	            		 {
		            		 alertMessage("AM054");
	            		 }
		            	 else if(data=="0")
		                {
		            		 alertMessage("AM053");
		                }
		            	 else
	            		 {
		            		 var result1=data.split("/")[3];
		  				     $("#" + imgHidden).val(result1);
		  				     //刷新图片
		  				     $("#" + imgId).attr("src", ".."+data+"?rand="+new Date().getTime());
	            		 }
		             },
		             error: function (data, status, e)            //相当于java中catch语句块的用法
		             {
		                alert("添加失败");
		             }
		         });
		      }
		}
	
	
     //图片格式验证
     function ValidateImgUrl(imgfileId) {
         var img = $("#"+imgfileId).val();
         var filename = img.substring(img.lastIndexOf(".") + 1).toLowerCase();
         if (filename != "jpg" && filename != "gif" && filename != "png" && filename != "bmp" && filename != "jpeg") {
             alertMessage("AM052");
             return false;
         }
         if (img.length > 0) {
             //return document.getElementById(id).value;
             return true;
         }
         else { return false; }
     }
     //文件大小和比例限制
     function imgSizeCorrection(obj){ 
            img = new Image();  
            img.src = getFullPath(obj); 
            //验证上传文件宽高比例  
            //if (img.height / img.width > 0.5 || img.height / img.width < 0.25) {  
             //   alertMessage("AM053");
             //   return false;  
           // }  
            //验证上传文件是否超出了大小  
            if (img.fileSize / 1024 >1024) {
            	alertMessage("AM054");
                return false;  
            }
            return true;
     } 
     //获得文件
     function GetObj(id) {
            return "string" == typeof id ? document.getElementById(id) : id;
     }
   /***************************************************************
 * 获得上传文件返回值并刷新图片
 ***************************************************************/
  function returnPreviewURL(result, textStatus){
	 //$("#previewImg").attr("src", result); 
	 //给隐藏变量赋值
	 var result1=result.split("/")[2];
	 $("#" + previewHidden).val(result1);
	 //刷新图片
	 $("#" + previewId).attr("src", ".."+result+"?rand="+new Date().getTime());
}
    //获得客户端文件路径
	function getFullPath(obj) {    
		
        if (obj) {  
            //ie  
            if (window.navigator.userAgent.indexOf("MSIE") >= 1) {  
                obj.select();  
                return document.selection.createRange().text;  
            }  
            //firefox  
            else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
                if (obj.files) { 
                	//alert( window.URL.createObjectURL(obj.files[0]));
                	//return window.URL.createObjectURL(obj.files[0]) ;
                    return obj.files.item(0).getAsDataURL();  
                }
                return obj.value;  
            }  
            return obj.value;  
        }  
    } 
	//打开大图
    function viewMaxImg(img,id,location)
	{
	    $("#maxImg").attr("src",img.src);
	    var imgMax= new Image();
	    imgMax.src =$("#maxImg").attr("src");
	    $("#DivimgMax").height(imgMax.height+45);
	    $("#DivimgMax").width(imgMax.width+10);
	    $("#DivimgMax").css("position","absolute");
	     //右上角为基准
	    if(location=="rt")
	    {
	        $("#DivimgMax").css("top",$("#"+id).offset().top);
	        $("#DivimgMax").css("left",$("#"+id).offset().left+img.width-$("#DivimgMax").width()+ "px"); 
	    }
	    //左上角为基准
	    if(location=="lt")
	    {
		    $("#DivimgMax").css("top",$("#"+id).offset().top);
		    $("#DivimgMax").css("left",$("#"+id).offset().left); 
	    }
	    //左下角为基准
	    if(location=="lb")
	    {
		     $("#DivimgMax").css("top",$("#"+id).offset().top+img.height-$("#DivimgMax").height());
	    	 $("#DivimgMax").css("left",$("#"+id).offset().left); 
	    }
	   //右下角为基准
	    if(location=="rb")
	    {
		     $("#DivimgMax").css("top",$("#"+id).offset().top+img.height-$("#DivimgMax").height());
	    	 $("#DivimgMax").css("left",$("#"+id).offset().left+img.width-$("#DivimgMax").width()); 
	    }
        $("#DivimgMax").show();
        $("#bg").height($(".content").height());
        $("#bg").show();
	}
   
    $(document).ready(function(){
    	 //大图div移动事件
        $("#banner").mousedown(  
              
              function (event) { 
              var isMove = true; 
              var abs_x = event.pageX - $("#DivimgMax").offset().left;  
              var abs_y = event.pageY - $("#DivimgMax").offset().top; 
              $(document).mousemove(function (event) { 
                if (isMove) {
                     var obj = $("#DivimgMax"); obj.css({"left":event.pageX - abs_x, "top":event.pageY - abs_y});
                     } 
               }).mouseup( function () {
                 event.stopPropagation();  //防止冒泡事件响应
                 isMove = false;  }  ); 
              $(document).mouseup(function (event) {
                  event.stopPropagation();  //防止冒泡事件响应
                  isMove = false;
              });
             }  
        ); 
    });