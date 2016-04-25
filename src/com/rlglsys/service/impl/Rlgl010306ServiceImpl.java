package com.rlglsys.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.rlglsys.bean.Rlgl011008Bean;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb09Irin;
import com.rlglsys.entity.Mtb29PersonalApply;
import com.rlglsys.entity.Mtb39Personnel;
import com.rlglsys.entity.Mtb40PersonnelPartisanInfo;
import com.rlglsys.entity.Mtb41PersonnelWorkInfo;
import com.rlglsys.entity.Mtb42PersonnelEduInfo;
import com.rlglsys.entity.Mtb43PersonnelSocialInfo;
import com.rlglsys.entity.Mtb44PersonnelJobInfo;
import com.rlglsys.entity.Mtb45PersonnelProfessionalInfo;
import com.rlglsys.entity.Mtb46PersonnelPractitionersInfo;
import com.rlglsys.entity.Mtb59PersonnelTutorInfo;
import com.rlglsys.entity.Mtb78PersonnelPracticeInfo;
import com.rlglsys.entity.TTb01AutoGetNum;
import com.rlglsys.mapper.IRlgl010306Mapper;
import com.rlglsys.service.IAutoGetNumService;
import com.rlglsys.service.IRlgl010306Service;
import com.rlglsys.service.IRlgl010309Service;
import com.rlglsys.util.DateTimeManager;

public class Rlgl010306ServiceImpl implements IRlgl010306Service {
	private IRlgl010306Mapper rlgl010306Mapper;
	private IRlgl010309Service rlgl010309Service;
	private IAutoGetNumService noSerVice;

	public IRlgl010306Mapper getRlgl010306Mapper() {
		return rlgl010306Mapper;
	}

	public void setRlgl010306Mapper(IRlgl010306Mapper rlgl010306Mapper) {
		this.rlgl010306Mapper = rlgl010306Mapper;
	}

