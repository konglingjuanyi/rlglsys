package com.rlglsys.action.learnonline;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.MUserExamInfoReport;
import com.rlglsys.bean.Rlgl100101_1Bean;
import com.rlglsys.entity.DBCommon;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IMUserExamInfoReportService;
import com.rlglsys.service.IMerchantInfoService;
import com.rlglsys.service.IRlgl050101Service;
import com.rlglsys.service.IRlgl100100Service;
import com.rlglsys.service.IRlgl100101Service;
import com.rlglsys.util.BeanFactory;
import com.rlglsys.util.Constant;
import com.rlglsys.util.DesUtil;

public class UserExamInfoReportInitAction extends BaseAction {

	private IMUserExamInfoReportService mUserExamInfoReportService;
	// 缴费明细列表
	private List<MUserExamInfoReport> mUserExamInfoReportList;

	public IMUserExamInfoReportService getmUserExamInfoReportService() {
		return mUserExamInfoReportService;
	}

	public void setmUserExamInfoReportService(IMUserExamInfoReportService mUserExamInfoReportService) {
		this.mUserExamInfoReportService = mUserExamInfoReportService;
	}

	public List<MUserExamInfoReport> getmUserExamInfoReportList() {
		return mUserExamInfoReportList;
	}

	public void setmUserExamInfoReportList(List<MUserExamInfoReport> mUserExamInfoReportList) {
		this.mUserExamInfoReportList = mUserExamInfoReportList;
	}

	// 分页用
	private int recordCount;
	private String txtInputPage = "";
	private String hdnCountOfPage = "";
	private int count = 0;
	// 是否允许退款标志
	private String refundable = "";

	@Override
	protected String doExecute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("ax");
		// 获取共通
		Mtb01User loginUser = (Mtb01User) super.getSession(Constant.SESSION_KEY_LOGINUSER);
		// 获取单位编号
		String user_id = loginUser.getUser_id();
		Rlgl100101_1Bean rlgl100101_1Bean = new Rlgl100101_1Bean();
		int pageCount = getPageCount();

		mUserExamInfoReportList = mUserExamInfoReportService.getReport(String.valueOf(1), String.valueOf(10000));
		HttpServletRequest mRequest=ServletActionContext.getRequest();
		mRequest.setAttribute("list", mUserExamInfoReportList);
		mRequest.setAttribute("count", mUserExamInfoReportList.size());
		return "init";
	}
 
	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public String getTxtInputPage() {
		return txtInputPage;
	}

	public void setTxtInputPage(String txtInputPage) {
		this.txtInputPage = txtInputPage;
	}

	public String getHdnCountOfPage() {
		return hdnCountOfPage;
	}

	public void setHdnCountOfPage(String hdnCountOfPage) {
		this.hdnCountOfPage = hdnCountOfPage;
	}

	 

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getRefundable() {
		return refundable;
	}

	public void setRefundable(String refundable) {
		this.refundable = refundable;
	}
}
