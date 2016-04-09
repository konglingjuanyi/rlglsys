package com.rlglsys.action.continumedicaleducation.creditreport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl020601Bean;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb04Unit;
import com.rlglsys.entity.Mtb56creditreportingdetail;
import com.rlglsys.entity.Mtb60formlist;
import com.rlglsys.entity.Mtb63CourseWare;
import com.rlglsys.entity.Mtb70publication;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IRlgl010106Service;
import com.rlglsys.service.IRlgl020601Service;
import com.rlglsys.service.IRlgl020602Service;
import com.rlglsys.service.IRlgl020807Service;
import com.rlglsys.util.CheckFlow;
import com.rlglsys.util.Constant;

public class Rlgl020601InitAction extends BaseAction {

	/**
	 * 学分申报UID
	 */
	private static final long serialVersionUID = 7530920447710969744L;

	private Rlgl020601Bean rlgl020601;
	private IRlgl020601Service rlgl020601Service;
	private List<Rlgl020601Bean> rlgl020601List;
	private IRlgl010106Service rlgl010106Service;
	private IRlgl020807Service rlgl020807Service;
	private Mtb04Unit unitinfo;
	private IMTb02AdmService mtb02AdmService;
	private List<Mtb60formlist> formList;
	private String naviId;
	private IRlgl020602Service rlgl020602Service;
	
	// 分页用
    private int recordCount;
    private String txtInputPage = "";
    private String hdnCountOfPage = "";
    private String payMentFlag = "";
    private String page_flg = "";

