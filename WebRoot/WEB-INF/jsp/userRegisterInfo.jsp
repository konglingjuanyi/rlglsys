<%@ page language="java"  pageEncoding="utf-8"%>
<%@ include file="global.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    <title><s:property value="getText('rlglsys.browserhead.IE')" /></title>
    <%
    response.addHeader("Cache-Control", "no-cache");
    %>
       
    </head>
    <body>
    <s:form id="form" name="form" method="post" action="rlgl100100Init" nameSpace="/rlgl">
        <s:hidden name="navigationId" id="navigationId"/>
        <s:hidden name="screenId" id="screenId"/>
        <s:hidden name="only_search" id="only_search"/>
        <s:hidden name="TransactionID" id="TransactionID"/>
      
        <!-- 检索条件 -->
        <div class="content">
            <!-- 明细一览列表 -->
            <div id="result" style="position: relative;top:15px">
            
              <table width="100%" style="position: relative; top: 10px;" border="1" cellpadding="2" cellspacing="2">
              <tr>
                <th height="28" width="5%" class="thTitleItrn">序号</th> 
                 <th height="28" width="10%" class="thTitleItrn">姓名</th>
                  <th height="28" width="10%" class="thTitleItrn">身份证号</th>
                 <th height="28" width="10%" class="thTitleItrn">工作单位</th>
                  <th height="28" width="10%" class="thTitleItrn">单位编号</th>
                   <th height="28" width="10%" class="thTitleItrn">所属区县</th>
                 <th height="28" width="10%" class="thTitleItrn">注册时间</th>
              </tr>
              <s:iterator value="#request.list" status="L">
                    <tr>
                    <td height="20" class="tdc">${L.index +1}</td> 
                     <td height="20" class="tdl">
                      <s:property value='USER_NAME'/>
                     </td>
                       <td height="20" class="tdl"><s:property value='USER_ID'/></td>
                         <td height="20" class="tdl"><s:property value='UNIT_NM'/></td>
                         <td height="20" class="tdl"><s:property value='Unit_NO'/></td>
                         
                          <td height="20" class="tdl"><s:property value='PROVINCE'/>
                          <s:property value='CITY'/>
                          <s:property value='ZONE'/>
                          </td>
                          <td>
                           <s:property value='LOGIN_DATE'/>
                          </td>
                        
                          </tr>
                  
              </s:iterator>
              </table>
            </div>
        </div>
    </s:form>
    </body>
</html>

