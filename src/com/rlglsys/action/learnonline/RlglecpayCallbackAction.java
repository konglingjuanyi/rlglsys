package com.rlglsys.action.learnonline;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ecc.tool.Signature;
import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl100101_1Bean;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb119MerchantInfo;
import com.rlglsys.entity.TTb01AutoGetNum;
import com.rlglsys.service.IAutoGetNumService;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IMerchantInfoService;
import com.rlglsys.service.IRlgl050101Service;
import com.rlglsys.service.IRlgl100101Service;
import com.rlglsys.util.BeanFactory;
import com.rlglsys.util.Common;
import com.rlglsys.util.Constant;
import com.rlglsys.util.DateTimeManager;
import com.rlglsys.util.DesUtil;

public class RlglecpayCallbackAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private IMerchantInfoService merchantInfoService;
	private IRlgl100101Service rlgl100101Service;
	private IRlgl050101Service rlgl050101Service;
	private IMTb02AdmService mtb02AdmService;
	private static Logger logger = Logger.getLogger(RlglecpayCallbackAction.class);
	// 自动递增
	private IAutoGetNumService noSerVice;

	private String flg = "";

	@Override
	protected String doExecute() throws Exception {
		String MerchantID = "";
		String TransactionID = "";
		String Amount = "";
		String Succeed = "";
		String Signatures = "";
		String key = "";
		String userId = "";
		flg = "";
		Mtb01User loginUser = (Mtb01User) getSession(Constant.SESSION_KEY_LOGINUSER);
		// 获取单位编号
		userId = loginUser.getUser_id();
		// 登陆用户信息
		/*
		 * Mtb01User loginUser =
		 * (Mtb01User)super.getSession(Constant.SESSION_KEY_LOGINUSER); userId =
		 * (String)loginUser.getUser_id();
		 */

		HttpServletResponse response = ServletActionContext.getResponse();
		// 获得银联支付返回信息

		// MerchantID = super.getRequest().getParameter("merID");
		// 订单号
		TransactionID = super.getRequest().getParameter("dealOrder");
		// 金额
		Amount = super.getRequest().getParameter("dealFee");
		// 处理状态 SUCCESS 支付成功
		Succeed = super.getRequest().getParameter("dealState");
		// 数字签名
		Signatures = super.getRequest().getParameter("dealSignure");
		if ("SUCCESS".equals(Succeed)) {
			Succeed = "1";
		} else {
			Succeed = "2";
		}

		if (TransactionID == null || Amount == null || Succeed == null || Signatures == null || "".equals(TransactionID)
				|| "".equals(Amount) || "".equals(Succeed) || "".equals(Signatures)) {
			// 返回信息中有空值时，支付失败
			flg = "1";
			return null;
		}
		// 获得当前年度
		Common common = new Common();
		// 获得当前年度
		String area_id = mtb02AdmService.getAdmName("237", "01");
		String payyear = Integer.toString(common.getNowCreditYear(area_id, rlgl050101Service));

		Rlgl100101_1Bean rlgl100101_1Bean1 = new Rlgl100101_1Bean();
		rlgl100101_1Bean1.setTransactionID(TransactionID);
		// 已有成功记录时说明已支付成功
		if (null != rlgl100101Service.getData2(rlgl100101_1Bean1)) {
			// flg = "0";
			try {
				PrintWriter out = response.getWriter();
				out.println("notify_success12");
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		// 解密
		BeanFactory beanFactory = new BeanFactory();
		Map<String, String> Fields = new HashMap<String, String>();
		beanFactory.reinstallFields(rlgl100101_1Bean1, DesUtil.class, "dec" + "rypt", Fields);
		// rlgl100101_1Bean1.setUserId(userId);
		rlgl100101_1Bean1.setMerchantID(MerchantID);
		rlgl100101_1Bean1.setAmount(Amount);
		rlgl100101_1Bean1.setSucceed(Succeed);
		rlgl100101_1Bean1.setPay_year(payyear);
		rlgl100101_1Bean1.setPay_date(DateTimeManager.getCurrentDateStrFormat());

		int count = 0;
		// 商户信息
		Mtb119MerchantInfo mtb119MerchantInfo = null;
		// 把支付返回信息更新到数据库中
		super.setDBCommonColOnUpdate(rlgl100101_1Bean1);
		rlgl100101_1Bean1.setUserId(userId);
		rlgl100101Service.updateData(rlgl100101_1Bean1);
		// 取得商户信息
		mtb119MerchantInfo = merchantInfoService.getMerchantInfo();

		if (mtb119MerchantInfo != null) {
			// 取得商户密钥
			key = mtb119MerchantInfo.getKey();
			if (key == null || "".equals(key)) {
				flg = "2";
				return null;
			}
		}

		String Signature1 = "";
		if ("1".equals(Succeed)) {
			// Signature.backSignure(dealOrder, dealState, key);
			Signature1 = Signature.backSignure(TransactionID, "SUCCESS", key);
		} else {
			Signature1 = Signature.backSignure(TransactionID, "FAILURE", key);
		}
		// 计算得出的Signature与返回的Signature相同时，说明支付成功
		if (Signatures.equals(Signature1)) {
			flg = "0";
			try {
				PrintWriter out = response.getWriter();
				out.println("notify_success11");
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			TTb01AutoGetNum noInfo = new TTb01AutoGetNum();
			// 自动递增表ID
			noInfo.setTable_id("t_tb02");
			// 自动递增字段
			noInfo.setCol_id("TransactionID");
			noInfo.setNum_type("3711" + DateTimeManager.getSystemDate14().substring(0, 8));
			if (count == 0) {
				noSerVice.updateNoInfoByUnit(noInfo);
			}
		}

		return null;
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

	public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}

	public IAutoGetNumService getNoSerVice() {
		return noSerVice;
	}

	public void setNoSerVice(IAutoGetNumService noSerVice) {
		this.noSerVice = noSerVice;
	}

	public IMerchantInfoService getMerchantInfoService() {
		return merchantInfoService;
	}

	public void setMerchantInfoService(IMerchantInfoService merchantInfoService) {
		this.merchantInfoService = merchantInfoService;
	}

	public IRlgl100101Service getRlgl100101Service() {
		return rlgl100101Service;
	}

	public void setRlgl100101Service(IRlgl100101Service rlgl100101Service) {
		this.rlgl100101Service = rlgl100101Service;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		RlglecpayCallbackAction.logger = logger;
	}

}
