package com.rlglsys.action.loginmanage.loginredirect;

import com.rlglsys.base.BaseAction;
import com.rlglsys.entity.Mtb120SysUrl;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IUserService;

public class LoginRedirectAction extends BaseAction {

	private static final long serialVersionUID = 483775379303898192L;
	private String selectPage;
	private String selectTitle;
	private String returnFlg;
	// 管理信息操作service
    private IMTb02AdmService mtb02AdmService;
    private String sysUrl = "";
    // 用户Service
    private IUserService userService;
	/**
	 * 画面迁移
	 * @return SUCCESS
	 * @throws Exception
	 */
	@Override
	public String doExecute() throws Exception {
		try {
			if (selectPage == null || "".equals(selectPage))
			{
				return "show";
			}
			Mtb120SysUrl urlInfoNew = new Mtb120SysUrl();
			urlInfoNew.setUrl_id("001");
			urlInfoNew.setUrl(sysUrl);
			Mtb120SysUrl urlInfo = userService.getMtb120SysurlInfo(urlInfoNew);
			if (urlInfo == null)
			{
				urlInfoNew.setUrl(sysUrl);
				super.setDBCommonColOnInsert(urlInfoNew);
				userService.insertMtb120Sysurl(urlInfoNew);
			} else {
				if (!sysUrl.trim().equals(urlInfo.getUrl().trim()))
				{
					userService.updateMtb120SysurlInfo(urlInfoNew);
				}
			}
			super.setSession("selectPage", selectPage);
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}

		return SUCCESS;
	}
	public String getSelectPage() {
		return selectPage;
	}
	public void setSelectPage(String selectPage) {
		this.selectPage = selectPage;
	}

	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}
	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
	}
	public String getSelectTitle() {
		return selectTitle;
	}
	public void setSelectTitle(String selectTitle) {
		this.selectTitle = selectTitle;
	}
	public String getReturnFlg() {
		return returnFlg;
	}
	public void setReturnFlg(String returnFlg) {
		this.returnFlg = returnFlg;
	}
	public String getSysUrl() {
		return sysUrl;
	}
	public void setSysUrl(String sysUrl) {
		this.sysUrl = sysUrl;
	}
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
}
