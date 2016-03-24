package com.rlglsys.action.register;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.rlglsys.base.BaseAction;
import com.rlglsys.entity.MTb02Adm;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb04Unit;
import com.rlglsys.entity.Mtb12Personnel;
import com.rlglsys.entity.Mtb39Personnel;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IMTb04UnitService;
import com.rlglsys.service.IUserService;
import com.rlglsys.util.DateTimeManager;
import com.rlglsys.util.EncryptManager;

/**
 * 注册Action，把数据保存到数据库
 * 
 * @author Administrator
 *
 */
public class RegisterAction extends BaseAction {
	private static final long serialVersionUID = -4417738404739548291L;

	// 单位service
	private IMTb04UnitService mtb04UnitService;
		// 页面上的下拉框
	private List<Mtb04Unit> mtb04unitList;
	// 人员进入方式
	private List<MTb02Adm> mtb02Adm094List;
	private IMTb02AdmService mtb02AdmService;
	// private Rlgl090101BeanNew rlgl090101BeanNew;
	// 用户操作service
	private IUserService userService;
	private String user_id;
	private String user_name;
	private String password;
	private String unit_no;
	private String adm_num;
	private String escrow_unit_nm = "";
	private String personnel_email = "";
	private String resultMessage;
	public String getPersonnel_email() {
		return personnel_email;
	}

	public void setPersonnel_email(String personnel_email) {
		this.personnel_email = personnel_email;
	}

