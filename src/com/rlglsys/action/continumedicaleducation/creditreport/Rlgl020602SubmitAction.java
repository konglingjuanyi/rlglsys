package com.rlglsys.action.continumedicaleducation.creditreport;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl020602Bean;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb04Unit;
import com.rlglsys.entity.Mtb29PersonalApply;
import com.rlglsys.entity.Mtb31ApprovalProcess;
import com.rlglsys.entity.Mtb63CourseWare;
import com.rlglsys.entity.Mtb69creditcount;
import com.rlglsys.entity.Mtb70publication;
import com.rlglsys.entity.TTb01AutoGetNum;
import com.rlglsys.service.IApprovalProcessService;
import com.rlglsys.service.IAutoGetNumService;
import com.rlglsys.service.IRlgl010106Service;
import com.rlglsys.service.IRlgl010309Service;
import com.rlglsys.service.IRlgl020602Service;
import com.rlglsys.service.IRlgl020603Service;
import com.rlglsys.service.IRlgl020807Service;
import com.rlglsys.util.Common;
import com.rlglsys.util.Constant;
import com.rlglsys.util.DateTimeManager;

public class Rlgl020602SubmitAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2909227668465231302L;

	private Rlgl020602Bean rlgl020602;
	private IRlgl020602Service rlgl020602Service;
	private IAutoGetNumService noSerVice;
	private IRlgl010106Service rlgl010106Service;
	private IRlgl020807Service rlgl020807Service;
	private Mtb31ApprovalProcess processGetInfo;
	private IApprovalProcessService approvalProcessService;
	private IRlgl010309Service rlgl010309Service;
	private String navi_Id;
	private String areaLevel;
	private IRlgl020603Service rlgl020603Service;
	private String page_flg = "";

	@Override
	protected String doExecute() throws Exception {

		String apply_no = "";
		String endMark = "";
		String nowdate = "";
		String now = "";
		String date1 = "";
		String date2 = "";
		String applyKbn = "";
		double period = 0;
		Mtb69creditcount mtb69 = null;
		DecimalFormat df=new DecimalFormat("0.#");
		
		// 设置申请区分
		// 山东卫生
		if ("navi052".equals(navi_Id)) {
			applyKbn = "018";
		}
		// 市级继续教育培训
		if ("navi053".equals(navi_Id)) {
			applyKbn = "019";
		}
		// 省/外地继教项目|学术会议
		if ("navi054".equals(navi_Id)) {
			applyKbn = "020";
		}
		// 公共课程考试
		if ("navi055".equals(navi_Id)) {
			applyKbn = "021";
		}
		// 著作信息
		if ("navi056".equals(navi_Id)) {
			applyKbn = "022";
		}
		// 译作信息
		if ("navi066".equals(navi_Id)) {
			applyKbn = "023";
		}
		// 考察报告/专题报告
		if ("navi067".equals(navi_Id)) {
			applyKbn = "024";
		}
		// 科研成果信息 未确定
		if ("navi068".equals(navi_Id)) {
			applyKbn = "025";
		}
		// 论文综述信息 根据刊物级别授予学分
		if ("navi069".equals(navi_Id)) {
			applyKbn = "026";
		}
		// 科研立项 学分计算
		if ("navi070".equals(navi_Id)) {
			applyKbn = "027";
		}
		// 院内学分
		if ("navi071".equals(navi_Id)) {
			applyKbn = "028";
		}
		// 学历（学位）教育
		if ("navi072".equals(navi_Id)) {
			applyKbn = "029";
		}
		// 外出进修
		if ("navi073".equals(navi_Id)) {
			applyKbn = "030";
		}
		// 自学考试
		if ("navi074".equals(navi_Id)) {
			applyKbn = "031";
		}
		// 住院医师规范化培训
		if ("navi075".equals(navi_Id)) {
			applyKbn = "032";
		}
		// 全科医师在岗培训
		if ("navi076".equals(navi_Id)) {
			applyKbn = "033";
		}
		// 参加卫生支农
		if ("navi077".equals(navi_Id)) {
			applyKbn = "034";
		}
		// “360”、“1127”工程
		if ("navi078".equals(navi_Id)) {
			applyKbn = "035";
		}
		
		// 用户信息
		Mtb01User userInfo = (Mtb01User) super.getSession().getAttribute(
				Constant.SESSION_KEY_LOGINUSER);
		// 用户单位
		String userUnitNo = userInfo.getUnit_no();

		Mtb04Unit unitInfo = rlgl010106Service.getUnitInfo(userUnitNo);

		// 插入申请主表
		if (unitInfo != null) {
			// 审核流程编号取得
			Mtb31ApprovalProcess processInfo = new Mtb31ApprovalProcess();
			// 申请事项
			processInfo.setApply_kbn(applyKbn);
			// 单位No
			String unitNo = unitInfo.getUnit_no();
			if ("37".equals(unitNo)) {
                processInfo.setUnit_no(unitNo);
            } else if(unitNo.length() == 4) {
                processInfo.setUnit_no("37");
            } else {
            	if ("3700".equals(unitNo.substring(0,4))) {
            		processInfo.setUnit_no("37");
            	}
            	else
            	{
            		unitNo = unitNo.substring(0, 4);
    	            processInfo.setUnit_no(unitNo);
            	}
            }
			processGetInfo = approvalProcessService.getProcessInfo(processInfo);
			System.out.println("=========processGetInfo========="+processGetInfo);
			if (processGetInfo != null) {
				// 终审节点
				endMark = processGetInfo.getEnd_mark();
			}
			System.out.println("=========endMark========="+endMark);
			Mtb29PersonalApply tb29Info = new Mtb29PersonalApply();
			TTb01AutoGetNum noInfo = new TTb01AutoGetNum();
			noInfo.setTable_id("m_tb29");
			noInfo.setCol_id("apply_no");
			// 申请编号的自动取得
			apply_no = noSerVice.searchNoInfo(noInfo);

			// 登录个人人事申请表
			// 申请编号
			tb29Info.setApply_no(apply_no);
			// 最大连番
			tb29Info.setMax_number("001");
			// 申请事项
			tb29Info.setApply_kbn(applyKbn);
			// 申请人ID
			tb29Info.setPersonal_id(userInfo.getPersonnel_id());
			// 申请人姓名
			tb29Info.setPersonal_nm(userInfo.getUser_name());
			// 申请人单位NO
			tb29Info.setPersonal_unitno(userInfo.getUnit_no());
			// 提交人
			tb29Info.setCheck_user(userInfo.getUser_id());
			// 提交人单位NO
			tb29Info.setCheck_unitno(userInfo.getUnit_no());
			// 申请时间
			nowdate = DateTimeManager.getSystemDate14();
			now = nowdate.substring(0, 8);
			tb29Info.setApply_date(now);
			// 审核流程编号
			tb29Info.setEnd_mark(endMark);
			
			// 当前节点
			tb29Info.setNow_mark("00");
			// 待审核单位NO
			tb29Info.setWill_checkunitno(userInfo.getUnit_no());
			rlgl010309Service.insertTb29Info(tb29Info);
			// 更新申请编号连番
			noSerVice.updateNoInfo(noInfo);
		}

		int months = 0;// 相差月份
		
		// 插入申请明细表
		// 设置插入明细表变量值
		// NAVI值
		rlgl020602.setNavi(navi_Id);
		// 申请编号
		rlgl020602.setApply_no(apply_no);
		// 申请人ID
		rlgl020602.setPersonal_id(userInfo.getPersonnel_id());
		// 单位编号
		rlgl020602.setUnit_no(userInfo.getUnit_no());
		// 学分和学分类别
		if ("".equals(rlgl020602.getCredit())) {
			// 山东卫生
			if ("navi052".equals(navi_Id)) {
				rlgl020602.setCredit("6");
				rlgl020602.setCredit_category("002");
			}
			// 市级继续教育培训
			if ("navi053".equals(navi_Id)) {
				rlgl020602.setCredit_category("002");
				period = Double.parseDouble(rlgl020602.getExpand6());
				rlgl020602.setCredit(String.valueOf(df.format(period / 6)));
			}
			// 省/外地继教项目|学术会议
			if ("navi054".equals(navi_Id)) {
				period = Double.parseDouble(rlgl020602.getExpand8());
				rlgl020602.setCredit(String.valueOf(df.format(period / 6)));
			}
			// 公共课程考试
			if ("navi055".equals(navi_Id)) {
				// 公共课程考试的学分
				Mtb63CourseWare Mtb63Course =rlgl020807Service.getPublicCourseInfoById(rlgl020602.getExpand1());
				if(Mtb63Course !=null){
					if(Mtb63Course.getXuefen() != 0.0){
						String creditTemp = String.valueOf(Mtb63Course.getXuefen());
						rlgl020602.setCredit(creditTemp);
					}else{
						rlgl020602.setCredit("");
					}
				}else{
					rlgl020602.setCredit("");
				}
			}
			// 著作信息
			if ("navi056".equals(navi_Id)) {
				rlgl020602.setCredit_category("002");
				period = Double.parseDouble(rlgl020602.getExpand3());
				rlgl020602.setCredit(String.valueOf(df.format(period / 10000)));
			}
			// 译作信息
			if ("navi066".equals(navi_Id)) {
				rlgl020602.setCredit_category("002");
				period = Double.parseDouble(rlgl020602.getExpand6());
				rlgl020602.setCredit(String.valueOf(df.format(period / 15000)));
			}
			// 考察报告/专题报告
			if ("navi067".equals(navi_Id)) {
				rlgl020602.setCredit_category("002");
				period = Double.parseDouble(rlgl020602.getExpand6());
				if (period / 3000 > 10) {
					rlgl020602.setCredit("10");
				} else {
					rlgl020602.setCredit(String.valueOf(df.format(period / 3000)));
				}
			}
			// 科研成果信息 未确定
			if ("navi068".equals(navi_Id)) {
				rlgl020602.setCredit_category("001");
				mtb69 = new Mtb69creditcount();
				mtb69.setCredit_category("001");
				mtb69.setDictionary_cd(rlgl020602.getExpand7());
				mtb69.setSecond_order(rlgl020602.getExpand8());
				mtb69.setThird_order(rlgl020602.getExpand9());
				rlgl020602.setCredit(rlgl020602Service.getCredit(mtb69));
			}
			// 论文综述信息 根据刊物级别授予学分
			if ("navi069".equals(navi_Id)) {
				
				mtb69 = new Mtb69creditcount();
				mtb69.setCredit_category("002");
				
				Mtb70publication mtb70pub = new Mtb70publication();
				mtb70pub = rlgl020602Service.getPublicationDetail(rlgl020602.getExpand2());
				if ("001".equals(mtb70pub.getPublication_level()) ||
						"002".equals(mtb70pub.getPublication_level()) ||
						"003".equals(mtb70pub.getPublication_level())) {
					rlgl020602.setCredit_category("001");
				} else {
					rlgl020602.setCredit_category("002");
				}
				mtb69.setDictionary_cd(rlgl020602.getCredit_category());
				mtb69.setSecond_order(mtb70pub.getPublication_level());
				mtb69.setThird_order(rlgl020602.getExpand4());
				rlgl020602.setCredit(rlgl020602Service.getCredit(mtb69));
				
				rlgl020602.setExpand5("");
				rlgl020602.setExpand6("");
				rlgl020602.setExpand7("");
				rlgl020602.setExpand8("");
				rlgl020602.setExpand9("");
			}
			// 科研立项 学分计算
			if ("navi070".equals(navi_Id)) {
				rlgl020602.setCredit_category("001");
				mtb69 = new Mtb69creditcount();
				mtb69.setCredit_category("003");
				mtb69.setDictionary_cd(rlgl020602.getExpand4());
				mtb69.setSecond_order("001");
				mtb69.setThird_order(rlgl020602.getExpand6());
				rlgl020602.setCredit(rlgl020602Service.getCredit(mtb69));
			}
			// 院内学分
			if ("navi071".equals(navi_Id)) {
				rlgl020602.setCredit_category("002");
			}
			// 自学考试
			if ("navi074".equals(navi_Id)) {
				rlgl020602.setCredit("5");
			}
			// 参加卫生支农
			if ("navi077".equals(navi_Id)) {
				try {
					if (Integer.parseInt(rlgl020602.getExpand4()) >= 6)
					{
						rlgl020602.setCredit("25");
					}
				} catch (Exception e) {
				}
			}
			// 专利信息
			if ("navi078".equals(navi_Id)) {
				rlgl020602.setCredit_category("001");
				rlgl020602.setCredit("12.5");
				
				mtb69 = new Mtb69creditcount();
				mtb69.setCredit_category("004");
				mtb69.setDictionary_cd("131");
				mtb69.setSecond_order(rlgl020602.getExpand1());
				mtb69.setThird_order(rlgl020602.getExpand4());
				rlgl020602.setCredit(rlgl020602Service.getCredit(mtb69));
			}
		}

        // 获得学分年度
        String kbn = rlgl020603Service.getCreditYear(userUnitNo);
        Common common = new Common();
        int year = 0;
        if (kbn != null && !"".equals(kbn))
        {
	        year = common.getCreditYear(kbn);
        }else{
        	// TODO 这里应该提示一个信息，学分年度为设置
        }
		// 计分年度
		if ("".equals(rlgl020602.getScore_year())) {
			rlgl020602.setScore_year(now.substring(0, 4));
			if ("navi052".equals(navi_Id)) {
				// 针对《山东卫生》，订阅年度要和计分年度一样
				rlgl020602.setScore_year(rlgl020602.getExpand1());
			}
			if ("navi054".equals(navi_Id)) {
				// 针对《省/外地继教项目|学术会议》，计分年度等于发证时间
				if(year !=0){
					rlgl020602.setScore_year(String.valueOf(year));
				}else{
					rlgl020602.setScore_year(rlgl020602.getExpand9().substring(0, 4));
				}
				
			}
			if ("navi055".equals(navi_Id)) {
				// 针对《公共课程考试》
				if(year !=0){
					rlgl020602.setScore_year(String.valueOf(year));
				}else{
					rlgl020602.setScore_year(rlgl020602.getExpand4().substring(0, 4));
				}
				
			}
			if ("navi066".equals(navi_Id)) {
				// 针对《译作信息》
				if(year !=0){
					rlgl020602.setScore_year(String.valueOf(year));
				}else{
					rlgl020602.setScore_year(rlgl020602.getExpand5().substring(0, 4));
				}
				
			}
			if ("navi068".equals(navi_Id)) {
				// 针对《科研成果信息》，计分年度等于翻译年度
				if(year !=0){
					rlgl020602.setScore_year(String.valueOf(year));
				}else{
					rlgl020602.setScore_year(rlgl020602.getExpand5().substring(0, 4));
				}
			}
			if ("navi071".equals(navi_Id)) {
				// 针对《院内学分》，计分年度等于结束时间（本来是等于发证时间）
				if(year !=0){
					rlgl020602.setScore_year(String.valueOf(year));
				}else{
					rlgl020602.setScore_year(rlgl020602.getExpand3().substring(0, 4));
				}
				
			}
		}

		// 申请状态
		rlgl020602.setApply_status("0");
		// 审核结果
		rlgl020602.setCheck_result("001");
		// 当前节点
		rlgl020602.setNow_mark("00");
		// 终审节点
		rlgl020602.setEnd_mark(endMark);
		// 待审核单位NO
		rlgl020602.setWill_checkunitno(userInfo.getUnit_no());
		
		rlgl020602.setEx_key("1");
		
		rlgl020602.setDel_kbn("0");
		
		rlgl020602.setLogin_user_id(userInfo.getUser_id());
		
		rlgl020602.setLogin_date(nowdate);
		
		rlgl020602.setUpdate_user_id(userInfo.getUser_id());
		
		rlgl020602.setUpdate_date(nowdate);

		int intResult = 0;
		
		// 学历（学位）教育
		if ("navi072".equals(navi_Id)) {
			date1 = rlgl020602.getExpand2();
			date2 = rlgl020602.getExpand3();
			
			// 获得两个日期相差的月份
			months = (int) getMonthDiff(date1,date2);
			int y1 = Integer.parseInt(date1.substring(0, 4));
			int y2 = Integer.parseInt(date2.substring(0, 4));
				
			if (months >= 6 && months < 12) {
				// 设置计分年度为：开始时间
				rlgl020602.setScore_year(String.valueOf(y1));
				rlgl020602.setCredit_category("002");
				rlgl020602.setCredit("12.5");
				intResult = rlgl020602Service.submitCredit(rlgl020602);
			} else if (months >= 12) {
				for (int i = y1; i < y2; i++)
				{
					// 由于有两类学分，所以登录两条数据
					// 设置计分年度为：开始时间
					rlgl020602.setScore_year(String.valueOf(i+1));
					rlgl020602.setCredit_category("001");
					rlgl020602.setCredit("10");
					intResult = rlgl020602Service.submitCredit(rlgl020602);
					// 设置计分年度为：开始时间
					rlgl020602.setScore_year(String.valueOf(i+1));
					rlgl020602.setCredit_category("002");
					rlgl020602.setCredit("15");
					intResult = rlgl020602Service.submitCredit(rlgl020602);
				}
			}
			if (intResult == 0) {
				page_flg="6";
			}
		// 外出进修
		} else 
		if ("navi073".equals(navi_Id)) {
			date1 = rlgl020602.getExpand1();
			date2 = rlgl020602.getExpand2();
			
			// 获得两个日期相差的月份
			months = (int) getMonthDiff(date1,date2);
			int y1 = Integer.parseInt(date1.substring(0, 4));
			int y2 = Integer.parseInt(date2.substring(0, 4));
			
			if(months > 0)
			{
				if (months < 6) {
					// 设置计分年度为：开始时间
					rlgl020602.setScore_year(String.valueOf(y1));
					rlgl020602.setCredit_category("002");
					// 不满半年的，每个月给3分
					rlgl020602.setCredit(String.valueOf(months * 3));
					intResult = rlgl020602Service.submitCredit(rlgl020602);
				} else if (months >= 6) {
					for (int i = y1; i < y2; i++)
					{
						// 由于有两类学分，所以登录两条数据
						// 设置计分年度为：开始时间
						rlgl020602.setScore_year(String.valueOf(i+1));
						rlgl020602.setCredit_category("001");
						rlgl020602.setCredit("10");
						intResult = rlgl020602Service.submitCredit(rlgl020602);
						// 设置计分年度为：开始时间
						rlgl020602.setScore_year(String.valueOf(i+1));
						rlgl020602.setCredit_category("002");
						rlgl020602.setCredit("15");
						intResult = rlgl020602Service.submitCredit(rlgl020602);
					}
				}
			} else {
				page_flg="1";
			}
		// 住院医师规范化培训
		} else if ("navi075".equals(navi_Id)) {
			if ("001".equals(rlgl020602.getExpand2())) {
				// 由于有两类学分，所以登录两条数据
				rlgl020602.setCredit_category("001");
				rlgl020602.setCredit("10");
				intResult = rlgl020602Service.submitCredit(rlgl020602);
				rlgl020602.setCredit_category("002");
				rlgl020602.setCredit("15");
				intResult = rlgl020602Service.submitCredit(rlgl020602);
			} else {
				// 完成第二阶段的，不给学分，视为合格
				rlgl020602.setCheck_result("002");
				intResult = rlgl020602Service.submitCredit(rlgl020602);
			}
			if (intResult == 0) {
				page_flg="2";
			}
		// 全科医师在岗培训
		} else if ("navi076".equals(navi_Id)) {
			// 由于有两类学分，所以登录两条数据
			rlgl020602.setCredit_category("001");
			rlgl020602.setCredit("10");
			intResult = rlgl020602Service.submitCredit(rlgl020602);
			rlgl020602.setCredit_category("002");
			rlgl020602.setCredit("15");
			intResult = rlgl020602Service.submitCredit(rlgl020602);
			if (intResult == 0) {
				page_flg="2";
			}
		// 其他情况
		} else {
			intResult = rlgl020602Service.submitCredit(rlgl020602);
			if (intResult == 0) {
				page_flg="2";
			}
		}

		return SUCCESS;
	}
	
	/**
     * 得到两日期相差几个月
     *
     * @param String
     * @return
     */
    public static long getMonthDiff(String startDate, String endDate) throws ParseException {
        long monthday;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate1 = fmt.parse(startDate);

        Calendar starCal = Calendar.getInstance();
        starCal.setTime(startDate1);

        int sYear  = starCal.get(Calendar.YEAR);
        int sMonth = starCal.get(Calendar.MONTH);
        int sDay   = starCal.get(Calendar.DAY_OF_MONTH);

        Date endDate1 = fmt.parse(endDate);
        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate1);
        int eYear  = endCal.get(Calendar.YEAR);
        int eMonth = endCal.get(Calendar.MONTH);
        int eDay   = endCal.get(Calendar.DAY_OF_MONTH);

        monthday = ((eYear - sYear) * 12 + (eMonth - sMonth));
        
        //这里计算零头的情况，根据实际确定是否要加1 还是要减1
        if (sDay < eDay) {
            monthday = monthday + 1;
        }
        return monthday;
    }
    
	public Rlgl020602Bean getRlgl020602() {
		return rlgl020602;
	}

	public void setRlgl020602(Rlgl020602Bean rlgl020602) {
		this.rlgl020602 = rlgl020602;
	}

	public IRlgl020602Service getRlgl020602Service() {
		return rlgl020602Service;
	}

	public void setRlgl020602Service(IRlgl020602Service rlgl020602Service) {
		this.rlgl020602Service = rlgl020602Service;
	}

	public IAutoGetNumService getNoSerVice() {
		return noSerVice;
	}

	public void setNoSerVice(IAutoGetNumService noSerVice) {
		this.noSerVice = noSerVice;
	}

	public IRlgl010106Service getRlgl010106Service() {
		return rlgl010106Service;
	}

	public void setRlgl010106Service(IRlgl010106Service rlgl010106Service) {
		this.rlgl010106Service = rlgl010106Service;
	}

	public Mtb31ApprovalProcess getProcessGetInfo() {
		return processGetInfo;
	}

	public void setProcessGetInfo(Mtb31ApprovalProcess processGetInfo) {
		this.processGetInfo = processGetInfo;
	}

	public IApprovalProcessService getApprovalProcessService() {
		return approvalProcessService;
	}

	public void setApprovalProcessService(
			IApprovalProcessService approvalProcessService) {
		this.approvalProcessService = approvalProcessService;
	}

	public IRlgl010309Service getRlgl010309Service() {
		return rlgl010309Service;
	}

	public void setRlgl010309Service(IRlgl010309Service rlgl010309Service) {
		this.rlgl010309Service = rlgl010309Service;
	}

	public String getNavi_Id() {
		return navi_Id;
	}

	public void setNavi_Id(String navi_Id) {
		this.navi_Id = navi_Id;
	}

	public String getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(String areaLevel) {
		this.areaLevel = areaLevel;
	}

	public IRlgl020807Service getRlgl020807Service() {
		return rlgl020807Service;
	}

	public void setRlgl020807Service(IRlgl020807Service rlgl020807Service) {
		this.rlgl020807Service = rlgl020807Service;
	}

	public IRlgl020603Service getRlgl020603Service() {
		return rlgl020603Service;
	}

	public void setRlgl020603Service(IRlgl020603Service rlgl020603Service) {
		this.rlgl020603Service = rlgl020603Service;
	}

	public String getPage_flg() {
		return page_flg;
	}

	public void setPage_flg(String page_flg) {
		this.page_flg = page_flg;
	}
	
}
