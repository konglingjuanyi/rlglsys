/****************************************************************
 * 方法名：$(document).ready
 * 概 要：页面各事件的定义，页面初期显示处理调用的Javascript定义
 * 参 数：无
 * 返回值：无
 ***************************************************************/
$(document).ready(function(){
	var flg = "";
	// 设置按钮非活性
	$(".btnClass_1").attr("disabled",true); 
	$(".btnClass_sel").attr("disabled",true); 
    // 数字验证
    $(".textnumber").blur(function(){
    	if (flg != "1")
    	{
    		if ($(this).val() == "") {
    	        return;
    	    }
    	    if (!checkNumber($(this).val())) {
    	        var obj = $(this);
    	        var callBackFun = function() {obj.focus();flg="";};
    	        alertMessage("AM001", callBackFun);
    	    }
    	}
    });
    // 数字(带小数)验证
    $(".textnumberpoint").blur(function(){
    	if (flg != "1")
    	{
    		if ($(this).val() == "") {
                return;
            }
            if (!checkNumberPoint($(this).val())) {
            	var obj = $(this);
            	var callBackFun = function() {obj.focus();flg="";};
                alertMessage("AM059", callBackFun);
            }
    	}
    });
    // 英文字母验证
    $(".textenglish").blur(function(){
    	if (flg != "1")
    	{
    		if ($(this).val() == "") {
                return;
            }
            if (!checkEnglish($(this).val())) {
            	var obj = $(this);
            	var callBackFun = function() {obj.focus();flg="";};
                alertMessage("AM002", callBackFun);
            }
    	}
    });
    // 数字和英文字母验证
    $(".textnumandeng").blur(function(){
    	if (flg != "1")
    	{
    		if ($(this).val() == "") {
                return;
            }
            if (!checkNumAndEng($(this).val())) {
            	var obj = $(this);
            	var callBackFun = function() {obj.focus();flg="";};
                alertMessage("AM003", callBackFun);
            }
    	}
    });
    // 身份证验证
    $(".textidentificationCard").blur(function(){
    	if (flg != "1")
    	{
    		if ($(this).val() == "") {
                return;
            }
            if (!checkIdentificationCard($(this).val())) {
            	var obj = $(this);
            	var callBackFun = function() {obj.focus();flg="";};
                alertMessage("AM006", callBackFun);
                flg = "1";
            }
    	}
    });
    // 邮箱格式验证
    $(".textEmail").blur(function(){
    	if (flg != "1")
    	{
            if ($(this).val() == "") {
                return;
            }
            if (!checkEmail($(this).val())) {
            	var obj = $(this);
            	var callBackFun = function() {obj.focus();flg="";};
                alertMessage("AM048", callBackFun);
            }
    	}
    });
    
    // 电话号码验证
    $(".textphonenum").blur(function(){
    	if (flg != "1")
    	{
    		if ($(this).val() == "") {
                return;
            }
            if (!checkPhoneNum($(this).val())) {
            	var obj = $(this);
            	var callBackFun = function() {obj.focus();flg="";};
                alertMessage("AM017", callBackFun);
            }
    	}
    });
    try {
        if (top.topFlg != undefined) {
        	top.isSubmitted = false;
        }
    } catch(e)
    {}

    // 二重送信防止
    $("form").submit(restrictDoubleTransmit);

    // 跳转按钮
    $("#btnMovePage").click(function() {
        // Action取得
        var action = $("#hdnActionId").val();
        var pageNo = parseInt($("#txtInputPage").val());
        var pageCount = parseInt($("#hdnTotlePage").val());
        if(pageNo == 0 || pageNo > pageCount) {
        	var callBackFun = function() {$("#txtInputPage").focus();};
            alertMessage("AM055", callBackFun);
            return false;
        }
        // Action修改
        $("form").attr("action", action);
        $("form").submit();
    });

    // 首页按钮
    $("#btnLeftLeft").click(function() {
        // Action取得
        var action = $("#hdnActionId").val();
        // 当前页设定
        $("#txtInputPage").val("1");
        // Action修改
        $("form").attr("action", action);
        $("form").submit();
    });
    // 上一页按钮
    $("#btnLeft").click(function() {
        // Action取得
        var action = $("#hdnActionId").val();
        // 当前页设定
        var dispPage = $("#txtInputPage").val() - 1;
        $("#txtInputPage").val(dispPage);
        $("form").attr("action", action);
        $("form").submit();
    });
    // 下一页按钮
    $("#btnRight").click(function() {
        // Action取得
        var action = $("#hdnActionId").val();
        // 当前页设定
        var dispPage = parseInt($("#txtInputPage").val()) + parseInt("1");
        $("#txtInputPage").val(dispPage);
        $("form").attr("action", action);
        $("form").submit();
    });

    // 尾页按钮
    $("#btnRightRight").click(function() {
        // Action取得
        var action = $("#hdnActionId").val();
        // 当前页设定
        var pageCount = $("#hdnTotlePage").val();
        $("#txtInputPage").val(pageCount);
        // Action修改
        $("form").attr("action", action);
        $("form").submit();
    });
    $(".RowOdd").mouseover(function(){
    	//如果鼠标移到class为stripe的表格的tr上时，执行函数
    	$(this).addClass("RowOver");
    });
    $(".RowOdd").mouseout(function(){
    	//如果鼠标移到class为stripe的表格的tr上时，执行函数
    	$(this).removeClass("RowOver");
    });
    $(".RowEven").mouseover(function(){
    	//如果鼠标移到class为stripe的表格的tr上时，执行函数
    	$(this).addClass("RowOver");
    });
    $(".RowEven").mouseout(function(){
    	//如果鼠标移到class为stripe的表格的tr上时，执行函数
    	$(this).removeClass("RowOver");
    });
    // 浏览器关闭事件
    if (window.attachEvent) {
        window.attachEvent('onbeforeunload', function() {
            // IE关闭
        	if (this.name == 'identity' && (event.altKey || (($.browser.version >= 6
                    || event.clientX > document.body.clientWidth) && event.clientY < 0))) {
            	$.post("windowsClose.action", "", "");
            }
        });
    }

    $("#loading").remove();
    $("#loading2").remove();
});
// 对话框标题
var MESSAGE_TITLE = "提示信息";

