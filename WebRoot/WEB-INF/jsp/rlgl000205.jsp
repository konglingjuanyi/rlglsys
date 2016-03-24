<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title><s:property value="getText('rlglsys.browserhead.IE')" /></title>
<%
response.addHeader("Cache-Control", "no-cache");
%>

<script language="JavaScript">
$(document).ready(function(){
    // 返回
    $("#btnReturn").click(function(){
       var callBackFun = function() {
         $("#form1").attr("action", "rlgl000204Init.action");
         $("#form1").submit();
       };
       confirmMessage("CM066", callBackFun);
    });
    // 返回明细画面
    $("#btnReturnDetail").click(function(){
       var callBackFun = function() {
         $("#form1").attr("action", "rlgl000208Init.action");
         $("#form1").submit();
       };
       confirmMessage("CM067", callBackFun);
    });
    // 查询
    $("#btnSearch").click(function(){
         $("#form1").attr("action", "rlgl000205Search.action");
         $("#form1").submit();
    });
    // 保存
    $("#btnSave").click(function(){
        var m = 0;
        $("input[id='select_kbn_check']").each(function(idx, obj) {
            if (this.checked == true)
            {
                 m++;
            }
        });
        if (m == 0)
        {
            alertMessage("AM089");
            return false;
        }
        confirmMessage("CM068", confirmRole);
    });
    // 菜单选择框级联
    $("#menu_id1").change(function(){
         comboxLinkageStructure.url = "ComboxContextFromListFor000204.action";
         comboxLinkageStructure.nextComboxId = "menu_id2,menu_id3,menu_id4";
         comboxLinkageStructure.nextComboxName = "全部,全部,全部";
         comboxLinkageStructure.urlParams = $(this).val() + ",0";
         comboxChanged();
    });
    $("#menu_id2").change(function(){
         comboxLinkageStructure.url = "ComboxContextFromListFor000204.action";
         comboxLinkageStructure.nextComboxId = "menu_id3,menu_id4";
         comboxLinkageStructure.nextComboxName = "全部,全部";
         comboxLinkageStructure.urlParams = $(this).val() + ",1";
         comboxChanged();
    });
    $("#menu_id3").change(function(){
         comboxLinkageStructure.url = "ComboxContextFromListFor000204.action";
         comboxLinkageStructure.nextComboxId = "menu_id4";
         comboxLinkageStructure.nextComboxName = "全部";
         comboxLinkageStructure.urlParams = $(this).val() + ",2";

         comboxChanged();
    });
    // 画面初始化
    initialize();
    
    $("input[id='select_kbn_check']").click(function(){
         if ($(this).attr("checked"))
         {
         	var menuId = $(this).next().next().next().val();
         	$(".C" + menuId).each(function(idx, obj) {
         	    $(this).attr("checked","true");
         	});
         	
         	var parentId = $(this).next().next().val();
         	while(parentId != "") {
         	    var parentNode = $("input[type=checkbox][name='name_" + parentId + "']");
         	    parentNode.attr("checked","true");
         	    parentId = parentNode.next().next().val();
         	}
         } else {
            var menuId = $(this).next().next().next().val();
         	$(".C" + menuId).each(function(idx, obj) {
         	    $(this).attr("checked","");
         	});
         	
         	var parentId = $(this).next().next().val();
         	while(parentId != "") {
         	    var parentNode = $("input[type=checkbox][name='name_" + parentId + "']");
         	    parentNode.attr("checked","");
         	    var menuId = parentNode.next().next().next().val();
         	    var checkFlg = false;
         	    if($(".C" + menuId + ":checked").length > 0) {
         	       checkFlg = true;
         	    }
         	    //$(".C" + menuId).each(function(idx, obj) {
         	     //   if ($(this).attr("checked")) {
         	      //      checkFlg = true;
         	      //  }
         	   // });
         	    if (checkFlg) {
         	        parentNode.attr("checked","true");
         	        parentId = "";
         	    } else {
         	        parentId = parentNode.next().next().val();
         	    }
         	}
         }
    });
    // 全部基本查询 
    $("input[id='serch_all_check']").click(function(){
      if ($(this).attr("checked"))
      {
          $("input[id='serch_kbn_check']").each(function(idx, obj) {
              this.checked = true;
         });
      } else 
      {
           $("input[id='serch_kbn_check']").each(function(idx, obj) {
              this.checked = false;
         });
      }
    });
    
    // 展开收缩
    $("img[id='openclose']").click(function(){
        var showflg = $(this).next().next().val();
        var trId = $(this).parent().parent().attr("id");
        if (showflg == "0") {
            $("." + trId + "show").each(function(idx, obj) {
                $(this).show();
            });
            $(this).next().next().val("1");
            $(this).attr("src","${pageContext.request.contextPath}/statu_open.png");
        } else {
            $("." + trId + "hide").each(function(idx, obj) {
                $(this).children().next().next().children().next().val("0");
                $(this).hide();
                $(this).children().next().next().children().attr("src","${pageContext.request.contextPath}/statu_close.png");
            });
            $(this).next().next().val("0");
            $(this).attr("src","${pageContext.request.contextPath}/statu_close.png");
        }
    });
    
    // 展开收缩
    $("a[id='aMenuName']").click(function(){
        var showflg = $(this).next().val();
        var trId = $(this).parent().parent().attr("id");
        if (showflg == "0") {
            $("." + trId + "show").each(function(idx, obj) {
                $(this).show();
            });
            $(this).next().val("1");
            $(this).prev().attr("src","${pageContext.request.contextPath}/statu_open.png");
        } else {
            $("." + trId + "hide").each(function(idx, obj) {
                $(this).children().next().next().children().next().val("0");
                $(this).hide();
                $(this).children().next().next().children().attr("src","${pageContext.request.contextPath}/statu_close.png");
            });
            $(this).next().val("0");
            $(this).prev().attr("src","${pageContext.request.contextPath}/statu_close.png");
        }
    });
    $(".RowOdd").each(function(idx, obj) {
        $(this).hide();
    });
    $(".RowEven").each(function(idx, obj) {
        $(this).hide();
    });
    $("#result").show();
    $(".topmenu").each(function(idx, obj) {
        $(this).show();
    });
});
function initialize(){
    $("input[id='select_kbn_check']").each(function(idx, obj) {
        var parentId = $(this).next().next().val();
        var menuId = $(this).next().next().next().val();
        if (parentId != "") {
            var parentCss = $("input[type=checkbox][name='name_" + parentId + "']").attr("class");
            $(this).addClass(parentCss + " C" + menuId);
            $(this).parent().parent().attr("id", "tr" + menuId);
            $(this).parent().parent().addClass("tr" + parentId + "show");
            var hidecss = $("#tr" + parentId).children().next().children().val();
            $(this).parent().next().children().val(hidecss + " tr" + parentId + "hide");
            $(this).parent().parent().addClass($(this).parent().next().children().val());
        } else {
            $(this).addClass("C" + menuId);
            $(this).parent().parent().attr("id", "tr" + menuId);
            $(this).parent().parent().addClass("topmenu");
        }
    });
}

