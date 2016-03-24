package com.rlglsys.action.learnonline;

import com.rlglsys.base.BaseAction;

public class forgetPasswordAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String user_id1 = "";
	private String email = "";

	public String getUser_id1() {
		return user_id1;
	}

	public void setUser_id1(String user_id1) {
		this.user_id1 = user_id1;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	protected String doExecute() throws Exception {
		
		return "init";
	}
}
