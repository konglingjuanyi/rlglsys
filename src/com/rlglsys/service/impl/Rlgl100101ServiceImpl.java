package com.rlglsys.service.impl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.rlglsys.bean.Rlgl100101Bean;
import com.rlglsys.bean.Rlgl100101_1Bean;
import com.rlglsys.bean.Rlgl500102Bean;
import com.rlglsys.bean.Rlgl500103Bean;
import com.rlglsys.entity.DBCommon;
import com.rlglsys.entity.MTb81CourseExams;
import com.rlglsys.entity.TTb02PrepayMsg;
import com.rlglsys.mapper.IRlgl100101Mapper;
import com.rlglsys.service.IRlgl100101Service;
import com.rlglsys.util.BeanFactory;
import com.rlglsys.util.DateTimeManager;
import com.rlglsys.util.DesUtil;

public class Rlgl100101ServiceImpl implements IRlgl100101Service {

	private IRlgl100101Mapper rlgl100101Mapper;

	public IRlgl100101Mapper getRlgl100101Mapper() {
		return rlgl100101Mapper;
	}

	public void setRlgl100101Mapper(IRlgl100101Mapper rlgl100101Mapper) {
		this.rlgl100101Mapper = rlgl100101Mapper;
	}

	@Override
	public Rlgl100101Bean getData(Rlgl100101Bean rlgl100101Bean) {
		return rlgl100101Mapper.getData(rlgl100101Bean);
	}

	@Override
	public int insertData(Rlgl100101_1Bean rlgl100101_1Bean) {
		// 付费数据加密
		rlgl100101_1Bean = necBean(rlgl100101_1Bean);
		return rlgl100101Mapper.insertData(rlgl100101_1Bean);
	}

	// 加密
	private Rlgl100101_1Bean necBean(Rlgl100101_1Bean rlgl100101_1Bean) {
		// 排除共同列加密
		Field[] fields = DBCommon.class.getDeclaredFields();
		Map<String, String> B01fields = new HashMap<String, String>();
		for (Field f : fields) {
			// 更新用户及时间也要加密
			if (!(f.getName().contains("login") || f.getName().contains("update"))) {
				B01fields.put(f.getName(), "");
			}
		}
		B01fields.put("userId", "");
		B01fields.put("clum001", "");
		B01fields.put("MerchantID", "");
		B01fields.put("clum002", "");
		B01fields.put("pay_date", "");
		B01fields.put("clum005", "");
		B01fields.put("pay_year", "");
		B01fields.put("clum006", "");
		rlgl100101_1Bean.setClum001(rlgl100101_1Bean.getUserId());
		rlgl100101_1Bean.setClum002(rlgl100101_1Bean.getMerchantID());
		rlgl100101_1Bean.setClum003(rlgl100101_1Bean.getTransactionID());
		rlgl100101_1Bean.setClum004(rlgl100101_1Bean.getSucceed());
		rlgl100101_1Bean.setClum005(rlgl100101_1Bean.getPay_date());
		rlgl100101_1Bean.setClum006(rlgl100101_1Bean.getPay_year());
		rlgl100101_1Bean.setClum007(rlgl100101_1Bean.getRefund_flag());
		rlgl100101_1Bean.setClum008(rlgl100101_1Bean.getAmount());
		rlgl100101_1Bean.setClum009(rlgl100101_1Bean.getRefund_time());

		// 付费数据加密
		BeanFactory bf = new BeanFactory();
		bf.reinstallFields(rlgl100101_1Bean, DesUtil.class, "enc" + "rypt", B01fields);
		return rlgl100101_1Bean;
	}

