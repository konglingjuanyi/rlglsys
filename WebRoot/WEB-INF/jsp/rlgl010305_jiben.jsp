<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <title><s:property value="getText('rlglsys.browserhead.IE')" /></title>
    <%
		response.addHeader("Cache-Control", "no-cache");
	%>
       <script type="text/javascript" language="javascript"> 
			$(document).ready(function(){
			// 图片
				$("[id^=showImgFile]").each(function(e){
			        var w = $(this).width();
			        var h = $(this).height();
			        if( w >=141){
			            $(this).width(141);
			        }
			        if( h >=200){
			            $(this).height(200);
			        }
			      });
			      $("[id^=showImgFile]").each(function(e){
			        var w = $(this).width();
			        var h = $(this).height();
			        if( w >=141){
			            $(this).width(141);
			        }
			        if( h >=200){
			            $(this).height(200);
			        }
			      });
			    // 添加待审核状态
			    createReviewMark($("#reviewElement").val());
			    // 返回事件的定义
			    $("#btnBack").click(function(){
			          $("#modal-confirm").text("是否确认返回前画面？");
						$('#my-confirm').modal({
					        relatedTarget: this,
					        onConfirm: function(options) {
					        	backAction();
					        },
					        onCancel: function() {
					          return;
					        }
					      });
			      });
			});
			
		    function updatePersonnel() {
			    $("form").attr("action", "rlgl010306Jiben.action");
				$("form").submit();
			}
			
	        function backAction() {
	        	$("#backFlag").val("1");
	       		$("form").attr("action", $("#backAction").val());
			    $("form").submit();
            }
		</script> 

    </HEAD>
    <body>
    <s:form name="init" id="init" action="rlgl010306Init"  method="post" nameSpace="/rlgl">
    <s:hidden name="navigationId" id="navigationId"/>
    <s:hidden name="screenId" id="screenId"/>
    <s:hidden name="reviewElement" id="reviewElement"/>
    <s:hidden name="backAction" id="backAction"/>
    <s:hidden name="backFlag" id="backFlag"/>
    <!-- test begin -->
    <s:hidden name="personnel_id" id="personnel_id"/>
    <!-- test end -->
 <div class="content">
    <my:navigation></my:navigation>
    <table width="100%"  border="0" cellspacing="0" cellpadding="0">
    	<tr>
            <td align="center"><font color="#0066CC" style="font-size:14px;">
        </tr>
      <tr>
        <td align="center" valign="top">
        <table width="100%" border="0" align="center" cellspacing="0" cellpadding="0">
          <tr>
            <td>
    <!-- 基本信息 -->
    <table width="100%" border="0" cellspacing="0" cellpadding="0" >
        <tr>
            <td height="28" style="padding-top:5px;" align="left"><font color="#1F6087" style="font-size:14px;"><strong>基本信息</strong></font></td>
        </tr>
        <tr>
            <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="userBaseCss" style="border-bottom:0px">
                <tr valign="top">
                <td width="80%">
                 <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">姓名</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;"><s:property value="personnel.personnel_nm"/></td>
                      <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">身份证号</td>
                      <td width="35%" style="padding-left:6px;font-size:12px;"><s:property value="personnel.personnel_card_id"/></td>
                    </tr>
                     <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">曾用名</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <label id="init_personnel_personnel_beforename"><s:property value="personnel.personnel_beforename"/></label>
                      </td>
                      <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">性别</td>
                      <td width="35%" style="padding-left:6px;font-size:12px;">
                      <label id="init_personnel_personnel_gender"><s:property value="personnel.personnel_gender"/></label></td>
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                    <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">出生日期</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;"><label id="init_personnel_personnel_birthday"><s:property value="personnel.personnel_birthday"/></label></td>
                      <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">籍贯</td>
                      <td width="35%" style="padding-left:6px;font-size:12px;"><label id="init_personnel_personnel_hometown"><s:property value="personnel.personnel_hometown"/></label></td>
			</tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                    <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">民族</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                       <label id="init_personnel_personnel_ethnic"><s:property value="personnel.personnel_ethnic"/></label>
                        </td>
                      <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">参加工作时间</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;"> <label id="init_personnel_personnel_worktime"><s:property value="personnel.personnel_worktime"/></label></td>
                   
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                     <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">户口所在地</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;"><label id="init_personnel_personnel_account_location"><s:property value="personnel.personnel_account_location"/></label></td>
                     
                     <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">健康状况</td>
                      <td style="padding-left:6px;font-size:12px;">
                      <label id="init_personnel_personnel_health_status"><s:property value="personnel.personnel_health_status"/></label>
                      </td>
                     </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                     <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">个人身份</td>
                      <td style="padding-left:6px;font-size:12px;">
                      <label id="init_personnel_personnel_personal_identification"><s:property value="personnel.personnel_personal_identification"/></label>
                       </td>
                     <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">用工形式</td>
                      <td style="padding-left:6px;font-size:12px;">
                      <label id="init_personnel_personnel_employment_forms"><s:property value="personnel.personnel_employment_forms"/></label>
                      </td>
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                    <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">政治面貌</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <label id="init_personnel_personnel_political_landscape"><s:property value="personnel.personnel_political_landscape"/></label>
                      </td>
                     
                     <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">入党(团)时间</td>
                      <td style="padding-left:6px;font-size:12px;">
                      <label id="init_personnel_personnel_joinpartytime"><s:property value="personnel.personnel_joinpartytime"/></label>
                       </td>
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                     <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">婚姻状况</td>
                      <td style="padding-left:6px;font-size:12px;">
                      <label id="init_personnel_personnel_marital_status"><s:property value="personnel.personnel_marital_status"/></label>
                      </td>
                      <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">爱好特长</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <label id="init_personnel_personnel_hobbies"><s:property value="personnel.personnel_hobbies"/></label>
                      </td>
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                     <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">外语水平</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <label id="init_personnel_personnel_foreignlanguage_level"><s:property value="personnel.personnel_foreignlanguage_level"/></label>
                      </td>
                      <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">在编状态</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <label id="init_personnel_personnel_regular"><s:property value="personnel.personnel_regular"/></label>
                      </td>
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                     <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">存档单位</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <label id="init_personnel_personnel_archive_unit"><s:property value="personnel.personnel_archive_unit"/></label>
                      </td>
                      <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">档案位置</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <label id="init_personnel_personnel_filelocation"><s:property value="personnel.personnel_filelocation"/></label>
                      </td>
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                    <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">岗位状态</td>
                      <td style="padding-left:6px;font-size:12px;">
                      <label id="init_personnel_personnel_status"><s:property value="personnel.personnel_status"/></label>
                      </td>
                      <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">年度审核判定</td>
                      <td style="padding-left:6px;font-size:12px;">
                      <s:if test="%{personnel.personnel_check.trim() != ''}">
                     <label id="init_personnel_personnel_check"><s:property value="personnel.personnel_check"/></label>
                      </s:if>
               		  <s:else>未审</s:else>
                      </td>
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                    <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">所在单位</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <label id="init_personnel_personnel_unit"><s:property value="personnel.personnel_unit"/></label>
                      </td>
                      <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">所在科室</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <s:if test="%{personnel.personnel_office.trim() != ''}">
                      <label id="init_personnel_personnel_office"><s:property value="personnel.personnel_office" /></label>
                      </s:if>
               		  <s:else>未分配</s:else>
                      </td>
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                    <tr align="left"> 
                      <td width="15%" class="lc1" style="padding-left:5px;font-size:12px;">岗位类别</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;">
                      <s:if test="%{personnel.personnel_admintype.trim() != ''}">
                      <label id="init_personnel_personnel_admintype"><s:property value="personnel.personnel_admintype" /></label>
                      </s:if>
               		  <s:else>未分配</s:else>
                      </td>
                      <td width="15%" class="lc1" style="padding-left:6px;font-size:12px;">是否继续教育</td>
                      <td width="35%" style="padding-left:5px;font-size:12px;"> 是</td>
                    </tr>
                    <tr><td colspan="4" height="1" bgcolor="#eaeaea"></td></tr>
                  </table>
                </td>
                <td width="20%" align="center" style="padding-top:14px;">
                <!-- 个人照片 -->
               <label id="init_imgFile"> 
               <s:if test="%{personnel.personnel_imgname.trim() != ''}">
                <img src="${pageContext.request.contextPath}/upload/${personnel.personnel_imgname}"  id="showImgFile"  width="141" height="200" border="0">
                </s:if>
                <s:else>
                <img src="${pageContext.request.contextPath}/images/picture.jpg" id="showImgFile"  width="141" height="200" border="0">
                </s:else>
                </label>
                  </td>
                </tr>
            </table>
            
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="userBaseCss" style="border-top:0px; text-align:left;">
              <tr > 
                <td width="12%" class="lc1" style="padding-left:5px;font-size:12px;">家庭住址</td>
                <td width="78%" style="padding-left:5px;font-size:12px;">
                <label  id="province" ><s:property value="personnel.personnel_province"  /></label>&nbsp;
                 <label  id="city" ><s:property value="personnel.personnel_city"   /></label>&nbsp;
                 <label  id="zone" ><s:property value="personnel.personnel_zone"  /></label>&nbsp;
                <label  id="init_personnel_personnel_address" > <s:property value="personnel.personnel_address"/></label>
                </td>
                <td width="10%" ></td>
              </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-top:0px; text-align:left;">
              <tr>
                  <td height="28" style="padding-top:5px;" align="left"><font color="#1F6087" style="font-size:14px;"><strong>联系信息</strong></font></td>
              </tr>
              <tr>
              <td>
              <table width="100%" border="0" cellspacing="2" cellpadding="0" class="userBaseCss">
              <tr>
                <td  width="10%" class="lc1" style="padding-left:5px;font-size:12px;">电子邮件</td>
                <td width="20%" style="padding-left:5px;font-size:12px;">
                <s:property value="personnel.personnel_email"/>
                </td>
                <td width="10%" class="lc1" style="padding-left:6px;font-size:12px;">移动电话</td>
                <td width="20%" style="padding-left:6px;font-size:12px;">
                <s:property value="personnel.personnel_tel"/>
                </td>
                <td width="10%" class="lc1" style="padding-left:6px;font-size:12px;">单位电话</td>
                <td width="20%" style="padding-left:6px;font-size:12px;">
                <s:property value="personnel.personnel_officetel"/>
                </td>
              </tr>
             </table>
            </td>
            </tr>
          </table>
          </td>
        </tr>
      </table>
    <div class="am-modal am-modal-confirm" tabindex="-1" id="my-confirm">
	  <div class="am-modal-dialog">
	    <div class="am-modal-hd">提示信息</div>
	    <div class="am-modal-bd" id="modal-confirm">
	    </div>
	    <div class="am-modal-footer">
	      <span class="am-modal-btn" data-am-modal-cancel>取消</span>
	      <span class="am-modal-btn" data-am-modal-confirm>确定</span>
	    </div>
	  </div>
	</div>
<!-- 操作按扭 -->
<table width="70%" align="center" border="0" cellspacing="1" cellpadding="0">
<tr>
    <td height="50" align="center">
<s:if test="%{updateAction == null}">
<s:if test="%{personnel.personnel_isapproval.trim() != '001'}"> 
<input type="button" class="am-btn am-btn-danger" onClick="updatePersonnel();"value="修改"  id="btnUpdate" name="btnUpdate"/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</s:if>
</s:if>
<s:if test="%{backAction.trim() != ''}">
 <input type="button" class="am-btn am-btn-danger"  id="btnBack" value="返回" name="btnBack"/>
</s:if>
</td>
</tr>
</table>

          </td>
          </tr>
        </table>
        </td>
      </tr>
    </table>
    </div>
    </s:form>
    <script type="text/javascript" language="javascript"> 
    $("#showImgFile").attr("src", $("#showImgFile")[0].src+"?rand="+new Date().getTime());
    </script>
    </body>
</html>
