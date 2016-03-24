package com.rlglsys.service;

import java.util.List;

import com.rlglsys.bean.Rlgl100102Bean;
import com.rlglsys.entity.Mtb121PublicCourseWare;

public interface IRlgl060311Service {
    /**
     * 插入试卷信息
     * @param rlgl100102BeanInfoList
     * @return
     */
    public int insertExamWareInfoData(List<Rlgl100102Bean> rlgl060311BeanInfoList) throws Exception;
    
    /**
     * 插入试卷信息
     * @param rlgl100102Bean
     * @return
     */
    public int insertExamWareSelectData(
    		Mtb121PublicCourseWare rlgl100102Bean) throws Exception;
    
    /**
     * 试卷个数检索
     * @param rlgl100102Bean
     * @return
     */
    public int getExamWareCountById(Rlgl100102Bean coursewareInfo) throws Exception;
        
    public int getExamWareCount(Mtb121PublicCourseWare coursewareInfo) throws Exception;
        
    public List<Mtb121PublicCourseWare> getExamWareByBean(Mtb121PublicCourseWare coursewareInfo) throws Exception;
    /**
	 * 根据课件Id删除课件信息
	 * @param course_id 课件id
	 * @return 0： 删除失败   非零正整数：删除成功
	 */
	public int deleteCourseWare(String course_id);
}
