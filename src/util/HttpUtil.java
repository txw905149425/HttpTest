package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;


public class HttpUtil {
       
	 /** 
     * get方法获取链接内容
     * @param url 需要获取的页面链接
     * @param map 请求头设置
     * @param charset 编码
     * @param times 抓取次数
     * @return html 页面源码
     * @throws ClientProtocolException 
     * @throws IOException 
     */ 
	public String getHtml(String url,HashMap<String,String> map,String charset,int times)throws ClientProtocolException, IOException{
		  String html="";
		  CloseableHttpClient httpclient =null;
//				  HttpClients.createDefault();
//		  CredentialsProvider credsProvider = new BasicCredentialsProvider();
//		 credsProvider.setCredentials(new AuthScope("ip",8080), new UsernamePasswordCredentials("账号","密码"));
//		  Builder configBuilder = RequestConfig.custom();
//		  httpclient.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "gb2312");  
////		  org.apache.http.HttpHost proxyer = new org.apache.http.HttpHost(netInterface.ip, netInterface.port);
//		  configBuilder.setProxy(proxyer);
		  
		  Builder configBuilder = RequestConfig.custom();
		  CredentialsProvider credsProvider = new BasicCredentialsProvider();
		  credsProvider.setCredentials(new AuthScope("163.204.80.62",20414), new UsernamePasswordCredentials("cjl","wifg7dex"));
		  httpclient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
		  org.apache.http.HttpHost proxyer = new org.apache.http.HttpHost("163.204.80.62",20414);
		  RequestConfig requestConfig = null;
		  try {
//			  configBuilder.setProxy(proxyer);
			  requestConfig=configBuilder.setConnectTimeout(15*1000).setConnectionRequestTimeout(16*1000).setSocketTimeout(30*1000).build();
			} catch (Exception e) {
				e.printStackTrace();
			}  
		  for(int i=0;i<times;i++){
			  HttpGet httpGet=new HttpGet(url);
			  //请求参数设置
			 
//			 httpGet.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "gb2312");  
	 		 httpGet.setConfig(requestConfig);  
	 		 httpGet.setHeader("Accept-Encoding","gzip, deflate, sdch");  
 			 httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.8");  
 			 httpGet.setHeader("Upgrade-Insecure-Requests","1");  
 			 httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");  
 			 httpGet.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");  
 			 httpGet.setHeader("Cache-Control","max-age=0");  
// 		     httpGet.setHeader("Content-Type", "charset=gb2312");
// 			 httpGet.setHeader("Referer","http://weixin.sogou.com/weixin?type=2&query=%E4%B8%AD%E5%9B%BD%E9%93%B6%E8%A1%8C");
// 			 httpGet.setHeader("Cookie","CXID=FE106C9CD3A4126D209A661247471F83; SUV=002617873DED88505796DDDAE71B9790; pgv_pvi=1603656704; jrtt_at=7773eb338386c94f6602dc8c53cce335; ld=Dkllllllll2gOHTBlllllVWdgowlllll1cENOlllllUlllllpllll5@@@@@@@@@@; ad=$lllllllll2g0wbLlllllVKJ39ylllll1cEN6lllll9llllllOxlw@@@@@@@@@@@; SUID=90DD8ED3566C860A578C86F600085F73; ABTEST=0|1472972301|v1; weixinIndexVisited=1; SNUID=DAD611F19396A83D83BD1AF994C5D1DC; JSESSIONID=aaaUGH-gbnSkr4PRrWIAv; IPLOC=CN4301; LSTMV=432%2C76; LCLKINT=1081508; sct=40");
	 		  if(!map.isEmpty()){
	 			  for(String key:map.keySet()){
	 				  httpGet.setHeader(key,map.get(key));
	 			  }
	 		  }
	 		CloseableHttpResponse response= httpclient.execute(httpGet);
//	 		System.out.println(response.getParams().getParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET));
	 		System.out.println(response.getStatusLine());	
	 		//可以加判断  response.getStatusLine()  => （200ok|404|...）
	 		try {
			    HttpEntity entity = response.getEntity();
				ContentType contentType = ContentType.getOrDefault(entity);
				Charset defaultCharset = contentType.getCharset();
				if(entity!=null){
					if (defaultCharset == null) {
						byte[] raw = EntityUtils.toByteArray(entity);
						html = new String(raw);
						String charsetstr = StringUtil.getCharSet(html);
						if (!StringUtil.isEmpty(charsetstr)){
							html = new String(raw, charsetstr);
						}else {
							html = new String(raw, "utf-8");
						}
					} else {
						html = EntityUtils.toString(entity, charset);
					}
				}
//			    if(entity!=null){
//			    	 html=EntityUtils.toString(entity,charset);
//			    	 BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), charset));  //编码
//			    	 String line;  
//			    	 while((line = br.readLine()) != null){  
//			    		 html+=line;
//			    	     System.out.println(line);  
//			    	 }
//			    	 br.close();
//			    	 System.out.println(html);
//			    }
			    EntityUtils.consume(entity);
			}finally {
			    response.close();
			}
		 	if(html!=null&&html!=""){
		 		break;
		 	}
		  }
		  return html;
	}
	
