package com.rlglsys.action.rlgl.main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.rlglsys.action.rlgl.main.datadetect.DrawBarChart;
import com.rlglsys.action.rlgl.main.datadetect.DrawBarChartForAllStudy;
import com.rlglsys.action.rlgl.main.datadetect.DrawBarChartForPersonnel;
import com.rlglsys.action.rlgl.main.datadetect.DrawBarChartForStudy;
import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Mtb07UnitapplyBean;
import com.rlglsys.bean.Mtb29PersonalApplyBean;
import com.rlglsys.bean.Mtb56CreditReportingDetailBean;
import com.rlglsys.bean.Rlgl000203Bean;
import com.rlglsys.bean.Rlgl001080Bean;
import com.rlglsys.bean.Rlgl011001Bean;
import com.rlglsys.bean.Rlgl400102Bean;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb03Menu;
import com.rlglsys.entity.Mtb04Unit;
import com.rlglsys.entity.Mtb111Admscore;
import com.rlglsys.entity.Mtb72Userrole;
import com.rlglsys.entity.Mtb73Roleaction;
import com.rlglsys.entity.Mtb83NoticeTo;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IMenuService;
import com.rlglsys.service.IRlgl000203Service;
import com.rlglsys.service.IRlgl011001Service;
import com.rlglsys.service.IRlgl020603Service;
import com.rlglsys.service.IRlgl022004Service;
import com.rlglsys.service.IRlgl050101Service;
import com.rlglsys.util.Common;
import com.rlglsys.util.CommonManager;
import com.rlglsys.util.Constant;
import com.rlglsys.util.DateTimeManager;