// 提示消息内容
alertMessageList = {
    "AM001":"请输入数字！",
    "AM002":"请输入英文字母！",
    "AM003":"请输入数字或者英文字母！",
    "AM004":"数据写入完成，请5秒后点击确定！！",
    "AM005":"请选择统计范围！",
    "AM006":"请输入正确的身份证！",
    "AM007":"对不起，本系统禁止使用右键！",
    "AM008":"禁止该操作！",
    "AM009":"请输入姓名！",
    "AM010":"请输入身份证号！",
    "AM011":"请输入当前工作单位！",
    "AM012":"请输入调往工作单位！",
    "AM013":"该人员编号不存在，请重新输入！",
    "AM014":"请输入人员编号！",
    "AM015":"请选择管理口径！",
    "AM016":"请上传正确的图片！",
    "AM017":"请输入正确的电话号码！",
    "AM018":"已超过允许的最大文字数！",
    "AM019":"请选择指定口径！",
    "AM020":"该管理口径下，此管理员账户已存在！",
    "AM021":"请填写必须输入项目！",
    "AM022":"请选择指定人员！",
    "AM023":"请选择追加人员！",
    "AM024":"请选择需导入的文件！",
    "AM025":"请输入内设机构名称！",
    "AM026":"添加的单位信息重复，请确认！",
    "AM027":"添加的单位字母缩写重复，请确认！",
    "AM028":"添加的用户编码重复，请确认！",
    "AM029":"添加的用户身份证号重复，请确认！",
    "AM030":"两次输入的密码不一致！",
    "AM031":"密码不能为空！",
    "AM032":"原密码输入不正确，请确认！",
    "AM033":"密码已经修改成功，需要退出重新登录！",
    "AM034":"请输入申请日期！",
    "AM035":"请输入不在岗原因！",
    "AM036":"指定代管单位已成功，需要退出系统！",
    "AM037":"注销已成功，需要退出系统！",
    "AM038":"请选择新科室！",
    "AM039":"用户名不能为空！",
    "AM040":"办公电话不正确，请重新输入！",
    "AM041":"申请事项流程重复，请确认后再添加！",
    "AM042":"请选择对象！",
    "AM043":"您没有变更信息不能提交变更申请！"	,
    "AM044":"调往外省的情况，请手动入力调往单位名称！",
    "AM045":"请至少保留一行！",
    "AM046":"同一单位不能授权为代管！",
    "AM047":"代管单位或被代管单位不能为空！",
    "AM048":"邮箱格式不正确，请重新输入！",
    "AM049":"调出单位不能是本单位，请重新输入！",
    "AM050":"验证码不能为空！",
    "AM051":"终审通过的情况，请录入编制文号！",
    "AM052":"图片格式有误，请选择jpg|gif|png|bmp格式的图片上传！",
    "AM053":"上传失败！",
    "AM054":"您上传的文件大小超出了1024K限制！",
    "AM055":"请输入有效的页码！",
    "AM056":"您还没有设立单位联系人，请在【系统管理-单位管理-管理员设定】中进行设立！",
    "AM057":"开始时间不能大于结束时间！",
    "AM058":"当前时间不在申请日期范围内，您不能进行申请！",
    "AM059":"请输入数字，小数位最多4位！",
    "AM060":"证书申请截止日期必须大于开始日期，请确认！",
    "AM061":"证书考核结束日期必须大于开始日期，请确认！",
    "AM062":"课程结课日期必须大于开课日期，请确认！",
    "AM063":"学分数必须为数字类型，请确认！",
    "AM064":"考试时间不能大于发证时间！",
    "AM065":"派出时间不能大于结业时间！",
    "AM066":"请选择人员调入二级方式！",
    "AM067":"请选择人员调入三级方式！",
    "AM068":"请选择人员入口！",
    "AM069":"请选择专家级别！",
    "AM070":"请选择课件！",
    "AM071":"所选课件包含过期课件，请取消或删除过期课件后再支付！",
    "AM072":"请选择公告发布的对象！",
    "AM073":"次年举办结束日期必须大于开始日期，请确认！",
    "AM074":"举办结束日期必须大于开始日期，请确认！",
    "AM075":"请选择报名项！",
    "AM076":"学分不能大于拟授学员学分，请确认！",
    "AM077":"请选择需要提交的考题信息！",
    "AM078":"请指定考题适用考核人群！",
    "AM079":"请填写必须项！",
    "AM080":"请选择追加的字典信息！",
    "AM081":"请选择需要安排考场的报名信息！",
    "AM082":"请指定考点和考场！",
    "AM083":"请选择科目！",
    "AM084":"请重新确认时间信息！",
    "AM085":"开始年月不能大于结束年月，请重新选择！",
    "AM086":"开始日期不能大于结束日期，请重新选择！",
    "AM087":"考试时间必须大于零！",
    "AM089":"请选择需追加菜单！",
    "AM090":"证书考核开始日期必须大于证书申请结束日期，请确认！",
    "AM091":"发证日期必须大于证书考核结束日期，请确认！",
    "AM092":"项目举办日期必须大于申报日期，请确认！",
    "AM093":"请选择学分年度！",
    "AM094":"请选择设定单位！",
    "AM095":"请选择新的支付方式！",
    "AM096":"请选择课程！",
    "AM097":"请输入正确的学分数(0-100)！",
    "AM098":"预定回国日期必须大于出国日期！",
    "AM099":"结业时间必须大于派出时间！",
    "AM100":"该用户已缴纳当年费用！",
    "AM101":"派出时间必须大于备案时间！",
    "AM102":"年度请输入4位数字！",
    "AM103":"开始年度不能大于结束年度！",
    "AM104":"报名开始时间不能为空！",
    "AM105":"报名结束时间不能为空！",
    "AM106":"考试开始时间不能为空！",
    "AM107":"考试开始时间不能为空！",
    "AM108":"科目区分不能为空！",
    "AM109":"考试名称不能为空！",
    "AM110":"考试时间不能为空！",
    "AM111":"考试地点不能为空！",
    "AM112":"开始时间不能为空！",
    "AM113":"结束时间不能为空！",
    "AM114":"您没有选择数据，请先选择数据！",
    "AM115":"参加工作时间必须大于出生日期！",
    "AM116":"入党(团)时间必须大于出生日期！",
    "AM117":"申请单位名称不能为空！",
    "AM118":"申请人姓名不能为空！",
    "AM119":"申请人电话不能为空！",
    "AM121":"请输入邮箱,找回密码用！",
    "AM120":"请选择需要开具发票人员！"
};
// 确认消息内容
confirmMessageList = {
    "CM001":"是否确认退出？",
    "CM002":"是否确认重新登录？",
    "CM003":"是否确认审核通过此信息申请？",
    "CM004":"是否确认驳回此信息申请？",
    "CM005":"是否确认返回前画面？",
    "CM006":"是否确认追加内设机构？",
    "CM007":"是否确认删除该内设机构？",
    "CM008":"是否确认提交？",
    "CM009":"是否确认Excel导出？",
    "CM010":"是否确认追加内聘人员？",
    "CM011":"是否确认追加正聘人员？",
    "CM012":"是否确认删除该人员？",
    "CM013":"是否确认保存申请信息？",
    "CM014":"是否确认追加该兼职人员？",
    "CM015":"是否确认退出该页面？",
    "CM016":"你确定要删除选择行吗？",
    "CM017":"你确定要离开此页面吗？",
    "CM018":"是否确定删除该用户？",
    "CM019":"是否确定保存用户信息？",
    "CM020":"是否确定迁移到一览画面？",
    "CM021":"是否确认保存单位信息？",
    "CM022":"是否确认提交申请信息？",
    "CM023":"是否确认返回单位添加画面？",
    "CM024":"是否确认返回用户添加画面？",
    "CM025":"选择代管单位后您的权限将受限制，是否确认提交？",
    "CM026":"是否确认修改密码？",
    "CM027":"是否确认登录流程？",
    "CM028":"是否确认停用该流程？",
    "CM029":"单位注销后该单位的所有用户将没有权限进入系统，是否需要注销？",
    "CM030":"是否确认停用该单位？",
    "CM031":"是否确认启用该单位？",
    "CM032":"是否确认停用该用户？",
    "CM033":"是否确认启用该用户？",
    "CM034":"是否确认启用该流程？",
    "CM035":"是否确认提交科室信息？",
    "CM036":"是否确认否决该用户的申请信息？",
    "CM037":"是否确认取消该用户的申请信息？",
    "CM038":"是否确认进行该用户的申请？",
    "CM039":"是否确认删除该科室？",
    "CM040":"是否确认启用该科室？",
    "CM041":"是否确认迁移到科室详细画面？",
    "CM042":"是否确认迁移到科室更新画面？",
    "CM043":"是否确认从科室中移除该人员？",
    "CM044":"是否确认迁移到科员追加画面？",
    "CM045":"是否确认添加人员到科室？",
    "CM046":"是否确认撤销申请？",
    "CM047":"是否确认进入申请明细画面？",
    "CM048":"是否确认将选中的人员转到新科室？",
    "CM049":"单位解除注销后该单位的所有用户将有权限进入系统，是否确认解除注销？",
    "CM050":"单位代管后该单位用户只有查看权限，是否确认代管？",
    "CM051":"单位代管释放后，代管单位将没有权限管理该单位，是否确认取消代管？",
    "CM052":"是否确定将选定的用户信息存入到数据库？",
    "CM053":"是否确认重新申请？",
    "CM054":"是否确认切换到该单位？",
    "CM055":"是否确认将该用户密码恢复到初始状态？",
    "CM056":"是否确认返回申请流程一览画面？",
    "CM057":"是否确认修改该流程信息？",
    "CM058":"是否确认提交该课程信息？",
    "CM059":"是否确认提交该证书信息？",
	"CM060":"是否确认进入角色添加画面？",
	"CM061":"是否确认停用该角色？",
	"CM062":"是否确认启用该角色？",
	"CM063":"是否确认进入角色修改画面？",
	"CM064":"是否确认进入角色设定画面？",
	"CM065":"是否确认进入角色详细画面？",
	"CM066":"是否确认返回角色一览画面？",
	"CM067":"是否确认返回角色详细画面？",
	"CM068":"是否确认保存角色设定信息？",
	"CM069":"是否确认保存角色信息？",
	"CM070":"是否确认保存角色变更信息？",
	"CM071":"是否确认打印证书？",
	"CM072":"是否确认删除该条信息？",
	"CM073":"是否确认提交公告信息？",
	"CM074":"是否确认删除选中课件？",
	"CM075":"该课件已过期，不能进行学习与考试。是否确认删除该过期课件？",
	"CM076":"是否确认删除该公告信息？",
	"CM077":"是否确认恢复该公告信息？",
	"CM078":"是否确认迁移到公告详细画面？",
	"CM079":"是否确认迁移到公告修改画面？",
	"CM080":"是否确认迁移到公告发布画面？",
	"CM081":"是否确认返回到公告一览画面？",
	"CM082":"是否确认提交公告修改信息？",
	"CM083":"是否确认发布公告到全部单位或用户？",
	"CM084":"是否确认发布公告到选择的用户？",
	"CM085":"是否确认迁移到增加公告画面？",
	"CM086":"是否确认迁移到新项目申报详细画面？",
	"CM087":"是否确认迁移到新项目申报更新画面？",
	"CM088":"是否确认开放该单位院内学分举办权？",
	"CM089":"是否确认关闭该单位院内学分举办权？",
    "CM090":"是否确认提交新项目申报信息？",
    "CM091":"是否确认提交备案项目申报信息？",
    "CM092":"是否确认迁移到备案项目申报详细画面？",
    "CM093":"是否确认迁移到备案项目申报更新画面？",
    "CM094":"是否确认修改备案项目申报信息？",
    "CM095":"是否确认修改新项目申报信息？",
    "CM096":"是否确认迁移到项目办结画面？",
    "CM097":"是否确认迁移到授予学分画面？",
    "CM098":"是否确认迁移到打印学分证一览画面？",
    "CM099":"是否确认迁移到项目一览画面？",
    "CM100":"是否确认提交项目办结信息？",
    "CM101":"是否确认提交学分授予信息？",
    "CM102":"是否确认迁移到学分证打印画面？",
    "CM103":"是否确认打印学分证？",
    "CM104":"是否确认删除选中试题？",
    "CM105":"是否确认提交选中考题信息？",
    "CM106":"该套题目的总分为：{0}，是否确认提交试卷信息？",
    "CM107":"是否确认提交科室完善信息？",
    "CM108":"是否确认返回到科室一览画面？",
    "CM109":"是否确认迁移到科室完善画面？",
    "CM110":"是否确认迁移到新增字典信息画面？",
    "CM111":"是否确认迁移到字典信息明细画面？",
    "CM112":"是否确认迁移到字典信息修改画面？",
    "CM113":"是否确认迁移到字典信息删除画面？",
    "CM114":"是否确认登录字典信息？",
    "CM115":"是否确认迁移到字典信息一览画面？",
    "CM116":"是否确认修改字典信息？",
    "CM117":"是否确认删除字典信息？",
    "CM118":"是否确认提交所选择的字典信息？",
    "CM119":"是否确认提交选中报名信息？",
    "CM120":"是否确认迁移到新增地区信息画面？",
    "CM121":"是否确认迁移到地区信息明细画面？",
    "CM122":"是否确认迁移到地区信息修改画面？",
    "CM123":"是否确认迁移到地区信息删除画面？",
    "CM124":"是否确认登录地区信息？",
    "CM125":"是否确认迁移到地区信息一览画面？",
    "CM126":"是否确认修改地区信息？",
    "CM127":"是否确认删除地区信息？",
    "CM128":"是否确认提交所选择的地区信息？",
    "CM129":"是否确认打印准考证？",
    "CM130":"是否确认导出WORD文档？",
    "CM131":"是否确认迁移到学分审核明细画面？",
    "CM132":"是否确认迁移到学分审核一览画面？",
    "CM133":"是否确认对选中人员的学分申报项目进行批量审核？",
    "CM134":"是否确认发送短信？",
    "CM135":"是否确认为选中的单位设定新的支付方式？",
    "CM136":"是否确认删除选中的数据？",
    "CM137":"是否确定将选定的课件信息存入到数据库？",
    "CM138":"该年度只能申请一次，是否确认提交？",
    "CM139":"是否确认迁移到余额修改画面？"
};