function confirmRole() {
    var selectKbn = "";
    var selectSearchKbn = "";
    var i = 0;
    // 遍历checkBOx
    $("input[id='select_kbn_check']").each(function(idx, obj) {
        if (i == 0)
        {
            if (this.checked == true)
            {
                selectKbn = selectKbn + "1";
            }
            else
            {
                selectKbn = selectKbn + "0";
            }
        }
        else
        {
            if (this.checked == true)
            {
                selectKbn = selectKbn + ",1";
            }
            else
            {
                selectKbn = selectKbn + ",0";
            }
        }
        i++;
    });
    i = 0;
    var menuId = "";
    $("input[id='serch_kbn_check']").each(function(idx, obj) {
        menuId = $(this).parent().parent().children().children().next().next().next().val();
        if (i == 0)
        {
            if (this.checked == true)
            {
                selectSearchKbn = selectSearchKbn + menuId;
            }
        }
        else
        {
            if (this.checked == true)
            {
                selectSearchKbn = selectSearchKbn + "," + menuId;
            }
        }
        i++;
    });

    // 给隐藏变量赋值
    $("#menu_sel_check").val(selectKbn);
    $("#searchOnly_sel_check").val(selectSearchKbn);
    $("#form1").attr("action", "rlgl000205Confirm.action");
    $("#form1").submit();
}
</script>
  </head>
  <body><div>
  <s:form id="form1" name="form1" method="post" action="rlgl000205Init" nameSpace="/rlgl">
    <s:hidden name="navigationId" id="navigationId"/>
    <s:hidden name="screenId" id="screenId"/>
    <s:hidden name="rlgl000204Bean.menu_sel_check" id="menu_sel_check"/>
    <s:hidden name="rlgl000204Bean.searchOnly_sel_check" id="searchOnly_sel_check"/>
    <s:hidden name="roleDetail_pageFlg" id="roleDetail_pageFlg"/>
        <s:hidden name="rlgl000204Bean.user_enter" id="rlgl000204Bean_user_enter"/>
    <div id="searchInfo">
    <my:navigation></my:navigation>
    <my:message></my:message>
    <div class="content">
       <div style="position: relative">
        <table width="100%" style="position: relative; top: 10px;" border="1" cellpadding="2" cellspacing="2">
           <tr>
           <td width="15%" class="lc1" >角色编号</td>
            <td width="20%" >
                <s:property value="rlgl000204Bean.role_id"/>
                <s:hidden id="rlgl000204Bean_role_id" name="rlgl000204Bean.role_id"/>
                <s:hidden name="rlgl000204Bean.unit_no" id="rlgl000204Bean_unit_no"/>
                <s:hidden name="rlgl000204Bean.unit_role" id="rlgl000204Bean_unit_role"/>
            </td>
            <td width="15%" class="lc1" >角色名称</td>
            <td>
                <s:property value="rlgl000204Bean.role_name"/>
                <s:hidden name="rlgl000204Bean.role_name" id="rlgl000204Bean_role_name"/>
            </td>
          </tr>
          <tr>
           <td width="15%" class="lc1" >选择菜单</td>
            <td colspan="3">
               <s:select id="menu_id1" name="rlgl000204Bean.menu_id1" list="menuLevel1List" listKey="menu_id" listValue="menu_name" headerKey="" headerValue="--请选择全部--" cssClass="txt1"/>
               <s:select id="menu_id2" name="rlgl000204Bean.menu_id2" list="menuLevel2List" listKey="menu_id" listValue="menu_name" headerKey="" headerValue="--请选择全部--" cssClass="txt1"/>
               <s:select id="menu_id3" name="rlgl000204Bean.menu_id3" list="menuLevel3List" listKey="menu_id" listValue="menu_name" headerKey="" headerValue="--请选择全部--" cssClass="txt1"/>
               <s:select id="menu_id4" name="rlgl000204Bean.menu_id4" list="menuLevel4List" listKey="menu_id" listValue="menu_name" headerKey="" headerValue="--请选择全部--" cssClass="txt1"/>
            </td>
          </tr>
          <tr>
            <td align="right" colspan="4">
            <s:if test="%{roleDetail_pageFlg == 1}">
                <input type="button" class="inp_L3" value="返回" name="btnReturnDetail" id="btnReturnDetail">
            </s:if>
            <s:else>
                <input type="button" class="inp_L3" value="返回" name="btnReturn" id="btnReturn">
            </s:else>
            <input type="button" class="inp_L3" value="查询" name="btnSearch" id="btnSearch">
            <input type="button" class="inp_L3" value="保存" name="btnSave" id="btnSave">
            </td>
          </tr>
        </table>
      </div>
    
      <div id="result" style="position: relative;top:20px;display:none">
        <table width="100%" style="position: relative;" border="1" cellpadding="2" cellspacing="2">
       <tr>
          <th height="28" width="30px" class="thTitleItrn">选择</th>
          <th height="28" width="30px" class="thTitleItrn">序号</th>
          <th height="28" class="thTitleItrn">菜单名称</th>
          <th height="28" width="10%" class="thTitleItrn">菜单层级</th>
          <th height="28" width="80px" class="thTitleItrn">基础查询&nbsp;<s:checkbox id="serch_all_check" name="serch_all_check"/></th>
        </tr>
          <my:roleMenu></my:roleMenu>
        </table>
      </div>
      </div>
  </div>
</s:form>
</div>
  </body>
</html>
