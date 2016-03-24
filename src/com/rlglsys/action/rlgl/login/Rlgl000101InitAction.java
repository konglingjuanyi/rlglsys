package com.rlglsys.action.rlgl.login;

import com.rlglsys.base.BaseAction;
public class Rlgl000101InitAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String returnFlg;
	@Override
	public String doExecute() throws Exception {
		return "show";
	}
	
	public String getReturnFlg() {
		return returnFlg;
	}
	public void setReturnFlg(String returnFlg) {
		this.returnFlg = returnFlg;
	}
}