/****************************************************************
 * 方法名：createUpdateMark
 * 概 要：创建修改标志
 * 参 数：无
 * 返回值：无
 ***************************************************************/
function createUpdateMark(curElementId) {
	var updateMark = $("<span id=\"span" + curElementId + "\" style=\"color:red\"> 修改</span>");
	updateMark.insertAfter($("#" + curElementId));
}
// 表示种别
showKind = {
	"update" : "修改",
	"review" : "待审核"
};
function createReviewMark(elementIdArray, kind) {
	if (kind == undefined) {
		kind = "review";
	}
	// 审核项目ID
	var elementIds = elementIdArray.split(",");
	// 下级下拉框设值
	for (var count = 0; count < elementIds.length; count++) {
		var reviewMark = $("<span id=\"span" + elementIds[count] + "\" style=\"color:red\">  " + showKind[kind] + "</span>");
		reviewMark.insertAfter($("#" + elementIds[count]));
	}
}
/****************************************************************
 * 方法名：removeUpdateMark
 * 概 要：删除修改标志
 * 参 数：无
 * 返回值：无
 ***************************************************************/
function removeUpdateMark(curElementId) {
	$("#span" + curElementId).remove();  
}

/****************************************************************
 * 方法名：openChildWindow
 * 概 要：打开子页面
 * 参 数：无
 * 返回值：无
 ***************************************************************/
var C_SCREEN_WIDTH = "650px";
var C_SCREEN_HEIGHT = "450px";
function openChildWindow(actionId, param, callBackFun, screenType) {
	var arguments = new Array();
	arguments[0] = callBackFun;
	var url = actionId;
	if(param != undefined && param != "") {
		url = url + "?" + param;
	}
	var features;
	if (screenType == "1") {
		features="status=no; dialogHeight=" + C_SCREEN_HEIGHT + 
	                       ";dialogWidth=" + C_SCREEN_WIDTH;
	}
	window.showModalDialog(url, arguments, features);
}

/****************************************************************
 * 方法名：openUnitSelectWindow
 * 概 要：打开单位选择页面
 * 参 数：无
 * 返回值：无
 ***************************************************************/
function openUnitSelectWindow(param) {
	openChildWindow("rlgl300101Init.action", param, callBackFun_unitSelect, "1");
}
/****************************************************************
 * 方法名：showMessage
 * 概 要：显示提示消息
 * 参 数：无
 * 返回值：无
 ***************************************************************/
