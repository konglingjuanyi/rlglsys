<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <TITLE>
              日照医学教育
     </TITLE>
<script language="JavaScript">
$(document).ready(function(){
  
  $("#select_kbn_check").click(function(){
          if(this.checked){
            $("#total").html("150.00");
           }
           else
           {
             $("#total").html($("#totalHid").val());
           }
    });
     $("#select_kbn_check").click(function(){
         openChildWindow.open("rlgl300101Init.action", param, callBackFun_unitSelect, "1",location=no);
    });
   
});
//返回页面
function goBack() {
   $("#rlgl022008form").attr("action", "rlgl022005Init.action");
   $("#rlgl022008form").submit();
}
function learnExam(end_date,course_id, course_support)
{
	learnOnline.url = "LearnOnlineLink.action";
    learnOnline.pageObjectId = "learnOnlineUrl";
    // 课程ID、课程提供方、画面标记、用户ID、系统网址
    learnOnline.urlParams = course_id +"," + course_support + "," + "rlgl022008" + ","+ $("#user_id").val()+"," + $("#sysUrl").val();
    learnOnlineLink();
}
</script>    
</head>
    <body>
     <my:navigation></my:navigation>
    <table cellpadding='0' cellspacing='0' width='100%'>
      <tr>
        <td> 
        
              <div class="content">
                <span style="color: red" >  小提示： </br>&nbsp;&nbsp;&nbsp;&nbsp;学习并经过考试合格后的课程不会出现在该列表中。 </br>
                                            &nbsp;&nbsp;&nbsp;&nbsp;学习的形式分视频与文本形式，您在观看视频的时候可能会遇到知识点答题。</span> 
		     </div>
		      <div class="content">
		      <form id="rlgl022008form" nameSpace="/rlgl" action="doLogin" method="post" >
		       <s:hidden name="navigationId" id="navigationId"/>
		       <s:hidden name="total" id="totalHid"/>
		        <s:hidden name="temp_str" id="temp_str"/>
		        <s:hidden name="end_date" id="end_date"/>
		        <s:hidden name="sysUrl" id="sysUrl" />
	           <table width="100%"  border="1px">
	           <caption>已选课程列表</caption>
		              <tr>
		                <th class='thTitleItrn'>序号</th>
		                <th class='thTitleItrn'>课件名称</th>
		                <th class='thTitleItrn'>出版单位</th>
		                <th class='thTitleItrn'>出品人</th>
		                <th class='thTitleItrn'>截止日期</th>
		                <th class='thTitleItrn'>单价</th>
		                <th class='thTitleItrn'>学分</th>
		                <th class='thTitleItrn'>学习/考试</th>
		              </tr>
		              
		              <s:iterator value="courseSelectedList" status="L">
			              <my:trStripe index="${L.index}">
			                <td class="tdc">${L.index +1}</td>
			                <td class='tbl' >
			                <s:if test="course_name==''"> 
			                   <s:property value='无'/>
			                </s:if>
			                <s:else>
			                 <s:property value='course_name'/>
			                </s:else>
			                  </td>
			                <td class='tbl' ><s:property value='course_expert_unit'/></td>
			                 <td class='tbl'  >
			                   <s:if test="course_expert==''"> 
			                                                         无
			                   </s:if>
			                   <s:else>
			                       <s:property value='course_expert'/>
			                    </s:else>
			                </td>
			                <td class='tbl' ><s:property value='end_date'/></td>
			                 
			                 <td class='tdc'  >
			                     <span style="color: red">￥<s:property value='course_price'/></span>
			                </td>
			                <td class='tdc'  >
		 						 <span style="color: #00AA00 "><s:property value='xuefen'/>(分)</span>
			                </td>
			                <td class='tdc'  >
		 						<input class="inp_L3 btnClass_${only_search}" type="button" id="butLearn" value="学习/考试" onclick="learnExam('<s:property value='end_date'/>','<s:property value='course_id'/>','<s:property value='course_support'/>');"/>
			                </td>
			              </my:trStripe>
		              </s:iterator>
		          </table>
	             </form>
	             </div>
        </td>
      </tr>
    </table>
  </body>
</html>
