<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="global.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<TITLE>山东省卫生人力资源管理系统</TITLE>
<script type="text/javascript">
	function subgo() {
		  parent.addTab("rlgl400103TimeInit.action", "学分明细");
	}
</script>
</head>
<body>
	<my:navigation></my:navigation>
	
	<s:form id="rlgl100101"  method="post" action="rlgl400102Init" nameSpace="/rlgl">
		<div class="am-panel am-panel-warning am-margin">
								<div class="am-panel-hd am-cf"
									data-am-collapse="{target: '#collapse-panel-5'}">
									继续教育情况完成度--您近7年内的学分如下<span class="am-icon-chevron-down am-fr"></span>
								</div>
								<div id="collapse-panel-5" class="am-panel-bd am-collapse am-in">
									<ul
										class="am-avg-sm-1 am-avg-md-8 am-margin am-padding am-text-center admin-content-list ">
										<li><a href="#" class="am-text-success"><br /> <s:if
													test="rlgl400102bean.credit_year0!=null&&rlgl400102bean.credit_year0!=''">
													<s:property value="rlgl400102bean.credit_year0" />
												</s:if> <br /> <s:if
													test="rlgl400102bean.credit_year0!=null&&rlgl400102bean.credit_year0!=''">
													<s:property value="rlgl400102bean.credit0" />
													<br>
													<s:property value="rlgl400102bean.credit_classification0" />
												</s:if></a></li>
										<li><a href="#" class="am-text-warning"><br /> <s:if
													test="rlgl400102bean.credit_year1!=null&&rlgl400102bean.credit_year1!=''">
													<s:property value="rlgl400102bean.credit_year1" />
												</s:if> <br /> <s:if
													test="rlgl400102bean.credit_year1!=null&&rlgl400102bean.credit_year1!=''">
													<s:property value="rlgl400102bean.credit1" />
													<br>
													<s:property value="rlgl400102bean.credit_classification1" />

												</s:if></a></li>
										<li><a href="#" class="am-text-danger"><br /> <s:if
													test="rlgl400102bean.credit_year2!=null&&rlgl400102bean.credit_year2!=''">
													<s:property value="rlgl400102bean.credit_year2" />
												</s:if> <br /> <s:if
													test="rlgl400102bean.credit_year2!=null&&rlgl400102bean.credit_year2!=''">

													<s:property value="rlgl400102bean.credit2" />
													<br>
													<s:property value="rlgl400102bean.credit_classification2" />

												</s:if> </a></li>
										<li><a href="#" class="am-text-secondary"><br /> <s:if
													test="rlgl400102bean.credit_year3!=null&&rlgl400102bean.credit_year3!=''">
													<s:property value="rlgl400102bean.credit_year3" />
												</s:if> <br />
											<s:if
													test="rlgl400102bean.credit_year3!=null&&rlgl400102bean.credit_year3!=''">

													<s:property value="rlgl400102bean.credit3" />
													<br>
													<s:property value="rlgl400102bean.credit_classification3" />

												</s:if></a></li>
										<li><a href="#" class="am-text-secondary"><br />
											<s:if
													test="rlgl400102bean.credit_year4!=null&&rlgl400102bean.credit_year4!=''">
													<s:property value="rlgl400102bean.credit_year4" />
												</s:if><br />
											<s:if
													test="rlgl400102bean.credit_year4!=null&&rlgl400102bean.credit_year4!=''">
													<s:property value="rlgl400102bean.credit4" />
													<br>
													<s:property value="rlgl400102bean.credit_classification4" />

												</s:if></a></li>
										<li><a href="#" class="am-text-secondary"><br />
											<s:if
													test="rlgl400102bean.credit_year5!=null&&rlgl400102bean.credit_year5!=''">
													<s:property value="rlgl400102bean.credit_year5" />
												</s:if><br />
											<s:if
													test="rlgl400102bean.credit_year5!=null&&rlgl400102bean.credit_year5!=''">
													<s:property value="rlgl400102bean.credit5" />
													<br>
													<s:property value="rlgl400102bean.credit_classification5" />

												</s:if></a></li>
										<li><a href="#" class="am-text-secondary"><br />
											<s:if
													test="rlgl400102bean.credit_year6!=null&&rlgl400102bean.credit_year6!=''">

													<s:property value="rlgl400102bean.credit_year6" />

												</s:if><br />
											<s:if
													test="rlgl400102bean.credit_year6!=null&&rlgl400102bean.credit_year6!=''">
													<s:property value="rlgl400102bean.credit6" />
													<br>
													<s:property value="rlgl400102bean.credit_classification6" />

												</s:if></a></li>
										<li><a href="#" class="am-text-secondary"><br />
											<s:property value="rlgl400102bean.total_score" /><br /> <br /><s:property
													value="rlgl400102bean.score" /> </a></li>
									</ul>
								</div>
								<footer class="am-panel-footer">本学分情况只包含在本网站通过学习获取的学分情况--<a
									href="#" id="ckgdxf">查看更多学分信息</a></footer>
							</div>
							
		<!--  <table cellpadding='0' cellspacing='0' width='75%'
			style='margin-top: 20px'>
			<tr  style='text-align: center; text-indent: 0px; font-size: 12px; color: #1254a4; font-weight: bold;'>
            <th colspan="4">本学分情况只包含在本网站通过学习获取的学分情况</th>
            </tr>
            <tr>
            <td>  <br/> </td>
            </tr>
			<tr>
				<s:if
					test="rlgl400102bean.credit_year0!=null&&rlgl400102bean.credit_year0!=''">
