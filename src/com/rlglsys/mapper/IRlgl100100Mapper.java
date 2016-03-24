package com.rlglsys.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.rlglsys.bean.Rlgl100101_1Bean;
import com.rlglsys.entity.MTb80CourseSelected;

public interface IRlgl100100Mapper {
	
	/**
	 * 查询个人缴费记录数
	 * 
	 * @param user_id
	 *            用户ID
	 * @return
	 */
	public int getPrepayMsgCount(@Param(value = "user_id") String user_id, @Param(value = "succeed") String succeed);

	/**
	 * 查询个人缴费明细列表
	 * 
	 * @param user_id
	 *            用户ID
	 * @param pageCount
	 *            每页显示条数
	 * @param pageNo
	 *            页号
	 * @return
	 */
	public List<Rlgl100101_1Bean> getPrepayMsgList(@Param(value = "user_id") String user_id,
			@Param(value = "pageCount") int pageCount, @Param(value = "pageNo") int pageNo,
			@Param(value = "succeed") String succeed);

	/**
	 * 查询个人消费记录数
	 * 
	 * @param user_id
	 *            用户ID
	 * @return
	 */
	public int getPrepayMsgTempCount(@Param(value = "user_id") String user_id,
			@Param(value = "succeed") String succeed);
	
	/**
	 * 查询个人消费明细列表
	 * 
	 * @param user_id
	 *            用户ID
	 * @param pageCount
	 *            每页显示条数
	 * @param pageNo
	 *            页号
	 * @return
	 */
	public List<Rlgl100101_1Bean> getPrepayMsgTempList(@Param(value = "user_id") String user_id,
			@Param(value = "pageCount") int pageCount, @Param(value = "pageNo") int pageNo,
			@Param(value = "succeed") String succeed);

	/**
	 * 管理员查询个人缴费记录数
	 * 
	 * @param user_id
	 *            用户ID
	 * @return
	 */
	public int getPrepayMsgAdminCount(@Param(value = "user_id") String user_id,
			@Param(value = "succeed") String succeed,@Param(value = "id") String id);
	
	/**
	 * 管理员查询个人缴费明细列表
	 * 
	 * @param user_id
	 *            用户ID
	 * @param pageCount
	 *            每页显示条数
	 * @param pageNo
	 *            页号
	 * @return
	 */
	public List<Rlgl100101_1Bean> getPrepayMsgAdminList(@Param(value = "user_id") String user_id,
			@Param(value = "pageCount") int pageCount, @Param(value = "pageNo") int pageNo,
			@Param(value = "succeed") String succeed,@Param(value = "id") String id);

	/**
	 * 通过缴费订单查询订单包含选课信息列表
	 * 
	 * @param TransactionID
	 *            缴费订单
	 * @return
	 */
	public List<MTb80CourseSelected> getSelectedCourseList(@Param(value = "TransactionID") String TransactionID);

	/**
	 * 更新缴费申请退款状态
	 * 
	 * @param rlgl100101_1Bean
	 * @return
	 */
	public int updateTTb02PrepayMsg(@Param(value = "rlgl100101_1Bean") Rlgl100101_1Bean rlgl100101_1Bean);
	
	/**
	 * 管理员查询个人缴费记录数
	 * 
	 * @param user_id
	 *            用户ID
	 * @return
	 */
	public int getPrepayMsgTempAdminCount(@Param(value = "user_id") String user_id,
			@Param(value = "succeed") String succeed,@Param(value = "id") String id);
	
	/**
	 * 管理员查询个人缴费明细列表
	 * 
	 * @param user_id
	 *            用户ID
	 * @param pageCount
	 *            每页显示条数
	 * @param pageNo
	 *            页号
	 * @return
	 */
	public List<Rlgl100101_1Bean> getPrepayMsgTempAdminList(@Param(value = "user_id") String user_id,
			@Param(value = "pageCount") int pageCount, @Param(value = "pageNo") int pageNo,
			@Param(value = "succeed") String succeed,@Param(value = "id") String id);
	
	/**
	 * 管理员查询个人缴费记录数
	 * 
	 * @param user_id
	 *            用户ID
	 * @return
	 */
	public int getBalanceAdminCount(@Param(value = "user_id") String user_id,
			@Param(value = "succeed") String succeed,@Param(value = "id") String id);
	
	/**
	 * 管理员查询个人缴费明细列表
	 * 
	 * @param user_id
	 *            用户ID
	 * @param pageCount
	 *            每页显示条数
	 * @param pageNo
	 *            页号
	 * @return
	 */
	public List<Rlgl100101_1Bean> getBalanceAdminList(@Param(value = "user_id") String user_id,
			@Param(value = "pageCount") int pageCount, @Param(value = "pageNo") int pageNo,
			@Param(value = "succeed") String succeed,@Param(value = "id") String id);
}
