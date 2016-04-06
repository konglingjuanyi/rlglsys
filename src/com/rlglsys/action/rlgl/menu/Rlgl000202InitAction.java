package com.rlglsys.action.rlgl.menu;

import java.util.ArrayList;
import java.util.List;
import com.rlglsys.base.BaseAction;
import com.rlglsys.bean.Rlgl100101_1Bean;
import com.rlglsys.entity.Mtb01User;
import com.rlglsys.entity.Mtb03Menu;
import com.rlglsys.entity.Mtb04Unit;
import com.rlglsys.entity.Mtb39Personnel;
import com.rlglsys.entity.Mtb72Userrole;
import com.rlglsys.entity.Mtb73Roleaction;
import com.rlglsys.service.IMTb02AdmService;
import com.rlglsys.service.IMenuService;
import com.rlglsys.service.IRlgl010306Service;
import com.rlglsys.service.IRlgl050101Service;
import com.rlglsys.service.IRlgl100101Service;
import com.rlglsys.util.Common;
import com.rlglsys.util.Constant;
import com.rlglsys.util.CommonManager;

public class Rlgl000202InitAction extends BaseAction {

    private static final long serialVersionUID = 483775379303898192L;
	private IMTb02AdmService  mtb02AdmService;
    private List<Mtb03Menu> menuList;
	private IRlgl050101Service rlgl050101Service;
    private Mtb03Menu menu;
    // MenuService
    private IMenuService menuService;
	private IRlgl100101Service rlgl100101Service;
	private IRlgl010306Service rlgl010306Service; 
    /**
     * 菜单取得处理
     * @return SUCCESS
     * @throws Exception
     */
    @Override
    public String doExecute() throws Exception {
        try {
            CommonManager common = new CommonManager();
			// 用户对象
            Mtb01User loginUser = (Mtb01User)super.getSession(Constant.SESSION_KEY_LOGINUSER);
            Mtb04Unit unitInfo = (Mtb04Unit)super.getSession("UnitInfo");
            
            // 系统超级用户取得
            String sysUser = common.getSystemUser("rlglsys.sys.manager");
            
            // 设定检索条件对象
            Mtb73Roleaction roleactionInfo = new Mtb73Roleaction();
            if (!loginUser.getUser_id().equals(sysUser))
            {
            	Mtb72Userrole rileInfo = new Mtb72Userrole();
                rileInfo.setUser_id(loginUser.getUser_id());
                rileInfo.setUser_enter(loginUser.getUser_enter());
                
                List<String> roleList = new ArrayList<String>();
                
                // 用户角色取得
                List<Mtb72Userrole> userRoleInfoList = menuService.getUserRoleInfoList(rileInfo);
            	if (userRoleInfoList != null && userRoleInfoList.size() > 0)
            	{
            		for (int i = 0; i < userRoleInfoList.size(); i++)
            		{
            			Mtb72Userrole roleInfo = userRoleInfoList.get(i);
            			roleList.add(roleInfo.getUnit_role());
            		}
            		 // 单位编号
                    roleactionInfo.setUnit_role(userRoleInfoList.get(0).getUnit_role());
                    roleactionInfo.setUser_enter(userRoleInfoList.get(0).getUser_enter());
                    roleactionInfo.setRoleIdList(roleList);
            	}
            }
        	// Menu菜单取得编辑处理
            List<Mtb03Menu> mtb03MenuList = this.getMenuList(roleactionInfo, unitInfo.getUnit_status(), sysUser);
            // MenU菜单信息
            super.setSession("menuList", mtb03MenuList);
        } catch(Exception ex)
        {
            ex.printStackTrace();
            throw ex;
        }
        return SUCCESS;
    }

