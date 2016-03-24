package com.rlglsys.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

import com.ecc.tool.Signature;
import com.opensymphony.xwork2.ActionSupport;
import com.rlglsys.bean.MessageInfoBean;
import com.rlglsys.bean.Rlgl100101Bean;
import com.rlglsys.bean.Rlgl100101_1Bean;
import com.rlglsys.bean.Rlgl500102Bean;
import com.rlglsys.entity.DBCommon;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb119MerchantInfo;
import com.rlglsys.entity.TTb02PrepayMsg;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IMerchantInfoService;
import com.rlglsys.service.IRlgl050101Service;
import com.rlglsys.service.IRlgl100100Service;
import com.rlglsys.service.IRlgl100101Service;
import com.rlglsys.util.BeanFactory;
import com.rlglsys.util.Common;
import com.rlglsys.util.Constant;
import com.rlglsys.util.DateTimeManager;
import com.rlglsys.util.DesUtil;
import com.rlglsys.util.HttpPostUtils;

public abstract class BaseAction extends ActionSupport {

	private static final long serialVersionUID = -8893643179019791643L;
	// 导航信息ID
		private String navigationId = "";
	// 页面ID
	private String screenId = "";
	private String only_search = "";
	private String checkResult = "";

	public void setScreenId(String screenId) {
		this.screenId = screenId;
	}

	public String getScreenId() {
		return this.getClass().getSimpleName().substring(0, 10);
	}

	public String getNavigationId() {
		return navigationId;
	}

	public void setNavigationId(String navigationId) {
		this.navigationId = navigationId;
	}
	protected void saveErrorMessage(String messageId) {
		saveErrorMessage(messageId, null);
	}
	protected int getPageCount() {
		InputStream is = null;
		Properties p = new Properties();
		is = this.getClass().getResourceAsStream("/pagecount.properties");
		try {
			p.load(is);
			is.close();
		    String countPage = p.getProperty(screenId + "_countOfPage");
		    if (countPage == null || "".equals(countPage)) {
		    	return 10;
		    } else {
		    	return Integer.valueOf(countPage);
		    }
		} catch (IOException e) {
			return 10;
		}
	}
	protected void saveErrorMessage(String messageId, Object[] args) {
		List<MessageInfoBean> message = new ArrayList<MessageInfoBean>();
		if (this.getRequest().getAttribute("rlglsys.message.messageBean") != null) {
			message = (List)this.getRequest().getAttribute("rlglsys.message.messageBean");
		}
		MessageInfoBean messageInfoBean = new MessageInfoBean();
		messageInfoBean.setMsgId(messageId);
		messageInfoBean.setMsgType("1");
		messageInfoBean.setParam(args);
		message.add(messageInfoBean);

		this.getRequest().setAttribute("rlglsys.message.type", "1");
		this.getRequest().setAttribute("rlglsys.message.messageBean", message);
	}
	protected void saveInfoMessage(String messageId) {
		saveInfoMessage(messageId, null);
	}
	protected void saveInfoMessage(String messageId, Object[] args) {
		String msgType = "";
		List<MessageInfoBean> message = new ArrayList<MessageInfoBean>();
		if (this.getRequest().getAttribute("rlglsys.message.type") != null) {
			msgType = this.getRequest().getAttribute("rlglsys.message.type").toString();
		}
		if (!"1".equals(msgType)) {
			msgType = "2";
		}
		if (this.getRequest().getAttribute("rlglsys.message.messageBean") != null) {
			message = (List)this.getRequest().getAttribute("rlglsys.message.messageBean");
		}
		MessageInfoBean messageInfoBean = new MessageInfoBean();
		messageInfoBean.setMsgId(messageId);
		messageInfoBean.setMsgType("2");
		messageInfoBean.setParam(args);
		message.add(messageInfoBean);

		this.getRequest().setAttribute("rlglsys.message.type", msgType);
		this.getRequest().setAttribute("rlglsys.message.messageBean", message);
	}

	public String execute() throws Exception {
		setScreenId(getScreenId());

		// 控制画面按钮非活性
		if (this.getRequest().getParameter("only_search") != null) {
			only_search = this.getRequest().getParameter("only_search");
			this.getRequest().getSession().setAttribute("only_search", only_search);
		}
		if ("".equals(only_search) && this.getRequest().getSession().getAttribute("only_search") != null) {
			only_search = (String)this.getRequest().getSession().getAttribute("only_search");
		}
		
		// 根据用户状态判断画面功能
		if (this.getRequest().getParameter("checkResult") != null) {
			checkResult = this.getRequest().getParameter("checkResult");
			this.getRequest().getSession().setAttribute("checkResult", checkResult);
		}
		if ("".equals(checkResult) && this.getRequest().getSession().getAttribute("checkResult") != null) {
			checkResult = (String)this.getRequest().getSession().getAttribute("checkResult");
		}
		
		if (this.getRequest().getParameter("naviId") != null) {
			setNavigationId(this.getRequest().getParameter("naviId"));
			this.getRequest().getSession().setAttribute("naviId", this.getNavigationId());
		}
		if ("".equals(this.getNavigationId()) && this.getRequest().getSession().getAttribute("naviId") != null) {
			setNavigationId(this.getRequest().getSession().getAttribute("naviId").toString());
		}
		this.getRequest().setAttribute("naviId", this.getNavigationId());
		return doExecute();
	}
	protected abstract String doExecute() throws Exception;

