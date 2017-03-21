package temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import util.FileUtil;

public class Test {
   
	
		 public static void main(String[] args) {
			 String ip="163.204.80.62";
			 int port=20414;
			 String url="http://www.baidu.com/s?wd=ip";
			 	  
			  CredentialsProvider credsProvider = new BasicCredentialsProvider();			  
			  credsProvider.setCredentials(new AuthScope(ip,port), new UsernamePasswordCredentials("cjl","wifg7dex"));
			  CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();	
			  Builder configBuilder=RequestConfig.custom().setConnectTimeout(15*1000).setConnectionRequestTimeout(16*1000).setSocketTimeout(16*1000);
			  RequestConfig config= configBuilder.build();
			  org.apache.http.HttpHost proxyer = new org.apache.http.HttpHost(ip,port);
			  HttpGet httpGet=new HttpGet(url);
			  
	 		 httpGet.setConfig(config);
//	 		 httpGet.setHeader("Accept-Encoding","gzip, deflate, sdch");
// 			 httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.8");
// 			 httpGet.setHeader("Upgrade-Insecure-Requests","1");
// 			 httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");  
// 			 httpGet.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");  
// 			 httpGet.setHeader("Cache-Control","max-age=0");
// 		     httpGet.setHeader("Content-Type", "charset=gb2312");
// 			 httpGet.setHeader("Referer","http://weixin.sogou.com/weixin?type=2&query=%E4%B8%AD%E5%9B%BD%E9%93%B6%E8%A1%8C");
// 			 httpGet.setHeader("Cookie","CXID=FE106C9CD3A4126D209A661247471F83; SUV=002617873DED88505796DDDAE71B9790; pgv_pvi=1603656704; jrtt_at=7773eb338386c94f6602dc8c53cce335; ld=Dkllllllll2gOHTBlllllVWdgowlllll1cENOlllllUlllllpllll5@@@@@@@@@@; ad=$lllllllll2g0wbLlllllVKJ39ylllll1cEN6lllll9llllllOxlw@@@@@@@@@@@; SUID=90DD8ED3566C860A578C86F600085F73; ABTEST=0|1472972301|v1; weixinIndexVisited=1; SNUID=DAD611F19396A83D83BD1AF994C5D1DC; JSESSIONID=aaaUGH-gbnSkr4PRrWIAv; IPLOC=CN4301; LSTMV=432%2C76; LCLKINT=1081508; sct=40");
		   
		 		CloseableHttpResponse response = null;
				try {
					response = httpclient.execute(httpGet);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String html=null;
		 		System.out.println(response.getStatusLine());	
		 		//可以加判断  response.getStatusLine()  => （200ok|404|...）
		 		if(response.getStatusLine().getStatusCode()==200){
		 			BufferedReader br=null;
			 		try {
					    HttpEntity entity = response.getEntity();
					    if(entity!=null){
					    	html=EntityUtils.toString(entity,"");
					    }
					    EntityUtils.consume(entity);
					}catch(Exception e){
						e.printStackTrace();
					}finally {
//						response.close();
//					    br.close();
					}
		 		}else{
		 			System.out.println("获取页面源码出错：    "+response.getStatusLine().getStatusCode());
		 		}
		 		
			  
			 
		 }
		 public void testIP2(){
			 String url="http://shiju.tax861.gov.cn/bjds/swcx/nsajcx2015/result_json.asp?1=1&aa=2";
			 CredentialsProvider credsProvider = new BasicCredentialsProvider();			  
			 credsProvider.setCredentials(new AuthScope("104.251.235.193", 50070), new UsernamePasswordCredentials("gaodig", "gaodig2015."));
			 CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build(); 
			 HttpHost proxyer = new HttpHost("104.251.235.193", 50070);
			 Builder configBuilder =RequestConfig.custom().setConnectTimeout(15*1000).setConnectionRequestTimeout(16*1000).setSocketTimeout(16*1000);
			 configBuilder.setProxy(proxyer);
			 RequestConfig config = configBuilder.build();
			 
			 
			 
		 }
		 
		 
		 		 
		 public void testIP()throws Exception{
			 CredentialsProvider credsProvider = new BasicCredentialsProvider();
				credsProvider.setCredentials(new AuthScope("104.251.235.193", 50070), new UsernamePasswordCredentials("gaodig", "gaodig2015."));
				CloseableHttpClient httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
				try {
//					HttpHost target = new HttpHost("http://www.baidu.com/s?wd=ip", 80, "http");
					HttpHost proxy = new HttpHost("104.251.235.193", 50070);
					Builder configBuilder = RequestConfig.custom();
//					configBuilder.setProxy(proxy);
					configBuilder.setSocketTimeout(10000);
					RequestConfig config = configBuilder.build();
					HttpGet httpget = new HttpGet("http://www.baidu.com/s?wd=ip");
					httpget.setConfig(config);

					System.out.println("Executing request " + httpget.getRequestLine() + " to " +  " via "  /*proxy*/);

					CloseableHttpResponse response = httpclient.execute( httpget);
					try {
						System.out.println("----------------------------------------");
						System.out.println(response.getStatusLine());
						String html = EntityUtils.toString(response.getEntity());
						Elements es = Jsoup.parse(html).select(" div.c-border > div:nth-child(2) > div.c-span21.c-span-last.op-ip-detail > table > tbody > tr > td");
						if(es.hasText()){
						System.out.println(es.text());
						}else{
							System.out.println(html);
						}
					} finally {
						response.close();
					}
				} finally {
					httpclient.close();
				}
			}
		 
}