	@Override
	public String doExecute() throws Exception {

		try {
			// List<Rlgl090101Bean> resultList = new
			// ArrayList<Rlgl090101Bean>();
			// 单位下拉框
			mtb04unitList = mtb04UnitService.getUnitList("3711");
			if (mtb04unitList == null) {
				mtb04unitList = new ArrayList<Mtb04Unit>();
			}
			// 人员进入方式
			mtb02Adm094List = mtb02AdmService.getAdmInfo("094");
			if (mtb02Adm094List == null) {
				mtb02Adm094List = new ArrayList<MTb02Adm>();
			}

			Mtb01User user = new Mtb01User();
			Mtb12Personnel personnelInfo = new Mtb12Personnel();
			Mtb39Personnel mtb39PersonnelInfo = new Mtb39Personnel();

			Mtb01User userSelect = null;
			// 根据用户ID或备用ID检索用户表数据
			userSelect = userService.getUserInfo(user_id, "");
			// 判断用户是否存在
			if ((userSelect != null && userSelect.getUser_id() != null)) {
				// 设定画面message
				// super.saveErrorMessage("MSG0019E");
				super.addActionError(this.getMessageById("MSG0093I"));
				return SUCCESS;
			}

			// 系统时间取得
			String newDate = DateTimeManager.getSystemDate14();
			// 用户ID
			user.setUser_id(user_id);
			// 用户名
			user.setUser_name(user_name);
			// 身份证号
			user.setPersonnel_id(user_id);
			// 登录密码
			user.setPassword(EncryptManager.EncryptStr(password));
			// 单位编号
			user.setUnit_no(unit_no);
			// 用户类别(个人用户)
			user.setUser_type("0");
			user.setDel_kbn("0");
			// 登录时间
			user.setLogin_date(newDate);
			user.setLogin_user_id(user_id);
			// 更新时间
			user.setUpdate_date(newDate);
			user.setUpdate_user_id(user_id);

			/*
			 * String open_recruitment =
			 * rlgl090101BeanNew.getOpen_recruitment(); if (open_recruitment ==
			 * null) { open_recruitment = ""; } String type_from =
			 * rlgl090101BeanNew.getType_from(); if (type_from == null) {
			 * type_from = ""; } String recruitment_methods =
			 * rlgl090101BeanNew.getRecruitment_methods(); if
			 * (recruitment_methods == null) { recruitment_methods = ""; }
			 */
			// 用户表登录（审核后）
			personnelInfo.setPersonnel_id(user_id);
			personnelInfo.setPersonnel_nm(user_name);
			personnelInfo.setPersonnel_card_id(user_id);
			personnelInfo.setPersonnel_unit(unit_no);
			// personnelInfo.setPersonnel_joinmode("");
			// personnelInfo.setPersonnel_biko(rlgl090101BeanNew.getPersonnel_biko());
			personnelInfo.setPersonnel_inouttime(newDate.substring(0, 8));
			personnelInfo.setEx_key(0);
			personnelInfo.setDel_kbn("0");
			// 登录时间
			personnelInfo.setLogin_date(newDate);
			personnelInfo.setLogin_user_id(user_id);
			// 更新时间
			personnelInfo.setUpdate_date(newDate);
			personnelInfo.setUpdate_user_id(user_id);
			personnelInfo.setPersonnel_email(personnel_email);

			// 用户表登录（审核前）
			mtb39PersonnelInfo.setPersonnel_id(user_id);
			mtb39PersonnelInfo.setPersonnel_nm(user_name);
			mtb39PersonnelInfo.setPersonnel_card_id(user_id);
			mtb39PersonnelInfo.setPersonnel_unit(unit_no);
			// mtb39PersonnelInfo.setPersonnel_joinmode(rlgl090101BeanNew.getPersonnel_joinmode());
			// mtb39PersonnelInfo.setPersonnel_biko(rlgl090101BeanNew.getPersonnel_biko());
			mtb39PersonnelInfo.setPersonnel_inouttime(newDate.substring(0, 8));
			mtb39PersonnelInfo.setPersonnel_email(personnel_email);
			mtb39PersonnelInfo.setEx_key(0);
			mtb39PersonnelInfo.setDel_kbn("0");
			// 登录时间
			mtb39PersonnelInfo.setLogin_date(newDate);
			mtb39PersonnelInfo.setLogin_user_id(user_id);
			// 更新时间
			mtb39PersonnelInfo.setUpdate_date(newDate);
			mtb39PersonnelInfo.setUpdate_user_id(user_id);

			// 保存数据
			// 依次保存到用户表，个人信息表，个人信息审核表，权限表
			int count = userService.insertRegisterUser(user, personnelInfo, mtb39PersonnelInfo);
			if (count > 0) {
				HttpServletResponse response=ServletActionContext.getResponse();
				 response.setContentType("text/html; charset=UTF-8"); //转码
				    PrintWriter out = response.getWriter();
				    out.flush();
				    out.println("<script>");
				    out.println("alert('注册成功！');");
				    out.println("</script>");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return "comeback";
	}

	/**
	 * 系统message取得
	 * 
	 * @param messageId
	 * @return
	 * @throws Exception
	 */
	public String getMessageById(String messageId) throws Exception {
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

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUnit_no() {
		return unit_no;
	}

	public void setUnit_no(String unit_no) {
		this.unit_no = unit_no;
	}

	public String getAdm_num() {
		return adm_num;
	}

	public void setAdm_num(String adm_num) {
		this.adm_num = adm_num;
	}

	public IMTb04UnitService getMtb04UnitService() {
		return mtb04UnitService;
	}

	public void setMtb04UnitService(IMTb04UnitService mtb04UnitService) {
		this.mtb04UnitService = mtb04UnitService;
	}

	public List<Mtb04Unit> getMtb04unitList() {
		return mtb04unitList;
	}

	public void setMtb04unitList(List<Mtb04Unit> mtb04unitList) {
		this.mtb04unitList = mtb04unitList;
	}

	public List<MTb02Adm> getMtb02Adm094List() {
		return mtb02Adm094List;
	}

	public void setMtb02Adm094List(List<MTb02Adm> mtb02Adm094List) {
		this.mtb02Adm094List = mtb02Adm094List;
	}

	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}

	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
	}

	public String getEscrow_unit_nm() {
		return escrow_unit_nm;
	}

	public void setEscrow_unit_nm(String escrow_unit_nm) {
		this.escrow_unit_nm = escrow_unit_nm;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

}