    /**
     * Menu菜单取得编辑处理
     * @param selectPage
     * @return menuList
     * @throws Exception
     */
    public List<Mtb03Menu> getMenuList(Mtb73Roleaction roleactionInfo, String unitStatus, String sysUser) throws Exception {
        try {
			// 用户对象
            Mtb01User loginUser = (Mtb01User)super.getSession(Constant.SESSION_KEY_LOGINUSER);
            Mtb04Unit unitInfo = (Mtb04Unit)super.getSession("UnitInfo");
        	// 入口标记
            String selectPage = "";
        	if (super.getSession("selectPage") != null)
        	{
        		selectPage = (String)super.getSession("selectPage");
        		System.out.println("selectPage:"+selectPage);
        	}
        	
        	 // 入口判断标记
            String entrance = null;
            // 医疗卫生机构入口的场合
            if ("loginpage1".equals(selectPage))
            {
                entrance = "1";
            }
            // 卫生行政部门入口的场合
            if ("loginpage2".equals(selectPage))
            {
                entrance = "2";;
            }
            // 卫生技术人员入口的场合
            if ("loginpage3".equals(selectPage))
            {
                entrance = "3";
            }
            // 乡医培训入口的场合
            if ("loginpage4".equals(selectPage))
            {
                entrance = "4";
            }
            
            List<String> entrance4List = new ArrayList<String>();
            entrance4List.add("0");
            // 预付费场合
            if ("01".equals(unitInfo.getUnit_payment()))
            {
            	entrance4List.add("1");
            } else {
            	entrance4List.add("2");
            }
            roleactionInfo.setEntrance4("1");
            roleactionInfo.setEntrance4List(entrance4List);
            
            // 取得菜单信息
            List<Mtb03Menu> menuList1 = null;
            if (!sysUser.equals(loginUser.getUser_id()))
            {
                roleactionInfo.setEntrance(entrance);
                menuList1 = menuService.getMenuInfoByUserList(roleactionInfo);
                for(int i = 0; i < menuList1.size(); i++){
                	   System.out.println("======0000000000=====menu:"+menuList1.get(i).getMenu_name());
                   }
            } else {
            	menuList1 = menuService.getMenuInfoList("");
            	for(int i = 0; i < menuList1.size(); i++){
                	   System.out.println("======3333333=====menu:"+menuList1.get(i).getMenu_name());
                   }
            }
            
            
            if (!"loginpage3".equals(selectPage))
            {
            	menuList1 = this.filteMenuList(menuList1, unitStatus, selectPage);
            }
            for(int i = 0; i < menuList1.size(); i++){
            	   System.out.println("======44444444=====menu:"+menuList1.get(i).getMenu_name());
               }
//            // 个人菜单过滤
//            if ("loginpage3".equals(selectPage) || "loginpage4".equals(selectPage))
//            {
//                // 过滤
//               menuList1 = this.filteMenuList2(menuList1, unitInfo.getUnit_payment());
//            }
            
            // 个人菜单过滤
            if ("loginpage3".equals(selectPage))
            {
            	for(int i = 0; i < menuList1.size(); i++){
               	   System.out.println("======1111=====menu:"+menuList1.get(i).getMenu_name());
                  }
                // 过滤
               menuList1 = this.filteMenuList3(menuList1);
               
               for(int i = 0; i < menuList1.size(); i++){
            	   System.out.println("=====2222======menu:"+menuList1.get(i).getMenu_name());
               }
            }

            // 实例化MenuList
            menuList = new ArrayList<Mtb03Menu>();
            int level = 0;
            // 菜单编辑排序
            this.editMenuList(level, menuList1, menuList, "");
            
            // 树形菜单编辑
            this.reEditMenuList(menuList);
        } catch(Exception ex)
        {
            ex.printStackTrace();
            throw ex;
        }
        // 返回树形菜单
        return menuList;
    }
    
