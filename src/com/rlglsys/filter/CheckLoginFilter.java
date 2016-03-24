package com.rlglsys.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rlglsys.util.Constant;

public class CheckLoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * 该方法主要实现的是，如果用户没有登录，不能进行系统内的任何操作。
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String url = request.getRequestURI();
		// 手机端对应
		if (url.contains("OnMobile")) {
			chain.doFilter(request, response);
			return;
		}
		if (url.contains("learnonlinecallback")) {
			chain.doFilter(request, response);
			return;
		}

		if (url.contains("rlgl100104Init.action")) {
			chain.doFilter(request, response);
			return;
		}
		if (url.contains("coursInit_jn.action")) {
			chain.doFilter(request, response);
			return;
		}
		if (url.contains("rlgl022008Init.action")) {
			chain.doFilter(request, response);
			return;
		}

		// 用户注册 rlglregiter.action
		if (url.contains("rlglregiter")) {
			chain.doFilter(request, response);
			return;
		}

		if (url.contains("doregiter")) {
			chain.doFilter(request, response);
			return;
		}

		if (url.contains("rlgl300101Init")) {
			chain.doFilter(request, response);
			return;
		}

		if (url.contains("rlgl300101Search")) {
			chain.doFilter(request, response);
			return;
		}

		// 网上缴费返回
		if (url.contains("rlglecpayCallback")) {
			chain.doFilter(request, response);
			return;
		}
		
		// 网上缴费返回提示页面。
		if (url.contains("rlglpayCallback")) {
			chain.doFilter(request, response);
			return;
		}

		// 密码找回
		if (url.contains("Rlgl022110Init")) {
			chain.doFilter(request, response);
			return;
		}
		//密码修改初始化
		if (url.contains("rlgl000103Init")) {
			chain.doFilter(request, response);
			return;
		}
		// 密码修改
		if (url.contains("rlgl000103Update")) {
			chain.doFilter(request, response);
			return;
		}
		if (!url.contains("/topMenu") && !url.contains("/doRedirect.") && !url.contains("doLogin")) {
			HttpSession session = request.getSession();
			Object userInfo = session.getAttribute(Constant.SESSION_KEY_LOGINUSER);
			Object selectPage = session.getAttribute("selectPage");
			if (userInfo == null || selectPage == null) {

				if (!url.contains("/topMenu") && !url.contains("/doRedirect.") && !url.contains("/sessioncheck.")) {
					session.setAttribute("sessionTimeOutFLg", "1");
					// response.sendRedirect(request.getContextPath() +
					// "/index.jsp");
					/* session失效后用户再次操作时页面将会跳回登录页面。 */
					PrintWriter out = response.getWriter();
					String url1 = request.getContextPath() + "/index.jsp";
					// 利用js跳出iframe。
					out.println("<script language=\"javascript\">");
					out.println("top.location=\"" + url1 + "\";");
					out.println("</script>");

					return;
				} else {
					chain.doFilter(request, response);
				}
			} else {
				chain.doFilter(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
