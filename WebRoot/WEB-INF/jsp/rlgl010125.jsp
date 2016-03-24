<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <TITLE>
              日照医学教育
     </TITLE>
<script language="JavaScript">
$(document).ready(function(){
    //搜索
    $("#btnSearch").click(function(){
        //document.form.action = "rlgl010125Search.action";
    	//document.form.nameSpace = "/rlgl";
    	//document.form.submit();
    	$("#form").attr("action", "rlgl010125Search.action");
        $("#form").submit();
    });
    $("#btnSearch").click(function(){
        //document.form.action = "rlgl010125Search.action";
    	//document.form.nameSpace = "/rlgl";
    	//document.form.submit();
    	$("#form").attr("action", "rlgl010125UserView.action");
        $("#form").submit();
    });
   
});
//添加操作人
function addOperator(unit_no,unit_nm) { 
    $("#operatorUnitNo").val(unit_no);
    $("#operatorUnitNm").val(unit_nm);
    $("#form").attr("action", "rlgl010109AddOperator.action");
    $("#form").submit();
    //document.form.action = "rlgl010109AddOperator.action";
    //document.form.nameSpace = "/rlgl";
    //document.form.submit();
}
//添加操作人
function view(user_id) { 
    $("#user_id").val(user_id);
    $("#form").attr("action", "rlgl010125UserView.action");
    $("#form").submit();
    //document.form.action = "rlgl010109AddOperator.action";
    //document.form.nameSpace = "/rlgl";
    //document.form.submit();
}

//切换单位管理代管单位
function switchUnit(unit_no) { 
      var switchUnitfun= function() {
             $("#unitNo").val(unit_no);
            logout(unit_no);
          };
     confirmMessage("CM054", switchUnitfun);
    //document.form.action = "rlgl010109AddOperator.action";
    //document.form.nameSpace = "/rlgl";
    //document.form.submit();
}
function logout(unit_no){
	var topMenu_top=0;
	var topMenu_left=0;
	var topMenu_x=980;
	var topMenu_y=680;
	var WINDOW_NAME = "rlglsys";
	var strParameters = "status=yes, toolbar=no, location=no, menubar=no, scrollbars=yes, resizable=yes,";
	strParameters = strParameters +  " top=" + topMenu_top +", left=" + topMenu_left +",  height=" + topMenu_y + ", width=" + topMenu_x;
	var StartGamen = window.open("./rlgl010125SwitchUnit.action?unitNo="+unit_no, WINDOW_NAME, strParameters);
	StartGamen.focus();
	top.opener = "";
	top.open("","_self");
}
</script>    
</head>
    <body>
    <table cellpadding='0' cellspacing='0' width='100%'>
      <tr>
        <td>
         <!-- 内容显示区域 -->
	          <my:navigation></my:navigation>
          <!-- 数据检索条件 --> 
           <!-- 数据检索条件 -->  
        <div class="content">
              <form name="form" id="form" nameSpace="/rlgl" action="doLogin" method="post" >
                  <s:hidden name="navigationId" id="navigationId"/>
          		  <s:hidden name="screenId" id="screenId"/>
                  <s:hidden name="operatorUnitNo" id="operatorUnitNo"/>
                  <s:hidden name="operatorUnitNm" id="operatorUnitNm"/>
                  <s:hidden name="unitNo" id="unitNo"/>
                  <s:hidden name="user_id" id="user_id"/>
                   <s:hidden name="only_search" id="only_search"/>
                  <table width="100%" valign="top" border="1px">
		         	 <caption>查询条件</caption>
		            <tr> 
		              <td class='lc1' sytle="">单位编号</td>
		              <td class='lc2'><s:textfield  name="unit.unit_no"  style="width:150px"/></td>
		              <td class='lc1'>单位名称</td>
		              <td class='lc2'><s:textfield  name="unit.unit_nm"  style="width:150px"/></td>
		              <td class='tdc' style="text-align:left"><input class="inp_L3" type="button" name="btn_Interview" value="查询" style="width:80px" id="btnSearch"></td>
		            </tr>
	          	  </table>
             
              <div id="result" style="position: relative;top:20px">
                <my:dividepage actionId="rlgl010125Search.action"></my:dividepage>
	            <table width="100%"  style="position: relative; top: 2px; left: 3px" valign="top" border="1px">
	              <tr>
	                <th class='thTitleItrn'>序号</th>
	                <th class='thTitleItrn'>单位编号</th>
	                <th class='thTitleItrn'>单位名称</th>
	                <th class='thTitleItrn'>区域划分</th>
	                <th class='thTitleItrn'>单位级别</th>
	                <th class='thTitleItrn'>单位类别</th>
	                <th class='thTitleItrn'>联系人</th>
	                <th class='thTitleItrn'>联系电话</th>
	                <th class='thTitleItrn'>邮箱</th>
	                <th width="110px" class='thTitleItrn'>用户操作</th>
	              </tr>
	              <s:iterator value="%{#request.unitList}" status='L'>
		              <my:trStripe index="${L.index}">
		                <td  class="tdc">${L.index +1}</td>
		                <td class='tdl'>
		                   <s:if test="unit_no==''"> 
		                      <s:property value='无'/>
		                   </s:if>
		                   <s:else>
		                      <s:property value='unit_no'/>
		                    </s:else>
		                </td>
		                <td class='tdl'><s:property value='unit_nm'/></td>
		                <td class='tdl'>
		                   <s:if test="area_id==''"> 
		                                                        无
		                   </s:if>
		                   <s:else>
		                       <s:property value='area_id'/>
		                    </s:else>
		                </td>
		                <td class='tdl'>
		                   <s:if test="unit_scale==''"> 
		                                                        无
		                   </s:if>
		                   <s:else>
		                       <s:property value='unit_scale'/>
		                    </s:else>
		                </td>
		                  <td class='tdl'>
		                   <s:if test="unit_assort==''"> 
		                                                        无
		                   </s:if>
		                   <s:else>
		                       <s:property value='unit_assort'/>
		                    </s:else>
		                </td>
		                 <td class='tdl'>
		                   <s:if test="contact ==''"> 
		                                                        无
		                   </s:if>
		                   <s:else>
		                       <a href="#" onclick="view('<s:property value='user_id'/>')"/><s:property value='contact'/></a>
		                    </s:else>
		                </td>
		                 <td class='tdl'>
		                   <s:if test="phone=='' "> 
		                                                        无
		                   </s:if>
		                   <s:else>
		                       <s:property value='phone'/>
		                    </s:else>
		                </td>
		                 <td class='tdl'>
		                   <s:if test="email==''"> 
		                                                        无
		                   </s:if>
		                   <s:else>
		                       <s:property value='email'/>
		                    </s:else>
		                </td>
		                <td class='tdc'>
		               <!--    <input class="inp_L3" type="button" value="添加操作人" name="infoChange" onclick="addOperator('<s:property value='unit_no'/>','<s:property value='unit_nm'/>')"/>&nbsp;--> 
		                  <input class="inp_L3 btnClass_${only_search}" type="button" value="切换"  onclick="switchUnit(<s:property value='unit_no'/>)"/>
		                </td>
		              </my:trStripe>
	              </s:iterator>
	          </table>
	          </div>
	           </form>
	        </div>
        </td>
      </tr>
    </table>
  </body>
</html>
