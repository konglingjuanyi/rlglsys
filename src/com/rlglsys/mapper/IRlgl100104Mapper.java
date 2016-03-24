package com.rlglsys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rlglsys.bean.Rlgl100104Bean;

public interface IRlgl100104Mapper {
	/**
	 * 查询支付成功课件
	 * @param rlgl100104Bean
	 * @return
	 */
	public List<Rlgl100104Bean> selectCourseSelected(@Param(value = "rlgl100104Bean")Rlgl100104Bean rlgl100104Bean);
	/**
	 * 查询课件简介
	 * @param rlgl100104Bean
	 * @return
	 */
	public int UpdateLearnState(@Param(value = "rlgl100104Bean")Rlgl100104Bean rlgl100104Bean);
	/**
	 * 删除已选课程
	 * @param ids 课程编号
	 * @param userid 用户id
	 */
	public void deleteCourse(@Param(value = "ids")List<String> ids,@Param(value = "userid")String userid);
	/**
	 * 查询公共课试题
	 * @param rlgl100104Bean
	 * @return
	 */
	public List<Rlgl100104Bean> selectPublicCourse(@Param(value = "rlgl100104Bean")Rlgl100104Bean rlgl100104Bean);
	
	/**
	 * 查询公共课已选考试题
	 * @param rlgl100104Bean
	 * @return
	 */
	public List<Rlgl100104Bean> selectPublicSelectCourse(@Param(value = "rlgl100104Bean")Rlgl100104Bean rlgl100104Bean);
	
	
	/**
	 * 查询公共课只考试题
	 * @param rlgl100104Bean
	 * @return
	 */
	public List<Rlgl100104Bean> selectPublicSLearnCourse(@Param(value = "rlgl100104Bean")Rlgl100104Bean rlgl100104Bean);
	
	/**
	 * 取得通过在线学习数
	 * @param rlgl100104Bean
	 * @return
	 */
	public int selectCourseCount(@Param(value = "rlgl100104Bean")Rlgl100104Bean rlgl100104Bean);
	/**
	 * 取得通过公共课数
	 * @param rlgl100104Bean
	 * @return
	 */
	public int selectPublicCourseCount(@Param(value = "rlgl100104Bean")Rlgl100104Bean rlgl100104Bean);
}
