package com.rlglsys.service;

import java.util.List;
import com.rlglsys.entity.MTb02Adm;

public interface IMTb02AdmService {
	public List<MTb02Adm> getAdmInfo(String admtypecd);
	public String getAdmName(String admtypecd, String admnum);
	public String getAdmNum(String admtypecd, String admName);
	// 根据管理类别区分和扩展域01取List
	public List<MTb02Adm> getAdmInfo(String admtypecd,String expend_01);
	// 根据管理类别区分和扩展域01，扩展域02取List
	public List<MTb02Adm> getAdmInfo(String admtypecd,String expend_01,String expend_02);
	public List<MTb02Adm> getAdmExamInfo(String admValue01);
	
	/**
	 * 依据查询条件的到符合条件的数据条数
	 * 
	 * @param userId
	 *            身份证号
	 * @param credit_year
	 *            学分年度
	 * @param credit_category
	 *            学分类别
	 * @return
	 */
	public int getCourseCreditSum(String userId, String credit_year, String credit_category);
	
	/**
	 * 根据课程二级类别编号得到课程三级类别列表
	 * @param course_catagory 课程二级类别
	 * @return
	 */
	public List<MTb02Adm> getThreeList(String course_catagory);

}
