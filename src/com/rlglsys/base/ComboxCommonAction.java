package com.rlglsys.base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.ecc.tool.Signature;
import com.opensymphony.xwork2.ActionSupport;
import com.rlglsys.bean.Rlgl022012Bean;
import com.rlglsys.bean.Rlgl022013Bean;
import com.rlglsys.bean.Rlgl090301Bean;
import com.rlglsys.bean.Rlgl100101_1Bean;
import com.rlglsys.entity.DBCommon;
import com.rlglsys.entity.MTb02Adm;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb03Menu;
import com.rlglsys.entity.Mtb119MerchantInfo;
import com.rlglsys.entity.Mtb72Userrole;
import com.rlglsys.entity.Mtb73Roleaction;
import com.rlglsys.entity.TTb01AutoGetNum;
import com.rlglsys.service.IApprovalProcessService;
import com.rlglsys.service.IAutoGetNumService;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IMenuService;
import com.rlglsys.service.IMerchantInfoService;
import com.rlglsys.service.IRlgl022012Service;
import com.rlglsys.service.IRlgl050101Service;
import com.rlglsys.service.IRlgl100101Service;
import com.rlglsys.util.BeanFactory;
import com.rlglsys.util.Common;
import com.rlglsys.util.CommonManager;
import com.rlglsys.util.Constant;
import com.rlglsys.util.DateTimeManager;
import com.rlglsys.util.DesEnCodeAndDeCode;
import com.rlglsys.util.DesUtil;

import net.sf.json.JSONObject;

public class ComboxCommonAction extends ActionSupport {

	private static final long serialVersionUID = -8893643179019791643L;
	// 被选择值
	private String params = "";
	private IMTb02AdmService mtb02AdmService;
	// 画面申请流程List
	private List<Rlgl090301Bean> processList;
	// 申请流程表操作service
	private IApprovalProcessService approvalProcessService;
	private IMerchantInfoService merchantInfoService;
	private IRlgl100101Service rlgl100101Service;
	private IRlgl050101Service rlgl050101Service;
	private IRlgl022012Service rlgl022012Service;
	// 自动递增
	private IAutoGetNumService noSerVice;
	// 指定区分
	private String appoint_kbn;
	// MenuService
	private IMenuService menuService;

	public IAutoGetNumService getNoSerVice() {
		return noSerVice;
	}

	public void setNoSerVice(IAutoGetNumService noSerVice) {
		this.noSerVice = noSerVice;
	}

	public IRlgl100101Service getRlgl100101Service() {
		return rlgl100101Service;
	}

	public void setRlgl100101Service(IRlgl100101Service rlgl100101Service) {
		this.rlgl100101Service = rlgl100101Service;
	}

	public IRlgl050101Service getRlgl050101Service() {
		return rlgl050101Service;
	}

	public void setRlgl050101Service(IRlgl050101Service rlgl050101Service) {
		this.rlgl050101Service = rlgl050101Service;
	}

	public IMenuService getMenuService() {
		return menuService;
	}

	public IMerchantInfoService getMerchantInfoService() {
		return merchantInfoService;
	}

