<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="my" uri="/WEB-INF/navigation.tld"%> 

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Amaze UI Admin form Examples</title>
  <meta name="description" content="这是一个form页面">
  <meta name="keywords" content="form">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <link rel="icon" type="image/png" href="assets/i/favicon.png">
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
  <meta name="apple-mobile-web-app-title" content="Amaze UI" />
  <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
  <link rel="stylesheet" href="assets/css/admin.css">
  
  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/style/boxy.css" rel="stylesheet" type="text/css" /> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.2.6.pack.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.boxy.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
  <title>
    <s:property value="getText('rlglsys.browserhead.IE')" />
  </title>
</head>
<script language="javascript" type="text/javascript">
    //说明 所有的元素以ul li ul li ul li的循环格式嵌套 如果没有下级分类 就用li a结束嵌套
    $(document).ready(function(){
        // 画面初始化
        initialize();
    
        // 展开收缩
        $(".opencloseicon").click(function(){
            var trIndex = $(this).parent().children().val();
            var showflg = $("#showflg_" + trIndex).val();
            var trId = $(this).parent().parent().attr("id");
            
            if (showflg == "0") {
                closeOtherMenu(trId);
            } else {
                $("." + trId + "hide").each(function(idx, obj) {
                    hideTr($(this));
                });
                $("#showflg_" + trIndex).val("0");
                $(this).attr("src","${pageContext.request.contextPath}/images/statu_close.png");
            }
        });

        // 展开收缩
        $("a[id='aMenuName']").click(function(){
            var trIndex = $(this).parent().children().val();
            var showflg = $("#showflg_" + trIndex).val();
            var trId = $(this).parent().parent().attr("id");
            if (showflg == "0") {
                closeOtherMenu(trId);
            } else {
                $("." + trId + "hide").each(function(idx, obj) {
                    hideTr($(this));
                });
                $("#showflg_" + trIndex).val("0");
                $("#openclose_" + trIndex).attr("src","${pageContext.request.contextPath}/images/statu_close.png");
            }
        });
    });
      
    // 入口按钮押下
    function hideTr(curTr) {
        var index = curTr.children().children().val();
        $("#showflg_" + index).val("0");
        curTr.hide();
        $("#openclose_" + index).attr("src","${pageContext.request.contextPath}/images/statu_close.png");
    }
      
    // 入口按钮押下
    function showTr(curTr) {
        var index = curTr.children().children().val();
        $("#showflg_" + index).val("1");
        curTr.show();
        $("#openclose_" + index).attr("src","${pageContext.request.contextPath}/images/statu_open.png");
    }
      
    // 入口按钮押下
    function closeOtherMenu(trId) {
        var tmp = trId;
        var parentIdArray = new Array();
        while(tmp != "") {
            parentIdArray.push(tmp);
            var curIndex = $("#" + tmp).children().children().val();
            var parentId = $("#menu_param_id_" + curIndex).val();
            if (parentId == "") {
                tmp = "";
            } else {
                tmp = $("#tr" + parentId).attr("id");
            }
        }
        $("tr").each(function(idx, obj) {
            hideTr($(this));
        });
        $(".topmenu").each(function() {
            $(this).show();
        });
        for(var i = parentIdArray.length - 1; i >= 0; i--) {
            $("." + parentIdArray[i] + "show").each(function(idx, obj) {
                $(this).show();
            });
            var trIndex = $("#" + parentIdArray[i]).children().children().val();
            $("#showflg_" + trIndex).val("1");
            $("#openclose_" + trIndex).attr("src","${pageContext.request.contextPath}/images/statu_open.png");
        }
    }
      
    // 入口按钮押下
    function menuClick(action, naviId, menu_name,only_search, checkResult) {
        if (action.indexOf("?") > 0) {
            action = action + "&naviId=" + naviId + "&only_search=" + only_search + "&checkResult=" + checkResult;
        } else {
            action = action + "?naviId=" + naviId + "&only_search=" + only_search + "&checkResult=" + checkResult;
        }
        parent.contents.addTab(action, menu_name);
    }

    function initialize(){
        $("tr").each(function() {
            var index = $(this).children().children().val();
            var parentId = $("#menu_param_id_" + index).val();
            if (parentId != "") {
                var hidecss = $("#tr" + parentId).children().children().next().val();
                $("#hideCss_" + index).val(hidecss + " tr" + parentId + "hide");
                $(this).addClass($(this).children().children().next().val());
            } 
        });
        $(".topmenu").each(function() {
            $(this).show();
        });
    }
</script>
<body style="background-color:rgba(254, 254, 254, 1);min-width:210px;overflow-y:scroll;overflow-x:hidden;">
<s:form id="leftMenu" action="menuInit" nameSpace="/rlgl" target="contents" method="POST"> 
<s:hidden name="screenId" id="screenId"/>
  <div  id="result">
      <table  id="menuT" width="100%" style="position: relative;" class="am-table-hover"  cellpadding="2" cellspacing="2">
          <my:menu></my:menu>
        </table>
  </div>
</s:form>
</body>
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