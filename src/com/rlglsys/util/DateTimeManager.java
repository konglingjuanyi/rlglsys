package com.rlglsys.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import freemarker.core.ParseException;

public class DateTimeManager {
	/**
	 * 日期时间格式yyyyMMddhhmmss
	 * @return 
	 */
	public static String getSystemDate14() {
		Date systemDate = new Date();
		DateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
		fmt.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai")); 
		return fmt.format(systemDate);
	}
	 /**
     * 获取当前日期和时间格式yyyy-MM-dd HH:mm:ss
     * @return String
     */
    public static String getCurrentDateStr() {
		 Date date = new Date();
		 String str = null;
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai")); 
		 str = df.format(date);
		 return str;
    }
    
	 /**
     * 获取当前日期和时间格式yyyy-MM-dd
     * @return String
     */
    public static String getCurrentDateStrFormat() {
		 Date date = new Date();
		 String str = null;
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		 df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai")); 
		 str = df.format(date);
		 return str;
    }
    /**
     * 日期格式转换
     * @param date
     * @return
     * @throws java.text.ParseException 
     */
    public static String dateChange(String date) throws ParseException, java.text.ParseException{
    	if(date !=null && !"".equals(date))
    	{
    		//日期时间类型格式转换
        	if(date.length()>10)
    		{
    	    	if(date.indexOf("-")==-1)
    	    	{
    	    		SimpleDateFormat myfmt1 = new SimpleDateFormat("yyyyMMddHHmmss");   
    	    		SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		    	date=myfmt.format(myfmt1.parse(date)).toString();
    	    		
    	    	}
    	    	else
    	    	{
    	    		SimpleDateFormat myfmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    	    		SimpleDateFormat myfmt = new SimpleDateFormat("yyyyMMddHHmmss");       
    	        	date=myfmt.format(myfmt1.parse(date)).toString();
    	    	}
    		}
        	//日期类型格式转换
    		else
    		{
    			if(date.indexOf("-")==-1)
    	    	{
    				SimpleDateFormat myfmt1 = new SimpleDateFormat("yyyyMMdd");
    	    		SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd");       
    		    	date=myfmt.format(myfmt1.parse(date)).toString();
    	    		
    	    	}
    	    	else
    	    	{
    	    		SimpleDateFormat myfmt1 = new SimpleDateFormat("yyyy-MM-dd");
    	    		SimpleDateFormat myfmt = new SimpleDateFormat("yyyyMMdd");       
    	        	date=myfmt.format(myfmt1.parse(date)).toString();
    	    	}
    			
    		}
    	
    	
	}
    	return date;	
			
	}

}
