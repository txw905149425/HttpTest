package temp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import db.CopyOfWriterIntoMysql;
import util.FileUtil;
import util.HttpUtil;

public class TempForHexun {
       
       public static void main(String[] args) {
    	   HttpUtil ht=new HttpUtil();
    	   CopyOfWriterIntoMysql copy = new CopyOfWriterIntoMysql();
    	   String html="";
          FileUtil fu=new FileUtil();
          ArrayList<String> list=fu.readFileReturn("d:/a2.txt");
    	   for(String url:list){
    	   HashMap<String, String> map=new HashMap<String, String>();
    	  try{
    		  html=ht.getHtml(url, map,"utf8", 3);
    		  }
    	  catch(ClientProtocolException e){
    		  e.printStackTrace();
    	  }catch (IOException e) {
    		  e.printStackTrace();
		 }
    	  Parse parse=new Parse();
    	  HashMap<String, List<HashMap<String, Object>>> sql=parse.parseMain(html,url);
          copy.saveRecord(sql);
          System.out.println("OK..........");
    	 }
    	   System.out.println("end............................");
	   }
       
       
       
}
