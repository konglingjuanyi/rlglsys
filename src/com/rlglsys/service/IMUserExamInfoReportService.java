package com.rlglsys.service;

import java.util.List;

import com.rlglsys.bean.MUserExamInfoReport;
import com.rlglsys.entity.MTb02Adm;

public interface IMUserExamInfoReportService {
	public List<MUserExamInfoReport> getReport(String page,String size);
 

}
