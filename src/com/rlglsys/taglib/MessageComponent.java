package com.rlglsys.taglib;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;
import com.rlglsys.bean.MessageInfoBean;

public class MessageComponent extends Component {

	private List<MessageInfoBean> message;
	private String msgType;
	private String ContextPath;
	
	public MessageComponent(ValueStack stack, HttpServletRequest request) {
		super(stack);
		getMessage(request);
		ContextPath =request.getContextPath();
	}

	@Override  
	public boolean start(Writer writer) {
		if (message == null) {
			return true;
		}
	
		// 输出内容
		StringBuilder context = new StringBuilder();   
		try {
			context.append("<table width=\"100%\" cellpadding=\"1\" cellspacing=\"1\" border=\"0\" >");
			context.append("<tr>");
			context.append("<td valign=\"middle\"><font style=\"font-size:14px;color=#FF0000;font-weight:bold;\">");
			if ("1".equals(msgType)) {
				context.append("&nbsp;<img align=\"absmiddle\" src="+ContextPath+"/images/error.gif>&nbsp;");
			} else {
				context.append("&nbsp;<img align=\"absmiddle\" src="+ContextPath+"/images/sucess.gif>&nbsp;");
			}
			context.append("<a href=\"javascript:void(0)\" onclick=\"return showMessage(");
			context.append(msgType);
			context.append(");\">");
			if ("1".equals(msgType)) {
				context.append("发生错误，详细信息请点击这里");
			} else {
				context.append("系统提示，详细信息请点击这里");
			}
			context.append("</a></font>");
			context.append("<input type=\"hidden\" id=\"message\" name=\"message\" value=\"");
			String msg = "";
			for (int i = 0; i < message.size(); i++) {
				msg = msg + (i + 1) + "." + message.get(i).getMsg();
			}
			context.append(msg);
			context.append("\">");
			context.append("</td></tr></table>");

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
	private void getMessage(HttpServletRequest request) {
	    if (request.getAttribute("rlglsys.message.messageBean") == null) {
	    	return;
		}
	    msgType = request.getAttribute("rlglsys.message.type").toString();
	    message = new ArrayList<MessageInfoBean>();
		List messageInfo = (List)request.getAttribute("rlglsys.message.messageBean");
		InputStream is = null;
		Properties p = new Properties();
		is = this.getClass().getResourceAsStream("/systemMessage.properties");
		try {
		    p.load(is);
			is.close();
			for (int i = 0; i < messageInfo.size(); i++) {
				MessageInfoBean messageInfoBean = (MessageInfoBean)messageInfo.get(i);
				String msg = p.getProperty(messageInfoBean.getMsgId());
				if (msg == null) {
					msg = "此信息不存在！";
				}
				messageInfoBean.setMsg(MessageFormat.format(msg, messageInfoBean.getParam()));
				message.add(messageInfoBean);
			}
		} catch (IOException e) {
			message = null;
		} 
	}
}
