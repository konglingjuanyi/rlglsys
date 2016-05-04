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
			    $("form").attr("action", "rlgl010306Init.action");
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
            <table width="100%" border="0" cellspacing="0" cellpadding="0" style="display:none;">
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
    
      <div> 
        <table width="100%" border="0" cellspacing="0" cellpadding="0" ID="Table20">
          <tr> 
            <td height="28" style="padding-top:5px;" align="left"><font color="#1F6087" style="font-size:14px;"><strong>资格信息</strong></font></td>
          </tr>
            <tr>
                <td style="PADDING-TOP: 2px">
            <table cellSpacing="0" cellPadding="1" width="100%" border="1" ID="tab_zyzgxx" class="tabCss">
            <tr>
              <th height="28" width="11%" class="thTitleItrn">资格证编号</th>
              <th width="11%" class="thTitleItrn">发证机关</th>
              <th width="11%" class="thTitleItrn">发证日期</th>
              <th width="11%"class="thTitleItrn">专业类别</th>
              <th width="11%"class="thTitleItrn">专业级别</th>
              <th width="11%"class="thTitleItrn" style="display:none">执业范围</th>
              <th width="11%"class="thTitleItrn" style="display:none">变更记录</th>
              <th width="11%"class="thTitleItrn" style="display:none">执业考核记录</th>
            </tr>
            <s:iterator value="rlgl010302PractitionersInfoList" status='st'>
            <tr>
              <td height="25"><s:label id="init_rlgl010306PractitionersInfoList_%{#st.index }__certificate_no"><s:property value="certificate_no"/></s:label></td>
              <td><s:label id="init_rlgl010306PractitionersInfoList_%{#st.index }__issuing_authority"><s:property value="issuing_authority"/></s:label></td>
              <td><s:label id="init_rlgl010306PractitionersInfoList_%{#st.index }__issue_time"><s:property value="issue_time"/></s:label></td>
              <td><s:label id="pratypelist_%{#st.index }"><s:property value="type"/></s:label></td>
              <td><s:label id="pralevellist_%{#st.index }"><s:property value="level"/></s:label></td>
              <td style="display:none"><s:label id="init_rlgl010306PractitionersInfoList_%{#st.index }__area"><s:property value="area"/></s:label></td>
              <td style="display:none"><s:label id="init_rlgl010306PractitionersInfoList_%{#st.index }__upd_record"><s:property value="upd_record"/></s:label></td>
              <td style="display:none"><s:label id="init_rlgl010306PractitionersInfoList_%{#st.index }__assess_record"><s:property value="assess_record"/></s:label></td>
            </tr> 
             </s:iterator>
          </table>
                </TD>
            </TR>
        </table>
      </div>
      <div> 
        <table width="100%" border="0" cellspacing="0" cellpadding="0" ID="Table20">
          <tr> 
            <td height="28" style="padding-top:5px;" align="left"><font color="#1F6087" style="font-size:14px;"><strong>执业信息</strong></font></td>
          </tr>
            <tr>
                <td style="PADDING-TOP: 2px">
            <table cellSpacing="0" cellPadding="1" width="100%" border="1" ID="tab_practice" class="tabCss">
            <tr>
              <th height="28" width="11%" class="thTitleItrn">执业证编号</th>
              <th width="11%" class="thTitleItrn">发证机关</th>
              <th width="9%" class="thTitleItrn">发证日期</th>
              <th width="11%"class="thTitleItrn">专业类别</th>
              <th width="11%"class="thTitleItrn">专业级别</th>
              <th width="11%"class="thTitleItrn">执业类别</th>
              <th width="11%"class="thTitleItrn">执业范围</th>
              <th width="11%"class="thTitleItrn">执业地点</th>
              <th width="9%"class="thTitleItrn" style="display:none">变更记录</th>
              <th width="9%"class="thTitleItrn" style="display:none">执业考核记录</th>
            </tr>
            <s:iterator value="rlgl010302PracticeInfoList" status='st'>
            <tr>
              <td height="25"><s:label id="init_rlgl010306PracticeInfoList_%{#st.index }__certificate_no"><s:property value="certificate_no"/></s:label></td>
              <td><s:label id="init_rlgl010306PracticeInfoList_%{#st.index }__issuing_authority"><s:property value="issuing_authority"/></s:label></td>
              <td><s:label id="init_rlgl010306PracticeInfoList_%{#st.index }__issue_time"><s:property value="issue_time"/></s:label></td>
              <td><s:label id="practypelist_%{#st.index }"><s:property value="type"/></s:label></td>
              <td><s:label id="pralevellist_%{#st.index }"><s:property value="level"/></s:label></td>
              <td><s:label id="proctypelist_%{#st.index }"><s:property value="pro_type"/></s:label></td>
              <td>范围1:<s:label id="init_rlgl010306PracticeInfoList_%{#st.index }__area1"><s:property value="area1"/></s:label>
              <br>范围2:<s:label id="init_rlgl010306PracticeInfoList_%{#st.index }__area2"><s:property value="area2"/></s:label>
              </td>
              <td>
              地点1:<s:label id="init_rlgl010306PracticeInfoList_%{#st.index }__place1"><s:property value="place1"/></s:label>
              <br>地点2:<s:label id="init_rlgl010306PracticeInfoList_%{#st.index }__place2"><s:property value="place2"/></s:label>
              </td>
              <td style="display:none"><s:label id="init_rlgl010306PracticeInfoList_%{#st.index }__upd_record"><s:property value="upd_record"/></s:label></td>
              <td style="display:none"><s:label id="init_rlgl010306PracticeInfoList_%{#st.index }__assess_record"><s:property value="assess_record"/></s:label></td>
            </tr> 
             </s:iterator>
          </table>
                </TD>
            </TR>
        </table>
      </div>
    <div>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" ID="Table11">
        <tr>
            <td height="28" style="padding-top:5px;" align="left"><font color="#1F6087" style="font-size:14px;"><strong>专业技术职务信息</strong></font></td>
        </tr>
        <tr>
            <td style="padding-top:2px;">
            <table cellSpacing="0" cellPadding="1" width="100%" border="1" ID="tab_zyjszwxx" name="tab_zyjszwxx" class="tabCss">
            <tr>
              <th height="28" width="25%" class="thTitleItrn">级别</th>
              <th width="15%" class="thTitleItrn">名称</th>
              <th width="15%" class="thTitleItrn">审批机关</th>
              <th width="15%" class="thTitleItrn">取得时间</th>
            </tr>
            <tr>
   <s:iterator value="rlgl010302ProfessionalInfoList" status='st'>
            <tr>
            <td width="15%">
            <s:label id="onelevel_%{#st.index }"><s:property value="onelevel"/></s:label>
            <s:label id="twolevel_%{#st.index }"><s:property value="twolevel"/></s:label>
            <s:label id="threelevel_%{#st.index }"><s:property value="threelevel"/></s:label>
            </td>
            <td width="15%"><s:label id="init_rlgl010306ProfessionalInfoList_%{#st.index }__name"><s:property value="name"/></s:label></td>
            <td width="15%"><s:label id="init_rlgl010306ProfessionalInfoList_%{#st.index }__original"><s:property value="original"/></s:label></td>
            <td width="15%"><s:label id="init_rlgl010306ProfessionalInfoList_%{#st.index }__get_time"><s:property value="get_time"/></s:label></td>
            </tr>
        </s:iterator>
            </tr>
        </table>
        </td>
      </tr>
    </table></div>

    <div >
        <TABLE cellSpacing="0" cellPadding="0" width="100%" border="0" ID="tab_xzzw">
            <tr><td style="padding-top:5px;" height="28" align="left"><FONT color="#1F6087" style="font-size:14px;"><STRONG>行政职务信息</STRONG></FONT></td>
            </tr>
            <tr>
                <td style="PADDING-TOP: 2px">
                
            <TABLE cellSpacing="0" cellPadding="1" width="100%" border="1" ID="tab_xzzwxx" class="tabCss">
            <tr>
              <th height="28" width="15%" class="thTitleItrn">职务名称</th>
              <th width="15%" class="thTitleItrn">任职方式</th>
              <th width="15%" class="thTitleItrn">职务类别</th>
              <th width="15%" class="thTitleItrn">职务级别</th>
              <th width="15%" class="thTitleItrn">任职日期</th>
              <th width="15%" class="thTitleItrn">任职文号</th>
            </tr>
			<s:iterator value="rlgl010302JobInfoList" status='st'>
            <tr>
              <td height="25" width="15%">
             <s:label id="init_rlgl010306JobInfoList_%{#st.index }__position_nm"><s:property value="position_nm"/>
              </s:label>
              </td>
              <td width="15%">
              <s:label id="init_rlgl010306JobInfoList_%{#st.index }__mode">
              <s:property value="mode"/>
              </s:label>
              </td>
              <td width="15%">
              <s:label id="init_rlgl010306JobInfoList_%{#st.index }__type"><s:property value="type"/></s:label>
              </td>
              <td width="15%">
              <s:label id="init_rlgl010306JobInfoList_%{#st.index }__level"><s:property value="level"/></s:label>
              </td>
              <td width="15%">
              <s:label id="init_rlgl010306JobInfoList_%{#st.index }__appoint_time"><s:property value="appoint_time"/></s:label>
              </td>
              <td width="15%">
              <s:label id="init_rlgl010306JobInfoList_%{#st.index }__appoint_no"><s:property value="appoint_no"/></s:label>
              </td>
            </tr>
 			</s:iterator>
        </table>
                </td>
            </tr>
        </TABLE>
    </div>
    
        <div>
        <TABLE cellSpacing="0" cellPadding="0" width="100%" border="0" ID="">
            <tr><td style="padding-top:5px;" height="28" align="left"><FONT color="#1F6087" style="font-size:14px;"><STRONG>社会关系</STRONG></FONT></td>
            </tr>
            <tr>
                <td style="PADDING-TOP: 2px">
            <TABLE cellSpacing="0" cellPadding="1" width="100%" border="1" ID="tab_shgl" class="tabCss">
            <tr>
              <th height="28" width="14%" class="thTitleItrn">与本人关系</th>
              <th width="14%" class="thTitleItrn">姓名</th>
              <th width="14%" class="thTitleItrn">出生日期</th>
              <th width="13%" class="thTitleItrn">政治面貌</th>
              <th width="15%" class="thTitleItrn">工作单位</th>
              <th width="10%" class="thTitleItrn">职务</th>
              <th width="10%" class="thTitleItrn">电话</th>
            </tr>
