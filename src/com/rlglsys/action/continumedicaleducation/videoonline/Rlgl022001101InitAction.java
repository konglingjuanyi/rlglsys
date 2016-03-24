package com.rlglsys.action.continumedicaleducation.videoonline;

import com.rlglsys.base.BaseAction;

public class Rlgl022001101InitAction extends BaseAction{
	private static final long serialVersionUID = 1L;

    
	//课程名称
	private String course_name1="";
	//课程编号
	private String course_id1="";
	//画面
	private String PageFlg1 ="";
	//金额
	private String Amount1 ="";
	
	

	

	public String getCourse_name1() {
		return course_name1;
	}





	public void setCourse_name1(String course_name1) {
		this.course_name1 = course_name1;
	}





	public String getCourse_id1() {
		return course_id1;
	}





	public void setCourse_id1(String course_id1) {
		this.course_id1 = course_id1;
	}





	public String getPageFlg1() {
		return PageFlg1;
	}





	public void setPageFlg1(String pageFlg1) {
		PageFlg1 = pageFlg1;
	}





	public String getAmount1() {
		return Amount1;
	}





	public void setAmount1(String amount1) {
		Amount1 = amount1;
	}





	@Override
	protected String doExecute() throws Exception {		
		return "success";
	}
}
