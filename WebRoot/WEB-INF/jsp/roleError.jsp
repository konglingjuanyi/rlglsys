<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><s:property value="getText('rlglsys.browserhead.IE')" /></title>
<link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/style/other.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/style/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/menu.js"></script>
</head>
<body>
    <jsp:include page="rlgl000201.jsp" />
    <div class="warp">
      <div class="warp_wz">
        <div class="warp_wz_wz">当前您所在的位置：</div>
      </div>
      <jsp:include page="rlgl000202.jsp" />

      <div class="column_wx">
      <div class="share">
        <div class="column_zs_leftbg">错误提示</div>
          <div class="column_zs_left_news">
              <ul class="errorMessage">
              <li>对不起，您没有访问本页面的权限</li>
              </ul>
          </div>
      </div>
      </div>
      <div style=" clear:both;"></div> 
    </div>
    
<jsp:include page="footer.jsp" />
</body>
</html>
