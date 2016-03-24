package com.rlglsys.service;

import java.util.List;

import com.rlglsys.entity.MTb81CourseExams;

public interface IRlgl022004Service {
	/**
	 * 依据用户编号和学分年度得到通过考试的课程信息
	 * 
	 * @param courseExam
	 * @return
	 */
	public List<MTb81CourseExams> getCourseExamsList(MTb81CourseExams courseExam);

	/**
	 * 依据用户编号和学分年度得到通过考试的课程信息
	 * 
	 * @param courseExam
	 * @return
	 */
	public List<MTb81CourseExams> getCourseCreditList(MTb81CourseExams courseExam);

	/**
	 * 依据查询条件的到符合条件的数据条数
	 * 
	 * @param userId
	 *            身份证号
	 * @param credit_year
	 *            学分年度
	 * @param course_name
	 *            课程名称
	 * @return
	 */
	public int getCourseExamsCount(String userId, String credit_year, String course_name,String credit_category);
	
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
	 * 依据查询条件的到符合条件的数据条数
	 * 
	 * @param userId
	 *            身份证号
	 * @param credit_year
	 *            学分年度
	 * @return
	 */
	public int getCourseCreditCount(String userId, String credit_year);

}