<%-- 					<s:if test = ""></s:if> --%>
					<th
						style='text-align: center; text-indent: 0px; font-size: 12px; color: #1254a4; font-weight: bold;'>
						<s:property value="rlgl400102bean.credit_year0" />
					</th>
				</s:if>
				<s:if
					test="rlgl400102bean.credit_year1!=null&&rlgl400102bean.credit_year1!=''">
					<th
						style='text-align: center; text-indent: 0px; font-size: 12px; color: #1254a4; font-weight: bold;'><s:property
							value="rlgl400102bean.credit_year1" /></th>
				</s:if>
				<s:if
					test="rlgl400102bean.credit_year2!=null&&rlgl400102bean.credit_year2!=''">

					<th
						style='text-align: center; text-indent: 0px; font-size: 12px; color: #1254a4; font-weight: bold;'><s:property
							value="rlgl400102bean.credit_year2" /></th>
				</s:if>
				<s:if
					test="rlgl400102bean.credit_year3!=null&&rlgl400102bean.credit_year3!=''">

					<th
						style='text-align: center; text-indent: 0px; font-size: 12px; color: #1254a4; font-weight: bold;'><s:property
							value="rlgl400102bean.credit_year3" /></th>
				</s:if>
				<s:if
					test="rlgl400102bean.credit_year4!=null&&rlgl400102bean.credit_year4!=''">
					<th
						style='text-align: center; text-indent: 0px; font-size: 12px; color: #1254a4; font-weight: bold; width: 120px'><s:property
							value="rlgl400102bean.credit_year4" /></th>
				</s:if>
				<s:if
					test="rlgl400102bean.credit_year5!=null&&rlgl400102bean.credit_year5!=''">
					<th
						style='text-align: center; text-indent: 0px; font-size: 12px; color: #1254a4; font-weight: bold; width: 120px'><s:property
							value="rlgl400102bean.credit_year5" /></th>
				</s:if>
				<s:if
					test="rlgl400102bean.credit_year6!=null&&rlgl400102bean.credit_year6!=''">
					<th
						style='text-align: center; text-indent: 0px; font-size: 12px; color: #1254a4; font-weight: bold;'>
						<s:property value="rlgl400102bean.credit_year6" />
					</th>
				</s:if>
				<th
					style='text-align: center; text-indent: 0px; font-size: 12px; color: #1254a4; font-weight: bold;'><s:property
						value="rlgl400102bean.total_score" /></th>
			</tr>
			<tr>
				<s:if
					test="rlgl400102bean.credit_year0!=null&&rlgl400102bean.credit_year0!=''">
					<td style="text-align: center; text-indent: 0px; width: 120px">
						<label style="font-size: 18px; color: Red; font-weight: bold;"><s:property
								value="rlgl400102bean.credit0" /></label><br> <label
						style="font-size: 14px; color: Red; font-weight: bold;"><s:property
								value="rlgl400102bean.credit_classification0" /></label>
					</td>
				</s:if>
				<s:if
					test="rlgl400102bean.credit_year1!=null&&rlgl400102bean.credit_year1!=''">
					<td style="text-align: center; text-indent: 0px; width: 120px">
						<label style="font-size: 18px; color: Red; font-weight: bold;"><s:property
								value="rlgl400102bean.credit1" /></label><br> <label
						style="font-size: 14px; color: Red; font-weight: bold;"><s:property
								value="rlgl400102bean.credit_classification1" /></label>
					</td>
				</s:if>
				<s:if
					test="rlgl400102bean.credit_year2!=null&&rlgl400102bean.credit_year2!=''">

					<td style="text-align: center; text-indent: 0px; width: 120px">
						<label style="font-size: 18px; color: Red; font-weight: bold;"><s:property
								value="rlgl400102bean.credit2" /></label><br> <label
						style="font-size: 14px; color: Red; font-weight: bold;"><s:property
								value="rlgl400102bean.credit_classification2" /></label>
					</td>
				</s:if>
				<s:if
					test="rlgl400102bean.credit_year3!=null&&rlgl400102bean.credit_year3!=''">

					<td style="text-align: center; text-indent: 0px; width: 120px">
						<label style="font-size: 18px; color: Red; font-weight: bold;"><s:property
								value="rlgl400102bean.credit3" /></label><br> <label
						style="font-size: 14px; color: Red; font-weight: bold;"><s:property
								value="rlgl400102bean.credit_classification3" /></label>
					</td>
				</s:if>
				<s:if
					test="rlgl400102bean.credit_year4!=null&&rlgl400102bean.credit_year4!=''">
					<td style="text-align: center; text-indent: 0px; width: 120px">
						<label style="font-size: 18px; color: Red; font-weight: bold;"><s:property
								value="rlgl400102bean.credit4" /></label><br> <label
						style="font-size: 14px; color: Red; font-weight: bold;"><s:property
								value="rlgl400102bean.credit_classification4" /></label>
					</td>
				</s:if>
				<s:if
					test="rlgl400102bean.credit_year5!=null&&rlgl400102bean.credit_year5!=''">
					<td style="text-align: center; text-indent: 0px; width: 120px">
						<label style="font-size: 18px; color: Red; font-weight: bold;"><s:property
								value="rlgl400102bean.credit5" /></label><br> <label
						style="font-size: 14px; color: Red; font-weight: bold;"><s:property
								value="rlgl400102bean.credit_classification5" /></label>
					</td>
				</s:if>
				<s:if
					test="rlgl400102bean.credit_year6!=null&&rlgl400102bean.credit_year6!=''">
					<td style="text-align: center; text-indent: 0px; width: 120px">
						<label style="font-size: 18px; color: Red; font-weight: bold;"><s:property
								value="rlgl400102bean.credit6" /></label><br> <label
						style="font-size: 14px; color: Red; font-weight: bold;"><s:property
								value="rlgl400102bean.credit_classification6" /></label>
					</td>
				</s:if>
				<td
					style="text-align: center; text-indent: 0px; font-size: 12px; color: #1254a4; width: 120px">
					<label style="font-size: 18px; color: Red; font-weight: bold;"><s:property
							value="rlgl400102bean.score" /></label>
				</td>
				<td
					style="text-align: center; text-indent: 0px; font-size: 12px; color: #1254a4; width: 120px">
					<a href="javascript:void(0)" onclick="subgo()">查看更多学分信息</a>
				</td>
			</tr>
		</table>-->
		</s:form>
	
</body>
</html>
