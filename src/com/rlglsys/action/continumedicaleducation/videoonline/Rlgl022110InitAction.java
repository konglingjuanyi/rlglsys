package com.rlglsys.action.continumedicaleducation.videoonline;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.apache.struts2.ServletActionContext;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl500102Bean;
import com.rlglsys.bean.Rlgl500103Bean;
import com.rlglsys.entity.MTb81CourseExams;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb120SysUrl;
import com.rlglsys.entity.Mtb121PublicCourseWare;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IRlgl020807Service;
import com.rlglsys.service.IRlgl050101Service;
import com.rlglsys.service.IRlgl060311Service;
import com.rlglsys.service.IRlgl100101Service;
import com.rlglsys.service.IUserService;
import com.rlglsys.util.Common;
import com.rlglsys.util.Constant;
import com.rlglsys.util.DateTimeManager;
import com.rlglsys.util.MailSenderInfo;
import com.rlglsys.util.SimpleMailSender;

public class Rlgl022110InitAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	// 是否通过
	private String pass = "";
	// 课程名称
	private String course_name1 = "";
	// 课程编号
	private String course_id1 = "";
	// 画面
	private String PageFlg1 = "";
	// 金额
	private String Amount1 = "";
	// 用户编号
	private String user_id1 = "";
	// 邮箱
	private String email = "";

	public String getUser_id1() {
		return user_id1;
	}

	public void setUser_id1(String user_id1) {
		this.user_id1 = user_id1;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// 设置链接数据库
	private IRlgl100101Service rlgl100101Service;

	private IRlgl020807Service rlgl020807Service;
	private IMTb02AdmService mtb02AdmService;
	private IRlgl050101Service rlgl050101Service;
	  // 用户Service
    private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}

	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
	}

	public IRlgl050101Service getRlgl050101Service() {
		return rlgl050101Service;
	}

	public void setRlgl050101Service(IRlgl050101Service rlgl050101Service) {
		this.rlgl050101Service = rlgl050101Service;
	}

	public IRlgl020807Service getRlgl020807Service() {
		return rlgl020807Service;
	}

	public void setRlgl020807Service(IRlgl020807Service rlgl020807Service) {
		this.rlgl020807Service = rlgl020807Service;
	}

	private IRlgl060311Service rlgl060311Service;

	public IRlgl060311Service getRlgl060311Service() {
		return rlgl060311Service;
	}

	public void setRlgl060311Service(IRlgl060311Service rlgl060311Service) {
		this.rlgl060311Service = rlgl060311Service;
	}

	public IRlgl100101Service getRlgl100101Service() {
		return rlgl100101Service;
	}

	public void setRlgl100101Service(IRlgl100101Service rlgl100101Service) {
		this.rlgl100101Service = rlgl100101Service;
	}

	public String getCourse_name1() {
		return course_name1;
	}

	public void setCourse_name1(String course_name1) {
		this.course_name1 = course_name1;
	}

	public String getCourse_id1() {
		return course_id1;
	}

	public void setCourse_id1(String course_id1) {
		this.course_id1 = course_id1;
	}

	public String getPageFlg1() {
		return PageFlg1;
	}

	public void setPageFlg1(String pageFlg1) {
		PageFlg1 = pageFlg1;
	}

	public String getAmount1() {
		return Amount1;
	}

	public void setAmount1(String amount1) {
		Amount1 = amount1;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	protected String doExecute() throws Exception {
		try {
			if (PageFlg1.equals("forgetPassword")) {
				int incount = rlgl100101Service.getUserData(user_id1, email);
				if (incount > 0) {
					// 系统网址取得
				    Mtb120SysUrl urlInfoNew = new Mtb120SysUrl();
					urlInfoNew.setUrl_id("001");
					Mtb120SysUrl urlInfo = userService.getMtb120SysurlInfo(urlInfoNew);
					String sysUrl = "";
					if (urlInfo != null)
					{
						sysUrl = urlInfo.getUrl();
					}
					// 这个类主要是设置邮件
					MailSenderInfo mailInfo = new MailSenderInfo();
					mailInfo.setMailServerHost("smtp.163.com");
					mailInfo.setMailServerPort("25");
					mailInfo.setValidate(true);
					mailInfo.setUserName("");//您的邮箱
					mailInfo.setPassword("");// 您的邮箱密码
					mailInfo.setFromAddress("");//您的邮箱
					mailInfo.setToAddress(email);
					mailInfo.setSubject("密码找回");
					mailInfo.setContent(sysUrl+"/rlgl000103Init.action?code="+user_id1);
					// 这个类主要来发送邮件
					SimpleMailSender sms = new SimpleMailSender();
					// sms.sendTextMail(mailInfo);//发送文体格式
					sms.sendHtmlMail(mailInfo);// 发送html格式
					pass = "1";
				} else {
					HttpServletResponse response=ServletActionContext.getResponse();
					 response.setContentType("text/html; charset=UTF-8"); //转码
					    PrintWriter out = response.getWriter();
					    out.flush();
					    out.println("<script>");
					    out.println("alert('请输入正确的用户名和密码！');");
					    out.println("</script>");
					pass = "0";
					return "success";
				}
			} else {
				int fla = 0;
				int count = 0;
				// 用户对象
				Mtb01User loginUser = (Mtb01User) super.getSession(Constant.SESSION_KEY_LOGINUSER);
				String user_id = loginUser.getPersonnel_id();
				if (PageFlg1.equals("rlg022011")) {
					MTb81CourseExams mtb81Bean = new MTb81CourseExams();
					mtb81Bean.setCourse_id(course_id1);
					mtb81Bean.setIsapply("1");
					mtb81Bean.setUser_id(user_id);
					mtb81Bean.setUpdate_date(DateTimeManager.getCurrentDateStrFormat());
					mtb81Bean.setUpdate_user_id(user_id);
					// 申请学分
					count = rlgl100101Service.updateMtb81CourseExams(mtb81Bean);

				} else if (PageFlg1.equals("rlg100112")) {
					// 获取年度用变量
					Common common = new Common();
					String area_id = mtb02AdmService.getAdmName("237", "01");
					String credit_year = Integer.toString(common.getNowCreditYear(area_id, rlgl050101Service));
					Mtb121PublicCourseWare mtb121Bean = new Mtb121PublicCourseWare();
					mtb121Bean = rlgl020807Service.getPublicCourseWareById(course_id1);
					mtb121Bean.setUser_id(user_id);
					mtb121Bean.setCredit_year(credit_year);
					// 公共课程报名
					count = rlgl060311Service.insertExamWareSelectData(mtb121Bean);
				}
				if (count == 0) {
					return "success";
				}
				// 添加消费记录
				Rlgl500103Bean rlgl500103Bean = new Rlgl500103Bean();
				rlgl500103Bean.setUser_id(user_id);
				rlgl500103Bean.setCOURSE_ID(course_id1);
				rlgl500103Bean.setCategory(Amount1);
				rlgl500103Bean.setName(course_name1);
				rlgl500103Bean.setTime(DateTimeManager.getCurrentDateStrFormat());
				rlgl500103Bean.setLogin_user_id(user_id);
				rlgl500103Bean.setLogin_date(DateTimeManager.getCurrentDateStrFormat());
				rlgl500103Bean.setUpdate_date(DateTimeManager.getCurrentDateStrFormat());
				rlgl500103Bean.setUpdate_user_id(user_id);
				fla = rlgl100101Service.insertRecordData(rlgl500103Bean);
				if (fla > 0) {
					fla = 0;
					// 修改余额
					double yue = 0;
					if(!"".equals(Amount1)&&Amount1!=null)
					{
						yue =Double.parseDouble(Amount1);
					}
					Rlgl500102Bean rlgl500102Bean = new Rlgl500102Bean();
					rlgl500102Bean.setUser_id(user_id);
					rlgl500102Bean.setBalance(yue);
					rlgl500102Bean.setUpdate_date(DateTimeManager.getCurrentDateStrFormat());
					rlgl500102Bean.setUpdate_user_id(user_id);
					fla = rlgl100101Service.updateBalanceData(rlgl500102Bean);
				}

				if (fla > 0) {
					pass = "1";
				} else {
					pass = "0";
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "success";
	}
}
