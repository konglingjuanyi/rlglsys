package com.rlglsys.bean;

import com.rlglsys.entity.DBCommon;

public class Rlgl500102Bean extends DBCommon {
	private static final long serialVersionUID = 1L;

	// 用户ID
	private String user_id = "";

	// 账户余额
	private double Balance = 0;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public double getBalance() {
		return Balance;
	}

	public void setBalance(double balance) {
		Balance = balance;
	}
	
}