    /**
     * menu过滤，如果单位信息完善前进行过滤
     * @param menuList1
     * @param unitStatus
     * @return
     * @throws Exception
     */
    public List<Mtb03Menu> filteMenuList(List<Mtb03Menu> menuList1, String unitStatus, String selectPage) throws Exception {
    	List<Mtb03Menu> newMenuList = new ArrayList<Mtb03Menu>();
    	if (menuList1 != null && menuList1.size() > 0)
        {
    		// 完善前
    		if ("0".equals(unitStatus))
    		{
    			for (int i = 0; i < menuList1.size(); i++)
            	{
            		Mtb03Menu menu = (Mtb03Menu)menuList1.get(i);
            		// 信息变更、信息明细、下级单位、下级注销、统计设定、代管管理、管理员设定
            		if (!"0013".equals(menu.getMenu_id()) && !"0014".equals(menu.getMenu_id()) 
            				&& !"0112".equals(menu.getMenu_id()) && !"0113".equals(menu.getMenu_id()) && !"0100".equals(menu.getMenu_id()))
            		{
            			newMenuList.add(menu);
            		}
            	}
    		}
    		// 完善后
    		if ("1".equals(unitStatus))
    		{
    			for (int i = 0; i < menuList1.size(); i++)
            	{
    				// 信息完善
    				Mtb03Menu menu = (Mtb03Menu)menuList1.get(i);
            		if (!"0012".equals(menu.getMenu_id()))
            		{
            			newMenuList.add(menu);
            		}
            		
            	}
    		}
        }
    	return newMenuList;
    }
    
    /**
     * menu过滤，预付费
     * @param menuList1
     * @param unitStatus
     * @return
     * @throws Exception
     */
    public List<Mtb03Menu> filteMenuList2(List<Mtb03Menu> menuList1, String unitPayment) throws Exception {
    	List<Mtb03Menu> newMenuList = new ArrayList<Mtb03Menu>();
		// 登陆用户信息
		Mtb01User loginUser = (Mtb01User)super.getSession(Constant.SESSION_KEY_LOGINUSER);
	    String user_id=(String)loginUser.getUser_id();
	    
	    //获得当前年度
	    Common common = new Common();
	    //获得当前学分年度（申请年度）
    	String area_id = mtb02AdmService.getAdmName("237", "01");
	    String payyear = Integer.toString(common.getNowCreditYear(area_id, rlgl050101Service));
	    
//	    // 判断该用户是否已缴费
//	    Rlgl100101_1Bean rlgl100101_1Bean =new Rlgl100101_1Bean();
//	    int cnt = 0;
//	    rlgl100101_1Bean.setUserId(user_id);
//	    rlgl100101_1Bean.setPay_year(payyear);
//
//    	if (menuList1 != null && menuList1.size() > 0)
//        {
//	    	    try {
//	    	    	cnt = rlgl100101Service.getData3(rlgl100101_1Bean);
//	    			
//	    		} catch (Exception e) {
//	    			cnt = 0;
//	    			// TODO: handle exception
//	    			System.out.println(e);
//	    		}
//
//    		    // 已缴费
//    		   // if (cnt > 0) {
//    		    	for (int i = 0; i < menuList1.size(); i++)
//                	{
//                		Mtb03Menu menu = (Mtb03Menu)menuList1.get(i);
//                		// 在线缴费
////                		if (!"0241".equals(menu.getMenu_id()))
//                		//网上缴费
//                		if (!"0258".equals(menu.getMenu_id()))
//                		{
//                			newMenuList.add(menu);
//                		}
//                		if ("01".equals(unitPayment) && "0156".equals(menu.getMenu_id()))
//                		{
//                			continue;
//                		}
//                		// 在线选课，已选课件学习
//                		if (!"0265".equals(menu.getMenu_id()) && !"0266".equals(menu.getMenu_id()))
//                		{
//                			newMenuList.add(menu);
//                		}
//                	}
//    		    //} else {
////    		    	for (int i = 0; i < menuList1.size(); i++)
////                	{
////                		Mtb03Menu menu = (Mtb03Menu)menuList1.get(i);
////                		if ("01".equals(unitPayment) && "0156".equals(menu.getMenu_id()))
////                		{
////                			continue;
////                		}
////                		// 在线选课，已选课件学习
////                		if (!"0265".equals(menu.getMenu_id()) && !"0266".equals(menu.getMenu_id()))
////                		{
////                			newMenuList.add(menu);
////                		}
////                	}
//    		   // }
       // }
    	return newMenuList;
    }
    
