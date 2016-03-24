package com.rlglsys.service.impl;

import java.util.List;

import com.rlglsys.bean.MUserExamInfoReport;
import com.rlglsys.entity.MTb02Adm;
import com.rlglsys.mapper.IMTb02AdmMapper;
import com.rlglsys.mapper.IMUserExamInfoReportMapper;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IMUserExamInfoReportService;

public class MUserExamInfoReportServiceImpl implements IMUserExamInfoReportService {

	private IMUserExamInfoReportMapper imUserExamInfoReportMapper;

	 



	public IMUserExamInfoReportMapper getImUserExamInfoReportMapper() {
		return imUserExamInfoReportMapper;
	}





	public void setImUserExamInfoReportMapper(IMUserExamInfoReportMapper imUserExamInfoReportMapper) {
		this.imUserExamInfoReportMapper = imUserExamInfoReportMapper;
	}





	@Override
	public List<MUserExamInfoReport> getReport(String page, String size) {
		// TODO Auto-generated method stub
		return imUserExamInfoReportMapper.getReportList(page, size);
	}
	
	

	 

	 
}
