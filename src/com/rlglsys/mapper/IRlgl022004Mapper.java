package com.rlglsys.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.rlglsys.entity.MTb81CourseExams;
public interface IRlgl022004Mapper {
	/**
	 * 依据用户编号和学分年度得到通过考试的课程信息
	 * @param courseExam 
	 * @return 
	 */
	public List<MTb81CourseExams>  getCourseExamsList(@Param(value = "courseExam")MTb81CourseExams courseExam);
	/**
	 * 依据查询条件得到数据条数
	 * @param userId 身份证号
	 * @param credit_year 学分年度
	 * @param course_name 课程名称
	 * @return
	 */
	public int getCourseExamsCount(@Param(value = "userId")String userId, 
			                        @Param(value = "credit_year")String credit_year,
			                        @Param(value = "course_name")String course_name,
			                        @Param(value = "credit_category")String credit_category);
	
	/**
	 * 依据查询条件得到数据条数
	 * @param userId 身份证号
	 * @param credit_year 学分年度
	 * @return
	 */
	public int getCourseCreditCount(@Param(value = "userId")String userId, 
			                        @Param(value = "credit_year")String credit_year);
	
	/**
	 * 依据查询条件得到数据条数
	 * @param userId 身份证号
	 * @param credit_year 学分年度
	 * @param credit_category 学分分类
	 * @return
	 */
	public int getCourseCreditSum(@Param(value = "userId")String userId, 
			                        @Param(value = "credit_year")String credit_year,
			                        @Param(value = "credit_category")String credit_category);
	
	/**
	 * 依据用户编号和学分年度得到通过考试的课程信息
	 * @param courseExam 
	 * @return 
	 */
	public List<MTb81CourseExams>  getCourseCreditList(@Param(value = "courseExam")MTb81CourseExams courseExam);
	
}
