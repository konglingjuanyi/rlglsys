package com.rlglsys.action.learnonline;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl022003Bean;
import com.rlglsys.bean.Rlgl100103Bean;
import com.rlglsys.entity.MTb02Adm;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb04Unit;
import com.rlglsys.entity.Mtb120SysUrl;
import com.rlglsys.entity.TunitCourse;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IMtb63CourseWareService;
import com.rlglsys.service.IRlgl100103Service;
import com.rlglsys.service.IUserService;
import com.rlglsys.util.Constant;

public class Rlgl100103CourseInitAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private IRlgl100103Service rlgl100103Service;
	private String course_catagory = "";
	private String user_id = "";
	// 分页用
	private int recordCount;
	private String txtInputPage = "";
	private String hdnCountOfPage = "";
	// 华医网的课件
	private List<Rlgl022003Bean> hywCourseList;
	// 好医生的课件
	private List<Rlgl022003Bean> hysCourseList;
	private List<Rlgl100103Bean> resultList;
	private IMtb63CourseWareService mtb63CourseWareService;
	private String sysUrl = "";
	private String flg;

	public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}

	// 已选课程数目
	private int yxkcCount;
	// 用户Service
	private IUserService userService;
	// 筛选课程列表
	private List<MTb02Adm> kcThreeList;
	// 管理信息操作service
	private IMTb02AdmService mtb02AdmService;
	// 课件的三级类别
	private String leibie3 = "";
	// 提示信息
	private String message;

	@Override
	protected String doExecute() throws Exception {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
	        flg = request.getParameter("ab");
			List<Rlgl022003Bean> courseList = new ArrayList<Rlgl022003Bean>();
			// 用户对象
			Mtb01User loginUser = (Mtb01User) super.getSession(Constant.SESSION_KEY_LOGINUSER);
			Mtb04Unit unitInfo = (Mtb04Unit) super.getSession("UnitInfo");
			String xuefeileibie = "001";

			Rlgl022003Bean rlgl022003Bean = new Rlgl022003Bean();
			rlgl022003Bean.setPersonnel_id(loginUser.getPersonnel_id());
			rlgl022003Bean.setXuefen_leibie(xuefeileibie);
			// 初始化筛选课程列表
			// 初始化的时候传递过来的二级课程类别为null，初始化列表即可
			// 传递过来的二级类别不为null或者空的时候，查询出来即可
			if (course_catagory != null && !"".equals(course_catagory)) {
				// 根据课件类别得到课件类别的三级分类列表
				kcThreeList = mtb02AdmService.getThreeList(course_catagory);
				if (kcThreeList == null) {
					kcThreeList = new ArrayList<MTb02Adm>();
				}
			} else {
				kcThreeList = new ArrayList<MTb02Adm>();
			}

			// 预付费
			if ("01".equals(unitInfo.getUnit_payment())) {
				List<TunitCourse> unitCourseInfoList = mtb63CourseWareService
						.getTunitCourseInfoList(loginUser.getUnit_no());
				if (unitCourseInfoList == null || unitCourseInfoList.size() == 0) {
					unitCourseInfoList = new ArrayList<TunitCourse>();
					TunitCourse tunitCourse = new TunitCourse();
					tunitCourse.setCourse_support("999999");
					unitCourseInfoList.add(tunitCourse);
				}
				rlgl022003Bean.setUnitCourseFlg("1");
				rlgl022003Bean.setUnitCourseInfoList(unitCourseInfoList);
			}

			user_id = loginUser.getUser_id();

			// 获得当前日期
			Date dt = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String temp_str = sdf.format(dt);
			String niandu = temp_str.substring(0, 4);
			// 查询出用户已经选择的课程数目
			yxkcCount = rlgl100103Service.getYxkcCount(user_id, niandu);

			rlgl022003Bean.setCurrent_date(temp_str);
			if ("".equals(course_catagory)) {
				recordCount = 0;
			} else {
				recordCount = rlgl100103Service.getCourseCount(course_catagory, user_id, rlgl022003Bean);
				String course_catagoryAll = course_catagory;
				// 拼接完整的课件类别
				if (!"".equals(leibie3)) {
					course_catagoryAll = course_catagory + leibie3;
				}
				courseList = rlgl100103Service.courseList(course_catagoryAll, user_id, rlgl022003Bean);
				// 把来自华医网和好医生的课件分离出来
				hywCourseList = new ArrayList<Rlgl022003Bean>();
				hysCourseList = new ArrayList<Rlgl022003Bean>();
				for (Rlgl022003Bean rlgl022003 : courseList) {
					// 课件来源为好医生
					if ("001".equals(rlgl022003.getCourse_support())) {
						hysCourseList.add(rlgl022003);
					}
					// 课件来源为华医网
					if ("002".equals(rlgl022003.getCourse_support())) {
						hywCourseList.add(rlgl022003);
					}
				}
				int hysCount = hysCourseList.size();
				int hywCount = hywCourseList.size();
				if (resultList == null) {
					resultList = new ArrayList<Rlgl100103Bean>();
				}
				Rlgl100103Bean rlgl100103Bean = new Rlgl100103Bean();
				if (hysCount > hywCount) {
					for (int i = 0; i < hysCount; i++) {
						rlgl100103Bean = new Rlgl100103Bean();
						rlgl100103Bean = this.editHysData(rlgl100103Bean, hysCourseList.get(i), "1");
						if (hywCount > i) {
							rlgl100103Bean = this.editHysData(rlgl100103Bean, hywCourseList.get(i), "2");
						} else {
							rlgl100103Bean = this.editHysData(rlgl100103Bean, null, "3");
						}
						resultList.add(rlgl100103Bean);
					}
				} else if (hywCount > hysCount) {
					for (int i = 0; i < hywCount; i++) {
						rlgl100103Bean = new Rlgl100103Bean();
						rlgl100103Bean = this.editHysData(rlgl100103Bean, hywCourseList.get(i), "2");
						if (hysCount > i) {
							rlgl100103Bean = this.editHysData(rlgl100103Bean, hysCourseList.get(i), "1");
						} else {
							rlgl100103Bean = this.editHysData(rlgl100103Bean, null, "4");
						}
						resultList.add(rlgl100103Bean);
					}
				} else {
					for (int i = 0; i < hywCount; i++) {
						rlgl100103Bean = new Rlgl100103Bean();
						rlgl100103Bean = this.editHysData(rlgl100103Bean, hywCourseList.get(i), "2");
						rlgl100103Bean = this.editHysData(rlgl100103Bean, hysCourseList.get(i), "1");
						resultList.add(rlgl100103Bean);
					}
				}

			}
			Mtb120SysUrl urlInfoNew = new Mtb120SysUrl();
			urlInfoNew.setUrl_id("001");
			Mtb120SysUrl urlInfo = userService.getMtb120SysurlInfo(urlInfoNew);
			if (urlInfo != null) {
				sysUrl = urlInfo.getUrl();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (!StringUtils.isBlank(message)) {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("message", "选课成功，请到“已选课件”查看已选择的课件！");
		}
		return SUCCESS;
	}

	public Rlgl100103Bean editHysData(Rlgl100103Bean rlgl100103Bean, Rlgl022003Bean rlgl022003Bean, String flg) {

		if ("1".equals(flg)) {
			// 课件id
			rlgl100103Bean.setCourse_id_hys(rlgl022003Bean.getCourse_id());
			// 课件名称
			rlgl100103Bean.setCourse_name_hys(rlgl022003Bean.getCourse_name());
			// 课件所属类别
			rlgl100103Bean.setCourse_catagory_hys(rlgl022003Bean.getCourse_catagory());
			rlgl100103Bean.setCourse_catagory_nm_hys(rlgl022003Bean.getCourse_catagory_nm());
			// 学科1
			if (!StringUtils.isBlank(rlgl022003Bean.getCourse_catagory())
					&& rlgl022003Bean.getCourse_catagory().length() == 6) {
				String course1 = "";
				String course2 = "";
				course1 = mtb02AdmService.getAdmName("212", rlgl022003Bean.getCourse_catagory().substring(0, 3));
				course2 = mtb02AdmService.getAdmName(rlgl022003Bean.getCourse_catagory().substring(0, 3),
						rlgl022003Bean.getCourse_catagory().substring(3));
				rlgl100103Bean.setCourse_catagory_nm(course1.concat("--").concat(course2));
			} else {
				rlgl100103Bean
						.setCourse_catagory_nm(mtb02AdmService.getAdmName("212", rlgl022003Bean.getCourse_catagory()));
			}
			// 学科2
			if (!StringUtils.isBlank(rlgl022003Bean.getCourse_catagory2())
					&& rlgl022003Bean.getCourse_catagory2().length() == 6) {
				String course1 = "";
				String course2 = "";
				course1 = mtb02AdmService.getAdmName("212", rlgl022003Bean.getCourse_catagory2().substring(0, 3));
				course2 = mtb02AdmService.getAdmName(rlgl022003Bean.getCourse_catagory2().substring(0, 3),
						rlgl022003Bean.getCourse_catagory2().substring(3));
				rlgl100103Bean.setCourse_catagory_nm2(course1.concat("--").concat(course2));
			} else {
				rlgl100103Bean.setCourse_catagory_nm2(
						mtb02AdmService.getAdmName("212", rlgl022003Bean.getCourse_catagory2()));
			}
			// 学科3
			if (!StringUtils.isBlank(rlgl022003Bean.getCourse_catagory3())
					&& rlgl022003Bean.getCourse_catagory3().length() == 6) {
				String course1 = "";
				String course2 = "";
				course1 = mtb02AdmService.getAdmName("212", rlgl022003Bean.getCourse_catagory3().substring(0, 3));
				course2 = mtb02AdmService.getAdmName(rlgl022003Bean.getCourse_catagory3().substring(0, 3),
						rlgl022003Bean.getCourse_catagory3().substring(3));
				rlgl100103Bean.setCourse_catagory_nm3(course1.concat("--").concat(course2));
			} else {
				rlgl100103Bean.setCourse_catagory_nm3(
						mtb02AdmService.getAdmName("212", rlgl022003Bean.getCourse_catagory3()));
			}
			if (rlgl100103Bean.getCourse_catagory_nm() == null) {
				rlgl100103Bean.setCourse_catagory_nm("");
			}
			if (rlgl100103Bean.getCourse_catagory_nm2() == null) {
				rlgl100103Bean.setCourse_catagory_nm2("");
			}
			if (rlgl100103Bean.getCourse_catagory_nm3() == null) {
				rlgl100103Bean.setCourse_catagory_nm3("");
			}
			// 所有学科
			if (!StringUtils.isBlank(rlgl022003Bean.getIsallcourse())) {
				if (rlgl022003Bean.getIsallcourse().equals("001")) {
					rlgl100103Bean.setCourse_catagory_nm_hys("所有学科");
				} else {
					rlgl100103Bean.setCourse_catagory_nm_hys(rlgl100103Bean.getCourse_catagory_nm() + "  "
							+ rlgl100103Bean.getCourse_catagory_nm2() + "  " + rlgl100103Bean.getCourse_catagory_nm3());
				}
			} else {
				rlgl100103Bean.setCourse_catagory_nm_hys(rlgl100103Bean.getCourse_catagory_nm() + "  "
						+ rlgl100103Bean.getCourse_catagory_nm2() + "  " + rlgl100103Bean.getCourse_catagory_nm3());
			}

			// 课件提供方
			rlgl100103Bean.setCourse_support_hys(rlgl022003Bean.getCourse_support());
			// 课件学分
			rlgl100103Bean.setXuefen_hys(rlgl022003Bean.getXuefen());
			// 课件负责人
			rlgl100103Bean.setCourse_expert_hys(rlgl022003Bean.getCourse_expert());
			// 课件负责单位
			rlgl100103Bean.setCourse_expert_unit_hys(rlgl022003Bean.getCourse_expert_unit());
			// 视频课程地址
			rlgl100103Bean.setCourse_url_hys(rlgl022003Bean.getCourse_url());
			// 课程图片地址
			rlgl100103Bean.setCourse_image_url_hys(rlgl022003Bean.getCourse_image_url());
			// 课件开始日期
			rlgl100103Bean.setBegin_date_hys(rlgl022003Bean.getBegin_date());
			// 课件结束日期
			rlgl100103Bean.setEnd_date_hys(rlgl022003Bean.getEnd_date());
			// 课程简介
			if (rlgl022003Bean.getCourse_explain().length() >= 80) {
				rlgl100103Bean.setCourse_explain_hys(rlgl022003Bean.getCourse_explain().substring(0, 80) + "...");
			} else {
				rlgl100103Bean.setCourse_explain(rlgl022003Bean.getCourse_explain());
			}
		}
		if ("2".equals(flg)) {
			// 课件id
			rlgl100103Bean.setCourse_id_hyw(rlgl022003Bean.getCourse_id());
			// 课件名称
			rlgl100103Bean.setCourse_name_hyw(rlgl022003Bean.getCourse_name());
			// 课件所属类别
			rlgl100103Bean.setCourse_catagory_hyw(rlgl022003Bean.getCourse_catagory());
			rlgl100103Bean.setCourse_catagory_nm_hyw(rlgl022003Bean.getCourse_catagory_nm());

			// 学科1
			if (!StringUtils.isBlank(rlgl022003Bean.getCourse_catagory())
					&& rlgl022003Bean.getCourse_catagory().length() == 6) {
				String course1 = "";
				String course2 = "";
				course1 = mtb02AdmService.getAdmName("212", rlgl022003Bean.getCourse_catagory().substring(0, 3));
				course2 = mtb02AdmService.getAdmName(rlgl022003Bean.getCourse_catagory().substring(0, 3),
						rlgl022003Bean.getCourse_catagory().substring(3));
				rlgl100103Bean.setCourse_catagory_nm(course1.concat("--").concat(course2));
			} else {
				rlgl100103Bean
						.setCourse_catagory_nm(mtb02AdmService.getAdmName("212", rlgl022003Bean.getCourse_catagory()));
			}
			// 学科2
			if (!StringUtils.isBlank(rlgl022003Bean.getCourse_catagory2())
					&& rlgl022003Bean.getCourse_catagory2().length() == 6) {
				String course1 = "";
				String course2 = "";
				course1 = mtb02AdmService.getAdmName("212", rlgl022003Bean.getCourse_catagory2().substring(0, 3));
				course2 = mtb02AdmService.getAdmName(rlgl022003Bean.getCourse_catagory2().substring(0, 3),
						rlgl022003Bean.getCourse_catagory2().substring(3));
				rlgl100103Bean.setCourse_catagory_nm2(course1.concat("--").concat(course2));
			} else {
				rlgl100103Bean.setCourse_catagory_nm2(
						mtb02AdmService.getAdmName("212", rlgl022003Bean.getCourse_catagory2()));
			}
			// 学科3
			if (!StringUtils.isBlank(rlgl022003Bean.getCourse_catagory3())
					&& rlgl022003Bean.getCourse_catagory3().length() == 6) {
				String course1 = "";
				String course2 = "";
				course1 = mtb02AdmService.getAdmName("212", rlgl022003Bean.getCourse_catagory3().substring(0, 3));
				course2 = mtb02AdmService.getAdmName(rlgl022003Bean.getCourse_catagory3().substring(0, 3),
						rlgl022003Bean.getCourse_catagory3().substring(3));
				rlgl100103Bean.setCourse_catagory_nm3(course1.concat("--").concat(course2));
			} else {
				rlgl100103Bean.setCourse_catagory_nm3(
						mtb02AdmService.getAdmName("212", rlgl022003Bean.getCourse_catagory3()));
			}
			if (rlgl100103Bean.getCourse_catagory_nm() == null) {
				rlgl100103Bean.setCourse_catagory_nm("");
			}
			if (rlgl100103Bean.getCourse_catagory_nm2() == null) {
				rlgl100103Bean.setCourse_catagory_nm2("");
			}
			if (rlgl100103Bean.getCourse_catagory_nm3() == null) {
				rlgl100103Bean.setCourse_catagory_nm3("");
			}
			// 所有学科
			if (!StringUtils.isBlank(rlgl022003Bean.getIsallcourse())) {
				if (rlgl022003Bean.getIsallcourse().equals("001")) {
					rlgl100103Bean.setCourse_catagory_nm_hyw("所有学科");
				} else {
					rlgl100103Bean.setCourse_catagory_nm_hyw(rlgl100103Bean.getCourse_catagory_nm() + "  "
							+ rlgl100103Bean.getCourse_catagory_nm2() + "  " + rlgl100103Bean.getCourse_catagory_nm3());
				}
			} else {
				rlgl100103Bean.setCourse_catagory_nm_hyw(rlgl100103Bean.getCourse_catagory_nm() + "  "
						+ rlgl100103Bean.getCourse_catagory_nm2() + "  " + rlgl100103Bean.getCourse_catagory_nm3());
			}

			// 课件提供方
			rlgl100103Bean.setCourse_support_hyw(rlgl022003Bean.getCourse_support());
			// 课件学分
			rlgl100103Bean.setXuefen_hyw(rlgl022003Bean.getXuefen());
			// 课件负责人
			rlgl100103Bean.setCourse_expert_hyw(rlgl022003Bean.getCourse_expert());
			// 课件负责单位
			rlgl100103Bean.setCourse_expert_unit_hyw(rlgl022003Bean.getCourse_expert_unit());
			// 视频课程地址
			rlgl100103Bean.setCourse_url_hyw(rlgl022003Bean.getCourse_url());
			// 课程图片地址
			rlgl100103Bean.setCourse_image_url_hyw(rlgl022003Bean.getCourse_image_url());
			// 课件开始日期
			rlgl100103Bean.setBegin_date_hyw(rlgl022003Bean.getBegin_date());
			// 课件结束日期
			rlgl100103Bean.setEnd_date_hyw(rlgl022003Bean.getEnd_date());
			// 课程简介
			if (rlgl022003Bean.getCourse_explain().length() >= 80) {
				rlgl100103Bean.setCourse_explain_hyw(rlgl022003Bean.getCourse_explain().substring(0, 80) + "...");
			} else {
				rlgl100103Bean.setCourse_explain(rlgl022003Bean.getCourse_explain());
			}
		}
		if ("3".equals(flg)) {
			// 课件id
			rlgl100103Bean.setCourse_id_hyw("");
			// 课件名称
			rlgl100103Bean.setCourse_name_hyw("");
			// 课件所属类别
			rlgl100103Bean.setCourse_catagory_hyw("");
			rlgl100103Bean.setCourse_catagory_nm_hyw("");
			// 课件提供方
			rlgl100103Bean.setCourse_support_hyw("");
			// 课件学分
			rlgl100103Bean.setXuefen_hyw(0);
			// 课件负责人
			rlgl100103Bean.setCourse_expert_hyw("");
			// 课件负责单位
			rlgl100103Bean.setCourse_expert_unit_hyw("");
			// 视频课程地址
			rlgl100103Bean.setCourse_url_hyw("");
			// 课程图片地址
			rlgl100103Bean.setCourse_image_url_hyw("");
			// 课件开始日期
			rlgl100103Bean.setBegin_date_hyw("");
			// 课件结束日期
			rlgl100103Bean.setEnd_date_hyw("");
			// 课程简介
			rlgl100103Bean.setCourse_explain_hyw("");
		}

		if ("4".equals(flg)) {
			// 课件id
			rlgl100103Bean.setCourse_id_hys("");
			// 课件名称
			rlgl100103Bean.setCourse_name_hys("");
			// 课件所属类别
			rlgl100103Bean.setCourse_catagory_hys("");
			rlgl100103Bean.setCourse_catagory_nm_hys("");
			// 课件提供方
			rlgl100103Bean.setCourse_support_hys("");
			// 课件学分
			rlgl100103Bean.setXuefen_hys(0);
			// 课件负责人
			rlgl100103Bean.setCourse_expert_hys("");
			// 课件负责单位
			rlgl100103Bean.setCourse_expert_unit_hys("");
			// 视频课程地址
			rlgl100103Bean.setCourse_url_hys("");
			// 课程图片地址
			rlgl100103Bean.setCourse_image_url_hys("");
			// 课件开始日期
			rlgl100103Bean.setBegin_date_hys("");
			// 课件结束日期
			rlgl100103Bean.setEnd_date_hys("");
			// 课程简介
			rlgl100103Bean.setCourse_explain_hys("");
		}

		return rlgl100103Bean;
	}

	public IRlgl100103Service getRlgl100103Service() {
		return rlgl100103Service;
	}

	public void setRlgl100103Service(IRlgl100103Service rlgl100103Service) {
		this.rlgl100103Service = rlgl100103Service;
	}

	/**
	 * @return the course_catagory
	 */
	public String getCourse_catagory() {
		return course_catagory;
	}

	/**
	 * @param course_catagory
	 *            the course_catagory to set
	 */
	public void setCourse_catagory(String course_catagory) {
		this.course_catagory = course_catagory;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id
	 *            the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the recordCount
	 */
	public int getRecordCount() {
		return recordCount;
	}

	/**
	 * @param recordCount
	 *            the recordCount to set
	 */
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	/**
	 * @return the txtInputPage
	 */
	public String getTxtInputPage() {
		return txtInputPage;
	}

	/**
	 * @param txtInputPage
	 *            the txtInputPage to set
	 */
	public void setTxtInputPage(String txtInputPage) {
		this.txtInputPage = txtInputPage;
	}

	/**
	 * @return the hdnCountOfPage
	 */
	public String getHdnCountOfPage() {
		return hdnCountOfPage;
	}

	/**
	 * @param hdnCountOfPage
	 *            the hdnCountOfPage to set
	 */
	public void setHdnCountOfPage(String hdnCountOfPage) {
		this.hdnCountOfPage = hdnCountOfPage;
	}

	public List<Rlgl022003Bean> getHywCourseList() {
		return hywCourseList;
	}

	public void setHywCourseList(List<Rlgl022003Bean> hywCourseList) {
		this.hywCourseList = hywCourseList;
	}

	public List<Rlgl022003Bean> getHysCourseList() {
		return hysCourseList;
	}

	public void setHysCourseList(List<Rlgl022003Bean> hysCourseList) {
		this.hysCourseList = hysCourseList;
	}

	public int getYxkcCount() {
		return yxkcCount;
	}

	public void setYxkcCount(int yxkcCount) {
		this.yxkcCount = yxkcCount;
	}

	public List<Rlgl100103Bean> getResultList() {
		return resultList;
	}

	public void setResultList(List<Rlgl100103Bean> resultList) {
		this.resultList = resultList;
	}

	public IMtb63CourseWareService getMtb63CourseWareService() {
		return mtb63CourseWareService;
	}

	public void setMtb63CourseWareService(IMtb63CourseWareService mtb63CourseWareService) {
		this.mtb63CourseWareService = mtb63CourseWareService;
	}

	public String getSysUrl() {
		return sysUrl;
	}

	public void setSysUrl(String sysUrl) {
		this.sysUrl = sysUrl;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public List<MTb02Adm> getKcThreeList() {
		return kcThreeList;
	}

	public void setKcThreeList(List<MTb02Adm> kcThreeList) {
		this.kcThreeList = kcThreeList;
	}

	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}

	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
	}

	public String getLeibie3() {
		return leibie3;
	}

	public void setLeibie3(String leibie3) {
		this.leibie3 = leibie3;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
