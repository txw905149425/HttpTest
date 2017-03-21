package threadtest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import util.HttpUtil;

public class Crawler {
	
	
	public static void main(String[] args) {
		HttpUtil hp=new HttpUtil();
		ArrayList<ArrayList<HashMap<String,Object>>> records;
		String url="http://blog.caijing.com.cn/expert.html";
		HashMap<String,String> map=new HashMap<String, String>();
		ArrayList<HashMap<String,Object>> list = null;
		try {
			String html=hp.getHtml(url, map, "utf-8", 2);
			list=returnSome(html);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 int size=list.size();
	     int num=size%50==0?size/50:size/50+1;
	 for(int i=0;i<num;i++){
		 int begin=i*50;
		 int end=(i+1)*50>list.size()?list.size():(i+1)*50;
		ExecutorService execute=Executors.newFixedThreadPool(num);
		ThreadSplit thread=new ThreadSplit("thread"+i,begin, end, list);
		execute.execute(thread);
		execute.shutdown();
	}
//	 System.out.println(records.size());	
	 
	}

	public static ArrayList<HashMap<String,Object>> returnSome(String html){
		ArrayList<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
		Document doc=Jsoup.parse(html);
		Elements eles=doc.select("li>a[target=_blank]");
		for(int i=0;i<eles.size();i++){
			HashMap<String,Object> map=new HashMap<String, Object>();
			String url="http://blog.caijing.com.cn"+eles.get(i).attr("href");
//			System.out.println(url);
			String name=eles.get(i).text();
//			System.out.println(name);
			map.put("name", name);
			map.put("url", url);
			list.add(map);
		}
		return list;
	}
	

	
	
}
