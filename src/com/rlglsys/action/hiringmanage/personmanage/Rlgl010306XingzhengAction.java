package com.rlglsys.action.hiringmanage.personmanage;

import java.util.List;
import com.rlglsys.base.BaseAction;
import com.rlglsys.entity.*;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IMTb04UnitService;
import com.rlglsys.service.IMTb20AreaService;
import com.rlglsys.service.IMtb48ectionService;
import com.rlglsys.service.IRlgl010302Service;
import com.rlglsys.service.IRlgl010306Service;
import com.rlglsys.service.IUserService;
import com.rlglsys.util.Constant;

public class Rlgl010306XingzhengAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IRlgl010306Service rlgl010306Service; 
	private IMTb02AdmService  mtb02AdmService;
	private IMTb20AreaService  mtb20AreaService;
	private IUserService  userService;  
	private List<MTb20Area> provincelist;
	private List<MTb20Area> citylist;
	private List<MTb20Area> zonelist;
	private List<MTb02Adm> genderAdmlist;
	private List<MTb02Adm> ethnicAdmlist;
	private List<MTb02Adm> identificationAdmlist;
	private List<MTb02Adm> landscapeAdmlist;
	private List<MTb02Adm> formsAdmlist;
	private List<MTb02Adm> maritalAdmlist;
	private List<MTb02Adm> healthAdmlist;
	private List<MTb02Adm> statusAdmlist;
	private List<MTb02Adm> highestAdmlist;
	private List<MTb02Adm> checkAdmlist;
	private List<MTb02Adm> appointAdmlist;
	private List<MTb02Adm> regularlist;
	private List<MTb02Adm> learninglist;
	private List<MTb02Adm> collegetypelist;
	private List<MTb02Adm> educationalbglist;
	private List<MTb02Adm> degreelist;
	private List<MTb02Adm> onelevellist;
	private List<MTb02Adm> twolevellist;
	private List<MTb02Adm> threelevellist;
	private List<MTb02Adm> typelist;
	private List<MTb02Adm> levellist;
	private List<MTb02Adm> pralevellist;
	private List<MTb02Adm> protypelist;
	private List<MTb02Adm> proArealist;
	private List<MTb02Adm> pratypelist;
	private List<MTb02Adm> teachertypelist;
	private List<MTb02Adm> partisanlist;
	private List<MTb02Adm> preplist;
	private List<MTb02Adm> jobArealist;
	private List<MTb02Adm> positionlist;
	
	private IRlgl010302Service rlgl010302Service; 
	private IMTb04UnitService  mtb04UnitService;  
	private IMtb48ectionService  mtb48ectionService; 
	private List<Mtb04Unit> unitlist;
	private List<Mtb48Ection> ectionlist;
	private List<MTb20Area> unitprovincelist;
	private List<MTb20Area> unitcitylist;
	private List<MTb20Area> unitzonelist;
	// 人员基本信息
	private String personnel_id;
	private Mtb39Personnel personnel;
	// 岗位信息
	private Mtb09Irin irin;
	// 人员基本信息-专业技术职务信息
	private List<Mtb45PersonnelProfessionalInfo> rlgl010306ProfessionalInfoList;
	// 人员基本信息-行政职务信息
	private List<Mtb44PersonnelJobInfo> rlgl010306JobInfoList;
	// 人员基本信息-社会关系
	private List<Mtb43PersonnelSocialInfo> rlgl010306SocialInfoList;
	// 人员基本信息-教育经历
	private List<Mtb42PersonnelEduInfo> rlgl010306EduInfoList;
	// 人员基本信息-工作经历
	private List<Mtb41PersonnelWorkInfo> rlgl010306WorkInfoList;
	// 人员基本信息-党派信息
	private List<Mtb40PersonnelPartisanInfo> rlgl010306PartisanInfoList;
	// 人员基本信息-导师信息
	private List<Mtb59PersonnelTutorInfo> rlgl010306TutorInfoList;
	// 人员基本信息-资格信息
	private List<Mtb46PersonnelPractitionersInfo> rlgl010306PractitionersInfoList;
	// 人员基本信息-执业信息
	private List<Mtb78PersonnelPracticeInfo> rlgl010306PracticeInfoList;
	// 返回页面
	private String backAction;
	// 变更标识
	private String change_value;
	private String objectArray;
	private String addOrInputFlg;
	// 上次保存的变更字段
	private String saveChangeValue;
	@Override
	protected String doExecute() {
		// TODO Auto-generated method stub
	try{
			
		Mtb01User loginUser = (Mtb01User)super.getSession(Constant.SESSION_KEY_LOGINUSER);
		Mtb39Personnel mtb39Personnel = new Mtb39Personnel();
		String personnelId=loginUser.getPersonnel_id();
		if(personnel_id!=null && !"".equals(personnel_id)){
			personnelId = personnel_id;
		}
		mtb39Personnel.setPersonnel_id(personnelId);
		personnel = rlgl010306Service.searchRlgl010306(mtb39Personnel);
		// 审核中时不可更改，跳到不可更改画面
		if(personnel != null && "001".equals(personnel.getPersonnel_isapproval())){
			return "rlgl010305";
		}
		// 获得性别下拉列表
		genderAdmlist=mtb02AdmService.getAdmInfo("030");
		// 获得民族下拉列表
		ethnicAdmlist=mtb02AdmService.getAdmInfo("026");
		// 获得个人身份下拉列表
		identificationAdmlist=mtb02AdmService.getAdmInfo("031");
		// 获得政治面貌下拉列表
		landscapeAdmlist=mtb02AdmService.getAdmInfo("032");
		// 获得用工形式下拉列表
		formsAdmlist=mtb02AdmService.getAdmInfo("003");
		// 获得婚姻状况下拉列表
		maritalAdmlist=mtb02AdmService.getAdmInfo("034");
		// 获得健康状况下拉列表
		healthAdmlist=mtb02AdmService.getAdmInfo("035");
		// 获得岗位状态下拉列表
		statusAdmlist=mtb02AdmService.getAdmInfo("036");
		// 获得年度审核判定下拉列表
		checkAdmlist=mtb02AdmService.getAdmInfo("037");
		// 获得是否聘任下拉列表
		appointAdmlist=mtb02AdmService.getAdmInfo("038");
		// 获得是否最高职称下拉列表
		highestAdmlist=mtb02AdmService.getAdmInfo("039");
		// 获得学习形式下拉列表
		learninglist=mtb02AdmService.getAdmInfo("100");
		// 获得院校类型下拉列表
		collegetypelist=mtb02AdmService.getAdmInfo("101");
		// 获得学历下拉列表
		educationalbglist=mtb02AdmService.getAdmInfo("040");
		// 获得学位下拉列表
		degreelist=mtb02AdmService.getAdmInfo("090");
		// 获得职务类别下拉列表
		typelist=mtb02AdmService.getAdmInfo("020");
		// 获得职务级别下拉列表
		levellist=mtb02AdmService.getAdmInfo("006");
		// 获得所在单位下拉列表
		unitlist=mtb04UnitService.getUnitList("00");
		unitlist.clear();
		// 获得所在科室下拉列表
		ectionlist=mtb48ectionService.getEctionList("00");
		ectionlist.clear();
		// 获得省下拉列表
		provincelist=mtb20AreaService.getAreaProvince();
		unitprovincelist=mtb20AreaService.getAreaProvince();
		// 获得市下拉列表
		citylist=mtb20AreaService.getAreaCity("00");
		citylist.clear();
		unitcitylist=mtb20AreaService.getAreaCity("00");
		unitcitylist.clear();
		// 获得县下拉列表
		zonelist=mtb20AreaService.getAreaZone("00","00");
		zonelist.clear();
		unitzonelist=mtb20AreaService.getAreaZone("00","00");
		unitzonelist.clear();
		// 获得在编状态下拉列表
		regularlist=mtb02AdmService.getAdmInfo("092");
		// 技术第一级别下拉列表
		onelevellist=mtb02AdmService.getAdmInfo("088");
		// 技术第二级别下拉列表
		twolevellist=mtb02AdmService.getAdmInfo("087");
		// 技术第三级别下拉列表
		threelevellist=mtb02AdmService.getAdmInfo("027");
		// 执业级别下拉列表
		pralevellist=mtb02AdmService.getAdmInfo("009");
		// 专业类别 下拉列表
		protypelist=mtb02AdmService.getAdmInfo("008");
		// 执业类别 下拉列表
		pratypelist=mtb02AdmService.getAdmInfo("121");
		// 导师类别 下拉列表
		teachertypelist=mtb02AdmService.getAdmInfo("099");
		// 党派名称下拉列表
		partisanlist=mtb02AdmService.getAdmInfo("102");
		// 正式、预备下拉列表
		preplist=mtb02AdmService.getAdmInfo("103");
		// 执业范围下拉列表
		jobArealist=mtb02AdmService.getAdmInfo("121");
		// 医师执业范围下拉列表(9.28新追加)
		proArealist=mtb02AdmService.getAdmInfo("134");
		// 行政职务名称
		positionlist=mtb02AdmService.getAdmInfo("181");
		
		// 获得地区
		personnel.setPersonnel_province(((personnel.getPersonnel_area()+"      ").substring(0,2)).trim());
		citylist=mtb20AreaService.getAreaCity(personnel.getPersonnel_province());
		personnel.setPersonnel_city(((personnel.getPersonnel_area()+"      ").substring(2,4)).trim());
		zonelist=mtb20AreaService.getAreaZone(personnel.getPersonnel_province(),personnel.getPersonnel_city());
		personnel.setPersonnel_zone(((personnel.getPersonnel_area()+"      ").substring(4,6)).trim());
		// 单位名称
		personnel.setPersonnel_unit_nm(mtb04UnitService.getUnitName(personnel.getPersonnel_unit()));
		// 岗位名称
		irin = rlgl010306Service.getPersonInfo(personnel);
		if(irin != null){
			personnel.setPersonnel_admintype(mtb02AdmService.getAdmName("029", irin.getPost_kbn())+mtb02AdmService.getAdmName(irin.getPost_kbn(), irin.getPost_level())+irin.getPost_name());
		}
		if("".equals(personnel.getPersonnel_birthday())){
			personnel.setPersonnel_birthday(personnel.getPersonnel_card_id().substring(6,10) + 
											"-" + personnel.getPersonnel_card_id().substring(10,12) + 
											"-" + personnel.getPersonnel_card_id().substring(12,14));
		}
		// 科室取得
		personnel.setPersonnel_office_nm(mtb48ectionService.getEctionName(rlgl010306Service.searchUser(personnel).getSection_id(),personnel.getPersonnel_unit()));
		// 生日日期格式变化
		if(personnel.getPersonnel_birthday().length()== 8 && !personnel.getPersonnel_birthday().contains("-")){
			personnel.setPersonnel_birthday(personnel.getPersonnel_birthday().substring(0,4) + 
											"-" + personnel.getPersonnel_birthday().substring(4,6) + 
											"-" + personnel.getPersonnel_birthday().substring(6,8));
		}
		// 参加工作时间日期格式变化
		if(personnel.getPersonnel_worktime().length()== 8 && !personnel.getPersonnel_worktime().contains("-")){
			personnel.setPersonnel_worktime(personnel.getPersonnel_worktime().substring(0,4) + 
											"-" + personnel.getPersonnel_worktime().substring(4,6) + 
											"-" + personnel.getPersonnel_worktime().substring(6,8));
		}
		// 入党(团)时间日期格式变化
		if(personnel.getPersonnel_joinpartytime().length()== 8 && !personnel.getPersonnel_joinpartytime().contains("-")){
			personnel.setPersonnel_joinpartytime(personnel.getPersonnel_joinpartytime().substring(0,4) + 
											"-" + personnel.getPersonnel_joinpartytime().substring(4,6) + 
											"-" + personnel.getPersonnel_joinpartytime().substring(6,8));
		}
		// 年度审核判定
		if(personnel.getPersonnel_check() !=null){
			personnel.setPersonnel_check(mtb02AdmService.getAdmName("054", personnel.getPersonnel_check()));
		}
		
		// 获得变更记录
		saveChangeValue=personnel.getChange_value();
		objectArray=saveChangeValue;
		change_value=objectArray;
		addOrInputFlg="";
		// 获得科室下拉列表
		ectionlist=mtb48ectionService.getEctionList(personnel.getPersonnel_unit());

		// 专业技术下拉列表
		rlgl010306ProfessionalInfoList = rlgl010306Service.searchRlgl010306ProfessionalInfo(mtb39Personnel);
		// 职务信息
		rlgl010306JobInfoList = rlgl010306Service.searchRlgl010306JobInfo(mtb39Personnel);
		// 社会关系
		rlgl010306SocialInfoList = rlgl010306Service.searchRlgl010306SocialInfo(mtb39Personnel);
		// 教育经历
		rlgl010306EduInfoList = rlgl010306Service.searchRlgl010306EduInfo(mtb39Personnel);
		// 工作经历
		rlgl010306WorkInfoList = rlgl010306Service.searchRlgl010306WorkInfo(mtb39Personnel);
		// 党派信息
		rlgl010306PartisanInfoList = rlgl010306Service.searchRlgl010306PartisanInfo(mtb39Personnel);
		// 资格信息
		rlgl010306PractitionersInfoList = rlgl010306Service.searchRlgl010306PractitionersInfo(mtb39Personnel);
		// 执业信息
		rlgl010306PracticeInfoList = rlgl010306Service.searchRlgl010306PracticeInfo(mtb39Personnel);
		// 导师信息
		rlgl010306TutorInfoList = rlgl010306Service.searchRlgl010306TutorInfo(mtb39Personnel);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
			
		return SUCCESS;
	}
	public IRlgl010306Service getRlgl010306Service() {
		return rlgl010306Service;
	}
	public void setRlgl010306Service(IRlgl010306Service rlgl010306Service) {
		this.rlgl010306Service = rlgl010306Service;
	}
	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}
	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
	}
	public IMTb20AreaService getMtb20AreaService() {
		return mtb20AreaService;
	}
	public void setMtb20AreaService(IMTb20AreaService mtb20AreaService) {
		this.mtb20AreaService = mtb20AreaService;
	}
	public List<MTb20Area> getProvincelist() {
		return provincelist;
	}
	public void setProvincelist(List<MTb20Area> provincelist) {
		this.provincelist = provincelist;
	}
	public List<MTb20Area> getCitylist() {
		return citylist;
	}
	public void setCitylist(List<MTb20Area> citylist) {
		this.citylist = citylist;
	}
	public List<MTb20Area> getZonelist() {
		return zonelist;
	}
	public void setZonelist(List<MTb20Area> zonelist) {
		this.zonelist = zonelist;
	}
	public List<MTb02Adm> getGenderAdmlist() {
		return genderAdmlist;
	}
	public void setGenderAdmlist(List<MTb02Adm> genderAdmlist) {
		this.genderAdmlist = genderAdmlist;
	}
	public List<MTb02Adm> getEthnicAdmlist() {
		return ethnicAdmlist;
	}
	public void setEthnicAdmlist(List<MTb02Adm> ethnicAdmlist) {
		this.ethnicAdmlist = ethnicAdmlist;
	}
	public List<MTb02Adm> getIdentificationAdmlist() {
		return identificationAdmlist;
	}
	public void setIdentificationAdmlist(List<MTb02Adm> identificationAdmlist) {
		this.identificationAdmlist = identificationAdmlist;
	}
	public List<MTb02Adm> getLandscapeAdmlist() {
		return landscapeAdmlist;
	}
	public void setLandscapeAdmlist(List<MTb02Adm> landscapeAdmlist) {
		this.landscapeAdmlist = landscapeAdmlist;
	}
	public List<MTb02Adm> getFormsAdmlist() {
		return formsAdmlist;
	}
	public void setFormsAdmlist(List<MTb02Adm> formsAdmlist) {
		this.formsAdmlist = formsAdmlist;
	}
	public List<MTb02Adm> getMaritalAdmlist() {
		return maritalAdmlist;
	}
	public void setMaritalAdmlist(List<MTb02Adm> maritalAdmlist) {
		this.maritalAdmlist = maritalAdmlist;
	}
	public List<MTb02Adm> getHealthAdmlist() {
		return healthAdmlist;
	}
	public void setHealthAdmlist(List<MTb02Adm> healthAdmlist) {
		this.healthAdmlist = healthAdmlist;
	}
	public List<MTb02Adm> getStatusAdmlist() {
		return statusAdmlist;
	}
	public void setStatusAdmlist(List<MTb02Adm> statusAdmlist) {
		this.statusAdmlist = statusAdmlist;
	}
	public List<MTb02Adm> getHighestAdmlist() {
		return highestAdmlist;
	}
	public void setHighestAdmlist(List<MTb02Adm> highestAdmlist) {
		this.highestAdmlist = highestAdmlist;
	}
	public List<MTb02Adm> getCheckAdmlist() {
		return checkAdmlist;
	}
	public void setCheckAdmlist(List<MTb02Adm> checkAdmlist) {
		this.checkAdmlist = checkAdmlist;
	}
	public List<MTb02Adm> getAppointAdmlist() {
		return appointAdmlist;
	}
	public void setAppointAdmlist(List<MTb02Adm> appointAdmlist) {
		this.appointAdmlist = appointAdmlist;
	}
	public Mtb39Personnel getPersonnel() {
		return personnel;
	}
	public void setPersonnel(Mtb39Personnel personnel) {
		this.personnel = personnel;
	}
	public List<Mtb45PersonnelProfessionalInfo> getRlgl010306ProfessionalInfoList() {
		return rlgl010306ProfessionalInfoList;
	}
	public void setRlgl010306ProfessionalInfoList(
			List<Mtb45PersonnelProfessionalInfo> rlgl010306ProfessionalInfoList) {
		this.rlgl010306ProfessionalInfoList = rlgl010306ProfessionalInfoList;
	}
	public List<Mtb44PersonnelJobInfo> getRlgl010306JobInfoList() {
		return rlgl010306JobInfoList;
	}
	public void setRlgl010306JobInfoList(
			List<Mtb44PersonnelJobInfo> rlgl010306JobInfoList) {
		this.rlgl010306JobInfoList = rlgl010306JobInfoList;
	}
	public List<Mtb43PersonnelSocialInfo> getRlgl010306SocialInfoList() {
		return rlgl010306SocialInfoList;
	}
	public void setRlgl010306SocialInfoList(
			List<Mtb43PersonnelSocialInfo> rlgl010306SocialInfoList) {
		this.rlgl010306SocialInfoList = rlgl010306SocialInfoList;
	}
	public List<Mtb42PersonnelEduInfo> getRlgl010306EduInfoList() {
		return rlgl010306EduInfoList;
	}
	public void setRlgl010306EduInfoList(
			List<Mtb42PersonnelEduInfo> rlgl010306EduInfoList) {
		this.rlgl010306EduInfoList = rlgl010306EduInfoList;
	}
	public List<Mtb41PersonnelWorkInfo> getRlgl010306WorkInfoList() {
		return rlgl010306WorkInfoList;
	}
	public void setRlgl010306WorkInfoList(
			List<Mtb41PersonnelWorkInfo> rlgl010306WorkInfoList) {
		this.rlgl010306WorkInfoList = rlgl010306WorkInfoList;
	}
	public List<Mtb40PersonnelPartisanInfo> getRlgl010306PartisanInfoList() {
		return rlgl010306PartisanInfoList;
	}
	public void setRlgl010306PartisanInfoList(
			List<Mtb40PersonnelPartisanInfo> rlgl010306PartisanInfoList) {
		this.rlgl010306PartisanInfoList = rlgl010306PartisanInfoList;
	}
	public List<Mtb46PersonnelPractitionersInfo> getRlgl010306PractitionersInfoList() {
		return rlgl010306PractitionersInfoList;
	}
	public void setRlgl010306PractitionersInfoList(
			List<Mtb46PersonnelPractitionersInfo> rlgl010306PractitionersInfoList) {
		this.rlgl010306PractitionersInfoList = rlgl010306PractitionersInfoList;
	}
	public IRlgl010302Service getRlgl010302Service() {
		return rlgl010302Service;
	}
	public void setRlgl010302Service(IRlgl010302Service rlgl010302Service) {
		this.rlgl010302Service = rlgl010302Service;
	}
	public IMTb04UnitService getMtb04UnitService() {
		return mtb04UnitService;
	}
	public void setMtb04UnitService(IMTb04UnitService mtb04UnitService) {
		this.mtb04UnitService = mtb04UnitService;
	}
	public IMtb48ectionService getMtb48ectionService() {
		return mtb48ectionService;
	}
	public void setMtb48ectionService(IMtb48ectionService mtb48ectionService) {
		this.mtb48ectionService = mtb48ectionService;
	}
	public List<Mtb04Unit> getUnitlist() {
		return unitlist;
	}
	public void setUnitlist(List<Mtb04Unit> unitlist) {
		this.unitlist = unitlist;
	}
	public List<Mtb48Ection> getEctionlist() {
		return ectionlist;
	}
	public void setEctionlist(List<Mtb48Ection> ectionlist) {
		this.ectionlist = ectionlist;
	}
	public List<MTb20Area> getUnitprovincelist() {
		return unitprovincelist;
	}
	public void setUnitprovincelist(List<MTb20Area> unitprovincelist) {
		this.unitprovincelist = unitprovincelist;
	}
	public List<MTb20Area> getUnitcitylist() {
		return unitcitylist;
	}
	public void setUnitcitylist(List<MTb20Area> unitcitylist) {
		this.unitcitylist = unitcitylist;
	}
	public List<MTb20Area> getUnitzonelist() {
		return unitzonelist;
	}
	public void setUnitzonelist(List<MTb20Area> unitzonelist) {
		this.unitzonelist = unitzonelist;
	}
	public String getPersonnel_id() {
		return personnel_id;
	}
	public void setPersonnel_id(String personnel_id) {
		this.personnel_id = personnel_id;
	}
	public List<MTb02Adm> getRegularlist() {
		return regularlist;
	}
	public void setRegularlist(List<MTb02Adm> regularlist) {
		this.regularlist = regularlist;
	}
	public List<MTb02Adm> getEducationalbglist() {
		return educationalbglist;
	}
	public void setEducationalbglist(List<MTb02Adm> educationalbglist) {
		this.educationalbglist = educationalbglist;
	}
	public List<MTb02Adm> getDegreelist() {
		return degreelist;
	}
	public void setDegreelist(List<MTb02Adm> degreelist) {
		this.degreelist = degreelist;
	}
	public List<MTb02Adm> getOnelevellist() {
		return onelevellist;
	}
	public void setOnelevellist(List<MTb02Adm> onelevellist) {
		this.onelevellist = onelevellist;
	}
	public List<MTb02Adm> getTwolevellist() {
		return twolevellist;
	}
	public void setTwolevellist(List<MTb02Adm> twolevellist) {
		this.twolevellist = twolevellist;
	}
	public List<MTb02Adm> getThreelevellist() {
		return threelevellist;
	}
	public void setThreelevellist(List<MTb02Adm> threelevellist) {
		this.threelevellist = threelevellist;
	}
	public List<MTb02Adm> getTypelist() {
		return typelist;
	}
	public void setTypelist(List<MTb02Adm> typelist) {
		this.typelist = typelist;
	}
	public List<MTb02Adm> getLevellist() {
		return levellist;
	}
	public void setLevellist(List<MTb02Adm> levellist) {
		this.levellist = levellist;
	}
	public List<MTb02Adm> getPralevellist() {
		return pralevellist;
	}
	public void setPralevellist(List<MTb02Adm> pralevellist) {
		this.pralevellist = pralevellist;
	}
	public List<MTb02Adm> getPratypelist() {
		return pratypelist;
	}
	public void setPratypelist(List<MTb02Adm> pratypelist) {
		this.pratypelist = pratypelist;
	}
	public List<Mtb59PersonnelTutorInfo> getRlgl010306TutorInfoList() {
		return rlgl010306TutorInfoList;
	}
	public void setRlgl010306TutorInfoList(
			List<Mtb59PersonnelTutorInfo> rlgl010306TutorInfoList) {
		this.rlgl010306TutorInfoList = rlgl010306TutorInfoList;
	}
	public String getChange_value() {
		return change_value;
	}
	public void setChange_value(String change_value) {
		this.change_value = change_value;
	}
	public List<MTb02Adm> getTeachertypelist() {
		return teachertypelist;
	}
	public void setTeachertypelist(List<MTb02Adm> teachertypelist) {
		this.teachertypelist = teachertypelist;
	}
	public String getBackAction() {
		return backAction;
	}
	public void setBackAction(String backAction) {
		this.backAction = backAction;
	}
	public String getObjectArray() {
		return objectArray;
	}
	public void setObjectArray(String objectArray) {
		this.objectArray = objectArray;
	}
	public String getAddOrInputFlg() {
		return addOrInputFlg;
	}
	public void setAddOrInputFlg(String addOrInputFlg) {
		this.addOrInputFlg = addOrInputFlg;
	}
	public IUserService getUserService() {
		return userService;
	}
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public List<MTb02Adm> getLearninglist() {
		return learninglist;
	}
	public void setLearninglist(List<MTb02Adm> learninglist) {
		this.learninglist = learninglist;
	}
	public List<MTb02Adm> getCollegetypelist() {
		return collegetypelist;
	}
	public void setCollegetypelist(List<MTb02Adm> collegetypelist) {
		this.collegetypelist = collegetypelist;
	}
	public Mtb09Irin getIrin() {
		return irin;
	}
	public void setIrin(Mtb09Irin irin) {
		this.irin = irin;
	}
	public List<MTb02Adm> getPartisanlist() {
		return partisanlist;
	}
	public void setPartisanlist(List<MTb02Adm> partisanlist) {
		this.partisanlist = partisanlist;
	}
	public List<MTb02Adm> getPreplist() {
		return preplist;
	}
	public void setPreplist(List<MTb02Adm> preplist) {
		this.preplist = preplist;
	}
	public String getSaveChangeValue() {
		return saveChangeValue;
	}
	public void setSaveChangeValue(String saveChangeValue) {
		this.saveChangeValue = saveChangeValue;
	}
	public List<MTb02Adm> getJobArealist() {
		return jobArealist;
	}
	public void setJobArealist(List<MTb02Adm> jobArealist) {
		this.jobArealist = jobArealist;
	}
	public List<Mtb78PersonnelPracticeInfo> getRlgl010306PracticeInfoList() {
		return rlgl010306PracticeInfoList;
	}
	public void setRlgl010306PracticeInfoList(
			List<Mtb78PersonnelPracticeInfo> rlgl010306PracticeInfoList) {
		this.rlgl010306PracticeInfoList = rlgl010306PracticeInfoList;
	}
	public List<MTb02Adm> getProtypelist() {
		return protypelist;
	}
	public void setProtypelist(List<MTb02Adm> protypelist) {
		this.protypelist = protypelist;
	}
	public List<MTb02Adm> getProArealist() {
		return proArealist;
	}
	public void setProArealist(List<MTb02Adm> proArealist) {
		this.proArealist = proArealist;
	}
	public List<MTb02Adm> getPositionlist() {
		return positionlist;
	}
	public void setPositionlist(List<MTb02Adm> positionlist) {
		this.positionlist = positionlist;
	}

	
}
