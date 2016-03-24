<%@ page language="java" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>单位选择</title>
<link href="ui-select.css" rel="stylesheet" />

<style>
body{font-family:Arial, Helvetica, sans-serif;background:#eee;}
.demo-box {
    margin: 150px auto;
    width: 500px;
    padding: 20px;
    border: 5px solid #ccc;
	background:#fff;
}
.demo-table {
    border-collapse: collapse;
    width: 100%;
}
.demo-table caption {
    border-bottom: 1px dashed #ccc;
    height: 40px;
    margin-bottom: 20px;
	font-size:18px;line-height:1.2;
}
.demo-table tr td {
    padding: 8px 10px;
	font-size:16px;line-height:1.8;
    vertical-align: top;
}
.ui-input {
    vertical-align: top;
    height: 18px;
    font-size: 16px;
    line-height: 20px;
    border: 1px solid #aaa;
    padding: 6px 8px;
    border-radius: 3px;
}
</style>
</head>
<body>
<div class="demo-box">
  <table class="demo-table">
    <caption>单位选择</caption>
    <tr>
      <td>所属地市</td>
      <td><select class="ui-select" id="sel_03" disabled  >
          <option value="1">日照市</option>
          
        </select></td>
    </tr>
  <META NAME="Generator" CONTENT="EditPlus">

  <META NAME="Author" CONTENT="">

  <META NAME="Keywords" CONTENT="">

  <META NAME="Description" CONTENT="">

  <script language="JavaScript" type="text/javascript">
  
//<a href="http://115.28.111.82:8080/rlglsys/rlglregister.jsp">

  

     //定义了单位名称的二维数组，里面的顺序跟省份的顺序是相同的。通过selectedIndex获得省份的下标值来得到相应的城市数组

     var city=[

["日照市人民医院(3711000001)","日照市中医医院(3711000002)","日照市疾病预防控制中心(3711000003)","日照市妇幼保健院(3711000004)","日照市爱卫会办公室(3711000007)","日照市精神卫生中心(3711000008)","日照市结核病防治所(3711000009)","日照市皮肤病防治所(3711000010)","日照市卫生学校(3711000011)","日照市中心血站(3711000012)","日照市120紧急救援指挥中心(3711000013)","日照市健康教育所(3711000014)","日照港口医院(3711000015)","山东兖矿集团日照疗养院(3711000016)","山东省体育学院日照校区卫生所(3711000017)","日照华方中医医院(3711000018)"],
["东港区计划生育服务中心(3711000019)","东港区人民医院(3711000020)","东港区疾病预防控制中心(3711000021)","东港区妇幼保健站(3711000022)","东港区卫生学校(3711000023)","东港关爱医院(3711000024)","日照运输公司总医院(3711000025)","东港东方医院(3711000026)","东港区卫生监督所(3711000027)","东港区日照街道社区卫生服务中心(3711000028)","东港区石臼街道社区卫生服务中心(3711000029)","东港区秦楼街道社区卫生服务中心(3711000030)","东港区南湖中心卫生院(3711000031)","东港区三庄中心卫生院(3711000032)","东港区河山镇卫生院(3711000033)","东港区后村医院(3711000034)","东港区陈疃镇卫生院(3711000035)","东港区西湖镇卫生院(3711000036)","东港区其他单位（含个体诊所）(3711000037)"],
["岚山区人民医院(3711000038)","岚山区疾病预防控制中心(3711000039)","岚山区妇幼保健站(3711000040)","岚山区计划生育服务中心(3711000041)","岚山头卫生院(3711000042)","岚山区虎山卫生院(3711000043)","岚山区碑廓中心卫生院(3711000044)","岚山区黄墩中心卫生院(3711000045)","岚山区高兴卫生院(3711000046)","岚山区巨峰中心卫生院(3711000047)","岚山区中楼卫生院(3711000048)","岚山区其他单位（含个体诊所）(3711000049)"],
["莒县人民医院(3711000050)","莒县中医医院(3711000051)","莒县妇幼保健计划生育服务中心(3711000052)","莒县卫计局卫生监督所(3711000053)","莒县疾病预防控制中心(3711000054)","莒县皮肤病防治所(3711000055)","莒县结核病防治所(3711000056)","莒县精神卫生中心(3711000057)","莒县城阳卫生院(3711000058)","莒县招贤卫生院(3711000059)","莒县夏庄卫生院(3711000060)","莒县浮来山卫生院(3711000061)","莒县小店卫生院(3711000062)","莒县刘官庄卫生院(3711000063)","莒县长岭卫生院(3711000064)","莒县寨里河卫生院(3711000065)","莒县陵阳卫生院(3711000066)","莒县店子集卫生院(3711000067)","莒县龙山卫生院(3711000068)","莒县峤山卫生院(3711000069)","莒县桑园卫生院(3711000070)","莒县洛河卫生院(3711000071)","莒县安庄卫生院(3711000072)","莒县果庄卫生院(3711000073)","莒县碁山卫生院(3711000074)","莒县库山卫生院(3711000075)","莒县东莞卫生院(3711000076)","莒县阎庄卫生院(3711000077)","莒县其他单位（含个体诊所）(3711000078)"],
["五莲县人民医院(3711000079)","五莲县中医医院(3711000080)","五莲县疾病预防控制中心(3711000081)","五莲县妇幼保健院(3711000082)","五莲县皮肤病防治站(3711000083)","五莲县精神病医院(3711000084)","五莲县计划生育服务中心(3711000085)","日照市北经济开发区医院(3711000086)","五莲县洪凝镇卫生院(3711000087)","五莲县叩官镇中心卫生院(3711000088)","五莲县于里镇中心卫生院(3711000089)","五莲县街头镇中心卫生院(3711000090)","五莲县许孟镇中心卫生院(3711000091)","五莲县松柏镇卫生院(3711000092)","五莲县户部镇卫生院(3711000093)","五莲县高泽镇卫生院(3711000094)","五莲县汪湖镇卫生院(3711000095)","五莲县石场乡卫生院(3711000096)","五莲县中至镇卫生院(3711000097)","五莲县其他单位（含个体诊所）(3711000098)"],
["北京路街道社区卫生服务中心(3711000099)","奎山街道社区卫生服务中心(3711000100)"],
["卧龙山社区卫生服务中心(3711000101)","两城中心卫生院(3711000102)"],
["涛雒镇卫生院(3711000103)"]
     ];




     function getCity(){

         //获得所属区县下拉框的对象

         var sltProvince=document.form1.province;

         //获得单位名称下拉框的对象

         var sltCity=document.form1.city;         

         //得到对应所属区县的单位名称数组

         var provinceCity=city[sltProvince.selectedIndex - 1];

 

         //清空单位名称下拉框，仅留提示选项

         sltCity.length=1;

 

         //将单位名称数组中的值填充到单位名称下拉框中

         for(var i=0;i<provinceCity.length;i++){

             sltCity[i+1]=new Option(provinceCity[i],provinceCity[i]);
danwei=provinceCity[i];
         }

     }
	

	 

 </script>
 <script type="javascript">
 function onSelect()
{
  documentEleById("下拉框的ID").checked=="true";
}



</script>

 </HEAD>

 

 <BODY>
<tr>
      <td>地市辖区</td>
 <FORM METHOD=POST action="<%=path %>/1.jsp" name="form1" id="s1">

        <td> <SELECT NAME="province" onChange="getCity()">

             <OPTION VALUE="0">请选择单位隶属 </OPTION>

             <OPTION VALUE="市辖区">市辖区 </OPTION>

             <OPTION VALUE="东港区">东港区 </OPTION>

             <OPTION VALUE="岚山区">岚山区 </OPTION>

             <OPTION VALUE="莒县">莒县 </OPTION>

             <OPTION VALUE="五莲县">五莲县 </OPTION>

			 <OPTION VALUE="日照经济开发区">日照经济开发区 </OPTION>

			 <OPTION VALUE="日照国际海洋城">日照国际海洋城 </OPTION>

			 <OPTION VALUE="山海天旅游度假区">山海天旅游度假区 </OPTION>

				

         </SELECT>
		 </td>
    </tr>
 <tr>

<td>单位名称</td>
        <td> 
  					
  		
        
        <SELECT NAME="city">

              <OPTION VALUE="0">------------------请选择所在单位------------------ </OPTION>

         </SELECT>
		 <tr>
                	<td></td>
                	<td><input  type="submit"  value="下一步"  ></input>
                	
                	
                	
                	</td>
                </tr>


     </FORM>

 </BODY>

</HTML>