function showMessage(msgType) {
    var options = {behaviours: function(r) {
                       $(r).hover(function() {
                               $(this).css({
                            	   "background-color":"#FFFAF0"});
                           }, function() {
                               $(this).css({
                            	   "background-color":"#FFE1FF"});
                           });
                       }};
    var msgTitle;
    if (msgType == "1") {
        msgTitle = "错误信息详细";
    } else {
        msgTitle = "系统提示信息";
    }
    options = $.extend({title: msgTitle}, options || {});
    
   // new Boxy("<div><p>" + $("#message").val() + "</p></div>", options);
}

/****************************************************************
 * 方法名：showMessage
 * 概 要：显示提示消息
 * 参 数：无
 * 返回值：无
 ***************************************************************/
function showApplyInfo(flow, stutas) {
    var options = {behaviours: function(r) {
                       $(r).hover(function() {
                                   $(this).css({
                                	   "background-color":"#FFFAF0"});
                               }, function() {
                                   $(this).css({
                                	   "background-color":"#FFE1FF"});
                               });
                           }};
    var msgTitle = "审核状态";
    options = $.extend({title: msgTitle}, options || {});
    var message = "本申请的审核流程：<br/>&nbsp;&nbsp;";
    // 提交申请->单位审核->区局审核->市局审核
    message = message + flow;
    message = message + "<br/>目前状态：<br/>&nbsp;&nbsp;";
    message = message + stutas;
    
    new Boxy("<div><p>" + stutas + "</p></div>", options);
}
function showApplyStatus(status) {
    var options = {behaviours: function(r) {
                       $(r).hover(function() {
                                   $(this).css({
                                	   "background-color":"#FFFAF0"});
                               }, function() {
                                   $(this).css({
                                	   "background-color":"#FFE1FF"});
                               });
                           }};
    var msgTitle = "待审核的申请信息";
    options = $.extend({title: msgTitle}, options || {});
    
    new Boxy("<div><p>" + status + "</p></div>", options);
}
/****************************************************************
 * 方法名：showMessage
 * 概 要：显示提示消息
 * 参 数：无
 * 返回值：无
 ***************************************************************/
function showCourseInfo() {
    var options = {behaviours: function(r) {
                       $(r).hover(function() {
                                   $(this).css({
                                	   "background-color":"#FFFAF0"});
                               }, function() {
                                   $(this).css({
                                	   "background-color":"#FFE1FF"});
                               });
                           }};
    var msgTitle = "必修课情况";
    options = $.extend({title: msgTitle}, options || {});
    var message = $("#courseInfo").html();
    new Boxy("<div><p>" + message + "</p></div>", options);
}

/****************************************************************
 * 方法名：showCourseExamsInfo
 * 概 要：显示已通过课程
 * 参 数：无
 * 返回值：无
 ***************************************************************/
function showCourseExamsInfo(value) {
    var options = {behaviours: function(r) {
                       $(r).hover(function() {
                                   $(this).css({
                                	   "background-color":"#FFFAF0"});
                               }, function() {
                                   $(this).css({
                                	   "background-color":"#FFE1FF"});
                               });
                           }};
    var msgTitle = "已通过课程名称";
    options = $.extend({title: msgTitle}, options || {});
    var message = $("#courseInfo_"+value).html();
    new Boxy("<div><p>" + message + "</p></div>", options);
}

/****************************************************************
 * 方法名：showMessage
 * 概 要：显示明细信息
 * 参 数：无
 * 返回值：无
 ***************************************************************/
function showDetailsInfo(value) {
	var index = $(value).parent().parent().children().children().val();
    var options = {behaviours: function(r) {
                       $(r).hover(function() {
                                   $(this).css({
                                	   "background-color":"#FFFAF0"});
                               }, function() {
                                   $(this).css({
                                	   "background-color":"#FFE1FF"});
                               });
                           }};
    var msgTitle = "订单明细";
    options = $.extend({title: msgTitle}, options || {});
    var message = $("#courseInfo_"+index).html();
    new Boxy("<div><p>" + message + "</p></div>", options);
}


/****************************************************************
 * 方法名：alertMessage
 * 概 要：显示提示消息
 * 参 数：无
 * 返回值：无
 ***************************************************************/
function alertMessage(messageId, callBackFun) {
	alert(alertMessageList[messageId]);
	if (top.menu) {
	//	top.contents.Boxy.alert(alertMessageList[messageId], callBackFun, {title:MESSAGE_TITLE, modal:true});
    } else {
   // 	Boxy.alert(alertMessageList[messageId], callBackFun, {title:MESSAGE_TITLE, modal:true});
    }
    //alert(alertMessageList[messageId]);
    return false;
}
/****************************************************************
 * 方法名：confirmMessage
 * 概 要：显示确认消息
 * 参 数：无
 * 返回值：无
 ***************************************************************/
function confirmMessage(messageId, callBackFun) {
    // 必须输入验证
    if (!checkRequire()) {
        return false;
    }
    if (top.menu) {
    	top.contents.Boxy.confirm(confirmMessageList[messageId], callBackFun, {title:MESSAGE_TITLE, modal:true});
    } else {
        Boxy.confirm(confirmMessageList[messageId], callBackFun, {title:MESSAGE_TITLE, modal:true});
    }
}
//function confirmMessage(messageId, param, callBackFun) {
//    // 必须输入验证
//    if (!checkRequire()) {
//        return false;
//    }
//	var message = confirmMessageList[messageId];
//	message = message.replace("{0}",param);
//    if (top.menu) {
//    	top.contents.Boxy.confirm(message, callBackFun, {title:MESSAGE_TITLE, modal:true});
//    } else {
//        Boxy.confirm(message, callBackFun, {title:MESSAGE_TITLE, modal:true});
//    }
//}

// 验证只能输入数字
function checkNumber(value) {
    //定义正则表达式部分
    var reg = new RegExp("^[0-9]*$");
    return reg.test(value);
}


//验证只能输入数字带小数
function checkNumberPoint(value) {
 //定义正则表达式部分
 var reg = new RegExp("^[0-9]*(\.[0-9]{1,4})?$");
 return reg.test(value);
}

// 验证只能输入字母
function checkEnglish(value) {
    //定义正则表达式部分
    var reg = new RegExp("^[A-Za-z]+$");
    return reg.test(value);
}

// 验证只能输入字母和数字
function checkNumAndEng(value) {
    //定义正则表达式部分
    var reg = new RegExp("^[A-Za-z0-9]+$");
    return reg.test(value);
}

