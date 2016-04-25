package com.rlglsys.action.hiringmanage.personreview;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.rlglsys.base.BaseAction;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb04Unit;
import com.rlglsys.entity.Mtb13PersonnelPartisanInfo;
import com.rlglsys.entity.Mtb14PersonnelWorkInfo;
import com.rlglsys.entity.Mtb15PersonnelEduInfo;
import com.rlglsys.entity.Mtb16PersonnelSocialInfo;
import com.rlglsys.entity.Mtb17PersonnelJobInfo;
import com.rlglsys.entity.Mtb18PersonnelProfessionalInfo;
import com.rlglsys.entity.Mtb19PersonnelPractitionersInfo;
import com.rlglsys.entity.Mtb29PersonalApply;
import com.rlglsys.entity.Mtb39Personnel;
import com.rlglsys.entity.Mtb59PersonnelTutorInfo;
import com.rlglsys.entity.Mtb77PersonnelPracticeInfo;
import com.rlglsys.service.IApprovalProcessService;
import com.rlglsys.service.IRlgl010106Service;
import com.rlglsys.service.IRlgl010305Service;
import com.rlglsys.service.IRlgl010505Service;
import com.rlglsys.service.IRlgl010506Service;
import com.rlglsys.util.Constant;
import com.rlglsys.util.DateTimeManager;

public class Rlgl010506ReviewOkAction extends BaseAction{
    