	 /** 
     * ���ݸ���������get������ȡԴ���� 
     * @param url ���������� 
     * @param map ����ͷ
     * @param list �������
     * @param num ����ʱ��
     * @param times �������
     * @return htmlԴ����
     * @throws ClientProtocolException 
     * @throws IOException 
     */ 
	public String postHtml(String url,HashMap<String,String> map,ArrayList<NameValuePair> list,int num,int times) throws ClientProtocolException, IOException{
		  String html="";
		  CloseableHttpClient httpclient = HttpClients.createDefault();
		  for(int i=0;i<times;i++){
			  HttpPost httpPost=new HttpPost(url);
			  //����ʱ������
			  RequestConfig requestConfig = RequestConfig.custom(). setConnectTimeout(10*1000)
			  .setConnectionRequestTimeout(10*1000)  
			  .setSocketTimeout(10*1000).build();
			  httpPost.setConfig(requestConfig);
			  httpPost.setHeader("Accept-Encoding","gzip, deflate, sdch");  
			  httpPost.setHeader("Accept-Language","zh-CN,zh;q=0.8");
			  httpPost.setHeader("Upgrade-Insecure-Requests","1");
			  httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");  
			  httpPost.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");  
			  httpPost.setHeader("Cache-Control","max-age=0");
	 		  if(!map.isEmpty()){
	 			  for(String key:map.keySet()){
	 				 httpPost.setHeader(key,map.get(key));
	 			  }
	 		  }
	 		  if(!list.isEmpty()){
	 			 httpPost.setEntity(new UrlEncodedFormEntity(list)); 
	 		  }
	 		 CloseableHttpResponse response= httpclient.execute(httpPost);
		 	 System.out.println(response.getStatusLine());	
	 		try {
			    HttpEntity entity = response.getEntity();
			    if(entity!=null){
			    	 html=EntityUtils.toString(entity);
			    }
			    EntityUtils.consume(entity);
			}finally {
			    response.close();
			}
		 	if(html!=null&&html!=""){
		 		break;
		 	}
		  }
		  return html;
	}
	/**
     * 
     * @param url 请求地址
     * @param map 请求参数 
     * @param res 返回结果
     * @param timeOut 超时时间(min)
     * @param useProxy 是否使用代理
     * @return
     * @throws Exception
     */
//    public static String get(String url,Map<String,String> map, String res, int timeOut, boolean useProxy, boolean needCert) throws Exception{
//        RequestConfig config = null;
//        CloseableHttpClient httpClient= HttpClients.createDefault();
//        CloseableHttpResponse response=null;
//        
//        URIBuilder uriBuilder=new URIBuilder(url);
//        for (Entry<String, String> entry : map.entrySet()) {
//            uriBuilder=uriBuilder.setParameter(entry.getKey(),entry.getValue());
//        }
//        URI uri=uriBuilder.build();
//        HttpGet httpGet=new HttpGet(uri);
//        try {
//            if(useProxy){
//                HttpHost proxy = new HttpHost("代理IP",11,"http");
//                config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(timeOut * 1000 * 60).build();
//            }else{
//                config = RequestConfig.custom().setConnectTimeout(timeOut * 1000 * 60).build();  
//            }
//            
//            httpGet.setConfig(config);
//            ECommonUtil.getLog().info("执行get请求" + httpGet.getRequestLine());
//            response = httpClient.execute(httpGet);  
//            HttpEntity entity = response.getEntity();  
//            if (entity != null) {  
//                ECommonUtil.getLog().info("响应状态:"+ response.getStatusLine()); 
//                String rStr=EntityUtils.toString(entity,"UTF-8");
//                ECommonUtil.getLog().info("响应内容:" + rStr);
//                if(HttpStatus.SC_OK==response.getStatusLine().getStatusCode()){
//                    res=rStr;
//                }
//                EntityUtils.consume(entity);  
//            }
//        }catch (Exception e) {
//            ECommonUtil.getLog().info("http请求错误"+e);
//            throw e;
//        }finally{
//            if(httpGet!=null){
//                httpGet.releaseConnection();
//            }
//            if(response!=null){
//                response.close();
//            }
//            if(httpClient!=null){
//                httpClient.close();
//            }
//        }
//        return res;
//    }
    
	public static void main(String args[]){
	 StringBuffer sb = new StringBuffer();
	 //创建HttpClient实例
	 HttpClient client = getHttpClient();
	 //创建httpGet
	 HttpGet httpGet = new HttpGet("http://www.csdn.net");
	 //执行
	 try {
	  HttpResponse response = client.execute(httpGet);
	   
	  HttpEntity entry = response.getEntity();
	   
	  if(entry != null)
	  {
	   InputStreamReader is = new InputStreamReader(entry.getContent());
	   BufferedReader br = new BufferedReader(is);
	   String str = null;
	   while((str = br.readLine()) != null)
	   {
	    sb.append(str.trim());
	   }
	   br.close();
	  }
	   
	 } catch (ClientProtocolException e) {
	  // TODO Auto-generated catch block
	  e.printStackTrace();
	 } catch (IOException e) {
	  // TODO Auto-generated catch block
	  e.printStackTrace();
	 }
	 System.out.println(sb.toString());
	}
	//设置代理
	public static HttpClient getHttpClient() {
//	 CloseableHttpClient httpClient= HttpClients.createDefault();
	 DefaultHttpClient httpClient = new DefaultHttpClient();
	 String proxyHost = "proxycn2.huawei.com";
	 int proxyPort = 8080;
	 String userName = "china\\******";
	 String password = "*******";
	 httpClient.getCredentialsProvider().setCredentials(new AuthScope(proxyHost, proxyPort),new UsernamePasswordCredentials(userName, password));
	 HttpHost proxy = new HttpHost(proxyHost,proxyPort);
	 httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
	 return httpClient;
	}   
}