//身份证验证
function checkIdentificationCard(value) {
    // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X   
    var reg = /(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
    return reg.test(value);
}

//验证输入框输入是否为空
function checkInput(value) {
    if(value.replace(/^ +| +$/g,'') == '') {
        return true;
    } else {
        return false;
    }    
}
//邮箱格式验证
function checkEmail(value)
{
	var reg = new RegExp("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");

   // var reg = new RegExp("/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/");
    return reg.test(value);
}
// 电话号码验证
function checkPhoneNum(value) {
    // 固定电话
    var reg0 = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
    // 手机
    var reg1 = /^13\d{5,9}$/;
    var reg2 = /^15\d{5,9}$/;
    var reg3 = /^18\d{5,9}$/;
/*    var reg2 = /^153\d{4,8}$/;
    var reg3 = /^159\d{4,8}$/;
    var reg4 = /^182\d{4,8}$/;
    var reg5 = /^152\d{4,8}$/;*/
    return reg0.test(value) || reg1.test(value) || reg2.test(value) || reg3.test(value);
}
/***************************************************************
 * 机能  视频文件参数编辑
 ***************************************************************/
var learnOnline = {
	// 指定Action
	url : "",
	// url参数
	urlParams : "",
	// 需要变更的隐藏变量的ID
	pageObjectId : ""
};
function learnOnlineLink(){
    var params = {
        'params':learnOnline.urlParams
    };
    $.post(learnOnline.url, params, callbackLearnOnline);
}

/***************************************************************
 * 机能  为画面隐藏变量赋值
 ***************************************************************/
function callbackLearnOnline(result, textStatus){
	// 需要变更的隐藏变量的ID
	var pageObject = learnOnline.pageObjectId;
	var pageParams = learnOnline.urlParams.split(",");
	var page_flg = pageParams[2];
	var object = $("#" + pageObject);
	var course_support = "";
	var arr = new Array();
	if (result != "") {
		arr = result.split(";");
		object.val(arr[0]);
		course_support = arr["1"];
		// 华医网
		if (course_support == "002")
		{
			window.location.href = "http://sdjn.91huayi.com/cme/test.aspx?params=" + arr[0];
		}
		// 好医生
		if (course_support == "001")
		{
			if ("rlgl100107" == page_flg)
	    	{
				//公共课考试
				var url ="http://sd.haoyisheng.com/sdcme/weihai/rizhao.jsp?code="+ arr[0]+"&from=rz";
				window.open(url,"_blank");
	    	}else if("rlgl400101" == page_flg){
					//乡医培训
					var url = "http://qdjcpx.cmechina.net/xjjcpx/jnwarestudy?code="+ arr[0]+"&from=rz";
		    		window.open(url,"_blank");
			}else if("rlgl100113" == page_flg){
				//公共课程学习
				var url  ="http://www.cmechina.net/rizhao/index.jsp?code=" + arr[0]+"&from=rz";
				window.open(url,"_blank");
	    	}else {//项目学习
	    		var url ="http://www.cmechina.net/rizhao/index.jsp?code=" + arr[0]+"&from=rz";
	    		window.open(url,"_blank");
				}
		}
	}
}
function paydataEdit(){
    var params = {
        'params':learnOnline.urlParams
    };
    $.post(learnOnline.url, params, callbackpaydataEdit);
}
function paydataInsert(){
    var params = {
        'params':learnOnline.urlParams
    };
    $.post(learnOnline.url, params, callbackpaydataInsert);
    
}
function callbackpaydataInsert(result, textStatus){
	// 需要变更的隐藏变量的ID
	var pageObject = learnOnline.pageObjectId;
	var object = $("#" + pageObject);
	var arr = new Array();
	if (result != "") {
		arr = result.split(";");
		object.val(arr[0]);
		window.open("https://user.ecpay.cn/paygate.html?merId="+arr["1"]+"&dealOrder="+arr["2"]+"&dealFee="+arr["3"]+"&dealReturn="+arr["5"]+"&dealNotify="+arr["7"]+"&dealSignure="+arr["4"] +"&dealName="+arr["6"]);
	}
}
function paydataPrepay(){
    var params = {
        'params':learnOnline.urlParams
    };
    $.post(learnOnline.url, params, callbackpaydataPrepay);
    
}
function callbackpaydataPrepay(result, textStatus){
	// 需要变更的隐藏变量的ID
	var pageObject = learnOnline.pageObjectId;
	var object = $("#" + pageObject);
	var arr = new Array();
	if (result != "") {
		arr = result.split(";");
		object.val(arr["3"]);
		window.open("http://i.chinaecpay.com/paygate.html?merId="+arr["0"]+"&dealOrder="+arr["1"]+"&dealFee="+arr["2"]+"&dealReturn="+arr["4"]+"&dealNotify="+arr["4"]+"&dealSignure="+arr["3"]+"&dealName="+arr["5"]);
	}
}
/***************************************************************
 * 机能  为画面隐藏变量赋值
 ***************************************************************/
function callbackpaydataEdit(result, textStatus){
	// 需要变更的隐藏变量的ID
	var pageObject = learnOnline.pageObjectId;
	var object = $("#" + pageObject);
	if (result != "") {
		object.val(result);
	}
}
/****************************************************************
 * 方法名：openChildWindowForLearn
 * 概 要：打开子页面
 * 参 数：无
 * 返回值：无
 ***************************************************************/
var C_SCREEN_WIDTH = "900px";
var C_SCREEN_HEIGHT = "700px";
function openChildWindowForLearn(actionId, param, callBackFun, screenType) {
	var arguments = new Array();
	arguments[0] = callBackFun;
	var url = actionId;
	if(param != undefined && param != "") {
		url = url + "?" + param;
	}
	var features;
	if (screenType == "1") {
		features="status=no; dialogHeight=" + C_SCREEN_HEIGHT + 
	                       ";dialogWidth=" + C_SCREEN_WIDTH;
	}
	var sFeatures = "height=700, width=900, scrollbars=no, resizable=no,location=no,status=no";  
	//window.open("http://124.207.214.12:8083/jinan/index.jsp?user_id=1&course_id=200800005053&back_url=http://rlglsys.xicp.net:8070/learnOnlineCallback.action","3km",sFeatures);
	//window.showModalDialog(url, arguments, features);
}

/****************************************************************
 * 方法名：openLearnSelectWindow
 * 概 要：打开单位选择页面
 * 参 数：无
 * 返回值：无
 ***************************************************************/
function openLearnSelectWindow(param) {
	//没关系
	openChildWindowForLearn("http://124.207.214.12:8083/jinan/index.jsp?user_id=1&course_id=200800005053&back_url=http://rlglsys.xicp.net:8070/learnOnlineCallback.action", param, callBackFun_learnBack, "1");
}
/***************************************************************
 * 机能  下拉框选择事件
 ***************************************************************/
var comboxLinkageStructure = {
	// 指定Action
	url : "",
	// url参数
	urlParams : "",
	// 下级下拉框ID
	nextComboxId : "",
	// 下级下拉框名称
	nextComboxName : ""
};
function comboxChanged(){
    var params = {
        'params':comboxLinkageStructure.urlParams
    };
    $.post(comboxLinkageStructure.url, params, callbackOfComboxChanged);
}

/***************************************************************
 * 机能  下拉框选择后联动
 ***************************************************************/
function callbackOfComboxChanged(result, textStatus){
	// 下级下拉框ID
	var nextComboxIds = comboxLinkageStructure.nextComboxId.split(",");
	// 下级下拉框名称
	var nextComboxNames = comboxLinkageStructure.nextComboxName.split(",");
	// 下级下拉框设值
	for (var linkageCount = 0; linkageCount < nextComboxIds.length; linkageCount++) {
		var combox = $("#" + nextComboxIds[linkageCount]);
		combox.empty();
		// 添加默认选择项
		combox.append("<option value = ''>" + "--请选择" + nextComboxNames[linkageCount] + "--</option>");

		// 添加下级下拉框的选择项
		if (linkageCount == 0) {
		    if (result != "") {
			    var options = result.split(",");
			    for (var i = 0; i < options.length; i++) {
			    	var option = options[i].split(":");
			    	combox.append("<option value = '"+option[0]+"'>"+option[1]+"</option>");
			    }
		    }
		}
	}
}

/***************************************************************
 * 机能  给数组添加是否包含方法
 ***************************************************************/
Array.prototype.contains = function (element) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == element) {
            return true;
        }
    }
    return false;
};
Array.prototype.indexOf = function(val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};
Array.prototype.remove = function(val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};

/***************************************************************
 * 机能  提交等待
 ***************************************************************/
