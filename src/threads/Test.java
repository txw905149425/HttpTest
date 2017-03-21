package threads;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;

import util.FileUtil;
import util.HttpUtil;



/**
 * 
 * 实现的一个简单并发爬虫
 * 
 * 
 * */
public class Test extends Thread{
 public String str="";
 public int start;
 public int end;
 public static int count=0; //线程执行了多少次任务

 ArrayList<String> list;
 public static HttpUtil hp=new HttpUtil();
 public Test(String str,int start,int end,ArrayList<String> list){
	 this.str=str;  //线程名称
	 this.start=start; //线程开始执行时list的下标
	 this.end=end; //线程结束时list的下标
	 this.list=list;//需要处理的任务
	 
 }
	@Override
	public void run(){
		Thread.currentThread().setName(str);
		String html="";
         for(int i=start;i<end;i++){
        	 String url=list.get(i).trim();
//        	 System.out.println(url);
        	 HashMap<String, String> map=new HashMap<String, String>();
        	 try {
        		html=hp.getHtml(url, map,"gb2312", 2);
        		 String name=Jsoup.parse(html).select("#quoteName").first().text();
    		     System.out.println(Thread.currentThread().getName()+"   ...........      "+name);
    		     count++;
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
         System.out.println("count:    "+count);
	}
	
	
	public static void main(String[] args) throws ClientProtocolException, IOException ,Exception{
		 long starTime=System.currentTimeMillis();
		ArrayList<String> list1=FileUtil.readFileReturn("d:/test.txt"); //读取需要抓取的链接
//		for(int i=0;i<list.size();i++){
//			String url=list.get(i);
//			 HashMap<String, String> map=new HashMap<String, String>();
//		     String html=hp.getHtml(url, map,"gb2312",2);
//		     String name=Jsoup.parse(html).select("#quoteName").first().text();
//		     System.out.println(name);
//		}
//		204232   49051
		 split(list1);
		 long endTime=System.currentTimeMillis();
		 long Time=endTime-starTime;
		 System.out.println("*********  "+Time);
		System.out.println("count:    "+count);
	}
	
	public synchronized static void split(ArrayList<String> list){
		int len=list.size();
//		System.out.println(list.size());
		int th=len%50==0?len/50:len/50+1;//计算总共需要多少个线程    一个线程执行50个链接数
		ExecutorService executor = Executors.newFixedThreadPool(th) ;
		for(int i=0;i<th;i++){
			int star=i*50;
			int end=(i+1)*50;
			Test test=new Test("ssssss"+i,star,end>len?len:end,list);
			test.start();
		}
        
		
		
		
	}
	
	
}
