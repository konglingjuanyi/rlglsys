package com.rlglsys.action.loginmanage.userlogin;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import com.rlglsys.base.BaseAction;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb04Unit;
import com.rlglsys.entity.Mtb72Userrole;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IMenuService;
import com.rlglsys.service.IUserService;
import com.rlglsys.util.CommonManager;
import com.rlglsys.util.Constant;
import com.rlglsys.util.DateTimeManager;
import com.rlglsys.util.EncryptManager;

public class Rlgl000102LoginAction extends BaseAction {

    private static final long serialVersionUID = 483775379303898192L;

    // 用户Service
    private IUserService userService;
    // 用户对象
    private Mtb01User user;
    // 入口标记
    private String selectPage;
    // 验证码
    private String authImgNo;
    // 被代管单位编码
    private String operatorUnitNo ;
    // 代管标记
	private String operatorFlg;
    // MenuService
    private IMenuService menuService;
	// 管理信息操作service
    private IMTb02AdmService mtb02AdmService;
    private String selectTitle;
    private String returnFlg;
    /**
     * 登录处理
     * 
     * @return SUCCESS
     * @throws Exception
     */
    @Override
    public String doExecute() throws Exception {
        try {
            
            Mtb01User loginUser = null;
            if ("unit_operator".equals(operatorFlg))
            {
            	loginUser = userService.getUserInfo(operatorUnitNo, "");
            } else {
            	if (user == null) {
                    return INPUT;
                }
            	// 验证码验证处理
                if ( authImgNo !=null 
                    && authImgNo.toLowerCase().equals(super.getSession(Constant.SESSION_KEY_RANDOMCODE).toString().toLowerCase())) {
                        removeSession(Constant.SESSION_KEY_RANDOMCODE);
                } else {
                    super.addActionError(this.getMessageById("MSG0043E"));
                    return INPUT;
                }
                // 用户密码加密
                user.setPassword(EncryptManager.EncryptStr(user.getPassword()));
                // 单位入口进入时
                if ("loginpage1".equals(selectPage) || "loginpage2".equals(selectPage))
                {
                	// 设定用户类别（单位用户）
                	user.setUser_type("1");
                	// 设定单位字母缩写
                	user.setUser_id_biko(user.getUser_id());
                }
                // 个人入口进入时
                if ("loginpage3".equals(selectPage) || "loginpage4".equals(selectPage))
                {
                	// 设定用户类别(个人)
                	user.setUser_type("0");
                	// 设定身份证号
                	user.setPersonnel_id(user.getUser_id());
                }
                loginUser = userService.getUserInfoWithLogin(user);
            }
            
            if (loginUser == null) {
                super.addActionError(this.getMessageById("MSG0044E"));
                return INPUT;
             } 
//            else if ("1".equals(loginUser.getLogin_status())){
//            	super.addActionError(this.getMessageById("MSG0056E"));
//                return INPUT;
//            } 
            else {
            	Mtb04Unit unitInfo = userService.getUnitInfo(loginUser.getUnit_no());
            	// 用户类别（0：个人用户 1：单位用户）
            	String userType = loginUser.getUser_type();
            	// 单位分类(001 行政单位,002医疗单位)
            	String unitAssort = unitInfo.getUnit_assort();
            	if (super.getSession("selectPage") != null)
            	{
            		selectPage = (String)super.getSession("selectPage");
            	}
            	if ("loginpage1".equals(selectPage))
                {
            		if ("0".equals(userType))
            		{
            			// 您是个人用户，无法从医疗卫生机构入口登录
            			super.addActionError(this.getMessageById("MSG0045E"));
                        return INPUT;
            		}
            		if ("001".equals(unitAssort))
            		{
            			// 您是行政单位用户，无法从医疗卫生机构入口登录
            			super.addActionError(this.getMessageById("MSG0046E"));
                        return INPUT;
            		}
                }
            	if ("loginpage2".equals(selectPage))
                {
            		if ("0".equals(userType))
            		{
            			// 您是个人用户，无法从卫生行政部门入口登录
            			super.addActionError(this.getMessageById("MSG0047E"));
                        return INPUT;
            		}
            		if ("002".equals(unitAssort))
            		{
            			// 您是医疗卫生机构用户，无法从医疗单位入口登录
            			super.addActionError(this.getMessageById("MSG0048E"));
                        return INPUT;
            		}
                }
            	if ("loginpage3".equals(selectPage))
                {
            		if ("1".equals(userType))
            		{
            			// 您是单位用户，无法从卫生技术人员入口登录
            			super.addActionError(this.getMessageById("MSG0049E"));
                        return INPUT;
            		}
//            		if ("2".equals(userType))
//            		{
//            			// 您是乡医培训用户，无权从卫生技术人员入口登录，请确认！
//            			super.addActionError(this.getMessageById("MSG0095E"));
//                        return INPUT;
//            		}
                }
            	if ("loginpage4".equals(selectPage))
                {
            		if ("1".equals(userType))
            		{
            			// 您是单位用户，无法从卫生技术人员入口登录
            			super.addActionError(this.getMessageById("MSG0094E"));
                        return INPUT;
            		}
//            		if ("0".equals(userType))
//            		{
//            			// 您是卫生技术人员用户，无权从乡医培训入口登录，请确认！
//            			super.addActionError(this.getMessageById("MSG0096E"));
//                        return INPUT;
//            		}
                }
            	CommonManager common = new CommonManager();
                // 系统超级用户取得
                String sysUser = common.getSystemUser("rlglsys.sys.manager");
            	// 用户角色取得
            	Mtb72Userrole roleInfo = new Mtb72Userrole();
            	roleInfo.setUser_id(loginUser.getUser_id());
            	
            	// 用户角色取得
                List<Mtb72Userrole> userRoleInfoList = menuService.getUserRoleInfoList(roleInfo);

                if (!sysUser.equals(loginUser.getUser_id()))
                {
                	 if (userRoleInfoList == null || userRoleInfoList.size() == 0)
                     {
                     	super.addActionError(this.getMessageById("MSG0073E"));
                         return INPUT;
                     }
                }

                // 初始化用户对象
                Mtb01User userInfo = new Mtb01User();
                if ("0".equals(userType)){
                    userInfo.setUser_id(loginUser.getUser_id());
                    userInfo.setUser_enter(roleInfo.getUser_enter());
                    userService.updateUserEnterInfo(userInfo);
                }
                
                // 系统时间取得
                String newDate = DateTimeManager.getSystemDate14();
                userInfo = new Mtb01User();
                userInfo.setUser_id(loginUser.getUser_id());
                // 设定用户登录系统时间
                userInfo.setLast_logindate(newDate);
                // 设定用户登录状态（已登录）
                userInfo.setLogin_status("1");
                // 更新数据库
                userService.updateUserLoginStatus(userInfo);
                
                // 登录用户信息
                super.setSession(Constant.SESSION_KEY_LOGINUSER, loginUser);
                super.setSession("UnitInfo", unitInfo);
            }
        } catch(Exception ex)
        {
            ex.printStackTrace();
            throw ex;
        }
        return SUCCESS;
    }

        /**
         * 系统message取得
         * @param messageId
         * @return
         * @throws Exception
         */
    	public String getMessageById(String messageId) throws Exception{
    		InputStream is = null;
    		Properties p = new Properties();
    		String msg = null;
    		is = this.getClass().getResourceAsStream("/systemMessage.properties");
    		try {
    		    p.load(is);
    			is.close();
    			msg = p.getProperty(messageId);
    			if (msg == null) {
    				msg = "此信息不存在！";
    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		return msg;
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

    public void setAuthImgNo(String authImgNo) {
        this.authImgNo = authImgNo;
    }

    public String getAuthImgNo() {
        return authImgNo;
    }

    public String getSelectPage() {
        return selectPage;
    }

    public void setSelectPage(String selectPage) {
        this.selectPage = selectPage;
    }

	public String getOperatorUnitNo() {
		return operatorUnitNo;
	}

	public void setOperatorUnitNo(String operatorUnitNo) {
		this.operatorUnitNo = operatorUnitNo;
	}

	public String getOperatorFlg() {
		return operatorFlg;
	}

	public void setOperatorFlg(String operatorFlg) {
		this.operatorFlg = operatorFlg;
	}

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
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
}