	protected void setDBCommonColOnInsert(DBCommon dbcommon) {
		dbcommon.setDel_kbn("0");
		dbcommon.setEx_key(1);
		//获得session中的用户对象
		Mtb01User mtb01User=(Mtb01User)getSession(Constant.SESSION_KEY_LOGINUSER);
		if(mtb01User !=null)
		{
			dbcommon.setLogin_user_id(mtb01User.getUser_id());
			dbcommon.setUpdate_user_id(mtb01User.getUser_id());
		}
		dbcommon.setLogin_date(DateTimeManager.getSystemDate14());
		dbcommon.setUpdate_date(DateTimeManager.getSystemDate14());
    }

	protected void setDBCommonColOnUpdate(DBCommon dbcommon) {
		//获得session中的用户对象
		Mtb01User mtb01User=(Mtb01User)getSession(Constant.SESSION_KEY_LOGINUSER);
		if(mtb01User !=null)
		{
			dbcommon.setUpdate_user_id(mtb01User.getUser_id());
		}
		dbcommon.setUpdate_date(DateTimeManager.getSystemDate14());
		dbcommon.setDel_kbn("0");
		dbcommon.setEx_key(dbcommon.getEx_key() + 1);
    }

	protected void setDBCommonColOnDelete(DBCommon dbcommon) {
		dbcommon.setDel_kbn("1");
		dbcommon.setEx_key(dbcommon.getEx_key() + 1);
		dbcommon.setUpdate_date(DateTimeManager.getSystemDate14());
		//获得session中的用户对象
		Mtb01User mtb01User=(Mtb01User)getSession(Constant.SESSION_KEY_LOGINUSER);
		if(mtb01User !=null)
		{
			dbcommon.setUpdate_user_id(mtb01User.getUser_id());
		}
    }
	
	// 获取交费成功数据
		public void GetPrepay(IRlgl100101Service rlgl100101Service,IMTb02AdmService mtb02AdmService,IRlgl050101Service rlgl050101Service,IMerchantInfoService merchantInfoService) throws Exception {
			// 获取共通
			Mtb01User loginUser = (Mtb01User) getSession(Constant.SESSION_KEY_LOGINUSER);
			// 获取单位编号
			String user_id = loginUser.getUser_id();
			// 获得当前年度
			Common common = new Common();
			// 获得当前学分年度（申请年度）
			String area_id = mtb02AdmService.getAdmName("237", "01");
			String payyear = Integer.toString(common.getNowCreditYear(area_id, rlgl050101Service));
			// 到数据库中查数据，得到登陆用户最近登陆的订单号，
			// 然后调用银联支付的接口，查询银联端的支付状态
			// 得到银行的银联的支付状态后和数据库中的状态进行比对

			Rlgl100101_1Bean rlgl100101_1Bean = new Rlgl100101_1Bean();
			// 设置查询条件，用户名和缴费年度
			rlgl100101_1Bean.setUserId(user_id);
			rlgl100101_1Bean.setPay_year(payyear);
			// 查询出缴费信息
			// TTb02PrepayMsg tt02Msg = null;
			List<TTb02PrepayMsg> jFInfoList = rlgl100101Service.getJFInfoByUserId(rlgl100101_1Bean);

			// 循环查询到的数据
			for (TTb02PrepayMsg tt02Msg : jFInfoList) {
				// 解密
				BeanFactory bf = new BeanFactory();
				Map<String, String> B01fields = new HashMap<String, String>();
				// 解密
				bf.reinstallFields(tt02Msg, DesUtil.class, "dec" + "rypt", B01fields);

				// 调用银联的查询接口，查询用户的缴费信息
				Mtb119MerchantInfo mtb119MerchantInfo = merchantInfoService.getMerchantInfo();
				// 商户号
				String merId = mtb119MerchantInfo.getMerchantID();
				// key
				String key = mtb119MerchantInfo.getKey();
				// 查询地址
				String urlAddress = Constant.CHAXUNDIZHI;
				// 订单号
				String dealQuery = tt02Msg.getClum003();
				// 数字签名
				String signature = Signature.querySignure(merId, dealQuery, key);

				HashMap<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("merId", merId);
				paramMap.put("dealQuery", dealQuery);
				paramMap.put("dealSignure", signature);
				// 到银联支付提供的查询接口处去查询缴费信息
				String strResult = HttpPostUtils.httpPost(urlAddress, paramMap);
				String[] arrResult = strResult.split("\\|");
				// SUCCESS 为银联支付方面缴费成功返回的信息
				if ("SUCCESS".equals(arrResult[arrResult.length - 1])) {
					// 根据转换出来的信息进行比对
					// 如果银联方面的信息是缴费成功的，数据中的信息不成功，进行更新处理
					String state = tt02Msg.getClum004();
					if (state != null && !"".equals(state)) {
						if (state.endsWith("1") == false) {
							// 更新数据
							// arrResult[3];
							Rlgl100101_1Bean rlgl100101_1Bean1 = new Rlgl100101_1Bean();
							rlgl100101_1Bean1.setUserId(user_id);
							rlgl100101_1Bean1.setTransactionID(dealQuery);
							rlgl100101_1Bean1.setSucceed("1");
							rlgl100101_1Bean1.setPay_year(payyear);
							rlgl100101_1Bean1.setAmount(arrResult[3]);
							rlgl100101Service.updateData(rlgl100101_1Bean1);
							// 缴费成功的状态
							// flg = "1";
							continue;
						} else {
							// 缴费成功的状态
							// flg = "1";
							continue;
						}
					}
				}
			}
		}
	
	public HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    public HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }

    public  HttpSession getSession() {
        return  getRequest().getSession(true);
    }

    public Object getSession(String key) {
        return getSession().getAttribute(key);
    }

    public void setSession(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    public void removeSession(String key) {
        getSession().removeAttribute(key);
    }

    public String getCtx() {
		return getRequest().getContextPath();
	}

	public String getOnly_search() {
		return only_search;
	}

	public void setOnly_search(String only_search) {
		this.only_search = only_search;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
}
