

<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <TITLE>
              日照医学教育
     </TITLE>
     <script type="text/javascript">
     function CloseWebPage(){
    	 if (navigator.userAgent.indexOf("MSIE") > 0) {
    	  if (navigator.userAgent.indexOf("MSIE 6.0") > 0) {
    	   window.opener = null;
    	   window.close();
    	  } else {
    	   window.open('', '_top');
    	   window.top.close();
    	  }
    	 }
    	 else if (navigator.userAgent.indexOf("Firefox") > 0) {
    	  window.location.href = 'about:blank ';
    	 } else {
    	  window.opener = null;
    	  window.open('', '_self', '');
    	  window.close();
    	 }
    	}
     </script>
   
</head>
   <body><div>
    
    <div class="content">
       
       <div class="am-panel am-panel-danger am-margin">
         <header class="am-panel-hd">
    <h3 class="am-panel-title">Tips</h3>
  </header>
  <p class =" am-padding am-danger">
 			缴费成功，单击<a href="#" onclick="CloseWebPage();">关闭本页面</a>
		</p>
		</div>
      </div>
  </div>

</div>
  </body>
</html>

