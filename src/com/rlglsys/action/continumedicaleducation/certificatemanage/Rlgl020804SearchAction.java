package com.rlglsys.action.continumedicaleducation.certificatemanage;

import java.util.List;
import java.util.Map;
import com.rlglsys.base.BaseAction;
import com.rlglsys.entity.MTb02Adm;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb04Unit;
import com.rlglsys.entity.Mtb64CertificateDetails;
import com.rlglsys.entity.Mtb65CertificateApply;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IRlgl010106Service;
import com.rlglsys.service.IRlgl020803Service;
import com.rlglsys.service.IRlgl020804Service;
import com.rlglsys.util.CheckFlow;
import com.rlglsys.util.Constant;
import com.rlglsys.util.DateTimeManager;

public class Rlgl020804SearchAction extends BaseAction {

    private static final long serialVersionUID = 483775379303898192L;
    private IRlgl020804Service rlgl020804Service;
    private IRlgl010106Service rlgl010106Service;
    private IMTb02AdmService mtb02AdmService;
    private List<Mtb65CertificateApply> resultList;
    private Mtb65CertificateApply rlgl020804bean;
    @SuppressWarnings("unused")
	private List<Mtb64CertificateDetails> certList;
    private IRlgl020803Service rlgl020803Service;
    @SuppressWarnings("unused")
	private List<MTb02Adm> statusList;
	// 分页用
    private int recordCount;
    private String txtInputPage = "";
    private String hdnCountOfPage = "";
    private String flg = "";

	/**
     * 查询处理
     * 
     * @return SUCCESS
     * @throws Exception
     */
    @Override
    protected String doExecute() throws Exception {
       if (rlgl020804bean == null) {
           rlgl020804bean = (Mtb65CertificateApply)super.getSession().getAttribute("rlgl020804bean");
       }
       if (!"".equals(rlgl020804bean.getUnitNo())) {
           rlgl020804bean.setUnitNm(rlgl010106Service.getUnitInfo(rlgl020804bean.getUnitNo()).getUnit_nm());
       }
       if (flg == null || "".equals(flg)) {
    	   flg = (String)super.getSession().getAttribute("flg");
       }
       if ("2".equals(flg)) {
           // 证书查询
           rlgl020804bean.setCheck_result("002");
       }
       // 用户信息
       Mtb01User userInfo = (Mtb01User)super.getSession().getAttribute(Constant.SESSION_KEY_LOGINUSER);
       // 用户单位
       String userUnitNo = userInfo.getUnit_no();
       
       String selectPage = (String)super.getSession("selectPage");
       //个人用户入口
       if ("loginpage3".equals(selectPage)) {
           rlgl020804bean.setSearchKbn("1");
           // 用户信息
           rlgl020804bean.setPersonnelId(userInfo.getPersonnel_id());
           rlgl020804bean.setPersonnelNm(userInfo.getUser_name());
           rlgl020804bean.setUnitNo(userInfo.getUnit_no());
           Mtb04Unit unitinfo = rlgl010106Service.getUnitInfo(userInfo.getUnit_no());
           rlgl020804bean.setUnitNm(unitinfo.getUnit_nm());
       } else {
           rlgl020804bean.setSearchKbn("0");
       }
        // 每页条数
        int pageCount = getPageCount();
        int pageNo = "".equals(txtInputPage)? 0*pageCount:(Integer.valueOf(txtInputPage) - 1)*pageCount;
        // 总件数取得
        recordCount = rlgl020804Service.getCount(rlgl020804bean,userUnitNo);
        rlgl020804bean.setPageCount(pageCount);
        rlgl020804bean.setPageNo(pageNo);
        //执行检索
        resultList = rlgl020804Service.search(rlgl020804bean,userUnitNo);
        //一览显示设定
        rlgl020804bean.setShow_flag("1");
        String date = "";
        if (resultList != null && resultList.size()>0) {
            String check_result = "";
            String result_Name = "";
            for (int i=0; i<resultList.size(); i++) {
                //申请时间
                date = DateTimeManager.dateChange(resultList.get(i).getApply_date());
                resultList.get(i).setApply_date(date);
                // 证书名称
                String certNo = resultList.get(i).getCert_no();
                Mtb64CertificateDetails certInfo = rlgl020803Service.getCert(certNo);
                if (certInfo != null) {
                    resultList.get(i).setCertNm(certInfo.getCert_name());
                }
                //审核状态
                check_result = resultList.get(i).getCheck_result();
                result_Name = mtb02AdmService.getAdmName("042", check_result);
                resultList.get(i).setCheck_resultNm(result_Name);
                Mtb04Unit unitinfo = rlgl010106Service.getUnitInfo(resultList.get(i).getUnitNo());
                // 审核结果
                String result = resultList.get(i).getCheck_result();
                // 当前节点
                String nowMark = resultList.get(i).getNow_mark();
                // 终审节点
                String ednMark = resultList.get(i).getEnd_mark();
                // 单位级别
                String scale = unitinfo.getUnit_level();
                String flow="";
                String stutas="";
                // 审核流程状态
                CheckFlow checkFlow = new CheckFlow();
                Map<String,String> checkInfoMap = null;
                if ("37".equals(resultList.get(i).getUnitNo())) {
                    checkInfoMap = checkFlow.getUnitFlow(ednMark, scale, nowMark, result);
                } else {
                    checkInfoMap = checkFlow.getPersonalFlow(ednMark,scale,nowMark,result);
                }
                flow = checkInfoMap.get("flow");
                stutas = checkInfoMap.get("stutas");
                resultList.get(i).setFlow(flow);
                resultList.get(i).setStutas(stutas);
            }
        }
        super.getSession().setAttribute("rlgl020804bean", rlgl020804bean);
        super.getSession().setAttribute("flg", flg);
        return SUCCESS;
    }

