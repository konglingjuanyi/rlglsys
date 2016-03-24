package com.rlglsys.bean;

import java.io.Serializable;
import com.rlglsys.entity.Mtb63CourseWare;

public class Rlgl100102Bean extends Mtb63CourseWare implements Serializable {

    private static final long serialVersionUID = 3096154202413606831L;

	private int pageCount = 0;
 	private int pageNo = 0;
 	private String course_sel_check = "";
 	private String check_flg = "";
 	private String error_flg = "";
 	private String course_id_sel = "";
 	private String course_name_sel = "";
 	private String user_id = "";
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	// 课件所属类别
	private String course_catagory_nm;
	
	/**
	 * 课件难易程度
	 */
	private String course_level_nm;
	/**
	 * 课件提供方
	 */
	private String course_support_nm;
	
 	
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public String getCourse_sel_check() {
		return course_sel_check;
	}
	public void setCourse_sel_check(String course_sel_check) {
		this.course_sel_check = course_sel_check;
	}

	public String getCourse_catagory_nm() {
		return course_catagory_nm;
	}
	public void setCourse_catagory_nm(String course_catagory_nm) {
		this.course_catagory_nm = course_catagory_nm;
	}
	public String getCourse_level_nm() {
		return course_level_nm;
	}
	public void setCourse_level_nm(String course_level_nm) {
		this.course_level_nm = course_level_nm;
	}
	public String getCourse_support_nm() {
		return course_support_nm;
	}
	public void setCourse_support_nm(String course_support_nm) {
		this.course_support_nm = course_support_nm;
	}
	public String getCheck_flg() {
		return check_flg;
	}
	public void setCheck_flg(String check_flg) {
		this.check_flg = check_flg;
	}
	public String getError_flg() {
		return error_flg;
	}
	public void setError_flg(String error_flg) {
		this.error_flg = error_flg;
	}
	public String getCourse_id_sel() {
		return course_id_sel;
	}
	public void setCourse_id_sel(String course_id_sel) {
		this.course_id_sel = course_id_sel;
	}
	public String getCourse_name_sel() {
		return course_name_sel;
	}
	public void setCourse_name_sel(String course_name_sel) {
		this.course_name_sel = course_name_sel;
	}
}

