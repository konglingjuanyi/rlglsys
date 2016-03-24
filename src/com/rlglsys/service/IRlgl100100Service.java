package com.rlglsys.service;

import java.util.List;
import com.rlglsys.bean.Rlgl100101_1Bean;
import com.rlglsys.entity.MTb80CourseSelected;

public interface IRlgl100100Service {
	/**
	 * 查询个人缴费记录数
	 * 
	 * @param user_id
	 *            用户ID
	 * @return
	 */
	public int getPrepayMsgCount(String user_id, String succeed);

	/**
	 * 查询个人消费记录数
	 * 
	 * @param user_id
	 *            用户ID
	 * @return
	 */
	public int getPrepayMsgTempCount(String user_id, String succeed);

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
	public List<Rlgl100101_1Bean> getPrepayMsgList(String user_id, int pageCount, int pageNo, String succeed);

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
	public List<Rlgl100101_1Bean> getPrepayMsgTempList(String user_id, int pageCount, int pageNo, String succeed);

	/**
	 * 通过缴费订单查询订单包含选课信息列表
	 * 
	 * @param TransactionID
	 *            缴费订单
	 * @return
	 */
	public List<MTb80CourseSelected> getSelectedCourseList(String TransactionID);

	/**
	 * 更新缴费申请退款状态
	 * 
	 * @param rlgl100101_1Bean
	 * @return
	 */
	public int updateTTb02PrepayMsg(Rlgl100101_1Bean rlgl100101_1Bean);

	/**
	 * 查询个人消费记录数
	 * 
	 * @param user_id
	 *            用户ID
	 * @return
	 */
	public int getPrepayMsgAdminCount(String user_id, String succeed, String id);

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
	public List<Rlgl100101_1Bean> getPrepayMsgAdminList(String user_id, int pageCount, int pageNo, String succeed,
			String id);

	/**
	 * 管理员查询个人消费记录数
	 * 
	 * @param user_id
	 *            用户ID
	 * @return
	 */
	public int getPrepayMsgTempAdminCount(String user_id, String succeed, String id);

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
	public List<Rlgl100101_1Bean> getPrepayMsgTempAdminList(String user_id, int pageCount, int pageNo, String succeed,
			String id);

	/**
	 * 管理员查询个人消费记录数
	 * 
	 * @param user_id
	 *            用户ID
	 * @return
	 */
	public int getBalanceAdminCount(String user_id, String succeed, String id);

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
	public List<Rlgl100101_1Bean> getBalanceAdminList(String user_id, int pageCount, int pageNo, String succeed,
			String id);

}
