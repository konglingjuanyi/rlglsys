package com.rlglsys.entity;

import java.io.Serializable;

public class MTb81CourseExams extends DBCommon implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 课程编号
	 */
	private String course_id    ="";
	/**
	 * 课程名
	 */
	private String course_name   ="";
	/**
	 * 学分
	 */
	private String credit   ="";    
	/**
	 * 考试时间
	 */
	private String exams_time   ="";
	/**
	 * 学分年度
	 */
	private String credit_year   =""; 
	/**
	 * 学分类别
	 */
	private String credit_category   ="";
	/**
	 * 用户编号
	 */
	private String user_id="";
	
	/**
	 * 课件价格
	 */
	private String course_price ="";
	public String getCourse_price() {
		return course_price;
	}
	public void setCourse_price(String course_price) {
		this.course_price = course_price;
	}
	/**
	 * 是否申请
	 */
	private String isapply="";
	
	public String getIsapply() {
		return isapply;
	}
	public void setIsapply(String isapply) {
		this.isapply = isapply;
	}
	// 查询件数
    private String count = "";
    // 每页显示条数
 	private int pageCount = 0;
 	// 页数
 	private int pageNo = 0;
    // 总条数
  	private int recordCount = 0;
    // 当前页
  	private String txtInputPage = "";
	/**
	 * @return the course_id
	 */
	public String getCourse_id() {
		return course_id;
	}
	/**
	 * @param course_id the course_id to set
	 */
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	/**
	 * @return the course_name
	 */
	public String getCourse_name() {
		return course_name;
	}
	/**
	 * @param course_name the course_name to set
	 */
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	/**
	 * @return the credit
	 */
	public String getCredit() {
		return credit;
	}
	/**
	 * @param credit the credit to set
	 */
	public void setCredit(String credit) {
		this.credit = credit;
	}
	/**
	 * @return the exams_time
	 */
	public String getExams_time() {
		return exams_time;
	}
	/**
	 * @param exams_time the exams_time to set
	 */
	public void setExams_time(String exams_time) {
		this.exams_time = exams_time;
	}
	/**
	 * @return the credit_year
	 */
	public String getCredit_year() {
		return credit_year;
	}
	/**
	 * @param credit_year the credit_year to set
	 */
	public void setCredit_year(String credit_year) {
		this.credit_year = credit_year;
	}
	/**
	 * @return the credit_category
	 */
	public String getCredit_category() {
		return credit_category;
	}
	/**
	 * @param credit_category the credit_category to set
	 */
	public void setCredit_category(String credit_category) {
		this.credit_category = credit_category;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
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
	public int getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public String getTxtInputPage() {
		return txtInputPage;
	}
	public void setTxtInputPage(String txtInputPage) {
		this.txtInputPage = txtInputPage;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	} 
	

}
