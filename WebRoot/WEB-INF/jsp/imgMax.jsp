<%@ page contentType="text/html; charset=UTF-8"%>
 <!-- 显示大图 start-->
 <div id="DivimgMax"  style="display:none; position: absolute; border: 1px solid #02314D;   background-color: white;z-index:9999;overflow: auto;">
  <div style="background: #52CCCC;">
   <table style="width:100%">
     <tr>
        <td style="width:90%"> <div id="banner" style="cursor:move">按住此处可拖动</div></td>
        <td style="width:10%"><img src="${pageContext.request.contextPath}/images/close.png" id="hideDiv"></img></td> 
     </tr>
   
   </table>
 </div>
 <table align="center">
   <tr>
     <td align="center">
       <img id="maxImg" src=""></img>
    </td>
  </tr>
 </table>
 </div>
 <div id="bg"  style="display:none;position:absolute;left:0;width:100%;height:100%;top:0;background:#FFFFFF;opacity:0.1;filter:alpha(opacity=10);"></div>
 <!-- 显示大图 end-->