	/**
	 * 初始化处理
	 * 
	 * @return SUCCESS
	 * @throws Exception
	 */
	@Override
	protected String doExecute() throws Exception {
		
		if (!"".equals(page_flg))
		{
			if ("1".equals(page_flg))
				super.saveErrorMessage("MSG0087I");
			if ("2".equals(page_flg))
				super.saveErrorMessage("MSG0086I");
			if ("6".equals(page_flg))
				super.saveErrorMessage("MSG0088I");
		}
		
		// 获得session中的用户信息
		Mtb01User userInfo = (Mtb01User) super.getSession().getAttribute(
				Constant.SESSION_KEY_LOGINUSER);
		
		// 获得页面的navi值，以区分进入的是哪个页面
		naviId = super.getRequest().getAttribute("naviId").toString();
		
		// 设置获取列表表头所需变量
		Mtb60formlist mtb60 = new Mtb60formlist();
		mtb60.setNavi(naviId);
		// 1：需要显示的列表头
		mtb60.setItem_category("1");
		// 从数据库获取列表表头
		formList = rlgl020601Service.getFormList(mtb60);

		// 取得用户单位信息
		String userUnitNo = userInfo.getUnit_no();

		// 获取列表数据所需要的变量
		rlgl020601 = new Rlgl020601Bean();
		rlgl020601List = new ArrayList<Rlgl020601Bean>();

		// 设置查询属性（根据个人ID和naviId）
		rlgl020601.setPersonal_id(userInfo.getPersonnel_id());
		rlgl020601.setNavi(naviId);

		// 每页条数
        int pageCount = getPageCount();
        // 第几页
        int pageNo = "".equals(txtInputPage)? 0*pageCount:(Integer.valueOf(txtInputPage) - 1)*pageCount;
		// 获得数据库数据
		List<Mtb56creditreportingdetail> applyList = rlgl020601Service
				.searchUnitinfo(rlgl020601,formList,pageCount,pageNo);
		recordCount = rlgl020601Service.getCount(rlgl020601,formList,pageCount,pageNo);;

		// 定义临时变量，用以向列表中填充数据
		Mtb56creditreportingdetail mtb56 = null;
		// 扩展字段所需变量
		List<String> expandInfoTemp = null;

		// 如果能够取到数据
		if (applyList.size() > 0) {

			// 公司编号
			String unitNo = "";
			// 公司名称
			String unitName = "";
			// 审核结果
			String check_result = "";
			// 结果名称
			String result_Name = "";

			for (int i = 0; i < applyList.size(); i++) {
				mtb56 = (Mtb56creditreportingdetail) applyList.get(i);

				// 创建临时存储
				rlgl020601 = new Rlgl020601Bean();

				// 设置临时存储的值
				// NAVI值
				rlgl020601.setNavi(mtb56.getNavi());

				// 申请编号
				rlgl020601.setApply_no(mtb56.getApply_no());

				// 申请人ID
				rlgl020601.setPersonal_id(mtb56.getPersonal_id());

				// 单位编号
				rlgl020601.setUnit_no(mtb56.getUnit_no());

				// 学分
				rlgl020601.setCredit(mtb56.getCredit());

				// 计分年度
				rlgl020601.setScore_year(mtb56.getScore_year());

				// 学分类别
				rlgl020601.setCredit_category(mtb56.getCredit_category());

				// 扩展字段1
				rlgl020601.setExpand1(mtb56.getExpand1());

				// 扩展字段2
				rlgl020601.setExpand2(mtb56.getExpand2());

				// 扩展字段3
				rlgl020601.setExpand3(mtb56.getExpand3());

				// 扩展字段4
				rlgl020601.setExpand4(mtb56.getExpand4());

				// 扩展字段5
				rlgl020601.setExpand5(mtb56.getExpand5());

				// 扩展字段6
				rlgl020601.setExpand6(mtb56.getExpand6());

				// 扩展字段7
				rlgl020601.setExpand7(mtb56.getExpand7());

				// 扩展字段8
				rlgl020601.setExpand8(mtb56.getExpand8());

				// 扩展字段9
				rlgl020601.setExpand9(mtb56.getExpand9());

				// 扩展字段10
				rlgl020601.setExpand10(mtb56.getExpand10());

				// 扩展字段11
				rlgl020601.setExpand11(mtb56.getExpand11());

				// 扩展字段12
				rlgl020601.setExpand12(mtb56.getExpand12());

				// 扩展字段13
				rlgl020601.setExpand13(mtb56.getExpand13());

				// 扩展字段14
				rlgl020601.setExpand14(mtb56.getExpand14());

				// 扩展字段15
				rlgl020601.setExpand15(mtb56.getExpand15());

				// 扩展字段16
				rlgl020601.setExpand16(mtb56.getExpand16());

				// 扩展字段17
				rlgl020601.setExpand17(mtb56.getExpand17());

				// 扩展字段18
				rlgl020601.setExpand18(mtb56.getExpand18());

				// 扩展字段19
				rlgl020601.setExpand19(mtb56.getExpand19());

				// 扩展字段20
				rlgl020601.setExpand20(mtb56.getExpand20());
				
				expandInfoTemp = new ArrayList<String>();
				
				// 设置申请区分
				// 山东卫生
				if ("navi052".equals(naviId)) {
					// 时间段
					String masterName = mtb02AdmService.getAdmName("108", mtb56.getExpand2());
					mtb56.setExpand2(masterName);
				}
				// 省/外地继教项目|学术会议
				if ("navi054".equals(naviId)) {
					// 时间段
					String masterName = mtb02AdmService.getAdmName("128", mtb56.getExpand5());
					mtb56.setExpand5(masterName);
				}
				// 公共课程考试
				if ("navi055".equals(naviId)) {
					// 课程名称
					String course_name = "";
					Mtb63CourseWare Mtb63Course =rlgl020807Service.getPublicCourseInfoById(mtb56.getExpand1());
					if (Mtb63Course != null) {
						course_name = Mtb63Course.getCourse_name();
					} else {
						course_name = "该课程已经删除，请联系管理员！";
					}
					mtb56.setExpand1(course_name);
					//只能申请两个公共课程考试
					Mtb04Unit unitInfo = (Mtb04Unit)super.getSession("UnitInfo");
					// 预付费
					if ("01".equals(unitInfo.getUnit_payment()))
					{
						if(applyList.size() >= 2)
							payMentFlag ="true";
					}
				}
				// 省/外地继教项目|学术会议
				if ("navi066".equals(naviId)) {
					// 时间段
					String masterName = mtb02AdmService.getAdmName("129", mtb56.getExpand3());
					mtb56.setExpand3(masterName);
				}
				// 科研成果信息 
				if ("navi068".equals(naviId)) {
					// 地区等级
					String masterName = mtb02AdmService.getAdmName("109", mtb56.getExpand7());
					mtb56.setExpand7(masterName);
					// 获奖等级
					String masterName0 = mtb02AdmService.getAdmName("110", mtb56.getExpand8());
					mtb56.setExpand8(masterName0);
					// 参与级别
					String masterName1 = mtb02AdmService.getAdmName("111", mtb56.getExpand9());
					mtb56.setExpand9(masterName1);
				}
				// 论文综述信息 根据刊物级别授予学分
				if ("navi069".equals(naviId)) {
					// 发表刊物
					Mtb70publication mtb70pub = rlgl020602Service.getPublicationDetail(mtb56.getExpand2());
					mtb56.setExpand2(mtb70pub.getPublication_name());
					// 参与等级
					String masterName = mtb02AdmService.getAdmName("118", mtb56.getExpand4());
					mtb56.setExpand4(masterName);
				}
				// 科研立项 学分计算
				if ("navi070".equals(naviId)) {
					// 参与等级
					String masterName = mtb02AdmService.getAdmName("112", mtb56.getExpand4());
					mtb56.setExpand4(masterName);
					// 参与等级
					String masterName1 = mtb02AdmService.getAdmName("110", mtb56.getExpand5());
					mtb56.setExpand5(masterName1);
					// 参与等级
					String masterName2 = mtb02AdmService.getAdmName("113", mtb56.getExpand6());
					mtb56.setExpand6(masterName2);
				}
				// 院内学分
//				if ("navi071".equals(naviId)) {
//					// 课题类别
//					String masterName = mtb02AdmService.getAdmName("115", mtb56.getExpand5());
//					mtb56.setExpand5(masterName);
//					// 参与方式
//					String masterName1 = mtb02AdmService.getAdmName("116", mtb56.getExpand6());
//					mtb56.setExpand6(masterName1);
//				}
				// 学历（学位）教育
				if ("navi072".equals(naviId)) {
					// 认定方式
					String masterName = mtb02AdmService.getAdmName("114", mtb56.getExpand1());
					mtb56.setExpand1(masterName);
				}
				// 专利信息
				if ("navi076".equals(naviId)) {
					// 培训项目
					String masterName = mtb02AdmService.getAdmName("130", mtb56.getExpand1());
					mtb56.setExpand1(masterName);
				}
				// 专利信息
				if ("navi078".equals(naviId)) {
					// 专利类别
					String masterName = mtb02AdmService.getAdmName("131", mtb56.getExpand1());
					mtb56.setExpand1(masterName);
					// 专利人位次
					String masterName1 = mtb02AdmService.getAdmName("132", mtb56.getExpand4());
					mtb56.setExpand4(masterName1);
				}
					
				expandInfoTemp.add(mtb56.getExpand1());
				expandInfoTemp.add(mtb56.getExpand2());
				expandInfoTemp.add(mtb56.getExpand3());
				expandInfoTemp.add(mtb56.getExpand4());
				expandInfoTemp.add(mtb56.getExpand5());
				expandInfoTemp.add(mtb56.getExpand6());
				expandInfoTemp.add(mtb56.getExpand7());
				expandInfoTemp.add(mtb56.getExpand8());
				expandInfoTemp.add(mtb56.getExpand9());
				expandInfoTemp.add(mtb56.getExpand10());
				expandInfoTemp.add(mtb56.getExpand11());
				expandInfoTemp.add(mtb56.getExpand12());
				expandInfoTemp.add(mtb56.getExpand13());
				expandInfoTemp.add(mtb56.getExpand14());
				expandInfoTemp.add(mtb56.getExpand15());
				expandInfoTemp.add(mtb56.getExpand16());
				expandInfoTemp.add(mtb56.getExpand17());
				expandInfoTemp.add(mtb56.getExpand18());
				expandInfoTemp.add(mtb56.getExpand19());
				expandInfoTemp.add(mtb56.getExpand20());
				
				rlgl020601.setExpandInfo(expandInfoTemp);

				// 申请状态
				rlgl020601.setApply_status(mtb56.getApply_status());

				// 审核状态
				check_result = mtb56.getCheck_result();
				result_Name = mtb02AdmService.getAdmName("042", check_result);
				rlgl020601.setCheck_result(result_Name);

				// 区县审核人
				rlgl020601.setCounty_checker(mtb56.getCounty_checker());

				// 区县审核时间
				rlgl020601.setCounty_check_date(mtb56.getCounty_check_date());

				// 区县审核意见
				rlgl020601.setCounty_check_views(mtb56.getCounty_check_views());

				// 区县审核结果
				rlgl020601.setCounty_check_result(mtb56.getCounty_check_result());

				// 市局审核人
				rlgl020601.setDowntown_checker(mtb56.getDowntown_checker());

				// 市局审核时间
				rlgl020601.setDowntown_check_date(mtb56.getDowntown_check_date());

				// 市局审核意见
				rlgl020601.setDowntown_check_views(mtb56.getDowntown_check_views());

				// 市局审核结果
				rlgl020601.setDowntown_check_result(mtb56.getDowntown_check_result());

				// 省厅审核人
				rlgl020601.setMinistry_checker(mtb56.getMinistry_checker());

				// 省厅审核时间
				rlgl020601.setMinistry_check_date(mtb56.getMinistry_check_date());

				// 省厅审核意见
				rlgl020601.setMinistry_check_views(mtb56.getMinistry_check_views());

				// 省厅审核结果
				rlgl020601.setMinistry_check_result(mtb56.getMinistry_check_result());

				// 当前节点
				rlgl020601.setNow_mark(mtb56.getNow_mark());

				// 终审节点
				rlgl020601.setEnd_mark(mtb56.getEnd_mark());

				// 待审核单位NO
				rlgl020601.setWill_checkunitno(mtb56.getWill_checkunitno());

				// 申请单位
				unitNo = mtb56.getUnit_no();
				unitinfo = rlgl010106Service.getUnitInfo(unitNo);
				if (unitinfo != null) {
					unitName = unitinfo.getUnit_nm();
				} else {
					unitName = "";
				}

				// 审核结果
				String result = mtb56.getCheck_result();
				// 当前节点
				String nowMark = mtb56.getNow_mark();
				// 终审节点
				String ednMark = mtb56.getEnd_mark();

				if (nowMark.equals(ednMark)) {
					rlgl020601.setEnd_mark("1");
				} else {
					rlgl020601.setEnd_mark("0");
				}

				// 单位级别
				String scale = unitinfo.getUnit_level();
				String flow = "";
				String stutas = "";

				// 审核流程状态
				CheckFlow checkFlow = new CheckFlow();
				Map<String, String> checkInfoMap = checkFlow.getPersonalFlow(
						ednMark, scale, nowMark, result);
				flow = checkInfoMap.get("flow");
				rlgl020601.setFlow(flow);
				stutas = checkInfoMap.get("stutas");
				rlgl020601.setStutas(stutas);

				rlgl020601List.add(rlgl020601);
			}
		}

		super.getSession().setAttribute("rlgl020601List", rlgl020601List);

		return SUCCESS;
	}

