package com.rlglsys.action.continumedicaleducation.hospitalcredit;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl400102Bean;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IRlgl022004Service;
import com.rlglsys.service.IRlgl050101Service;
import com.rlglsys.util.Common;
import com.rlglsys.util.Constant;

public class Rlgl400102InitAction extends BaseAction {
	private static final long serialVersionUID = -690007320589862048L;
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

	public Rlgl400102Bean getRlgl400102bean() {
		return rlgl400102bean;
	}

	public void setRlgl400102bean(Rlgl400102Bean rlgl400102bean) {
		this.rlgl400102bean = rlgl400102bean;
	}

	@Override
	protected String doExecute() throws Exception {
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
//			int ft = year - 2015;
//			if (ft > 6) {
//				ft = 6;
//			}
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
							"【 I:" + String.valueOf(I_credit) +  "  II:" + String.valueOf(II_credit) + "】");
					break;
				case 1:
					rlgl400102bean.setIcredit5(I_credit);
					rlgl400102bean.setCredit5(II_credit + I_credit);
					rlgl400102bean.setIIcredit5(II_credit);
					rlgl400102bean.setCredit_year5(credityear + "年度");
					rlgl400102bean.setCredit_classification5(
							"【 I:" + String.valueOf(I_credit) +  "  II:" + String.valueOf(II_credit) + "】");
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
							"【 I:" + String.valueOf(I_credit) +  "  II:" + String.valueOf(II_credit) + "】");
					break;
				case 4:
					rlgl400102bean.setIcredit2(I_credit);
					rlgl400102bean.setIIcredit2(II_credit);
					rlgl400102bean.setCredit2(II_credit + I_credit);
					rlgl400102bean.setCredit_year2(credityear + "年度");
					rlgl400102bean.setCredit_classification2(
							"【 I:" + String.valueOf(I_credit) +  "  II:" + String.valueOf(II_credit) + "】");
					break;
				case 5:
					rlgl400102bean.setIcredit1(I_credit);
					rlgl400102bean.setIIcredit1(II_credit);
					rlgl400102bean.setCredit1(II_credit + I_credit);
					rlgl400102bean.setCredit_year1(credityear + "年度");
					rlgl400102bean.setCredit_classification1(
							"【 I:" + String.valueOf(I_credit) +  "  II:" + String.valueOf(II_credit) + "】");
					break;
				case 6:
					rlgl400102bean.setIcredit0(I_credit);
					rlgl400102bean.setIIcredit0(II_credit);
					rlgl400102bean.setCredit0(II_credit + I_credit);
					rlgl400102bean.setCredit_year0(credityear + "年度");
					rlgl400102bean.setCredit_classification0(
							"【 I:" + String.valueOf(I_credit) +  "  II:" + String.valueOf(II_credit) + "】");
					break;
				}
			}
			rlgl400102bean.setTotal_score("总分");
			rlgl400102bean.setScore(score);
		}
		return SUCCESS;
	}

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

}
