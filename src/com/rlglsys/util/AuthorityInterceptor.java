package com.rlglsys.util;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -3907473021615143040L;

	private static Log log = LogFactory.getLog(AuthorityInterceptor.class);
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		log.info("intercept START");
		
		Map<String, ?> session = invocation.getInvocationContext().getSession();
		if (session != null) {
			Object user = session.get(Constant.SESSION_KEY_USER_AUTHORITY);
			if (user != null) {
				log.info("intercept END");
				return invocation.invoke();
			}
		}

		log.info("intercept END redirect login");
		return "login";
	}

}