package com.rlglsys.taglib;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.components.Component;
import com.opensymphony.xwork2.util.ValueStack;
import com.rlglsys.entity.Mtb03Menu;

public class RoleMenuComponent extends Component {
	private List<Mtb03Menu> menuList;
	@SuppressWarnings("unchecked")
	public RoleMenuComponent(ValueStack stack, HttpServletRequest request) {
		super(stack);
		menuList = (List<Mtb03Menu>)request.getSession().getAttribute("menuList");
		request.getSession().removeAttribute("menuList");
	}

	@Override  
	public boolean start(Writer writer) {
		// 输出内容
		StringBuilder context = new StringBuilder();
		if (menuList == null)
		{
			return true;
		}

		for (int i = 0; i < menuList.size(); i++) {
			Mtb03Menu mtb03Menu = menuList.get(i);
			String parentId = mtb03Menu.getMenu_param_id();
			int index = i + 1;
			int menu_level = mtb03Menu.getMenu_level() + 1;

			
			if (index%2 == 1)
			{
				context.append("<tr class=\"RowOdd\" id = \"tr" + mtb03Menu.getMenu_id() + "\">");
			} else {
				context.append("<tr class=\"RowEven\" id = \"tr" + mtb03Menu.getMenu_id() + "\">");
			}

			context.append("<td class=\"tdc\">");
			
			context.append("<input type=\"checkbox\" name=\"name_" + mtb03Menu.getMenu_id()+"\" value=\"true\" id=\"select_kbn_check\" class=\"\"/>");
			context.append("<input type=\"hidden\" id=\"__checkbox_select_kbn_check\" name=\"__checkbox_name_" +mtb03Menu.getMenu_id()+"\" value=\"true\" />");
			context.append("<input type=\"hidden\" name=\"menuResultList[" + i+ "].menu_param_id\" value=\""+ parentId + "\" id=\"menuResultList[" + i+ "].menu_param_id\"/>");
			context.append("<input type=\"hidden\" name=\"menuResultList[" + i+ "].menu_id\" value=\""+ mtb03Menu.getMenu_id() + "\" id=\"menuResultList[" + i+ "].menu_id\"/>");
			context.append("</td>");
			context.append(" <td class=\"tdc\"><input type=\"hidden\" id=\"hidecss\" value=\"\"/>" + index +"</td>");
			context.append("<td class=\"tdl\">");
			for (int j = 0; j < mtb03Menu.getMenu_level(); j++)
			{
				context.append("&nbsp;&nbsp;&nbsp;&nbsp;");
			}
			if (mtb03Menu.getMenu_level_crl1() == 0)
			{
				context.append("&nbsp;&nbsp;&nbsp;&nbsp;" + mtb03Menu.getMenu_name());
			} else {
				context.append("<img id = \"openclose\" src=\"${pageContext.request.contextPath}/statu_close.png\" align=\"absmiddle\"></img>");
				context.append("<a href=\"#\" id=\"aMenuName\">" + mtb03Menu.getMenu_name() + "</a>");
				context.append("<input type=\"hidden\" name=\"\" value=\"0\" id=\"showflg\"/>");
			}
			context.append("<input type=\"hidden\" name=\"menuResultList[" + i+ "].menu_name\" value=\""+ mtb03Menu.getMenu_name() + "\" id=\"menuResultList[" + i+ "].menu_name\"/>");
			context.append(" </td>");
			context.append("<td class=\"tdl\">");
			context.append(menu_level + "级菜单");
			context.append("<input type=\"hidden\" name=\"menuResultList[" + i+ "].menu_level\" value=\""+ mtb03Menu.getMenu_level() + "\" id=\"menuResultList[" + i+ "].menu_level\"/>");
			context.append("<input type=\"hidden\" name=\"menuResultList[" + i+ "].menu_level_crl1\" value=\""+ mtb03Menu.getMenu_level_crl1() + "\" id=\"menuResultList[" + i+ "].menu_level_crl1\"/>");
			context.append("</td>");
			context.append("<td class=\"tdc\">");
			if (mtb03Menu.getMenu_level_crl1() == 0)
			{
				context.append("<input type=\"checkbox\" name=\"serch_kbn_check\" value=\"true\" id=\"serch_kbn_check\"/>");
				context.append("<input type=\"hidden\" id=\"__checkbox_serch_kbn_check\" name=\"__checkbox_serch_kbn_check\" value=\"true\"/>");
			}
			
			context.append("</td>");
			context.append(" </tr>");
		}
		try {
			writer.write(context.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	 }

	 @Override
	 public boolean end(Writer writer, String body) {
		 return super.end(writer, body);  
	 }
}
