package com.rlglsys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rlglsys.bean.MUserExamInfoReport;
import com.rlglsys.bean.Rlgl100104Bean;

public interface IMUserExamInfoReportMapper {
	 
	public List<MUserExamInfoReport> getReportList(@Param(value = "page")String page,@Param(value = "size")String size);
	 
}
