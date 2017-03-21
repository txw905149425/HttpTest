  package threads;

	import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;

import com.mysql.jdbc.PreparedStatement;

import util.FileUtil;
import util.HttpUtil;

	/**
	* 线程池启动
	*/
	public class TestThread2 {
		
		 public static int count=0; 
//	    public static void prepare(int nThreads,Runnable runnable){
//	    	ExecutorService service=Executors.newFixedThreadPool(nThreads);
//	    	service.execute(runnable);
//	    }
	    public static void main(String[] args) {
	    	ArrayList<String> list=FileUtil.readFileReturn("d:/test.txt");
	    	int num=list.size()%42==0?list.size()/42:list.size()/42+1;
	    	ExecutorService executor=Executors.newFixedThreadPool(num);
	    	for(int i=0;i<num;i++){
	    		String name="thread"+i;
	    		int start=i*42;
	    		int end=(i+1)*42;
	    		Demo demo=new Demo(name, start, end, list);
	    		executor.execute(demo);
	    	}
	    	executor.shutdown();
	    				
		}
	    
	    
	   static class Demo implements Runnable{
         public String name;
         public int start;
         public int end;
         public ArrayList<String> list;
         public HttpUtil hp=new HttpUtil();
          
         Demo(String name,int start,int end,ArrayList<String> list){
        	 this.name=name;
        	 this.start=start;
        	 this.end=end;
        	 this.list=list;
         }
			@Override
			public void run() {
				Thread.currentThread().setName(name);
				HashMap<String, String>  map=new HashMap<String, String>();
				String charset="gb2312";
				// TODO Auto-generated method stub
				for(int i=start;i<end;i++){
					String url=list.get(i);
					String html="";
					try {
						html=hp.getHtml(url, map, charset, 2);
						String name=Jsoup.parse(html).select("#quoteName").first().text();
		    		    System.out.println(Thread.currentThread().getName()+"   ...........      "+name);
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					count++;
				}
				System.out.println("&&&&&&&&&&&&&&&&&&&&&线程"+Thread.currentThread().getName()+"结束&&&&&&&&&&&&&&&&&&&&&&&&&&&");
				System.out.println(count);
			}
	    	
	    }
	
   }