function waitShow() {
	try {
		if (window.parent.name == "contents") {
			var _html = "<div id=\"loading\" style=\"position:absolute;left:0;width:100%;height:100%;top:0;background:#FFFFFF;opacity:0.1;filter:alpha(opacity=10);\"></div>";
			_html = _html + "<div id=\"loading2\" style=\"position:absolute;cursor:wait;left:50%;top:50%;padding:0;color:#000;\">";
			_html = _html + "<img src=\"${pageContext.request.contextPath}/images/pagination_loading.gif/></div>";
			jQuery(_html)
		    .appendTo(document.body);
		}
	} catch(e){
	}
}

/***************************************************************
 * 机能  必须输入验证。
 ***************************************************************/
function checkRequire() {
	$(':text,textarea').each(function(flgl, obj) {
		obj.value = Trim(obj.value);
    });
	if (window.event == null) {
		return true;
	}
    var eventId = window.event.srcElement.id;
    if ($("#" + eventId + "Require").length > 0) {
        var requireObjects = $("#" + eventId + "Require").val().split(",");
        for (var i = 0; i < requireObjects.length; i++) {
            if ($("#" + requireObjects[i]).val() == "") {
                alertMessage("AM021");
                $("#" + requireObjects[i]).focus();
                return false;
            }
        }
    }
    return true;
}

/***************************************************************
 * 机能  二重送信防止。
 ***************************************************************/
var isSubmitted = false;
function restrictDoubleTransmit(ev) {
    // 必须输入验证
    if (!checkRequire()) {
        return false;
    }
    // 菜单或者头部页面提交的时候
//    if (top.topFlg != undefined) {
//        if (top.isSubmitted) {
//            cancelAlert(ev, "AM004");
//            return false;
//        }
//        top.isSubmitted = true;
//    } else {
        if (isSubmitted) {
            cancelAlert(ev, "AM004");
            return false;
        }
       
        isSubmitted = true;
        var distinguish=$("#distinguish").val();
        if(distinguish=="1")
        {
        	isSubmitted = false;
        }
//    }
    var action = $("form").attr("action");
    var naviId = $("#navigationId").val();
    // action里导航Id添加
    if (naviId != undefined && naviId != "") {
        if(action.indexOf("?") > 0) {
            action = action + "&naviId=" + naviId;
        } else {
            action = action + "?naviId=" + naviId;
        }
        $("form").attr("action", action);
    }

	waitShow();

    return true;
}

/***************************************************************
 * 机能  取得被修改的项目。
 ***************************************************************/
function getChangedObjectId() {
	var result = "";
    $(':text,:password,:file,textarea').each(function(flgl, obj) {
        if (obj.defaultValue != obj.value) {
        	if (result == "") {
        		result = obj.id;
        	} else {
        		result = result + ","+ obj.id;
        	}
        }
    });
    $(':checkbox,:radio').each(function(flgl, obj) {
        if (obj.defaultChecked != obj.checked) {
        	if (result == "") {
        		result = obj.id;
        	} else {
        		result = result + ","+ obj.id;
        	}
        }
    });
    $('select').each(function(flgl, obj) {
        for(var i = 0; i < obj.options.length; i++) {
        	if (obj.options[i].defaultSelected != obj.options[i].selected) {
            	if (result == "") {
            		result = obj.id;
            	} else {
            		result = result + ","+ obj.id;
            	}
        	}
        }
    });
    return result;
}

/***************************************************************
 * 机能  停止处理弹出提示消息。
 ***************************************************************/
function alertInProcessing(ev) {
    cancelAlert(ev, "AM004");
}
/***************************************************************
 * 机能  停止处理弹出提示消息。
 ***************************************************************/
function cancelAlert(ev, message) {
  //  stop(ev);
    alertMessage(message);
}

/***************************************************************
 * 机能 停止处理
 ***************************************************************/
function stop(ev) {
 /*   try {
      ev.keyCode = 0;
    } catch(e) {
    }
    ev.stopPropagation();
    ev.preventDefault();
    window.event.cancelBubble = true;
    window.event.keyCode = null;
    return false;
    */
}

var KeyCode = {
    BACKSPACE:8, TAB:9, SHIFT:16, CONTROL:17, ESC:27, SPACE:32, END:35, HOME:36,
    LEFT:37, UP:38, PAGE_UP:33, PAGE_DOWN:34, RIGHT:39, DOWN:40, INSERT:45, DELETE:46,
    A:65, B:66, C:67, D:68, E:69, F:70, G:71, H:72, I:73, J:74, K:75, L:76, M:77, N:78,
    O:79, P:80, Q:81, R:82, S:83, T:84, U:85, V:86, W:87, X:88, Y:89, Z:90, F1:112, F2:113,
    F3:114, F4:115, F5:116, F6:117, F7:118, F8:119, F9:120, F10:121, F11:122, F12:123
};
/***************************************************************
 * 机能  禁止特殊键
 ***************************************************************/
function restructKey(ev) {
    if (ev.metaKey == true) {
        switch (ev.which){
          case KeyCode.TAB:
          case KeyCode.B:
          case KeyCode.D:
          case KeyCode.E:
          case KeyCode.F:
          case KeyCode.H:
          case KeyCode.I:
          case KeyCode.L:
          case KeyCode.N:
          case KeyCode.O:
          case KeyCode.R:
          case KeyCode.S:
          case KeyCode.W:
          case KeyCode.F5:
          case KeyCode.F6:
          case KeyCode.F10:
            restrictKeyAlert(ev);
        }
    } else if (ev.altKey == true) {
        switch (ev.which) {
          case KeyCode.F4:
          case KeyCode.SPACE:
          case KeyCode.HOME:
          case KeyCode.LEFT:
          case KeyCode.RIGHT:
            restrictKeyAlert(ev);
        }
    } else if (ev.shiftKey == true) {
        switch (ev.which) {
          case KeyCode.F6:
          case KeyCode.F10:
            restrictKeyAlert(ev);
        }
    } else {
        switch (ev.which) {
          case KeyCode.BACKSPACE:
            if ((ev.target.type == "text" && !ev.target.readOnly)
             || (ev.target.type == "textarea" && !ev.target.readOnly)
             || (ev.target.type == "password" && !ev.target.readOnly)) {
                break;
            }
          case KeyCode.ESC:
          case KeyCode.F3:
          case KeyCode.F5:
          case KeyCode.F6:
          case KeyCode.F10:
          case KeyCode.F11:
            restrictKeyAlert(ev);
        }
    }
    return true;
}
/***************************************************************
 * 机能  禁止操作消息弹出
 ***************************************************************/
function restrictKeyAlert(ev) {
    cancelAlert(ev, "AM008");
}
/***************************************************************
 * 机能  禁止右键
 ***************************************************************/
function restrictRight(ev) {
    cancelAlert(ev, "AM007");
}

/***************************************************************
 * 机能  操作禁止
 ***************************************************************/
//$(document).keydown(alertInProcessing);
//$(document).mousedown(alertInProcessing);
$(document).keydown(restructKey);
//$(document).bind('contextmenu', restrictRight);

/***************************************************************
 * 机能  document（加载时执行）
 ***************************************************************/
$(document).ready(function() {
    // 键盘操作无效解除
    $(document).unbind('keydown', alertInProcessing);
    // 鼠标操作无效解除
    //$(document).unbind('mousedown', alertInProcessing);
});

$(document).unload(function() {
    $(document).unbind('contextmenu', restrictRight);
    $(document).unkeydown(restructKey);
});

/***************************************************************
 * 机能 取汉字首字母
 ***************************************************************/
