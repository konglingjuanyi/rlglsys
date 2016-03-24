package com.rlglsys.action.learnonline;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.rlglsys.base.BaseAction;

public class Rlgl100100_2TimeInitAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String StartDate = "";
	private String EndDate = "";

	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	@Override
	protected String doExecute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("fla");
		if ("001".equals(name)) {
			return "init";
		} else if ("002".equals(name)) {
			return "success";
		} else {
			return "press";
		}

	}
}