    private static final long serialVersionUID = 483775379303898192L;
    private IRlgl010505Service rlgl010505Service;
    private IRlgl010506Service rlgl010506Service;
    private IRlgl010106Service rlgl010106Service;
    private IRlgl010305Service rlgl010305Service;
    private IApprovalProcessService approvalProcessService;
    private Mtb39Personnel personnel;
    private String apply_no;
    private String max_number;
    private String checkViews;
    private String btnKbn;
    /**
     * 审核通过处理
     * 
     * @return SUCCESS
     * @throws Exception
     */
    @Override
    protected String doExecute() throws Exception {
        
        String personnelId = rlgl010506Service.getPersonal_id(apply_no);
        //个人信息申请明细检索
        personnel=rlgl010506Service.getPersonnel(personnelId);
        // 用户信息
        Mtb01User userInfo = (Mtb01User)super.getSession().getAttribute(Constant.SESSION_KEY_LOGINUSER);
        // 用户单位
        String unitNo = userInfo.getUnit_no();
        
        // 单位信息
        Mtb04Unit applyUnitinfo = rlgl010106Service.getUnitInfo(personnel.getPersonnel_unit());
        Mtb39Personnel updatetb39Info = personnel;
        updatetb39Info.setApply_no(apply_no);
        updatetb39Info.setApply_number(max_number);
        String mark = null;
       if ("00".equals(personnel.getNow_mark())) {
    	   if ("37".equals(personnel.getPersonnel_unit())) {
               //省厅审核
    		   updatetb39Info.setMinistry_checker(userInfo.getUser_id());
    		   updatetb39Info.setMinistry_check_date(DateTimeManager.getSystemDate14().substring(0, 8));
    		   updatetb39Info.setMinistry_check_views(checkViews);
               if ("1".equals(btnKbn)) {
            	   updatetb39Info.setMinistry_check_result("001");
               } else if ("2".equals(btnKbn)) {
            	   updatetb39Info.setMinistry_check_result("002");
               }
               // 当前节点
               updatetb39Info.setNow_mark("04");
               mark = "04";
           } else {
        	 //单位审核
               updatetb39Info.setUnit_checker(userInfo.getUser_id());
               updatetb39Info.setUnit_check_date(DateTimeManager.getSystemDate14().substring(0, 8));
               updatetb39Info.setUnit_check_views(checkViews);
               if ("1".equals(btnKbn)) {
                   updatetb39Info.setUnit_check_result("001");
               } else if ("2".equals(btnKbn)) {
                   updatetb39Info.setUnit_check_result("002");
               }
               // 当前节点
               updatetb39Info.setNow_mark("01");
               mark = "01";
           }
        } else if ("01".equals(personnel.getNow_mark())) {
              if ("01".equals(applyUnitinfo.getUnit_level()) || "02".equals(applyUnitinfo.getUnit_level())) {
                  //区县审核
                  updatetb39Info.setCounty_checker(userInfo.getUser_id());
                  updatetb39Info.setCounty_check_date(DateTimeManager.getSystemDate14().substring(0, 8));
                  updatetb39Info.setCounty_check_views(checkViews);
                  if ("1".equals(btnKbn)) {
                      updatetb39Info.setCounty_check_result("001");
                  } else if ("2".equals(btnKbn)) {
                      updatetb39Info.setCounty_check_result("002");
                  }
                  // 当前节点
                  updatetb39Info.setNow_mark("02");
                  mark = "02";
              } else if ("03".equals(applyUnitinfo.getUnit_level())) {
                  //市局审核
                  updatetb39Info.setDowntown_checker(userInfo.getUser_id());
                  updatetb39Info.setDowntown_check_date(DateTimeManager.getSystemDate14().substring(0, 8));
                  updatetb39Info.setDowntown_check_views(checkViews);
                  if ("1".equals(btnKbn)) {
                      updatetb39Info.setDowntown_check_result("001");
                  } else if ("2".equals(btnKbn)) {
                      updatetb39Info.setDowntown_check_result("002");
                  }
                  // 当前节点
                  updatetb39Info.setNow_mark("03");
                  mark = "03";
              } else if ("04".equals(applyUnitinfo.getUnit_level())) {
                  //省厅审核
                  updatetb39Info.setMinistry_checker(userInfo.getUser_id());
                  updatetb39Info.setMinistry_check_date(DateTimeManager.getSystemDate14().substring(0, 8));
                  updatetb39Info.setMinistry_check_views(checkViews);
                  if ("1".equals(btnKbn)) {
                      updatetb39Info.setMinistry_check_result("001");
                  } else if ("2".equals(btnKbn)) {
                      updatetb39Info.setMinistry_check_result("002");
                  }
                  // 当前节点
                  updatetb39Info.setNow_mark("04");
                  mark = "04";
              }
        } else if ("02".equals(personnel.getNow_mark())) {
            //市局审核
            updatetb39Info.setDowntown_checker(userInfo.getUser_id());
            updatetb39Info.setDowntown_check_date(DateTimeManager.getSystemDate14().substring(0, 8));
            updatetb39Info.setDowntown_check_views(checkViews);
            if ("1".equals(btnKbn)) {
                updatetb39Info.setDowntown_check_result("001");
            } else if ("2".equals(btnKbn)) {
                updatetb39Info.setDowntown_check_result("002");
            }
            // 当前节点
            updatetb39Info.setNow_mark("03");
            mark = "03";
           } else if ("03".equals(personnel.getNow_mark())) {
            //省厅审核
            updatetb39Info.setMinistry_checker(userInfo.getUser_id());
            updatetb39Info.setMinistry_check_date(DateTimeManager.getSystemDate14().substring(0, 8));
            updatetb39Info.setMinistry_check_views(checkViews);
            if ("1".equals(btnKbn)) {
                updatetb39Info.setMinistry_check_result("001");
            } else if ("2".equals(btnKbn)) {
                updatetb39Info.setMinistry_check_result("002");
            }
            // 当前节点
            updatetb39Info.setNow_mark("04");
            mark = "04";
            }
            Mtb29PersonalApply tb29Info = rlgl010505Service.getTb29Info(apply_no);
            // 终审节点
            String ednMark = tb29Info.getEnd_mark();
            Mtb29PersonalApply updatetb29Info = tb29Info;
            updatetb29Info.setApply_no(apply_no);
            // 当前节点
            updatetb29Info.setNow_mark(mark);

        if ("1".equals(btnKbn)) {
            // 审核结果 待审核单位NO
            if (ednMark.equals(mark) || "00".equals(ednMark)) {
                // 审核通过
                updatetb29Info.setCheck_result("002");
                updatetb39Info.setPersonnel_isapproval("002");
                // 20130909  新添加
                updatetb39Info.setChange_value("");
                // 执行更新操作
                update(personnel,personnelId,userInfo);
                
            } else {
                // 审核中
                updatetb29Info.setCheck_result("001");
                updatetb39Info.setPersonnel_isapproval("001");
                Mtb04Unit unitinfo = rlgl010106Service.getUnitInfo(unitNo);
                // 上级单位
                String willUnitNo = unitinfo.getUnit_super();
                updatetb29Info.setWill_checkunitno(willUnitNo);
            }
        } else if ("2".equals(btnKbn)) {
            // 审核驳回
            updatetb29Info.setCheck_result("003");
            updatetb39Info.setPersonnel_isapproval("003");
        }
        // 个人申请明细信息更新
        updatetb39Info.setUpdate_user_id(userInfo.getUser_id());
        updatetb39Info.setUpdate_date(DateTimeManager.getSystemDate14());
        updatetb29Info.setUpdate_user_id(userInfo.getUser_id());
        updatetb29Info.setUpdate_date(DateTimeManager.getSystemDate14());
        // 个人基本信息申请明细信息更新，个人人事申请信息更新
        rlgl010506Service.updateGroupInfo(updatetb39Info,updatetb29Info);
        
        return SUCCESS;
    }