	/**
	 * 人员基本信息事务添加
	 */
	@Override
	@org.springframework.transaction.annotation.Transactional(rollbackFor = Exception.class)
	public int updateRlgl010306(Mtb39Personnel mtb39Personnel
			, List<Mtb45PersonnelProfessionalInfo> rlgl010306ProfessionalInfoList
			, List<Mtb44PersonnelJobInfo> rlgl010306JobInfoList
			, List<Mtb43PersonnelSocialInfo> rlgl010306SocialInfoList
			, List<Mtb42PersonnelEduInfo> rlgl010306EduInfoList
			, List<Mtb41PersonnelWorkInfo> rlgl010306WorkInfoList
			, List<Mtb40PersonnelPartisanInfo> rlgl010306PartisanInfoList
			, List<Mtb46PersonnelPractitionersInfo> rlgl010306PractitionersInfoList
			, List<Mtb78PersonnelPracticeInfo> rlgl010306PracticeInfoList
			, List<Mtb59PersonnelTutorInfo> rlgl010306TutorInfoList
			, Mtb01User loginUser, Mtb29PersonalApply tb29Info
			, TTb01AutoGetNum noInfo
			, Rlgl011008Bean rlgl011008Bean) throws Exception {
		// TODO Auto-generated method stub
		try {
			// 申请用户表删除
			int count1 = 0;
			rlgl010306Mapper.deleteRlgl010306(mtb39Personnel);
			// 申请用户表登录
			System.out.println("=====changeValue====="+mtb39Personnel.getChange_value());
			count1 = rlgl010306Mapper.doSaveRlgl010306(mtb39Personnel);
			if (count1 == 0) {
				throw new Exception();
			}
			// 人员基本信息-专业技术职务信息
			int count2 = 0;
			if(rlgl010306ProfessionalInfoList != null){
				deleteRlgl010306ProfessionalInfo(mtb39Personnel);
				for (int rowIndex = 0; rowIndex < rlgl010306ProfessionalInfoList.size(); rowIndex++) {
					rlgl010306ProfessionalInfoList.get(rowIndex).setPersonnel_id(mtb39Personnel.getPersonnel_card_id());
					rlgl010306ProfessionalInfoList.get(rowIndex).setLevel(rlgl010306ProfessionalInfoList.get(rowIndex).getOnelevel().trim() + rlgl010306ProfessionalInfoList.get(rowIndex).getTwolevel().trim() + rlgl010306ProfessionalInfoList.get(rowIndex).getThreelevel().trim());
					// 2013-10-07（yc）修改信息变更人和变更时间
					// rlgl010306ProfessionalInfoList.get(rowIndex).setLogin_user_id(loginUser.getLogin_user_id());
					// rlgl010306ProfessionalInfoList.get(rowIndex).setLogin_date(DateTimeManager.getSystemDate14());
					rlgl010306ProfessionalInfoList.get(rowIndex).setUpdate_user_id(loginUser.getUpdate_user_id());
					rlgl010306ProfessionalInfoList.get(rowIndex).setUpdate_date(DateTimeManager.getSystemDate14());
					count2 = saveRlgl010306ProfessionalInfo(rlgl010306ProfessionalInfoList.get(rowIndex));
					if (count2 == 0) {
						throw new Exception();
					}
				}
			}
			
			// 人员基本信息-行政职务信息
			int count3 = 0;
			
			if(rlgl010306JobInfoList != null){
				deleteRlgl010306JobInfo(mtb39Personnel);
				for (int rowIndex = 0; rowIndex < rlgl010306JobInfoList.size(); rowIndex++) {
					rlgl010306JobInfoList.get(rowIndex).setPersonnel_id(mtb39Personnel.getPersonnel_card_id());
					// 2013-10-07（yc）修改信息变更人和变更时间
					// rlgl010306JobInfoList.get(rowIndex).setLogin_user_id(loginUser.getLogin_user_id());
					// rlgl010306JobInfoList.get(rowIndex).setLogin_date(loginUser.getLogin_date());
					rlgl010306JobInfoList.get(rowIndex).setUpdate_user_id(loginUser.getUpdate_user_id());
					rlgl010306JobInfoList.get(rowIndex).setUpdate_date(DateTimeManager.getSystemDate14());
					count3 = saveRlgl010306JobInfo(rlgl010306JobInfoList.get(rowIndex));
					if (count3 == 0) {
						throw new Exception();
					}
				}
			}
			
			// 人员基本信息-社会关系
			int count4 = 0;
			
			if(rlgl010306SocialInfoList != null){
				deleteRlgl010306SocialInfo(mtb39Personnel);
				for (int rowIndex = 0; rowIndex < rlgl010306SocialInfoList.size(); rowIndex++) {
					rlgl010306SocialInfoList.get(rowIndex).setPersonnel_id(mtb39Personnel.getPersonnel_card_id());
					// 2013-10-07（yc）修改信息变更人和变更时间
					// rlgl010306SocialInfoList.get(rowIndex).setLogin_user_id(loginUser.getLogin_user_id());
					// rlgl010306SocialInfoList.get(rowIndex).setLogin_date(loginUser.getLogin_date());
					rlgl010306SocialInfoList.get(rowIndex).setUpdate_user_id(loginUser.getUpdate_user_id());
					rlgl010306SocialInfoList.get(rowIndex).setUpdate_date(DateTimeManager.getSystemDate14());
					count4 = saveRlgl010306SocialInfo(rlgl010306SocialInfoList.get(rowIndex));
					if (count4 == 0) {
						throw new Exception();
					}
				}
			}
			
			// 人员基本信息-教育经历
			int count5 = 0;
			if(rlgl010306EduInfoList != null){
				deleteRlgl010306EduInfo(mtb39Personnel);
				for (int rowIndex = 0; rowIndex < rlgl010306EduInfoList.size(); rowIndex++) {
					rlgl010306EduInfoList.get(rowIndex).setPersonnel_id(mtb39Personnel.getPersonnel_card_id());
					// 2013-10-07（yc）修改信息变更人和变更时间
					// rlgl010306EduInfoList.get(rowIndex).setLogin_user_id(loginUser.getLogin_user_id());
					// rlgl010306EduInfoList.get(rowIndex).setLogin_date(loginUser.getLogin_date());
					rlgl010306EduInfoList.get(rowIndex).setUpdate_user_id(loginUser.getUpdate_user_id());
					rlgl010306EduInfoList.get(rowIndex).setUpdate_date(DateTimeManager.getSystemDate14());
					count5 = saveRlgl010306EduInfo(rlgl010306EduInfoList.get(rowIndex));
					if (count5 == 0) {
						throw new Exception();
					}
				}
			}
			
			// 人员基本信息-工作经历
			int count6 = 0;
			if(rlgl010306WorkInfoList != null){
				deleteRlgl010306WorkInfo(mtb39Personnel);
				for (int rowIndex = 0; rowIndex < rlgl010306WorkInfoList.size(); rowIndex++) {
					rlgl010306WorkInfoList.get(rowIndex).setPersonnel_id(mtb39Personnel.getPersonnel_card_id());
					// 2013-10-07（yc）修改信息变更人和变更时间
					// rlgl010306WorkInfoList.get(rowIndex).setLogin_user_id(loginUser.getLogin_user_id());
					// rlgl010306WorkInfoList.get(rowIndex).setLogin_date(loginUser.getLogin_date());
					if(!StringUtils.isBlank(loginUser.getLogin_date())){
					rlgl010306WorkInfoList.get(rowIndex).setUpdate_user_id(loginUser.getUpdate_user_id());
					rlgl010306WorkInfoList.get(rowIndex).setUpdate_date(DateTimeManager.getSystemDate14());
					}
					count6 = saveRlgl010306WorkInfo(rlgl010306WorkInfoList.get(rowIndex));
					if (count6 == 0) {
						throw new Exception();
					}
				}
			}
			
			// 人员基本信息-党派信息
			int count7 = 0;
			if(rlgl010306PartisanInfoList != null){
				deleteRlgl010306PartisanInfo(mtb39Personnel);
				for (int rowIndex = 0; rowIndex < rlgl010306PartisanInfoList.size(); rowIndex++) {
					rlgl010306PartisanInfoList.get(rowIndex).setPersonnel_id(mtb39Personnel.getPersonnel_card_id());
					// 2013-10-07（yc）修改信息变更人和变更时间
					// rlgl010306PartisanInfoList.get(rowIndex).setLogin_user_id(loginUser.getLogin_user_id());
					// rlgl010306PartisanInfoList.get(rowIndex).setLogin_date(loginUser.getLogin_date());
					if(!StringUtils.isBlank(loginUser.getLogin_date())){
					rlgl010306PartisanInfoList.get(rowIndex).setUpdate_user_id(loginUser.getUpdate_user_id());
					rlgl010306PartisanInfoList.get(rowIndex).setUpdate_date(DateTimeManager.getSystemDate14());
					}
					count7 = saveRlgl010306PartisanInfo(rlgl010306PartisanInfoList.get(rowIndex));
					if (count7 == 0) {
						throw new Exception();
					}
				}
			}
			
			// 人员基本信息-资格信息
			int count8 = 0;
			if(rlgl010306PractitionersInfoList != null){
				deleteRlgl010306PractitionersInfo(mtb39Personnel);
				for (int rowIndex = 0; rowIndex < rlgl010306PractitionersInfoList.size(); rowIndex++) {
					rlgl010306PractitionersInfoList.get(rowIndex).setPersonnel_id(mtb39Personnel.getPersonnel_card_id());
					if(!StringUtils.isBlank(loginUser.getLogin_date())){
					rlgl010306PractitionersInfoList.get(rowIndex).setLogin_user_id(loginUser.getLogin_user_id());
					rlgl010306PractitionersInfoList.get(rowIndex).setLogin_date(loginUser.getLogin_date());
					rlgl010306PractitionersInfoList.get(rowIndex).setUpdate_user_id(loginUser.getUpdate_user_id());
					rlgl010306PractitionersInfoList.get(rowIndex).setUpdate_date(loginUser.getUpdate_date());
					}
					count8 = saveRlgl010306PractitionersInfo(rlgl010306PractitionersInfoList.get(rowIndex));
					if (count8 == 0) {
						throw new Exception();
					}
				}
			}
			
			// 人员基本信息-执业信息
			int count12 = 0;
			if(rlgl010306PracticeInfoList != null){
				deleteRlgl010306PracticeInfo(mtb39Personnel);
				for (int rowIndex = 0; rowIndex < rlgl010306PracticeInfoList.size(); rowIndex++) {
					rlgl010306PracticeInfoList.get(rowIndex).setPersonnel_id(mtb39Personnel.getPersonnel_card_id());
					
					if(!StringUtils.isBlank(loginUser.getLogin_date())){
					rlgl010306PracticeInfoList.get(rowIndex).setLogin_user_id(loginUser.getLogin_user_id());
					rlgl010306PracticeInfoList.get(rowIndex).setLogin_date(loginUser.getLogin_date());
					rlgl010306PracticeInfoList.get(rowIndex).setUpdate_user_id(loginUser.getUpdate_user_id());
					rlgl010306PracticeInfoList.get(rowIndex).setUpdate_date(loginUser.getUpdate_date());
					}
					count12 = saveRlgl010306PracticeInfo(rlgl010306PracticeInfoList.get(rowIndex));
					if (count12 == 0) {
						throw new Exception();
					}
				}
			}
			
			// 人员基本信息-导师信息
			int count9 = 0;
			if(rlgl010306TutorInfoList != null){
				deleteRlgl010306TutorInfo(mtb39Personnel);
				for (int rowIndex = 0; rowIndex < rlgl010306TutorInfoList.size(); rowIndex++) {
					rlgl010306TutorInfoList.get(rowIndex).setPersonnel_id(mtb39Personnel.getPersonnel_card_id());
					if(!StringUtils.isBlank(loginUser.getLogin_date())){
					rlgl010306TutorInfoList.get(rowIndex).setLogin_user_id(loginUser.getLogin_user_id());
					rlgl010306TutorInfoList.get(rowIndex).setLogin_date(loginUser.getLogin_date());
					rlgl010306TutorInfoList.get(rowIndex).setUpdate_user_id(loginUser.getUpdate_user_id());
					rlgl010306TutorInfoList.get(rowIndex).setUpdate_date(loginUser.getUpdate_date());
					}
					count9 = saveRlgl010306TutorInfo(rlgl010306TutorInfoList.get(rowIndex));
					if (count9 == 0) {
						throw new Exception();
					}
				}
			}
			

			int count10 = 0;
			if (tb29Info != null) {
				// 登录个人人事申请表
				count10 = rlgl010309Service.insertTb29Info(tb29Info);
				if (count10 == 0) {
					throw new Exception();
				}
			}
			int count11 = 0;
			if (noInfo != null) {
				// 变更申请编号
				count11 = noSerVice.updateNoInfo(noInfo);
				if (count11 == 0) {
					throw new Exception();
				}
			}
			int count13 = 0;
			if (rlgl011008Bean != null) {
				// 变更申请编号
				count13 = saveChangePersonForMtb113Personnelupdate(rlgl011008Bean);
				if (count13 == 0) {
					throw new Exception();
				}
			}
			// 返回操作件数
			return count1 + count2 + count3 + count4 + count5 + count6 + count7 + count8 + count9 + count10 + count11 + count12 + count13;

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * 人员基本信息
	 */
	@Override
	public int saveRlgl010306(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.doSaveRlgl010306(mtb39Personnel);
	}

	/**
	 * 人员基本信息-专业技术职务信息
	 */
	@Override
	public int saveRlgl010306ProfessionalInfo(Mtb45PersonnelProfessionalInfo mtb45PersonnelProfessionalInfo) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.doSaveRlgl010306ProfessionalInfo(mtb45PersonnelProfessionalInfo);
	}

	/**
	 * 人员基本信息-行政职务信息
	 */
	@Override
	public int saveRlgl010306JobInfo(Mtb44PersonnelJobInfo mtb44PersonnelJobInfo) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.doSaveRlgl010306JobInfo(mtb44PersonnelJobInfo);
	}

