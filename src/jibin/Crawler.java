package jibin;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import db.CopyOfWriterIntoMysql;
import util.FileUtil;
import util.HttpUtil;

public class Crawler {
      public static void main(String[] args) throws Exception{   
    	  
     HashMap<String, List<HashMap<String, Object>>>rs = new HashMap<String, List<HashMap<String, Object>>>();
     CopyOfWriterIntoMysql copy = new CopyOfWriterIntoMysql();
     HashMap<String, String> map=new HashMap<String, String>();
//     Accept:application/json, text/plain, */*
//     Accept-Encoding:gzip, deflate, sdch
//     Accept-Language:zh-CN,zh;q=0.8
//     CheckError:check
//     Connection:keep-alive
//     Cookie:TYCID=fd4c22907938439897cb6a4a12a88e2b; tnet=175.13.67.77; _pk_ref.1.e431=%5B%22%22%2C%22%22%2C1475892900%2C%22https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DEo2ZRCEqWWcfKjkaaeHwDyqvoYIx0XNtGkRRnYCMw9bst8QMyvh9T3dkRqryF0Xb%26wd%3D%26eqid%3De3b1c2c40000c61d0000000357e8d0e2%22%5D; RTYCID=c4e148776ea940ba926cc058b5a040af; _pk_id.1.e431=a542ee3f0be07af9.1469422520.42.1475896416.1475892900.; _pk_ses.1.e431=*; Hm_lvt_e92c8d65d92d534b0fc290df538b4758=1474621115,1474875633,1475057253,1475811059; Hm_lpvt_e92c8d65d92d534b0fc290df538b4758=1475896416; token=1a2b062d8fce4b9bb71fca197f6d73b0; _utm=7cc5d717f5484535952ac5055579fb59
//     Host:www.tianyancha.com
//     Referer:http://www.tianyancha.com/company/22822
//     Tyc-From:normal
//     User-Agent:Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36
//     map.put("Accept","application/json, text/plain, */*");
//     map.put("Accept-Encoding","gzip, deflate, sdch");
//     map.put("Accept-Language","zh-CN,zh;q=0.8");
//     map.put("CheckError","check");
//     map.put("Connection", "keep-alive");
//     map.put("Cookie","TYCID=fd4c22907938439897cb6a4a12a88e2b; tnet=175.13.67.77; _pk_ref.1.e431=%5B%22%22%2C%22%22%2C1475892900%2C%22https%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DEo2ZRCEqWWcfKjkaaeHwDyqvoYIx0XNtGkRRnYCMw9bst8QMyvh9T3dkRqryF0Xb%26wd%3D%26eqid%3De3b1c2c40000c61d0000000357e8d0e2%22%5D; RTYCID=c4e148776ea940ba926cc058b5a040af; _pk_id.1.e431=a542ee3f0be07af9.1469422520.42.1475896416.1475892900.; _pk_ses.1.e431=*; Hm_lvt_e92c8d65d92d534b0fc290df538b4758=1474621115,1474875633,1475057253,1475811059; Hm_lpvt_e92c8d65d92d534b0fc290df538b4758=1475896416; token=1a2b062d8fce4b9bb71fca197f6d73b0; _utm=7cc5d717f5484535952ac5055579fb59");
//     map.put("Host","www.tianyancha.com");
//     map.put("loop","null");
//     map.put("Content-Type","application/json");
//     map.put("Referer","http://www.tianyancha.com/company/22822");
//     map.put("Tyc-From","normal");
//     map.put("User-Agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.76 Mobile Safari/537.36");
//     String url="http://www.tianyancha.com/search/%E7%99%BE%E5%BA%A6.json?";
//     String url="http://www.tianyancha.com/search/%E9%98%BF%E9%87%8C.json?";
      String url="http://www.tianyancha.com/company/22822.json";
      url="http://221.232.141.251/ECPS/earlyWarnInfoMore.jspx";
    	 HttpUtil hp=new HttpUtil();
         String html= hp.getHtml(url, map, "utf-8", 2);
         System.out.println("success...."+html);
         
//         String str = java.net.URLEncoder.encode("思远","utf-8");
//         System.out.println(str);
     }
      
      public static List<HashMap<String, Object>> parse(String html){
      	List<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
      	 System.out.println(html);
         Document doc=Jsoup.parse(html);
         String sup=doc.select("div.lib-category>h3").get(0).text();
         Elements eles=doc.select("ul.category-list").get(0).select("li");
         for(int i=0;i<eles.size();i++){
         HashMap<String, Object> map=new HashMap<String, Object>();
         if(!eles.get(i).select("a").attr("href").contains("http")){
        	 
        	 map.put("superclass",sup+" > "+eles.get(i).text());
             map.put("url","http://wenwen.sogou.com/"+eles.get(i).select("a").attr("href")+"&tp=7");
             list.add(map);
         }
         
         }
      	return list;
      } 
      
}


