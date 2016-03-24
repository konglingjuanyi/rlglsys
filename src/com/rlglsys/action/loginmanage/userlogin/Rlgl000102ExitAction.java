package com.rlglsys.action.loginmanage.userlogin;

import com.rlglsys.base.BaseAction;

public class Rlgl000102ExitAction extends BaseAction {

	private static final long serialVersionUID = 483775379303898192L;
	private String selectPage;
	/**
	 * 退出处理
	 * 
	 * @return SUCCESS
	 * @throws Exception
	 */
	@Override
	public String doExecute() throws Exception {
		// 清空session
		super.getSession().invalidate();
		return NONE;
	}
	
	public String getSelectPage() {
		return selectPage;
	}
	public void setSelectPage(String selectPage) {
		this.selectPage = selectPage;
	}
}