public class Rlgl000203InitAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	// 用户对象
	private Mtb01User user;
	private String userFlg;
	// rlgl000203Service
	private IRlgl000203Service rlgl000203Service;
	private List<Rlgl000203Bean> noticeInfoList;
	private String msgId;
	private Rlgl000203Bean rlgl000203Bean;
	private IMenuService menuService;
	private IRlgl020603Service rlgl020603Service;
	private List<String> naviIdList;
	// 单位信息处理service
	private IRlgl011001Service rlgl011001Service;
	private String imgPathBarChart = "";
	private String imgPathBarStudy = "";
	// 学分年度
	private String credit_year;
	// 课程名称
	private String course_name;
	// 身份证号码
	private String userId;

	private String playFlag;
	private IRlgl050101Service rlgl050101Service;
	private IMTb02AdmService mtb02AdmService;
	private IRlgl022004Service rlgl022004service;
	private Rlgl400102Bean rlgl400102bean;

	public String getCredit_year() {
		return credit_year;
	}

	public void setCredit_year(String credit_year) {
		this.credit_year = credit_year;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPlayFlag() {
		return playFlag;
	}

	public void setPlayFlag(String playFlag) {
		this.playFlag = playFlag;
	}

	public IRlgl050101Service getRlgl050101Service() {
		return rlgl050101Service;
	}

	public void setRlgl050101Service(IRlgl050101Service rlgl050101Service) {
		this.rlgl050101Service = rlgl050101Service;
	}

	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}

	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
	}

	public IRlgl022004Service getRlgl022004service() {
		return rlgl022004service;
	}

	public void setRlgl022004service(IRlgl022004Service rlgl022004service) {
		this.rlgl022004service = rlgl022004service;
	}

	public Rlgl400102Bean getRlgl400102bean() {
		return rlgl400102bean;
	}

	public void setRlgl400102bean(Rlgl400102Bean rlgl400102bean) {
		this.rlgl400102bean = rlgl400102bean;
	}

	@Override
	public String doExecute() throws Exception {
		try {
			if (rlgl000203Bean == null) {
				rlgl000203Bean = new Rlgl000203Bean();
			}
			user = (Mtb01User) super.getSession(Constant.SESSION_KEY_LOGINUSER);
			Mtb04Unit unitInfo = (Mtb04Unit) super.getSession("UnitInfo");

			if ("1".equals(user.getUser_type()) && "1".equals(unitInfo.getUnit_status())
					&& ("".equals(unitInfo.getContact()) || unitInfo.getContact() == null)) {
				msgId = "AM056";
			}
			get() ;
			// 公告信息对象
			Mtb83NoticeTo noticeInfo = new Mtb83NoticeTo();
			// Update yangq 20141106 start
			// 单位用户
			// if ("1".equals(user.getUser_type()))
			// {
			// noticeInfo.setNotice_to(user.getUnit_no());
			// noticeInfo.setUser_enter(user.getUser_enter());
			// } else {
			// // 个人用户
			// noticeInfo.setNotice_to(user.getUser_id());
			// }
			// 单位用户登录时
			if ("1".equals(user.getUser_type())) {
				// 单位用户ＩＤ
				noticeInfo.setNotice_to(user.getUnit_no());
				// 单位公告
				noticeInfo.setExpend_01("001");
			} else {
				// 个人登录时，个人所在单位ＩＤ
				noticeInfo.setNotice_to(user.getUnit_no());
				// 人员公告
				noticeInfo.setExpend_01("002");
			}
			//
			noticeInfo.setNotice_to(user.getUnit_no());
			// Update yangq 20141106 end
			// 公告件数
			noticeInfoList = rlgl000203Service.getNoticeInfoList(noticeInfo);
			if (noticeInfoList != null && noticeInfoList.size() > 0) {
				rlgl000203Bean.setNoticeCount(noticeInfoList.size());
			}
			Mtb07UnitapplyBean applyInfo = new Mtb07UnitapplyBean();
			Mtb29PersonalApplyBean mtb29PersonalApplyBean = new Mtb29PersonalApplyBean();
			Mtb56CreditReportingDetailBean mtb56CreditReportingDetailBean = new Mtb56CreditReportingDetailBean();

			// 获得当前年度
			String kbn = rlgl020603Service.getCreditYear(user.getUnit_no());
			int year = 0;
			if (kbn != null && !"".equals(kbn)) {
				year = getCreditYear(kbn);
			}
			// 取得菜单信息
			this.getMenuInfoList(unitInfo);

			if ("1".equals(user.getUser_type())) {
				// 单位信息审核数据查询
				applyInfo.setWill_checkunitno(user.getUnit_no());
				applyInfo.setCheck_result("001");
				int mtb07UnitapplyCount = rlgl000203Service.getMtb07UnitapplyInfoCount(applyInfo);
				// 单位信息审核数据查询件数
				if ("1".equals(rlgl000203Bean.getMtb07UnitapplyMenuFlg())) {
					rlgl000203Bean.setMtb07UnitapplyCount(mtb07UnitapplyCount);
				}

				// 个人信息审核数据查询
				mtb29PersonalApplyBean.setWill_checkunitno(user.getUnit_no());
				mtb29PersonalApplyBean.setCheck_result("001");
				// 人事单位用户
				if ("A".equals(user.getUser_enter())) {
					mtb29PersonalApplyBean.setUser_enter("1");
				}
				// 医政单位用户
				if ("B".equals(user.getUser_enter())) {
					mtb29PersonalApplyBean.setUser_enter("2");
				}
				// 科教单位用户
				if ("C".equals(user.getUser_enter())) {
					mtb29PersonalApplyBean.setUser_enter("3");
				}
				int mtb29PersonalApplyCount = rlgl000203Service
						.getMtb29PersonalapplicationInfoCount(mtb29PersonalApplyBean);

				// 个人信息审核数据查询件数
				if ("1".equals(rlgl000203Bean.getMtb29PersonalApplyMenuFlg())) {
					rlgl000203Bean.setMtb29PersonalApplyCount(mtb29PersonalApplyCount);
				}

				// 三名审核信息检索
				mtb29PersonalApplyBean = new Mtb29PersonalApplyBean();
				mtb29PersonalApplyBean.setWill_checkunitno(user.getUnit_no());
				mtb29PersonalApplyBean.setCheck_result("001");
				mtb29PersonalApplyBean.setThreeApply_flg("1");

				int mtb29PersonalapplyThreeApplyCount = rlgl000203Service
						.getMtb29PersonalapplicationInfoCount(mtb29PersonalApplyBean);

				// 三名审核信息检索件数
				if ("1".equals(rlgl000203Bean.getMtb29PersonalapplyThreeApplyMenuFlg())) {
					rlgl000203Bean.setMtb29PersonalapplyThreeApplyCount(mtb29PersonalapplyThreeApplyCount);
				}

				if (year != 0) {
					// 学分审核
					mtb56CreditReportingDetailBean.setYear(year);
					mtb56CreditReportingDetailBean.setWill_checkunitno(user.getUnit_no());
					this.setNaviIdList();
					mtb56CreditReportingDetailBean.setNaviIdList(naviIdList);
					mtb56CreditReportingDetailBean.setCheck_result("001");

					int mtb56CreditReportCount = rlgl000203Service
							.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
					if ("1".equals(rlgl000203Bean.getMtb56CreditReportMenuFlg())) {
						rlgl000203Bean.setMtb56CreditReportCount(mtb56CreditReportCount);
					}
				}

				// 单位待办总件数设定
				rlgl000203Bean.setTodoTotalCount(rlgl000203Bean.getMtb07UnitapplyCount()
						+ rlgl000203Bean.getMtb29PersonalApplyCount() + rlgl000203Bean.getMtb29PersonalApplyCount()
						+ rlgl000203Bean.getMtb56CreditReportCount());

				List<Rlgl001080Bean> rlgl011001List = this.getRlgl011001List();

				DrawBarChart drawBarChart = new DrawBarChart(rlgl011001List);

				imgPathBarChart = drawBarChart.draw(ServletActionContext.getRequest().getSession(),
						ServletActionContext.getRequest().getContextPath(), user.getUnit_no());

				List<Rlgl001080Bean> rlgl011001AllStudyList = this.getRlgl001080ListForUnit();

				DrawBarChartForAllStudy drawBarStudy = new DrawBarChartForAllStudy(rlgl011001AllStudyList);
				if (user.getUnit_no().length() > 3 && user.getUnit_no().substring(0, 4).equals("3711")
						&& user.getUser_id().contains("C")) {
					imgPathBarStudy = drawBarStudy.draw(ServletActionContext.getRequest().getSession(),
							ServletActionContext.getRequest().getContextPath(), user.getUser_id());

					userFlg = "1";
				}
			} else {
				// 个人申请审核中件数检索
				mtb29PersonalApplyBean = new Mtb29PersonalApplyBean();
				mtb29PersonalApplyBean.setWill_checkunitno(user.getUnit_no());
				mtb29PersonalApplyBean.setCheck_result("001");
				mtb29PersonalApplyBean.setPersonal_id(user.getPersonnel_id());
				int personalReviewInfoCount = rlgl000203Service.getMtb29PersonalReviewInfoCount(mtb29PersonalApplyBean);
				rlgl000203Bean.setPersonalReviewInfoCount(personalReviewInfoCount);

				// 个人申请驳回件数检索
				mtb29PersonalApplyBean = new Mtb29PersonalApplyBean();
				mtb29PersonalApplyBean.setWill_checkunitno(user.getUnit_no());
				mtb29PersonalApplyBean.setCheck_result("003");
				mtb29PersonalApplyBean.setPersonal_id(user.getPersonnel_id());
				int personalRejectInfoCount = rlgl000203Service.getMtb29PersonalRejectInfoCount(mtb29PersonalApplyBean);
				rlgl000203Bean.setPersonalRejectInfoCount(personalRejectInfoCount);

				// 总件数
				if ("1".equals(rlgl000203Bean.getPersonalInfoMenuFlg())) {
					rlgl000203Bean.setPersonalInfoCount(personalReviewInfoCount + personalRejectInfoCount);
				}

				// 学分审核
				mtb56CreditReportingDetailBean = new Mtb56CreditReportingDetailBean();
				mtb56CreditReportingDetailBean.setYear(year);
				mtb56CreditReportingDetailBean.setWill_checkunitno(user.getUnit_no());
				// 山东卫生
				naviIdList = new ArrayList<String>();
				naviIdList.add("navi052");
				mtb56CreditReportingDetailBean.setNaviIdList(naviIdList);
				mtb56CreditReportingDetailBean.setPersonal_id(user.getPersonnel_id());
				mtb56CreditReportingDetailBean.setCheck_result("001");
				// 审核中
				int mtb56CreditReportnavi052ReviewCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi052ReviewCount(mtb56CreditReportnavi052ReviewCount);
				// 驳回
				mtb56CreditReportingDetailBean.setCheck_result("003");
				int mtb56CreditReportnavi052RejectCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi052RejectCount(mtb56CreditReportnavi052RejectCount);

				if ("1".equals(rlgl000203Bean.getMtb56CreditReportnavi052MenuFlg())) {
					rlgl000203Bean.setMtb56CreditReportnavi052Count(
							mtb56CreditReportnavi052ReviewCount + mtb56CreditReportnavi052RejectCount);
				}

				// 市级继续教育培训
				naviIdList = new ArrayList<String>();
				naviIdList.add("navi053");
				mtb56CreditReportingDetailBean.setNaviIdList(naviIdList);
				mtb56CreditReportingDetailBean.setCheck_result("001");
				// 审核中
				int mtb56CreditReportnavi053ReviewCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi053ReviewCount(mtb56CreditReportnavi053ReviewCount);
				// 驳回
				mtb56CreditReportingDetailBean.setCheck_result("003");
				int mtb56CreditReportnavi053RejectCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi053RejectCount(mtb56CreditReportnavi053RejectCount);

				if ("1".equals(rlgl000203Bean.getMtb56CreditReportnavi053MenuFlg())) {
					rlgl000203Bean.setMtb56CreditReportnavi053Count(
							mtb56CreditReportnavi053ReviewCount + mtb56CreditReportnavi053RejectCount);
				}

				// 省/外地继教项目|学术会议
				naviIdList = new ArrayList<String>();
				naviIdList.add("navi054");
				mtb56CreditReportingDetailBean.setNaviIdList(naviIdList);
				mtb56CreditReportingDetailBean.setCheck_result("001");
				// 审核中
				int mtb56CreditReportnavi054ReviewCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi054ReviewCount(mtb56CreditReportnavi054ReviewCount);
				// 驳回
				mtb56CreditReportingDetailBean.setCheck_result("003");
				int mtb56CreditReportnavi054RejectCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi054RejectCount(mtb56CreditReportnavi054RejectCount);

				if ("1".equals(rlgl000203Bean.getMtb56CreditReportnavi054MenuFlg())) {
					rlgl000203Bean.setMtb56CreditReportnavi054Count(
							mtb56CreditReportnavi054ReviewCount + mtb56CreditReportnavi054RejectCount);
				}

				// 公共课程考试
				naviIdList = new ArrayList<String>();
				naviIdList.add("navi055");
				mtb56CreditReportingDetailBean.setNaviIdList(naviIdList);
				mtb56CreditReportingDetailBean.setCheck_result("001");
				// 审核中
				int mtb56CreditReportnavi055ReviewCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi055ReviewCount(mtb56CreditReportnavi055ReviewCount);
				// 驳回
				mtb56CreditReportingDetailBean.setCheck_result("003");
				int mtb56CreditReportnavi055RejectCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi055RejectCount(mtb56CreditReportnavi055RejectCount);

				if ("1".equals(rlgl000203Bean.getMtb56CreditReportnavi055MenuFlg())) {
					rlgl000203Bean.setMtb56CreditReportnavi055Count(
							mtb56CreditReportnavi055ReviewCount + mtb56CreditReportnavi055RejectCount);
				}

				// 著作信息
				naviIdList = new ArrayList<String>();
				naviIdList.add("navi056");
				mtb56CreditReportingDetailBean.setNaviIdList(naviIdList);
				mtb56CreditReportingDetailBean.setCheck_result("001");
				// 审核中
				int mtb56CreditReportnavi056ReviewCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi056ReviewCount(mtb56CreditReportnavi056ReviewCount);
				// 驳回
				mtb56CreditReportingDetailBean.setCheck_result("003");
				int mtb56CreditReportnavi056RejectCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi056RejectCount(mtb56CreditReportnavi056RejectCount);

				if ("1".equals(rlgl000203Bean.getMtb56CreditReportnavi056MenuFlg())) {
					rlgl000203Bean.setMtb56CreditReportnavi056Count(
							mtb56CreditReportnavi056ReviewCount + mtb56CreditReportnavi056RejectCount);
				}

				// 译作信息
				naviIdList = new ArrayList<String>();
				naviIdList.add("navi066");
				mtb56CreditReportingDetailBean.setNaviIdList(naviIdList);
				mtb56CreditReportingDetailBean.setCheck_result("001");
				// 审核中
				int mtb56CreditReportnavi066ReviewCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi066ReviewCount(mtb56CreditReportnavi066ReviewCount);
				// 驳回
				mtb56CreditReportingDetailBean.setCheck_result("003");
				int mtb56CreditReportnavi066RejectCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi066RejectCount(mtb56CreditReportnavi066RejectCount);

				if ("1".equals(rlgl000203Bean.getMtb56CreditReportnavi066MenuFlg())) {
					rlgl000203Bean.setMtb56CreditReportnavi066Count(
							mtb56CreditReportnavi066ReviewCount + mtb56CreditReportnavi066RejectCount);
				}

				// 考察报告/专题报告
				naviIdList = new ArrayList<String>();
				naviIdList.add("navi067");
				mtb56CreditReportingDetailBean.setNaviIdList(naviIdList);
				mtb56CreditReportingDetailBean.setCheck_result("001");
				// 审核中
				int mtb56CreditReportnavi067ReviewCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi067ReviewCount(mtb56CreditReportnavi067ReviewCount);
				// 驳回
				mtb56CreditReportingDetailBean.setCheck_result("003");
				int mtb56CreditReportnavi067RejectCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi067RejectCount(mtb56CreditReportnavi067RejectCount);

				if ("1".equals(rlgl000203Bean.getMtb56CreditReportnavi067MenuFlg())) {
					rlgl000203Bean.setMtb56CreditReportnavi067Count(
							mtb56CreditReportnavi067ReviewCount + mtb56CreditReportnavi067RejectCount);
				}

				// 科研成果信息
				naviIdList = new ArrayList<String>();
				naviIdList.add("navi068");
				mtb56CreditReportingDetailBean.setNaviIdList(naviIdList);
				mtb56CreditReportingDetailBean.setCheck_result("001");
				// 审核中
				int mtb56CreditReportnavi068ReviewCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi068ReviewCount(mtb56CreditReportnavi068ReviewCount);
				// 驳回
				mtb56CreditReportingDetailBean.setCheck_result("003");
				int mtb56CreditReportnavi068RejectCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi068RejectCount(mtb56CreditReportnavi068RejectCount);

				if ("1".equals(rlgl000203Bean.getMtb56CreditReportnavi068MenuFlg())) {
					rlgl000203Bean.setMtb56CreditReportnavi068Count(
							mtb56CreditReportnavi068ReviewCount + mtb56CreditReportnavi068RejectCount);
				}

				// 论文综述信息
				naviIdList = new ArrayList<String>();
				naviIdList.add("navi069");
				mtb56CreditReportingDetailBean.setNaviIdList(naviIdList);
				mtb56CreditReportingDetailBean.setCheck_result("001");
				// 审核中
				int mtb56CreditReportnavi069ReviewCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi069ReviewCount(mtb56CreditReportnavi069ReviewCount);
				// 驳回
				mtb56CreditReportingDetailBean.setCheck_result("003");
				int mtb56CreditReportnavi069RejectCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi069RejectCount(mtb56CreditReportnavi069RejectCount);

				if ("1".equals(rlgl000203Bean.getMtb56CreditReportnavi069MenuFlg())) {
					rlgl000203Bean.setMtb56CreditReportnavi069Count(
							mtb56CreditReportnavi069ReviewCount + mtb56CreditReportnavi069RejectCount);
				}

				// 科研立项
				naviIdList = new ArrayList<String>();
				naviIdList.add("navi070");
				mtb56CreditReportingDetailBean.setNaviIdList(naviIdList);
				mtb56CreditReportingDetailBean.setCheck_result("001");
				// 审核中
				int mtb56CreditReportnavi070ReviewCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi070ReviewCount(mtb56CreditReportnavi070ReviewCount);
				// 驳回
				mtb56CreditReportingDetailBean.setCheck_result("003");
				int mtb56CreditReportnavi070RejectCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi070RejectCount(mtb56CreditReportnavi070RejectCount);

				if ("1".equals(rlgl000203Bean.getMtb56CreditReportnavi070MenuFlg())) {
					rlgl000203Bean.setMtb56CreditReportnavi070Count(
							mtb56CreditReportnavi070ReviewCount + mtb56CreditReportnavi070RejectCount);
				}

				// 院内学分
				naviIdList = new ArrayList<String>();
				naviIdList.add("navi071");
				mtb56CreditReportingDetailBean.setNaviIdList(naviIdList);
				mtb56CreditReportingDetailBean.setCheck_result("001");
				// 审核中
				int mtb56CreditReportnavi071ReviewCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi071ReviewCount(mtb56CreditReportnavi071ReviewCount);
				// 驳回
				mtb56CreditReportingDetailBean.setCheck_result("003");
				int mtb56CreditReportnavi071RejectCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi071RejectCount(mtb56CreditReportnavi071RejectCount);

				if ("1".equals(rlgl000203Bean.getMtb56CreditReportnavi071MenuFlg())) {
					rlgl000203Bean.setMtb56CreditReportnavi071Count(
							mtb56CreditReportnavi071ReviewCount + mtb56CreditReportnavi071RejectCount);
				}

				// 学历（学位）教育
				naviIdList = new ArrayList<String>();
				naviIdList.add("navi072");
				mtb56CreditReportingDetailBean.setNaviIdList(naviIdList);
				mtb56CreditReportingDetailBean.setCheck_result("001");
				// 审核中
				int mtb56CreditReportnavi072ReviewCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi072ReviewCount(mtb56CreditReportnavi072ReviewCount);
				// 驳回
				mtb56CreditReportingDetailBean.setCheck_result("003");
				int mtb56CreditReportnavi072RejectCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi072RejectCount(mtb56CreditReportnavi072RejectCount);

				if ("1".equals(rlgl000203Bean.getMtb56CreditReportnavi072MenuFlg())) {
					rlgl000203Bean.setMtb56CreditReportnavi072Count(
							mtb56CreditReportnavi072ReviewCount + mtb56CreditReportnavi072RejectCount);
				}

				// 外出进修
				naviIdList = new ArrayList<String>();
				naviIdList.add("navi073");
				mtb56CreditReportingDetailBean.setNaviIdList(naviIdList);
				mtb56CreditReportingDetailBean.setCheck_result("001");
				// 审核中
				int mtb56CreditReportnavi073ReviewCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi073ReviewCount(mtb56CreditReportnavi073ReviewCount);
				// 驳回
				mtb56CreditReportingDetailBean.setCheck_result("003");
				int mtb56CreditReportnavi073RejectCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi073RejectCount(mtb56CreditReportnavi073RejectCount);

				if ("1".equals(rlgl000203Bean.getMtb56CreditReportnavi073MenuFlg())) {
					rlgl000203Bean.setMtb56CreditReportnavi073Count(
							mtb56CreditReportnavi073ReviewCount + mtb56CreditReportnavi073RejectCount);
				}

				// 自学考试
				naviIdList = new ArrayList<String>();
				naviIdList.add("navi074");
				mtb56CreditReportingDetailBean.setNaviIdList(naviIdList);
				mtb56CreditReportingDetailBean.setCheck_result("001");
				// 审核中
				int mtb56CreditReportnavi074ReviewCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi074ReviewCount(mtb56CreditReportnavi074ReviewCount);
				// 驳回
				mtb56CreditReportingDetailBean.setCheck_result("003");
				int mtb56CreditReportnavi074RejectCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi074RejectCount(mtb56CreditReportnavi074RejectCount);

				if ("1".equals(rlgl000203Bean.getMtb56CreditReportnavi074MenuFlg())) {
					rlgl000203Bean.setMtb56CreditReportnavi074Count(
							mtb56CreditReportnavi074ReviewCount + mtb56CreditReportnavi074RejectCount);
				}

				// 住院医师规范化培训
				naviIdList = new ArrayList<String>();
				naviIdList.add("navi075");
				mtb56CreditReportingDetailBean.setNaviIdList(naviIdList);
				mtb56CreditReportingDetailBean.setCheck_result("001");
				// 审核中
				int mtb56CreditReportnavi075ReviewCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi075ReviewCount(mtb56CreditReportnavi075ReviewCount);
				// 驳回
				mtb56CreditReportingDetailBean.setCheck_result("003");
				int mtb56CreditReportnavi075RejectCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi075RejectCount(mtb56CreditReportnavi075RejectCount);

				if ("1".equals(rlgl000203Bean.getMtb56CreditReportnavi075MenuFlg())) {
					rlgl000203Bean.setMtb56CreditReportnavi075Count(
							mtb56CreditReportnavi075ReviewCount + mtb56CreditReportnavi075RejectCount);
				}

				// 全科医师及社区骨干培训
				naviIdList = new ArrayList<String>();
				naviIdList.add("navi076");
				mtb56CreditReportingDetailBean.setNaviIdList(naviIdList);
				mtb56CreditReportingDetailBean.setCheck_result("001");
				// 审核中
				int mtb56CreditReportnavi076ReviewCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi076ReviewCount(mtb56CreditReportnavi076ReviewCount);
				// 驳回
				mtb56CreditReportingDetailBean.setCheck_result("003");
				int mtb56CreditReportnavi076RejectCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi076RejectCount(mtb56CreditReportnavi076RejectCount);

				if ("1".equals(rlgl000203Bean.getMtb56CreditReportnavi076MenuFlg())) {
					rlgl000203Bean.setMtb56CreditReportnavi076Count(
							mtb56CreditReportnavi076ReviewCount + mtb56CreditReportnavi076RejectCount);
				}

				// 援外与支农
				naviIdList = new ArrayList<String>();
				naviIdList.add("navi077");
				mtb56CreditReportingDetailBean.setNaviIdList(naviIdList);
				mtb56CreditReportingDetailBean.setCheck_result("001");
				// 审核中
				int mtb56CreditReportnavi077ReviewCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi077ReviewCount(mtb56CreditReportnavi077ReviewCount);
				// 驳回
				mtb56CreditReportingDetailBean.setCheck_result("003");
				int mtb56CreditReportnavi077RejectCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi077RejectCount(mtb56CreditReportnavi077RejectCount);

				if ("1".equals(rlgl000203Bean.getMtb56CreditReportnavi077MenuFlg())) {
					rlgl000203Bean.setMtb56CreditReportnavi077Count(
							mtb56CreditReportnavi077ReviewCount + mtb56CreditReportnavi077RejectCount);
				}

				// 专利信息
				naviIdList = new ArrayList<String>();
				naviIdList.add("navi078");
				mtb56CreditReportingDetailBean.setNaviIdList(naviIdList);
				mtb56CreditReportingDetailBean.setCheck_result("001");
				// 审核中
				int mtb56CreditReportnavi078ReviewCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi078ReviewCount(mtb56CreditReportnavi078ReviewCount);
				// 驳回
				mtb56CreditReportingDetailBean.setCheck_result("003");
				int mtb56CreditReportnavi078RejectCount = rlgl000203Service
						.searchMtb56creditreportInfoCount(mtb56CreditReportingDetailBean);
				rlgl000203Bean.setMtb56CreditReportnavi078RejectCount(mtb56CreditReportnavi078RejectCount);

				if ("1".equals(rlgl000203Bean.getMtb56CreditReportnavi078MenuFlg())) {
					rlgl000203Bean.setMtb56CreditReportnavi078Count(
							mtb56CreditReportnavi078ReviewCount + mtb56CreditReportnavi078RejectCount);
				}

				// 个人待办总件数
				rlgl000203Bean.setTodoTotalCount(
						rlgl000203Bean.getPersonalInfoCount() + rlgl000203Bean.getMtb56CreditReportnavi052Count()
								+ rlgl000203Bean.getMtb56CreditReportnavi052Count()
								+ rlgl000203Bean.getMtb56CreditReportnavi053Count()
								+ rlgl000203Bean.getMtb56CreditReportnavi054Count()
								+ rlgl000203Bean.getMtb56CreditReportnavi055Count()
								+ rlgl000203Bean.getMtb56CreditReportnavi056Count()
								+ rlgl000203Bean.getMtb56CreditReportnavi066Count()
								+ rlgl000203Bean.getMtb56CreditReportnavi067Count()
								+ rlgl000203Bean.getMtb56CreditReportnavi068Count()
								+ rlgl000203Bean.getMtb56CreditReportnavi069Count()
								+ rlgl000203Bean.getMtb56CreditReportnavi070Count()
								+ rlgl000203Bean.getMtb56CreditReportnavi071Count()
								+ rlgl000203Bean.getMtb56CreditReportnavi072Count()
								+ rlgl000203Bean.getMtb56CreditReportnavi073Count()
								+ rlgl000203Bean.getMtb56CreditReportnavi074Count()
								+ rlgl000203Bean.getMtb56CreditReportnavi075Count()
								+ rlgl000203Bean.getMtb56CreditReportnavi076Count()
								+ rlgl000203Bean.getMtb56CreditReportnavi077Count()
								+ rlgl000203Bean.getMtb56CreditReportnavi078Count());

				List<Rlgl001080Bean> rlgl011001List = this.getRlgl011001ListForPersonnel();

				DrawBarChartForPersonnel drawBarChart = new DrawBarChartForPersonnel(rlgl011001List);

				imgPathBarChart = drawBarChart.draw(ServletActionContext.getRequest().getSession(),
						ServletActionContext.getRequest().getContextPath(), user.getPersonnel_id());

				List<Rlgl001080Bean> rlgl011001StudyList = this.getRlgl001080ListForPersonnel();

				DrawBarChartForStudy drawBarStudy = new DrawBarChartForStudy(rlgl011001StudyList);
				if (user.getUnit_no().length() > 3 && user.getUnit_no().substring(0, 4).equals("3711")) {
					imgPathBarStudy = drawBarStudy.draw(ServletActionContext.getRequest().getSession(),
							ServletActionContext.getRequest().getContextPath(), user.getPersonnel_id());

					userFlg = "1";
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		CommonManager common = new CommonManager();
		// 系统超级用户取得
		String sysUser = common.getSystemUser("rlglsys.sys.manager");
		if (user.getUser_id().equals(sysUser)) {
			user.setSystem_userFlg("1");
		}

		return "success";
	}

	/**
	 * 
	 * @return
	 */
	public List<Rlgl001080Bean> getRlgl011001List() {
		List<Rlgl001080Bean> rlgl011080BeanList = new ArrayList<Rlgl001080Bean>();
		try {
			// 用户对象
			Mtb01User user = (Mtb01User) super.getSession(Constant.SESSION_KEY_LOGINUSER);
			Mtb04Unit unitInfo = (Mtb04Unit) super.getSession("UnitInfo");
			// 取得单位字段额度信息
			Mtb111Admscore mtb111AdminScore = new Mtb111Admscore();
			mtb111AdminScore.setAdmscore_type_cd("001");
			List<Mtb111Admscore> mtb111AdminScoreList = rlgl011001Service.getMtb111AdmscoreDataInfo(mtb111AdminScore);

			// 共同方法
			CommonManager commonManager = new CommonManager();

			// 检索条件设定
			Rlgl011001Bean rlgl011001BeanNew = new Rlgl011001Bean();
			// 单位编码
			rlgl011001BeanNew.setUnit_no("'" + user.getUnit_no() + "'");
			// 本单位信息完成度
			Rlgl011001Bean rlgl011001Bean = commonManager.getLowerUnitScoreBean(mtb111AdminScoreList, user, unitInfo,
					rlgl011001BeanNew, rlgl011001Service);

			Rlgl001080Bean rlgl011080Bean = new Rlgl001080Bean();

			if (rlgl011001Bean != null) {
				rlgl011080Bean.setObject_name("联系方式");
				rlgl011080Bean.setObject_count(rlgl011001Bean.getScore_contact_sel());
				rlgl011080BeanList.add(rlgl011080Bean);

				rlgl011080Bean = new Rlgl001080Bean();
				rlgl011080Bean.setObject_name("执业信息");
				rlgl011080Bean.setObject_count(rlgl011001Bean.getScore_professional_sel());
				rlgl011080BeanList.add(rlgl011080Bean);

				rlgl011080Bean = new Rlgl001080Bean();
				rlgl011080Bean.setObject_name("基本信息");
				rlgl011080Bean.setObject_count(rlgl011001Bean.getScore_basic_sel());
				rlgl011080BeanList.add(rlgl011080Bean);

				rlgl011080Bean = new Rlgl001080Bean();
				rlgl011080Bean.setObject_name("其他信息");
				rlgl011080Bean.setObject_count(rlgl011001Bean.getScore_other_sel());
				rlgl011080BeanList.add(rlgl011080Bean);

				rlgl011080Bean = new Rlgl001080Bean();
				rlgl011080Bean.setObject_name("必填信息");
				rlgl011080Bean.setObject_count(rlgl011001Bean.getTotal_score_b_sel());
				rlgl011080BeanList.add(rlgl011080Bean);

				rlgl011080Bean = new Rlgl001080Bean();
				rlgl011080Bean.setObject_name("选填信息");
				rlgl011080Bean.setObject_count(rlgl011001Bean.getTotal_score_x_sel());
				rlgl011080BeanList.add(rlgl011080Bean);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return rlgl011080BeanList;
	}

	/**
	 * 
	 * @return
	 */
	public List<Rlgl001080Bean> getRlgl011001ListForPersonnel() {
		List<Rlgl001080Bean> rlgl011080BeanList = new ArrayList<Rlgl001080Bean>();
		try {
			// 用户对象
			Mtb01User user = (Mtb01User) super.getSession(Constant.SESSION_KEY_LOGINUSER);
			Mtb04Unit unitInfo = (Mtb04Unit) super.getSession("UnitInfo");
			// 取得单位字段额度信息
			Mtb111Admscore mtb111AdminScore = new Mtb111Admscore();
			mtb111AdminScore.setAdmscore_type_cd("002");
			List<Mtb111Admscore> mtb111AdminScoreList = rlgl011001Service.getMtb111AdmscoreDataInfo(mtb111AdminScore);

			// 共同方法
			CommonManager commonManager = new CommonManager();

			// 检索条件设定
			Rlgl011001Bean rlgl011001BeanNew = new Rlgl011001Bean();
			// 单位编码
			rlgl011001BeanNew.setUnit_no("'" + user.getUnit_no() + "'");
			rlgl011001BeanNew.setPersonnel_id("'" + user.getPersonnel_id() + "'");
			// 本单位信息完成度
			Rlgl011001Bean rlgl011001Bean = commonManager.getPersoonelInfoSql(mtb111AdminScoreList, unitInfo,
					rlgl011001BeanNew, rlgl011001Service);

			Rlgl001080Bean rlgl011080Bean = new Rlgl001080Bean();

			if (rlgl011001Bean != null) {
				rlgl011080Bean.setObject_name("基本信息");
				rlgl011080Bean.setObject_count(rlgl011001Bean.getBisicCount_sel());
				rlgl011080BeanList.add(rlgl011080Bean);

				rlgl011080Bean = new Rlgl001080Bean();
				rlgl011080Bean.setObject_name("专业技术职务");
				rlgl011080Bean.setObject_count(rlgl011001Bean.getProfessionalTechnicalCount());
				rlgl011080BeanList.add(rlgl011080Bean);

				rlgl011080Bean = new Rlgl001080Bean();
				rlgl011080Bean.setObject_name("行政职务");
				rlgl011080Bean.setObject_count(rlgl011001Bean.getAdministrativePostCount());
				rlgl011080BeanList.add(rlgl011080Bean);

				rlgl011080Bean = new Rlgl001080Bean();
				rlgl011080Bean.setObject_name("社会关系");
				rlgl011080Bean.setObject_count(rlgl011001Bean.getSocialRelationsCount());
				rlgl011080BeanList.add(rlgl011080Bean);

				rlgl011080Bean = new Rlgl001080Bean();
				rlgl011080Bean.setObject_name("学习经历");
				rlgl011080Bean.setObject_count(rlgl011001Bean.getLearningExperienceCount());
				rlgl011080BeanList.add(rlgl011080Bean);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return rlgl011080BeanList;
	}

	/**
	 * 学习情况完成度
	 * 
	 * @return
	 */
	public List<Rlgl001080Bean> getRlgl001080ListForUnit() {
		List<Rlgl001080Bean> rlgl011080BeanList = new ArrayList<Rlgl001080Bean>();
		try {
			// 用户对象
			Mtb01User user = (Mtb01User) super.getSession(Constant.SESSION_KEY_LOGINUSER);
			// 取得单位字段额度信息
			Mtb111Admscore mtb111AdminScore = new Mtb111Admscore();
			mtb111AdminScore.setAdmscore_type_cd("002");

			// 共同方法
			CommonManager commonManager = new CommonManager();

			// 检索条件设定
			Rlgl011001Bean rlgl011001BeanNew = new Rlgl011001Bean();
			// 单位编码
			rlgl011001BeanNew.setUnit_no("'" + user.getUnit_no() + "'");
			rlgl011001BeanNew.setPersonnel_id("'" + user.getPersonnel_id() + "'");
			// 本单位信息完成度
			Rlgl011001Bean rlgl011001Bean = commonManager.getCourseUnitSql(user.getUnit_no(), rlgl011001BeanNew,
					rlgl011001Service);

			Rlgl001080Bean rlgl011080Bean = new Rlgl001080Bean();

			if (rlgl011001Bean != null) {
				rlgl011080Bean.setObject_name("参加继续教育的总人数");
				rlgl011080Bean.setObject_count(rlgl011001Bean.getSumcourseCount());
				rlgl011080BeanList.add(rlgl011080Bean);

				rlgl011080Bean = new Rlgl001080Bean();
				rlgl011080Bean.setObject_name("完成选课的人数");
				rlgl011080Bean.setObject_count(rlgl011001Bean.getSelectcoursecount());
				rlgl011080BeanList.add(rlgl011080Bean);

				rlgl011080Bean = new Rlgl001080Bean();
				rlgl011080Bean.setObject_name("学习并通过考试的人数");
				rlgl011080Bean.setObject_count(rlgl011001Bean.getPasscoursecount());
				rlgl011080BeanList.add(rlgl011080Bean);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return rlgl011080BeanList;
	}

	/**
	 * 学习情况完成度
	 * 
	 * @return
	 */
	public List<Rlgl001080Bean> getRlgl001080ListForPersonnel() {
		List<Rlgl001080Bean> rlgl011080BeanList = new ArrayList<Rlgl001080Bean>();
		try {
			// 用户对象
			Mtb01User user = (Mtb01User) super.getSession(Constant.SESSION_KEY_LOGINUSER);
			// 取得单位字段额度信息
			Mtb111Admscore mtb111AdminScore = new Mtb111Admscore();
			mtb111AdminScore.setAdmscore_type_cd("002");

			// 共同方法
			CommonManager commonManager = new CommonManager();

			// 检索条件设定
			Rlgl011001Bean rlgl011001BeanNew = new Rlgl011001Bean();
			// 单位编码
			rlgl011001BeanNew.setUnit_no("'" + user.getUnit_no() + "'");
			rlgl011001BeanNew.setPersonnel_id("'" + user.getPersonnel_id() + "'");
			// 本单位信息完成度
			Rlgl011001Bean rlgl011001Bean = commonManager.getCourseInfoSql(user.getUser_id(), user.getPersonnel_id(),
					rlgl011001BeanNew, rlgl011001Service);

			Rlgl001080Bean rlgl011080Bean = new Rlgl001080Bean();

			if (rlgl011001Bean != null) {
				rlgl011080Bean = new Rlgl001080Bean();
				rlgl011080Bean.setObject_name("要求学习考试的课程数");
				rlgl011080Bean.setObject_count(rlgl011001Bean.getSumcourseCount());
				rlgl011080BeanList.add(rlgl011080Bean);

				rlgl011080Bean = new Rlgl001080Bean();
				rlgl011080Bean.setObject_name("选课完成度");
				rlgl011080Bean.setObject_count(rlgl011001Bean.getSelectcoursecount());
				rlgl011080BeanList.add(rlgl011080Bean);

				rlgl011080Bean = new Rlgl001080Bean();
				rlgl011080Bean.setObject_name("学习并通过考试完成度");
				rlgl011080Bean.setObject_count(rlgl011001Bean.getPasscoursecount());
				rlgl011080BeanList.add(rlgl011080Bean);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return rlgl011080BeanList;
	}

	/**
	 * 生成naviList
	 * 
	 * @throws Exception
	 */
	public void setNaviIdList() throws Exception {
		if (naviIdList == null) {
			naviIdList = new ArrayList<String>();
		}
		naviIdList.add("navi052");
		naviIdList.add("navi053");
		naviIdList.add("navi054");
		naviIdList.add("navi055");
		naviIdList.add("navi056");
		naviIdList.add("navi066");
		naviIdList.add("navi067");
		naviIdList.add("navi068");
		naviIdList.add("navi069");
		naviIdList.add("navi070");
		naviIdList.add("navi071");
		naviIdList.add("navi072");
		naviIdList.add("navi073");
		naviIdList.add("navi074");
		naviIdList.add("navi075");
		naviIdList.add("navi076");
		naviIdList.add("navi077");
		naviIdList.add("navi078");
	}

	/**
	 * 获得学分年度
	 * 
	 * @return 学分年度
	 * @throws ParseException
	 */
	public int getCreditYear(String kbn) throws ParseException {
		// 获得当前系统时间
		int creditYear = 0;
		String systemDate = DateTimeManager.getSystemDate14().substring(0, 8);
		String year = systemDate.substring(0, 4);
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Date dt1 = new Date();
		try {
			dt1 = df.parse(systemDate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// 6.30-下年7.1
		if (kbn.equals("001")) {
			String date1 = year + "0630";
			String date2 = (Integer.parseInt(year) + 1) + "0701";
			Date dt2 = df.parse(date1);
			Date dt3 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime() && dt1.getTime() <= dt3.getTime()) {
				creditYear = Integer.parseInt(year);
			} else if (dt1.getTime() <= dt2.getTime()) {
				creditYear = (Integer.parseInt(year) - 1);
			} else if (dt1.getTime() > dt2.getTime()) {
				creditYear = (Integer.parseInt(year) + 1);
			}
		} else if (kbn.equals("002")) {
			creditYear = Integer.parseInt(year);
		}
		return creditYear;
	}

	/**
	 * 取得菜单信息
	 * 
	 * @param unitInfo
	 * @return
	 * @throws Exception
	 */
	public List<Mtb03Menu> getMenuInfoList(Mtb04Unit unitInfo) throws Exception {
		CommonManager common = new CommonManager();
		// 用户对象
		Mtb01User loginUser = (Mtb01User) super.getSession(Constant.SESSION_KEY_LOGINUSER);

		List<String> roleList = new ArrayList<String>();

		// 系统超级用户取得
		String sysUser = common.getSystemUser("rlglsys.sys.manager");

		// 设定检索条件对象
		Mtb73Roleaction roleactionInfo = new Mtb73Roleaction();
		if (!loginUser.getUser_id().equals(sysUser)) {
			Mtb72Userrole rileInfo = new Mtb72Userrole();
			rileInfo.setUser_id(user.getUser_id());
			rileInfo.setUser_enter(user.getUser_enter());
			// 用户角色取得
			List<Mtb72Userrole> userRoleInfoList = menuService.getUserRoleInfoList(rileInfo);
			if (userRoleInfoList != null && userRoleInfoList.size() > 0) {
				for (int i = 0; i < userRoleInfoList.size(); i++) {
					Mtb72Userrole roleInfo = userRoleInfoList.get(i);
					roleList.add(roleInfo.getUnit_role());
				}
				// 单位编号
				roleactionInfo.setUnit_role(userRoleInfoList.get(0).getUnit_role());
				roleactionInfo.setUser_enter(userRoleInfoList.get(0).getUser_enter());
				roleactionInfo.setRoleIdList(roleList);
			}

		}

		// Menu菜单取得编辑处理
		List<Mtb03Menu> mtb03MenuList = this.getMenuList(roleactionInfo, unitInfo.getUnit_status(), sysUser);

		if (mtb03MenuList != null && mtb03MenuList.size() > 0) {
			for (int i = 0; i < mtb03MenuList.size(); i++) {
				Mtb03Menu menuInfo = mtb03MenuList.get(i);
				// 单位信息审核
				if ("0046".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb07UnitapplyMtb03Menu(menuInfo);
					rlgl000203Bean.setMtb07UnitapplyMenuFlg("1");
				}
				// 个人信息审核
				if ("0047".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb29PersonalApplyMtb03Menu(menuInfo);
					rlgl000203Bean.setMtb29PersonalApplyMenuFlg("1");
				}
				// 三名申请
				if ("0169".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb29PersonalapplyThreeApplyMtb03Menu(menuInfo);
					rlgl000203Bean.setMtb29PersonalapplyThreeApplyMenuFlg("1");
				}
				// 个人申请列表
				if ("0036".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setPersonalInMtb03Menu(menuInfo);
					rlgl000203Bean.setPersonalInfoMenuFlg("1");
				}

				// 学分审核
				if ("0124".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb56CreditReportMenu(menuInfo);
					rlgl000203Bean.setMtb56CreditReportMenuFlg("1");
				}

				// 山东卫生
				if ("0061".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb56CreditReportnavi052Menu(menuInfo);
					rlgl000203Bean.setMtb56CreditReportnavi052MenuFlg("1");
				}

				// 市级继续教育培训
				if ("0062".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb56CreditReportnavi053Menu(menuInfo);
					rlgl000203Bean.setMtb56CreditReportnavi053MenuFlg("1");
				}

				// 省/外地继教项目|学术会议
				if ("0063".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb56CreditReportnavi054Menu(menuInfo);
					rlgl000203Bean.setMtb56CreditReportnavi054MenuFlg("1");
				}

				// 公共课程考试
				if ("0064".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb56CreditReportnavi055Menu(menuInfo);
					rlgl000203Bean.setMtb56CreditReportnavi055MenuFlg("1");
				}

				// 著作信息
				if ("0065".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb56CreditReportnavi056Menu(menuInfo);
					rlgl000203Bean.setMtb56CreditReportnavi056MenuFlg("1");
				}

				// 译作信息
				if ("0082".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb56CreditReportnavi066Menu(menuInfo);
					rlgl000203Bean.setMtb56CreditReportnavi066MenuFlg("1");
				}

				// 考察报告/专题报告
				if ("0083".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb56CreditReportnavi067Menu(menuInfo);
					rlgl000203Bean.setMtb56CreditReportnavi067MenuFlg("1");
				}

				// 科研成果信息
				if ("0084".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb56CreditReportnavi068Menu(menuInfo);
					rlgl000203Bean.setMtb56CreditReportnavi068MenuFlg("1");
				}

				// 论文综述信息
				if ("0085".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb56CreditReportnavi069Menu(menuInfo);
					rlgl000203Bean.setMtb56CreditReportnavi069MenuFlg("1");
				}

				// 科研立项
				if ("0086".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb56CreditReportnavi070Menu(menuInfo);
					rlgl000203Bean.setMtb56CreditReportnavi070MenuFlg("1");
				}

				// 院内学分
				if ("0087".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb56CreditReportnavi071Menu(menuInfo);
					rlgl000203Bean.setMtb56CreditReportnavi071MenuFlg("1");
				}

				// 学历（学位）教育
				if ("0088".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb56CreditReportnavi072Menu(menuInfo);
					rlgl000203Bean.setMtb56CreditReportnavi072MenuFlg("1");
				}

				// 外出进修
				if ("0089".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb56CreditReportnavi073Menu(menuInfo);
					rlgl000203Bean.setMtb56CreditReportnavi073MenuFlg("1");
				}

				// 自学考试
				if ("0090".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb56CreditReportnavi074Menu(menuInfo);
					rlgl000203Bean.setMtb56CreditReportnavi074MenuFlg("1");
				}

				// 住院医师规范化培训
				if ("0091".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb56CreditReportnavi075Menu(menuInfo);
					rlgl000203Bean.setMtb56CreditReportnavi075MenuFlg("1");
				}

				// 全科医师及社区骨干培训
				if ("0092".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb56CreditReportnavi076Menu(menuInfo);
					rlgl000203Bean.setMtb56CreditReportnavi076MenuFlg("1");
				}

				// 援外与支农
				if ("0093".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb56CreditReportnavi077Menu(menuInfo);
					rlgl000203Bean.setMtb56CreditReportnavi077MenuFlg("1");
				}

				// 专利信息
				if ("0094".equals(menuInfo.getMenu_id())) {
					rlgl000203Bean.setMtb56CreditReportnavi078Menu(menuInfo);
					rlgl000203Bean.setMtb56CreditReportnavi078MenuFlg("1");
				}
			}
		}
		return mtb03MenuList;
	}

	/**
	 * Menu菜单取得编辑处理
	 * 
	 * @param selectPage
	 * @return menuList
	 * @throws Exception
	 */
	public List<Mtb03Menu> getMenuList(Mtb73Roleaction roleactionInfo, String unitStatus, String sysUser)
			throws Exception {
		// 取得菜单信息
		List<Mtb03Menu> menuList1 = new ArrayList<Mtb03Menu>();
		try {
			// 用户对象
			Mtb01User loginUser = (Mtb01User) super.getSession(Constant.SESSION_KEY_LOGINUSER);
			// 入口标记
			String selectPage = "";
			if (super.getSession("selectPage") != null) {
				selectPage = (String) super.getSession("selectPage");
			}
			// 入口判断标记
			String entrance = null;
			// 医疗卫生机构入口的场合
			if ("loginpage1".equals(selectPage)) {
				entrance = "1";
			}
			// 卫生行政部门入口的场合
			if ("loginpage2".equals(selectPage)) {
				entrance = "2";
				;
			}
			// 卫生技术人员入口的场合
			if ("loginpage3".equals(selectPage)) {
				entrance = "3";
			}
			if (!"37".equals(loginUser.getUser_id())) {
				roleactionInfo.setEntrance(entrance);
			}
			if (!sysUser.equals(loginUser.getUser_id())) {
				roleactionInfo.setEntrance(entrance);
				menuList1 = menuService.getMenuInfoByUserList(roleactionInfo);
			} else {
				menuList1 = menuService.getMenuInfoList("");
			}

			menuList1 = this.filteMenuList(menuList1, unitStatus);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		// 返回树形菜单
		return menuList1;
	}

	/**
	 * menu过滤，如果单位信息完善前进行过滤
	 * 
	 * @param menuList1
	 * @param unitStatus
	 * @return
	 * @throws Exception
	 */
	public List<Mtb03Menu> filteMenuList(List<Mtb03Menu> menuList1, String unitStatus) throws Exception {
		List<Mtb03Menu> newMenuList = new ArrayList<Mtb03Menu>();
		if (menuList1 != null && menuList1.size() > 0) {
			// 完善前
			if ("0".equals(unitStatus)) {
				for (int i = 0; i < menuList1.size(); i++) {
					Mtb03Menu menu = (Mtb03Menu) menuList1.get(i);
					// 信息变更、信息明细、下级单位、下级注销、统计设定、代管管理、管理员设定
					if (!"0013".equals(menu.getMenu_id()) && !"0014".equals(menu.getMenu_id())
							&& !"0112".equals(menu.getMenu_id()) && !"0113".equals(menu.getMenu_id())
							&& !"0100".equals(menu.getMenu_id())) {
						newMenuList.add(menu);
					}
				}
			}
			// 完善后
			if ("1".equals(unitStatus)) {
				for (int i = 0; i < menuList1.size(); i++) {
					// 信息完善
					Mtb03Menu menu = (Mtb03Menu) menuList1.get(i);
					if (!"0012".equals(menu.getMenu_id())) {
						newMenuList.add(menu);
					}

				}
			}
		}
		return newMenuList;
	}

	public void get() {
		try {
			// 获取年度用变量
			Common common = new Common();
			rlgl400102bean = new Rlgl400102Bean();
			// 获得当前学分年度（申请年度）
			String area_id = mtb02AdmService.getAdmName("237", "01");
			String credit_year = Integer.toString(common.getNowCreditYear(area_id, rlgl050101Service));
			// 用户对象
			Mtb01User loginUser = (Mtb01User) super.getSession(Constant.SESSION_KEY_LOGINUSER);
			userId = loginUser.getPersonnel_id();
			if (!credit_year.isEmpty()) {
				int year = Integer.parseInt(credit_year);
				// int ft = year - 2015;
				// if (ft > 6) {
				// ft = 6;
				// }
				int score = 0;
				for (int i = 0; i < 7; i++) {
					String credityear = String.valueOf(year - i);
					// 一类学分统计
					int I_credit = mtb02AdmService.getCourseCreditSum(userId, credityear, "001");
					// 二类学分统计
					int II_credit = mtb02AdmService.getCourseCreditSum(userId, credityear, "002");
					score = score + I_credit + II_credit;
					switch (i) {
					case 0:
						rlgl400102bean.setIcredit6(I_credit);
						rlgl400102bean.setIIcredit6(II_credit);
						rlgl400102bean.setCredit6(II_credit + I_credit);
						rlgl400102bean.setCredit_year6(credityear + "年度");
						rlgl400102bean.setCredit_classification6(
								"【 I:" + String.valueOf(I_credit) + "  II:" + String.valueOf(II_credit) + "】");
						break;
					case 1:
						rlgl400102bean.setIcredit5(I_credit);
						rlgl400102bean.setCredit5(II_credit + I_credit);
						rlgl400102bean.setIIcredit5(II_credit);
						rlgl400102bean.setCredit_year5(credityear + "年度");
						rlgl400102bean.setCredit_classification5(
								"【 I:" + String.valueOf(I_credit) + "  II:" + String.valueOf(II_credit) + "】");
						break;
					case 2:
						rlgl400102bean.setIcredit4(I_credit);
						rlgl400102bean.setIIcredit4(II_credit);
						rlgl400102bean.setCredit4(II_credit + I_credit);
						rlgl400102bean.setCredit_year4(credityear + "年度");
						rlgl400102bean.setCredit_classification4(
								"【 I:" + String.valueOf(I_credit) + "  II:" + String.valueOf(II_credit) + "】");
						break;
					case 3:
						rlgl400102bean.setIcredit3(I_credit);
						rlgl400102bean.setIIcredit3(II_credit);
						rlgl400102bean.setCredit3(II_credit + I_credit);
						rlgl400102bean.setCredit_year3(credityear + "年度");
						rlgl400102bean.setCredit_classification3(
								"【 I:" + String.valueOf(I_credit) + "  II:" + String.valueOf(II_credit) + "】");
						break;
					case 4:
						rlgl400102bean.setIcredit2(I_credit);
						rlgl400102bean.setIIcredit2(II_credit);
						rlgl400102bean.setCredit2(II_credit + I_credit);
						rlgl400102bean.setCredit_year2(credityear + "年度");
						rlgl400102bean.setCredit_classification2(
								"【 I:" + String.valueOf(I_credit) + "  II:" + String.valueOf(II_credit) + "】");
						break;
					case 5:
						rlgl400102bean.setIcredit1(I_credit);
						rlgl400102bean.setIIcredit1(II_credit);
						rlgl400102bean.setCredit1(II_credit + I_credit);
						rlgl400102bean.setCredit_year1(credityear + "年度");
						rlgl400102bean.setCredit_classification1(
								"【 I:" + String.valueOf(I_credit) + "  II:" + String.valueOf(II_credit) + "】");
						break;
					case 6:
						rlgl400102bean.setIcredit0(I_credit);
						rlgl400102bean.setIIcredit0(II_credit);
						rlgl400102bean.setCredit0(II_credit + I_credit);
						rlgl400102bean.setCredit_year0(credityear + "年度");
						rlgl400102bean.setCredit_classification0(
								"【 I:" + String.valueOf(I_credit) + "  II:" + String.valueOf(II_credit) + "】");
						break;
					}
				}
				rlgl400102bean.setTotal_score("总分");
				rlgl400102bean.setScore(score);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// -------------------Get Set-----------------------------
	/**
	 * @return the rlgl000203Service
	 */
	public IRlgl000203Service getRlgl000203Service() {
		return rlgl000203Service;
	}

	/**
	 * @param rlgl000203Service
	 *            the rlgl000203Service to set
	 */
	public void setRlgl000203Service(IRlgl000203Service rlgl000203Service) {
		this.rlgl000203Service = rlgl000203Service;
	}

	/**
	 * @return the user
	 */
	public Mtb01User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(Mtb01User user) {
		this.user = user;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public List<Rlgl000203Bean> getNoticeInfoList() {
		return noticeInfoList;
	}

	public void setNoticeInfoList(List<Rlgl000203Bean> noticeInfoList) {
		this.noticeInfoList = noticeInfoList;
	}

	public Rlgl000203Bean getRlgl000203Bean() {
		return rlgl000203Bean;
	}

	public void setRlgl000203Bean(Rlgl000203Bean rlgl000203Bean) {
		this.rlgl000203Bean = rlgl000203Bean;
	}

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public IRlgl020603Service getRlgl020603Service() {
		return rlgl020603Service;
	}

	public void setRlgl020603Service(IRlgl020603Service rlgl020603Service) {
		this.rlgl020603Service = rlgl020603Service;
	}

	public List<String> getNaviIdList() {
		return naviIdList;
	}

	public void setNaviIdList(List<String> naviIdList) {
		this.naviIdList = naviIdList;
	}

	public IRlgl011001Service getRlgl011001Service() {
		return rlgl011001Service;
	}

	public void setRlgl011001Service(IRlgl011001Service rlgl011001Service) {
		this.rlgl011001Service = rlgl011001Service;
	}

	public String getImgPathBarChart() {
		return imgPathBarChart;
	}

	public void setImgPathBarChart(String imgPathBarChart) {
		this.imgPathBarChart = imgPathBarChart;
	}

	public String getImgPathBarStudy() {
		return imgPathBarStudy;
	}

	public void setImgPathBarStudy(String imgPathBarStudy) {
		this.imgPathBarStudy = imgPathBarStudy;
	}

	public String getUserFlg() {
		return userFlg;
	}

	public void setUserFlg(String userFlg) {
		this.userFlg = userFlg;
	}

}