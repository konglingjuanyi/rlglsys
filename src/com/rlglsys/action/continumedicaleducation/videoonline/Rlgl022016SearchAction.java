package com.rlglsys.action.continumedicaleducation.videoonline;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl022012Bean;
import com.rlglsys.bean.Rlgl022013Bean;
import com.rlglsys.entity.DBCommon;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IRlgl022012Service;
import com.rlglsys.service.IUserService;
import com.rlglsys.util.BeanFactory;
import com.rlglsys.util.Constant;
import com.rlglsys.util.DateTimeManager;
import com.rlglsys.util.DesUtil;

public class Rlgl022016SearchAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private IRlgl022012Service rlgl022012Service;
	private List<Rlgl022013Bean> prepayRecordList;
	private String type="";
	//是否显示返回标识
	private String backShowFlg="";
	//获得管理表信息
	private IMTb02AdmService  mtb02AdmService;
	private IUserService userService;
	// 分页用
    private int recordCount;
    private String txtInputPage = "";
    private String hdnCountOfPage = "";
    private Rlgl022013Bean rlgl022013Bean;
    //单位
  	private String unit="";
  	private String personnelId="";
  	private String personnelName="";
  	private String personnelCard="";
  	private String personnelUnit="";
  	private String personnelAmount="";
  	private String personnelTime="";
	@Override
	protected String doExecute() throws Exception {
		// 登陆用户信息
		Mtb01User loginUser = (Mtb01User)super.getSession(Constant.SESSION_KEY_LOGINUSER);
		String user_id=(String)loginUser.getUser_id();
		 // 每页条数
	    int pageCount = getPageCount();
	    int pageNo = "".equals(txtInputPage)? 0*pageCount:(Integer.valueOf(txtInputPage) - 1)*pageCount;
	    rlgl022013Bean.setClum001(user_id);
	    // 本单位
	    rlgl022013Bean.setClum003(loginUser.getUnit_no());
	    rlgl022013Bean.setClum008(rlgl022013Bean.getMoney());
	    unit=loginUser.getUnit_no();
	    // 省厅登陆时赋予权限
	    if("37".equals(user_id) || "37A".equals(user_id) || "37B".equals(user_id) || "37C".equals(user_id))
	    {
	    	rlgl022013Bean.setAdminFlag("1");
	    	rlgl022013Bean.setClum003("");
	    }
	    //排除共同列加密
	    Map<String, String> fields12 = new HashMap<String, String>();
  		
  		fields12.put("clum007", "");
  		fields12.put("paymentCheck", "");
	    Map<String, String> fields = new HashMap<String, String>();
	    fields.put("clum003", "");
	    fields.put("clum009", "");
	    fields.put("clum013", "");
	    fields.put("clum014", "");
	    fields.put("startDate", "");
	    fields.put("endDate", "");
	    fields.put("pageCount", "");
	    fields.put("pageNo", "");
	    fields.put("bank_order_no", "");
	    fields.put("money", "");
	    fields.put("adminFlag", "");
	    Field[] fieldsDb = DBCommon.class.getDeclaredFields();
  		for(Field f : fieldsDb) {
  			
  		// 更新用户及时间也要加密
		if(!(f.getName().contains("login") || f.getName().contains("update")))
			{
  				fields12.put(f.getName(), "");
  				fields.put(f.getName(), "");
			}
  		}
		BeanFactory bf = new BeanFactory();
	    bf.reinstallFields(rlgl022013Bean, DesUtil.class, "enc" + "rypt",fields);
	    rlgl022013Bean.setPageNo(pageNo);
	    rlgl022013Bean.setPageCount(pageCount);
	    Rlgl022012Bean rlgl022012Bean=new Rlgl022012Bean();
	    if(!StringUtils.isBlank(rlgl022013Bean.getBank_order_no()))
	    {
	    	// 核实充值记录
	    	rlgl022013Bean.setClum014("001");
	    	rlgl022012Service.updateConfimMoney(rlgl022013Bean);
	    	rlgl022013Bean.setBank_order_no("");
	    	// 更改充值总额
	    	rlgl022012Bean.setClum001(rlgl022013Bean.getClum001());
	    	Rlgl022012Bean	rlgl022012Bean1=rlgl022012Service.getOverMoney(rlgl022012Bean);
	    	if(rlgl022012Bean1==null)
	    	{
	    		rlgl022012Bean.setPayDate(personnelTime.replace("-", "").replace(" ", "").replace(":", ""));
	    		rlgl022012Bean.setClum001(personnelId);
	    		rlgl022012Bean.setClum002("");
	    		rlgl022012Bean.setClum003(personnelCard);
	    		rlgl022012Bean.setClum004(personnelUnit);
	    		rlgl022012Bean.setClum005("");
	    		rlgl022012Bean.setClum006(personnelAmount);
	    		super.setDBCommonColOnInsert(rlgl022012Bean);
	    		//预付费充值数据加密
	    		bf.reinstallFields(rlgl022012Bean, DesUtil.class, "enc" + "rypt",fields12);
	    		rlgl022012Service.insertOverMoney(rlgl022012Bean);
	    	}
	    	else
	    	{
		    	BigDecimal overMoneyNew = new BigDecimal(rlgl022013Bean.getMoney()); 
				bf.reinstallFields(rlgl022012Bean1, DesUtil.class, "dec" + "rypt",fields);
		    	BigDecimal overMoneyOld = new BigDecimal(rlgl022012Bean1.getClum006()); 
			    BigDecimal overMoneySum=overMoneyOld.add(overMoneyNew); 
			    rlgl022012Bean1.setClum006(overMoneySum.toString());
			    super.setDBCommonColOnUpdate(rlgl022012Bean1);
			    bf.reinstallFields(rlgl022012Bean1, DesUtil.class, "enc" + "rypt",fields);
		    	rlgl022012Service.updateConfimOverMoney(rlgl022012Bean1);
	    	}
	    }
	    if(StringUtils.isNotBlank(rlgl022013Bean.getStartDate()) && rlgl022013Bean.getStartDate()!="000000")
	    {
	    	rlgl022013Bean.setStartDate(DateTimeManager.dateChange(rlgl022013Bean.getStartDate())+"000000");
	    }
	    if(StringUtils.isNotBlank(rlgl022013Bean.getEndDate()) && rlgl022013Bean.getEndDate()!="000000")
	    {
	    	rlgl022013Bean.setEndDate(DateTimeManager.dateChange(rlgl022013Bean.getEndDate())+"2435959");
	    }
	    
	    // 只查询线下
	    rlgl022013Bean.setPaymentWay("002");
	    
	    recordCount=rlgl022012Service.getCountPrepayRecord(rlgl022013Bean);
	    prepayRecordList=rlgl022012Service.getprepayRecord(rlgl022013Bean);
	    Mtb01User user=new Mtb01User();
	    if(prepayRecordList !=null && prepayRecordList.size()>0){
	    	for(int i=0;i<prepayRecordList.size();i++)
		    {
		    	 bf.reinstallFields(prepayRecordList.get(i), DesUtil.class, "dec"+"rypt", fields);
		    	 prepayRecordList.get(i).setClum009(DateTimeManager.dateChange(prepayRecordList.get(i).getClum009()));
		    	 prepayRecordList.get(i).setClum010(mtb02AdmService.getAdmName("225", prepayRecordList.get(i).getClum010()));
		    	 if("001".equals(prepayRecordList.get(i).getClum013()))
		    	 {
		    		 prepayRecordList.get(i).setPaymentWay("网银");
		    	 }else
		    	 {
		    		 prepayRecordList.get(i).setPaymentWay("线下");
		    	 }
		    	 if("001".equals(prepayRecordList.get(i).getClum014()))
		    	 {
		    		 prepayRecordList.get(i).setPaymentCheck("已核实");
		    	 }else
		    	 {
		    		 prepayRecordList.get(i).setPaymentCheck("未核实");
		    	 }
		    	// 得到用户姓名
		    	 user.setUser_id(prepayRecordList.get(i).getClum001());
		    	 user=userService.login(user);
		    	 prepayRecordList.get(i).setClum007(user.getUser_name());
		    }
	    }
	    
	    if(StringUtils.isNotBlank(rlgl022013Bean.getStartDate()) && rlgl022013Bean.getStartDate()!="000000")
	    {
	    	rlgl022013Bean.setStartDate(DateTimeManager.dateChange(rlgl022013Bean.getStartDate()).substring(0,10));
	    }
	    if(StringUtils.isNotBlank(rlgl022013Bean.getEndDate()) && rlgl022013Bean.getEndDate()!="000000")
	    {
	    	rlgl022013Bean.setEndDate(DateTimeManager.dateChange(rlgl022013Bean.getEndDate()).substring(0,10));
	    }
	    
	    
		return "init";
	}
	/**
	 * @return the mtb02AdmService
	 */
	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}
	/**
	 * @param mtb02AdmService the mtb02AdmService to set
	 */
	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
	}
	/**
	 * @return the rlgl022013Bean
	 */
	public Rlgl022013Bean getRlgl022013Bean() {
		return rlgl022013Bean;
	}
	/**
	 * @param rlgl022013Bean the rlgl022013Bean to set
	 */
	public void setRlgl022013Bean(Rlgl022013Bean rlgl022013Bean) {
		this.rlgl022013Bean = rlgl022013Bean;
	}
	/**
	 * @return the rlgl022012Service
	 */
	public IRlgl022012Service getRlgl022012Service() {
		return rlgl022012Service;
	}
	/**
	 * @param rlgl022012Service the rlgl022012Service to set
	 */
	public void setRlgl022012Service(IRlgl022012Service rlgl022012Service) {
		this.rlgl022012Service = rlgl022012Service;
	}
	/**
	 * @return the prepayRecordList
	 */
	public List<Rlgl022013Bean> getPrepayRecordList() {
		return prepayRecordList;
	}
	/**
	 * @param prepayRecordList the prepayRecordList to set
	 */
	public void setPrepayRecordList(List<Rlgl022013Bean> prepayRecordList) {
		this.prepayRecordList = prepayRecordList;
	}
	/**
	 * @return the recordCount
	 */
	public int getRecordCount() {
		return recordCount;
	}
	/**
	 * @param recordCount the recordCount to set
	 */
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	/**
	 * @return the txtInputPage
	 */
	public String getTxtInputPage() {
		return txtInputPage;
	}
	/**
	 * @param txtInputPage the txtInputPage to set
	 */
	public void setTxtInputPage(String txtInputPage) {
		this.txtInputPage = txtInputPage;
	}
	/**
	 * @return the hdnCountOfPage
	 */
	public String getHdnCountOfPage() {
		return hdnCountOfPage;
	}
	/**
	 * @param hdnCountOfPage the hdnCountOfPage to set
	 */
	public void setHdnCountOfPage(String hdnCountOfPage) {
		this.hdnCountOfPage = hdnCountOfPage;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public String getBackShowFlg() {
		return backShowFlg;
	}
	public void setBackShowFlg(String backShowFlg) {
		this.backShowFlg = backShowFlg;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getPersonnelId() {
		return personnelId;
	}
	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}
	public String getPersonnelName() {
		return personnelName;
	}
	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
	}
	public String getPersonnelCard() {
		return personnelCard;
	}
	public void setPersonnelCard(String personnelCard) {
		this.personnelCard = personnelCard;
	}
	public String getPersonnelUnit() {
		return personnelUnit;
	}
	public void setPersonnelUnit(String personnelUnit) {
		this.personnelUnit = personnelUnit;
	}
	public String getPersonnelAmount() {
		return personnelAmount;
	}
	public void setPersonnelAmount(String personnelAmount) {
		this.personnelAmount = personnelAmount;
	}
	public String getPersonnelTime() {
		return personnelTime;
	}
	public void setPersonnelTime(String personnelTime) {
		this.personnelTime = personnelTime;
	}
	
}