    // ----------------get,set--------------------

    public String getFlg() {
		return flg;
	}

	public void setFlg(String flg) {
		this.flg = flg;
	}

    public IRlgl020803Service getRlgl020803Service() {
		return rlgl020803Service;
	}

	public void setRlgl020803Service(IRlgl020803Service rlgl020803Service) {
		this.rlgl020803Service = rlgl020803Service;
	}

	public IRlgl020804Service getRlgl020804Service() {
        return rlgl020804Service;
    }

    public void setRlgl020804Service(IRlgl020804Service rlgl020804Service) {
        this.rlgl020804Service = rlgl020804Service;
    }

    public List<Mtb65CertificateApply> getResultList() {
        return resultList;
    }

    public void setResultList(List<Mtb65CertificateApply> resultList) {
        this.resultList = resultList;
    }

    public Mtb65CertificateApply getRlgl020804bean() {
        return rlgl020804bean;
    }

    public void setRlgl020804bean(Mtb65CertificateApply rlgl020804bean) {
        this.rlgl020804bean = rlgl020804bean;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public String getTxtInputPage() {
        return txtInputPage;
    }

    public void setTxtInputPage(String txtInputPage) {
        this.txtInputPage = txtInputPage;
    }

    public String getHdnCountOfPage() {
        return hdnCountOfPage;
    }

    public void setHdnCountOfPage(String hdnCountOfPage) {
        this.hdnCountOfPage = hdnCountOfPage;
    }

    
    public IMTb02AdmService getMtb02AdmService() {
        return mtb02AdmService;
    }

    public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
        this.mtb02AdmService = mtb02AdmService;
    }

    public IRlgl010106Service getRlgl010106Service() {
        return rlgl010106Service;
    }

    public void setRlgl010106Service(IRlgl010106Service rlgl010106Service) {
        this.rlgl010106Service = rlgl010106Service;
    }

	@SuppressWarnings("unchecked")
	public List<Mtb64CertificateDetails> getCertList() {
		return (List<Mtb64CertificateDetails>)super.getSession().getAttribute("certList");
	}

	public void setCertList(List<Mtb64CertificateDetails> certList) {
		this.certList = certList;
	}

    @SuppressWarnings("unchecked")
	public List<MTb02Adm> getStatusList() {
		return (List<MTb02Adm>)super.getSession().getAttribute("statusList");
	}

	public void setStatusList(List<MTb02Adm> statusList) {
		this.statusList = statusList;
	}

}
