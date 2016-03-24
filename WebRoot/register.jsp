<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
  <title>
    <s:property value="getText('rlglsys.browserhead.IE')" />
  </title>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="alternate icon" type="image/png" href="assets/i/favicon.png">
  <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
  <style>
    .header {
      text-align: center;
    }
    .header h1 {
      font-size: 200%;
      color: #333;
      margin-top: 30px;
    }
    .header p {
      font-size: 14px;
    }
  </style>
  
  
 
  <%
   response.addHeader("Cache-Control", "no-cache");
  %>
  <!-- <link href="${pageContext.request.contextPath}/style/login.css" rel="stylesheet" type="text/css"/> 
  <link rel="icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon" /> 
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon" /> -->
  
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/topMenu.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.boxy.js"></script>
 <!-- 超链接的点击事件 -->
 <script language="JavaScript">
 function reloadImg() {
    $("#identifyingCode").attr("src", "${pageContext.request.contextPath}/authImg?h="+Math.random());
    $("#authImgNo").focus();
 }
 $(function(){
    // 注册按钮押下
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
 });
 </script> 
</head>
<body  >


  <div class="<s:property value='selectPage' />">
    <h3><img id="imgTitle" class="tit" src="${pageContext.request.contextPath}/images/login/tit_5.png"/></h3>
    <img src="${pageContext.request.contextPath}/images/login/su.png" class="su"/>
    <div class="reigst">
      <div class="reigstL">
        <img src="${pageContext.request.contextPath}/images/login/logo.png"/><br/>
        <p>欢迎注册</p>
      </div>
      <div class="loginR">
        <s:form action="doLogin" nameSpace="/rlgl" id="form1">        
          <label><span class="chin">用户名</span><span class="eng">USER ID</span></label>
            <s:textfield name="" cssClass="login_input" maxLength="20" id="user_id"/><br />
          <label><span class="chin">密&nbsp;&nbsp;码</span><span class="eng">PASSWORD</span></label>
            <s:password name="" cssClass="login_pass" maxLength="20" id="password"/><br/>
          <label><span class="chin">所在单位</span><span class="eng">UNIT</span></label>
            <s:password name="" cssClass="login_pass" maxLength="20" id="password"/><br/>
          <label><span class="chin">进入方式</span><span class="eng">ENTER</span></label>
            <s:password name="" cssClass="login_pass" maxLength="20" id="password"/><br/>
          <label><span class="chin">验证码</span><span class="eng">CODE</span></label>
            <s:textfield name="" cssClass="code" size="5" maxLength="4" id="authImgNo"/>
            <img id="identifyingCode" class="imgCode" src="${pageContext.request.contextPath}/authImg"/>
            <a href="javascript:reloadImg();" class="see">看不清楚？</a><br />
          <span><font color="red"><s:actionerror/></font></span>
            <br />
          <a id="btnLogin" class="log login_1" href="#">注册</a>
          <a id="btnReturn" class="log exit" href="#">退出</a>
        </s:form>
       <s:form id="form2" action="doLogin" nameSpace="/rlgl" method="post">
       <s:hidden name="returnFlg" id="returnFlg"/>
       </s:form>
      </div>
    </div>
  </div>
</body>
</html>