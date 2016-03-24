package com.rlglsys.action.learnonline;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl100101_1Bean;
import com.rlglsys.entity.MTb02Adm;
import com.rlglsys.entity.MTb80CourseSelected;
import com.rlglsys.entity.Mtb63CourseWare;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IMtb63CourseWareService;
import com.rlglsys.service.IRlgl050101Service;
import com.rlglsys.service.IRlgl100101Service;
import com.rlglsys.service.IRlgl100103Service;
import com.rlglsys.util.Common;
import com.rlglsys.util.DateTimeManager;

public class Rlgl100103_2SaveCourseAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private IRlgl100103Service rlgl100103Service;
	private IMtb63CourseWareService mtb63CourseWareService;
	//课程id
	private String ishere;
	//用户id
	private String user_id;
	// 提示信息
	private String  message;
    private IRlgl050101Service rlgl050101Service;
    private IMTb02AdmService mtb02AdmService;
	private IRlgl100101Service rlgl100101Service;
	// 課件類別
	private String course_catagory="";
    // 课件的三级类别
    private String leibie3="";
    // 筛选课程列表
    private List<MTb02Adm> kcThreeList;

	@Override
	protected String doExecute() throws Exception {
		try {
			
	        if(course_catagory !=null && !"".equals(course_catagory)){
	        	// 根据课件类别得到课件类别的三级分类列表
	        	kcThreeList = mtb02AdmService.getThreeList(course_catagory);
	        	if(kcThreeList ==null ){
	        		kcThreeList = new ArrayList<MTb02Adm>();
	        	}
	        }else{
	        	kcThreeList = new ArrayList<MTb02Adm>();
	        }
			//把选择的课程保存进已选课程信息表
			String [] ids = ishere.replace(" ", "").split(",");
			//获取年度用变量
	    	Common common = new Common();

	    	//获得当前学分年度（申请年度）
	    	String area_id = mtb02AdmService.getAdmName("237", "01");
	        String year = Integer.toString(common.getNowCreditYear(area_id, rlgl050101Service));
	        
	        // 查询成功缴费信息
		    Rlgl100101_1Bean rlgl100101_1Bean =new Rlgl100101_1Bean();
		    rlgl100101_1Bean.setUserId(user_id);
		    rlgl100101_1Bean.setPay_year(year);
		    
		    rlgl100101_1Bean = rlgl100101Service.getData5(rlgl100101_1Bean);
		    String TransactionID = "";
		    if (rlgl100101_1Bean != null)
		    {
		    	TransactionID = rlgl100101_1Bean.getTransactionID();
		    }
		    
			for(String coursId :ids){
				MTb80CourseSelected mtb80 = new MTb80CourseSelected();
				mtb80.setCourse_id(coursId);
				// 课程名称
				List<Mtb63CourseWare> courList= new ArrayList<Mtb63CourseWare>();
				Mtb63CourseWare oneMtb63 = new Mtb63CourseWare();
				oneMtb63.setCourse_id(coursId);
				courList = mtb63CourseWareService.getCourseWareByBean(oneMtb63);
				if(courList !=null && courList.size()>0){
					Mtb63CourseWare mtb63 = new Mtb63CourseWare();
					mtb63 = courList.get(0);
					if(mtb63 !=null){
						mtb80.setCourse_name(mtb63.getCourse_name());
					}else{
						mtb80.setCourse_name("");
					}
				}else{
					mtb80.setCourse_name("");
				}
				mtb80.setUser_id(user_id);
				mtb80.setDel_kbn("0");
				mtb80.setLogin_user_id(user_id);
				mtb80.setLogin_date(DateTimeManager.getSystemDate14());
				mtb80.setEx_key(1);
				mtb80.setIs_prep("0");
				mtb80.setCredit_year(year);
				mtb80.setTransactionID(TransactionID);
				super.setDBCommonColOnInsert(mtb80);
				courList.clear();
				rlgl100103Service.insertSelCourse(mtb80);
			}
			HttpServletRequest request = ServletActionContext.getRequest ();
			message="选课成功，请到“已选课件”查看已选择的课件！";
			request.setAttribute("message", message);
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}

		return SUCCESS;
	}

	public IRlgl100103Service getRlgl100103Service() {
		return rlgl100103Service;
	}

	public void setRlgl100103Service(IRlgl100103Service rlgl100103Service) {
		this.rlgl100103Service = rlgl100103Service;
	}

	public String getIshere() {
		return ishere;
	}

	public void setIshere(String ishere) {
		this.ishere = ishere;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public IMtb63CourseWareService getMtb63CourseWareService() {
		return mtb63CourseWareService;
	}

	public void setMtb63CourseWareService(
			IMtb63CourseWareService mtb63CourseWareService) {
		this.mtb63CourseWareService = mtb63CourseWareService;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public IRlgl050101Service getRlgl050101Service() {
		return rlgl050101Service;
	}

	public void setRlgl050101Service(IRlgl050101Service rlgl050101Service) {
		this.rlgl050101Service = rlgl050101Service;
	}

	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}

	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
	}

	public IRlgl100101Service getRlgl100101Service() {
		return rlgl100101Service;
	}

	public void setRlgl100101Service(IRlgl100101Service rlgl100101Service) {
		this.rlgl100101Service = rlgl100101Service;
	}

	public String getCourse_catagory() {
		return course_catagory;
	}

	public void setCourse_catagory(String course_catagory) {
		this.course_catagory = course_catagory;
	}

	public String getLeibie3() {
		return leibie3;
	}

	public void setLeibie3(String leibie3) {
		this.leibie3 = leibie3;
	}

	public List<MTb02Adm> getKcThreeList() {
		return kcThreeList;
	}

	public void setKcThreeList(List<MTb02Adm> kcThreeList) {
		this.kcThreeList = kcThreeList;
	}
	
}
