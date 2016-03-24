package com.rlglsys.action.learnonline;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl100101_1Bean;
import com.rlglsys.entity.DBCommon;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IMerchantInfoService;
import com.rlglsys.service.IRlgl050101Service;
import com.rlglsys.service.IRlgl100100Service;
import com.rlglsys.service.IRlgl100101Service;
import com.rlglsys.util.BeanFactory;
import com.rlglsys.util.Constant;
import com.rlglsys.util.DesUtil;

public class Rlgl100100InitAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IRlgl100100Service rlgl100100Service;
	// 缴费明细列表
	private List<Rlgl100101_1Bean> tb02PrepayMsgList;
	private List<Rlgl100101_1Bean> tb02PrepayMsgTempList;
	private IMTb02AdmService mtb02AdmService;
	private IRlgl100101Service rlgl100101Service;
	private IMerchantInfoService merchantInfoService;
	private IRlgl050101Service rlgl050101Service;

	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}

	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
	}

	public IRlgl100101Service getRlgl100101Service() {
		return rlgl100101Service;
	}

	public void setRlgl100101Service(IRlgl100101Service rlgl100101Service) {
		this.rlgl100101Service = rlgl100101Service;
	}

	public IMerchantInfoService getMerchantInfoService() {
		return merchantInfoService;
	}

	public void setMerchantInfoService(IMerchantInfoService merchantInfoService) {
		this.merchantInfoService = merchantInfoService;
	}

	public IRlgl050101Service getRlgl050101Service() {
		return rlgl050101Service;
	}

	public void setRlgl050101Service(IRlgl050101Service rlgl050101Service) {
		this.rlgl050101Service = rlgl050101Service;
	}

	public List<Rlgl100101_1Bean> getTb02PrepayMsgTempList() {
		return tb02PrepayMsgTempList;
	}

	public void setTb02PrepayMsgTempList(List<Rlgl100101_1Bean> tb02PrepayMsgTempList) {
		this.tb02PrepayMsgTempList = tb02PrepayMsgTempList;
	}

	// 分页用
	private int recordCount;
	private String txtInputPage = "";
	private String hdnCountOfPage = "";
	private int count = 0;
	// 是否允许退款标志
	private String refundable = "";


	@Override
	protected String doExecute() throws Exception {
		super.GetPrepay(rlgl100101Service, mtb02AdmService, rlgl050101Service, merchantInfoService);
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("ax");
		// 获取共通
		Mtb01User loginUser = (Mtb01User) super.getSession(Constant.SESSION_KEY_LOGINUSER);
		// 获取单位编号
		String user_id = loginUser.getUser_id();
		Rlgl100101_1Bean rlgl100101_1Bean = new Rlgl100101_1Bean();
		int pageCount = getPageCount();
		// 当前页
		// txtInputPage = "";
		int pageNo = "".equals(txtInputPage) ? 0 * pageCount : (Integer.valueOf(txtInputPage) - 1) * pageCount;
		if ("001".equals(name)) {
			// 个人缴费记录数
			recordCount = rlgl100100Service.getPrepayMsgTempCount(user_id, rlgl100101_1Bean.getClum004());
			// 个人缴费明细列表
			tb02PrepayMsgList = rlgl100100Service.getPrepayMsgTempList(user_id, pageCount, pageNo,
					rlgl100101_1Bean.getClum004());
			// 返回初始界面
			return "press";

		} else {

			rlgl100101_1Bean.setClum004("1");
			// 排除共同列加密
			Field[] fields = DBCommon.class.getDeclaredFields();
			Map<String, String> B01fields = new HashMap<String, String>();
			for (Field f : fields) {
				// 更新用户及时间也要加密
				if (!(f.getName().contains("login") || f.getName().contains("update"))) {
					B01fields.put(f.getName(), "");
				}
			}
			// 付费数据加密
			BeanFactory bf = new BeanFactory();
			bf.reinstallFields(rlgl100101_1Bean, DesUtil.class, "enc" + "rypt", B01fields);
			// 个人缴费记录数
			recordCount = rlgl100100Service.getPrepayMsgCount(user_id, rlgl100101_1Bean.getClum004());
			// 个人缴费明细列表
			tb02PrepayMsgList = rlgl100100Service.getPrepayMsgList(user_id, pageCount, pageNo,
					rlgl100101_1Bean.getClum004());
			if ("OK".equals(refundable)) {
				super.saveErrorMessage("MSG0091I");
			}
			// ========解密查询结果==============
			BeanFactory beanFactory = new BeanFactory();
			Map Fields = new HashMap();
			if (tb02PrepayMsgList != null && tb02PrepayMsgList.size() > 0) {
				for (int i = 0; i < tb02PrepayMsgList.size(); i++) {
					beanFactory.reinstallFields(tb02PrepayMsgList.get(i), DesUtil.class, "dec" + "rypt", Fields);
				}
			}

			// 个人缴费明细列表
			// tb02PrepayMsgTempList =
			// rlgl100100Service.getPrepayMsgTempList(user_id, pageCount,
			// pageNo,rlgl100101_1Bean.getClum004());
			// tb02PrepayMsgList.addAll(tb02PrepayMsgTempList);
			// tb02PrepayMsgList
			// Collections.sort(tb02PrepayMsgList);
			// 返回初始界面
			return "init";
		}

	}

	

	public List<Rlgl100101_1Bean> getTb02PrepayMsgList() {
		return tb02PrepayMsgList;
	}

	public void setTb02PrepayMsgList(List<Rlgl100101_1Bean> tb02PrepayMsgList) {
		this.tb02PrepayMsgList = tb02PrepayMsgList;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public String getTxtInputPage() {
		return txtInputPage;
	}

	public void setTxtInputPage(String txtInputPage) {
		this.txtInputPage = txtInputPage;
	}

	public String getHdnCountOfPage() {
		return hdnCountOfPage;
	}

	public void setHdnCountOfPage(String hdnCountOfPage) {
		this.hdnCountOfPage = hdnCountOfPage;
	}

	public IRlgl100100Service getRlgl100100Service() {
		return rlgl100100Service;
	}

	public void setRlgl100100Service(IRlgl100100Service rlgl100100Service) {
		this.rlgl100100Service = rlgl100100Service;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getRefundable() {
		return refundable;
	}

	public void setRefundable(String refundable) {
		this.refundable = refundable;
	}
}
