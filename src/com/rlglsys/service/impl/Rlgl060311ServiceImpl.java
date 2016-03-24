package com.rlglsys.service.impl;

import java.util.List;

import com.rlglsys.bean.Rlgl100102Bean;
import com.rlglsys.entity.Mtb121PublicCourseWare;
import com.rlglsys.mapper.IRlgl060311Mapper;
import com.rlglsys.service.IRlgl060311Service;

public class Rlgl060311ServiceImpl implements IRlgl060311Service {

    private IRlgl060311Mapper rlgl060311Mapper;	
	
	@Override
	public int insertExamWareSelectData(
			Mtb121PublicCourseWare rlgl100102Bean) throws Exception {
		
		return rlgl060311Mapper.insertExamWareSelectData(rlgl100102Bean);
	}
	
	@Override
	public int insertExamWareInfoData(
			List<Rlgl100102Bean> rlgl060311BeanInfoList) throws Exception {
		
		return rlgl060311Mapper.insertExamWareInfoData(rlgl060311BeanInfoList);
	}
	
	@Override
	public int getExamWareCountById(Rlgl100102Bean coursewareInfo)
			throws Exception {
		
		return rlgl060311Mapper.getExamWareCountById(coursewareInfo);
	}
	
	@Override
	public int getExamWareCount(Mtb121PublicCourseWare coursewareInfo)
			throws Exception {
		
		return rlgl060311Mapper.getExamWareCount(coursewareInfo);
	}
	
	@Override
	public List<Mtb121PublicCourseWare> getExamWareByBean(Mtb121PublicCourseWare coursewareInfo)
			throws Exception {
		return rlgl060311Mapper.getExamWareByBean(coursewareInfo);
	}

	/**
	 * 根据课件id删除课件信息
	 */
	@Override
	@org.springframework.transaction.annotation.Transactional(rollbackFor=Exception.class)
	public int deleteCourseWare(String course_id) {
		int count = 0;
		try
		{
			count = rlgl060311Mapper.deleteCourseWare(course_id);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return count;
	}
	
	public IRlgl060311Mapper getRlgl060311Mapper() {
		return rlgl060311Mapper;
	}

	public void setRlgl060311Mapper(IRlgl060311Mapper rlgl060311Mapper) {
		this.rlgl060311Mapper = rlgl060311Mapper;
	}
}
