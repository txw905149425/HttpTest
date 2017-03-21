package gaodig.com;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.entity.mime.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClient45 {
	public static void main(String[] args) throws ClientProtocolException, IOException {
	    String url="http://www.yeetrack.com/?p=773#HTTP-request";
	    HashMap<String, String> list=new HashMap<String,String>();
//	    list.add(new Header("",""));
		String html=getHtml(url,list);
		System.out.println(html);
	}
	public  static String getHtml(String url,HashMap<String, String> map)throws ClientProtocolException, IOException {
		String html="";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		RequestConfig requestConfig = RequestConfig.custom()  
		        .setConnectTimeout(10000).setConnectionRequestTimeout(5000)  
		        .setSocketTimeout(5000).build();  
		httpGet.setConfig(requestConfig);  
		//设置请求头
		for (String key : map.keySet()) {
			   System.out.println("key= "+ key + " and value= " + map.get(key));
			   httpGet.setHeader(key,map.get(key));		   
			  }
		
		CloseableHttpResponse response1 = httpclient.execute(httpGet); 
		try {
		    System.out.println(response1.getStatusLine());
		    HttpEntity entity = response1.getEntity();
		    if(entity!=null){
		    	 html=EntityUtils.toString(entity);
		    }
		    EntityUtils.consume(entity);
//		    System.out.println();
		}finally {
		    response1.close();
		}
		return html;
	}
	public  static String postHtml(String url)throws ClientProtocolException, IOException {
		String html="";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		//设置请求头
//		httpPost.setHeader("", "");
		CloseableHttpResponse response1 = httpclient.execute(httpPost); 
		try {
		    System.out.println(response1.getStatusLine());
		    HttpEntity entity = response1.getEntity();
		    if(entity!=null){
		    	 html=EntityUtils.toString(entity);
		    }
		    EntityUtils.consume(entity);
//		    System.out.println();
		}finally {
		    response1.close();
		}
		return html;
	}
	
	
	
	
	
}
