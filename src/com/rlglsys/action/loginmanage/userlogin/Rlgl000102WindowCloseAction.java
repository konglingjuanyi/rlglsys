package com.rlglsys.action.loginmanage.userlogin;

import com.rlglsys.base.BaseAction;

public class Rlgl000102WindowCloseAction extends BaseAction {

	private static final long serialVersionUID = 483775379303898192L;
	/**
	 * 重新登录处理
	 * 
	 * @return SUCCESS
	 * @throws Exception
	 */
	@Override
	public String doExecute() throws Exception {
		return "";
	}
	
	/**
	 * 关闭画面
	 * 
	 * @return SUCCESS
	 * @throws Exception
	 */
	public void windowClose() throws Exception {
		// 清空session
		super.getSession().invalidate();
	}
}