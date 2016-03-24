package com.rlglsys.entity;

import java.io.Serializable;
import java.util.List;
/**
 * 课件信息实体类
 * @author Administrator
 *
 */
public class Mtb121PublicCourseWare extends DBCommon implements Serializable{
	
	private static final long serialVersionUID = 5952689219411916553L;
	// 课件id
	private String course_id;
	
	// 课件id
		private String credit_year;
	
	public String getCredit_year() {
			return credit_year;
		}
		public void setCredit_year(String credit_year) {
			this.credit_year = credit_year;
		}
		// 用户
		private String user_id;
		
	public String getUser_id() {
			return user_id;
		}
		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}
	// 课件名称
	private String course_name;
	// 课件所属类别
	private String course_catagory;
	// 课件所属类别2
	private String course_catagory2;
	// 课件所属类别3
	private String course_catagory3;
	// 是否全科
	private String isallcourse;
	
	// 可见所属类别2
	private String course_catagory_nm2;
	
	// 课件所属类别3
	private String course_catagory_nm3;
	// 课件来源lIst
	private List<TunitCourse> unitCourseInfoList;
	/**
	 * 课件价格
	 */
	private String course_price;
	/**
	 * 课件难易程度
	 */
	private String course_level;
	/**
	 * 课件提供方
	 */
	private String course_support;
	/**
	 * 本地文件flag
	 */
	private String local_flag;
	// 课件学分
	private double xuefen;
	/**
	 * 学分类别
	 */
	private String xuefen_leibie;
	
	private String course_explain;
	// 课件开始日期
	private String begin_date;
	// 课件结束日期
	private String end_date;
	// 课件是否优惠
	private String youhui_flag;
	// 课件优惠后价格
	private String youhui_price;
	// 是否为视频课件
	private String video_flag;
	/**
	 * 是否为必修课
	 */
	private String bixiu_flag;
    // 适用人群资格
    private String suit_person;
    // 适用人群范围
    private String suit_person_1;
    // 适用地域
    private String suit_area;
    // 适用地域-(省)
    private String suit_province = "";
    // 适用地域-(市)
    private String suit_city = "";
	// 课件负责人
	private String course_expert;
	//课件负责单位
	private String course_expert_unit;
	//视频课程地址
	private String course_url;
	//课程图片地址
	private String course_image_url;
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
  	private String pageFlg = "";
  	private String course_catagory_nm = "";
  	private String course_level_nm = "";
  	private String course_support_nm = "";
  	/**
  	 *  是否必修  00 否   01 是
  	 */
  	private String bixiu;
	
  	private String result = "";
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getCourse_catagory() {
		return course_catagory;
	}
	public void setCourse_catagory(String course_catagory) {
		this.course_catagory = course_catagory;
	}
	
	public double getXuefen() {
		return xuefen;
	}
	public void setXuefen(double xuefen) {
		this.xuefen = xuefen;
	}
	public String getBegin_date() {
		return begin_date;
	}
	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getYouhui_flag() {
		return youhui_flag;
	}
	public void setYouhui_flag(String youhui_flag) {
		this.youhui_flag = youhui_flag;
	}
	public String getYouhui_price() {
		return youhui_price;
	}
	public void setYouhui_price(String youhui_price) {
		this.youhui_price = youhui_price;
	}
	public String getVideo_flag() {
		return video_flag;
	}
	public void setVideo_flag(String video_flag) {
		this.video_flag = video_flag;
	}
	public String getCourse_expert() {
		return course_expert;
	}
	public void setCourse_expert(String course_expert) {
		this.course_expert = course_expert;
	}
	public String getCourse_expert_unit() {
		return course_expert_unit;
	}
	public void setCourse_expert_unit(String course_expert_unit) {
		this.course_expert_unit = course_expert_unit;
	}
	public String getCourse_url() {
		return course_url;
	}
	public String getCourse_image_url() {
		return course_image_url;
	}
	public void setCourse_image_url(String course_image_url) {
		this.course_image_url = course_image_url;
	}
	public void setCourse_url(String course_url) {
		this.course_url = course_url;
	}
	public String getCourse_level() {
		return course_level;
	}
	public void setCourse_level(String course_level) {
		this.course_level = course_level;
	}
	public String getCourse_support() {
		return course_support;
	}
	public void setCourse_support(String course_support) {
		this.course_support = course_support;
	}
	public String getLocal_flag() {
		return local_flag;
	}
	public void setLocal_flag(String local_flag) {
		this.local_flag = local_flag;
	}
	public String getXuefen_leibie() {
		return xuefen_leibie;
	}
	public void setXuefen_leibie(String xuefen_leibie) {
		this.xuefen_leibie = xuefen_leibie;
	}
	public String getCourse_explain() {
		return course_explain;
	}
	public void setCourse_explain(String course_explain) {
		this.course_explain = course_explain;
	}
	public String getBixiu_flag() {
		return bixiu_flag;
	}
	public void setBixiu_flag(String bixiu_flag) {
		this.bixiu_flag = bixiu_flag;
	}
	public String getCourse_price() {
		return course_price;
	}
	public void setCourse_price(String course_price) {
		this.course_price = course_price;
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
	public String getBixiu() {
		return bixiu;
	}
	public void setBixiu(String bixiu) {
		this.bixiu = bixiu;
	}
	public String getSuit_person() {
		return suit_person;
	}
	public void setSuit_person(String suit_person) {
		this.suit_person = suit_person;
	}
	public String getSuit_person_1() {
		return suit_person_1;
	}
	public void setSuit_person_1(String suit_person_1) {
		this.suit_person_1 = suit_person_1;
	}
	public String getSuit_area() {
		return suit_area;
	}
	public void setSuit_area(String suit_area) {
		this.suit_area = suit_area;
	}
	public String getSuit_province() {
		return suit_province;
	}
	public void setSuit_province(String suit_province) {
		this.suit_province = suit_province;
	}
	public String getSuit_city() {
		return suit_city;
	}
	public void setSuit_city(String suit_city) {
		this.suit_city = suit_city;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getPageFlg() {
		return pageFlg;
	}
	public void setPageFlg(String pageFlg) {
		this.pageFlg = pageFlg;
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
	public List<TunitCourse> getUnitCourseInfoList() {
		return unitCourseInfoList;
	}
	public void setUnitCourseInfoList(List<TunitCourse> unitCourseInfoList) {
		this.unitCourseInfoList = unitCourseInfoList;
	}
	public String getCourse_catagory2() {
		return course_catagory2;
	}
	public void setCourse_catagory2(String course_catagory2) {
		this.course_catagory2 = course_catagory2;
	}
	public String getCourse_catagory3() {
		return course_catagory3;
	}
	public void setCourse_catagory3(String course_catagory3) {
		this.course_catagory3 = course_catagory3;
	}
	public String getIsallcourse() {
		return isallcourse;
	}
	public void setIsallcourse(String isallcourse) {
		this.isallcourse = isallcourse;
	}
	public String getCourse_catagory_nm2() {
		return course_catagory_nm2;
	}
	public void setCourse_catagory_nm2(String course_catagory_nm2) {
		this.course_catagory_nm2 = course_catagory_nm2;
	}
	public String getCourse_catagory_nm3() {
		return course_catagory_nm3;
	}
	public void setCourse_catagory_nm3(String course_catagory_nm3) {
		this.course_catagory_nm3 = course_catagory_nm3;
	}
}