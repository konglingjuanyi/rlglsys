package com.rlglsys.action.hiringmanage.personmanage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.rlglsys.base.BaseAction;
import com.rlglsys.base.excel.ReadExcel;
import com.rlglsys.base.excel.UploadFile;
import com.rlglsys.entity.MTb02Adm;
import com.rlglsys.entity.MTb20Area;
import com.rlglsys.entity.Mtb04Unit;
import com.rlglsys.entity.Mtb12Personnel;
import com.rlglsys.entity.Mtb13PersonnelPartisanInfo;
import com.rlglsys.entity.Mtb14PersonnelWorkInfo;
import com.rlglsys.entity.Mtb15PersonnelEduInfo;
import com.rlglsys.entity.Mtb16PersonnelSocialInfo;
import com.rlglsys.entity.Mtb17PersonnelJobInfo;
import com.rlglsys.entity.Mtb18PersonnelProfessionalInfo;
import com.rlglsys.entity.Mtb19PersonnelPractitionersInfo;
import com.rlglsys.entity.Mtb48Ection;
import com.rlglsys.entity.Mtb59PersonnelTutorInfo;
import com.rlglsys.entity.Mtb77PersonnelPracticeInfo;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IMTb04UnitService;
import com.rlglsys.service.IMTb20AreaService;
import com.rlglsys.service.IMtb48ectionService;

