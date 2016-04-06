package com.rlglsys.action.hiringmanage.personmanage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl011007Bean;
import com.rlglsys.bean.Rlgl011008Bean;
import com.rlglsys.entity.*;
import com.rlglsys.service.IApprovalProcessService;
import com.rlglsys.service.IMTb04UnitService;
import com.rlglsys.service.IMTb20AreaService;
import com.rlglsys.service.IMtb48ectionService;
import com.rlglsys.service.IAutoGetNumService;
import com.rlglsys.service.IRlgl010106Service;
import com.rlglsys.service.IRlgl010306Service;
import com.rlglsys.service.IRlgl010309Service;
import com.rlglsys.util.Common;
import com.rlglsys.util.Constant;
import com.rlglsys.util.DateTimeManager;
import com.rlglsys.util.uploadPhotos;

public class Rlgl010306DangpaiAddAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IRlgl010306Service rlgl010306Service;
	private IMTb04UnitService mtb04UnitService;
	private IMtb48ectionService  mtb48ectionService; 
	private Mtb31ApprovalProcess processGetInfo;
	private IApprovalProcessService approvalProcessService;
	private IRlgl010309Service rlgl010309Service;
	private IRlgl010106Service rlgl010106Service;
	private IMTb20AreaService  mtb20AreaService;  
	private Mtb04Unit unitinfo;
	private IAutoGetNumService noSerVice;
	private File myImgFile;
	private String imgFileFileName;
	private String imgFileHidden;
	private String imgFileContentType;
	private String saveAction;
	// 人员基本信息
	private String personnel_id;
	private String personnelId;
	private String change_value;
	private Mtb39Personnel personnel;
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
	// 人员基本信息-资格信息
	private List<Mtb46PersonnelPractitionersInfo> rlgl010306PractitionersInfoList;
	// 人员基本信息-执业信息
	private List<Mtb78PersonnelPracticeInfo> rlgl010306PracticeInfoList;
	// 人员基本信息-导师信息
	private List<Mtb59PersonnelTutorInfo> rlgl010306TutorInfoList;
	
	@Override
	protected String doExecute() throws Exception{
		Mtb01User loginUser = (Mtb01User)super.getSession(Constant.SESSION_KEY_LOGINUSER);
		InputStream is = null;
		OutputStream os = null;
		String apply_no ="";
		Mtb29PersonalApply tb29Info= null;
		TTb01AutoGetNum noInfo = null;
		uploadPhotos  upImg = null; 
		try{
			//得到工程保存图片的路径
			if(!"".equals(personnel.getPersonnel_imgname())){
				upImg  = new uploadPhotos();
				boolean copy=upImg.copyFile(ServletActionContext.getRequest().getRealPath(File.separator+"temporary")+File.separator+personnel.getPersonnel_imgname(),ServletActionContext.getRequest().getRealPath(File.separator+"upload")+File.separator+personnel.getPersonnel_imgname());
		    	if(copy){
		    		upImg.delFile(ServletActionContext.getRequest().getRealPath(File.separator+"temporary")+File.separator+personnel.getPersonnel_imgname());
		    	}	
		    	//personnel.setPersonnel_imgname(imgFileHidden);
			}

			// 单位信息取得
	        unitinfo = rlgl010106Service.getUnitInfo(loginUser.getUnit_no());
	        // 保存并提交审核时才登录到个人人事申请表
 			if(unitinfo != null && "0".equals(saveAction)) {
				// 审核流程编号取得
		        Mtb31ApprovalProcess processInfo = new Mtb31ApprovalProcess();
		        // 申请事项
		        processInfo.setApply_kbn("013");
		        // 单位No
		        String unitNo = unitinfo.getUnit_no();
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
		        String endMark = "";
		        processGetInfo = approvalProcessService.getProcessInfo(processInfo);
		        if (processGetInfo != null) {
		            // 终审节点
		            endMark = processGetInfo.getEnd_mark();
		        }
				tb29Info = new Mtb29PersonalApply();
				noInfo = new TTb01AutoGetNum();
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
		        tb29Info.setApply_kbn("013");
		        // 申请人ID
		        System.out.println(personnel.getPersonnel_card_id()+"====306ZigeAdd===="+personnel.getPersonnel_id());
		        tb29Info.setPersonal_id(personnel.getPersonnel_card_id());
		        // 申请人姓名
		        tb29Info.setPersonal_nm(personnel.getPersonnel_nm());
		        // 申请人单位NO
		        tb29Info.setPersonal_unitno(personnel.getPersonnel_unit());
		        // 提交人
		        tb29Info.setCheck_user(loginUser.getUser_id());
		        // 提交人单位NO
		        tb29Info.setCheck_unitno(loginUser.getUnit_no());
		        // 申请时间
		        String nowdate = DateTimeManager.getSystemDate14();
		        String now = nowdate.substring(0, 8);
		        tb29Info.setApply_date(now);
		        // 审核流程编号
		        tb29Info.setEnd_mark(endMark);
		        // 当前节点
		        tb29Info.setNow_mark("00");
		        // 待审核单位NO
		        tb29Info.setWill_checkunitno(loginUser.getUnit_no());
			}
	        
			//人员基本信息
 			personnelId=personnel.getPersonnel_id();
			// 申请编号
			personnel.setApply_no(apply_no);
	        // 申请连番
			personnel.setApply_number("001");
			// 当前节点
			personnel.setNow_mark("00");
			personnel.setPersonnel_id(personnel.getPersonnel_card_id());
			personnel.setPersonnel_area(personnel.getPersonnel_province()+personnel.getPersonnel_city()+personnel.getPersonnel_zone());
			//personnel.setLogin_user_id(loginUser.getLogin_user_id());
			//personnel.setLogin_date(loginUser.getLogin_date());
			//personnel.setUpdate_user_id(loginUser.getUpdate_user_id());
			//personnel.setUpdate_date(loginUser.getUpdate_date());
			//yc2013-10-07 修改登录或更新日期
			super.setDBCommonColOnInsert(personnel);
			// 变更字段
			personnel.setChange_value(change_value);
			// 提交审核判定
			if("1".equals(saveAction)) {
				personnel.setPersonnel_isapproval("000");
			}else{
				personnel.setPersonnel_isapproval("001");
			}
			personnel.setPersonnel_unit_nm(mtb04UnitService.getUnitName(personnel.getPersonnel_unit()));
			personnel.setPersonnel_office_nm(mtb48ectionService.getEctionName(personnel.getPersonnel_office(),personnel.getPersonnel_unit()));
			// 获得地区
			personnel.setPersonnel_area("" + personnel.getPersonnel_province()+personnel.getPersonnel_city()+personnel.getPersonnel_zone());
			personnel.setPersonnel_area(personnel.getPersonnel_area().replace("-1", "").trim());
			
            
			//下面的操作是为了去掉list中的空数据
			//资格信息list
			if(rlgl010306PractitionersInfoList != null){
				for(int i=0;i<rlgl010306PractitionersInfoList.size();i++){
					Mtb46PersonnelPractitionersInfo mtb46 = new Mtb46PersonnelPractitionersInfo();
					mtb46 = rlgl010306PractitionersInfoList.get(i);
					super.setDBCommonColOnInsert(mtb46);
					if("".equals(mtb46.getCertificate_no())){
						rlgl010306PractitionersInfoList.remove(i);
					}
				}
			}
			
			// 执业信息list
			if(rlgl010306PracticeInfoList != null){
				for(int k=0;k<rlgl010306PracticeInfoList.size();k++){
					Mtb78PersonnelPracticeInfo mtb78 = new Mtb78PersonnelPracticeInfo();
					mtb78 = rlgl010306PracticeInfoList.get(k);
					super.setDBCommonColOnInsert(mtb78);
					if("".equals(mtb78.getCertificate_no())){
						rlgl010306PracticeInfoList.remove(k);
					}
				}
			}
			
			
			//专业技术职务信息list
			if(rlgl010306ProfessionalInfoList != null){
				for(int j=rlgl010306ProfessionalInfoList.size()-1;j>=0;j--){
					Mtb45PersonnelProfessionalInfo mtb45 = new Mtb45PersonnelProfessionalInfo();
					mtb45 = rlgl010306ProfessionalInfoList.get(j);
					super.setDBCommonColOnInsert(mtb45);
					if("".equals(mtb45.getOnelevel())){
						rlgl010306ProfessionalInfoList.remove(j);
					}
				}
			}
			
			// 行政职务信息list
			if(rlgl010306JobInfoList != null){
				for(int a=rlgl010306JobInfoList.size()-1;a>=0;a--){
					Mtb44PersonnelJobInfo mtb44 = new Mtb44PersonnelJobInfo();
					mtb44 = rlgl010306JobInfoList.get(a);
					super.setDBCommonColOnInsert(mtb44);
					if("".equals(mtb44.getPosition_nm())){
						rlgl010306JobInfoList.remove(a);
					}
				}
			}
			
			// 社会关系list
			if(rlgl010306SocialInfoList != null){
				for(int b=rlgl010306SocialInfoList.size()-1;b>=0;b--){
					Mtb43PersonnelSocialInfo mtb43 =  new Mtb43PersonnelSocialInfo();
					mtb43 = rlgl010306SocialInfoList.get(b);
					super.setDBCommonColOnInsert(mtb43);
					if("".equals(mtb43.getRelationship())){
						rlgl010306SocialInfoList.remove(b);
					}
				}
			}
			
			// 教育经历list
			if(rlgl010306EduInfoList != null){
				for(int c=rlgl010306EduInfoList.size()-1;c>=0;c--){
					Mtb42PersonnelEduInfo mtb42 = new Mtb42PersonnelEduInfo();
					mtb42 = rlgl010306EduInfoList.get(c);
					super.setDBCommonColOnInsert(mtb42);
					if("".equals(mtb42.getLearning_format())){
						rlgl010306EduInfoList.remove(c);
					}
				}
			}
			
			// 工作经历
			if(rlgl010306WorkInfoList != null){
				for(int d=rlgl010306WorkInfoList.size()-1;d>=0;d--){
					Mtb41PersonnelWorkInfo mtb41 = new Mtb41PersonnelWorkInfo();
					mtb41 = rlgl010306WorkInfoList.get(d);
					super.setDBCommonColOnInsert(mtb41);
					if("".equals(mtb41.getStarttime())){
						rlgl010306WorkInfoList.remove(d);
					}
				}
			}
			
			// 党派信息
			if(rlgl010306PartisanInfoList != null){
				for(int m=rlgl010306PartisanInfoList.size()-1;m>=0;m--){
					Mtb40PersonnelPartisanInfo mtb40 = new Mtb40PersonnelPartisanInfo();
					mtb40 = rlgl010306PartisanInfoList.get(m);
					super.setDBCommonColOnInsert(mtb40);
					if("".equals(mtb40.getJointime())){
						rlgl010306PartisanInfoList.remove(m);
					}
				}
			}
			
			// 导师信息
			if(rlgl010306TutorInfoList != null){
				for(int f=0;f<rlgl010306TutorInfoList.size();f++){
					Mtb59PersonnelTutorInfo mtb59 = new Mtb59PersonnelTutorInfo();
					mtb59 = rlgl010306TutorInfoList.get(f);
					super.setDBCommonColOnInsert(mtb59);
					if("".equals(mtb59.getTeachertype())){
						rlgl010306TutorInfoList.remove(f);
					}
				}
			}
			
			
			// 编辑修改字段并保存
			Common common = new Common();
			Rlgl011008Bean rlgl011008Bean = common.editRlgl011008Bean(change_value);
			rlgl011008Bean.setPersonnel_id(personnel.getPersonnel_id());
			rlgl011008Bean.setAdmtb_updatetime(DateTimeManager.getSystemDate14());
			super.setDBCommonColOnInsert(rlgl011008Bean);
			
			// 事务更新
			rlgl010306Service.updateRlgl010306(personnel
											  ,rlgl010306ProfessionalInfoList
											  ,rlgl010306JobInfoList
											  ,rlgl010306SocialInfoList
											  ,rlgl010306EduInfoList
											  ,rlgl010306WorkInfoList
											  ,rlgl010306PartisanInfoList
											  ,rlgl010306PractitionersInfoList
											  ,rlgl010306PracticeInfoList
											  ,rlgl010306TutorInfoList
											  ,loginUser
											  ,tb29Info
											  ,noInfo
											  ,rlgl011008Bean);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw e;
				}
			}
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw e;
				}
			}
            
		}
		return SUCCESS;
	}
	public IRlgl010306Service getRlgl010306Service() {
		return rlgl010306Service;
	}

	public void setRlgl010306Service(IRlgl010306Service rlgl010306Service) {
		this.rlgl010306Service = rlgl010306Service;
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


	public File getMyImgFile() {
		return myImgFile;
	}

	public void setMyImgFile(File myImgFile) {
		this.myImgFile = myImgFile;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}

	public String getImgFileContentType() {
		return imgFileContentType;
	}

	public void setImgFileContentType(String imgFileContentType) {
		this.imgFileContentType = imgFileContentType;
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

	public IAutoGetNumService getNoSerVice() {
		return noSerVice;
	}

	public void setNoSerVice(IAutoGetNumService noSerVice) {
		this.noSerVice = noSerVice;
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

	public IMTb20AreaService getMtb20AreaService() {
		return mtb20AreaService;
	}

	public void setMtb20AreaService(IMTb20AreaService mtb20AreaService) {
		this.mtb20AreaService = mtb20AreaService;
	}

	public String getSaveAction() {
		return saveAction;
	}

	public void setSaveAction(String saveAction) {
		this.saveAction = saveAction;
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
	public String getImgFileHidden() {
		return imgFileHidden;
	}
	public void setImgFileHidden(String imgFileHidden) {
		this.imgFileHidden = imgFileHidden;
	}
	public List<Mtb78PersonnelPracticeInfo> getRlgl010306PracticeInfoList() {
		return rlgl010306PracticeInfoList;
	}
	public void setRlgl010306PracticeInfoList(
			List<Mtb78PersonnelPracticeInfo> rlgl010306PracticeInfoList) {
		this.rlgl010306PracticeInfoList = rlgl010306PracticeInfoList;
	}
	public String getPersonnelId() {
		return personnelId;
	}
	public void setPersonnelId(String personnelId) {
		this.personnelId = personnelId;
	}


	
}
