package com.rlglsys.bean;

import com.rlglsys.entity.DBCommon;

public class Rlgl500103Bean extends DBCommon {
	private static final long serialVersionUID = 1L;

	// 用户ID
	private String user_id = "";
	// 消费类别
	private String Category = "";
	// 消费时间
	private String Time = "";
	// 消费项目
	private String Name = "";
	// 消费项目
		private String COURSE_ID = "";
	

	public String getCOURSE_ID() {
			return COURSE_ID;
		}

		public void setCOURSE_ID(String cOURSE_ID) {
			COURSE_ID = cOURSE_ID;
		}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

}