	/**
	 * 人员基本信息-社会关系
	 */
	@Override
	public int saveRlgl010306SocialInfo(Mtb43PersonnelSocialInfo mtb43PersonnelSocialInfo) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.doSaveRlgl010306SocialInfo(mtb43PersonnelSocialInfo);
	}

	/**
	 * 人员基本信息-教育经历
	 */
	@Override
	public int saveRlgl010306EduInfo(Mtb42PersonnelEduInfo mtb42PersonnelEduInfo) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.doSaveRlgl010306EduInfo(mtb42PersonnelEduInfo);
	}

	/**
	 * 人员基本信息-工作经历
	 */
	@Override
	public int saveRlgl010306WorkInfo(Mtb41PersonnelWorkInfo mtb41PersonnelWorkInfo) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.doSaveRlgl010306WorkInfo(mtb41PersonnelWorkInfo);
	}

	/**
	 * 人员基本信息-党派信息
	 */
	@Override
	public int saveRlgl010306PartisanInfo(Mtb40PersonnelPartisanInfo mtb40PersonnelPartisanInfo) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.doSaveRlgl010306PartisanInfo(mtb40PersonnelPartisanInfo);
	}

	/**
	 * 人员基本信息-执业资格信息
	 */
	@Override
	public int saveRlgl010306PractitionersInfo(Mtb46PersonnelPractitionersInfo mtb46PersonnelPractitionersInfo) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.doSaveRlgl010306PractitionersInfo(mtb46PersonnelPractitionersInfo);
	}

	@Override
	public Mtb39Personnel searchRlgl010306(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.searchRlgl010306(mtb39Personnel);
	}

	@Override
	public List<Mtb40PersonnelPartisanInfo> searchRlgl010306PartisanInfo(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.searchRlgl010306PartisanInfo(mtb39Personnel);
	}

	@Override
	public List<Mtb41PersonnelWorkInfo> searchRlgl010306WorkInfo(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.searchRlgl010306WorkInfo(mtb39Personnel);
	}

	@Override
	public List<Mtb42PersonnelEduInfo> searchRlgl010306EduInfo(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.searchRlgl010306EduInfo(mtb39Personnel);
	}

	@Override
	public List<Mtb43PersonnelSocialInfo> searchRlgl010306SocialInfo(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.searchRlgl010306SocialInfo(mtb39Personnel);
	}

	@Override
	public List<Mtb44PersonnelJobInfo> searchRlgl010306JobInfo(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.searchRlgl010306JobInfo(mtb39Personnel);
	}

	@Override
	public List<Mtb45PersonnelProfessionalInfo> searchRlgl010306ProfessionalInfo(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.searchRlgl010306ProfessionalInfo(mtb39Personnel);
	}

	@Override
	public List<Mtb46PersonnelPractitionersInfo> searchRlgl010306PractitionersInfo(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.searchRlgl010306PractitionersInfo(mtb39Personnel);
	}

	// 岗位信息
	@Override
	public Mtb09Irin getPersonInfo(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.getPersonInfo(mtb39Personnel);
	}

	// 删除
	@Override
	public int deleteRlgl010306(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.deleteRlgl010306(mtb39Personnel);
	}

	@Override
	public int deleteRlgl010306PartisanInfo(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.deleteRlgl010306PartisanInfo(mtb39Personnel);
	}

	@Override
	public int deleteRlgl010306WorkInfo(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.deleteRlgl010306WorkInfo(mtb39Personnel);
	}

	@Override
	public int deleteRlgl010306EduInfo(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.deleteRlgl010306EduInfo(mtb39Personnel);
	}

	@Override
	public int deleteRlgl010306SocialInfo(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.deleteRlgl010306SocialInfo(mtb39Personnel);
	}

	@Override
	public int deleteRlgl010306JobInfo(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.deleteRlgl010306JobInfo(mtb39Personnel);
	}

	@Override
	public int deleteRlgl010306ProfessionalInfo(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.deleteRlgl010306ProfessionalInfo(mtb39Personnel);
	}

	@Override
	public int deleteRlgl010306PractitionersInfo(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.deleteRlgl010306PractitionersInfo(mtb39Personnel);
	}

	@Override
	public int saveRlgl010306TutorInfo(Mtb59PersonnelTutorInfo mtb59PersonnelTutorInfo) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.doSaveRlgl010306TutorInfo(mtb59PersonnelTutorInfo);
	}

	@Override
	public List<Mtb59PersonnelTutorInfo> searchRlgl010306TutorInfo(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.searchRlgl010306TutorInfo(mtb39Personnel);
	}

	@Override
	public int deleteRlgl010306TutorInfo(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.deleteRlgl010306TutorInfo(mtb39Personnel);
	}
	
	/**
	 * 人员信息变更信息记录
	 * @param personnelInfo
	 * @return
	 */
	@org.springframework.transaction.annotation.Transactional(rollbackFor = Exception.class)
	public int saveChangePersonForMtb113Personnelupdate(Rlgl011008Bean personnelInfo) {
		return rlgl010306Mapper.saveChangePersonForMtb113Personnelupdate(personnelInfo);
	}

	public IRlgl010309Service getRlgl010309Service() {
		return rlgl010309Service;
	}

	public void setRlgl010309Service(IRlgl010309Service rlgl010309Service) {
		this.rlgl010309Service = rlgl010309Service;
	}

	public IAutoGetNumService getNoSerVice() {
		return noSerVice;
	}

	public void setNoSerVice(IAutoGetNumService noSerVice) {
		this.noSerVice = noSerVice;
	}

	@Override
	public Mtb01User searchUser(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.searchUser(mtb39Personnel);
	}

	// 执业信息
	@Override
	public int saveRlgl010306PracticeInfo(Mtb78PersonnelPracticeInfo mtb78PersonnelPracticeInfo) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.doSaveRlgl010306PracticeInfo(mtb78PersonnelPracticeInfo);
	}

	@Override
	public List<Mtb78PersonnelPracticeInfo> searchRlgl010306PracticeInfo(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.searchRlgl010306PracticeInfo(mtb39Personnel);
	}

	@Override
	public int deleteRlgl010306PracticeInfo(Mtb39Personnel mtb39Personnel) throws Exception {
		// TODO Auto-generated method stub
		return rlgl010306Mapper.deleteRlgl010306PracticeInfo(mtb39Personnel);
	}

}
