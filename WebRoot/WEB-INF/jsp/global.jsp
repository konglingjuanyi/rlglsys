<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="my" uri="/WEB-INF/navigation.tld"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<!-- <link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/style/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/style/boxy.css" rel="stylesheet" type="text/css" />  -->
 <link rel="alternate icon" type="image/png" href="${pageContext.request.contextPath}/assets/i/favicon.png">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/amazeui.min.css"/> 
  <link rel="stylesheet" href="assets/css/admin.css">
  
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.2.6.pack.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.boxy.js"></script>
 --%><script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>

<%! private String getHTTPDate() {
    java.text.SimpleDateFormat formatter =
      new java.text.SimpleDateFormat("E, dd MMM yyyy hh:mm:ss zzz", java.util.Locale.US);
    formatter.setTimeZone(java.util.TimeZone.getTimeZone("GMT"));
    return formatter.format(new java.util.Date());
  }
%>
<%
  response.setHeader("Expires", getHTTPDate());
  response.setHeader("Pragma","no-cache");
  response.setHeader("Cache-Control","no-cache");
%>
</head>
<!-- <body>
    <div id="loading" style="position:absolute;left:0;width:100%;height:100%;top:0;background:#FFFFFF;opacity:0.1;filter:alpha(opacity=10);Z-index:9999;">
    </div>
    <div id="loading2" style="position:absolute;cursor:wait;left:50%;top:50%;padding:0;color:#000;Z-index:9999;">
    <img src="${pageContext.request.contextPath}/images/pagination_loading.gif"/></div>
</body>  -->

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="assets/js/jquery.min.js"></script>
<!--<![endif]-->
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/app.js"></script>
</body>