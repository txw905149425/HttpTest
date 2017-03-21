package code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import db.CopyOfWriterIntoMysql;
import util.HttpUtil;

public class Crawler {
	
         public static void main(String[] args) throws Exception{
        	 HttpUtil hp=new HttpUtil();
        	 CopyOfWriterIntoMysql copy = new CopyOfWriterIntoMysql();
        	 HashMap<String, List<HashMap<String, Object>>>rs = new HashMap<String, List<HashMap<String, Object>>>();
        	 for(int i=0;i<445;i++){
        		 String url="http://www.neeq.com.cn/nqxxController/nqxx.do?page="+i+"&typejb=T&xxzqdm=&xxzrlx=&xxhyzl=&xxssdq=&sortfield=xxzqdm&sorttype=asc&dicXxzbqs=&xxfcbj=&_=1472606509068";
        		 HashMap<String,String> map=new HashMap<String,String>();
        		 String html=hp.getHtml(url, map,"utf8", 3).replace("null([", "").replace("])", "");
//        		 System.out.println(html);
        		 List<HashMap<String, Object>> last= parse(html);
        		 rs.put("company_list", last);
        		 copy.saveRecord(rs);
        	 }
//        	 System.out.println(rs.size());
//        	 copy.saveRecord(rs);
        	
		}
        public static List<HashMap<String, Object>> parse(String html){
        	List<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
        	JSONObject obj=JSONObject.fromObject(html);
        	JSONArray arry=obj.getJSONArray("content");
        	for(int i=0;i<arry.size();i++){
        			HashMap<String, Object> map=new HashMap<String, Object>();
        			JSONObject json=(JSONObject)arry.getJSONObject(i);
            	    Object company=json.get("xxzqjc");
            	    Object code=json.get("xxzqdm");
            	    Object type=json.get("xxfcbj");
            	    Object zhuan=json.get("xxzrlx");
            	    Object own=json.get("xxhyzl");
            	    Object host=json.get("xxzbqs");
            	    Object area=json.get("xxssdq");
            	    map.put("company_name",company);
            	    map.put("company_code", code);
            	    map.put("company_type", type);
            	    map.put("transfer_type", zhuan);
            	    map.put("owned_industry", own);
            	    map.put("host_brokerage", host);
            	    map.put("area", area);
            	    list.add(map);
//            	    System.out.println(i+"  "+company+"  "+type +"  "+code +"  "+zhuan +"  "+own+"  "+host+"  "+area);
        	}
        	return list;
        } 
         
         
         
}
