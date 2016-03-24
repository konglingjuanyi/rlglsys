package com.rlglsys.util;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.servlet.ServletUtilities;

public class BarChart extends ServletUtilities {
	//为生成的图片创建文件夹
    protected static void createTempDir()
    {
    	try {
    		Common common = new Common();
	     	String filePath = common.getGraphPath();
    		
            String tempDirName = filePath; //此路径可以在属性文件中配置
            if (tempDirName == null)
            {
                throw new RuntimeException("Temporary directory system property " + "(java.io.tmpdir) is null.");
            }
            // create the temporary directory if it doesn't exist
            File tempDir = new File(tempDirName);
            if (!tempDir.exists())
            {
               tempDir.mkdirs();
            }
    	} catch (Exception ex)
    	{
    		ex.printStackTrace();
    	}
   }

   //覆盖父类的方法
   public static String saveChartAsPNG(JFreeChart chart, int width, int height,
                                ChartRenderingInfo info, HttpSession session, String userId) throws IOException
   {
	   String returnStr = "";
	   try {
		   if (chart == null)
	         {
	               throw new IllegalArgumentException("Null 'chart' argument.");  
	         }
	         Common common = new Common();
	         CommonManager commonManager = new CommonManager();
	     	 String filePath = common.getGraphPath();
	         createTempDir();
	         // 删除以userId+"-"开头的文件
	         commonManager.delFilesByPath(filePath, userId+"-");
	         File tempFile = File.createTempFile(userId+"-", ".png", new File(filePath));
	         ChartUtilities.saveChartAsPNG(tempFile, chart, width, height, info);
	         if (session != null)
	         {
	               ServletUtilities.registerChartForDeletion(tempFile, session);
	         }
	         returnStr = tempFile.getName();
	   } catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
         return returnStr;
   }
}
