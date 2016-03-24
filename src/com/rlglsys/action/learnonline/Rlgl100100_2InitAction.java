package com.rlglsys.action.learnonline;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl100101_1Bean;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IMerchantInfoService;
import com.rlglsys.service.IRlgl050101Service;
import com.rlglsys.service.IRlgl100100Service;
import com.rlglsys.service.IRlgl100101Service;
import com.rlglsys.util.BeanFactory;
import com.rlglsys.util.DesUtil;

public class Rlgl100100_2InitAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IRlgl100100Service rlgl100100Service;
	// 缴费明细列表
	private List<Rlgl100101_1Bean> tb02PrepayMsgList;
	private List<Rlgl100101_1Bean> tb02PrepayMsgTempList;
	private IMTb02AdmService mtb02AdmService;
	private IRlgl100101Service rlgl100101Service;
	private IMerchantInfoService merchantInfoService;
	private IRlgl050101Service rlgl050101Service;

	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}

	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
	}

	public IRlgl100101Service getRlgl100101Service() {
		return rlgl100101Service;
	}

	public void setRlgl100101Service(IRlgl100101Service rlgl100101Service) {
		this.rlgl100101Service = rlgl100101Service;
	}

	public IMerchantInfoService getMerchantInfoService() {
		return merchantInfoService;
	}

	public void setMerchantInfoService(IMerchantInfoService merchantInfoService) {
		this.merchantInfoService = merchantInfoService;
	}

	public IRlgl050101Service getRlgl050101Service() {
		return rlgl050101Service;
	}

	public void setRlgl050101Service(IRlgl050101Service rlgl050101Service) {
		this.rlgl050101Service = rlgl050101Service;
	}

	public List<Rlgl100101_1Bean> getTb02PrepayMsgTempList() {
		return tb02PrepayMsgTempList;
	}

	public void setTb02PrepayMsgTempList(List<Rlgl100101_1Bean> tb02PrepayMsgTempList) {
		this.tb02PrepayMsgTempList = tb02PrepayMsgTempList;
	}

	// 分页用
	private int recordCount;
	private String txtInputPage = "";
	private String hdnCountOfPage = "";
	private String playFlag = "";
	private String id = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPlayFlag() {
		return playFlag;
	}

	public void setPlayFlag(String playFlag) {
		this.playFlag = playFlag;
	}

	private int count = 0;
	// 是否允许退款标志
	private String refundable = "";

	private String startdate = "";

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	private String enddate = "";

	@Override
	protected String doExecute() throws Exception {
	
		int pageCount = getPageCount();
		// 当前页
		// txtInputPage = "";
		int pageNo = "".equals(txtInputPage) ? 0 * pageCount : (Integer.valueOf(txtInputPage) - 1) * pageCount;
		recordCount = rlgl100100Service.getPrepayMsgAdminCount(startdate, enddate,id);

		// 管理员查询个人缴费明细列表
		tb02PrepayMsgList = rlgl100100Service.getPrepayMsgAdminList(startdate, pageCount, pageNo, enddate,id);
		if ("OK".equals(refundable)) {
			super.saveErrorMessage("MSG0091I");
		}
		// ========解密查询结果==============
		BeanFactory beanFactory = new BeanFactory();
		Map Fields = new HashMap();
		if (tb02PrepayMsgList != null && tb02PrepayMsgList.size() > 0) {
			playFlag = "1";
			for (int i = 0; i < tb02PrepayMsgList.size(); i++) {
				beanFactory.reinstallFields(tb02PrepayMsgList.get(i), DesUtil.class, "dec" + "rypt", Fields);
			}
		} else {
			playFlag = "2";
		}
		return "init";
	}

	public List<Rlgl100101_1Bean> getTb02PrepayMsgList() {
		return tb02PrepayMsgList;
	}

	public void setTb02PrepayMsgList(List<Rlgl100101_1Bean> tb02PrepayMsgList) {
		this.tb02PrepayMsgList = tb02PrepayMsgList;
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

	public IRlgl100100Service getRlgl100100Service() {
		return rlgl100100Service;
	}

	public void setRlgl100100Service(IRlgl100100Service rlgl100100Service) {
		this.rlgl100100Service = rlgl100100Service;
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