var spell = {0xB0A1:"a", 0xB0A3:"ai", 0xB0B0:"an", 0xB0B9:"ang", 0xB0BC:"ao",
		0xB0C5:"ba", 0xB0D7:"bai", 0xB0DF:"ban", 0xB0EE:"bang", 0xB0FA:"bao", 
		0xB1AD:"bei", 0xB1BC:"ben", 0xB1C0:"beng", 0xB1C6:"bi", 0xB1DE:"bian", 
		0xB1EA:"biao", 0xB1EE:"bie", 0xB1F2:"bin", 0xB1F8:"bing", 0xB2A3:"bo", 
		0xB2B8:"bu", 0xB2C1:"ca", 0xB2C2:"cai", 0xB2CD:"can", 0xB2D4:"cang", 
		0xB2D9:"cao", 0xB2DE:"ce", 0xB2E3:"ceng", 0xB2E5:"cha", 0xB2F0:"chai", 
		0xB2F3:"chan", 0xB2FD:"chang", 0xB3AC:"chao", 0xB3B5:"che", 0xB3BB:"chen", 
		0xB3C5:"cheng", 0xB3D4:"chi", 0xB3E4:"chong", 0xB3E9:"chou", 0xB3F5:"chu", 
		0xB4A7:"chuai", 0xB4A8:"chuan", 0xB4AF:"chuang", 0xB4B5:"chui", 0xB4BA:"chun", 
		0xB4C1:"chuo", 0xB4C3:"ci", 0xB4CF:"cong", 0xB4D5:"cou", 0xB4D6:"cu", 
		0xB4DA:"cuan", 0xB4DD:"cui", 0xB4E5:"cun", 0xB4E8:"cuo", 0xB4EE:"da", 
		0xB4F4:"dai", 0xB5A2:"dan", 0xB5B1:"dang", 0xB5B6:"dao", 0xB5C2:"de",
		0xB5C5:"deng", 0xB5CC:"di", 0xB5DF:"dian", 0xB5EF:"diao", 0xB5F8:"die",
		0xB6A1:"ding", 0xB6AA:"diu", 0xB6AB:"dong", 0xB6B5:"dou", 0xB6BC:"du",
		0xB6CB:"duan", 0xB6D1:"dui", 0xB6D5:"dun", 0xB6DE:"duo", 0xB6EA:"e",
		0xB6F7:"en", 0xB6F8:"er", 0xB7A2:"fa", 0xB7AA:"fan", 0xB7BB:"fang", 
		0xB7C6:"fei", 0xB7D2:"fen", 0xB7E1:"feng", 0xB7F0:"fo", 0xB7F1:"fou",
		0xB7F2:"fu", 0xB8C1:"ga", 0xB8C3:"gai", 0xB8C9:"gan", 0xB8D4:"gang", 
		0xB8DD:"gao", 0xB8E7:"ge", 0xB8F8:"gei", 0xB8F9:"gen", 0xB8FB:"geng", 
		0xB9A4:"gong", 0xB9B3:"gou", 0xB9BC:"gu", 0xB9CE:"gua", 0xB9D4:"guai", 
		0xB9D7:"guan", 0xB9E2:"guang", 0xB9E5:"gui", 0xB9F5:"gun", 0xB9F8:"guo",
		0xB9FE:"ha", 0xBAA1:"hai", 0xBAA8:"han", 0xBABB:"hang", 0xBABE:"hao",
		0xBAC7:"he", 0xBAD9:"hei", 0xBADB:"hen", 0xBADF:"heng", 0xBAE4:"hong", 
		0xBAED:"hou", 0xBAF4:"hu", 0xBBA8:"hua", 0xBBB1:"huai", 0xBBB6:"huan",
		0xBBC4:"huang", 0xBBD2:"hui", 0xBBE7:"hun", 0xBBED:"huo", 0xBBF7:"ji",
		0xBCCE:"jia", 0xBCDF:"jian", 0xBDA9:"jiang", 0xBDB6:"jiao", 0xBDD2:"jie",
		0xBDED:"jin", 0xBEA3:"jing", 0xBEBC:"jiong", 0xBEBE:"jiu", 0xBECF:"ju",
		0xBEE8:"juan", 0xBEEF:"jue", 0xBEF9:"jun", 0xBFA6:"ka", 0xBFAA:"kai",
		0xBFAF:"kan", 0xBFB5:"kang", 0xBFBC:"kao", 0xBFC0:"ke", 0xBFCF:"ken",
		0xBFD3:"keng", 0xBFD5:"kong", 0xBFD9:"kou", 0xBFDD:"ku", 0xBFE4:"kua",
		0xBFE9:"kuai", 0xBFED:"kuan", 0xBFEF:"kuang", 0xBFF7:"kui", 0xC0A4:"kun",
		0xC0A8:"kuo", 0xC0AC:"la", 0xC0B3:"lai", 0xC0B6:"lan", 0xC0C5:"lang",
		0xC0CC:"lao", 0xC0D5:"le", 0xC0D7:"lei", 0xC0E2:"leng", 0xC0E5:"li",
		0xC1A9:"lia", 0xC1AA:"lian", 0xC1B8:"liang", 0xC1C3:"liao", 0xC1D0:"lie",
		0xC1D5:"lin", 0xC1E1:"ling", 0xC1EF:"liu", 0xC1FA:"long", 0xC2A5:"lou",
		0xC2AB:"lu", 0xC2BF:"lv", 0xC2CD:"luan", 0xC2D3:"lue", 0xC2D5:"lun",
		0xC2DC:"luo", 0xC2E8:"ma", 0xC2F1:"mai", 0xC2F7:"man", 0xC3A2:"mang",
		0xC3A8:"mao", 0xC3B4:"me", 0xC3B5:"mei", 0xC3C5:"men", 0xC3C8:"meng",
		0xC3D0:"mi", 0xC3DE:"mian", 0xC3E7:"miao", 0xC3EF:"mie", 0xC3F1:"min",
		0xC3F7:"ming", 0xC3FD:"miu", 0xC3FE:"mo", 0xC4B1:"mou", 0xC4B4:"mu",
		0xC4C3:"na", 0xC4CA:"nai", 0xC4CF:"nan", 0xC4D2:"nang", 0xC4D3:"nao",
		0xC4D8:"ne", 0xC4D9:"nei", 0xC4DB:"nen", 0xC4DC:"neng", 0xC4DD:"ni",
		0xC4E8:"nian", 0xC4EF:"niang", 0xC4F1:"niao", 0xC4F3:"nie", 0xC4FA:"nin",
		0xC4FB:"ning", 0xC5A3:"niu", 0xC5A7:"nong", 0xC5AB:"nu", 0xC5AE:"nv",
		0xC5AF:"nuan", 0xC5B0:"nue", 0xC5B2:"nuo", 0xC5B6:"o", 0xC5B7:"ou",
		0xC5BE:"pa", 0xC5C4:"pai", 0xC5CA:"pan", 0xC5D2:"pang", 0xC5D7:"pao",
		0xC5DE:"pei", 0xC5E7:"pen", 0xC5E9:"peng", 0xC5F7:"pi", 0xC6AA:"pian",
		0xC6AE:"piao", 0xC6B2:"pie", 0xC6B4:"pin", 0xC6B9:"ping", 0xC6C2:"po",
		0xC6CB:"pu", 0xC6DA:"qi", 0xC6FE:"qia", 0xC7A3:"qian", 0xC7B9:"qiang",
		0xC7C1:"qiao", 0xC7D0:"qie", 0xC7D5:"qin", 0xC7E0:"qing", 0xC7ED:"qiong",
		0xC7EF:"qiu", 0xC7F7:"qu", 0xC8A6:"quan", 0xC8B1:"que", 0xC8B9:"qun",
		0xC8BB:"ran", 0xC8BF:"rang", 0xC8C4:"rao", 0xC8C7:"re", 0xC8C9:"ren",
		0xC8D3:"reng", 0xC8D5:"ri", 0xC8D6:"rong", 0xC8E0:"rou", 0xC8E3:"ru",
		0xC8ED:"ruan", 0xC8EF:"rui", 0xC8F2:"run", 0xC8F4:"ruo", 0xC8F6:"sa",
		0xC8F9:"sai", 0xC8FD:"san", 0xC9A3:"sang", 0xC9A6:"sao", 0xC9AA:"se",
		0xC9AD:"sen", 0xC9AE:"seng", 0xC9AF:"sha", 0xC9B8:"shai", 0xC9BA:"shan",
		0xC9CA:"shang", 0xC9D2:"shao", 0xC9DD:"she", 0xC9E9:"shen", 0xC9F9:"sheng",
		0xCAA6:"shi", 0xCAD5:"shou", 0xCADF:"shu", 0xCBA2:"shua", 0xCBA4:"shuai",
		0xCBA8:"shuan", 0xCBAA:"shuang", 0xCBAD:"shui", 0xCBB1:"shun", 0xCBB5:"shuo",
		0xCBB9:"si", 0xCBC9:"song", 0xCBD1:"sou", 0xCBD4:"su", 0xCBE1:"suan",
		0xCBE4:"sui", 0xCBEF:"sun", 0xCBF2:"suo", 0xCBFA:"ta", 0xCCA5:"tai",
		0xCCAE:"tan", 0xCCC0:"tang", 0xCCCD:"tao", 0xCCD8:"te", 0xCCD9:"teng",
		0xCCDD:"ti", 0xCCEC:"tian", 0xCCF4:"tiao", 0xCCF9:"tie", 0xCCFC:"ting",
		0xCDA8:"tong", 0xCDB5:"tou", 0xCDB9:"tu", 0xCDC4:"tuan", 0xCDC6:"tui",
		0xCDCC:"tun", 0xCDCF:"tuo", 0xCDDA:"wa", 0xCDE1:"wai", 0xCDE3:"wan",
		0xCDF4:"wang", 0xCDFE:"wei", 0xCEC1:"wen", 0xCECB:"weng", 0xCECE:"wo",
		0xCED7:"wu", 0xCEF4:"xi", 0xCFB9:"xia", 0xCFC6:"xian", 0xCFE0:"xiang",
		0xCFF4:"xiao", 0xD0A8:"xie", 0xD0BD:"xin", 0xD0C7:"xing", 0xD0D6:"xiong",
		0xD0DD:"xiu", 0xD0E6:"xu", 0xD0F9:"xuan", 0xD1A5:"xue", 0xD1AB:"xun", 
		0xD1B9:"ya", 0xD1C9:"yan", 0xD1EA:"yang", 0xD1FB:"yao", 0xD2AC:"ye",
		0xD2BB:"yi", 0xD2F0:"yin", 0xD3A2:"ying", 0xD3B4:"yo", 0xD3B5:"yong",
		0xD3C4:"you", 0xD3D9:"yu", 0xD4A7:"yuan", 0xD4BB:"yue", 0xD4C5:"yun",
		0xD4D1:"za", 0xD4D4:"zai", 0xD4DB:"zan", 0xD4DF:"zang", 0xD4E2:"zao",
		0xD4F0:"ze", 0xD4F4:"zei", 0xD4F5:"zen", 0xD4F6:"zeng", 0xD4FA:"zha",
		0xD5AA:"zhai", 0xD5B0:"zhan", 0xD5C1:"zhang", 0xD5D0:"zhao", 0xD5DA:"zhe",
		0xD5E4:"zhen", 0xD5F4:"zheng", 0xD6A5:"zhi", 0xD6D0:"zhong", 0xD6DB:"zhou",
		0xD6E9:"zhu", 0xD7A5:"zhua", 0xD7A7:"zhuai", 0xD7A8:"zhuan", 0xD7AE:"zhuang",
		0xD7B5:"zhui", 0xD7BB:"zhun", 0xD7BD:"zhuo", 0xD7C8:"zi", 0xD7D7:"zong",
		0xD7DE:"zou", 0xD7E2:"zu", 0xD7EA:"zuan", 0xD7EC:"zui", 0xD7F0:"zun", 0xD7F2:"zuo"};