<s:iterator value="rlgl010302SocialInfoList" status='st'>
            <tr>
              <td height="25" width="14%"><s:label id="init_rlgl010306SocialInfoList_%{#st.index }__relationship"><s:property value="relationship"/></s:label></td>
              <td width="14%"><s:label id="init_rlgl010306SocialInfoList_%{#st.index }__name"><s:property value="name"/></s:label></td>
              <td width="14%"><s:label id="init_rlgl010306SocialInfoList_%{#st.index }__birthday"><s:property value="birthday"/></s:label></td>
              <td width="13%"><s:label id="init_rlgl010306SocialInfoList_%{#st.index }__political_landscape"><s:property value="political_landscape"/></s:label></td>
              <td width="15%"><s:label id="init_rlgl010306SocialInfoList_%{#st.index }__workunit"><s:property value="workunit"/></s:label></td>
              <td width="10%"><s:label id="init_rlgl010306SocialInfoList_%{#st.index }__position"><s:property value="position"/></s:label></td>
              <td width="10%"><s:label id="init_rlgl010306SocialInfoList_%{#st.index }__tel"><s:property value="tel"/></s:label></td>
            </tr>
 </s:iterator>
        </table>
                </td>
            </tr>
        </TABLE>
    </div>
    
    
    <div> 
        <table width="100%" border="0" cellspacing="0" cellpadding="0" ID="Table15">
          <tr> 
            <td height="28" style="padding-top:5px;" align="left"><font color="#1F6087" style="font-size:14px;"><strong>教育经历</strong></font></td>
          </tr>
          
            <tr>
                <td style="PADDING-TOP: 2px">
            <TABLE cellSpacing="0" cellPadding="1" width="100%" border="1" ID="tab_jyjl" class="tabCss">
            <tr>
              <th height="28" width="10%" class="thTitleItrn">学习形式</th>
              <th width="10%" class="thTitleItrn">入学时间</th>
              <th width="10%" class="thTitleItrn">毕业时间</th>
              <th width="10%" class="thTitleItrn">毕业学校</th>
              <th width="10%" class="thTitleItrn">院校类型</th>
              <th width="10%" class="thTitleItrn">所学专业</th>
              <th width="10%" class="thTitleItrn">证明人</th>
              <th width="10%" class="thTitleItrn">学历</th>
              <th width="10%" class="thTitleItrn">学位</th>
            </tr>
