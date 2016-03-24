package com.rlglsys.taglib;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.components.Component;
import com.opensymphony.xwork2.util.ValueStack;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb03Menu;
import com.rlglsys.entity.Mtb04Unit;
import com.rlglsys.util.Constant;

public class MenuComponent extends Component {
	private List<Mtb03Menu> menuList;
	private Mtb04Unit unitInfo;
	private Mtb01User user;
	private String contextPath = ""; 
	public MenuComponent(ValueStack stack, HttpServletRequest request) {
		super(stack);
		menuList = (List<Mtb03Menu>)request.getSession().getAttribute("menuList");
		unitInfo = (Mtb04Unit)request.getSession().getAttribute("UnitInfo");
        // 用户对象
		user = (Mtb01User)request.getSession().getAttribute(Constant.SESSION_KEY_LOGINUSER);
		request.getSession().removeAttribute("menuList");
		contextPath = request.getContextPath();
	}

	@Override  
	public boolean start(Writer writer) {
		
		String checkResult = "";
		// 单位用户且单位未审核
		if ("1".equals(user.getUser_type()) && "0".equals(unitInfo.getUnit_status()))
		{
			checkResult = "sel";
		}
		// 输出内容
		StringBuilder context = new StringBuilder();
		if (menuList == null)
		{
			return true;
		}
		 
		for (int i = 0; i < menuList.size(); i++) {
			Mtb03Menu mtb03Menu = menuList.get(i);
			
			context.append("<tr id=\"tr" + mtb03Menu.getMenu_id() + "\" ");
			
			String parentId = mtb03Menu.getMenu_param_id();
			if ("".equals(parentId)) {
				context.append("class=\"topmenu\"");
			} else {
				context.append("class=\"tr" + parentId + "show\"");
			}
			context.append(" style=\"display:none;border: 1px solid #ddd;height:48px\" >");
			context.append("<td>");
			context.append("<input type=\"hidden\" value=\"" + i + "\" id=\"indexT\"/>");
			context.append("<input type=\"hidden\" value=\"" + i + "\" id=\"hideCss_" + i + "\"/>");
			context.append("<input type=\"hidden\" value=\"" + parentId + "\" id=\"menu_param_id_" + i + "\"/>");
			int menu_level = mtb03Menu.getMenu_level();
			for (int j = 0; j < menu_level; j++) {
			    context.append("&nbsp;&nbsp;&nbsp;&nbsp;");
		    }
			int menu_level_crl1 = mtb03Menu.getMenu_level_crl1();
			if (menu_level_crl1 == 0) {
			    context.append("&nbsp;&nbsp;&nbsp;&nbsp;");
			    context.append("<a href=\"#\" id=\"aSubMenuName\" style=\"text-decoration:none;color:#333333;font-size:16px;\" onclick=\"menuClick('");
				context.append(mtb03Menu.getMenu_action());
				context.append("','");
				context.append(mtb03Menu.getExpend_01());
				context.append("','");
				context.append(mtb03Menu.getMenu_name());
				context.append("','");
				context.append(mtb03Menu.getOnly_search());
				context.append("','");
				context.append(checkResult);
				context.append("')\">");
			    context.append(mtb03Menu.getMenu_name());
			    context.append("</a>");
			} else {
			    context.append("<img id = \"openclose_" + i +"\"");
			    context.append(" class = \"opencloseicon\" src="+contextPath+"/images/statu_close.png  align=\"absmiddle\"></img>");
			    context.append("<a href=\"#\" id=\"aMenuName\"  style=\"text-decoration:none;color:#333333;font-size:16px;\">");
			    context.append(mtb03Menu.getMenu_name());
			    context.append("</a><s:hidden id=\"showflg_" + i +"\" value=\"0\"/>");
			}
			context.append("</td></tr>");
		}
		
		try {
			writer.write(context.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	 }
//	@Override  
//	public boolean start(Writer writer) {
//		// 输出内容
//		StringBuilder context = new StringBuilder();   
//		context.append("<ul>");
//		for (int i = 0; i < menuList.size(); i++) {
//			Mtb03Menu mtb03Menu = menuList.get(i);
//			context.append("<li>");
//			
//			if (mtb03Menu.getMenu_level_crl1() == 1) {
//				if (mtb03Menu.getMenu_level() == 0) {
//					context.append("<a href=\"#\"><dt class=\"sidenav_bt\">" + mtb03Menu.getMenu_name() + "</dt></a>");
//				} else {
//					context.append("<a href=\"#\"><dt class=\"sidenav_con\">" + mtb03Menu.getMenu_name() + "</dt></a>");
//				}
//			} else {
//				context.append("<dt class=\"sidenav_con\" onclick=\"menuClick('");
//				context.append(mtb03Menu.getMenu_action());
//				context.append("','");
//				context.append(mtb03Menu.getExpend_01());
//				context.append("','");
//				context.append(mtb03Menu.getMenu_name());
//				context.append("','");
//				context.append(mtb03Menu.getOnly_search());
//				context.append("')\">");
//				context.append(mtb03Menu.getMenu_name());
//				context.append("</dt>");
//			}
//			
//			if (mtb03Menu.getMenu_level_crl1() == 1) {
//				context.append("<ul class=\"child\">");
//			}
//			int menu_level_crl3 = mtb03Menu.getMenu_level_crl3();
//			for (int j = 0; j < menu_level_crl3; j++) {
//				context.append("</li></ul>");
//			}
////			if (mtb03Menu.getMenu_level_crl1() != 0) {
////				context.append("</li>");
////			}
//		}
//		context.append("</ul>");
//		
//		try {
//			writer.write(context.toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return true;
//	 }
	 @Override
	 public boolean end(Writer writer, String body) {
		 return super.end(writer, body);  
	 }
}
