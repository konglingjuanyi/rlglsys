package com.rlglsys.action.continumedicaleducation.videoonline;

import java.util.List;

import com.rlglsys.base.BaseAction;
import com.rlglsys.entity.MTb81CourseExams;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IRlgl022004Service;
import com.rlglsys.util.Constant;

public class Rlgl400103SearchAction extends BaseAction {

	private static final long serialVersionUID = 5541674777763881922L;
	// 学分年度
	private String credit_year;
	// 课程名称
	private String course_name;
	// 身份证号码
	private String userId;
	//// 显示列表的标志位
	private String playFlag;
	private IRlgl022004Service rlgl022004Service;
	private List<MTb81CourseExams> courseExamsList;
	// 管理信息操作service
	private IMTb02AdmService mtb02AdmService;
	// 分页用
	// 总条数
	private int recordCount;
	// 当前页
	private String txtInputPage = "";
	private String hdnCountOfPage = "";
	private String xuefeileibie = "";
	private String zongxuefen = "";

	public String getXuefeileibie() {
		return xuefeileibie;
	}

	public void setXuefeileibie(String xuefeileibie) {
		this.xuefeileibie = xuefeileibie;
	}

	@Override
	protected String doExecute() throws Exception {
		// 用户对象
		Mtb01User loginUser = (Mtb01User) super.getSession(Constant.SESSION_KEY_LOGINUSER);
		userId = loginUser.getPersonnel_id();
		// 数据条数
		int resultCount = rlgl022004Service.getCourseCreditCount(userId, credit_year);
		if (resultCount == 0) {
			// 页面显示检索不到数据的信息
			playFlag = "2";
		} else {
			int pageNo = 0;
			// 每页条数
			int pageCount = getPageCount();
			// 页数
			if (pageNo == 0) {
				pageNo = "".equals(txtInputPage) ? 0 * pageCount : (Integer.valueOf(txtInputPage) - 1) * pageCount;
			}
			// 总数量
			if (recordCount == 0) {
				recordCount = resultCount;
			}
			MTb81CourseExams courseExams = new MTb81CourseExams();
			courseExams.setPageCount(pageCount);
			courseExams.setPageNo(pageNo);
			courseExams.setRecordCount(recordCount);
			courseExams.setTxtInputPage(txtInputPage);
			courseExams.setCredit_category(xuefeileibie);
			// 设置用户的证件编号，课程名称，学分年度信息
			courseExams.setUser_id(userId);
			courseExams.setCourse_name(course_name);
			courseExams.setCredit_year(credit_year);
			courseExamsList = rlgl022004Service.getCourseCreditList(courseExams);
			// 显示列表
			playFlag = "1";
		}

		return SUCCESS;
	}

	public String getZongxuefen() {
		return zongxuefen;
	}

	public void setZongxuefen(String zongxuefen) {
		this.zongxuefen = zongxuefen;
	}

	public IRlgl022004Service getRlgl022004Service() {
		return rlgl022004Service;
	}

	public void setRlgl022004Service(IRlgl022004Service rlgl022004Service) {
		this.rlgl022004Service = rlgl022004Service;
	}

	public List<MTb81CourseExams> getCourseExamsList() {
		return courseExamsList;
	}

	public void setCourseExamsList(List<MTb81CourseExams> courseExamsList) {
		this.courseExamsList = courseExamsList;
	}

	public String getCredit_year() {
		return credit_year;
	}

	public void setCredit_year(String credit_year) {
		this.credit_year = credit_year;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
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

	public String getPlayFlag() {
		return playFlag;
	}

	public void setPlayFlag(String playFlag) {
		this.playFlag = playFlag;
	}

	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}

	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
	}

}
