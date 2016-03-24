<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="global.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title></title>
    <!--  <link href="${pageContext.request.contextPath}/style/themes/base/style.css" rel="stylesheet" type="text/css"/>-->
    <link href="${pageContext.request.contextPath}/style/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>

    <script src="${pageContext.request.contextPath}/js/jquery-1.7.2.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-ui.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.contextMenu.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.cleverTabs.js" type="text/javascript"></script>
    <script type="text/javascript">
        var tabs;
        $(document).ready(function(){
            tabs = $('#pageTabs').cleverTabs();
            $(window).bind('resize', function () {
                tabs.resizePanelContainer();
            });
            tabs.add({
                url: 'rlgl000203Init.action?naviId=navi001',
                label: '首页'
            });
        });
        function addTab(pageUrl, title) {
            tabs.add({
                url: pageUrl,
                label: title
            });
        }
    </script>
</head>
<body>
 
    <div id="pageTabs"  style="width:100%; height:100%;">
        <ul>
        </ul>
    </div>
    
</body>
</html>
