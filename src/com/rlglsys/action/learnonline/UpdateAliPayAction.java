package com.rlglsys.action.learnonline;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl100101_1Bean;
import com.rlglsys.bean.Rlgl500102Bean;
import com.rlglsys.entity.DBCommon;
import com.rlglsys.service.IRlgl100101Service;
import com.rlglsys.util.BeanFactory;
import com.rlglsys.util.DateTimeManager;
import com.rlglsys.util.DesUtil;

public class UpdateAliPayAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	int fla = 0;
	
	
	 
	private String trade_no="";//支付宝交易号 
	private String out_trade_no="";//获取订单号  //clum003
	private String total_fee="";//获取总金额
	private String subject="";//商品名称、订单名称
	private String body=""; ////商品描述、订单备注、描述
	private String buyer_email="";//买家支付宝账号
	private String trade_status=""; //交易状态
	private String notify_time="";// 交易日期
	

	
	
	public int getFla() {
		return fla;
	}

	public void setFla(int fla) {
		this.fla = fla;
	}

	 

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBuyer_email() {
		return buyer_email;
	}

	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	



	public String getNotify_time() {
		return notify_time;
	}

	public void setNotify_time(String notify_time) {
		this.notify_time = notify_time;
	}

	public Rlgl100101_1Bean getRlgl100101_1Bean() {
		return rlgl100101_1Bean;
	}

	public void setRlgl100101_1Bean(Rlgl100101_1Bean rlgl100101_1Bean) {
		this.rlgl100101_1Bean = rlgl100101_1Bean;
	}





	private IRlgl100101Service rlgl100101Service;
	public IRlgl100101Service getRlgl100101Service() {
		return rlgl100101Service;
	}

	public void setRlgl100101Service(IRlgl100101Service rlgl100101Service) {
		this.rlgl100101Service = rlgl100101Service;
	}
	private Rlgl100101_1Bean rlgl100101_1Bean;
	
	 
	

	@Override
	protected String doExecute() throws Exception {
		if("".equals(body) || body==null)
		{
			return "false";
		}
		
		// 修改余额
		double yue = 0;
		if(!"".equals(total_fee)&&total_fee!=null)
		{
			yue =Double.parseDouble(total_fee);
		}
		 
		rlgl100101_1Bean =new Rlgl100101_1Bean();
		rlgl100101_1Bean.setUserId(body);
		rlgl100101_1Bean.setMerchantID("323230");
		rlgl100101_1Bean.setAmount(total_fee);
		if(trade_status.equals("TRADE_SUCCESS")){
			
			rlgl100101_1Bean.setSucceed("1");
		}else{
			
			rlgl100101_1Bean.setSucceed("0");	
		} 
		rlgl100101_1Bean.setTransactionID(out_trade_no);
		rlgl100101_1Bean.setPay_year(notify_time);
		rlgl100101_1Bean.setPay_date(DateTimeManager.getCurrentDateStrFormat());
		rlgl100101_1Bean.setDel_kbn("0");
		rlgl100101_1Bean.setEx_key(1); 
		rlgl100101_1Bean.setExpend_01("商品名称:继续医学教育费用"+",买家支付宝账号"+buyer_email+",支付宝交易号:"+trade_no);
		
	/*	rlgl100101_1Bean.setUserId(body); //用户ID
		rlgl100101_1Bean.setMerchantID("323230");
		rlgl100101_1Bean.setClum003(out_trade_no); //TransactionID 
		rlgl100101_1Bean.setPay_date(notify_time);
		rlgl100101_1Bean.setClum006(notify_time); //pay_year
		rlgl100101_1Bean.setClum007(trade_status); //refund_flag
		rlgl100101_1Bean.setClum008(total_fee); //Amount
		rlgl100101_1Bean.setClum009(notify_time);
		rlgl100101_1Bean.setEx_key(1);
		rlgl100101_1Bean.setDel_kbn("0");
		rlgl100101_1Bean.setExpend_01("");
		rlgl100101_1Bean.setExpend_02("");
		rlgl100101_1Bean.setExpend_03("");
		rlgl100101_1Bean.setExpend_04("");
		rlgl100101_1Bean.setExpend_05("");  */
		rlgl100101_1Bean.setLogin_user_id(body);
		rlgl100101_1Bean.setUpdate_user_id(body);
		rlgl100101_1Bean.setLogin_date(DateTimeManager.getCurrentDateStrFormat());
		rlgl100101_1Bean.setUpdate_date(DateTimeManager.getCurrentDateStrFormat());
		
		int year = Integer.parseInt(DateTimeManager.getSystemDate14().substring(0,4));
		String year1 = String.valueOf(year);
		rlgl100101_1Bean.setPay_year(year1);
		// 排除共同列加密
	/*	Field[] fields = DBCommon.class.getDeclaredFields();
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
					*/
		
		rlgl100101Service.insertData(rlgl100101_1Bean);
	
		//插入、更新 余额-----
		Rlgl500102Bean rlgl500102Bean = new Rlgl500102Bean();
		rlgl500102Bean.setUser_id(body);
	
		rlgl500102Bean.setBalance(yue);
		
		rlgl500102Bean.setUpdate_date(DateTimeManager.getCurrentDateStrFormat());
		
		rlgl500102Bean.setUpdate_user_id(body);
		
		fla = rlgl100101Service.updateBalance(rlgl500102Bean);
		fla = rlgl100101Service.insertBalanceData(rlgl500102Bean);
	
		
		return "success";
	}
}
