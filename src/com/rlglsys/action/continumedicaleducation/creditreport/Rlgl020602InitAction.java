package com.rlglsys.action.continumedicaleducation.creditreport;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.rlglsys.base.BaseAction;
import com.rlglsys.entity.MTb02Adm;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb19PersonnelPractitionersInfo;
import com.rlglsys.entity.Mtb60formlist;
import com.rlglsys.entity.Mtb63CourseWare;
import com.rlglsys.entity.Mtb70publication;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IRlgl020602Service;
import com.rlglsys.service.IRlgl020803Service;
import com.rlglsys.service.IRlgl020807Service;
import com.rlglsys.util.Constant;
import com.rlglsys.util.DateTimeManager;

public class Rlgl020602InitAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 420963007376212028L;

	private String navi_Id;
	private List<Mtb60formlist> formList;
	private IRlgl020602Service rlgl020602Service;
	private IRlgl020807Service rlgl020807Service;
	private IMTb02AdmService  mtb02AdmService;
	private IRlgl020803Service rlgl020803Service;
	List<MTb02Adm> timeList;
	List<MTb02Adm> creditList;
	List<MTb02Adm> areaLevel;
	List<MTb02Adm> winnerLevel;
	List<MTb02Adm> participateLevel;
	List<MTb02Adm> participate;
	List<MTb02Adm> topicLevel;
	List<MTb02Adm> takersLevel;
	List<MTb02Adm> topicKind;
	List<MTb02Adm> participation;
	List<MTb02Adm> methods;
	List<MTb02Adm> phases;
	List<MTb02Adm> country;
	List<MTb02Adm> city;
	List<Mtb63CourseWare> course;
	List<Mtb70publication> publication;
	List<MTb02Adm> train;
	List<MTb02Adm> patentCategory;
	List<MTb02Adm> patentPlaces;

	@Override
	protected String doExecute() throws Exception {
		
		// 获得session中的用户信息
		Mtb01User userInfo = (Mtb01User) super.getSession().getAttribute(
				Constant.SESSION_KEY_LOGINUSER);

		// 设置获取列表表头所需变量
		Mtb60formlist mtb60 = new Mtb60formlist();
		mtb60.setNavi(navi_Id);
		// 1：获取列表页表头
		mtb60.setItem_land_flag("1");

		// 从数据库获取列表表头
		formList = rlgl020602Service.getFormList(mtb60);
		
		// 获取填充列表所需要的List
		// 山东卫生
		if ("navi052".equals(navi_Id)) {
			// 时间段
			timeList = mtb02AdmService.getAdmInfo("108");
			ActionContext.getContext().put("timeList", timeList);
		}
		// 省/外地继教项目|学术会议
		if ("navi054".equals(navi_Id)) {
			// 学分类别
			creditList = mtb02AdmService.getAdmInfo("122");
			ActionContext.getContext().put("creditList", creditList);
			
			// 城市
			city = mtb02AdmService.getAdmInfo("128");
			ActionContext.getContext().put("city", city);
		}
		// 公共课程考试
		if ("navi055".equals(navi_Id)) {
			// 学分类别
			creditList = mtb02AdmService.getAdmInfo("122");
			ActionContext.getContext().put("creditList", creditList);
			
			// 课程名称
			// 取得执业资格
			String type = userInfo.getUser_type();
			// 取得执业范围
			String area = "";
			Mtb19PersonnelPractitionersInfo practiInfo = rlgl020803Service.getPracti(userInfo.getPersonnel_id());
			if (practiInfo != null) {
				area = practiInfo.getArea();
			}
			String nowdate = DateTimeManager.getSystemDate14();
			String nowYear = nowdate.substring(0,4);
			String startYear = String.valueOf(Integer.parseInt(nowYear) - 6) + "-01-01";
			String endYear = nowYear + "-12-31";
			course = rlgl020807Service.getMtb63InfoBySuitPerson(type,area,startYear,endYear);
			if(course ==null){
				course = new ArrayList<Mtb63CourseWare>();
			}
			ActionContext.getContext().put("course", course);
		}
		// 译作信息
		if ("navi066".equals(navi_Id)) {
			// 城市
			country = mtb02AdmService.getAdmInfo("129");
			ActionContext.getContext().put("country", country);
		}
		// 科研成果信息
		if ("navi068".equals(navi_Id)) {
			// 地区等级
			areaLevel = mtb02AdmService.getAdmInfo("109");
			ActionContext.getContext().put("areaLevel", areaLevel);
			// 获奖等级
			winnerLevel = mtb02AdmService.getAdmInfo("110");
			ActionContext.getContext().put("winnerLevel", winnerLevel);
			// 参与级别
			participateLevel = mtb02AdmService.getAdmInfo("111");
			ActionContext.getContext().put("participateLevel", participateLevel);
		}
		// 论文综述信息 根据刊物级别授予学分
		if ("navi069".equals(navi_Id)) {
			// 参与等级
			participate = mtb02AdmService.getAdmInfo("118");
			ActionContext.getContext().put("participate", participate);
			// 发表刊物
			publication = rlgl020602Service.getPublication();
			ActionContext.getContext().put("publication", publication);
		}
		// 科研立项 学分计算
		if ("navi070".equals(navi_Id)) {
			// 课题级别
			topicLevel = mtb02AdmService.getAdmInfo("112");
			ActionContext.getContext().put("topicLevel", topicLevel);
			// 获奖等级
			winnerLevel = mtb02AdmService.getAdmInfo("110");
			ActionContext.getContext().put("winnerLevel", winnerLevel);
			// 承担者排名
			takersLevel = mtb02AdmService.getAdmInfo("113");
			ActionContext.getContext().put("takersLevel", takersLevel);
		}
		// 院内学分
		if ("navi071".equals(navi_Id)) {
			// 课题类别
			topicKind = mtb02AdmService.getAdmInfo("115");
			ActionContext.getContext().put("topicKind", topicKind);
			// 参与方式
			participation = mtb02AdmService.getAdmInfo("116");
			ActionContext.getContext().put("participation", participation);
		}
		// 学历（学位）教育
		if ("navi072".equals(navi_Id)) {
			// 认定方式
			methods = mtb02AdmService.getAdmInfo("114");
			ActionContext.getContext().put("methods", methods);
		}
		// 住院医师规范化培训
		if ("navi075".equals(navi_Id)) {
			// 参加阶段
			phases = mtb02AdmService.getAdmInfo("117");
			ActionContext.getContext().put("phases", phases);
		}
		// 全科医师在岗培训
		if ("navi076".equals(navi_Id)) {
			// 培训项目
			train = mtb02AdmService.getAdmInfo("130");
			ActionContext.getContext().put("train", train);
		}
		// 专利信息
		if ("navi078".equals(navi_Id)) {
			// 专利类别
			patentCategory = mtb02AdmService.getAdmInfo("131");
			ActionContext.getContext().put("patentCategory", patentCategory);
			// 专利位次
			patentPlaces = mtb02AdmService.getAdmInfo("132");
			ActionContext.getContext().put("patentPlaces", patentPlaces);
		}

		return SUCCESS;
	}

	public String getNavi_Id() {
		return navi_Id;
	}

	public void setNavi_Id(String navi_Id) {
		this.navi_Id = navi_Id;
	}

	public List<Mtb60formlist> getFormList() {
		return formList;
	}

	public void setFormList(List<Mtb60formlist> formList) {
		this.formList = formList;
	}

	public IRlgl020602Service getRlgl020602Service() {
		return rlgl020602Service;
	}

	public void setRlgl020602Service(IRlgl020602Service rlgl020602Service) {
		this.rlgl020602Service = rlgl020602Service;
	}

	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}

	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
	}

	public List<MTb02Adm> getTimeList() {
		return timeList;
	}

	public void setTimeList(List<MTb02Adm> timeList) {
		this.timeList = timeList;
	}

	public List<MTb02Adm> getCreditList() {
		return creditList;
	}

	public void setCreditList(List<MTb02Adm> creditList) {
		this.creditList = creditList;
	}

	public List<MTb02Adm> getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(List<MTb02Adm> areaLevel) {
		this.areaLevel = areaLevel;
	}

	public List<MTb02Adm> getWinnerLevel() {
		return winnerLevel;
	}

	public void setWinnerLevel(List<MTb02Adm> winnerLevel) {
		this.winnerLevel = winnerLevel;
	}

	public List<MTb02Adm> getParticipateLevel() {
		return participateLevel;
	}

	public void setParticipateLevel(List<MTb02Adm> participateLevel) {
		this.participateLevel = participateLevel;
	}

	public List<MTb02Adm> getParticipate() {
		return participate;
	}

	public void setParticipate(List<MTb02Adm> participate) {
		this.participate = participate;
	}

	public List<MTb02Adm> getTopicLevel() {
		return topicLevel;
	}

	public void setTopicLevel(List<MTb02Adm> topicLevel) {
		this.topicLevel = topicLevel;
	}

	public List<MTb02Adm> getTakersLevel() {
		return takersLevel;
	}

	public void setTakersLevel(List<MTb02Adm> takersLevel) {
		this.takersLevel = takersLevel;
	}

	public List<MTb02Adm> getTopicKind() {
		return topicKind;
	}

	public void setTopicKind(List<MTb02Adm> topicKind) {
		this.topicKind = topicKind;
	}

	public List<MTb02Adm> getParticipation() {
		return participation;
	}

	public void setParticipation(List<MTb02Adm> participation) {
		this.participation = participation;
	}

	public List<MTb02Adm> getMethods() {
		return methods;
	}

	public void setMethods(List<MTb02Adm> methods) {
		this.methods = methods;
	}

	public List<MTb02Adm> getPhases() {
		return phases;
	}

	public void setPhases(List<MTb02Adm> phases) {
		this.phases = phases;
	}

	public List<Mtb70publication> getPublication() {
		return publication;
	}

	public void setPublication(List<Mtb70publication> publication) {
		this.publication = publication;
	}

	public List<MTb02Adm> getCountry() {
		return country;
	}

	public void setCountry(List<MTb02Adm> country) {
		this.country = country;
	}

	public List<MTb02Adm> getCity() {
		return city;
	}

	public void setCity(List<MTb02Adm> city) {
		this.city = city;
	}

	public IRlgl020807Service getRlgl020807Service() {
		return rlgl020807Service;
	}

	public void setRlgl020807Service(IRlgl020807Service rlgl020807Service) {
		this.rlgl020807Service = rlgl020807Service;
	}

	public List<Mtb63CourseWare> getCourse() {
		return course;
	}

	public void setCourse(List<Mtb63CourseWare> course) {
		this.course = course;
	}

	public List<MTb02Adm> getTrain() {
		return train;
	}

	public void setTrain(List<MTb02Adm> train) {
		this.train = train;
	}

	public List<MTb02Adm> getPatentCategory() {
		return patentCategory;
	}

	public void setPatentCategory(List<MTb02Adm> patentCategory) {
		this.patentCategory = patentCategory;
	}

	public List<MTb02Adm> getPatentPlaces() {
		return patentPlaces;
	}

	public void setPatentPlaces(List<MTb02Adm> patentPlaces) {
		this.patentPlaces = patentPlaces;
	}

	public IRlgl020803Service getRlgl020803Service() {
		return rlgl020803Service;
	}

	public void setRlgl020803Service(IRlgl020803Service rlgl020803Service) {
		this.rlgl020803Service = rlgl020803Service;
	}
}
