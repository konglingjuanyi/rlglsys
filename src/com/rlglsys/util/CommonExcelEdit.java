package com.rlglsys.util;

import java.util.ArrayList;
import java.util.List;
import com.rlglsys.bean.Rlgl200101Bean;
import com.rlglsys.service.IRlgl200101Service;

public class CommonExcelEdit {

    /**
     * 国统表信息取得
     * 
     * @param mtb111AdminScoreList
     * @param user
     * @param unitInfo
     * @param rlgl011001BeanNew
     * @param rlgl011001Service
     * @return
     */
    public List<Rlgl200101Bean> getExcelTableInfoList(Rlgl200101Bean rlgl200101BeanNew, IRlgl200101Service rlgl200101Service) {
        List<Rlgl200101Bean> rlgl200101BeanList = new ArrayList<Rlgl200101Bean>();
        try {
            String sql = "";
            // 根据不同条件调用不同的sql编辑方法
            // 事业单位基本情况sql编辑
            if ("01".equals(rlgl200101BeanNew.getFileno()))
            {
                sql = this.EditUnitBaseInfoSql(rlgl200101BeanNew);
            }
            // 事业单位工作人员基本情况
            if ("02".equals(rlgl200101BeanNew.getFileno()))
            {
                sql = this.EditUnitBaseInfoSql02(rlgl200101BeanNew);
            }
            if ("021".equals(rlgl200101BeanNew.getFileno()))
            {
                sql = this.EditUnitBaseInfoSql021(rlgl200101BeanNew);
            }
            if("03".equals(rlgl200101BeanNew.getFileno()))
            {
                sql = this.EditUnitBaseInfoSql03(rlgl200101BeanNew);
            }    
            if("091".equals(rlgl200101BeanNew.getFileno()))
            {
                sql=this.EditUnitBaseInfoSql091(rlgl200101BeanNew);
            }
            if("071".equals(rlgl200101BeanNew.getFileno())){
                sql=this.EditUnitBaseInfoSql071(rlgl200101BeanNew);
            }
            if("12".equals(rlgl200101BeanNew.getFileno())){
                sql=this.EditUnitBaseInfoSql12(rlgl200101BeanNew);
            }
            if("04".equals(rlgl200101BeanNew.getFileno())){
                sql=this.EditUnitBaseInfoSql04(rlgl200101BeanNew);
            }
            if("09".equals(rlgl200101BeanNew.getFileno())){
                sql=this.EditUnitBaseInfoSql09(rlgl200101BeanNew);
            }
            if("061".equals(rlgl200101BeanNew.getFileno())){
                sql=this.EditUnitBaseInfoSql061(rlgl200101BeanNew);
            }
            if("063".equals(rlgl200101BeanNew.getFileno())){
                sql=this.EditUnitBaseInfoSql063(rlgl200101BeanNew);
            }
            if("081".equals(rlgl200101BeanNew.getFileno())){
                sql=this.EditUnitBaseInfoSql081(rlgl200101BeanNew);
            }
            if("08".equals(rlgl200101BeanNew.getFileno())){
                sql=this.EditUnitBaseInfoSql08(rlgl200101BeanNew);
            }
            if ("".equals(sql)) {
                return rlgl200101BeanList;
            }
            rlgl200101BeanNew.setStrSql(sql);
            rlgl200101BeanList = rlgl200101Service.getExcelTableInfoList(rlgl200101BeanNew);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rlgl200101BeanList;
    }   
    /*10
     * */
    public String EditUnitBaseInfoSql12(Rlgl200101Bean rlgl200101BeanNew) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        //山东，管理人员
        sql.append(" SUM(CASE WHEN a.PERSONNEL_AREA LIKE CONCAT('','37','%')and b.in_post_kbn in('006','004') then 1 else 0 end) as 'R11', ");
      //山东，专业技术人员
        sql.append(" SUM(CASE WHEN a.PERSONNEL_AREA LIKE CONCAT('','37','%')and b.in_post_kbn in('027','004') then 1 else 0 end) as 'R12', ");
      //山东，专业技术人员在管理岗位工作的
        sql.append(" SUM(CASE WHEN a.PERSONNEL_AREA LIKE CONCAT('','37','%')and b.in_post_kbn='004' then 1 else 0 end) as 'R13', ");
      //山东，工勤技能人员
        sql.append(" SUM(CASE WHEN a.PERSONNEL_AREA LIKE CONCAT('','37','%')and b.in_post_kbn='007' then 1 else 0 end) as 'R14', ");
      //山东，其他从业人员
        sql.append(" SUM(CASE WHEN a.PERSONNEL_AREA LIKE CONCAT('','37','%')and (b.IN_POST_KBN NOT IN('006','027','004','007') OR b.IN_POST_KBN=NULL) then 1 else 0 end) as 'R15', ");
      //年度
        sql.append(" '"+rlgl200101BeanNew.getSel_year()+"'+'年' as 'P7' ");
        sql.append(" FROM ");
        sql.append(" m_tb12_personnel a ");
        sql.append(" LEFT JOIN ");
        sql.append(" m_tb09_irin b ");
        sql.append(" on a.PERSONNEL_UNIT=b.UNIT_NO ");
        sql.append(" WHERE      ");
        sql.append(" substr(a.LOGIN_DATE,1,4)= '"+rlgl200101BeanNew.getSel_year()+"' ");
        sql.append(" and  ");
        //管辖单位
        sql.append(" (A.PERSONNEL_UNIT  ");
        sql.append(" in  ");
        sql.append(" (SELECT  ");
        sql.append(" unit_no  ");
        sql.append(" FROM  ");
        sql.append(" m_tb04_unit  ");
        sql.append(" WHERE  ");
        sql.append(" substr(a.LOGIN_DATE,1,4)<="+rlgl200101BeanNew.getSel_year());
        sql.append(" and ");
        sql.append(" ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no()+"'  ");
        sql.append(" AND  ");
        sql.append(" DEL_KBN = 0  ");
        sql.append(" AND  ");
        sql.append(" UNIT_STATUS = '1' ");
        sql.append(" )  ");
        sql.append(" or  ");
        sql.append(" A.PERSONNEL_UNIT  ");
        sql.append(" LIKE CONCAT('', '"+rlgl200101BeanNew.getUnit_no()+"', '%')) ");

        return sql.toString();
    }
    /**
     * 编辑事业单位基本情况Sql，对应ps01工作表
     */
    public String EditUnitBaseInfoSql(Rlgl200101Bean rlgl200101BeanNew) {
        StringBuilder sql = new StringBuilder();
        // 一、事业单位
        // 上年末单位个数
        sql.append("SELECT SUM(CASE WHEN SUBSTR(T04.LOGIN_DATE,1,4) < '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS D11 ,");
        // 本年末单位个数
        sql.append("SUM(CASE WHEN SUBSTR(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS E11 ,");
        // 公益一类
        sql.append("SUM(CASE WHEN T04.unit_nature_tow ='001' THEN 1 ELSE 0 END) AS F11, ");
        // 公益二类
        sql.append("SUM(CASE WHEN T04.unit_nature_tow ='002' THEN 1 ELSE 0 END) AS G11,");
        // 其他
        sql.append("SUM(CASE WHEN T04.unit_nature_tow NOT IN ('001','002') THEN  1  ELSE 0 END) AS H11,");
        // 行业：卫生
        sql.append("SUM(CASE WHEN T04.industry_plan = '004' THEN 1 ELSE 0 END) AS P11,");
        // 层次：中央
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='061' THEN 1 ELSE 0 END) AS T11,");
        // 层次：省
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='062' THEN 1 ELSE 0 END) AS U11,");
        // 层次：地
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='063' THEN 1 ELSE 0 END) AS V11,");
        // 层次：县
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='064' THEN 1 ELSE 0 END) AS W11,");
        // 层次：乡镇
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='998' THEN 1 ELSE 0 END) AS X11,");
        // 从业人员总数
        sql.append(" T12.congyerenyuan AS Y11,");
        // 编制数量
        sql.append("SUM(T04.establishment_num) AS Z11,");
        // 退休人员数量  
        sql.append(" T12.tuixiurenyuan AS AC11,");
        // -- 参公管理人员
        // 上年末单位个数
        sql.append("SUM(CASE WHEN T04.unit_nature_tow ='004' AND SUBSTR(T04.LOGIN_DATE,1,4) < '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS D12 ,");
        // 本年末单位个数
        sql.append("SUM(CASE WHEN T04.unit_nature_tow ='004' AND SUBSTR(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS E12 ,");
        // 其他
        sql.append("SUM(CASE WHEN T04.unit_nature_tow ='004' THEN 1 ELSE 0 END)  AS H12,");
        // 行业：卫生
        sql.append("SUM(CASE WHEN T04.unit_nature_tow ='004' AND T04.industry_plan = '004'  THEN 1  ELSE 0 END)  AS P12,");
        // 层次：中央
        sql.append("SUM(CASE WHEN T04.unit_nature_tow ='004' AND T04.unit_manage_scale ='061' THEN 1 ELSE 0 END) AS T12,");
        // 层次：省
        sql.append("SUM(CASE WHEN T04.unit_nature_tow ='004' AND T04.unit_manage_scale ='062' THEN 1 ELSE 0 END) AS U12,");
        // 层次：地
        sql.append("SUM(CASE WHEN T04.unit_nature_tow ='004' AND T04.unit_manage_scale ='063' THEN 1 ELSE 0 END) AS V12,");
        // 层次：县
        sql.append("SUM(CASE WHEN T04.unit_nature_tow ='004' AND T04.unit_manage_scale ='064' THEN 1 ELSE 0 END) AS W12,");
        // 层次：乡镇
        sql.append("SUM(CASE WHEN T04.unit_nature_tow ='004' AND T04.unit_manage_scale ='998' THEN 1 ELSE 0 END )AS X12,");
        // -- 从业人数
        sql.append("T14.cyrs AS Y12,");
        //  编制数量
        sql.append("CASE WHEN T04.unit_nature_tow ='004' THEN SUM(T04.establishment_num) ELSE 0 END AS Z12,");
        // 退休人员数量
        sql.append("T14.txrys AS AC12,");
        // -- 行业：卫生
        // 上年末单位个数
        sql.append("SUM(CASE WHEN T04.industry_plan = '004' AND SUBSTR(T04.LOGIN_DATE,1,4) < '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS D19 ,");
        // 本年末单位个数
        sql.append("SUM(CASE WHEN T04.industry_plan = '004' AND SUBSTR(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS E19 ,");
        // 公益一类
        sql.append("SUM(CASE WHEN T04.industry_plan = '004' AND T04.unit_nature_tow ='001' THEN  1  ELSE 0 END) AS F19,");
        // 公益二类
        sql.append("SUM(CASE WHEN T04.industry_plan = '004' AND T04.unit_nature_tow ='002' THEN  1  ELSE 0 END) AS G19,");
        // 其他
        sql.append("SUM(CASE WHEN T04.industry_plan = '004' AND T04.unit_nature_tow NOT IN ('001','002') THEN  1  ELSE 0 END) AS H19,");
        // 行业：卫生
        sql.append("SUM(CASE WHEN T04.industry_plan = '004' THEN 1 ELSE 0 END )AS P19,");
        // 层次：中央
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='061' AND T04.industry_plan = '004' THEN 1 ELSE 0 END) AS T19,");
        // 层次：省
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='062' AND T04.industry_plan = '004' THEN 1 ELSE 0 END) AS U19,");
        // 层次：地
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='063' AND T04.industry_plan = '004' THEN 1 ELSE 0 END) AS V19,");
        // 层次：县
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='064' AND T04.industry_plan = '004' THEN 1 ELSE 0 END) AS W19,");
        // 层次：乡镇
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='998' AND T04.industry_plan = '004' THEN 1 ELSE 0 END) AS X19,");
        // 从业人员总数
        sql.append("T13.cyrs AS Y19, ");
        // 编制数量
        sql.append("(CASE WHEN T04.industry_plan = '004' THEN SUM(T04.establishment_num)ELSE 0 END) AS Z19,");
        // 退休人员数量
        sql.append("T13.txrys  AS AC19,");
        // -- 党的系统
        // 上年末单位个数
        sql.append("SUM(CASE WHEN T04.system_sort ='175'  AND SUBSTR(T04.LOGIN_DATE,1,4) < '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS D23 ,");
        // 本年末单位个数
        sql.append("SUM(CASE WHEN T04.system_sort ='175'  AND SUBSTR(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS E23 ,");
        //  公益一类
        sql.append("SUM(CASE WHEN T04.system_sort ='175'  AND T04.unit_nature_tow ='001' THEN 1 ELSE 0 END) AS F23,");
        // 公益二类
        sql.append("SUM(CASE WHEN T04.system_sort ='175' AND T04.unit_nature_tow ='002' THEN 1 ELSE 0 END) AS G23,");
        // 其他
        sql.append("SUM(CASE WHEN T04.system_sort ='175' AND T04.unit_nature_tow NOT IN ('001','002') THEN  1  ELSE 0 END )AS H23,");
        //  行业：卫生
        sql.append("SUM(CASE WHEN T04.system_sort ='175' AND T04.industry_plan = '004' THEN 1 ELSE 0 END) AS P23,");    
        // 层次：中央
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='061' AND T04.system_sort ='175' THEN 1 ELSE 0 END) AS T23,");
        // 层次：省
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='062' AND T04.system_sort ='175' THEN 1 ELSE 0 END) AS U23,");
        // 层次：地
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='063' AND T04.system_sort ='175' THEN 1 ELSE 0 END) AS V23,");
        // 层次：县
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='064' AND T04.system_sort ='175' THEN 1 ELSE 0 END) AS W23,");
        // 层次：乡镇
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='998' AND T04.system_sort ='175' THEN 1 ELSE 0 END) AS X23,");
        // 从业人员总数
        sql.append("T15.cyrs AS Y23,");
        // 编制数量
        sql.append("CASE WHEN T04.system_sort ='175' THEN SUM(T04.establishment_num) ELSE 0 END AS Z23,");
        // 退休人员数量
        sql.append("T15.txrys AS AC23,");
        // -- 行政系统
        // 上年末单位个数
        sql.append("SUM(CASE WHEN T04.system_sort ='176'  AND SUBSTR(T04.LOGIN_DATE,1,4) < '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS D24 ,");
        // 本年末单位个数
        sql.append("SUM(CASE WHEN T04.system_sort ='176'  AND SUBSTR(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS E24 ,");
        // 公益一类
        sql.append("SUM(CASE WHEN T04.system_sort ='176'  AND T04.unit_nature_tow ='001' THEN 1 ELSE 0 END) AS F24,");
        // 公益二类
        sql.append("SUM(CASE WHEN T04.system_sort ='176' AND T04.unit_nature_tow ='002' THEN 1 ELSE 0 END) AS G24,");
        // 其他
        sql.append("SUM(CASE WHEN T04.system_sort ='176' AND T04.unit_nature_tow NOT IN ('001','002') THEN  1  ELSE 0 END )AS H24, ");
        // 行业：卫生
        sql.append("SUM(CASE WHEN T04.system_sort ='176' AND T04.industry_plan = '004' THEN 1 ELSE 0 END) AS P24,");
        // 层次：中央
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='061' AND T04.system_sort ='176' THEN 1 ELSE 0 END) AS T24,");
        // 层次：省
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='062' AND T04.system_sort ='176' THEN 1 ELSE 0 END) AS U24,");
        // 层次：地
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='063' AND T04.system_sort ='176' THEN 1 ELSE 0 END) AS V24,");
        // 层次：县
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='064' AND T04.system_sort ='176' THEN 1 ELSE 0 END) AS W24,");
        // 层次：乡镇
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='998' AND T04.system_sort ='176' THEN 1 ELSE 0 END) AS X24,");
        // 从业人员总数
        sql.append("T16.cyrs AS Y24,");
        // 编制数量
        sql.append("CASE WHEN T04.system_sort ='176' THEN SUM(T04.establishment_num) ELSE 0 END AS Z24,");
        // 退休人员数量
        sql.append("T16.txrys AS AC24,");
        // -- 人大系统
        // 上年末单位个数
        sql.append(" SUM(CASE WHEN T04.system_sort ='177'  AND SUBSTR(T04.LOGIN_DATE,1,4) < '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS D25 ,");
        // 本年末单位个数
        sql.append("SUM(CASE WHEN T04.system_sort ='177'  AND  SUBSTR(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS E25 ,");
        // 公益一类
        sql.append("SUM(CASE WHEN T04.system_sort ='177'  AND T04.unit_nature_tow ='001' THEN 1 ELSE 0 END) AS F25,");
        // 公益二类
        sql.append("SUM(CASE WHEN T04.system_sort ='177' AND T04.unit_nature_tow ='002' THEN 1 ELSE 0 END) AS G25,");
        // 其他
        sql.append("SUM(CASE WHEN T04.system_sort ='177' AND T04.unit_nature_tow NOT IN ('001','002') THEN  1  ELSE 0 END )AS H25,");
        // 行业：卫生
        sql.append("SUM(CASE WHEN T04.system_sort ='177' AND T04.industry_plan = '004' THEN 1 ELSE 0 END) AS P25,");
        // 层次：中央
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='061' AND T04.system_sort ='177' THEN 1 ELSE 0 END) AS T25,");
        // 层次：省
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='062' AND T04.system_sort ='177' THEN 1 ELSE 0 END) AS U25,");
        // 层次：地
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='063' AND T04.system_sort ='177' THEN 1 ELSE 0 END) AS V25,");
        // 层次：县
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='064' AND T04.system_sort ='177' THEN 1 ELSE 0 END) AS W25,");
        // 层次：乡镇
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='998' AND T04.system_sort ='177' THEN 1 ELSE 0 END) AS X25,");
        // 从业人员总数
        sql.append("T17.cyrs AS Y25,");
        // 编制数量
        sql.append("CASE WHEN T04.system_sort ='177' THEN SUM(T04.establishment_num) ELSE 0 END AS Z25,");
        // 退休人员数量
        sql.append("T17.txrys AS AC25,");
        // -- 政协系统
        // 上年末单位个数
        sql.append("SUM(CASE WHEN T04.system_sort ='178' AND SUBSTR(T04.LOGIN_DATE,1,4) < '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS D26 ,");
        // 本年末单位个数
        sql.append("SUM(CASE WHEN T04.system_sort ='178' AND SUBSTR(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS E26 ,");
        // 公益一类
        sql.append("SUM(CASE WHEN T04.system_sort ='178'  AND T04.unit_nature_tow ='001' THEN 1 ELSE 0 END) AS F26,");
        // 公益二类
        sql.append("SUM(CASE WHEN T04.system_sort ='178' AND T04.unit_nature_tow ='002' THEN 1 ELSE 0 END) AS G26,");
        // 其他
        sql.append("SUM(CASE WHEN T04.system_sort ='178' AND T04.unit_nature_tow NOT IN ('001','002') THEN  1  ELSE 0 END )AS H26,");
        // 行业：卫生
        sql.append("SUM(CASE WHEN T04.system_sort ='178' AND T04.industry_plan = '004' THEN 1 ELSE 0 END) AS P26,");
        // 层次：中央
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='061' AND T04.system_sort ='178' THEN 1 ELSE 0 END) AS T26,");
        // 层次：省
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='062' AND T04.system_sort ='178' THEN 1 ELSE 0 END) AS U26,");
        // 层次：地
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='063' AND T04.system_sort ='178' THEN 1 ELSE 0 END) AS V26,");
        // 层次：县
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='064' AND T04.system_sort ='178' THEN 1 ELSE 0 END) AS W26,");
        // 层次：乡镇
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='998' AND T04.system_sort ='178' THEN 1 ELSE 0 END) AS X26,");
        // 从业人员总数
        sql.append("T18.cyrs AS Y26,");
        // 编制数量
        sql.append("CASE WHEN T04.system_sort ='178' THEN SUM(T04.establishment_num) ELSE 0 END AS Z26,");
        // 退休人员数量
        sql.append("T18.txrys AS AC26,");
        // -- 法院（system_sort ='179'）系统
        // 上年末单位个数
        sql.append("SUM(CASE WHEN T04.system_sort ='179'  AND SUBSTR(T04.LOGIN_DATE,1,4) < '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS D27 ,");
        // 本年末单位个数
        sql.append("SUM(CASE WHEN T04.system_sort ='179'  AND SUBSTR(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS E27 ,");
        // 公益一类
        sql.append("SUM(CASE WHEN T04.system_sort ='179'  AND T04.unit_nature_tow ='001' THEN 1 ELSE 0 END) AS F27,");
        // 公益二类
        sql.append("SUM(CASE WHEN T04.system_sort ='179' AND T04.unit_nature_tow ='002' THEN 1 ELSE 0 END) AS G27,");
        // 其他
        sql.append("SUM(CASE WHEN T04.system_sort ='179' AND T04.unit_nature_tow NOT IN ('001','002') THEN  1  ELSE 0 END )AS H27,");
        // 行业：卫生
        sql.append("SUM(CASE WHEN T04.system_sort ='179' AND T04.industry_plan = '004' THEN 1 ELSE 0 END) AS P27,");
        // 层次：中央
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='061' AND T04.system_sort ='179' THEN 1 ELSE 0 END) AS T27,");
        // 层次：省
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='062' AND T04.system_sort ='179' THEN 1 ELSE 0 END) AS U27,");
        // 层次：地
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='063' AND T04.system_sort ='179' THEN 1 ELSE 0 END) AS V27,");
        // 层次：县
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='064' AND T04.system_sort ='179' THEN 1 ELSE 0 END) AS W27,");
        // 层次：乡镇
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='998' AND T04.system_sort ='179' THEN 1 ELSE 0 END) AS X27,");
        // 从业人员总数
        sql.append("T19.cyrs AS Y27, ");
        //  编制数量
        sql.append("CASE WHEN T04.system_sort ='179' THEN SUM(T04.establishment_num) ELSE 0 END AS Z27,");
        // 退休人员数量
        sql.append("T19.txrys AS AC27,");
        // -- 检查院（system_sort ='180'）系统
        // 上年末单位个数
        sql.append("SUM(CASE WHEN T04.system_sort ='180'  AND SUBSTR(T04.LOGIN_DATE,1,4) < '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS D28 ,");
        // 本年末单位个数
        sql.append("SUM(CASE WHEN T04.system_sort ='180'  AND SUBSTR(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS E28 ,");
        // 公益一类
        sql.append("SUM(CASE WHEN T04.system_sort ='180'  AND T04.unit_nature_tow ='001' THEN 1 ELSE 0 END) AS F28,");
        // 公益二类
        sql.append("SUM(CASE WHEN T04.system_sort ='180' AND T04.unit_nature_tow ='002' THEN 1 ELSE 0 END) AS G28,");
        // 其他
        sql.append("SUM(CASE WHEN T04.system_sort ='180' AND T04.unit_nature_tow NOT IN ('001','002') THEN  1  ELSE 0 END )AS H28,");
        // 行业：卫生
        sql.append("SUM(CASE WHEN T04.system_sort ='180' AND T04.industry_plan = '004' THEN 1 ELSE 0 END) AS P28,");
        // 层次：中央
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='061' AND T04.system_sort ='180' THEN 1 ELSE 0 END) AS T28,");
        // 层次：省
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='062' AND T04.system_sort ='180' THEN 1 ELSE 0 END) AS U28,");
        // 层次：地
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='063' AND T04.system_sort ='180' THEN 1 ELSE 0 END) AS V28,");
        // 层次：县
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='064' AND T04.system_sort ='180' THEN 1 ELSE 0 END) AS W28,");
        // 层次：乡镇
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='998' AND T04.system_sort ='180' THEN 1 ELSE 0 END) AS X28,");
        // 从业人员总数
        sql.append("T20.cyrs AS Y28,");
        // 编制数量
        sql.append("CASE WHEN T04.system_sort ='180' THEN SUM(T04.establishment_num) ELSE 0 END AS Z28,");
        // 退休人员数量
        sql.append("T20.txrys AS AC28,");
        // -- 群众团体（system_sort ='181'）系统
        // 上年末单位个数
        sql.append("SUM(CASE WHEN T04.system_sort ='181'  AND SUBSTR(T04.LOGIN_DATE,1,4) < '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS D29 ,");
        // 本年末单位个数
        sql.append("SUM(CASE WHEN T04.system_sort ='181'  AND SUBSTR(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS E29 ,");
        // 公益一类
        sql.append("SUM(CASE WHEN T04.system_sort ='181'  AND T04.unit_nature_tow ='001' THEN 1 ELSE 0 END) AS F29, ");
        // 公益二类
        sql.append("SUM(CASE WHEN T04.system_sort ='181' AND T04.unit_nature_tow ='002' THEN 1 ELSE 0 END) AS G29,");
        // 其他
        sql.append("SUM(CASE WHEN T04.system_sort ='181' AND T04.unit_nature_tow NOT IN ('001','002') THEN  1  ELSE 0 END )AS H29,");
        // 行业：卫生
        sql.append("SUM(CASE WHEN T04.system_sort ='181' AND T04.industry_plan = '004' THEN 1 ELSE 0 END) AS P29,");
        // 层次：中央
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='061' AND T04.system_sort ='181' THEN 1 ELSE 0 END) AS T29,");
        // 层次：省
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='062' AND T04.system_sort ='181' THEN 1 ELSE 0 END) AS U29,");
        // 层次：地
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='063' AND T04.system_sort ='181' THEN 1 ELSE 0 END) AS V29,");
        // 层次：县
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='064' AND T04.system_sort ='181' THEN 1 ELSE 0 END) AS W29,");
        // 层次：乡镇
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='998' AND T04.system_sort ='181' THEN 1 ELSE 0 END) AS X29,");
        // 从业人员总数
        sql.append("T21.cyrs AS Y29,");
        // 编制数量
        sql.append("CASE WHEN T04.system_sort ='181' THEN SUM(T04.establishment_num) ELSE 0 END AS Z29,");
        // 退休人员数量
        sql.append("T21.txrys AS AC29,");
        // -- 民主党派（system_sort ='182'） 
        // 上年末单位个数
        sql.append("SUM(CASE WHEN T04.system_sort ='182'  AND SUBSTR(T04.LOGIN_DATE,1,4) < '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS D30 ,");
        // 本年末单位个数
        sql.append("SUM(CASE WHEN T04.system_sort ='182'  AND SUBSTR(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS E30 ,");
        // 公益一类
        sql.append("SUM(CASE WHEN T04.system_sort ='182'  AND T04.unit_nature_tow ='001' THEN 1 ELSE 0 END) AS F30,");
        // 公益二类
        sql.append("SUM(CASE WHEN T04.system_sort ='182' AND T04.unit_nature_tow ='002' THEN 1 ELSE 0 END) AS G30,");
        // 其他
        sql.append("SUM(CASE WHEN T04.system_sort ='182' AND T04.unit_nature_tow NOT IN ('001','002') THEN  1  ELSE 0 END )AS H30,");
        // 行业：卫生
        sql.append("SUM(CASE WHEN T04.system_sort ='182' AND T04.industry_plan = '004' THEN 1 ELSE 0 END) AS P30,");
        // 层次：中央
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='061' AND T04.system_sort ='182' THEN 1 ELSE 0 END) AS T30,");
        // 层次：省
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='062' AND T04.system_sort ='182' THEN 1 ELSE 0 END) AS U30,");
        // 层次：地
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='063' AND T04.system_sort ='182' THEN 1 ELSE 0 END) AS V30,");
        // 层次：县
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='064' AND T04.system_sort ='182' THEN 1 ELSE 0 END) AS W30,");
        // 层次：乡镇
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='998' AND T04.system_sort ='182' THEN 1 ELSE 0 END) AS X30,");
        // 从业人员总数
        sql.append("T22.cyrs AS Y30,");
        // 编制数量
        sql.append("CASE WHEN T04.system_sort ='182' THEN SUM(T04.establishment_num) ELSE 0 END AS Z30,");
        // 退休人员数量
        sql.append("T22.txrys AS AC30,");
        // -- 军队（system_sort ='183'） 
        // 上年末单位个数
        sql.append("SUM(CASE WHEN T04.system_sort ='183'  AND SUBSTR(T04.LOGIN_DATE,1,4) < '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS D31 ,");
        // 本年末单位个数
        sql.append("SUM(CASE WHEN T04.system_sort ='183'  AND SUBSTR(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS E31 ,");
        // 公益一类
        sql.append("SUM(CASE WHEN T04.system_sort ='183'  AND T04.unit_nature_tow ='001' THEN 1 ELSE 0 END) AS F31,");
        // 公益二类
        sql.append("SUM(CASE WHEN T04.system_sort ='183' AND T04.unit_nature_tow ='002' THEN 1 ELSE 0 END) AS G31,");
        // 其他
        sql.append("SUM(CASE WHEN T04.system_sort ='183' AND T04.unit_nature_tow NOT IN ('001','002') THEN  1  ELSE 0 END )AS H31, ");
        // 行业：卫生
        sql.append("SUM(CASE WHEN T04.system_sort ='183' AND T04.industry_plan = '004' THEN 1 ELSE 0 END) AS P31,");
        // 层次：中央
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='061' AND T04.system_sort ='183' THEN 1 ELSE 0 END) AS T31,");
        // 层次：省
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='062' AND T04.system_sort ='183' THEN 1 ELSE 0 END) AS U31,");
        // 层次：地
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='063' AND T04.system_sort ='183' THEN 1 ELSE 0 END) AS V31,");
        // 层次：县
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='064' AND T04.system_sort ='183' THEN 1 ELSE 0 END) AS W31,");
        // 层次：乡镇
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='998' AND T04.system_sort ='183' THEN 1 ELSE 0 END) AS X31,");
        // 从业人员总数
        sql.append("T23.cyrs AS Y31, ");
        // 编制数量
        sql.append("CASE WHEN T04.system_sort ='183' THEN SUM(T04.establishment_num) ELSE 0 END AS Z31,");
        // 退休人员数量
        sql.append("T23.txrys AS AC31,");
        // -- 层次：中央 unit_manage_scale ='061'
        // 上年末单位个数
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='061'  AND  SUBSTR(T04.LOGIN_DATE,1,4) < '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS D32 ,");
        // 本年末单位个数
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='061'  AND SUBSTR(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS E32 ,");
        // 公益一类
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='061'  AND T04.unit_nature_tow ='001' THEN 1 ELSE 0 END) AS F32,");
        // 公益二类
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='061' AND T04.unit_nature_tow ='002' THEN 1 ELSE 0 END) AS G32,");
        // 其他
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='061' AND T04.unit_nature_tow NOT IN ('001','002') THEN  1  ELSE 0 END )AS H32,");
        //行业：卫生 
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='061' AND T04.industry_plan = '004' THEN 1 ELSE 0 END) AS P32,");
        //  层次：中央
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='061'  THEN 1 ELSE 0 END) AS T32,");
        // 从业人员总数
        sql.append("T24.cyrs AS Y32,");
        //  编制数量
        sql.append("CASE WHEN T04.unit_manage_scale ='061' THEN SUM(T04.establishment_num) ELSE 0 END AS Z32,");
        // 退休人员数量
        sql.append("T24.txrys AS AC32,");
        // -- 省（区、市）unit_manage_scale ='062' 层次
        // 上年末单位个数
        sql.append("SUM(T04.unit_manage_scale ='062'  AND CASE WHEN SUBSTR(T04.LOGIN_DATE,1,4) < '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS D33 ,");
        // 本年末单位个数
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='062'  AND SUBSTR(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS E33 ,");
        // 公益一类
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='062'  AND T04.unit_nature_tow ='001' THEN 1 ELSE 0 END) AS F33,");
        // 公益二类
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='062' AND T04.unit_nature_tow ='002' THEN 1 ELSE 0 END) AS G33,");
        // 其他
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='062' AND T04.unit_nature_tow NOT IN ('001','002') THEN  1  ELSE 0 END )AS H33,");
        //行业：卫生 
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='062' AND T04.industry_plan = '004' THEN 1 ELSE 0 END) AS P33,");
        //  层次：省
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='062'  THEN 1 ELSE 0 END) AS U33,");
        // 从业人员总数
        sql.append("T25.cyrs AS Y33,");
        //  编制数量
        sql.append("CASE WHEN T04.unit_manage_scale ='062' THEN SUM(T04.establishment_num) ELSE 0 END AS Z33,");
        // 退休人员数量
        sql.append("T25.txrys AS AC33,");
        // -- 地（市、州、盟）unit_manage_scale ='063' 层次
        // 上年末单位个数
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='063'  AND SUBSTR(T04.LOGIN_DATE,1,4) < '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS D34 ,");
        // 本年末单位个数
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='063'  AND SUBSTR(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS E34 ,");
        // 公益一类
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='063'  AND T04.unit_nature_tow ='001' THEN 1 ELSE 0 END) AS F34, ");
        // 公益二类
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='063' AND T04.unit_nature_tow ='002' THEN 1 ELSE 0 END) AS G34,");
        // 其他
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='063' AND T04.unit_nature_tow NOT IN ('001','002') THEN  1  ELSE 0 END )AS H34,");
        //行业：卫生 
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='063' AND T04.industry_plan = '004' THEN 1 ELSE 0 END) AS P34,");
        //  层次：地（市、州、盟）
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='063'  THEN 1 ELSE 0 END) AS V34,");
        // 从业人员总数
        sql.append("T26.cyrs AS Y34,");
        //  编制数量
        sql.append("CASE WHEN T04.unit_manage_scale ='062' THEN SUM(T04.establishment_num) ELSE 0 END AS Z34,");
        // 退休人员数量
        sql.append("T26.txrys AS AC34,");
        // --县（市、区、旗）unit_manage_scale ='064' 层次
        // 上年末单位个数
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='064'  AND SUBSTR(T04.LOGIN_DATE,1,4) < '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS D35 ,");
        // 本年末单位个数
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='064'  AND SUBSTR(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS E35 ,");
        // 公益一类
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='064'  AND T04.unit_nature_tow ='001' THEN 1 ELSE 0 END) AS F35,");
        // 公益二类
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='064' AND T04.unit_nature_tow ='002' THEN 1 ELSE 0 END) AS G35,");
        // 其他
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='064' AND T04.unit_nature_tow NOT IN ('001','002') THEN  1  ELSE 0 END )AS H35,");
        //行业：卫生 
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='064' AND T04.industry_plan = '004' THEN 1 ELSE 0 END) AS P35,");
        // 层次：县（市、区、旗）
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='064' THEN 1 ELSE 0 END) AS W35,");
        // 从业人员总数
        sql.append("T27.cyrs AS Y35, ");
        //  编制数量
        sql.append("CASE WHEN T04.unit_manage_scale ='064' THEN SUM(T04.establishment_num) ELSE 0 END AS Z35,");
        // 退休人员数量
        sql.append("T27.txrys AS AC35,");
        // -- 乡镇 unit_manage_scale ='998' 层次
        // 上年末单位个数
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='998'  AND SUBSTR(T04.LOGIN_DATE,1,4) < '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS D36 ,");
        // 本年末单位个数
        sql.append("SUM(CASE WHEN SUBSTR(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS E36 ,");
        // 公益一类
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='998'  AND T04.unit_nature_tow ='001' THEN 1 ELSE 0 END) AS F36,");
        // 公益二类
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='998' AND T04.unit_nature_tow ='002' THEN 1 ELSE 0 END) AS G36,");
        // 其他
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='998' AND T04.unit_nature_tow NOT IN ('001','002') THEN  1  ELSE 0 END )AS H36, ");
        //行业：卫生 
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='998' AND T04.industry_plan = '004' THEN 1 ELSE 0 END) AS P36,");
        //  乡镇 
        sql.append("SUM(CASE WHEN T04.unit_manage_scale ='998'  THEN 1 ELSE 0 END) AS X36,");
        // 从业人员总数
        sql.append("T28.cyrs AS Y36, ");
        //  编制数量
        sql.append("CASE WHEN T04.unit_manage_scale ='064' THEN SUM(T04.establishment_num) ELSE 0 END AS Z36,");
        // 退休人员数量
        sql.append("T28.txrys AS AC36 ");
        sql.append(" FROM m_tb04_unit AS T04 LEFT JOIN(SELECT PERSONNEL_UNIT,PERSONNEL_ID,");
        sql.append("    SUM(CASE WHEN RETIRE_FLAG ='1' THEN 1 ELSE 0 END) AS tuixiurenyuan,");
        sql.append("    SUM(CASE WHEN RETIRE_FLAG <>'1' THEN 1 ELSE 0 END) as congyerenyuan ");
        sql.append(" FROM m_tb12_personnel WHERE DEL_KBN = '0' ");
        sql.append(" GROUP BY PERSONNEL_UNIT) AS  T12 ");
        sql.append(" ON T04.unit_no = T12.PERSONNEL_UNIT ");
        sql.append("LEFT JOIN  ");
        sql.append(" (SELECT  PERSONNEL_UNIT,");
        sql.append(" SUM(CASE WHEN RETIRE_FLAG ='1' THEN 1 ELSE 0 END) AS txrys, ");
        sql.append(" SUM(CASE WHEN RETIRE_FLAG <>'1' THEN 1 ELSE 0 END) AS cyrs  ");
        sql.append("FROM m_tb12_personnel WHERE DEL_KBN ='0' AND PERSONNEL_UNIT  ");
        sql.append(" IN (SELECT UNIT_NO FROM m_tb04_unit WHERE DEL_KBN ='0' AND industry_plan = '004' AND unit_nature = '056' AND ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no() +"' OR UNIT_NO LIKE CONCAT('',+'"+rlgl200101BeanNew.getUnit_no()+"', '%'))) AS T13  ");
        sql.append(" ON T04.unit_no = T13.PERSONNEL_UNIT ");
        sql.append(" LEFT JOIN  ");
        sql.append(" (SELECT PERSONNEL_UNIT, SUM(CASE WHEN RETIRE_FLAG ='1' THEN 1 ELSE 0 END) AS txrys, ");
        sql.append(" SUM(CASE WHEN RETIRE_FLAG <>'1' THEN 1 ELSE 0 END) AS cyrs  ");
        sql.append(" FROM m_tb12_personnel ");
        sql.append(" WHERE DEL_KBN='0' AND RETIRE_FLAG <>'1' AND PERSONNEL_UNIT  ");
        sql.append(" IN( SELECT UNIT_NO FROM m_tb04_unit WHERE unit_nature_tow ='004' AND unit_nature = '056' AND DEL_KBN='0' AND ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no() +"' OR UNIT_NO LIKE CONCAT('','"+rlgl200101BeanNew.getUnit_no()+"', '%')))AS T14 ");
        sql.append(" ON T04.unit_no = T14.PERSONNEL_UNIT ");
        sql.append("LEFT JOIN ");
        sql.append(" (SELECT PERSONNEL_UNIT,SUM(CASE WHEN RETIRE_FLAG ='1' THEN 1 ELSE 0 END) AS txrys, ");
        sql.append(" SUM(CASE WHEN RETIRE_FLAG <>'1' THEN 1 ELSE 0 END) AS cyrs ");
        sql.append(" FROM m_tb12_personnel WHERE DEL_KBN ='0' AND PERSONNEL_UNIT IN ");
        sql.append(" (SELECT UNIT_NO FROM m_tb04_unit WHERE DEL_KBN ='0' AND system_sort ='175' AND unit_nature = '056' AND ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no() +"' OR UNIT_NO LIKE CONCAT('','"+rlgl200101BeanNew.getUnit_no()+"', '%')))AS T15 ");
        sql.append("ON T04.unit_no = T15.PERSONNEL_UNIT  ");
        sql.append("LEFT JOIN ");
        sql.append(" (SELECT PERSONNEL_UNIT,SUM(CASE WHEN RETIRE_FLAG ='1' THEN 1 ELSE 0 END) AS txrys, ");
        sql.append(" SUM(CASE WHEN RETIRE_FLAG <>'1' THEN 1 ELSE 0 END) AS cyrs ");
        sql.append(" FROM m_tb12_personnel WHERE DEL_KBN ='0' AND PERSONNEL_UNIT IN ");
        sql.append(" (SELECT UNIT_NO FROM m_tb04_unit WHERE DEL_KBN ='0' AND system_sort ='176' AND unit_nature = '056' AND ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no() +"' OR UNIT_NO LIKE CONCAT('','"+rlgl200101BeanNew.getUnit_no()+"', '%')))AS T16 ");
        sql.append(" ON T04.unit_no = T16.PERSONNEL_UNIT  ");
        sql.append("LEFT JOIN ");
        sql.append(" (SELECT PERSONNEL_UNIT,SUM(CASE WHEN RETIRE_FLAG ='1' THEN 1 ELSE 0 END) AS txrys,");
        sql.append(" SUM(CASE WHEN RETIRE_FLAG <>'1' THEN 1 ELSE 0 END) AS cyrs ");
        sql.append(" FROM m_tb12_personnel WHERE DEL_KBN ='0' AND PERSONNEL_UNIT IN");
        sql.append(" (SELECT UNIT_NO FROM m_tb04_unit WHERE DEL_KBN ='0' AND system_sort ='177' AND unit_nature = '056' AND ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no() +"' OR UNIT_NO LIKE CONCAT('','"+rlgl200101BeanNew.getUnit_no()+"', '%')))AS T17 ");
        sql.append("ON T04.unit_no = T17.PERSONNEL_UNIT  ");
        sql.append("LEFT JOIN  ");
        sql.append(" (SELECT PERSONNEL_UNIT,SUM(CASE WHEN RETIRE_FLAG ='1' THEN 1 ELSE 0 END) AS txrys, ");
        sql.append(" SUM(CASE WHEN RETIRE_FLAG <>'1' THEN 1 ELSE 0 END) AS cyrs  ");
        sql.append(" FROM m_tb12_personnel WHERE DEL_KBN ='0' AND PERSONNEL_UNIT IN  ");
        sql.append(" (SELECT UNIT_NO FROM m_tb04_unit WHERE DEL_KBN ='0' AND system_sort ='178' AND unit_nature = '056' AND ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no() +"' OR UNIT_NO LIKE CONCAT('','"+rlgl200101BeanNew.getUnit_no()+"', '%')))AS T18 ");
        sql.append("ON T04.unit_no = T18.PERSONNEL_UNIT  ");
        sql.append("LEFT JOIN  ");
        sql.append(" (SELECT PERSONNEL_UNIT,SUM(CASE WHEN RETIRE_FLAG ='1' THEN 1 ELSE 0 END) AS txrys,");
        sql.append(" SUM(CASE WHEN RETIRE_FLAG <>'1' THEN 1 ELSE 0 END) AS cyrs ");
        sql.append(" FROM m_tb12_personnel WHERE DEL_KBN ='0' AND PERSONNEL_UNIT IN ");
        sql.append(" (SELECT UNIT_NO FROM m_tb04_unit WHERE DEL_KBN ='0' AND system_sort ='179' AND unit_nature = '056' AND ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no() +"' OR UNIT_NO LIKE CONCAT('','"+rlgl200101BeanNew.getUnit_no()+"', '%')))AS T19 ");
        sql.append(" ON T04.unit_no = T19.PERSONNEL_UNIT ");
        sql.append(" LEFT JOIN ");
        sql.append(" (SELECT PERSONNEL_UNIT,SUM(CASE WHEN RETIRE_FLAG ='1' THEN 1 ELSE 0 END) AS txrys,");
        sql.append(" SUM(CASE WHEN RETIRE_FLAG <>'1' THEN 1 ELSE 0 END) AS cyrs");
        sql.append(" FROM m_tb12_personnel WHERE DEL_KBN ='0' AND PERSONNEL_UNIT IN ");
        sql.append(" (SELECT UNIT_NO FROM m_tb04_unit WHERE DEL_KBN ='0' AND system_sort ='180' AND unit_nature = '056' AND ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no() +"' OR UNIT_NO LIKE CONCAT('','"+rlgl200101BeanNew.getUnit_no()+"', '%')))AS T20 ");
        sql.append(" ON T04.unit_no = T20.PERSONNEL_UNIT ");
        sql.append(" LEFT JOIN ");
        sql.append(" (SELECT PERSONNEL_UNIT,SUM(CASE WHEN RETIRE_FLAG ='1' THEN 1 ELSE 0 END) AS txrys, ");
        sql.append(" SUM(CASE WHEN RETIRE_FLAG <>'1' THEN 1 ELSE 0 END) AS cyrs ");
        sql.append(" FROM m_tb12_personnel WHERE DEL_KBN ='0' AND PERSONNEL_UNIT IN ");
        sql.append(" (SELECT UNIT_NO FROM m_tb04_unit WHERE DEL_KBN ='0' AND system_sort ='181' AND unit_nature = '056' AND ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no() +"' OR UNIT_NO LIKE CONCAT('','"+rlgl200101BeanNew.getUnit_no()+"', '%')))AS T21 ");
        sql.append(" ON T04.unit_no = T21.PERSONNEL_UNIT ");
        sql.append(" LEFT JOIN ");
        sql.append(" (SELECT PERSONNEL_UNIT, ");
        sql.append(" SUM(CASE WHEN RETIRE_FLAG ='1' THEN 1 ELSE 0 END) AS txrys, ");
        sql.append(" SUM(CASE WHEN RETIRE_FLAG <>'1' THEN 1 ELSE 0 END) AS cyrs ");
        sql.append(" FROM m_tb12_personnel WHERE DEL_KBN ='0' AND PERSONNEL_UNIT IN ");
        sql.append(" (SELECT UNIT_NO FROM m_tb04_unit WHERE DEL_KBN ='0' AND system_sort ='182' AND unit_nature = '056' AND ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no() +"' OR UNIT_NO LIKE CONCAT('','"+rlgl200101BeanNew.getUnit_no()+"', '%')))AS T22 ");
        sql.append(" ON T04.unit_no = T22.PERSONNEL_UNIT ");
        sql.append(" LEFT JOIN ");
        sql.append(" (SELECT PERSONNEL_UNIT,SUM(CASE WHEN RETIRE_FLAG ='1' THEN 1 ELSE 0 END) AS txrys,  ");
        sql.append(" SUM(CASE WHEN RETIRE_FLAG <>'1' THEN 1 ELSE 0 END) AS cyrs ");
        sql.append(" FROM m_tb12_personnel WHERE DEL_KBN ='0' AND PERSONNEL_UNIT IN ");
        sql.append(" (SELECT UNIT_NO FROM m_tb04_unit WHERE DEL_KBN ='0' AND system_sort ='183' AND unit_nature = '056' AND ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no() +"' OR UNIT_NO LIKE CONCAT('','"+rlgl200101BeanNew.getUnit_no()+"', '%')))AS T23 ");
        sql.append(" ON T04.unit_no = T23.PERSONNEL_UNIT ");
        sql.append(" LEFT JOIN ");
        sql.append(" (SELECT PERSONNEL_UNIT,SUM(CASE WHEN RETIRE_FLAG ='1' THEN 1 ELSE 0 END) AS txrys,  ");
        sql.append(" SUM(CASE WHEN RETIRE_FLAG <>'1' THEN 1 ELSE 0 END) AS cyrs ");
        sql.append(" FROM m_tb12_personnel WHERE DEL_KBN ='0' AND PERSONNEL_UNIT IN ");
        sql.append(" (SELECT UNIT_NO FROM m_tb04_unit WHERE DEL_KBN ='0' AND unit_manage_scale ='061' AND unit_nature = '056' AND ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no() +"' OR UNIT_NO LIKE CONCAT('','"+rlgl200101BeanNew.getUnit_no()+"', '%')))AS T24 ");
        sql.append(" ON T04.unit_no = T24.PERSONNEL_UNIT ");
        sql.append(" LEFT JOIN ");
        sql.append(" (SELECT PERSONNEL_UNIT,SUM(CASE WHEN RETIRE_FLAG ='1' THEN 1 ELSE 0 END) AS txrys,");
        sql.append(" SUM(CASE WHEN RETIRE_FLAG <>'1' THEN 1 ELSE 0 END) AS cyrs ");
        sql.append(" FROM m_tb12_personnel WHERE DEL_KBN ='0' AND PERSONNEL_UNIT IN ");
        sql.append(" (SELECT UNIT_NO FROM m_tb04_unit WHERE DEL_KBN ='0' AND unit_manage_scale ='062' AND unit_nature = '056' AND ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no() +"' OR UNIT_NO LIKE CONCAT('','"+rlgl200101BeanNew.getUnit_no()+"', '%')))AS T25 ");
        sql.append(" ON T04.unit_no = T25.PERSONNEL_UNIT ");
        sql.append(" LEFT JOIN ");
        sql.append(" (SELECT PERSONNEL_UNIT,SUM(CASE WHEN RETIRE_FLAG ='1' THEN 1 ELSE 0 END) AS txrys, ");
        sql.append(" SUM(CASE WHEN RETIRE_FLAG <>'1' THEN 1 ELSE 0 END) AS cyrs ");
        sql.append(" FROM m_tb12_personnel WHERE DEL_KBN ='0' AND PERSONNEL_UNIT IN ");
        sql.append(" (SELECT UNIT_NO FROM m_tb04_unit WHERE DEL_KBN ='0' AND unit_manage_scale ='063' AND unit_nature = '056' AND ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no() +"' OR UNIT_NO LIKE CONCAT('','"+rlgl200101BeanNew.getUnit_no()+"', '%')))AS T26 ");
        sql.append(" ON T04.unit_no = T26.PERSONNEL_UNIT ");
        sql.append(" LEFT JOIN ");
        sql.append(" (SELECT PERSONNEL_UNIT,SUM(CASE WHEN RETIRE_FLAG ='1' THEN 1 ELSE 0 END) AS txrys,");
        sql.append(" SUM(CASE WHEN RETIRE_FLAG <>'1' THEN 1 ELSE 0 END) AS cyrs  ");
        sql.append(" FROM m_tb12_personnel WHERE DEL_KBN ='0' AND PERSONNEL_UNIT IN ");
        sql.append(" (SELECT UNIT_NO FROM m_tb04_unit WHERE DEL_KBN ='0' AND unit_manage_scale ='064' AND unit_nature = '056' AND ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no() +"' OR UNIT_NO LIKE CONCAT('','"+rlgl200101BeanNew.getUnit_no()+"', '%')))AS T27 ");
        sql.append(" ON T04.unit_no = T27.PERSONNEL_UNIT ");
        sql.append(" LEFT JOIN ");
        sql.append(" (SELECT PERSONNEL_UNIT,SUM(CASE WHEN RETIRE_FLAG ='1' THEN 1 ELSE 0 END) AS txrys,");
        sql.append("SUM(CASE WHEN RETIRE_FLAG <>'1' THEN 1 ELSE 0 END) AS cyrs ");
        sql.append(" FROM m_tb12_personnel WHERE DEL_KBN ='0' AND PERSONNEL_UNIT IN ");
        sql.append(" (SELECT UNIT_NO FROM m_tb04_unit WHERE DEL_KBN ='0' AND unit_manage_scale ='998' AND unit_nature = '056' AND ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no() +"' OR UNIT_NO LIKE CONCAT('','"+rlgl200101BeanNew.getUnit_no()+"', '%')))AS T28 ");
        sql.append(" ON T04.unit_no = T28.PERSONNEL_UNIT ");
        sql.append(" WHERE  T04.unit_nature = '056'  AND T04.DEL_KBN ='0' ");
        sql.append(" AND T04.unit_no in ");
        // 下级单位的限定
        sql.append(" (SELECT unit_no FROM m_tb04_unit WHERE ESCROW_UNIT_NO = '" + rlgl200101BeanNew.getUnit_no() + "'  AND UNIT_STATUS = '1' or unit_no like CONCAT('', '"+rlgl200101BeanNew.getUnit_no()+"','%')) ");
    
        return sql.toString();
    }
    public String EditUnitBaseInfoSql071(Rlgl200101Bean rlgl200101BeanNew) {
        StringBuilder sql = new StringBuilder();
        
        sql.append(" select  ");
        //女，卫生和社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND b.industry_divide='153' then 1 else 0 end) as 'E23', ");
        //女，公立医院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND b.industry_divide_two='167' then 1 else 0 end) as 'E24', ");
        //女，社区医疗与卫生院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND b.industry_divide_two='168' then 1 else 0 end) as 'E25', ");
        //女，门诊部（所）
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND b.industry_divide_two='169' then 1 else 0 end) as 'E26', ");
        //女，社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND b.industry_divide_two='170' then 1 else 0 end) as 'E27', ");
        //少数民族，卫生和社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND b.industry_divide='153' then 1 else 0 end) as 'F23', ");
        //少数民族，公立医院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND b.industry_divide_two='167' then 1 else 0 end) as 'F24', ");
        //少数民族，社区医疗与卫生院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND b.industry_divide_two='168' then 1 else 0 end) as 'F25', ");
        //少数民族，门诊部（所）
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND b.industry_divide_two='169' then 1 else 0 end) as 'F26', ");
        //少数民族，社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND b.industry_divide_two='170' then 1 else 0 end) as 'F27', ");
        //中共党员，卫生和社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND b.industry_divide='153' then 1 else 0 end) as 'G23', ");
        //中共党员，公立医院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND b.industry_divide_two='167' then 1 else 0 end) as 'G24', ");
        //中共党员，社区医疗与卫生院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND b.industry_divide_two='168' then 1 else 0 end) as 'G25', ");
        //中共党员，门诊部（所）
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND b.industry_divide_two='169' then 1 else 0 end) as 'G26', ");
        //中共党员，社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND b.industry_divide_two='170' then 1 else 0 end) as 'G27', ");
        //博士，卫生和社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='006' AND b.industry_divide='153' then 1 else 0 end) as 'H23', ");
        //博士，公立医院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='006' AND b.industry_divide_two='167' then 1 else 0 end) as 'H24', ");
        //博士，社区医疗与卫生院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='006' AND b.industry_divide_two='168' then 1 else 0 end) as 'H25', ");
        //博士，门诊部（所）
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='006' AND b.industry_divide_two='169' then 1 else 0 end) as 'H26', ");
        //博士，社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='006' AND b.industry_divide_two='170' then 1 else 0 end) as 'H27', ");
        //硕士，卫生和社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='005' AND b.industry_divide='153' then 1 else 0 end) as 'I23', ");
        //硕士，公立医院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='005' AND b.industry_divide_two='167' then 1 else 0 end) as 'I24', ");
        //硕士，社区医疗与卫生院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='005' AND b.industry_divide_two='168' then 1 else 0 end) as 'I25', ");
        //硕士，门诊部（所）
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='005' AND b.industry_divide_two='169' then 1 else 0 end) as 'I26', ");
        //硕士，社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='005' AND b.industry_divide_two='170' then 1 else 0 end) as 'I27', ");
        //研究生，卫生和社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu IN ('005','006') AND b.industry_divide='153' then 1 else 0 end) as 'K23', ");
        //研究生，公立医院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu IN ('005','006') and b.industry_divide_two='167' then 1 else 0 end) as 'K24', ");
        //研究生，社区医疗与卫生院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu IN ('005','006') AND b.industry_divide_two='168' then 1 else 0 end) as 'K25', ");
        //研究生，门诊部（所）
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu IN ('005','006') AND b.industry_divide_two='169' then 1 else 0 end) as 'K26', ");
        //研究生，社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu IN ('005','006') AND b.industry_divide_two='170' then 1 else 0 end) as 'K27', ");
        //大学本科，卫生和社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='004' AND b.industry_divide='153' then 1 else 0 end) as 'L23', ");
        //大学本科，公立医院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='004' AND b.industry_divide_two='167' then 1 else 0 end) as 'L24', ");
        //大学本科，社区医疗与卫生院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='004' AND b.industry_divide_two='168' then 1 else 0 end) as 'L25', ");
        //大学本科，门诊部（所）
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='004' AND b.industry_divide_two='169' then 1 else 0 end) as 'L26', ");
        //大学本科，社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='004' AND b.industry_divide_two='170' then 1 else 0 end) as 'L27', ");
        //大学专科，卫生和社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='003' AND b.industry_divide='153' then 1 else 0 end) as 'M23', ");
        //大学专科，公立医院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='003' AND b.industry_divide_two='167' then 1 else 0 end) as 'M24', ");
        //大学专科，社区医疗与卫生院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='003' AND b.industry_divide_two='168' then 1 else 0 end) as 'M25', ");
        //大学专科，门诊部（所）
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='003' AND b.industry_divide_two='169' then 1 else 0 end) as 'M26', ");
        //大学专科，社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='003' AND b.industry_divide_two='170' then 1 else 0 end) as 'M27', ");
        //中专，卫生和社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='002' AND b.industry_divide='153' then 1 else 0 end) as 'N23', ");
        //中专，公立医院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='002' AND b.industry_divide_two='167' then 1 else 0 end) as 'N24', ");
        //中专，社区医疗与卫生院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='002' AND b.industry_divide_two='168' then 1 else 0 end) as 'N25', ");
        //中专，门诊部（所）
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='002' AND b.industry_divide_two='169' then 1 else 0 end) as 'N26', ");
        //中专，社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='002' AND b.industry_divide_two='170' then 1 else 0 end) as 'N27', ");
        //高中及以下，卫生和社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='001' AND b.industry_divide='153' then 1 else 0 end) as 'O23', ");
        //高中及以下，公立医院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='001' AND b.industry_divide_two='167' then 1 else 0 end) as 'O24', ");
        //高中及以下，社区医疗与卫生院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='001' AND b.industry_divide_two='168' then 1 else 0 end) as 'O25', ");
        //高中及以下，门诊部（所）
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='001' AND b.industry_divide_two='169' then 1 else 0 end) as 'O26', ");
        //高中及以下，社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='001' AND b.industry_divide_two='170' then 1 else 0 end) as 'O27', ");
        //35岁及以下，卫生和社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='001' AND b.industry_divide='153' then 1 else 0 end) as 'P23', ");
        //35岁及以下，公立医院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='001' AND b.industry_divide_two='167' then 1 else 0 end) as 'P24', ");
        //35岁及以下，社区医疗及卫生院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='001' AND b.industry_divide_two='168' then 1 else 0 end) as 'P25', ");
        //35岁及以下，门诊部（所）
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='001' AND b.industry_divide_two='169' then 1 else 0 end) as 'P26', ");
        //35岁及以下，社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='001' AND b.industry_divide_two='170' then 1 else 0 end) as 'P27', ");
        //36岁至40岁，卫生和社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='002' AND b.industry_divide='153' then 1 else 0 end) as 'Q23', ");
        //36岁至40岁，公立医院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='002' AND b.industry_divide_two='167' then 1 else 0 end) as 'Q24', ");
        //36岁至40岁，社区医疗及卫生院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='002' AND b.industry_divide_two='168' then 1 else 0 end) as 'Q25', ");
        //36岁至40岁，门诊部（所）
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='002' AND b.industry_divide_two='169' then 1 else 0 end) as 'Q26', ");
        //36岁至40岁，社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='002' AND b.industry_divide_two='170' then 1 else 0 end) as 'Q27', ");
        //41岁至45岁，卫生和社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='003' AND b.industry_divide='153' then 1 else 0 end) as 'R23', ");
        //41岁至45岁，公立医院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='003' AND b.industry_divide_two='167' then 1 else 0 end) as 'R24', ");
        //41岁至45岁，社区医疗与卫生院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='003' AND b.industry_divide_two='168' then 1 else 0 end) as 'R25', ");
        //41岁至45岁，门诊部（所）
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='003' AND b.industry_divide_two='169' then 1 else 0 end) as 'R26', ");
        //41岁至45岁，社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='003' AND b.industry_divide_two='170' then 1 else 0 end) as 'R27', ");
        //46岁至50岁，卫生和社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='004' AND b.industry_divide='153' then 1 else 0 end) as 'S23', ");
        //46岁至50岁，公立医院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='004' AND b.industry_divide_two='167' then 1 else 0 end) as 'S24', ");
        //46岁至50岁，社区医疗与卫生院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='004' AND b.industry_divide_two='168' then 1 else 0 end) as 'S25', ");
        //46岁至50岁，门诊部（所）
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='004' AND b.industry_divide_two='169' then 1 else 0 end) as 'S26', ");
        //46岁至50岁，社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='004' AND b.industry_divide_two='170' then 1 else 0 end) as 'S27', ");
        //51岁至54岁，卫生和社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='005' AND b.industry_divide='153' then 1 else 0 end) as 'T23', ");
        //51岁至54岁，公立医院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='005' AND b.industry_divide_two='167' then 1 else 0 end) as 'T24', ");
        //51岁至54岁，社区医疗与卫生院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='005' AND b.industry_divide_two='168' then 1 else 0 end) as 'T25', ");
        //51岁至54岁，门诊部（所）
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='005' AND b.industry_divide_two='169' then 1 else 0 end) as 'T26', ");
        //51岁至54岁，社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='005' AND b.industry_divide_two='170' then 1 else 0 end) as 'T27', ");
        //55岁至59岁，卫生和社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='006' AND b.industry_divide='153' then 1 else 0 end) as 'U23', ");
        //55岁至59岁，公立医院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='006' AND b.industry_divide_two='167' then 1 else 0 end) as 'U24', ");
        //55岁至59岁，社区医疗与卫生院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='006' AND b.industry_divide_two='168' then 1 else 0 end) as 'U25', ");
        //55岁至59岁，门诊部（所）
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='006' AND b.industry_divide_two='169' then 1 else 0 end) as 'U26', ");
        //55岁至59岁，社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='006' AND b.industry_divide_two='170' then 1 else 0 end) as 'U27', ");
        //60岁及以上，卫生和社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='007' AND b.industry_divide='153' then 1 else 0 end) as 'V23', ");
        //60岁及以上，公立医院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='007' AND b.industry_divide_two='167' then 1 else 0 end) as 'V24', ");
        //60岁及以上，社区医疗与卫生院
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='007' AND b.industry_divide_two='168' then 1 else 0 end) as 'V25', ");
        //60岁及以上，门诊部（所）
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='007' AND b.industry_divide_two='169' then 1 else 0 end) as 'V26', ");
        //60岁及以上，社会工作
        sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='007' AND b.industry_divide_two='170' then 1 else 0 end) as 'V27', ");
        sql.append("  ");
        //在管理岗位工作的，卫生和社会工作
        sql.append(" sum(case when c.IN_POST_KBN='004' AND c.AGEEREA='007' AND b.industry_divide='153' then 1 else 0 end) as 'W23', ");
        //在管理岗位工作的，公立医院
        sql.append(" sum(case when c.IN_POST_KBN='004' AND c.AGEEREA='007' AND b.industry_divide_two='167' then 1 else 0 end) as 'W24', ");
        //在管理岗位工作的，社区医疗与卫生院
        sql.append(" sum(case when c.IN_POST_KBN='004' AND c.AGEEREA='007' AND b.industry_divide_two='168' then 1 else 0 end) as 'W25', ");
        //在管理岗位工作的，门诊部（所）
        sql.append(" sum(case when c.IN_POST_KBN='004' AND c.AGEEREA='007' AND b.industry_divide_two='169' then 1 else 0 end) as 'W26', ");
        //在管理岗位工作的，社会工作
        sql.append(" sum(case when c.IN_POST_KBN='004' AND c.AGEEREA='007' AND b.industry_divide_two='170' then 1 else 0 end) as 'W27', ");
        //获取当前年度
        sql.append(" '"+rlgl200101BeanNew.getSel_year()+"'+'年' as 'O7' ");
        sql.append(" from M_TB12_PERSONNEL tb ");
        sql.append(" LEFT JOIN  ");
        sql.append(" (SELECT  ");
        sql.append(" age.AGEEREA,age.IN_POST_LEVEL,age.IN_POST_KBN,age.PERSONNEL_ID,age.EDU,age.CERTIFICATE_NO,personnel_unit ");
        sql.append(" from (SELECT  ");
        sql.append(" CASE WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) - YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
        sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ,  ");
        sql.append(" 5 ) < RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
        sql.append(" '%Y-%m-%d' ) ,  ");
        sql.append(" 5 ) ) <=35 THEN '001' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) - YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
        sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ,  ");
        sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
        sql.append(" '%Y-%m-%d' ) ,5 ) )  ");
        sql.append(" BETWEEN 36  ");
        sql.append(" AND 40 THEN '002' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
        sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ,  ");
        sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
        sql.append(" '%Y-%m-%d' ) ,  ");
        sql.append(" 5 ) )  ");
        sql.append(" BETWEEN 41  ");
        sql.append(" AND 45 THEN '003' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
        sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ,  ");
        sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
        sql.append(" '%Y-%m-%d' ) ,  ");
        sql.append(" 5 ) )  ");
        sql.append(" BETWEEN 46  ");
        sql.append(" AND 50 THEN '004' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
        sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ,  ");
        sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
        sql.append(" '%Y-%m-%d' ) ,  ");
        sql.append(" 5 ) )  ");
        sql.append(" BETWEEN 51  ");
        sql.append(" AND 54 THEN '005' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
        sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ,  ");
        sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
        sql.append(" '%Y-%m-%d' ) ,5 ) )  ");
        sql.append(" BETWEEN 55  ");
        sql.append(" AND 59 THEN '006' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
        sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ,  ");
        sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
        sql.append(" '%Y-%m-%d' ) , 5 ) )  ");
        sql.append(" >=60 THEN '007'  ");
        sql.append(" ELSE 0 END AS AGEEREA , ");
        sql.append(" ( ");
        sql.append(" SELECT EDUCATIONAL_BG  ");
        sql.append(" FROM m_tb15_personnel_edu_info ");
        sql.append(" WHERE  ");
        sql.append(" PERSONNEL_ID=A.PERSONNEL_ID  ");
        sql.append(" and  ");
        sql.append(" GRADUATION_TIME =  ");
        sql.append(" ( ");
        sql.append(" Select Max(GRADUATION_TIME)  ");
        sql.append(" From  m_tb15_personnel_edu_info  ");
        sql.append(" WHERE ");
        sql.append(" PERSONNEL_ID=A.PERSONNEL_ID  ");
        sql.append(" ) ");
        sql.append(" ) AS edu , ");
        sql.append(" case when m.IN_POST_LEVEL = '' then null else m.IN_POST_LEVEL end as IN_POST_LEVEL, ");
        sql.append(" m.IN_POST_KBN, ");
        sql.append(" D.CERTIFICATE_NO, ");
        sql.append(" A.PERSONNEL_ID, ");
        sql.append(" A.personnel_unit  ");
        sql.append(" FROM  ");
        sql.append(" M_TB12_PERSONNEL as A  ");
        sql.append(" LEFT JOIN ");
        sql.append(" m_tb09_irin m ");
        sql.append(" on m.PERSON_NO=A.PERSONNEL_ID  ");
        sql.append(" LEFT JOIN m_tb19_personnel_practitioners_info D ");
        sql.append(" on D.PERSONNEL_ID=A.PERSONNEL_ID  ");
        sql.append(" where substr(a.LOGIN_DATE,1,4)<='"+rlgl200101BeanNew.getSel_year()+"' ");
        sql.append(" and substr(m.LOGIN_DATE,1,4)<='"+rlgl200101BeanNew.getSel_year()+"' ");
        sql.append(" and  ");
        //所管辖单位
        sql.append(" (A.PERSONNEL_UNIT in  ");
        sql.append(" (SELECT unit_no FROM m_tb04_unit WHERE ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no()+"' AND DEL_KBN = 0 AND UNIT_STATUS = '1') ");
        sql.append(" or A.PERSONNEL_UNIT LIKE CONCAT('', '"+rlgl200101BeanNew.getUnit_no()+"', '%') ");
        sql.append(" ) ");
        sql.append(" ) as age ");
        sql.append(" ) as c ");
        sql.append(" on tb.PERSONNEL_ID =c.PERSONNEL_ID  ");
        sql.append(" LEFT JOIN ");
        sql.append(" m_tb04_unit b ");
        sql.append(" ON ");
        sql.append(" c.personnel_unit=b.unit_no ");
        sql.append(" where  ");
        sql.append(" substr(tb.LOGIN_DATE,1,4)<='"+rlgl200101BeanNew.getSel_year()+"' ");
        return sql.toString();
    }
    /*
     * 编辑事业单位工作人员分系统情况,sheet7.1
     * */
    public String EditUnitBaseInfoSql091(Rlgl200101Bean rlgl200101BeanNew) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        //卫生，八级
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='008' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'D38', ");
        //卫生，九级
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='009' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'E38', ");
        //卫生，十级
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='010' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'F38', ");
        //卫生，十一级
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='011' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'G38', ");
        //卫生，十二级
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='012' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'H38', ");
        //卫生，十三级
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='013' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'I38', ");
        //卫生，其他等级人员
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL not in('008','009','010','011','012','013') and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'J38', ");
        //卫生，其他从业人员
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and (a.IN_POST_LEVEL='014' OR a.IN_POST_LEVEL is NULL) and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'K38', ");
        //卫生，在管理岗位的
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_KBN='004' then 1 else 0 end) as 'L38', ");
        //卫生，具有执业资格的
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and c.CERTIFICATE_NO is NOT NULL and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'M38', ");
        //卫生，中央
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and b.UNIT_MANAGE_SCALE='061' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'N38', ");
        //卫生，省（区、市）
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and b.UNIT_MANAGE_SCALE='062' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'O38', ");
        //卫生，地（市，州，盟）
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and b.UNIT_MANAGE_SCALE='063' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'P38', ");
        //卫生，县（市、区、旗）
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and b.UNIT_MANAGE_SCALE='064' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'Q38', ");
        //卫生，乡（镇）
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and b.UNIT_MANAGE_SCALE='998' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'R38', ");
        //卫生，一级（高级技师）
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and SYSTEM_SORT_TWO='024' and a.POST_KBN='007' and a.POST_LEVEL='001' then 1 else 0 end) as 'T38', ");
        //卫生，二级（技师）
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.POST_KBN='007' and a.POST_LEVEL='002' then 1 else 0 end) as 'U38', ");
        //卫生，三级（高级工）
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.POST_KBN='007' and a.POST_LEVEL='003' then 1 else 0 end) as 'V38', ");
        //卫生，四级（中级工）
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.POST_KBN='007' and a.POST_LEVEL='004' then 1 else 0 end) as 'W38', ");
        //卫生，五级（初级工）
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.POST_KBN='007' and a.POST_LEVEL='005' then 1 else 0 end) as 'X38', ");
        //卫生，普通工
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.POST_KBN='007' and a.POST_LEVEL='006' then 1 else 0 end) as 'Y38', ");
        //卫生，其他等级人员
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.POST_KBN='007' and a.POST_LEVEL='007' then 1 else 0 end) as 'Z38', ");
        //卫生，其他从业人员
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.POST_KBN='007' and a.POST_LEVEL is NULL then 1 else 0 end) as 'AA38', ");
        //卫生，中央
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.POST_KBN='007' and b.UNIT_MANAGE_SCALE='061' then 1 else 0 end) as 'AB38', ");
        //卫生，省（区、市）
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.POST_KBN='007' and b.UNIT_MANAGE_SCALE='062' then 1 else 0 end) as 'AC38', ");
        //卫生，地（市、州、盟）
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.POST_KBN='007' and b.UNIT_MANAGE_SCALE='063' then 1 else 0 end) as 'AD38', ");
        //卫生，县（市、区、旗）
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.POST_KBN='007' and b.UNIT_MANAGE_SCALE='064' then 1 else 0 end) as 'AE38', ");
        //卫生，乡（镇）
        sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.POST_KBN='007' and b.UNIT_MANAGE_SCALE='998' then 1 else 0 end) as 'AF38',");
        sql.append(" '"+rlgl200101BeanNew.getSel_year()+"' as 'P7' ");
        sql.append(" FROM ");
        sql.append(" m_tb09_irin a ");
        sql.append(" LEFT JOIN ");
        sql.append(" m_tb04_unit b ");
        sql.append(" ON ");
        sql.append(" a.UNIT_NO=b.UNIT_NO ");
        sql.append(" LEFT JOIN ");
        sql.append(" m_tb19_personnel_practitioners_info c ");
        sql.append(" ON ");
        sql.append(" a.PERSON_NO=c.PERSONNEL_ID ");
        sql.append(" WHERE ");
        sql.append(" substr(a.LOGIN_DATE,1,4)='"+rlgl200101BeanNew.getSel_year()+"' ");
        sql.append(" and  ");
        sql.append("         (a.UNIT_NO in  ");
        sql.append(" (SELECT unit_no FROM m_tb04_unit WHERE ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no()+"' AND DEL_KBN = 0 AND UNIT_STATUS = '1') ");
        sql.append("         or a.UNIT_NO LIKE CONCAT('', '"+rlgl200101BeanNew.getUnit_no()+"', '%') ");
        sql.append("         ) ");
        sql.append(" and ");
        sql.append(" substr(a.LOGIN_DATE,1,4)<='"+rlgl200101BeanNew.getSel_year()+"' ");

        return sql.toString();
    }    
    /**
     * 编辑事业单位工作人员增加、减少情况Sql,sheet3
     */
    public String EditUnitBaseInfoSql03(Rlgl200101Bean rlgl200101BeanNew) {
        StringBuilder sql = new StringBuilder();
        
        sql.append(" SELECT ");
        //管理人员上年末总数包括 管理人员，专职兼管理人员
        sql.append(" SUM(CASE when b.IN_POST_KBN in('006','004') and SUBSTR(b.login_date,1,4)<'" + rlgl200101BeanNew.getSel_year() + "' and substr(a.login_date,1,4)+1='" + rlgl200101BeanNew.getSel_year() + "' then 1 else 0 end) as 'D14', ");
        //专业技术人员上年末总数 包括 专业技术人员，专职兼管理人员
        sql.append(" SUM(CASE when b.IN_POST_KBN in('027','004') and SUBSTR(b.login_date,1,4)+1='" + rlgl200101BeanNew.getSel_year() + "' and substr(a.login_date,1,4)+1='" + rlgl200101BeanNew.getSel_year() + "' then 1 else 0 end) as 'D15', ");
        //其中：在管理岗位的上年末总数
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='004' and SUBSTR(b.login_date,1,4)+1='" + rlgl200101BeanNew.getSel_year() + "' and substr(a.login_date,1,4)+1='" + rlgl200101BeanNew.getSel_year() + "' then 1 else 0 end) as 'D16', ");
        //工勤技能人员上年末总数
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='007' and SUBSTR(b.login_date,1,4)+1='" + rlgl200101BeanNew.getSel_year() + "' and substr(a.login_date,1,4)+1='" + rlgl200101BeanNew.getSel_year() + "' then 1 else 0 end) as 'D17', ");
        //其他从业人员上年末总数
        sql.append(" SUM(CASE WHEN (b.IN_POST_KBN NOT IN('006','027','004','007') OR b.IN_POST_KBN=NULL) and SUBSTR(b.login_date,1,4)-1='2012' and substr(a.login_date,1,4)+1='" + rlgl200101BeanNew.getSel_year() + "' then 1 else 0 end) as 'D18', ");
        //中央 上年末总数
        sql.append(" SUM(CASE WHEN c.unit_manage_scale='061' and SUBSTR(c.login_date,1,4)+1='" + rlgl200101BeanNew.getSel_year() + "' and substr(a.login_date,1,4)+1='" + rlgl200101BeanNew.getSel_year() + "' then 1 else 0 end) as 'D19', ");
        //省（区、市） 上年末总数
        sql.append(" SUM(CASE WHEN c.unit_manage_scale='062' and SUBSTR(c.login_date,1,4)+1='" + rlgl200101BeanNew.getSel_year() + "' and substr(a.login_date,1,4)+1='" + rlgl200101BeanNew.getSel_year() + "' then 1 else 0 end) as 'D20', ");
        //地（市、州、盟） 上年末总数
        sql.append(" SUM(CASE WHEN c.unit_manage_scale='063' and SUBSTR(c.login_date,1,4)+1='" + rlgl200101BeanNew.getSel_year() + "' and substr(a.login_date,1,4)+1='" + rlgl200101BeanNew.getSel_year() + "' then 1 else 0 end) as 'D21', ");
        //县（市、区、旗） 上年末总数
        sql.append(" SUM(CASE WHEN c.unit_manage_scale='064' and SUBSTR(c.login_date,1,4)+1='" + rlgl200101BeanNew.getSel_year() + "' and substr(a.login_date,1,4)+1='" + rlgl200101BeanNew.getSel_year() + "' then 1 else 0 end) as 'D22', ");
        //乡（镇） 上年末总数
        sql.append(" SUM(CASE WHEN c.unit_manage_scale='998' and SUBSTR(c.login_date,1,4)+1='" + rlgl200101BeanNew.getSel_year() + "' and substr(a.login_date,1,4)+1='" + rlgl200101BeanNew.getSel_year() + "' then 1 else 0 end) as 'D23', ");
        sql.append("  ");
        //管理人员，应届毕业生
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('006','004') and a.PERSONNEL_JOINMODE='177178001' then 1 else 0 end) as 'E14', ");
        //管理人员，社会人员
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('006','004') and a.PERSONNEL_JOINMODE='177178002' then 1 else 0 end) as 'F14', ");
        //管理人员，考试
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('006','004') and a.PERSONNEL_JOINMODE='177179001' then 1 else 0 end) as 'G14', ");
        //管理人员，考察
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('006','004') and a.PERSONNEL_JOINMODE='177179002' then 1 else 0 end) as 'H14', ");
        //管理人员，考核
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('006','004') and a.PERSONNEL_JOINMODE='177179003' then 1 else 0 end) as 'I14', ");
        //管理人员，任命
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('006','004') and a.PERSONNEL_JOINMODE='002' then 1 else 0 end) as 'J14', ");
        //管理人员，政策性安置
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('006','004') and a.PERSONNEL_JOINMODE='003' then 1 else 0 end) as 'K14', ");
        //管理人员，交流
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('006','004') and a.PERSONNEL_JOINMODE='004' then 1 else 0 end) as 'L14', ");
        //管理人员，其他
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('006','004') and a.PERSONNEL_JOINMODE in('006','005','007') then 1 else 0 end) as 'M14', ");
        //管理人员，退休
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('006','004') and a.PERSONNEL_OUTMODE='001' then 1 else 0 end) as 'N14', ");
        //管理人员，解除合同
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('006','004') and a.PERSONNEL_OUTMODE='002' then 1 else 0 end) as 'O14', ");
        //管理人员，开除
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('006','004') and a.PERSONNEL_OUTMODE='003' then 1 else 0 end) as 'P14', ");
        //管理人员，终止合同
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('006','004') and a.PERSONNEL_OUTMODE='004' then 1 else 0 end) as 'Q14', ");
        //管理人员，辞职辞退
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('006','004') and a.PERSONNEL_OUTMODE='005' then 1 else 0 end) as 'R14', ");
        //管理人员，交流
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('006','004') and a.PERSONNEL_OUTMODE='006' then 1 else 0 end) as 'S14', ");
        //管理人员，其他
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('006','004') and a.PERSONNEL_OUTMODE IN('007','008') OR a.personnel_outmode=NULL then 1 else 0 end) as 'T14', ");
        sql.append("  ");
        //专业技术人员，应届毕业生
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('027','004') and a.PERSONNEL_JOINMODE='177178001' then 1 else 0 end) as 'E15', ");
        //专业技术人员，社会人员
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('027','004') and a.PERSONNEL_JOINMODE='177178002' then 1 else 0 end) as 'F15', ");
        //专业技术人员，考试
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('027','004') and a.PERSONNEL_JOINMODE='177179001' then 1 else 0 end) as 'G15', ");
        //专业技术人员，考察
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('027','004') and a.PERSONNEL_JOINMODE='177179002' then 1 else 0 end) as 'H15', ");
        //专业技术人员，考核
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('027','004') and a.PERSONNEL_JOINMODE='177179003' then 1 else 0 end) as 'I15', ");
        //专业技术人员，任命
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('027','004') and a.PERSONNEL_JOINMODE='002' then 1 else 0 end) as 'J15', ");
        //专业技术人员，政策性安置
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('027','004') and a.PERSONNEL_JOINMODE='003' then 1 else 0 end) as 'K15', ");
        //专业技术人员，交流
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('027','004') and a.PERSONNEL_JOINMODE='004' then 1 else 0 end) as 'L15', ");
        //专业技术人员，其他
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('027','004') and a.PERSONNEL_JOINMODE in('006','005','007') then 1 else 0 end) as 'M15', ");
        //专业技术人员，退休
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('027','004') and a.PERSONNEL_OUTMODE='001' then 1 else 0 end) as 'N15', ");
        //专业技术人员，解除合同
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('027','004') and a.PERSONNEL_OUTMODE='002' then 1 else 0 end) as 'O15', ");
        //专业技术人员，开除
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('027','004') and a.PERSONNEL_OUTMODE='003' then 1 else 0 end) as 'P15', ");
        //专业技术人员，终止合同
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('027','004') and a.PERSONNEL_OUTMODE='004' then 1 else 0 end) as 'Q15', ");
        //专业技术人员，辞职辞退
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('027','004') and a.PERSONNEL_OUTMODE='005' then 1 else 0 end) as 'R15', ");
        //专业技术人员，交流
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('027','004') and a.PERSONNEL_OUTMODE='006' then 1 else 0 end) as 'S15', ");
        //专业技术人员，其他
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN in('027','004') and a.PERSONNEL_OUTMODE IN('007','008') OR a.personnel_outmode=NULL then 1 else 0 end) as 'T15', ");
        sql.append("  ");
        //其中：在管理岗位的，应届毕业生
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='004' and a.PERSONNEL_JOINMODE='177178001' then 1 else 0 end) as 'E16', ");
        //其中：在管理岗位的，社会人员
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='004' and a.PERSONNEL_JOINMODE='177178002' then 1 else 0 end) as 'F16', ");
        //其中：在管理岗位的，考试
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='004' and a.PERSONNEL_JOINMODE='177179001' then 1 else 0 end) as 'G16', ");
        //其中：在管理岗位的，考察
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='004' and a.PERSONNEL_JOINMODE='177179002' then 1 else 0 end) as 'H16', ");
        //其中：在管理岗位的，考核
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='004' and a.PERSONNEL_JOINMODE='177179003' then 1 else 0 end) as 'I16', ");
        //其中：在管理岗位的，任命
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='004' and a.PERSONNEL_JOINMODE='002' then 1 else 0 end) as 'J16', ");
        //其中：在管理岗位的，政策性安置
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='004' and a.PERSONNEL_JOINMODE='003' then 1 else 0 end) as 'K16', ");
        //其中：在管理岗位的，交流
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='004' and a.PERSONNEL_JOINMODE='004' then 1 else 0 end) as 'L16', ");
        //其中：在管理岗位的，其他
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='004' and a.PERSONNEL_JOINMODE in('006','005','007') then 1 else 0 end) as 'M16', ");
        //其中：在管理岗位的，退休
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='004' and a.PERSONNEL_OUTMODE='001' then 1 else 0 end) as 'N16', ");
        //其中：在管理岗位的，解除合同
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='004' and a.PERSONNEL_OUTMODE='002' then 1 else 0 end) as 'O16', ");
        //其中：在管理岗位的，开除
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='004' and a.PERSONNEL_OUTMODE='003' then 1 else 0 end) as 'P16', ");
        //其中：在管理岗位的，终止合同
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='004' and a.PERSONNEL_OUTMODE='004' then 1 else 0 end) as 'Q16', ");
        //其中：在管理岗位的，辞职辞退
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='004' and a.PERSONNEL_OUTMODE='005' then 1 else 0 end) as 'R16', ");
        //其中：在管理岗位的，交流
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='004' and a.PERSONNEL_OUTMODE='006' then 1 else 0 end) as 'S16', ");
        //其中：在管理岗位的，其他
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='004' and a.PERSONNEL_OUTMODE IN('007','008') OR a.personnel_outmode=NULL then 1 else 0 end) as 'T16', ");
        sql.append("  ");
        //工勤技能人员，应届毕业生
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='007' and a.PERSONNEL_JOINMODE='177178001' then 1 else 0 end) as 'E17', ");
        //工勤技能人员，社会人员
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='007' and a.PERSONNEL_JOINMODE='177178002' then 1 else 0 end) as 'F17', ");
        //工勤技能人员，考试
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='007' and a.PERSONNEL_JOINMODE='177179001' then 1 else 0 end) as 'G17', ");
        //工勤技能人员，考察
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='007' and a.PERSONNEL_JOINMODE='177179002' then 1 else 0 end) as 'H17', ");
        //工勤技能人员，考核
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='007' and a.PERSONNEL_JOINMODE='177179003' then 1 else 0 end) as 'I17', ");
        //工勤技能人员，任命
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='007' and a.PERSONNEL_JOINMODE='002' then 1 else 0 end) as 'J17', ");
        //工勤技能人员，政策性安置
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='007' and a.PERSONNEL_JOINMODE='003' then 1 else 0 end) as 'K17', ");
        //工勤技能人员，交流
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='007' and a.PERSONNEL_JOINMODE='004' then 1 else 0 end) as 'L17', ");
        //工勤技能人员，其他
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='007' and a.PERSONNEL_JOINMODE in('006','005','007') then 1 else 0 end) as 'M17', ");
        //工勤技能人员，退休
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='007' and a.PERSONNEL_OUTMODE='001' then 1 else 0 end) as 'N17', ");
        //工勤技能人员，解除合同
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='007' and a.PERSONNEL_OUTMODE='002' then 1 else 0 end) as 'O17', ");
        //工勤技能人员，开除
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='007' and a.PERSONNEL_OUTMODE='003' then 1 else 0 end) as 'P17', ");
        //工勤技能人员，终止合同
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='007' and a.PERSONNEL_OUTMODE='004' then 1 else 0 end) as 'Q17', ");
        //工勤技能人员，辞职辞退
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='007' and a.PERSONNEL_OUTMODE='005' then 1 else 0 end) as 'R17', ");
        //工勤技能人员，交流
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='007' and a.PERSONNEL_OUTMODE='006' then 1 else 0 end) as 'S17', ");
        //工勤技能人员，其他
        sql.append(" SUM(CASE WHEN b.IN_POST_KBN='007' and a.PERSONNEL_OUTMODE IN('007','008') OR a.personnel_outmode=NULL then 1 else 0 end) as 'T17', ");
        sql.append("  ");
        //其他从业人员，应届毕业生
        sql.append(" SUM(CASE WHEN (b.IN_POST_KBN NOT IN('006','027','004','007') OR b.IN_POST_KBN=NULL) and a.PERSONNEL_JOINMODE='177178001' then 1 else 0 end) as 'E18', ");
        //其他从业人员，社会人员
        sql.append(" SUM(CASE WHEN (b.IN_POST_KBN NOT IN('006','027','004','007') OR b.IN_POST_KBN=NULL) and a.PERSONNEL_JOINMODE='177178002' then 1 else 0 end) as 'F18', ");
        //其他从业人员，考试
        sql.append(" SUM(CASE WHEN (b.IN_POST_KBN NOT IN('006','027','004','007') OR b.IN_POST_KBN=NULL) and a.PERSONNEL_JOINMODE='177179001' then 1 else 0 end) as 'G18', ");
        //其他从业人员，考察
        sql.append(" SUM(CASE WHEN (b.IN_POST_KBN NOT IN('006','027','004','007') OR b.IN_POST_KBN=NULL) and a.PERSONNEL_JOINMODE='177179002' then 1 else 0 end) as 'H18', ");
        //其他从业人员，考核
        sql.append(" SUM(CASE WHEN (b.IN_POST_KBN NOT IN('006','027','004','007') OR b.IN_POST_KBN=NULL) and a.PERSONNEL_JOINMODE='177179003' then 1 else 0 end) as 'I18', ");
        //其他从业人员，任命
        sql.append(" SUM(CASE WHEN (b.IN_POST_KBN NOT IN('006','027','004','007') OR b.IN_POST_KBN=NULL) and a.PERSONNEL_JOINMODE='002' then 1 else 0 end) as 'J18', ");
        //其他从业人员，政策性安置
        sql.append(" SUM(CASE WHEN (b.IN_POST_KBN NOT IN('006','027','004','007') OR b.IN_POST_KBN=NULL) and a.PERSONNEL_JOINMODE='003' then 1 else 0 end) as 'K18', ");
        //其他从业人员，交流
        sql.append(" SUM(CASE WHEN (b.IN_POST_KBN NOT IN('006','027','004','007') OR b.IN_POST_KBN=NULL) and a.PERSONNEL_JOINMODE='004' then 1 else 0 end) as 'L18', ");
        //其他从业人员，其他
        sql.append(" SUM(CASE WHEN (b.IN_POST_KBN NOT IN('006','027','004','007') OR b.IN_POST_KBN=NULL) and a.PERSONNEL_JOINMODE in('006','005','007') then 1 else 0 end) as 'M18', ");
        //其他从业人员，退休
        sql.append(" SUM(CASE WHEN (b.IN_POST_KBN NOT IN('006','027','004','007') OR b.IN_POST_KBN=NULL) and a.PERSONNEL_OUTMODE='001' then 1 else 0 end) as 'N18', ");
        //其他从业人员，解除合同
        sql.append(" SUM(CASE WHEN (b.IN_POST_KBN NOT IN('006','027','004','007') OR b.IN_POST_KBN=NULL) and a.PERSONNEL_OUTMODE='002' then 1 else 0 end) as 'O18', ");
        //其他从业人员，开除
        sql.append(" SUM(CASE WHEN (b.IN_POST_KBN NOT IN('006','027','004','007') OR b.IN_POST_KBN=NULL) and a.PERSONNEL_OUTMODE='003' then 1 else 0 end) as 'P18', ");
        //其他从业人员，终止合同
        sql.append(" SUM(CASE WHEN (b.IN_POST_KBN NOT IN('006','027','004','007') OR b.IN_POST_KBN=NULL) and a.PERSONNEL_OUTMODE='004' then 1 else 0 end) as 'Q18', ");
        //其他从业人员，辞退辞职
        sql.append(" SUM(CASE WHEN (b.IN_POST_KBN NOT IN('006','027','004','007') OR b.IN_POST_KBN=NULL) and a.PERSONNEL_OUTMODE='005' then 1 else 0 end) as 'R18', ");
        //其他从业人员，交流
        sql.append(" SUM(CASE WHEN (b.IN_POST_KBN NOT IN('006','027','004','007') OR b.IN_POST_KBN=NULL) and a.PERSONNEL_OUTMODE='006' then 1 else 0 end) as 'S18', ");
        //其他从业人员，其他
        sql.append(" SUM(CASE WHEN (b.IN_POST_KBN NOT IN('006','027','004','007') OR b.IN_POST_KBN=NULL) and a.PERSONNEL_OUTMODE IN('007','008') OR a.personnel_outmode=NULL then 1 else 0 end) as 'T18', ");
        sql.append("  ");
        //中央，应届毕业生
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='061' and a.PERSONNEL_JOINMODE='177178001' then 1 else 0 end) as 'E19', ");
        //中央，社会人员
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='061' and a.PERSONNEL_JOINMODE='177178002' then 1 else 0 end) as 'F19', ");
        //中央，考试
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='061' and a.PERSONNEL_JOINMODE='177179001' then 1 else 0 end) as 'G19', ");
        //中央，考察
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='061' and a.PERSONNEL_JOINMODE='177179002' then 1 else 0 end) as 'H19', ");
        //中央，考核
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='061' and a.PERSONNEL_JOINMODE='177179003' then 1 else 0 end) as 'I19', ");
        //中央，任命
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='061' and a.PERSONNEL_JOINMODE='002' then 1 else 0 end) as 'J19', ");
        //中央，政策性安置
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='061' and a.PERSONNEL_JOINMODE='003' then 1 else 0 end) as 'K19', ");
        //中央，交流
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='061' and a.PERSONNEL_JOINMODE='004' then 1 else 0 end) as 'L19', ");
        //中央，其他
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='061' and a.PERSONNEL_JOINMODE in('006','005','007') then 1 else 0 end) as 'M19', ");
        //中央，退休
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='061' and a.PERSONNEL_OUTMODE='001' then 1 else 0 end) as 'N19', ");
        //中央，解除合同
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='061' and a.PERSONNEL_OUTMODE='002' then 1 else 0 end) as 'O19', ");
        //中央，开除
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='061' and a.PERSONNEL_OUTMODE='003' then 1 else 0 end) as 'P19', ");
        //中央，终止合同
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='061' and a.PERSONNEL_OUTMODE='004' then 1 else 0 end) as 'Q19', ");
        //中央，辞职辞退
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='061' and a.PERSONNEL_OUTMODE='005' then 1 else 0 end) as 'R19', ");
        //中央，交流
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='061' and a.PERSONNEL_OUTMODE='006' then 1 else 0 end) as 'S19', ");
        //中央，其他
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='061' and a.PERSONNEL_OUTMODE IN('007','008') OR a.personnel_outmode=NULL then 1 else 0 end) as 'T19', ");
        sql.append("  ");
        //省（区、市），应届毕业生
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='062' and a.PERSONNEL_JOINMODE='177178001' then 1 else 0 end) as 'E20', ");
        //省（区、市），社会人员
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='062' and a.PERSONNEL_JOINMODE='177178002' then 1 else 0 end) as 'F20', ");
        //省（区、市），考试
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='062' and a.PERSONNEL_JOINMODE='177179001' then 1 else 0 end) as 'G20', ");
        //省（区、市），考察
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='062' and a.PERSONNEL_JOINMODE='177179002' then 1 else 0 end) as 'H20', ");
        //省（区、市），考核
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='062' and a.PERSONNEL_JOINMODE='177179003' then 1 else 0 end) as 'I20', ");
        //省（区、市），任命
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='062' and a.PERSONNEL_JOINMODE='002' then 1 else 0 end) as 'J20', ");
        //省（区、市），政策性安置
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='062' and a.PERSONNEL_JOINMODE='003' then 1 else 0 end) as 'K20', ");
        //省（区、市），交流
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='062' and a.PERSONNEL_JOINMODE='004' then 1 else 0 end) as 'L20', ");
        //省（区、市），其他
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='062' and a.PERSONNEL_JOINMODE in('006','005','007') then 1 else 0 end) as 'M20', ");
        //省（区、市），退休
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='062' and a.PERSONNEL_OUTMODE='001' then 1 else 0 end) as 'N20', ");
        //省（区、市），解除合同
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='062' and a.PERSONNEL_OUTMODE='002' then 1 else 0 end) as 'O20', ");
        //省（区、市），开除
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='062' and a.PERSONNEL_OUTMODE='003' then 1 else 0 end) as 'P20', ");
        //省（区、市），终止合同
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='062' and a.PERSONNEL_OUTMODE='004' then 1 else 0 end) as 'Q20', ");
        //省（区、市），辞职辞退
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='062' and a.PERSONNEL_OUTMODE='005' then 1 else 0 end) as 'R20', ");
        //省（区、市），交流
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='062' and a.PERSONNEL_OUTMODE='006' then 1 else 0 end) as 'S20', ");
        //省（区、市），其他
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='062' and a.PERSONNEL_OUTMODE IN('007','008') OR a.personnel_outmode=NULL then 1 else 0 end) as 'T20', ");
        sql.append("  ");
        //地（市、州、盟），应届毕业生
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='063' and a.PERSONNEL_JOINMODE='177178001' then 1 else 0 end) as 'E21', ");
        //地（市、州、盟），社会人员
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='063' and a.PERSONNEL_JOINMODE='177178002' then 1 else 0 end) as 'F21', ");
        //地（市、州、盟），考试
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='063' and a.PERSONNEL_JOINMODE='177179001' then 1 else 0 end) as 'G21', ");
        //地（市、州、盟），考察
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='063' and a.PERSONNEL_JOINMODE='177179002' then 1 else 0 end) as 'H21', ");
        //地（市、州、盟），考核
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='063' and a.PERSONNEL_JOINMODE='177179003' then 1 else 0 end) as 'I21', ");
        //地（市、州、盟），任命
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='063' and a.PERSONNEL_JOINMODE='002' then 1 else 0 end) as 'J21', ");
        //地（市、州、盟），政策性安置
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='063' and a.PERSONNEL_JOINMODE='003' then 1 else 0 end) as 'K21', ");
        //地（市、州、盟），交流
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='063' and a.PERSONNEL_JOINMODE='004' then 1 else 0 end) as 'L21', ");
        //地（市、州、盟），其他
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='063' and a.PERSONNEL_JOINMODE in('006','005','007') then 1 else 0 end) as 'M21', ");
        //地（市、州、盟），退休
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='063' and a.PERSONNEL_OUTMODE='001' then 1 else 0 end) as 'N21', ");
        //地（市、州、盟），解除合同
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='063' and a.PERSONNEL_OUTMODE='002' then 1 else 0 end) as 'O21', ");
        //地（市、州、盟），开除
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='063' and a.PERSONNEL_OUTMODE='003' then 1 else 0 end) as 'P21', ");
        //地（市、州、盟），终止合同
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='063' and a.PERSONNEL_OUTMODE='004' then 1 else 0 end) as 'Q21', ");
        //地（市、州、盟），辞职辞退
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='063' and a.PERSONNEL_OUTMODE='005' then 1 else 0 end) as 'R21', ");
        //地（市、州、盟），交流
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='063' and a.PERSONNEL_OUTMODE='006' then 1 else 0 end) as 'S21', ");
        //地（市、州、盟），其他
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='063' and a.PERSONNEL_OUTMODE IN('007','008') OR a.personnel_outmode=NULL then 1 else 0 end) as 'T21', ");
        sql.append("  ");
        //县（市、区、旗），应届毕业生
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='064' and a.PERSONNEL_JOINMODE='177178001' then 1 else 0 end) as 'E22', ");
        //县（市、区、旗），社会人员
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='064' and a.PERSONNEL_JOINMODE='177178002' then 1 else 0 end) as 'F22', ");
        //县（市、区、旗），考试
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='064' and a.PERSONNEL_JOINMODE='177179001' then 1 else 0 end) as 'G22', ");
        //县（市、区、旗），考察
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='064' and a.PERSONNEL_JOINMODE='177179002' then 1 else 0 end) as 'H22', ");
        //县（市、区、旗），考核
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='064' and a.PERSONNEL_JOINMODE='177179003' then 1 else 0 end) as 'I22', ");
        //县（市、区、旗），任命
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='064' and a.PERSONNEL_JOINMODE='002' then 1 else 0 end) as 'J22', ");
        //县（市、区、旗），政策性安置
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='064' and a.PERSONNEL_JOINMODE='003' then 1 else 0 end) as 'K22', ");
        //县（市、区、旗），交流
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='064' and a.PERSONNEL_JOINMODE='004' then 1 else 0 end) as 'L22', ");
        //县（市、区、旗），其他
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='064' and a.PERSONNEL_JOINMODE in('006','005','007') then 1 else 0 end) as 'M22', ");
        //县（市、区、旗），退休
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='064' and a.PERSONNEL_OUTMODE='001' then 1 else 0 end) as 'N22', ");
        //县（市、区、旗），解除合同
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='064' and a.PERSONNEL_OUTMODE='002' then 1 else 0 end) as 'O22', ");
        //县（市、区、旗），开除
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='064' and a.PERSONNEL_OUTMODE='003' then 1 else 0 end) as 'P22', ");
        //县（市、区、旗），终止合同
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='064' and a.PERSONNEL_OUTMODE='004' then 1 else 0 end) as 'Q22', ");
        //县（市、区、旗），辞职辞退
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='064' and a.PERSONNEL_OUTMODE='005' then 1 else 0 end) as 'R22', ");
        //县（市、区、旗），交流
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='064' and a.PERSONNEL_OUTMODE='006' then 1 else 0 end) as 'S22', ");
        //县（市、区、旗），其他
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='064' and a.PERSONNEL_OUTMODE IN('007','008') OR a.personnel_outmode=NULL then 1 else 0 end) as 'T22', ");
        sql.append("  ");
        //乡（镇），应届毕业生
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='998' and a.PERSONNEL_JOINMODE='177178001' then 1 else 0 end) as 'E23', ");
        //乡（镇），社会人员
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='998' and a.PERSONNEL_JOINMODE='177178002' then 1 else 0 end) as 'F23', ");
        //乡（镇），考试
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='998' and a.PERSONNEL_JOINMODE='177179001' then 1 else 0 end) as 'G23', ");
        //乡（镇），考察
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='998' and a.PERSONNEL_JOINMODE='177179002' then 1 else 0 end) as 'H23', ");
        //乡（镇），考核
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='998' and a.PERSONNEL_JOINMODE='177179003' then 1 else 0 end) as 'I23', ");
        //乡（镇），任命
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='998' and a.PERSONNEL_JOINMODE='002' then 1 else 0 end) as 'J23', ");
        //乡（镇），政策性安置
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='998' and a.PERSONNEL_JOINMODE='003' then 1 else 0 end) as 'K23', ");
        //乡（镇），交流
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='998' and a.PERSONNEL_JOINMODE='004' then 1 else 0 end) as 'L23', ");
        //乡（镇），其他
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='998' and a.PERSONNEL_JOINMODE in('006','005','007') then 1 else 0 end) as 'M23', ");
        //乡（镇），退休
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='998' and a.PERSONNEL_OUTMODE='001' then 1 else 0 end) as 'N23', ");
        //乡（镇），解除合同
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='998' and a.PERSONNEL_OUTMODE='002' then 1 else 0 end) as 'O23', ");
        //乡（镇），开除
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='998' and a.PERSONNEL_OUTMODE='003' then 1 else 0 end) as 'P23', ");
        //乡（镇），终止合同
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='998' and a.PERSONNEL_OUTMODE='004' then 1 else 0 end) as 'Q23', ");
        //乡（镇），辞职辞退
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='998' and a.PERSONNEL_OUTMODE='005' then 1 else 0 end) as 'R23', ");
        //乡（镇），交流
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='998' and a.PERSONNEL_OUTMODE='006' then 1 else 0 end) as 'S23', ");
        //乡（镇），其他
        sql.append(" SUM(CASE WHEN c.UNIT_MANAGE_SCALE='998' and a.PERSONNEL_OUTMODE IN('007','008') OR a.personnel_outmode=NULL then 1 else 0 end) as 'T23' ,");
        sql.append(" '"+rlgl200101BeanNew.getSel_year()+"' as 'J7' ");    
        sql.append("  ");
        sql.append(" FROM ");
        sql.append(" m_tb12_personnel a ");
        sql.append(" LEFT JOIN ");
        sql.append(" m_tb09_irin b ");
        sql.append(" ON  ");
        sql.append(" a.PERSONNEL_UNIT=b.UNIT_NO ");
        sql.append(" LEFT JOIN ");
        sql.append(" m_tb04_unit c ");
        sql.append(" ON ");
        sql.append(" c.UNIT_NO=a.PERSONNEL_UNIT ");
        sql.append(" where ");
        //年度
        sql.append(" substr(a.LOGIN_DATE,1,4)= "+rlgl200101BeanNew.getSel_year());
        sql.append(" and ");
        //管辖单位
        sql.append(" (A.PERSONNEL_UNIT in ");
        sql.append(" (SELECT unit_no FROM m_tb04_unit WHERE ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no()+"' AND DEL_KBN = 0 AND UNIT_STATUS = '1') ");
        sql.append(" or A.PERSONNEL_UNIT LIKE CONCAT('', '"+rlgl200101BeanNew.getUnit_no()+"', '%') ");
        sql.append(" ) ");
        sql.append(" and ");
        sql.append(" substr(a.LOGIN_DATE,1,4)<='"+rlgl200101BeanNew.getSel_year()+"' ");
        return sql.toString();
    }
    /**
     * 事业单位工作人员基本情况Sql
     */
	public String EditUnitBaseInfoSql02(Rlgl200101Bean rlgl200101BeanNew) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select  ");
		// 管理人员，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F13', ");
		// 管理人员，女，少数名族
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_ETHNIC<>'001' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'G13', ");
		// 管理人员，女，中共党员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'H13', ");
		// 管理人员，少数民族，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_ETHNIC<>'001' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F14', ");
		// 管理人员，少数民族，
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G14', ");
		// 管理人员，少数民族，中共党员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_ETHNIC<>'001' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H14', ");
		// 管理人员，一级职员（部级正职），女
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='001' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F15', ");
		// 管理人员，二级职员（部级副职），女
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='002' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F16', ");
		// 管理人员，三级职员（厅级正职），女
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='003' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F17', ");
		// 管理人员，四级职员（厅级副职），女
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='004' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F18', ");
		// 管理人员，五级职员（处级正职），女
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='005' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F19', ");
		// 管理人员，六级职员（处级副职），女
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='006' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F20', ");
		// 管理人员，七级职员（科技正职），女
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='007' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F21', ");
		// 管理人员，八级职员（科级副职），女
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='008' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F22', ");
		// 管理人员，九级职员（科员），女
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='009' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F23', ");
		// 管理人员，十级职员（办事员），女
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='010' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F24', ");
		// 管理人员，其他登记人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='011' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F25', ");
		// 管理人员，一级职员（部级正职），少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='001' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G15', ");
		// 管理人员，二级职员（部级副职），少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='002' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G16', ");
		// 管理人员，三级职员（厅级正职），少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='003' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G17', ");
		// 管理人员，四级职员（厅级副职），少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='004' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G18', ");
		// 管理人员，五级职员（处级正职），少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='005' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G19', ");
		// 管理人员，六级职员（处级副职），少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='006' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G20', ");
		// 管理人员，七级职员（科技正职），少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='007' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G21', ");
		// 管理人员，八级职员（科级副职），少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='008' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G22', ");
		// 管理人员，九级职员（科员），少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='009' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G23', ");
		// 管理人员，十级职员（办事员），少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='010' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G24', ");
		// 管理人员，其他等级人员，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='011' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G25', ");
		// 管理人员，一级职员（部级正职），中共党员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='001' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H15', ");
		// 管理人员，二级职员（部级副职），中共党员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='002' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H16', ");
		// 管理人员，三级职员（厅级正职），中共党员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='003' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H17', ");
		// 管理人员，四级职员（厅级副职），中共党员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='004' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H18', ");
		// 管理人员，五级职员（处级正职），中共党员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='005' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H19', ");
		// 管理人员，六级职员（处级副职），中共党员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='006' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H20', ");
		// 管理人员，七级职员（科技正职），中共党员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='007' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H21', ");
		// 管理人员，八级职员（科级副职），中共党员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='008' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H22', ");
		// 管理人员，九级职员（科员），中共党员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='009' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H23', ");
		// 管理人员，十级职员（办事员），中共党员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='010' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H24', ");
		// 管理人员，其他等级人员，中共党员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='011' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H25', ");
		// 管理人员，女，博士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_GENDER='002' AND edu='006' then 1 else 0 end) as 'I13', ");
		// 管理人员，少数民族，博士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_ETHNIC<>'001' AND edu='006' then 1 else 0 end) as 'I14', ");
		// 管理人员，女，硕士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_GENDER='002' AND edu='005' then 1 else 0 end) as 'J13', ");
		// 管理人员，少数民族，硕士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_ETHNIC<>'001' AND edu='005' then 1 else 0 end) as 'J14', ");
		// 管理人员，女，研究生
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_GENDER='002' AND edu IN ('005','006') then 1 else 0 end) as 'L13', ");
		// 管理人员，少数民族，研究生
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_ETHNIC<>'001' AND edu IN ('005','006') then 1 else 0 end) as 'L14', ");
		// 管理人员，女，大学本科
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_GENDER='002' AND edu='004' then 1 else 0 end) as 'M13', ");
		// 管理人员，少数民族，大学本科
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_ETHNIC<>'001' AND edu='004' then 1 else 0 end) as 'M14', ");
		// 管理人员，女，大学专科
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_GENDER='002' AND edu='003' then 1 else 0 end) as 'N13', ");
		// 管理人员，少数民族，大学专科
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_ETHNIC<>'001' AND edu='003' then 1 else 0 end) as 'N14', ");
		// 管理人员，女，中专
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_GENDER='002' AND edu='002' then 1 else 0 end) as 'O13', ");
		// 管理人员，少数民族，中专
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_ETHNIC<>'001' AND edu='002' then 1 else 0 end) as 'O14', ");
		// 管理人员，女，高中及以下
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_GENDER='002' AND edu='001' then 1 else 0 end) as 'P13', ");
		// 管理人员，少数民族，高中及以下
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_ETHNIC<>'001' AND edu='001' then 1 else 0 end) as 'P14', ");
		// 管理人员，一级职员（部级正职），博士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='001' AND edu='006' then 1 else 0 end) as 'I15', ");
		// 管理人员，二级职员（部级副职），博士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='002' AND edu='006' then 1 else 0 end) as 'I16', ");
		// 管理人员，三级职员（厅级正职），博士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='003' AND edu='006' then 1 else 0 end) as 'I17', ");
		// 管理人员，四级职员（厅级副职），博士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='004' AND edu='006' then 1 else 0 end) as 'I18', ");
		// 管理人员，五级职员（处级正职），博士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='005' AND edu='006' then 1 else 0 end) as 'I19', ");
		// 管理人员，六级职员（处级副职），博士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='006' AND edu='006' then 1 else 0 end) as 'I20', ");
		// 管理人员，七级职员（科技正职），博士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='007' AND edu='006' then 1 else 0 end) as 'I21', ");
		// 管理人员，八级职员（科级副职），博士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='008' AND edu='006' then 1 else 0 end) as 'I22', ");
		// 管理人员，九级职员（科员），博士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='009' AND edu='006' then 1 else 0 end) as 'I23', ");
		// 管理人员，十级职员（办事员），博士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='010' AND edu='006' then 1 else 0 end) as 'I24', ");
		// 管理人员，其他等级人员，博士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='011' AND edu='006' then 1 else 0 end) as 'I25', ");
		// 管理人员，一级职员（部级正职），硕士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='001' AND edu='005' then 1 else 0 end) as 'J15', ");
		// 管理人员，二级职员（部级副职），硕士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='002' AND edu='005' then 1 else 0 end) as 'J16', ");
		// 管理人员，三级职员（厅级正职），硕士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='003' AND edu='005' then 1 else 0 end) as 'J17', ");
		// 管理人员，四级职员（厅级副职），硕士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='004' AND edu='005' then 1 else 0 end) as 'J18', ");
		// 管理人员，五级职员（处级正职），硕士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='005' AND edu='005' then 1 else 0 end) as 'J19', ");
		// 管理人员，六级职员（处级副职），硕士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='006' AND edu='005' then 1 else 0 end) as 'J20', ");
		// 管理人员，七级职员（科技正职），硕士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='007' AND edu='005' then 1 else 0 end) as 'J21', ");
		// 管理人员，八级职员（科级副职），硕士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='008' AND edu='005' then 1 else 0 end) as 'J22', ");
		// 管理人员，九级职员（科员），硕士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='009' AND edu='005' then 1 else 0 end) as 'J23', ");
		// 管理人员，十级职员（办事员），硕士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='010' AND edu='005' then 1 else 0 end) as 'J24', ");
		// 管理人员，其他等级人员，硕士
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='011' AND edu='005' then 1 else 0 end) as 'J25', ");
		// 管理人员，研究生，一级职员（部级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='001' AND edu IN ('005','006') then 1 else 0 end) as 'L15', ");
		// 管理人员，研究生，二级职员（部级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='002' AND edu IN ('005','006') then 1 else 0 end) as 'L16', ");
		// 管理人员，研究生，三级职员（厅级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='003' AND edu IN ('005','006') then 1 else 0 end) as 'L17', ");
		// 管理人员，研究生，四级职员（厅级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='004' AND edu IN ('005','006') then 1 else 0 end) as 'L18', ");
		// 管理人员，研究生，五级职员（处级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='005' AND edu IN ('005','006') then 1 else 0 end) as 'L19', ");
		// 管理人员，研究生，六级职员（处级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='006' AND edu IN ('005','006') then 1 else 0 end) as 'L20', ");
		// 管理人员，研究生，七级职员（科技正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='007' AND edu IN ('005','006') then 1 else 0 end) as 'L21', ");
		// 管理人员，研究生，八级职员（科级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='008' AND edu IN ('005','006') then 1 else 0 end) as 'L22', ");
		// 管理人员，研究生，九级职员（科员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='009' AND edu IN ('005','006') then 1 else 0 end) as 'L23', ");
		// 管理人员，研究生，十级职员（办事员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='010' AND edu IN ('005','006') then 1 else 0 end) as 'L24', ");
		// 管理人员，研究生，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='011' AND edu IN ('005','006') then 1 else 0 end) as 'L25', ");
		// 管理人员，大学本科，一级职员（部级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='001' AND edu='004' then 1 else 0 end) as 'M15', ");
		// 管理人员，大学本科，二级职员（部级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='002' AND edu='004' then 1 else 0 end) as 'M16', ");
		// 管理人员，大学本科，三级职员（厅级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='003' AND edu='004' then 1 else 0 end) as 'M17', ");
		// 管理人员，大学本科，四级职员（厅级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='004' AND edu='004' then 1 else 0 end) as 'M18', ");
		// 管理人员，大学本科，五级职员（处级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='005' AND edu='004' then 1 else 0 end) as 'M19', ");
		// 管理人员，大学本科，六级职员（处级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='006' AND edu='004' then 1 else 0 end) as 'M20', ");
		// 管理人员，大学本科，七级职员（科技正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='007' AND edu='004' then 1 else 0 end) as 'M21', ");
		// 管理人员，大学本科，八级职员（科级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='008' AND edu='004' then 1 else 0 end) as 'M22', ");
		// 管理人员，大学本科，九级职员（科员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='009' AND edu='004' then 1 else 0 end) as 'M23', ");
		// 管理人员，大学本科，十级职员（办事员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='010' AND edu='004' then 1 else 0 end) as 'M24', ");
		// 管理人员，大学本科，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='011' AND edu='004' then 1 else 0 end) as 'M25', ");
		// 管理人员，大学专科，一级职员（部级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='001' AND edu='003' then 1 else 0 end) as 'N15', ");
		// 管理人员，大学专科，二级职员（部级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='002' AND edu='003' then 1 else 0 end) as 'N16', ");
		// 管理人员，大学专科，三级职员（厅级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='003' AND edu='003' then 1 else 0 end) as 'N17', ");
		// 管理人员，大学专科，四级职员（厅级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='004' AND edu='003' then 1 else 0 end) as 'N18', ");
		// 管理人员，大学专科，五级职员（处级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='005' AND edu='003' then 1 else 0 end) as 'N19', ");
		// 管理人员，大学专科，六级职员（处级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='006' AND edu='003' then 1 else 0 end) as 'N20', ");
		// 管理人员，大学专科，七级职员（科级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='007' AND edu='003' then 1 else 0 end) as 'N21', ");
		// 管理人员，大学专科，八级职员（科级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='008' AND edu='003' then 1 else 0 end) as 'N22', ");
		// 管理人员，大学专科，九级职员（科员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='009' AND edu='003' then 1 else 0 end) as 'N23', ");
		// 管理人员，大学专科，十级职员（办事员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='010' AND edu='003' then 1 else 0 end) as 'N24', ");
		// 管理人员，大学专科，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='011' AND edu='003' then 1 else 0 end) as 'N25', ");
		// 管理人员，中专，一级职员（部级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='001' AND edu='002' then 1 else 0 end) as 'O15', ");
		// 管理人员，中专，二级职员（部级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='002' AND edu='002' then 1 else 0 end) as 'O16', ");
		// 管理人员，中专，三级职员（厅级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='003' AND edu='002' then 1 else 0 end) as 'O17', ");
		// 管理人员，中专，四级职员（厅级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='004' AND edu='002' then 1 else 0 end) as 'O18', ");
		// 管理人员，中专，五级职员（处级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='005' AND edu='002' then 1 else 0 end) as 'O19', ");
		// 管理人员，中专，六级职员（处级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='006' AND edu='002' then 1 else 0 end) as 'O20', ");
		// 管理人员，中专，七级职员（科级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='007' AND edu='002' then 1 else 0 end) as 'O21', ");
		// 管理人员，中专，八级职员（科级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='008' AND edu='002' then 1 else 0 end) as 'O22', ");
		// 管理人员，中专，九级职员（科员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='009' AND edu='002' then 1 else 0 end) as 'O23', ");
		// 管理人员，中专，十级职员（办事员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='010' AND edu='002' then 1 else 0 end) as 'O24', ");
		// 管理人员，中专，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='011' AND edu='002' then 1 else 0 end) as 'O25', ");
		// 管理人员，高中及以下，一级职员（部级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='001' AND edu='001' then 1 else 0 end) as 'P15', ");
		// 管理人员，高中及以下，二级职员（部级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='002' AND edu='001' then 1 else 0 end) as 'P16', ");
		// 管理人员，高中及以下，三级职员（厅级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='003' AND edu='001' then 1 else 0 end) as 'P17', ");
		// 管理人员，高中及以下，四级职员（厅级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='004' AND edu='001' then 1 else 0 end) as 'P18', ");
		// 管理人员，高中及以下，五级职员（处级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='005' AND edu='001' then 1 else 0 end) as 'P19', ");
		// 管理人员，高中及以下，六级职员（处级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='006' AND edu='001' then 1 else 0 end) as 'P20', ");
		// 管理人员，高中及以下，七级职员（科级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='007' AND edu='001' then 1 else 0 end) as 'P21', ");
		// 管理人员，高中及以下，八级职员（科级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='008' AND edu='001' then 1 else 0 end) as 'P22', ");
		// 管理人员，高中及以下，九级职员（科员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='009' AND edu='001' then 1 else 0 end) as 'P23', ");
		// 管理人员，高中及以下，十级职员（办事员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='010' AND edu='001' then 1 else 0 end) as 'P24', ");
		// 管理人员，高中及以下，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='011' AND edu='001' then 1 else 0 end) as 'P25', ");
		// 管理人员，35岁及以下，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_GENDER='002' AND c.AGEEREA='001' then 1 else 0 end) as 'Q13', ");
		// 管理人员，35岁及以下，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_ETHNIC<>'001' AND c.AGEEREA='001' then 1 else 0 end) as 'Q14', ");
		// 管理人员，35岁及以下，一级职员（部级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='001' AND c.AGEEREA='001' then 1 else 0 end) as 'Q15', ");
		// 管理人员，35岁及以下，二级职员（部级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='002' AND c.AGEEREA='001' then 1 else 0 end) as 'Q16', ");
		// 管理人员，35岁及以下，三级职员（厅级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='003' AND c.AGEEREA='001' then 1 else 0 end) as 'Q17', ");
		// 管理人员，35岁及以下，四级职员（厅级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='004' AND c.AGEEREA='001' then 1 else 0 end) as 'Q18', ");
		// 管理人员，35岁及以下，五级职员（处级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='005' AND c.AGEEREA='001' then 1 else 0 end) as 'Q19', ");
		// 管理人员，35岁及以下，六级职员（处级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='006' AND c.AGEEREA='001' then 1 else 0 end) as 'Q20', ");
		// 管理人员，35岁及以下，七级职员（科级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='007' AND c.AGEEREA='001' then 1 else 0 end) as 'Q21', ");
		// 管理人员，35岁及以下，八级职员（科级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='008' AND c.AGEEREA='001' then 1 else 0 end) as 'Q22', ");
		// 管理人员，35岁及以下，九级职员（科员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='009' AND c.AGEEREA='001' then 1 else 0 end) as 'Q23', ");
		// 管理人员，35岁及以下，十级职员（办事员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='010' AND c.AGEEREA='001' then 1 else 0 end) as 'Q24', ");
		// 管理人员，35岁及以下，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='011' AND c.AGEEREA='001' then 1 else 0 end) as 'Q25', ");
		// 管理人员，36岁至40岁，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_GENDER='002' AND c.AGEEREA='002' then 1 else 0 end) as 'R13', ");
		// 管理人员，36岁至40岁，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_ETHNIC<>'001' AND c.AGEEREA='002' then 1 else 0 end) as 'R14', ");
		// 管理人员，36岁至40岁，一级职员（部级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='001' AND c.AGEEREA='002' then 1 else 0 end) as 'R15', ");
		// 管理人员，36岁至40岁，二级职员（部级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='002' AND c.AGEEREA='002' then 1 else 0 end) as 'R16', ");
		// 管理人员，36岁至40岁，三级职员（厅级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='003' AND c.AGEEREA='002' then 1 else 0 end) as 'R17', ");
		// 管理人员，36岁至40岁，四级职员（厅级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='004' AND c.AGEEREA='002' then 1 else 0 end) as 'R18', ");
		// 管理人员，36岁至40岁，五级职员（处级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='005' AND c.AGEEREA='002' then 1 else 0 end) as 'R19', ");
		// 管理人员，36岁至40岁，六级职员（处级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='006' AND c.AGEEREA='002' then 1 else 0 end) as 'R20', ");
		// 管理人员，36岁至40岁，七级职员（科级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='007' AND c.AGEEREA='002' then 1 else 0 end) as 'R21', ");
		// 管理人员，36岁至40岁，八级职员（科级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='008' AND c.AGEEREA='002' then 1 else 0 end) as 'R22', ");
		// 管理人员，36岁至40岁，九级职员（科员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='009' AND c.AGEEREA='002' then 1 else 0 end) as 'R23', ");
		// 管理人员，36岁至40岁，十级职员（办事员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='010' AND c.AGEEREA='002' then 1 else 0 end) as 'R24', ");
		// 管理人员，36岁至40岁，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='011' AND c.AGEEREA='002' then 1 else 0 end) as 'R25', ");
		// 管理人员，41岁至45岁，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_GENDER='002' AND c.AGEEREA='003' then 1 else 0 end) as 'S13', ");
		// 管理人员，41岁至45岁，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_ETHNIC<>'001' AND c.AGEEREA='003' then 1 else 0 end) as 'S14', ");
		// 管理人员，41岁至45岁，一级职员（部级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='001' AND c.AGEEREA='003' then 1 else 0 end) as 'S15', ");
		// 管理人员，41岁至45岁，二级职员（部级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='002' AND c.AGEEREA='003' then 1 else 0 end) as 'S16', ");
		// 管理人员，41岁至45岁，三级职员（厅级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='003' AND c.AGEEREA='003' then 1 else 0 end) as 'S17', ");
		// 管理人员，41岁至45岁，四级职员（厅级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='004' AND c.AGEEREA='003' then 1 else 0 end) as 'S18', ");
		// 管理人员，41岁至45岁，五级职员（处级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='005' AND c.AGEEREA='003' then 1 else 0 end) as 'S19', ");
		// 管理人员，41岁至45岁，六级职员（处级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='006' AND c.AGEEREA='003' then 1 else 0 end) as 'S20', ");
		// 管理人员，41岁至45岁，七级职员（科级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='007' AND c.AGEEREA='003' then 1 else 0 end) as 'S21', ");
		// 管理人员，41岁至45岁，八级职员（科级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='008' AND c.AGEEREA='003' then 1 else 0 end) as 'S22', ");
		// 管理人员，41岁至45岁，九级职员（科员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='009' AND c.AGEEREA='003' then 1 else 0 end) as 'S23', ");
		// 管理人员，41岁至45岁，十级职员（办事员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='010' AND c.AGEEREA='003' then 1 else 0 end) as 'S24', ");
		// 管理人员，41岁至45岁，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='011' AND c.AGEEREA='003' then 1 else 0 end) as 'S25', ");
		// 管理人员，46岁至50岁，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_GENDER='002' AND c.AGEEREA='004' then 1 else 0 end) as 'T13', ");
		// 管理人员，46岁至50岁，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_ETHNIC<>'001' AND c.AGEEREA='004' then 1 else 0 end) as 'T14', ");
		// 管理人员，46岁至50岁，一级职员（部级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='001' AND c.AGEEREA='004' then 1 else 0 end) as 'T15', ");
		//管理人员，46岁至50岁，二级职员（部级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='002' AND c.AGEEREA='004' then 1 else 0 end) as 'T16', ");
		//管理人员，46岁至50岁，三级职员（厅级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='003' AND c.AGEEREA='004' then 1 else 0 end) as 'T17', ");
		//管理人员，46岁至50岁，四级职员（厅级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='004' AND c.AGEEREA='004' then 1 else 0 end) as 'T18', ");
		//管理人员，46岁至50岁，五级职员（处级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='005' AND c.AGEEREA='004' then 1 else 0 end) as 'T19', ");
		//管理人员，46岁至50岁，六级职员（处级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='006' AND c.AGEEREA='004' then 1 else 0 end) as 'T20', ");
		//管理人员，46岁至50岁，七级职员（科级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='007' AND c.AGEEREA='004' then 1 else 0 end) as 'T21', ");
		//管理人员，46岁至50岁，八级职员（科级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='008' AND c.AGEEREA='004' then 1 else 0 end) as 'T22', ");
		//管理人员，46岁至50岁，九级职员（科员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='009' AND c.AGEEREA='004' then 1 else 0 end) as 'T23', ");
		//管理人员，46岁至50岁，十级职员（办事员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='010' AND c.AGEEREA='004' then 1 else 0 end) as 'T24', ");
		//管理人员，46岁至50岁，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='011' AND c.AGEEREA='004' then 1 else 0 end) as 'T25', ");
		//管理人员，51岁至54岁，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_GENDER='002' AND c.AGEEREA='005' then 1 else 0 end) as 'U13', ");
		//管理人员，51岁至54岁，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_ETHNIC<>'001' AND c.AGEEREA='005' then 1 else 0 end) as 'U14', ");
		//管理人员，51岁至54岁，一级职员（部级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='001' AND c.AGEEREA='005' then 1 else 0 end) as 'U15', ");
		//管理人员，51岁至54岁，二级职员（部级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='002' AND c.AGEEREA='005' then 1 else 0 end) as 'U16', ");
		//管理人员，51岁至54岁，三级职员（厅级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='003' AND c.AGEEREA='005' then 1 else 0 end) as 'U17', ");
		//管理人员，51岁至54岁，四级职员（厅级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='004' AND c.AGEEREA='005' then 1 else 0 end) as 'U18', ");
		//管理人员，51岁至54岁，五级职员（处级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='005' AND c.AGEEREA='005' then 1 else 0 end) as 'U19', ");
		//管理人员，51岁至54岁，六级职员（处级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='006' AND c.AGEEREA='005' then 1 else 0 end) as 'U20', ");
		//管理人员，51岁至54岁，七级职员（科级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='007' AND c.AGEEREA='005' then 1 else 0 end) as 'U21', ");
		//管理人员，51岁至54岁，八级职员（科级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='008' AND c.AGEEREA='005' then 1 else 0 end) as 'U22', ");
		//管理人员，51岁至54岁，九级职员（科员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='009' AND c.AGEEREA='005' then 1 else 0 end) as 'U23', ");
		//管理人员，51岁至54岁，十级职员（办事员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='010' AND c.AGEEREA='005' then 1 else 0 end) as 'U24', ");
		//管理人员，51岁至54岁，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='011' AND c.AGEEREA='005' then 1 else 0 end) as 'U25', ");
		//管理人员，55岁至59岁，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_GENDER='002' AND c.AGEEREA='006' then 1 else 0 end) as 'V13', ");
		//管理人员，55岁至59岁，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_ETHNIC<>'001' AND c.AGEEREA='006' then 1 else 0 end) as 'V14', ");
		//管理人员，55岁至59岁，一级职员（部级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='001' AND c.AGEEREA='006' then 1 else 0 end) as 'V15', ");
		//管理人员，55岁至59岁，二级职员（部级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='002' AND c.AGEEREA='006' then 1 else 0 end) as 'V16', ");
		//管理人员，55岁至59岁，三级职员（厅级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='003' AND c.AGEEREA='006' then 1 else 0 end) as 'V17', ");
		//管理人员，55岁至59岁，四级职员（厅级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='004' AND c.AGEEREA='006' then 1 else 0 end) as 'V18', ");
		//管理人员，55岁至59岁，五级职员（处级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='005' AND c.AGEEREA='006' then 1 else 0 end) as 'V19', ");
		//管理人员，55岁至59岁，六级职员（处级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='006' AND c.AGEEREA='006' then 1 else 0 end) as 'V20', ");
		//管理人员，55岁至59岁，七级职员（科级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='007' AND c.AGEEREA='006' then 1 else 0 end) as 'V21', ");
		//管理人员，55岁至59岁，八级职员（科级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='008' AND c.AGEEREA='006' then 1 else 0 end) as 'V22', ");
		//管理人员，55岁至59岁，九级职员（科员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='009' AND c.AGEEREA='006' then 1 else 0 end) as 'V23', ");
		//管理人员，55岁至59岁，十级职员（办事员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='010' AND c.AGEEREA='006' then 1 else 0 end) as 'V24', ");
		//管理人员，55岁至59岁，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='011' AND c.AGEEREA='006' then 1 else 0 end) as 'V25', ");
		//管理人员，60岁及以上，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_GENDER='002' AND c.AGEEREA='007' then 1 else 0 end) as 'W13', ");
		//管理人员，60岁及以上，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND PERSONNEL_ETHNIC<>'001' AND c.AGEEREA='007' then 1 else 0 end) as 'W14', ");
		//管理人员，60岁及以上，一级职员（部级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='001' AND c.AGEEREA='007' then 1 else 0 end) as 'W15', ");
		//管理人员，60岁及以上，二级职员（部级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='002' AND c.AGEEREA='007' then 1 else 0 end) as 'W16', ");
		//管理人员，60岁及以上，三级职员（厅级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='003' AND c.AGEEREA='007' then 1 else 0 end) as 'W17', ");
		//管理人员，60岁及以上，四级职员（厅级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='004' AND c.AGEEREA='007' then 1 else 0 end) as 'W18', ");
		//管理人员，60岁及以上，五级职员（处级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='005' AND c.AGEEREA='007' then 1 else 0 end) as 'W19', ");
		//管理人员，60岁及以上，六级职员（处级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='006' AND c.AGEEREA='007' then 1 else 0 end) as 'W20', ");
		//管理人员，60岁及以上，七级职员（科级正职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='007' AND c.AGEEREA='007' then 1 else 0 end) as 'W21', ");
		//管理人员，60岁及以上，八级职员（科级副职）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='008' AND c.AGEEREA='007' then 1 else 0 end) as 'W22', ");
		//管理人员，60岁及以上，九级职员（科员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='009' AND c.AGEEREA='007' then 1 else 0 end) as 'W23', ");
		//管理人员，60岁及以上，十级职员（办事员）
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='010' AND c.AGEEREA='007' then 1 else 0 end) as 'W24', ");
		//管理人员，60岁及以上，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='011' AND c.AGEEREA='007' then 1 else 0 end) as 'W25', ");
		//专业技术人员，女，专业技术人员在管理岗位的
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F27', ");
		//专业技术人员，女，具有执业资格的
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND (c.CERTIFICATE_NO IS NOT NULL AND c.CERTIFICATE_NO <> '') then 1 else 0 end) as 'F28', ");
		//专业技术人员，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F29', ");
		//专业技术人员，女，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'F30', ");
		//专业技术人员，少数民族，专业技术人员其中在管理岗位工作的
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G27', ");
		//专业技术人员，少数民族，具有职业资格的
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND (c.CERTIFICATE_NO IS NOT NULL AND c.CERTIFICATE_NO <> '') then 1 else 0 end) as 'G28', ");
		//专业技术人员，少数民族，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G29', ");
		//专业技术人员，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G30', ");
		//专业技术人员，中共党员，专业技术人员其中在管理岗位工作的
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H27', ");
		//专业技术人员，中共党员，具有职业资格的
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND (c.CERTIFICATE_NO IS NOT NULL AND c.CERTIFICATE_NO <> '') then 1 else 0 end) as 'H28', ");
		//专业技术人员，中共党员，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H29', ");
		//专业技术人员，中共党员，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H30', ");
		//专业技术人员，博士，专业技术人员其中在管理岗位的
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND edu='006' then 1 else 0 end) as 'I27', ");
		//专业技术人员，博士，具有职业资格的
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='006' AND (c.CERTIFICATE_NO IS NOT NULL AND c.CERTIFICATE_NO <> '') then 1 else 0 end) as 'I28', ");
		//专业技术人员，博士，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND edu='006' then 1 else 0 end) as 'I29', ");
		//专业技术人员，博士，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND edu='006' then 1 else 0 end) as 'I30', ");
		//专业技术人员，硕士，专业技术人员其中在管理岗位工作的
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND edu='005' then 1 else 0 end) as 'J27', ");
		//专业技术人员，硕士，具有职业资格的
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='005' AND (c.CERTIFICATE_NO IS NOT NULL AND c.CERTIFICATE_NO <> '') then 1 else 0 end) as 'J28', ");
		//专业技术人员，硕士，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND edu='005' then 1 else 0 end) as 'J29', ");
		//专业技术人员，硕士，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND edu='005' then 1 else 0 end) as 'J30', ");
		//专业技术人员，研究生，专业技术人员其中在管理岗位工作的
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND edu IN ('005','006') then 1 else 0 end) as 'L27', ");
		//专业技术人员，研究生，具有职业资格的
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu IN ('005','006') AND (c.CERTIFICATE_NO IS NOT NULL AND c.CERTIFICATE_NO <> '') then 1 else 0 end) as 'L28', ");
		//专业技术人员，研究生，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND edu IN ('005','006') then 1 else 0 end) as 'L29', ");
		//专业技术人员，研究生，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND edu IN ('005','006') then 1 else 0 end) as 'L30', ");
		//专业技术人员，大学本科，专业技术人员其中在管理岗位工作的
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND edu='004' then 1 else 0 end) as 'M27', ");
		//专业技术人员，大学本科，具有职业资格的
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='004' AND (c.CERTIFICATE_NO IS NOT NULL AND c.CERTIFICATE_NO <> '') then 1 else 0 end) as 'M28', ");
		//专业技术人员，大学本科，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND edu='004' then 1 else 0 end) as 'M29', ");
		//专业技术人员，大学本科，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND edu='004' then 1 else 0 end) as 'M30', ");
		//专业技术人员，大学专科，专业技术人员其中在管理岗位工作的
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND edu='003' then 1 else 0 end) as 'N27', ");
		//专业技术人员，大学专科，具有职业资格的
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='003' AND (c.CERTIFICATE_NO IS NOT NULL AND c.CERTIFICATE_NO <> '') then 1 else 0 end) as 'N28', ");
		//专业技术人员，大学专科，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND edu='003' then 1 else 0 end) as 'N29', ");
		//专业技术人员，大学专科，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND edu='003' then 1 else 0 end) as 'N30', ");
		//专业技术人员，中专，专业技术人员其中在管理岗位工作的
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND edu='002' then 1 else 0 end) as 'O27', ");
		//专业技术人员，中专，具有职业资格的
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='002' AND (c.CERTIFICATE_NO IS NOT NULL AND c.CERTIFICATE_NO <> '') then 1 else 0 end) as 'O28', ");
		//专业技术人员，中专，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND edu='002' then 1 else 0 end) as 'O29', ");
		//专业技术人员，中专，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND edu='002' then 1 else 0 end) as 'O30', ");
		//专业技术人员，高中及以下，专业技术人员其中在管理岗位工作的
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND edu='001' then 1 else 0 end) as 'P27', ");
		//专业技术人员，高中及以下，具有职业资格的
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='001' AND (c.CERTIFICATE_NO IS NOT NULL AND c.CERTIFICATE_NO <> '') then 1 else 0 end) as 'P28', ");
		//专业技术人员，高中及以下，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND edu='001' then 1 else 0 end) as 'P29', ");
		//专业技术人员，高中及以下，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND edu='001' then 1 else 0 end) as 'P30', ");
		//专业技术人员，35岁及以下，专于技术人员其中在管理岗位工作的
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND c.AGEEREA='001' then 1 else 0 end) as 'Q27', ");
		//专业技术人员，35岁及以下，具有职业资格的的
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='001' AND (c.CERTIFICATE_NO IS NOT NULL AND c.CERTIFICATE_NO <> '') then 1 else 0 end) as 'Q28', ");
		//专业技术人员，35岁及以下，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND c.AGEEREA='001' then 1 else 0 end) as 'Q29', ");
		//专业技术人员，35岁及以下，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND c.AGEEREA='001' then 1 else 0 end) as 'Q30', ");
		//专业技术人员，36岁至40岁，专业技术人员其中在管理岗位工作的
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND c.AGEEREA='002' then 1 else 0 end) as 'R27', ");
		//专业技术人员，36岁至40岁，具有执业资格的
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='002' AND (c.CERTIFICATE_NO IS NOT NULL AND c.CERTIFICATE_NO <> '') then 1 else 0 end) as 'R28', ");
		//专业技术人员，36岁至40岁，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND c.AGEEREA='002' then 1 else 0 end) as 'R29', ");
		//专业技术人员，36岁至40岁，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND c.AGEEREA='002' then 1 else 0 end) as 'R30', ");
		//专业技术人员，41岁至45岁，专业技术人员其中在管理岗位工作的
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND c.AGEEREA='003' then 1 else 0 end) as 'S27', ");
		//专业技术人员，41岁至45岁，具有职业资格的
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='003' AND (c.CERTIFICATE_NO IS NOT NULL AND c.CERTIFICATE_NO <> '') then 1 else 0 end) as 'S28', ");
		//专业技术人员，41岁至45岁，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND c.AGEEREA='003' then 1 else 0 end) as 'S29', ");
		//专业技术人员，41岁至45岁，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND c.AGEEREA='003' then 1 else 0 end) as 'S30', ");
		//专业技术人员，46岁至50岁，专业技术人员其中在管理岗位上的
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND c.AGEEREA='004' then 1 else 0 end) as 'T27', ");
		//专业技术人员，46岁至50岁，具有职业资格的
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='004' AND (c.CERTIFICATE_NO IS NOT NULL AND c.CERTIFICATE_NO <> '') then 1 else 0 end) as 'T28', ");
		//专业技术人员，46岁至50岁，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND c.AGEEREA='004' then 1 else 0 end) as 'T29', ");
		//专业技术人员，46岁至50岁，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND c.AGEEREA='004' then 1 else 0 end) as 'T30', ");
		//专业技术人员，51岁至54岁，专业技术人员其中在管理岗位上的
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND c.AGEEREA='005' then 1 else 0 end) as 'U27', ");
		//专业技术人员，51岁至54岁，具有职业资格的
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='005' AND (c.CERTIFICATE_NO IS NOT NULL AND c.CERTIFICATE_NO <> '') then 1 else 0 end) as 'U28', ");
		//专业技术人员，51岁至54岁，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND c.AGEEREA='005' then 1 else 0 end) as 'U29', ");
		//专业技术人员，51岁至54岁，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND c.AGEEREA='005' then 1 else 0 end) as 'U30', ");
		//专业技术人员，55岁至59岁，专业技术人员其中在管理岗位工作的
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND c.AGEEREA='006' then 1 else 0 end) as 'V27', ");
		//专业技术人员，55岁至59岁，具有职业资格的
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='006' AND (c.CERTIFICATE_NO IS NOT NULL AND c.CERTIFICATE_NO <> '') then 1 else 0 end) as 'V28', ");
		//专业技术人员，55岁至59岁，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND c.AGEEREA='006' then 1 else 0 end) as 'V29', ");
		//专业技术人员，55岁至59岁，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND c.AGEEREA='006' then 1 else 0 end) as 'V30', ");
		//专业技术人员，60岁及以上，专业技术人员其中在管理岗位工作的
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND c.AGEEREA='007' then 1 else 0 end) as 'W27', ");
		//专业技术人员，60岁及以上，具有职业资格的
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='007' AND (c.CERTIFICATE_NO IS NOT NULL AND c.CERTIFICATE_NO <> '') then 1 else 0 end) as 'W28', ");
		//专业技术人员，60岁及以上，女
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND c.AGEEREA='007' then 1 else 0 end) as 'W29', ");
		//专业技术人员，60岁及以上，少数民族
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND c.AGEEREA='007' then 1 else 0 end) as 'W30', ");
		//专业技术人员，女，高级岗位（一级）
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND c.IN_POST_LEVEL='001' then 1 else 0 end) as 'F31', ");
		//专业技术人员，女，高级岗位（二级）
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND c.IN_POST_LEVEL='002' then 1 else 0 end) as 'F32', ");
		//专业技术人员，少数民族，高级岗位（一级）
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND c.IN_POST_LEVEL='001' then 1 else 0 end) as 'G31', ");
		//专业技术人员，少数民族，高级岗位（二级）
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND c.IN_POST_LEVEL='002' then 1 else 0 end) as 'G32', ");
		//专业技术人员，中共党员，高级岗位（一级）
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND c.IN_POST_LEVEL='001' then 1 else 0 end) as 'H31', ");
		//专业技术人员，中共党员，高级岗位（二级）
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND c.IN_POST_LEVEL='002' then 1 else 0 end) as 'H32', ");
		//专业技术人员，博士，高级岗位（一级）
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='006' AND c.IN_POST_LEVEL='001' then 1 else 0 end) as 'I31', ");
		//专业技术人员，博士，高级岗位（二级）
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='006' AND c.IN_POST_LEVEL='002' then 1 else 0 end) as 'I32', ");
		//专业技术人员，高级岗位（一级），硕士
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='005' AND c.IN_POST_LEVEL='001' then 1 else 0 end) as 'J31', ");
		//专业技术人员，高级岗位（二级），硕士
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='005' AND c.IN_POST_LEVEL='002' then 1 else 0 end) as 'J32', ");
		//专业技术人员，高级岗位（一级），研究生
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu IN ('005','006') AND c.IN_POST_LEVEL='001' then 1 else 0 end) as 'L31', ");
		//专业技术人员，高级岗位（二级），研究生
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu IN ('005','006') AND c.IN_POST_LEVEL='002' then 1 else 0 end) as 'L32', ");
		//专业技术人员，高级岗位（一级），大学本科
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='004' AND c.IN_POST_LEVEL='001' then 1 else 0 end) as 'M31', ");
		//专业技术人员，高级岗位（二级），大学本科
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='004' AND c.IN_POST_LEVEL='002' then 1 else 0 end) as 'M32', ");
		//专业技术人员，高级岗位（一级），大学专科
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='003' AND c.IN_POST_LEVEL='001' then 1 else 0 end) as 'N31', ");
		//专业技术人员，高级岗位（二级），大学专科
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='003' AND c.IN_POST_LEVEL='002' then 1 else 0 end) as 'N32', ");
		//专业技术人员，高级岗位（一级），中专
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='002' AND c.IN_POST_LEVEL='001' then 1 else 0 end) as 'O31', ");
		//专业技术人员，高级岗位（二级），中专
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='002' AND c.IN_POST_LEVEL='002' then 1 else 0 end) as 'O32', ");
		//专业技术人员，高级岗位（一级），高中及以下
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='001' AND c.IN_POST_LEVEL='001' then 1 else 0 end) as 'P31', ");
		//专业技术人员，高级岗位（二级），高中及以下
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='001' AND c.IN_POST_LEVEL='002' then 1 else 0 end) as 'P32', ");
		//专业技术人员，高级岗位（一级），35岁及以下
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='001' AND c.IN_POST_LEVEL='001' then 1 else 0 end) as 'Q31', ");
		//专业技术人员，高级岗位（二级），35岁及以下
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='001' AND c.IN_POST_LEVEL='002' then 1 else 0 end) as 'Q32', ");
		//专业技术人员，高级岗位（一级），36岁至40岁
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='002' AND c.IN_POST_LEVEL='001' then 1 else 0 end) as 'R31', ");
		//专业技术人员，高级岗位（二级），36岁至40岁
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='002' AND c.IN_POST_LEVEL='002' then 1 else 0 end) as 'R32', ");
		//专业技术人员，高级岗位（一级），41岁到45岁
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='003' AND c.IN_POST_LEVEL='001' then 1 else 0 end) as 'S31', ");
		//专业技术人员，高级岗位（二级），41岁到45岁
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='003' AND c.IN_POST_LEVEL='002' then 1 else 0 end) as 'S32', ");
		//专业技术人员，高级岗位（一级），46岁到50岁
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='004' AND c.IN_POST_LEVEL='001' then 1 else 0 end) as 'T31', ");
		//专业技术人员，高级岗位（二级），46岁到50岁
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='004' AND c.IN_POST_LEVEL='002' then 1 else 0 end) as 'T32', ");
		//专业技术人员，高级岗位（一级），51岁至54岁
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='005' AND c.IN_POST_LEVEL='001' then 1 else 0 end) as 'U31', ");
		//专业技术人员，高级岗位（二级），51岁到54岁
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='005' AND c.IN_POST_LEVEL='002' then 1 else 0 end) as 'U32', ");
		//专业技术人员，高级岗位（一级），55岁到59岁
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='006' AND c.IN_POST_LEVEL='001' then 1 else 0 end) as 'V31', ");
		//专业技术人员，高级岗位（二级），55岁到59岁
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='006' AND c.IN_POST_LEVEL='002' then 1 else 0 end) as 'V32', ");
		//专业技术人员，高级岗位（一级），60岁及以上
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='007' AND c.IN_POST_LEVEL='001' then 1 else 0 end) as 'W31', ");
		//专业技术人员，高级岗位（二级），60岁及以上
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='007' AND c.IN_POST_LEVEL='002' then 1 else 0 end) as 'W32', ");
		sql.append(" '"+rlgl200101BeanNew.getSel_year()+"' + '年' as 'M7' ");
		sql.append(" from M_TB12_PERSONNEL tb ");
		sql.append(" LEFT JOIN  ");
		sql.append(" (SELECT  ");
		sql.append(" age.AGEEREA,age.IN_POST_LEVEL,age.IN_POST_KBN,age.PERSONNEL_ID,age.EDU,age.CERTIFICATE_NO ");
		sql.append(" from (SELECT  ");
		sql.append(" CASE WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) - YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ,  ");
		sql.append(" 5 ) < RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ,  ");
		sql.append(" 5 ) ) <=35 THEN '001' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) - YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ,  ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ,5 ) )  ");
		sql.append(" BETWEEN 36  ");
		sql.append(" AND 40 THEN '002' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ,  ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ,  ");
		sql.append(" 5 ) )  ");
		sql.append(" BETWEEN 41  ");
		sql.append(" AND 45 THEN '003' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ,  ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ,  ");
		sql.append(" 5 ) )  ");
		sql.append(" BETWEEN 46  ");
		sql.append(" AND 50 THEN '004' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ,  ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ,  ");
		sql.append(" 5 ) )  ");
		sql.append(" BETWEEN 51  ");
		sql.append(" AND 54 THEN '005' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ,  ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ,5 ) )  ");
		sql.append(" BETWEEN 55  ");
		sql.append(" AND 59 THEN '006' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ,  ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) , 5 ) )  ");
		sql.append(" >=60 THEN '007'  ");
		sql.append(" ELSE 0 END AS AGEEREA , ");
		sql.append(" ( ");
		sql.append(" SELECT EDUCATIONAL_BG  ");
		sql.append(" FROM m_tb15_personnel_edu_info ");
		sql.append(" WHERE  ");
		sql.append(" PERSONNEL_ID=A.PERSONNEL_ID  ");
		sql.append(" and  ");
		sql.append(" GRADUATION_TIME =  ");
		sql.append(" ( ");
		sql.append(" Select Max(GRADUATION_TIME)  ");
		sql.append(" From  m_tb15_personnel_edu_info  ");
		sql.append(" WHERE ");
		sql.append(" PERSONNEL_ID=A.PERSONNEL_ID  ");
		sql.append(" ) ");
		sql.append(" ) AS edu , ");
		sql.append("  ");
		sql.append(" case when m.IN_POST_LEVEL = '' then null else m.IN_POST_LEVEL end as IN_POST_LEVEL, ");
		sql.append("  ");
		sql.append(" m.IN_POST_KBN, ");
		sql.append(" D.CERTIFICATE_NO, ");
		sql.append(" A.PERSONNEL_ID  ");
		sql.append("  ");
		sql.append(" FROM  ");
		sql.append(" M_TB12_PERSONNEL as A  ");
		sql.append(" LEFT JOIN ");
		sql.append(" m_tb09_irin m ");
		sql.append(" on m.PERSON_NO=A.PERSONNEL_ID  ");
		sql.append(" LEFT JOIN m_tb19_personnel_practitioners_info D ");
		sql.append(" on D.PERSONNEL_ID=A.PERSONNEL_ID  ");
		sql.append(" where substr(a.LOGIN_DATE,1,4)<='"
				+ rlgl200101BeanNew.getSel_year() + "' ");
		sql.append(" and substr(m.LOGIN_DATE,1,4)<='"
				+ rlgl200101BeanNew.getSel_year() + "' ");
		sql.append(" and  ");
		sql.append(" (A.PERSONNEL_UNIT in  ");
		sql.append(" (SELECT unit_no FROM m_tb04_unit WHERE ESCROW_UNIT_NO = '"
				+ rlgl200101BeanNew.getUnit_no()
				+ "' AND DEL_KBN = 0 AND UNIT_STATUS = '1') ");
		sql.append(" or A.PERSONNEL_UNIT LIKE CONCAT('', '"
				+ rlgl200101BeanNew.getUnit_no() + "', '%') ");
		sql.append(" ) ");
		sql.append(" ) as age ");
		sql.append(" ) as c ");
		sql.append(" on tb.PERSONNEL_ID =c.PERSONNEL_ID  ");
		sql.append(" where  ");
		sql.append(" substr(tb.LOGIN_DATE,1,4)<='"
				+ rlgl200101BeanNew.getSel_year() + "' ");

		return sql.toString();
	}
    /**
     * 事业单位工作人员基本情况Sql
     */
	public String EditUnitBaseInfoSql021(Rlgl200101Bean rlgl200101BeanNew) {
		StringBuilder sql = new StringBuilder();

		sql.append(" select  ");
		//专业技术人员，女，高级岗位（三级）
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND c.IN_POST_LEVEL='003' then 1 else 0 end) as 'F11', ");
		//专业技术人员，女，高级岗位（四级）
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND c.IN_POST_LEVEL='004' then 1 else 0 end) as 'F12', ");
		//专业技术人员，女，高级岗位（五级）
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND c.IN_POST_LEVEL='005' then 1 else 0 end) as 'F13', ");
		//专业技术人员，女，高级岗位（六级）
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND c.IN_POST_LEVEL='006' then 1 else 0 end) as 'F14', ");
		//专业技术人员，女，高级岗位（七级）
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND c.IN_POST_LEVEL='007' then 1 else 0 end) as 'F15', ");
		//专业技术人员，女，中级岗位（八级）
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND c.IN_POST_LEVEL='008' then 1 else 0 end) as 'F16', ");
		//专业技术人员，女，中级岗位（九级）
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND c.IN_POST_LEVEL='009' then 1 else 0 end) as 'F17', ");
		//专业技术人员，女，中级岗位（十级）
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND c.IN_POST_LEVEL='010' then 1 else 0 end) as 'F18', ");
		//专业技术人员，女,初级岗位（十一级）
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND c.IN_POST_LEVEL='011' then 1 else 0 end) as 'F19', ");
		//专业技术人员，女，初级岗位（十二级）
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND c.IN_POST_LEVEL='012' then 1 else 0 end) as 'F20', ");
		//专业技术人员，女，初级岗位（十三级）
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND c.IN_POST_LEVEL='013' then 1 else 0 end) as 'F21', ");
		//专业技术人员，女，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_GENDER='002' AND c.IN_POST_LEVEL is null then 1 else 0 end) as 'F22', ");
		//专业技术人员，少数民族，高级岗位（三级）
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND c.IN_POST_LEVEL='003' then 1 else 0 end) as 'G11', ");
		//专业技术人员，少数民族，高级岗位 四级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND c.IN_POST_LEVEL='004' then 1 else 0 end) as 'G12', ");
		//专业技术人员，少数民族，高级岗位 五级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND c.IN_POST_LEVEL='005' then 1 else 0 end) as 'G13', ");
		//专业技术人员，少数民族，高级岗位 六级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND c.IN_POST_LEVEL='006' then 1 else 0 end) as 'G14', ");
		//专业技术人员，少数民族，高级岗位 七级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND c.IN_POST_LEVEL='007' then 1 else 0 end) as 'G15', ");
		//专业技术人员，少数民族，中级岗位 八级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND c.IN_POST_LEVEL='008' then 1 else 0 end) as 'G16', ");
		//专业技术人员，少数民族，中级岗位 九级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND c.IN_POST_LEVEL='009' then 1 else 0 end) as 'G17', ");
		//专业技术人员，少数民族，中级岗位 十级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND c.IN_POST_LEVEL='010' then 1 else 0 end) as 'G18', ");
		//专业技术人员，少数民族，初级岗位 十一级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND c.IN_POST_LEVEL='011' then 1 else 0 end) as 'G19', ");
		//专业技术人员，少数民族，初级岗位 十二级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND c.IN_POST_LEVEL='012' then 1 else 0 end) as 'G20', ");
		//专业技术人员，少数民族，初级岗位 十三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND c.IN_POST_LEVEL='013' then 1 else 0 end) as 'G21', ");
		//专业技术人员，少数民族，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_ETHNIC<>'001' AND c.IN_POST_LEVEL is null then 1 else 0 end) as 'G22', ");
		//专业技术人员，中共党员，高级岗位 三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND c.IN_POST_LEVEL='003' then 1 else 0 end) as 'H11', ");
		//专业技术人员，中共党员，高级岗位 四级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND c.IN_POST_LEVEL='004' then 1 else 0 end) as 'H12', ");
		//专业技术人员，中共党员，高级岗位 五级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND c.IN_POST_LEVEL='005' then 1 else 0 end) as 'H13', ");
		//专业技术人员，中共党员，高级岗位 六级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND c.IN_POST_LEVEL='006' then 1 else 0 end) as 'H14', ");
		//专业技术人员，中共党员，高级岗位 七级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND c.IN_POST_LEVEL='007' then 1 else 0 end) as 'H15', ");
		//专业技术人员，中共党员，中级岗位 八级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND c.IN_POST_LEVEL='008' then 1 else 0 end) as 'H16', ");
		//专业技术人员，中共党员，中级岗位 九级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND c.IN_POST_LEVEL='009' then 1 else 0 end) as 'H17', ");
		//专业技术人员，中共党员，中级岗位 十级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND c.IN_POST_LEVEL='010' then 1 else 0 end) as 'H18', ");
		//专业技术人员，中共党员，初级岗位 十一级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND c.IN_POST_LEVEL='011' then 1 else 0 end) as 'H19', ");
		//专业技术人员，中共党员，初级岗位 十二级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND c.IN_POST_LEVEL='012' then 1 else 0 end) as 'H20', ");
		//专业技术人员，中共党员，初级岗位 十三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND c.IN_POST_LEVEL='013' then 1 else 0 end) as 'H21', ");
		//专业技术人员，中共党员，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND c.IN_POST_LEVEL is null then 1 else 0 end) as 'H22', ");
		//专业技术人员，博士，高级岗位 三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='006' AND c.IN_POST_LEVEL='003' then 1 else 0 end) as 'I11', ");
		//专业技术人员，博士，高级岗位 四级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='006' AND c.IN_POST_LEVEL='004' then 1 else 0 end) as 'I12', ");
		//专业技术人员，博士，高级岗位 五级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='006' AND c.IN_POST_LEVEL='005' then 1 else 0 end) as 'I13', ");
		//专业技术人员，博士，高级岗位 六级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='006' AND c.IN_POST_LEVEL='006' then 1 else 0 end) as 'I14', ");
		//专业技术人员，博士，高级岗位 七级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='006' AND c.IN_POST_LEVEL='007' then 1 else 0 end) as 'I15', ");
		//专业技术人员，博士，中级岗位 八级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='006' AND c.IN_POST_LEVEL='008' then 1 else 0 end) as 'I16', ");
		//专业技术人员，博士，中级岗位 九级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='006' AND c.IN_POST_LEVEL='009' then 1 else 0 end) as 'I17', ");
		//专业技术人员，博士，中级岗位 十级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='006' AND c.IN_POST_LEVEL='010' then 1 else 0 end) as 'I18', ");
		//专业技术人员，博士，初级岗位 十一级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='006' AND c.IN_POST_LEVEL='011' then 1 else 0 end) as 'I19', ");
		//专业技术人员，博士，初级岗位 十二级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='006' AND c.IN_POST_LEVEL='012' then 1 else 0 end) as 'I20', ");
		//专业技术人员，博士，初级岗位 十三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='006' AND c.IN_POST_LEVEL='013' then 1 else 0 end) as 'I21', ");
		//专业技术人员，博士，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='006' AND c.IN_POST_LEVEL is null then 1 else 0 end) as 'I22', ");
		//专业技术人员，硕士，高级岗位 三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='005' AND c.IN_POST_LEVEL='003' then 1 else 0 end) as 'J11', ");
		//专业技术人员，硕士，高级岗位 四级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='005' AND c.IN_POST_LEVEL='004' then 1 else 0 end) as 'J12', ");
		//专业技术人员，硕士，高级岗位 五级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='005' AND c.IN_POST_LEVEL='005' then 1 else 0 end) as 'J13', ");
		//专业技术人员，硕士，高级岗位 六级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='005' AND c.IN_POST_LEVEL='006' then 1 else 0 end) as 'J14', ");
		//专业技术人员，硕士，高级岗位 七级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='005' AND c.IN_POST_LEVEL='007' then 1 else 0 end) as 'J15', ");
		//专业技术人员，硕士，中级岗位 八级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='005' AND c.IN_POST_LEVEL='008' then 1 else 0 end) as 'J16', ");
		//专业技术人员，硕士，中级岗位 九级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='005' AND c.IN_POST_LEVEL='009' then 1 else 0 end) as 'J17', ");
		//专业技术人员，硕士，中级岗位 十级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='005' AND c.IN_POST_LEVEL='010' then 1 else 0 end) as 'J18', ");
		//专业技术人员，硕士，初级岗位 十一级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='005' AND c.IN_POST_LEVEL='011' then 1 else 0 end) as 'J19', ");
		//专业技术人员，硕士，初级岗位 十二级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='005' AND c.IN_POST_LEVEL='012' then 1 else 0 end) as 'J20', ");
		//专业技术人员，硕士，初级岗位 十三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='005' AND c.IN_POST_LEVEL='013' then 1 else 0 end) as 'J21', ");
		//专业技术人员，硕士，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='005' AND c.IN_POST_LEVEL is null then 1 else 0 end) as 'J22', ");
		//专业技术人员，研究生，高级岗位 三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu IN ('005','006') AND c.IN_POST_LEVEL='003' then 1 else 0 end) as 'L11', ");
		//专业技术人员，研究生，高级岗位 四级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu IN ('005','006') and c.IN_POST_LEVEL='004' then 1 else 0 end) as 'L12', ");
		//专业技术人员，研究生，高级岗位 五级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu IN ('005','006') AND c.IN_POST_LEVEL='005' then 1 else 0 end) as 'L13', ");
		//专业技术人员，研究生，高级岗位 六级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu IN ('005','006') AND c.IN_POST_LEVEL='006' then 1 else 0 end) as 'L14', ");
		//专业技术人员，研究生，高级岗位 七级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu IN ('005','006') AND c.IN_POST_LEVEL='007' then 1 else 0 end) as 'L15', ");
		//专业技术人员，研究生，中级岗位 八级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu IN ('005','006') AND c.IN_POST_LEVEL='008' then 1 else 0 end) as 'L16', ");
		//专业技术人员，研究生，中级岗位 九级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu IN ('005','006') AND c.IN_POST_LEVEL='009' then 1 else 0 end) as 'L17', ");
		//专业技术人员，研究生，中级岗位 十级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu IN ('005','006') AND c.IN_POST_LEVEL='010' then 1 else 0 end) as 'L18', ");
		//专业技术人员，研究生，初级岗位 十一级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu IN ('005','006') AND c.IN_POST_LEVEL='011' then 1 else 0 end) as 'L19', ");
		//专业技术人员，研究生，初级岗位 十二级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu IN ('005','006') AND c.IN_POST_LEVEL='012' then 1 else 0 end) as 'L20', ");
		//专业技术人员，研究生，初级岗位 十三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu IN ('005','006') AND c.IN_POST_LEVEL='013' then 1 else 0 end) as 'L21', ");
		//专业技术人员，研究生，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu IN ('005','006') AND c.IN_POST_LEVEL is null then 1 else 0 end) as 'L22', ");
		//专业技术人员，大学本科，高级岗位 三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='004' AND c.IN_POST_LEVEL='003' then 1 else 0 end) as 'M11', ");
		//专业技术人员，大学本科，高级岗位 四级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='004' AND c.IN_POST_LEVEL='004' then 1 else 0 end) as 'M12', ");
		//专业技术人员，大学本科，高级岗位 五级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='004' AND c.IN_POST_LEVEL='005' then 1 else 0 end) as 'M13', ");
		//专业技术人员，大学本科，高级岗位 六级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='004' AND c.IN_POST_LEVEL='006' then 1 else 0 end) as 'M14', ");
		//专业技术人员，大学本科，高级岗位 七级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='004' AND c.IN_POST_LEVEL='007' then 1 else 0 end) as 'M15', ");
		//专业技术人员，大学本科，中级岗位 八级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='004' AND c.IN_POST_LEVEL='008' then 1 else 0 end) as 'M16', ");
		//专业技术人员，大学本科，中级岗位 九级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='004' AND c.IN_POST_LEVEL='009' then 1 else 0 end) as 'M17', ");
		//专业技术人员，大学本科，中级岗位 十级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='004' AND c.IN_POST_LEVEL='010' then 1 else 0 end) as 'M18', ");
		//专业技术人员，大学本科，初级岗位 十一级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='004' AND c.IN_POST_LEVEL='011' then 1 else 0 end) as 'M19', ");
		//专业技术人员，大学本科，初级岗位 十二级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='004' AND c.IN_POST_LEVEL='012' then 1 else 0 end) as 'M20', ");
		//专业技术人员，大学本科，初级岗位 十三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='004' AND c.IN_POST_LEVEL='013' then 1 else 0 end) as 'M21', ");
		//专业技术人员，大学本科，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='004' AND c.IN_POST_LEVEL is null then 1 else 0 end) as 'M22', ");
		//专业技术人员，大学专科，高级岗位 三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='003' AND c.IN_POST_LEVEL='003' then 1 else 0 end) as 'N11', ");
		//专业技术人员，大学专科，高级岗位 四级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='003' AND c.IN_POST_LEVEL='004' then 1 else 0 end) as 'N12', ");
		//专业技术人员，大学专科，高级岗位 五级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='003' AND c.IN_POST_LEVEL='005' then 1 else 0 end) as 'N13', ");
		//专业技术人员，大学专科，高级岗位 六级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='003' AND c.IN_POST_LEVEL='006' then 1 else 0 end) as 'N14', ");
		//专业技术人员，大学专科，高级岗位 七级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='003' AND c.IN_POST_LEVEL='007' then 1 else 0 end) as 'N15', ");
		//专业技术人员，大学专科，中级岗位 八级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='003' AND c.IN_POST_LEVEL='008' then 1 else 0 end) as 'N16', ");
		//专业技术人员，大学专科，中级岗位 九级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='003' AND c.IN_POST_LEVEL='009' then 1 else 0 end) as 'N17', ");
		//专业技术人员，大学专科，中级岗位 十级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='003' AND c.IN_POST_LEVEL='010' then 1 else 0 end) as 'N18', ");
		//专业技术人员，大学专科，初级岗位 十一级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='003' AND c.IN_POST_LEVEL='011' then 1 else 0 end) as 'N19', ");
		//专业技术人员，大学专科，初级岗位 十二级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='003' AND c.IN_POST_LEVEL='012' then 1 else 0 end) as 'N20', ");
		//专业技术人员，大学专科，初级岗位 十三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='003' AND c.IN_POST_LEVEL='013' then 1 else 0 end) as 'N21', ");
		//专业技术人员，大学专科，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='003' AND c.IN_POST_LEVEL is null then 1 else 0 end) as 'N22', ");
		//专业技术人员，中专，高级岗位 三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='002' AND c.IN_POST_LEVEL='003' then 1 else 0 end) as 'O11', ");
		//专业技术人员，中专，高级岗位 四级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='002' AND c.IN_POST_LEVEL='004' then 1 else 0 end) as 'O12', ");
		//专业技术人员，中专，高级岗位 五级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='002' AND c.IN_POST_LEVEL='005' then 1 else 0 end) as 'O13', ");
		//专业技术人员，中专，高级岗位 六级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='002' AND c.IN_POST_LEVEL='006' then 1 else 0 end) as 'O14', ");
		//专业技术人员，中专，高级岗位 七级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='002' AND c.IN_POST_LEVEL='007' then 1 else 0 end) as 'O15', ");
		//专业技术人员，中专，中级岗位 八级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='002' AND c.IN_POST_LEVEL='008' then 1 else 0 end) as 'O16', ");
		//专业技术人员，中专，中级岗位 九级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='002' AND c.IN_POST_LEVEL='009' then 1 else 0 end) as 'O17', ");
		//专业技术人员，中专，中级岗位 十级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='002' AND c.IN_POST_LEVEL='010' then 1 else 0 end) as 'O18', ");
		//专业技术人员，中专，初级岗位 十一级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='002' AND c.IN_POST_LEVEL='011' then 1 else 0 end) as 'O19', ");
		//专业技术人员，中专，初级岗位 十二级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='002' AND c.IN_POST_LEVEL='012' then 1 else 0 end) as 'O20', ");
		//专业技术人员，中专，初级岗位 十三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='002' AND c.IN_POST_LEVEL='013' then 1 else 0 end) as 'O21', ");
		//专业技术人员，中专，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='002' AND c.IN_POST_LEVEL is null then 1 else 0 end) as 'O22', ");
		//专业技术人员，高中及以下，高级岗位 三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='001' AND c.IN_POST_LEVEL='003' then 1 else 0 end) as 'P11', ");
		//专业技术人员，高中及以下，高级岗位 四级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='001' AND c.IN_POST_LEVEL='004' then 1 else 0 end) as 'P12', ");
		//专业技术人员，高中及以下，高级岗位 五级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='001' AND c.IN_POST_LEVEL='005' then 1 else 0 end) as 'P13', ");
		//专业技术人员，高中及以下，高级岗位 六级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='001' AND c.IN_POST_LEVEL='006' then 1 else 0 end) as 'P14', ");
		//专业技术人员，高中及以下，高级岗位 七级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='001' AND c.IN_POST_LEVEL='007' then 1 else 0 end) as 'P15', ");
		//专业技术人员，高中及以下，中级岗位 八级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='001' AND c.IN_POST_LEVEL='008' then 1 else 0 end) as 'P16', ");
		//专业技术人员，高中及以下，中级岗位 九级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='001' AND c.IN_POST_LEVEL='009' then 1 else 0 end) as 'P17', ");
		//专业技术人员，高中及以下，中级岗位 十级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='001' AND c.IN_POST_LEVEL='010' then 1 else 0 end) as 'P18', ");
		//专业技术人员，高中及以下，初级岗位 十一级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='001' AND c.IN_POST_LEVEL='011' then 1 else 0 end) as 'P19', ");
		//专业技术人员，高中及以下，初级岗位 十二级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='001' AND c.IN_POST_LEVEL='012' then 1 else 0 end) as 'P20', ");
		//专业技术人员，高中及以下，初级岗位 十三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='001' AND c.IN_POST_LEVEL='013' then 1 else 0 end) as 'P21', ");
		//专业技术人员，高中及以下，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND edu='001' AND c.IN_POST_LEVEL is null then 1 else 0 end) as 'P22', ");
		//专业技术人员，35岁及以下，高级岗位 三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='001' AND c.IN_POST_LEVEL='003' then 1 else 0 end) as 'Q11', ");
		//专业技术人员，35岁及以下，高级岗位 四级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='001' AND c.IN_POST_LEVEL='004' then 1 else 0 end) as 'Q12', ");
		//专业技术人员，35岁及以下，高级岗位 五级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='001' AND c.IN_POST_LEVEL='005' then 1 else 0 end) as 'Q13', ");
		//专业技术人员，35岁及以下，高级岗位 六级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='001' AND c.IN_POST_LEVEL='006' then 1 else 0 end) as 'Q14', ");
		//专业技术人员，35岁及以下，高级岗位 七级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='001' AND c.IN_POST_LEVEL='007' then 1 else 0 end) as 'Q15', ");
		//专业技术人员，35岁及以下，中级岗位 八级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='001' AND c.IN_POST_LEVEL='008' then 1 else 0 end) as 'Q16', ");
		//专业技术人员，35岁及以下，中级岗位 九级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='001' AND c.IN_POST_LEVEL='009' then 1 else 0 end) as 'Q17', ");
		//专业技术人员，35岁及以下，中级岗位 十级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='001' AND c.IN_POST_LEVEL='010' then 1 else 0 end) as 'Q18', ");
		//专业技术人员，35岁及以下，初级岗位 十一级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='001' AND c.IN_POST_LEVEL='011' then 1 else 0 end) as 'Q19', ");
		//专业技术人员，35岁及以下，初级岗位 十二级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='001' AND c.IN_POST_LEVEL='012' then 1 else 0 end) as 'Q20', ");
		//专业技术人员，35岁及以下，初级岗位 十三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='001' AND c.IN_POST_LEVEL='013' then 1 else 0 end) as 'Q21', ");
		//专业技术人员，35岁及以下，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='001' AND c.IN_POST_LEVEL is null then 1 else 0 end) as 'Q22', ");
		//专业技术人员，36岁至40岁，高级岗位 三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='002' AND c.IN_POST_LEVEL='003' then 1 else 0 end) as 'R11', ");
		//专业技术人员，36岁至40岁，高级岗位 四级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='002' AND c.IN_POST_LEVEL='004' then 1 else 0 end) as 'R12', ");
		//专业技术人员，36岁至40岁，高级岗位 五级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='002' AND c.IN_POST_LEVEL='005' then 1 else 0 end) as 'R13', ");
		//专业技术人员，36岁至40岁，高级岗位 六级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='002' AND c.IN_POST_LEVEL='006' then 1 else 0 end) as 'R14', ");
		//专业技术人员，36岁至40岁，高级岗位 七级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='002' AND c.IN_POST_LEVEL='007' then 1 else 0 end) as 'R15', ");
		//专业技术人员，36岁至40岁，中级岗位 八级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='002' AND c.IN_POST_LEVEL='008' then 1 else 0 end) as 'R16', ");
		//专业技术人员，36岁至40岁，中级岗位 九级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='002' AND c.IN_POST_LEVEL='009' then 1 else 0 end) as 'R17', ");
		//专业技术人员，36岁至40岁，中级岗位 十级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='002' AND c.IN_POST_LEVEL='010' then 1 else 0 end) as 'R18', ");
		//专业技术人员，36岁至40岁，初级岗位 十一级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='002' AND c.IN_POST_LEVEL='011' then 1 else 0 end) as 'R19', ");
		//专业技术人员，36岁至40岁，初级岗位 十二级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='002' AND c.IN_POST_LEVEL='012' then 1 else 0 end) as 'R20', ");
		//专业技术人员，36岁至40岁，初级岗位 十三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='002' AND c.IN_POST_LEVEL='013' then 1 else 0 end) as 'R21', ");
		//专业技术人员，36岁至40岁，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='002' AND c.IN_POST_LEVEL is null then 1 else 0 end) as 'R22', ");
		//专业技术人员，41岁至45岁，高级岗位 三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='003' AND c.IN_POST_LEVEL='003' then 1 else 0 end) as 'S11', ");
		//专业技术人员，41岁至45岁，高级岗位 四级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='003' AND c.IN_POST_LEVEL='004' then 1 else 0 end) as 'S12', ");
		//专业技术人员，41岁至45岁，高级岗位 五级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='003' AND c.IN_POST_LEVEL='005' then 1 else 0 end) as 'S13', ");
		//专业技术人员，41岁至45岁，高级岗位 六级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='003' AND c.IN_POST_LEVEL='006' then 1 else 0 end) as 'S14', ");
		//专业技术人员，41岁至45岁，高级岗位 七级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='003' AND c.IN_POST_LEVEL='007' then 1 else 0 end) as 'S15', ");
		//专业技术人员，41岁至45岁，中级岗位 八级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='003' AND c.IN_POST_LEVEL='008' then 1 else 0 end) as 'S16', ");
		//专业技术人员，41岁至45岁，中级岗位 九级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='003' AND c.IN_POST_LEVEL='009' then 1 else 0 end) as 'S17', ");
		//专业技术人员，41岁至45岁，中级岗位 十级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='003' AND c.IN_POST_LEVEL='010' then 1 else 0 end) as 'S18', ");
		//专业技术人员，41岁至45岁，初级岗位 十一级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='003' AND c.IN_POST_LEVEL='011' then 1 else 0 end) as 'S19', ");
		//专业技术人员，41岁至45岁，初级岗位 十二级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='003' AND c.IN_POST_LEVEL='012' then 1 else 0 end) as 'S20', ");
		//专业技术人员，41岁至45岁，初级岗位 十三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='003' AND c.IN_POST_LEVEL='013' then 1 else 0 end) as 'S21', ");
		//专业技术人员，41岁至45岁，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='003' AND c.IN_POST_LEVEL is null then 1 else 0 end) as 'S22', ");
		//专业技术人员，46岁至50岁，高级岗位 三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='004' AND c.IN_POST_LEVEL='003' then 1 else 0 end) as 'T11', ");
		//专业技术人员，46岁至50岁，高级岗位 四级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='004' AND c.IN_POST_LEVEL='004' then 1 else 0 end) as 'T12', ");
		//专业技术人员，46岁至50岁，高级岗位 五级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='004' AND c.IN_POST_LEVEL='005' then 1 else 0 end) as 'T13', ");
		//专业技术人员，46岁至50岁，高级岗位 六级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='004' AND c.IN_POST_LEVEL='006' then 1 else 0 end) as 'T14', ");
		//专业技术人员，46岁至50岁，高级岗位 七级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='004' AND c.IN_POST_LEVEL='007' then 1 else 0 end) as 'T15', ");
		//专业技术人员，46岁至50岁，中级岗位 八级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='004' AND c.IN_POST_LEVEL='008' then 1 else 0 end) as 'T16', ");
		//专业技术人员，46岁至50岁，中级岗位 九级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='004' AND c.IN_POST_LEVEL='009' then 1 else 0 end) as 'T17', ");
		//专业技术人员，46岁至50岁，中级岗位 十级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='004' AND c.IN_POST_LEVEL='010' then 1 else 0 end) as 'T18', ");
		//专业技术人员，46岁至50岁，初级岗位 十一级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='004' AND c.IN_POST_LEVEL='011' then 1 else 0 end) as 'T19', ");
		//专业技术人员，46岁至50岁，初级岗位 十二级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='004' AND c.IN_POST_LEVEL='012' then 1 else 0 end) as 'T20', ");
		//专业技术人员，46岁至50岁，初级岗位 十三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='004' AND c.IN_POST_LEVEL='013' then 1 else 0 end) as 'T21', ");
		//专业技术人员，46岁至50岁，其他登记人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='004' AND c.IN_POST_LEVEL is null then 1 else 0 end) as 'T22', ");
		//专业技术人员，51岁至54岁，高级岗位 三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='005' AND c.IN_POST_LEVEL='003' then 1 else 0 end) as 'U11', ");
		//专业技术人员，51岁至54岁，高级岗位 四级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='005' AND c.IN_POST_LEVEL='004' then 1 else 0 end) as 'U12', ");
		//专业技术人员，51岁至54岁，高级岗位 五级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='005' AND c.IN_POST_LEVEL='005' then 1 else 0 end) as 'U13', ");
		//专业技术人员，51岁至54岁，高级岗位 六级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='005' AND c.IN_POST_LEVEL='006' then 1 else 0 end) as 'U14', ");
		//专业技术人员，51岁至54岁，高级岗位 七级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='005' AND c.IN_POST_LEVEL='007' then 1 else 0 end) as 'U15', ");
		//专业技术人员，51岁至54岁，中级岗位 八级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='005' AND c.IN_POST_LEVEL='008' then 1 else 0 end) as 'U16', ");
		//专业技术人员，51岁至54岁，中级岗位 九级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='005' AND c.IN_POST_LEVEL='009' then 1 else 0 end) as 'U17', ");
		//专业技术人员，51岁至54岁，中级岗位 十级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='005' AND c.IN_POST_LEVEL='010' then 1 else 0 end) as 'U18', ");
		//专业技术人员，51岁至54岁，初级岗位 十一级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='005' AND c.IN_POST_LEVEL='011' then 1 else 0 end) as 'U19', ");
		//专业技术人员，51岁至54岁，初级岗位 十二级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='005' AND c.IN_POST_LEVEL='012' then 1 else 0 end) as 'U20', ");
		//专业技术人员，51岁至54岁，初级岗位 十三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='005' AND c.IN_POST_LEVEL='013' then 1 else 0 end) as 'U21', ");
		//专业技术人员，51岁至54岁，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='005' AND c.IN_POST_LEVEL is null then 1 else 0 end) as 'U22', ");
		//专业技术人员，55岁至59岁，高级岗位 三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='006' AND c.IN_POST_LEVEL='003' then 1 else 0 end) as 'V11', ");
		//专业技术人员，55岁至59岁，高级岗位 四级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='006' AND c.IN_POST_LEVEL='004' then 1 else 0 end) as 'V12', ");
		//专业技术人员，55岁至59岁，高级岗位 五级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='006' AND c.IN_POST_LEVEL='005' then 1 else 0 end) as 'V13', ");
		//专业技术人员，55岁至59岁，高级岗位 六级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='006' AND c.IN_POST_LEVEL='006' then 1 else 0 end) as 'V14', ");
		//专业技术人员，55岁至59岁，高级岗位 七级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='006' AND c.IN_POST_LEVEL='007' then 1 else 0 end) as 'V15', ");
		//专业技术人员，55岁至59岁，中级岗位 八级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='006' AND c.IN_POST_LEVEL='008' then 1 else 0 end) as 'V16', ");
		//专业技术人员，55岁至59岁，中级岗位 九级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='006' AND c.IN_POST_LEVEL='009' then 1 else 0 end) as 'V17', ");
		//专业技术人员，55岁至59岁，中级岗位 十级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='006' AND c.IN_POST_LEVEL='010' then 1 else 0 end) as 'V18', ");
		//专业技术人员，55岁至59岁，初级岗位 十一级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='006' AND c.IN_POST_LEVEL='011' then 1 else 0 end) as 'V19', ");
		//专业技术人员，55岁至59岁，初级岗位 十二级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='006' AND c.IN_POST_LEVEL='012' then 1 else 0 end) as 'V20', ");
		//专业技术人员，55岁至59岁，初级岗位 十三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='006' AND c.IN_POST_LEVEL='013' then 1 else 0 end) as 'V21', ");
		//专业技术人员，55岁至59岁，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='006' AND c.IN_POST_LEVEL is null then 1 else 0 end) as 'V22', ");
		//专业技术人员，60岁及以上，高级岗位 三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='007' AND c.IN_POST_LEVEL='003' then 1 else 0 end) as 'W11', ");
		//专业技术人员，60岁及以上，高级岗位 四级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='007' AND c.IN_POST_LEVEL='004' then 1 else 0 end) as 'W12', ");
		//专业技术人员，60岁及以上，高级岗位 五级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='007' AND c.IN_POST_LEVEL='005' then 1 else 0 end) as 'W13', ");
		//专业技术人员，60岁及以上，高级岗位 六级 
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='007' AND c.IN_POST_LEVEL='006' then 1 else 0 end) as 'W14', ");
		//专业技术人员，60岁及以上，高级岗位 七级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='007' AND c.IN_POST_LEVEL='007' then 1 else 0 end) as 'W15', ");
		//专业技术人员，60岁及以上，中级岗位 八级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='007' AND c.IN_POST_LEVEL='008' then 1 else 0 end) as 'W16', ");
		//专业技术人员，60岁及以上，中级岗位 九级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='007' AND c.IN_POST_LEVEL='009' then 1 else 0 end) as 'W17', ");
		//专业技术人员，60岁及以上，中级岗位 十级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='007' AND c.IN_POST_LEVEL='010' then 1 else 0 end) as 'W18', ");
		//专业技术人员，60岁及以上，初级岗位 十一级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='007' AND c.IN_POST_LEVEL='011' then 1 else 0 end) as 'W19', ");
		//专业技术人员，60岁及以上，初级岗位 十二级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='007' AND c.IN_POST_LEVEL='012' then 1 else 0 end) as 'W20', ");
		//专业技术人员，60岁及以上，初级岗位 十三级
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='007' AND c.IN_POST_LEVEL='013' then 1 else 0 end) as 'W21', ");
		//专业技术人员，60岁及以上，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.AGEEREA='007' AND c.IN_POST_LEVEL is null then 1 else 0 end) as 'W22', ");
		
		sql.append("  ");
		//工勤技能人员，女
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F24', ");
		//工勤技能人员，女，少数民族
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_ETHNIC<>'001' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F25', ");
		//工勤技能人员，女，中共党员
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_POLITICAL_LANDSCAPE='001' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'H24', ");
		//工勤技能人员，女，少数民族
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_ETHNIC<>'001' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'G24', ");
		//工勤技能人员，少数民族
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G25', ");
		//工勤技能人员，少数民族，中共党员
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_ETHNIC<>'001' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H25', ");
		//工勤技能人员，女，一级岗位（高级技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='001' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F26', ");
		//工勤技能人员，女，二级岗位（技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='002' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F27', ");
		//工勤技能人员，女，三级岗位（高级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='003' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F28', ");
		//工勤技能人员，女，四级岗位（中级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='004' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F29', ");
		//工勤技能人员，女，五级岗位（初级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='005' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F30', ");
		//工勤技能人员，女，普通工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='006' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F31', ");
		//工勤技能人员，女，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='007' AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F32', ");
		//工勤技能人员，少数民族，一级岗位（高级技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='001' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G26', ");
		//工勤技能人员，少数民族，二级岗位（技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='002' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G27', ");
		//工勤技能人员，少数民族，三级岗位（高级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='003' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G28', ");
		//工勤技能人员，少数民族，四级岗位（中级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='004' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G29', ");
		//工勤技能人员，少数民族，五级岗位（初级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='005' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G30', ");
		//工勤技能人员，少数民族，普通工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='006' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G31', ");
		//工勤技能人员，少数民族，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='007' AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G32', ");
		//工勤技能人员，中共党员，一级岗位（高级技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='001' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H26', ");
		//工勤技能人员，中共党员，二级岗位（技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='002' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H27', ");
		//工勤技能人员，中共党员，三级岗位（高级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='003' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H28', ");
		//工勤技能人员，中共党员，四级岗位（中级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='004' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H29', ");
		//工勤技能人员，中共党员，五级岗位（初级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='005' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H30', ");
		//工勤技能人员，中共党员，普通工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='006' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H31', ");
		//工勤技能人员，中共党员，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='007' AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H32', ");
		//工勤技能人员，博士，女
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_GENDER='002' AND edu='006' then 1 else 0 end) as 'I24', ");
		//工勤技能人员，博士，少数民族
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_ETHNIC<>'001' AND edu='006' then 1 else 0 end) as 'I25', ");
		//工勤技能人员，硕士，女
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_GENDER='002' AND edu='005' then 1 else 0 end) as 'J24', ");
		//工勤技能人员，硕士，少数民族
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_ETHNIC<>'001' AND edu='005' then 1 else 0 end) as 'J25', ");
		//工勤技能人员，研究生，女
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_GENDER='002' AND edu IN ('005','006') then 1 else 0 end) as 'L24', ");
		//工勤技能人员，研究生，少数民族
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_ETHNIC<>'001' AND edu IN ('005','006') then 1 else 0 end) as 'L25', ");
		//工勤技能人员，大学本科，女
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_GENDER='002' AND edu='004' then 1 else 0 end) as 'M24', ");
		//工勤技能人员，大学本科，少数民族
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_ETHNIC<>'001' AND edu='004' then 1 else 0 end) as 'M25', ");
		//工勤技能人员，大学专科，女
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_GENDER='002' AND edu='003' then 1 else 0 end) as 'N24', ");
		//工勤技能人员，大学专科，少数民族
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_ETHNIC<>'001' AND edu='003' then 1 else 0 end) as 'N25', ");
		//工勤技能人员，中专，女
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_GENDER='002' AND edu='002' then 1 else 0 end) as 'O24', ");
		//工勤技能人员，中专，少数民族
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_ETHNIC<>'001' AND edu='002' then 1 else 0 end) as 'O25', ");
		//工勤技能人员，高中及以下，女
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_GENDER='002' AND edu='001' then 1 else 0 end) as 'P24', ");
		//工勤技能人员，高中及以下，少数民族
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_ETHNIC<>'001' AND edu='001' then 1 else 0 end) as 'P25', ");
		//工勤技能人员，博士，一级岗位（高级技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='001' AND edu='006' then 1 else 0 end) as 'I26', ");
		//工勤技能人员，博士，二级岗位（技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='002' AND edu='006' then 1 else 0 end) as 'I27', ");
		//工勤技能人员，博士，三级岗位（高级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='003' AND edu='006' then 1 else 0 end) as 'I28', ");
		//工勤技能人员，博士，四级岗位（中级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='004' AND edu='006' then 1 else 0 end) as 'I29', ");
		//工勤技能人员，博士，五级岗位（初级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='005' AND edu='006' then 1 else 0 end) as 'I30', ");
		//工勤技能人员，博士，普通工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='006' AND edu='006' then 1 else 0 end) as 'I31', ");
		//工勤技能人员，博士，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='007' AND edu='006' then 1 else 0 end) as 'I32', ");
		//工勤技能人员，硕士，一级岗位（高级技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='001' AND edu='005' then 1 else 0 end) as 'J26', ");
		//工勤技能人员，硕士，二级岗位（技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='002' AND edu='005' then 1 else 0 end) as 'J27', ");
		//工勤技能人员，硕士，三级岗位（高级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='003' AND edu='005' then 1 else 0 end) as 'J28', ");
		//工勤技能人员，硕士，四级岗位（中级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='004' AND edu='005' then 1 else 0 end) as 'J29', ");
		//工勤技能人员，硕士，五级岗位（初级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='005' AND edu='005' then 1 else 0 end) as 'J30', ");
		//工勤技能人员，硕士，普通工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='006' AND edu='005' then 1 else 0 end) as 'J31', ");
		//工勤技能人员，硕士，其他登记人员
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='007' AND edu='005' then 1 else 0 end) as 'J32', ");
		//工勤技能人员，研究生，一级岗位（高级技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='001' AND edu IN ('005','006') then 1 else 0 end) as 'L26', ");
		//工勤技能人员，研究生，二级岗位（技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='002' AND edu IN ('005','006') then 1 else 0 end) as 'L27', ");
		//工勤技能人员，研究生，三级岗位（高级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='003' AND edu IN ('005','006') then 1 else 0 end) as 'L28', ");
		//工勤技能人员，研究生，四级岗位（中级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='004' AND edu IN ('005','006') then 1 else 0 end) as 'L29', ");
		//工勤技能人员，研究生，五级岗位（初级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='005' AND edu IN ('005','006') then 1 else 0 end) as 'L30', ");
		//工勤技能人员，研究生，普通工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='006' AND edu IN ('005','006') then 1 else 0 end) as 'L31', ");
		//工勤技能人员，研究生，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='007' AND edu IN ('005','006') then 1 else 0 end) as 'L32', ");
		//工勤技能人员，大学本科，一级岗位（高级技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='001' AND edu='004' then 1 else 0 end) as 'M26', ");
		//工勤技能人员，大学本科，二级岗位（技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='002' AND edu='004' then 1 else 0 end) as 'M27', ");
		//工勤技能人员，大学本科，三级岗位（高级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='003' AND edu='004' then 1 else 0 end) as 'M28', ");
		//工勤技能人员，大学本科，四级岗位（中级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='004' AND edu='004' then 1 else 0 end) as 'M29', ");
		//工勤技能人员，大学本科，五级岗位（初级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='005' AND edu='004' then 1 else 0 end) as 'M30', ");
		//工勤技能人员，大学本科，普通工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='006' AND edu='004' then 1 else 0 end) as 'M31', ");
		//工勤技能人员，大学本科，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='007' AND edu='004' then 1 else 0 end) as 'M32', ");
		//工勤技能人员，大学专科，一级岗位（高级技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='001' AND edu='003' then 1 else 0 end) as 'N26', ");
		//工勤技能人员，大学专科，二级岗位（技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='002' AND edu='003' then 1 else 0 end) as 'N27', ");
		//工勤技能人员，大学专科，三级岗位（高级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='003' AND edu='003' then 1 else 0 end) as 'N28', ");
		//工勤技能人员，大学专科，四级岗位（中级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='004' AND edu='003' then 1 else 0 end) as 'N29', ");
		//工勤技能人员，大学专科，五级岗位（初级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='005' AND edu='003' then 1 else 0 end) as 'N30', ");
		//工勤技能人员，大学专科，普通工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='006' AND edu='003' then 1 else 0 end) as 'N31', ");
		//工勤技能人员，大学专科，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='007' AND edu='003' then 1 else 0 end) as 'N32', ");
		//工勤技能人员，中专，一级岗位（高级技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='001' AND edu='002' then 1 else 0 end) as 'O26', ");
		//工勤技能人员，中专，二级岗位（技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='002' AND edu='002' then 1 else 0 end) as 'O27', ");
		//工勤技能人员，中专，三级岗位（高级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='003' AND edu='002' then 1 else 0 end) as 'O28', ");
		//工勤技能人员，中专，四级岗位（中级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='004' AND edu='002' then 1 else 0 end) as 'O29', ");
		//工勤技能人员，中专，五级岗位（初级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='005' AND edu='002' then 1 else 0 end) as 'O30', ");
		//工勤技能人员，中专，普通工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='006' AND edu='002' then 1 else 0 end) as 'O31', ");
		//工勤技能人员，中专，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='007' AND edu='002' then 1 else 0 end) as 'O32', ");
		//工勤技能人员，高中及以下，一级岗位（高级技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='001' AND edu='001' then 1 else 0 end) as 'P26', ");
		//工勤技能人员，高中及以下，二级岗位（技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='002' AND edu='001' then 1 else 0 end) as 'P27', ");
		//工勤技能人员，高中及以下，三级岗位（高级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='003' AND edu='001' then 1 else 0 end) as 'P28', ");
		//工勤技能人员，高中及以下，四级岗位（中级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='004' AND edu='001' then 1 else 0 end) as 'P29', ");
		//工勤技能人员，高中及以下，五级岗位（初级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='005' AND edu='001' then 1 else 0 end) as 'P30', ");
		//工勤技能人员，高中及以下，普通工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='006' AND edu='001' then 1 else 0 end) as 'P31', ");
		//工勤技能人员，高中及以下，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='007' AND edu='001' then 1 else 0 end) as 'P32', ");
		//工勤技能人员，35岁及以下，女
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_GENDER='002' AND c.AGEEREA='001' then 1 else 0 end) as 'Q24', ");
		//工勤技能人员，35岁及以下，少数民族
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_ETHNIC<>'001' AND c.AGEEREA='001' then 1 else 0 end) as 'Q25', ");
		//工勤技能人员，35岁及以下，一级岗位（高级技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='001' AND c.AGEEREA='001' then 1 else 0 end) as 'Q26', ");
		//工勤技能人员，35岁及以下，二级岗位（技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='002' AND c.AGEEREA='001' then 1 else 0 end) as 'Q27', ");
		//工勤技能人员，35岁及以下，三级岗位（高级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='003' AND c.AGEEREA='001' then 1 else 0 end) as 'Q28', ");
		//工勤技能人员，35岁及以下，四级岗位（中级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='004' AND c.AGEEREA='001' then 1 else 0 end) as 'Q29', ");
		//工勤技能人员，35岁及以下，五级岗位（初级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='005' AND c.AGEEREA='001' then 1 else 0 end) as 'Q30', ");
		//工勤技能人员，35岁及以下，普通工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='006' AND c.AGEEREA='001' then 1 else 0 end) as 'Q31', ");
		//工勤技能人员，35岁及以下，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='007' AND c.AGEEREA='001' then 1 else 0 end) as 'Q32', ");
		//工勤技能人员，36岁至40岁，女
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_GENDER='002' AND c.AGEEREA='002' then 1 else 0 end) as 'R24', ");
		//工勤技能人员，36岁至40岁，少数民族
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_ETHNIC<>'001' AND c.AGEEREA='002' then 1 else 0 end) as 'R25', ");
		//工勤技能人员，36岁至40岁，一级岗位（高级技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='001' AND c.AGEEREA='002' then 1 else 0 end) as 'R26', ");
		//工勤技能人员，36岁至40岁，二级岗位（技师）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='002' AND c.AGEEREA='002' then 1 else 0 end) as 'R27', ");
		//工勤技能人员，36岁至40岁，三级岗位（高级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='003' AND c.AGEEREA='002' then 1 else 0 end) as 'R28', ");
		//工勤技能人员，36岁至40岁，四级岗位（中级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='004' AND c.AGEEREA='002' then 1 else 0 end) as 'R29', ");
		//工勤技能人员，36岁至40岁，五级岗位（初级工）
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='005' AND c.AGEEREA='002' then 1 else 0 end) as 'R30', ");
		//工勤技能人员，36岁至40岁，普通工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='006' AND c.AGEEREA='002' then 1 else 0 end) as 'R31', ");
		//工勤技能人员，36岁至40岁，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='007' AND c.AGEEREA='002' then 1 else 0 end) as 'R32', ");
		//工勤技能人员，41岁至45岁，女
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_GENDER='002' AND c.AGEEREA='003' then 1 else 0 end) as 'S24', ");
		//工勤技能人员，41岁至45岁，少数民族
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_ETHNIC<>'001' AND c.AGEEREA='003' then 1 else 0 end) as 'S25', ");
		//工勤技能人员，41岁至45岁，一级岗位 高级技师
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='001' AND c.AGEEREA='003' then 1 else 0 end) as 'S26', ");
		//工勤技能人员，41岁至45岁，二级岗位 技师
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='002' AND c.AGEEREA='003' then 1 else 0 end) as 'S27', ");
		//工勤技能人员，41岁至45岁，三级岗位 高级工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='003' AND c.AGEEREA='003' then 1 else 0 end) as 'S28', ");
		//工勤技能人员，41岁至45岁，四级岗位 中级工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='004' AND c.AGEEREA='003' then 1 else 0 end) as 'S29', ");
		//工勤技能人员，41岁至45岁，五级岗位 初级工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='005' AND c.AGEEREA='003' then 1 else 0 end) as 'S30', ");
		//工勤技能人员，41岁至45岁，普通工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='006' AND c.AGEEREA='003' then 1 else 0 end) as 'S31', ");
		//工勤技能人员，41岁至45岁，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='007' AND c.AGEEREA='003' then 1 else 0 end) as 'S32', ");
		//工勤技能人员，46岁至50岁，女
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_GENDER='002' AND c.AGEEREA='004' then 1 else 0 end) as 'T24', ");
		//工勤技能人员，46岁至50岁，少数民族
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_ETHNIC<>'001' AND c.AGEEREA='004' then 1 else 0 end) as 'T25', ");
		//工勤技能人员，46岁至50岁，一级岗位 高级技师
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='001' AND c.AGEEREA='004' then 1 else 0 end) as 'T26', ");
		//工勤技能人员，46岁至50岁，二级岗位 技师
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='002' AND c.AGEEREA='004' then 1 else 0 end) as 'T27', ");
		//工勤技能人员，46岁至50岁，三级岗位 高级工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='003' AND c.AGEEREA='004' then 1 else 0 end) as 'T28', ");
		//工勤技能人员，46岁至50岁，四级岗位 中级工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='004' AND c.AGEEREA='004' then 1 else 0 end) as 'T29', ");
		//工勤技能人员，46岁至50岁，五级岗位 初级工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='005' AND c.AGEEREA='004' then 1 else 0 end) as 'T30', ");
		//工勤技能人员，46岁至50岁，普通工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='006' AND c.AGEEREA='004' then 1 else 0 end) as 'T31', ");
		//工勤技能人员，46岁至50岁，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='007' AND c.AGEEREA='004' then 1 else 0 end) as 'T32', ");
		//工勤技能人员，51岁至54岁，女
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_GENDER='002' AND c.AGEEREA='005' then 1 else 0 end) as 'U24', ");
		//工勤技能人员，51岁至54岁，少数民族
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_ETHNIC<>'001' AND c.AGEEREA='005' then 1 else 0 end) as 'U25', ");
		//工勤技能人员，51岁至54岁，一级岗位 高级技师
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='001' AND c.AGEEREA='005' then 1 else 0 end) as 'U26', ");
		//工勤技能人员，51岁至54岁，二级岗位 技师
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='002' AND c.AGEEREA='005' then 1 else 0 end) as 'U27', ");
		//工勤技能人员，51岁至54岁，三级岗位 高级工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='003' AND c.AGEEREA='005' then 1 else 0 end) as 'U28', ");
		//工勤技能人员，51岁至54岁，四级岗位 中级工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='004' AND c.AGEEREA='005' then 1 else 0 end) as 'U29', ");
		//工勤技能人员，51岁至54岁，五级岗位 初级工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='005' AND c.AGEEREA='005' then 1 else 0 end) as 'U30', ");
		//工勤技能人员，51岁至54岁，普通工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='006' AND c.AGEEREA='005' then 1 else 0 end) as 'U31', ");
		//工勤技能人员，51岁至54岁，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='007' AND c.AGEEREA='005' then 1 else 0 end) as 'U32', ");
		//工勤技能人员，55岁至59岁，女
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_GENDER='002' AND c.AGEEREA='006' then 1 else 0 end) as 'V24', ");
		//工勤技能人员，55岁至59岁，少数民族
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_ETHNIC<>'001' AND c.AGEEREA='006' then 1 else 0 end) as 'V25', ");
		//工勤技能人员，55岁至59岁，一级岗位 高级技师
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='001' AND c.AGEEREA='006' then 1 else 0 end) as 'V26', ");
		//工勤技能人员，55岁至59岁，二级岗位 技师
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='002' AND c.AGEEREA='006' then 1 else 0 end) as 'V27', ");
		//工勤技能人员，55岁至59岁，三级岗位 高级工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='003' AND c.AGEEREA='006' then 1 else 0 end) as 'V28', ");
		//工勤技能人员，55岁至59岁，四级岗位 中级工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='004' AND c.AGEEREA='006' then 1 else 0 end) as 'V29', ");
		//工勤技能人员，55岁至59岁，五级岗位 初级工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='005' AND c.AGEEREA='006' then 1 else 0 end) as 'V30', ");
		//工勤技能人员，55岁至59岁，普通工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='006' AND c.AGEEREA='006' then 1 else 0 end) as 'V31', ");
		//工勤技能人员，55岁至59岁，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='007' AND c.AGEEREA='006' then 1 else 0 end) as 'V32', ");
		//工勤技能人员，60岁及以上，女
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_GENDER='002' AND c.AGEEREA='007' then 1 else 0 end) as 'W24', ");
		//工勤技能人员，60岁及以上，少数民族
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_ETHNIC<>'001' AND c.AGEEREA='007' then 1 else 0 end) as 'W25', ");
		//工勤技能人员，60岁及以上，一级岗位 高级技师
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='001' AND c.AGEEREA='007' then 1 else 0 end) as 'W26', ");
		//工勤技能人员，60岁及以上，二级岗位 技师
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='002' AND c.AGEEREA='007' then 1 else 0 end) as 'W27', ");
		//工勤技能人员，60岁及以上，三级岗位 高级工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='003' AND c.AGEEREA='007' then 1 else 0 end) as 'W28', ");
		//工勤技能人员，60岁及以上，四级岗位 中级工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='004' AND c.AGEEREA='007' then 1 else 0 end) as 'W29', ");
		//工勤技能人员，60岁及以上，五级岗位 初级工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='005' AND c.AGEEREA='007' then 1 else 0 end) as 'W30', ");
		//工勤技能人员，60岁及以上，普通工
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='006' AND c.AGEEREA='007' then 1 else 0 end) as 'W31', ");
		//工勤技能人员，60岁及以上，其他等级人员
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='007' AND c.AGEEREA='007' then 1 else 0 end) as 'W32', ");
		//工勤技能人员，其他从业人员，女
		sql.append(" sum(case when (c.IN_POST_KBN IS NULL OR c.IN_POST_KBN='') AND PERSONNEL_GENDER='002' then 1 else 0 end) as 'F33', ");
		//工勤技能人员，其他从业人员，少数民族
		sql.append(" sum(case when (c.IN_POST_KBN IS NULL OR c.IN_POST_KBN='') AND PERSONNEL_ETHNIC<>'001' then 1 else 0 end) as 'G33', ");
		//工勤技能人员，其他从业人员，中共党员
		sql.append(" sum(case when (c.IN_POST_KBN IS NULL OR c.IN_POST_KBN='') AND PERSONNEL_POLITICAL_LANDSCAPE='001' then 1 else 0 end) as 'H33', ");
		//工勤技能人员，其他从业人员，博士
		sql.append(" sum(case when (c.IN_POST_KBN IS NULL OR c.IN_POST_KBN='') AND edu='006' then 1 else 0 end) as 'I33', ");
		//工勤技能人员，其他从业人员，硕士
		sql.append(" sum(case when (c.IN_POST_KBN IS NULL OR c.IN_POST_KBN='') AND edu='005' then 1 else 0 end) as 'J33', ");
		//工勤技能人员，其他从业人员，研究生
		sql.append(" sum(case when (c.IN_POST_KBN IS NULL OR c.IN_POST_KBN='') AND edu IN ('005','006') then 1 else 0 end) as 'L33', ");
		//工勤技能人员，其他从业人员，大学本科
		sql.append(" sum(case when (c.IN_POST_KBN IS NULL OR c.IN_POST_KBN='') AND edu='004' then 1 else 0 end) as 'M33', ");
		//工勤技能人员，其他从业人员，大学专科
		sql.append(" sum(case when (c.IN_POST_KBN IS NULL OR c.IN_POST_KBN='') AND edu='003' then 1 else 0 end) as 'N33', ");
		//工勤技能人员，其他从业人员，中专
		sql.append(" sum(case when (c.IN_POST_KBN IS NULL OR c.IN_POST_KBN='') AND edu='002' then 1 else 0 end) as 'O33', ");
		//工勤技能人员，其他从业人员，高中及以下
		sql.append(" sum(case when (c.IN_POST_KBN IS NULL OR c.IN_POST_KBN='') AND edu='001' then 1 else 0 end) as 'P33', ");
		//工勤技能人员，其他从业人员，35岁及以下
		sql.append(" sum(case when (c.IN_POST_KBN IS NULL OR c.IN_POST_KBN='') AND c.AGEEREA='001' then 1 else 0 end) as 'Q33', ");
		//工勤技能人员，其他从业人员，36岁至40岁
		sql.append(" sum(case when (c.IN_POST_KBN IS NULL OR c.IN_POST_KBN='') AND c.AGEEREA='002' then 1 else 0 end) as 'R33', ");
		//工勤技能人员，其他从业人员，41岁至45岁
		sql.append(" sum(case when (c.IN_POST_KBN IS NULL OR c.IN_POST_KBN='') AND c.AGEEREA='003' then 1 else 0 end) as 'S33', ");
		//工勤技能人员，其他从业人员，46岁至50岁
		sql.append(" sum(case when (c.IN_POST_KBN IS NULL OR c.IN_POST_KBN='') AND c.AGEEREA='004' then 1 else 0 end) as 'T33', ");
		//工勤技能人员，其他从业人员，51岁至54岁
		sql.append(" sum(case when (c.IN_POST_KBN IS NULL OR c.IN_POST_KBN='') AND c.AGEEREA='005' then 1 else 0 end) as 'U33', ");
		//工勤技能人员，其他从业人员，55岁至59岁
		sql.append(" sum(case when (c.IN_POST_KBN IS NULL OR c.IN_POST_KBN='') AND c.AGEEREA='006' then 1 else 0 end) as 'V33', ");
		//工勤技能人员，其他从业人员，60岁及以上
		sql.append(" sum(case when (c.IN_POST_KBN IS NULL OR c.IN_POST_KBN='') AND c.AGEEREA='007' then 1 else 0 end) as 'W33', ");
		sql.append(" '"+rlgl200101BeanNew.getSel_year()+"' + '年' as 'O7' ");
		sql.append(" from M_TB12_PERSONNEL tb ");
		sql.append(" LEFT JOIN  ");
		sql.append(" (SELECT  ");
		sql.append(" age.AGEEREA,age.IN_POST_LEVEL,age.IN_POST_KBN,age.PERSONNEL_ID,age.EDU,age.CERTIFICATE_NO ");
		sql.append(" from (SELECT  ");
		sql.append(" CASE WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 ))  ) - YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ,  ");
		sql.append(" 5 ) < RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ,  ");
		sql.append(" 5 ) ) <=35 THEN '001' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) - YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ,  ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ,5 ) )  ");
		sql.append(" BETWEEN 36  ");
		sql.append(" AND 40 THEN '002' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ,  ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ,  ");
		sql.append(" 5 ) )  ");
		sql.append(" BETWEEN 41  ");
		sql.append(" AND 45 THEN '003' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ,  ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ,  ");
		sql.append(" 5 ) )  ");
		sql.append(" BETWEEN 46  ");
		sql.append(" AND 50 THEN '004' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ,  ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ,  ");
		sql.append(" 5 ) )  ");
		sql.append(" BETWEEN 51  ");
		sql.append(" AND 54 THEN '005' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ,  ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ,5 ) )  ");
		sql.append(" BETWEEN 55  ");
		sql.append(" AND 59 THEN '006' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ,  ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,  ");
		sql.append(" '%Y-%m-%d' ) , 5 ) )  ");
		sql.append(" >=60 THEN '007'  ");
		sql.append(" ELSE 0 END AS AGEEREA , ");
		sql.append(" ( ");
		sql.append(" SELECT EDUCATIONAL_BG  ");
		sql.append(" FROM m_tb15_personnel_edu_info ");
		sql.append(" WHERE  ");
		sql.append(" PERSONNEL_ID=A.PERSONNEL_ID  ");
		sql.append(" and  ");
		sql.append(" GRADUATION_TIME =  ");
		sql.append(" ( ");
		sql.append(" Select Max(GRADUATION_TIME)  ");
		sql.append(" From  m_tb15_personnel_edu_info  ");
		sql.append(" WHERE ");
		sql.append(" PERSONNEL_ID=A.PERSONNEL_ID  ");
		sql.append(" ) ");
		sql.append(" ) AS edu , ");
		sql.append("  ");
		sql.append(" case when m.IN_POST_LEVEL = '' then null else m.IN_POST_LEVEL end as IN_POST_LEVEL, ");
		sql.append("  ");
		sql.append(" m.IN_POST_KBN, ");
		sql.append(" D.CERTIFICATE_NO, ");
		sql.append(" A.PERSONNEL_ID  ");
		sql.append("  ");
		sql.append(" FROM  ");
		sql.append(" M_TB12_PERSONNEL as A  ");
		sql.append(" LEFT JOIN ");
		sql.append(" m_tb09_irin m ");
		sql.append(" on m.PERSON_NO=A.PERSONNEL_ID  ");
		sql.append(" LEFT JOIN m_tb19_personnel_practitioners_info D ");
		sql.append(" on D.PERSONNEL_ID=A.PERSONNEL_ID  ");
		// 年度
		sql.append(" where substr(a.LOGIN_DATE,1,4)<='"
				+ rlgl200101BeanNew.getSel_year() + "' ");
		sql.append(" and substr(m.LOGIN_DATE,1,4)<='"
				+ rlgl200101BeanNew.getSel_year() + "' ");
		sql.append(" and  ");
		// 管辖单位
		sql.append(" (A.PERSONNEL_UNIT in  ");
		sql.append(" (SELECT unit_no FROM m_tb04_unit WHERE ESCROW_UNIT_NO = '"
				+ rlgl200101BeanNew.getUnit_no()
				+ "' AND DEL_KBN = 0 AND UNIT_STATUS = '1') ");
		sql.append(" or A.PERSONNEL_UNIT LIKE CONCAT('', '"
				+ rlgl200101BeanNew.getUnit_no() + "', '%') ");
		sql.append(" ) ");
		sql.append(" ) as age ");
		sql.append(" ) as c ");
		sql.append(" on tb.PERSONNEL_ID =c.PERSONNEL_ID  ");
		sql.append(" where  ");
		sql.append(" substr(tb.LOGIN_DATE,1,4)<='"
				+ rlgl200101BeanNew.getSel_year() + "' ");

		return sql.toString();
	}

    public String EditUnitBaseInfoSql04(Rlgl200101Bean rlgl200101BeanNew) {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		//管理岗位-一级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='001' then 1 else 0 end) as 'D13',");
		//管理岗位-一级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='001' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'E13',");
		//专业技术岗位-一级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='001' then 1 else 0 end) as 'F13',");
		//专业技术岗位-一级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='001' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'G13',");
		//专业技术敢为兼在管理岗位的-一级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND c.IN_POST_LEVEL='001' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'H13',");
		//工勤技能岗位-一级-数量
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='001' then 1 else 0 end) as 'I13',");
		//工勤技能岗位-一级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='001' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'J13',");
		//管理岗位-二级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='002' then 1 else 0 end) as 'D14',");
		//管理岗位-二级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='002' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'E14',");
		//专业技术岗位-二级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='002' then 1 else 0 end) as 'F14',");
		//专业技术岗位-二级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='002' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'G14',");
		//专业技术敢为兼在管理岗位的-二级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND c.IN_POST_LEVEL='002' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'H14',");
		//工勤技能岗位-二级-数量
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='002' then 1 else 0 end) as 'I14',");
		//工勤技能岗位-二级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='002' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'J14',");
		//管理岗位-三级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='003' then 1 else 0 end) as 'D15',");
		//管理岗位-三级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='003' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'E15',");
		//专业技术岗位-三级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='003' then 1 else 0 end) as 'F15',");
		//专业技术岗位-三级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='003' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'G15',");
		//专业技术敢为兼在管理岗位的-三级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND c.IN_POST_LEVEL='003' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'H15',");
		//工勤技能岗位-三级-数量
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='003' then 1 else 0 end) as 'I15',");
		//工勤技能岗位-三级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='003' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'J15',");
		//管理岗位-四级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='004' then 1 else 0 end) as 'D16',");
		//管理岗位-四级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='004' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'E16',");
		//专业技术岗位-四级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='004' then 1 else 0 end) as 'F16',");
		//专业技术岗位-四级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='004' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'G16',");
		//专业技术敢为兼在管理岗位的-四级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND c.IN_POST_LEVEL='004' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'H16',");
		//工勤技能岗位-四级-数量
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='004' then 1 else 0 end) as 'I16',");
		//工勤技能岗位-四级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='004' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'J16',");
		//管理岗位-五级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='005' then 1 else 0 end) as 'D17',");
		//管理岗位-五级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='005' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'E17',");
		//专业技术岗位-五级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='005' then 1 else 0 end) as 'F17',");
		//专业技术岗位-五级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='005' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'G17',");
		//专业技术敢为兼在管理岗位的-五级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND c.IN_POST_LEVEL='005' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'H17',");
		//工勤技能岗位-五级-数量
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='005' then 1 else 0 end) as 'I17',");
		//工勤技能岗位-五级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='005' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'J17',");
		//管理岗位-六级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='006' then 1 else 0 end) as 'D18',");
		//管理岗位-六级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='006' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'E18',");
		//专业技术岗位-六级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='006' then 1 else 0 end) as 'F18',");
		//专业技术岗位-六级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='006' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'G18',");
		//专业技术敢为兼在管理岗位的-六级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND c.IN_POST_LEVEL='006' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'H18',");
		//工勤技能岗位-普通工-数量
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='006' then 1 else 0 end) as 'I18',");
		//工勤技能岗位-普通工-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='006' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'J18',");
		//管理岗位-七级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='007' then 1 else 0 end) as 'D19',");
		//管理岗位-七级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='007' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'E19',");
		//专业技术岗位-七级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='007' then 1 else 0 end) as 'F19',");
		//专业技术岗位-七级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='007' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'G19',");
		//专业技术敢为兼在管理岗位的-七级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND c.IN_POST_LEVEL='007' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'H19',");
		//管理岗位-八级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='008' then 1 else 0 end) as 'D20',");
		//管理岗位-八级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='008' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'E20',");
		//专业技术岗位-八级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='008' then 1 else 0 end) as 'F20',");
		//专业技术岗位-八级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='008' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'G20',");
		//专业技术敢为兼在管理岗位的-八级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND c.IN_POST_LEVEL='008' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'H20',");
		//管理岗位-九级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='009' then 1 else 0 end) as 'D21',");
		//管理岗位-九级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='009' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'E21',");
		//专业技术岗位-九级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='009' then 1 else 0 end) as 'F21',");
		//专业技术岗位-九级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='009' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'G21',");
		//专业技术敢为兼在管理岗位的-九级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND c.IN_POST_LEVEL='008' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'H21',");
		//管理岗位-十级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='010' then 1 else 0 end) as 'D22',");
		//管理岗位-十级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL='010' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'E22',");
		//专业技术岗位-十级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='010' then 1 else 0 end) as 'F22',");
		//专业技术岗位-十级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='010' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'G22',");
		//专业技术敢为兼在管理岗位的-十级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND c.IN_POST_LEVEL='010' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'H22',");
		//专业技术岗位-十一级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='011' then 1 else 0 end) as 'F23',");
		//专业技术岗位-十一级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='011' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'G23',");
		//专业技术敢为兼在管理岗位的-十一级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND c.IN_POST_LEVEL='011' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'H23',");
		//专业技术岗位-十二级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='012' then 1 else 0 end) as 'F24',");
		//专业技术岗位-十二级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='012' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'G24',");
		//专业技术敢为兼在管理岗位的-十二级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND c.IN_POST_LEVEL='012' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'H24',");
		//专业技术岗位-十三级-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='013' then 1 else 0 end) as 'F25',");
		//专业技术岗位-十三级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL='013' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'G25',");
		//专业技术敢为兼在管理岗位的-十三级-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND c.IN_POST_LEVEL='013' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'H25',");
		//管理岗位-其他-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL in('011','012',NULL)then 1 else 0 end) as 'D26',");
		//管理岗位-其他-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') AND c.IN_POST_LEVEL in('011','012',NULL) AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'E26',");
		//专业技术岗位-其他-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL in('014',null) then 1 else 0 end) as 'F26',");
		//专业技术岗位-其他-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND c.IN_POST_LEVEL in('014',null) AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'G26',");
		//专业技术敢为兼在管理岗位的-其他-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND c.IN_POST_LEVEL in('014',NULL) AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'H26',");
		//工勤技能岗位-其他-数量
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='007' then 1 else 0 end) as 'I26',");
		//工勤技能岗位-其他-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND c.IN_POST_LEVEL='007' AND PERSONNEL_STATUS='001' then 1 else 0 end) as 'J26',");
		//管理岗位-增加-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') and substr(tb.LOGIN_DATE,1,4)='"+rlgl200101BeanNew.getSel_year()+"' then 1 else 0 end) as 'D28',");
		//管理岗位-增加-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004')  AND PERSONNEL_STATUS='001' and substr(tb.LOGIN_DATE,1,4)='"+rlgl200101BeanNew.getSel_year()+"' then 1 else 0 end) as 'E28',");
		//专业技术岗位-增加-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') and substr(tb.LOGIN_DATE,1,4)='"+rlgl200101BeanNew.getSel_year()+"' then 1 else 0 end) as 'F28',");
		//专业技术岗位-增加-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') AND PERSONNEL_STATUS='001' and substr(tb.LOGIN_DATE,1,4)='"+rlgl200101BeanNew.getSel_year()+"' then 1 else 0 end) as 'G28',");
		//专业技术敢为兼在管理岗位的-增加-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '004' AND PERSONNEL_STATUS='001' and substr(tb.LOGIN_DATE,1,4)='"+rlgl200101BeanNew.getSel_year()+"' then 1 else 0 end) as 'H28',");
		//工勤技能岗位-增加-数量
		sql.append(" sum(case when c.IN_POST_KBN = '007' and substr(tb.LOGIN_DATE,1,4)='"+rlgl200101BeanNew.getSel_year()+"' then 1 else 0 end) as 'I28',");
		//工勤技能岗位-增加-在岗人数
		sql.append(" sum(case when c.IN_POST_KBN = '007' AND PERSONNEL_STATUS='001' and substr(tb.LOGIN_DATE,1,4)='"+rlgl200101BeanNew.getSel_year()+"' then 1 else 0 end) as 'J28',");
		//管理岗位-减少-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('006','004') and substr(tb.LOGIN_DATE,1,4)='"+rlgl200101BeanNew.getSel_year()+"' AND PERSONNEL_STATUS in('003','004','005','006') then 1 else 0 end) as 'D29',");
		//专业技术岗位-减少-数量
		sql.append(" sum(case when c.IN_POST_KBN IN ('027','004') and substr(tb.LOGIN_DATE,1,4)='"+rlgl200101BeanNew.getSel_year()+"' AND PERSONNEL_STATUS in('003','004','005','006') then 1 else 0 end) as 'F29',");
		//工勤技能岗位-减少-数量
		sql.append(" sum(case when c.IN_POST_KBN = '007' and substr(tb.LOGIN_DATE,1,4)='"+rlgl200101BeanNew.getSel_year()+"' AND PERSONNEL_STATUS in('003','004','005','006') then 1 else 0 end) as 'I29',");
		sql.append(" '"+rlgl200101BeanNew.getSel_year()+"' as 'E7' ");
		sql.append(" from M_TB12_PERSONNEL tb");
		sql.append(" LEFT JOIN ");
		sql.append(" (SELECT ");
		sql.append(" age.AGEEREA,age.IN_POST_LEVEL,age.IN_POST_KBN,age.PERSONNEL_ID,age.EDU,age.CERTIFICATE_NO");
		sql.append(" from (SELECT ");
		sql.append(" CASE WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) - YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) , ");
		sql.append(" 5 ) < RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) , ");
		sql.append(" 5 ) ) <=35 THEN '001' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) - YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) , ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) ,5 ) ) ");
		sql.append(" BETWEEN 36 ");
		sql.append(" AND 40 THEN '002' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) , ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) , ");
		sql.append(" 5 ) ) ");
		sql.append(" BETWEEN 41 ");
		sql.append(" AND 45 THEN '003' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) , ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) , ");
		sql.append(" 5 ) ) ");
		sql.append(" BETWEEN 46 ");
		sql.append(" AND 50 THEN '004' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) , ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) , ");
		sql.append(" 5 ) ) ");
		sql.append(" BETWEEN 51 ");
		sql.append(" AND 54 THEN '005' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) , ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) ,5 ) ) ");
		sql.append(" BETWEEN 55 ");
		sql.append(" AND 59 THEN '006' WHEN ( YEAR ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CONCAT('"+rlgl200101BeanNew.getSel_year()+"',RIGHT( DATE_FORMAT ( PERSONNEL_BIRTHDAY ,'%Y-%m-%d' ) ,6 )) , ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) , 5 ) ) ");
		sql.append(" >=60 THEN '007' ");
		sql.append(" ELSE 0 END AS AGEEREA ,");
		sql.append(" (");
		sql.append(" SELECT EDUCATIONAL_BG ");
		sql.append(" FROM m_tb15_personnel_edu_info");
		sql.append(" WHERE ");
		sql.append(" PERSONNEL_ID=A.PERSONNEL_ID ");
		sql.append(" and");
		sql.append(" GRADUATION_TIME =");
		sql.append(" (");
		sql.append(" Select Max(GRADUATION_TIME)");
		sql.append(" From  m_tb15_personnel_edu_info ");
		sql.append(" WHERE");
		sql.append(" PERSONNEL_ID=A.PERSONNEL_ID ");
		sql.append(" )");
		sql.append(" ) AS edu ,");
		sql.append(" case when m.IN_POST_LEVEL = '' then null else m.IN_POST_LEVEL end as IN_POST_LEVEL,");
		sql.append(" m.IN_POST_KBN,");
		sql.append(" D.CERTIFICATE_NO,");
		sql.append(" A.PERSONNEL_ID ");
		sql.append(" FROM ");
		sql.append(" M_TB12_PERSONNEL as A ");
		sql.append(" LEFT JOIN");
		sql.append(" m_tb09_irin m");
		sql.append(" on m.PERSON_NO=A.PERSONNEL_ID ");
		sql.append(" LEFT JOIN m_tb19_personnel_practitioners_info D");
		sql.append(" on D.PERSONNEL_ID=A.PERSONNEL_ID ");
		sql.append(" where substr(a.LOGIN_DATE,1,4)<="+rlgl200101BeanNew.getSel_year());
		sql.append(" and substr(m.LOGIN_DATE,1,4)<="+rlgl200101BeanNew.getSel_year());
		sql.append(" and ");
		sql.append(" (A.PERSONNEL_UNIT in ");
		sql.append(" (SELECT unit_no FROM m_tb04_unit WHERE ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no()+"' AND DEL_KBN = 0 AND UNIT_STATUS = '1')");
		sql.append(" or A.PERSONNEL_UNIT LIKE CONCAT('', '"+rlgl200101BeanNew.getUnit_no()+"', '%')");
		sql.append(" )");
		sql.append(" ) as age");
		sql.append(" ) as c");
		sql.append(" on tb.PERSONNEL_ID =c.PERSONNEL_ID ");
		sql.append(" where ");
		sql.append(" substr(tb.LOGIN_DATE,1,4)<="+rlgl200101BeanNew.getSel_year());


		return sql.toString();
	}
	public String EditUnitBaseInfoSql09(Rlgl200101Bean rlgl200101BeanNew) {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		//管理岗位-一级职员（部级正职）-卫生
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='001' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'E38', ");
		//管理岗位-二级职员（部级副职）-卫生
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='002' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'F38', ");
		//管理岗位-三级职员（厅级正职）-卫生
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='003' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'G38', ");
		//管理岗位-四级职员（厅级副职）-卫生
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='004' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'H38', ");
		//管理岗位-五级职员（处级正职）-卫生
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='005' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'I38', ");
		//管理岗位-六级职员（处级副职）-卫生
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='006' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'J38', ");
		//管理岗位-七级职员（科级正职）-卫生
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='007' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'K38', ");
		//管理岗位-八级职员（科级副职）-卫生
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='008' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'L38', ");
		//管理岗位-九级职员（科员）-卫生
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='009' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'M38', ");
		//管理岗位-十级职员（办事员）-卫生
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='010' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'N38', ");
		//管理岗位-其他等级人员-卫生
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='011' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'O38', ");
		//管理岗位-其他从业人员-卫生
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='012' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'P38', ");
		//中央
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and b.UNIT_MANAGE_SCALE='061' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'Q38', ");
		//省（区、市）
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and b.UNIT_MANAGE_SCALE='062' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'R38', ");
		//地（市、州、盟）
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and b.UNIT_MANAGE_SCALE='063' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'S38', ");
		//县（市、区、旗）
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and b.UNIT_MANAGE_SCALE='064' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'T38', ");
		//乡（镇）
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and b.UNIT_MANAGE_SCALE='998' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'U38', ");
		//专业技术人员-一级-卫生
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='001' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'W38', ");
		//专业技术人员-二级-卫生
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='002' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'X38', ");
		//专业技术人员-三级-卫生
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='003' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'Y38', ");
		//专业技术人员-四级-卫生
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='004' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'Z38', ");
		//专业技术人员-五级-卫生
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='005' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AA38', ");
		//专业技术人员-六级-卫生
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='006' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AB38', ");
		//专业技术人员-七级-卫生
		sql.append(" SUM(CASE WHEN b.SYSTEM_SORT='176' and SYSTEM_SORT_TWO='024' and a.IN_POST_LEVEL='007' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AC38', ");
		sql.append(" '"+rlgl200101BeanNew.getSel_year()+"' as 'N7' ");
		sql.append(" FROM ");
		sql.append(" m_tb09_irin a ");
		sql.append(" LEFT JOIN ");
		sql.append(" m_tb04_unit b ");
		sql.append(" ON ");
		sql.append(" a.UNIT_NO=b.UNIT_NO ");
		sql.append(" LEFT JOIN ");
		sql.append(" m_tb19_personnel_practitioners_info c ");
		sql.append(" ON ");
		sql.append(" a.PERSON_NO=c.PERSONNEL_ID ");
		sql.append(" WHERE ");
		sql.append(" substr(a.LOGIN_DATE,1,4)="+rlgl200101BeanNew.getSel_year());
		sql.append(" and ");
		sql.append(" (a.UNIT_NO in  ");
		sql.append(" (SELECT unit_no FROM m_tb04_unit WHERE ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no()+"' AND DEL_KBN = 0 AND UNIT_STATUS = '1') ");
		sql.append(" or a.UNIT_NO LIKE CONCAT('', '"+rlgl200101BeanNew.getUnit_no()+"', '%') ");
		sql.append(" ) ");

		return sql.toString();
	}
	public String EditUnitBaseInfoSql061(Rlgl200101Bean rlgl200101BeanNew) {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		//管理人员-一级职员（部级正职）卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='001' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'D21',");
		//管理人员-二级职员（部级副职）卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='002' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'E21',");
		//管理人员-三级职员（厅级正职）卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='003' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'F21',");
		//管理人员-四级职员（厅级副职）卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='004' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'G21',");
		//管理人员-五级职员（处级正职）卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='005' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'H21',");
		//管理人员-六级职员（处级副职）卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='006' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'I21',");
		//管理人员-七级职员（科级正职）卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='007' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'J21',");
		//管理人员-八级职员（科级副职）卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='008' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'K21',");
		//管理人员-九级职员（科员）卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='009' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'L21',");
		//管理人员-十级职员（办事员）卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='010' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'M21',");
		//管理人员-其他等级人员    卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='011' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'N21',");
		//管理人员-其他从业人员   卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='012' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'O21',");
		//中央   卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and b.UNIT_MANAGE_SCALE='061' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'P21',");
		//省（区、市）   卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and b.UNIT_MANAGE_SCALE='062' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'Q21',");
		//地（市、州、盟）   卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and b.UNIT_MANAGE_SCALE='063' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'R21',");
		//县（市、区、旗）   卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and b.UNIT_MANAGE_SCALE='064' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'S21',");
		//乡（镇）  卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and b.UNIT_MANAGE_SCALE='998' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'T21',");
		//专职技术人员-一级-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='001' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'V21',");
		//专职技术人员-二级-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='002' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'W21',");
		//专职技术人员-三级-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='003' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'X21',");
		//专职技术人员-四级-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='004' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'Y21',");
		//专职技术人员-五级-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='005' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'Z21',");
		//专职技术人员-六级-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='006' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AA21',");
		//专职技术人员-七级-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='007' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AB21',");
		//专职技术人员-八级-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='008' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AC21',");
		//专职技术人员-九级-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='009' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AD21',");
		//专职技术人员-十级-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' and a.IN_POST_LEVEL='010' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AE21',");
		//管理人员-一级职员（部级正职） 公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='001' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'D22',");
		//管理人员-二级职员（部级副职） 公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='002' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'E22',");
		//管理人员-三级职员（厅级正职） 公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='003' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'F22',");
		//管理人员-四级职员（厅级副职）公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='004' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'G22',");
		//管理人员-五级职员（处级正职）公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='005' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'H22',");
		//管理人员-六级职员（处级副职）公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='006' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'I22',");
		//管理人员-七级职员（科级正职）公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='007' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'J22',");
		//管理人员-八级职员（科级副职）公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='008' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'K22',");
		//管理人员-九级职员（科员）公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='009' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'L22',");
		//管理人员-十级职员（办事员）公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='010' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'M22',");
		//管理人员-其他等级人员    公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='011' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'N22',");
		//管理人员-其他从业人员   公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='012' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'O22',");
		//中央   公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and b.UNIT_MANAGE_SCALE='061' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'P22',");
		//省（区、市） 公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and b.UNIT_MANAGE_SCALE='062' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'Q22',");
		//地（市、州、盟）公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and b.UNIT_MANAGE_SCALE='063' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'R22',");
		//县（市、区、旗）公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and b.UNIT_MANAGE_SCALE='064' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'S22',");
		//乡（镇）公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and b.UNIT_MANAGE_SCALE='998' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'T22',");
		//专职技术人员-一级-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='001' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'V22',");
		//专职技术人员-二级-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='002' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'W22',");
		//专职技术人员-三级-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='003' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'X22',");
		//专职技术人员-四级-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='004' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'Y22',");
		//专职技术人员-五级-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='005' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'Z22',");
		//专职技术人员-六级-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='006' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AA22',");
		//专职技术人员-七级-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='007' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AB22',");
		//专职技术人员-八级-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='008' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AC22',");
		//专职技术人员-九级-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='009' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AD22',");
		//专职技术人员-十级-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' and a.IN_POST_LEVEL='010' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AE22',");
		//管理人员-一级职员（部级正职）社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='001' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'D23',");
		//管理人员-二级职员（部级副职）社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='002' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'E23',");
		//管理人员-三级职员（厅级正职）社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='003' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'F23',");
		//管理人员-四级职员（厅级副职）社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='004' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'G23',");
		//管理人员-五级职员（处级正职）社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='005' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'H23',");
		//管理人员-六级职员（处级副职）社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='006' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'I23',");
		//管理人员-七级职员（科级正职）社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='007' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'J23',");
		//管理人员-八级职员（科级副职）社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='008' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'K23',");
		//管理人员-九级职员（科员）社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='009' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'L23',");
		//管理人员-十级职员（办事员）社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='010' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'M23',");
		//管理人员-其他等级人员     社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='011' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'N23',");
		//管理人员-其他从业人员   社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='012' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'O23',");
		//中央   社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and b.UNIT_MANAGE_SCALE='061' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'P23',");
		//省（区、市）社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and b.UNIT_MANAGE_SCALE='062' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'Q23',");
		//地（市、州、盟） 社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and b.UNIT_MANAGE_SCALE='063' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'R23',");
		//县（市、区、旗）社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and b.UNIT_MANAGE_SCALE='064' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'S23',");
		//乡（镇）社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and b.UNIT_MANAGE_SCALE='998' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'T23',");
		//专职技术人员-一级-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='001' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'V23',");
		//专职技术人员-二级-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='002' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'W23',");
		//专职技术人员-三级-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='003' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'X23',");
		//专职技术人员-四级-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='004' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'Y23',");
		//专职技术人员-五级-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='005' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'Z23',");
		//专职技术人员-六级-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='006' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AA23',");
		//专职技术人员-七级-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='007' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AB23',");
		//专职技术人员-八级-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='008' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AC23',");
		//专职技术人员-九级-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='009' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AD23',");
		//专职技术人员-十级-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' and a.IN_POST_LEVEL='010' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AE23',");
		//管理人员-一级职员（部级正职）门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='001' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'D24',");
		//管理人员-二级职员（部级副职）门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='002' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'E24',");
		//管理人员-三级职员（厅级正职）门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='003' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'F24',");
		//管理人员-四级职员（厅级副职）门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='004' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'G24',");
		//管理人员-五级职员（处级正职）门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='005' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'H24',");
		//管理人员-六级职员（处级副职）门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='006' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'I24',");
		//管理人员-七级职员（科级正职）门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='007' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'J24',");
		//管理人员-八级职员（科级副职）门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='008' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'K24',");
		//管理人员-九级职员（科员）门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='009' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'L24',");
		//管理人员-十级职员（办事员）门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='010' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'M24',");
		//管理人员-其他等级人员     门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='011' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'N24',");
		//管理人员-其他从业人员     门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='012' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'O24',");
		//中央    门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and b.UNIT_MANAGE_SCALE='061' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'P24',");
		//省（区、市）门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and b.UNIT_MANAGE_SCALE='062' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'Q24',");
		//地（市、州、盟）门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and b.UNIT_MANAGE_SCALE='063' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'R24',");
		//县（市、区、旗）门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and b.UNIT_MANAGE_SCALE='064' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'S24',");
		//乡（镇）门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and b.UNIT_MANAGE_SCALE='998' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'T24',");
		//专职技术人员-一级-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='001' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'V24',");
		//专职技术人员-二级-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='002' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'W24',");
		//专职技术人员-三级-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='003' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'X24',");
		//专职技术人员-四级-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='004' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'Y24',");
		//专职技术人员-五级-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='005' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'Z24',");
		//专职技术人员-六级-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='006' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AA24',");
		//专职技术人员-七级-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='007' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AB24',");
		//专职技术人员-八级-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='008' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AC24',");
		//专职技术人员-九级-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='009' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AD24',");
		//专职技术人员-十级-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' and a.IN_POST_LEVEL='010' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AE24',");
		//管理人员-一级职员（部级正职）社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='001' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'D25',");
		//管理人员-二级职员（部级副职）社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='002' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'E25',");
		//管理人员-三级职员（厅级正职）社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='003' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'F25',");
		//管理人员-四级职员（厅级副职）社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='004' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'G25',");
		//管理人员-五级职员（处级正职）社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='005' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'H25',");
		//管理人员-六级职员（处级副职）社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='006' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'I25',");
		//管理人员-七级职员（科级正职）社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='007' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'J25',");
		//管理人员-八级职员（科级副职）社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='008' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'K25',");
		//管理人员-九级职员（科员）社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='009' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'L25',");
		//管理人员-十级职员（办事员）社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='010' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'M25',");
		//管理人员-其他等级人员     社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='011' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'N25',");
		//管理人员-其他从业人员     社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='012' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'O25',");
		//中央     社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and b.UNIT_MANAGE_SCALE='061' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'P25',");
		//省（区、市）社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and b.UNIT_MANAGE_SCALE='062' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'Q25',");
		//地（市、州、盟）社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and b.UNIT_MANAGE_SCALE='063' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'R25',");
		//县（市、区、旗）社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and b.UNIT_MANAGE_SCALE='064' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'S25',");
		//乡（镇）社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and b.UNIT_MANAGE_SCALE='998' and a.in_post_kbn IN ('006','004') then 1 else 0 end) as 'T25',");
		//专职技术人员-一级-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='001' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'V25',");
		//专职技术人员-二级-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='002' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'W25',");
		//专职技术人员-三级-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='003' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'X25',");
		//专职技术人员-四级-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='004' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'Y25',");
		//专职技术人员-五级-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='005' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'Z25',");
		//专职技术人员-六级-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='006' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AA25',");
		//专职技术人员-七级-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='007' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AB25',");
		//专职技术人员-八级-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='008' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AC25',");
		//专职技术人员-九级-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='009' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AD25',");
		//专职技术人员-十级-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' and a.IN_POST_LEVEL='010' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'AE25',");
		sql.append(" '"+rlgl200101BeanNew.getSel_year()+"' as 'N7' ");
		sql.append(" FROM");
		sql.append(" m_tb09_irin a");
		sql.append(" LEFT JOIN");
		sql.append(" m_tb04_unit b");
		sql.append(" ON");
		sql.append(" a.UNIT_NO=b.UNIT_NO");
		sql.append(" WHERE");
		sql.append(" substr(a.LOGIN_DATE,1,4)="+rlgl200101BeanNew.getSel_year());
		sql.append(" and ");
		sql.append(" (a.UNIT_NO in ");
		sql.append(" (SELECT unit_no FROM m_tb04_unit WHERE ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no()+"' AND DEL_KBN = 0 AND UNIT_STATUS = '1')");
		sql.append(" or a.UNIT_NO LIKE CONCAT('', '"+rlgl200101BeanNew.getUnit_no()+"', '%'))");

		return sql.toString();
	}
	public String EditUnitBaseInfoSql063(Rlgl200101Bean rlgl200101BeanNew) {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		//专业技术人员-十一级-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND a.IN_POST_LEVEL='011' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'C21',");
		//专业技术人员-十二级-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND a.IN_POST_LEVEL='012' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'D21',");
		//专业技术人员-十三级-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND a.IN_POST_LEVEL='013' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'E21',");
		//专业技术人员-其他等级-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND a.IN_POST_LEVEL='014' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'F21',");
		//专业技术人员-中央-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.UNIT_MANAGE_SCALE='061' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'H21',");
		//专业技术人员-省（区、市）-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.UNIT_MANAGE_SCALE='062' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'I21',");
		//专业技术人员-地（市、州、盟）-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.UNIT_MANAGE_SCALE='063' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'J21',");
		//专业技术人员-县（市、区、旗）-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.UNIT_MANAGE_SCALE='064' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'K21',");
		//专业技术人员-乡（镇）-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.UNIT_MANAGE_SCALE='998' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'L21',");
		//专业技术人员-在管理岗位工作的-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND a.in_post_kbn ='004' then 1 else 0 end) as 'M21',");
		//专业技术人员-具有职业资格的-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND a.in_post_kbn IN ('027','004') AND c.CERTIFICATE_NO is NOT NULL then 1 else 0 end) as 'N21',");
		//工勤技能人员-一级-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND a.IN_POST_LEVEL='001' and a.in_post_kbn ='007' then 1 else 0 end) as 'P21',");
		//工勤技能人员-二级-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND a.IN_POST_LEVEL='002' and a.in_post_kbn ='007' then 1 else 0 end) as 'Q21',");
		//工勤技能人员-三级-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND a.IN_POST_LEVEL='003' and a.in_post_kbn ='007' then 1 else 0 end) as 'R21',");
		//工勤技能人员-四级-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND a.IN_POST_LEVEL='004' and a.in_post_kbn ='007' then 1 else 0 end) as 'S21',");
		//工勤技能人员-五级-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND a.IN_POST_LEVEL='005' and a.in_post_kbn ='007' then 1 else 0 end) as 'T21',");
		//工勤技能人员-普通工-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND a.IN_POST_LEVEL='006' and a.in_post_kbn ='007' then 1 else 0 end) as 'U21',");
		//工勤技能人员-其他等级人员-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND a.IN_POST_LEVEL='007' and a.in_post_kbn ='007' then 1 else 0 end) as 'V21',");
		//工勤技能人员-中央-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.UNIT_MANAGE_SCALE='061' and a.in_post_kbn ='007' then 1 else 0 end) as 'X21',");
		//工勤技能人员-省（区、市）-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.UNIT_MANAGE_SCALE='062' and a.in_post_kbn ='007' then 1 else 0 end) as 'Y21',");
		//工勤技能人员-地（市、州、盟-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.UNIT_MANAGE_SCALE='063' and a.in_post_kbn ='007' then 1 else 0 end) as 'Z21',");
		//工勤技能人员-县（市、区、旗）-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.UNIT_MANAGE_SCALE='064' and a.in_post_kbn ='007' then 1 else 0 end) as 'AA21',");
		//工勤技能人员-乡（镇）-卫生和社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.UNIT_MANAGE_SCALE='998' and a.in_post_kbn ='007' then 1 else 0 end) as 'AB21',");
		//专业技术人员-十一级-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND a.IN_POST_LEVEL='011' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'C22',");
		//专业技术人员-十二级-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND a.IN_POST_LEVEL='012' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'D22',");
		//专业技术人员-十三级-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND a.IN_POST_LEVEL='013' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'E22',");
		//专业技术人员-其他等级-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND a.IN_POST_LEVEL='014' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'F22',");
		//专业技术人员-中央-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND b.UNIT_MANAGE_SCALE='061' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'H22',");
		//专业技术人员-省（区、市）-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND b.UNIT_MANAGE_SCALE='062' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'I22',");
		//专业技术人员-地（市、州、盟）-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND b.UNIT_MANAGE_SCALE='063' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'J22',");
		//专业技术人员-县（市、区、旗）-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND b.UNIT_MANAGE_SCALE='064' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'K22',");
		//专业技术人员-乡（镇）-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND b.UNIT_MANAGE_SCALE='998' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'L22',");
		//专业技术人员-在管理岗位工作的-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND a.in_post_kbn ='004' then 1 else 0 end) as 'M22',");
		//专业技术人员-具有职业资格的-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND a.in_post_kbn IN ('027','004') AND c.CERTIFICATE_NO is NOT NULL then 1 else 0 end) as 'N22',");
		//工勤技能人员-一级-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND a.IN_POST_LEVEL='001' and a.in_post_kbn ='007' then 1 else 0 end) as 'P22',");
		//工勤技能人员-二级-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND a.IN_POST_LEVEL='002' and a.in_post_kbn ='007' then 1 else 0 end) as 'Q22',");
		//工勤技能人员-三级-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND a.IN_POST_LEVEL='003' and a.in_post_kbn ='007' then 1 else 0 end) as 'R22',");
		//工勤技能人员-四级-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND a.IN_POST_LEVEL='004' and a.in_post_kbn ='007' then 1 else 0 end) as 'S22',");
		//工勤技能人员-五级-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND a.IN_POST_LEVEL='005' and a.in_post_kbn ='007' then 1 else 0 end) as 'T22',");
		//工勤技能人员-普通工-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND a.IN_POST_LEVEL='006' and a.in_post_kbn ='007' then 1 else 0 end) as 'U22',");
		//工勤技能人员-其他等级-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND a.IN_POST_LEVEL='007' and a.in_post_kbn ='007' then 1 else 0 end) as 'V22',");
		//工勤技能人员-中央-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND b.UNIT_MANAGE_SCALE='061' and a.in_post_kbn ='007' then 1 else 0 end) as 'X22',");
		//工勤技能人员-省（区、市）-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND b.UNIT_MANAGE_SCALE='062' and a.in_post_kbn ='007' then 1 else 0 end) as 'Y22',");
		//工勤技能人员-地（市、州、盟-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND b.UNIT_MANAGE_SCALE='063' and a.in_post_kbn ='007' then 1 else 0 end) as 'Z22',");
		//工勤技能人员-县（市、区、旗）-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND b.UNIT_MANAGE_SCALE='064' and a.in_post_kbn ='007' then 1 else 0 end) as 'AA22',");
		//工勤技能人员-乡（镇）-公立医院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='167' AND b.UNIT_MANAGE_SCALE='998' and a.in_post_kbn ='007' then 1 else 0 end) as 'AB22',");
		//专业技术人员-十一级-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND a.IN_POST_LEVEL='011' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'C23',");
		//专业技术人员-十二级-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND a.IN_POST_LEVEL='012' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'D23',");
		//专业技术人员-十三级-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND a.IN_POST_LEVEL='013' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'E23',");
		//专业技术人员-其他等级-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND a.IN_POST_LEVEL='014' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'F23',");
		//专业技术人员-中央-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND b.UNIT_MANAGE_SCALE='061' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'H23',");
		//专业技术人员-省（区、市）-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND b.UNIT_MANAGE_SCALE='062' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'I23',");
		//专业技术人员-地（市、州、盟）-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND b.UNIT_MANAGE_SCALE='063' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'J23',");
		//专业技术人员-县（市、区、旗）-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND b.UNIT_MANAGE_SCALE='064' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'K23',");
		//专业技术人员-乡（镇）-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND b.UNIT_MANAGE_SCALE='998' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'L23',");
		//专业技术人员-在管理岗位工作的-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND a.in_post_kbn ='004' then 1 else 0 end) as 'M23',");
		//专业技术人员-具有职业资格的-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND a.in_post_kbn IN ('027','004') AND c.CERTIFICATE_NO is NOT NULL then 1 else 0 end) as 'N23',");
		//工勤技能人员-一级-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND a.IN_POST_LEVEL='001' and a.in_post_kbn ='007' then 1 else 0 end) as 'P23',");
		//工勤技能人员-二级-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND a.IN_POST_LEVEL='002' and a.in_post_kbn ='007' then 1 else 0 end) as 'Q23',");
		//工勤技能人员-三级-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND a.IN_POST_LEVEL='003' and a.in_post_kbn ='007' then 1 else 0 end) as 'R23',");
		//工勤技能人员-四级-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND a.IN_POST_LEVEL='004' and a.in_post_kbn ='007' then 1 else 0 end) as 'S23',");
		//工勤技能人员-五级-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND a.IN_POST_LEVEL='005' and a.in_post_kbn ='007' then 1 else 0 end) as 'T23',");
		//工勤技能人员-普通工-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND a.IN_POST_LEVEL='006' and a.in_post_kbn ='007' then 1 else 0 end) as 'U23',");
		//工勤技能人员-其他等级人员-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND a.IN_POST_LEVEL='007' and a.in_post_kbn ='007' then 1 else 0 end) as 'V23',");
		//工勤技能人员-中央-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND b.UNIT_MANAGE_SCALE='061' and a.in_post_kbn ='007' then 1 else 0 end) as 'X23',");
		//工勤技能人员-省（区、市）-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND b.UNIT_MANAGE_SCALE='062' and a.in_post_kbn ='007' then 1 else 0 end) as 'Y23',");
		//工勤技能人员-地（市、州、盟-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND b.UNIT_MANAGE_SCALE='063' and a.in_post_kbn ='007' then 1 else 0 end) as 'Z23',");
		//工勤技能人员-县（市、区、旗）-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND b.UNIT_MANAGE_SCALE='064' and a.in_post_kbn ='007' then 1 else 0 end) as 'AA23',");
		//工勤技能人员-乡（镇）-社区医疗与卫生院
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='168' AND b.UNIT_MANAGE_SCALE='998' and a.in_post_kbn ='007' then 1 else 0 end) as 'AB23',");
		//专业技术人员-十一级-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND a.IN_POST_LEVEL='011' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'C24',");
		//专业技术人员-十二级-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND a.IN_POST_LEVEL='012' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'D24',");
		//专业技术人员-十三级-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND a.IN_POST_LEVEL='013' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'E24',");
		//专业技术人员-其他等级-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND a.IN_POST_LEVEL='014' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'F24',");
		//专业技术人员-中央-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND b.UNIT_MANAGE_SCALE='061' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'H24',");
		//专业技术人员-省（区、市）-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND b.UNIT_MANAGE_SCALE='062' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'I24',");
		//专业技术人员-地（市、州、盟）-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND b.UNIT_MANAGE_SCALE='063' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'J24',");
		//专业技术人员-县（市、区、旗）-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND b.UNIT_MANAGE_SCALE='064' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'K24',");
		//专业技术人员-乡（镇）-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND b.UNIT_MANAGE_SCALE='998' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'L24',");
		//专业技术人员-在管理岗位工作的-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND a.in_post_kbn ='004' then 1 else 0 end) as 'M24',");
		//专业技术人员-具有职业资格的-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND a.in_post_kbn IN ('027','004') AND c.CERTIFICATE_NO is NOT NULL then 1 else 0 end) as 'N24',");
		//工勤技能人员-一级-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND a.IN_POST_LEVEL='001' and a.in_post_kbn ='007' then 1 else 0 end) as 'P24',");
		//工勤技能人员-二级-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND a.IN_POST_LEVEL='002' and a.in_post_kbn ='007' then 1 else 0 end) as 'Q24',");
		//工勤技能人员-三级-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND a.IN_POST_LEVEL='003' and a.in_post_kbn ='007' then 1 else 0 end) as 'R24',");
		//工勤技能人员-四级-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND a.IN_POST_LEVEL='004' and a.in_post_kbn ='007' then 1 else 0 end) as 'S24',");
		//工勤技能人员-五级-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND a.IN_POST_LEVEL='005' and a.in_post_kbn ='007' then 1 else 0 end) as 'T24',");
		//工勤技能人员-普通工-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND a.IN_POST_LEVEL='006' and a.in_post_kbn ='007' then 1 else 0 end) as 'U24',");
		//工勤技能人员-其他等级人员-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND a.IN_POST_LEVEL='007' and a.in_post_kbn ='007' then 1 else 0 end) as 'V24',");
		//工勤技能人员-中央-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND b.UNIT_MANAGE_SCALE='061' and a.in_post_kbn ='007' then 1 else 0 end) as 'X24',");
		//工勤技能人员-省（区、市）-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND b.UNIT_MANAGE_SCALE='062' and a.in_post_kbn ='007' then 1 else 0 end) as 'Y24',");
		//工勤技能人员-地（市、州、盟-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND b.UNIT_MANAGE_SCALE='063' and a.in_post_kbn ='007' then 1 else 0 end) as 'Z24',");
		//工勤技能人员-县（市、区、旗）-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND b.UNIT_MANAGE_SCALE='064' and a.in_post_kbn ='007' then 1 else 0 end) as 'AA24',");
		//工勤技能人员-乡（镇）-门诊部（所）
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='169' AND b.UNIT_MANAGE_SCALE='998' and a.in_post_kbn ='007' then 1 else 0 end) as 'AB24',");
		//专业技术人员-十一级-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND a.IN_POST_LEVEL='011' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'C25',");
		//专业技术人员-十二级-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND a.IN_POST_LEVEL='012' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'D25',");
		//专业技术人员-十三级-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND a.IN_POST_LEVEL='013' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'E25',");
		//专业技术人员-其他等级-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND a.IN_POST_LEVEL='014' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'F25',");
		//专业技术人员-中央-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND b.UNIT_MANAGE_SCALE='061' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'H25',");
		//专业技术人员-省（区、市）-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND b.UNIT_MANAGE_SCALE='062' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'I25',");
		//专业技术人员-地（市、州、盟）-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND b.UNIT_MANAGE_SCALE='063' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'J25',");
		//专业技术人员-县（市、区、旗）-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND b.UNIT_MANAGE_SCALE='064' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'K25',");
		//专业技术人员-乡（镇）-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND b.UNIT_MANAGE_SCALE='998' and a.in_post_kbn IN ('027','004') then 1 else 0 end) as 'L25',");
		//专业技术人员-在管理岗位工作的-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND a.in_post_kbn ='004' then 1 else 0 end) as 'M25',");
		//专业技术人员-具有职业资格的-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND a.in_post_kbn IN ('027','004') AND c.CERTIFICATE_NO is NOT NULL then 1 else 0 end) as 'N25',");
		//工勤技能人员-一级-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND a.IN_POST_LEVEL='001' and a.in_post_kbn ='007' then 1 else 0 end) as 'P25',");
		//工勤技能人员-二级-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND a.IN_POST_LEVEL='002' and a.in_post_kbn ='007' then 1 else 0 end) as 'Q25',");
		//工勤技能人员-三级-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND a.IN_POST_LEVEL='003' and a.in_post_kbn ='007' then 1 else 0 end) as 'R25',");
		//工勤技能人员-四级-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND a.IN_POST_LEVEL='004' and a.in_post_kbn ='007' then 1 else 0 end) as 'S25',");
		//工勤技能人员-五级-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND a.IN_POST_LEVEL='005' and a.in_post_kbn ='007' then 1 else 0 end) as 'T25',");
		//工勤技能人员-普通工-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND a.IN_POST_LEVEL='006' and a.in_post_kbn ='007' then 1 else 0 end) as 'U25',");
		//工勤技能人员-其他等级人员-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND a.IN_POST_LEVEL='007' and a.in_post_kbn ='007' then 1 else 0 end) as 'V25',");
		//工勤技能人员-中央-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND b.UNIT_MANAGE_SCALE='061' and a.in_post_kbn ='007' then 1 else 0 end) as 'X25',");
		//工勤技能人员-省（区、市）-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND b.UNIT_MANAGE_SCALE='062' and a.in_post_kbn ='007' then 1 else 0 end) as 'Y25',");
		//工勤技能人员-地（市、州、盟-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND b.UNIT_MANAGE_SCALE='063' and a.in_post_kbn ='007' then 1 else 0 end) as 'Z25',");
		//工勤技能人员-县（市、区、旗）-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND b.UNIT_MANAGE_SCALE='064' and a.in_post_kbn ='007' then 1 else 0 end) as 'AA25',");
		//工勤技能人员-乡（镇）-社会工作
		sql.append(" SUM(CASE WHEN b.INDUSTRY_DIVIDE='153' AND b.INDUSTRY_DIVIDE_TWO='170' AND b.UNIT_MANAGE_SCALE='998' and a.in_post_kbn ='007' then 1 else 0 end) as 'AB25',");
		sql.append(" '"+rlgl200101BeanNew.getSel_year()+"' as 'N7' ");
		sql.append(" FROM");
		sql.append(" m_tb09_irin a");
		sql.append(" LEFT JOIN");
		sql.append(" m_tb04_unit b");
		sql.append(" ON");
		sql.append(" a.UNIT_NO=b.UNIT_NO");
		sql.append(" LEFT JOIN");
		sql.append(" m_tb19_personnel_practitioners_info c");
		sql.append(" ON");
		sql.append(" a.PERSON_NO=c.PERSONNEL_ID");
		sql.append(" WHERE");
		sql.append(" substr(a.LOGIN_DATE,1,4)="+rlgl200101BeanNew.getSel_year());
		sql.append(" and ");
		sql.append("  (a.UNIT_NO in ");
		sql.append(" (SELECT unit_no FROM m_tb04_unit WHERE ESCROW_UNIT_NO = '"+rlgl200101BeanNew.getUnit_no()+"' AND DEL_KBN = 0 AND UNIT_STATUS = '1')");
		sql.append(" or a.UNIT_NO LIKE CONCAT('', '"+rlgl200101BeanNew.getUnit_no()+"', '%'))");

		return sql.toString();
	}
	
    /**
     * 事业单位工作人员分行业情况3（续）(2)的sql
     * @param rlgl200101BeanNew
     * @return sql语句
     */
    public String EditUnitBaseInfoSql081(Rlgl200101Bean rlgl200101BeanNew){
    	StringBuilder sql = new StringBuilder();
    	sql.append("SELECT ");
    	// 卫生和社会工作 
    	sql.append(" SUM(CASE WHEN T04.INDUSTRY_DIVIDE ='153' AND SUBSTRING(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"'THEN 1 ELSE 0 END) AS 'T17',");
    	// 中央
    	sql.append("SUM(CASE WHEN T04.unit_manage_scale ='061' AND SUBSTRING(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"' THEN 1 ELSE 0 END) AS 'X17',");
    	// 省（区、市）
    	sql.append("SUM(CASE WHEN T04.unit_manage_scale ='062' AND SUBSTRING(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"'THEN 1 ELSE 0 END) AS 'Y17',");
    	// 地（市、州、盟）
    	sql.append("SUM(CASE WHEN T04.unit_manage_scale ='063' AND SUBSTRING(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"'THEN 1 ELSE 0 END) AS 'Z17',");
    	// 县（市、区、旗）
    	sql.append("SUM(CASE WHEN T04.unit_manage_scale ='064' AND SUBSTRING(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"'THEN 1 ELSE 0 END) AS 'AA17',");
    	// 乡（镇）
    	sql.append("SUM(CASE WHEN T04.unit_manage_scale ='998' AND SUBSTRING(T04.LOGIN_DATE,1,4) <= '"+rlgl200101BeanNew.getSel_year()+"'THEN 1 ELSE 0 END) AS 'AB17'");
    	sql.append(" FROM m_tb09_irin AS T09 ");
    	sql.append(" LEFT JOIN m_tb04_unit AS T04  ");
    	sql.append(" ON T09.UNIT_NO = T04.UNIT_NO ");
    	sql.append(" WHERE ");
    	sql.append(" T09.IN_POST_KBN = '027' AND  ");
    	sql.append(" T04.unit_nature = '056' AND ");
    	sql.append(" T09.DEL_KBN ='0' AND ");
    	sql.append(" T04.DEL_KBN ='0' AND ");
    	sql.append(" SUBSTRING(T09.LOGIN_DATE,1,4)<='"+rlgl200101BeanNew.getSel_year()+"'");
    	sql.append(" AND T04.unit_no in");
    	sql.append(" (SELECT unit_no FROM m_tb04_unit WHERE ESCROW_UNIT_NO = '" + rlgl200101BeanNew.getUnit_no() + "'  AND UNIT_STATUS = '1' or unit_no like CONCAT('', '"+rlgl200101BeanNew.getUnit_no()+"','%')) ");

    	return sql.toString();
    } 
    public String EditUnitBaseInfoSql08(Rlgl200101Bean rlgl200101BeanNew) {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT ");
		
		//卫生技术人员-女
		sql.append(" SUM(CASE WHEN T12.personnel_gender ='002' and T09.IN_POST_KBN = '027' THEN 1 ELSE 0 END) AS 'E17',");
		//卫生技术人员-少数民族
		sql.append(" SUM(CASE WHEN T12.personnel_ethnic <> '' AND T12.PERSONNEL_ETHNIC <> '001' and T09.IN_POST_KBN = '027' THEN 1 ELSE 0 END) AS 'F17',");
		//卫生技术人员-中共党员
		sql.append(" SUM(CASE WHEN T12.PERSONNEL_POLITICAL_LANDSCAPE ='001' and T09.IN_POST_KBN = '027' THEN 1 ELSE 0 END ) AS 'G17',");
		//卫生技术人员-博士
		sql.append(" SUM(CASE WHEN T15.DEGREE = '004' and T09.IN_POST_KBN = '027' THEN 1 ELSE 0 END ) AS 'H17',");
		//卫生技术人员-硕士
		sql.append(" SUM(CASE WHEN T15.DEGREE = '003' and T09.IN_POST_KBN = '027' THEN 1 ELSE 0 END ) AS  'I17',");
		//卫生技术人员-研究生
		sql.append(" SUM(CASE WHEN T15.EDUCATIONAL_BG ='006' OR T15.EDUCATIONAL_BG ='005' and T09.IN_POST_KBN = '027' THEN 1 ELSE 0 END) AS 'K17',");
		//卫生技术人员-大学本科
		sql.append(" SUM(CASE WHEN T15.EDUCATIONAL_BG ='004' and T09.IN_POST_KBN = '027' THEN 1 ELSE 0 END) AS 'L17',");
		//卫生技术人员-大学专科
		sql.append(" SUM(CASE WHEN T15.EDUCATIONAL_BG ='003' and T09.IN_POST_KBN = '027' THEN 1 ELSE 0 END) AS 'M17',");
		//卫生技术人员-中专
		sql.append(" SUM(CASE WHEN T15.EDUCATIONAL_BG ='002' and T09.IN_POST_KBN = '027' THEN 1 ELSE 0 END) AS 'N17',");
		//卫生技术人员-高中及以下
		sql.append(" SUM(CASE WHEN T15.EDUCATIONAL_BG ='001' and T09.IN_POST_KBN = '027'  THEN 1 ELSE 0 END) AS 'O17',");
		//卫生技术人员-35岁及以下
		sql.append(" SUM(CASE when nl.AGEEREA='001' and T09.IN_POST_KBN = '027' THEN 1 ELSE 0 end) as 'p17',");
		//卫生技术人员-36岁至40岁
		sql.append(" SUM(CASE when nl.AGEEREA='002' and T09.IN_POST_KBN = '027' THEN 1 ELSE 0 end) as 'Q17',");
		//卫生技术人员-41岁至45岁
		sql.append(" SUM(CASE when nl.AGEEREA='003' and T09.IN_POST_KBN = '027' THEN 1 ELSE 0 end) as 'R17',");
		//卫生技术人员-46岁至50岁
		sql.append(" SUM(CASE when nl.AGEEREA='004' and T09.IN_POST_KBN = '027' THEN 1 ELSE 0 end) as 'S17',");
		//卫生技术人员-51岁至54岁
		sql.append(" SUM(CASE when nl.AGEEREA='005' and T09.IN_POST_KBN = '027' THEN 1 ELSE 0 end) as 'T17',");
		//卫生技术人员-55岁至59岁
		sql.append(" SUM(CASE when nl.AGEEREA='006' and T09.IN_POST_KBN = '027' THEN 1 ELSE 0 end) as 'U17',");
		//卫生技术人员-60岁及以上
		sql.append(" SUM(CASE when nl.AGEEREA='007' and T09.IN_POST_KBN = '027' THEN 1 ELSE 0 end) as 'V17'");
		sql.append(" '"+rlgl200101BeanNew.getSel_year()+"' as 'J7' ");
		sql.append(" FROM m_tb12_personnel AS T12 ");
		sql.append(" LEFT JOIN m_tb09_irin  AS T09 ON");
		sql.append(" T12.PERSONNEL_UNIT = T09.UNIT_NO");
		sql.append(" LEFT  JOIN m_tb15_personnel_edu_info AS T15");
		sql.append(" ON T12.PERSONNEL_ID  = T15.PERSONNEL_ID");
		sql.append(" LEFT JOIN (SELECT ");
		sql.append(" CASE WHEN ( YEAR ( CURRENT_DATE ( ) ) - YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CURRENT_DATE ( ) , ");
		sql.append(" 5 ) < RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) , ");
		sql.append(" 5 ) ) <=35 THEN '001' WHEN ( YEAR ( CURRENT_DATE ( ) ) - YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CURRENT_DATE ( ) , ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) ,5 ) ) ");
		sql.append(" BETWEEN 36 ");
		sql.append(" AND 40 THEN '002' WHEN ( YEAR ( CURRENT_DATE ( ) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CURRENT_DATE ( ) , ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) , ");
		sql.append(" 5 ) ) ");
		sql.append(" BETWEEN 41 ");
		sql.append(" AND 45 THEN '003' WHEN ( YEAR ( CURRENT_DATE ( ) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CURRENT_DATE ( ) , ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) , ");
		sql.append(" 5 ) ) ");
		sql.append(" BETWEEN 46 ");
		sql.append(" AND 50 THEN '004' WHEN ( YEAR ( CURRENT_DATE ( ) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CURRENT_DATE ( ) , ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) , ");
		sql.append(" 5 ) ) ");
		sql.append(" BETWEEN 51 ");
		sql.append(" AND 54 THEN '005' WHEN ( YEAR ( CURRENT_DATE ( ) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CURRENT_DATE ( ) , ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) ,5 ) ) ");
		sql.append(" BETWEEN 55 ");
		sql.append(" AND 59 THEN '006' WHEN ( YEAR ( CURRENT_DATE ( ) ) -YEAR ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) ) ) - ( RIGHT ( CURRENT_DATE ( ) , ");
		sql.append(" 5 ) <RIGHT ( DATE_FORMAT ( PERSONNEL_BIRTHDAY , ");
		sql.append(" '%Y-%m-%d' ) , 5 ) ) ");
		sql.append(" >=60 THEN '007' ");
		sql.append(" ELSE 0 END AS AGEEREA ,");
		sql.append(" (");
		sql.append(" SELECT EDUCATIONAL_BG ");
		sql.append(" FROM m_tb15_personnel_edu_info");
		sql.append(" WHERE  ");
		sql.append(" PERSONNEL_ID=A.PERSONNEL_ID  ");
		sql.append(" and  ");
		sql.append(" GRADUATION_TIME =  ");
		sql.append(" ( ");
		sql.append(" Select Max(GRADUATION_TIME)  ");
		sql.append(" From  m_tb15_personnel_edu_info  ");
		sql.append(" WHERE ");
		sql.append(" PERSONNEL_ID=A.PERSONNEL_ID  ");
		sql.append(" ) ");
		sql.append(" ) AS edu , ");
		sql.append("  ");
		sql.append(" case when m.IN_POST_LEVEL = '' then null else m.IN_POST_LEVEL end as IN_POST_LEVEL, ");
		sql.append("  ");
		sql.append(" m.IN_POST_KBN, ");
		sql.append(" D.CERTIFICATE_NO, ");
		sql.append(" A.PERSONNEL_ID  ");
		sql.append("  ");
		sql.append(" FROM  ");
		sql.append(" M_TB12_PERSONNEL as A  ");
		sql.append(" LEFT JOIN ");
		sql.append(" m_tb09_irin m ");
		sql.append(" on m.PERSON_NO=A.PERSONNEL_ID  ");
		sql.append(" LEFT JOIN m_tb19_personnel_practitioners_info D ");
		sql.append(" on D.PERSONNEL_ID=A.PERSONNEL_ID where substr(a.LOGIN_DATE,1,4)<='2013' ");
		sql.append(" and substr(m.LOGIN_DATE,1,4)<='2013') as nl on nl.PERSONNEL_ID=T12.PERSONNEL_ID ");
		sql.append(" WHERE  ");
		sql.append("   ");
		sql.append("  T09.DEL_KBN ='0' AND ");
		sql.append("  T12.DEL_KBN ='0' ");
		sql.append(" AND T12.RETIRE_FLAG <> '1' ");
		sql.append(" AND T15.DEL_KBN ='0'  ");
		sql.append(" and  ");
		sql.append(" (T09.UNIT_NO in  ");
		sql.append(" (SELECT unit_no FROM m_tb04_unit WHERE ESCROW_UNIT_NO = '37' AND DEL_KBN = 0 AND UNIT_STATUS = '1') ");
		sql.append(" or T09.UNIT_NO LIKE CONCAT('', '37', '%') ");
		sql.append(" )");
		
		return sql.toString();
    } 
}
