package com.rlglsys.action.learnonline;

import com.rlglsys.base.BaseAction;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.util.Constant;
import com.rlglsys.service.IRlgl100101Service;
import com.rlglsys.bean.Rlgl500102Bean;

public class Rlgl1001003InitAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	// 用户名
	private String Name = "";
	// 账户余额
	private String Amount = "";
	// 获取余额信息
	private IRlgl100101Service rlgl100101Service;

	public IRlgl100101Service getRlgl100101Service() {
		return rlgl100101Service;
	}

	public void setRlgl100101Service(IRlgl100101Service rlgl100101Service) {
		this.rlgl100101Service = rlgl100101Service;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	@Override
	protected String doExecute() throws Exception {

		// 用户对象
		Mtb01User loginUser = (Mtb01User) super.getSession(Constant.SESSION_KEY_LOGINUSER);
		this.Name = loginUser.getUser_name();
		Rlgl500102Bean rlgl500102Bean = new Rlgl500102Bean();
		rlgl500102Bean.setUser_id(loginUser.getUser_id());
		Rlgl500102Bean l500102Bean = new Rlgl500102Bean();
		l500102Bean = rlgl100101Service.getBalanceData(rlgl500102Bean);
		double paymoney = 0;
		if (l500102Bean != null)
			paymoney = l500102Bean.getBalance();

		this.Amount = String.valueOf(paymoney);
		return "success";
	}
}
