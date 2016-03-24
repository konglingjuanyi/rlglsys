<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

     <TITLE>
              日照医学教育
     </TITLE>
<script language="JavaScript">
// 筛选按钮的点击事件
 $(document).ready(function(){
 	var msg="${requestScope.message}";
	if(msg!=""){
		 alert(msg);
	}
	//篩選按鈕的點擊事件
  	$("#shaixuan").click(function(){ 
	  	 $("#rlgl022003form").attr("action", "coursInit_jn.action");
	     $("#rlgl022003form").attr("namespace", "/rlgl");
	     $("#rlgl022003form").submit();
  	});
  	
   });  
function saveUserRole(user_id)
{
   var sum=0;
    $("input[id='select_kbn_check']").each(function(idx, obj) {
          if(this.checked){
            sum+=parseInt(1);
           }
    });
    if(sum>0)
    {
//        	if(sum > 3){
//     	   alert("您选择了3门以上的课程，请重新选择！");
//     	   return false;
//     	}
//    	    var yxkcs = $("#yxkcs").val();
// 	    if(yxkcs >= 3){
// 	       alert("你已有三门已选课程，不用在进行选择课程！");
// 	       return false;
// 	    }
// 	    if(yxkcs ==1 && sum ==3){
// 	    	alert("你最多只可以选择二门课程！");
// 	    	return false;
// 	    }
// 	    if(yxkcs == 2 && sum > 1){
// 	    	alert("你最多可以选择一门课程！");
// 	    	return false;
// 	    }
	    
        $("#user_id").val(user_id);
	    $("#rlgl022003form").attr("action", "rlgl100103SaveCourse.action");
	    $("#rlgl022003form").attr("target","contents1");
	    $("#rlgl022003form").submit(); 
    }
    else
    {
       alert("请选择课程！");
       return false;
    }
    
}

function learnExam(end_date,course_id, course_support)
{
	learnOnline.url = "LearnOnlineLink.action";
    learnOnline.pageObjectId = "learnOnlineUrl";
    // 课程ID、课程提供方、画面标记、用户ID、系统网址
    learnOnline.urlParams = course_id +"," + course_support + "," + "rlgl100103" + ","+ $("#user_id").val()+"," + $("#sysUrl").val();
    learnOnlineLink();
}
</script>    
</head>
    <body>
    <table  style="width:100%">
      <tr>
        <td>
		     <div class="content">
		     <my:message></my:message>
	          <!-- 数据检索条件 -->  
	          <form name="form"  id="rlgl022003form" nameSpace="/rlgl" target="contents1" action="rlgl100103SaveCourse.action" method="post" >
	              <s:hidden name="user_id" id="user_id"/>
	              <s:hidden name="flg" id="flg"/>
	              <s:hidden name="course_catagory" id="course_catagory"/>
	              <s:hidden name="sysUrl" id="sysUrl" />
	              <%--已选课程数 --%>
	              <s:hidden name="yxkcCount" id="yxkcs"/>
	              <table style="width:100%;">
	              	<caption>选课列表</caption>
	              	<tr>
	              		<td>
	              			 筛选课程条件：<s:select list="kcThreeList" headerKey="" headerValue="点击选择" listKey="adm_num"  listValue="Adm_name" name="leibie3" ></s:select>&nbsp; &nbsp; 
	              			 <input type="button"  class="inp_L3" value="筛&nbsp;&nbsp;选" id="shaixuan"/></input>&nbsp;&nbsp;
	              			<input class="inp_L5 btnClass_${only_search}"   id="butAddCourse" type="button" value="添加课程" onclick="saveUserRole('<s:property value='user_id'/>')"></input>
	              		</td>
	              	</tr>
	              	
	              </table>
	              <div  style="width:99%;height:445px;overflow-y:auto;overflow-x:hidden;" >
	              <table style = "width:99%;valign:top;" class="am-table am-table-bordered am-table-radius am-table-striped am-table-hover">
		              <s:iterator value="resultList" status="L" var ='rlgl100103Bean'>
		              <s:if test="%{course_id_hyw != ''}">
		              	
		              	<tr>
		              		<td class='tbc' width="60">
				                
				               	 课件名称:
				               	 </td>
				               	 <td>
				               	 <s:property value='course_name_hyw'/><s:if test="%{course_id_hyw != ''}">
					                <input class="inp_L5 btnClass_${only_search}" type="button" 
				                 	 value="学 &nbsp;习" style="display:none" onclick="learnExam('<s:property value='end_date_hyw' />','<s:property value='course_id_hyw'/>','<s:property value='course_support_hyw'/>')"></input>
									 &nbsp; &nbsp; &nbsp; &nbsp;
			 						 选择课程：
			 						 <s:checkbox name="ishere" id="select_kbn_check" cssClass="ishere" fieldValue="%{#rlgl100103Bean.course_id_hyw}" />
				                </s:if>
				               	 </td>
		              	</tr>

			              <s:if test="%{course_id_hyw != ''}">
			              <tr>
			                <td class='tbc' width="60">
			                	课件类别: 
			                </td>
			                <td>
			                	<s:property value='course_catagory_nm_hyw'/>
			                </td>
			              </tr>
			              </s:if>
			              <s:if test="%{course_id_hyw != ''}">
			              <tr>
			                <td class='tbc' width="60">
			                  	出版单位:
			                </td>
			                <td>
			                  	<s:property value='course_expert_unit_hyw'/>
			                </td>
			              </tr>
			              </s:if>
			              <tr>
				                <td class='tbc' width="60">
				                	课程简介：
				                </td>
				                <td class='tdl' >
				                     <s:property value='course_explain_hyw'/>
				                </td>
			              </tr>  
			            </s:if>
			              
			               <tr border ="0" height="10"></tr>
			              <s:if test="%{course_id_hys != ''}">
			              <tr>
			              <td class='tbc' width="60">
			               	 课件名称:
			               	 </td>
			               	 <td><s:property value='course_name_hys'/></td>
			              </tr>
			              </s:if>
			               <s:if test="%{course_id_hys != ''}">
			              <tr>
			              <td class='tbc' width="60">
			               
			                	课件类别:</td>
			                	<td>
			                	<s:property value='course_catagory_nm_hys'/><s:if test="%{course_id_hys != ''}">
			                	<input class="inp_L5 btnClass_${only_search}" type="button" 
			                 	 value="学 &nbsp;习" style="display:none" onclick="learnExam('<s:property value='end_date_hys'  />','<s:property value='course_id_hys'/>','<s:property value='course_support_hys'/>')"></input>
								 &nbsp; &nbsp; &nbsp; &nbsp;选择课程：
								
								 <s:checkbox name="ishere" id="select_kbn_check" cssClass="ishere" fieldValue="%{#rlgl100103Bean.course_id_hys}" />
								
			               
			                	</td>
			              </tr>
			               
			              </s:if>
			              <tr>
			              <s:if test="%{course_id_hys != ''}">
			              <td class='tbc' width="60">
			                  	出版单位:</td>
			                  	<td><s:property value='course_expert_unit_hys'/></td>
			               </s:if>   	
			              </tr>
			              
			              <tr>
			              <td class='tbc' width="60">课程简介：</td>
			                <td class='tdl' >
			                	  <s:property value='course_explain_hys'/>
			                </td>
			              </tr>
			             
			              </s:if>
			              <tr border ="0" height="10"></tr>
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