<s:iterator value="rlgl010302EduInfoList" status='st'>
            <tr>
              <td height="25"><s:label id="init_rlgl010306EduInfoList_%{#st.index }__learning_format"><s:property value="learning_format"/></s:label></td>
              <td><s:label id="init_rlgl010306EduInfoList_%{#st.index }__admission_time"><s:property value="admission_time"/></s:label></td>
              <td><s:label id="init_rlgl010306EduInfoList_%{#st.index }__graduation_time"><s:property value="graduation_time"/></s:label></td>
              <td><s:label id="init_rlgl010306EduInfoList_%{#st.index }__school"><s:property value="school"/></s:label></td>
              <td><s:label id="init_rlgl010306EduInfoList_%{#st.index }__college_type"><s:property value="college_type"/></s:label></td>
              <td><s:label id="init_rlgl010306EduInfoList_%{#st.index }__profession"><s:property value="profession"/></s:label></td>
              <td><s:label id="init_rlgl010306EduInfoList_%{#st.index }__proof_people"><s:property value="proof_people"/></s:label></td>
              <td><s:label id="init_rlgl010306EduInfoList_%{#st.index }__educational_bg"><s:property value="educational_bg"/></s:label></td>
              <td><s:label id="init_rlgl010306EduInfoList_%{#st.index }__degree"><s:property value="degree"/></s:label></td>
            </tr>
 </s:iterator>
        </table>
        
                </td>
            </tr>
        </table>
      </div>

      <div> 
        <table width="100%" border="0" cellspacing="0" cellpadding="0" ID="Table20">
          <tr> 
            <td height="28" style="padding-top:5px;" align="left"><font color="#1F6087" style="font-size:14px;"><strong>工作经历</strong></font></td>
          </tr>        
            <tr>
                <td style="PADDING-TOP: 2px">
            <TABLE cellSpacing="0" cellPadding="1" width="100%" border="1" ID="tab_gzjl" class="tabCss">
            <tr>
              <th height="28" width="16%" class="thTitleItrn">开始时间</th>
              <th width="16%" class="thTitleItrn">结束时间</th>
              <th width="16%" class="thTitleItrn">工作单位</th>
              <th width="16%" class="thTitleItrn">证明人</th>
              <th width="16%" class="thTitleItrn">职务</th>
              <th width="16%" class="thTitleItrn">电话</th>
            </tr>
