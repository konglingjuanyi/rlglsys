package com.rlglsys.bean;

import com.rlglsys.entity.DBCommon;

public class Rlgl500101Bean extends DBCommon {
	private static final long serialVersionUID = 1L;

	// 用户ID
	private String user_id = "";

	// 消费类别
	private String category = "";
	// 消费时间
	private String time = "";

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