var spellArray = new Array();
var pn = "";

function Trim(info)
{
	return info.replace(/(^\s*)|(\s*$)/g, "");
}

function isEnKong1(argValue)
{
    var flag=false;
    var compStr="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    //数字
    compStr+="1234567890";
    //特殊字符
    compStr+=" ";
    var length=argValue.length;
	for (var iIndex=0;iIndex<length;iIndex++)
	{
	    var temp=compStr.indexOf(argValue.charAt(iIndex));
	    if(temp==-1) 
	    {
	     flag=false;
	    }
	    else
	    {
	     flag=true;
	    }
	 }
	return flag;
}

function pinyin(char)
{
	if (!char.charCodeAt(0) ||char.charCodeAt(0) < 1328) 
	{
		return char;
	}
	
	if (spellArray[char.charCodeAt(0)])
	{
		return spellArray[char.charCodeAt(0)];
	}
	ascCode=toAsc(char);
	ascCode = eval("0x"+ascCode);
	
	if (!(ascCode>0xB0A0 && ascCode<0xD7FC))
	{
		return char;	
	}
	
	for (var i=ascCode; (!spell[i] && i>0);)
	{
		i--;
	}
	//这个是获得汉字首字母，大写
	return spell[i].substring(0,1).toUpperCase();	
}

//修改的方法
function pinyin_fan(char)
{
	if (!char.charCodeAt(0) ||char.charCodeAt(0) < 1328) 
	{
		return '';
	}

	if (spellArray[char.charCodeAt(0)])
	{
		return spellArray[char.charCodeAt(0)];
	}
	   
	ascCode=toAsc(char);
	ascCode = eval("0x"+ascCode);
	if (!(ascCode>0xB0A0 && ascCode<0xD7FC))
	{
		return '';
	}

	for (var i=ascCode; (!spell[i] && i>0);)
	{
		i--;
	}
	return spell[i];
}
// 转换成拼音
function toPinyin(str)
{
    if (str){
	   var pStr = "";
	   for (var i=0; i<str.length; i++)
	   {
		  if (isEnKong1(str.charAt(i))) {
		     //除汉字外，其他值，如果是小写字母变成大写输出
		     pStr += str.charAt(i).toUpperCase();
		  }
		  else{
		   pStr += "" + pinyin(str.charAt(i));
		  }
	   }
	   return Trim(pStr);
    }
}
//检查日期大小(开始时间，结束时间，错误信息)
function checkStartDateToEndDate(date1,date2,alertMsg) {
    // 开始日期
    var s_date_arys = $("#" + date1).val().split('-');
    var s_date = new Date(s_date_arys[0], s_date_arys[1], s_date_arys[2]);
    // 结束日期
    var e_date_arys = $("#" + date2).val().split('-');
    var e_date = new Date(e_date_arys[0], e_date_arys[1], e_date_arys[2]);
    // 如果开始日期大于结束日期
    if(s_date > e_date) {
        // 提示错误信息
    	var callBackFun = function() {$("#" + date2).focus();};
        alertMessage(alertMsg, callBackFun);
    }
}
/****************************************************************
 * 方法名：openUserSelectWindow
 * 概 要：打开用户选择页面
 * 参 数：无
 * 返回值：无
 ***************************************************************/
function openUserSelectWindow(param) {
	if(param == "1"){
		openChildWindow("rlgl090408Init.action", "", callBackFun_Select, "1");
	}else{
		openChildWindow("rlgl090408Init.action", param, callBackFun_userSelect, "1");
	}
}

String.prototype.replaceAll = function(s1,s2) { 
    return this.replace(new RegExp(s1,"gm"),s2); 
	 };