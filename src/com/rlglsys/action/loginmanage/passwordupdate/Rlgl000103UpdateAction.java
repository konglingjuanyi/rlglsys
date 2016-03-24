package com.rlglsys.action.loginmanage.passwordupdate;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl000103Bean;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.service.IUserService;
import com.rlglsys.util.DateTimeManager;
import com.rlglsys.util.EncryptManager;

public class Rlgl000103UpdateAction extends BaseAction {

	private static final long serialVersionUID = 483775379303898192L;

	// 用户Service
	private IUserService userService;
	private Rlgl000103Bean rlgl000103Bean;
	private String updateFlg;
	private String user_id;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * 密码修正
	 * 
	 * @return SUCCESS
	 * @throws Exception
	 */
	@Override
	public String doExecute() throws Exception {
		try {
			// 系统时间取得
			String newDate = DateTimeManager.getSystemDate14();
			Mtb01User userInfo = new Mtb01User();
			if (user_id == null || "".equals(user_id) ) {
				if (!rlgl000103Bean.getPassword().equals(EncryptManager.EncryptStr(rlgl000103Bean.getPassword_old()))) {
					super.saveErrorMessage("MSG0051E");
					return "input";
				}
				userInfo.setUser_id(rlgl000103Bean.getUser_id());
			} else {
				userInfo.setUser_id(user_id);
			}
			userInfo.setPassword(EncryptManager.EncryptStr(rlgl000103Bean.getPassword_new()));
			// 更新时间
			userInfo.setUpdate_date(newDate);
			userInfo.setUpdate_user_id(rlgl000103Bean.getUser_id());
			userService.updatePasswordInfo(userInfo);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		updateFlg = "success";
		return SUCCESS;
	}

	// ----------------get,set--------------------
	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	/**
	 * @return the rlgl000103Bean
	 */
	public Rlgl000103Bean getRlgl000103Bean() {
		return rlgl000103Bean;
	}

	/**
	 * @param rlgl000103Bean
	 *            the rlgl000103Bean to set
	 */
	public void setRlgl000103Bean(Rlgl000103Bean rlgl000103Bean) {
		this.rlgl000103Bean = rlgl000103Bean;
	}

	/**
	 * @return the updateFlg
	 */
	public String getUpdateFlg() {
		return updateFlg;
	}

	/**
	 * @param updateFlg
	 *            the updateFlg to set
	 */
	public void setUpdateFlg(String updateFlg) {
		this.updateFlg = updateFlg;
	}
}