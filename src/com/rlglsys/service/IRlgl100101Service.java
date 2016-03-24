package com.rlglsys.service;

import java.util.List;

import com.rlglsys.bean.Rlgl100101Bean;
import com.rlglsys.bean.Rlgl100101_1Bean;
import com.rlglsys.bean.Rlgl500102Bean;
import com.rlglsys.bean.Rlgl500103Bean;
import com.rlglsys.entity.MTb81CourseExams;
import com.rlglsys.entity.TTb02PrepayMsg;

public interface IRlgl100101Service {
	/**
	 * 查询预付费
	 * 
	 * @param rlgl022005Bean
	 * @return
	 */
	public Rlgl100101Bean getData(Rlgl100101Bean rlgl100101Bean) throws Exception;

	public int insertData(Rlgl100101_1Bean rlgl100101_1Bean) throws Exception;

	public int updateData(Rlgl100101_1Bean rlgl100101_1Bean) throws Exception;

	public Rlgl100101_1Bean getData2(Rlgl100101_1Bean rlgl100101_1Bean) throws Exception;

	public int getData3(Rlgl100101_1Bean rlgl100101_1Bean) throws Exception;

	public int getData4(Rlgl100101_1Bean rlgl100101_1Bean) throws Exception;

	public Rlgl100101_1Bean getData5(Rlgl100101_1Bean rlgl100101_1Bean) throws Exception;

	// 查询个人的缴费信息
	public List<TTb02PrepayMsg> getJFInfoByUserId(Rlgl100101_1Bean rlgl100101_1Bean);

	// 余额登录
	public int insertBalanceData(Rlgl500102Bean rlgl500102Bean) throws Exception;

	// 查询余额
	public Rlgl500102Bean getBalanceData(Rlgl500102Bean rlgl500102Bean) throws Exception;

	// 消费记录登陆
	public int insertRecordData(Rlgl500103Bean rlgl500103Bean) throws Exception;

	// 余额更新
	public int updateBalanceData(Rlgl500102Bean rlgl500102Bean) throws Exception;
	// 余额更新添加
    public int updateBalance(Rlgl500102Bean rlgl500102Bean) throws Exception;
    
	// 管理员修改余额
    public int updateAdminBalance(Rlgl500102Bean rlgl500102Bean) throws Exception;

	// 更新
	public int updateMtb81CourseExams(MTb81CourseExams mtb81Bean) throws Exception;
	
	//查找数据
	public int getUserData(String user_id, String email)throws Exception;

}
