package com.rlglsys.action.hiringmanage.personmanage;

import java.util.List;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl010303Bean;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb09Irin;
import com.rlglsys.entity.Mtb12Personnel;
import com.rlglsys.entity.Mtb13PersonnelPartisanInfo;
import com.rlglsys.entity.Mtb14PersonnelWorkInfo;
import com.rlglsys.entity.Mtb15PersonnelEduInfo;
import com.rlglsys.entity.Mtb16PersonnelSocialInfo;
import com.rlglsys.entity.Mtb17PersonnelJobInfo;
import com.rlglsys.entity.Mtb18PersonnelProfessionalInfo;
import com.rlglsys.entity.Mtb19PersonnelPractitionersInfo;
import com.rlglsys.entity.Mtb39Personnel;
import com.rlglsys.entity.Mtb58PersonnelTutorInfo;
import com.rlglsys.entity.Mtb77PersonnelPracticeInfo;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IMTb04UnitService;
import com.rlglsys.service.IMTb20AreaService;
import com.rlglsys.service.IMtb48ectionService;
import com.rlglsys.service.IRlgl010305Service;
import com.rlglsys.service.IRlgl010306Service;
import com.rlglsys.util.Constant;
public class Rlgl010305DaoshiAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String personnel_id;
	private IRlgl010305Service rlgl010305Service; 
	private IRlgl010306Service rlgl010306Service; 
	private IMTb02AdmService  mtb02AdmService;
	private IMTb20AreaService  mtb20AreaService;  
	private IMTb04UnitService mtb04UnitService;
	private IMtb48ectionService  mtb48ectionService; 
	// 人员基本信息
	private Mtb12Personnel personnel;
	private Mtb39Personnel personnel39;
	// 岗位信息
	private Mtb09Irin irin;
	// 变更字段的存取
	private String reviewElement;
	// 查询时要用的bean
	private Rlgl010303Bean personnelBean;
	// 人员基本信息-专业技术职务信息
	private List<Mtb18PersonnelProfessionalInfo> rlgl010302ProfessionalInfoList;
	// 人员基本信息-行政职务信息
	private List<Mtb17PersonnelJobInfo> rlgl010302JobInfoList;
	// 人员基本信息-社会关系
	private List<Mtb16PersonnelSocialInfo> rlgl010302SocialInfoList;
	// 人员基本信息-教育经历
	private List<Mtb15PersonnelEduInfo> rlgl010302EduInfoList;
	// 人员基本信息-工作经历
	private List<Mtb14PersonnelWorkInfo> rlgl010302WorkInfoList;
	// 人员基本信息-党派信息
	private List<Mtb13PersonnelPartisanInfo> rlgl010302PartisanInfoList;
	// 人员基本信息-资格信息
	private List<Mtb19PersonnelPractitionersInfo> rlgl010302PractitionersInfoList;
	// 人员基本信息-执业信息
	private List<Mtb77PersonnelPracticeInfo> rlgl010302PracticeInfoList;
	// 人员基本信息-导师信息
	private List<Mtb58PersonnelTutorInfo> rlgl010302TutorInfoList;
	// 返回页面
	private String backAction;
	// 更新按钮显示控制标签
	private String updateAction;
	@Override
	protected String doExecute() throws Exception {
		Mtb01User loginUser = (Mtb01User)super.getSession(Constant.SESSION_KEY_LOGINUSER);
		String personnelId=loginUser.getPersonnel_id();
		if(personnel_id!=null && !"".equals(personnel_id)){
			personnelId = personnel_id;
		}
		
		// 人员基本信息
		personnel=rlgl010305Service.getPersonnel(personnelId);
		reviewElement=personnel.getChange_value();
		// 获得性别
		personnel.setPersonnel_gender(mtb02AdmService.getAdmName("030", personnel.getPersonnel_gender()));
		// 获得民族
		personnel.setPersonnel_ethnic(mtb02AdmService.getAdmName("026", personnel.getPersonnel_ethnic()));
		// 获得个人身份
		personnel.setPersonnel_personal_identification(mtb02AdmService.getAdmName("031", personnel.getPersonnel_personal_identification()));
		// 获得政治面貌
		personnel.setPersonnel_political_landscape(mtb02AdmService.getAdmName("032", personnel.getPersonnel_political_landscape()));
		// 获得用工形式
		personnel.setPersonnel_employment_forms(mtb02AdmService.getAdmName("003", personnel.getPersonnel_employment_forms()));
		// 获得婚姻状况
		personnel.setPersonnel_marital_status(mtb02AdmService.getAdmName("034", personnel.getPersonnel_marital_status()));
		// 获得健康状况
		personnel.setPersonnel_health_status(mtb02AdmService.getAdmName("035", personnel.getPersonnel_health_status()));
		// 获得岗位状态
		personnel.setPersonnel_status(mtb02AdmService.getAdmName("036", personnel.getPersonnel_status()));
		// 获得年度审核判定
		personnel.setPersonnel_check(mtb02AdmService.getAdmName("054", personnel.getPersonnel_check()));
		// 获得在编状态
		personnel.setPersonnel_regular(mtb02AdmService.getAdmName("092", personnel.getPersonnel_regular()));
		
		// 获得地区
		personnel.setPersonnel_province(mtb20AreaService.getProvinceName(personnel.getPersonnel_area()));
		personnel.setPersonnel_city(mtb20AreaService.getCityName(personnel.getPersonnel_area()));
		personnel.setPersonnel_zone(mtb20AreaService.getZoneName(personnel.getPersonnel_area()));
		
		personnel39 = new Mtb39Personnel();
		personnel39.setPersonnel_id(personnelId);
		// 获得科室
		personnel.setPersonnel_office(mtb48ectionService.getEctionName(rlgl010306Service.searchUser(personnel39).getSection_id(),personnel.getPersonnel_unit()));
		// 获得单位
		personnel.setPersonnel_unit(mtb04UnitService.getUnitName(personnel.getPersonnel_unit()));
		// 岗位名称
		irin = rlgl010306Service.getPersonInfo(personnel39);
		if(irin != null){
			personnel.setPersonnel_admintype(mtb02AdmService.getAdmName("029", irin.getPost_kbn())+mtb02AdmService.getAdmName(irin.getPost_kbn(), irin.getPost_level())+irin.getPost_name());
		}
		if("".equals(personnel.getPersonnel_birthday())){
			personnel.setPersonnel_birthday(personnel.getPersonnel_card_id().substring(6,10) + 
											"-" + personnel.getPersonnel_card_id().substring(10,12) + 
											"-" + personnel.getPersonnel_card_id().substring(12,14));
		}
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
		
		// 人员基本信息-专业技术职务信息
		rlgl010302ProfessionalInfoList=rlgl010305Service.getProfessionalInfo(personnelId);
		if(rlgl010302ProfessionalInfoList !=null && rlgl010302ProfessionalInfoList.size()>0){
			for(int index=0;index<rlgl010302ProfessionalInfoList.size();index++){
				// 获得是否聘任
				rlgl010302ProfessionalInfoList.get(index).setWhether_appoint(mtb02AdmService.getAdmName("038", rlgl010302ProfessionalInfoList.get(index).getWhether_appoint()));
				// 获得是否最高职称
				rlgl010302ProfessionalInfoList.get(index).setWhether_highest(mtb02AdmService.getAdmName("039", rlgl010302ProfessionalInfoList.get(index).getWhether_highest()));
				// 获得级别
				rlgl010302ProfessionalInfoList.get(index).setOnelevel(mtb02AdmService.getAdmName("088", (rlgl010302ProfessionalInfoList.get(index).getLevel() + "       ").substring(0, 2)));
				rlgl010302ProfessionalInfoList.get(index).setTwolevel(mtb02AdmService.getAdmName("087", (rlgl010302ProfessionalInfoList.get(index).getLevel() + "       ").substring(2, 4)));
				rlgl010302ProfessionalInfoList.get(index).setThreelevel(mtb02AdmService.getAdmName("027", (rlgl010302ProfessionalInfoList.get(index).getLevel() + "       ").substring(4, 7)));
			}
		}
		
		// 人员基本信息-行政职务信息
		rlgl010302JobInfoList=rlgl010305Service.getJobInfo(personnelId);
		if(rlgl010302JobInfoList !=null && rlgl010302JobInfoList.size()>0){
			for(int index=0;index<rlgl010302JobInfoList.size();index++){
				// 获得职务类别名称
				rlgl010302JobInfoList.get(index).setType(mtb02AdmService.getAdmName("020", rlgl010302JobInfoList.get(index).getType()));
				// 获得职务级别名称
				rlgl010302JobInfoList.get(index).setLevel(mtb02AdmService.getAdmName("006", rlgl010302JobInfoList.get(index).getLevel()));
			}
		}

		// 人员基本信息-社会关系
		rlgl010302SocialInfoList=rlgl010305Service.getSocialInfo(personnelId);
		if(rlgl010302SocialInfoList !=null && rlgl010302SocialInfoList.size()>0){
			for(int index=0;index<rlgl010302SocialInfoList.size();index++){
				// 获得政治面貌名称
				rlgl010302SocialInfoList.get(index).setPolitical_landscape(mtb02AdmService.getAdmName("032", rlgl010302SocialInfoList.get(index).getPolitical_landscape()));
			}
		}

		// 人员基本信息-教育经历
		rlgl010302EduInfoList=rlgl010305Service.getEduInfo(personnelId);
		if(rlgl010302EduInfoList !=null && rlgl010302EduInfoList.size()>0){
			for(int index=0;index<rlgl010302EduInfoList.size();index++){
				// 获得学历
				rlgl010302EduInfoList.get(index).setEducational_bg(mtb02AdmService.getAdmName("040", rlgl010302EduInfoList.get(index).getEducational_bg()));
				// 获得学位
				rlgl010302EduInfoList.get(index).setDegree(mtb02AdmService.getAdmName("090", rlgl010302EduInfoList.get(index).getDegree()));
				// 获得学习形式
				rlgl010302EduInfoList.get(index).setLearning_format(mtb02AdmService.getAdmName("100", rlgl010302EduInfoList.get(index).getLearning_format()));
				// 获得院校类型
				rlgl010302EduInfoList.get(index).setCollege_type(mtb02AdmService.getAdmName("101", rlgl010302EduInfoList.get(index).getCollege_type()));
			}
		}
		
		// 人员基本信息-工作经历
		rlgl010302WorkInfoList=rlgl010305Service.getWorkInfo(personnelId);
		// 人员基本信息-党派信息
		rlgl010302PartisanInfoList=rlgl010305Service.getPartisanInfo(personnelId);
		if(rlgl010302PartisanInfoList !=null && rlgl010302PartisanInfoList.size()>0){
			for(int index=0;index<rlgl010302PartisanInfoList.size();index++){
				// 获得党派名称
				rlgl010302PartisanInfoList.get(index).setPartisan_nm(mtb02AdmService.getAdmName("102", rlgl010302PartisanInfoList.get(index).getPartisan_nm()));
				// 获得正式、预备名称
				rlgl010302PartisanInfoList.get(index).setPrep_or_officially(mtb02AdmService.getAdmName("103", rlgl010302PartisanInfoList.get(index).getPrep_or_officially()));
			}
		}
		
		// 人员基本信息-资格信息
		rlgl010302PractitionersInfoList=rlgl010305Service.getPractitionersInfo(personnelId);
		if(rlgl010302PractitionersInfoList !=null && rlgl010302PractitionersInfoList.size()>0){
			for(int index=0;index<rlgl010302PractitionersInfoList.size();index++){
				// 获得执业级别(医师资格可见)
				if("183".equals(rlgl010302PractitionersInfoList.get(index).getType())){
					rlgl010302PractitionersInfoList.get(index).setLevel(mtb02AdmService.getAdmName("009", rlgl010302PractitionersInfoList.get(index).getLevel()));	
				}else{
					rlgl010302PractitionersInfoList.get(index).setLevel("");
				}
				// 获得专业类别
				rlgl010302PractitionersInfoList.get(index).setType(mtb02AdmService.getAdmName("008", rlgl010302PractitionersInfoList.get(index).getType()));
		
			}
		}
		
		// 人员基本信息-执业信息
		rlgl010302PracticeInfoList=rlgl010305Service.getPracticeInfo(personnelId);
		if(rlgl010302PracticeInfoList !=null && rlgl010302PracticeInfoList.size()>0){
			for(int index=0;index<rlgl010302PracticeInfoList.size();index++){
				// 获得执业级别(医师资格可见)
				if("183".equals(rlgl010302PracticeInfoList.get(index).getType())){
					rlgl010302PracticeInfoList.get(index).setLevel(mtb02AdmService.getAdmName("009", rlgl010302PracticeInfoList.get(index).getLevel()));	
				}else{
					rlgl010302PracticeInfoList.get(index).setLevel("");
				}
				// 获得专业类别
				rlgl010302PracticeInfoList.get(index).setType(mtb02AdmService.getAdmName("008", rlgl010302PracticeInfoList.get(index).getType()));

				// 获得执业范围
				rlgl010302PracticeInfoList.get(index).setArea1(mtb02AdmService.getAdmName("134", rlgl010302PracticeInfoList.get(index).getArea1()));
				rlgl010302PracticeInfoList.get(index).setArea2(mtb02AdmService.getAdmName("134", rlgl010302PracticeInfoList.get(index).getArea2()));
				// 获得执业类别
				rlgl010302PracticeInfoList.get(index).setPro_type(mtb02AdmService.getAdmName("121", rlgl010302PracticeInfoList.get(index).getPro_type()));
			}
		}
		
		// 人员基本信息-导师信息
		rlgl010302TutorInfoList=rlgl010305Service.getTutorInfo(personnelId);
		if(rlgl010302TutorInfoList !=null && rlgl010302TutorInfoList.size()>0){
			for(int index=0;index<rlgl010302TutorInfoList.size();index++){
				// 获得导师类别
				rlgl010302TutorInfoList.get(index).setTeachertype(mtb02AdmService.getAdmName("099", rlgl010302TutorInfoList.get(index).getTeachertype()));
			}
		}

		return SUCCESS;
	}
	

	public Mtb12Personnel getPersonnel() {
		return personnel;
	}
	public void setPersonnel(Mtb12Personnel personnel) {
		this.personnel = personnel;
	}
	public IMTb20AreaService getMtb20AreaService() {
		return mtb20AreaService;
	}
	public void setMtb20AreaService(IMTb20AreaService mtb20AreaService) {
		this.mtb20AreaService = mtb20AreaService;
	}
	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}
	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
	}

	public List<Mtb18PersonnelProfessionalInfo> getRlgl010302ProfessionalInfoList() {
		return rlgl010302ProfessionalInfoList;
	}

	public void setRlgl010302ProfessionalInfoList(
			List<Mtb18PersonnelProfessionalInfo> rlgl010302ProfessionalInfoList) {
		this.rlgl010302ProfessionalInfoList = rlgl010302ProfessionalInfoList;
	}

	public List<Mtb17PersonnelJobInfo> getRlgl010302JobInfoList() {
		return rlgl010302JobInfoList;
	}

	public void setRlgl010302JobInfoList(
			List<Mtb17PersonnelJobInfo> rlgl010302JobInfoList) {
		this.rlgl010302JobInfoList = rlgl010302JobInfoList;
	}

	public List<Mtb16PersonnelSocialInfo> getRlgl010302SocialInfoList() {
		return rlgl010302SocialInfoList;
	}

	public void setRlgl010302SocialInfoList(
			List<Mtb16PersonnelSocialInfo> rlgl010302SocialInfoList) {
		this.rlgl010302SocialInfoList = rlgl010302SocialInfoList;
	}

	public List<Mtb15PersonnelEduInfo> getRlgl010302EduInfoList() {
		return rlgl010302EduInfoList;
	}

	public void setRlgl010302EduInfoList(
			List<Mtb15PersonnelEduInfo> rlgl010302EduInfoList) {
		this.rlgl010302EduInfoList = rlgl010302EduInfoList;
	}

	public List<Mtb14PersonnelWorkInfo> getRlgl010302WorkInfoList() {
		return rlgl010302WorkInfoList;
	}

	public void setRlgl010302WorkInfoList(
			List<Mtb14PersonnelWorkInfo> rlgl010302WorkInfoList) {
		this.rlgl010302WorkInfoList = rlgl010302WorkInfoList;
	}

	public List<Mtb13PersonnelPartisanInfo> getRlgl010302PartisanInfoList() {
		return rlgl010302PartisanInfoList;
	}

	public void setRlgl010302PartisanInfoList(
			List<Mtb13PersonnelPartisanInfo> rlgl010302PartisanInfoList) {
		this.rlgl010302PartisanInfoList = rlgl010302PartisanInfoList;
	}

	public List<Mtb19PersonnelPractitionersInfo> getRlgl010302PractitionersInfoList() {
		return rlgl010302PractitionersInfoList;
	}

	public void setRlgl010302PractitionersInfoList(
			List<Mtb19PersonnelPractitionersInfo> rlgl010302PractitionersInfoList) {
		this.rlgl010302PractitionersInfoList = rlgl010302PractitionersInfoList;
	}

	public IRlgl010305Service getRlgl010305Service() {
		return rlgl010305Service;
	}

	public void setRlgl010305Service(IRlgl010305Service rlgl010305Service) {
		this.rlgl010305Service = rlgl010305Service;
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
	
	public String getPersonnel_id() {
		return personnel_id;
	}

	public void setPersonnel_id(String personnel_id) {
		this.personnel_id = personnel_id;
	}

	public List<Mtb58PersonnelTutorInfo> getRlgl010302TutorInfoList() {
		return rlgl010302TutorInfoList;
	}

	public void setRlgl010302TutorInfoList(
			List<Mtb58PersonnelTutorInfo> rlgl010302TutorInfoList) {
		this.rlgl010302TutorInfoList = rlgl010302TutorInfoList;
	}

	public String getReviewElement() {
		return reviewElement;
	}

	public void setReviewElement(String reviewElement) {
		this.reviewElement = reviewElement;
	}

	public Rlgl010303Bean getPersonnelBean() {
		return personnelBean;
	}

	public void setPersonnelBean(Rlgl010303Bean personnelBean) {
		this.personnelBean = personnelBean;
	}

	public String getBackAction() {
		return backAction;
	}

	public void setBackAction(String backAction) {
		this.backAction = backAction;
	}

	public IRlgl010306Service getRlgl010306Service() {
		return rlgl010306Service;
	}

	public void setRlgl010306Service(IRlgl010306Service rlgl010306Service) {
		this.rlgl010306Service = rlgl010306Service;
	}

	public Mtb39Personnel getPersonnel39() {
		return personnel39;
	}

	public void setPersonnel39(Mtb39Personnel personnel39) {
		this.personnel39 = personnel39;
	}


	public Mtb09Irin getIrin() {
		return irin;
	}


	public void setIrin(Mtb09Irin irin) {
		this.irin = irin;
	}


	public String getUpdateAction() {
		return updateAction;
	}


	public void setUpdateAction(String updateAction) {
		this.updateAction = updateAction;
	}


	public List<Mtb77PersonnelPracticeInfo> getRlgl010302PracticeInfoList() {
		return rlgl010302PracticeInfoList;
	}


	public void setRlgl010302PracticeInfoList(
			List<Mtb77PersonnelPracticeInfo> rlgl010302PracticeInfoList) {
		this.rlgl010302PracticeInfoList = rlgl010302PracticeInfoList;
	}
	
}
