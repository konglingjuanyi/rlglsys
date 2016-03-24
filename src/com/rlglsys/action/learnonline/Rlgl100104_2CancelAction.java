package com.rlglsys.action.learnonline;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl100104Bean;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb120SysUrl;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IRlgl020803Service;
import com.rlglsys.service.IRlgl050101Service;
import com.rlglsys.service.IRlgl100104Service;
import com.rlglsys.service.IUserService;
import com.rlglsys.util.Common;
import com.rlglsys.util.Constant;

public class Rlgl100104_2CancelAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private IRlgl100104Service rlgl100104Service;
	private String xz_ckb;

	@Override
	protected String doExecute() throws Exception {

    	try {
    		// 用户对象
            Mtb01User loginUser = (Mtb01User)super.getSession(Constant.SESSION_KEY_LOGINUSER);
            String userid = loginUser.getUser_id();
            //替换空白的空格
            String[] courseIds = xz_ckb.replace(" ", "").split(",");
    		//删除操作
    		List<String> ids = new ArrayList<String>();
    		for(String courseId :courseIds){
    			ids.add(courseId);
    		}
    		rlgl100104Service.deleteCourse(ids,userid);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		return SUCCESS;
	}

	public IRlgl100104Service getRlgl100104Service() {
		return rlgl100104Service;
	}

	public void setRlgl100104Service(IRlgl100104Service rlgl100104Service) {
		this.rlgl100104Service = rlgl100104Service;
	}

	public String getXz_ckb() {
		return xz_ckb;
	}

	public void setXz_ckb(String xz_ckb) {
		this.xz_ckb = xz_ckb;
	}
	
	


	
	
}
