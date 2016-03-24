package com.rlglsys.service.impl;

import java.util.List;
import com.rlglsys.bean.Rlgl100101_1Bean;
import com.rlglsys.entity.MTb80CourseSelected;
import com.rlglsys.mapper.IRlgl100100Mapper;
import com.rlglsys.service.IRlgl100100Service;

public class Rlgl100100ServiceImpl implements IRlgl100100Service{

    private IRlgl100100Mapper rlgl100100Mapper;

    public IRlgl100100Mapper getRlgl100100Mapper() {
        return rlgl100100Mapper;
    }

    public void setRlgl100100Mapper(IRlgl100100Mapper rlgl100100Mapper) {
        this.rlgl100100Mapper = rlgl100100Mapper;
    }

    /**
     *  查询个人缴费记录数
     */
    @Override
    public int getPrepayMsgCount(String user_id,String succeed) {
        // TODO Auto-generated method stub
        int count = 0;
        try {
            count = rlgl100100Mapper.getPrepayMsgCount(user_id,succeed);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return count;
    }
    
   
    
    /**
     * 查询个人缴费明细列表
     */
    @Override
    public List<Rlgl100101_1Bean> getPrepayMsgTempList(String user_id,
            int pageCount, int pageNo,String succeed) {
        // TODO Auto-generated method stub
        List<Rlgl100101_1Bean> list = null;
        try {
            list = rlgl100100Mapper.getPrepayMsgTempList(user_id, pageCount, pageNo,succeed);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 通过缴费订单查询订单包含选课信息列表
     */
	@Override
	public List<MTb80CourseSelected> getSelectedCourseList(String TransactionID) {
		// TODO Auto-generated method stub
		List<MTb80CourseSelected> list = null;
        try {
            list = rlgl100100Mapper.getSelectedCourseList(TransactionID);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
	}

	/**
	 * 更新缴费申请退款状态
	 */
	@Override
	public int updateTTb02PrepayMsg(Rlgl100101_1Bean rlgl100101_1Bean) {
		// TODO Auto-generated method stub
		int result = 0;
        try {
        	result = rlgl100100Mapper.updateTTb02PrepayMsg(rlgl100101_1Bean);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return result;
	}
	
	 /**
     *  查询个人消费记录数
     */
    @Override
    public int getPrepayMsgTempCount(String user_id,String succeed) {
        // TODO Auto-generated method stub
        int count = 0;
        try {
            count = rlgl100100Mapper.getPrepayMsgTempCount(user_id,succeed);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 查询个人消费明细列表
     */
    @Override
    public List<Rlgl100101_1Bean> getPrepayMsgList(String user_id,
            int pageCount, int pageNo,String succeed) {
        // TODO Auto-generated method stub
        List<Rlgl100101_1Bean> list = null;
        try {
            list = rlgl100100Mapper.getPrepayMsgList(user_id, pageCount, pageNo,succeed);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }
    
    /**
     *  查询个人消费记录数
     */
    @Override
    public int getPrepayMsgAdminCount(String user_id,String succeed,String id) {
        // TODO Auto-generated method stub
        int count = 0;
        try {
            count = rlgl100100Mapper.getPrepayMsgAdminCount(user_id,succeed,id);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 查询个人消费明细列表
     */
    @Override
	public List<Rlgl100101_1Bean> getPrepayMsgAdminList(String user_id,
            int pageCount, int pageNo,String succeed,String id) {
        // TODO Auto-generated method stub
        List<Rlgl100101_1Bean> list = null;
        try {
            list = rlgl100100Mapper.getPrepayMsgAdminList(user_id, pageCount, pageNo,succeed,id);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }
	
	/**
     *  查询个人消费记录数
     */
    @Override
    public int getPrepayMsgTempAdminCount(String user_id,String succeed,String id) {
        // TODO Auto-generated method stub
        int count = 0;
        try {
            count = rlgl100100Mapper.getPrepayMsgTempAdminCount(user_id,succeed,id);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 查询个人消费明细列表
     */
    @Override
	public List<Rlgl100101_1Bean> getPrepayMsgTempAdminList(String user_id,
            int pageCount, int pageNo,String succeed,String id) {
        // TODO Auto-generated method stub
        List<Rlgl100101_1Bean> list = null;
        try {
            list = rlgl100100Mapper.getPrepayMsgTempAdminList(user_id, pageCount, pageNo,succeed,id);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }
	
	/**
     *  查询个人消费记录数
     */
    @Override
    public int getBalanceAdminCount(String user_id,String succeed,String id) {
        // TODO Auto-generated method stub
        int count = 0;
        try {
            count = rlgl100100Mapper.getBalanceAdminCount(user_id,succeed,id);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 查询个人消费明细列表
     */
    @Override
	public List<Rlgl100101_1Bean> getBalanceAdminList(String user_id,
            int pageCount, int pageNo,String succeed,String id) {
        // TODO Auto-generated method stub
        List<Rlgl100101_1Bean> list = null;
        try {
            list = rlgl100100Mapper.getBalanceAdminList(user_id, pageCount, pageNo,succeed,id);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return list;
    }
}