    /**
     * menu过滤，预付费
     * @param menuList1
     * @param unitStatus
     * @return
     * @throws Exception
     */
    public List<Mtb03Menu> filteMenuList3(List<Mtb03Menu> menuList1) throws Exception {
    	
    	List<Mtb03Menu> newMenuList = new ArrayList<Mtb03Menu>();
    	try{
    		// 登陆用户信息
    		Mtb01User loginUser = (Mtb01User)super.getSession(Constant.SESSION_KEY_LOGINUSER);
    	    
    		Mtb39Personnel mtb39Personnel = new Mtb39Personnel();
    		String personnelId=loginUser.getPersonnel_id();
    		Mtb39Personnel personnel = new Mtb39Personnel();
    		
    		mtb39Personnel.setPersonnel_id(personnelId);
    		personnel = rlgl010306Service.searchRlgl010306(mtb39Personnel);
    		
    		if (menuList1 != null && menuList1.size() > 0)
            {
    			// 完善信息后跳到不可更改画面
    			if(personnel != null && (
    				!"000".equals(personnel.getPersonnel_isapproval()) 
    				&& !"".equals(personnel.getPersonnel_isapproval())
    				)){
    				for (int i = 0; i < menuList1.size(); i++)
                	{
                		Mtb03Menu menu = (Mtb03Menu)menuList1.get(i);
                		// 基本信息完善
                		//if (!"0264".equals(menu.getMenu_id()) && !"0078".equals(menu.getMenu_id()))
                		if (!"0078".equals(menu.getMenu_id()))
                		{
                			newMenuList.add(menu);
                		}
                	}
    			} else {
    				for (int i = 0; i < menuList1.size(); i++)
                	{
                		Mtb03Menu menu = (Mtb03Menu)menuList1.get(i);
                		// 基本信息完善
                		//&& !"0263".equals(menu.getMenu_id()) 删除
//                		if (!"0027".equals(menu.getMenu_id()) && !"0029".equals(menu.getMenu_id()) && !"0030".equals(menu.getMenu_id())
//                				&& !"0261".equals(menu.getMenu_id()) && !"0262".equals(menu.getMenu_id()) && !"0263".equals(menu.getMenu_id()))
                		if (!"0027".equals(menu.getMenu_id()) && !"0029".equals(menu.getMenu_id()) && !"0030".equals(menu.getMenu_id())
                    			&& !"0261".equals(menu.getMenu_id()))
                		{
                			newMenuList.add(menu);
                		}
                	}
    			}
            }
    	} catch (Exception ex)
    	{
    		ex.printStackTrace();
    	}
		
    	return newMenuList;
    }
    /**
     * menu过滤，如果单位信息完善前进行过滤
     * @param menuList1
     * @param unitStatus
     * @return
     * @throws Exception
     */
    public List<Mtb03Menu> filteMenuListPersonnel(List<Mtb03Menu> menuList1, String personnelStatus, String selectPage) throws Exception {
    	List<Mtb03Menu> newMenuList = new ArrayList<Mtb03Menu>();
    	if (menuList1 != null && menuList1.size() > 0)
        {
    		// 完善前
    		if ("0".equals(personnelStatus))
    		{
    			for (int i = 0; i < menuList1.size(); i++)
            	{
            		Mtb03Menu menu = (Mtb03Menu)menuList1.get(i);
            		// 信息变更、信息明细、下级单位、下级注销、统计设定、代管管理、管理员设定
            		if (!"loginpage3".equals(selectPage) && (!"0013".equals(menu.getMenu_id()) && !"0014".equals(menu.getMenu_id()) 
            				&& !"0112".equals(menu.getMenu_id()) && !"0113".equals(menu.getMenu_id()) && !"0100".equals(menu.getMenu_id())))
            		{
            			newMenuList.add(menu);
            		} else if ("loginpage3".equals(selectPage) && (!"0013".equals(menu.getMenu_id()) && !"0014".equals(menu.getMenu_id()) 
            				&& !"0112".equals(menu.getMenu_id()) && !"0113".equals(menu.getMenu_id()))){
            			newMenuList.add(menu);
            		}
            	}
    		}
    		// 完善后
    		if ("1".equals(personnelStatus))
    		{
    			for (int i = 0; i < menuList1.size(); i++)
            	{
    				// 信息完善
    				Mtb03Menu menu = (Mtb03Menu)menuList1.get(i);
            		if (!"0012".equals(menu.getMenu_id()))
            		{
            			newMenuList.add(menu);
            		}
            	}
    		}
        }
    	return newMenuList;
    }
    
