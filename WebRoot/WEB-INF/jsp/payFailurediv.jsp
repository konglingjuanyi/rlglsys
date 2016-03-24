<%@ page contentType="text/html; charset=UTF-8"%>
 <!-- 显示大图 start-->
 <div id="payFailure"  style="display:none;  position: absolute; border: 1px solid #02314D;width:50%;height:20%; left:25%; top:10%;  background-color: white;z-index:9999;overflow: auto;">
  <div style="background: #52CCCC;">
   <table style="width:100%">
     <tr>
        <td style="font-family: fantasy;">未支付成功</td>
     </tr>
   
   </table>
 </div>
 <table align="center">
   <tr>
     <td align="center" style="line-height: 50px;font-size:18px;">
        订单未支付成功，请重新支付！
    </td>
  </tr>

  <tr>
    <td align="center" >
      <input type="button" class="inp_L6 btnClass_${only_search}" value="确定" name="closeDiv2" id="closeDiv2">
      </td>
  </tr>
 
 </table>
 </div>
 <div id="bg2"  style="display:none;position:absolute;left:0;width:100%;height:100%;top:0;background:#FFFFFF;opacity:0.1;filter:alpha(opacity=10);"></div>
 <!-- 显示大图 end-->