	public Rlgl020601Bean getRlgl020601() {
		return rlgl020601;
	}

	public void setRlgl020601(Rlgl020601Bean rlgl020601) {
		this.rlgl020601 = rlgl020601;
	}

	public IRlgl020601Service getRlgl020601Service() {
		return rlgl020601Service;
	}

	public void setRlgl020601Service(IRlgl020601Service rlgl020601Service) {
		this.rlgl020601Service = rlgl020601Service;
	}

	public List<Rlgl020601Bean> getRlgl020601List() {
		return rlgl020601List;
	}

	public void setRlgl020601List(List<Rlgl020601Bean> rlgl020601List) {
		this.rlgl020601List = rlgl020601List;
	}

	public IRlgl010106Service getRlgl010106Service() {
		return rlgl010106Service;
	}

	public void setRlgl010106Service(IRlgl010106Service rlgl010106Service) {
		this.rlgl010106Service = rlgl010106Service;
	}

	public Mtb04Unit getUnitinfo() {
		return unitinfo;
	}

	public void setUnitinfo(Mtb04Unit unitinfo) {
		this.unitinfo = unitinfo;
	}

	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}

	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
	}

	public List<Mtb60formlist> getFormList() {
		return formList;
	}

	public void setFormList(List<Mtb60formlist> formList) {
		this.formList = formList;
	}

	public String getNaviId() {
		return naviId;
	}

	public void setNaviId(String naviId) {
		this.naviId = naviId;
	}

	public IRlgl020602Service getRlgl020602Service() {
		return rlgl020602Service;
	}

	public void setRlgl020602Service(IRlgl020602Service rlgl020602Service) {
		this.rlgl020602Service = rlgl020602Service;
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

	public IRlgl020807Service getRlgl020807Service() {
		return rlgl020807Service;
	}

	public void setRlgl020807Service(IRlgl020807Service rlgl020807Service) {
		this.rlgl020807Service = rlgl020807Service;
	}

	public String getPayMentFlag() {
		return payMentFlag;
	}

	public void setPayMentFlag(String payMentFlag) {
		this.payMentFlag = payMentFlag;
	}

	public String getPage_flg() {
		return page_flg;
	}

	public void setPage_flg(String page_flg) {
		this.page_flg = page_flg;
	}
}
