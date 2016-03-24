package com.rlglsys.service;

import java.util.List;

import com.rlglsys.bean.Rlgl100104Bean;

public interface IRlgl100104Service {
	/**
	 * 添加支付成功课件
	 * 
	 * @param rlgl100104Bean
	 * @return
	 */
	public List<Rlgl100104Bean> selectCourseSelected(Rlgl100104Bean rlgl100104Bean);

	/**
	 * 查询课件简介
	 * 
	 * @param rlgl100104Bean
	 * @return
	 */
	public int UpdateLearnState(Rlgl100104Bean rlgl100104Bean);

	/**
	 * 删除已选课程
	 * 
	 * @param ids
	 *            课程编号数组
	 * @param userId
	 *            用户id
	 */
	public void deleteCourse(List<String> ids, String userId);

	/**
	 * 取得公共课试题
	 * 
	 * @param rlgl100104Bean
	 * @return
	 */
	public List<Rlgl100104Bean> selectPublicCourse(Rlgl100104Bean rlgl100104Bean);

	/**
	 * 取得公共课已选考试试题
	 * 
	 * @param rlgl100104Bean
	 * @return
	 */
	public List<Rlgl100104Bean> selectPublicSelectCourse(Rlgl100104Bean rlgl100104Bean);

	/**
	 * 取得公共课已选考试试题
	 * 
	 * @param rlgl100104Bean
	 * @return
	 */
	public List<Rlgl100104Bean> selectPublicSLearnCourse(Rlgl100104Bean rlgl100104Bean);

	/**
	 * 取得通过在线学习数
	 * 
	 * @param rlgl100104Bean
	 * @return
	 */
	public int selectCourseCount(Rlgl100104Bean rlgl100104Bean);

	/**
	 * 取得通过公共课数
	 * 
	 * @param rlgl100104Bean
	 * @return
	 */
	public int selectPublicCourseCount(Rlgl100104Bean rlgl100104Bean);
}
