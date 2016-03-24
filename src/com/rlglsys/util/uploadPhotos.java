package com.rlglsys.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;

public class uploadPhotos {
  
	  //从临时文件夹下复制图片到永久文件夹
		 public boolean copyFile(String oldPath, String newPath) { 
			 int byteread = 0;
			 //读取的位数
			 InputStream in = null;
			 OutputStream out = null;
			 try {
				 //打开原文件
				 in = new FileInputStream(oldPath); 
				 //打开连接到目标文件的输出流
				 out = new FileOutputStream(newPath);
				 byte[] buffer = new byte[1024];
				 //一次读取1024个字节，当byteread为-1时表示文件已经读完
				 while ((byteread = in.read(buffer)) != -1) 
				 {
					 out.write(buffer, 0, byteread);
					 
				 }//将读取的字节写入输出流
				return true;
			 } catch (Exception e) {
				 System.out.println("复制文件失败：" + e.getMessage());
				 return false;
				 } 
			 finally {
				 //关闭输入输出流，注意先关闭输出流，再关闭输入流
				 if (out != null){
					 try {out.close();
					 } catch (IOException e) {
						 e.printStackTrace();
						 return false;
						 }
					 }if (in != null){
						 try {in.close();
						 } catch (IOException e) {
							 e.printStackTrace();
							 return false;
							 }
					}
				}
			
		 } 	
		 //删除图片
		 public void delFile(String path)
		 {
			 File file = new File(path);  
			  boolean falg = false;
			  falg=file.exists();
			  if(falg)
			  {
				  file.delete();
			  }
		 }
		 
		 
		 
	
}
