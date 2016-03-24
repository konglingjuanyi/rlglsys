<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <TITLE>
              日照医学教育
     </TITLE>
<script language="JavaScript">
var boxyContent = "boxy-content-detail";
//返回页面
function goBack() {
   $("#rlgl022008form").attr("action", "rlgl022005Init.action");
   $("#rlgl022008form").submit();
}
function refundBut(user_id)
{
    $("#user_id").val(user_id);
   $("#rlgl022009form").attr("action", "rlgl022010Init.action");
   $("#rlgl022009form").submit();
}

</script>    
</head>
    <body>
     <my:navigation></my:navigation>
    <table cellpadding='0' cellspacing='0' width='100%'>
      <tr>
        <td> 
		      <div class="content">
		      <form id="rlgl022008form" nameSpace="/rlgl" action="doLogin" method="post" >
		       <s:hidden name="navigationId" id="navigationId"/>
		       <s:hidden name="total" id="totalHid"/>
		        <s:hidden name="temp_str" id="temp_str"/>
		        <s:hidden name="end_date" id="end_date"/>
	           <table width="100%"  border="1px">
	           <caption>可退费课程列表</caption>
		              <tr>
		                <th class='thTitleItrn'>序号</th>
		                <th class='thTitleItrn'>订单编号</th>
		                <th class='thTitleItrn'>银行名称</th>
		                <th class='thTitleItrn'>用户账号</th>
		                <th class='thTitleItrn'>缴费时间</th>
		                <th class='thTitleItrn'>缴费金额</th>
		                <th class='thTitleItrn'>操作</th>
		              </tr>
		              <s:iterator value="bankOrderList" status="L">
			              <my:trStripe index="${L.index}">
			                
			                <td class="tdc">
			                    <s:hidden id="indexL" value="%{#L.index}"/>
			                   ${L.index +1}
			                </td>
			                <td class='tbl' >
			                <s:if test="bank_order_no==''"> 
			                      <s:property value='无'/>
			                </s:if>
			                <s:else>
			                 <a href="#" onclick="showDetailsInfo(this)"><s:property value='bank_order_no'/></a>
			                    <div id="courseInfo_${L.index}" style="display:none">
				                    <table style="position: relative;width:100%" border="1" cellpadding="2" cellspacing="2">
				                    <tr>
				                       <th class='thTitleItrn'>序号</th>							
						                <th class='thTitleItrn'>课件名称</th>					
						                <th class='thTitleItrn'>出版单位</th>					
						                <th class='thTitleItrn'>出品人</th>					
						                <th class='thTitleItrn'>截止日期</th>					
						                <th class='thTitleItrn'>单价</th>					
						                <th class='thTitleItrn'>学分</th>					
				                     </tr>
				                      <s:iterator value="courseList" status="d">
							              <my:trStripe index="${d.index}">
							                <td class="tdc">${d.index +1}</td>
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
							              </my:trStripe>
						              </s:iterator>
				                    </table>
			                     </div>
			                </s:else>
			                  </td>
			                <td class='tbl' ><s:property value='bank_name'/></td>
			                 <td class='tbl'  >
			                   <s:if test="user_account==''"> 
			                                                         无
			                   </s:if>
			                   <s:else>
			                       <s:property value='user_account'/>
			                    </s:else>
			                </td>
			                <td class='tbl' ><s:property value='payment_time'/></td>
			                 <td class='tdc'  >
			                     <span style="color: red">￥<s:property value='payment_money'/></span>
			                </td>
			                <td class='tdc'  >
		 						<input class="inp_L3 btnClass_${only_search}" type="button" id="butLearn" value="退费" onclick="refundBut('<s:property value='course_id'/>');"/>
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