	public void setMerchantInfoService(IMerchantInfoService merchantInfoService) {
		this.merchantInfoService = merchantInfoService;
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

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public List<Rlgl090301Bean> getProcessList() {
		return processList;
	}

	public void setProcessList(List<Rlgl090301Bean> processList) {
		this.processList = processList;
	}

	public IApprovalProcessService getApprovalProcessService() {
		return approvalProcessService;
	}

	public void setApprovalProcessService(IApprovalProcessService approvalProcessService) {
		this.approvalProcessService = approvalProcessService;
	}

	public String getAppoint_kbn() {
		return appoint_kbn;
	}

	public void setAppoint_kbn(String appoint_kbn) {
		this.appoint_kbn = appoint_kbn;
	}

	/**
	 * 从管理表取得下拉框选项
	 * 
	 * @return NULL
	 * @throws Exception
	 */
	public String getContextFromAdm() throws Exception {
		// 客户端发送的params是ADM_TYPE_CD
		List<MTb02Adm> admInfoList = mtb02AdmService.getAdmInfo(params);
		String result = "";
		for (int i = 0; i < admInfoList.size(); i++) {
			MTb02Adm admInfo = admInfoList.get(i);
			if ("".equals(result)) {
				result = admInfo.getAdm_num() + ":" + admInfo.getAdm_name();
			} else {
				result = result + "," + admInfo.getAdm_num() + ":" + admInfo.getAdm_name();
			}
		}

		// 往客户端发送消息
		sendMsgToClient(result);
		return null;
	}

	/**
	 * 从管理表取得考点考场用下拉框选项
	 * 
	 * @return NULL
	 * @throws Exception
	 */
	public String getContextFromAdmForExam() throws Exception {
		// 客户端发送的params是ADM_TYPE_CD
		List<MTb02Adm> admInfoList = mtb02AdmService.getAdmExamInfo(params);
		String result = "";
		for (int i = 0; i < admInfoList.size(); i++) {
			MTb02Adm admInfo = admInfoList.get(i);
			if ("".equals(result)) {
				result = admInfo.getAdm_num() + ":" + admInfo.getAdm_name();
			} else {
				result = result + "," + admInfo.getAdm_num() + ":" + admInfo.getAdm_name();
			}
		}

		// 往客户端发送消息
		sendMsgToClient(result);
		return null;
	}

	/**
	 * 从管理表取得级别下拉框选项
	 * 
	 * @return NULL
	 * @throws Exception
	 */
	public String getContextFromAdmForGrade() throws Exception {
		String[] paramArr = params.split(",");
		List<MTb02Adm> admInfoList = null;
		if (paramArr.length == 2) {
			admInfoList = mtb02AdmService.getAdmInfo(paramArr[0], paramArr[1]);
		}
		if (paramArr.length == 3) {
			admInfoList = mtb02AdmService.getAdmInfo(paramArr[0], paramArr[1], paramArr[2]);
		}
		String result = "";
		if (admInfoList != null) {
			for (int i = 0; i < admInfoList.size(); i++) {
				MTb02Adm admInfo = admInfoList.get(i);
				if ("".equals(result)) {
					result = admInfo.getAdm_num() + ":" + admInfo.getAdm_name();
				} else {
					result = result + "," + admInfo.getAdm_num() + ":" + admInfo.getAdm_name();
				}
			}
		}
		// 往客户端发送消息
		sendMsgToClient(result);
		return null;
	}

	/**
	 * 从管理表取得人员离开方式下拉框选项（rlgl010312人员调入画面）
	 * 
	 * @return NULL
	 * @throws Exception
	 */
	public String getContextFromAdmJoinMode() throws Exception {
		List<MTb02Adm> admInfoList = mtb02AdmService.getAdmInfo(params);
		String result = "";
		for (int i = 0; i < admInfoList.size(); i++) {
			MTb02Adm admInfo = admInfoList.get(i);
			if ("".equals(result)) {
				result = admInfo.getAdm_num() + ":" + admInfo.getAdm_name();
			} else {
				result = result + "," + admInfo.getAdm_num() + ":" + admInfo.getAdm_name();
			}
		}
		// 往客户端发送消息
		sendMsgToClient(result);
		return null;
	}

	/**
	 * 从session中取得超时标记
	 * 
	 * @return NULL
	 * @throws Exception
	 */
	public String getSessionTimeoutFlg() throws Exception {
		String result = "";
		if (ServletActionContext.getRequest().getSession().getAttribute("sessionTimeOutFLg") != null) {
			result = (String) ServletActionContext.getRequest().getSession().getAttribute("sessionTimeOutFLg");
		}
		ServletActionContext.getRequest().getSession().removeAttribute("sessionTimeOutFLg");
		// 往客户端发送消息
		sendMsgToClient(result);
		return null;
	}

	/**
	 * 从管理表取得下拉框选项(画面090301用)
	 * 
	 * @return NULL
	 * @throws Exception
	 */
	public String getContextFromAdmFor090310() throws Exception {
		String[] paramArr = new String[3];
		paramArr = params.split(",");

		// 流程信息取得
		Rlgl090301Bean rlgl090301BeanNew = new Rlgl090301Bean();
		// 审定单位编码
		rlgl090301BeanNew.setUnit_no(paramArr[1]);
		// 申请流程信息取得
		processList = approvalProcessService.getProcessInfobyCityList(rlgl090301BeanNew);

		// 客户端发送的params是ADM_TYPE_CD
		List<MTb02Adm> admInfoList = mtb02AdmService.getAdmInfo(paramArr[2]);
		List<MTb02Adm> admInfoNewList = new ArrayList<MTb02Adm>();
		// 画面选择已指定是
		if ("1".equals(paramArr[0])) {
			// 过滤取得已经指定流程的信息
			for (int i = 0; i < processList.size(); i++) {
				// 申请流程信息
				Rlgl090301Bean processInfo = processList.get(i);
				for (int k = 0; k < admInfoList.size(); k++) {
					// 申请事项
					MTb02Adm mtb02admInfo = admInfoList.get(k);
					if (processInfo.getApply_kbn().equals(mtb02admInfo.getAdm_num())) {
						admInfoNewList.add(mtb02admInfo);
						break;
					}
				}
			}
			admInfoList = admInfoNewList;
		}
		// 画面选择未指定
		if ("2".equals(paramArr[0])) {
			// 过滤未指定申请流程信息
			for (int i = 0; i < processList.size(); i++) {
				// 申请流程信息
				Rlgl090301Bean processInfo = processList.get(i);
				for (int k = 0; k < admInfoList.size(); k++) {
					// 申请事项
					MTb02Adm mtb02admInfo = admInfoList.get(k);
					if (processInfo.getApply_kbn().equals(mtb02admInfo.getAdm_num())) {
						admInfoList.remove(k);
						break;
					}
				}
			}
		}

		String result = "";
		for (int i = 0; i < admInfoList.size(); i++) {
			MTb02Adm admInfo = admInfoList.get(i);
			if ("".equals(result)) {
				result = admInfo.getAdm_num() + ":" + admInfo.getAdm_name();
			} else {
				result = result + "," + admInfo.getAdm_num() + ":" + admInfo.getAdm_name();
			}
		}

		// 往客户端发送消息
		sendMsgToClient(result);
		return null;
	}

	/**
	 * 下拉框级联（rlgl000204用）
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getContextFromListFor000204() throws Exception {
		String[] paramArr = new String[3];
		paramArr = params.split(",");

		// 菜单ID
		String menuId = paramArr[0];
		int level = Integer.valueOf(paramArr[1]);
		// 用户对象
		Mtb01User loginUser = (Mtb01User) ServletActionContext.getRequest().getSession()
				.getAttribute(Constant.SESSION_KEY_LOGINUSER);

		CommonManager common = new CommonManager();
		// 系统超级用户取得
		String sysUser = common.getSystemUser("rlglsys.sys.manager");

		// 设定检索条件对象
		Mtb73Roleaction roleactionInfo = new Mtb73Roleaction();
		if (!loginUser.getUser_id().equals(sysUser)) {
			Mtb72Userrole roleInfo = new Mtb72Userrole();
			roleInfo.setUser_enter(loginUser.getUser_enter());
			roleInfo.setUser_id(loginUser.getUser_id());
			// 用户角色取得
			List<Mtb72Userrole> userRoleInfoList = menuService.getUserRoleInfoList(roleInfo);
			List<String> roleList = new ArrayList<String>();

			if (userRoleInfoList != null && userRoleInfoList.size() > 0) {
				for (int i = 0; i < userRoleInfoList.size(); i++) {
					Mtb72Userrole roleInfo1 = userRoleInfoList.get(i);
					roleList.add(roleInfo1.getUnit_role());
				}
				// 单位编号
				roleactionInfo.setUnit_role(userRoleInfoList.get(0).getUnit_role());
				roleactionInfo.setUser_enter(userRoleInfoList.get(0).getUser_enter());
				roleactionInfo.setRoleIdList(roleList);
			}
		}

		// Menu菜单取得编辑处理
		List<Mtb03Menu> menuList = this.getMenuList(roleactionInfo, sysUser, loginUser);

		List<Mtb03Menu> resultList = new ArrayList<Mtb03Menu>();
		if (menuList != null && menuList.size() > 0) {
			Mtb03Menu menuInfo = null;
			for (int i = 0; i < menuList.size(); i++) {
				menuInfo = menuList.get(i);
				if ((menuInfo.getMenu_level() == level + 1) && menuId.equals(menuInfo.getMenu_param_id())) {
					resultList.add(menuInfo);
				}
			}
		}
		String result = "";
		for (int i = 0; i < resultList.size(); i++) {
			Mtb03Menu menuInfo = resultList.get(i);
			if ("".equals(result)) {
				result = menuInfo.getMenu_id() + ":" + menuInfo.getMenu_name();
			} else {
				result = result + "," + menuInfo.getMenu_id() + ":" + menuInfo.getMenu_name();
			}
		}

		// 往客户端发送消息
		sendMsgToClient(result);
		return null;
	}

	/**
	 * Menu菜单取得编辑处理
	 * 
	 * @param selectPage
	 * @return menuList
	 * @throws Exception
	 */
	public List<Mtb03Menu> getMenuList(Mtb73Roleaction roleactionInfo, String sysUser, Mtb01User loginUser)
			throws Exception {
		List<Mtb03Menu> menuList1 = new ArrayList<Mtb03Menu>();
		List<Mtb03Menu> menuList = new ArrayList<Mtb03Menu>();
		try {
			// 取得菜单信息
			if (!sysUser.equals(loginUser.getUser_id())) {
				menuList1 = menuService.getMenuInfoByUserList(roleactionInfo);
			} else {
				menuList1 = menuService.getMenuInfoList("");
			}

			int level = 0;
			// 菜单编辑排序
			this.editMenuList(level, menuList1, menuList, "");
			// 树形菜单编辑
			this.reEditMenuList(menuList);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		// 返回树形菜单
		return menuList;
	}

	/**
	 * 数据库中取得MenuList
	 * 
	 * @param level
	 * @param menuList
	 * @param newMenuList
	 * @param paramId
	 */
	public void editMenuList(int level, List<Mtb03Menu> menuList, List<Mtb03Menu> newMenuList, String paramId) {
		// Menu数据为空的时候，直接返回
		if (menuList == null || menuList.size() == 0) {
			return;
		}
		// 循环遍历MenuList进行排序
		for (int i = 0; i < menuList.size(); i++) {
			Mtb03Menu menu = (Mtb03Menu) menuList.get(i);
			// 判断是否是同一层的Menu
			if (menu.getMenu_level() == level && paramId.equals(menu.getMenu_param_id())) {
				newMenuList.add(menu);
				// 递归调用
				this.editMenuList(level + 1, menuList, newMenuList, menu.getMenu_id());
			} else if (menu.getMenu_level() > level) {
				// 退出循环
				break;
			}
		}
	}

	/**
	 * 树形菜单生成处理
	 * 
	 * @param menuList
	 */
	public void reEditMenuList(List<Mtb03Menu> menuList) {
		// Menu数据为空的时候，直接返回
		if (menuList == null || menuList.size() == 0) {
			return;
		}
		// 循环遍历MenuList
		for (int i = 0; i < menuList.size(); i++) {
			Mtb03Menu menu = (Mtb03Menu) menuList.get(i);
			if (i == menuList.size() - 1) {
				menu.setMenu_level_crl1(0);
				menu.setMenu_level_crl3(menu.getMenu_level());
				continue;
			}
			Mtb03Menu menuNext = (Mtb03Menu) menuList.get(i + 1);
			if (menuNext.getMenu_level() > menu.getMenu_level()) {
				menu.setMenu_level_crl1(1);
			} else {
				menu.setMenu_level_crl1(0);
			}
			if (menuNext.getMenu_level() < menu.getMenu_level()) {
				menu.setMenu_level_crl3(menu.getMenu_level() - menuNext.getMenu_level());
			} else {
				menu.setMenu_level_crl3(0);
			}
		}
	}

	/**
	 * 下拉框级联（登录画面用）
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getContextFromListFor000102() throws Exception {
		String[] paramArr = new String[3];
		paramArr = params.split(",");

		// 用户D
		String user_id = paramArr[0];

		Mtb72Userrole roleInfo = new Mtb72Userrole();
		roleInfo.setUser_id(user_id);
		List<Mtb72Userrole> userRoleInfoList = menuService.getUserRoleInfoList(roleInfo);
		List<MTb02Adm> resultList = new ArrayList<MTb02Adm>();
		if (userRoleInfoList == null) {
			userRoleInfoList = new ArrayList<Mtb72Userrole>();
		}
		// 用户入口
		List<MTb02Adm> userEnterList = mtb02AdmService.getAdmInfo("180");
		if (userEnterList == null) {
			userEnterList = new ArrayList<MTb02Adm>();
		}

		for (int i = 0; i < userRoleInfoList.size(); i++) {
			Mtb72Userrole userRole = userRoleInfoList.get(i);
			for (int j = 0; j < userEnterList.size(); j++) {
				MTb02Adm mtb02am = userEnterList.get(j);
				if (userRole.getUser_enter().equals(mtb02am.getAdm_num())) {
					resultList.add(mtb02am);
				}
			}
		}

		String result = "";
		for (int i = 0; i < resultList.size(); i++) {
			MTb02Adm userEnter = resultList.get(i);
			if ("".equals(result)) {
				result = userEnter.getAdm_num() + ":" + userEnter.getAdm_name();
			} else {
				result = result + "," + userEnter.getAdm_num() + ":" + userEnter.getAdm_name();
			}
		}

		// 往客户端发送消息
		sendMsgToClient(result);
		return null;
	}

	/**
	 * 编辑视频课程参数
	 * 
	 * @return NULL
	 * @throws Exception
	 */
	public String editLearnOnlineDataInfo() throws Exception {
		String[] paramArr = new String[5];
		Common common = new Common();
		paramArr = params.split(",");
		// 课程ID
		String course_id = paramArr[0];
		// 课程提供方
		String course_support = paramArr[1];
		// 页面标志
		String page_flg = paramArr[2];
		// 用户ID
		String user_id = paramArr[3];
		// 系统网址
		String sysUrl = paramArr[4];
		// 2015/03/09 韩晓凤
		// 学习状态 学习：0 再学习：2
		String status = paramArr[5];

		// 时间
		String timer = common.getNowDate();

		// 视频地址
		//加入ip地址提供传输界面的参数回调（使用的服务器ip）
		String back_url ="http://118.192.147.9:8080"+ sysUrl + "/learnonlinecallback.action";

		// 好医生数据整合
		JSONObject jsonObj2 = new JSONObject();
		jsonObj2.put("user_id", user_id);
		jsonObj2.put("course_id", course_id);
		jsonObj2.put("back_url", back_url);
		jsonObj2.put("page_flg", page_flg);
		// 2015/03/09 韩晓凤
		jsonObj2.put("status", status);

		DesEnCodeAndDeCode decode = new DesEnCodeAndDeCode();

		String result = user_id + ";" + course_id + ";" + timer + ";" + page_flg + ";" + back_url + ";" + status;

		CommonManager commonManager = new CommonManager();
		// 密钥
		String desKey = commonManager.getSystemDesKey("rlglsys.sys.deskey");
		// 好医生
		if ("001".equals(course_support)) {
			result = decode.getencode(desKey, jsonObj2.toString());
		} else if ("002".equals(course_support)) {
			// 华医网
			result = decode.encode(result, desKey);
		}
		result = result + ";" + course_support;
		// 往客户端发送消息
		sendMsgToClient(result);
		return null;
	}

	/**
	 * 编辑在线支付参数
	 * 
	 * @return NULL
	 * @throws Exception
	 */
	public String payDataedit() throws Exception {

		String key = "";
		// 取得商户信息
		Mtb119MerchantInfo mtb119MerchantInfo = null;
		try {
			mtb119MerchantInfo = merchantInfoService.getMerchantInfo();
		} catch (Exception e) {
			System.out.println(e);
		}

		if (mtb119MerchantInfo == null) {
			return "error";
		} else {
			// 密钥
			key = mtb119MerchantInfo.getKey();
		}

		String[] paramArr = new String[4];
		paramArr = params.split(",");
		//
		String MerchantID = paramArr[0];
		//
		String TransactionID = paramArr[1];
		// 金额
		String Amount = paramArr[2];
		//
		String MerchantURL = paramArr[3];

		// 商户ID，交易号，金额，返回url都不为空时，继续
		if ("".equals(MerchantID) || "".equals(TransactionID) || "".equals(Amount) || "".equals(MerchantURL)) {
			return "error";
		}

		// 验证码取得
		/*
		 * java.security.MessageDigest alga =
		 * java.security.MessageDigest.getInstance("SHA-1"); String data =
		 * MerchantID + TransactionID + Amount + MerchantURL + key;
		 * alga.update(data.getBytes()); byte[] digesta = alga.digest();
		 * 
		 * String result = new Common().bintoascii(digesta);
		 */
		// 往客户端发送消息
		String result = Signature.dealSignure(MerchantID, TransactionID, Amount, MerchantURL, key);
		sendMsgToClient(result);
		return null;
	}

	/**
	 * 编辑在线支付参数
	 * 
	 * @return NULL
	 * @throws Exception
	 */
	public String insertEcypaydata() throws Exception {
		String[] paramArr = new String[6];
		paramArr = params.split(",");
		String MerchantID = paramArr[0];
		String MerchantName = "";
		String TransactionID = paramArr[1];
		String Amount = paramArr[2];
		String Signatures = paramArr[3];
		String MerchantURL = paramArr[4];
		String merchantURLReturn = paramArr[5];
		String Succeed = "";
		// 登陆用户信息
		Mtb01User loginUser = (Mtb01User) ServletActionContext.getRequest().getSession()
				.getAttribute(Constant.SESSION_KEY_LOGINUSER);
		String userId = (String) loginUser.getUser_id();
		// 获得当前年度
		Common common = new Common();
		// 获得当前年度
		String area_id = mtb02AdmService.getAdmName("237", "01");
		String payyear = Integer.toString(common.getNowCreditYear(area_id, rlgl050101Service));

		Rlgl100101_1Bean rlgl100101_1Bean1 = new Rlgl100101_1Bean();

		rlgl100101_1Bean1.setUserId(userId);
		rlgl100101_1Bean1.setMerchantID(MerchantID);
		rlgl100101_1Bean1.setAmount(Amount);
		rlgl100101_1Bean1.setSucceed(Succeed);
		rlgl100101_1Bean1.setTransactionID(TransactionID);
		rlgl100101_1Bean1.setPay_year(payyear);
		rlgl100101_1Bean1.setPay_date(DateTimeManager.getCurrentDateStrFormat());
		rlgl100101_1Bean1.setDel_kbn("0");
		rlgl100101_1Bean1.setEx_key(1);
		if (loginUser != null) {
			rlgl100101_1Bean1.setLogin_user_id(loginUser.getUser_id());
			rlgl100101_1Bean1.setUpdate_user_id(loginUser.getUser_id());
		}
		rlgl100101_1Bean1.setLogin_date(DateTimeManager.getSystemDate14());
		rlgl100101_1Bean1.setUpdate_date(DateTimeManager.getSystemDate14());
		String result = "";
		String key = "";
		// 取得商户信息
		Mtb119MerchantInfo mtb119MerchantInfo = null;
		try {
			mtb119MerchantInfo = merchantInfoService.getMerchantInfo();
		} catch (Exception e) {
			System.out.println(e);
		}

		if (mtb119MerchantInfo == null) {
			return "error";
		} else {
			// 密钥
			key = mtb119MerchantInfo.getKey();
			MerchantName = mtb119MerchantInfo.getMerchantNM();
		}
		int count = 0;
		// 把支付返回信息更新到数据库中
		try {
			count = rlgl100101Service.insertData(rlgl100101_1Bean1);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		if (count > 0) {
			result = "1" + ";" + MerchantID + ";" + TransactionID + ";" + Amount + ";" + Signatures + ";" + MerchantURL
					+ ";" + MerchantName+ ";" + merchantURLReturn;
			TTb01AutoGetNum noInfo = new TTb01AutoGetNum();
			// 自动递增表ID
			noInfo.setTable_id("t_tb02");
			// 自动递增字段
			noInfo.setCol_id("TransactionID");
			noInfo.setNum_type("3711" + DateTimeManager.getSystemDate14().substring(0, 8));

			noSerVice.updateNoInfoByUnit(noInfo);
		}
		// 往客户端发送消息
		sendMsgToClient(result);
		return null;
	}

	/**
	 * 编辑在线支付参数
	 * 
	 * @return NULL
	 * @throws Exception
	 */
	public String insertPrepaydata() throws Exception {
		String[] paramArr = new String[5];
		paramArr = params.split(",");
		String MerchantID = paramArr[0];
		String MerchantName = "";
		String TransactionID = paramArr[1];
		String Amount = paramArr[2];
		String Signatures = "";
		String MerchantURL = paramArr[3];
		String Succeed = "";
		String invoice = paramArr[4];

		// 登陆用户信息
		Mtb01User loginUser = (Mtb01User) ServletActionContext.getRequest().getSession()
				.getAttribute(Constant.SESSION_KEY_LOGINUSER);
		String userId = (String) loginUser.getUser_id();
		// 获得当前年度
		Common common = new Common();
		// 获得当前年度
		String area_id = mtb02AdmService.getAdmName("237", "01");
		String payyear = Integer.toString(common.getNowCreditYear(area_id, rlgl050101Service));

		Rlgl100101_1Bean rlgl100101_1Bean1 = new Rlgl100101_1Bean();

		rlgl100101_1Bean1.setUserId(userId);
		rlgl100101_1Bean1.setMerchantID(MerchantID);
		rlgl100101_1Bean1.setAmount(Amount);
		rlgl100101_1Bean1.setInvoice(invoice);
		rlgl100101_1Bean1.setSucceed(Succeed);
		rlgl100101_1Bean1.setTransactionID(TransactionID);
		rlgl100101_1Bean1.setPay_year(payyear);
		rlgl100101_1Bean1.setPay_date(DateTimeManager.getCurrentDateStrFormat());
		rlgl100101_1Bean1.setDel_kbn("0");
		rlgl100101_1Bean1.setEx_key(1);
		if (loginUser != null) {
			rlgl100101_1Bean1.setLogin_user_id(loginUser.getUser_id());
			rlgl100101_1Bean1.setUpdate_user_id(loginUser.getUser_id());
		}
		rlgl100101_1Bean1.setLogin_date(DateTimeManager.getSystemDate14());
		rlgl100101_1Bean1.setUpdate_date(DateTimeManager.getSystemDate14());
		String result = "";
		String key = "";
		// 取得商户信息
		Mtb119MerchantInfo mtb119MerchantInfo = null;
		try {
			mtb119MerchantInfo = merchantInfoService.getMerchantInfo();
		} catch (Exception e) {
			System.out.println(e);
		}

		if (mtb119MerchantInfo == null) {
			return "error";
		} else {
			// 密钥
			key = mtb119MerchantInfo.getKey();
			MerchantName = mtb119MerchantInfo.getMerchantNM();
		}
		int count = 0;
		// 把支付返回信息更新到数据库中
		try {
			count = rlgl100101Service.insertData(rlgl100101_1Bean1);

			result = MerchantID + ";" + TransactionID + ";" + Amount + ";" + Signatures + ";" + MerchantURL + ";"
					+ MerchantName;
			TTb01AutoGetNum noInfo = new TTb01AutoGetNum();
			// 自动递增表ID
			noInfo.setTable_id("t_tb02");
			// 自动递增字段
			noInfo.setCol_id("TransactionID");
			// 设定长度
			noInfo.setLength(8);
			noInfo.setNum_type("3711"+DateTimeManager.getSystemDate14().substring(0, 8));

			noSerVice.updateNoInfoByUnit(noInfo);

			count = insertPayData(rlgl100101_1Bean1, loginUser);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		// 签名数据生成
		// java.security.MessageDigest alga =
		// java.security.MessageDigest.getInstance("SHA-1");
		// String data = MerchantID + TransactionID + Amount + MerchantURL +
		// key;
		// alga.update(data.getBytes());
		// byte[] digesta = alga.digest();
		// Signatures = new Common().bintoascii(digesta);

		// Signature.dealSignure(merId, dealOrder, dealFee, dealReturn, key);
		Signatures = Signature.dealSignure(MerchantID, TransactionID, Amount, MerchantURL, key);

		result = MerchantID + ";" + TransactionID + ";" + Amount + ";" + Signatures + ";" + MerchantURL + ";"
				+ MerchantName;
		// 往客户端发送消息
		sendMsgToClient(result);
		return null;
	}

	/**
	 * 往客户端发送消息
	 * 
	 * @return NULL
	 * @throws Exception
	 */
	private void sendMsgToClient(String result) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(result);
	}

	protected int insertPayData(Rlgl100101_1Bean rlgl100101_1Bean1, Mtb01User loginUser) {
		int result = 0;
		// 登陆用户信息
		String user_id = loginUser.getUser_id();
		Rlgl022012Bean rlgl022012Bean = new Rlgl022012Bean();
		Rlgl022013Bean rlgl022013Bean = new Rlgl022013Bean();
		Map<String, String> B13fields = new HashMap<String, String>();
		BeanFactory bf = new BeanFactory();
		bf.reinstallFields(rlgl100101_1Bean1, DesUtil.class, "dec" + "rypt", B13fields);
		// 用户id
		rlgl022013Bean.setClum001(user_id);
		// 身份证号
		rlgl022013Bean.setClum002((String) loginUser.getPersonnel_id());
		// 单位编号
		rlgl022013Bean.setClum003((String) loginUser.getUnit_no());
		// 科室编号
		rlgl022013Bean.setClum004((String) loginUser.getSection_id());
		// 银行编号
		rlgl022013Bean.setClum005("");
		// 订单号
		rlgl022013Bean.setClum006("");
		rlgl022013Bean.setTransactionID(rlgl100101_1Bean1.getTransactionID());
		rlgl022013Bean.setClum015(rlgl100101_1Bean1.getTransactionID());
		// 用户账号
		rlgl022013Bean.setClum007("");
		rlgl022013Bean.setClum008(rlgl100101_1Bean1.getAmount());
		rlgl022013Bean.setClum009(DateTimeManager.getSystemDate14());
		rlgl022013Bean.setLogin_user_id(user_id);
		rlgl022013Bean.setLogin_date(rlgl022013Bean.getClum009());
		rlgl022013Bean.setUpdate_user_id(user_id);
		rlgl022013Bean.setUpdate_date(rlgl022013Bean.getClum009());
		rlgl022013Bean.setClum010("001");
		rlgl022013Bean.setClum013("001");
		// 摘要
		rlgl022013Bean.setClum011("");
		// 核实状态
		rlgl022013Bean.setClum014("002");
		rlgl022012Bean.setPaymentCheck("002");
		rlgl022012Bean.setDel_kbn("0");
		rlgl022012Bean.setClum007(rlgl100101_1Bean1.getInvoice());
		rlgl022013Bean.setDel_kbn("0");
		rlgl022013Bean.setEx_key(1);
		rlgl022012Bean.setEx_key(1);
		// 排除共同列加密
		Field[] fields = DBCommon.class.getDeclaredFields();

		for (Field f : fields) {
			// 更新用户及时间也要加密
			if (!(f.getName().contains("login") || f.getName().contains("update"))) {
				B13fields.put(f.getName(), "");
			}
		}
		B13fields.put("clum003", "");
		B13fields.put("clum009", "");
		B13fields.put("clum013", "");
		B13fields.put("clum014", "");
		B13fields.put("bank_order_no", "");
		B13fields.put("money", "");
		B13fields.put("adminFlag", "");
		B13fields.put("TransactionID", "");
		B13fields.put("Succeed", "");

		// 预付费记录数据加密
		bf.reinstallFields(rlgl022013Bean, DesUtil.class, "enc" + "rypt", B13fields);

		result = rlgl022012Service.updateOverMoney(rlgl022013Bean, rlgl022012Bean);
		return result;
	}

	public IRlgl022012Service getRlgl022012Service() {
		return rlgl022012Service;
	}

	public void setRlgl022012Service(IRlgl022012Service rlgl022012Service) {
		this.rlgl022012Service = rlgl022012Service;
	}

}