    public void update(Mtb39Personnel per,String personnelId ,Mtb01User userInfo) throws Exception{
        
        // 人员基本信息-专业技术职务信息
        List<Mtb18PersonnelProfessionalInfo> rlgl010302ProfessionalInfoList;
        // 人员基本信息-行政职务信息
        List<Mtb17PersonnelJobInfo> rlgl010302JobInfoList;
        // 人员基本信息-社会关系
        List<Mtb16PersonnelSocialInfo> rlgl010302SocialInfoList;
        // 人员基本信息-教育经历
        List<Mtb15PersonnelEduInfo> rlgl010302EduInfoList;
        // 人员基本信息-工作经历
        List<Mtb14PersonnelWorkInfo> rlgl010302WorkInfoList;
        // 人员基本信息-党派信息
        List<Mtb13PersonnelPartisanInfo> rlgl010302PartisanInfoList;
        // 人员基本信息-资格信息
        List<Mtb19PersonnelPractitionersInfo> rlgl010302PractitionersInfoList;
        // 人员基本信息-执业信息
        List<Mtb77PersonnelPracticeInfo> rlgl010302PracticeInfoList;
        
        // 人员基本信息-导师信息
        List<Mtb59PersonnelTutorInfo>  mtb59PersonnelTutorInfoList = new ArrayList<Mtb59PersonnelTutorInfo>();
        
        rlgl010302ProfessionalInfoList=rlgl010305Service.getProfessionalInfo(personnelId);
        rlgl010302JobInfoList=rlgl010305Service.getJobInfo(personnelId);
        rlgl010302SocialInfoList=rlgl010305Service.getSocialInfo(personnelId);
        rlgl010302EduInfoList=rlgl010305Service.getEduInfo(personnelId);
        rlgl010302WorkInfoList=rlgl010305Service.getWorkInfo(personnelId);
        rlgl010302PartisanInfoList=rlgl010305Service.getPartisanInfo(personnelId);
        rlgl010302PractitionersInfoList=rlgl010305Service.getPractitionersInfo(personnelId);
        rlgl010302PracticeInfoList=rlgl010305Service.getPracticeInfo(personnelId);
        // 取出 Mtb_59Personnel_Tutor_Info中的数据
        mtb59PersonnelTutorInfoList = rlgl010506Service.getmtb59PersonnelTutorInfo(personnelId);
        
        if(rlgl010302ProfessionalInfoList !=null && rlgl010302ProfessionalInfoList.size()>0){
            for(int i = 0; i<rlgl010302ProfessionalInfoList.size(); i++){
                rlgl010302ProfessionalInfoList.get(i).setGet_time(DateTimeManager.dateChange(rlgl010302ProfessionalInfoList.get(i).getGet_time()));
                if(!StringUtils.isBlank(userInfo.getLogin_date())){
	                rlgl010302ProfessionalInfoList.get(i).setLogin_user_id(userInfo.getLogin_user_id());
	                rlgl010302ProfessionalInfoList.get(i).setLogin_date(userInfo.getLogin_date());
	                rlgl010302ProfessionalInfoList.get(i).setUpdate_user_id(userInfo.getUpdate_user_id());
	                rlgl010302ProfessionalInfoList.get(i).setUpdate_date(userInfo.getUpdate_date());
                }else
                {
                	super.setDBCommonColOnInsert(rlgl010302ProfessionalInfoList.get(i));
                }
             }
        }
        if(rlgl010302JobInfoList !=null && rlgl010302JobInfoList.size()>0){
            for(int i = 0; i<rlgl010302JobInfoList.size(); i++){
                rlgl010302JobInfoList.get(i).setAppoint_time(DateTimeManager.dateChange(rlgl010302JobInfoList.get(i).getAppoint_time()));
                if(!StringUtils.isBlank(userInfo.getLogin_date())){
                rlgl010302JobInfoList.get(i).setLogin_user_id(userInfo.getLogin_user_id());
                rlgl010302JobInfoList.get(i).setLogin_date(userInfo.getLogin_date());
                rlgl010302JobInfoList.get(i).setUpdate_user_id(userInfo.getUpdate_user_id());
                rlgl010302JobInfoList.get(i).setUpdate_date(userInfo.getUpdate_date());
                }else
                {
                	super.setDBCommonColOnInsert(rlgl010302JobInfoList.get(i));
                }
            }
        }
        if(rlgl010302SocialInfoList !=null && rlgl010302SocialInfoList.size()>0){
            for(int i = 0; i<rlgl010302SocialInfoList.size(); i++){
                rlgl010302SocialInfoList.get(i).setBirthday(DateTimeManager.dateChange(rlgl010302SocialInfoList.get(i).getBirthday()));
                if(!StringUtils.isBlank(userInfo.getLogin_date())){
                rlgl010302SocialInfoList.get(i).setLogin_user_id(userInfo.getLogin_user_id());
                rlgl010302SocialInfoList.get(i).setLogin_date(userInfo.getLogin_date());
                rlgl010302SocialInfoList.get(i).setUpdate_user_id(userInfo.getUpdate_user_id());
                rlgl010302SocialInfoList.get(i).setUpdate_date(userInfo.getUpdate_date());
                }else
                {
                	super.setDBCommonColOnInsert(rlgl010302SocialInfoList.get(i));
                }
            }
        }
        if(rlgl010302EduInfoList !=null && rlgl010302EduInfoList.size()>0){
            for(int i = 0; i<rlgl010302EduInfoList.size(); i++){
                rlgl010302EduInfoList.get(i).setAdmission_time(DateTimeManager.dateChange(rlgl010302EduInfoList.get(i).getAdmission_time()));
                rlgl010302EduInfoList.get(i).setGraduation_time(DateTimeManager.dateChange(rlgl010302EduInfoList.get(i).getGraduation_time()));
                if(!StringUtils.isBlank(userInfo.getLogin_date())){
                rlgl010302EduInfoList.get(i).setLogin_user_id(userInfo.getLogin_user_id());
                rlgl010302EduInfoList.get(i).setLogin_date(userInfo.getLogin_date());
                rlgl010302EduInfoList.get(i).setUpdate_user_id(userInfo.getUpdate_user_id());
                rlgl010302EduInfoList.get(i).setUpdate_date(userInfo.getUpdate_date());
                }else
                {
                	super.setDBCommonColOnInsert(rlgl010302EduInfoList.get(i));
                }
            }
        }
        if(rlgl010302WorkInfoList !=null && rlgl010302WorkInfoList.size()>0){
            for(int i = 0; i<rlgl010302WorkInfoList.size(); i++){
                rlgl010302WorkInfoList.get(i).setStarttime(DateTimeManager.dateChange(rlgl010302WorkInfoList.get(i).getStarttime()));
                rlgl010302WorkInfoList.get(i).setEndtime(DateTimeManager.dateChange(rlgl010302WorkInfoList.get(i).getEndtime()));
                if(!StringUtils.isBlank(userInfo.getLogin_date())){
                rlgl010302WorkInfoList.get(i).setLogin_user_id(userInfo.getLogin_user_id());
                rlgl010302WorkInfoList.get(i).setLogin_date(userInfo.getLogin_date());
                rlgl010302WorkInfoList.get(i).setUpdate_user_id(userInfo.getUpdate_user_id());
                rlgl010302WorkInfoList.get(i).setUpdate_date(userInfo.getUpdate_date());
                }else
                {
                	super.setDBCommonColOnInsert(rlgl010302WorkInfoList.get(i));
                }
            }
        }
        if(rlgl010302PartisanInfoList !=null && rlgl010302PartisanInfoList.size()>0){
            for(int i = 0; i<rlgl010302PartisanInfoList.size(); i++){
                rlgl010302PartisanInfoList.get(i).setJointime(DateTimeManager.dateChange(rlgl010302PartisanInfoList.get(i).getJointime()));
                if(!StringUtils.isBlank(userInfo.getLogin_date())){
                rlgl010302PartisanInfoList.get(i).setLogin_user_id(userInfo.getLogin_user_id());
                rlgl010302PartisanInfoList.get(i).setLogin_date(userInfo.getLogin_date());
                rlgl010302PartisanInfoList.get(i).setUpdate_user_id(userInfo.getUpdate_user_id());
                rlgl010302PartisanInfoList.get(i).setUpdate_date(userInfo.getUpdate_date());
                }else
                {
                	super.setDBCommonColOnInsert(rlgl010302PartisanInfoList.get(i));
                }
            }
        }
        if(rlgl010302PractitionersInfoList !=null && rlgl010302PractitionersInfoList.size()>0){
            for(int i = 0; i<rlgl010302PractitionersInfoList.size(); i++){
                rlgl010302PractitionersInfoList.get(i).setIssue_time(DateTimeManager.dateChange(rlgl010302PractitionersInfoList.get(i).getIssue_time()));
                if(!StringUtils.isBlank(userInfo.getLogin_date())){
                rlgl010302PractitionersInfoList.get(i).setLogin_user_id(userInfo.getLogin_user_id());
                rlgl010302PractitionersInfoList.get(i).setLogin_date(userInfo.getLogin_date());
                rlgl010302PractitionersInfoList.get(i).setUpdate_user_id(userInfo.getUpdate_user_id());
                rlgl010302PractitionersInfoList.get(i).setUpdate_date(userInfo.getUpdate_date());
                }else
                {
                	super.setDBCommonColOnInsert(rlgl010302PractitionersInfoList.get(i));
                }
            }
        }
        if(rlgl010302PracticeInfoList !=null && rlgl010302PracticeInfoList.size()>0){
            for(int i = 0; i<rlgl010302PracticeInfoList.size(); i++){
            	rlgl010302PracticeInfoList.get(i).setIssue_time(DateTimeManager.dateChange(rlgl010302PracticeInfoList.get(i).getIssue_time()));
                if(!StringUtils.isBlank(userInfo.getLogin_date())){
            	rlgl010302PracticeInfoList.get(i).setLogin_user_id(userInfo.getLogin_user_id());
            	rlgl010302PracticeInfoList.get(i).setLogin_date(userInfo.getLogin_date());
            	rlgl010302PracticeInfoList.get(i).setUpdate_user_id(userInfo.getUpdate_user_id());
            	rlgl010302PracticeInfoList.get(i).setUpdate_date(userInfo.getUpdate_date());
                }else
                {
                	super.setDBCommonColOnInsert(rlgl010302PracticeInfoList.get(i));
                }
            }
        }
        //  删除Mtb_59Personnel_Tutor_Info中的数据，并把数据放入Mtb_58Personnel_Tutor_Info中
        if(mtb59PersonnelTutorInfoList !=null && mtb59PersonnelTutorInfoList.size() >0){
            for(int i = 0; i<mtb59PersonnelTutorInfoList.size(); i++){
                if(!StringUtils.isBlank(userInfo.getLogin_date())){
                mtb59PersonnelTutorInfoList.get(i).setLogin_user_id(userInfo.getLogin_user_id());
                mtb59PersonnelTutorInfoList.get(i).setLogin_date(userInfo.getLogin_date());
                mtb59PersonnelTutorInfoList.get(i).setUpdate_user_id(userInfo.getUpdate_user_id());
                mtb59PersonnelTutorInfoList.get(i).setUpdate_date(userInfo.getUpdate_date());
                }else
                {
                	super.setDBCommonColOnInsert(mtb59PersonnelTutorInfoList.get(i));
                }
            }
        }
        
        // 事务更新
        rlgl010506Service.updateRlgl010506(per
                    ,rlgl010302ProfessionalInfoList
                    ,rlgl010302JobInfoList
                    ,rlgl010302SocialInfoList
                    ,rlgl010302EduInfoList
                    ,rlgl010302WorkInfoList
                    ,rlgl010302PartisanInfoList
                    ,rlgl010302PractitionersInfoList
                    ,rlgl010302PracticeInfoList
                    ,mtb59PersonnelTutorInfoList
                    ,userInfo);
            }
    
