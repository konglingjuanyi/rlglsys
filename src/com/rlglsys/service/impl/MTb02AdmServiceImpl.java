package com.rlglsys.service.impl;

import java.util.List;

import com.rlglsys.entity.MTb02Adm;
import com.rlglsys.mapper.IMTb02AdmMapper;
import com.rlglsys.service.IMTb02AdmService;

public class MTb02AdmServiceImpl implements IMTb02AdmService {

	private IMTb02AdmMapper imtb02AdmMapper;

	public IMTb02AdmMapper getImtb02AdmMapper() {
		return imtb02AdmMapper;
	}

	public void setImtb02AdmMapper(IMTb02AdmMapper imtb02AdmMapper) {
		this.imtb02AdmMapper = imtb02AdmMapper;
	}

	/**
	 *
	 */
	@Override
	public List<MTb02Adm> getAdmInfo(String admtypecd) {
		// TODO Auto-generated method stub
		return imtb02AdmMapper.getAdmInfo(admtypecd);
	}

	@Override
	public String getAdmNum(String admtypecd, String admName) {
		// TODO Auto-generated method stub
		return imtb02AdmMapper.getAdmNum(admtypecd, admName);
	}

	/**
	 *
	 */
	@Override
	public String getAdmName(String admtypecd, String admnum) {
		// TODO Auto-generated method stub
		return imtb02AdmMapper.getAdmName(admtypecd, admnum);
	}

	/**
	 *
	 */
	@Override
	public List<MTb02Adm> getAdmInfo(String admtypecd, String expend_01) {
		// TODO Auto-generated method stub
		return imtb02AdmMapper.getAdmInfoExpend01(admtypecd, expend_01);
	}

	/**
	 *
	 */
	@Override
	public List<MTb02Adm> getAdmInfo(String admtypecd, String expend_01, String expend_02) {
		// TODO Auto-generated method stub
		return imtb02AdmMapper.getAdmInfoExpend0102(admtypecd, expend_01, expend_02);
	}

	/**
	 *
	 */
	@Override
	public List<MTb02Adm> getAdmExamInfo(String admValue01) {
		// TODO Auto-generated method stub
		return imtb02AdmMapper.getAdmExamInfo(admValue01);
	}

	@Override
	public List<MTb02Adm> getThreeList(String course_catagory) {
		List<MTb02Adm> threeList = null;
		try {
			threeList = imtb02AdmMapper.getThreeList(course_catagory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return threeList;
	}

	/**
	 * 得到数据条数
	 */
	@Override
	public int getCourseCreditSum(String userId, String credit_year, String credit_category) {
		int resultCount = 0;
		try {

			String Count = imtb02AdmMapper.getCourseCreditSum(userId, credit_year, credit_category);
			if (Count == null || Count == "") {
				resultCount = 0;
			} else {
				resultCount = Integer.parseInt(Count);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return resultCount;
	}
}
