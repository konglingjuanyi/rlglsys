package com.rlglsys.action.learnonline;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl022002Bean;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb04Unit;
import com.rlglsys.entity.TunitCourse;
import com.rlglsys.service.IMtb63CourseWareService;
import com.rlglsys.service.IRlgl100103Service;
import com.rlglsys.util.Constant;
/**
 * 页面靠左部分，课件按照类型的列表显示action
 * @author zhanghuan
 *
 */
public class Rlgl100103CourseWareInitAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
    private IRlgl100103Service rlgl100103Service;
    private List<Rlgl022002Bean> courseList;
    private IMtb63CourseWareService mtb63CourseWareService;
    private String flg ;
	public String getFlg() {
		return flg;
	}


	public void setFlg(String flg) {
		this.flg = flg;
	}


	@Override
	protected String doExecute() throws Exception {
		try {
			
			Mtb01User loginUser = (Mtb01User)super.getSession(Constant.SESSION_KEY_LOGINUSER);
			Mtb04Unit unitInfo = (Mtb04Unit)super.getSession("UnitInfo");
			String user_id=(String)loginUser.getUser_id();
			Rlgl022002Bean rlgl022002Bean=new Rlgl022002Bean();
			rlgl022002Bean.setPersonnel_id(loginUser.getPersonnel_id());
			// 预付费
	        if ("01".equals(unitInfo.getUnit_payment()))
	        {
		       	 List<TunitCourse> unitCourseInfoList = mtb63CourseWareService.getTunitCourseInfoList(loginUser.getUnit_no());
		       	 if (unitCourseInfoList == null || unitCourseInfoList.size() == 0)
		       	 {
		       		unitCourseInfoList = new ArrayList<TunitCourse>();
		       		TunitCourse tunitCourse = new TunitCourse();
		       		tunitCourse.setCourse_support("999999");
		       		unitCourseInfoList.add(tunitCourse);
		       	 }
		       	rlgl022002Bean.setUnitCourseFlg("1");
		       	rlgl022002Bean.setUnitCourseInfoList(unitCourseInfoList);
	        }
	        
			//获得当前日期
	        Date dt = new Date();   
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        String temp_str=sdf.format(dt);
	        rlgl022002Bean.setCurrent_date(temp_str);
	        rlgl022002Bean.setUser_id(user_id);
			courseList=rlgl100103Service.courseList(rlgl022002Bean);
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return "init";
	}

	
	public IRlgl100103Service getRlgl100103Service() {
		return rlgl100103Service;
	}


	public void setRlgl100103Service(IRlgl100103Service rlgl100103Service) {
		this.rlgl100103Service = rlgl100103Service;
	}


	/**
	 * @return the courseList
	 */
	public List<Rlgl022002Bean> getCourseList() {
		return courseList;
	}
	/**
	 * @param courseList the courseList to set
	 */
	public void setCourseList(List<Rlgl022002Bean> courseList) {
		this.courseList = courseList;
	}


	public IMtb63CourseWareService getMtb63CourseWareService() {
		return mtb63CourseWareService;
	}


	public void setMtb63CourseWareService(IMtb63CourseWareService mtb63CourseWareService) {
		this.mtb63CourseWareService = mtb63CourseWareService;
	}

}
