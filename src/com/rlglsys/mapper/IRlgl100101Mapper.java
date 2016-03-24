package com.rlglsys.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rlglsys.bean.Rlgl100101Bean;
import com.rlglsys.bean.Rlgl100101_1Bean;
import com.rlglsys.bean.Rlgl500102Bean;
import com.rlglsys.bean.Rlgl500103Bean;
import com.rlglsys.entity.MTb81CourseExams;
import com.rlglsys.entity.TTb02PrepayMsg;
public interface IRlgl100101Mapper {
	/**
	 * 查询预付费
	 * @param Rlgl100101Bean
	 * @return
	 */
	public Rlgl100101Bean getData(@Param(value = "rlgl100101Bean")Rlgl100101Bean rlgl100101Bean);
	/**
	 * 查询预付费
	 * @param Rlgl100101Bean
	 * @return
	 */
	public int insertData(@Param(value = "rlgl100101_1Bean")Rlgl100101_1Bean rlgl100101_1Bean);
	/**
	 * 查询预付费
	 * @param Rlgl100101Bean
	 * @return
	 */
	public int updateData(@Param(value = "rlgl100101_1Bean")Rlgl100101_1Bean rlgl100101_1Bean);
	/**
	 * 查询预付费
	 * @param Rlgl100101Bean
	 * @return
	 */
	public Rlgl100101_1Bean getData2(@Param(value = "rlgl100101_1Bean")Rlgl100101_1Bean rlgl100101_1Bean);
	/**
	 * 查询预付费
	 * @param Rlgl100101Bean
	 * @return
	 */
	public int getData3(@Param(value = "rlgl100101_1Bean")Rlgl100101_1Bean rlgl100101_1Bean);
	
	public int getData4(@Param(value = "rlgl100101_1Bean")Rlgl100101_1Bean rlgl100101_1Bean);
	
	public Rlgl100101_1Bean getData5(@Param(value = "rlgl100101_1Bean")Rlgl100101_1Bean rlgl100101_1Bean);
	/**
	 * 查询个人缴费信息
	 * @param rlgl100101_1Bean
	 * @return
	 */
	public List<TTb02PrepayMsg> getJFInfoByUserId(@Param(value = "rlgl100101_1Bean")Rlgl100101_1Bean rlgl100101_1Bean);

	/**
	 * 插入数据
	 * @param rlgl500102Bean
	 * @return
	 */
	public int insertBalanceData(@Param(value = "rlgl500102Bean")Rlgl500102Bean rlgl500102Bean);
	
	/**
	 * 取得数据
	 * @param rlgl500102Bean
	 * @return
	 */
	public Rlgl500102Bean getBalanceData(@Param(value = "rlgl500102Bean")Rlgl500102Bean rlgl500102Bean);
	
	/**
	 * 插入数据
	 * @param rlgl500103Bean
	 * @return
	 */
	public int insertRecordData(@Param(value = "rlgl500103Bean")Rlgl500103Bean rlgl500103Bean);
	
	/**
	 * 更新余额
	 * @param rlgl500102Bean
	 * @return
	 */
	public int updateBalanceData(@Param(value = "rlgl500102Bean")Rlgl500102Bean rlgl500102Bean);
	
	/**
	 * 更新余额
	 * @param rlgl500102Bean
	 * @return
	 */
	public int updateBalance(@Param(value = "rlgl500102Bean")Rlgl500102Bean rlgl500102Bean);
	
	/**
	 * 更新余额
	 * @param rlgl500102Bean
	 * @return
	 */
	public int updateAdminBalance(@Param(value = "rlgl500102Bean")Rlgl500102Bean rlgl500102Bean);
	
	
	/**
	 * 更新考试结果
	 * @param mtb81Bean
	 * @return
	 */
	public int updateMtb81CourseExams(@Param(value = "mtb81Bean")MTb81CourseExams mtb81Bean);
	
	
	/**
	 * 更新考试结果
	 * @param user_id
	 * @param email
	 * @return
	 */
	public String getUserData(@Param(value = "user_id")String user_id,@Param(value = "email")String email);
	
	
}
