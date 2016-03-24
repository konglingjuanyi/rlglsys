
package com.rlglsys.action.learnonline;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl100101_1Bean;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.service.IRlgl100100Service;
import com.rlglsys.util.BeanFactory;
import com.rlglsys.util.Constant;
import com.rlglsys.util.DesUtil;

public class Rlgl100100ExportAction extends BaseAction {
	/**
	 * 缴费一览Excel导出处理
	 */
	private static final long serialVersionUID = 6708785320327731778L;
	// 缴费一览List
	private List<Rlgl100101_1Bean> resultList;
	private IRlgl100100Service rlgl100100Service;

	public IRlgl100100Service getRlgl100100Service() {
		return rlgl100100Service;
	}

	public void setRlgl100100Service(IRlgl100100Service rlgl100100Service) {
		this.rlgl100100Service = rlgl100100Service;
	}

	// 用户信息
	private Mtb01User loginUser;
	private int recordCount;
	private String startdate = "";
	private String enddate = "";
	private String id = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	InputStream excelStream;
	String fileName;
	String time;

	/**
	 * @return success
	 * @throws Exception
	 */
	protected String doExecute() throws Exception {
		loginUser = (Mtb01User) super.getSession(Constant.SESSION_KEY_LOGINUSER);
		// 管理员查询个人缴费明细列表
		resultList = rlgl100100Service.getPrepayMsgAdminList(startdate, 0, 0, enddate,id);
		if (resultList == null || resultList.size() == 0) {
			super.saveErrorMessage("MSG0017E");
			return ERROR;
		} else {

			recordCount = resultList.size();
			BeanFactory bf = new BeanFactory();
			Map<String, String> B01fields = new HashMap<String, String>();
			// 解密
			for (int i = 0; i < recordCount; i++) {
				bf.reinstallFields(resultList.get(i), DesUtil.class, "dec" + "rypt", B01fields);
			}

			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			String month_ = new String("" + month);
			if (month < 10) {
				month_ = "0" + month;
			}
			int day = c.get(Calendar.DAY_OF_MONTH);
			String day_ = new String("" + day);
			if (day < 10) {
				day_ = "0" + day;
			}
			time = year + "-" + month_ + "-" + day_ + "";
			HSSFWorkbook workbook = getWorkbook(resultList);
			if (workbook != null) {
				try {
					// 将Workbook写入到InputStream
					this.workbook2InputStream(workbook, time);
					return SUCCESS;
				} catch (IOException e) {
					e.printStackTrace();
					// 创建Excel失败
					super.saveErrorMessage("MSG0017E");
				}
			} else {
				// 创建Excel失败
				super.saveErrorMessage("MSG0017E");
			}
		}
		return SUCCESS;
	}

	/* 下面这个方法是将list转换为Excel工作表的 */
	private HSSFWorkbook getWorkbook(List<Rlgl100101_1Bean> list) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		String sheetName = "缴费一览表";

		HSSFSheet sheet = workbook.createSheet(sheetName);
		String name = "";
		if (loginUser != null) {
			name = loginUser.getUser_name();
		}
		// 标题/表头设置
		String title = "统计名称：缴费一览表";
		String[] columnNames = new String[] { "序号", "姓名", "身份证号", "工作单位", "单位编号", "单位所属区县", "充值订单号", "充值金额", "充值是否成功" };
		String[] titleNames = new String[] { title, "", "统计单位：" + name, "", "统计时间：" + time };
		// 创建第1行，也就是输出表头
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell;
		// 标题设置
		for (int i = 0; i < titleNames.length; i++) {
			// 设置字体
			HSSFFont font = workbook.createFont();
			font.setColor(HSSFFont.COLOR_NORMAL);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			// 创建格式
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFont(font);
			// 应用格式
			cell = row.createCell((short) i + 1);
			cell.setCellStyle(cellStyle);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(new HSSFRichTextString(titleNames[i]));
		}
		// 表头设置
		row = sheet.createRow(2);
		for (int i = 0; i < columnNames.length; i++) {
			// 创建第i列
			cell = row.createCell((short) i);
			cell.setCellValue(new HSSFRichTextString(columnNames[i]));
		}
		// 下面是输出各行的数据
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 3);// 创建第i+2行
			for (int j = 0; j < 9; j++) {
				cell = row.createCell((short) j);// 创建第j列
				if (j == 0) {
					cell.setCellValue(i + 1);
				} else if (j == 1) {
					cell.setCellValue(list.get(i).getMerchantNM());
				} else if (j == 2) {
					cell.setCellValue(list.get(i).getUserId());
				} else if (j == 3) {
					cell.setCellValue("");//list.get(i).getDanweibianNM());
				} else if (j == 4) {
					cell.setCellValue("");//list.get(i).getDanweibianhao());
				} else if (j == 5) {
					cell.setCellValue("");//list.get(i).getDangweidizhi());
				} else if (j == 6) {
					cell.setCellValue("");//list.get(i).getMerchantID());
				} else if (j == 7) {
					cell.setCellValue(list.get(i).getAmount());
				} else if (j == 8) {
					if ("1".equals(list.get(i).getSucceed())) {
						cell.setCellValue("是");
					} else {
						cell.setCellValue("否");
					}
				}
			}

		}
		return workbook;
	}

	// 将Workbook写入到InputStream
	private void workbook2InputStream(HSSFWorkbook workbook, String fileName) throws Exception {
		// 设置fileName
		this.fileName = fileName;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		workbook.write(baos);
		baos.flush();
		byte[] aa = baos.toByteArray();
		excelStream = new ByteArrayInputStream(aa, 0, aa.length);
		baos.close();
	}

	public List<Rlgl100101_1Bean> getResultList() {
		return resultList;
	}

	public void setResultList(List<Rlgl100101_1Bean> resultList) {
		this.resultList = resultList;
	}

	/**
	 * @return the loginUser
	 */
	public Mtb01User getLoginUser() {
		return loginUser;
	}

	/**
	 * @param loginUser
	 *            the loginUser to set
	 */
	public void setLoginUser(Mtb01User loginUser) {
		this.loginUser = loginUser;
	}

	/**
	 * @return the excelStream
	 */
	public InputStream getExcelStream() {
		return excelStream;
	}

	/**
	 * @param excelStream
	 *            the excelStream to set
	 */
	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

}
