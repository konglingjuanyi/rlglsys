package com.rlglsys.action.continumedicaleducation.videoonline;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl500102Bean;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IRlgl050101Service;
import com.rlglsys.service.IRlgl100101Service;
import com.rlglsys.util.Common;
import com.rlglsys.util.Constant;

public class Rlgl022004_2TimeInitAction extends BaseAction {

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
	private double balance = 0;
	private IRlgl100101Service rlgl100101Service;

	public IRlgl100101Service getRlgl100101Service() {
		return rlgl100101Service;
	}

	public void setRlgl100101Service(IRlgl100101Service rlgl100101Service) {
		this.rlgl100101Service = rlgl100101Service;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	protected String doExecute() throws Exception {
		// 获取年度用变量
		Common common = new Common();
		// 获得当前学分年度（申请年度）
		String area_id = mtb02AdmService.getAdmName("237", "01");
		String year = Integer.toString(common.getNowCreditYear(area_id, rlgl050101Service));
		credit_year = year;
		Mtb01User loginUser = (Mtb01User) super.getSession(Constant.SESSION_KEY_LOGINUSER);
		userId = loginUser.getPersonnel_id();
		Rlgl500102Bean rlgl500102Bean = new Rlgl500102Bean();
		rlgl500102Bean.setUser_id(userId);
		Rlgl500102Bean rlgl500102 = rlgl100101Service.getBalanceData(rlgl500102Bean);
		if (rlgl500102 != null) {
			balance = rlgl500102.getBalance();
		}
		// 用户对象

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

}
