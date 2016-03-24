package com.rlglsys.action.learnonline;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl100101_1Bean;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IRlgl050101Service;
import com.rlglsys.service.IRlgl100101Service;
import com.rlglsys.util.Common;
import com.rlglsys.util.Constant;

public class Rlgl100103_2InitAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private IRlgl100101Service rlgl100101Service;
	private IMTb02AdmService  mtb02AdmService;
	private IRlgl050101Service rlgl050101Service;
	
	@Override
	protected String doExecute() throws Exception {
		try {
			// 登陆用户信息
			Mtb01User loginUser = (Mtb01User)super.getSession(Constant.SESSION_KEY_LOGINUSER);
		    String user_id=(String)loginUser.getUser_id();
		    //获得当前年度
		    Common common = new Common();
		    //获得当前学分年度（申请年度）
	    	String area_id = mtb02AdmService.getAdmName("237", "01");
		    String payyear = Integer.toString(common.getNowCreditYear(area_id, rlgl050101Service));
		    
		    // 判断该用户是否已缴费
		    Rlgl100101_1Bean rlgl100101_1Bean =new Rlgl100101_1Bean();
		    
		    rlgl100101_1Bean.setUserId(user_id);
		    rlgl100101_1Bean.setPay_year(payyear);
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	public IRlgl100101Service getRlgl100101Service() {
		return rlgl100101Service;
	}

	public void setRlgl100101Service(IRlgl100101Service rlgl100101Service) {
		this.rlgl100101Service = rlgl100101Service;
	}

	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}

	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
	}

	public IRlgl050101Service getRlgl050101Service() {
		return rlgl050101Service;
	}

	public void setRlgl050101Service(IRlgl050101Service rlgl050101Service) {
		this.rlgl050101Service = rlgl050101Service;
	}

}
