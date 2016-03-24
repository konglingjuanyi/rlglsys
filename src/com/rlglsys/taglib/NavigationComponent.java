package com.rlglsys.taglib;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;
import com.rlglsys.bean.NavigationInfoBean;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.util.Constant;

public class NavigationComponent extends Component {

	private NavigationInfoBean navigationInfoBean;
	private String ContextPath ="";
	public NavigationComponent(ValueStack stack, HttpServletRequest request) {
		super(stack);
		getNavigationInfo(request);
		ContextPath= request.getContextPath();
	}
 
	@Override  
	public boolean start(Writer writer) {
		if (navigationInfoBean.getId() == "") {
			return true;
		}
		// 输出内容
		StringBuilder context = new StringBuilder();
		try {
			context.append("<ol class=\"am-breadcrumb\">");
			context.append("<li><a href=\"#\" class=\"am-icon-home\">"+navigationInfoBean.getTitle()+"</a></li>"); 
			for (int i = 0; i < navigationInfoBean.getContext().size(); i++) { 
				context.append("<li><a href=\"#\">"+navigationInfoBean.getContext().get(i)+"</a></li>"); 
			}
			context.append("</ol>");
			writer.write(context.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return true;
	 }
	 @Override
	 public boolean end(Writer writer, String body) {  
		 return super.end(writer, body);  
	 }

	 private void getNavigationInfo(HttpServletRequest request) {
		 navigationInfoBean =  new NavigationInfoBean();
		 if (request.getAttribute("naviId") == null || ("").equals(request.getAttribute("naviId"))) {
			 return;
		 }
		 navigationInfoBean.setId(request.getAttribute("naviId").toString());
		 if (request.getSession().getAttribute(Constant.SESSION_KEY_LOGINUSER) != null) {
			 Mtb01User loginUser = (Mtb01User)request.getSession().getAttribute(Constant.SESSION_KEY_LOGINUSER);
			 navigationInfoBean.setUserName(loginUser.getUser_name());
		 }
		 InputStream is = null;
		 Properties p = new Properties();
		 is = this.getClass().getResourceAsStream("/navigation.properties");
		 try {
			p.load(is);
			is.close();
		    String context = p.getProperty(navigationInfoBean.getId());
		    String[] contexts = context.split(",");
		    navigationInfoBean.setTitle(contexts[0]);
		    for (int i = 1; i < contexts.length; i++) {
		    	navigationInfoBean.getContext().add(contexts[i]);
		    }
		} catch (IOException e) {
			navigationInfoBean.setTitle("");
		}
	 }
}
