package gaodig.com;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import util.FileUtil;

public class DownLoad {
      public static void main(String[] args) {
    	  FileUtil fu=new FileUtil();
//    	 String url= "http://api.xueqiu.com/stock/f10/balsheet.csv?symbol=SZ000776&page=1&size=10000";
 		 ArrayList<String> list=fu.readFileReturn("d:/last.txt");
 		 System.out.println(list.size());
 		 for(String url:list){
 			 String filename=url.substring(url.length()-8,url.length())+".csv";
 			String uri="http://api.xueqiu.com/stock/f10/cfstatement.csv?symbol="+url.substring(url.length()-8,url.length())+"&page=1&size=10000";
// 			System.out.println(uri);
 			try{  
 	              downLoadFromUrl(uri, filename,"d:/test");  
 	             System.out.println("info:"+url+"   success");   
 	           }catch (Exception e) {  
 	        	   System.out.println("*************************shibai****************");
 	               // TODO: handle exception  
 		 
 	           }  
 		 }
    	   System.out.println("end#######################");
 		 
	  }
      
      
      public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{  
          URL url = new URL(urlStr);    
          HttpURLConnection conn = (HttpURLConnection)url.openConnection();    
                  //设置超时间为5秒  
          conn.setConnectTimeout(5*1000);  
          //防止屏蔽程序抓取而返回403错误  
          conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
          //得到输入流  
          InputStream inputStream = conn.getInputStream();    
          //获取自己数组  
          byte[] getData = readInputStream(inputStream);      
          //文件保存位置  
          File saveDir = new File(savePath);  
          if(!saveDir.exists()){  
              saveDir.mkdir();  
          }  
          File file = new File(saveDir+File.separator+fileName);      
          FileOutputStream fos = new FileOutputStream(file);       
          fos.write(getData);   
          if(fos!=null){  
              fos.close();    
          }  
          if(inputStream!=null){  
              inputStream.close();  
          }  
    
      }  
      
      public static  byte[] readInputStream(InputStream inputStream) throws IOException {    
          byte[] buffer = new byte[3*1024];    
          int len = 0;    
          ByteArrayOutputStream bos = new ByteArrayOutputStream();    
          while((len = inputStream.read(buffer)) != -1) {    
              bos.write(buffer, 0, len);    
          }    
          bos.close();    
          return bos.toByteArray();    
      }    
	
}
