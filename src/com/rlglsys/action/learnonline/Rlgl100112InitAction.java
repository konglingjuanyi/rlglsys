package com.rlglsys.action.learnonline;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl100104Bean;
import com.rlglsys.bean.Rlgl500102Bean;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IRlgl050101Service;
import com.rlglsys.service.IRlgl100101Service;
import com.rlglsys.service.IRlgl100104Service;
import com.rlglsys.service.IUserService;
import com.rlglsys.util.Common;
import com.rlglsys.util.Constant;

public class Rlgl100112InitAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IRlgl100104Service rlgl100104Service;
	private IMTb02AdmService mtb02AdmService;
	private IRlgl050101Service rlgl050101Service;
	private IRlgl100101Service rlgl100101Service;
	public IRlgl100101Service getRlgl100101Service() {
		return rlgl100101Service;
	}

	public void setRlgl100101Service(IRlgl100101Service rlgl100101Service) {
		this.rlgl100101Service = rlgl100101Service;
	}

	private List<Rlgl100104Bean> courseSelectedList;
	private String temp_str = "";
	private String sysUrl = "";
	private String user_id = "";
	private String xuefenleibie = "";
	private double balance = 0;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getXuefenleibie() {
		return xuefenleibie;
	}

	public void setXuefenleibie(String xuefenleibie) {
		this.xuefenleibie = xuefenleibie;
	}

	private int course_count = 0;
	private int public_course_count = 0;
	// 用户Service
	private IUserService userService;

	@Override
	protected String doExecute() throws Exception {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			xuefenleibie = "001";
			// 用户对象
			Mtb01User loginUser = (Mtb01User) super.getSession(Constant.SESSION_KEY_LOGINUSER);
			user_id = loginUser.getUser_id();
			// 获得当前日期
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			temp_str = sdf.format(dt);
			Rlgl100104Bean rlgl100104Bean = new Rlgl100104Bean();
			rlgl100104Bean.setUser_id(user_id);
			rlgl100104Bean.setXuefen_leibie(xuefenleibie);
			Rlgl500102Bean rlgl500102Bean = new Rlgl500102Bean();
			rlgl500102Bean.setUser_id(user_id);
			Rlgl500102Bean rlgl500102 =	rlgl100101Service.getBalanceData(rlgl500102Bean);
			if(rlgl500102 != null){balance = rlgl500102.getBalance();}
			
			// 获取年度用变量
			Common common = new Common();
			// 获得当前学分年度（申请年度）
			String area_id = mtb02AdmService.getAdmName("237", "01");
			String credit_year = Integer.toString(common.getNowCreditYear(area_id, rlgl050101Service));

			rlgl100104Bean.setCredit_year(credit_year);
			// course_count =
			// rlgl100104Service.selectCourseCount(rlgl100104Bean);
			public_course_count = rlgl100104Service.selectPublicCourseCount(rlgl100104Bean);
			courseSelectedList = rlgl100104Service.selectPublicCourse(rlgl100104Bean);
			// Mtb120SysUrl urlInfoNew = new Mtb120SysUrl();
			// urlInfoNew.setUrl_id("001");
			// Mtb120SysUrl urlInfo =
			// userService.getMtb120SysurlInfo(urlInfoNew);
			// if (urlInfo != null)
			// {
			// sysUrl = urlInfo.getUrl();
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "init";
	}

	/**
	 * @return the courseSelectedList
	 */
	public List<Rlgl100104Bean> getCourseSelectedList() {
		return courseSelectedList;
	}

	/**
	 * @param courseSelectedList
	 *            the courseSelectedList to set
	 */
	public void setCourseSelectedList(List<Rlgl100104Bean> courseSelectedList) {
		this.courseSelectedList = courseSelectedList;
	}

	/**
	 * @return the rlgl100104Service
	 */
	public IRlgl100104Service getRlgl100104Service() {
		return rlgl100104Service;
	}

	/**
	 * @param rlgl100104Service
	 *            the rlgl100104Service to set
	 */
	public void setRlgl100104Service(IRlgl100104Service rlgl100104Service) {
		this.rlgl100104Service = rlgl100104Service;
	}

	/**
	 * @return the temp_str
	 */
	public String getTemp_str() {
		return temp_str;
	}

	/**
	 * @param temp_str
	 *            the temp_str to set
	 */
	public void setTemp_str(String temp_str) {
		this.temp_str = temp_str;
	}

	public String getSysUrl() {
		return sysUrl;
	}

	public void setSysUrl(String sysUrl) {
		this.sysUrl = sysUrl;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getCourse_count() {
		return course_count;
	}

	public void setCourse_count(int course_count) {
		this.course_count = course_count;
	}

	public int getPublic_course_count() {
		return public_course_count;
	}

	public void setPublic_course_count(int public_course_count) {
		this.public_course_count = public_course_count;
	}

	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}

	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
	}

	public IRlgl050101Service getRlgl050101Service() {
		return rlgl050101Service;
	}

	public void setRlgl050101Service(IRlgl050101Service rlgl050101Service) {
		this.rlgl050101Service = rlgl050101Service;
	}
}
