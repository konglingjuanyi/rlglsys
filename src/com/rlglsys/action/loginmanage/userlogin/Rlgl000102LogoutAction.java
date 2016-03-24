package com.rlglsys.action.loginmanage.userlogin;

import java.util.List;
import com.rlglsys.base.BaseAction;
import com.rlglsys.entity.MTb02Adm;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IUserService;
import com.rlglsys.util.Constant;

public class Rlgl000102LogoutAction extends BaseAction {

	private static final long serialVersionUID = 483775379303898192L;
	private String selectPage;
	// 用户Service
    private IUserService userService;
    private List<MTb02Adm> userEnterList;
	// 管理信息操作service
    private IMTb02AdmService mtb02AdmService;
    // 用户对象
    private Mtb01User user;
	/**
	 * 重新登录处理
	 * 
	 * @return SUCCESS
	 * @throws Exception
	 */
	@Override
	public String doExecute() throws Exception {
		super.removeSession(Constant.SESSION_KEY_USER_AUTHORITY);
		selectPage = super.getSession("selectPage").toString();
		super.getSession().invalidate();
		super.setSession("selectPage", selectPage);
		if (user == null)
		{
			user = new Mtb01User();
		}
		return "logout";
	}
	
	public String getSelectPage() {
		return selectPage;
	}
	public void setSelectPage(String selectPage) {
		this.selectPage = selectPage;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public List<MTb02Adm> getUserEnterList() {
		return userEnterList;
	}

	public void setUserEnterList(List<MTb02Adm> userEnterList) {
		this.userEnterList = userEnterList;
	}

	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}

	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
	}

	public Mtb01User getUser() {
		return user;
	}

	public void setUser(Mtb01User user) {
		this.user = user;
	}
	
}