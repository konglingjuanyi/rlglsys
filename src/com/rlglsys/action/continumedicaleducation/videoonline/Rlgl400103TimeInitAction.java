package com.rlglsys.action.continumedicaleducation.videoonline;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.rlglsys.base.BaseAction;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IRlgl050101Service;
import com.rlglsys.util.Common;
import com.rlglsys.util.Constant;

public class Rlgl400103TimeInitAction extends BaseAction {

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

	@Override
	protected String doExecute() throws Exception {
    	//获取年度用变量
    	Common common = new Common();
    	HttpServletRequest request = ServletActionContext.getRequest();
    	 String name = request.getParameter("ab");
    	 //设置学分类别
	      super.setSession("xflb", name);
    	//获得当前学分年度（申请年度）
    	String area_id = mtb02AdmService.getAdmName("237", "01");
        String year = Integer.toString(common.getNowCreditYear(area_id, rlgl050101Service));
		credit_year = year;

		// 用户对象
        Mtb01User loginUser = (Mtb01User)super.getSession(Constant.SESSION_KEY_LOGINUSER);
        userId=loginUser.getPersonnel_id();
        
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
