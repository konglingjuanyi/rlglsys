package com.rlglsys.action.register;

import java.util.ArrayList;
import java.util.List;

import com.rlglsys.base.BaseAction;
import com.rlglsys.entity.MTb02Adm;
import com.rlglsys.entity.Mtb04Unit;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IMTb04UnitService;
/**
 * 个人注册初始化action
 * @author Administrator
 *
 */
public class RegInitAction extends BaseAction {

	private static final long serialVersionUID = -309387131831358294L;
	// 单位service
	private IMTb04UnitService mtb04UnitService;
	// 页面上的下拉框
	private List<Mtb04Unit> mtb04unitList;
	// 人员进入方式
	private List<MTb02Adm> mtb02Adm094List;
	private IMTb02AdmService mtb02AdmService;

	@Override
	public String doExecute() throws Exception {
		
		try {
			// 单位下拉框
			 mtb04unitList = mtb04UnitService.getUnitList("3711");
			if(mtb04unitList == null){
				mtb04unitList =  new ArrayList<Mtb04Unit>();
			}
			// 人员进入方式
			mtb02Adm094List = mtb02AdmService.getAdmInfo("094");
			if (mtb02Adm094List == null)
			{
				mtb02Adm094List = new ArrayList<MTb02Adm>();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	// ============= get set========================
	public IMTb04UnitService getMtb04UnitService() {
		return mtb04UnitService;
	}

	public void setMtb04UnitService(IMTb04UnitService mtb04UnitService) {
		this.mtb04UnitService = mtb04UnitService;
	}

	public List<Mtb04Unit> getMtb04unitList() {
		return mtb04unitList;
	}

	public void setMtb04unitList(List<Mtb04Unit> mtb04unitList) {
		this.mtb04unitList = mtb04unitList;
	}

	public List<MTb02Adm> getMtb02Adm094List() {
		return mtb02Adm094List;
	}

	public void setMtb02Adm094List(List<MTb02Adm> mtb02Adm094List) {
		this.mtb02Adm094List = mtb02Adm094List;
	}

	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}

	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
	}
	
	

}
