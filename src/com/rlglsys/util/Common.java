package com.rlglsys.util;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.lowagie.text.Cell;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.rlglsys.bean.DateYearMonthInfoBean;
import com.rlglsys.bean.Rlgl011007Bean;
import com.rlglsys.bean.Rlgl011008Bean;
import com.rlglsys.bean.Rlgl050101Bean;
import com.rlglsys.entity.Mtb76CreditYearSet;
import com.rlglsys.service.IRlgl050101Service;

public class Common {
	
	/**
	 * 生成naviList
	 * @throws Exception
	 */
	public List<String> setNaviIdList() throws Exception{
		List<String>naviIdList = new ArrayList<String>();

		naviIdList.add("navi052");
		naviIdList.add("navi053");
		naviIdList.add("navi054");
		naviIdList.add("navi055");
		naviIdList.add("navi056");
		naviIdList.add("navi066");
		naviIdList.add("navi067");
		naviIdList.add("navi068");
		naviIdList.add("navi069");
		naviIdList.add("navi070");
		naviIdList.add("navi071");
		naviIdList.add("navi072");
		naviIdList.add("navi073");
		naviIdList.add("navi074");
		naviIdList.add("navi075");
		naviIdList.add("navi076");
		naviIdList.add("navi077");
		naviIdList.add("navi078");
		
		return naviIdList;
	}
    /**
     * 获得学分年度
     * @return 学分年度
     * @throws ParseException 
     */
    public int getCreditYear(String kbn) throws ParseException{
    	// 获得当前系统时间
    	int creditYear = 0;
    	String systemDate = DateTimeManager.getSystemDate14().substring(0,8);
    	String year = systemDate.substring(0, 4);
    	DateFormat df = new SimpleDateFormat("yyyyMMdd"); 
    	Date dt1 = new Date();
    	try{
    		dt1 = df.parse(systemDate);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    	// 6.30-下年7.1
    	if(kbn.equals("001")){
    		String date1 = year+"0630";
    		String date2 = (Integer.parseInt(year)+1)+"0701";
    		Date dt2 = df.parse(date1);
    		Date dt3 = df.parse(date2);
    		if(dt1.getTime() > dt2.getTime() && dt1.getTime() <= dt3.getTime()){
    			creditYear = Integer.parseInt(year);
    		} else if(dt1.getTime() <= dt2.getTime()){
    			creditYear = (Integer.parseInt(year)-1);
    		} else if(dt1.getTime() > dt2.getTime()){
    			creditYear = (Integer.parseInt(year)+1);
    		}
    	} else if(kbn.equals("002")){
    		creditYear = Integer.parseInt(year);
    	}
    	return creditYear;
    }
    
    /**
     * 获得当前学分年度
     * @return 学分年度
     * @throws Exception 
     */
    public int getNowCreditYear(String area_id,IRlgl050101Service rlgl050101Service) throws Exception{
    	Rlgl050101Bean rlgl050101 = new Rlgl050101Bean();
    	rlgl050101.setArea_id(area_id);
    	Mtb76CreditYearSet mtb76 = rlgl050101Service.getCreditYearInfo(rlgl050101);
    	String kbn = mtb76.getCredit_year_id();
    	// 获得当前系统时间
    	int creditYear = 0;
    	String systemDate = DateTimeManager.getSystemDate14().substring(0,8);
    	String year = systemDate.substring(0, 4);
    	DateFormat df = new SimpleDateFormat("yyyyMMdd"); 
    	Date dt1 = new Date();
    	try{
    		dt1 = df.parse(systemDate);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	
    	// 6.30-下年7.1
    	if(kbn.equals("001")){
    		String date2 = year+"0701";
    		String date1 = (Integer.parseInt(year)-1)+"0630";
    		Date dt2 = df.parse(date1);
    		Date dt3 = df.parse(date2);
    		if(dt1.getTime() > dt2.getTime() && dt1.getTime() <= dt3.getTime()){
    			creditYear = Integer.parseInt(year);
    		} else if(dt1.getTime() <= dt2.getTime()){
    			creditYear = (Integer.parseInt(year)-1);
    		} else if(dt1.getTime() > dt2.getTime()){
    			creditYear = (Integer.parseInt(year)+1);
    		}
    	} else if(kbn.equals("002")){
    		creditYear = Integer.parseInt(year);
    	}
    	return creditYear;
    }
    
	public Rlgl011007Bean editRlgl011007Bean(String unitStr) throws Exception
	{
		Rlgl011007Bean rlgl011007Bean = new Rlgl011007Bean();
		try {
			String [] objectArray = unitStr.split(",");
			String objectStr = "";
			for (int i = 0; i < objectArray.length; i++)
			{
				objectStr = objectArray[i];
				// 单位名称
				if ("unit_nm".equals(objectStr))
				{
					rlgl011007Bean.setUnit_nm("1");
				}
				// 单位照片
				if (objectStr != null && objectStr.contains("unit_img"))
				{
					rlgl011007Bean.setUnit_img("1");
				}
				// 机构代码证
				if (objectStr != null && objectStr.contains("organization_code"))
				{
					rlgl011007Bean.setOrganization_code("1");
				}
				// 所有制形式
				if ("unit_own".equals(objectStr))
				{
					rlgl011007Bean.setUnit_own("1");
				}
				// 盈利性质
				if ("earnings_nature".equals(objectStr))
				{
					rlgl011007Bean.setEarnings_nature("1");
				}
				// 经济类型
				if ("unit_economic_type".equals(objectStr))
				{
					rlgl011007Bean.setUnit_economic_type("1");
				}
				// 单位性质
				if ("unit_nature".equals(objectStr))
				{
					rlgl011007Bean.setUnit_nature("1");
				}
				// 单位性质二级
				if ("unit_nature_tow".equals(objectStr))
				{
					rlgl011007Bean.setUnit_nature_tow("1");
				}
				// 业务隶属
				if ("unit_level".equals(objectStr))
				{
					rlgl011007Bean.setUnit_level("1");
				}
				// 行业
				if ("industry_plan".equals(objectStr))
				{
					rlgl011007Bean.setIndustry_plan("1");
				}
				// 单位管理级别
				if ("unit_manage_scale".equals(objectStr))
				{
					rlgl011007Bean.setUnit_manage_scale("1");
				}
				if ("unit_manage_scale_tow".equals(objectStr))
				{
					rlgl011007Bean.setUnit_manage_scale_tow("1");
				}
				
				// 单位级别
				if ("unit_scale".equals(objectStr))
				{
					rlgl011007Bean.setUnit_scale("1");
				}
				// 批准文号
				if ("license_no".equals(objectStr))
				{
					rlgl011007Bean.setLicense_no("1");
				}
				// 医院等级
				if ("hospital_grade".equals(objectStr))
				{
					rlgl011007Bean.setHospital_grade("1");
				}
				// 医院等级二级
				if ("hospital_grade_tow".equals(objectStr))
				{
					rlgl011007Bean.setHospital_grade_tow("1");
				}
				// 主管部门
				if ("unit_lower".equals(objectStr))
				{
					rlgl011007Bean.setUnit_lower("1");
				}
				// 编制人数
				if ("establishment_num".equals(objectStr))
				{
					rlgl011007Bean.setEstablishment_num("1");
				}
				// 机构代码证号
				if ("organization_no".equals(objectStr))
				{
					rlgl011007Bean.setOrganization_no("1");
				}
				// 区域划分province city zone
				
				// 机构层次
				if ("agency_level".equals(objectStr))
				{
					rlgl011007Bean.setAgency_level("1");
				}
				// 机构层次二级
				if ("agency_level_tow".equals(objectStr))
				{
					rlgl011007Bean.setAgency_level_tow("1");
				}
				// 机构层次三级
				if ("agency_level_three".equals(objectStr))
				{
					rlgl011007Bean.setAgency_level_three("1");
				}
				// 机构划分
				if ("agency_plan".equals(objectStr))
				{
					rlgl011007Bean.setAgency_plan("1");
				}
				// 系统类别
				if ("system_sort".equals(objectStr))
				{
					rlgl011007Bean.setSystem_sort("1");
				}
				// 系统类别二级
				if ("system_sort_two".equals(objectStr))
				{
					rlgl011007Bean.setSystem_sort_two("1");
				}
				// 行业划分
				if ("industry_divide".equals(objectStr))
				{
					rlgl011007Bean.setIndustry_divide("1");
				}
				// 行业划分二级
				if ("industry_divide_two".equals(objectStr))
				{
					rlgl011007Bean.setIndustry_divide_two("1");
				}
				// 详细地址
				if ("unit_address".equals(objectStr))
				{
					rlgl011007Bean.setUnit_address("1");
				}
				// 职责范围
				if ("offical_scope".equals(objectStr))
				{
					rlgl011007Bean.setOffical_scope("1");
				}
				// 单位简介
				if ("unit_pro".equals(objectStr))
				{
					rlgl011007Bean.setUnit_pro("1");
				}

				// 区域划分
				if ("area_id".equals(objectStr))
				{
					rlgl011007Bean.setArea_id("1");
				}
				// 单位管理级别名称
				if ("unit_manage_scale_nm".equals(objectStr))
				{
					rlgl011007Bean.setUnit_manage_scale_nm("1");
				}
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return rlgl011007Bean;
	}
	
	public Rlgl011008Bean editRlgl011008Bean(String personnelStr) throws Exception
	{
		Rlgl011008Bean rlgl011008Bean = new Rlgl011008Bean();
		try {
			String [] objectArray = personnelStr.split(",");
			String objectStr = "";
			for (int i = 0; i < objectArray.length; i++)
			{
				objectStr = objectArray[i];
				// 姓名
				if ("init_personnel_personnel_nm".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_nm("1");
					rlgl011008Bean.setBisic("1");
				}
				// 曾用名
				if ("init_personnel_personnel_beforename".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_beforename("1");
					rlgl011008Bean.setBisic("1");
				}
				// 性别
				if ("init_personnel_personnel_gender".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_gender("1");
					rlgl011008Bean.setBisic("1");
				}
				// 出生日期
				if ("init_personnel_personnel_birthday".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_birthday("1");
					rlgl011008Bean.setBisic("1");
				}
				// 籍贯
				if ("init_personnel_personnel_hometown".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_hometown("1");
					rlgl011008Bean.setBisic("1");
				}
				// 民族
				if ("init_personnel_personnel_ethnic".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_ethnic("1");
					rlgl011008Bean.setBisic("1");
				}
				// 参加工作时间
				if ("init_personnel_personnel_worktime".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_worktime("1");
					rlgl011008Bean.setBisic("1");
				}
				// 户口所在地
				if ("init_personnel_personnel_account_location".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_account_location("1");
					rlgl011008Bean.setBisic("1");
				}
				// 健康状况
				if ("init_personnel_personnel_health_status".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_health_status("1");
					rlgl011008Bean.setBisic("1");
				}
				// 个人身份
				if ("init_personnel_personnel_personal_identification".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_personal_identification("1");
					rlgl011008Bean.setBisic("1");
				}
				// 用工形式
				if ("init_personnel_personnel_employment_forms".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_employment_forms("1");
					rlgl011008Bean.setBisic("1");
				}
				// 政治面貌
				if ("init_personnel_personnel_political_landscape".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_political_landscape("1");
					rlgl011008Bean.setBisic("1");
				}
				// 入党(团)时间
				if ("init_personnel_personnel_joinpartytime".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_joinpartytime("1");
					rlgl011008Bean.setBisic("1");
				}
				// 婚姻状况
				if ("init_personnel_personnel_marital_status".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_marital_status("1");
					rlgl011008Bean.setBisic("1");
				}
				// 爱好特长
				if ("init_personnel_personnel_hobbies".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_hobbies("1");
					rlgl011008Bean.setBisic("1");
				}
				// 外语水平
				if ("init_personnel_personnel_foreignlanguage_level".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_foreignlanguage_level("1");
					rlgl011008Bean.setBisic("1");
				}
				// 在编状态
				if ("init_personnel_personnel_regular".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_regular("1");
					rlgl011008Bean.setBisic("1");
				}
				// 存档单位
				if ("init_personnel_personnel_archive_unit".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_archive_unit("1");
					rlgl011008Bean.setBisic("1");
				}
				// 档案位置
				if ("init_personnel_personnel_filelocation".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_filelocation("1");
					rlgl011008Bean.setBisic("1");
				}
				// 岗位状态
				if ("init_personnel_personnel_status".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_status("1");
					rlgl011008Bean.setBisic("1");
				}
				// 年度审核判定
				if ("init_personnel_personnel_check".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_check("1");
					rlgl011008Bean.setBisic("1");
				}
				// 所在单位
				if ("init_personnel_personnel_unit".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_unit("1");
					rlgl011008Bean.setBisic("1");
				}
				// 家庭住址
				if ("province".equals(objectStr) || "city".equals(objectStr) || "zone".equals(objectStr) || "init_personnel_personnel_address".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_area("1");
					rlgl011008Bean.setBisic("1");
				}
				// 联系信息
				// 电子邮件
				if ("init_personnel_personnel_email".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_email("1");
					rlgl011008Bean.setBisic("1");
				}
				// 移动电话
				if ("init_personnel_personnel_tel".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_tel("1");
					rlgl011008Bean.setBisic("1");
				}
				// 单位电话
				if ("init_personnel_personnel_officetel".equals(objectStr))
				{
					rlgl011008Bean.setPersonnel_officetel("1");
					rlgl011008Bean.setBisic("1");
				}
				
				// 资格信息
				// 资格证编号
				if (objectStr != null && (objectStr.contains("rlgl010306PractitionersInfoList") || 
						objectStr.contains("pratypelist") || objectStr.contains("pralevellist")))
				{
					rlgl011008Bean.setPersonnelPractitioners("1");
				}
				// 执业信息
				if (objectStr != null && (objectStr.contains("zyzbh") || objectStr.contains("zyzfzjg") || 
						objectStr.contains("zyzfzrq") || objectStr.contains("practypelist") || objectStr.contains("praclevellist")
						|| objectStr.contains("proctypelist") || objectStr.contains("zyfw1") || objectStr.contains("zyfw2")
						|| objectStr.contains("rlgl010306PracticeInfoList") || objectStr.contains("zydd1") || objectStr.contains("zydd2")))
				{
					rlgl011008Bean.setPersonnelPractice("1");
				}
				// 专业技术职务信息
				if (objectStr != null && (objectStr.contains("onelevel") || objectStr.contains("twolevel") || 
						objectStr.contains("threelevel") || objectStr.contains("zyjszw_name") || objectStr.contains("zyjszw_spjg")
						|| objectStr.contains("zyjszw_qdsj")))
				{
					rlgl011008Bean.setProfessionalTechnical("1");
				}
				// 行政职务信息
				if (objectStr != null && objectStr.contains("rlgl010306JobInfoList"))
				{
					rlgl011008Bean.setAdministrativePost("1");
				}
				// 社会关系
				if (objectStr != null && objectStr.contains("rlgl010306SocialInfoList"))
				{
					rlgl011008Bean.setSocialRelations("1");
				}
				// 教育经历
				if (objectStr != null && objectStr.contains("rlgl010306EduInfoList_"))
				{
					rlgl011008Bean.setLearningExperience("1");
				}
				// 工作经历
				if (objectStr != null && objectStr.contains("rlgl010306WorkInfoList"))
				{
					rlgl011008Bean.setPersonnelWork("1");
				}
				// 导师信息
				if (objectStr != null && (objectStr.contains("dslb") || objectStr.contains("dsxm") || objectStr.contains("szdx")
						 || objectStr.contains("yjfx")))
				{
					rlgl011008Bean.setPersonnelTutor("1");
				}
				// 奖惩情况
				if (objectStr != null && objectStr.contains("rlgl010306PersonnelrewardspunishmentInfoList"))
				{
					rlgl011008Bean.setPersonnelrewardspunishment("1");
				}
				// 出国情况
				if (objectStr != null && objectStr.contains("rlgl010306PersonnelgoabroadInfoList"))
				{
					rlgl011008Bean.setPersonnelgoabroad("1");
				}
				// 党派信息
				if (objectStr != null && objectStr.contains("rlgl010306PartisanInfoList"))
				{
					rlgl011008Bean.setPersonnelPartisan("1");
				}
				// 档案信息
				if (objectStr != null && objectStr.contains("rlgl010306PersonnelrecordsInfoList"))
				{
					rlgl011008Bean.setPersonnelrecords("1");
				}
				// 荣誉奖励
				if (objectStr != null && objectStr.contains("rlgl010306PersonnelawardedhonorInfoList"))
				{
					rlgl011008Bean.setPersonnelawardedhonor("1");
				}
				// 人事保险
				if (objectStr != null && objectStr.contains("rlgl010306PersonnelinsurersInfoList"))
				{
					rlgl011008Bean.setPersonnelinsurers("1");
				}
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return rlgl011008Bean;
	}
	
	public List<DateYearMonthInfoBean> getYearList(){
		List<DateYearMonthInfoBean> yearList = new ArrayList<DateYearMonthInfoBean>();
		try {
			String year = DateTimeManager.getSystemDate14().substring(0, 4);
			String startYear = String.valueOf(Integer.valueOf(year) - 15);
            String endYear = String.valueOf(Integer.valueOf(year) + 10);
            DateYearMonthInfoBean yearBean = new DateYearMonthInfoBean();
            for (int i = Integer.valueOf(startYear); i <= Integer.valueOf(endYear); i++)
            {
            	yearBean = new DateYearMonthInfoBean();
            	yearBean.setYear(String.valueOf(i));
            	yearBean.setYearName(String.valueOf(i) + "年");
            	yearList.add(yearBean);
            }
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return yearList;
	}
	
	public List<DateYearMonthInfoBean> getMonthList(){
		List<DateYearMonthInfoBean> monthList = new ArrayList<DateYearMonthInfoBean>();
		try {
            DateYearMonthInfoBean yearBean = new DateYearMonthInfoBean();
            String month = "";
            String monthName = "";
            for (int i = 1; i <= 12; i++)
            {
            	yearBean = new DateYearMonthInfoBean();
            	
            	if (i < 10)
            	{
            		month = "0" + String.valueOf(i);
            		monthName = "0" + String.valueOf(i) + "月";
            		
            	} else {
            		month = String.valueOf(i);
            		monthName = String.valueOf(i) + "月";
            	}
            	yearBean.setMonth(month);
            	yearBean.setMonthName(monthName);
            	monthList.add(yearBean);
            }
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return monthList;
	}
	
	/**
	 * 编辑是否
	 * @param strId
	 * @return
	 * @throws Exception
	 */
	public String editStrForYesNo(String strId) throws Exception
	{
		String strNm = "";
		try {
			if ("1".equals(strId))
			{
				strNm = "是";
			}
			if ("0".equals(strId))
			{
				strNm = "否";
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return strNm;
	}
	
	/**
	 * 编辑表格内容对齐方式
	 * @param nero
	 * @param fontChinese
	 * @param cellFlg
	 * @return
	 */
	public Cell editTableCellCenter (String nero, Font fontChinese, String cellFlg){
		Cell new_cell = null;
		try {
			new_cell = new Cell(new Paragraph(nero,fontChinese));
			// 居中
			if ("1".equals(cellFlg))
			{
				new_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			}
			// 居左
			if ("2".equals(cellFlg))
			{
				new_cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			}
			// 居右
			if ("3".equals(cellFlg))
			{
				new_cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			}
			
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return new_cell;
	}
	
	/**
	 * 获取路径
	 * @return
	 */
	public String getGraphPath()
	{
		String filePath = "";
		try {
			 CommonManager common = new CommonManager();
	     	 filePath = common.getFilePath("graph_image");
	 		 filePath = ServletActionContext.getServletContext().getRealPath(filePath) + File.separator;
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return filePath;
	}
	
	/**
	 * 获取路径
	 * @return
	 */
	public String getPageGraphPath()
	{
		String filePath = "";
		try {
			 CommonManager common = new CommonManager();
	     	 filePath = common.getFilePath("page_graph_image");
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return filePath;
	}
	
	/**
	 * 获取路径
	 * @return
	 */
	public String getLearnOnlineImagePath()
	{
		String filePath = "";
		try {
			 CommonManager common = new CommonManager();
	     	 filePath = common.getFilePath("learnonline");
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return filePath;
	}
	
	public String bintoascii(byte []bySourceByte)
    {
        int len,i;
        byte tb;
        char high,tmp,low;
        String result = new String();

        len = bySourceByte.length;

        for(i = 0; i< len;i ++)
        {
            tb = bySourceByte[i];

            tmp = (char)((tb >>> 4) & 0x000f);
            if(tmp >= 10)
                high = (char)('a' + tmp - 10);
            else
                high = (char)('0' + tmp);

            result += high;

            tmp =( char)(tb & 0x000f);
            if(tmp >= 10)
                low = (char)('a' + tmp - 10);
            else
                low = (char)('0' + tmp);

            result += low;
        }

        return result;
    }
	
	public String getDateTimeSring()
    {
       String _dateTimeString = "";
       Calendar cal = Calendar.getInstance();
       SimpleDateFormat formatter = new SimpleDateFormat("%yyyy%MM%dd%HH%mm%ss");
       _dateTimeString = formatter.format(cal.getTime());

       return _dateTimeString;
    }
	
	/**
	   * 获取现在时间
	   * 
	   * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	   */
	public String getNowDate() {
	   Date currentTime = new Date();
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String dateString = formatter.format(currentTime);

	   return dateString;
	}

}