    /**
     * 数据库中取得MenuList
     * @param level
     * @param menuList
     * @param newMenuList
     * @param paramId
     */
     public void editMenuList(int level, List<Mtb03Menu> menuList, List<Mtb03Menu> newMenuList, String paramId)
        {
             // Menu数据为空的时候，直接返回
            if (menuList == null || menuList.size() == 0)
            {
                return;
            }
            // 循环遍历MenuList进行排序
            for (int i = 0; i < menuList.size(); i++)
            {
                Mtb03Menu menu = (Mtb03Menu)menuList.get(i);
                // 判断是否是同一层的Menu
                if (menu.getMenu_level() == level && paramId.equals(menu.getMenu_param_id()))
                {
                    newMenuList.add(menu);
                    // 递归调用
                    this.editMenuList(level+1, menuList, newMenuList, menu.getMenu_id());
                }
                else if (menu.getMenu_level() > level)
                {
                    // 退出循环
                    break;
                }
            }
        }
    /**
     * 树形菜单生成处理
     * @param menuList
     */
    public void reEditMenuList(List<Mtb03Menu> menuList)
    {
        // Menu数据为空的时候，直接返回
        if (menuList == null || menuList.size() == 0)
        {
            return;
        }
        // 循环遍历MenuList
        for (int i = 0; i< menuList.size(); i++)
        {
            Mtb03Menu menu = (Mtb03Menu)menuList.get(i);
            if (i == menuList.size() - 1) {
                menu.setMenu_level_crl1(0);
                menu.setMenu_level_crl3(menu.getMenu_level());
                continue;
            }
            Mtb03Menu menuNext = (Mtb03Menu)menuList.get(i + 1);
            if (menuNext.getMenu_level() > menu.getMenu_level()) {
                menu.setMenu_level_crl1(1);
            } else {
               menu.setMenu_level_crl1(0);
            }
            if (menuNext.getMenu_level() < menu.getMenu_level()) {
               menu.setMenu_level_crl3(menu.getMenu_level() - menuNext.getMenu_level());
            } else {
                menu.setMenu_level_crl3(0);
            }
        }
    }
     
    /**
     * @return the menuList
     */
    public List<Mtb03Menu> getMenuList() {
        return menuList;
    }

    /**
     * @param menuList the menuList to set
     */
    public void setMenuList(List<Mtb03Menu> menuList) {
        this.menuList = menuList;
    }

    /**
     * @return the menu
     */
    public Mtb03Menu getMenu() {
        return menu;
    }

    /**
     * @param menu the menu to set
     */
    public void setMenu(Mtb03Menu menu) {
        this.menu = menu;
    }

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	public IRlgl100101Service getRlgl100101Service() {
		return rlgl100101Service;
	}

	public void setRlgl100101Service(IRlgl100101Service rlgl100101Service) {
		this.rlgl100101Service = rlgl100101Service;
	}

	public IMTb02AdmService getMtb02AdmService() {
		return mtb02AdmService;
	}

	public void setMtb02AdmService(IMTb02AdmService mtb02AdmService) {
		this.mtb02AdmService = mtb02AdmService;
	}

	public IRlgl050101Service getRlgl050101Service() {
		return rlgl050101Service;
	}

	public void setRlgl050101Service(IRlgl050101Service rlgl050101Service) {
		this.rlgl050101Service = rlgl050101Service;
	}

	public IRlgl010306Service getRlgl010306Service() {
		return rlgl010306Service;
	}

	public void setRlgl010306Service(IRlgl010306Service rlgl010306Service) {
		this.rlgl010306Service = rlgl010306Service;
	}
}