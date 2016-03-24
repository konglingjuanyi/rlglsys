package com.rlglsys.action.loginmanage.passwordupdate;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl000103Bean;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.service.IUserService;
import com.rlglsys.util.Constant;

public class Rlgl000103InitAction extends BaseAction {

	private static final long serialVersionUID = 483775379303898192L;

	// 用户Service
	private IUserService userService;
	// 用户对象
	private Mtb01User user;
	private Rlgl000103Bean rlgl000103Bean;

	/**
	 * 初始化处理
	 * 
	 * @return SUCCESS
	 * @throws Exception
	 */
	@Override
	public String doExecute() throws Exception {
		try {
			 // 用户对象
            Mtb01User loginUser = (Mtb01User)super.getSession(Constant.SESSION_KEY_LOGINUSER);
            rlgl000103Bean = new Rlgl000103Bean();
            rlgl000103Bean.setPassword(loginUser.getPassword());
            rlgl000103Bean.setUser_id(loginUser.getUser_id());
		} catch(Exception ex)
		{
			ex.printStackTrace();
			throw ex;
		}
		return SUCCESS;
	}

	// ----------------get,set--------------------
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public Mtb01User getUser() {
		return user;
	}

	public void setUser(Mtb01User user) {
		this.user = user;
	}

	/**
	 * @return the rlgl000103Bean
	 */
	public Rlgl000103Bean getRlgl000103Bean() {
		return rlgl000103Bean;
	}

	/**
	 * @param rlgl000103Bean the rlgl000103Bean to set
	 */
	public void setRlgl000103Bean(Rlgl000103Bean rlgl000103Bean) {
		this.rlgl000103Bean = rlgl000103Bean;
	}
}