	@Override
	public int updateData(Rlgl100101_1Bean rlgl100101_1Bean) {
		// 更新我的余额
		double Pay = Double.parseDouble(rlgl100101_1Bean.getAmount());
		Rlgl500102Bean rlgl500102Bean = new Rlgl500102Bean();
		rlgl500102Bean.setUser_id(rlgl100101_1Bean.getUserId());
		rlgl500102Bean.setLogin_user_id(rlgl100101_1Bean.getUserId());
		rlgl500102Bean.setUpdate_user_id(rlgl100101_1Bean.getUserId());
		rlgl500102Bean.setLogin_date(DateTimeManager.getCurrentDateStrFormat());
		rlgl500102Bean.setUpdate_date(DateTimeManager.getCurrentDateStrFormat());
		Rlgl500102Bean rlgl500102 = rlgl100101Mapper.getBalanceData(rlgl500102Bean);
	int count = 0;
		
		rlgl500102Bean.setBalance(Pay);
		
		//if (rlgl500102 != null) {
			
		//	count = rlgl100101Mapper.updateBalance(rlgl500102Bean);
            
		//} else {
			
		//	count = rlgl100101Mapper.insertBalanceData(rlgl500102Bean);
			
		//}

		// 付费数据加密
		int jfCount = 0;
		if (count > 0) {
			rlgl100101_1Bean = necBean(rlgl100101_1Bean);
			jfCount = rlgl100101Mapper.updateData(rlgl100101_1Bean);
		}
		return jfCount;
	}

	//余额更新
	@Override
	public int updateBalance(Rlgl500102Bean rlgl500102Bean) 
	{
		
		return rlgl100101Mapper.updateBalance(rlgl500102Bean);
		
	}
	
	//余额更新
	@Override
	public int updateAdminBalance(Rlgl500102Bean rlgl500102Bean) 
	{
		return rlgl100101Mapper.updateAdminBalance(rlgl500102Bean);
		
	}
	@Override
	public Rlgl100101_1Bean getData2(Rlgl100101_1Bean rlgl100101_1Bean) {
		rlgl100101_1Bean.setSucceed("1");
		// 付费数据加密
		rlgl100101_1Bean = necBean(rlgl100101_1Bean);
		return rlgl100101Mapper.getData2(rlgl100101_1Bean);
	}

	@Override
	public int getData3(Rlgl100101_1Bean rlgl100101_1Bean) {
		rlgl100101_1Bean.setSucceed("1");
		// 付费数据加密
		rlgl100101_1Bean = necBean(rlgl100101_1Bean);
		return rlgl100101Mapper.getData3(rlgl100101_1Bean);
	}

	public int getData4(Rlgl100101_1Bean rlgl100101_1Bean) {
		// 付费数据加密
		rlgl100101_1Bean = necBean(rlgl100101_1Bean);
		return rlgl100101Mapper.getData4(rlgl100101_1Bean);
	}

	public Rlgl100101_1Bean getData5(Rlgl100101_1Bean rlgl100101_1Bean) {
		rlgl100101_1Bean.setSucceed("1");
		// 付费数据加密
		rlgl100101_1Bean = necBean(rlgl100101_1Bean);
		return rlgl100101Mapper.getData5(rlgl100101_1Bean);
	}

	@Override
	public List<TTb02PrepayMsg> getJFInfoByUserId(Rlgl100101_1Bean rlgl100101_1Bean) {
		List<TTb02PrepayMsg> jFInfoList = null;
		try {
			jFInfoList = rlgl100101Mapper.getJFInfoByUserId(rlgl100101_1Bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jFInfoList;
	}

	@Override
	public int insertBalanceData(Rlgl500102Bean rlgl500102Bean) {

		try {
			return rlgl100101Mapper.insertBalanceData(rlgl500102Bean);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Rlgl500102Bean getBalanceData(Rlgl500102Bean rlgl500102Bean) {

		try {
			return rlgl100101Mapper.getBalanceData(rlgl500102Bean);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int insertRecordData(Rlgl500103Bean rlgl500103Bean) {

		try {
			return rlgl100101Mapper.insertRecordData(rlgl500103Bean);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateBalanceData(Rlgl500102Bean rlgl500102Bean) {

		try {
			return rlgl100101Mapper.updateBalanceData(rlgl500102Bean);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateMtb81CourseExams(MTb81CourseExams mtb81Bean) {

		try {
			return rlgl100101Mapper.updateMtb81CourseExams(mtb81Bean);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getUserData(String user_id, String email) {

		try {
			String count = rlgl100101Mapper.getUserData(user_id, email);
			if (count == null || count == "") {
				return 0;
			} else {
				return Integer.parseInt(count);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

}
