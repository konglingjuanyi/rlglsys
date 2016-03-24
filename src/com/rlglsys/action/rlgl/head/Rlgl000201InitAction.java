package com.rlglsys.action.rlgl.head;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.rlglsys.base.BaseAction;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.service.IRlgl010204Service;
import com.rlglsys.util.Constant;

public class Rlgl000201InitAction extends BaseAction {

	private static final long serialVersionUID = 483775379303898192L;
	private String loginDate;
	private String userFlag;
	private String user_id;
	private String unitName;
	private String user_name;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	private IRlgl010204Service rlgl010204Service;

	public IRlgl010204Service getRlgl010204Service() {
		return rlgl010204Service;
	}

	public void setRlgl010204Service(IRlgl010204Service rlgl010204Service) {
		this.rlgl010204Service = rlgl010204Service;
	}

	/**
	 * 标题初始化
	 * 
	 * @return SUCCESS
	 * @throws Exception
	 */
	@Override
	public String doExecute() throws Exception {

		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
		loginDate = dateFormat.format(now) + "  " + this.getWeekOfDate(now);
		// 登陆用户信息
		Mtb01User user = (Mtb01User) super.getSession(Constant.SESSION_KEY_LOGINUSER);
		user_id = (String) user.getUser_id();
		user_name = (String) user.getUser_name();
		unitName = rlgl010204Service.getUnitName(user.getUnit_no());
		String userUnit = "";
		if (user.getUnit_no().length() > 3) {
			userUnit = StringUtils.left(user.getUnit_no(), 4);
		}
		if ("3711".equals(userUnit) && !user_id.contains("A")) {
			userFlag = "1";
		}
		return SUCCESS;
	}

	public String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * @return the loginDate
	 */
	public String getLoginDate() {
		return loginDate;
	}

	/**
	 * @param loginDate
	 *            the loginDate to set
	 */
	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public String getUserFlag() {
		return userFlag;
	}

	public void setUserFlag(String userFlag) {
		this.userFlag = userFlag;
	}

}