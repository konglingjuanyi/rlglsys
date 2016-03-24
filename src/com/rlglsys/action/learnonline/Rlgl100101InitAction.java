package com.rlglsys.action.learnonline;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl100101Bean;
import com.rlglsys.entity.DBCommon;
import com.rlglsys.entity.MTb02Adm;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb12Personnel;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IMerchantInfoService;
import com.rlglsys.service.IRlgl010319Service;
import com.rlglsys.service.IRlgl050101Service;
import com.rlglsys.service.IRlgl100101Service;
import com.rlglsys.util.BeanFactory;
import com.rlglsys.util.Constant;
import com.rlglsys.util.DesUtil;

/**
 * 网上缴费初始化action
 * 
 * @author Administrator
 *
 */
public class Rlgl100101InitAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private Rlgl100101Bean rlgl100101Bean;
	private List<MTb02Adm> applyInvoiceList;
	// 商户信息service
	private IMTb02AdmService mtb02AdmService;
	private IRlgl100101Service rlgl100101Service;
	private IMerchantInfoService merchantInfoService;
	private IRlgl050101Service rlgl050101Service;
	// 信息显示的标志位
	private String flg;
	private String status = "";
	private String Payment = "";

	public String getPayment() {
		return Payment;
	}

	public void setPayment(String payment) {
		Payment = payment;
	}

	// 人员基本信息
	private IRlgl010319Service rlgl010319Service;
	private Mtb12Personnel personnel;

	@Override
	protected String doExecute() throws Exception {

		// 登陆用户信息
		Mtb01User loginUser = (Mtb01User) super.getSession(Constant.SESSION_KEY_LOGINUSER);
		String user_id = (String) loginUser.getUser_id();

		// 2015/3/19 网上缴费前，判断联系卡的信息是否完整
		// 人员基本信息
		personnel = rlgl010319Service.getPersonnel(user_id);
		super.GetPrepay(rlgl100101Service, mtb02AdmService, rlgl050101Service, merchantInfoService);
		// 用户ID
		Rlgl100101Bean rlgl100101Bean1 = new Rlgl100101Bean();
		HashMap<String, String> Fields = new HashMap<String, String>();
		Field[] fields = DBCommon.class.getDeclaredFields();
		for (Field f : fields) {
			Fields.put(f.getName(), "");
			Fields.put("pageCount", "");
			Fields.put("pageNo", "");
			Fields.put("bank_order_no", "");
			Fields.put("startDate", "");
			Fields.put("endDate", "");
		}
		// bean对象加密
		rlgl100101Bean1.setClum001(user_id);
		BeanFactory bf = new BeanFactory();
		bf.reinstallFields(rlgl100101Bean1, DesUtil.class, "enc" + "rypt", Fields);
		try {
			rlgl100101Bean = rlgl100101Service.getData(rlgl100101Bean1);
			if (rlgl100101Bean == null) {
				rlgl100101Bean = new Rlgl100101Bean();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		bf.reinstallFields(rlgl100101Bean, DesUtil.class, "dec" + "rypt", Fields);

		// 获得缴费金额
		// String amount = mtb02AdmService.getAdmName("238", "01");

		rlgl100101Bean.setClum001(user_id);
		rlgl100101Bean.setClum002((String) loginUser.getUser_name());
		rlgl100101Bean.setClum003((String) loginUser.getPersonnel_id());
		// rlgl100101Bean.setClum006(amount);
		applyInvoiceList = mtb02AdmService.getAdmInfo("228");
		Payment = "20";
		return "init";
		// }
	}

	/**
	 * 系统message取得
	 * 
	 * @param messageId
	 * @return
	 * @throws Exception
	 */
	public String getMessageById(String messageId) throws Exception {
		InputStream is = null;
		Properties p = new Properties();
		String msg = null;
		is = this.getClass().getResourceAsStream("/systemMessage.properties");
		try {
			p.load(is);
			is.close();
			msg = p.getProperty(messageId);
			if (msg == null) {
				msg = "此信息不存在！";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return msg;
	}

	public IRlgl050101Service getRlgl050101Service() {
		return rlgl050101Service;
	}

	public void setRlgl050101Service(IRlgl050101Service rlgl050101Service) {
		this.rlgl050101Service = rlgl050101Service;
	}

	public IMerchantInfoService getMerchantInfoService() {
		return merchantInfoService;
	}

	public void setMerchantInfoService(IMerchantInfoService merchantInfoService) {
		this.merchantInfoService = merchantInfoService;
	}

	public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the applyInvoiceList
	 */
	public List<MTb02Adm> getApplyInvoiceList() {
		return applyInvoiceList;
	}

	/**
	 * @param applyInvoiceList
	 *            the applyInvoiceList to set
	 */
	public void setApplyInvoiceList(List<MTb02Adm> applyInvoiceList) {
		this.applyInvoiceList = applyInvoiceList;
	}

	/**
	 * @return the mtb02AdmService
	 */
	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}

	/**
	 * @param mtb02AdmService
	 *            the mtb02AdmService to set
	 */
	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
	}

	public Rlgl100101Bean getRlgl100101Bean() {
		return rlgl100101Bean;
	}

	public void setRlgl100101Bean(Rlgl100101Bean rlgl100101Bean) {
		this.rlgl100101Bean = rlgl100101Bean;
	}

	public IRlgl100101Service getRlgl100101Service() {
		return rlgl100101Service;
	}

	public void setRlgl100101Service(IRlgl100101Service rlgl100101Service) {
		this.rlgl100101Service = rlgl100101Service;
	}

	// 人员基本信息
	public IRlgl010319Service getRlgl010319Service() {
		return rlgl010319Service;
	}

	public void setRlgl010319Service(IRlgl010319Service rlgl010319Service) {
		this.rlgl010319Service = rlgl010319Service;
	}

	public Mtb12Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Mtb12Personnel personnel) {
		this.personnel = personnel;
	}

}
