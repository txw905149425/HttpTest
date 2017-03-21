package gaodig.com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

import util.FileUtil;
import util.HttpUtil;

public class XueQiu {
	
	   public static void main(String[] args) throws ClientProtocolException, IOException {
//		 String url="http://xueqiu.com/k?q=000876&sort=time&page=1&source=notice";
		 HashMap<String, String> map=new HashMap<String, String>();
		 PrintWriter pw=new PrintWriter(new FileWriter("d:/hh.txt",true));
		 map.put("Cookie", "s=761192ws3m; xq_a_token=664f9c2f3a106367614a9f7e02f0110cd356d056; xq_r_token=5aa2870ca6af12b59c8d16bd3e8939d7d02ab41e; __utmt=1; Hm_lvt_1db88642e346389874251b5a1eded6e3=1470708192,1470789966,1470791288,1470791530; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1470796793; __utma=1.1882509610.1470796785.1470796785.1470796785.1; __utmb=1.3.10.1470796785; __utmc=1; __utmz=1.1470796785.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)");
		 HttpUtil http=new HttpUtil();
		 FileUtil fu=new FileUtil();
		 ArrayList<String> list=fu.readFileReturn("d:/code.txt");
		 for(String code:list){
			 String url="http://xueqiu.com/k?q="+code+"&sort=time&page=1&source=notice";
			 System.out.println(url);
			 String html=http.getHtml(url, map,"utf8", 3);
			 String json=parseList(html);
			 System.out.println(json);
			 if(json!=""){
				 pw.println(code+"=>"+json);
			 }else{
				 pw.println(code+"=>");
			 }
			 
		 }		 
		 pw.close();
	   }
	   
	   public static  String parseList(String html){
		   Document doc=Jsoup.parse(html);
		   Elements eles=doc.select("script");
		   String json="";
		   System.out.println(eles.size());
		   for(int i=0;i<eles.size();i++){
			   if(eles.get(i).toString().contains("SNB.data.search")){
//				  System.out.println(eles.get(i).toString().split("\"profile\":")[]);
				if(eles.get(i).toString().contains("profile")){  
					String[] str=eles.get(i).toString().split("\"profile\":");
					String tmp="";
					for(int j=1;j<str.length;j++){
						tmp=eles.get(i).toString().split("\"profile\":")[j].split(",")[0].replaceAll("\"","");
//						System.out.println(tmp+" "+tmp.length());
						if(tmp.length()==11){
							break;
						}
					}
			    json="http://xueqiu.com"+tmp;
				}else{
					System.out.println("*************************");
					System.out.println(eles.get(i).toString());
				}
//			   System.out.println("parseList..........................");
			   }
		   }
	       return json;
	   }
	   
	
	  	   
}
