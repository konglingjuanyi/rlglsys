package com.rlglsys.service.impl;
import java.util.ArrayList;
import java.util.List;

import com.rlglsys.entity.MTb81CourseExams;
import com.rlglsys.mapper.IRlgl022004Mapper;
import com.rlglsys.service.IRlgl022004Service;
public class Rlgl022004ServiceImpl implements IRlgl022004Service{
	private IRlgl022004Mapper rlgl022004Mapper;

	/**
	 * 依据用户编号和学分年度得到通过考试的课程信息
	 */
	@Override
	public List<MTb81CourseExams> getCourseExamsList(MTb81CourseExams courseExam) {
			
		List<MTb81CourseExams>  courseExamsList = new ArrayList<MTb81CourseExams>(10);
		try{
			courseExamsList = rlgl022004Mapper.getCourseExamsList(courseExam);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return courseExamsList;
	}
	
	/**
	 * 依据用户编号和学分年度得到通过考试的课程信息
	 */
	@Override
	public List<MTb81CourseExams> getCourseCreditList(MTb81CourseExams courseExam) {
			
		List<MTb81CourseExams>  courseExamsList = new ArrayList<MTb81CourseExams>(10);
		try{
			courseExamsList = rlgl022004Mapper.getCourseCreditList(courseExam);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return courseExamsList;
	}
	
	/**
	 * 得到数据条数
	 */
	@Override
	public int getCourseExamsCount(String userId, String credit_year,
			String course_name,String credit_category) {
		int resultCount =0;
		try{
			resultCount = rlgl022004Mapper.getCourseExamsCount(userId, credit_year, course_name,credit_category);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return resultCount;
	}
	
	/**
	 * 得到数据条数
	 */
	@Override
	public int getCourseCreditSum(String userId, String credit_year,
			String credit_category) {
		int resultCount =0;
		try{
			resultCount = rlgl022004Mapper.getCourseCreditSum(userId, credit_year, credit_category);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return resultCount;
	}
	
	/**
	 * 得到学分数据条数
	 */
	@Override
	public int getCourseCreditCount(String userId, String credit_year) 
	{
		int resultCount =0;
		try{
			resultCount = rlgl022004Mapper.getCourseCreditCount(userId, credit_year);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return resultCount;
	}
	
	public IRlgl022004Mapper getRlgl022004Mapper() {
		return rlgl022004Mapper;
	}

	public void setRlgl022004Mapper(IRlgl022004Mapper rlgl022004Mapper) {
		this.rlgl022004Mapper = rlgl022004Mapper;
	}

	
}
