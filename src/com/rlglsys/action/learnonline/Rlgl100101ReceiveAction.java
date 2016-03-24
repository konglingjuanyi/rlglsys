package com.rlglsys.action.learnonline;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl100101_1Bean;
import com.rlglsys.service.IRlgl050101Service;
import com.rlglsys.service.IRlgl100101Service;
import com.rlglsys.util.DateTimeManager;

public class Rlgl100101ReceiveAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private String MerId;
	private String status;
	private Rlgl100101_1Bean rlgl100101_1Bean;
	private IRlgl100101Service rlgl100101Service;

	public IRlgl100101Service getRlgl100101Service() {
		return rlgl100101Service;
	}

	public void setRlgl100101Service(IRlgl100101Service rlgl100101Service) {
		this.rlgl100101Service = rlgl100101Service;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMerId() {
		return MerId;
	}

	public void setMerId(String merId) {
		MerId = merId;
	}

	@Override
	protected String doExecute() throws Exception {
		// 交易完成（成功）返回后，往t_tb02_prepay_msg表中插入交易数据
		rlgl100101_1Bean = (Rlgl100101_1Bean)super.getRequest();
		int year = Integer.parseInt(DateTimeManager.getSystemDate14().substring(0,4));
		String year1 = String.valueOf(year);
		rlgl100101_1Bean.setPay_year(year1);
		rlgl100101Service.insertData(rlgl100101_1Bean);
	  
		if (status != null && "1001".equals(status)) {
			
		} else {
			
			return "error";
		}
		return "init"; 
	}
}
