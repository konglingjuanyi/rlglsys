<%@ page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>
    <s:property value="getText('rlglsys.browserhead.IE')" />
  </title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=8" />
  <%
   response.addHeader("Cache-Control", "no-cache");
  %>
  <!--  <link href="${pageContext.request.contextPath}/style/login.css" rel="stylesheet" type="text/css"/>---->
  <link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon" /> 
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon" />
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.boxy.js"></script>
    <link href="style/alogin.css" rel="stylesheet" type="text/css" />
<script language="JavaScript">

function reloadImg() {
    $("#identifyingCode").attr("src", "${pageContext.request.contextPath}/authImg?h="+Math.random());
    $("#authImgNo").focus();
}
/****************************************************************
 * 方法名：$(function(){
 * 概 要：页面各事件的定义，页面初期显示处理调用的Javascript定义
 * 参 数：无
 * 返回值：无
 ***************************************************************/
$(function(){
    // 登录按钮押下
    $("#btnLogin").click(function(){
      if (Trim($("#user_id").val()) == "")
      {
         alertMessage("AM039");
         $("#user_id").val(Trim($("#user_id").val()));
         return false;
      }
      
       if (Trim($("#authImgNo").val()) == "")
      {
         alertMessage("AM050");
         $("#authImgNo").val(Trim($("#authImgNo").val()));
         return false;
      }
     $("#form1").get(0).submit();
       return false;
    });
    // 返回按钮
    $("#btnReturn").click(function(){
      $("#returnFlg").val("1");
      $("#form2").attr("action", "topMenu.action");
      $("#form2").get(0).submit();
      return false;
    });
    //$("#user_id").blur(function(){
   //      comboxLinkageStructure.url = "ComboxContextFromListFor000102.action";
    //     comboxLinkageStructure.nextComboxId = "user_enter";
    //     comboxLinkageStructure.nextComboxName = "入口";
   //      comboxLinkageStructure.urlParams = $(this).val();
   //      comboxChanged();
   // });
    //$(window).resize(function(){
	  //$("#content").height($(document).height());
    //});

    // 画面初始化
    initialize();

	//$("#content").height($(document).height());
}); 
/****************************************************************
 * 方法名：initialize
 * 处理概要：画面出事话处理
 * 参数： 无
 * 返回值： 无
 ***************************************************************/
function initialize(){

    var selectPage = $("#selectPage").val();
    
    // 卫生技术人员入口
    if (selectPage == "loginpage3")
    {
    	$("#imgTitle").attr("src", "${pageContext.request.contextPath}/images/login/tit_3.png");
    }
    
    $("#authImgNo").val("");
}
</script>

</head>

<body>

<div class="Main">
        <ul>
            <li class="top"></li>
            <li class="top2"></li>
            <li class="topA"></li>
            <li class="topB"><span>
                <img src="images/login/logo.gif" alt="" style="" />
            </span></li>
            <li class="topC"></li>
            <li class="topD">
            
<div class="header">
  <div class="am-g">
    
  
<div class="am-g">
  <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
  
    <hr>
    
 <s:form action="doLogin" nameSpace="/rlgl" id="form1">

  
        <div class="am-form">
          <s:hidden name="selectPage" id="selectPage"/>
          <s:hidden name="selectTitle" id="selectTitle"/>
          <s:if test="%{selectPage == 'loginpage3'}">
		    <label for="user_id">身份证&nbsp;&nbsp;ID CARD</label>
		  </s:if>
		  
		  <s:else>
		    <label for="user_id" >用户名&nbsp;&nbsp;USER ID</label>
		  </s:else>
        
          <s:textfield name="user.user_id"  maxLength="20" id="user_id"/>
          <br /> 
          <br>
		
          <label for="password">密&nbsp;&nbsp;码&nbsp;&nbsp;PASSWORD</label>
            <s:password name="user.password"  maxLength="20" id="password"/>
            <br/>
               <br>
                 
          <label>验证码&nbsp;&nbsp;CODE</label>
            <s:textfield name="authImgNo"  size="5" maxLength="4" id="authImgNo"/>
            <img id="identifyingCode"  src="${pageContext.request.contextPath}/authImg"/>
            <a href="javascript:reloadImg();" >看不清楚？</a><br />
          <span><font color="red"><s:actionerror/></font></span>
                      <a href="${pageContext.request.contextPath}/forgetPassword.jsp" class="am-btn am-btn-default am-btn-sm am-fr">忘记密码</a>
           </div>
         </div>

      </div>
          <br/>
            <br>
	
           
        
        
      
      
          <a class="chin" ></a>
         
        </s:form> 
     <s:form id="form4" action="doLogin" nameSpace="/rlgl" method="post">
       <s:hidden name="returnFlg" id="returnFlg"/>
       </s:form>
                    
                    </li>
                    
                   
                </ul>
            </li>
            <ul>
            <li class="topE"></li>
            <li class="middle_A"></li>
            <li class="middle_B"></li>
            <li class="middle_C">
            <span class="btn">
               
          <br/>
          <br/>
   
   
            <div class="am-cf">
             <a id="btnLogin" class="am-btn am-btn-primary am-btn-sm am-fl" href="#"><img alt="" src="images/login/3.jpg" /></a>&nbsp;&nbsp;&nbsp;
          <a id="btnReturn" class="am-btn am-btn-danger am-btn-sm am-fl" href="/rlglsys/zcdw.jsp"><img alt="" src="images/login/2.jpg" /></a>
          
			<br>
    		<br>
			
      			
             
             
             
             
             
      		
		
			

    
           
             </span>
            </li>
            <li class="middle_D"></li>
            <li class="bottom_A"></li>
            <li class="bottom_B"></li>


        </ul>

    </div>
     
    
		<div class="am-u-lg-6 am-u-md-8 am-u-sm-centered am-text-center">
		  
			<div class="footR">
			<li>	<p>CopyRight 2015 All Rights reserved</p>
			</li>
			</div>
		 
	</div> 
	</div>
	

</body>
</html>