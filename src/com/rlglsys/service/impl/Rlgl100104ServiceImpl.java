package com.rlglsys.service.impl;
import java.util.List;

import com.rlglsys.bean.Rlgl100104Bean;
import com.rlglsys.mapper.IRlgl100104Mapper;
import com.rlglsys.service.IRlgl100104Service;
public class Rlgl100104ServiceImpl implements IRlgl100104Service{
	private IRlgl100104Mapper rlgl100104Mapper;

	@Override
	public List<Rlgl100104Bean> selectCourseSelected(
			Rlgl100104Bean rlgl100104Bean) {
		return rlgl100104Mapper.selectCourseSelected(rlgl100104Bean);
	}
	
	@Override
	public List<Rlgl100104Bean> selectPublicCourse(
			Rlgl100104Bean rlgl100104Bean) {
		return rlgl100104Mapper.selectPublicCourse(rlgl100104Bean);
	}
	
	@Override
	public List<Rlgl100104Bean> selectPublicSelectCourse(
			Rlgl100104Bean rlgl100104Bean) {
		return rlgl100104Mapper.selectPublicSelectCourse(rlgl100104Bean);
	}
	
	@Override
	public List<Rlgl100104Bean> selectPublicSLearnCourse(
			Rlgl100104Bean rlgl100104Bean) {
		return rlgl100104Mapper.selectPublicSLearnCourse(rlgl100104Bean);
	}
	
	@Override
	public int selectCourseCount(Rlgl100104Bean rlgl100104Bean) {
		int count = 0;
		try {
            count = rlgl100104Mapper.selectCourseCount(rlgl100104Bean);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return count;
	}
	
	@Override
	public int selectPublicCourseCount(Rlgl100104Bean rlgl100104Bean) {
		int count = 0;
		try {
            count = rlgl100104Mapper.selectPublicCourseCount(rlgl100104Bean);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return count;
	}
	
	@Override
	public int UpdateLearnState(Rlgl100104Bean rlgl100104Bean) {
		return rlgl100104Mapper.UpdateLearnState(rlgl100104Bean);
	}
	
	/**
	 * @return the rlgl100104Mapper
	 */
	public IRlgl100104Mapper getRlgl100104Mapper() {
		return rlgl100104Mapper;
	}

	/**
	 * @param rlgl100104Mapper the rlgl100104Mapper to set
	 */
	public void setRlgl100104Mapper(IRlgl100104Mapper rlgl100104Mapper) {
		this.rlgl100104Mapper = rlgl100104Mapper;
	}

	@Override
	@org.springframework.transaction.annotation.Transactional(rollbackFor=Exception.class)
	public void deleteCourse(List<String> ids, String userId) {
		try {
			rlgl100104Mapper.deleteCourse(ids, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
