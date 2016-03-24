<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<HTML  xmlns="http://www.w3.org/1999/xhtml">
    <HEAD>
        <TITLE>
              日照医学教育
        </TITLE>
    </HEAD>
    <body>
       <s:form action="addUnit" nameSpace="/rlgl">
	      <table width="100%" cellspacing= "0" cellpadding= "0" valign="top">
	        <tr>
	          <td width="100%" valign=top>
	          <!-- 内容显示区域 -->
	          <table width="100%"cellspacing= "0" cellpadding= "0" style="background-image:url(../image/ywbltitleBG.jpg);background-repeat:repeat-x;height:27px;">
	            <tr>
	              <td width="60%">
	              &nbsp;&nbsp;<img src="../image/nowpositon.jpg"/>
	              &nbsp;&nbsp;<font class="banner1">当 前 位 置 : 单 位 信 息 中 心 > </font><font  class="banner2">单 位 信 息 完 善</font></td>
	              <td width="30%"align="right"><img src="../image/nowuser.jpg"/>&nbsp;&nbsp;<font class="banner1">当前用户 : jpcnsoft</font>&nbsp;&nbsp;&nbsp;&nbsp;</td>
	            </tr>
	          </table>
	          <div style="font-size:14px;color:red">您已成功注册为企业用户，请完善以下企业单位信息...</div>
	          <table width="100%" border="1px" cellspacing="0" cellpadding="0">
	            <tr>
	              <td width="10%" class='lc1'>单位编号</td>
	              <td width="40%" class='lc2'><s:textfield  name="unit.unit_no" cssClass="put" size="20" ></s:textfield></td>
	              <td width="10%" class='lc1'>单位名称</td>
	              <td width="40%" class='lc2'><s:textfield  name="unit.unit_nm" cssClass="put" maxLength="20" ></s:textfield></td>
	            </tr>
	            <tr> 
	              <td class='lc1'>单位所有制</td>
	              <td class='lc2'>
	                 <s:select name="unit.unit_own" list="#request.mtb02admlist" listKey="adm_num" listValue="Adm_name" headerValue="请选择" headerKey="-1"/>
	             </td>
	              <td class='lc1'>单位属性</td>
	              <td class='lc2'>
	                 <s:select name="unit.unit_property" list="#request.mtb02admlist" listKey="adm_num" listValue="adm_name" headerValue="请选择" headerKey="-1"/>
	               </td>
	            </tr>
	            <tr> 
	              <td class='lc1'>单位层次</td>
	              
	              <td class='lc2'>
	                 <s:select name="unit.unit_level" list="#request.mtb02admlist" listKey="adm_num" listValue="adm_name"  headerValue="请选择" headerKey="-1" />
	               </td>
	              <td class='lc1'>经济类型</td>
	              <td class='lc2'>
	                 <s:select name="unit.unit_economic_type" list="#request.mtb02admlist" listKey="adm_num" listValue="adm_name"  headerValue="请选择" headerKey="-1" />
	               </td>
	            </tr>
	             <tr> 
	              <td class='lc1'>单位性质</td>
	              <td class='lc2'>
	                 <s:select name="unit.unit_nature" list="#request.mtb02admlist" listKey="adm_num" listValue="adm_name"  headerValue="请选择" headerKey="-1" />
	               </td>
	              <td class='lc1'>上级单位</td>
	              <td class='lc2'>&nbsp;</td>
	            </tr>
	            <tr> 
	              <td class='lc1'>单位级别</td>
	              <td class='lc2'>
	                 <s:select name="unit.unit_scale" list="#request.mtb02admlist" listKey="adm_num" listValue="adm_name"  headerValue="请选择" headerKey="-1" />
	               </td>
	              <td class='lc1'>行业划分</td>
	              <td class='lc2'>
	                 <s:select name="unit.industry_plan" list="#request.mtb02admlist" listKey="adm_num" listValue="adm_name"  headerValue="请选择" headerKey="-1" />
	               </td>
	            </tr>
	            <tr> 
	              <td class='lc1'>批准文号</td>
	              <td class='lc2'><s:textfield  name="unit.license_no" cssClass="put" maxLength="20"  ></s:textfield></td>
	              <td class='lc1'>机构代码证</td>
	              <td class='lc2'><s:textfield  name="unit.organization_code" cssClass="put" maxLength="20" ></s:textfield></td>
	            </tr>
	            <tr> 
	              <td class='lc1'>编制人数</td>
	              <td class='lc2'><s:textfield  name="unit.establishment_num" cssClass="put" maxLength="20" ></s:textfield></td>
	              <td class='lc1'>详细地址</td>
	              <td class='lc2'><s:textfield  name="unit.unit_address" cssClass="put" maxLength="20" ></s:textfield></td>
	            </tr><tr>
	              <td class='lc1'>时间</td>
	              <td class='lc2'><s:textfield    name="unitno" cssClass="put" maxLength="20"></s:textfield></td>
	              <td class='lc1'>单位状态</td>
	              <td class='lc2'>
	              <s:select name="unit.unit_status" list="#request.mtb02admlist" listKey="adm_num" listValue="adm_name" headerValue="请选择" headerKey="-1" />
	              </td>
	            </tr>
	            <tr> 
	              <td class='lc1'>职责范围</td>
	              <td class='lc2' colspan="3"><s:textarea rows="4" cols="95" name="unit.offical_scope" ></s:textarea>&nbsp;<font color="red">可输入的最大文字数是255</font></td>
	            </tr>
	            
	            <tr> 
	              <td class='lc1'>单位简介</td>
	              <td class='lc2' colspan="3"><s:textarea rows="4" cols="95" name="unit.unit_pro" ></s:textarea>&nbsp;<font color="red">可输入的最大文字数是255</font></td>
	            </tr>
	            <tr>
	              <td colspan="4">
	                <!--操作按扭-->
	                <table width="100%" border="0" cellspacing="0" cellpadding="0">
	                  <tr>
	                    <td align="center">
	                     <s:submit value="提交"></s:submit>
	                     <!--  <input class="inp_L3" type="button" name="btn_Interview" value="提交" onClick="goSubmit();">
	                      <input class="inp_L3" type="button" name="btn_Close" value="返回" onClick="goSubmit();">-->
	                    </td>
	                  </tr>
	                </table>
	              </td>
	            </tr>
	          </table>
	        </td>
	      </tr>
	      </table>
      </s:form>
          <script type="text/javascript">
    function goSubmit(){
      if(window.confirm('你确定要离开此页面吗？')){
        window.location.href('../page/CorpInfoListDetails.html');
      }
    }
    function goSubmit(){
      if(window.confirm('你确定要离开此页面吗？')){
        window.location.href('../page/context.html');
      }
    }
    </script>
    </body>
</html>
