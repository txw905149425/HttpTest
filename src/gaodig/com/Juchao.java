package gaodig.com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class Juchao {
        public static void main(String[] args)throws ClientProtocolException, IOException  {
//        	http://www.cninfo.com.cn//disclosure/summary/stocks/summary1y/cninfo/002276.js?ver=201608081536   获取公告摘要数据链接
//			String url="http://www.cninfo.com.cn/information/stock/financialreport_.jsp?stockCode=002217";
			String uri="http://www.cninfo.com.cn//disclosure/summary/stocks/summary1y/cninfo/002276.js?ver=201608081536";
//			HashMap<String, String> map=new HashMap<String, String>();
//			map.put("Origin", "http://www.cninfo.com.cn");
//			map.put("Accept-Encoding", "gzip, deflate");
//			map.put("Accept-Language", "zh-CN,zh;q=0.8");
//			map.put("Upgrade-Insecure-Requests", "1");
//			map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
//			map.put("Content-Type", "application/x-www-form-urlencoded");
//			map.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//			map.put("Cache-Control", "max-age=0");
//			map.put("Referer", "http://www.cninfo.com.cn/information/financialreport/szsme002217.html");
//			map.put("Connection", "keep-alive");
//			ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();  
//		    data.add(new BasicNameValuePair("yyyy", "2015"));  
//		    data.add(new BasicNameValuePair("mm", "-09-30"));  //三季度 9.30 ；一季度 3.31；中期6.30；年度  12.31；
//		    data.add(new BasicNameValuePair("cwzb", "financialreport"));// 公司综合能力指标：financialreport；现金流量表：cashflow；利润表：incomestatements；资产负债表：balancesheet
//		    data.add(new BasicNameValuePair("button2", "CC%E1%BD%BB")); 
//			String html=postHtml(url, map,data);
//			System.out.println(html);
		    
//			curl 'http://www.cninfo.com.cn//disclosure/summary/stocks/summary1y/cninfo/002276.js?ver=201608081536'
//-H 'Accept-Encoding: gzip, deflate, sdch' 
//-H 'Accept-Language: zh-CN,zh;q=0.8'
//-H 'Upgrade-Insecure-Requests: 1'
//-H 'User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36' 
//-H 'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8' 
//-H 'Cache-Control: max-age=0'
//-H 'If-None-Match: "2083dbf-e8b-535c8969a9e00"' 
//-H 'Connection: keep-alive' 
//-H 'If-Modified-Since: Tue, 21 Jun 2016 12:02:00 GMT' --compressed
		    HashMap<String, String> map=new HashMap<String, String>();
			map.put("Origin", "http://www.cninfo.com.cn");
			map.put("Accept-Encoding", "gzip, deflate, sdch");
			map.put("Accept-Language", "zh-CN,zh;q=0.8");
			map.put("Upgrade-Insecure-Requests", "1");
			map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
//			map.put("Content-Type", "application/x-www-form-urlencoded");
			map.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			map.put("Cache-Control", "max-age=0");
			map.put("If-Modified-Since", "Tue, 21 Jun 2016 12:02:00 GMT");
			String str=getHtml(uri, map);
			System.out.println(str);
		}
        public  static String postHtml(String url,HashMap<String, String> map,ArrayList<NameValuePair> list)throws ClientProtocolException, IOException {
    		String html="";
    		CloseableHttpClient httpclient = HttpClients.createDefault();
    		HttpPost httpPost = new HttpPost(url);
    		//请求时间
    		RequestConfig requestConfig = RequestConfig.custom()  
    		        .setConnectTimeout(5000).setConnectionRequestTimeout(1000)  
    		        .setSocketTimeout(5000).build();  
    		httpPost.setConfig(requestConfig);  
    		//设置请求头
    		if(!map.isEmpty()){
    			for(String key : map.keySet()){
        			httpPost.setHeader(key,map.get(key));
        		}
    		}
    	  //设置请求参数
    		 httpPost.setEntity(new UrlEncodedFormEntity(list)); 
    		
    		CloseableHttpResponse response1 = httpclient.execute(httpPost); 
    		try {
    		    System.out.println(response1.getStatusLine());
    		    HttpEntity entity = response1.getEntity();
    		    if(entity!=null){
    		    	 html=EntityUtils.toString(entity);
    		    }else{
    		    	return  "catch nothing!!!";	
    		    }
    		    EntityUtils.consume(entity);
//    		    System.out.println();
    		}finally {
    		    response1.close();
    		}
    		return html;
    	}
	  public static String getHtml(String url,HashMap<String, String> map) throws ClientProtocolException, IOException {
		  String html="";
		  CloseableHttpClient httpclient = HttpClients.createDefault();
		  HttpGet httpGet=new HttpGet(url);
		  //请求时间设置
		  RequestConfig requestConfig = RequestConfig.custom()  
  		        .setConnectTimeout(5000).setConnectionRequestTimeout(1000)  
  		        .setSocketTimeout(5000).build();  
  		  httpGet.setConfig(requestConfig);  
  		  if(!map.isEmpty()){
  			  for(String key:map.keySet()){
  				  httpGet.setHeader(key,map.get(key));
  			  }
  		  }
  		CloseableHttpResponse response= httpclient.execute(httpGet);
  		System.out.println(response.getStatusLine());	  
  		try {
		    HttpEntity entity = response.getEntity();
		    if(entity!=null){
		    	 html=EntityUtils.toString(entity);
		    }
		    EntityUtils.consume(entity);
//		    System.out.println();
		}finally {
		    response.close();
		}
		  return html;
	  }
        
}
