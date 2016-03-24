package com.rlglsys.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import com.rlglsys.bean.Rlgl011001Bean;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb04Unit;
import com.rlglsys.entity.Mtb111Admscore;
import com.rlglsys.service.IRlgl011001Service;
import com.swetake.util.Qrcode;

public class CommonManager {

	/**
	 * 系统超级用户取得
	 * 
	 * @param paramID
	 * @return
	 * @throws Exception
	 */
	public String getSystemUser(String paramID) throws Exception {
		InputStream is = null;
		Properties p = new Properties();
		String value = null;
		is = this.getClass().getResourceAsStream("/message_zh_CN.properties");
		try {
			p.load(is);
			is.close();
			value = p.getProperty(paramID);
			if (value == null) {
				value = "此信息不存在！";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	/**
	 * 密钥取得
	 * 
	 * @param paramID
	 * @return
	 * @throws Exception
	 */
	public String getSystemDesKey(String paramID) throws Exception {
		InputStream is = null;
		Properties p = new Properties();
		String value = null;
		is = this.getClass().getResourceAsStream("/message_zh_CN.properties");
		try {
			p.load(is);
			is.close();
			value = p.getProperty(paramID);
			if (value == null) {
				value = "此信息不存在！";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 系统超级用户取得
	 * 
	 * @param paramID
	 * @return
	 * @throws Exception
	 */
	public String getSystemMsg(String paramID) throws Exception {
		InputStream is = null;
		Properties p = new Properties();
		String value = null;
		is = this.getClass().getResourceAsStream("/message_zh_CN.properties");
		try {
			p.load(is);
			is.close();
			value = p.getProperty(paramID);
			if (value == null) {
				value = "此信息不存在！";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 文件路径取得
	 * 
	 * @param paramID
	 * @return
	 * @throws Exception
	 */
	public String getFilePath(String paramID) throws Exception {
		InputStream is = null;
		Properties p = new Properties();
		String value = null;
		is = this.getClass().getResourceAsStream("/conf/filePath.properties");
		try {
			p.load(is);
			is.close();
			value = p.getProperty(paramID);
			if (value == null) {
				value = "此信息不存在！";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 生成二维码(QRCode)图片
	 * 
	 * @param content
	 * @param imgPath
	 */
	public void encoderQRCode(String content, String imgPath) {
		try {
			Qrcode qrcodeHandler = new Qrcode();
			qrcodeHandler.setQrcodeErrorCorrect('M');
			qrcodeHandler.setQrcodeEncodeMode('B');
			qrcodeHandler.setQrcodeVersion(7);
			System.out.println(content);
			byte[] contentBytes = content.getBytes("gb2312");
			BufferedImage bufImg = new BufferedImage(140, 140, BufferedImage.TYPE_INT_RGB);

			Graphics2D gs = bufImg.createGraphics();
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, 140, 140);
			// 设定图像颜色> BLACK
			gs.setColor(Color.BLACK);
			// 设置偏移量 不设置可能导致解析出错
			int pixoff = 2;
			// 输出内容> 二维码
			if (contentBytes.length > 0 && contentBytes.length < 120) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				System.err.println("QRCode content bytes length = " + contentBytes.length + " not in [ 0,120 ]. ");
			}
			gs.dispose();
			bufImg.flush();
			File imgFile = new File(imgPath);
			// 生成二维码QRCode图片
			ImageIO.write(bufImg, "png", imgFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成二维码
	 * 
	 * @param cardId
	 * @param userName
	 * @param personnelID
	 */
	public void createQRCode(String cardId, String userName, String personnelID) {
		try {
			String filePath = this.getFilePath("ticket_number_image");
			filePath = ServletActionContext.getServletContext().getRealPath(filePath) + File.separator + cardId + ".png";
			String content = "准考证号：" + cardId + "\n 姓名：" + userName + "\n 身份证号：" + personnelID;

			this.encoderQRCode(content, filePath);
			System.out.println("encoder QRcode success");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 删除文件，可以是单个文件或文件夹
	 * 
	 * @param fileName
	 *            待删除的文件名
	 * @return 文件删除成功返回true,否则返回false
	 */
	public static boolean delete(String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			System.out.println("删除文件失败：" + fileName + "文件不存在");
			return false;
		} else {
			if (file.isFile()) {

				return deleteFile(fileName);
			} else {
				return deleteDirectory(fileName);
			}
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param fileName
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true,否则返回false
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
			System.out.println("删除单个文件" + fileName + "成功！");
			return true;
		} else {
			System.out.println("删除单个文件" + fileName + "失败！");
			return false;
		}
	}

	// 用以模糊删除头部为str的文件
	public static boolean delFilesByPath(String path, String str) {
		// 参数说明---------path:要删除的文件的文件夹的路径---------str:要匹配的字符串的头
		boolean b = false;
		File file = new File(path);
		File[] tempFile = file.listFiles();
		for (int i = 0; i < tempFile.length; i++) {
			if (tempFile[i].getName().startsWith(str)) {
				System.out.println("将被删除的文件名:" + tempFile[i].getName());
				boolean del = deleteFile(path + tempFile[i].getName());
				if (del) {
					System.out.println("文件" + tempFile[i].getName() + "删除成功");
					b = true;
				} else {
					System.out.println("文件" + tempFile[i].getName() + "删除失败");
				}
			}
		}
		return b;
	}
	
	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param dir
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true,否则返回false
	 */
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			System.out.println("删除目录失败" + dir + "目录不存在！");
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
			// 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}

		if (!flag) {
			System.out.println("删除目录失败");
			return false;
		}

		// 删除当前目录
		if (dirFile.delete()) {
			System.out.println("删除目录" + dir + "成功！");
			return true;
		} else {
			System.out.println("删除目录" + dir + "失败！");
			return false;
		}
	}

	/**
	 * 取得下级单位信息完成度
	 * 
	 * @param mtb111AdminScoreList
	 * @param user
	 * @param unitInfo
	 * @param rlgl011001BeanNew
	 * @param rlgl011001Service
	 * @return
	 */
	public List<Rlgl011001Bean> getLowerUnitScoreList(List<Mtb111Admscore> mtb111AdminScoreList, Mtb01User user, Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew, IRlgl011001Service rlgl011001Service, String pageFlg) {
		List<Rlgl011001Bean> rlgl011001BeanList = new ArrayList<Rlgl011001Bean>();
		try {
			DecimalFormat df = new DecimalFormat(".00");
			if (mtb111AdminScoreList != null && mtb111AdminScoreList.size() > 0) {
				String sql = "";
				if ("01".equals(pageFlg)) {
					sql = this.EditLowerUnitStrSql(mtb111AdminScoreList, "1", unitInfo);
				}
				if ("03".equals(pageFlg)) {
					sql = this.EditStrSqlArea(mtb111AdminScoreList, "", unitInfo, rlgl011001BeanNew);
				}

				if ("".equals(sql)) {
					return rlgl011001BeanList;
				}
				rlgl011001BeanNew.setStrSql(sql);
				if ("01".equals(pageFlg)) {

					rlgl011001BeanList = rlgl011001Service.getMtb111AdmscoreLowerInfoList(rlgl011001BeanNew);
				}

				if ("03".equals(pageFlg)) {

					rlgl011001BeanList = rlgl011001Service.getMtb111AdmscoreAreaInfoList(rlgl011001BeanNew);
				}

				if (rlgl011001BeanList != null && rlgl011001BeanList.size() > 0) {
					for (int i = 0; i < rlgl011001BeanList.size(); i++) {

						// 必填项
						if (rlgl011001BeanList.get(i).getTotal_score_b_sel() != 0) {
							if ("03".equals(pageFlg) || "01".equals(pageFlg)) {
								rlgl011001BeanList.get(i).setTotal_score_b("1");
								rlgl011001BeanList.get(i).setTotal_score_b_nm("是");
							} else {
								rlgl011001BeanList.get(i).setTotal_score_b(df.format(rlgl011001BeanList.get(i).getTotal_score_b_sel()) + "%");
							}
						} else {
							rlgl011001BeanList.get(i).setTotal_score_b("0");
							rlgl011001BeanList.get(i).setTotal_score_b_nm("否");
						}
						// 选填项
						if (rlgl011001BeanList.get(i).getTotal_score_x_sel() != 0) {
							if ("03".equals(pageFlg) || "01".equals(pageFlg)) {
								rlgl011001BeanList.get(i).setTotal_score_x("1");
								rlgl011001BeanList.get(i).setTotal_score_x_nm("是");
							} else {
								rlgl011001BeanList.get(i).setTotal_score_x(df.format(rlgl011001BeanList.get(i).getTotal_score_x_sel()) + "%");
							}

						} else {
							rlgl011001BeanList.get(i).setTotal_score_x("0");
							rlgl011001BeanList.get(i).setTotal_score_x_nm("否");
						}
						// 联系方式
						if (rlgl011001BeanList.get(i).getScore_contact_sel() != 0) {
							if ("03".equals(pageFlg) || "01".equals(pageFlg)) {
								rlgl011001BeanList.get(i).setScore_contact("1");
								rlgl011001BeanList.get(i).setScore_contact_nm("是");
							} else {
								rlgl011001BeanList.get(i).setScore_contact(df.format(rlgl011001BeanList.get(i).getScore_contact_sel()) + "%");
							}
						} else {
							rlgl011001BeanList.get(i).setScore_contact("0");
							rlgl011001BeanList.get(i).setScore_contact_nm("否");
						}
						// 执业信息
						if (rlgl011001BeanList.get(i).getScore_professional_sel() != 0) {
							if ("03".equals(pageFlg) || "01".equals(pageFlg)) {
								rlgl011001BeanList.get(i).setScore_professional("1");
								rlgl011001BeanList.get(i).setScore_professional_nm("是");
							} else {
								rlgl011001BeanList.get(i).setScore_professional(df.format(rlgl011001BeanList.get(i).getScore_professional_sel()) + "%");
							}
						} else {
							rlgl011001BeanList.get(i).setScore_professional("0");
							rlgl011001BeanList.get(i).setScore_professional_nm("否");
						}
						// 基本信息
						if (rlgl011001BeanList.get(i).getScore_basic_sel() != 0) {
							if ("03".equals(pageFlg) || "01".equals(pageFlg)) {
								rlgl011001BeanList.get(i).setScore_basic("1");
								rlgl011001BeanList.get(i).setScore_basic_nm("是");
							} else {
								rlgl011001BeanList.get(i).setScore_basic(df.format(rlgl011001BeanList.get(i).getScore_basic_sel()) + "%");
							}
						} else {
							rlgl011001BeanList.get(i).setScore_basic("0");
							rlgl011001BeanList.get(i).setScore_basic_nm("否");
						}
						// 其他信息
						if (rlgl011001BeanList.get(i).getScore_other_sel() != 0) {
							if ("03".equals(pageFlg) || "01".equals(pageFlg)) {
								rlgl011001BeanList.get(i).setScore_other("1");
								rlgl011001BeanList.get(i).setScore_other_nm("是");
							} else {
								rlgl011001BeanList.get(i).setScore_other(df.format(rlgl011001BeanList.get(i).getScore_other_sel()) + "%");
							}
						} else {
							rlgl011001BeanList.get(i).setScore_other("0");
							rlgl011001BeanList.get(i).setScore_other_nm("否");
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rlgl011001BeanList;
	}

	/**
	 * 取得本单位信息完成度
	 * 
	 * @param mtb111AdminScoreList
	 * @param user
	 * @param unitInfo
	 * @param rlgl011001BeanNew
	 * @param rlgl011001Service
	 * @return
	 */
	public Rlgl011001Bean getLowerUnitScoreBean(List<Mtb111Admscore> mtb111AdminScoreList, Mtb01User user, Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew, IRlgl011001Service rlgl011001Service) {
		Rlgl011001Bean rlgl011001Bean = new Rlgl011001Bean();
		try {
			DecimalFormat df = new DecimalFormat(".00");
			if (mtb111AdminScoreList != null && mtb111AdminScoreList.size() > 0) {
				String sql = this.EditStrSql(mtb111AdminScoreList, "0", unitInfo);

				if ("".equals(sql)) {
					return rlgl011001Bean;
				}
				rlgl011001BeanNew.setStrSql(sql);
				rlgl011001Bean = rlgl011001Service.getMtb111AdmscoreInfo(rlgl011001BeanNew);

				if (rlgl011001Bean != null) {
					// 必填项
					if (rlgl011001Bean.getTotal_score_b_sel() != 0) {
						rlgl011001Bean.setTotal_score_b(df.format(rlgl011001Bean.getTotal_score_b_sel()) + "%");
					} else {
						rlgl011001Bean.setTotal_score_b("0");
					}
					// 选填项
					if (rlgl011001Bean.getTotal_score_x_sel() != 0) {
						rlgl011001Bean.setTotal_score_x(df.format(rlgl011001Bean.getTotal_score_x_sel()) + "%");
					} else {
						rlgl011001Bean.setTotal_score_x("0");
					}
					// 联系方式
					if (rlgl011001Bean.getScore_contact_sel() != 0) {
						rlgl011001Bean.setScore_contact(df.format(rlgl011001Bean.getScore_contact_sel()) + "%");
					} else {
						rlgl011001Bean.setScore_contact("0");
					}
					// 执业信息
					if (rlgl011001Bean.getScore_professional_sel() != 0) {
						rlgl011001Bean.setScore_professional(df.format(rlgl011001Bean.getScore_professional_sel()) + "%");
					} else {
						rlgl011001Bean.setScore_professional("0");
					}
					// 基本信息
					if (rlgl011001Bean.getScore_basic_sel() != 0) {
						rlgl011001Bean.setScore_basic(df.format(rlgl011001Bean.getScore_basic_sel()) + "%");
					} else {
						rlgl011001Bean.setScore_basic("0");
					}
					// 其他信息
					if (rlgl011001Bean.getScore_other_sel() != 0) {
						rlgl011001Bean.setScore_other(df.format(rlgl011001Bean.getScore_other_sel()) + "%");
					} else {
						rlgl011001Bean.setScore_other("0");
					}
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rlgl011001Bean;
	}

	/**
	 * 取得单位建立情况信息
	 * 
	 * @param unitInfo
	 * @param rlgl011001Service
	 * @return
	 */
	public List<Rlgl011001Bean> getUnitCreatDataInfoList(Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew, IRlgl011001Service rlgl011001Service) {
		List<Rlgl011001Bean> rlgl011001BeanList = new ArrayList<Rlgl011001Bean>();
		try {
			String sql = this.EditStrSqlAreaCount(unitInfo);
			if ("".equals(sql)) {
				return rlgl011001BeanList;
			}
			rlgl011001BeanNew.setStrSql(sql);
			rlgl011001BeanList = rlgl011001Service.getMtb111AdmscoreAreaInfoList(rlgl011001BeanNew);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rlgl011001BeanList;
	}

	/**
	 * 取得人员专业建立情况信息
	 * 
	 * @param unitInfo
	 * @param rlgl011001Service
	 * @return
	 */
	public List<Rlgl011001Bean> getPersonProfessionInfoList(Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew, IRlgl011001Service rlgl011001Service) {
		List<Rlgl011001Bean> rlgl011001BeanList = new ArrayList<Rlgl011001Bean>();
		try {
			String sql = this.EditStrSqlPersonProfession(unitInfo, rlgl011001BeanNew);
			if ("".equals(sql)) {
				return rlgl011001BeanList;
			}
			rlgl011001BeanNew.setStrSql(sql);
			rlgl011001BeanList = rlgl011001Service.getMtb111AdmscoreAreaInfoList(rlgl011001BeanNew);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rlgl011001BeanList;
	}

	/**
	 * 取得人员状态维护审核情况表
	 * 
	 * @param unitInfo
	 * @param rlgl011001Service
	 * @return
	 */
	public List<Rlgl011001Bean> getPersonStatusInfoList(Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew, IRlgl011001Service rlgl011001Service) {
		List<Rlgl011001Bean> rlgl011001BeanList = new ArrayList<Rlgl011001Bean>();
		try {
			String sql = this.EditStrSqlPersonStatus(unitInfo, rlgl011001BeanNew);
			if ("".equals(sql)) {
				return rlgl011001BeanList;
			}
			rlgl011001BeanNew.setStrSql(sql);
			rlgl011001BeanList = rlgl011001Service.getMtb111AdmscoreAreaInfoList(rlgl011001BeanNew);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rlgl011001BeanList;
	}

	/**
	 * 取得人员各模块信息维护情况表
	 * 
	 * @param unitInfo
	 * @param rlgl011001Service
	 * @return
	 */
	public List<Rlgl011001Bean> getPersonEditStatusInfoList(Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew, IRlgl011001Service rlgl011001Service) {
		List<Rlgl011001Bean> rlgl011001BeanList = new ArrayList<Rlgl011001Bean>();
		try {
			String sql = this.EditPersonnelDataInfoSql(unitInfo, rlgl011001BeanNew);
			if ("".equals(sql)) {
				return rlgl011001BeanList;
			}
			rlgl011001BeanNew.setStrSql(sql);
			rlgl011001BeanList = rlgl011001Service.getMtb111AdmscoreAreaInfoList(rlgl011001BeanNew);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rlgl011001BeanList;
	}

	/**
	 * 取得人员各模块信息维护明细情况表
	 * 
	 * @param unitInfo
	 * @param rlgl011001Service
	 * @return
	 */
	public List<Rlgl011001Bean> getPersonEditStatusDetailInfoList(Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew, IRlgl011001Service rlgl011001Service) {
		List<Rlgl011001Bean> rlgl011001BeanList = new ArrayList<Rlgl011001Bean>();
		try {
			String sql = this.EditPersonnelDataDetailInfoSql(unitInfo, rlgl011001BeanNew);
			if ("".equals(sql)) {
				return rlgl011001BeanList;
			}
			rlgl011001BeanNew.setStrSql(sql);
			rlgl011001BeanList = rlgl011001Service.getMtb111AdmscoreAreaInfoList(rlgl011001BeanNew);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rlgl011001BeanList;
	}

	/**
	 * 取得单位近X月更新情况表
	 * 
	 * @param mtb111AdminScoreList
	 * @param user
	 * @param unitInfo
	 * @param rlgl011001BeanNew
	 * @param rlgl011001Service
	 * @return
	 */
	public List<Rlgl011001Bean> getUnitDataXmonthUpdateInfoList(List<Mtb111Admscore> mtb111AdminScoreList, Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew, IRlgl011001Service rlgl011001Service) {
		List<Rlgl011001Bean> rlgl011001BeanList = new ArrayList<Rlgl011001Bean>();
		try {
			String sql = this.EditUnitDataXmonthUpdateInfoSql(mtb111AdminScoreList, unitInfo, rlgl011001BeanNew);
			if ("".equals(sql)) {
				return rlgl011001BeanList;
			}
			rlgl011001BeanNew.setStrSql(sql);
			rlgl011001BeanList = rlgl011001Service.getMtb111AdmscoreAreaInfoList(rlgl011001BeanNew);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rlgl011001BeanList;
	}

	/**
	 * 取得单位近X月更新情况表
	 * 
	 * @param mtb111AdminScoreList
	 * @param user
	 * @param unitInfo
	 * @param rlgl011001BeanNew
	 * @param rlgl011001Service
	 * @return
	 */
	public List<Rlgl011001Bean> getPersonnelDataInfoXMonthUpdateList(Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew, IRlgl011001Service rlgl011001Service) {
		List<Rlgl011001Bean> rlgl011001BeanList = new ArrayList<Rlgl011001Bean>();
		try {
			String sql = this.EditPersonnelDataInfoXMonthUpdateSql(unitInfo, rlgl011001BeanNew);
			if ("".equals(sql)) {
				return rlgl011001BeanList;
			}
			rlgl011001BeanNew.setStrSql(sql);
			rlgl011001BeanList = rlgl011001Service.getMtb111AdmscoreAreaInfoList(rlgl011001BeanNew);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rlgl011001BeanList;
	}

	/**
	 * 单位年度考核情况
	 * 
	 * @param unitInfo
	 * @param rlgl011001BeanNew
	 * @param rlgl011001Service
	 * @return
	 */
	public List<Rlgl011001Bean> getUnitCheckByYearSql(Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew, IRlgl011001Service rlgl011001Service) {
		List<Rlgl011001Bean> rlgl011001BeanList = new ArrayList<Rlgl011001Bean>();
		try {
			String sql = this.EditUnitCheckByYearSql(unitInfo, rlgl011001BeanNew);
			if ("".equals(sql)) {
				return rlgl011001BeanList;
			}
			rlgl011001BeanNew.setStrSql(sql);
			rlgl011001BeanList = rlgl011001Service.getMtb111AdmscoreAreaInfoList(rlgl011001BeanNew);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rlgl011001BeanList;
	}

	/**
	 * 人员其他信息维护情况
	 * 
	 * @param unitInfo
	 * @param rlgl011001BeanNew
	 * @param rlgl011001Service
	 * @return
	 */
	public List<Rlgl011001Bean> getPersonnelOtherDataInfoSql(Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew, IRlgl011001Service rlgl011001Service) {
		List<Rlgl011001Bean> rlgl011001BeanList = new ArrayList<Rlgl011001Bean>();
		try {
			String sql = this.EditPersonnelOtherDataInfoSql(unitInfo, rlgl011001BeanNew);
			if ("".equals(sql)) {
				return rlgl011001BeanList;
			}
			rlgl011001BeanNew.setStrSql(sql);
			rlgl011001BeanList = rlgl011001Service.getMtb111AdmscoreAreaInfoList(rlgl011001BeanNew);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rlgl011001BeanList;
	}
	
	/**
	 * 人员学历统计
	 * 
	 * 
	 * @param unitInfo
	 * @param rlgl011001BeanNew
	 * @param rlgl011001Service
	 * @return
	 */
	public List<Rlgl011001Bean> getEduList(String year,String unitNo) {
		List<Rlgl011001Bean> rlgl011001BeanList = new ArrayList<Rlgl011001Bean>();
		try {
			String sql = this.GetEdoInfoSql(year, unitNo);
			if ("".equals(sql)) {
				return rlgl011001BeanList;
			}
			//rlgl011001BeanNew.setStrSql(sql);
			//rlgl011001BeanList = rlgl011001Service.getMtb111AdmscoreAreaInfoList(rlgl011001BeanNew);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rlgl011001BeanList;
	}

	/**
	 * 人员近X月其他信息维护情况
	 * 
	 * @param unitInfo
	 * @param rlgl011001BeanNew
	 * @param rlgl011001Service
	 * @return
	 */
	public List<Rlgl011001Bean> getPersonnelOtherDataInfoXMonthUpdateSql(Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew, IRlgl011001Service rlgl011001Service) {
		List<Rlgl011001Bean> rlgl011001BeanList = new ArrayList<Rlgl011001Bean>();
		try {
			String sql = this.EditPersonnelOtherDataInfoXMonthUpdateSql(unitInfo, rlgl011001BeanNew);
			if ("".equals(sql)) {
				return rlgl011001BeanList;
			}
			rlgl011001BeanNew.setStrSql(sql);
			rlgl011001BeanList = rlgl011001Service.getMtb111AdmscoreAreaInfoList(rlgl011001BeanNew);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rlgl011001BeanList;
	}

	/**
	 * 新增人员情况统计表
	 * 
	 * @param unitInfo
	 * @param rlgl011001BeanNew
	 * @param rlgl011001Service
	 * @return
	 */
	public List<Rlgl011001Bean> getPersonProfessionNewAddInfoSql(Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew, IRlgl011001Service rlgl011001Service) {
		List<Rlgl011001Bean> rlgl011001BeanList = new ArrayList<Rlgl011001Bean>();
		try {
			String sql = this.EditPersonProfessionNewAddInfoSql(unitInfo, rlgl011001BeanNew);
			if ("".equals(sql)) {
				return rlgl011001BeanList;
			}
			rlgl011001BeanNew.setStrSql(sql);
			rlgl011001BeanList = rlgl011001Service.getMtb111AdmscoreAreaInfoList(rlgl011001BeanNew);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rlgl011001BeanList;
	}

	/**
	 * 编制党员信息
	 * 
	 * @param unitInfo
	 * @param rlgl011001BeanNew
	 * @param rlgl011001Service
	 * @return
	 */
	public List<Rlgl011001Bean> getHeadcountPartymemberInfoSql(Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew, IRlgl011001Service rlgl011001Service) {
		List<Rlgl011001Bean> rlgl011001BeanList = new ArrayList<Rlgl011001Bean>();
		try {
			String sql = this.EditHeadcountPartymemberInfoSql(unitInfo, rlgl011001BeanNew);
			if ("".equals(sql)) {
				return rlgl011001BeanList;
			}
			rlgl011001BeanNew.setStrSql(sql);
			rlgl011001BeanList = rlgl011001Service.getMtb111AdmscoreAreaInfoList(rlgl011001BeanNew);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rlgl011001BeanList;
	}

	/**
	 * 查询本人信息完成度
	 * 
	 * @param unitInfo
	 * @param rlgl011001BeanNew
	 * @param rlgl011001Service
	 * @return
	 */
	public Rlgl011001Bean getPersoonelInfoSql(List<Mtb111Admscore> mtb111AdminScoreList, Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew, IRlgl011001Service rlgl011001Service) {
		Rlgl011001Bean rlgl011001Bean = new Rlgl011001Bean();
		try {
			String sql = this.EditStrPersonnelSql(mtb111AdminScoreList, unitInfo, rlgl011001BeanNew);
			if ("".equals(sql)) {
				return rlgl011001Bean;
			}
			rlgl011001BeanNew.setStrSql(sql);
			rlgl011001Bean = rlgl011001Service.getPersoonelInfo(rlgl011001BeanNew);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rlgl011001Bean;
	}

	/**
	 * 选课完成度
	 * 
	 * @param unitInfo
	 * @param rlgl011001BeanNew
	 * @param rlgl011001Service
	 * @return
	 */
	public Rlgl011001Bean getCourseInfoSql(String userId,String personnelId, Rlgl011001Bean rlgl011001BeanNew, IRlgl011001Service rlgl011001Service) {
		Rlgl011001Bean rlgl011001Bean = new Rlgl011001Bean();
		try {
			String sql = this.EditStrPersonnelCourseSql(userId,personnelId);
			if ("".equals(sql)) {
				return rlgl011001Bean;
			}
			rlgl011001BeanNew.setStrSql(sql);
			rlgl011001Bean = rlgl011001Service.getPersoonelInfo(rlgl011001BeanNew);
			int selectcoursecount=rlgl011001Bean.getSelectcoursecount();
			sql = this.EditStrPersonnelPassCourseSql(userId,personnelId);
			if ("".equals(sql)) {
				return rlgl011001Bean;
			}
			rlgl011001BeanNew.setStrSql(sql);
			rlgl011001Bean = rlgl011001Service.getPersoonelInfo(rlgl011001BeanNew);
			rlgl011001Bean.setSelectcoursecount(selectcoursecount);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rlgl011001Bean;
	}
	/**
	 * 选课完成度
	 * 
	 * @param unitInfo
	 * @param rlgl011001BeanNew
	 * @param rlgl011001Service
	 * @return
	 */
	public Rlgl011001Bean getCourseUnitSql(String unitno, Rlgl011001Bean rlgl011001BeanNew, IRlgl011001Service rlgl011001Service) {
		Rlgl011001Bean rlgl011001Bean = new Rlgl011001Bean();
		try {
			String sql = this.EditStrAllPersonnelUnitSql(unitno);
			if ("".equals(sql)) {
				return rlgl011001Bean;
			}
			rlgl011001BeanNew.setStrSql(sql);
			rlgl011001Bean = rlgl011001Service.getPersoonelInfo(rlgl011001BeanNew);
			int allpersonnelcount=rlgl011001Bean.getSumcourseCount();
			sql = this.EditStrPersonnelUnitSql(unitno);
			if ("".equals(sql)) {
				return rlgl011001Bean;
			}
			rlgl011001BeanNew.setStrSql(sql);
			rlgl011001Bean = rlgl011001Service.getPersoonelInfo(rlgl011001BeanNew);
			int selectcoursecount=rlgl011001Bean.getSelectcoursecount();
			sql = this.EditStrPersonnelPassUnitSql(unitno);
			if ("".equals(sql)) {
				return rlgl011001Bean;
			}
			rlgl011001BeanNew.setStrSql(sql);
			rlgl011001Bean = rlgl011001Service.getPersoonelInfo(rlgl011001BeanNew);
			rlgl011001Bean.setSelectcoursecount(selectcoursecount);
			rlgl011001Bean.setSumcourseCount(allpersonnelcount);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rlgl011001Bean;
	}
	/**
	 * 编辑本单位信息完成度Sql
	 * 
	 * @param mtb111AdminScoreList
	 * @param flg
	 * @param unitInfo
	 */
	public String EditStrSql(List<Mtb111Admscore> mtb111AdminScoreList, String flg, Mtb04Unit unitInfo) {
		String sql = "";
		Mtb111Admscore admscore = new Mtb111Admscore();
		if (mtb111AdminScoreList != null && mtb111AdminScoreList.size() > 0) {
			String strSqlB = "(";
			float scoreB = 0;
			String strSqlX = "(";
			float scoreX = 0;
			String strSqlTA = "(";
			float scoreTA = 0;
			String strSqlTB = "(";
			float scoreTB = 0;
			String strSqlTC = "(";
			float scoreTC = 0;
			String strSqlTD = "(";
			float scoreTD = 0;

			for (int i = 0; i < mtb111AdminScoreList.size(); i++) {
				admscore = mtb111AdminScoreList.get(i);
				// 必填项
				if ("1".equals(admscore.getAdmscore_kbn())) {
					strSqlB = strSqlB + " CASE WHEN (" + admscore.getAdmscore_id() + " IS NULL OR " + admscore.getAdmscore_id() + "='') THEN 0 ELSE " + admscore.getAdmscore_score() + " END +";
					scoreB = scoreB + admscore.getAdmscore_score();
				}
				// 选填项
				if ("0".equals(admscore.getAdmscore_kbn())) {
					strSqlX = strSqlX + " CASE WHEN (" + admscore.getAdmscore_id() + " IS NULL OR " + admscore.getAdmscore_id() + "='' ) THEN 0 ELSE " + admscore.getAdmscore_score() + " END +";
					scoreX = scoreX + admscore.getAdmscore_score();
				}
				// 联系信息
				if ("A".equals(admscore.getAdmscore_xb())) {
					strSqlTA = strSqlTA + " CASE WHEN (" + admscore.getAdmscore_id() + " IS NULL OR " + admscore.getAdmscore_id() + "='' ) THEN 0 ELSE " + admscore.getAdmscore_score() + " END +";
					scoreTA = scoreTA + admscore.getAdmscore_score();
				}
				// 执业信息
				if ("B".equals(admscore.getAdmscore_xb())) {
					strSqlTB = strSqlTB + " CASE WHEN (" + admscore.getAdmscore_id() + " IS NULL OR " + admscore.getAdmscore_id() + "='') THEN 0 ELSE " + admscore.getAdmscore_score() + " END +";
					scoreTB = scoreTB + admscore.getAdmscore_score();
				}
				// 基本信息
				if ("C".equals(admscore.getAdmscore_xb())) {
					strSqlTC = strSqlTC + " CASE WHEN (" + admscore.getAdmscore_id() + " IS NULL OR " + admscore.getAdmscore_id() + "='' ) THEN 0 ELSE " + admscore.getAdmscore_score() + " END +";
					scoreTC = scoreTC + admscore.getAdmscore_score();
				}
				// 其他信息
				if ("D".equals(admscore.getAdmscore_xb())) {
					strSqlTD = strSqlTD + " CASE WHEN (" + admscore.getAdmscore_id() + " IS NULL OR " + admscore.getAdmscore_id() + "='' ) THEN 0 ELSE " + admscore.getAdmscore_score() + " END +";
					scoreTD = scoreTD + admscore.getAdmscore_score();
				}
			}

			// 必填项
			if (strSqlB.length() > 1 && scoreB != 0) {
				// 取列表数据时
				if ("1".equals(flg)) {
					sql = sql + ", ";
				}
				sql = sql + strSqlB.substring(0, strSqlB.length() - 1) + ") * 100/" + scoreB + " AS total_score_b_sel";
			} else {
				sql = sql + "0 AS total_score_b_sel";
			}

			// 选填项
			if (strSqlX.length() > 1 && scoreX != 0) {
				sql = sql + "," + strSqlX.substring(0, strSqlX.length() - 1) + ") * 100/" + scoreX + " AS total_score_x_sel";
			} else {
				sql = sql + ",0 AS total_score_x_sel";
			}
			// 联系信息
			if (strSqlTA.length() > 1 && scoreTA != 0) {
				sql = sql + "," + strSqlTA.substring(0, strSqlTA.length() - 1) + ") * 100/" + scoreTA + " AS score_contact_sel";
			} else {
				sql = sql + ",0 AS score_contact_sel";
			}
			// 执业信息
			if (strSqlTB.length() > 1 && scoreTB != 0) {
				sql = sql + "," + strSqlTB.substring(0, strSqlTB.length() - 1) + ") * 100/" + scoreTB + " AS score_professional_sel";
			} else {
				sql = sql + ",0 AS score_professional_sel";
			}
			// 基本信息
			if (strSqlTC.length() > 1 && scoreTC != 0) {
				sql = sql + "," + strSqlTC.substring(0, strSqlTC.length() - 1) + ") * 100/" + scoreTC + " AS score_basic_sel";
			} else {
				sql = sql + ",0 AS score_basic_sel";
			}
			// 其他信息
			if (strSqlTD.length() > 1 && scoreTD != 0) {
				sql = sql + "," + strSqlTD.substring(0, strSqlTD.length() - 1) + ") * 100/" + scoreTD + " AS score_other_sel";
			} else {
				sql = sql + ",0 AS score_other_sel ";
			}
			// 取列表数据时
			if ("1".equals(flg)) {
				sql = "unit_nm,'" + unitInfo.getUnit_nm() + "' As superUnitNm " + sql;
			}
		}
		return sql;
	}

	/**
	 * 编辑下级单位单位信息完成度Sql
	 * 
	 * @param mtb111AdminScoreList
	 * @param flg
	 * @param unitInfo
	 */
	public String EditLowerUnitStrSql(List<Mtb111Admscore> mtb111AdminScoreList, String flg, Mtb04Unit unitInfo) {
		String sql = "";
		Mtb111Admscore admscore = new Mtb111Admscore();
		if (mtb111AdminScoreList != null && mtb111AdminScoreList.size() > 0) {
			String strSqlB = "CASE WHEN (";
			String strSqlX = "CASE WHEN (";
			String strSqlTA = "CASE WHEN (";
			String strSqlTB = "CASE WHEN (";
			String strSqlTC = "CASE WHEN (";
			String strSqlTD = "CASE WHEN (";

			for (int i = 0; i < mtb111AdminScoreList.size(); i++) {
				admscore = mtb111AdminScoreList.get(i);
				// 必填项
				if ("1".equals(admscore.getAdmscore_kbn())) {
					strSqlB = strSqlB + " CASE WHEN (" + admscore.getAdmscore_id() + " IS NULL OR " + admscore.getAdmscore_id() + "='') THEN 0 ELSE " + admscore.getAdmscore_score() + " END +";
				}
				// 选填项
				if ("0".equals(admscore.getAdmscore_kbn())) {
					strSqlX = strSqlX + " CASE WHEN (" + admscore.getAdmscore_id() + " IS NULL OR " + admscore.getAdmscore_id() + "='' ) THEN 0 ELSE " + admscore.getAdmscore_score() + " END +";
				}
				// 联系信息
				if ("A".equals(admscore.getAdmscore_xb())) {
					strSqlTA = strSqlTA + " CASE WHEN (" + admscore.getAdmscore_id() + " IS NULL OR " + admscore.getAdmscore_id() + "='' ) THEN 0 ELSE " + admscore.getAdmscore_score() + " END +";
				}
				// 执业信息
				if ("B".equals(admscore.getAdmscore_xb())) {
					strSqlTB = strSqlTB + " CASE WHEN (" + admscore.getAdmscore_id() + " IS NULL OR " + admscore.getAdmscore_id() + "='') THEN 0 ELSE " + admscore.getAdmscore_score() + " END +";
				}
				// 基本信息
				if ("C".equals(admscore.getAdmscore_xb())) {
					strSqlTC = strSqlTC + " CASE WHEN (" + admscore.getAdmscore_id() + " IS NULL OR " + admscore.getAdmscore_id() + "='' ) THEN 0 ELSE " + admscore.getAdmscore_score() + " END +";
				}
				// 其他信息
				if ("D".equals(admscore.getAdmscore_xb())) {
					strSqlTD = strSqlTD + " CASE WHEN (" + admscore.getAdmscore_id() + " IS NULL OR " + admscore.getAdmscore_id() + "='' ) THEN 0 ELSE " + admscore.getAdmscore_score() + " END +";
				}
			}

			// 必填项
			if (strSqlB.length() > 11) {
				// 取列表数据时
				if ("1".equals(flg)) {
					sql = sql + ", ";
				}
				sql = sql + strSqlB.substring(0, strSqlB.length() - 1) + ") >0 THEN 1 ELSE 0 END AS total_score_b_sel";
			} else {
				sql = sql + "0 AS total_score_b_sel";
			}

			// 选填项
			if (strSqlX.length() > 11) {
				sql = sql + "," + strSqlX.substring(0, strSqlX.length() - 1) + ")  >0 THEN 1 ELSE 0 END AS total_score_x_sel";
			} else {
				sql = sql + ",0 AS total_score_x_sel";
			}
			// 联系信息
			if (strSqlTA.length() > 11) {
				sql = sql + "," + strSqlTA.substring(0, strSqlTA.length() - 1) + ")  >0 THEN 1 ELSE 0 END AS score_contact_sel";
			} else {
				sql = sql + ",0 AS score_contact_sel";
			}
			// 执业信息
			if (strSqlTB.length() > 11) {
				sql = sql + "," + strSqlTB.substring(0, strSqlTB.length() - 1) + ")  >0 THEN 1 ELSE 0 END AS score_professional_sel";
			} else {
				sql = sql + ",0 AS score_professional_sel";
			}
			// 基本信息
			if (strSqlTC.length() > 11) {
				sql = sql + "," + strSqlTC.substring(0, strSqlTC.length() - 1) + ")  >0 THEN 1 ELSE 0 END AS score_basic_sel";
			} else {
				sql = sql + ",0 AS score_basic_sel";
			}
			// 其他信息
			if (strSqlTD.length() > 11) {
				sql = sql + "," + strSqlTD.substring(0, strSqlTD.length() - 1) + ")  >0 THEN 1 ELSE 0 END AS score_other_sel";
			} else {
				sql = sql + ",0 AS score_other_sel ";
			}
			// 取列表数据时
			if ("1".equals(flg)) {
				sql = "unit_nm,'" + unitInfo.getUnit_nm() + "' As superUnitNm " + sql;
			}
		}
		return sql;
	}

	/**
	 * 编辑单位基本信息维护情况表Sql
	 * 
	 * @param mtb111AdminScoreList
	 * @param flg
	 * @param unitInfo
	 */
	public String EditStrSqlArea(List<Mtb111Admscore> mtb111AdminScoreList, String flg, Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew) {
		String sql = "";
		Mtb111Admscore admscore = new Mtb111Admscore();
		if (mtb111AdminScoreList != null && mtb111AdminScoreList.size() > 0) {
			String strSqlB = "(";
			float scoreB = 0;
			String strSqlX = "(";
			float scoreX = 0;
			String strSqlTA = "(";
			float scoreTA = 0;
			String strSqlTB = "(";
			float scoreTB = 0;
			String strSqlTC = "(";
			float scoreTC = 0;
			String strSqlTD = "(";
			float scoreTD = 0;

			for (int i = 0; i < mtb111AdminScoreList.size(); i++) {
				admscore = mtb111AdminScoreList.get(i);
				// 必填项
				if ("1".equals(admscore.getAdmscore_kbn())) {
					strSqlB = strSqlB + " CASE WHEN (" + admscore.getAdmscore_id() + " IS NULL OR " + admscore.getAdmscore_id() + "='') THEN 0 ELSE " + admscore.getAdmscore_score() + " END +";
					scoreB = scoreB + admscore.getAdmscore_score();
				}
				// 选填项
				if ("0".equals(admscore.getAdmscore_kbn())) {
					strSqlX = strSqlX + " CASE WHEN (" + admscore.getAdmscore_id() + " IS NULL OR " + admscore.getAdmscore_id() + "='' ) THEN 0 ELSE " + admscore.getAdmscore_score() + " END +";
					scoreX = scoreX + admscore.getAdmscore_score();
				}
				// 联系信息
				if ("A".equals(admscore.getAdmscore_xb())) {
					strSqlTA = strSqlTA + " CASE WHEN (" + admscore.getAdmscore_id() + " IS NULL OR " + admscore.getAdmscore_id() + "='' ) THEN 0 ELSE " + admscore.getAdmscore_score() + " END +";
					scoreTA = scoreTA + admscore.getAdmscore_score();
				}
				// 执业信息
				if ("B".equals(admscore.getAdmscore_xb())) {
					strSqlTB = strSqlTB + " CASE WHEN (" + admscore.getAdmscore_id() + " IS NULL OR " + admscore.getAdmscore_id() + "='') THEN 0 ELSE " + admscore.getAdmscore_score() + " END +";
					scoreTB = scoreTB + admscore.getAdmscore_score();
				}
				// 基本信息
				if ("C".equals(admscore.getAdmscore_xb())) {
					strSqlTC = strSqlTC + " CASE WHEN (" + admscore.getAdmscore_id() + " IS NULL OR " + admscore.getAdmscore_id() + "='' ) THEN 0 ELSE " + admscore.getAdmscore_score() + " END +";
					scoreTC = scoreTC + admscore.getAdmscore_score();
				}
				// 其他信息
				if ("D".equals(admscore.getAdmscore_xb())) {
					strSqlTD = strSqlTD + " CASE WHEN (" + admscore.getAdmscore_id() + " IS NULL OR " + admscore.getAdmscore_id() + "='' ) THEN 0 ELSE " + admscore.getAdmscore_score() + " END +";
					scoreTD = scoreTD + admscore.getAdmscore_score();
				}
			}

			// 必填项
			if (strSqlB.length() > 1 && scoreB != 0) {
				sql = sql + strSqlB.substring(0, strSqlB.length() - 1) + ") AS total_score_b_sel";
			} else {
				sql = sql + "0 AS total_score_b_sel";
			}

			// 选填项
			if (strSqlX.length() > 1 && scoreX != 0) {
				sql = sql + "," + strSqlX.substring(0, strSqlX.length() - 1) + ") AS total_score_x_sel";
			} else {
				sql = sql + ",0 AS total_score_x_sel";
			}
			// 联系信息
			if (strSqlTA.length() > 1 && scoreTA != 0) {
				sql = sql + "," + strSqlTA.substring(0, strSqlTA.length() - 1) + ") AS score_contact_sel";
			} else {
				sql = sql + ",0 AS score_contact_sel";
			}
			// 执业信息
			if (strSqlTB.length() > 1 && scoreTB != 0) {
				sql = sql + "," + strSqlTB.substring(0, strSqlTB.length() - 1) + ") AS score_professional_sel";
			} else {
				sql = sql + ",0 AS score_professional_sel";
			}
			// 基本信息
			if (strSqlTC.length() > 1 && scoreTC != 0) {
				sql = sql + "," + strSqlTC.substring(0, strSqlTC.length() - 1) + ") AS score_basic_sel";
			} else {
				sql = sql + ",0 AS score_basic_sel";
			}
			// 其他信息
			if (strSqlTD.length() > 1 && scoreTD != 0) {
				sql = sql + "," + strSqlTD.substring(0, strSqlTD.length() - 1) + ") AS score_other_sel";
			} else {
				sql = sql + ",0 AS score_other_sel ";
			}

			String areaSql = "";
			if (unitInfo.getArea_id() != null && !"".equals(unitInfo.getArea_id())) {
				if (unitInfo.getArea_id().length() == 2) {
					areaSql = "SELECT SUBSTR(MIN(area_id),1,4) as area_id FROM m_tb20_area where area_id like CONCAT('" + unitInfo.getArea_id() + "' ,'%') GROUP BY city ORDER BY area_id";
				} else if (unitInfo.getArea_id().length() == 4) {
					areaSql = "SELECT SUBSTR(MIN(area_id),1,6) as area_id FROM m_tb20_area where area_id like CONCAT('" + unitInfo.getArea_id() + "' ,'%') GROUP BY zone ORDER BY area_id";
				}
				// 取本单位建立的医疗单位
				sql = "SELECT AREA_ID,COUNT(DISTINCT UNIT_NO) AS unitCount," + sql + " FROM M_TB04_UNIT WHERE UNIT_SUPER ='" + unitInfo.getArea_id() + "' and UNIT_ASSORT='002' ";
				if(!StringUtils.isBlank(areaSql)){
					sql = sql+" and AREA_ID in (" + areaSql + ") ";
				}
				sql = sql+" GROUP BY area_id  ORDER BY area_id";
				// if (rlgl011001BeanNew.getPageCount() > 0)
				// {
				// sql = sql + " LIMIT " + rlgl011001BeanNew.getPageNo() +"," +
				// rlgl011001BeanNew.getPageCount();
				// }
			} else {
				sql = "";
			}
		}
		return sql;
	}

	/**
	 * 单位建立情况表Sql
	 * 
	 * @param unitInfo
	 */
	public String EditStrSqlAreaCount(Mtb04Unit unitInfo) {
		//String sql = "SELECT UNIT_SUPER ";
		String sql = "SELECT AREA_ID ";
		// 市属医院
		// 单位性质=事业单位 且 单位管理级别=市直属 且 行业划分=公立医院
		sql = sql + ",SUM(CASE WHEN UNIT_NATURE ='056' AND UNIT_MANAGE_SCALE='063' AND UNIT_MANAGE_SCALE_TOW = '001' AND INDUSTRY_DIVIDE='153' AND INDUSTRY_DIVIDE_TWO='167' THEN 1 ELSE 0 END) AS SSHCOUNT";
		// 市管医院
		// 单位管理级别=市直管
		sql = sql + ",SUM(CASE WHEN UNIT_MANAGE_SCALE='063' AND UNIT_MANAGE_SCALE_TOW = '002' THEN 1 ELSE 0 END) AS SGCOUNT";
		// 区县医院
		// 单位性质=事业单位 且 （单位管理级别=县直属 或 单位管理级别=县直管） 且 行业划分=公立医院
		sql = sql + ",SUM(CASE WHEN UNIT_NATURE ='056' AND UNIT_MANAGE_SCALE='064' AND (UNIT_MANAGE_SCALE_TOW = '001' OR UNIT_MANAGE_SCALE_TOW = '002') AND INDUSTRY_DIVIDE='153' AND INDUSTRY_DIVIDE_TWO='167' THEN 1 ELSE 0 END) AS QXCOUNT";
		// 企业医院
		// 单位性质=企业 且 单位管理级别 = 空 且 行业划分=空
		sql = sql + ",SUM(CASE WHEN UNIT_NATURE ='059' AND (UNIT_MANAGE_SCALE IS NULL OR UNIT_MANAGE_SCALE = '') AND (INDUSTRY_DIVIDE IS NULL OR INDUSTRY_DIVIDE = '') THEN 1 ELSE 0 END) AS QYCOUNT";
		// 乡镇卫生所
		// 单位性质=事业单位 且 单位管理级别=乡镇 且 行业划分=社区医疗与卫生院
		sql = sql + ",SUM(CASE WHEN UNIT_NATURE ='056' AND UNIT_MANAGE_SCALE='998' AND INDUSTRY_DIVIDE='153' AND INDUSTRY_DIVIDE_TWO='168' THEN 1 ELSE 0 END) AS SZHCOUNT";
		// 门诊
		// 单位性质=事业单位 且 单位管理级别=空 且 行业划分=门诊部（所）
		sql = sql + ",SUM(CASE WHEN UNIT_NATURE ='056' AND (UNIT_MANAGE_SCALE IS NULL OR UNIT_MANAGE_SCALE = '') AND INDUSTRY_DIVIDE='153' AND INDUSTRY_DIVIDE_TWO='169' THEN 1 ELSE 0 END) AS MZCOUNT";
		// 村卫生室
		// 单位性质=事业单位 且 单位管理级别=村（社区） 且 行业划分=社区医疗与卫生院
		sql = sql + ",SUM(CASE WHEN UNIT_NATURE ='056' AND UNIT_MANAGE_SCALE='999' AND INDUSTRY_DIVIDE='153' AND INDUSTRY_DIVIDE_TWO='168' THEN 1 ELSE 0 END) AS CWSCOUNT";
		// 合计
		sql = sql + ",(SUM(CASE WHEN UNIT_NATURE ='056' AND UNIT_MANAGE_SCALE='063' AND UNIT_MANAGE_SCALE_TOW = '001' AND INDUSTRY_DIVIDE='153' AND INDUSTRY_DIVIDE_TWO='167' THEN 1 ELSE 0 END)";
		sql = sql + " + SUM(CASE WHEN UNIT_MANAGE_SCALE='063' AND UNIT_MANAGE_SCALE_TOW = '002' THEN 1 ELSE 0 END)";
		sql = sql + " + SUM(CASE WHEN UNIT_NATURE ='056' AND UNIT_MANAGE_SCALE='064' AND (UNIT_MANAGE_SCALE_TOW = '001' OR UNIT_MANAGE_SCALE_TOW = '002') AND INDUSTRY_DIVIDE='153' AND INDUSTRY_DIVIDE_TWO='167' THEN 1 ELSE 0 END)";
		sql = sql + " + SUM(CASE WHEN UNIT_NATURE ='059' AND (UNIT_MANAGE_SCALE IS NULL OR UNIT_MANAGE_SCALE = '') AND (INDUSTRY_DIVIDE IS NULL OR INDUSTRY_DIVIDE = '') THEN 1 ELSE 0 END)";
		sql = sql + " + SUM(CASE WHEN UNIT_NATURE ='056' AND UNIT_MANAGE_SCALE='998' AND INDUSTRY_DIVIDE='153' AND INDUSTRY_DIVIDE_TWO='168' THEN 1 ELSE 0 END)";
		sql = sql + " + SUM(CASE WHEN UNIT_NATURE ='056' AND (UNIT_MANAGE_SCALE IS NULL OR UNIT_MANAGE_SCALE = '') AND INDUSTRY_DIVIDE='153' AND INDUSTRY_DIVIDE_TWO='169' THEN 1 ELSE 0 END)";
		sql = sql + " + SUM(CASE WHEN UNIT_NATURE ='056' AND UNIT_MANAGE_SCALE='999' AND INDUSTRY_DIVIDE='153' AND INDUSTRY_DIVIDE_TWO='168' THEN 1 ELSE 0 END)";

		sql = sql + ") AS unitCount";

		sql = sql + " FROM M_TB04_UNIT WHERE UNIT_NO IN ( SELECT UNIT_NO FROM M_TB04_UNIT WHERE UNIT_SUPER='" + unitInfo.getUnit_no() + "' AND UNIT_ASSORT='001' AND DEL_KBN='0') ";
		sql = sql + "GROUP BY AREA_ID";
		return sql;
	}

	/**
	 * 人员分专业建立情况SQL
	 * 
	 * @param unitInfo
	 */
	public String EditStrSqlPersonProfession(Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew) {
		String sql = "SELECT ";
		// 单位编码
		sql = sql + "M.PERSONNEL_UNIT AS UNIT_NO ";
		sql = sql + ",M2.UNIT_NM AS UNIT_NM ";
		// 医
		sql = sql + ",SUM(case when trim(M1.TYPE) ='183' then 1 else 0 end) AS physicianCount ";
		// 护
		sql = sql + ",SUM(case when M1.TYPE ='184' then 1 else 0 end) AS nurseCount ";
		// 药
		sql = sql + ",SUM(case when M1.TYPE ='185' then 1 else 0 end) AS medicineCount ";
		// 检
		sql = sql + ",SUM(case when M1.TYPE ='186' then 1 else 0 end) AS examineCount ";
		// 放
		sql = sql + ",SUM(case when M1.TYPE ='187' then 1 else 0 end) AS radiationCount ";
		// 其他
		sql = sql + ",SUM(case when M1.TYPE IS NULL OR (M1.TYPE !='183' AND M1.TYPE !='184' AND M1.TYPE !='185' AND M1.TYPE !='186' AND M1.TYPE !='187') then 1 else 0 end) AS otherCount ";

		// 合计
		sql = sql + ",count(DISTINCT M.PERSONNEL_ID) AS totalCount";

		sql = sql + " FROM M_TB12_PERSONNEL M";
		sql = sql + " LEFT JOIN m_tb19_personnel_practitioners_info M1";
		sql = sql + " ON M.PERSONNEL_ID = M1.PERSONNEL_ID";
		sql = sql + " AND M1.DEL_KBN = '0'";
		sql = sql + "  LEFT JOIN m_tb04_unit M2";
		sql = sql + " ON M.PERSONNEL_UNIT = M2.UNIT_NO";
		sql = sql + " AND M2.DEL_KBN = '0'";
		sql = sql + " WHERE";
		sql = sql + " M.PERSONNEL_UNIT ";
		sql = sql + " IN ( SELECT UNIT_NO FROM M_TB04_UNIT WHERE ( UNIT_NO = '" + unitInfo.getUnit_no() + "' OR UNIT_SUPER='" + unitInfo.getUnit_no() + "' ) AND UNIT_ASSORT='002' AND DEL_KBN = '0') ";
		sql = sql + " GROUP BY UNIT_NO";
		sql = sql + " ORDER BY UNIT_NO";
		return sql;
	}

	/**
	 * 人员状态维护审核情况表SQL
	 * 
	 * @param unitInfo
	 */
	public String EditStrSqlPersonStatus(Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew) {
		String sql = "SELECT ";
		// 单位编码
		sql = sql + "M.PERSONNEL_UNIT AS UNIT_NO ";
		// 人事口未维护
		sql = sql + ",SUM(case when (M.PERSONNEL_STATUS IS NULL OR M.PERSONNEL_STATUS ='') AND (M1.PERSONNEL_STATUS IS NULL OR M1.PERSONNEL_STATUS ='') THEN 1 ELSE 0 END) AS hirMaintainCount ";
		// 人事口未终审
		sql = sql + ",SUM(case when (M.PERSONNEL_STATUS IS NULL OR M.PERSONNEL_STATUS ='') AND M1.PERSONNEL_STATUS ='001' THEN 1 ELSE 0 END) AS hirReviewCount ";
		// 医政口未维护
		sql = sql + ",SUM(0) AS medicalMaintainCount ";
		// 医政口未终审
		sql = sql + ",SUM(0) AS medicalReviewCount ";
		// 人员总数
		sql = sql + ",COUNT(DISTINCT M.PERSONNEL_ID) AS personnelCount ";

		sql = sql + " FROM m_tb12_personnel M, m_tb39_personnel M1 ";
		sql = sql + "WHERE M.PERSONNEL_ID = M1.PERSONNEL_ID ";
		sql = sql + "AND M.DEL_KBN='0' ";
		sql = sql + "AND M1.DEL_KBN='0' ";
		sql = sql + "AND M.PERSONNEL_UNIT ";
		sql = sql + " IN ( SELECT UNIT_NO FROM M_TB04_UNIT WHERE ( UNIT_NO = '" + unitInfo.getUnit_no() + "' OR UNIT_SUPER='" + unitInfo.getUnit_no() + "' ) AND DEL_KBN = '0') ";
		sql = sql + " GROUP BY UNIT_NO";
		sql = sql + " ORDER BY UNIT_NO";
		return sql;
	}

	/**
	 * 人员各模块信息维护情况表Sql
	 * 
	 * @param mtb111AdminScoreList
	 * @param flg
	 * @param unitInfo
	 */
	public String EditPersonnelDataInfoSql(Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew) {
		String sql = "SELECT ";
		// 单位编码
		sql = sql + "M.PERSONNEL_UNIT AS UNIT_NO ";
		// 单位人员总数
		sql = sql + ",COUNT(DISTINCT M.PERSONNEL_ID) AS personnelCount ";
		// 基础数据
		sql = sql + ",SUM(CASE WHEN M.LOGIN_DATE < M.UPDATE_DATE THEN 1 ELSE 0 END) AS bisicCount ";
		// 资格信息
		sql = sql + ",SUM(CASE WHEN (M1.PERSONNEL_ID IS NULL OR M1.PERSONNEL_ID='') THEN 0 ELSE 1 END) AS personnelPractitionersCount ";
		// 专业技术职务
		sql = sql + ",SUM(CASE WHEN (M2.PERSONNEL_ID IS NULL OR M2.PERSONNEL_ID='') THEN 0 ELSE 1 END) AS professionalTechnicalCount ";
		// 行政职务
		sql = sql + ",SUM(CASE WHEN (M3.PERSONNEL_ID IS NULL OR M3.PERSONNEL_ID='') THEN 0 ELSE 1 END) AS administrativePostCount ";
		// 社会关系
		sql = sql + ",SUM(CASE WHEN (M4.PERSONNEL_ID IS NULL OR M4.PERSONNEL_ID='') THEN 0 ELSE 1 END) AS socialRelationsCount ";
		// 学习经历
		sql = sql + ",SUM(CASE WHEN (M5.PERSONNEL_ID IS NULL OR M5.PERSONNEL_ID='') THEN 0 ELSE 1 END) AS learningExperienceCount ";
		// 工作经历
		sql = sql + ",SUM(CASE WHEN (M6.PERSONNEL_ID IS NULL OR M6.PERSONNEL_ID='') THEN 0 ELSE 1 END) AS personnelWorkCount ";
		// 党派信息
		sql = sql + ",SUM(CASE WHEN (M7.PERSONNEL_ID IS NULL OR M7.PERSONNEL_ID='') THEN 0 ELSE 1 END) AS personnelPartisanCount ";
		// 导师信息
		sql = sql + ",SUM(CASE WHEN (M8.PERSONNEL_ID IS NULL OR M8.PERSONNEL_ID='') THEN 0 ELSE 1 END) AS personnelTutorCount ";
		// 执业信息
		sql = sql + ",SUM(CASE WHEN (M9.PERSONNEL_ID IS NULL OR M9.PERSONNEL_ID='') THEN 0 ELSE 1 END) AS personnelPracticeCount ";

		sql = sql + " FROM ";
		sql = sql + " m_tb39_personnel M ";
		sql = sql + " LEFT JOIN m_tb46_personnel_practitioners_info M1 ON ";
		sql = sql + " M.PERSONNEL_ID = M1.PERSONNEL_ID ";
		sql = sql + " AND M1.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb45_personnel_professional_info M2 ON ";
		sql = sql + " M.PERSONNEL_ID = M2.PERSONNEL_ID ";
		sql = sql + " AND M2.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb44_personnel_job_info M3 ON ";
		sql = sql + " M.PERSONNEL_ID = M3.PERSONNEL_ID ";
		sql = sql + " AND M3.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb43_personnel_social_info M4 ON ";
		sql = sql + " M.PERSONNEL_ID = M4.PERSONNEL_ID ";
		sql = sql + " AND M4.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb42_personnel_edu_info M5 ON ";
		sql = sql + " M.PERSONNEL_ID = M5.PERSONNEL_ID ";
		sql = sql + " AND M5.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb41_personnel_work_info M6 ON ";
		sql = sql + " M.PERSONNEL_ID = M6.PERSONNEL_ID ";
		sql = sql + " AND M6.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb40_personnel_partisan_info M7 ON ";
		sql = sql + " M.PERSONNEL_ID = M7.PERSONNEL_ID ";
		sql = sql + " AND M7.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb59_personnel_tutor_info M8 ON ";
		sql = sql + " M.PERSONNEL_ID = M8.PERSONNEL_ID ";
		sql = sql + " AND M8.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb78_personnel_practice_info M9 ON ";
		sql = sql + " M.PERSONNEL_ID = M9.PERSONNEL_ID ";
		sql = sql + " AND M9.DEL_KBN = '0' ";

		sql = sql + " WHERE ";
		sql = sql + " M.DEL_KBN='0' ";
		sql = sql + " AND M.PERSONNEL_UNIT ";
		sql = sql + " IN ( SELECT UNIT_NO FROM M_TB04_UNIT WHERE  (UNIT_NO = '" + unitInfo.getUnit_no() + "' OR UNIT_SUPER='" + unitInfo.getUnit_no() + "') AND DEL_KBN = '0') ";
		sql = sql + " GROUP BY UNIT_NO";
		sql = sql + " ORDER BY UNIT_NO";
		return sql;
	}

	/**
	 * 人员各模块信息维护明细情况表Sql
	 * 
	 * @param mtb111AdminScoreList
	 * @param flg
	 * @param unitInfo
	 */
	public String EditPersonnelDataDetailInfoSql(Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew) {
		String sql = "SELECT ";
		// 单位编码
		sql = sql + "M.PERSONNEL_UNIT AS UNIT_NO ";
		// 身份证号
		sql = sql + ",M.PERSONNEL_ID AS PERSONNEL_ID ";
		// 姓名
		sql = sql + ",M.PERSONNEL_NM AS PERSONNEL_NM ";
		// 单位
		sql = sql + ",M10.UNIT_NM AS UNIT_NM ";
		// 数量
		sql = sql + ",1 AS personnelCount ";
		// 基础数据
		sql = sql + ",CASE WHEN M.LOGIN_DATE < M.UPDATE_DATE THEN 1 ELSE 0 END AS bisicCount ";
		// 资格信息
		sql = sql + ",CASE WHEN (M1.PERSONNEL_ID IS NULL OR M1.PERSONNEL_ID='') THEN 0 ELSE 1 END AS personnelPractitionersCount ";
		// 专业技术职务
		sql = sql + ",CASE WHEN (M2.PERSONNEL_ID IS NULL OR M2.PERSONNEL_ID='') THEN 0 ELSE 1 END AS professionalTechnicalCount ";
		// 行政职务
		sql = sql + ",CASE WHEN (M3.PERSONNEL_ID IS NULL OR M3.PERSONNEL_ID='') THEN 0 ELSE 1 END AS administrativePostCount ";
		// 社会关系
		sql = sql + ",CASE WHEN (M4.PERSONNEL_ID IS NULL OR M4.PERSONNEL_ID='') THEN 0 ELSE 1 END AS socialRelationsCount ";
		// 学习经历
		sql = sql + ",CASE WHEN (M5.PERSONNEL_ID IS NULL OR M5.PERSONNEL_ID='') THEN 0 ELSE 1 END AS learningExperienceCount ";
		// 工作经历
		sql = sql + ",CASE WHEN (M6.PERSONNEL_ID IS NULL OR M6.PERSONNEL_ID='') THEN 0 ELSE 1 END AS personnelWorkCount ";
		// 党派信息
		sql = sql + ",CASE WHEN (M7.PERSONNEL_ID IS NULL OR M7.PERSONNEL_ID='') THEN 0 ELSE 1 END AS personnelPartisanCount ";
		// 导师信息
		sql = sql + ",CASE WHEN (M8.PERSONNEL_ID IS NULL OR M8.PERSONNEL_ID='') THEN 0 ELSE 1 END AS personnelTutorCount ";
		// 执业信息
		sql = sql + ",CASE WHEN (M9.PERSONNEL_ID IS NULL OR M9.PERSONNEL_ID='') THEN 0 ELSE 1 END AS personnelPracticeCount ";

		sql = sql + " FROM ";
		sql = sql + " m_tb39_personnel M ";
		sql = sql + " LEFT JOIN m_tb46_personnel_practitioners_info M1 ON ";
		sql = sql + " M.PERSONNEL_ID = M1.PERSONNEL_ID ";
		sql = sql + " AND M1.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb45_personnel_professional_info M2 ON ";
		sql = sql + " M.PERSONNEL_ID = M2.PERSONNEL_ID ";
		sql = sql + " AND M2.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb44_personnel_job_info M3 ON ";
		sql = sql + " M.PERSONNEL_ID = M3.PERSONNEL_ID ";
		sql = sql + " AND M3.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb43_personnel_social_info M4 ON ";
		sql = sql + " M.PERSONNEL_ID = M4.PERSONNEL_ID ";
		sql = sql + " AND M4.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb42_personnel_edu_info M5 ON ";
		sql = sql + " M.PERSONNEL_ID = M5.PERSONNEL_ID ";
		sql = sql + " AND M5.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb41_personnel_work_info M6 ON ";
		sql = sql + " M.PERSONNEL_ID = M6.PERSONNEL_ID ";
		sql = sql + " AND M6.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb40_personnel_partisan_info M7 ON ";
		sql = sql + " M.PERSONNEL_ID = M7.PERSONNEL_ID ";
		sql = sql + " AND M7.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb59_personnel_tutor_info M8 ON ";
		sql = sql + " M.PERSONNEL_ID = M8.PERSONNEL_ID ";
		sql = sql + " AND M8.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb78_personnel_practice_info M9 ON ";
		sql = sql + " M.PERSONNEL_ID = M9.PERSONNEL_ID ";
		sql = sql + " AND M9.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb04_unit M10 ON ";
		sql = sql + " M.PERSONNEL_UNIT = M10.UNIT_NO ";
		sql = sql + " AND M10.DEL_KBN = '0' ";

		sql = sql + " WHERE ";
		sql = sql + " M.DEL_KBN='0' ";
		sql = sql + " AND M.PERSONNEL_UNIT ";
		sql = sql + " IN ( SELECT UNIT_NO FROM M_TB04_UNIT WHERE (UNIT_NO = '" + unitInfo.getUnit_no() + "' OR UNIT_SUPER='" + unitInfo.getUnit_no() + "') AND DEL_KBN = '0') ";
		sql = sql + " ORDER BY UNIT_NO,PERSONNEL_ID";
		if (rlgl011001BeanNew.getPageCount() > 0) {
			sql = sql + " LIMIT " + rlgl011001BeanNew.getPageNo() + "," + rlgl011001BeanNew.getPageCount();
		}
		return sql;
	}

	/**
	 * 单位最近X月更新情况Sql
	 * 
	 * @param mtb111AdminScoreList
	 * @param flg
	 * @param unitInfo
	 */
	public String EditUnitDataXmonthUpdateInfoSql(List<Mtb111Admscore> mtb111AdminScoreList, Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew) {
		String sql = "";
		Mtb111Admscore admscore = new Mtb111Admscore();
		if (mtb111AdminScoreList != null && mtb111AdminScoreList.size() > 0) {
			String strSqlB = "(";
			String strSqlX = "(";
			String strSqlTA = "(";
			String strSqlTB = "(";
			String strSqlTC = "(";
			String strSqlTD = "(";

			for (int i = 0; i < mtb111AdminScoreList.size(); i++) {
				admscore = mtb111AdminScoreList.get(i);
				// 必填项
				if ("1".equals(admscore.getAdmscore_kbn())) {
					strSqlB = strSqlB + " CASE WHEN M1." + admscore.getAdmscore_id() + " ='1' THEN 1 ELSE 0 END +";
				}
				// 选填项
				if ("0".equals(admscore.getAdmscore_kbn())) {
					strSqlX = strSqlX + " CASE WHEN M1." + admscore.getAdmscore_id() + "='1' THEN 1 ELSE 0 END +";
				}
				// 联系信息
				if ("A".equals(admscore.getAdmscore_xb())) {
					strSqlTA = strSqlTA + " CASE WHEN M1." + admscore.getAdmscore_id() + "='1' THEN 1 ELSE 0 END +";
				}
				// 执业信息
				if ("B".equals(admscore.getAdmscore_xb())) {
					strSqlTB = strSqlTB + " CASE WHEN M1." + admscore.getAdmscore_id() + "='1' THEN 1 ELSE 0 END +";
				}
				// 基本信息
				if ("C".equals(admscore.getAdmscore_xb())) {
					strSqlTC = strSqlTC + " CASE WHEN M1." + admscore.getAdmscore_id() + "='1' THEN 1 ELSE 0 END +";
				}
				// 其他信息
				if ("D".equals(admscore.getAdmscore_xb())) {
					strSqlTD = strSqlTD + " CASE WHEN M1." + admscore.getAdmscore_id() + "='1' THEN 1 ELSE 0 END +";
				}
			}

			// 必填项
			if (strSqlB.length() > 1) {
				sql = sql + "CASE WHEN " + strSqlB.substring(0, strSqlB.length() - 1) + ")> 0 THEN 1 ELSE 0 END AS total_score_b_sel";
			} else {
				sql = sql + "0 AS total_score_b_sel";
			}

			// 选填项
			if (strSqlX.length() > 1) {
				sql = sql + "," + "CASE WHEN " + strSqlX.substring(0, strSqlX.length() - 1) + ") > 0 THEN 1 ELSE 0 END AS total_score_x_sel";
			} else {
				sql = sql + ",0 AS total_score_x_sel";
			}
			// 联系信息
			if (strSqlTA.length() > 1) {
				sql = sql + "," + "CASE WHEN " + strSqlTA.substring(0, strSqlTA.length() - 1) + ") > 0 THEN 1 ELSE 0 END AS score_contact_sel";
			} else {
				sql = sql + ",0 AS score_contact_sel";
			}
			// 执业信息
			if (strSqlTB.length() > 1) {
				sql = sql + "," + "CASE WHEN " + strSqlTB.substring(0, strSqlTB.length() - 1) + ") > 0 THEN 1 ELSE 0 END AS score_professional_sel";
			} else {
				sql = sql + ",0 AS score_professional_sel";
			}
			// 基本信息
			if (strSqlTC.length() > 1) {
				sql = sql + "," + "CASE WHEN " + strSqlTC.substring(0, strSqlTC.length() - 1) + ") > 0 THEN 1 ELSE 0 END AS score_basic_sel";
			} else {
				sql = sql + ",0 AS score_basic_sel";
			}
			// 其他信息
			if (strSqlTD.length() > 1) {
				sql = sql + "," + "CASE WHEN " + strSqlTD.substring(0, strSqlTD.length() - 1) + ") > 0 THEN 1 ELSE 0 END AS score_other_sel";
			} else {
				sql = sql + ",0 AS score_other_sel ";
			}

			String strsql = "SELECT ";
			sql = strsql + " M.unit_nm AS unit_nm, M.UNIT_NO AS UNIT_NO," + sql;
			sql = sql + " FROM m_tb04_unit M, m_tb112_admunittbupdate M1 ";
			sql = sql + " WHERE M.UNIT_NO = M1.UNIT_NO ";
			sql = sql + " AND M.UNIT_NO ";
			sql = sql + " IN ( SELECT UNIT_NO FROM M_TB04_UNIT WHERE (UNIT_NO = '" + unitInfo.getUnit_no() + "' OR UNIT_SUPER='" + unitInfo.getUnit_no() + "') AND DEL_KBN = '0') ";
			if (rlgl011001BeanNew.getStartYear() != null && !"".equals(rlgl011001BeanNew.getStartYear()) && rlgl011001BeanNew.getStartMonth() != null && !"".equals(rlgl011001BeanNew.getStartMonth())) {
				sql = sql + " AND SUBSTR(admtb_updatetime FROM 1 FOR 6) >= " + rlgl011001BeanNew.getStartYear() + rlgl011001BeanNew.getStartMonth();
			}
			if (rlgl011001BeanNew.getEndYear() != null && !"".equals(rlgl011001BeanNew.getEndYear()) && rlgl011001BeanNew.getEndMonth() != null && !"".equals(rlgl011001BeanNew.getEndMonth())) {
				sql = sql + " AND SUBSTR(admtb_updatetime FROM 1 FOR 6) <= " + rlgl011001BeanNew.getEndYear() + rlgl011001BeanNew.getEndMonth();
			}
			sql = sql + " ORDER BY UNIT_NO";
		}
		return sql;
	}

	/**
	 * 人员近X月各模块信息更新情况
	 * 
	 * @param mtb111AdminScoreList
	 * @param flg
	 * @param unitInfo
	 */
	public String EditPersonnelDataInfoXMonthUpdateSql(Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew) {
		String sql = "SELECT ";
		// 单位编码
		sql = sql + "M.PERSONNEL_UNIT AS UNIT_NO ";
		// 单位人员总数
		sql = sql + ",COUNT(DISTINCT M.PERSONNEL_ID) AS personnelCount ";
		// 基础数据
		sql = sql + ",CASE WHEN SUM(CASE WHEN M1.bisic='1' THEN 1 ELSE 0 END) > 0 THEN 1 ELSE 0 END AS bisicCount ";
		// 资格信息
		sql = sql + ",CASE WHEN SUM(CASE WHEN M1.personnelPractitioners='1' THEN 1 ELSE 0 END) > 0 THEN 1 ELSE 0 END AS personnelPractitionersCount ";
		// 专业技术职务
		sql = sql + ",CASE WHEN SUM(CASE WHEN M1.professionalTechnical='1' THEN 1 ELSE 0 END) > 0 THEN 1 ELSE 0 END AS professionalTechnicalCount ";
		// 行政职务
		sql = sql + ",CASE WHEN SUM(CASE WHEN M1.administrativePost='1' THEN 1 ELSE 0 END) > 0 THEN 1 ELSE 0 END AS administrativePostCount ";
		// 社会关系
		sql = sql + ",CASE WHEN SUM(CASE WHEN M1.socialRelations='1' THEN 1 ELSE 0 END) > 0 THEN 1 ELSE 0 END AS socialRelationsCount ";
		// 学习经历
		sql = sql + ",CASE WHEN SUM(CASE WHEN M1.learningExperience='1' THEN 1 ELSE 0 END) > 0 THEN 1 ELSE 0 END AS learningExperienceCount ";
		// 工作经历
		sql = sql + ",CASE WHEN SUM(CASE WHEN M1.personnelWork='1' THEN 1 ELSE 0 END) > 0 THEN 1 ELSE 0 END AS personnelWorkCount ";
		// 党派信息
		sql = sql + ",CASE WHEN SUM(CASE WHEN M1.personnelPartisan='1' THEN 1 ELSE 0 END) > 0 THEN 1 ELSE 0 END AS personnelPartisanCount ";
		// 导师信息
		sql = sql + ",CASE WHEN SUM(CASE WHEN M1.personnelTutor='1' THEN 1 ELSE 0 END) > 0 THEN 1 ELSE 0 END AS personnelTutorCount ";
		// 执业信息
		sql = sql + ",CASE WHEN SUM(CASE WHEN M1.personnelPractice='1' THEN 1 ELSE 0 END) > 0 THEN 1 ELSE 0 END AS personnelPracticeCount ";

		sql = sql + " FROM ";
		sql = sql + " m_tb12_personnel M ";

		sql = sql + " LEFT JOIN m_tb113_personnelupdate M1 ON ";
		sql = sql + " M.PERSONNEL_ID = M1.PERSONNEL_ID ";
		sql = sql + " AND M1.DEL_KBN = '0' ";
		if (rlgl011001BeanNew.getStartYear() != null && !"".equals(rlgl011001BeanNew.getStartYear()) && rlgl011001BeanNew.getStartMonth() != null && !"".equals(rlgl011001BeanNew.getStartMonth())) {
			sql = sql + " AND SUBSTR(M1.admtb_updatetime FROM 1 FOR 6) >= " + rlgl011001BeanNew.getStartYear() + rlgl011001BeanNew.getStartMonth();
		}
		if (rlgl011001BeanNew.getEndYear() != null && !"".equals(rlgl011001BeanNew.getEndYear()) && rlgl011001BeanNew.getEndMonth() != null && !"".equals(rlgl011001BeanNew.getEndMonth())) {
			sql = sql + " AND SUBSTR(M1.admtb_updatetime FROM 1 FOR 6) <= " + rlgl011001BeanNew.getEndYear() + rlgl011001BeanNew.getEndMonth();
		}

		sql = sql + " WHERE ";
		sql = sql + " M.DEL_KBN='0' ";
		sql = sql + " AND M.PERSONNEL_UNIT ";
		sql = sql + " IN ( SELECT UNIT_NO FROM M_TB04_UNIT WHERE (UNIT_NO = '" + unitInfo.getUnit_no() + "' OR UNIT_SUPER='" + unitInfo.getUnit_no() + "') AND DEL_KBN = '0') ";
		sql = sql + " GROUP BY UNIT_NO";
		sql = sql + " ORDER BY UNIT_NO";
		return sql;
	}

	/**
	 * 单位年度考核情况
	 * 
	 * @param mtb111AdminScoreList
	 * @param flg
	 * @param unitInfo
	 */
	public String EditUnitCheckByYearSql(Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew) {
		String sql = "SELECT ";
		// 单位编码
		sql = sql + " M.PERSONNEL_UNIT AS UNIT_NO ";
		// 年度
		sql = sql + ",M1.ANNUAL_STATEMENT AS annual_statement ";
		// 总人数
		sql = sql + ",COUNT(DISTINCT PERSONNEL_ID) AS personnelCount ";
		// 考核人数
		sql = sql + ",SUM(CASE WHEN M1.CHECK_MESSAGE IS NOT NULL AND M1.CHECK_MESSAGE != '' AND M1.CHECK_MESSAGE != '5' THEN 1 ELSE 0 END)AS checkPersonnelCount ";
		// 优秀人数
		sql = sql + ",SUM(CASE WHEN M1.CHECK_MESSAGE = '0' THEN 1 ELSE 0 END)AS superiorPersonnelCount ";
		// 合格人数
		sql = sql + ",SUM(CASE WHEN M1.CHECK_MESSAGE = '1' THEN 1 ELSE 0 END)AS qualifiedPersonnelCount ";
		// 基本合格
		sql = sql + ",SUM(CASE WHEN M1.CHECK_MESSAGE = '2' THEN 1 ELSE 0 END)AS basicQualifiedPersonnelCount ";
		// 不合格人数
		sql = sql + ",SUM(CASE WHEN M1.CHECK_MESSAGE = '3' THEN 1 ELSE 0 END)AS notQualifiedPersonnelCount ";
		// 不定等次
		sql = sql + ",SUM(CASE WHEN M1.CHECK_MESSAGE = '4' THEN 1 ELSE 0 END)AS NoOrderPersonnelCount ";

		sql = sql + " FROM ";
		sql = sql + " m_tb12_personnel M ";

		sql = sql + " LEFT JOIN m_tb11_annualstatement M1 ON ";
		sql = sql + " M.PERSONNEL_ID = M1.ID_NUMBER ";
		sql = sql + " AND M.PERSONNEL_UNIT = M1.UNIT_NO ";
		sql = sql + " AND M1.ANNUAL_STATEMENT ='" + rlgl011001BeanNew.getAnnual_statement() + "'";
		sql = sql + " AND M1.DEL_KBN = '0' ";

		sql = sql + " WHERE ";
		sql = sql + " M.PERSONNEL_UNIT ";
		sql = sql + " IN ( SELECT UNIT_NO FROM M_TB04_UNIT WHERE (UNIT_NO = '" + unitInfo.getUnit_no() + "' OR UNIT_SUPER='" + unitInfo.getUnit_no() + "') AND DEL_KBN = '0') ";
		sql = sql + " AND M.DEL_KBN='0' ";
		sql = sql + " GROUP BY UNIT_NO";
		sql = sql + " ORDER BY UNIT_NO";
		return sql;
	}

	/**
	 * 人员其他信息维护情况
	 * 
	 * @param unitInfo
	 * @param rlgl011001BeanNew
	 */
	public String EditPersonnelOtherDataInfoSql(Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew) {
		String sql = "SELECT ";
		// 单位编码
		sql = sql + "M.PERSONNEL_UNIT AS UNIT_NO ";
		// 单位人员总数
		sql = sql + ",COUNT(DISTINCT M.PERSONNEL_ID) AS personnelCount ";
		// 奖惩情况
		sql = sql + ",SUM(CASE WHEN (M1.PERSONNEL_ID IS NULL OR M1.PERSONNEL_ID='') THEN 0 ELSE 1 END) AS personnelrewardspunishmentsCount ";
		// 出国情况
		sql = sql + ",SUM(CASE WHEN (M2.PERSONNEL_ID IS NULL OR M2.PERSONNEL_ID='') THEN 0 ELSE 1 END) AS personnelgoabroadCount ";
		// 党派信息
		sql = sql + ",SUM(CASE WHEN (M3.PERSONNEL_ID IS NULL OR M3.PERSONNEL_ID='') THEN 0 ELSE 1 END) AS personnelPartisanCount ";
		// 档案信息
		sql = sql + ",SUM(CASE WHEN (M4.PERSONNEL_ID IS NULL OR M4.PERSONNEL_ID='') THEN 0 ELSE 1 END) AS personnelrecordsCount ";
		// 荣誉奖励
		sql = sql + ",SUM(CASE WHEN (M5.PERSONNEL_ID IS NULL OR M5.PERSONNEL_ID='') THEN 0 ELSE 1 END) AS personnelawardedhonorCount ";
		// 人事保险
		sql = sql + ",SUM(CASE WHEN (M6.PERSONNEL_ID IS NULL OR M6.PERSONNEL_ID='') THEN 0 ELSE 1 END) AS personnelinsurersCount ";

		sql = sql + " FROM ";
		sql = sql + " m_tb39_personnel M ";

		sql = sql + " LEFT JOIN m_tb114_personnel_rewards_punishments M1 ON ";
		sql = sql + " M.PERSONNEL_ID = M1.PERSONNEL_ID ";
		sql = sql + " AND M1.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb115_personnel_goabroad M2 ON ";
		sql = sql + " M.PERSONNEL_ID = M2.PERSONNEL_ID ";
		sql = sql + " AND M2.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb40_personnel_partisan_info M3 ON ";
		sql = sql + " M.PERSONNEL_ID = M3.PERSONNEL_ID ";
		sql = sql + " AND M3.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb116_personnel_records M4 ON ";
		sql = sql + " M.PERSONNEL_ID = M4.PERSONNEL_ID ";
		sql = sql + " AND M4.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb117_personnel_awarded_honor M5 ON ";
		sql = sql + " M.PERSONNEL_ID = M5.PERSONNEL_ID ";
		sql = sql + " AND M5.DEL_KBN = '0' ";

		sql = sql + " LEFT JOIN m_tb118_personnel_insurers M6 ON ";
		sql = sql + " M.PERSONNEL_ID = M6.PERSONNEL_ID ";
		sql = sql + " AND M6.DEL_KBN = '0' ";

		sql = sql + " WHERE ";
		sql = sql + " M.DEL_KBN='0' ";
		sql = sql + " AND M.PERSONNEL_UNIT ";
		sql = sql + " IN ( SELECT UNIT_NO FROM M_TB04_UNIT WHERE (UNIT_NO = '" + unitInfo.getUnit_no() + "' OR UNIT_SUPER='" + unitInfo.getUnit_no() + "') AND DEL_KBN = '0') ";
		sql = sql + " GROUP BY UNIT_NO";
		sql = sql + " ORDER BY UNIT_NO";
		return sql;
	}

	/**
	 * 人员近X月其他信息维护情况
	 * 
	 * @param flg
	 * @param unitInfo
	 */
	public String EditPersonnelOtherDataInfoXMonthUpdateSql(Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew) {
		String sql = "SELECT ";
		// 单位编码
		sql = sql + "M.PERSONNEL_UNIT AS UNIT_NO ";
		// 单位人员总数
		sql = sql + ",COUNT(DISTINCT M.PERSONNEL_ID) AS personnelCount ";
		// 奖惩情况
		sql = sql + ",CASE WHEN SUM(CASE WHEN M1.personnelrewardspunishment='1' THEN 1 ELSE 0 END) > 0 THEN 1 ELSE 0 END AS personnelrewardspunishmentsCount ";
		// 出国情况
		sql = sql + ",CASE WHEN SUM(CASE WHEN M1.personnelgoabroad='1' THEN 1 ELSE 0 END) > 0 THEN 1 ELSE 0 END AS personnelgoabroadCount ";
		// 党派信息
		sql = sql + ",CASE WHEN SUM(CASE WHEN M1.personnelPartisan='1' THEN 1 ELSE 0 END) > 0 THEN 1 ELSE 0 END AS personnelPartisanCount ";
		// 档案信息
		sql = sql + ",CASE WHEN SUM(CASE WHEN M1.personnelrecords='1' THEN 1 ELSE 0 END) > 0 THEN 1 ELSE 0 END AS personnelrecordsCount ";
		// 荣誉奖励
		sql = sql + ",CASE WHEN SUM(CASE WHEN M1.personnelawardedhonor='1' THEN 1 ELSE 0 END) > 0 THEN 1 ELSE 0 END AS personnelawardedhonorCount ";
		// 人事保险
		sql = sql + ",CASE WHEN SUM(CASE WHEN M1.personnelinsurers='1' THEN 1 ELSE 0 END) > 0 THEN 1 ELSE 0 END AS personnelinsurersCount ";

		sql = sql + " FROM ";
		sql = sql + " m_tb12_personnel M ";

		sql = sql + " LEFT JOIN m_tb113_personnelupdate M1 ON ";
		sql = sql + " M.PERSONNEL_ID = M1.PERSONNEL_ID ";
		sql = sql + " AND M1.DEL_KBN = '0' ";
		if (rlgl011001BeanNew.getStartYear() != null && !"".equals(rlgl011001BeanNew.getStartYear()) && rlgl011001BeanNew.getStartMonth() != null && !"".equals(rlgl011001BeanNew.getStartMonth())) {
			sql = sql + " AND SUBSTR(M1.admtb_updatetime FROM 1 FOR 6) >= " + rlgl011001BeanNew.getStartYear() + rlgl011001BeanNew.getStartMonth();
		}
		if (rlgl011001BeanNew.getEndYear() != null && !"".equals(rlgl011001BeanNew.getEndYear()) && rlgl011001BeanNew.getEndMonth() != null && !"".equals(rlgl011001BeanNew.getEndMonth())) {
			sql = sql + " AND SUBSTR(M1.admtb_updatetime FROM 1 FOR 6) <= " + rlgl011001BeanNew.getEndYear() + rlgl011001BeanNew.getEndMonth();
		}

		sql = sql + " WHERE ";
		sql = sql + " M.DEL_KBN='0' ";
		sql = sql + " AND M.PERSONNEL_UNIT ";
		sql = sql + " IN ( SELECT UNIT_NO FROM M_TB04_UNIT WHERE (UNIT_NO = '" + unitInfo.getUnit_no() + "' OR UNIT_SUPER='" + unitInfo.getUnit_no() + "') AND DEL_KBN = '0') ";
		sql = sql + " GROUP BY UNIT_NO";
		sql = sql + " ORDER BY UNIT_NO";
		return sql;
	}

	/**
	 * 新增人员统计SQL
	 * 
	 * @param unitInfo
	 */
	public String EditPersonProfessionNewAddInfoSql(Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew) {
		String sql = "SELECT ";
		// 单位编码
		sql = sql + "M.PERSONNEL_UNIT AS UNIT_NO ";
		sql = sql + ",M2.UNIT_NM AS UNIT_NM ";
		// 医
		sql = sql + ",SUM(case when trim(M1.TYPE) ='183' then 1 else 0 end) AS physicianCount ";
		// 护
		sql = sql + ",SUM(case when M1.TYPE ='184' then 1 else 0 end) AS nurseCount ";
		// 药
		sql = sql + ",SUM(case when M1.TYPE ='185' then 1 else 0 end) AS medicineCount ";
		// 技
		sql = sql + ",SUM(case when M1.TYPE ='186' OR M1.TYPE ='187' then 1 else 0 end) AS inventionPersonnelCount ";
		// 其他卫技
		sql = sql + ",SUM(case when M1.TYPE ='188' then 1 else 0 end) AS otherinventionPersonnelCount ";
		// 非卫技
		sql = sql + ",SUM(case when M1.TYPE ='189' then 1 else 0 end) AS noinventionPersonnelCount ";
		// 其他
		sql = sql + ",SUM(case when M1.TYPE IS NULL OR (M1.TYPE !='183' AND M1.TYPE !='184' AND M1.TYPE !='185' AND M1.TYPE !='186' AND M1.TYPE !='187' AND M1.TYPE !='188' AND M1.TYPE !='188') then 1 else 0 end) AS otherCount ";
		// 合计
		sql = sql + ",count(DISTINCT M.PERSONNEL_ID) AS newenterPersonnelCount";

		sql = sql + " FROM M_TB12_PERSONNEL M";

		sql = sql + " LEFT JOIN m_tb19_personnel_practitioners_info M1";
		sql = sql + " ON M.PERSONNEL_ID = M1.PERSONNEL_ID";
		sql = sql + " AND M1.DEL_KBN = '0'";

		sql = sql + "  LEFT JOIN m_tb04_unit M2";
		sql = sql + " ON M.PERSONNEL_UNIT = M2.UNIT_NO";
		sql = sql + " AND M2.DEL_KBN = '0'";

		sql = sql + " WHERE";
		sql = sql + " M.PERSONNEL_UNIT ";
		sql = sql + " IN ( SELECT UNIT_NO FROM M_TB04_UNIT WHERE (UNIT_NO = '" + unitInfo.getUnit_no() + "' OR UNIT_SUPER='" + unitInfo.getUnit_no() + "')  AND UNIT_ASSORT='002' AND DEL_KBN = '0') ";
		sql = sql + " AND M.DEL_KBN ='0' ";
		if (rlgl011001BeanNew.getSelDatetime_from() != null && !"".equals(rlgl011001BeanNew.getSelDatetime_from())) {
			sql = sql + " AND SUBSTR(M.LOGIN_DATE FROM 1 FOR 8) >= " + rlgl011001BeanNew.getSelDatetime_from();
		}
		if (rlgl011001BeanNew.getSelDatetime_to() != null && !"".equals(rlgl011001BeanNew.getSelDatetime_to())) {
			sql = sql + " AND SUBSTR(M.LOGIN_DATE FROM 1 FOR 8) <= " + rlgl011001BeanNew.getSelDatetime_to();
		}

		if (rlgl011001BeanNew.getPersonnel_joinmode() != null && !"".equals(rlgl011001BeanNew.getPersonnel_joinmode())) {
			sql = sql + " AND SUBSTR(M.PERSONNEL_JOINMODE FROM 1 FOR 3) = '" + rlgl011001BeanNew.getPersonnel_joinmode() + "'";
		}
		sql = sql + " GROUP BY UNIT_NO";
		sql = sql + " ORDER BY UNIT_NO";
		return sql;
	}

	/**
	 * 编制党员情况
	 * 
	 * @param unitInfo
	 * @param rlgl011001BeanNew
	 */
	public String EditHeadcountPartymemberInfoSql(Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew) {
		String sql = "SELECT ";
		// 单位编码
		sql = sql + " M.PERSONNEL_UNIT AS UNIT_NO ";
		// 核准编制
		sql = sql + ",CASE WHEN M2.ESTABLISHMENT_NUM IS NULL OR M2.ESTABLISHMENT_NUM ='' THEN 0 ELSE M2.ESTABLISHMENT_NUM + 0 END AS unitEstablishment_num";
		// 现有在编人员数
		sql = sql + ",SUM(CASE WHEN M.PERSONNEL_REGULAR ='001' THEN 1 ELSE 0 END) AS regularPersonnelCount ";
		// 正在审批的在编人员数
		sql = sql + ",SUM(CASE WHEN M1.PERSONNEL_REGULAR ='001' AND M1.PERSONNEL_STATUS='001' AND M.PERSONNEL_REGULAR !='001' THEN 1 ELSE 0 END) AS checkingregularPersonnelCount ";
		// 党员数
		sql = sql + ",SUM(CASE WHEN M.PERSONNEL_POLITICAL_LANDSCAPE ='001' THEN 1 ELSE 0 END) AS partyPersonnelCount ";
		// 正在审核的党员数
		sql = sql + ",SUM(CASE WHEN M1.PERSONNEL_POLITICAL_LANDSCAPE ='001' AND M1.PERSONNEL_STATUS='001' AND M.PERSONNEL_POLITICAL_LANDSCAPE !='001' THEN 1 ELSE 0 END) AS checkingpartyPersonnelCount ";

		sql = sql + " FROM ";
		sql = sql + " m_tb12_personnel M ";

		sql = sql + " LEFT JOIN m_tb04_unit M2 ON ";
		sql = sql + " M.PERSONNEL_UNIT = M2.UNIT_NO ";
		sql = sql + " AND M2.DEL_KBN='0' ";

		sql = sql + " , m_tb39_personnel M1 ";

		sql = sql + " WHERE ";
		sql = sql + " M.PERSONNEL_UNIT ";
		sql = sql + " IN ( SELECT UNIT_NO FROM M_TB04_UNIT WHERE (UNIT_NO = '" + unitInfo.getUnit_no() + "' OR UNIT_SUPER='" + unitInfo.getUnit_no() + "') AND DEL_KBN = '0') ";
		sql = sql + " AND M.PERSONNEL_ID = M1.PERSONNEL_ID ";
		sql = sql + " AND M.DEL_KBN = '0' ";
		sql = sql + " AND M1.DEL_KBN ='0' ";
		sql = sql + " GROUP BY UNIT_NO";
		sql = sql + " ORDER BY UNIT_NO";
		return sql;
	}

	
	
	/**
	 * 学历统计
	 * 
	 * @param year
	 * @param unitNo
	 */
	public String GetEdoInfoSql(String year,String unitNo) {
			String sql = "";
			sql = sql + " SELECT  ";
			sql = sql + "         IN_POST_KBN '类别' ,  ";
			sql = sql + "         LEVEL '级别' ,  ";
			sql = sql + "         MAX ( CASE EDU WHEN '001' THEN COUNTS ELSE 0 END ) '高中及以下' ,  ";
			sql = sql + "         MAX ( CASE EDU WHEN '002' THEN COUNTS ELSE 0 END ) '中专' ,  ";
			sql = sql + "         MAX ( CASE EDU WHEN '003' THEN COUNTS ELSE 0 END ) '大学专科' ,  ";
			sql = sql + "         MAX ( CASE EDU WHEN '004' THEN COUNTS ELSE 0 END ) '大学本科' ,  ";
			sql = sql + "         MAX ( CASE EDU WHEN '005' THEN COUNTS ELSE 0 END ) '硕士研究生' ,  ";
			sql = sql + "         MAX ( CASE EDU WHEN '006' THEN COUNTS ELSE 0 END ) '博士研究生'  ";
			sql = sql + " FROM  ";
			sql = sql + "         (  ";
			sql = sql + "         SELECT  ";
			sql = sql + "                 IN_POST_KBN ,  ";
			sql = sql + "                 LEVEL ,  ";
			sql = sql + "                 EDU ,  ";
			sql = sql + "                 COUNT ( * ) AS COUNTS  ";
			sql = sql + "         FROM  ";
			sql = sql + "                 (  ";
			sql = sql + "                 SELECT  ";
			sql = sql + "                         (  ";
			sql = sql + "                         SELECT  ";
			sql = sql + "                                 EDUCATIONAL_BG  ";
			sql = sql + "                         FROM  ";
			sql = sql + "                                 M_TB15_PERSONNEL_EDU_INFO  ";
			sql = sql + "                         WHERE  ";
			sql = sql + "                                 PERSONNEL_ID=A.PERSONNEL_ID  ";
			sql = sql + "                                 AND GRADUATION_TIME = (  ";
			sql = sql + "                                 SELECT  ";
			sql = sql + "                                         MAX ( GRADUATION_TIME )  ";
			sql = sql + "                                 FROM  ";
			sql = sql + "                                         M_TB15_PERSONNEL_EDU_INFO  ";
			sql = sql + "                                 WHERE  ";
			sql = sql + "                                         PERSONNEL_ID=A.PERSONNEL_ID ) ) AS EDU ,  ";
			sql = sql + "                         CASE WHEN M.IN_POST_LEVEL = '' THEN NULL  ";
			sql = sql + "                         ELSE M.IN_POST_LEVEL END AS  ";
			sql = sql + "                         LEVEL ,  ";
			sql = sql + "                         M.IN_POST_KBN  ";
			sql = sql + "                 FROM  ";
			sql = sql + "                         M_TB12_PERSONNEL AS A INNER JOIN M_TB09_IRIN M  ";
			sql = sql + "                         ON M.PERSON_NO=A.PERSONNEL_ID ) AS B  ";
			sql = sql + "         GROUP BY  ";
			sql = sql + "                 IN_POST_KBN ,  ";
			sql = sql + "                 LEVEL ,  ";
			sql = sql + "                 EDU ) AS A  ";
			sql = sql + " GROUP BY  ";
			sql = sql + "         IN_POST_KBN ,  ";
			sql = sql + "         LEVEL  ";
			return sql;
		}
	/**
	 * 继续教育总人数统计
	 * 
	 * @param year
	 * @param unitNo
	 */
	public String EditStrAllPersonnelUnitSql(String unitno) {
			String sql = "";
			sql = sql + " select count(*) as sumcourseCount  ";
			sql = sql + " from m_tb12_personnel ";
			sql = sql + " where CONTINUE_EDUCATION_FLAG=1 ";
			sql = sql + " and PERSONNEL_UNIT like '"+unitno+"%' ";
			sql = sql + " and DEL_KBN=0 ";
			return sql;
		}
	/**
	 * 选课数量统计
	 * 
	 * @param year
	 * @param unitNo
	 */
	public String EditStrPersonnelCourseSql(String userId,String personnelId) {
			String sql = "";
			sql = sql + " select count(COURSE_ID) as selectcoursecount , ";
			sql = sql + " 3 as sumcourseCount ";
			sql = sql + " from m_tb80_course_selected ";
			sql = sql + " where  ";
			sql = sql + " USER_ID in ('"+userId+"','"+personnelId+"') and LOGIN_DATE > CONCAT(mid(SYSDATE(),1,4),'0000000000') and DEL_KBN = 0 ";
			return sql;
		}
	/**
	 * 选课人数统计
	 * 
	 * @param year
	 * @param unitNo
	 */
	public String EditStrPersonnelUnitSql(String unitno) {
			String sql = "";
			sql = sql + " select count(b.USER_ID) as selectcoursecount  ";
			sql = sql + " from (select * from m_tb01_user where UNIT_NO like '"+unitno+"%' and del_kbn=0) a ";
			sql = sql + " LEFT JOIN (select USER_ID from m_tb80_course_selected ";
			sql = sql + " where del_kbn=0 and LOGIN_DATE > CONCAT(mid(SYSDATE(),1,4),'0000000000')  ";
			sql = sql + " group by user_id HAVING sum(1)>2 )  b ";
			sql = sql + " on a.USER_ID = b.USER_ID ";
			return sql;
		}
	/**
	 * 学习通过课程数量统计
	 * 
	 * @param year
	 * @param unitNo
	 */
	public String EditStrPersonnelPassCourseSql(String userId,String personnelId) {
			String sql = "";
			sql = sql + " select count(COURSE_ID) as passcoursecount , ";
			sql = sql + " 3 as sumcourseCount ";
			sql = sql + " from m_tb81_course_exams  ";
			sql = sql + " where  ";
			sql = sql + " USER_ID in ('"+userId+"','"+personnelId+"') and LOGIN_DATE > CONCAT(mid(SYSDATE(),1,4),'0000000000') and DEL_KBN = 0 ";
			return sql;
		}
	/**
	 * 学习通过课程数量人数统计
	 * 
	 * @param year
	 * @param unitNo
	 */
	public String EditStrPersonnelPassUnitSql(String unitno) {
			String sql = "";
			sql = sql + " select count(b.USER_ID) as passcoursecount ";
			sql = sql + " from (select * from m_tb01_user where UNIT_NO like '"+unitno+"%' and del_kbn=0) a ";
			sql = sql + " LEFT JOIN   ";
			sql = sql + " (select USER_ID from m_tb81_course_exams ";
			sql = sql + " where del_kbn=0 and LOGIN_DATE > CONCAT(mid(SYSDATE(),1,4),'0000000000')   ";
			sql = sql + " group by user_id HAVING sum(1)>2 )  b ";
			sql = sql + " on (a.USER_ID = b.USER_ID or a.personnel_id = b.USER_ID) ";
			return sql;
		}
	/**
	 * 编辑本人信息完成度Sql
	 * 
	 * @param mtb111AdminScoreList
	 * @param flg
	 * @param unitInfo
	 */
	public String EditStrPersonnelSql(List<Mtb111Admscore> mtb111AdminScoreList, Mtb04Unit unitInfo, Rlgl011001Bean rlgl011001BeanNew) {
		String sql = "SELECT ";
		// 身份证号
		sql = sql + "DISTINCT(M.PERSONNEL_ID) AS PERSONNEL_ID ";
		Mtb111Admscore admscore = new Mtb111Admscore();
		if (mtb111AdminScoreList != null && mtb111AdminScoreList.size() > 0) {
			String strSqlB = "(";
			float scoreB = 0;

			for (int i = 0; i < mtb111AdminScoreList.size(); i++) {
				admscore = mtb111AdminScoreList.get(i);
				// 基本信息
				if ("A".equals(admscore.getAdmscore_xb())) {
					strSqlB = strSqlB + " CASE WHEN (M." + admscore.getAdmscore_id() + " IS NULL OR M." + admscore.getAdmscore_id() + "='' ) THEN 0 ELSE " + admscore.getAdmscore_score() + " END +";
					scoreB = scoreB + admscore.getAdmscore_score();
				}
			}

			// 基本信息
			if (strSqlB.length() > 1 && scoreB != 0) {
				sql = sql + "," + strSqlB.substring(0, strSqlB.length() - 1) + ") * 100/" + scoreB + " AS bisicCount_sel";
			} else {
				sql = sql + ",0 AS bisicCount_sel";
			}

			// 专业技术职务
			sql = sql + ",(CASE WHEN (M2.PERSONNEL_ID IS NULL OR M2.PERSONNEL_ID='') THEN 0 ELSE 1 END) * 100/1 AS professionalTechnicalCount ";
			// 行政职务
			sql = sql + ",(CASE WHEN (M3.PERSONNEL_ID IS NULL OR M3.PERSONNEL_ID='') THEN 0 ELSE 1 END) * 100/1 AS administrativePostCount ";
			// 社会关系
			sql = sql + ",(CASE WHEN (M4.PERSONNEL_ID IS NULL OR M4.PERSONNEL_ID='') THEN 0 ELSE 1 END) * 100/1 AS socialRelationsCount ";
			// 学习经历
			sql = sql + ",(CASE WHEN (M5.PERSONNEL_ID IS NULL OR M5.PERSONNEL_ID='') THEN 0 ELSE 1 END) * 100/1 AS learningExperienceCount ";

			sql = sql + " FROM ";
			sql = sql + " m_tb39_personnel M ";

			sql = sql + " LEFT JOIN m_tb45_personnel_professional_info M2 ON ";
			sql = sql + " M.PERSONNEL_ID = M2.PERSONNEL_ID ";
			sql = sql + " AND M2.DEL_KBN = '0' ";

			sql = sql + " LEFT JOIN m_tb44_personnel_job_info M3 ON ";
			sql = sql + " M.PERSONNEL_ID = M3.PERSONNEL_ID ";
			sql = sql + " AND M3.DEL_KBN = '0' ";

			sql = sql + " LEFT JOIN m_tb43_personnel_social_info M4 ON ";
			sql = sql + " M.PERSONNEL_ID = M4.PERSONNEL_ID ";
			sql = sql + " AND M4.DEL_KBN = '0' ";

			sql = sql + " LEFT JOIN m_tb42_personnel_edu_info M5 ON ";
			sql = sql + " M.PERSONNEL_ID = M5.PERSONNEL_ID ";
			sql = sql + " AND M5.DEL_KBN = '0' ";

			sql = sql + " WHERE ";
			sql = sql + " M.DEL_KBN='0' ";
			sql = sql + " AND M.PERSONNEL_ID =" + rlgl011001BeanNew.getPersonnel_id() + "";
		}
		return sql;
	}
}
