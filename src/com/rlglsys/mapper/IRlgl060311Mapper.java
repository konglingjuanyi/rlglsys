package com.rlglsys.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.rlglsys.bean.Rlgl100102Bean;
import com.rlglsys.entity.Mtb121PublicCourseWare;

public interface IRlgl060311Mapper {

    /**
     * 插入课件信息
     * @param rlgl100102BeanInfoList
     * @return
     */
    public int insertExamWareInfoData(@Param(value = "rlgl060311BeanInfoList")List<Rlgl100102Bean> rlgl060311BeanInfoList);
    
    /**
     * 课件个数检索
     * @param rlgl100102Bean
     * @return
     */
    public int getExamWareCountById(@Param(value = "coursewareInfo")Rlgl100102Bean coursewareInfo);

	public List<Mtb121PublicCourseWare> getExamWareByBean(@Param(value = "coursewareInfo")Mtb121PublicCourseWare coursewareInfo);

	public int getExamWareCount(@Param(value = "coursewareInfo")Mtb121PublicCourseWare coursewareInfo);
	
	/**
	 * 根据课件id删除课件信息
	 * @param course_id 课件Id
	 * @return  0 删除失败
	 */
	public int deleteCourseWare(@Param(value = "course_id")String course_id);
	
	
	/**
     * 插入课件信息
     * @param rlgl100102Bean
     * @return
     */
    public int insertExamWareSelectData(@Param(value = "rlgl100102Bean")Mtb121PublicCourseWare rlgl100102Bean);
}
