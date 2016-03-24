package com.rlglsys.action.learnonline;

import com.ecc.tool.Signature;
import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl100101Bean;
import com.rlglsys.bean.Rlgl100101_1Bean;
import com.rlglsys.bean.Rlgl500102Bean;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb119MerchantInfo;
import com.rlglsys.entity.Mtb120SysUrl;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IMerchantInfoService;
import com.rlglsys.service.IRlgl100101Service;
import com.rlglsys.service.IUserService;
import com.rlglsys.util.DateTimeManager;
import com.rlglsys.util.Common;
import com.rlglsys.util.Constant;
import com.rlglsys.service.IAutoGetNumService;
import com.rlglsys.entity.TTb01AutoGetNum;

public class Rlgl100101PrepayAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IRlgl100101Service rlgl100101Service;
	private Rlgl100101Bean rlgl100101Bean;
	private IMerchantInfoService merchantInfoService;
	private Rlgl100101_1Bean rlgl100101_1Bean;
	private IMTb02AdmService mtb02AdmService;
	// 自动递增
	private IAutoGetNumService noSerVice;

	private String status = "";
	private String MerchantID = "";
	private String MerchantNM = "";
	private String TransactionID = "";
	private String Amount = "";
	private String Succeed = "";
	private String Signatures = "";
	private String MerchantURL = "";
	private String MerchantURLReturn = "";
	public String getMerchantURLReturn() {
		return MerchantURLReturn;
	}

	public void setMerchantURLReturn(String merchantURLReturn) {
		MerchantURLReturn = merchantURLReturn;
	}

	private String flg = "";
	private String Payment = "";

	public String getPayment() {
		return Payment;
	}

	public void setPayment(String payment) {
		Payment = payment;
	}

	// 用户Service
	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}

	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
	}

	public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}

	public String getMerchantURL() {
		return MerchantURL;
	}

	public void setMerchantURL(String merchantURL) {
		MerchantURL = merchantURL;
	}

	public IAutoGetNumService getNoSerVice() {
		return noSerVice;
	}

	public void setNoSerVice(IAutoGetNumService noSerVice) {
		this.noSerVice = noSerVice;
	}

	public String getMerchantID() {
		return MerchantID;
	}

	public void setMerchantID(String merchantID) {
		MerchantID = merchantID;
	}

	public String getMerchantNM() {
		return MerchantNM;
	}

	public void setMerchantNM(String merchantNM) {
		MerchantNM = merchantNM;
	}

	public String getTransactionID() {
		return TransactionID;
	}

	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	public String getSucceed() {
		return Succeed;
	}

	public void setSucceed(String succeed) {
		Succeed = succeed;
	}

	public String getSignatures() {
		return Signatures;
	}

	public void setSignatures(String signatures) {
		Signatures = signatures;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	public IMerchantInfoService getMerchantInfoService() {
		return merchantInfoService;
	}

	public void setMerchantInfoService(IMerchantInfoService merchantInfoService) {
		this.merchantInfoService = merchantInfoService;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	protected String doExecute() throws Exception {

		String key = "";

		// 取得商户信息
		Mtb119MerchantInfo mtb119MerchantInfo = null;
		try {
			mtb119MerchantInfo = merchantInfoService.getMerchantInfo();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		if (mtb119MerchantInfo == null) {
			return "error";
		} else {
			// 商户ID
			MerchantID = mtb119MerchantInfo.getMerchantID();
			// 商户名
			MerchantNM = mtb119MerchantInfo.getMerchantNM();
			// 密钥
			key = mtb119MerchantInfo.getKey();
		}

		// 交易号取得
		if ("".equals(TransactionID)) {
			TTb01AutoGetNum noInfo = new TTb01AutoGetNum();
			// 自动递增表ID
			noInfo.setTable_id("t_tb02");
			// 自动递增字段
			noInfo.setCol_id("TransactionID");
			// 设定长度
			noInfo.setLength(8);
			noInfo.setNum_type("3711" + DateTimeManager.getSystemDate14().substring(0, 8));

			TransactionID = "3711" + DateTimeManager.getSystemDate14().substring(0, 8)
					+ noSerVice.searchNoInfoByUnit(noInfo);
		}
		if (Payment == null || "".equals(Payment)) {
			Payment = "0";
		}
		// 获得缴费金额
		Amount = Payment;
		super.setSession("Payment", Amount);
		// MerchantURL =
		// "http://localhost/rlgl100101Init.action?naviId=&only_search=0&checkResult=";
		Mtb120SysUrl urlInfoNew = new Mtb120SysUrl();
		urlInfoNew.setUrl_id("001");
		Mtb120SysUrl urlInfo = userService.getMtb120SysurlInfo(urlInfoNew);
		String sysUrl = "";
		if (urlInfo != null) {
			sysUrl = urlInfo.getUrl();
		}

		// 交易成功后，从银联网返回时指定的url
		MerchantURLReturn = sysUrl + "/rlglpayCallback.action";
		// 交易成功后，从银联网返回时指定的url
	    MerchantURL = sysUrl + "/rlglecpayCallback.action";
	    
      
		// 商户ID，交易号，金额，返回url都不为空时，继续
		if ("".equals(MerchantID) || "".equals(TransactionID) || "".equals(Amount) || "".equals(MerchantURL)) {
			return "error";
		}

		// 数据签名取得
		Signatures = Signature.dealSignure(MerchantID, TransactionID, Amount, MerchantURL, key);
		/*
		 * java.security.MessageDigest alga =
		 * java.security.MessageDigest.getInstance("SHA-1"); String data =
		 * MerchantID + TransactionID + Amount + MerchantURL + key;
		 * alga.update(data.getBytes()); byte[] digesta = alga.digest(); //byte
		 * dateByte[] = new byte[200]; //dateByte = data.getBytes();
		 * 
		 * Signatures = new Common().bintoascii(digesta);
		 */
		/*
		 * //排除共同列加密 Map Fields = new HashMap(); Field[] fields =
		 * DBCommon.class.getDeclaredFields(); for(Field f : fields) {
		 * Fields.put(f.getName(), ""); }
		 */

		return "success";
	}

	/**
	 * @return the applyInvoiceList
	 */

	public Rlgl100101Bean getRlgl100101Bean() {
		return rlgl100101Bean;
	}

	public void setRlgl100101Bean(Rlgl100101Bean rlgl100101Bean) {
		this.rlgl100101Bean = rlgl100101Bean;
	}

	public IRlgl100101Service getRlgl100101Service() {
		return rlgl100101Service;
	}

	public void setRlgl100101Service(IRlgl100101Service rlgl100101Service) {
		this.rlgl100101Service = rlgl100101Service;
	}

	public Rlgl100101_1Bean getRlgl100101_1Bean() {
		return rlgl100101_1Bean;
	}

	public void setRlgl100101_1Bean(Rlgl100101_1Bean rlgl100101_1Bean) {
		this.rlgl100101_1Bean = rlgl100101_1Bean;
	}
}
