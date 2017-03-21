package threadtest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import util.HttpUtil;

public class ThreadSplit implements Runnable{
   private String name;
   private int begin;
   private int end;
   private static int count;
   private ArrayList<HashMap<String,Object>> list;
   public static  ArrayList<ArrayList<HashMap<String,Object>>> records=new ArrayList<ArrayList<HashMap<String,Object>>>();
   public  HttpUtil hp=new HttpUtil();
   
	public ThreadSplit(String name,int begin,int end,ArrayList<HashMap<String,Object>> list){
		this.name=name;
		this.begin=begin;
		this.end=end;
		this.list=list;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Thread.currentThread().setName(name);
		for(int i=begin;i<end;i++){
			String html="";
			HashMap<String,Object> map=list.get(i);
			String url=map.get("url").toString().trim();
			System.out.println(i+"   "+map.size()+"   "+Thread.currentThread().getName()+"        "+url);
			if(url.contains("expert_article")){
				continue;
			}
			try {
				html=hp.getHtml(url, new HashMap<String, String>(), "utf-8", 2);
				System.out.println("....................");
				parse(html);
//				records.add(parse(html));
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			records.add(e)
			count++;
		}
		System.err.println("&&&&&&&&&  "+records.size());
		
		System.err.println("thread end!    =>   "+Thread.currentThread().getName()+"     "+count);
	}
	
	public ArrayList<HashMap<String,Object>> parse(String html){
		ArrayList<HashMap<String,Object>> list=new ArrayList<HashMap<String,Object>>();
		Document doc=Jsoup.parse(html);
		try{
		Elements eles=doc.select("div.toplisttit");
		Elements div=doc.select("p#toplisttit");
		for(int i=0;i<eles.size();i++){
			HashMap<String,Object> record=new HashMap<String,Object>();
			String title=eles.get(i).text();
			String href=eles.get(i).attr("href");
			String abst=div.get(i).text();
		    record.put("title", title);
			record.put("href", href);
			record.put("abst", abst);
			list.add(record);
			System.out.println(title+"   "+href);
		}
	}catch(NullPointerException e){
		return null;
	}
		System.out.println("parse ..........");
		return list;
	}
	
	

}