public class Rlgl010306JishuChangeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private IMTb20AreaService mtb20AreaService;
	private IMTb04UnitService mtb04UnitService;
	private IMtb48ectionService  mtb48ectionService; 
	private IMTb02AdmService  mtb02AdmService;
	// 单位地区信息
	private List<MTb20Area> unitprovincelist;
	private List<MTb20Area> unitcitylist;
	private List<MTb20Area> unitzonelist;
	// 单位信息
	private List<Mtb04Unit> unitlist;
	// 科室信息
	private List<Mtb48Ection> ectionlist;
	// 地区信息
	private List<MTb20Area> provincelist;
	private List<MTb20Area> citylist;
	private List<MTb20Area> zonelist;
	// 人员基本信息
	private Mtb12Personnel personnel;
	private String imgFile;
	// 人员基本信息-专业技术职务信息
	private Mtb18PersonnelProfessionalInfo rlgl010306ProfessionalInfo;
	private List<Mtb18PersonnelProfessionalInfo> rlgl010306ProfessionalInfoList;
	// 人员基本信息-行政职务信息
	private Mtb17PersonnelJobInfo rlgl010306JobInfo;
	private List<Mtb17PersonnelJobInfo> rlgl010306JobInfoList;
	// 人员基本信息-社会关系
	private Mtb16PersonnelSocialInfo rlgl010306SocialInfo;
	private List<Mtb16PersonnelSocialInfo> rlgl010306SocialInfoList;
	// 人员基本信息-教育经历
	private Mtb15PersonnelEduInfo rlgl010306EduInfo;
	private List<Mtb15PersonnelEduInfo> rlgl010306EduInfoList;
	// 人员基本信息-工作经历
	private Mtb14PersonnelWorkInfo rlgl010306WorkInfo;
	private List<Mtb14PersonnelWorkInfo> rlgl010306WorkInfoList;
	// 人员基本信息-党派信息
	private Mtb13PersonnelPartisanInfo rlgl010306PartisanInfo;
	private List<Mtb13PersonnelPartisanInfo> rlgl010306PartisanInfoList;
	// 人员基本信息-导师信息
	private Mtb59PersonnelTutorInfo rlgl010306TutorInfo;
	private List<Mtb59PersonnelTutorInfo> rlgl010306TutorInfoList;
	// 人员基本信息-资格信息
	private Mtb19PersonnelPractitionersInfo rlgl010306PractitionersInfo;
	private List<Mtb19PersonnelPractitionersInfo> rlgl010306PractitionersInfoList;
	// 人员基本信息-执业信息
	private Mtb77PersonnelPracticeInfo rlgl010306PracticeInfo;
	private List<Mtb77PersonnelPracticeInfo> rlgl010306PracticeInfoList;
	// 批量导入文档标识
	private String inputObject_id;
	// 行追加标识
	private String addFlg;
	// 行追加或者导入标识
	private String addOrInputFlg;
	// 变更标识
	private String change_value;
	private String objectArray;
	// 上次保存的变更字段
	private String saveChangeValue;
	// 文件信息
	private String filePath;
	private String fileProfessionalInfoFileName;
	private File fileProfessionalInfo;
	// 管理表下拉框
	private List<MTb02Adm> genderAdmlist;
	private List<MTb02Adm> ethnicAdmlist;
	private List<MTb02Adm> identificationAdmlist;
	private List<MTb02Adm> landscapeAdmlist;
	private List<MTb02Adm> formsAdmlist;
	private List<MTb02Adm> maritalAdmlist;
	private List<MTb02Adm> healthAdmlist;
	private List<MTb02Adm> statusAdmlist;
	private List<MTb02Adm> learninglist;
	private List<MTb02Adm> collegetypelist;
	private List<MTb02Adm> highestAdmlist;
	private List<MTb02Adm> checkAdmlist;
	private List<MTb02Adm> appointAdmlist;
	private List<MTb02Adm> regularlist;
	private List<MTb02Adm> educationalbglist;
	private List<MTb02Adm> degreelist;
	private List<MTb02Adm> onelevellist;
	private List<MTb02Adm> twolevellist;
	private List<MTb02Adm> threelevellist;
	private List<MTb02Adm> typelist;
	private List<MTb02Adm> levellist;
	private List<MTb02Adm> pralevellist;
	private List<MTb02Adm> pratypelist;
	private List<MTb02Adm> teachertypelist;
	private List<MTb02Adm> partisanlist;
	private List<MTb02Adm> preplist;
	private List<MTb02Adm> jobArealist;
	private List<MTb02Adm> protypelist;
	private List<MTb02Adm> proArealist;
	private List<MTb02Adm> positionlist;
	/**
	 * @return the positionlist
	 */
	public List<MTb02Adm> getPositionlist() {
		return positionlist;
	}
	/**
	 * @param positionlist the positionlist to set
	 */
	public void setPositionlist(List<MTb02Adm> positionlist) {
		this.positionlist = positionlist;
	}
	/**
	 * @return the proArealist
	 */
	public List<MTb02Adm> getProArealist() {
		return proArealist;
	}
	/**
	 * @param proArealist the proArealist to set
	 */
	public void setProArealist(List<MTb02Adm> proArealist) {
		this.proArealist = proArealist;
	}
	/**
	 * @return the protypelist
	 */
	public List<MTb02Adm> getProtypelist() {
		return protypelist;
	}
	/**
	 * @param protypelist the protypelist to set
	 */
	public void setProtypelist(List<MTb02Adm> protypelist) {
		this.protypelist = protypelist;
	}
	@Override
	protected String doExecute() throws Exception {
		try {
			// TODO Auto-generated method stub
						String province =personnel.getPersonnel_province();
						String city =personnel.getPersonnel_city();
						String unitprovince =personnel.getPersonnel_unit_province();
						String unitcity =personnel.getPersonnel_unit_city();
						String unitzone =personnel.getPersonnel_unit_zone();
						String unit =personnel.getPersonnel_unit();
						String unit_area_id="";
						// 获得省下拉列表
						provincelist=mtb20AreaService.getAreaProvince();
						unitprovincelist=mtb20AreaService.getAreaProvince();
						// 获得市空的下拉列表
						citylist=mtb20AreaService.getAreaCity("00");
						unitcitylist=mtb20AreaService.getAreaCity("00");
						// 获得县空的下拉列表
						zonelist=mtb20AreaService.getAreaZone("00","00");
						unitzonelist=mtb20AreaService.getAreaZone("00","00");
						// 获得单位空的下拉列表
						unitlist=mtb04UnitService.getUnitList("00");
						unitlist.clear();
						citylist.clear();
						zonelist.clear();
						unitcitylist.clear();
						unitzonelist.clear();
						// 获得科室空的下拉列表
						ectionlist=mtb48ectionService.getEctionList("00");
						ectionlist.clear();
						// 住址地区联动
						if(!city.equals("-1")){
							// 获得县下拉列表
							zonelist=mtb20AreaService.getAreaZone(province,city);
							// 获得市下拉列表
							citylist=mtb20AreaService.getAreaCity(province);
						}else if(!province.equals("-1")){
							// 获得市下拉列表
							citylist=mtb20AreaService.getAreaCity(province);
						}
						// 单位地区id
						if(!unitprovince.equals("-1")){
							unit_area_id=unitprovince;
							if(!unitcity.equals("-1")){
								unit_area_id=unit_area_id.concat(unitcity);
								if(!unitzone.equals("-1")){
									unit_area_id=unit_area_id.concat(unitzone);
								}
							}
							// 获得单位下拉列表
							unitlist=mtb04UnitService.getUnitList(unit_area_id);
						}
						// 单位地区联动
						if(!unit.equals("-1")){
							// 单位联动
							// 获得科室下拉列表
							ectionlist=mtb48ectionService.getEctionList(unit);
							// 获得县下拉列表
							unitzonelist=mtb20AreaService.getAreaZone(unitprovince,unitcity);
							// 获得市下拉列表
							unitcitylist=mtb20AreaService.getAreaCity(unitprovince);
						}else if(!unitzone.equals("-1")){
							// 县联动
							// 获得单位下拉列表
							unitlist=mtb04UnitService.getUnitList(unit_area_id);
							// 获得县下拉列表
							unitzonelist=mtb20AreaService.getAreaZone(unitprovince,unitcity);
							// 获得市下拉列表
							unitcitylist=mtb20AreaService.getAreaCity(unitprovince);
						}else if(!unitcity.equals("-1")){
							// 市联动
							// 获得县下拉列表
								unitzonelist=mtb20AreaService.getAreaZone(unitprovince,unitcity);
							// 获得市下拉列表
								unitcitylist=mtb20AreaService.getAreaCity(unitprovince);
						}else if(!unitprovince.equals("-1")){
							// 省联动
							// 获得市下拉列表
							unitcitylist=mtb20AreaService.getAreaCity(unitprovince);
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
						// 执业类别 下拉列表
						pratypelist=mtb02AdmService.getAdmInfo("008");
						// 导师类别 下拉列表
						teachertypelist=mtb02AdmService.getAdmInfo("099");
						// 党派名称下拉列表
						partisanlist=mtb02AdmService.getAdmInfo("102");
						// 正式、预备下拉列表
						preplist=mtb02AdmService.getAdmInfo("103");
						// 执业范围下拉列表
						jobArealist=mtb02AdmService.getAdmInfo("121");
						// 专业类别 下拉列表
						protypelist=mtb02AdmService.getAdmInfo("008");
						// 医师执业范围下拉列表(9.28新追加)
						proArealist=mtb02AdmService.getAdmInfo("134");
						// 行政职务名称
						positionlist=mtb02AdmService.getAdmInfo("181");
						// 批量添加
						int listCount=0;
						// 人员基本信息-专业技术职务信息
						if("addFlg1".equals(addFlg)){

							// 已经添加过的标记删除
							listCount =rlgl010306ProfessionalInfoList.size();
							for (int i = 0; i<listCount;i++){
								rlgl010306ProfessionalInfoList.get(i).setAddFlag("0");
							}
							addOrInputFlg="Flg1";
							rlgl010306ProfessionalInfo=new Mtb18PersonnelProfessionalInfo();
							rlgl010306ProfessionalInfo.setAddFlag("1");
							rlgl010306ProfessionalInfoList.add(rlgl010306ProfessionalInfo);
						}
						
						// 导入excel
						String directory = "/file";
						String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
						// 人员基本信息-专业技术职务信息
						if("rlgl010306ProfessionalInfoList".equals(inputObject_id)){

							// 已经添加过的标记删除
							listCount =rlgl010306ProfessionalInfoList.size();
							for (int i = 0; i<listCount;i++){
								rlgl010306ProfessionalInfoList.get(i).setAddFlag("0");
							}
							addOrInputFlg="Flg1";
							File target = UploadFile.Upload(fileProfessionalInfo, fileProfessionalInfoFileName,targetDirectory);
							List<List<Object>> list = new LinkedList<List<Object>>();
							list = ReadExcel.readExcel(target,1,0);
							if (list != null && list.size() > 0)
							{
								List<Object> listObject = new LinkedList<Object>();
								for (int i = 0; i < list.size(); i++)
								{
									rlgl010306ProfessionalInfo = new Mtb18PersonnelProfessionalInfo();
									
									listObject = list.get(i);
									for (int k = 0; k < listObject.size(); k++)
									{
										switch (k)
										{
											  case 0:
											    rlgl010306ProfessionalInfo.setOnelevel((listObject.get(k).toString()+ "       ").substring(0, 2));
												rlgl010306ProfessionalInfo.setTwolevel((listObject.get(k).toString()+ "       ").substring(2, 4));
												rlgl010306ProfessionalInfo.setThreelevel((listObject.get(k).toString()+ "       ").substring(4, 7));
												rlgl010306ProfessionalInfo.setLevel(listObject.get(k).toString());
												break;
											  case 1:
												  rlgl010306ProfessionalInfo.setName(listObject.get(k).toString());
												  break;
											  case 2:
												  rlgl010306ProfessionalInfo.setOriginal(listObject.get(k).toString());
												  break;
											  case 3:
												  rlgl010306ProfessionalInfo.setGet_time(listObject.get(k).toString());
												  break;
										}
									}
									rlgl010306ProfessionalInfo.setPersonnel_id(personnel.getPersonnel_id());
									rlgl010306ProfessionalInfo.setAddFlag("1");
									rlgl010306ProfessionalInfoList.add(rlgl010306ProfessionalInfo);
								}
							}
							// 空白行删除
							listCount =rlgl010306ProfessionalInfoList.size();
							for (int i = 0; i<listCount;i++){
								if(0==rlgl010306ProfessionalInfoList.size()){
									break;
								}
								if(i==rlgl010306ProfessionalInfoList.size()){
									break;
								}
								if(rlgl010306ProfessionalInfoList.get(i) == null){
									rlgl010306ProfessionalInfoList.remove(i);
									i--;
									continue;
								}
								if("".equals(rlgl010306ProfessionalInfoList.get(i).getOnelevel())
								   && "".equals(rlgl010306ProfessionalInfoList.get(i).getName())
								   && "".equals(rlgl010306ProfessionalInfoList.get(i).getOriginal())
								   && "".equals(rlgl010306ProfessionalInfoList.get(i).getGet_time())
								   ){
									rlgl010306ProfessionalInfoList.remove(i);
									i--;
								}
							}
						}
						// 人员基本信息-行政职务信息
						if("addFlg2".equals(addFlg)){
							// 已经添加过的标记删除
							listCount =rlgl010306JobInfoList.size();
							for (int i = 0; i<listCount;i++){
								rlgl010306JobInfoList.get(i).setAddFlag("0");
							}
							addOrInputFlg="Flg2";
							rlgl010306JobInfo=new Mtb17PersonnelJobInfo();
							rlgl010306JobInfo.setAddFlag("1");
							rlgl010306JobInfoList.add(rlgl010306JobInfo);
						}
						if("rlgl010306JobInfoList".equals(inputObject_id)){
							// 已经添加过的标记删除
							listCount =rlgl010306JobInfoList.size();
							for (int i = 0; i<listCount;i++){
								rlgl010306JobInfoList.get(i).setAddFlag("0");
							}
							addOrInputFlg="Flg2";
							File target = UploadFile.Upload(fileProfessionalInfo, fileProfessionalInfoFileName,targetDirectory);
							List<List<Object>> list = new LinkedList<List<Object>>();
							list = ReadExcel.readExcel(target,1,1);
							if (list != null && list.size() > 0)
							{
								List<Object> listObject = new LinkedList<Object>();
								for (int i = 0; i < list.size(); i++)
								{
									rlgl010306JobInfo = new Mtb17PersonnelJobInfo();
									
									listObject = list.get(i);
									for (int k = 0; k < listObject.size(); k++)
									{
										switch (k)
										{
											  case 0:
												  rlgl010306JobInfo.setPosition_nm(listObject.get(k).toString());
												break;
											  case 1:
												  rlgl010306JobInfo.setMode(listObject.get(k).toString());
												  break;
											  case 2:
												  rlgl010306JobInfo.setType(listObject.get(k).toString());
												  break;
											  case 3:
												  rlgl010306JobInfo.setLevel(listObject.get(k).toString());
												  break;
											  case 4:
												  rlgl010306JobInfo.setAppoint_time(listObject.get(k).toString());
												  break;
											  case 5:
												  rlgl010306JobInfo.setAppoint_no(listObject.get(k).toString());
												  break;
										}
									}
									rlgl010306JobInfo.setPersonnel_id(personnel.getPersonnel_id());
									rlgl010306JobInfo.setAddFlag("1");
									rlgl010306JobInfoList.add(rlgl010306JobInfo);
								}
							}
							// 空白行删除
							listCount =rlgl010306JobInfoList.size();
							for (int i = 0; i<listCount;i++){
								if(0==rlgl010306JobInfoList.size()){
									break;
								}
								if(i==rlgl010306JobInfoList.size()){
									break;
								}
								if(rlgl010306JobInfoList.get(i) == null){
									rlgl010306JobInfoList.remove(i);
									i--;
									continue;
								}
								if("".equals(rlgl010306JobInfoList.get(i).getPosition_nm())
								   && "".equals(rlgl010306JobInfoList.get(i).getMode())
								   && "".equals(rlgl010306JobInfoList.get(i).getType()) 
								   && "".equals(rlgl010306JobInfoList.get(i).getLevel())
								   && "".equals(rlgl010306JobInfoList.get(i).getAppoint_time())
								   && "".equals(rlgl010306JobInfoList.get(i).getAppoint_no())){
									rlgl010306JobInfoList.remove(i);
									i--;
								}
							}
						}
						// 人员基本信息-社会关系
						if("addFlg3".equals(addFlg)){
							// 已经添加过的标记删除
							listCount =rlgl010306SocialInfoList.size();
							for (int i = 0; i<listCount;i++){
								rlgl010306SocialInfoList.get(i).setAddFlag("0");
							}
							addOrInputFlg="Flg3";
							rlgl010306SocialInfo=new Mtb16PersonnelSocialInfo();
							rlgl010306SocialInfo.setAddFlag("1");
							rlgl010306SocialInfoList.add(rlgl010306SocialInfo);
						}
						if("rlgl010306SocialInfoList".equals(inputObject_id)){
							// 已经添加过的标记删除
							listCount =rlgl010306SocialInfoList.size();
							for (int i = 0; i<listCount;i++){
								rlgl010306SocialInfoList.get(i).setAddFlag("0");
							}
							addOrInputFlg="Flg3";
							File target = UploadFile.Upload(fileProfessionalInfo, fileProfessionalInfoFileName,targetDirectory);
							List<List<Object>> list = new LinkedList<List<Object>>();
							list = ReadExcel.readExcel(target,1,2);
							if (list != null && list.size() > 0)
							{
								List<Object> listObject = new LinkedList<Object>();
								for (int i = 0; i < list.size(); i++)
								{
									rlgl010306SocialInfo = new Mtb16PersonnelSocialInfo();
									
									listObject = list.get(i);
									for (int k = 0; k < listObject.size(); k++)
									{
										switch (k)
										{
											  case 0:
												  rlgl010306SocialInfo.setRelationship(listObject.get(k).toString());
												break;
											  case 1:
												  rlgl010306SocialInfo.setName(listObject.get(k).toString());
												  break;
											  case 2:
												  rlgl010306SocialInfo.setBirthday(listObject.get(k).toString());
												  break;
											  case 3:
												  rlgl010306SocialInfo.setPolitical_landscape(listObject.get(k).toString());
												  break;
											  case 4:
												  rlgl010306SocialInfo.setWorkunit(listObject.get(k).toString());
												  break;
											  case 5:
												  rlgl010306SocialInfo.setPosition(listObject.get(k).toString());
												  break;
											  case 6:
												  rlgl010306SocialInfo.setTel(listObject.get(k).toString());
												  break;
										}
									}
									rlgl010306SocialInfo.setPersonnel_id(personnel.getPersonnel_id());
									rlgl010306SocialInfo.setAddFlag("1");
									rlgl010306SocialInfoList.add(rlgl010306SocialInfo);
								}
							}
							// 空白行删除
							listCount =rlgl010306SocialInfoList.size();
							for (int i = 0; i<listCount;i++){
								if(0==rlgl010306SocialInfoList.size()){
									break;
								}
								if(i==rlgl010306SocialInfoList.size()){
									break;
								}
								if(rlgl010306SocialInfoList.get(i) == null){
									rlgl010306SocialInfoList.remove(i);
									i--;
									continue;
								}
								if("".equals(rlgl010306SocialInfoList.get(i).getRelationship())
								   && "".equals(rlgl010306SocialInfoList.get(i).getName())
								   && "".equals(rlgl010306SocialInfoList.get(i).getBirthday())
								   && "".equals(rlgl010306SocialInfoList.get(i).getPolitical_landscape())
								   && "".equals(rlgl010306SocialInfoList.get(i).getWorkunit())
								   && "".equals(rlgl010306SocialInfoList.get(i).getPosition())
								   && "".equals(rlgl010306SocialInfoList.get(i).getTel())){
									rlgl010306SocialInfoList.remove(i);
									i--;
								}
							}
						}
						// 人员基本信息-教育经历
						if("addFlg4".equals(addFlg)){
							// 已经添加过的标记删除
							listCount =rlgl010306EduInfoList.size();
							for (int i = 0; i<listCount;i++){
								rlgl010306EduInfoList.get(i).setAddFlag("0");
							}
							addOrInputFlg="Flg4";
							rlgl010306EduInfo=new Mtb15PersonnelEduInfo();
							rlgl010306EduInfo.setAddFlag("1");
							rlgl010306EduInfoList.add(rlgl010306EduInfo);
						}
						if("rlgl010306EduInfoList".equals(inputObject_id)){
							// 已经添加过的标记删除
							listCount =rlgl010306EduInfoList.size();
							for (int i = 0; i<listCount;i++){
								rlgl010306EduInfoList.get(i).setAddFlag("0");
							}
							addOrInputFlg="Flg4";
							File target = UploadFile.Upload(fileProfessionalInfo, fileProfessionalInfoFileName,targetDirectory);
							List<List<Object>> list = new LinkedList<List<Object>>();
							list = ReadExcel.readExcel(target,1,3);
							if (list != null && list.size() > 0)
							{
								List<Object> listObject = new LinkedList<Object>();
								for (int i = 0; i < list.size(); i++)
								{
									rlgl010306EduInfo = new Mtb15PersonnelEduInfo();
									
									listObject = list.get(i);
									for (int k = 0; k < listObject.size(); k++)
									{
										switch (k)
										{
											  case 0:
												  rlgl010306EduInfo.setLearning_format(listObject.get(k).toString());
												break;
											  case 1:
												  rlgl010306EduInfo.setAdmission_time(listObject.get(k).toString());
												  break;
											  case 2:
												  rlgl010306EduInfo.setGraduation_time(listObject.get(k).toString());
												  break;
											  case 3:
												  rlgl010306EduInfo.setSchool(listObject.get(k).toString());
												  break;
											  case 4:
												  rlgl010306EduInfo.setCollege_type(listObject.get(k).toString());
												  break;
											  case 5:
												  rlgl010306EduInfo.setProfession(listObject.get(k).toString());
												  break;
											  case 6:
												  rlgl010306EduInfo.setProof_people(listObject.get(k).toString());
												  break;
											  case 7:
												  rlgl010306EduInfo.setEducational_bg(listObject.get(k).toString());
												  break;
											  case 8:
												  rlgl010306EduInfo.setDegree(listObject.get(k).toString());
												  break;
										}
									}
									rlgl010306EduInfo.setPersonnel_id(personnel.getPersonnel_id());
									rlgl010306EduInfo.setAddFlag("1");
									rlgl010306EduInfoList.add(rlgl010306EduInfo);
								}
							}
							// 空白行删除
							listCount =rlgl010306EduInfoList.size();
							for (int i = 0; i<listCount;i++){
								if(0==rlgl010306EduInfoList.size()){
									break;
								}
								if(i==rlgl010306EduInfoList.size()){
									break;
								}
								if(rlgl010306EduInfoList.get(i) == null){
									rlgl010306EduInfoList.remove(i);
									i--;
									continue;
								}
								if("".equals(rlgl010306EduInfoList.get(i).getLearning_format())
								   && "".equals(rlgl010306EduInfoList.get(i).getAdmission_time())
								   && "".equals(rlgl010306EduInfoList.get(i).getGraduation_time())
								   && "".equals(rlgl010306EduInfoList.get(i).getSchool())
								   && "".equals(rlgl010306EduInfoList.get(i).getCollege_type())
								   && "".equals(rlgl010306EduInfoList.get(i).getProof_people())
								   && "".equals(rlgl010306EduInfoList.get(i).getEducational_bg())
								   && "".equals(rlgl010306EduInfoList.get(i).getDegree())){
									rlgl010306EduInfoList.remove(i);
									i--;
								}
							}
						}
						// 人员基本信息-工作经历
						if("addFlg5".equals(addFlg)){
							// 已经添加过的标记删除
							listCount =rlgl010306WorkInfoList.size();
							for (int i = 0; i<listCount;i++){
								rlgl010306WorkInfoList.get(i).setAddFlag("0");
							}
							addOrInputFlg="Flg5";
							rlgl010306WorkInfo=new Mtb14PersonnelWorkInfo();
							rlgl010306WorkInfo.setAddFlag("1");
							rlgl010306WorkInfoList.add(rlgl010306WorkInfo);
						}
						if("rlgl010306WorkInfoList".equals(inputObject_id)){
							// 已经添加过的标记删除
							listCount =rlgl010306WorkInfoList.size();
							for (int i = 0; i<listCount;i++){
								rlgl010306WorkInfoList.get(i).setAddFlag("0");
							}
							addOrInputFlg="Flg5";
							File target = UploadFile.Upload(fileProfessionalInfo, fileProfessionalInfoFileName,targetDirectory);
							List<List<Object>> list = new LinkedList<List<Object>>();
							list = ReadExcel.readExcel(target,1,4);
							if (list != null && list.size() > 0)
							{
								List<Object> listObject = new LinkedList<Object>();
								for (int i = 0; i < list.size(); i++)
								{
									rlgl010306WorkInfo = new Mtb14PersonnelWorkInfo();
									
									listObject = list.get(i);
									for (int k = 0; k < listObject.size(); k++)
									{
										switch (k)
										{
											  case 0:
												  rlgl010306WorkInfo.setStarttime(listObject.get(k).toString());
												break;
											  case 1:
												  rlgl010306WorkInfo.setEndtime(listObject.get(k).toString());
												  break;
											  case 2:
												  rlgl010306WorkInfo.setWorkunit(listObject.get(k).toString());
												  break;
											  case 3:
												  rlgl010306WorkInfo.setProofpeople(listObject.get(k).toString());
												  break;
											  case 4:
												  rlgl010306WorkInfo.setPosition(listObject.get(k).toString());
												  break;
											  case 5:
												  rlgl010306WorkInfo.setTel(listObject.get(k).toString());
												  break;
										}
									}
									rlgl010306WorkInfo.setPersonnel_id(personnel.getPersonnel_id());
									rlgl010306WorkInfo.setAddFlag("1");
									rlgl010306WorkInfoList.add(rlgl010306WorkInfo);
								}
							}
							// 空白行删除
							listCount =rlgl010306WorkInfoList.size();
							for (int i = 0; i<listCount;i++){
								if(0==rlgl010306WorkInfoList.size()){
									break;
								}
								if(i==rlgl010306WorkInfoList.size()){
									break;
								}
								if(rlgl010306WorkInfoList.get(i) == null){
									rlgl010306WorkInfoList.remove(i);
									i--;
									continue;
								}
								if("".equals(rlgl010306WorkInfoList.get(i).getStarttime())
								   && "".equals(rlgl010306WorkInfoList.get(i).getEndtime())
								   && "".equals(rlgl010306WorkInfoList.get(i).getWorkunit())
								   && "".equals(rlgl010306WorkInfoList.get(i).getProofpeople())
								   && "".equals(rlgl010306WorkInfoList.get(i).getPosition())
								   && "".equals(rlgl010306WorkInfoList.get(i).getTel())){
									rlgl010306WorkInfoList.remove(i);
									i--;
								}
							}
						}
						// 人员基本信息-党派信息
						if("addFlg6".equals(addFlg)){
							// 已经添加过的标记删除
							listCount =rlgl010306PartisanInfoList.size();
							for (int i = 0; i<listCount;i++){
								rlgl010306PartisanInfoList.get(i).setAddFlag("0");
							}
							addOrInputFlg="Flg6";
							rlgl010306PartisanInfo=new Mtb13PersonnelPartisanInfo();
							rlgl010306PartisanInfo.setAddFlag("1");
							rlgl010306PartisanInfoList.add(rlgl010306PartisanInfo);
						}
						if("rlgl010306PartisanInfoList".equals(inputObject_id)){
							// 已经添加过的标记删除
							listCount =rlgl010306PartisanInfoList.size();
							for (int i = 0; i<listCount;i++){
								rlgl010306PartisanInfoList.get(i).setAddFlag("0");
							}
							addOrInputFlg="Flg6";
							File target = UploadFile.Upload(fileProfessionalInfo, fileProfessionalInfoFileName,targetDirectory);
							List<List<Object>> list = new LinkedList<List<Object>>();
							list = ReadExcel.readExcel(target,1,5);
							if (list != null && list.size() > 0)
							{
								List<Object> listObject = new LinkedList<Object>();
								for (int i = 0; i < list.size(); i++)
								{
									rlgl010306PartisanInfo = new Mtb13PersonnelPartisanInfo();
									
									listObject = list.get(i);
									for (int k = 0; k < listObject.size(); k++)
									{
										switch (k)
										{
											  case 0:
												  rlgl010306PartisanInfo.setJointime(listObject.get(k).toString());
												break;
											  case 1:
												  rlgl010306PartisanInfo.setPartisan_nm(listObject.get(k).toString());
												  break;
											  case 2:
												  rlgl010306PartisanInfo.setPrep_or_officially(listObject.get(k).toString());
												  break;
										}
									}
									rlgl010306PartisanInfo.setPersonnel_id(personnel.getPersonnel_id());
									rlgl010306PartisanInfo.setAddFlag("1");
									rlgl010306PartisanInfoList.add(rlgl010306PartisanInfo);
								}
							}
							// 空白行删除
							listCount =rlgl010306PartisanInfoList.size();
							for (int i = 0; i<listCount;i++){
								if(0==rlgl010306PartisanInfoList.size()){
									break;
								}
								if(i==rlgl010306PartisanInfoList.size()){
									break;
								}
								if(rlgl010306PartisanInfoList.get(i) == null){
									rlgl010306PartisanInfoList.remove(i);
									i--;
									continue;
								}
								if("".equals(rlgl010306PartisanInfoList.get(i).getJointime())
								   && "".equals(rlgl010306PartisanInfoList.get(i).getPartisan_nm())
								   && "".equals(rlgl010306PartisanInfoList.get(i).getPrep_or_officially())){
									rlgl010306PartisanInfoList.remove(i);
									i--;
								}
							}
						}
						// 人员基本信息-资格信息
						if("addFlg7".equals(addFlg)){
							// 已经添加过的标记删除
							listCount =rlgl010306PractitionersInfoList.size();
							for (int i = 0; i<listCount;i++){
								rlgl010306PractitionersInfoList.get(i).setAddFlag("0");
							}
							addOrInputFlg="Flg7";
							rlgl010306PractitionersInfo=new Mtb19PersonnelPractitionersInfo();
							rlgl010306PractitionersInfo.setAddFlag("1");
							rlgl010306PractitionersInfoList.add(rlgl010306PractitionersInfo);
						}
						if("rlgl010306PractitionersInfoList".equals(inputObject_id)){
							// 已经添加过的标记删除
							listCount =rlgl010306PractitionersInfoList.size();
							for (int i = 0; i<listCount;i++){
								rlgl010306PractitionersInfoList.get(i).setAddFlag("0");
							}
							addOrInputFlg="Flg7";
							File target = UploadFile.Upload(fileProfessionalInfo, fileProfessionalInfoFileName,targetDirectory);
							List<List<Object>> list = new LinkedList<List<Object>>();
							list = ReadExcel.readExcel(target,1,6);
							if (list != null && list.size() > 0)
							{
								List<Object> listObject = new LinkedList<Object>();
								for (int i = 0; i < list.size(); i++)
								{
									rlgl010306PractitionersInfo = new Mtb19PersonnelPractitionersInfo();
									
									listObject = list.get(i);
									for (int k = 0; k < listObject.size(); k++)
									{
										switch (k)
										{
											  case 0:
												  rlgl010306PractitionersInfo.setCertificate_no(listObject.get(k).toString());
												break;
											  case 1:
												  rlgl010306PractitionersInfo.setIssuing_authority(listObject.get(k).toString());
												  break;
											  case 2:
												  rlgl010306PractitionersInfo.setIssue_time(listObject.get(k).toString());
												  break;
											  case 3:
												  rlgl010306PractitionersInfo.setLevel(listObject.get(k).toString());
												  break;
											  case 4:
												  rlgl010306PractitionersInfo.setType(listObject.get(k).toString());
												  break;
											  case 5:
												  rlgl010306PractitionersInfo.setArea1(listObject.get(k).toString());
												  break;
											  case 6:
												  rlgl010306PractitionersInfo.setUpd_record(listObject.get(k).toString());
												  break;
											  case 7:
												  rlgl010306PractitionersInfo.setAssess_record(listObject.get(k).toString());
												  break;
										}
									}
									rlgl010306PractitionersInfo.setPersonnel_id(personnel.getPersonnel_id());
									rlgl010306PractitionersInfo.setAddFlag("1");
									rlgl010306PractitionersInfoList.add(rlgl010306PractitionersInfo);
								}
							}
							// 空白行删除
							listCount =rlgl010306PractitionersInfoList.size();
							for (int i = 0; i<listCount;i++){
								if(0==rlgl010306PractitionersInfoList.size()){
									break;
								}
								if(i==rlgl010306PractitionersInfoList.size()){
									break;
								}
								if(rlgl010306PractitionersInfoList.get(i) == null){
									rlgl010306PractitionersInfoList.remove(i);
									i--;
									continue;
								}
								if("".equals(rlgl010306PractitionersInfoList.get(i).getCertificate_no())
								   && "".equals(rlgl010306PractitionersInfoList.get(i).getIssuing_authority())
								   && "".equals(rlgl010306PractitionersInfoList.get(i).getIssue_time())
								   && "".equals(rlgl010306PractitionersInfoList.get(i).getLevel())
								   && "".equals(rlgl010306PractitionersInfoList.get(i).getType())
								   && "".equals(rlgl010306PractitionersInfoList.get(i).getArea1())
								   && "".equals(rlgl010306PractitionersInfoList.get(i).getUpd_record())
								   && "".equals(rlgl010306PractitionersInfoList.get(i).getAssess_record())){
									rlgl010306PractitionersInfoList.remove(i);
									i--;
								}
							}
						}

						// 人员基本信息-执业信息
						if("addFlg8".equals(addFlg)){
							// 已经添加过的标记删除
							listCount =rlgl010306PracticeInfoList.size();
							for (int i = 0; i<listCount;i++){
								rlgl010306PracticeInfoList.get(i).setAddFlag("0");
							}
							addOrInputFlg="Flg8";
							rlgl010306PracticeInfo=new Mtb77PersonnelPracticeInfo();
							rlgl010306PracticeInfo.setAddFlag("1");
							rlgl010306PracticeInfoList.add(rlgl010306PracticeInfo);
						}
						if("rlgl010306PracticeInfoList".equals(inputObject_id)){
							// 已经添加过的标记删除
							listCount =rlgl010306PracticeInfoList.size();
							for (int i = 0; i<listCount;i++){
								rlgl010306PracticeInfoList.get(i).setAddFlag("0");
							}
							addOrInputFlg="Flg8";
							File target = UploadFile.Upload(fileProfessionalInfo, fileProfessionalInfoFileName,targetDirectory);
							List<List<Object>> list = new LinkedList<List<Object>>();
							list = ReadExcel.readExcel(target,1,7);
							if (list != null && list.size() > 0)
							{
								List<Object> listObject = new LinkedList<Object>();
								for (int i = 0; i < list.size(); i++)
								{
									rlgl010306PracticeInfo = new Mtb77PersonnelPracticeInfo();
									
									listObject = list.get(i);
									for (int k = 0; k < listObject.size(); k++)
									{
										switch (k)
										{
											  case 0:
												  rlgl010306PracticeInfo.setCertificate_no(listObject.get(k).toString());
												break;
											  case 1:
												  rlgl010306PracticeInfo.setIssuing_authority(listObject.get(k).toString());
												  break;
											  case 2:
												  rlgl010306PracticeInfo.setIssue_time(listObject.get(k).toString());
												  break;
											  case 3:
												  rlgl010306PracticeInfo.setLevel(listObject.get(k).toString());
												  break;
											  case 4:
												  rlgl010306PracticeInfo.setType(listObject.get(k).toString());
												  break;
											  case 5:
												  rlgl010306PracticeInfo.setArea(listObject.get(k).toString());
												  break;
											  case 6:
												  rlgl010306PracticeInfo.setUpd_record(listObject.get(k).toString());
												  break;
											  case 7:
												  rlgl010306PracticeInfo.setAssess_record(listObject.get(k).toString());
												  break;
										}
									}
									rlgl010306PracticeInfo.setPersonnel_id(personnel.getPersonnel_id());
									rlgl010306PracticeInfo.setAddFlag("1");
									rlgl010306PracticeInfoList.add(rlgl010306PracticeInfo);
								}
							}
							// 空白行删除
							listCount =rlgl010306PracticeInfoList.size();
							for (int i = 0; i<listCount;i++){
								if(0==rlgl010306PracticeInfoList.size()){
									break;
								}
								if(i==rlgl010306PracticeInfoList.size()){
									break;
								}
								if(rlgl010306PracticeInfoList.get(i) == null){
									rlgl010306PracticeInfoList.remove(i);
									i--;
									continue;
								}
								if("".equals(rlgl010306PracticeInfoList.get(i).getCertificate_no())
								   && "".equals(rlgl010306PracticeInfoList.get(i).getIssuing_authority())
								   && "".equals(rlgl010306PracticeInfoList.get(i).getIssue_time())
								   && "".equals(rlgl010306PracticeInfoList.get(i).getLevel())
								   && "".equals(rlgl010306PracticeInfoList.get(i).getType())
								   && "".equals(rlgl010306PracticeInfoList.get(i).getArea())
								   && "".equals(rlgl010306PracticeInfoList.get(i).getUpd_record())
								   && "".equals(rlgl010306PracticeInfoList.get(i).getAssess_record())){
									rlgl010306PracticeInfoList.remove(i);
									i--;
								}
							}
						}
					} catch(Exception ex)
					{
						ex.printStackTrace();	
					}
					inputObject_id="";
					addFlg="";
					return SUCCESS;
	}
	public static Workbook create(InputStream inp) throws IOException,InvalidFormatException {
		   if(!inp.markSupported()) {
		    inp = new PushbackInputStream(inp, 8);
		   }
		   if(POIFSFileSystem.hasPOIFSHeader(inp)) {
		    return new HSSFWorkbook(inp);
		   }
		   if(POIXMLDocument.hasOOXMLHeader(inp)) {
		    return new XSSFWorkbook(OPCPackage.open(inp));
		   }
		    throw new IllegalArgumentException("你的excel版本目前解析不了");
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
	public Mtb12Personnel getPersonnel() {
		return personnel;
	}
	public void setPersonnel(Mtb12Personnel personnel) {
		this.personnel = personnel;
	}
	public List<Mtb18PersonnelProfessionalInfo> getRlgl010306ProfessionalInfoList() {
		return rlgl010306ProfessionalInfoList;
	}
	public void setRlgl010306ProfessionalInfoList(
			List<Mtb18PersonnelProfessionalInfo> rlgl010306ProfessionalInfoList) {
		this.rlgl010306ProfessionalInfoList = rlgl010306ProfessionalInfoList;
	}
	public List<Mtb17PersonnelJobInfo> getRlgl010306JobInfoList() {
		return rlgl010306JobInfoList;
	}
	public void setRlgl010306JobInfoList(
			List<Mtb17PersonnelJobInfo> rlgl010306JobInfoList) {
		this.rlgl010306JobInfoList = rlgl010306JobInfoList;
	}
	public List<Mtb16PersonnelSocialInfo> getRlgl010306SocialInfoList() {
		return rlgl010306SocialInfoList;
	}
	public void setRlgl010306SocialInfoList(
			List<Mtb16PersonnelSocialInfo> rlgl010306SocialInfoList) {
		this.rlgl010306SocialInfoList = rlgl010306SocialInfoList;
	}
	public List<Mtb15PersonnelEduInfo> getRlgl010306EduInfoList() {
		return rlgl010306EduInfoList;
	}
	public void setRlgl010306EduInfoList(
			List<Mtb15PersonnelEduInfo> rlgl010306EduInfoList) {
		this.rlgl010306EduInfoList = rlgl010306EduInfoList;
	}
	public List<Mtb14PersonnelWorkInfo> getRlgl010306WorkInfoList() {
		return rlgl010306WorkInfoList;
	}
	public void setRlgl010306WorkInfoList(
			List<Mtb14PersonnelWorkInfo> rlgl010306WorkInfoList) {
		this.rlgl010306WorkInfoList = rlgl010306WorkInfoList;
	}
	public List<Mtb13PersonnelPartisanInfo> getRlgl010306PartisanInfoList() {
		return rlgl010306PartisanInfoList;
	}
	public void setRlgl010306PartisanInfoList(
			List<Mtb13PersonnelPartisanInfo> rlgl010306PartisanInfoList) {
		this.rlgl010306PartisanInfoList = rlgl010306PartisanInfoList;
	}
	public List<Mtb19PersonnelPractitionersInfo> getRlgl010306PractitionersInfoList() {
		return rlgl010306PractitionersInfoList;
	}
	public void setRlgl010306PractitionersInfoList(
			List<Mtb19PersonnelPractitionersInfo> rlgl010306PractitionersInfoList) {
		this.rlgl010306PractitionersInfoList = rlgl010306PractitionersInfoList;
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
	public String getImgFile() {
		return imgFile;
	}
	public void setImgFile(String imgFile) {
		this.imgFile = imgFile;
	}
	public Mtb18PersonnelProfessionalInfo getRlgl010306ProfessionalInfo() {
		return rlgl010306ProfessionalInfo;
	}
	public void setRlgl010306ProfessionalInfo(
			Mtb18PersonnelProfessionalInfo rlgl010306ProfessionalInfo) {
		this.rlgl010306ProfessionalInfo = rlgl010306ProfessionalInfo;
	}
	public Mtb17PersonnelJobInfo getRlgl010306JobInfo() {
		return rlgl010306JobInfo;
	}
	public void setRlgl010306JobInfo(Mtb17PersonnelJobInfo rlgl010306JobInfo) {
		this.rlgl010306JobInfo = rlgl010306JobInfo;
	}
	public Mtb16PersonnelSocialInfo getRlgl010306SocialInfo() {
		return rlgl010306SocialInfo;
	}
	public void setRlgl010306SocialInfo(
			Mtb16PersonnelSocialInfo rlgl010306SocialInfo) {
		this.rlgl010306SocialInfo = rlgl010306SocialInfo;
	}
	public Mtb15PersonnelEduInfo getRlgl010306EduInfo() {
		return rlgl010306EduInfo;
	}
	public void setRlgl010306EduInfo(Mtb15PersonnelEduInfo rlgl010306EduInfo) {
		this.rlgl010306EduInfo = rlgl010306EduInfo;
	}
	public Mtb14PersonnelWorkInfo getRlgl010306WorkInfo() {
		return rlgl010306WorkInfo;
	}
	public void setRlgl010306WorkInfo(Mtb14PersonnelWorkInfo rlgl010306WorkInfo) {
		this.rlgl010306WorkInfo = rlgl010306WorkInfo;
	}
	public Mtb13PersonnelPartisanInfo getRlgl010306PartisanInfo() {
		return rlgl010306PartisanInfo;
	}
	public void setRlgl010306PartisanInfo(
			Mtb13PersonnelPartisanInfo rlgl010306PartisanInfo) {
		this.rlgl010306PartisanInfo = rlgl010306PartisanInfo;
	}
	public Mtb19PersonnelPractitionersInfo getRlgl010306PractitionersInfo() {
		return rlgl010306PractitionersInfo;
	}
	public void setRlgl010306PractitionersInfo(
			Mtb19PersonnelPractitionersInfo rlgl010306PractitionersInfo) {
		this.rlgl010306PractitionersInfo = rlgl010306PractitionersInfo;
	}
	public String getInputObject_id() {
		return inputObject_id;
	}
	public void setInputObject_id(String inputObject_id) {
		this.inputObject_id = inputObject_id;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public File getFileProfessionalInfo() {
		return fileProfessionalInfo;
	}
	public void setFileProfessionalInfo(File fileProfessionalInfo) {
		this.fileProfessionalInfo = fileProfessionalInfo;
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
	public Mtb59PersonnelTutorInfo getRlgl010306TutorInfo() {
		return rlgl010306TutorInfo;
	}
	public void setRlgl010306TutorInfo(Mtb59PersonnelTutorInfo rlgl010306TutorInfo) {
		this.rlgl010306TutorInfo = rlgl010306TutorInfo;
	}
	public List<Mtb59PersonnelTutorInfo> getRlgl010306TutorInfoList() {
		return rlgl010306TutorInfoList;
	}
	public void setRlgl010306TutorInfoList(
			List<Mtb59PersonnelTutorInfo> rlgl010306TutorInfoList) {
		this.rlgl010306TutorInfoList = rlgl010306TutorInfoList;
	}
	public String getAddFlg() {
		return addFlg;
	}
	public void setAddFlg(String addFlg) {
		this.addFlg = addFlg;
	}
	public String getChange_value() {
		return change_value;
	}
	public void setChange_value(String change_value) {
		this.change_value = change_value;
	}
	public String getObjectArray() {
		return objectArray;
	}
	public void setObjectArray(String objectArray) {
		this.objectArray = objectArray;
	}
	public List<MTb02Adm> getTeachertypelist() {
		return teachertypelist;
	}
	public void setTeachertypelist(List<MTb02Adm> teachertypelist) {
		this.teachertypelist = teachertypelist;
	}
	public String getFileProfessionalInfoFileName() {
		return fileProfessionalInfoFileName;
	}
	public void setFileProfessionalInfoFileName(String fileProfessionalInfoFileName) {
		this.fileProfessionalInfoFileName = fileProfessionalInfoFileName;
	}
	public String getAddOrInputFlg() {
		return addOrInputFlg;
	}
	public void setAddOrInputFlg(String addOrInputFlg) {
		this.addOrInputFlg = addOrInputFlg;
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
	public Mtb77PersonnelPracticeInfo getRlgl010306PracticeInfo() {
		return rlgl010306PracticeInfo;
	}
	public void setRlgl010306PracticeInfo(
			Mtb77PersonnelPracticeInfo rlgl010306PracticeInfo) {
		this.rlgl010306PracticeInfo = rlgl010306PracticeInfo;
	}
	public List<Mtb77PersonnelPracticeInfo> getRlgl010306PracticeInfoList() {
		return rlgl010306PracticeInfoList;
	}
	public void setRlgl010306PracticeInfoList(
			List<Mtb77PersonnelPracticeInfo> rlgl010306PracticeInfoList) {
		this.rlgl010306PracticeInfoList = rlgl010306PracticeInfoList;
	}

}
