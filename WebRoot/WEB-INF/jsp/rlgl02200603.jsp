<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <TITLE>
              日照医学教育
     </TITLE>
<script language="JavaScript">
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
        $("#user_id").val(user_id);
	    $("#rlgl022003form").attr("action", "rlgl022003Save.action");
	    $("#rlgl022003form").attr("target","contents1");
	    $("#rlgl022003form").submit(); 
    }
    else
    {
       alertMessage("AM070");
    }
    
}

 //checkbox单选
	function checkchange(cb) {
		var obj = $("input[id='select_kbn_check']");
		if (cb.checked == false) {
			cb.checked = false;
		} else {
			for (i = 0; i < obj.length; i++) {
				if (obj[i] != cb)
					obj[i].checked = false;
				else
					obj[i].checked = true;
			}
		}
 }
</script>    
</head>
    <body>
		     <div class="content">
		     <my:message></my:message>
	          <!-- 数据检索条件 -->  
	          <form name="form"  id="rlgl022003form" nameSpace="/rlgl" target="contents1" action="doLogin" method="post" >
	              <s:hidden name="user_id" id="user_id"/>
	              <s:hidden name="course_catagory" id="course_catagory"/>
	              <my:dividepage actionId="coursInitBuKao.action"></my:dividepage>
		          <table style="width:100%;valign:top" border="1px">
		              <tr>
		                <th class='thTitleItrn'>序号</th>
		                <th class='thTitleItrn'>课件名称</th>
		                <th class='thTitleItrn'>出版单位</th>
		                <th class='thTitleItrn'>出品人</th>
		                <th class='thTitleItrn'>截止日期</th>
		                <th class='thTitleItrn'>单价</th>
		                <th class='thTitleItrn'>学分</th>
		                <th width="50" class='thTitleItrn'>选择</th>
		              </tr>
		              <s:iterator value="courseList" status="L">
		                  <s:hidden name="courseList[%{#L.index}].course_id" id="course_id"/>
		                  <s:hidden name="courseList[%{#L.index}].course_name" id="course_name"/>
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
			                 <td class='tdc'  >
			                   <s:if test="end_date=='' && end_date!=null"> 
			                                                        无
			                   </s:if>
			                   <s:else>
			                       <s:property value='end_date'/>
			                    </s:else>
			                </td>
			                 <td class='tdc'  >
			                     <span style="color: red">￥<s:property value='course_price'/></span>
			                </td>
			                <td class='tdc'  >
		 						 <span style="color: #00AA00 "><s:property value='xuefen'/>(分)</span>
			                </td>
			                <td class='tdc'>
			                  <s:checkbox name="ishere" id="select_kbn_check"  onclick="checkchange(this)" cssClass="ishere" fieldValue="%{#L.index }" />
			                </td>
			              </my:trStripe>
		              </s:iterator>
		          </table>
		          <table  style="width:100%;valign:top">
	                 <tr><td align="right"><input class="inp_L5 btnClass_${only_search}"   id="butAddCourse" type="button" value="添加到待支付" onclick="saveUserRole('<s:property value='user_id'/>')"></input>&nbsp;&nbsp;</td></tr>
	              </table>
		       </form>
		    </div>
  </body>
</html>
