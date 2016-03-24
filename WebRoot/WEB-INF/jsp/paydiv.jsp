<%@ page contentType="text/html; charset=UTF-8"%>
 <!-- 显示大图 start-->
 <div id="pay"  style="display:none;  position: absolute; border: 1px solid #02314D;width:50%;height:30%; left:25%; top:10%;  background-color: white;z-index:9999;overflow: auto;">
  <div style="background: #52CCCC;">
   <table style="width:100%">
     <tr>
        <td style="font-family: fantasy;">网上支付提示</td>
     </tr>
   
   </table>
 </div>
 <table align="center">
 <tr>
    <td>
    <img src="${pageContext.request.contextPath}/exclamation.jpg"></img>
    </td>
			

    <td>
    <table>
    <tr>
     <td align="left" style="line-height: 20px;font-size:15px;">
        支付完成前，请不要点击下面按钮。
    </td>
  </tr>
  <tr>
     <td align="left" style="line-height: 20px;font-size:15px;">
                       支付完成后，请根据支付的情况点击下面按钮。
    </td>
  </tr>
  <tr>
     <td align="left" style="line-height: 20px;font-size:15px;">
                      <span style="color: red;">注：支付成功后，在银行画面必须点击【返回商户】按钮！</span></td>
  </tr>
    </table>
    </td>
 </tr>
   
   </table>
   <table align="center">
  <tr>
    <td align="center" >
      <input type="button" class="inp_L6 btnClass_${only_search}" value="支付完成" name="overDiv" id="overDiv">
      <input type="button" class="inp_L6 btnClass_${only_search}" value="支付遇到问题" id="closeDiv">
     </td>
  </tr>
 
 </table>
 </div>
 <div id="bg1"  style="display:none;position:absolute;left:0;width:100%;height:100%;top:0;background:#FFFFFF;opacity:0.1;filter:alpha(opacity=10);"></div>
 <!-- 显示大图 end-->