package com.rlglsys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rlglsys.entity.MTb02Adm;

public interface IMTb02AdmMapper {
    public List<MTb02Adm> getAdmInfo(@Param(value = "admtypecd")String admtypecd);
    public String getAdmName(@Param(value = "admtypecd")String admtypecd, @Param(value = "admnum")String admnum);
    public String getAdmNum(@Param(value = "admtypecd")String admtypecd, @Param(value = "admname")String admname);
    // 根据管理类别区分和扩展域01取List
    public List<MTb02Adm> getAdmInfoExpend01(@Param(value = "admtypecd")String admtypecd,@Param(value = "expend_01")String expend_01);
    // 根据管理类别区分和扩展域01,扩展域02取List
    public List<MTb02Adm> getAdmInfoExpend0102(@Param(value = "admtypecd")String admtypecd,@Param(value = "expend_01")String expend_01,@Param(value = "expend_02")String expend_02);
    public List<MTb02Adm> getAdmExamInfo(@Param(value = "admValue01")String admValue01);
	/**
	 * 根据课程二级类别编号得到课程三级类别列表
	 * @param course_catagory 课程二级类别
	 * @return
	 */
	public List<MTb02Adm> getThreeList(@Param(value = "course_catagory")String course_catagory);
	
	/**
	 * 依据查询条件得到数据条数
	 * @param userId 身份证号
	 * @param credit_year 学分年度
	 * @param credit_category 学分分类
	 * @return
	 */
	public String getCourseCreditSum(@Param(value = "userId")String userId, 
			                        @Param(value = "credit_year")String credit_year,
			                        @Param(value = "credit_category")String credit_category);
}
