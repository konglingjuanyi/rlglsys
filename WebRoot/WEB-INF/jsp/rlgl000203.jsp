<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="global.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<link href="${pageContext.request.contextPath}/style/style.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/style/boxy.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.7.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.2.6.pack.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.boxy.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/common.js"></script>
<title><s:property value="getText('rlglsys.browserhead.IE')" />
</title>
<script language="JavaScript">
	$(function() {
		// 画面初始化
		initialize();
		//     waitShow();

		$("#ckgdxf").click(function() {
			parent.addTab("rlgl400103TimeInit.action", "学分明细");
		})
	});
	function initialize() {
		var msgId = $("#msgId").val();
		if (msgId != "" && msgId != null) {
			alertMessage(msgId);
		}
	}
	// 入口按钮押下
	function menuClick(action, naviId, menu_name, only_search) {
		if (action.indexOf("?") > 0) {
			action = action + "&naviId=" + naviId + "&only_search="
					+ only_search;
		} else {
			action = action + "?naviId=" + naviId + "&only_search="
					+ only_search;
			;
		}
		parent.addTab(action, menu_name);
	}
</script>

<!-- <script type="text/javascript">
	function subgo() {
		  parent.addTab("rlgl400103TimeInit.action", "学分明细");
	}
</script> -->
</head>
<body class="mainbody">
	<div>
		<s:form name="form1" method="post" action="rlgl000203Init"
			nameSpace="/rlgl">
			<s:hidden name="navigationId" id="navigationId" />
			<s:hidden name="screenId" id="screenId" />
			<s:hidden name="msgId" id="msgId" />
			<div class="admin-content">
				<div class="am-cf am-padding">
					<my:navigation></my:navigation>
					<div class="am-g  am-margin">
						<div class="am-u-sm-12">
							<div class="am-panel am-panel-primary">
								<div class="am-panel-hd am-cf"
									data-am-collapse="{target: '#collapse-panel-4'}">
									通知公告<span class="am-icon-chevron-down am-fr"></span>
								</div>
								<div id="collapse-panel-4" class="am-panel-bd am-collapse am-in">
									<s:if test="%{rlgl000203Bean.noticeCount > 0}">
										<ul class="am-list admin-content-task">
											<li><marquee direction="up" onmouseout="this.start()"
													onMouseOver="this.stop()" scrollamount="2">
													<s:iterator value="noticeInfoList" status="L"> 
                   · <s:property value="notice" />
														<br />
													</s:iterator>
												</marquee></li>
										</ul>
									</s:if>
									<s:else>
										<tr>
											<td class="td_bg" align="left" colspan="2"><font>暂无公告</font>
											</td>
										</tr>
									</s:else>
								</div>

							</div>
						</div>
						<div class="am-u-sm-12">
							<div class="am-panel am-panel-warning">
								<div class="am-panel-hd am-cf"
									data-am-collapse="{target: '#collapse-panel-5'}">
									--您近7年内的学分如下<span class="am-icon-chevron-down am-fr"></span>
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
						</div>
					</div>
				</div>
			</div>
		</s:form>
	</div>
</body>
</html>