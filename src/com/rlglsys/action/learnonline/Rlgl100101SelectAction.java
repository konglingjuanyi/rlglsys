package com.rlglsys.action.learnonline;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl100101_1Bean;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IMerchantInfoService;
import com.rlglsys.service.IRlgl050101Service;
import com.rlglsys.service.IRlgl100101Service;

public class Rlgl100101SelectAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private IRlgl100101Service rlgl100101Service;
	private String TransactionID = "";
	private String MerchantID = "";
	private String flg = "";
	private String successFlg = "";
	private IMTb02AdmService mtb02AdmService;
	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}


	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
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


	private IMerchantInfoService merchantInfoService;
	private IRlgl050101Service rlgl050101Service;

	public String getFlg() {
		return flg;
	}


	public String getSuccessFlg() {
		return successFlg;
	}


	public void setSuccessFlg(String successFlg) {
		this.successFlg = successFlg;
	}


	public void setFlg(String flg) {
		this.flg = flg;
	}


	public String getMerchantID() {
		return MerchantID;
	}


	public void setMerchantID(String merchantID) {
		MerchantID = merchantID;
	}


	public String getTransactionID() {
		return TransactionID;
	}


	public void setTransactionID(String transactionID) {
		TransactionID = transactionID;
	}


	public IRlgl100101Service getRlgl100101Service() {
		return rlgl100101Service;
	}


	public void setRlgl100101Service(IRlgl100101Service rlgl100101Service) {
		this.rlgl100101Service = rlgl100101Service;
	}


	@Override
	protected String doExecute() throws Exception {
		if (TransactionID != null || !"".equals(TransactionID)) {
			//super.GetPrepay(rlgl100101Service, mtb02AdmService, rlgl050101Service, merchantInfoService);
			// 以交易号为key，看t_tb02_prepay_msg表中交易数据是否存在
			Rlgl100101_1Bean rlgl100101_1Bean = new Rlgl100101_1Bean();
			rlgl100101_1Bean.setTransactionID(TransactionID);
			Rlgl100101_1Bean rlgl100101_1Bean1 = null;
			
			try {
			    rlgl100101_1Bean1 = rlgl100101Service.getData2(rlgl100101_1Bean);
		    } catch (Exception e) {
			    // TODO: handle exception
			    System.out.println(e);
		    }
			if (rlgl100101_1Bean1 != null) {
				// 交易数据存在，交易成功
				flg = "0";
			} else {
				// 交易数据不存在，交易失败
				flg = "1";
			}
		}
		TransactionID="";
		successFlg = flg;
		return "success";
	}
}
