package com.rlglsys.util;

import java.util.List;

import com.rlglsys.entity.UserAuthority;

public class UserAuthorityInfo {

	private static final long serialVersionUID = -3907473021615143040L;

	private String uid;
	
	private List<UserAuthority> userAuthorityList;

	public List<UserAuthority> getUserAuthorityList() {
		return userAuthorityList;
	}

	public void setUserAuthorityList(List<UserAuthority> userAuthorityList) {
		this.userAuthorityList = userAuthorityList;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * 有无权限判断
	 * @param apid apid
	 * @param level 权限级别
	 * @return true:有，false：无
	 */
	public boolean hasAuthority(String apid, String level) {

		// 根据取得的权限列表，进行权限check
		for (UserAuthority userAuthority : userAuthorityList) {

			// apid和level的判断
			if (apid.equals(userAuthority.getApid()) && level.equals(userAuthority.getLevel())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 是否业务员
	 * @param gid gid
	 * @return true:是，false：不是
	 */
	public boolean isSalesman() {

		// 根据取得的权限列表，进行check
		for (UserAuthority userAuthority : userAuthorityList) {

			// GID的判断
			if (Constant.ROLE_SALEMAN.equals(userAuthority.getGid())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 是否业务经理
	 * @param gid gid
	 * @return true:是，false：不是
	 */
	public boolean isManage() {

		// 根据取得的权限列表，进行check
		for (UserAuthority userAuthority : userAuthorityList) {

			// GID的判断
			if (Constant.ROLE_MANAGE.equals(userAuthority.getGid())) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 是否业务总监
	 * @param gid gid
	 * @return true:是，false：不是
	 */
	public boolean isDirector() {

		// 根据取得的权限列表，进行check
		for (UserAuthority userAuthority : userAuthorityList) {

			// GID的判断
			if (Constant.ROLE_DIRECTOR.equals(userAuthority.getGid())) {
				return true;
			}
		}

		return false;
	}
}