<s:iterator value="rlgl010302WorkInfoList" status='st'>
            <tr>
              <td height="25">
              <s:label id="init_rlgl010306WorkInfoList_%{#st.index }__starttime">
              <s:property value="starttime"/>
              </s:label>
              </td>
              <td><s:label id="init_rlgl010306WorkInfoList_%{#st.index }__endtime"><s:property value="endtime"/></s:label></td>
              <td><s:label id="init_rlgl010306WorkInfoList_%{#st.index }__workunit"><s:property value="workunit"/></s:label></td>
              <td><s:label id="init_rlgl010306WorkInfoList_%{#st.index }__proofpeople"><s:property value="proofpeople"/></s:label></td>
              <td><s:label id="init_rlgl010306WorkInfoList_%{#st.index }__position"><s:property value="position"/></s:label></td>
              <td><s:label id="init_rlgl010306WorkInfoList_%{#st.index }__tel"><s:property value="tel"/></s:label></td>
            </tr> 
 </s:iterator>
          </table>
       
                </td>
            </tr>
        </table>
      </div>
      <div> 
        <table width="100%" border="0" cellspacing="0" cellpadding="0" ID="Table20">
          <tr> 
            <td height="28" style="padding-top:5px;" align="left"><font color="#1F6087" style="font-size:14px;"><strong>党派信息</strong></font></td>
          </tr>
            <tr>
                <td style="PADDING-TOP: 2px">
            <TABLE cellSpacing="0" cellPadding="1" width="100%" border="1" ID="tab_dpxx" class="tabCss">
            <tr>
              <th height="28" width="30%" class="thTitleItrn">加入时间</th>
              <th width="30%" class="thTitleItrn">党派名称</th>
              <th width="30%" class="thTitleItrn">预备/正式</th>
            </tr>