    // ----------------get,set--------------------
    
    public IApprovalProcessService getApprovalProcessService() {
        return approvalProcessService;
    }
    public void setApprovalProcessService(
            IApprovalProcessService approvalProcessService) {
        this.approvalProcessService = approvalProcessService;
    }
    public String getApply_no() {
        return apply_no;
    }
    public void setApply_no(String apply_no) {
        this.apply_no = apply_no;
    }
    public String getCheckViews() {
        return checkViews;
    }
    public void setCheckViews(String checkViews) {
        this.checkViews = checkViews;
    }
    public String getMax_number() {
        return max_number;
    }
    public void setMax_number(String max_number) {
        this.max_number = max_number;
    }
    public String getBtnKbn() {
        return btnKbn;
    }
    public void setBtnKbn(String btnKbn) {
        this.btnKbn = btnKbn;
    }

    public Mtb39Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Mtb39Personnel personnel) {
        this.personnel = personnel;
    }

    public IRlgl010505Service getRlgl010505Service() {
        return rlgl010505Service;
    }

    public void setRlgl010505Service(IRlgl010505Service rlgl010505Service) {
        this.rlgl010505Service = rlgl010505Service;
    }

    public IRlgl010506Service getRlgl010506Service() {
        return rlgl010506Service;
    }

    public void setRlgl010506Service(IRlgl010506Service rlgl010506Service) {
        this.rlgl010506Service = rlgl010506Service;
    }

    public IRlgl010106Service getRlgl010106Service() {
        return rlgl010106Service;
    }

    public void setRlgl010106Service(IRlgl010106Service rlgl010106Service) {
        this.rlgl010106Service = rlgl010106Service;
    }

    public IRlgl010305Service getRlgl010305Service() {
        return rlgl010305Service;
    }

    public void setRlgl010305Service(IRlgl010305Service rlgl010305Service) {
        this.rlgl010305Service = rlgl010305Service;
    }

}
