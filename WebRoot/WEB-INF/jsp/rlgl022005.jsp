<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <TITLE>
              日照医学教育
     </TITLE>
<script language="JavaScript">
$(document).ready(function(){
    // 全选
    $("#btnSelectAll").click(function(){
        var total=0;
        var sum=0;
        $("input[id='select_kbn_check']").each(function(idx, obj) {
          this.checked = true;
           sum+=parseInt(1);
           total+=parseFloat($(this).next().next().val());
         });
        total=total.toFixed(2);
        $("#sum").html(sum);
        $("#total").html(total);
        butdis(sum);
    });
    // 清空
    $("#btnSelectNo").click(function(){
        $("input[id='select_kbn_check']").each(function(idx, obj) {
          this.checked = false;
         });
         $("#sum").html("0");
         $("#total").html("0.00");
         butdis("0");
    });
    //单个选择
    $(".ishere1").click(function(){
        var total=0;
        var sum=0;
        $("input[id='select_kbn_check']").each(function(idx, obj) {
          if(this.checked){
            sum+=parseInt(1);
            total+=parseFloat($(this).next().next().val());
           }
         });
        if(sum=="0")
        {
	        $("#sum").html("0");
	        $("#total").html("0.00");
	         butdis("0");
        }
        else
        {
            total=total.toFixed(2);
	        $("#sum").html(sum);
	        $("#total").html(total);
	        butdis(sum);
        }
    });
     //删除课件
    $("#btnDelete").click(function(){
        var rlgl022005Delete = function (){
		    $("#rlgl022005form").attr("action", "rlgl022005Delete.action");
		    $("#rlgl022005form").submit();
	    };
	    //判断是否选中课件
	    var sum=0;
        $("input[id='select_kbn_check']").each(function(idx, obj) {
          if(this.checked){
            sum+=parseInt(1);
           }
         });
        if(sum>0)
        {
           confirmMessage("CM074",rlgl022005Delete);
        }
	    else
	    {
	       alertMessage("AM070");
	    }
    });
   
});
//控制按钮是否变灰
function butdis(sum)
{
   if(sum>0)
   {
      $("#butAddCourse").attr("disabled",false);
   }
   else
   {
      $("#butAddCourse").attr("disabled",true);
   }
}
//跳转支付画面
function AddCourse()
{
    var date_kbn=false;
    $("input[id='select_kbn_check']").each(function(idx, obj) {
          if( this.checked)
          {
            var date_kbn1= $(this).next().next().next().val();
             if(date_kbn1=="1")
             {
                if(date_kbn==false)
                {
                    date_kbn=true;
                }
             }
          }
    });
    
    if(date_kbn==true)
    {
       alert("所选课件包含过期课件，请取消或删除过期课件后再支付");
    }
    else
    {
	    $("#rlgl022005form").attr("action", "rlgl022007Init.action");
	    $("#rlgl022005form").submit();
    }
    
}

</script>    
</head>
    <body>
     <my:navigation></my:navigation>
     <my:message></my:message>
    <table cellpadding='0' cellspacing='0' width='100%'>
      <tr>
        <td> 
             <div class="content">
                <span style="color: red" >  重要提示：您必须一次性选够5个课程才可享受150元打包优惠价，分次支付即使累计选够5个课程也不能享受打包优惠价。</span> 
		     </div>
		     <div class="content" style="height: 180px;overflow: auto;"  >
		      <form id="rlgl022005form" nameSpace="/rlgl" action="doLogin" method="post" >
		       <s:hidden name="navigationId" id="navigationId"/>
	           <table width="100%"  border="1px">
	           <caption>待支付列表</caption>
		              <tr>
		                <th width="50" class='thTitleItrn'>选择</th>
		                <th class='thTitleItrn'>序号</th>
		                <th class='thTitleItrn'>课件名称</th>
		                <th class='thTitleItrn'>出版单位</th>
		                <th class='thTitleItrn'>出品人</th>
		                <th class='thTitleItrn'>截止日期</th>
		                <th class='thTitleItrn'>单价</th>
		                <th class='thTitleItrn'>学分</th>
		              </tr>
		              <s:iterator value="courseTemporaryList" status="L">
		                  <s:hidden name="courseTemporaryList[%{#L.index}].course_id" id="course_id"/>
		                  <s:hidden name="courseTemporaryList[%{#L.index}].course_name" id="course_name"/>
		                  <s:hidden name="courseTemporaryList[%{#L.index}].course_expert_unit" id="course_expert_unit"/>
		                  <s:hidden name="courseTemporaryList[%{#L.index}].course_expert" id="course_expert"/>
		                  <s:hidden name="courseTemporaryList[%{#L.index}].course_price" id="course_price"/>
		                  <s:hidden name="courseTemporaryList[%{#L.index}].xuefen" id="xuefen"/>
		                  <s:if test="%{date_kbn == 1}">
            			    <my:trStripe index="${L.index}" delflg="1">
				                <td class='tdc'>
				                  <s:checkbox id="select_kbn_check" name="ishere" cssClass="ishere1" fieldValue="%{#L.index}" />
				                  <s:hidden  name="course_price"/>
				                  <s:hidden  name="date_kbn"/>
				                </td>
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
            				</my:trStripe>
            					
            					
            			  </s:if>
            			  <s:else>
	            			   <my:trStripe index="${L.index}">
				                <td class='tdc'>
				                  <s:checkbox id="select_kbn_check" name="ishere" cssClass="ishere1" fieldValue="%{#L.index}" />
				                  <s:hidden  name="course_price"/>
				                </td>
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
				              </my:trStripe>
            			  
            			  </s:else>
			             
		              </s:iterator>
		          </table>
	             </form>
		       </div>
		       <div class="content">
			       <table  width="100%" valign="top">
	                 <tr>
	                     <td align="left">
	                         <input type="button" class="inp_L3" value="全选" name="btnSelectAll" id="btnSelectAll"></input>
	                         <input type="button" class="inp_L3" value="清空" name="btnSelectNo" id="btnSelectNo"></input>
	                         <input class="inp_L3 btnClass_${only_search}" type="button" value="删除" name="btnDelete" id="btnDelete"/></input>
	                     </td>
	                     <td align="right">
	                       <span>已选件数：</span>  &nbsp;<span style="color:red" id="sum">0</span>&nbsp;<span>件</span> &nbsp;&nbsp;&nbsp; <span>合计：</span>&nbsp;<span style="color: red">￥</span><span id="total" style="color: red;">0.00</span> &nbsp; &nbsp; &nbsp;  <input class="inp_L3 btnClass_${only_search}" type="button" id="butAddCourse" value="支付" onclick="AddCourse()" disabled></input>&nbsp;&nbsp;
	                     </td>
	                 </tr>
	              </table>
		     </div>
        </td>
      </tr>
    </table>
  </body>
</html>