<s:iterator value="rlgl010302PartisanInfoList" status='st'>
            <tr>
              <td><s:label id="init_rlgl010306PartisanInfoList_%{#st.index }__jointime"><s:property value="jointime"/></s:label></td>
              <td><s:label id="init_rlgl010306PartisanInfoList_%{#st.index }__partisan_nm"><s:property value="partisan_nm"/></s:label></td>
              <td><s:label id="init_rlgl010306PartisanInfoList_%{#st.index }__prep_or_officially"><s:property value="prep_or_officially"/></s:label></td>
            </tr> 
 </s:iterator>
          </table>
                </td>
            </tr>
        </table>
      </div>

<div> 
        <table width="100%" border="0" cellspacing="0" cellpadding="0" ID="Table20">
          <tr> 
            <td height="28" style="padding-top:5px;" align="left"><font color="#1F6087" style="font-size:14px;"><strong>导师信息</strong></font></td>
          </tr>
            <tr>
                <td style="PADDING-TOP: 2px">
            <TABLE cellSpacing="0" cellPadding="1" width="100%" border="1" ID="tab_dpxx" class="tabCss">
            <tr>
            <th height="28" width="20%" class="thTitleItrn">导师类别</th>
              <th height="28" width="20%" class="thTitleItrn">导师姓名</th>
              <th width="20%" class="thTitleItrn">所在大学</th>
              <th width="20%" class="thTitleItrn">研究方向</th>
            </tr>
<s:iterator value="rlgl010302TutorInfoList" status='st'>
            <tr>
            <td><s:label id="init_rlgl010306TutorInfoList_%{#st.index }__teachertype"><s:property value="teachertype"/></s:label></td>
              
              <td><s:label id="init_rlgl010306TutorInfoList_%{#st.index }__name"><s:property value="name"/></s:label></td>
              <td><s:label id="init_rlgl010306TutorInfoList_%{#st.index }__school"><s:property value="school"/></s:label></td>
              <td>
              <s:label id="init_rlgl010306TutorInfoList_%{#st.index }__researcharea">
              <s:property value="researcharea"/>
              </s:label></td>
            </tr> 
 </s:iterator>
          </table>
                </td>
            </tr>
        </table>
      </div>
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
